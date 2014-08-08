<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.io.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.security.MessageDigest" %>
<%@ page import="lgdacom.XPayClient.XPayClient"%>

<%
    /* 
     * ysprj
     * UTF-8 처리
     */
    request.setCharacterEncoding("UTF-8");
    
    /*
     * [결제 인증요청 페이지(STEP2-1)]
     *
     * 샘플페이지에서는 기본 파라미터만 예시되어 있으며, 별도로 필요하신 파라미터는 연동메뉴얼을 참고하시어 추가 하시기 바랍니다.
     */

    /*
     * 1. 기본결제 인증요청 정보 변경
     *
     * 기본정보를 변경하여 주시기 바랍니다.(파라미터 전달시 POST를 사용하세요)
     */
    String CST_PLATFORM         = request.getParameter("CST_PLATFORM");                 //LG유플러스 결제서비스 선택(test:테스트, service:서비스)
    String CST_MID              = request.getParameter("CST_MID");                      //LG유플러스으로 부터 발급받으신 상점아이디를 입력하세요.
    String LGD_MID              = ("test".equals(CST_PLATFORM.trim())?"t":"")+CST_MID;  //테스트 아이디는 't'를 제외하고 입력하세요.
                                                                                        //상점아이디(자동생성)
    String LGD_OID              = request.getParameter("LGD_OID");                      //주문번호(상점정의 유니크한 주문번호를 입력하세요)
    String LGD_AMOUNT           = request.getParameter("LGD_AMOUNT");                   //결제금액("," 를 제외한 결제금액을 입력하세요)
    String LGD_MERTKEY          = "5a1573a357905648a789e27feea8cadb";					//상점MertKey(mertkey는 상점관리자 -> 계약정보 -> 상점정보관리에서 확인하실수 있습니다)
	String LGD_BUYER            = request.getParameter("LGD_BUYER");                    //구매자명
    String LGD_PRODUCTINFO      = request.getParameter("LGD_PRODUCTINFO");              //상품명
    String LGD_BUYEREMAIL       = request.getParameter("LGD_BUYEREMAIL");               //구매자 이메일
    String LGD_TIMESTAMP        = request.getParameter("LGD_TIMESTAMP");                //타임스탬프
    String LGD_CUSTOM_SKIN      = "red";                                                //상점정의 결제창 스킨(red, purple, yellow)
    String LGD_WINDOW_VER       = "2.5";                                                //결제창 버젼정보
    String LGD_BUYERID          = request.getParameter("LGD_BUYERID");       			//구매자 아이디
    String LGD_BUYERIP          = request.getParameter("LGD_BUYERIP");       			//구매자IP

    /*
     * 가상계좌(무통장) 결제 연동을 하시는 경우 아래 LGD_CASNOTEURL 을 설정하여 주시기 바랍니다. 
     */    
    String LGD_CASNOTEURL		= "http://localhost:8080/xpay/y/cas_noteurl.jsp";    

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
//out.println(sb.toString() + "<br/>");
    sb.append(LGD_OID);
//out.println(sb.toString() + "<br/>");
    sb.append(LGD_AMOUNT);
//out.println(sb.toString() + "<br/>");
    sb.append(LGD_TIMESTAMP);
//out.println(sb.toString() + "<br/>");
    sb.append(LGD_MERTKEY);
//out.println(sb.toString() + "<br/>");

    byte[] bNoti = sb.toString().getBytes();
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] digest = md.digest(bNoti);

    StringBuffer strBuf = new StringBuffer();
    for (int i=0 ; i < digest.length ; i++) {
        int c = digest[i] & 0xff;
        if (c <= 15){
            strBuf.append("0");
        }
        strBuf.append(Integer.toHexString(c));
    }

    String LGD_HASHDATA = strBuf.toString();
    String LGD_CUSTOM_PROCESSTYPE = "TWOTR";
    /*
     *************************************************
     * 2. MD5 해쉬암호화 (수정하지 마세요) - END
     *************************************************
     */
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>LG유플러스 eCredit서비스 결제테스트</title>
<script type="text/javascript">
/*
 * 상점결제 인증요청후 PAYKEY를 받아서 최종결제 요청.
 */
