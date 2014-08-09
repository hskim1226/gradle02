<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <style>
        th.header {
            background-color: #773344;
        }
    </style>
</head>
<body>
<section class="application-mylist" id="app-mylist">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-md-6 col-md-offset-3">
                <h2 class="slogan">결제 내용 확인</h2>
                <div class="align-center">
                    <form class="form-horizontal" id="LGD_PAYINFO" role="form" action="${contextPath}/pay/process" method="post">
                        <table class="table table-bordered">
                            <tr><th class="header">회원ID</th><td>${paymentVO.LGD_BUYERID}</td></tr>
                            <tr><th class="header">회원명</th><td>${paymentVO.LGD_BUYER}</td></tr>
                            <tr><th class="header">신청과정</th><td>${paymentVO.LGD_PRODUCTINFO}</td></tr>
                            <tr><th class="header">결제금액</th><td>${paymentVO.LGD_AMOUNT}원</td></tr>
                        </table>
                        <div>
                            <button class="btn btn-primary btn-lg btn-block" id="processPayment">결제하기</button>
                        </div>
                        <input type="hidden" name="CST_PLATFORM"                id="CST_PLATFORM">                   <!-- 테스트, 서비스 구분 -->
                        <input type="hidden" name="CST_MID"                     id="CST_MID">                        <!-- 상점아이디 -->
                        <input type="hidden" name="LGD_MID"                     id="LGD_MID">                        <!-- 상점아이디 -->
                        <input type="hidden" name="LGD_OID"                     id="LGD_OID">                        <!-- 주문번호 -->
                        <input type="hidden" name="LGD_BUYER"                   id="LGD_BUYER">                      <!-- 구매자 -->
                        <input type="hidden" name="LGD_PRODUCTINFO"             id="LGD_PRODUCTINFO">                <!-- 상품정보 -->
                        <input type="hidden" name="LGD_AMOUNT"                  id="LGD_AMOUNT">                     <!-- 결제금액 -->
                        <input type="hidden" name="LGD_BUYEREMAIL"              id="LGD_BUYEREMAIL">                 <!-- 구매자 이메일 -->
                        <input type="hidden" name="LGD_CUSTOM_SKIN"             id="LGD_CUSTOM_SKIN">                <!-- 결제창 SKIN -->
                        <input type="hidden" name="LGD_WINDOW_VER"              id="LGD_WINDOW_VER">                 <!-- 결제창 버젼정보 -->
                        <input type="hidden" name="LGD_CUSTOM_PROCESSTYPE"      id="LGD_CUSTOM_PROCESSTYPE">         <!-- 트랜잭션 처리방식 -->
                        <input type="hidden" name="LGD_TIMESTAMP"               id="LGD_TIMESTAMP">                  <!-- 타임스탬프 -->
                        <input type="hidden" name="LGD_HASHDATA"                id="LGD_HASHDATA">                   <!-- MD5 해쉬암호값 -->
                        <input type="hidden" name="LGD_PAYKEY"                  id="LGD_PAYKEY">   							   <!-- LG유플러스 PAYKEY(인증후 자동셋팅)-->
                        <input type="hidden" name="LGD_VERSION"         		id="LGD_VERSION">
                        <input type="hidden" name="LGD_BUYERIP"                 id="LGD_BUYERIP">           			<!-- 구매자IP -->
                        <input type="hidden" name="LGD_BUYERID"                 id="LGD_BUYERID">           			<!-- 구매자ID -->

                        <!-- 가상계좌(무통장) 결제연동을 하시는 경우  할당/입금 결과를 통보받기 위해 반드시 LGD_CASNOTEURL 정보를 LG 유플러스에 전송해야 합니다 . -->
                        <input type="hidden" name="LGD_CASNOTEURL"          id="LGD_CASNOTEURL">                 <!-- 가상계좌 NOTEURL -->

                    </form>
                </div>
                <div id="LGD_ACTIVEX_DIV"/> <!-- ActiveX 설치 안내 Layer 입니다. 수정하지 마세요. -->
                <div id="xpayLoad"></div>
            </div>
        </div>
    </div>
</section>
<content tag="local-script">
    <script language="javascript" src="http://xpay.uplus.co.kr:7080/xpay/js/xpay_utf-8.js" type="text/javascript"></script>
    <script>
        $(document).ready( function() {
            /*
             * 상점결제 인증요청후 PAYKEY를 받아서 최종결제 요청.
             */
            function doPay_ActiveX(){

                ret = xpay_check(document.getElementById('LGD_PAYINFO'), 'test');

                if (ret=="00"){     //ActiveX 로딩 성공
                    var LGD_RESPCODE        = dpop.getData('LGD_RESPCODE');       //결과코드
                    var LGD_RESPMSG         = dpop.getData('LGD_RESPMSG');        //결과메세지
                    alert('LGD_RESPCODE : ' + LGD_RESPCODE + '\n' + 'LGD_RESPMSG : ' + LGD_RESPMSG);
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
                if(lgdacom_atx_flag != true){
                    $('#xpayLoad').text("전자결제 모듈이 로딩되지 않았습니다.");
                }
            }

            $('#processPayment').click(function(){
                document.getElementById('CST_PLATFORM').value = "${paymentVO.CST_PLATFORM}";
                document.getElementById('CST_MID').value = "${paymentVO.CST_MID}";
                document.getElementById('LGD_MID').value = "${paymentVO.LGD_MID}";
                document.getElementById('LGD_OID').value = "${paymentVO.LGD_OID}";
                document.getElementById('LGD_BUYER').value = "${paymentVO.LGD_BUYER}";
                document.getElementById('LGD_PRODUCTINFO').value = "${paymentVO.LGD_PRODUCTINFO}";
                document.getElementById('LGD_AMOUNT').value = "${paymentVO.LGD_AMOUNT}";
                document.getElementById('LGD_BUYEREMAIL').value = "${paymentVO.LGD_BUYEREMAIL}";
                document.getElementById('LGD_CUSTOM_SKIN').value = "${paymentVO.LGD_CUSTOM_SKIN}";
                document.getElementById('LGD_WINDOW_VER').value = "${paymentVO.LGD_WINDOW_VER}";
                document.getElementById('LGD_CUSTOM_PROCESSTYPE').value = "${paymentVO.LGD_CUSTOM_PROCESSTYPE}";
                document.getElementById('LGD_TIMESTAMP').value = "${paymentVO.LGD_TIMESTAMP}";
                document.getElementById('LGD_HASHDATA').value = "${paymentVO.LGD_HASHDATA}";
                document.getElementById('LGD_VERSION').value = "${paymentVO.LGD_VERSION}";
                document.getElementById('LGD_BUYERIP').value = "${paymentVO.LGD_BUYERIP}";
                document.getElementById('LGD_BUYERID').value = "${paymentVO.LGD_BUYERID}";
                document.getElementById('LGD_CASNOTEURL').value = "${paymentVO.LGD_CASNOTEURL}";
                doPay_ActiveX();
            });

            isActiveXOK();
        })
    </script>
</content>
</body>
</html>
