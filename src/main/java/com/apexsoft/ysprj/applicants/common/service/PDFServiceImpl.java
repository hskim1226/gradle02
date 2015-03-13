package com.apexsoft.ysprj.applicants.common.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;
import com.apexsoft.ysprj.applicants.common.domain.ParamForPDFDocument;
import com.apexsoft.ysprj.applicants.common.util.FileUtil;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
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


        List<ApplicationDocument> pdfList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectPDFByApplNo", param, ApplicationDocument.class);
        String slipFilePath = null;
        String slipFileName = null;
        String applicationFilePath = null;
        String applicationFileName = null;
        for (ApplicationDocument aDoc : pdfList) {
            if ("수험표".equals(aDoc.getDocItemName())) {
                slipFilePath = aDoc.getFilePath();
                slipFileName = aDoc.getFileName();
            } else if ("지원서".equals(aDoc.getDocItemName())) {
                applicationFilePath = aDoc.getFilePath();
                applicationFileName = aDoc.getFileName();
            } else {
                File pdfFile = new File(aDoc.getFilePath(), aDoc.getFileName());
                mergerUtil.addSource(pdfFile);
                if (uploadDirFullPath == null)
                    uploadDirFullPath = aDoc.getFilePath();
            }
        }

        String rawMergedFileFullPath = FileUtil.getRawMergedFileFullPath(uploadDirFullPath, applNo);
        String numberedMergedFileFullPath = FileUtil.getNumberedMergedFileFullPath(uploadDirFullPath, applNo);

        mergerUtil.setDestinationFileName(rawMergedFileFullPath);
        PDDocument mergedPDF = null;
        try {
            mergerUtil.mergeDocuments();
            File mergedFile = new File(rawMergedFileFullPath);
            mergedPDF = PDDocument.load(mergedFile);
            ec = generatePageNumberedPDF(mergedPDF, numberedMergedFileFullPath, applNo);

            PDFMergerUtility lastMergeUtil = new PDFMergerUtility();
            lastMergeUtil.addSource(new File(applicationFilePath, applicationFileName));
            lastMergeUtil.addSource(new File(numberedMergedFileFullPath));
            lastMergeUtil.setDestinationFileName(FileUtil.getFinalMergedFileFullPath(uploadDirFullPath, applNo));
            lastMergeUtil.mergeDocuments();

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
