<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang='ko'>
<head>
    <title><spring:message code="L00221"/><%--비밀번호 찾기--%></title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
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
                        <i class="fa fa-search fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L00221"/><%--비밀번호 찾기--%></b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="form-group required">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-3 text-gray">
                                <label for="userId" class="control-label"><spring:message code="L00222"/><%--아이디--%></label>
                            </div>
                            <div class="col-sm-9">
                                <div class="col-sm-12 nopadding">
                                    <form:input path="userId" cssClass="form-control input-text" placeholder="아이디" />
                                </div>
                        <spring:bind path="userId">
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
                                <label for="name" class="control-label"><spring:message code="L00223"/><%--이름--%></label>
                            </div>
                            <div class="col-sm-9">
                                <div class="col-sm-12 nopadding">
                                    <form:input path="name" cssClass="form-control input-text" placeholder="이름" />
                                </div>
                        <spring:bind path="name">
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
                                <label for="mailAddr" class="control-label"><spring:message code="L00224"/><%--이메일--%></label>
                            </div>
                            <div class="col-sm-9">
                                <div class="col-sm-12 nopadding">
                                    <form:input path="mailAddr" cssClass="form-control input-text" placeholder="이메일" />
                                </div>
                                <spring:bind path="mailAddr">
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
                                <label for="bornDay" class="control-label"><spring:message code="L00225"/><%--생년월일--%></label>
                            </div>
                            <div class="col-sm-9">
                                <div class="input-group date">
                                    <form:input path="bornDay" cssClass="form-control input-text" readonly="true" />
                                    <span class="input-group-addon calendar-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                        <spring:bind path="bornDay">
                            <c:if test="${status.error}">
                                <div class="validation-error validation-container">
                                        ${status.errorMessage}
                                </div>
                            </c:if>
                        </spring:bind>
                            </div>
                        </div>
                    </div>
                    <div style="margin-bottom: 10px">&nbsp;</div>
                    <div class="col-sm-offset-1 col-sm-10">
                        <div class="col-sm-12 nopadding">
                            <button class="btn btn-primary btn-lg btn-block btn-save input-text"><spring:message code="L00226"/><%--비밀번호 찾기--%></button>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</section>
<content tag="local-script">
<script src="${contextPath}/js/jquery-ui.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {

    <%-- 하단 버튼 처리 --%>
    var formProcess = function(event) {
        event.preventDefault();
        var form = document.getElementById('user');
        form.action = "${contextPath}/user/resetPwd";
        form.submit();
    };
    $('.btn-save').on('click', formProcess);
    <%-- 하단 버튼 처리 --%>

    <%-- 달력 옵션 --%>
    var datePickerOption = {
        dateFormat: 'yymmdd',
        yearRange: "1950:",
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        dayNamesMin: ['일','월','화','수','목','금','토'],
        changeMonth: true, //월변경가능
        changeYear: true, //년변경가능
        showMonthAfterYear: true //년 뒤에 월 표시
    };

    <%-- 달력 시작 --%>
    $('.input-group.date>input').datepicker(datePickerOption);
    $('.calendar-addon').on('click', function () {
        $(this.parentNode).children('input')[0].focus();
    });
    <%-- 달력 끝 --%>

    <%-- action 성공 여부 알림 처리 --%>
    var showActionResult = function() {
        var msg = '${resultMsg}';
        if (msg.length > 0) {
            confirm(msg);
        }
    };
    showActionResult();
    <%-- action 성공 여부 알림 처리 --%>

    $('#username').focus();

    <%-- placeholder polyfill --%>
    $('input, textarea').placeholder();
    <%-- placeholder polyfill --%>
});
</script>
</content>
</body>
</html>