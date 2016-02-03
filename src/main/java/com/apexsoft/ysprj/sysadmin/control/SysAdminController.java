package com.apexsoft.ysprj.sysadmin.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.common.service.BirtService;
import com.apexsoft.ysprj.applicants.common.service.PDFService;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import com.apexsoft.ysprj.applicants.payment.domain.ApplicationPaymentTransaction;
import com.apexsoft.ysprj.applicants.payment.service.PaymentService;
import com.apexsoft.ysprj.sysadmin.service.SysAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.*;

/**
 * Created by hanmomhanda on 15. 4. 15.
 *
 * 수동 작업을 위한 SYSADMIN 기능 모음
 * SYSADMIN 기능이므로 일부러 Service 로 분리하지는 않고 Controller에서 처리
 */
@Controller
@RequestMapping(value="/sysadmin")
public class SysAdminController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BirtService birtService;

    @Autowired
    private PDFService pdfService;

    @Autowired
    private SysAdminService sysAdminService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private CommonDAO commonDAO;

    @Value("#{app['file.mergeTestDir']}")
    private String MERGE_TEST_DIR;

    @Value("#{app['s3.midPath']}")
    private String MID_PATH;

    /**
     * 관리자용 내원서 화면 보기를 위한 화면
     *
     * @param mv
     * @return
     */
    @RequestMapping(value="/form-showlist")
    public ModelAndView formShowList(ModelAndView mv) {
        mv.setViewName("sysadmin/formShowMylist");
        return mv;
    }

    /**
     * 관리자용 내원서 화면
     *
     * @param mv
     * @return
     */
    @RequestMapping(value="/showlist/{userId}")
    public ModelAndView showApplListByUserId(@PathVariable("userId") String userId, ModelAndView mv) {
        mv.setViewName("application/mylist");

        ParamForApplication parameter = new ParamForApplication();
        parameter.setUserId(userId);

        List<CustomMyList> myList =
                commonDAO.queryForList("com.apexsoft.ysprj.applicants.application.sqlmap.CustomApplicationMapper.selectApplByUserId",
                        parameter, CustomMyList.class);

        mv.addObject("myList", myList);
        mv.addObject("isSYSADMIN", true);
        mv.addObject("userId", userId);

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
     * @param mv
     * @return
     * @deprecated 서버 로컬에 파일 저장 안함
     */
//    @RequestMapping(value="/analyze/pdf")
//    public ModelAndView analyzeFinalPDF(ModelAndView mv) {
//        long start = System.currentTimeMillis();
//        mv.setViewName("sysadmin/rsltAnalyzePdf");
//        Map<String, Integer> map = new HashMap<String, Integer>();
//        map.put("totalPaidAppl", 0);
//        map.put("fileWithApplId", 0);
//        map.put("fileWithoutApplId", 0);
//        map.put("fileNotFound", 0);
//        String notPaidApplId = "지원 미완료";
//        String applIdTitle = "수험번호";
//        String applIdNone = "NONE";
//        String strY = "Y";
//        String strN = "N";
//
//        List<Application> paidApplList = paymentService.retrieveApplByApplStsCode(ApplicationStatus.COMPLETED.codeVal());
//        commonDAO.delete("com.apexsoft.ysprj.applicants.payment.sqlmap.CustomApplicationDocumentResultMapper.deleteAllRowsFromApplDocRslt");
//        int fileWithApplId = 0, fileWithoutApplId = 0, fileNotFound = 0;
//        List<CustomApplicationDocumentResult> docRsltList = new ArrayList<CustomApplicationDocumentResult>();
//        for (Application appl : paidApplList) {
//            String pdfFileFulPath = FilePathUtil.getFinalMergedFileFullPath(MERGE_TEST_DIR, MID_PATH, appl);
//            File mergedPdfFile = new File(pdfFileFulPath);
//            FileInputStream fis = null;
//
//            try {
//                fis = new FileInputStream(mergedPdfFile);
//
//                Document pdf = PDF.open(mergedPdfFile);
//                StringBuilder text = new StringBuilder(100);
//                pdf.getPage(0).pipe(new OutputTarget(text));
//                String applId = null;
//
//                try {
//                    pdf.close();
//                } catch (IOException e) {
//                    ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
//                    ec.setErrCode("PDFTextStream Document Closing Error");
//                    throw new YSBizException(ec);
//                }
//
//                if (text.indexOf(notPaidApplId) < 0) {
//                    fileWithApplId++;
//                    int posOfapplId = text.indexOf(applIdTitle) + applIdTitle.length() + 5;
//                    int lengthOfapplId = 10;
//                    applId = text.substring(posOfapplId, posOfapplId + lengthOfapplId);
//                }
//                else {
//                    fileWithoutApplId++;
//                    applId = applIdNone;
//                }
//
//                // insert FILE_YN = Y, APPL_ID = $값
//                CustomApplicationDocumentResult aDocRslt = new CustomApplicationDocumentResult();
//                aDocRslt.setApplNo(appl.getApplNo());
//                aDocRslt.setFileYn(strY);
//                aDocRslt.setApplId(applId);
////                    System.out.println(new StringBuilder().append(aDocRslt.getApplNo()).append(comma).append(aDocRslt.getFileYn()).append(comma).append(aDocRslt.getApplId()));
//                docRsltList.add(aDocRslt);
//            } catch (IOException e) {
//                // insert FILE_YN = N
//                fileNotFound++;
//                CustomApplicationDocumentResult aDocRslt = new CustomApplicationDocumentResult();
//                aDocRslt.setApplNo(appl.getApplNo());
//                aDocRslt.setFileYn(strN);
//                aDocRslt.setApplId(applIdNone);
////                    System.out.println(new StringBuilder().append(aDocRslt.getApplNo()).append(comma).append(aDocRslt.getFileYn()).append(comma).append(aDocRslt.getApplId()));
//                docRsltList.add(aDocRslt);
//            } finally {
//                if (fis != null) {
//                    try {
//                        fis.close();
//                    } catch (IOException e) {
//                        ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
//                        ec.setErrCode("Error While closing a file");
//                        throw new YSBizException(ec);
//                    }
//                }
//            }
//        }
//        Map<String, Object> dataMap = new HashMap<String, Object>();
//        dataMap.put("paramList", docRsltList);
//        commonDAO.insert("com.apexsoft.ysprj.applicants.payment.sqlmap.CustomApplicationDocumentResultMapper.insertRows", dataMap);
//        map.put("totalPaidAppl", paidApplList.size());
//        map.put("fileWithApplId", fileWithApplId);
//        map.put("fileWithoutApplId", fileWithoutApplId);
//        map.put("fileNotFound", fileNotFound);
//
//        mv.addAllObjects(map);
//        long end = System.currentTimeMillis();
//        System.out.println("PDF Analyzer finished job in " + (end - start) / 1000 + " seconds");
//        mv.addObject("elapsedTime", (end - start) / 1000 + " seconds");
//        return mv;
//    }

    /**
     * 수험번호 박힌 최종 PDF파일 수동 생성을 위해 applNo를 입력받는 화면
     *
     * @param mv
     * @return
     */
    @RequestMapping(value="/form-pdf-manual")
    public ModelAndView pdfmanual( ModelAndView mv ) {
        mv.setViewName("sysadmin/formPdfManual");
        return mv;
    }

    /**
     * applNo로 수험번호 박힌 최종 PDF 파일 1개 생성
     *
     * @param applNo
     * @param mv
     * @return
     */
    @RequestMapping(value="/pdf-manual")
    public ModelAndView exepdfmanually( @RequestParam(value = "applNo") int applNo, ModelAndView mv ) {
        mv.setViewName("sysadmin/rsltPdfManual");
        ExecutionContext ec;

        // 수험표, 지원서 생성 및 Merge
        // 타 대학원 확장 시 TODO - 학교 이름을 파라미터로 받도록
        Application application = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.ApplicationMapper.selectByPrimaryKey",
                applNo, Application.class);
        String lang = application.isForeignAppl() ? "en" : "kr";
        String reportName = "yonsei-appl-" + lang;
        ExecutionContext ecGenAppl = birtService.generateBirtFile(application.getApplNo(), reportName);
        reportName = "yonsei-adms-" + lang;
        ExecutionContext ecGenAdms = birtService.generateBirtFile(application.getApplNo(), reportName);
        ExecutionContext ecPdfMerge = pdfService.genAndUploadPDFByApplicants(application);
        if ( ExecutionContext.FAIL.equals(ecGenAppl.getResult()))
            throw new YSBizException(ecGenAppl);
        if ( ExecutionContext.FAIL.equals(ecGenAdms.getResult()))
            throw new YSBizException(ecGenAdms);
        if ( ExecutionContext.FAIL.equals(ecPdfMerge.getResult()))
            throw new YSBizException(ecPdfMerge);

        return mv;
    }

    /**
     * 수험번호 박힌 최종 PDF파일 수동 생성을 위해 쉼표로 구분된 applNo를 입력받는 화면
     *
     * @param mv
     * @return
     */
    @RequestMapping(value="/form-pdf-manual-multi")
    public ModelAndView pdfManualMulti( ModelAndView mv ) {
        mv.setViewName("sysadmin/formPdfManualMulti");
        return mv;
    }

    /**
     * 쉼표로 구분된 applNo로 수험번호 박힌 최종 PDF 파일 다건 생성
     *
     * @param mv
     * @return
     */
    @RequestMapping(value="/pdf-manual-multi")
    public ModelAndView pdfManualMulti( @RequestParam("noList") String applNoList, ModelAndView mv ) {
        mv.setViewName("sysadmin/rsltPdfManualMulti");
        ExecutionContext ec;

        String[] applNos = applNoList.split(",");

        List<Application> failedList = new ArrayList<Application>();
        int okCount = 0;

        for (String applNo : applNos) {
            Application application = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.ApplicationMapper.selectByPrimaryKey",
                    Integer.parseInt(applNo), Application.class);
            String lang = application.isForeignAppl() ? "en" : "kr";
            String reportName = "yonsei-appl-" + lang;
            ExecutionContext ecGenAppl = new ExecutionContext();
            try {
                ecGenAppl = birtService.generateBirtFile(application.getApplNo(), reportName);
            } catch (Exception e) {
                ecGenAppl.setResult(ExecutionContext.FAIL);
            }

            reportName = "yonsei-adms-" + lang;
            ExecutionContext ecGenAdms = new ExecutionContext();
            try {
                ecGenAdms = birtService.generateBirtFile(application.getApplNo(), reportName);
            } catch (Exception e) {
                ecGenAdms.setResult(ExecutionContext.FAIL);
            }

            ExecutionContext ecPdfMerge = new ExecutionContext();
            try {
                ecPdfMerge = pdfService.genAndUploadPDFByApplicants(application);
            } catch (Exception e) {
                ecPdfMerge.setResult(ExecutionContext.FAIL);
            }

            if ( ExecutionContext.FAIL.equals(ecGenAppl.getResult())) {
                failedList.add(application);
            } else if ( ExecutionContext.FAIL.equals(ecGenAdms.getResult())) {
                failedList.add(application);
            } else if ( ExecutionContext.FAIL.equals(ecPdfMerge.getResult())) {
                failedList.add(application);
            } else {
                okCount++;
            }
        }

        mv.addObject("failedList", failedList);
        mv.addObject("totalCount", applNos.length);
        mv.addObject("okCount", okCount);

        return mv;
    }

    /**
     * 결제 완료되었으나 최종 파일이 업로드 되지 않은 원서를
     * Batch로 파일 수동 생성 및 업로드 요청하는 화면
     *
     * @return
     * @Deprecated 단건 또는 다건 등 applNo 별로 처리하고 배치는 제공 안함
     */
