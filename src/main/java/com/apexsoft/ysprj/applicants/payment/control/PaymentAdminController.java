package com.apexsoft.ysprj.applicants.payment.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.exception.YSBizNoticeException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;
import com.apexsoft.ysprj.applicants.common.service.BirtService;
import com.apexsoft.ysprj.applicants.common.service.PDFService;
import com.apexsoft.ysprj.applicants.common.util.FileUtil;
import com.apexsoft.ysprj.applicants.payment.domain.ApplicationPaymentTransaction;
import com.apexsoft.ysprj.applicants.payment.domain.CustomApplicationDocumentResult;
import com.apexsoft.ysprj.applicants.payment.service.PaymentService;
import com.snowtide.PDF;
import com.snowtide.pdf.Document;
import com.snowtide.pdf.OutputTarget;
import org.eclipse.emf.common.util.ArrayDelegatingEList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

/**
 * Created by cosb071 on 15. 4. 7.
 */
@Controller
@RequestMapping(value = "/payment/admin")
public class PaymentAdminController {

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BirtService birtService;

    @Autowired
    PDFService pdfService;

    @Autowired
    private CommonDAO commonDAO;

    @Value("#{app['file.mergeTestDir']}")
    private String MERGE_TEST_DIR;

    @Value("#{app['s3.midPath']}")
    private String MID_PATH;

    @RequestMapping(value="/paymanual")
    public ModelAndView paymanual( Principal principal, ModelAndView mv ) {

        mv.setViewName("xpay/paymanual");

        String adminID = principal.getName();
        if (!adminID.equals("Apex1234")) {
            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U902"));
            ec.setErrCode("ERR0801");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("adminID", adminID);
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }

        return mv;
    }

    @RequestMapping(value="/exepaymanual")
    public String exepaymanually( ApplicationPaymentTransaction applPayTr ) {

        Application application = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.ApplicationMapper.selectByPrimaryKey",
                applPayTr.getApplNo(), Application.class);

        // 지원상태가 '00010' / '00021' 만 처리 가능
        if( application == null || application.getApplStsCode() == null ||
                (!application.getApplStsCode().equals("00010") && !application.getApplStsCode().equals("00021")) ) {
            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage("지원상태가 '작성완료(00010/00021)' 이어야 처리 가능합니다.");
            ec.setErrCode("");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", applPayTr.getApplNo().toString());
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }

        // 필요한 데이터 처리 (상태, 수험번호, 결제정보)
        paymentService.registerManualPay(applPayTr);

        // 수험표, 지원서 생성 및 Merge
        // 타 대학원 확장 시 TODO - 학교 이름을 파라미터로 받도록
        String admsTypeCode = application.getAdmsTypeCode();
        String lang = "C".equals(admsTypeCode) || "D".equals(admsTypeCode) ? "en" : "kr";
        String reportName = "yonsei-appl-" + lang;
        ExecutionContext ecGenAppl = birtService.generateBirtFile(application.getApplNo(), reportName);
        reportName = "yonsei-adms-" + lang;
        ExecutionContext ecGenAdms = birtService.generateBirtFile(application.getApplNo(), reportName);
        ExecutionContext ecPdfMerge = pdfService.getMergedPDFByApplicants(applPayTr.getApplNo());
        if ( ExecutionContext.FAIL.equals(ecGenAppl.getResult()) ||
                ExecutionContext.FAIL.equals(ecGenAdms.getResult()) ||
                ExecutionContext.FAIL.equals(ecPdfMerge.getResult()) ) {
            throw new YSBizException();
        }

        return "xpay/resultmanual";
    }

    @RequestMapping(value="/pdfmanual")
    public ModelAndView pdfmanual( Principal principal, ModelAndView mv ) {

        mv.setViewName("xpay/pdfmanual");

        String adminID = principal.getName();
        if (!adminID.equals("Apex1234")) {
            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U902"));
            ec.setErrCode("ERR0801");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("adminID", adminID);
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }

        return mv;
    }

    @RequestMapping(value="/exepdfmanual")
    public String exepdfmanually( ApplicationPaymentTransaction applPayTr ) {

        ExecutionContext ec;

        // 수험표, 지원서 생성 및 Merge
        // 타 대학원 확장 시 TODO - 학교 이름을 파라미터로 받도록
        Application application = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.ApplicationMapper.selectByPrimaryKey",
                                                           applPayTr.getApplNo(), Application.class);
