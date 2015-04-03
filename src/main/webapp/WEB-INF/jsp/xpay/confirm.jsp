<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title><spring:message code="L05101"/><%--결제 내용 확인--%></title>
    <style>
        th.header {
            background-color: #eeeeee;
        }
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <form class="form-horizontal" id="LGD_PAYINFO" role="form" action="${contextPath}/payment/process" method="post">
            <div class="row mar-bot40">
                <div class="col-md-offset-1 col-md-10">
                    <div class="form-group inner-container-white">
                        <div class="col-sm-12 text-gray">
                            <i class="fa fa-check-square-o fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L05101"/><%--결제 내용 확인--%></b></span>
                        </div>
                        <div class="spacer-small">&nbsp;</div>
                        <div class="col-sm-offset-1 col-sm-10 text-gray">
                            <div class="col-sm-12 align-center">
                                <table class="table table-bordered">
                                    <tr><th class="header col-md-4"><spring:message code="L05102"/><%--회원ID--%></th><td class="col-md-8">${payment.LGD_BUYERID}</td></tr>
                                    <tr><th class="header"><spring:message code="L05103"/><%--회원명--%></th><td>${payment.LGD_BUYER}</td></tr>
                                    <tr><th class="header"><spring:message code="L05104"/><%--신청과정--%></th><td>${payment.LGD_PRODUCTINFO}</td></tr>
                                    <tr><th class="header"><spring:message code="L05105"/><%--결제금액--%></th><td>${payment.LGD_AMOUNT}원</td></tr>
                                </table>
                                <div>
                                    <button class="btn btn-primary btn-lg btn-block ${payment.admsSts.equals("OP")?"":"disabled"}" id="processPayment"><spring:message code="L05106"/><%--결제하기--%></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="LGD_ACTIVEX_DIV"></div> <!-- ActiveX 설치 안내 Layer 입니다. 수정하지 마세요. -->
            <div id="xpayLoad"></div>
            <input type="hidden" name="LGD_AMOUNT"             id="LGD_AMOUNT"      value="${payment.LGD_AMOUNT}"/>
            <input type="hidden" name="LGD_BUYER"              id="LGD_BUYER"       value="${payment.LGD_BUYER}"/>
            <input type="hidden" name="LGD_PRODUCTINFO"        id="LGD_PRODUCTINFO" value="${payment.LGD_PRODUCTINFO}"/>
            <input type="hidden" name="LGD_BUYERID"            id="LGD_BUYERID"     value="${payment.LGD_BUYERID}">
            <input type="hidden" name="admsSts"                id="admsSts"         value="${payment.admsSts}">

            <input type="hidden" name="CST_PLATFORM"           id="CST_PLATFORM">
            <input type="hidden" name="CST_MID"                id="CST_MID">
            <input type="hidden" name="LGD_MID"                id="LGD_MID">
            <input type="hidden" name="LGD_OID"                id="LGD_OID">
            <input type="hidden" name="LGD_TIMESTAMP"          id="LGD_TIMESTAMP">
            <input type="hidden" name="LGD_HASHDATA"           id="LGD_HASHDATA">
            <input type="hidden" name="LGD_BUYERIP"            id="LGD_BUYERIP">
            <input type="hidden" name="LGD_BUYEREMAIL"         id="LGD_BUYEREMAIL">
            <input type="hidden" name="LGD_CUSTOM_SKIN"        id="LGD_CUSTOM_SKIN">
            <input type="hidden" name="LGD_WINDOW_VER"         id="LGD_WINDOW_VER">
            <input type="hidden" name="LGD_CUSTOM_PROCESSTYPE" id="LGD_CUSTOM_PROCESSTYPE">
            <input type="hidden" name="LGD_VERSION"            id="LGD_VERSION">
            <input type="hidden" name="LGD_CASNOTEURL"         id="LGD_CASNOTEURL">

            <input type="hidden" name="LGD_PAYKEY"             id="LGD_PAYKEY">
            <input type="hidden" name="LGD_RESPCODE"           id="LGD_RESPCODE">
            <input type="hidden" name="LGD_RESPMSG"            id="LGD_RESPMSG">

            <input type="hidden" name="application.applNo" id="applNo"/>
        </form>
    </div>
