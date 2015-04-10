<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title><spring:message code="L05101"/><%--결제 내용 확인--%></title>
    <style>
        th.header {
            background-color: #eeeeee;
        }

        /* 팝업창이 보여질 부분 */
        .bpopContainer, #popup2, .bMulti {
            background-color: #fff;
            color: #111;
            display: none;
            min-width: 500px;
            padding: 25px;
        }

        .bpopContainer, .bMulti {
            min-height: 250px;
        }
        /* 클릭할 버튼 */
        .button {
            background-color: #2b91af;
            color: #fff;
            cursor: pointer;
            display: inline-block;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
        }
        /* 닫기 버튼 */
        .button.b-close, .button.bClose {
            box-shadow: none;
            font: bold 131% sans-serif;
            padding: 0 6px 2px;
            position: absolute;
            right: -7px;
            top: -7px;
        }
    </style>
</head>
<body>

<div id="overlay" class="web_dialog_overlay"></div>

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
                                    <tr><th class="header"><spring:message code="L05105"/><%--결제금액--%></th><td>${payment.LGD_AMOUNT} 원(Won)</td></tr>
                                </table>
                                <div>
                                    <button class="btn btn-primary btn-lg btn-block ${payment.admsSts.equals("OP")?"":"disabled"}" id="processPayment"><spring:message code="L05106"/><%--결제하기--%></button>
                                </div>
                            </div>
                            <div class="spacer-tiny">&nbsp;</div>
                            <div class="col-sm-8 align-left"></div>
                            <div class="col-sm-4 align-right">
                                <button class="btn btn-warning btn-lg btn-block" id="inform"> <spring:message code="U05107"/> </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="LGD_ACTIVEX_DIV"></div> <!-- ActiveX 설치 안내 Layer 입니다. 수정하지 마세요. -->
            <div id="xpayLoad"></div>
            <input type="hidden" name="LGD_AMOUNT"             id="LGD_AMOUNT"         value="${payment.LGD_AMOUNT}"/>
            <input type="hidden" name="LGD_TAXFREEAMOUNT"      id="LGD_TAXFREEAMOUNT"  value="${payment.LGD_AMOUNT}"/>
            <input type="hidden" name="LGD_BUYER"              id="LGD_BUYER"          value="${payment.LGD_BUYER}"/>
            <input type="hidden" name="LGD_PRODUCTINFO"        id="LGD_PRODUCTINFO"    value="${payment.LGD_PRODUCTINFO}"/>
            <input type="hidden" name="LGD_BUYERID"            id="LGD_BUYERID"        value="${payment.LGD_BUYERID}">
            <input type="hidden" name="admsSts"                id="admsSts"            value="${payment.admsSts}">

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

    <div id="modal_popup3" class="popup1_wrap" style="display:none; margin-top:-250px; margin-left:-250px;">
        <div id="bpopContent" class="popuphead">
            <h1>
                <label id="searchTitle"> <spring:message code="U05106"/> </label>
            </h1>
        </div>
        <div class="popupbody" style="display:${pageContext.response.locale == 'en' ?'none':'visible'}">
            <h4><strong>
                1. 결제 모듈은 인터넷 익스플로러 (9.0 이상) 32Bit 버전만 지원됩니다.<br>
                &nbsp;&nbsp;&nbsp;&nbsp;64Bit 버전을 실행중이신 분은 ActiveX 설치에 문제가 발생합니다.<br>
                2. 크롬, 사파리 등의 웹브라우저에서는 결제를 진행하실 수 없습니다.<br>
                3. 국내 카드와 UnionPay가 지원됩니다.(해외 비자, 마스터 카드 불가)<br><br>
                <font color="red" size="3"> [[ 결제가 불가능한 경우 ]] <br><br></font>
                시스템에서 결제가 불가능한 경우 아래 계좌로 입금 후 메일을 주시기 바랍니다.<br>
                <font color="blue">&nbsp;&nbsp;입금은행 : 하나은행<br>
                &nbsp;&nbsp;계좌번호 : 178-910029-30904<br>
                &nbsp;&nbsp;예금주명 : 에이펙스소프트<br><br></font>
                <font color="orange">입금 완료 후 아이디와 입금자명을 반드시 기입하여<br>
                help@apexsoft.co.kr 로 보내주시기 바랍니다.<br></font>
                <span style="font-weight: 900; color: red; font-size: 25px;">현재 입금자가 폭주하여 입금 확인이 지연되고 있습니다.<br/>입금 확인이 지연되더라도, 시스템에서 원서를 제출하시고 16:30 전까지 입금을 완료하신 신청건은 모두 유효한 신청으로 완료된 것이므로<br/>별도의 연락이 없더라도 걱정하지 않으셔도 됩니다.</span>
            </strong></h4>
        </div>
        <div class="popupbody" style="display:${pageContext.response.locale == 'en' ?'visible':'none'}">
            <h4><strong>
                1. CANNOT pay with VISA, MASTER.<br>
                &nbsp;&nbsp;&nbsp;&nbsp;UnionPay and Korean Local Card is supported.<br>
                2. Internet Explorer (9.0 or above) 32Bit is supported only.<br>
                &nbsp;&nbsp;&nbsp;&nbsp;You may have problem with ActiveX Installation in 64Bit version.<br>
                3. Browser except Internet Explorer (like Chrome, Safari, Firefox, ...)<br>
                &nbsp;&nbsp;&nbsp;&nbsp;is NOT supported in payment.<br><br>
                <font color="red" size="3"> [[ If payment is impossible in System ]] <br><br></font>
                &nbsp;&nbsp;1. Paypal Payment<br>
                <font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;Send email with Gradnet UserID , Paypal account and Name<br>
                &nbsp;&nbsp;&nbsp;&nbsp;to help@apexsoft.co.kr. Then You can receive Paypal Invoice.<br><br></font>
                &nbsp;&nbsp;2. Wire Transfer<br>
                <font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;Send fee to this bank account.<br>
                &nbsp;&nbsp;&nbsp;&nbsp;After that, You should send email with Gradnet UserID<br>
                &nbsp;&nbsp;&nbsp;&nbsp;and Transfer Receipt(photo)<br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-BANK NAME : HANA BANK<br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-BANK SWIFT :  HNBNKRSE XXX<br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-BRANCH ADDR.: 97, Wausan-ro, Mapo-gu, Seoul, KOREA<br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-BRANCH NAME : SEOGYODONG<br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-ACCOUNT NO. : 178-910029-30904<br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-ACCOUNT NAME : APEXSOFT<br></font>
            </strong></h4>
        </div>
        <a class="btn_close b-close" title="닫기"><img src="<spring:eval expression="@app.getProperty('path.static')" />/img/btn_close1.png" alt="닫기"></a>
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

                ret = xpay_check(document.getElementById('LGD_PAYINFO'), document.getElementById('CST_PLATFORM').value);

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

                        if( LGD_RESPMSG != null && LGD_RESPMSG.indexOf("사용자가 취소") != -1 ) {
                            LGD_RESPMSG = LGD_RESPMSG + "\n(User Canceled)";
                        }

                        alert("<spring:message code="U05101"/>\n" + LGD_RESPMSG);  /*인증이 실패하였습니다.*/

                    }

                } else {

                    alert("<spring:message code="U05102"/>");  /*LG U+ 전자결제를 위한 ActiveX Control 이 설치되지 않았습니다.*/

                }
            }

            function isActiveXOK() {
                if(lgdacom_atx_flag != true){
                    $('#xpayLoad').text("<spring:message code="U05103"/>");  /*전자결제 모듈이 로딩되지 않았습니다.*/
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
                    alert("<spring:message code="U05104"/>\n\n");
                }
            }
        })

        onload = function() {
            showDialog(true, "#modal_popup3");
        }

        var hideDialog = function(obj) {
            $("#overlay").hide();
            $(obj).fadeOut(300);
        };

        var showDialog = function(modal, obj) {
            $("#overlay").show();
            $(obj).fadeIn(300);

            if (modal) {
                $("#overlay").unbind("click");
            }
            else {
                $("#overlay").click(function(e) {
                    hideDialog(obj);
                });
            }
        };

        $('.b-close').on('click', function(e) {
            e.preventDefault();
            hideDialog('#modal_popup3');
        });

        $('#inform').click( function(e) {
            e.preventDefault();
            showDialog(true, "#modal_popup3");
        });

    </script>
</content>
</body>
</html>
