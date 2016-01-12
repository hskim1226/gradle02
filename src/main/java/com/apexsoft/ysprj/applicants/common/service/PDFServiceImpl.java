package com.apexsoft.ysprj.applicants.common.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.exception.YSBizNoticeException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.file.exception.NotFoundInS3Exception;
import com.apexsoft.framework.persistence.file.exception.PDFMergeException;
import com.apexsoft.framework.persistence.file.manager.FilePersistenceManager;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationStatus;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.common.domain.ParamForPDFDocument;
import com.apexsoft.ysprj.applicants.common.util.FilePathUtil;
import com.apexsoft.ysprj.applicants.common.util.StreamUtil;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

/**
 * Created by hanmomhanda on 15. 2. 22.
 */
@Service
public class PDFServiceImpl implements PDFService {

    @Autowired
    CommonDAO commonDAO;

    @Autowired
    DocumentService documentService;

    @Resource
    FilePersistenceManager s3PersistenceManager;

    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;

    @Value("#{app['s3.bucketName']}")
    private String s3BucketName;

    @Value("#{app['s3.midPath']}")
    private String s3MidPath;

    @Value("#{app['s3.storageClass']}")
    private String s3StorageClass;

    private static final Logger logger = LoggerFactory.getLogger(PDFServiceImpl.class);

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";

    /**
     * 지원자 별 PDF 묶음 파일 생성
     * 첨부 파일을 S3로부터 서버 로컬에 내려받아 먼저 합치고, 페이지를 먹인 후,
     * 이미 생성되어 있는 지원서 파일과 합쳐서 최종 파일을 생성하고,
     * S3에 업로드
     *
     * @param application
     * @return
     */
    @Override
    public ExecutionContext genAndUploadPDFByApplicants(Application application) {

        ExecutionContext ec = new ExecutionContext();
        AmazonS3 s3 = new AmazonS3Client();

        int applNo = application.getApplNo();
        String userId = application.getUserId();
        ParamForPDFDocument param = new ParamForPDFDocument(applNo, "pdf");

        // DB에서 첨부 파일 정보 조회
        List<ApplicationDocument> pdfList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectPDFByApplNo", param, ApplicationDocument.class);

        // 첨부 파일 정보를 토대로 S3에서 각 첨부 파일 다운로드 하고 암호화 여부 체크
        List<ByteArrayOutputStream> unencryptedPdfFromS3List = getByteArrayOutputStreamedPdfListFromS3(s3, pdfList);

        // 페이지 넘버링을 위해 첨부 파일 머지
        File rawMergedFile = getRawMergedFile(unencryptedPdfFromS3List, application);

        // 페이지 넘버링 적용
        File numberedMergedFile = getPageNumberedPDF(rawMergedFile, application);

        // 로컬에 생성된 지원서 파일
        File applicationFormFile = new File(getPdfDirFullPath(application), FilePathUtil.getApplicationFormFileName(userId));

        // 로컬에 생성된 지원서 파일과 넘버링 된 합친 PDF를 또 합치기
        File mergedApplicationFormFile = getMergedApplicationFormFile(applicationFormFile, numberedMergedFile, application);

        // 최종 머지된 파일은 결제 전, 후에 S3에 업로드
        uploadToS3(s3, mergedApplicationFormFile, applNo);

        // 결제 완료 시 수험번호 채번된 수험표와 지원서를 S3에 업로드
        if (ApplicationStatus.COMPLETED.codeVal().equals(application.getApplStsCode())) {
            // 수험표 파일
            File applicationSlipFile = getApplicationSlipFile(application);
            uploadToS3(s3, applicationSlipFile, applNo); // 수험표는 결제 완료 후에 생성 및 업로드
            applicationSlipFile.delete();

            // 지원서 파일
            uploadToS3(s3, applicationFormFile, applNo);
        }

        // 중간 작업 파일 삭제
        // 여기서 지우면 파일 지우기 위한 I/O 추가 발생하지만 저장 공간은 절약
        // 나중에 batch로 지우면 I/O 는 절약하지만 지우기 전까지 저장 공간은 낭비
        rawMergedFile.delete();
        applicationFormFile.delete();
        numberedMergedFile.delete();
        mergedApplicationFormFile.delete();

        return ec;
    }