</section>
<content tag="local-script">
    <script language="javascript" src="http://xpay.uplus.co.kr:7080/xpay/js/xpay_utf-8.js" type="text/javascript"></script>
    <script>
        $(document).ready( function() {

            isActiveXOK();

            admsStsCheck();

            /*
             * 상점결제 인증요청후 PAYKEY를 받아서 최종결제 요청.
             */
            function doPay_ActiveX() {

                ret = xpay_check(document.getElementById('LGD_PAYINFO'), 'test');

                if (ret=="00") {     //ActiveX 로딩 성공

                    var LGD_RESPCODE = dpop.getData('LGD_RESPCODE');       //결과코드
                    var LGD_RESPMSG  = dpop.getData('LGD_RESPMSG');        //결과메세지
                    var LGD_PAYKEY   = dpop.getData('LGD_PAYKEY');         //LG유플러스 인증KEY

                    document.getElementById('LGD_RESPCODE').value = LGD_RESPCODE;
                    document.getElementById('LGD_RESPMSG').value = LGD_RESPMSG;
                    document.getElementById('LGD_PAYKEY').value = LGD_PAYKEY;

                    $.ajax( {
                        url: "${contextPath}/payment/certify",
                        type: "POST",
                        data: $("#LGD_PAYINFO").serialize(),
                        contentType: "application/x-www-form-urlencoded; charset=UTF-8"
                    });

                    if( "0000" == LGD_RESPCODE ) { //인증성공

                        document.getElementById('LGD_PAYINFO').submit();

                    } else { //인증실패

                        alert("인증이 실패하였습니다. " + LGD_RESPMSG);

                    }

                } else {

                    alert("LG U+ 전자결제를 위한 ActiveX Control 이 설치되지 않았습니다.");

                }
            }

            function isActiveXOK() {
                if(lgdacom_atx_flag != true){
                    $('#xpayLoad').text("전자결제 모듈이 로딩되지 않았습니다.");
                }
            }

            function add0(d, l) {
                var r = '', t0 = d.toString().length;
                if ( l > t0 ) {
                    while( l-- > t0 ) {
                        r += '0';
                    }
                    return r + d;
                }
                else return d;
            }

            function dateToFormat(d, f) {
                if ( d instanceof Date ) {
                    return f.replace(/(yyyy|MM|dd|hh|mm|ss)/gi, function(t) {
                        switch (t) {
                            case 'yyyy' : return d.getFullYear();
                            case 'MM'   : return add0(d.getMonth() + 1, 2);
                            case 'dd'   : return add0(d.getDate(), 2);
                            case 'hh'   : return add0(d.getHours(), 2);
                            case 'mm'   : return add0(d.getMinutes(), 2);
                            case 'ss'   : return add0(d.getSeconds(), 2);
                            default     : return t;
                        }
                    });
                }
            }

            $('#processPayment').click( function(e) {
                e.preventDefault();
//                alert('결제를 진행합니다');

                document.getElementById('LGD_TIMESTAMP').value = dateToFormat(new Date(), 'yyyyMMddhhmmss');
                document.getElementById('applNo').value = "${payment.applNo}";

                $.ajax( {

                    url: "${contextPath}/payment/info",
                    type: "GET",
                    data: $("#LGD_PAYINFO").serialize(),
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",

                    success: function(data){

                        var container = JSON.parse(data),
                            parsed = JSON.parse(container.data);

                        document.getElementById('CST_PLATFORM').value = parsed.cst_PLATFORM,
                        document.getElementById('CST_MID').value = parsed.cst_MID,
                        document.getElementById('LGD_MID').value = parsed.lgd_MID,
                        document.getElementById('LGD_OID').value = parsed.lgd_OID,
                        document.getElementById('LGD_HASHDATA').value = parsed.lgd_HASHDATA,
                        document.getElementById('LGD_BUYERIP').value = parsed.lgd_BUYERIP,
                        document.getElementById('LGD_BUYEREMAIL').value = parsed.lgd_BUYEREMAIL,
                        document.getElementById('LGD_CUSTOM_SKIN').value = parsed.lgd_CUSTOM_SKIN,
                        document.getElementById('LGD_WINDOW_VER').value = parsed.lgd_WINDOW_VER,
                        document.getElementById('LGD_CUSTOM_PROCESSTYPE').value = parsed.lgd_CUSTOM_PROCESSTYPE,
                        document.getElementById('LGD_VERSION').value = parsed.lgd_VERSION,
                        document.getElementById('LGD_CASNOTEURL').value = parsed.lgd_CASNOTEURL;

//                        alert(document.getElementById('LGD_MID').value);

                        doPay_ActiveX();
                    }
                });
            });

            function admsStsCheck() {

                if( document.getElementById('admsSts').value == "CL" ) {
                    alert('죄송합니다.\n\n원서 접수 기간이 아니므로 결제를 하실 수 없습니다.\n\n');
                }
            }
        })
    </script>
</content>
</body>
</html>