//        ec = processReGenMergeUpload(application);
        String admsTypeCode = application.getAdmsTypeCode();
        String lang = "C".equals(admsTypeCode) || "D".equals(admsTypeCode) ? "en" : "kr";
        String reportName = "yonsei-appl-" + lang;
        ExecutionContext ecGenAppl = birtService.generateBirtFile(application.getApplNo(), reportName);
        reportName = "yonsei-adms-" + lang;
        ExecutionContext ecGenAdms = birtService.generateBirtFile(application.getApplNo(), reportName);
        ExecutionContext ecPdfMerge = pdfService.getMergedPDFByApplicants(applPayTr.getApplNo());
        if ( ExecutionContext.FAIL.equals(ecGenAppl.getResult()) ||
                ExecutionContext.FAIL.equals(ecGenAdms.getResult()) ||
                ExecutionContext.FAIL.equals(ecPdfMerge.getResult()) ) {
            throw new YSBizException();
        }

        return "xpay/resultpdf";
    }

    /**
     * 결제 완료되었으나 최종 파일이 업로드 되지 않은 원서를
     * Batch로 파일 수동 생성 및 업로드 요청하는 화면
     *
     * @return
     */
    @RequestMapping(value = "/form-re-generate-merge-upload")
    public ModelAndView formReGenMergeUpload(Principal principal, ModelAndView mv) {

        mv.setViewName("xpay/formReGenMergeUpload");

        String adminID = principal.getName();
        if (!adminID.equals("Apex1234")) {
            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U902"));
            ec.setErrCode("ERR0801");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("adminID", adminID);
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }

        return mv;
    }

    /**
     * 결제 완료되었으나 최종 파일이 업로드 되지 않은 원서를
     * Batch로 파일 수동 생성 및 업로드
     *
     * @param principal
     * @param mv
     * @return
     */
    @RequestMapping(value = "/re-generate-merge-upload")
    public ModelAndView reGenMergeUpload(Principal principal,
                                         @RequestParam("fileYn") String fileYn,
                                         ModelAndView mv) {
        long start = System.currentTimeMillis();
        mv.setViewName("xpay/rsltReGenMergeUpload");
        String adminID = principal.getName();
        if (!adminID.equals("Apex1234")) {
            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U902"));
            ec.setErrCode("ERR0801");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("adminID", adminID);
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }

        List<Application> applList = commonDAO.queryForList(
                "com.apexsoft.ysprj.applicants.application.sqlmap.CustomApplicationMapper.selectPaidButApplIdNotApplieddApplList",
                fileYn,
                Application.class);

        List<Application> failGenApplList = new ArrayList<Application>();
        List<Application> failGenAdmsList = new ArrayList<Application>();
        List<Application> failMergePdfList = new ArrayList<Application>();
        ExecutionContext ec = null;
        Map<String, Object> resultMap = null;
        String resultGenAppl = null, resultGenAdms = null, resultMergePdf = null;
        List<List<ApplicationDocument>> encryptedPdfListList = new ArrayList<List<ApplicationDocument>>();
        int successCount = 0;
        for (Application application : applList) {
//        for (int i = 0 ; i < 1 ; i++) {
//            Application application = applList.get(i);

            ec = processReGenMergeUpload(application);
            resultMap = (Map<String, Object>)ec.getData();
            resultGenAppl = (String)resultMap.get("genAppl");
            resultGenAdms = (String)resultMap.get("genAdms");
            resultMergePdf = (String)resultMap.get("mergePdf");


            if (ExecutionContext.FAIL.equals(resultGenAppl)) {
                failGenApplList.add(application);
            }
            if (ExecutionContext.FAIL.equals(resultGenAdms)) {
                failGenAdmsList.add(application);
            }
            if (ExecutionContext.FAIL.equals(resultMergePdf)) {
                failMergePdfList.add(application);
                encryptedPdfListList.add((List<ApplicationDocument>)resultMap.get("encryptedPdfList"));
            }
            if (ExecutionContext.SUCCESS.equals(resultGenAppl) &&
                ExecutionContext.SUCCESS.equals(resultGenAdms) &&
                ExecutionContext.SUCCESS.equals(resultMergePdf)) {
                successCount++;
            }
            ec = null;
            resultMap.clear();
            resultGenAppl = resultGenAdms = resultMergePdf = null;
        }
        mv.addObject("totalCount", applList.size());
        mv.addObject("successCount", successCount);
        mv.addObject("failGenApplList", failGenApplList);
        mv.addObject("failGenAdmsList", failGenAdmsList);
        mv.addObject("failMergePdfList", failMergePdfList);
        mv.addObject("encryptedPdfListList", encryptedPdfListList);
        long end = System.currentTimeMillis();
        mv.addObject("elapsedTime", (end - start) / 1000 + " seconds");
        return mv;
    }

    /**
     * 결제 완료된 모든 원서에 대해
     * APP이 실행되는 서버 로컬에 다운받은 파일을 기준으로
     * 최종 원서가 존재하는지
     * 수험번호가 매겨져 있는 지
     * 통계를 화면에 보여주고
     * 상세 내역을 APPL_DOC_RSLT에 기록
     *
     * @param principal
     * @param mv
     * @return
     */
    @RequestMapping(value="/analyze/pdf")
    public ModelAndView analyzeFinalPDF(Principal principal,
                                        ModelAndView mv) {
        long start = System.currentTimeMillis();
        mv.setViewName("xpay/appldocresult");
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("totalPaidAppl", 0);
        map.put("fileWithApplId", 0);
        map.put("fileWithoutApplId", 0);
        map.put("fileNotFound", 0);
        String notPaidApplId = "지원 미완료";
        String applIdTitle = "수험번호";
        String applIdNone = "NONE";
        String comma = ", ";
        String strY = "Y";
        String strN = "N";
        String adminID = principal.getName();
        if (!adminID.equals("Apex1234")) {
            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U902"));
            ec.setErrCode("ERR0801");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("adminID", adminID);
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        } else {
            List<Application> paidApplList = paymentService.retrieveApplByApplStsCode("00020");
            commonDAO.delete("com.apexsoft.ysprj.applicants.payment.sqlmap.CustomApplicationDocumentResultMapper.deleteAllRowsFromApplDocRslt");
            int fileWithApplId = 0, fileWithoutApplId = 0, fileNotFound = 0;
            List<CustomApplicationDocumentResult> docRsltList = new ArrayList<CustomApplicationDocumentResult>();
            for (Application appl : paidApplList) {
                String pdfFileFulPath = FileUtil.getFinalMergedFileFullPath(MERGE_TEST_DIR, MID_PATH, appl);
                File mergedPdfFile = new File(pdfFileFulPath);
                FileInputStream fis = null;

                try {
                    fis = new FileInputStream(mergedPdfFile);

                    Document pdf = PDF.open(mergedPdfFile);
                    StringBuilder text = new StringBuilder(100);
                    pdf.getPage(0).pipe(new OutputTarget(text));
                    String applId = null;
//                    BlockParent bp = pdf.getPage(0).getTextContent();
//                    System.out.println(bp.getChild(0).getLineCnt());
//                    System.out.println(bp.getChildCnt());
//                    System.out.println(bp.getChild(0));
//                    System.out.println(bp.getChild(1).getLineCnt());
//                    System.out.println("##################################################################");

                    try {
                        pdf.close();
                    } catch (IOException e) {
                        ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
                        ec.setErrCode("PDFTextStream Document Closing Error");
                        throw new YSBizException(ec);
                    }
//                    System.err.println(text);
                    if (text.indexOf(notPaidApplId) < 0) {
                        fileWithApplId++;
                        int posOfapplId = text.indexOf(applIdTitle) + applIdTitle.length() + 5;
                        int lengthOfapplId = 10;
                        applId = text.substring(posOfapplId, posOfapplId + lengthOfapplId);
                    }
                    else {
                        fileWithoutApplId++;
                        applId = applIdNone;
                    }

                    // insert FILE_YN = Y, APPL_ID = $값
                    CustomApplicationDocumentResult aDocRslt = new CustomApplicationDocumentResult();
                    aDocRslt.setApplNo(appl.getApplNo());
                    aDocRslt.setFileYn(strY);
                    aDocRslt.setApplId(applId);
//                    System.out.println(new StringBuilder().append(aDocRslt.getApplNo()).append(comma).append(aDocRslt.getFileYn()).append(comma).append(aDocRslt.getApplId()));
                    docRsltList.add(aDocRslt);
                } catch (IOException e) {
                    // insert FILE_YN = N
                    fileNotFound++;
                    CustomApplicationDocumentResult aDocRslt = new CustomApplicationDocumentResult();
                    aDocRslt.setApplNo(appl.getApplNo());
                    aDocRslt.setFileYn(strN);
                    aDocRslt.setApplId(applIdNone);
//                    System.out.println(new StringBuilder().append(aDocRslt.getApplNo()).append(comma).append(aDocRslt.getFileYn()).append(comma).append(aDocRslt.getApplId()));
                    docRsltList.add(aDocRslt);
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
                            ec.setErrCode("Error While closing a file");
                            throw new YSBizException(ec);
                        }
                    }
                }
            }
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("paramList", docRsltList);
            commonDAO.insert("com.apexsoft.ysprj.applicants.payment.sqlmap.CustomApplicationDocumentResultMapper.insertRows", dataMap);
            map.put("totalPaidAppl", paidApplList.size());
            map.put("fileWithApplId", fileWithApplId);
            map.put("fileWithoutApplId", fileWithoutApplId);
            map.put("fileNotFound", fileNotFound);
        }
        mv.addAllObjects(map);
        long end = System.currentTimeMillis();
        System.out.println("PDF Analyzer finished job in " + (end - start)/1000 + " seconds");
        return mv;
    }

    /**
     * S3에 올라가 있는 파일을 다운로드 하기 위해 applNo 목록을 입력받는 화면
     *
     * @param principal
     * @param mv
     * @return
     */
    @RequestMapping(value = "/form-download-all")
    public ModelAndView downloadAllUploadedFiles(Principal principal, ModelAndView mv) {
        mv.setViewName("xpay/formDownloadAll");

        String adminID = principal.getName();
        if (!adminID.equals("Apex1234")) {
            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U902"));
            ec.setErrCode("ERR0801");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("adminID", adminID);
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }

        return mv;
    }

    /**
     * S3에 applNo로 올라가 있는 파일 모두 다운로드
     *
     * @param principal
     * @param applNoList
     * @param mv
     * @return
     */
    @RequestMapping(value = "/download-all")
    public ModelAndView downloadAllUploadedFiles(Principal principal,
                                                 @RequestParam("applNoList") String applNoList,
                                                 ModelAndView mv) {
        long start = System.currentTimeMillis();
        mv.setViewName("xpay/rsltDownloadAll");
        String[] applNoArr = applNoList.split("\n");


        // S3에서 다운로드
        // 파일 하나하나 읽고 isEncrypted 검사
        //

        long end = System.currentTimeMillis();
        System.out.println("PDF Analyzer finished job in " + (end - start) / 1000 + " seconds");
        return mv;
    }

    private ExecutionContext processReGenMergeUpload(Application application) {
        ExecutionContext ecResult = new ExecutionContext();
        Map<String, Object> map = new HashMap<String, Object>();

        String admsTypeCode = application.getAdmsTypeCode();
        int applNo = application.getApplNo();
        String lang = "C".equals(admsTypeCode) || "D".equals(admsTypeCode) ? "en" : "kr";
        String reportName = "yonsei-appl-" + lang;

        ExecutionContext ecGenAppl = null;

        try {
            ecGenAppl = birtService.generateBirtFile(applNo, reportName);
        } catch (Exception e) {
            ecGenAppl = new ExecutionContext(ExecutionContext.FAIL);
        } finally {
            map.put("genAppl", ecGenAppl.getResult());
        }

        reportName = "yonsei-adms-" + lang;
        ExecutionContext ecGenAdms = null;
        try {
            ecGenAdms = birtService.generateBirtFile(applNo, reportName);
        } catch (Exception e) {
            ecGenAdms = new ExecutionContext(ExecutionContext.FAIL);
        } finally {
            map.put("genAdms", ecGenAdms.getResult());
        }

        ExecutionContext ecPdfMerge = null;
        try {
            ecPdfMerge = pdfService.getMergedPDFByApplicants(applNo);
        } catch (Exception e) {
            ecPdfMerge = new ExecutionContext(ExecutionContext.FAIL);
            if (e instanceof YSBizNoticeException) {
                YSBizNoticeException ybne = (YSBizNoticeException)e;
                ExecutionContext ec = ybne.getExecutionContext();
                List<Application> encryptedPdfList = (List<Application>)ec.getData();
                map.put("encryptedPdfList", encryptedPdfList);
            }
        } finally {
            map.put("mergePdf", ecPdfMerge.getResult());
        }

        ecResult.setData(map);

        return ecResult;
    }

}
