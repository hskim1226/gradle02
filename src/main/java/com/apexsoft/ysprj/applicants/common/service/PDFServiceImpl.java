package com.apexsoft.ysprj.applicants.common.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.file.exception.NotFoundInS3Exception;
import com.apexsoft.framework.persistence.file.manager.FilePersistenceManager;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationStatus;
import com.apexsoft.ysprj.applicants.application.domain.GradnetPDDocument;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.common.domain.ParamForPDFDocument;
import com.apexsoft.ysprj.applicants.common.util.FilePathUtil;
import com.apexsoft.ysprj.applicants.common.util.StreamUtil;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import org.apache.commons.io.FileUtils;
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

        // 첨부 파일 정보를 토대로 사용자가 업로드한 첨부 파일을 S3에서 다운로드
        List<ByteArrayOutputStream> userUploadedPdfStreamFromS3List = getByteArrayOutputStreamedPdfListFromS3(s3, pdfList);

        // 원서 미리보기 또는 결제 완료를 통해 로컬에 생성된 지원서 파일 쪽수 계산
//        File applicationFormFile = new File(getPdfDirFullPath(application), FilePathUtil.getApplicationFormFileName(userId));
//        int applicationFormPageCounts = getPdfPageCount(applicationFormFile);



//        // 로컬에 생성된 지원서 파일과 사용자가 업로드 한 PDF 합치기
//        File mergedFile = getMergedApplicationFormFile(applicationFormFile, userUploadedPdfStreamFromS3List, application);
//
//        // 합친 파일에 페이지 넘버링 적용 TODO 99대신 실제 총 페이지 수
//        File numberedMergedFile = getPageNumberedPDF(mergedFile, applicationFormPageCounts + 1, 1, 99, application);
//
//        // 최종 파일은 결제 전, 후에 S3에 업로드
//        uploadToS3(s3, numberedMergedFile, applNo);



        // 파일 합치지 않고 개별 파일마다 페이지 넘버링
        List<File> userUploadedFiles = getFileListFromS3(s3, pdfList);
        List<File> numberedFiles = getPageNumberedPDFs(userUploadedFiles, application);

        // 파일 목록 루프돌며 S3에 업로드
        for (File file : numberedFiles) {
            uploadToS3(s3, file, applNo);
        }


        // 결제 완료 시 수험번호 채번된 수험표와 지원서를 S3에 업로드
        if (ApplicationStatus.COMPLETED.codeVal().equals(application.getApplStsCode())) {
            // 수험표 파일
            File applicationSlipFile = getApplicationSlipFile(application);
            uploadToS3(s3, applicationSlipFile, applNo); // 수험표는 결제 완료 후에 생성 및 업로드
            applicationSlipFile.delete();

            // 결제 완료를 통해 로컬에 생성된 지원서
            File applicationFormFile = getApplicationFormFile(application);
            uploadToS3(s3, applicationFormFile, applNo);
            applicationFormFile.delete();
        }

        // 중간 작업 파일 삭제
        // 여기서 지우면 파일 지우기 위한 I/O 추가 발생하지만 저장 공간은 절약
        // 나중에 batch로 지우면 I/O 는 절약하지만 지우기 전까지 저장 공간은 낭비

