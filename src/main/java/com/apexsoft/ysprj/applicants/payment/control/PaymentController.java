package com.apexsoft.ysprj.applicants.payment.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.security.UserSessionVO;
import com.apexsoft.framework.unused.xpay.service.TransactionVO;
import com.apexsoft.ysprj.applicants.application.domain.Basis;
import com.apexsoft.ysprj.applicants.payment.domain.Payment;
import com.apexsoft.ysprj.applicants.payment.domain.PaymentConfig;
import com.apexsoft.ysprj.applicants.payment.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by cosb071 on 15. 1. 27.
 *
 * 결제 관련 처리 컨트롤러
 */
@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    @Autowired
    private PaymentService paymentService;

    @Value("#{app['pay.platform']}")
    private String payPlatform;

    //private String LGD_MID = payPlatform.equals("test") ? "t" + PaymentConfig.CST_MID : PaymentConfig.CST_MID;

    @Value("#{app['pay.casnoteurl']}")
    private String casnoteURL;

    /**
     * 사용자 이름과 아이디를 결제 확인 화면에 반환
     *
     * @param httpSession
     * @param payment
     * @param model
     * @return
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String confirmPayment( HttpSession httpSession, Payment payment, Basis model ) throws NoSuchAlgorithmException {

        SecurityContext sc = (SecurityContext)httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        Authentication auth = sc.getAuthentication();
        UserSessionVO userSessionVO = (UserSessionVO)auth.getPrincipal();

        payment.setLGD_BUYER(userSessionVO.getName());
        payment.setLGD_BUYERID(userSessionVO.getUsername());
        payment.setApplNo(model.getApplication().getApplNo());

        paymentService.retrieveConfirmInfo(payment);

        String retPage;
        if( "00021".equals(payment.getApplStsCode()) ) {
            retPage = "xpay/waitPay";
        } else {
            retPage = "xpay/confirm";
        }

        return retPage;
    }

    /**
     * 결제 확인 화면의 요청을 받아 결제 정보를 생성하여 JSON화 해서 AJAX로 반환
     * 결제 확인 화면에서는 AJAX로 받은 값을 변수로 포장하여 hidden에 할당하므로 결제 정보 값 직접 노출 방지 가능
     *
     * @param request
     * @param httpSession
     * @param payment
     * @param model
     * @return
     * @throws NoSuchAlgorithmException
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     * @throws java.io.UnsupportedEncodingException
     */
