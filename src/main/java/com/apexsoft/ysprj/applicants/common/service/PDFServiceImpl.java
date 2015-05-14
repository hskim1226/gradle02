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
import com.apexsoft.framework.persistence.file.manager.FilePersistenceManager;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.common.domain.ParamForPDFDocument;
import com.apexsoft.ysprj.applicants.common.util.FileUtil;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * S3에서 PDF 파일을 다운로드 하고
     * 암호화 되지 않은 PDF 파일은 BAOS에 담아 리스트로 반환하고,
     * 암호화 된 PDF 파일은 encryptedPdfList에 추가한다.
     *
     * @param pdfList   DB에서 조회한 첨부 파일 정보 리스트
     * @param encryptedPdfList    암호화 된 PDF 파일이 저장될 리스트
     * @param applicationFilePath   원서 파일 경로
     * @param applicationFileName    원서 파일 이름
     * @return
     */
    private List<ByteArrayOutputStream> getPdfListFromS3(AmazonS3 s3, List<ApplicationDocument> pdfList, List<ApplicationDocument> encryptedPdfList,
                                                         String applicationFilePath, String applicationFileName) {
        List<ByteArrayOutputStream> unencryptedPdfBaosList = new ArrayList<ByteArrayOutputStream>();
        ExecutionContext ec = new ExecutionContext();


        for (ApplicationDocument aDoc : pdfList) {
            String filePath = FileUtil.recoverAmpersand(aDoc.getFilePath());
            String fileName = FileUtil.recoverAmpersand(aDoc.getFileName());
            if ("지원서".equals(aDoc.getDocItemName()) && StringUtil.getEmptyIfNull(aDoc.getDocItemCode()).equals(StringUtil.EMPTY_STRING)) {
                applicationFilePath = filePath;
                applicationFileName = fileName;
            } else if ("수험표".equals(aDoc.getDocItemName()) && StringUtil.getEmptyIfNull(aDoc.getDocItemCode()).equals(StringUtil.EMPTY_STRING)) {
//                수험표는 합치지 않으므로 주석처리
//                slipFilePath = filePath;
//                slipFileName = fileName;
            } else {
//                File pdfFile = new File(aDoc.getFilePath(), aDoc.getFileName());
//                mergerUtil.addSource(pdfFile);

                // S3에 업로드 되어 있는 첨부 파일 다운로드
                S3Object object = null;
                try {
                    object = s3.getObject(new GetObjectRequest(s3BucketName, filePath));
                } catch (Exception e) {
                    logger.error("Err in s3.getObject in PDFServiceImpl.getMergedPDFByApplicants");
                    logger.error(e.getMessage());
                    logger.error("bucketName : [" + s3BucketName + "]");
//                    logger.error("applNo : [" + applNo + "]");
                    logger.error("objectKey : [" + filePath +"]");
                    throw new YSBizException(e);
                }

                InputStream inputStream = object.getObjectContent();

                // 다운로드 한 PDF 파일 내용을 스트림 형태로 여러번 사용하기 위해 BAOS에 담아둔다.
                ByteArrayOutputStream baos = null;
                try {
                    baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1) {
                        baos.write(buffer, 0, len);
                    }
                    baos.flush();
                } catch (IOException e) {
                    throw new YSBizException(e);
                }

                // PDF 암호화 여부 확인
                PDDocument tPdf = null;
                try {
                    tPdf = PDDocument.load(new ByteArrayInputStream(baos.toByteArray()));
                    if (tPdf.isEncrypted()) {
                        logger.error("file from S3 is encrypted");
//                        logger.error("applNo : " + applNo);
                        logger.error("filePath : " + filePath);
                        ec.setResult(ExecutionContext.FAIL);
                        ec.setMessage(MessageResolver.getMessage("U06101"));
                        ec.setErrCode("ERR1101");
                        encryptedPdfList.add(aDoc);
//                        throw new YSBizNoticeException(ec);
                    }
                } catch ( IOException e ) {
                    // 여기까지 왔으면 S3에서 받아온 inputStream이 문제가 없는 것이므로 IOException 발생할 일 없음
                } finally {
                    if (tPdf != null) {
                        try {
                            tPdf.close();
                        } catch ( IOException e ) {
                            e.printStackTrace();
                        }
                    }
                }

//                try {
//                    IOUtils.copy(new ByteArrayInputStream(baos.toByteArray()), new FileOutputStream(applicationFilePath + "-test.pdf"));
//                } catch ( IOException e ) {
//                    logger.error("ByteArrayInputStream으로 파일 만들지 못함");
//                }



                unencryptedPdfBaosList.add(baos);

                // 업로드 된 파일 별로 서버 로컬에 넘버링을 위해 임시 저장할 경로를 만들지 않고 지원서 파일이 생성된 경로를 재사용
                // uploadDirFullPath는 App서버 로컬에서만 사용
//                if (uploadDirFullPath == null) { // 사용자가 업로드 한 파일의 원래 경로에 / 가 묻어있는 경우 매 파일마다 경로가 다르므로 null이 아니어도 재사용하면 안됨
                // S3에 저장된 파일을 불러서 App 서버에 저장하는 경우
//                    uploadDirFullPath = fileBaseDir + "/" + aDoc.getFilePath().substring(0, filePath.lastIndexOf('/'));
//                    uploadDirFullPath = aDoc.getFilePath(); // App서버 로컬에 저장된 파일을 불러서 사용하는 옛날 버전
//                }
            }
        }

        // 암호화 된 파일 목록을 YSBizNoticeException에 보내서 예외 처리
        if (encryptedPdfList.size() > 0) {
            ec.setData(encryptedPdfList);
            throw new YSBizNoticeException(ec);
        }

        return unencryptedPdfBaosList;
    }

    /**
     * 암호화 되지 않은 PDF 파일들에 대한 BAOS 리스트를 받아
     * 한 파일로 합친다.
     *
     * @param unencryptedPdfFromS3List    암호화 되지 않은 pdf 파일의 BAOS 리스트
     * @param applicationFilePath    원서 파일 경로
     * @return
     */
    private File getRawMergedFile(List<ByteArrayOutputStream> unencryptedPdfFromS3List, String applicationFilePath, int applNo) {
        ExecutionContext ec = new ExecutionContext();
        PDFMergerUtility mergerUtil = new PDFMergerUtility();

        // S3에 대한 OutputStream을 가져올 방법이 없어서
        // mergerUtil.setDestinationStream() 를 사용할 수 없고,
        // 머지된 파일을 APP 로컬에 저장 후 다시 S3로 보내는 방법 밖에 없음
        // 성능 상으로 PDF numbering을 하기 위한 파일 저장은 로컬에 하는 것이 맞을 듯
        String tempSlashReplacer = "_";

//        String rawMergedFileFullPath = FileUtil.encodeColonSlash(FileUtil.getRawMergedFileFullPath(uploadDirFullPath, applNo), tempSlashReplacer);
//        String numberedMergedFileFullPath = FileUtil.encodeColonSlash(FileUtil.getNumberedMergedFileFullPath(uploadDirFullPath, applNo), tempSlashReplacer);
        String rawMergedFileFullPath = FileUtil.encodeColonSlash(FileUtil.getRawMergedFileFullPath(applicationFilePath, applNo), tempSlashReplacer);


        // 파일 넘버링을 위한 첨부 파일 합치기
        mergerUtil.setDestinationFileName(rawMergedFileFullPath);
        //TODO : S3에 대한 OutputStream을 가져올 수 있다면 아래 방식 가능
        //mergerUtil.setDestinationStream(OutputStream to S3);

        for (ByteArrayOutputStream baos : unencryptedPdfFromS3List) {
            mergerUtil.addSource(new ByteArrayInputStream(baos.toByteArray()));
        }

        try {
            mergerUtil.mergeDocuments();
        } catch (IOException e) {
            logger.error("merge files from S3 failed");
            logger.error("applNo : " + applNo);
            logger.error(e.getMessage());
            logger.error("destFileName : " + mergerUtil.getDestinationFileName());
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U06101"));
            ec.setErrCode("ERR1101");
            throw new YSBizNoticeException(ec);
        } catch (COSVisitorException e) {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U801"));
            ec.setErrCode("ERR1101");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }
        return new File(rawMergedFileFullPath);
    }

    /**
     * 합쳐진 첨부 파일 우상단에 '현재 페이지/전체 페이지' 텍스트를 추가한다.
     *
     * @param rawMergedFile    하나로 머지된 첨부 파일
     * @param applicationFilePath    원서 파일 경로
     * @param applNo
     * @return
     * @throws IOException
     * @throws COSVisitorException
     */
    private File getPageNumberedPDF(File rawMergedFile, String applicationFilePath, int applNo) {

        ExecutionContext ec = new ExecutionContext();
        PDDocument mergedPDDocument = null;
        String numberedMergedFileFullPath = FileUtil.encodeColonSlash(FileUtil.getNumberedMergedFileFullPath(applicationFilePath, applNo), "_");

        try {
            mergedPDDocument = PDDocument.load(rawMergedFile);

            List allPages = mergedPDDocument.getDocumentCatalog().getAllPages();
            int length = allPages.size();
            PDFont font = PDType1Font.HELVETICA;
            float fontSize = 15.0f;

            for ( int i = 0 ; i < length ; i++ ) {
                PDPage page = (PDPage)allPages.get(i);
                PDRectangle pageSize = page.findMediaBox();
                String strPage = new StringBuilder().append(i+1).append("/").append(length).toString();
                float stringWidth = font.getStringWidth(strPage)*fontSize/1000f;
                float pageWidth = pageSize.getWidth();
                float pageHeight = pageSize.getHeight();
                PDPageContentStream contentStream = new PDPageContentStream(mergedPDDocument, page, true, true, true);
                contentStream.beginText();
                contentStream.setFont(font, fontSize);
                contentStream.setTextTranslation(pageWidth - stringWidth - 15, pageHeight - 20);
                contentStream.drawString(strPage);
                contentStream.endText();
                contentStream.close();
            }
            mergedPDDocument.save(numberedMergedFileFullPath);
        } catch (IOException e) {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U801"));
            ec.setErrCode("ERR1101");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        } catch (COSVisitorException e) {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U801"));
            ec.setErrCode("ERR1101");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        } finally {
            if (mergedPDDocument != null) {
                try {
                    mergedPDDocument.close();
                } catch (IOException e) {
                    ec.setResult(ExecutionContext.FAIL);
                    ec.setMessage(MessageResolver.getMessage("U801"));
                    ec.setErrCode("ERR1101");
                    Map<String, String> errorInfo = new HashMap<String, String>();
                    errorInfo.put("applNo", String.valueOf(applNo));
                    ec.setErrorInfo(new ErrorInfo(errorInfo));
                    throw new YSBizException(ec);
                }
            }
        }

        return new File(numberedMergedFileFullPath);
    }

    /**
     * 원서 파일과 넘버링 된 첨부 파일을 합쳐 최종 파일을 반환한다.
     *
     * @param applicationFormFile    원서 파일
     * @param numberedMergedFile    넘버링 된 첨부 파일 묶음 파일
     * @param applicationFilePath    원서 파일 경로
     * @param applNo
     * @return
     */
    private File getMergedApplicationFormFile(File applicationFormFile, File numberedMergedFile, String applicationFilePath, int applNo) {
        ExecutionContext ec = new ExecutionContext();
        String mergedApplicationFormFilePath = FileUtil.encodeSlash(FileUtil.getFinalMergedFileFullPath(applicationFilePath, applNo), "_");
        try {
            PDFMergerUtility lastMergeUtil = new PDFMergerUtility();
            lastMergeUtil.addSource(applicationFormFile);
            lastMergeUtil.addSource(numberedMergedFile);
            lastMergeUtil.setDestinationFileName(mergedApplicationFormFilePath);
            lastMergeUtil.mergeDocuments();
            logger.debug("All Merge 성공, applNo : " + applNo);
        } catch (IOException e) {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U801"));
            ec.setErrCode("ERR1101");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        } catch (COSVisitorException e) {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U801"));
            ec.setErrCode("ERR1101");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }
        return new File(mergedApplicationFormFilePath);
    }

    private File getApplicationSlipFile(int applNo) {
        return null;
    }

    private void uploadToS3(AmazonS3 s3, File file, int applNo) {
        // 머지된 최종 파일을 S3에 업로드
//        File lastMergedFile = new File(lastMergeUtil.getDestinationFileName());
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentEncoding("UTF-8");
        meta.setContentLength(file.length());
        meta.setHeader("x-amz-storage-class", s3StorageClass);
//        List<ApplicationDocument> applPaperInfosList =
//                documentService.retrieveApplicationPaperInfo(applNo); // DB에서 filePath가져온다
//        if (applPaperInfosList.size() == 1) {
//            String applPaperLocalFilePath = applPaperInfosList.get(0).getFilePath();
            String applPaperLocalFilePath = file.getAbsolutePath();
            String s3FilePath = FileUtil.getS3PathFromLocalFullPath(applPaperLocalFilePath, fileBaseDir);
            try {
                s3.putObject(new PutObjectRequest(s3BucketName,
                        FileUtil.getFinalMergedFileFullPath(s3FilePath, applNo),
                        file)
                        .withMetadata(meta)
                        .withCannedAcl(CannedAccessControlList.AuthenticatedRead.PublicRead));
            } catch (Exception e) {
                logger.error("Err in uploading final file to S3");
                logger.error(e.getMessage());
                logger.error("s3BucketName : [" + s3BucketName + "]");
                logger.error("applNo : [" + applNo + "]");
//                logger.error("ObjectKey : [" + FileUtil.getS3PathFromLocalFullPath(lastMergeUtil.getDestinationFileName(), fileBaseDir) + "]");
                logger.error("ObjectKey : [" + FileUtil.getS3PathFromLocalFullPath(file.getAbsolutePath(), fileBaseDir) + "]");
                throw new YSBizException(e);
            }
//        } else {
//            logger.error("Err in preparing uploading final file to S3");
//            logger.error("s3BucketName : [" + s3BucketName + "]");
//            logger.error("applNo : [" + applNo + "]");
////            logger.error("ObjectKey : [" + FileUtil.getS3PathFromLocalFullPath(lastMergeUtil.getDestinationFileName(), fileBaseDir) + "]");
//            logger.error("ObjectKey : [" + FileUtil.getS3PathFromLocalFullPath(file.getAbsolutePath(), fileBaseDir) + "]");
//            ExecutionContext ec1 = new ExecutionContext(ExecutionContext.FAIL);
//            ec1.setErrCode("ERR1102");
//            ec1.setMessage(MessageResolver.getMessage("U04518"));
//            throw new YSBizException(ec1);
//        }
    }

    /**
     * 지원자 별 PDF 묶음 파일 생성
     * 첨부 파일을 S3로부터 서버 로컬에 내려받아 먼저 합치고, 페이지를 먹인 후,
     * 이미 생성되어 있는 지원서 파일과 합쳐서 최종 파일을 생성하고,
     * S3에 업로드
     *
     *
     * @param applNo
     * @return
     */
    @Override
    public ExecutionContext getMergedPDFByApplicants(int applNo) {

        ExecutionContext ec = new ExecutionContext();
        ParamForPDFDocument param = new ParamForPDFDocument(applNo, "pdf");

        AmazonS3 s3 = new AmazonS3Client();
        List<ApplicationDocument> encryptedPdfList = new ArrayList<ApplicationDocument>();

        String applicationFilePath = null;
        String applicationFileName = null;

        // DB에서 첨부 파일 정보 조회
        List<ApplicationDocument> pdfList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectPDFByApplNo", param, ApplicationDocument.class);

        // 첨부 파일 정보를 토대로 S3에서 각 첨부 파일 다운로드 하고 암호화 여부 체크
        List<ByteArrayOutputStream> unencryptedPdfFromS3List = getPdfListFromS3(s3, pdfList, encryptedPdfList, applicationFilePath, applicationFileName);

        // 페이지 넘버링을 위해 첨부 파일 머지
        File rawMergedFile = getRawMergedFile(unencryptedPdfFromS3List, applicationFilePath, applNo);

        // 페이지 넘버링 파일
        File numberedMergedFile = getPageNumberedPDF(rawMergedFile, applicationFilePath, applNo);

        // 로컬에 저장된 지원서 파일과 페이지 넘버링 된 첨부 파일 묶음을 또 머지
        File mergedApplicationFormFile = getMergedApplicationFormFile(new File(applicationFilePath), numberedMergedFile, applicationFilePath, applNo);

        // 수험표 파일
        File applicationSlipFile = getApplicationSlipFile(applNo);

        // 최종 머지된 파일 지원서 파일과 수험표 파일을 S3에 업로드
        uploadToS3(s3, mergedApplicationFormFile, applNo);
        uploadToS3(s3, applicationSlipFile, applNo);

        // 중간 작업 파일 삭제
        // 여기서 지우면 파일 지우기 위한 I/O 추가 발생하지만 저장 공간은 절약
        // 나중에 batch로 지우면 I/O 는 절약하지만 지우기 전까지 저장 공간은 낭비
        rawMergedFile.delete();
//        applicationFormFile.delete();
        numberedMergedFile.delete();
        mergedApplicationFormFile.delete();

//
//
//
//
//        String numberedMergedFileFullPath = FileUtil.encodeColonSlash(FileUtil.getNumberedMergedFileFullPath(applicationFilePath, applNo), tempSlashReplacer);
//
//        PDDocument mergedPDF = null;
//        try {
//            logger.debug("raw Merge 성공, applNo : " + applNo);
//            File mergedFile = new File(rawMergedFileFullPath);
//            mergedPDF = PDDocument.load(mergedFile);
//            ec = getPageNumberedPDF(mergedPDF, numberedMergedFileFullPath, applNo);
//            logger.debug("numbering Merge 성공, applNo : " + applNo);
//
//            PDFMergerUtility lastMergeUtil = new PDFMergerUtility();
//            File applicationFormFile = new File(applicationFilePath, applicationFileName);
////            File applicationFormFile = new File(applicationFilePath);
////            if (!applicationFormFile.exists()) {
////                S3Object tObject = s3.getObject(new GetObjectRequest(s3BucketName, applicationFilePath));
////                InputStream tInputStream = tObject.getObjectContent();
////                FileUtils.copyInputStreamToFile(tInputStream, applicationFormFile);
////            }
//            File numberedMergedFile = new File(numberedMergedFileFullPath);
//            lastMergeUtil.addSource(applicationFormFile);
//            lastMergeUtil.addSource(numberedMergedFile);
//
//            lastMergeUtil.setDestinationFileName(FileUtil.encodeSlash(FileUtil.getFinalMergedFileFullPath(applicationFilePath, applNo), tempSlashReplacer));
//            lastMergeUtil.mergeDocuments();
//            logger.debug("All Merge 성공, applNo : " + applNo);
//
//            // 머지된 최종 파일을 S3에 업로드
//            File lastMergedFile = new File(lastMergeUtil.getDestinationFileName());
//            ObjectMetadata meta = new ObjectMetadata();
//            meta.setContentEncoding("UTF-8");
//            meta.setContentLength(lastMergedFile.length());
//            meta.setHeader("x-amz-storage-class", s3StorageClass);
//            List<ApplicationDocument> applPaperInfosList =
//                    documentService.retrieveApplicationPaperInfo(applNo); // DB에서 filePath가져온다
//            if (applPaperInfosList.size() == 1) {
//                String applPaperLocalFilePath = applPaperInfosList.get(0).getFilePath();
//                String s3FilePath = FileUtil.getS3PathFromLocalFullPath(applPaperLocalFilePath, fileBaseDir);
//                try {
//                    s3.putObject(new PutObjectRequest(s3BucketName,
//                            FileUtil.getFinalMergedFileFullPath(s3FilePath, applNo),
//                            lastMergedFile)
//                            .withMetadata(meta)
//                            .withCannedAcl(CannedAccessControlList.AuthenticatedRead.PublicRead));
//                } catch (Exception e) {
//                    logger.error("Err in uploading final file to S3");
//                    logger.error(e.getMessage());
//                    logger.error("s3BucketName : [" + s3BucketName + "]");
//                    logger.error("applNo : [" + applNo + "]");
//                    logger.error("ObjectKey : [" + FileUtil.getS3PathFromLocalFullPath(lastMergeUtil.getDestinationFileName(), fileBaseDir) + "]");
//                    throw new YSBizException(e);
//                }
//            } else {
//                logger.error("Err in preparing uploading final file to S3");
//                logger.error("s3BucketName : [" + s3BucketName + "]");
//                logger.error("applNo : [" + applNo + "]");
//                logger.error("ObjectKey : [" + FileUtil.getS3PathFromLocalFullPath(lastMergeUtil.getDestinationFileName(), fileBaseDir) + "]");
//                ExecutionContext ec1 = new ExecutionContext(ExecutionContext.FAIL);
//                ec1.setErrCode("ERR1102");
//                ec1.setMessage(MessageResolver.getMessage("U04518"));
//                throw new YSBizException(ec1);
//            }
//
//
//
//            // 여기서 지우면 파일 지우기 위한 I/O 추가 발생하지만 저장 공간은 절약
//            // 나중에 batch로 지우면 I/O 는 절약하지만 지우기 전까지 저장 공간은 낭비
//            mergedFile.delete();
//            applicationFormFile.delete();
//            numberedMergedFile.delete();
//            lastMergedFile.delete();
//        } catch (IOException e) {
//            ec.setResult(ExecutionContext.FAIL);
//            ec.setMessage(MessageResolver.getMessage("U801"));
//            ec.setErrCode("ERR1101");
//            Map<String, String> errorInfo = new HashMap<String, String>();
//            errorInfo.put("applNo", String.valueOf(applNo));
//            ec.setErrorInfo(new ErrorInfo(errorInfo));
//            throw new YSBizException(ec);
//        } catch (COSVisitorException e) {
//            ec.setResult(ExecutionContext.FAIL);
//            ec.setMessage(MessageResolver.getMessage("U801"));
//            ec.setErrCode("ERR1101");
//            Map<String, String> errorInfo = new HashMap<String, String>();
//            errorInfo.put("applNo", String.valueOf(applNo));
//            ec.setErrorInfo(new ErrorInfo(errorInfo));
//            throw new YSBizException(ec);
//        } finally {
//            if (mergedPDF != null) {
//                try {
//                    mergedPDF.close();
//                } catch (IOException e) {
//                    ec.setResult(ExecutionContext.FAIL);
//                    ec.setMessage(MessageResolver.getMessage("U801"));
//                    ec.setErrCode("ERR1101");
//                    Map<String, String> errorInfo = new HashMap<String, String>();
//                    errorInfo.put("applNo", String.valueOf(applNo));
//                    ec.setErrorInfo(new ErrorInfo(errorInfo));
//                    throw new YSBizException(ec);
//                }
//            }
//        }
        return ec;
    }

}