//        mergedFile.delete();
//        numberedMergedFile.delete();
        for (File file: numberedFiles) {
            file.delete();
        }

        return ec;
    }



    /**
     * pdf 파일의 페이지수 계산
     *
     * @param pdfFile
     * @return
     */
    private int getPdfPageCount(Object pdfFile, Application application) {
        int pageCounts = -1;
        PDDocument pdDocument = null;
        GradnetPDDocument gradnetPDDocument = null;
        ExecutionContext ec = new ExecutionContext();
        try {
            gradnetPDDocument = getPDDocument(pdfFile, application);
            pdDocument = gradnetPDDocument.getPdDocument();
            pageCounts = pdDocument.getNumberOfPages();
        } catch (IOException e) {
            exceptionThrower(gradnetPDDocument.getMetaDataMap(), ec);
        } finally {
            if (pdDocument != null) {
                try {
                    pdDocument.close();
                } catch (IOException e) {
                    exceptionThrower(gradnetPDDocument.getMetaDataMap(), ec);
                }
            }
        }

        return pageCounts;
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

    // 사용자가 직접 업로드 한 파일인지 판별
    private boolean isUserUploadedFile(ApplicationDocument aDoc) {
        String docItemName = aDoc.getDocItemName();
        return !"지원서".equals(docItemName) &&
               !"수험표".equals(docItemName) &&
               !StringUtil.getEmptyIfNull(aDoc.getDocItemCode()).equals(StringUtil.EMPTY_STRING);
    }

    private List<File> getFileListFromS3(AmazonS3 s3, List<ApplicationDocument> pdfList) {
        List<File> userUploadedFiles = new ArrayList<>();

        for (ApplicationDocument aDoc : pdfList) {
            String filePath = FilePathUtil.recoverAmpersand(aDoc.getFilePath());

            // 사용자가 직접 입력한 파일이면 다운로드
            if (isUserUploadedFile(aDoc)) {
                // S3에 업로드 되어 있는 첨부 파일 다운로드
                S3Object object = getS3Object(s3, filePath);

                // 다운로드 한 PDF 파일 내용을 스트림 형태로 여러번 사용하기 위해 BAOS에 담아둔다.
                String s3FilePath = object.getKey();
                InputStream inputStream = object.getObjectContent();
                String targetFilePath = FilePathUtil.getLocalFullPathFromS3Path(fileBaseDir, s3FilePath);
                File file = new File(targetFilePath);
                try {
                    FileUtils.copyInputStreamToFile(inputStream, file);
                } catch (IOException e) {
                    exceptionThrower("Stream to File error, FileName", file.getAbsolutePath(), new ExecutionContext());
                }
                userUploadedFiles.add(file);
            }
        }


        return userUploadedFiles;

    }

    // S3에서 파일 다운로드
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
     * 지원서 파일과 사용자가 업로드한 PDF 파일 합치기
     *
     * @param applicationFormFile
     * @param userUploadedPdfStreamList
     * @param application
     * @return
     */
    private File getMergedApplicationFormFile(File applicationFormFile, List<ByteArrayOutputStream> userUploadedPdfStreamList, Application application) {
        ExecutionContext ec = new ExecutionContext();
        String downloadedPdfDirFullPath = getPdfDirFullPath(application);
        int applNo = application.getApplNo();
        String mergedApplicationFormFilePath = FilePathUtil.getFinalMergedFileFullPath(downloadedPdfDirFullPath, applNo);
        try {
            PDFMergerUtility lastMergeUtil = new PDFMergerUtility();
            lastMergeUtil.addSource(applicationFormFile);
            for (ByteArrayOutputStream baos : userUploadedPdfStreamList) {
                lastMergeUtil.addSource(new ByteArrayInputStream(baos.toByteArray()));
            }
            lastMergeUtil.setDestinationFileName(mergedApplicationFormFilePath);
            lastMergeUtil.setIgnoreAcroFormErrors(true);
            lastMergeUtil.mergeDocuments(MemoryUsageSetting.setupMixed(500*1024*1024));
            logger.debug("All Merge 성공, applNo : " + applNo);
        } catch (IOException e) {
            exceptionThrower("applNo", String.valueOf(applNo), ec);
        }
        return new File(mergedApplicationFormFilePath);
    }

    /**
     * PDF 파일 우상단에 '현재 페이지/전체 페이지' 텍스트를 추가한다.
     * @param fileOrInputStream  쪽수를 매길 파일
     * @param startPage  쪽수를 매기기 시작할 페이지(0이 아니라 1부터 시작하는 숫자)
     * @param startNo  시작 쪽수 번호
     * @param endNo  전체 쪽수 번호, 0이나 음수를 입력하면 해당 파일의 페이지수를 기준으로 startPage를 고려해서 자동 계산
     * @param application  원서 정보
     * @return
     */
    private File getPageNumberedPDF(Object fileOrInputStream, int startPage, int startNo, int endNo, Application application) {
        ExecutionContext ec = new ExecutionContext();
        GradnetPDDocument pDDocument = null;
//        String applicationFileDirPath = getPdfDirFullPath(application);
//        String targetFilePath = FilePathUtil.encodeColonSlash(FilePathUtil.getFinalMergedFileFullPath(applicationFileDirPath, application.getApplNo()), "_");
        String targetFilePath = getTargetFilePath(fileOrInputStream, application);
        try {
            pDDocument = getPDDocument(fileOrInputStream, application);
            PDFont font = PDType1Font.HELVETICA;
            float fontSize = 15.0f;
            PDPageTree pageTree = pDDocument.getPages();
            int length = (endNo > 0) ? endNo : pDDocument.getNumberOfPages() - startPage + 1;
            Iterator<PDPage> it = pageTree.iterator();
            int i = 1;
            while (i < startPage && it.hasNext()) {
                it.next();
                i++;
            }
            while (it.hasNext()) {
                PDPage page = it.next();
                PDRectangle pageSize = page.getMediaBox();
                String strPage = new StringBuilder().append((i++ + startNo) - startPage).append("/").append(length).toString();
                float stringWidth = font.getStringWidth(strPage)*fontSize/1000f;
                float pageWidth = pageSize.getWidth();
                float pageHeight = pageSize.getHeight();
                Matrix m4Position = new Matrix();
                m4Position.translate(pageWidth - stringWidth - 15, pageHeight - 20);
                PDPageContentStream contentStream = new PDPageContentStream(pDDocument, page, true, true, true);
                contentStream.beginText();
                contentStream.setFont(font, fontSize);
                contentStream.setTextMatrix(m4Position);
                contentStream.showText(strPage);
                contentStream.endText();
                contentStream.close();
            }
            pDDocument.save(targetFilePath);
        } catch (IOException e) {
            exceptionThrower("applNo", String.valueOf(application.getApplNo()), ec);
        } finally {
            if (pDDocument != null) {
                try {
                    pDDocument.close();
                } catch (IOException e) {
                    exceptionThrower("applNo", String.valueOf(application.getApplNo()), ec);
                }
            }
        }

        return new File(targetFilePath);
    }

    // Local 다운로드 위치. S3 버킷 내의 path와 동일하다.
    private String getPdfDirFullPath(Application application) {
        return FilePathUtil.getUploadDirectoryFullPath(fileBaseDir, s3MidPath, application.getAdmsNo(), application.getUserId(), application.getApplNo());
    }

    private String getTargetFilePath(Object obj, Application application) {
        String targetFilePath = null;
        if (obj instanceof File) {
            File file = (File)obj;
            targetFilePath = file.getAbsolutePath();
        } else if (obj instanceof InputStream) {
            String fileDirPath = getPdfDirFullPath(application);
            targetFilePath = FilePathUtil.encodeColonSlash(FilePathUtil.getFinalMergedFileFullPath(fileDirPath, application.getApplNo()), "_");
        }
        return targetFilePath;
    }

    private GradnetPDDocument getPDDocument(Object obj, Application application) throws IOException {
        PDDocument pddocument = null;
        GradnetPDDocument gradnetPDDocument = new GradnetPDDocument();
        Map<String, String> metadataMap = gradnetPDDocument.getMetaDataMap();
        if (obj instanceof File) {
            File file = (File)obj;
            pddocument = PDDocument.load(file);
            metadataMap.put("applNo", String.valueOf(application.getApplNo()));
            metadataMap.put("filePath", file.getAbsolutePath());
        } else if (obj instanceof InputStream) {
            InputStream is = (InputStream)obj;
            pddocument = PDDocument.load(is);
            metadataMap.put("applNo", String.valueOf(application.getApplNo()));
            metadataMap.put("filePath", is.toString());
        } else {
            throw new IOException();
        }
        gradnetPDDocument.setPdDocument(pddocument);

        return gradnetPDDocument;
    }


    // PDF 처리 관련 예외 처리
    private void exceptionThrower(String infoKey, String infoValue, ExecutionContext ec) {
        ec.setResult(ExecutionContext.FAIL);
        ec.setMessage(MessageResolver.getMessage("U801"));
        ec.setErrCode("ERR1101");
        Map<String, String> errorInfo = new HashMap<String, String>();
        errorInfo.put(infoKey, infoValue);
        ec.setErrorInfo(new ErrorInfo(errorInfo));
        throw new YSBizException(ec);
    }

    private void exceptionThrower(Map<String, String> errInfo, ExecutionContext ec) {
        ec.setResult(ExecutionContext.FAIL);
        ec.setMessage(MessageResolver.getMessage("U801"));
        ec.setErrCode("ERR1101");
        ec.setErrorInfo(new ErrorInfo(errInfo));
        throw new YSBizException(ec);
    }

    // 합치지 않고 개별 파일에 페이지 수 넘버링
    private List<File> getPageNumberedPDFs(List<File> userUploadedFiles, Application application) {
        int totalPageCounts = 0;
        int accumulatedPages = 1;

        List<File> fileList = new ArrayList<>();

        for (File item : userUploadedFiles) {
            int pageCounts = getPdfPageCount(item, application);
            totalPageCounts += pageCounts;
        }

        for (File item : userUploadedFiles) {
            fileList.add(getPageNumberedPDF(item, 1, accumulatedPages, totalPageCounts, application));
            accumulatedPages += getPdfPageCount(item, application);
        }
        return fileList;
    }

    // 결제 완료 후 로컬에 생성된 수험표 파일
    private File getApplicationSlipFile(Application application) {
        String applicationSlipFileFullPath = FilePathUtil.getUploadDirectoryFullPath(
                fileBaseDir, s3MidPath, application.getAdmsNo(), application.getUserId(), application.getApplNo()) + "/" +
                FilePathUtil.getApplicationSlipFileName(application.getUserId());
        return new File(applicationSlipFileFullPath);
    }

    // 결제 완료 후 로컬에 생성된 지원서 파일
    private File getApplicationFormFile(Application application) {
        return new File(getPdfDirFullPath(application), FilePathUtil.getApplicationFormFileName(application.getUserId()));
    }

    // S3에 파일 업로드
    private void uploadToS3(AmazonS3 s3, File file, int applNo) {

        ObjectMetadata meta = createS3ObjMetaData(file);
//        String s3FilePath = getS3FilePath(file); // 이건 합침 파일 올릴때 사용
        String s3FilePath = FilePathUtil.getS3PathFromLocalFullPath(file.getAbsolutePath(), fileBaseDir);
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