    /**
     * S3에서 PDF 파일을 다운로드 하고
     * 암호화 되지 않은 PDF 파일은 BAOS에 담아 리스트로 반환
     *
     * @param s3
     * @param pdfList
     * @return
     */
    private List<ByteArrayOutputStream> getByteArrayOutputStreamedPdfListFromS3(AmazonS3 s3, List<ApplicationDocument> pdfList) {
        List<ByteArrayOutputStream> unencryptedPdfBaosList = new ArrayList<ByteArrayOutputStream>();

        for (ApplicationDocument aDoc : pdfList) {
            String filePath = FilePathUtil.recoverAmpersand(aDoc.getFilePath());

            // 사용자가 직접 입력한 파일이면 다운로드
            if (isUserUploadedFile(aDoc)) {

                // S3에 업로드 되어 있는 첨부 파일 다운로드
                S3Object object = getS3Object(s3, filePath);

                // 다운로드 한 PDF 파일 내용을 스트림 형태로 여러번 사용하기 위해 BAOS에 담아둔다.
                InputStream inputStream = object.getObjectContent();
                ByteArrayOutputStream baos = StreamUtil.getBaosFromInputStream(inputStream);

                unencryptedPdfBaosList.add(baos);
            }
        }

        return unencryptedPdfBaosList;
    }

    private boolean isUserUploadedFile(ApplicationDocument aDoc) {
        String docItemName = aDoc.getDocItemName();
        return !"지원서".equals(docItemName) &&
               !"수험표".equals(docItemName) &&
               !StringUtil.getEmptyIfNull(aDoc.getDocItemCode()).equals(StringUtil.EMPTY_STRING);
    }

    private S3Object getS3Object(AmazonS3 s3, String filePath) {
        S3Object object;
        try {
            object = s3.getObject(new GetObjectRequest(s3BucketName, filePath));
        } catch (Exception e) {
            ExecutionContext ec1 = new ExecutionContext(ExecutionContext.FAIL);
            logger.error("Err in " + Thread.currentThread().getStackTrace()[1] +
                    ", bucketName : [" + s3BucketName + "], objectKey : [" + filePath +"]" + e.getMessage());
            throw new NotFoundInS3Exception(ec1, "messageCode", "errorCode");
        }
        return object;
    }