//    @RequestMapping(value="/info", method= RequestMethod.GET, produces="text/plain;charset=UTF-8")
    @RequestMapping(value="/info", method= RequestMethod.GET)
    @ResponseBody
    public ExecutionContext getFullPaymentInfo( HttpServletRequest request, HttpSession httpSession, Payment payment, Basis model )
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {

        ExecutionContext ec = new ExecutionContext();
        SecurityContext sc = (SecurityContext)httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        Authentication auth = sc.getAuthentication();
        UserSessionVO userSessionVO = (UserSessionVO)auth.getPrincipal();

        payment.setCST_PLATFORM(payPlatform);
        payment.setCST_MID(PaymentConfig.CST_MID);
        payment.setLGD_MID(payPlatform.equals("test") ? "t" + PaymentConfig.CST_MID : PaymentConfig.CST_MID);
        payment.setLGD_OID( getOrderNumber(userSessionVO.getUsername() + payment.getLGD_TIMESTAMP()) );
        payment.setLGD_HASHDATA( getHashData(payment.getLGD_OID(), payment.getLGD_AMOUNT(), payment.getLGD_TIMESTAMP()) );
        payment.setLGD_BUYERIP( (request.getHeader("HTTP_X_FORWARDED_FOR") != null) ? request.getHeader("HTTP_X_FORWARDED_FOR") : request.getRemoteAddr() );
        payment.setLGD_BUYEREMAIL(userSessionVO.getEmail());
        payment.setLGD_CUSTOM_SKIN(PaymentConfig.LGD_CUSTOM_SKIN);
        payment.setLGD_WINDOW_VER(PaymentConfig.LGD_WINDOW_VER);
        payment.setLGD_CUSTOM_PROCESSTYPE(PaymentConfig.LGD_CUSTOM_PROCESSTYPE);
        payment.setLGD_VERSION(PaymentConfig.LGD_VERSION);
        payment.setLGD_CASNOTEURL(casnoteURL);
        payment.setApplNo(model.getApplication().getApplNo());

        String json = new ObjectMapper().writeValueAsString(payment);
        ec.setData(json);
        return ec;
    }

    /**
     * 인증 요청 및 결과 로그 DB 처리
     *
     * @param payment
     * @return
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/certify", method = RequestMethod.POST)
    public void certifyPayment( Payment payment ) throws NoSuchAlgorithmException {

        paymentService.registerPaymentCertifyLog(payment);

    }

    /**
     * 결제화면에서 XPay 처리(결제 팝업 및 정보 입력) 후 실제 결제 처리 및 DB 처리
     *
     * @param payment
     * @param model
     * @param transactionVO
     * @return
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public String processXPay( Payment payment, Basis model, @ModelAttribute TransactionVO transactionVO ) throws NoSuchAlgorithmException {

        payment.setApplNo(model.getApplication().getApplNo());
        String respStr = paymentService.executePayment(payment, transactionVO);

        if( respStr.equals("SC0040") ) {
            return "xpay/waitPay";
        }

        return "xpay/result";

    }

    /**
     * ID와 결제하기 클릭 당시의 타임스탬프를 합친 문자열을 SHA-1로 해쉬한 결과값 반환
     *
     * @param str   사용자 ID + 결제하기 클릭 당시의 타임스탬프
     * @return  주문식별문자
     */
    private String getOrderNumber( String str ) throws NoSuchAlgorithmException {

        byte[] bNoti = str.toString().getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(bNoti);

        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            int c = digest[i] & 0xff;
            if (c <= 15) {
                strBuf.append("0");
            }
            strBuf.append(Integer.toHexString(c));
        }

        return strBuf.toString();
    }

    /**
     * U+결제 내부적으로 사용하는 거래 식별자 반환
     *
     * @param LGD_OID
     * @param LGD_AMOUNT
     * @param LGD_TIMESTAMP
     * @return 거래식별문자
     */
    private String getHashData( String LGD_OID, String LGD_AMOUNT, String LGD_TIMESTAMP ) throws NoSuchAlgorithmException {

        /*
        *************************************************
        * 2. MD5 해쉬암호화 (수정하지 마세요) - BEGIN
        *
        * MD5 해쉬암호화는 거래 위변조를 막기위한 방법입니다.
        *************************************************
        *
        * 해쉬 암호화 적용( LGD_MID + LGD_OID + LGD_AMOUNT + LGD_TIMESTAMP + LGD_MERTKEY )
        * LGD_MID          : 상점아이디
        * LGD_OID          : 주문번호
        * LGD_AMOUNT       : 금액
        * LGD_TIMESTAMP    : 타임스탬프
        * LGD_MERTKEY      : 상점MertKey (mertkey는 상점관리자 -> 계약정보 -> 상점정보관리에서 확인하실수 있습니다)
        *
        * MD5 해쉬데이터 암호화 검증을 위해
        * LG유플러스에서 발급한 상점키(MertKey)를 환경설정 파일(lgdacom/conf/mall.conf)에 반드시 입력하여 주시기 바랍니다.
        */
        StringBuffer sb = new StringBuffer();
        sb.append(payPlatform.equals("test") ? "t" + PaymentConfig.CST_MID : PaymentConfig.CST_MID);
        sb.append(LGD_OID);
        sb.append(LGD_AMOUNT);
        sb.append(LGD_TIMESTAMP);
        sb.append(PaymentConfig.LGD_MERTKEY);

        byte[] bNoti = sb.toString().getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(bNoti);

        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            int c = digest[i] & 0xff;
            if (c <= 15) {
                strBuf.append("0");
            }
            strBuf.append(Integer.toHexString(c));
        }

        return strBuf.toString();
        /*
        *************************************************
        * 2. MD5 해쉬암호화 (수정하지 마세요) - END
        *************************************************
        */
    }

}
