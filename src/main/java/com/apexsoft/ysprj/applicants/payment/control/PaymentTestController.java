package com.apexsoft.ysprj.applicants.payment.control;

import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.common.domain.BirtRequest;
import com.apexsoft.ysprj.applicants.payment.service.PaymentService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cosb071 on 15. 1. 22.
 */
@Controller
@RequestMapping(value = "/test/payment")
public class PaymentTestController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value="/casnote_temp")
    public String processCasNote( HttpServletRequest request ) {

        System.out.println("***** Here *****");

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

        System.out.println("   " + LGD_CASFLAG + "  " + LGD_CASSEQNO  + "  " + LGD_OID );
        System.out.println("   " + LGD_CASCAMOUNT + "  " + LGD_CASTAMOUNT );

        return "test/test2";
    }

    @RequestMapping(value="/test1")
    public String test1( HttpServletRequest request ) {


        return "test/test1";
    }

    @RequestMapping(value="/test2")
    public String test2(String applNo) {

        Application appl = new Application();
        appl.setApplNo(Integer.valueOf(applNo));

        paymentService.saveApplicationPayment(appl);

        return "test/test2";
    }

    @RequestMapping(value="/test3")
    public String test3(String applNo) {

        try {

            HttpClient httpClient = new HttpClient();
            PostMethod postMethod = new PostMethod("http://localhost:8080/ysproject/test/payment/test4");

            NameValuePair[] params = new NameValuePair[1];
            params[0] = new NameValuePair("application.applNo", "398");

            postMethod.addParameters(params);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            httpClient.executeMethod(postMethod);


        } catch( Exception e) {

        }

        return "xpay/casnote";
    }

    @RequestMapping(value="/test4")
    public String test4( BirtRequest birtRequest ) {

        Application application = birtRequest.getApplication();

        return "test/test2";
    }

}