function doPay_ActiveX(){
	
    ret = xpay_check(document.getElementById('LGD_PAYINFO'), '<%= CST_PLATFORM %>');

    if (ret=="00"){     //ActiveX 로딩 성공
        var LGD_RESPCODE        = dpop.getData('LGD_RESPCODE');       //결과코드
        var LGD_RESPMSG         = dpop.getData('LGD_RESPMSG');        //결과메세지

        if( "0000" == LGD_RESPCODE ) { //인증성공
            var LGD_PAYKEY      = dpop.getData('LGD_PAYKEY');         //LG유플러스 인증KEY
            var msg = "인증결과 : " + LGD_RESPMSG + "\n";
            msg += "LGD_PAYKEY : " + LGD_PAYKEY +"\n\n";
            document.getElementById('LGD_PAYKEY').value = LGD_PAYKEY;
            alert(msg);
            document.getElementById('LGD_PAYINFO').submit();
        } else { //인증실패
            alert("인증이 실패하였습니다. " + LGD_RESPMSG);
            /*
             * 인증실패 화면 처리
             */
        }
    } else {
        alert("LG U+ 전자결제를 위한 ActiveX Control이  설치되지 않았습니다.");
        /*
         * 인증실패 화면 처리
         */
    }      
}

function isActiveXOK(){
	if(lgdacom_atx_flag == true){
    	document.getElementById('LGD_BUTTON1').style.display='none';
        document.getElementById('LGD_BUTTON2').style.display='';
	}else{
		document.getElementById('LGD_BUTTON1').style.display='';
        document.getElementById('LGD_BUTTON2').style.display='none';	
	}
}
</script>
</head>

<body onload="isActiveXOK();">
<div id="LGD_ACTIVEX_DIV"/> <!-- ActiveX 설치 안내 Layer 입니다. 수정하지 마세요. -->
<form method="post" name ="LGD_PAYINFO" id="LGD_PAYINFO" action="payres.jsp">
<table>
    <tr>
        <td>구매자 이름 </td>
        <td><%= LGD_BUYER %></td>
    </tr>
    <tr>
        <td>구매자 IP </td>
        <td><%= LGD_BUYERIP %></td>
    </tr>
    <tr>
        <td>구매자 ID </td>
        <td><%= LGD_BUYERID %></td>
    </tr>    
    <tr>
        <td>상품정보 </td>
        <td><%= LGD_PRODUCTINFO %></td>
    </tr>
    <tr>
        <td>결제금액 </td>
        <td><%= LGD_AMOUNT %></td>
    </tr>
    <tr>
        <td>구매자 이메일 </td>
        <td><%= LGD_BUYEREMAIL %></td>
    </tr>
    <tr>
        <td>주문번호 </td>
        <td><%= LGD_OID %></td>
    </tr>
    <tr>
        <td colspan="2">* 추가 상세 결제요청 파라미터는 메뉴얼을 참조하시기 바랍니다.</td>
    </tr>
    <tr>
        <td colspan="2"></td>
    </tr>    
    <tr>
        <td colspan="2">
		<div id="LGD_BUTTON1">결제를 위한 모듈을 다운 중이거나, 모듈을 설치하지 않았습니다. </div>
		<div id="LGD_BUTTON2" style="display:none"><input type="button" value="인증요청" onclick="doPay_ActiveX();"/> </div>        
        </td>
    </tr>    
</table>
<br>

