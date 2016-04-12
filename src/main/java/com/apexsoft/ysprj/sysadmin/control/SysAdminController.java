package com.apexsoft.ysprj.sysadmin.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.CustomMyList;
import com.apexsoft.ysprj.applicants.application.domain.ParamForApplication;
import com.apexsoft.ysprj.applicants.application.domain.Recommendation;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.application.service.RecommendationService;
import com.apexsoft.ysprj.applicants.common.service.BirtService;
import com.apexsoft.ysprj.applicants.common.service.PDFService;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import com.apexsoft.ysprj.applicants.payment.domain.ApplicationPaymentTransaction;
import com.apexsoft.ysprj.applicants.payment.service.PaymentService;
import com.apexsoft.ysprj.sysadmin.service.SysAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    private RecommendationService recommendationService;

    @Autowired
    private CommonDAO commonDAO;

    @Value("#{app['file.mergeTestDir']}")
    private String MERGE_TEST_DIR;

    @Value("#{app['file.midPath']}")
    private String MID_PATH;

    @Value("#{app['file.picturesDir']}")
    private String PICTURE_PATH;

    @Value("#{app['recommendation.notice.sendkey']}")
    private String SEND_KEY;

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

        return mv;
    }

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
        ExecutionContext ecGenAppl = birtService.generateBirtFile(application, reportName);
        reportName = "yonsei-adms-" + lang;
        ExecutionContext ecGenAdms = birtService.generateBirtFile(application, reportName);

        // 원서 및 수험표 업로드
        paymentService.processApplicationFiles(application);

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
                ecGenAppl = birtService.generateBirtFile(application, reportName);
            } catch (Exception e) {
                ecGenAppl.setResult(ExecutionContext.FAIL);
            }

            reportName = "yonsei-adms-" + lang;
            ExecutionContext ecGenAdms = new ExecutionContext();
            try {
                ecGenAdms = birtService.generateBirtFile(application, reportName);
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
     * 추천서 요청을 받았지만 아직 추천서를 등록하지 않은 모든 추천자에게 메일 발송
     *
     * @param mv
     * @return
     */
    @RequestMapping(value = "/form-send-urge-recommendation-mail")
    public ModelAndView sendUrgeRecommendationMailtoAllRecommendor(ModelAndView mv) {
        mv.setViewName("sysadmin/formSendUrgeRecommendationMailToAllRecommender");
        mv.addObject("sendkey", SEND_KEY);
        return mv;
    }

    /**
     * 추천 요청을 받은 추천자 전체에게 추천 처리 독려 메일 발송
     * @param sendKey
     */
    @RequestMapping(value = "/sendUrgeRecommendationMailToAll", method = RequestMethod.POST)
    public ModelAndView sendUrgeRecommendationMail(@RequestParam(value = "sendkey") String sendKey, ModelAndView mv) {
        mv.setViewName("sysadmin/rsltSendUrgeMailToAll");
        ExecutionContext ec = null;
        ExecutionContext ec1 = null;
        // 독려 메일 발송 키 확인
        if (SEND_KEY.equals(sendKey)) {
            // APPL_REC 테이블에서 REC_STS_CODE 가 00002(요청완료), 00003(교수확인)인 목록 조회
            ec = recommendationService.retrieveUncompletedRecommendationList();
            if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
                List<Recommendation> uncompletedRecList = (List<Recommendation>) ec.getData();
                ec1 = recommendationService.sendUrgeMail(uncompletedRecList);
                List<Mail> failedMailList = (List<Mail>)ec1.getData();
                List<Recommendation> failedSendingRecommendationList = new ArrayList<>();
                for (Mail mail : failedMailList) {
                    Recommendation rec = (Recommendation)mail.getInfo();
                    failedSendingRecommendationList.add(rec);
                }
                mv.addObject("allList", uncompletedRecList);
                // SES에서 발송 자체가 오류난 것만 리스트에 포함
                // 수신자 주소 오류는 체크할 수 없음
                mv.addObject("failedList", failedSendingRecommendationList);
            }
            // 목록 반복 돌면서 교수, 학생에게 메일 발송
            // 발송 오류시 파일로그 또는 DB에 기록
            // 발송 결과 관리자에게 메일 발송
        } else {
            // 어드민 아니면 파일로그에 남기고 종료
        }

        return mv;
    }

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
        mv.addObject("picturePath", PICTURE_PATH + "/" + MID_PATH);
        return mv;
    }

    @RequestMapping(value="/rslt-generate-pic")
    public ModelAndView rsltGeneratePic(ModelAndView mv) {
        mv.setViewName("sysadmin/rsltGeneratePic");
        ExecutionContext<Map<String, Object>> ec = sysAdminService.downaloadRenamedPictures();
        Map<String, Object> resultMap = ec.getData();
        Set<Map.Entry<String, Object>> entrySet = resultMap.entrySet();
        for (Map.Entry<String, Object> item : entrySet) {
            mv.addObject(item.getKey(), item.getValue());
        }
        mv.addObject("picturePath", PICTURE_PATH + "/" + MID_PATH);
        return mv;
    }
}
