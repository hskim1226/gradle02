package com.apexsoft.framework.xpay;

import com.apexsoft.framework.xpay.service.PaymentVO;
import com.springcryptoutils.core.digest.Digester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/pay")
public class XPayController {

    @Autowired
    private Digester digester;

    String CST_PLATFORM = "test";
    String CST_MID = "apex2739";
    String LGD_MID = CST_PLATFORM.equals("test") ? "t" + CST_MID : CST_MID;
    String LGD_MERTKEY = "5a1573a357905648a789e27feea8cadb";
    String LGD_CUSTOM_SKIN = "red";
    String LGD_WINDOW_VER = "2.5";
    String LGD_CUSTOM_PROCESSTYPE = "TWOTR";
    String LGD_VERSION = "JSP_XPay_2.5";
    String LGD_CASNOTEURL = "http://cas_noteurl.jsp";

    @RequestMapping("/confirm")
    public String confirmPayment( HttpServletRequest request, @ModelAttribute PaymentVO paymentVO ) throws NoSuchAlgorithmException {

        paymentVO.setLGD_MID(LGD_MID);
//        paymentVO.setLGD_OID("abcde"); // getOrderNumber("hanmomhanda" + LGD_TIMESTAMP); // TODO ID는 세션에서
        paymentVO.setLGD_OID(getOrderNumber("hanmomhanda" + paymentVO.getLGD_TIMESTAMP())); // TODO ID는 세션에서
        paymentVO.setLGD_BUYER("hanmomhanda"); // TODO 세션에서 읽어오도록
        paymentVO.setLGD_BUYEREMAIL("hanmomhanda@naver.com"); // TODO 세션에서 읽어오도록
        paymentVO.setLGD_BUYERID("hanmomhanda"); // TODO 세션에서 읽어오도록
        paymentVO.setLGD_BUYERIP((request.getHeader("HTTP_X_FORWARDED_FOR") != null) ? request.getHeader("HTTP_X_FORWARDED_FOR") : request.getRemoteAddr());
        paymentVO.setLGD_HASHDATA(getHashData( paymentVO.getLGD_OID(),
                                               paymentVO.getLGD_AMOUNT(),
                                               paymentVO.getLGD_TIMESTAMP()));
        paymentVO.setCST_PLATFORM(CST_PLATFORM);
        paymentVO.setCST_MID(CST_MID);
        paymentVO.setLGD_CUSTOM_SKIN(LGD_CUSTOM_SKIN);
        paymentVO.setLGD_WINDOW_VER(LGD_WINDOW_VER);
        paymentVO.setLGD_CUSTOM_PROCESSTYPE(LGD_CUSTOM_PROCESSTYPE);
        paymentVO.setLGD_VERSION(LGD_VERSION);
        paymentVO.setLGD_CASNOTEURL(LGD_CASNOTEURL);
        return "xpay/confirm";
    }

    @RequestMapping("/process")
    public String processXPay(@ModelAttribute PaymentVO paymentVO) throws NoSuchAlgorithmException {

//        String LGD_TIMESTAMP = request.getParameter("LGD_TIMESTAMP");
//        String LGD_OID = "abcde"; // getOrderNumber("hanmomhanda" + LGD_TIMESTAMP); // TODO ID는 세션에서
//        String LGD_AMOUNT = request.getParameter("LGD_AMOUNT");
//        String LGD_BUYER = "hanmomhanda"; // TODO 세션에서 읽어오도록
//        String LGD_PRODUCTINFO = request.getParameter("LGD_PRODUCTINFO");
//        String LGD_BUYEREMAIL = "hanmomhanda@naver.com"; // TODO 세션에서 읽어오도록
//        String LGD_BUYERID = "hanmomhanda"; // TODO 세션에서 읽어오도록
//        String LGD_BUYERIP = (request.getHeader("HTTP_X_FORWARDED_FOR") != null) ? request.getHeader("HTTP_X_FORWARDED_FOR") : request.getRemoteAddr();
//        String LGD_HASHDATA = getHashData( LGD_OID, LGD_AMOUNT, LGD_TIMESTAMP);
//
//
//        request.setAttribute( "CST_PLATFORM", CST_PLATFORM);
//        request.setAttribute( "CST_MID", CST_MID);
//        request.setAttribute( "LGD_MID", LGD_MID);
//        request.setAttribute( "LGD_MERTKEY", LGD_MERTKEY);
//        request.setAttribute( "LGD_CUSTOM_SKIN", LGD_CUSTOM_SKIN);
//        request.setAttribute( "LGD_WINDOW_VER", LGD_WINDOW_VER);
//        request.setAttribute( "LGD_CUSTOM_PROCESSTYPE", LGD_CUSTOM_PROCESSTYPE);
//        request.setAttribute( "LGD_VERSION", LGD_VERSION);
//        request.setAttribute( "LGD_CASNOTEURL", LGD_CASNOTEURL);
//
//        request.setAttribute( "LGD_OID", LGD_OID );
//        request.setAttribute( "LGD_BUYER", LGD_BUYER );
//        request.setAttribute( "LGD_BUYEREMAIL", LGD_BUYEREMAIL );
//        request.setAttribute( "LGD_BUYERID", LGD_BUYERID );
//        request.setAttribute( "LGD_BUYERIP", LGD_BUYERIP );
//        request.setAttribute( "LGD_HASHDATA", LGD_HASHDATA );
//
//        System.out.println(request.getAttribute("LGD_HASHDATA"));

        return "xpay/result";
    }

    /**
     * ID와 결제하기 클릭 당시의 타임스탬프를 합친 문자열을 SHA-1로 해쉬한 결과값 반환
     *
     *
     * @param str   사용자 ID + 결제하기 클릭 당시의 타임스탬프
     * @return  주문식별문자
     */
    private String getOrderNumber(String str) throws NoSuchAlgorithmException {

//        return digester.digest(str);
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
        sb.append(LGD_MID);
        sb.append(LGD_OID);
        sb.append(LGD_AMOUNT);
        sb.append(LGD_TIMESTAMP);
        sb.append(LGD_MERTKEY);

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
