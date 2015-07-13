<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lgdacom.XPayClient.XPayClient"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title><spring:message code="L05301"/><%--결제 및 신청 완료--%></title>
    <style>

    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <form id="resultForm" method="post">
            <div class="row mar-bot40">
                <div class="col-md-offset-1 col-md-10">
                    <div class="form-group inner-container-white">
                        <div class="col-md-offset-1 col-md-10">
                            <div class="col-sm-12 text-gray">
                                <i class="fa fa-thumbs-o-up fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L05301"/><%--결제 및 신청 완료--%></b></span>
                            </div>
                            <div class="spacer-small">&nbsp;</div>
                            <div class="col-sm-12 text-gray">
                                <h3 class="pay">${transactionVO.userMsg}</h3>
                            </div>
                            <div class="spacer-tiny">&nbsp;</div>

                            <div class="col-sm-12">
                                <button class="btn btn-primary btn-lg btn-block" id="goMain"><spring:message code="L05302"/><%--내 원서 보기--%></button>
                            </div>
                            <%--${transactionVO.sysMsg}--%>



                            <%--<c:forEach var="item" items="${transactionVO.txMap}" varStatus="status">--%>
                            <%--${status.count}<br/>--%>
                            <%--${item.key} : ${item.value}<br/>--%>
                            <%--</c:forEach>--%>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" name="application.applNo" value="${transactionVO.applNo}"/>
        </form>
    </div>
</section>
<content tag="local-script">
<script>
$(document).ready(function() {

    <%--Birt 문서를 http request가 아니라 소스 내부에서도 생성할 수 있게 되어 아래 로직은 모두--%>
    <%--PaymentServiceImpl.genAndUploadApplicationFormAndSlipFile(application)에서 처리하므로 삭제--%>
    <%--// 결제 완료 후 BirtController를 호출해야 수험표와 원서를 물리적 파일로 저장할 수 있음--%>
    <%--var genFile = function () {--%>

        <%--var form = document.getElementById('resultForm'),--%>
            <%--formData = $(form).serialize();--%>
        <%--$.ajax({--%>
            <%--type: 'POST',--%>
            <%--url: '${contextPath}/application/generate/application',--%>
            <%--data: formData,--%>
            <%--success: function (data) {--%>
                <%--if (console) {--%>
                    <%--console.log('원서 파일 생성 완료');--%>
                <%--}--%>

                <%--$.ajax({--%>
                    <%--type: 'POST',--%>
                    <%--url: '${contextPath}/pdf/merge/applicant',--%>
                    <%--data: formData,--%>
                    <%--success: function (data) {--%>
                        <%--if (console) {--%>
                            <%--console.log('머지 파일 생성 완료');--%>
                        <%--}--%>

                        <%--document.getElementById('spinner').style.display = 'none';--%>
                        <%--document.getElementById('goMain').style.display = 'block';--%>
                    <%--},--%>
                    <%--error: function (data, status, e) {--%>
                        <%--if (console) {--%>
                            <%--console.log('머지 파일 생성 실패');--%>
                        <%--}--%>

                    <%--}--%>
                <%--});--%>
            <%--},--%>
            <%--error: function (data, status, e) {--%>
                <%--if (console) {--%>
                    <%--console.log('원서 파일 생성 실패');--%>
                <%--}--%>

            <%--}--%>
        <%--});--%>
        <%--$.ajax({--%>
            <%--type: 'POST',--%>
            <%--url: '${contextPath}/application/generate/slip',--%>
            <%--data: formData,--%>
            <%--success: function (data) {--%>
                <%--if (console) {--%>
                    <%--console.log('수험표 파일 생성 완료');--%>
                <%--}--%>

            <%--},--%>
            <%--error: function (data, status, e) {--%>
                <%--if (console) {--%>
                    <%--console.log('수험표 파일 생성 실패');--%>
                <%--}--%>

            <%--}--%>
        <%--});--%>
    <%--};--%>
    <%--genFile();--%>

    $('#goMain').click( function () {
        var form = document.getElementById('resultForm');
        form.action = '${contextPath}/application/mylist';
        form.submit();
    });
});
</script>
</content>
</body>
</html>
