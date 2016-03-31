<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang='ko'>
<head>
    <title><spring:message code="L00191"/><%--비밀번호 재설정--%></title>
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
        <form:form commandName="user" cssClass="form-horizontal" role="form" method="post">
            <div class="col-md-offset-3 col-md-6">
                <div class="form-group inner-container-white">
                    <div class="col-sm-offset-1 col-sm-10 text-gray">
                        <i class="fa fa-key fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L00191"/><%--비밀번호 재설정--%></b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="form-group required">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-3 text-gray">
                                <label for="pswd" class="control-label"><spring:message code="L00192"/><%--비밀번호--%></label>
                            </div>
                            <div class="col-sm-9">
                                <div class="col-sm-12 nopadding input-group">
                                    <input type="password" id="pswd" name="pswd" class="form-control input-text passwd" maxlength="16" placeholder="<spring:message code="U00191"/>" />  <%--새 비밀번호 입력--%>
                                </div>
                        <spring:bind path="pswd">
                            <c:if test="${status.error}">
                                <div class="col-sm-12 nopadding">
                                    <div class="validation-error">${status.errorMessage}</div>
                                </div>
                            </c:if>
                        </spring:bind>
                            </div>
                        </div>
                    </div>
                    <div class="form-group required">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-3 text-gray">
                                <label for="pswd" class="control-label"><spring:message code="L00193"/><%--다시입력--%></label>
                            </div>
                            <div class="col-sm-9">
                                <div class="col-sm-12 nopadding input-group">
                                    <input type="password" id="pswd2" name="pswd2" class="form-control input-text passwd" maxlength="16" placeholder="<spring:message code="U00192"/>" />  <%--새 비밀번호 입력--%>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-offset-1 col-sm-10">
                        <div class="col-sm-12 nopadding">
                            <button id="changePwd" class="btn btn-primary btn-lg btn-block btn-save input-text"><spring:message code="L00194"/><%--비밀번호 변경--%></button>
                        </div>
                    </div>
                </div>
            </div>
            <form:hidden path="userId" />
        </form:form>
    </div>
</section>
<content tag="local-script">
<script type="text/javascript">
$(document).ready(function() {

    <%-- 비밀번호 비교 --%>
    var checkPwd = function() {
        var pwd = document.getElementById('pswd'),
            pwd1 = pwd.value,
            pwd2 = document.getElementById('pswd2').value,
            pwdOk = false;
        if (pwd1.length > 0 && pwd2.length > 0 && pwd1 == pwd2) {
            pwdOk = true;
        } else {
            alert('<spring:message code="U00130"/>');
            pwd.focus();
        }
        return pwdOk;
    }
    <%-- 비밀번호 비교 --%>

    <%-- 비밀 번호 validation --%>
    apex.passwordCheck('passwd', '<spring:message code="APEXJS_0004"/>');
    <%-- 비밀 번호 validation --%>

    <%-- 하단 버튼 처리 --%>
    var formProcess = function(event) {
        event.preventDefault();
        if (checkPwd()) {
            var form = document.getElementById('user');
            form.action = "${contextPath}/user/savePwd";
            form.submit();
        }
    };
    $('#changePwd').on('click', formProcess);
    $('body').on('keyup', function (e) {
        if (e.keyCode == 13)
            $('#changePwd').click();
    });
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
