<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <style>
        th.header {
            background-color: #eeeeee;
        }
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-md-offset-1 col-md-10">
                <div class="form-group inner-container-white">
                    <div class="col-sm-12 text-gray">
                        <i class="fa fa-check-square-o fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b>결제 내용 확인</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="col-sm-offset-1 col-sm-10 text-gray">
                        <div class="col-sm-12 align-center">
                            <table class="table table-bordered">
                                <tr><th class="header col-md-4">회원ID</th><td class="col-md-8">${payment.LGD_BUYERID}</td></tr>
                                <tr><th class="header">회원명</th><td>${payment.LGD_BUYER}</td></tr>
                                <tr><th class="header">신청과정</th><td>${payment.LGD_PRODUCTINFO}</td></tr>
                                <tr><th class="header">결제금액</th><td>${payment.LGD_AMOUNT}원</td></tr>
                                <tr><th class="header">가상계좌정보 <br> (입금전)</th><td>은행 : ${payment.LGD_FINANCENAME} <br> 계좌 : ${payment.LGD_ACCOUNTNUM}</td></tr>
                            </table>
                        </div>
                        <div class="col-sm-12">
                            <button class="btn btn-primary btn-lg btn-block" id="goMain">내 원서 보기</button>
                        </div>
                        <div class="col-sm-12 align-left">
                            <br><br>
                            * 테스트용 계좌이체 실행 (무통장입금 테스트 데모 선택)
                            <a target=new href="http://pgweb.uplus.co.kr:8080/pg/wmp/Home2009/skill/payment_demo.jsp">http://pgweb.uplus.co.kr:8080/pg/wmp/Home2009/skill/payment_demo.jsp</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<content tag="local-script">
    <script>
        $('#goMain').click( function () { location.href='${contextPath}/application/mylist'; });
    </script>
</content>
</body>
</html>
