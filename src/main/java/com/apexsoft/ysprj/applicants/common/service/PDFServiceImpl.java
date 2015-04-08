package com.apexsoft.ysprj.applicants.common.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;
import com.apexsoft.ysprj.applicants.common.domain.ParamForPDFDocument;
import com.apexsoft.ysprj.applicants.common.util.FileUtil;
import org.apache.commons.io.IOUtils;
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    MessageResolver messageResolver;

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
     * 첨부 파일을 먼저 합치고, 페이지를 먹인 후,
     * 이미 생성되어 있는 지원서 파일과 합쳐서 최종 파일을 생성한다.
     *
     *
     * @param applNo
     * @return
     */
    @Override
    public ExecutionContext getMergedPDFByApplicants(int applNo) {
        ExecutionContext ec = new ExecutionContext();
        ParamForPDFDocument param = new ParamForPDFDocument(applNo, "pdf");
        PDFMergerUtility mergerUtil = new PDFMergerUtility();
        String uploadDirFullPath = null;
        AmazonS3 s3 = new AmazonS3Client();


        List<ApplicationDocument> pdfList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectPDFByApplNo", param, ApplicationDocument.class);
//        String slipFilePath = null;
//        String slipFileName = null;
        String applicationFilePath = null;
        String applicationFileName = null;
        for (ApplicationDocument aDoc : pdfList) {
            String filePath = aDoc.getFilePath();
            String fileName = aDoc.getFileName();
            if ("지원서".equals(aDoc.getDocItemName())) {
                applicationFilePath = filePath;
                applicationFileName = fileName;
            } else if ("수험표".equals(aDoc.getDocItemName())) {
//                수험표는 합치지 않으므로 주석처리
//                slipFilePath = filePath;
//                slipFileName = fileName;
            } else {
//                File pdfFile = new File(aDoc.getFilePath(), aDoc.getFileName());
//                mergerUtil.addSource(pdfFile);
                S3Object object = null;
                try {
                    object = s3.getObject(new GetObjectRequest(s3BucketName, filePath));
                } catch (Exception e) {
                    logger.error("Err in s3.getObject i PDFServiceImpl.getMergedPDFByApplicants");
                    logger.error(e.getMessage());
                    logger.error("bucketName : [" + s3BucketName + "]");
                    logger.error("applNo : [" + applNo + "]");
                    logger.error("objectKey : [" + filePath +"]");
                    throw new YSBizException(e);
                }

                InputStream inputStream = object.getObjectContent();
                mergerUtil.addSource(inputStream);

                // uploadDirFullPath는 App서버 로컬에서만 사용
                if (uploadDirFullPath == null) {
                    // S3에 저장된 파일을 불러서 App 서버에 저장하는 경우
                    uploadDirFullPath = fileBaseDir + "/" + aDoc.getFilePath().substring(0, filePath.lastIndexOf('/'));
//                    uploadDirFullPath = aDoc.getFilePath(); // App서버 로컬에 저장된 파일을 불러서 사용하는 옛날 버전
                }
            }
        }

        // S3에 대한 OutputStream을 가져올 방법이 없어서
        // mergerUtil.setDestinationStream() 를 사용할 수 없고,
        // 머지된 파일을 APP 로컬에 저장 후 다시 S3로 보내는 방법 밖에 없음
        // 성능 상으로 PDF numbering을 하기 위한 파일 저장은 로컬에 하는 것이 맞을 듯

        String rawMergedFileFullPath = FileUtil.getRawMergedFileFullPath(uploadDirFullPath, applNo);
        String numberedMergedFileFullPath = FileUtil.getNumberedMergedFileFullPath(uploadDirFullPath, applNo);

        mergerUtil.setDestinationFileName(rawMergedFileFullPath);
        //TODO : S3에 대한 OutputStream을 가져올 수 있다면 아래 방식 가능
        //mergerUtil.setDestinationStream(OutputStream to S3);

        PDDocument mergedPDF = null;
        try {
            mergerUtil.mergeDocuments();
            logger.error("raw Merge 성공, applNo : " + applNo);
            File mergedFile = new File(rawMergedFileFullPath);
            mergedPDF = PDDocument.load(mergedFile);
            ec = generatePageNumberedPDF(mergedPDF, numberedMergedFileFullPath, applNo);
            logger.error("numbering Merge 성공, applNo : " + applNo);

            PDFMergerUtility lastMergeUtil = new PDFMergerUtility();
            File applicationFormFile = new File(applicationFilePath, applicationFileName);
            File numberedMergedFile = new File(numberedMergedFileFullPath);
            lastMergeUtil.addSource(applicationFormFile);
            lastMergeUtil.addSource(numberedMergedFile);
            lastMergeUtil.setDestinationFileName(FileUtil.getFinalMergedFileFullPath(uploadDirFullPath, applNo));
            lastMergeUtil.mergeDocuments();
            logger.error("All Merge 성공, applNo : " + applNo);

            File lastMergedFile = new File(lastMergeUtil.getDestinationFileName());
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentEncoding("UTF-8");
            meta.setContentLength(lastMergedFile.length());
            meta.setHeader("x-amz-storage-class", s3StorageClass);
            try {
                s3.putObject(new PutObjectRequest(s3BucketName,
                        FileUtil.getS3PathFromLocalFullPath(lastMergeUtil.getDestinationFileName(), fileBaseDir),
                        lastMergedFile)
                        .withMetadata(meta)
                        .withCannedAcl(CannedAccessControlList.AuthenticatedRead.PublicRead));
            } catch (Exception e) {
                logger.error("Err in uploading final file to S3");
                logger.error(e.getMessage());
                logger.error("s3BucketName : [" + s3BucketName + "]");
                logger.error("applNo : [" + applNo + "]");
                logger.error("ObjectKey : [" + FileUtil.getS3PathFromLocalFullPath(lastMergeUtil.getDestinationFileName(), fileBaseDir) + "]");
                throw new YSBizException(e);
            }


            // 여기서 지우면 파일 지우기 위한 I/O 추가 발생하지만 저장 공간은 절약
            // 나중에 batch로 지우면 I/O 는 절약하지만 지우기 전까지 저장 공간은 낭비
            mergedFile.delete();
            applicationFormFile.delete();
            numberedMergedFile.delete();
            lastMergedFile.delete();
        } catch (IOException e) {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U801"));
            ec.setErrCode("ERR1101");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        } catch (COSVisitorException e) {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U801"));
            ec.setErrCode("ERR1101");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        } catch (Exception e) {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U801"));
            ec.setErrCode("ERR1101");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        } finally {
            if (mergedPDF != null) {
                try {
                    mergedPDF.close();
                } catch (IOException e) {
                    ec.setResult(ExecutionContext.FAIL);
                    ec.setMessage(messageResolver.getMessage("U801"));
                    ec.setErrCode("ERR1101");
                    Map<String, String> errorInfo = new HashMap<String, String>();
                    errorInfo.put("applNo", String.valueOf(applNo));
                    ec.setErrorInfo(new ErrorInfo(errorInfo));
                    throw new YSBizException(ec);
                }
            }
        }
        return ec;
    }

    /**
     * 합쳐진 첨부 파일 우상단에 '현재 페이지/전체 페이지' 텍스트를 추가한다.
     *
     * @param pdDocument
     * @param destFilePath
     * @param applNo
     * @return
     * @throws IOException
     * @throws COSVisitorException
     */
    private ExecutionContext generatePageNumberedPDF(PDDocument pdDocument, String destFilePath, int applNo)
            throws IOException, COSVisitorException{
        ExecutionContext ec = new ExecutionContext();
        List allPages = pdDocument.getDocumentCatalog().getAllPages();
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
            PDPageContentStream contentStream = new PDPageContentStream(pdDocument, page, true, true, true);
            contentStream.beginText();
            contentStream.setFont(font, fontSize);
            contentStream.setTextTranslation(pageWidth - stringWidth - 15, pageHeight - 20);
            contentStream.drawString(strPage);
            contentStream.endText();
            contentStream.close();
        }
        pdDocument.save(destFilePath);

        return ec;
    }

    @Override
    public ExecutionContext savePaidDocuments(int applNo) {
        return null;
    }
}
