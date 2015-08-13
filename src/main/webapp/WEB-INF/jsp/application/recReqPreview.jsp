<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang='ko'>
<head>
    <title><spring:message code="L06501"/><%--추천서 요청--%></title>
    <style>
        .input-text {
            height: 50px;
            font-size: 100%;
            opacity: 1.0;
            margin-bottom: 5%;
        }
        input[readonly] {
            background-color: white !important;
            cursor: text !important;
        }
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <%--<form:form commandName="user" cssClass="form-horizontal" role="form" method="post">--%>
        <form id="reqRec" class="form-horizontal" method="post">
            <div class="col-md-offset-1 col-md-10">
                <div class="form-group inner-container-white">
                    <div class="spacer-small">&nbsp;</div>
                    <div class="form-group required">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-3 text-gray">
                                <label for="profName" class="control-label"><spring:message code="L06502"/><%--교수 이름--%></label>
                            </div>
                            <div class="col-sm-9">
                                <div class="col-sm-12 nopadding input-group">
                                    <input type="password" id="profName" name="profName" class="form-control input-text passwd" maxlength="16" placeholder="<spring:message code="L06502"/>" />  <%--교수 이름--%>
                                </div>
                        <%--<spring:bind path="profName">--%>
                            <%--<c:if test="${status.error}">--%>
                                <%--<div class="col-sm-12 nopadding">--%>
                                    <%--<div class="validation-error">${status.errorMessage}</div>--%>
                                <%--</div>--%>
                            <%--</c:if>--%>
                        <%--</spring:bind>--%>
                            </div>
                        </div>
                    </div>
                    <div class="form-group required">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-3 text-gray">
                                <label for="profMailAddr" class="control-label"><spring:message code="L06503"/><%--교수 e-mail--%></label>
                            </div>
                            <div class="col-sm-9">
                                <div class="col-sm-12 nopadding input-group">
                                    <input type="password" id="profMailAddr" name="profMailAddr" class="form-control input-text passwd" maxlength="16" placeholder="<spring:message code="L06503"/>" />  <%--교수 e-mail--%>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group required">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-3 text-gray">
                                <label for="reqText" class="control-label"><spring:message code="L06504"/><%--요청 내용--%></label>
                            </div>
                            <div class="col-sm-9">
                                <div class="col-sm-12 nopadding input-group">
                                    <textarea id="reqText" name="reqText" class="form-control" rows="10" placeholder="<spring:message code="U06503"/>"></textarea>  <%--교수님께 보낼 메일 내용을 500자 이내로 입력해주세요.--%>
                                    <%--<input type="password" id="pswd2" name="pswd2" class="form-control input-text passwd" maxlength="16" placeholder="<spring:message code="U06503"/>" />  &lt;%&ndash;교수님께 보낼 메일 내용을 입력해주세요.&ndash;%&gt;--%>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-offset-1 col-sm-10">
                        <div class="col-sm-4 nopadding">
                            <button class="preview btn btn-info btn-lg btn-block btn-save input-text"><spring:message code="L06531"/><%--미리 보기--%></button>
                        </div>
                        <div class="col-sm-4">
                            <button class="save btn btn-warning btn-lg btn-block btn-save input-text"><spring:message code="L06532"/><%--임시 저장--%></button>
                        </div>
                        <div class="col-sm-4 nopadding">
                            <button class="send btn btn-primary btn-lg btn-block btn-save input-text"><spring:message code="L06533"/><%--추천서 요청 메일 보내기--%></button>
                        </div>
                    </div>
                </div>
            </div>
            <%--<form:hidden path="userId" />--%>
            <textarea>abcde</textarea>
        </form>
    </div>
</section>
<content tag="local-script">
<script type="text/javascript">
$(document).ready(function() {


    <%-- 하단 버튼 처리 --%>
    var formProcess = function(event) {
        event.preventDefault();
        if (checkPwd()) {
            var form = document.getElementById('reqRec');
            form.action = "${contextPath}/user/savePwd";
            form.submit();
        }
    };
    $('.btn-save').on('click', formProcess);
    <%-- 하단 버튼 처리 --%>

    <%-- action 성공 여부 알림 처리 --%>
    var showActionResult = function() {
        var msg = '${resultMsg}',
            result = '${result}';
        if (msg.length > 0) {
            confirm(msg);
            if (result == 'SUCCESS') {
                document.location.href = '${contextPath}/user/login';
            }
        }
    };
    showActionResult();
    <%-- action 성공 여부 알림 처리 --%>

    $('#pswd').focus();
});
</script>
</content>
</body>
</html>
