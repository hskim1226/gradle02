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

        /* spinner */
        /* http://projects.lukehaas.me/css-loaders/ */
        .loader {
            font-size: 7px;
            margin: 4em auto;
            width: 1em;
            height: 1em;
            border-radius: 50%;
            position: relative;
            text-indent: -9999em;
            -webkit-animation: load4 1.3s infinite linear;
            animation: load4 1.3s infinite linear;
        }
        @-webkit-keyframes load4 {
            0%,
            100% {
                box-shadow: 0em -3em 0em 0.2em #55aaff, 2em -2em 0 0em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 0em #55aaff;
            }
            12.5% {
                box-shadow: 0em -3em 0em 0em #55aaff, 2em -2em 0 0.2em #55aaff, 3em 0em 0 0em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            25% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 0em #55aaff, 3em 0em 0 0.2em #55aaff, 2em 2em 0 0em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            37.5% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 0em #55aaff, 2em 2em 0 0.2em #55aaff, 0em 3em 0 0em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            50% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 0em #55aaff, 0em 3em 0 0.2em #55aaff, -2em 2em 0 0em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            62.5% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 0em #55aaff, -2em 2em 0 0.2em #55aaff, -3em 0em 0 0em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            75% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 0em #55aaff, -3em 0em 0 0.2em #55aaff, -2em -2em 0 0em #55aaff;
            }
            87.5% {
                box-shadow: 0em -3em 0em 0em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 0em #55aaff, -3em 0em 0 0em #55aaff, -2em -2em 0 0.2em #55aaff;
            }
        }
        @keyframes load4 {
            0%,
            100% {
                box-shadow: 0em -3em 0em 0.2em #55aaff, 2em -2em 0 0em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 0em #55aaff;
            }
            12.5% {
                box-shadow: 0em -3em 0em 0em #55aaff, 2em -2em 0 0.2em #55aaff, 3em 0em 0 0em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            25% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 0em #55aaff, 3em 0em 0 0.2em #55aaff, 2em 2em 0 0em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            37.5% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 0em #55aaff, 2em 2em 0 0.2em #55aaff, 0em 3em 0 0em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            50% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 0em #55aaff, 0em 3em 0 0.2em #55aaff, -2em 2em 0 0em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            62.5% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 0em #55aaff, -2em 2em 0 0.2em #55aaff, -3em 0em 0 0em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            75% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 0em #55aaff, -3em 0em 0 0.2em #55aaff, -2em -2em 0 0em #55aaff;
            }
            87.5% {
                box-shadow: 0em -3em 0em 0em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 0em #55aaff, -3em 0em 0 0em #55aaff, -2em -2em 0 0.2em #55aaff;
            }
        }

		ul.dashed {
			list-style: none;
			margin-left: 0;
    		padding-left: 1.1em;
		}
		ul.dashed > li {
/* 			list-style-position: inside; */
		}
		ul.dashed > li:before {
			content: "–"; /* en dash */
			position: absolute;
			margin-left: -1.1em;
			text-margin
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
                                    <button class="btn btn-primary btn-lg btn-block ${payment.admsSts.equals('OP')?'':'disabled'}" id="processPayment"><spring:message code="L05106"/><%--결제하기--%></button>
                                </div>
                            </div>
                            <div class="spacer-tiny">&nbsp;</div>
                            <div class="col-sm-8 align-left"></div>
                            <div class="col-sm-4 align-right">
                                <button class="btn btn-warning btn-lg btn-block" id="inform"> <spring:message code="U05107"/> </button>
                            </div>


                            <div class="spacer-tiny">&nbsp;</div>
                            <div id="spinner" class="col-sm-12" style="display: none;">
                                <div class="loader"></div>
                                <div class="col-sm-12" style="font-size: 24px; color: #55aaff; text-align: center;"><spring:message code="U04519"/></div>  <%--수험표 및 원서 파일 생성 중 입니다...--%>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
            <div id="LGD_ACTIVEX_DIV"></div> <!-- ActiveX 설치 안내 Layer 입니다. 수정하지 마세요. -->
            <div id="xpayLoad"></div>
            <input type="hidden" name="LGD_AMOUNT"             id="LGD_AMOUNT"         value="${payment.LGD_AMOUNT}"/>
            <%--<input type="hidden" name="LGD_AMOUNT"             id="LGD_AMOUNT"         value="200"/> &lt;%&ndash; 테스트용 &ndash;%&gt;--%>
            <input type="hidden" name="LGD_TAXFREEAMOUNT"      id="LGD_TAXFREEAMOUNT"  value="${payment.LGD_TAXFREEAMOUNT}"/>
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
            <%--<input type="hidden" name="applNo" id="applNo2"/>--%>
        </form>
    </div>

	<div class="modal fade" id="modal_popup3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h2 class="modal-title" id="myModalLabel"><spring:message code="U05106" /></h2>
					</div>
					<div class="modal-body">
						<ol style="list-style: decimal inside none">
							<li class="text-left text-muted"><spring:message code="U07101" /></li>
							<li class="text-left text-muted"><spring:message code="U07102" /></li>
							<c:if test="${pageContext.response.locale.language == 'ko'}">
							<li class="text-left text-muted"><spring:message code="U07103" /></li>
							</c:if>
							<li class="text-left text-muted"><spring:message code="U07104" /></li>
							<li class="text-left text-muted"><spring:message code="U07105" />
								<div class="col-sm-offset-1">
									<b class="text-left text-primary"><spring:message code="U07106" /></b>
									<ul class="dashed">
										<li class="text-left text-primary"><spring:message code="U07107" /></li>
										<c:if test="${pageContext.response.locale.language == 'en'}">
										<li class="text-left text-primary"><spring:message code="U07108" /></li>
										<li class="text-left text-primary"><spring:message code="U07109" /></li>
										<li class="text-left text-primary"><spring:message code="U07110" /></li>
										</c:if>
										<li class="text-left text-primary"><spring:message code="U07111" /></li>
										<li class="text-left text-primary"><spring:message code="U07112" /></li>
									</ul>
								</div>
							</li>
						</ol>
						<span class="text-left text-muted"><spring:message code="U07113" /></span>
						<span class="text-left text-muted"><spring:message code="U07114" /></span>
						<span class="text-left text-muted"><spring:message code="U07115" /></span>
						<div class="col-sm-offset-1">
							<ul class="dashed">
								<li class="text-left text-primary"><spring:message code="U07116" /></li>
							</ul>
						</div>
					</div>
					<div class="modal-footer">
                    	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                	</div>
				</div>
			</div>
		</div>
	</div>

<!--     <div id="modal_popup3" class="popup1_wrap" style="display:none;"> -->
<!--         <div id="bpopContent" class="popuphead"> -->
<!--             <h1> -->
<%--                 <label id="searchTitle"> <spring:message code="U05106"/> </label> --%>
<!--             </h1> -->
<!--         </div> -->
<%--         <div class="popupbody" style="display:${pageContext.response.locale == 'en' ?'none':'visible'}"> --%>
<!--             <h4><strong> -->
<!--                 1. 결제 모듈은 인터넷 익스플로러 (9.0 이상) 버전만 지원됩니다.<br> -->
<!--                 2. 크롬, 사파리 등의 웹브라우저에서는 결제를 진행하실 수 없습니다.<br> -->
<!--                 3. 국내 카드 및 VISA, MASTER, JCB, UnionPay 카드가 지원됩니다.<br> -->
<!--                 4. 결제 프로그램의 설치에 관한 문제는 유플러스 전자결제(1544-7772)로 문의하시기 바랍니다.<br><br> -->
<!--                 <font color="red" size="3"> [[ 결제가 불가능한 경우 ]] <br></font> -->
<!--                 시스템에서 결제가 불가능한 경우 아래 계좌로 입금 해주시기 바랍니다.<br> -->
<!--                 <span style="color: red;">입금 완료 후 반드시 다음의 내용을 payment@apexsoft.co.kr로 보내주시기 바랍니다.</span><br/> -->
<!--                 - 아이디, 지원자명, 출금계좌 예금주명, 입금증빙(사진)<br> -->
<!--                 (특히 자동화기기 송금, 해외 송금의 경우 입금증빙(사진) 필수)<br><br> -->
<!--                 <font color="blue">&nbsp;&nbsp;입금은행 : 하나은행<br> -->
<!--                     &nbsp;&nbsp;계좌번호 : 178-910029-30904<br> -->
<!--                     &nbsp;&nbsp;예금주명 : 에이펙스소프트<br><br></font> -->
<!--                 <span style="color: red;">해외에서 송금할 경우 GRADNET에 입금되는 원화금액이 전형료와 일치하도록, 송금 수수료를 발신자가 부담해야 합니다.</span><br/> -->
<!--                 <span style="color: red;">GRADNET에 입금된 금액이 전형료와 맞지 않을 경우 지원 처리가 완료되지 않을 수 있으며,</span><br/> -->
<!--                 <span style="color: red;">GRADNET은 이에 대해 어떠한 책임도 지지 않습니다.</span><br/> -->

<!--             </strong></h4> -->
<!--         </div> -->
<%--         <div class="popupbody" style="display:${pageContext.response.locale == 'en' ?'visible':'none'}"> --%>
<!--             <h4><strong> -->
<!--                 1. VISA, MASTER, JCB, UnionPay, Korean Local Card is supported.<br> -->
<!--                 2. Only Internet Explorer (9.0 or above) is supported.<br> -->
<!--                 3. Browsers except Internet Explorer (like Chrome, Safari, ...)<br> -->
<!--                 &nbsp;&nbsp;&nbsp;&nbsp;are NOT supported for payment.<br><br> -->
<!--                 <font color="red" size="3"> [[ If the payment is NOT possible in the System ]] <br><br></font> -->
<!--                 &nbsp;&nbsp;Please use Wire Transfer instead.<br/> -->
<!--                 &nbsp;&nbsp;Information for Wire Transfer is as follows.<br/> -->
<!--                 <font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;-BANK NAME : HANA BANK<br> -->
<!--                     &nbsp;&nbsp;&nbsp;&nbsp;-BANK SWIFT :  HNBNKRSE XXX<br> -->
<!--                     &nbsp;&nbsp;&nbsp;&nbsp;-BRANCH ADDR.: 97, Wausan-ro, Mapo-gu, Seoul, KOREA<br> -->
<!--                     &nbsp;&nbsp;&nbsp;&nbsp;-BRANCH NAME : SEOGYODONG<br> -->
<!--                     &nbsp;&nbsp;&nbsp;&nbsp;-ACCOUNT NO. : 178-910029-30904<br> -->
<!--                     &nbsp;&nbsp;&nbsp;&nbsp;-ACCOUNT NAME : APEXSOFT<br><br></font> -->
<!--                     <span style="color: red;">You should pay for the charge of wire transfer, so that the final transferred money to GRADNET matches the Admission Fee.</span><br> -->
<!--                     <span style="color: red;">IF NOT, your application may NOT be processed.</span><br> -->
<!--                     <span style="color: red;">GRADNET is NOT responsible for the unprocessed application due to the inconsistency of the amount of transferred money.</span><br> -->
<!--                     <span style="color: red;">After the wire transfer, You should send an Email to payment@apexsoft.co.kr with the following information.</span><br/> -->
<!--                     &nbsp;&nbsp;- User ID, Applicant Name, Withdrawal Account Holder Name and Transfer Receipt(photo).<br> -->


<!--             </strong></h4> -->
<!--         </div> -->
<%--         <a class="btn_close b-close" title="닫기"><img src="<spring:eval expression="@app.getProperty('path.static')" />/img/btn_close1.png" alt="닫기"></a> --%>
<!--     </div> -->
<!-- </div> -->

</section>
<content tag="local-script">
    <%--<script language="javascript" src="http://xpay.uplus.co.kr:7080/xpay/js/xpay_utf-8.js" type="text/javascript"></script>--%>
    <script language="javascript" src="<spring:eval expression="@app.getProperty('path.static')" />/js/xpay_utf-8.js" type="text/javascript"></script>
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
                        document.getElementById('applNo').value = "${payment.applNo}";
                        // ActiveX 설치 과정에서 알 수 없는 이유로 applNo가 유실되는 문제에 대한 회피
                        if (document.getElementById('applNo').value == "") {
                            alert("<spring:message code="U05110"/>");
                            $("#overlay").hide();
                        } else {
                            document.getElementById('LGD_PAYINFO').submit();
                            document.getElementById('spinner').style.display = 'block';
                        }

                    } else { //인증실패

                        if( LGD_RESPMSG != null && LGD_RESPMSG.indexOf("사용자가 취소") != -1 ) {
                            LGD_RESPMSG = LGD_RESPMSG + "\n(User Canceled)";
                        }

                        alert("<spring:message code="U05101"/>\n" + LGD_RESPMSG);  /*인증이 실패하였습니다.*/
                        $("#overlay").hide();
                    }

                } else {

                    alert("<spring:message code="U05102"/>");  /*LG U+ 전자결제를 위한 ActiveX Control 이 설치되지 않았습니다.*/
                    $("#overlay").hide();

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
                $("#overlay").show();
                document.getElementById('LGD_TIMESTAMP').value = dateToFormat(new Date(), 'yyyyMMddhhmmss');
                document.getElementById('applNo').value = "${payment.applNo}";
                <%--document.getElementById('applNo2').value = "${payment.applNo}";--%>
//                document.getElementById('applNo2').value = "";

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
        });

        onload = function() {
            showDialog(true, "#modal_popup3");
        };

        var hideDialog = function(obj) {
            $(obj).fadeOut(300);
        };

        var showDialog = function(modal, obj) {
        	$(obj).modal();
        };

        $('#inform').click( function(e) {
            e.preventDefault();
            showDialog(true, "#modal_popup3");
        });

    </script>
</content>
</body>
</html>