//    @RequestMapping(value = "/form-batch-re-generate-merge-upload")
//    public ModelAndView formReGenMergeUpload(ModelAndView mv) {
//        mv.setViewName("sysadmin/formBatchReGenMergeUpload");
//        return mv;
//    }

    /**
     * 결제 완료되었으나 최종 파일이 업로드 되지 않은 원서를
     * Batch로 파일 수동 생성 및 업로드
     *
     * @param mv
     * @return
     * @Deprecated 단건 또는 다건 등 applNo 별로 처리하고 배치는 제공 안함
     */
//    @RequestMapping(value = "/batch-re-generate-merge-upload")
//    public ModelAndView reGenMergeUpload(@RequestParam("fileYn") String fileYn,
//                                         ModelAndView mv) {
//        long start = System.currentTimeMillis();
//        mv.setViewName("sysadmin/rsltBatchReGenMergeUpload");
//
//        List<Application> applList = commonDAO.queryForList(
//                "com.apexsoft.ysprj.applicants.application.sqlmap.CustomApplicationMapper.selectPaidButApplIdNotApplieddApplList",
//                fileYn,
//                Application.class);
//
//        List<Application> failGenApplList = new ArrayList<Application>();
//        List<Application> failGenAdmsList = new ArrayList<Application>();
//        List<Application> failMergePdfList = new ArrayList<Application>();
//        ExecutionContext ec = null;
//        Map<String, Object> resultMap = null;
//        String resultGenAppl = null, resultGenAdms = null, resultMergePdf = null;
//        List<List<ApplicationDocument>> encryptedPdfListList = new ArrayList<List<ApplicationDocument>>();
//        int successCount = 0;
//        for (Application application : applList) {
////        for (int i = 0 ; i < 1 ; i++) {
////            Application application = applList.get(i);
//
//            ec = sysAdminService.processReGenMergeUpload(application);
//            // PDF 합치기로 변경한 이후 ec에는 아무 데이터 들어가있지 않음
//            resultMap = (Map<String, Object>)ec.getData();
//            resultGenAppl = (String)resultMap.get("genAppl");
//            resultGenAdms = (String)resultMap.get("genAdms");
//            resultMergePdf = (String)resultMap.get("mergePdf");
//
//
//            if (ExecutionContext.FAIL.equals(resultGenAppl)) {
//                failGenApplList.add(application);
//            }
//            if (ExecutionContext.FAIL.equals(resultGenAdms)) {
//                failGenAdmsList.add(application);
//            }
//            if (ExecutionContext.FAIL.equals(resultMergePdf)) {
//                failMergePdfList.add(application);
//                encryptedPdfListList.add((List<ApplicationDocument>)resultMap.get("encryptedPdfList"));
//            }
//            if (ExecutionContext.SUCCESS.equals(resultGenAppl) &&
//                    ExecutionContext.SUCCESS.equals(resultGenAdms) &&
//                    ExecutionContext.SUCCESS.equals(resultMergePdf)) {
//                successCount++;
//            }
//            ec = null;
//            resultMap.clear();
//            resultGenAppl = resultGenAdms = resultMergePdf = null;
//        }
//        mv.addObject("totalCount", applList.size());
//        mv.addObject("successCount", successCount);
//        mv.addObject("failGenApplList", failGenApplList);
//        mv.addObject("failGenAdmsList", failGenAdmsList);
//        mv.addObject("failMergePdfList", failMergePdfList);
//        mv.addObject("encryptedPdfListList", encryptedPdfListList);
//        long end = System.currentTimeMillis();
//        mv.addObject("elapsedTime", (end - start) / 1000 + " seconds");
//        return mv;
//    }


    /**
     * S3에 올라가 있는 파일을 다운로드 하기 위해 applNo을 입력받는 화면
     *
     * @param mv
     * @return
     */
    @RequestMapping(value = "/form-download-zip")
    public ModelAndView downloadAllUploadedFiles(ModelAndView mv) {
        mv.setViewName("sysadmin/formDownLoadZip");
        return mv;
    }

    /**
     * S3에 올라가 있는 zip 파일 다운로드
     *
     * @param applNo 다운받을 applNo
     * @return
     */
    @RequestMapping(value = "/download-zip", produces = "application/octet-stream")
    @ResponseBody
    public byte[] downloadAllUploadedFiles(@RequestParam("applNo") int applNo,
                                           HttpServletResponse response)
                        throws IOException, InterruptedException {

        Application application = documentService.getApplication(applNo);
        Map<String, byte[]> downloadableFileInfo = documentService.getDownloadableFileAsBytes(application, "merged");

        Set<String> key = downloadableFileInfo.keySet();
        Iterator<String> iter = key.iterator();
        String fileName = null;
        if (iter.hasNext()) {
            fileName = iter.next();
        }
        byte[] bytes = downloadableFileInfo.get(fileName);
        String downaloadFileName = StringUtil.urlEncodeSpecialCharacter(URLEncoder.encode(fileName, "UTF-8"));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + downaloadFileName + "\"");
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        response.setHeader("Content-Type", "application/octet-stream");
        response.setContentLength(bytes.length);

        return bytes;
    }


    /**
     * 수동 결제 화면
     *
     * @param principal
     * @param mv
     * @return
     */
    @RequestMapping(value="/form-pay-manual")
    public ModelAndView paymanual( Principal principal, ModelAndView mv ) {
        mv.setViewName("sysadmin/formPayManual");
        return mv;
    }

    /**
     * 수동 결제 처리
     *
     * @param applPayTr
     * @param mv
     * @return
     */
    @RequestMapping(value="/pay-manual")
    public ModelAndView exepaymanually( ApplicationPaymentTransaction applPayTr, ModelAndView mv ) {
        mv.setViewName("sysadmin/rsltPayManual");
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

        // 원서 수험표, 생성, S3 업로드
        paymentService.processApplicationFiles(application);

        // 지원 완료 알림 메일 발송
        paymentService.sendNotification(application);

//        // 수험표, 지원서 생성 및 Merge
//        // 타 대학원 확장 시 TODO - 학교 이름을 파라미터로 받도록
//        String admsTypeCode = application.getAdmsTypeCode();
//        String lang = "C".equals(admsTypeCode) || "D".equals(admsTypeCode) ? "en" : "kr";
//        String reportName = "yonsei-appl-" + lang;
//        ExecutionContext ecGenAppl = birtService.generateBirtFile(application.getApplNo(), reportName);
//        reportName = "yonsei-adms-" + lang;
//        ExecutionContext ecGenAdms = birtService.generateBirtFile(application.getApplNo(), reportName);
//        ExecutionContext ecPdfMerge = pdfService.genAndUploadPDFByApplicants(application);
//        if ( ExecutionContext.FAIL.equals(ecGenAppl.getResult()) ||
//                ExecutionContext.FAIL.equals(ecGenAdms.getResult()) ||
//                ExecutionContext.FAIL.equals(ecPdfMerge.getResult()) ) {
//            throw new YSBizException();
//        }

        return mv;
    }

    @RequestMapping(value="/form-backup-all-pdf")
    public ModelAndView formBackupAllPdf(ModelAndView mv) {
        mv.setViewName("sysadmin/formBackupAllPdf");
        return mv;
    }

    @RequestMapping(value="/rslt-backup-all-pdf")
    public ModelAndView rsltBackupAllPdf(ModelAndView mv) {
        mv.setViewName("sysadmin/rsltBackupAllPdf");
        ExecutionContext ec = sysAdminService.downloadAllPdf();
        Map<String, String> map = (Map<String, String>)ec.getData();
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        for (Map.Entry<String, String> item : entrySet) {
            mv.addObject(item.getKey(), item.getValue());
        }
        return mv;
    }

    @RequestMapping(value="/form-backup-all-slip-pdf")
    public ModelAndView formBackupAllSlipPdf(ModelAndView mv) {
        mv.setViewName("sysadmin/formBackupAllSlipPdf");
        return mv;
    }

    @RequestMapping(value="/rslt-backup-all-slip-pdf")
    public ModelAndView rsltBackupAllSlipPdf(ModelAndView mv) {
        mv.setViewName("sysadmin/rsltBackupAllSlipPdf");
        ExecutionContext ec = sysAdminService.downloadAllSlipPdf();
        Map<String, String> map = (Map<String, String>)ec.getData();
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        for (Map.Entry<String, String> item : entrySet) {
            mv.addObject(item.getKey(), item.getValue());
        }
        return mv;
    }

    @RequestMapping(value="/form-generate-pic")
    public ModelAndView formGeneratePic(ModelAndView mv) {
        mv.setViewName("sysadmin/formGeneratePic");
        return mv;
    }

    @RequestMapping(value="/rslt-generate-pic")
    public ModelAndView rsltGeneratePic(ModelAndView mv) {
        mv.setViewName("sysadmin/rsltGeneratePic");
        sysAdminService.downaloadRenamedPictures();
//        ExecutionContext ec = sysAdminService.downaloadRenamedPictures();
//        Map<String, String> map = (Map<String, String>)ec.getData();
//        Set<Map.Entry<String, String>> entrySet = map.entrySet();
//        for (Map.Entry<String, String> item : entrySet) {
//            mv.addObject(item.getKey(), item.getValue());
//        }
        return mv;
    }
}