    /**
     * 암호화 되지 않은 PDF 파일들에 대한 BAOS 리스트를 받아
     * 한 파일로 합친다.
     *
     * @param unencryptedPdfFromS3List    암호화 되지 않은 pdf 파일의 BAOS 리스트
     * @param application    원서 정보
     * @return
     */
    private File getRawMergedFile(List<ByteArrayOutputStream> unencryptedPdfFromS3List, Application application) {
        ExecutionContext ec = new ExecutionContext();

        // S3에 대한 OutputStream을 가져올 방법이 없어서
        // mergerUtil.setDestinationStream() 를 사용할 수 없고,
        // 머지된 파일을 APP 로컬에 저장 후 다시 S3로 보내는 방법 밖에 없음
        // 성능 상으로 PDF numbering을 하기 위한 파일 저장은 로컬에 하는 것이 맞을 듯
        String tempSlashReplacer = "_";


        String uploadDirFullPath = getPdfDirFullPath(application);
        String rawMergedFileFullPath = FilePathUtil.encodeColonSlash(FilePathUtil.getRawMergedFileFullPath(uploadDirFullPath, application.getApplNo()), tempSlashReplacer);


        // 파일 넘버링을 위한 첨부 파일 합치기
        PDFMergerUtility mergerUtil = new PDFMergerUtility();
        mergerUtil.setDestinationFileName(rawMergedFileFullPath);
        //TODO : S3를 향한 OutputStream을 가져올 수 있다면 아래 방식 가능
        //mergerUtil.setDestinationStream(OutputStream to S3);

        for (ByteArrayOutputStream baos : unencryptedPdfFromS3List) {
            mergerUtil.addSource(new ByteArrayInputStream(baos.toByteArray()));
        }

        try {
            mergerUtil.setIgnoreAcroFormErrors(true);
            mergerUtil.mergeDocuments(MemoryUsageSetting.setupMixed(500*1024*1024));
        } catch (IOException e) {
            logger.error("merge files from S3 failed, applNo : " + application.getApplNo() + ", destFileName : " + mergerUtil.getDestinationFileName());
            logger.error(e.getMessage());
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U06101"));
            ec.setErrCode("ERR1101");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(application.getApplNo()));
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizNoticeException(ec);
        } catch (Throwable t) {
            logger.debug("merging PDF files fails, in PDFServiceImpl.getRawMergedFile(), applNo : " + application.getApplNo());
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U06102"));
            ec.setErrCode("ERR1104");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(application.getApplNo()));
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new PDFMergeException(ec, "U06102", "ERR1104");
        }
        return new File(rawMergedFileFullPath);
    }

    /**
     * 합쳐진 첨부 파일 우상단에 '현재 페이지/전체 페이지' 텍스트를 추가한다.
     *
     * @param rawMergedFile  쪽수 안 매겨진 합친 pdf 파일
     * @param application    원서 정보
     * @return
     */
    private File getPageNumberedPDF(File rawMergedFile, Application application) {

        ExecutionContext ec = new ExecutionContext();
        PDDocument mergedPDDocument = null;
        String applicationFilePath = getPdfDirFullPath(application);
        String numberedMergedFileFullPath = FilePathUtil.encodeColonSlash(FilePathUtil.getNumberedMergedFileFullPath(applicationFilePath, application.getApplNo()), "_");

        try {
            mergedPDDocument = PDDocument.load(rawMergedFile);

            PDFont font = PDType1Font.HELVETICA;
            float fontSize = 15.0f;
            PDPageTree pageTree = mergedPDDocument.getPages();
            int length = mergedPDDocument.getNumberOfPages();
            Iterator<PDPage> it = pageTree.iterator();
            int i = 0;
            while (it.hasNext()) {
                PDPage page = it.next();
                PDRectangle pageSize = page.getMediaBox();
                String strPage = new StringBuilder().append(++i).append("/").append(length).toString();
                float stringWidth = font.getStringWidth(strPage)*fontSize/1000f;
                float pageWidth = pageSize.getWidth();
                float pageHeight = pageSize.getHeight();
                Matrix m4Position = new Matrix();
                m4Position.translate(pageWidth - stringWidth - 15, pageHeight - 20);
                PDPageContentStream contentStream = new PDPageContentStream(mergedPDDocument, page, true, true, true);
                contentStream.beginText();
                contentStream.setFont(font, fontSize);
                contentStream.setTextMatrix(m4Position);
                contentStream.showText(strPage);
                contentStream.endText();
                contentStream.close();
            }
            mergedPDDocument.save(numberedMergedFileFullPath);
        } catch (IOException e) {
            exceptionThrower(application.getApplNo(), ec);
        } finally {
            if (mergedPDDocument != null) {
                try {
                    mergedPDDocument.close();
                } catch (IOException e) {
                    exceptionThrower(application.getApplNo(), ec);
                }
            }
        }

        return new File(numberedMergedFileFullPath);
    }

    // Local 다운로드 위치. S3 버킷 내의 path와 동일하다.
    private String getPdfDirFullPath(Application application) {
        return FilePathUtil.getUploadDirectoryFullPath(fileBaseDir, s3MidPath, application.getAdmsNo(), application.getUserId(), application.getApplNo());
    }

    /**
     * 원서 파일과 넘버링 된 첨부 파일을 합쳐 최종 파일을 반환한다.
     *
     * @param applicationFormFile    원서 파일
     * @param numberedMergedFile    넘버링 된 첨부 파일 묶음 파일
     * @param application    원서 정보
     * @return
     */
    private File getMergedApplicationFormFile(File applicationFormFile, File numberedMergedFile, Application application) {
        ExecutionContext ec = new ExecutionContext();
        String downloadedPdfDirFullPath = getPdfDirFullPath(application);
        int applNo = application.getApplNo();
        String mergedApplicationFormFilePath = FilePathUtil.getFinalMergedFileFullPath(downloadedPdfDirFullPath, applNo);
        try {
            PDFMergerUtility lastMergeUtil = new PDFMergerUtility();
            lastMergeUtil.addSource(applicationFormFile);
            lastMergeUtil.addSource(numberedMergedFile);
            lastMergeUtil.setDestinationFileName(mergedApplicationFormFilePath);
            lastMergeUtil.mergeDocuments(MemoryUsageSetting.setupMixed(500*1024*1024));
            logger.debug("All Merge 성공, applNo : " + applNo);
        } catch (IOException e) {
            exceptionThrower(applNo, ec);
        }
        return new File(mergedApplicationFormFilePath);
    }

    // PDF 처리 관련 예외 처리
    private void exceptionThrower(int applNo, ExecutionContext ec) {
        ec.setResult(ExecutionContext.FAIL);
        ec.setMessage(MessageResolver.getMessage("U801"));
        ec.setErrCode("ERR1101");
        Map<String, String> errorInfo = new HashMap<String, String>();
        errorInfo.put("applNo", String.valueOf(applNo));
        ec.setErrorInfo(new ErrorInfo(errorInfo));
        throw new YSBizException(ec);
    }

    private File getApplicationSlipFile(Application application) {
        String applicationSlipFileFullPath = FilePathUtil.getUploadDirectoryFullPath(
                fileBaseDir, s3MidPath, application.getAdmsNo(), application.getUserId(), application.getApplNo()) + "/" +
                FilePathUtil.getApplicationSlipFileName(application.getUserId());
        return new File(applicationSlipFileFullPath);
    }

    private void uploadToS3(AmazonS3 s3, File file, int applNo) {
        // 머지된 최종 파일을 S3에 업로드
        ObjectMetadata meta = createS3ObjMetaData(file);
        String s3FilePath = getS3FilePath(file);
        try {
            s3.putObject(new PutObjectRequest(s3BucketName,
                    s3FilePath,
                    file)
                    .withMetadata(meta)
                    .withCannedAcl(CannedAccessControlList.AuthenticatedRead.PublicRead));
        } catch (Exception e) {
            logger.error("Err in uploading final file to S3, s3BucketName : [" + s3BucketName + "], applNo : [" + applNo + "], ObjectKey : [" + FilePathUtil.getS3PathFromLocalFullPath(file.getAbsolutePath(), fileBaseDir) + "]");
            logger.error(e.getMessage());
            throw new YSBizException(e);
        }
    }

    // 업로드 할 파일 정보로 S3 ObjectMetaData 생성
    private ObjectMetadata createS3ObjMetaData(File file) {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentEncoding("UTF-8");
        meta.setContentLength(file.length());
        meta.setHeader("x-amz-storage-class", s3StorageClass);
        return meta;
    }

    // S3 버킷 아래에 업로드 할 위치 반환
    private String getS3FilePath(File file) {
        String applPaperLocalFilePath = file.getAbsolutePath();
        String s3FilePath = FilePathUtil.getS3PathFromLocalFullPath(applPaperLocalFilePath, fileBaseDir);
        return s3FilePath;
    }


}
