package com.apexsoft.ysprj.applicants.payment.domain;

/**
 * Created by cosb071 on 15. 1. 27.
 *
 * U+ 전자결제에서 필요한 설정값
 */
public class PaymentConfig {

    static public String CST_PLATFORM = "test";
    static public String CST_MID = "apex2739";
    static public String LGD_MID = CST_PLATFORM.equals("test") ? "t" + CST_MID : CST_MID;
    static public String LGD_MERTKEY = "5a1573a357905648a789e27feea8cadb";
    static public String LGD_CUSTOM_SKIN = "red";
    static public String LGD_WINDOW_VER = "2.5";
    static public String LGD_CUSTOM_PROCESSTYPE = "TWOTR";
    static public String LGD_VERSION = "JSP_XPay_2.5";
    static public String LGD_CASNOTEURL = "http://apexsoft-svr1.iptime.org:3355/ysproject/test/payment/casnote";
    static public String LGD_TXNAME = "PaymentByKey";

    //로컬 개발용
    //static public String CONFIG_PATH = "c:/opt/ysproject/lgdacom";
    //서버 빌드용
    static public String CONFIG_PATH = "/opt/ysproject/lgdacom";

}
