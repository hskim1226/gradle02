package com.apexsoft.ysprj.applicants.payment.control;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.common.service.BirtService;
import com.apexsoft.ysprj.applicants.common.service.PDFService;
import com.apexsoft.ysprj.applicants.payment.domain.ApplicationPaymentCurStat;
import com.apexsoft.ysprj.applicants.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cosb071 on 15. 1. 22.
 */
@Controller
@RequestMapping(value = "/casnote")
public class CasNoteController {

    @Autowired
    private CommonDAO commonDAO;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BirtService birtService;

    @Autowired
    PDFService pdfService;

    /**
     * 가상 계좌 입금 완료 확인 시 LG U+에서 호출
     *
     *
     * @param request
     * @return
     */
    @RequestMapping(value="/notice")
    public String processCasNote( HttpServletRequest request ) {
//System.out.println("[DEBUG] processCasNote invoked.");
        String LGD_RESPCODE = "";           // 응답코드: 0000(성공) 그외 실패
        String LGD_RESPMSG = "";            // 응답메세지
        String LGD_MID = "";                // 상점아이디
        String LGD_OID = "";                // 주문번호
        String LGD_AMOUNT = "";             // 거래금액
        String LGD_TID = "";                // LG유플러스에서 부여한 거래번호
        String LGD_PAYTYPE = "";            // 결제수단코드
        String LGD_PAYDATE = "";            // 거래일시(승인일시/이체일시)
        String LGD_HASHDATA = "";           // 해쉬값
        String LGD_FINANCECODE = "";        // 결제기관코드(은행코드)
        String LGD_FINANCENAME = "";        // 결제기관이름(은행이름)
        String LGD_ESCROWYN = "";           // 에스크로 적용여부
        String LGD_TIMESTAMP = "";          // 타임스탬프
        String LGD_ACCOUNTNUM = "";         // 계좌번호(무통장입금)
        String LGD_CASTAMOUNT = "";         // 입금총액(무통장입금)
        String LGD_CASCAMOUNT = "";         // 현입금액(무통장입금)
        String LGD_CASFLAG = "";            // 무통장입금 플래그(무통장입금) - 'R':계좌할당, 'I':입금, 'C':입금취소
        String LGD_CASSEQNO = "";           // 입금순서(무통장입금)
        String LGD_CASHRECEIPTNUM = "";     // 현금영수증 승인번호
        String LGD_CASHRECEIPTSELFYN = "";  // 현금영수증자진발급제유무 Y: 자진발급제 적용, 그외 : 미적용
        String LGD_CASHRECEIPTKIND = "";    // 현금영수증 종류 0: 소득공제용 , 1: 지출증빙용
        String LGD_PAYER = "";    			// 임금자명

        String LGD_BUYER = "";              // 구매자
        String LGD_PRODUCTINFO = "";        // 상품명
        String LGD_BUYERID = "";            // 구매자 ID
        String LGD_BUYERADDRESS = "";       // 구매자 주소
        String LGD_BUYERPHONE = "";         // 구매자 전화번호
        String LGD_BUYEREMAIL = "";         // 구매자 이메일
        String LGD_BUYERSSN = "";           // 구매자 주민번호
        String LGD_PRODUCTCODE = "";        // 상품코드
        String LGD_RECEIVER = "";           // 수취인
        String LGD_RECEIVERPHONE = "";      // 수취인 전화번호
        String LGD_DELIVERYINFO = "";       // 배송지

        LGD_RESPCODE            = request.getParameter("LGD_RESPCODE");
        LGD_RESPMSG             = request.getParameter("LGD_RESPMSG");
        LGD_MID                 = request.getParameter("LGD_MID");
        LGD_OID                 = request.getParameter("LGD_OID");
        LGD_AMOUNT              = request.getParameter("LGD_AMOUNT");
        LGD_TID                 = request.getParameter("LGD_TID");
        LGD_PAYTYPE             = request.getParameter("LGD_PAYTYPE");
        LGD_PAYDATE             = request.getParameter("LGD_PAYDATE");
        LGD_HASHDATA            = request.getParameter("LGD_HASHDATA");
        LGD_FINANCECODE         = request.getParameter("LGD_FINANCECODE");
        LGD_FINANCENAME         = request.getParameter("LGD_FINANCENAME");
        LGD_ESCROWYN            = request.getParameter("LGD_ESCROWYN");
        LGD_TIMESTAMP           = request.getParameter("LGD_TIMESTAMP");
        LGD_ACCOUNTNUM          = request.getParameter("LGD_ACCOUNTNUM");
        LGD_CASTAMOUNT          = request.getParameter("LGD_CASTAMOUNT");
        LGD_CASCAMOUNT          = request.getParameter("LGD_CASCAMOUNT");
        LGD_CASFLAG             = request.getParameter("LGD_CASFLAG");
        LGD_CASSEQNO            = request.getParameter("LGD_CASSEQNO");
        LGD_CASHRECEIPTNUM      = request.getParameter("LGD_CASHRECEIPTNUM");
        LGD_CASHRECEIPTSELFYN   = request.getParameter("LGD_CASHRECEIPTSELFYN");
        LGD_CASHRECEIPTKIND     = request.getParameter("LGD_CASHRECEIPTKIND");
        LGD_PAYER     			= request.getParameter("LGD_PAYER");

        LGD_BUYER               = request.getParameter("LGD_BUYER");
        LGD_PRODUCTINFO         = request.getParameter("LGD_PRODUCTINFO");
        LGD_BUYERID             = request.getParameter("LGD_BUYERID");
        LGD_BUYERADDRESS        = request.getParameter("LGD_BUYERADDRESS");
        LGD_BUYERPHONE          = request.getParameter("LGD_BUYERPHONE");
        LGD_BUYEREMAIL          = request.getParameter("LGD_BUYEREMAIL");
        LGD_BUYERSSN            = request.getParameter("LGD_BUYERSSN");
        LGD_PRODUCTCODE         = request.getParameter("LGD_PRODUCTCODE");
        LGD_RECEIVER            = request.getParameter("LGD_RECEIVER");
        LGD_RECEIVERPHONE       = request.getParameter("LGD_RECEIVERPHONE");
        LGD_DELIVERYINFO        = request.getParameter("LGD_DELIVERYINFO");

        ApplicationPaymentCurStat applPay = new ApplicationPaymentCurStat();
        if( LGD_CASCAMOUNT != null && !LGD_CASCAMOUNT.equals("") )
            applPay.setPayAmt(Integer.valueOf(LGD_CASCAMOUNT));
        applPay.setPayTypeCode(LGD_PAYTYPE);
        applPay.setPayDate(LGD_PAYDATE);
        applPay.setLgdOid(LGD_OID);
        applPay.setLgdTid(LGD_TID);

        if( "I".equals(LGD_CASFLAG) ) {

            // 이미 LG U+ 측에서 입금 확인이 되어 결제 완료되었으므로 DB 처리만 한다.
            Application application = paymentService.registerCasNote(applPay);

            // 원서 수험표, 생성, S3 업로드
            paymentService.processApplicationFiles(application);


            // 지원 완료 알림 메일 발송
            paymentService.sendNotification(application);


            //수험표, 지원서 생섬 및 Merge
//            String urlHead = "http://localhost:" + Integer.toString(request.getLocalPort()) + request.getContextPath();
//            genApplFileAndMerge( applNo, urlHead );
//            genSlipFile( applNo, urlHead );

            // 수험표, 지원서 생성 및 Merge
            // 타 대학원 확장 시 TODO - 학교 이름을 파라미터로 받도록
//            Application application = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.ApplicationMapper.selectByPrimaryKey",
//                    applNo, Application.class);
//            String admsTypeCode = application.getAdmsTypeCode();
//            String lang = "C".equals(admsTypeCode) || "D".equals(admsTypeCode) ? "en" : "kr";
//            String reportName = "yonsei-appl-" + lang;
//            ExecutionContext ecGenAppl = birtService.generateBirtFile(application.getApplNo(), reportName);
//            reportName = "yonsei-adms-" + lang;
//            ExecutionContext ecGenAdms = birtService.generateBirtFile(application.getApplNo(), reportName);
//            ExecutionContext ecPdfMerge = pdfService.genAndUploadPDFByApplicants(application);
//            if ( ExecutionContext.FAIL.equals(ecGenAppl.getResult()) ||
//                 ExecutionContext.FAIL.equals(ecGenAdms.getResult()) ||
//                 ExecutionContext.FAIL.equals(ecPdfMerge.getResult()) ) {
//                throw new YSBizException();
//            }
        }

        return "xpay/casnote";
    }

//    PaymentServiceImpl.genAndUploadApplicationFormAndSlipFile()에서 처리하므로 여기서는 제거
//
//    @Async
//    private void genApplFileAndMerge( int applNo, String urlHead ) {
//
//        httpClient( urlHead + "/casnote/generate/application", Integer.toString(applNo) );
//        httpClient( urlHead + "/casnote//merge/applicant", Integer.toString(applNo));
//
//    }
//
//    @Async
//    private void genSlipFile( int applNo, String urlHead ) {
//
//        httpClient( urlHead + "/casnote/generate/slip", Integer.toString(applNo) );
//
//    }
//
//    private void httpClient( String urlAddr, String ApplNo ) {
//
//        try {
//
//            HttpClient httpClient = new HttpClient();
//            PostMethod postMethod = new PostMethod( urlAddr );
//
//            NameValuePair[] params = new NameValuePair[1];
//            params[0] = new NameValuePair( "application.applNo", ApplNo );
//
//            postMethod.addParameters(params);
//            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
//
//            httpClient.executeMethod(postMethod);
//
//        } catch( Exception e) {
//
//        }
//
//    }
//
//    @RequestMapping(value = "/generate/application")
//    public ModelAndView generateApplicationFile( BirtRequest birtRequest, Principal principal, ModelAndView mv ) {
//
//        System.out.println("   ** 지원서 pdf 처리 시작");
//
//        int applNo = birtRequest.getApplication().getApplNo();
//
//        mv.setViewName("pdfSingleFormatBirtSaveToFile");
//        mv.addObject("reportFormat", "pdf");
//        mv.addObject("reportName", "yonsei-appl-kr");
//        ExecutionContext ec = birtService.processBirt(applNo, "yonsei-appl-kr");
//        mv.addAllObjects((Map<String, Object>)ec.getData());
//
//        System.out.println("   ** 지원서 pdf 처리 끝");
//
//        return mv;
//    }
//
//    @RequestMapping(value="/generate/slip")
//    public ModelAndView generateSlipFile( BirtRequest birtRequest, Principal principal, ModelAndView mv ) {
//
//        System.out.println("   ** 수험표 pdf 처리 시작");
//
//        int applNo = birtRequest.getApplication().getApplNo();
//
//        mv.setViewName("pdfSingleFormatBirtSaveToFile");
//        mv.addObject("reportFormat", "pdf");
//        mv.addObject("reportName", "yonsei-adms-kr");
//        ExecutionContext ec = birtService.processBirt(applNo, "yonsei-adms-kr");
//        mv.addAllObjects((Map<String, Object>)ec.getData());
//
//        System.out.println("   ** 수험표 pdf 처리 끝");
//
//        return mv;
//    }
//
//    @RequestMapping(value="/merge/applicant")
//    public String mergeByApplicant( BirtRequest birtRequest ) {
//
//        System.out.println("   ** Merge 처리 시작");
//
////        int applNo = birtRequest.getApplication().getApplNo();
//        ExecutionContext ec = pdfService.genAndUploadPDFByApplicants(birtRequest.getApplication());
//
//        System.out.println("   ** Merge 처리 끝");
//
//        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
//            return ExecutionContext.SUCCESS;
//        } else {
//            return ExecutionContext.FAIL;
//        }
//    }

}
