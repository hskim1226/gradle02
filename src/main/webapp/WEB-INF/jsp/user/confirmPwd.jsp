<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang='ko'>
<head>
    <title>비밀번호 재설정</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
    <style>
        section.findInfo {
            padding: 150px 0 60px;
            /*background: #336655;*/
            <%--background-image: url(${contextPath}/img/common/login-bg1.jpg);--%>
            background-size: cover;
            color: #fdfdfd;
            min-height: 615px;
        }

        section.findInfo h2.slogan {
            color: #fff;
            font-size: 48px;
            font-weight: 900;
        }

        section.findInfo .spacer-big {
            margin-bottom: 7em;
        }

        section.findInfo .spacer-mid {
            margin-bottom: 5em;
        }

        section.findInfo .spacer-small {
            margin-bottom: 3em;
        }

        section.findInfo .spacer-tiny {
            margin-bottom: 0.5em;
        }

        .input-text {
            height: 50px;
            font-size: 100%;
            opacity: 1.0;
            margin-bottom: 5%;
        }

        .text-gray {
            color: #333333;
            opacity: 1.0;
            text-align: center;
        }

        .nopadding {
            padding: 0 !important;
            margin: 0 !important;
        }

        input[readonly] {
            background-color: white !important;
            cursor: text !important;
        }

        .validation-error {
            background: #ffdddd;
            color: #f55;
        }

        .required .control-label:after {
            content:"*";
            color:red;
        }
    </style>
</head>
<body>
<section class="findInfo">
    <div class="container">
        <form:form commandName="users" cssClass="form-horizontal" role="form" method="post">
            <div class="col-md-offset-3 col-md-6">
                <div class="form-group inner-container-white">
                    <div class="col-sm-offset-1 col-sm-10 text-gray">
                        <i class="fa fa-key fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b>비밀번호 재설정</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="form-group required">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-3 text-gray">
                                <label for="pswd" class="control-label">비밀번호</label>
                            </div>
                            <div class="col-sm-9">
                                <div class="col-sm-12 nopadding input-group">
                                    <input id="pswd" name="pswd" class="form-control input-text" placeholder="새 비밀번호 입력" />
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
                                <label for="pswd" class="control-label">다시입력</label>
                            </div>
                            <div class="col-sm-9">
                                <div class="col-sm-12 nopadding input-group">
                                    <input id="pswd2" name="pswd2" class="form-control input-text" placeholder="새 비밀번호 입력" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-offset-1 col-sm-10">
                        <div class="col-sm-12 nopadding">
                            <button class="btn btn-primary btn-lg btn-block btn-save input-text">비밀번호 저장</button>
                        </div>
                    </div>
                </div>
            </div>
            <form:hidden path="userId" />
        </form:form>
    </div>
</section>
<script src="${contextPath}/js/jquery-ui.min.js"></script>
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
            alert('비밀번호가 일치하지 않습니다.');
            pwd.focus();
        }
        return pwdOk;
    }
    <%-- 비밀번호 비교 --%>

    <%-- 하단 버튼 처리 --%>
    var formProcess = function(event) {
        event.preventDefault();
        if (checkPwd()) {
            var form = document.getElementById('users');
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

    <%-- placeholder polyfill --%>
    $('input, textarea').placeholder();
    <%-- placeholder polyfill --%>
});
</script>
</body>
</html>