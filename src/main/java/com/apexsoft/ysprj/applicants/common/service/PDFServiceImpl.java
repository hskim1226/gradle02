package com.apexsoft.ysprj.applicants.common.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.file.exception.EncryptedPDFException;
import com.apexsoft.framework.persistence.file.manager.FilePersistenceManager;
import com.apexsoft.framework.persistence.file.model.FileInfo;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationStatus;
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
import java.util.zip.DataFormatException;

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

    @Autowired
    FilePersistenceService filePersistenceService;

    @Autowired
    ZipService zipService;

    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;

    @Value("#{app['s3.bucketName']}")
    private String s3BucketName;

    @Value("#{app['file.midPath']}")
    private String midPath;

    @Value("#{app['s3.storageClass']}")
    private String s3StorageClass;

    @Value("#{app['site.tel']}")
    private String siteTel;

    @Value("#{app['site.helpdesk']}")
    private String helpdeskMail;

    private static final Logger logger = LoggerFactory.getLogger(PDFServiceImpl.class);

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";

    /**
     * 첨부 파일을 S3로부터 서버 로컬에 내려받아 합치지 않고 쪽수 넘버링 한 후
     * ZIP으로 압축해서 S3에 업로드
     *
     * @param application
     * @return
     */
    @Override
    public ExecutionContext genAndUploadPDFByApplicants(Application application) {

        ExecutionContext ec = new ExecutionContext();

        int applNo = application.getApplNo();
        String userId = application.getUserId();
        ParamForPDFDocument param = new ParamForPDFDocument(applNo, "pdf");

        // DB에서 첨부 파일 정보 조회
        List<ApplicationDocument> pdfList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectPDFByApplNo", param, ApplicationDocument.class);

        // 첨부 파일 정보를 토대로 사용자가 업로드한 첨부 파일을 S3에서 다운로드
        List<ByteArrayOutputStream> userUploadedPdfStreamFromS3List = getByteArrayOutputStreamedPdfListFromS3(pdfList);

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
        List<File> userUploadedFiles = getFileListFromS3(pdfList);
        List<File> numberedFiles = getPageNumberedPDFs(userUploadedFiles, application);

        // 페이지 넘버링 된 개별 파일을 zip으로 압축
        File zippedFile = getZippedFile(numberedFiles, application);

        // zip파일 S3에 업로드
        uploadToS3(zippedFile, applNo);

        // 업로드 후 삭제
        zippedFile.delete();

        // 파일 목록 루프돌며 S3에 업로드
//        for (File file : numberedFiles) {
//            uploadToS3(s3, file, applNo);
//        }




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
    private int getPdfPageCount(File pdfFile, Application application) {
        int pageCounts = -1;
        PDDocument pdDocument = null;
        ExecutionContext ec = new ExecutionContext();
        try {
            pdDocument = PDDocument.load(pdfFile);
            pageCounts = pdDocument.getNumberOfPages();
        } catch (IOException e) {
            Map<String, String> errMap = new HashMap<>();
            errMap.put("applNo", String.valueOf(application.getApplNo()));
            errMap.put("filePath", pdfFile.getAbsolutePath());
            exceptionThrower(errMap, ec);
        } finally {
            if (pdDocument != null) {
                try {
                    pdDocument.close();
                } catch (IOException e) {
                    Map<String, String> errMap = new HashMap<>();
                    errMap.put("applNo", String.valueOf(application.getApplNo()));
                    errMap.put("filePath", pdfFile.getAbsolutePath());
                    exceptionThrower(errMap, ec);
                }
            }
        }

        return pageCounts;
    }

    /**
     * S3에서 PDF 파일을 다운로드 하고
     * 암호화 되지 않은 PDF 파일은 BAOS에 담아 리스트로 반환
     *
     * @param pdfList
     * @return
     */
    private List<ByteArrayOutputStream> getByteArrayOutputStreamedPdfListFromS3(List<ApplicationDocument> pdfList) {
        List<ByteArrayOutputStream> unencryptedPdfBaosList = new ArrayList<ByteArrayOutputStream>();

        for (ApplicationDocument aDoc : pdfList) {
            String filePath = aDoc.getFilePath();

            // 사용자가 직접 입력한 파일이면 다운로드
            if (isUserUploadedFile(aDoc)) {

                // S3에서 다운로드 한 PDF 파일 내용을 스트림 형태로 여러번 사용하기 위해 BAOS에 담아둔다.
                InputStream inputStream = filePersistenceService.getInputStreamFromFileRepo(filePath);
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

    // 사용자가 업로드한 파일 다운로드
    private List<File> getFileListFromS3(List<ApplicationDocument> pdfList) {
        List<File> userUploadedFiles = new ArrayList<>();

        for (ApplicationDocument aDoc : pdfList) {
            String filePath = FilePathUtil.recoverAmpersand(aDoc.getFilePath());
            filePath = FilePathUtil.recoverSingleQuote(filePath);

            // 사용자가 직접 입력한 파일이면 다운로드
            if (isUserUploadedFile(aDoc)) {
                // S3에 업로드 되어 있는 첨부 파일 다운로드
                InputStream inputStream = filePersistenceService.getInputStreamFromFileRepo(filePath);
                String targetFilePath = FilePathUtil.getLocalFullPathFromS3Path(fileBaseDir, filePath);
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
     * @param file  쪽수를 매길 파일
     * @param applNo  원서 키
     * @return
     */
    @Override
    public File getPageNumberedPDF(File file, int applNo) {
        return getPageNumberedPDF(file, 1, 1, -1, applNo);
    }

    @Override
    public void deletePageNumberedPDF(File file) {
        String targetFilePath = getTargetFilePath(file);
        File numberedFile = new File(targetFilePath);
        if (numberedFile.exists())
            numberedFile.delete();
    }

    /**
     * PDF 파일 우상단에 '현재 페이지/전체 페이지' 텍스트를 추가한다.
     * @param file  쪽수를 매길 파일
     * @param startPage  쪽수를 매기기 시작할 페이지(0이 아니라 1부터 시작하는 숫자)
     * @param startNo  시작 쪽수 번호
     * @param endNo  전체 쪽수 번호, 0이나 음수를 입력하면 해당 파일의 페이지수를 기준으로 startPage를 고려해서 자동 계산
     * @param applNo  원서 키
     * @return
     */
    private File getPageNumberedPDF(File file, int startPage, int startNo, int endNo, int applNo) {
        ExecutionContext ec = new ExecutionContext();
//        String applicationFileDirPath = getPdfDirFullPath(application);
//        String targetFilePath = FilePathUtil.encodeColonSlash(FilePathUtil.getFinalMergedFileFullPath(applicationFileDirPath, application.getApplNo()), "_");
        String targetFilePath = getTargetFilePath(file);
        PDDocument pdDocument = null;
        try {
            pdDocument = PDDocument.load(file);
            PDFont font = PDType1Font.HELVETICA;
            float fontSize = 15.0f;
            PDPageTree pageTree = pdDocument.getPages();
            int length = (endNo > 0) ? endNo : pdDocument.getNumberOfPages() - startPage + 1;
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
                PDPageContentStream contentStream = new PDPageContentStream(pdDocument, page, true, true, true);
                contentStream.beginText();
                contentStream.setFont(font, fontSize);
                contentStream.setTextMatrix(m4Position);
                contentStream.showText(strPage);
                contentStream.endText();
                contentStream.close();
            }
            // 암호화 제거
            // PDF 파일이 합쳐지지 않는 유형은 2가지
            // 1. pdDocument.isEncrypted() == true 인 경우 : 외국 ETSN 등 증빙
            // 2. pdDocument.isEncrypted() == false 임에도 합쳐지지 않는 경우 : 국내 대학 증빙
            // 넘버링은 pdDocument.isEncrypted() == false이면 가능
            // 넘버링은 pdDocument.isEncrypted() == true이면 기본적으로는 pdDocument.save()에서 예외가 발생하지만
            // 아래와 같이 pdDocument.setAllSecurityToBeRemoved(true); 해주면
            // pdDocument.isEncrypted() == true 인 PDF에도 넘버링이 가능해진다.
            pdDocument.setAllSecurityToBeRemoved(true);
            pdDocument.save(targetFilePath);
        } catch (Exception e) {
            Map<String, String> errMap = new HashMap<>();
            errMap.put("applNo", String.valueOf(applNo));
            errMap.put("filePath", targetFilePath);
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U04530", new Object[]{siteTel, helpdeskMail}));
            ec.setErrCode("ERR1108");
            ec.setErrorInfo(new ErrorInfo(errMap));
            throw new YSBizException(ec);
        } finally {
            if (pdDocument != null) {
                try {
                    pdDocument.close();
                } catch (IOException e) {
                    Map<String, String> errMap = new HashMap<>();
                    errMap.put("applNo", String.valueOf(applNo));
                    errMap.put("filePath", targetFilePath);
                    ec.setResult(ExecutionContext.FAIL);
                    ec.setMessage(MessageResolver.getMessage("U04530", new Object[]{siteTel, helpdeskMail}));
                    ec.setErrCode("ERR1107");
                    ec.setErrorInfo(new ErrorInfo(errMap));
                    throw new YSBizException(ec);
                }
            }
        }

        return new File(targetFilePath);
    }

    // Local 다운로드 위치. S3 버킷 내의 path와 동일하다.
    private String getPdfDirFullPath(Application application) {
        return FilePathUtil.getUploadDirectoryFullPath(fileBaseDir, midPath, application.getAdmsNo(), application.getUserId(), application.getApplNo());
    }

    // 생성할 파일 전체 경로 반환
    private String getTargetFilePath(File file) {
        return file.getAbsolutePath();
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

    private void exceptionThrower(Map<String, String> errInfo, String msgCode, String errCode) {
        ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
        ec.setMessage(MessageResolver.getMessage(msgCode));
        ec.setErrCode(errCode);
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
            fileList.add(getPageNumberedPDF(item, 1, accumulatedPages, totalPageCounts, application.getApplNo()));
            accumulatedPages += getPdfPageCount(item, application);
        }
        return fileList;
    }

    // 결제 완료 시 수험번호 채번된 수험표와 지원서를 S3에 업로드
    @Override
    public ExecutionContext processApplicationFileWithApplId(Application application) {
        ExecutionContext ec = new ExecutionContext();
        int applNo = application.getApplNo();

        if (ApplicationStatus.COMPLETED.codeVal().equals(application.getApplStsCode())) {
            // 수험표 파일
            File applicationSlipFile = getApplicationSlipFile(application);
            uploadToS3(applicationSlipFile, applNo); // 수험표는 결제 완료 후에 생성 및 업로드
            applicationSlipFile.delete();

            // 결제 완료를 통해 로컬에 생성된 지원서
            File applicationFormFile = getApplicationFormFile(application);
            uploadToS3(applicationFormFile, applNo);
            applicationFormFile.delete();
        }
        return ec;
    }

    // 결제 완료 후 로컬에 생성된 수험표 파일
    private File getApplicationSlipFile(Application application) {
        String applicationSlipFileFullPath = FilePathUtil.getUploadDirectoryFullPath(
                fileBaseDir, midPath, application.getAdmsNo(), application.getUserId(), application.getApplNo()) + "/" +
                FilePathUtil.getApplicationSlipFileName(application.getUserId());
        return new File(applicationSlipFileFullPath);
    }

    // 결제 완료 후 로컬에 생성된 지원서 파일
    private File getApplicationFormFile(Application application) {
        return new File(getPdfDirFullPath(application), FilePathUtil.getApplicationFormFileName(application.getUserId()));
    }

    // 페이지 넘버링 된 파일들의 압축 파일 반환
    private File getZippedFile(List<File> numberedFiles, Application application) {
        String targetDir = getPdfDirFullPath(application);
        String zipFileName = FilePathUtil.getZippedFileName(application);
        File zipFile = null;

        try {
            zipFile = zipService.getZipFile(targetDir, zipFileName, numberedFiles);
        } catch (IOException e) {
            Map<String, String> errMap = new HashMap<>();
            errMap.put("applNo", String.valueOf(application.getApplNo()));
            exceptionThrower(errMap, "U04528", "ERR1105");
        } catch (InterruptedException e) {
            Map<String, String> errMap = new HashMap<>();
            errMap.put("applNo", String.valueOf(application.getApplNo()));
            exceptionThrower(errMap, "U04528", "ERR1105");
        }
        return zipFile;
    }

    // S3에 파일 업로드
    private void uploadToS3(File file, int applNo) {

//        String s3FilePath = getS3FilePath(file); // 이건 합침 파일 올릴때 사용
        String s3FilePath = FilePathUtil.getS3PathFromLocalFullPath(file.getAbsolutePath(), fileBaseDir);
        filePersistenceService.uploadToFileRepo(fileBaseDir, file, applNo);
    }

    // S3 버킷 아래에 업로드 할 위치 반환
    private String getS3FilePath(File file) {
        String applPaperLocalFilePath = file.getAbsolutePath();
        String s3FilePath = FilePathUtil.getS3PathFromLocalFullPath(applPaperLocalFilePath, fileBaseDir);
        return s3FilePath;
    }

    @Override
    public ExecutionContext uploadToS3(String uploadDir, String uploadFileName, File file, boolean isDelete) {
        ExecutionContext ec = new ExecutionContext();
        String originalFileName = uploadFileName;
        FileInfo fileInfo = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fileInfo = s3PersistenceManager.save(uploadDir, uploadFileName, originalFileName, fis);
            if (isDelete)
                file.delete();
        } catch (EncryptedPDFException e) {
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("uploadDir", uploadDir);
            errorInfo.put("originalFileName", originalFileName);
            exceptionThrower(errorInfo, "U04514", "ERR0052");
        } catch (IOException ioe) {
            if (ioe.getCause().getCause() instanceof DataFormatException) {
                Map<String, String> errorInfo = new HashMap<String, String>();
                errorInfo.put("uploadDir", uploadDir);
                errorInfo.put("originalFileName", originalFileName);
                exceptionThrower(errorInfo, "U04515", "ERR0052");
            } else {
                logger.error("Upload PDF is NOT loaded to PDDocument in " + Thread.currentThread().getStackTrace()[1]);
                Map<String, String> errorInfo = new HashMap<String, String>();
                errorInfo.put("uploadDir", uploadDir);
                errorInfo.put("originalFileName", originalFileName);
                exceptionThrower(errorInfo, "U04518", "ERR0052");
            }
        } finally {
            if (fis != null)
                try { fis.close(); } catch (IOException e) {}
        }
        ec.setData(fileInfo);
        return ec;
    }
}
