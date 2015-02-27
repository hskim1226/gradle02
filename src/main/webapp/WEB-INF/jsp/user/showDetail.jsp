<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang='ko'>
<head>
    <title>회원 정보</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
    <style>
        section.normal-white div.btn-group>label.btn {
            max-width: none;
        }
        
        section.normal-white textarea.form-control[readonly] {
            cursor: default;
            resize: none;
            -moz-user-select: none;
            -webkit-user-select: none;
            -khtml-user-select: none;
            user-select: none;
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
        <form:form class="form-horizontal" id="sign-up-form" commandName="users" action="${contextPath}/user/signup/save" method="post" role="form">
            <form:hidden path="userAgreYn" />
            <form:hidden path="privInfoYn" />
            <div class="col-md-offset-2 col-md-8">
                <div class="form-group inner-container-white">
                    <div class="col-sm-offset-1 col-sm-10 text-gray">
                        <i class="fa fa-user fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b>회원 정보</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <%--user id--%>
                    <div class="form-group text-gray">
                        <div class="col-sm-offset-2 col-sm-8">
                            <div class="form-group col-sm-4">
                                <label for="userId" class="control-label"><spring:message code="L101" /></label>
                            </div>
                            <div class="col-sm-8 nopadding">
                                <div>
                                    <h4>${users.userId}</h4>
                                    <form:hidden path="userId" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--preferrence language--%>
                    <div class="form-group text-gray">
                        <div class="col-sm-offset-2 col-sm-8">
                            <div class="form-group col-sm-4 required">
                                <label class="control-label"><spring:message code="L120" /></label>
                            </div>
                            <div class="col-sm-8 nopadding">
                                <div>
                                    <div class="btn-group btn-group-justified" data-toggle="buttons">
                                        <label class="btn btn-default ${users.prefLang == app['lang.kr']?'active':''}">
                                            <input type="radio" name="prefLang" value="${app['lang.kr']}" ${users.prefLang == app['lang.kr']?'checked':''} /><spring:message code="L121" />
                                        </label>
                                        <label class="btn btn-default ${users.prefLang == app['lang.en']?'active':''}">
                                            <input type="radio" name="prefLang" value="${app['lang.en']}" ${users.prefLang == app['lang.en']?'checked':''} /><spring:message code="L122" />
                                        </label>
                                    </div>
                                </div>
                                <spring:bind path="prefLang">
                                    <c:if test="${status.error}">
                                        <div class="validation-error">${status.errorMessage}</div>
                                    </c:if>
                                </spring:bind>
                            </div>
                        </div>
                    </div>
                    <%--email--%>
                    <div class="form-group text-gray">
                        <div class="col-sm-offset-2 col-sm-8">
                            <div class="form-group col-sm-4 required">
                                <label for="mailAddr" class="control-label"><spring:message code="L103" /></label>
                            </div>
                            <div class="col-sm-8 nopadding">
                                <div><form:input type="email" cssClass="form-control emailOnly" path="mailAddr" placeholder="이메일 주소를 입력해 주세요" /></div>
                        <spring:bind path="mailAddr">
                            <c:if test="${status.error}">
                                <div class="validation-error">${status.errorMessage}</div>
                            </c:if>
                        </spring:bind>
                            </div>
                        </div>
                    </div>
                    <%--mobiNum--%>
                    <div class="form-group text-gray">
                        <div class="col-sm-offset-2 col-sm-8">
                            <div class="form-group col-sm-4 required">
                                <label for="mobiNum" class="control-label"><spring:message code="L104" /></label>
                            </div>
                            <div class="col-sm-8 nopadding">
                                <div><form:input type="text" cssClass="form-control numOnly phone" path="mobiNum" placeholder="숫자로만 입력해 주세요" /></div>
                        <spring:bind path="mobiNum">
                            <c:if test="${status.error}">
                                <div class="validation-error">${status.errorMessage}</div>
                            </c:if>
                        </spring:bind>
                            </div>
                        </div>
                    </div>
                    <%--name--%>
                    <div class="form-group text-gray">
                        <div class="col-sm-offset-2 col-sm-8">
                            <div class="form-group col-sm-4 required">
                                <label for="name" class="control-label"><spring:message code="L105" /></label>
                            </div>
                            <div class="col-sm-8 nopadding">
                                <div><form:input type="text" cssClass="form-control" path="name" placeholder="실명을 입력해주세요" /></div>
                        <spring:bind path="name">
                            <c:if test="${status.error}">
                                <div class="validation-error">${status.errorMessage}</div>
                            </c:if>
                        </spring:bind>
                            </div>
                        </div>
                    </div>
                    <%--gend--%>
                    <div class="form-group text-gray">
                        <div class="col-sm-offset-2 col-sm-8">
                            <div class="form-group col-sm-4 required">
                                <label class="control-label"><spring:message code="L106" /></label>
                            </div>
                            <div class="col-sm-8 nopadding">
                                <div>
                                    <div class="btn-group btn-group-justified" data-toggle="buttons">
                                        <label class="btn btn-default ${users.gend == 'm'?'active':''}">
                                            <input type="radio" name="gend" value="m" ${users.gend == 'm'?'checked':''} /><spring:message code="L114" />
                                        </label>
                                        <label class="btn btn-default ${users.gend == 'f'?'active':''}">
                                            <input type="radio" name="gend" value="f" ${users.gend == 'f'?'checked':''} /><spring:message code="L115" />
                                        </label>
                                    </div>
                                </div>
                        <spring:bind path="gend">
                            <c:if test="${status.error}">
                                <div class="validation-error">${status.errorMessage}</div>
                            </c:if>
                        </spring:bind>
                            </div>
                        </div>
                    </div>
                    <%--calendar--%>
                    <div class="form-group text-gray">
                        <div class="col-sm-offset-2 col-sm-8">
                            <div class="form-group col-sm-4 required">
                                <label for="bornDay" class="control-label"><spring:message code="L107" /></label>
                            </div>
                            <div class="col-sm-8 nopadding">
                                <div>
                                    <div class="input-group date">
                                        <form:input path="bornDay" cssClass="form-control" readonly="true"/>
                                        <span class="input-group-addon calendar-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                        <spring:bind path="bornDay">
                            <c:if test="${status.error}">
                                <div class="validation-error">${status.errorMessage}</div>
                            </c:if>
                        </spring:bind>
                            </div>
                        </div>
                    </div>
                    <%--submit--%>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-8">
                            <div class="col-sm-12 btn-group btn-group-justified">
                                <div class="btn-group col-sm-6">
                                    <button id="modify" class="btn btn-primary btn-lg"><spring:message code="L118" /></button>
                                </div>
                                <div class="btn-group col-sm-6">
                                    <button id="change-pwd" class="btn btn-info btn-lg"><spring:message code="L119" /></button>
                                </div>
                            </div>
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
    $(document).ready(function(){
        $("#modify").on("click", function(e){
            e.preventDefault();

            apex.transKorPhoneNumber('phone');

            document.forms[0].action = "${contextPath}/user/modify";
            document.forms[0].submit();

        });

        $("#change-pwd").on("click", function(e){
            e.preventDefault();

            document.forms[0].action = "${contextPath}/user/changePwd";
            document.forms[0].submit();

        });

        <%-- 메일 주소 validation --%>
        apex.emailCheck('emailOnly');
        <%-- 메일 주소 validation --%>



        <%-- 숫자만 입력 - 주민번호, 휴대폰, 전화번호 --%>
        apex.numCheck('numOnly');
        <%-- 숫자만 입력 - 주민번호, 휴대폰, 전화번호 --%>

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
        <%-- 달력 옵션 --%>

        <%-- 달력 시작 --%>
        $('.input-group.date>input').datepicker(datePickerOption);
        $('.calendar-addon').on('click', function () {
            $(this.parentNode).children('input')[0].focus();
        });
        <%-- 달력 끝 --%>

        <%-- 달력 reset 함수 --%>
        var resetCalendar = function (block, calendarClass) {
            $(block).find(calendarClass).datepicker('destroy');
            $(block).find(calendarClass).datepicker(datePickerOption);
        };
        <%-- 달력 reset 함수 --%>

        <%-- action 성공 여부 알림 처리 --%>
        var showActionResult = function() {
            var msg = '${resultMsg}';
            if (msg.length > 0) {
                confirm(msg);
            }
        };
        showActionResult();
        <%-- action 성공 여부 알림 처리 --%>
    });
</script>
</content>
</body>
</html>