<input type="hidden" name="CST_PLATFORM"                id="CST_PLATFORM"		value="<%= CST_PLATFORM %>">                   <!-- 테스트, 서비스 구분 -->
<input type="hidden" name="CST_MID"                     id="CST_MID"			value="<%= CST_MID %>">                        <!-- 상점아이디 -->
<input type="hidden" name="LGD_MID"                     id="LGD_MID"			value="<%= LGD_MID %>">                        <!-- 상점아이디 -->
<input type="hidden" name="LGD_OID"                     id="LGD_OID"			value="<%= LGD_OID %>">                        <!-- 주문번호 -->
<input type="hidden" name="LGD_BUYER"                   id="LGD_BUYER"			value="<%= LGD_BUYER %>">                      <!-- 구매자 -->
<input type="hidden" name="LGD_PRODUCTINFO"             id="LGD_PRODUCTINFO"	value="<%= LGD_PRODUCTINFO %>">                <!-- 상품정보 -->
<input type="hidden" name="LGD_AMOUNT"                  id="LGD_AMOUNT"			value="<%= LGD_AMOUNT %>">                     <!-- 결제금액 -->
<input type="hidden" name="LGD_BUYEREMAIL"              id="LGD_BUYEREMAIL"		value="<%= LGD_BUYEREMAIL %>">                 <!-- 구매자 이메일 -->
<input type="hidden" name="LGD_CUSTOM_SKIN"             id="LGD_CUSTOM_SKIN" 	value="<%= LGD_CUSTOM_SKIN %>">                <!-- 결제창 SKIN -->
<input type="hidden" name="LGD_WINDOW_VER"              id="LGD_WINDOW_VER" 	value="<%= LGD_WINDOW_VER %>">                 <!-- 결제창 버젼정보 -->
<input type="hidden" name="LGD_CUSTOM_PROCESSTYPE"      id="LGD_CUSTOM_PROCESSTYPE"		value="<%= LGD_CUSTOM_PROCESSTYPE %>">         <!-- 트랜잭션 처리방식 -->
<input type="hidden" name="LGD_TIMESTAMP"               id="LGD_TIMESTAMP"		value="<%= LGD_TIMESTAMP %>">                  <!-- 타임스탬프 -->
<input type="hidden" name="LGD_HASHDATA"                id="LGD_HASHDATA"		value="<%= LGD_HASHDATA %>">                   <!-- MD5 해쉬암호값 -->
<input type="hidden" name="LGD_PAYKEY"                  id="LGD_PAYKEY">   							   <!-- LG유플러스 PAYKEY(인증후 자동셋팅)-->
<input type="hidden" name="LGD_VERSION"         		id="LGD_VERSION"		value="JSP_XPay_2.5">
<input type="hidden" name="LGD_BUYERIP"                 id="LGD_BUYERIP"		value="<%= LGD_BUYERIP %>">           			<!-- 구매자IP -->
<input type="hidden" name="LGD_BUYERID"                 id="LGD_BUYERID"		value="<%= LGD_BUYERID %>">           			<!-- 구매자ID -->


<!-- 가상계좌(무통장) 결제연동을 하시는 경우  할당/입금 결과를 통보받기 위해 반드시 LGD_CASNOTEURL 정보를 LG 유플러스에 전송해야 합니다 . -->
<input type="hidden" name="LGD_CASNOTEURL"          id="LGD_CASNOTEURL"		value="<%= LGD_CASNOTEURL %>">                 <!-- 가상계좌 NOTEURL -->

</form>
</body>
<!--  xpay.js는 반드시 body 밑에 두시기 바랍니다. -->
<!--  UTF-8 인코딩 사용 시는 xpay.js 대신 xpay_utf-8.js 을  호출하시기 바랍니다.-->
<script language="javascript" src="<%=request.getScheme()%>://xpay.uplus.co.kr<%="test".equals(CST_PLATFORM)?(request.getScheme().equals("https")?":7443":":7080"):""%>/xpay/js/xpay_utf-8.js" type="text/javascript">
</script>
</html>
