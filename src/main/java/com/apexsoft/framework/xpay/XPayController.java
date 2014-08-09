package com.apexsoft.framework.xpay;

import com.apexsoft.framework.xpay.service.PaymentVO;
import com.apexsoft.framework.xpay.service.TransactionVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lgdacom.XPayClient.XPayClient;

@Controller
@RequestMapping("/pay")
public class XPayController {

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
    public String processXPay(@ModelAttribute PaymentVO paymentVO,
                              @ModelAttribute TransactionVO transactionVO) throws NoSuchAlgorithmException {
    /*
     * [최종결제요청 페이지(STEP2-2)]
     *
     * LG유플러스으로 부터 내려받은 LGD_PAYKEY(인증Key)를 가지고 최종 결제요청.(파라미터 전달시 POST를 사용하세요)
     */

        String configPath = "/home/hanmomhanda/gitRepo/ysproject/src/main/webapp/WEB-INF/config/lgdacom";  //LG유플러스에서 제공한 환경파일("/conf/lgdacom.conf,/conf/mall.conf") 위치 지정.

    /*
     *************************************************
     * 1.최종결제 요청 - BEGIN
     *  (단, 최종 금액체크를 원하시는 경우 금액체크 부분 주석을 제거 하시면 됩니다.)
     *************************************************
     */

        String CST_PLATFORM = paymentVO.getCST_PLATFORM();
        String LGD_MID = paymentVO.getLGD_MID();
        String LGD_PAYKEY = paymentVO.getLGD_PAYKEY();

//        String CST_PLATFORM                 = request.getParameter("CST_PLATFORM");
//        String CST_MID                      = request.getParameter("CST_MID");
//        String LGD_MID                      = ("test".equals(CST_PLATFORM.trim())?"t":"")+CST_MID;
//        String LGD_PAYKEY                   = request.getParameter("LGD_PAYKEY");

        //해당 API를 사용하기 위해 WEB-INF/lib/XPayClient.jar 를 Classpath 로 등록하셔야 합니다.
        XPayClient xpay = new XPayClient();
        boolean isInitOK = xpay.Init(configPath, CST_PLATFORM);

        if( !isInitOK ) {
            //API 초기화 실패 화면처리
//            out.println( "결제요청을 초기화 하는데 실패하였습니다.<br>");
//            out.println( "LG유플러스에서 제공한 환경파일이 정상적으로 설치 되었는지 확인하시기 바랍니다.<br>");
//            out.println( "mall.conf에는 Mert ID = Mert Key 가 반드시 등록되어 있어야 합니다.<br><br>");
//            out.println( "문의전화 LG유플러스 1544-7772<br>");
            transactionVO.setMsg(
                "결제요청을 초기화 하는데 실패하였습니다.<br>" +
                "LG유플러스에서 제공한 환경파일이 정상적으로 설치 되었는지 확인하시기 바랍니다.<br>" +
                "mall.conf에는 Mert ID = Mert Key 가 반드시 등록되어 있어야 합니다.<br><br>" +
                "문의전화 LG유플러스 1544-7772<br>"
            );

            return "xpay/result";

        }else{
            try{
   			/*
   	   	     *************************************************
   	   	     * 1.최종결제 요청(수정하지 마세요) - END
   	   	     *************************************************
   	   	     */
                xpay.Init_TX(LGD_MID);
                xpay.Set("LGD_TXNAME", "PaymentByKey");
                xpay.Set("LGD_PAYKEY", LGD_PAYKEY);

                //금액을 체크하시기 원하는 경우 아래 주석을 풀어서 이용하십시요.
                //String DB_AMOUNT = "DB나 세션에서 가져온 금액"; //반드시 위변조가 불가능한 곳(DB나 세션)에서 금액을 가져오십시요.
                //xpay.Set("LGD_AMOUNTCHECKYN", "Y");
                //xpay.Set("LGD_AMOUNT", DB_AMOUNT);

            }catch(Exception e) {
//                out.println("LG유플러스 제공 API를 사용할 수 없습니다. 환경파일 설정을 확인해 주시기 바랍니다. ");
//                out.println(""+e.getMessage());
                transactionVO.setMsg(
                    "LG유플러스 제공 API를 사용할 수 없습니다. 환경파일 설정을 확인해 주시기 바랍니다. <br/>" +
                    e.getMessage()
                );
                return "xpay/result";
            }
        }

    /*
     * 2. 최종결제 요청 결과처리
     *
     * 최종 결제요청 결과 리턴 파라미터는 연동메뉴얼을 참고하시기 바랍니다.
     */
        if ( xpay.TX() ) {
            //1)결제결과 화면처리(성공,실패 결과 처리를 하시기 바랍니다.)
//            out.println( "결제요청이 완료되었습니다.  <br>");
//            out.println( "TX 결제요청 Response_code = " + xpay.m_szResCode + "<br>");
//            out.println( "TX 결제요청 Response_msg = " + xpay.m_szResMsg + "<p>");
//
//            out.println("거래번호 : " + xpay.Response("LGD_TID",0) + "<br>");
//            out.println("상점아이디 : " + xpay.Response("LGD_MID",0) + "<br>");
//            out.println("상점주문번호 : " + xpay.Response("LGD_OID",0) + "<br>");
//            out.println("결제금액 : " + xpay.Response("LGD_AMOUNT",0) + "<br>");
//            out.println("결과코드 : " + xpay.Response("LGD_RESPCODE",0) + "<br>");
//            out.println("결과메세지 : " + xpay.Response("LGD_RESPMSG",0) + "<p>");

            transactionVO.setMsg(
                "결제요청이 완료되었습니다.  <br>" +
                "TX 결제요청 Response_code = " + xpay.m_szResCode + "<br>" +
                "TX 결제요청 Response_msg = " + xpay.m_szResMsg + "<p>" +
                "거래번호 : " + xpay.Response("LGD_TID",0) + "<br>" +
                "상점아이디 : " + xpay.Response("LGD_MID",0) + "<br>" +
                "상점주문번호 : " + xpay.Response("LGD_OID",0) + "<br>" +
                "결제금액 : " + xpay.Response("LGD_AMOUNT",0) + "<br>" +
                "결과코드 : " + xpay.Response("LGD_RESPCODE",0) + "<br>" +
                "결과메세지 : " + xpay.Response("LGD_RESPMSG",0) + "<p>"
            );

//            for (int i = 0; i < xpay.ResponseNameCount(); i++)
//            {
//                out.println(xpay.ResponseName(i) + " = ");
//                for (int j = 0; j < xpay.ResponseCount(); j++)
//                {
//                    out.println("\t" + xpay.Response(xpay.ResponseName(i), j) + "<br>");
//                }
//            }
//            out.println("<p>");

            String tmp = "";
            for (int i = 0; i < xpay.ResponseNameCount(); i++) {
                for (int j = 0; j < xpay.ResponseCount(); j++) {
                    tmp += (j>1)?"|":"" + xpay.Response(xpay.ResponseName(i), j);
                }
                transactionVO.getTxMap().put(xpay.ResponseName(i), tmp);
                tmp = "";
            }

            if( "0000".equals( xpay.m_szResCode ) ) {
                //TODO 최종결제요청 결과 성공 DB처리
//                out.println("최종결제요청 결과 성공 DB처리하시기 바랍니다.<br>");
                transactionVO.setMsg(transactionVO.getMsg() + "최종결제요청 결과 성공 DB처리하시기 바랍니다.<br>");

                //TODO 최종결제요청 결과 성공 DB처리 실패시 Rollback 처리
//                boolean isDBOK = true; //DB처리 실패시 false로 변경해 주세요.
//                if( !isDBOK ) {
//                    xpay.Rollback("상점 DB처리 실패로 인하여 Rollback 처리 [TID:" +xpay.Response("LGD_TID",0)+",MID:" + xpay.Response("LGD_MID",0)+",OID:"+xpay.Response("LGD_OID",0)+"]");
//
//                    out.println( "TX Rollback Response_code = " + xpay.Response("LGD_RESPCODE",0) + "<br>");
//                    out.println( "TX Rollback Response_msg = " + xpay.Response("LGD_RESPMSG",0) + "<p>");
//
//                    if( "0000".equals( xpay.m_szResCode ) ) {
//                        out.println("자동취소가 정상적으로 완료 되었습니다.<br>");
//                    }else{
//                        out.println("자동취소가 정상적으로 처리되지 않았습니다.<br>");
//                    }
//                }

            }else{
                //TODO 최종결제요청 결과 실패 DB처리
//                out.println("최종결제요청 결과 실패 DB처리하시기 바랍니다.<br>");
                transactionVO.setMsg(transactionVO.getMsg() + "최종결제요청 결과 실패 DB처리하시기 바랍니다.<br>");
            }
        }else {
            //TODO 2)API 요청실패 화면처리
//            out.println( "결제요청이 실패하였습니다.  <br>");
//            out.println( "TX 결제요청 Response_code = " + xpay.m_szResCode + "<br>");
//            out.println( "TX 결제요청 Response_msg = " + xpay.m_szResMsg + "<p>");

            transactionVO.setMsg(
                "결제요청이 실패하였습니다.  <br>" +
                "TX 결제요청 Response_code = " + xpay.m_szResCode + "<br>" +
                "TX 결제요청 Response_msg = " + xpay.m_szResMsg + "<p>"
            );

            //TODO 최종결제요청 결과 실패 DB처리
//            out.println("최종결제요청 결과 실패 DB처리하시기 바랍니다.<br>");
            transactionVO.setMsg(transactionVO.getMsg() + "최종결제요청 결과 실패 DB처리하시기 바랍니다.<br>");
        }


        return "xpay/result";
//        return "xpay/payres";
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
