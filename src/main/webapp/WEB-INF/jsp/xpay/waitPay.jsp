<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title><spring:message code="L05201"/><%--가상 계좌 생성 확인--%></title>
    <style>
        th.header {
            background-color: #eeeeee;
        }
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <form class="form-horizontal" id="resultForm" method="post">
            <div class="row mar-bot40">
                <div class="col-md-offset-1 col-md-10">
                    <div class="form-group inner-container-white">
                        <div class="col-sm-12 text-gray">
                            <i class="fa fa-check-square-o fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L05201"/><%--가상계좌 생성 확인--%></b></span>
                        </div>
                        <div class="spacer-small">&nbsp;</div>
                        <div class="col-sm-offset-1 col-sm-10 text-gray">
                            <div class="col-sm-12 align-center">
                                <table class="table table-bordered">
                                    <tr><th class="header col-md-4"><spring:message code="L05202"/><%--회원ID--%></th><td class="col-md-8">${payment.LGD_BUYERID}</td></tr>
                                    <tr><th class="header"><spring:message code="L05203"/><%--회원명--%></th><td>${payment.LGD_BUYER}</td></tr>
                                    <tr><th class="header"><spring:message code="L05204"/><%--신청과정--%></th><td>${payment.LGD_PRODUCTINFO}</td></tr>
                                    <tr><th class="header"><spring:message code="L05205"/><%--결제금액--%></th><td>${payment.LGD_AMOUNT} 원(Won)</td></tr>
                                    <tr><th class="header"><spring:message code="L05206"/><%--가상계좌정보--%> <br> <spring:message code="L05207"/><%--(입금전)--%></th><td><spring:message code="L05210"/><%--은행--%> : ${payment.LGD_FINANCENAME} <br> <spring:message code="L05211"/><%--계좌--%> : ${payment.LGD_ACCOUNTNUM}</td></tr>
                                </table>
                                <div class="col-sm-12 align-left">
                                    <spring:message code="U05301"/><%--해당 가상계좌로 입금해야 지원이 완료됩니다.--%><br><br>
                                </div>
                                <div class="col-sm-12">
                                    <button class="btn btn-primary btn-lg btn-block" id="goMain"><spring:message code="L05208"/><%--내 원서 보기--%></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section>
<content tag="local-script">
    <script>
        $('#goMain').click( function (e) {
            e.preventDefault();
            location.href='${contextPath}/application/mylist';
        });
    </script>
</content>
</body>
</html>
