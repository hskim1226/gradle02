<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang='ko'>
<head>
    <title><spring:message code="L00121"/><%--회원 가입--%></title>
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
        .input-info {
            background: #ddddff;
            color: #55c;
        }
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <form:form class="form-horizontal" id="sign-up-form" commandName="user" action="${contextPath}/user/signup/save" method="post" role="form">
            <form:hidden path="userAgreYn" />
            <form:hidden path="privInfoYn" />
            <div class="col-md-offset-1 col-md-10">
                <div class="form-group inner-container-white">
                    <div class="col-sm-offset-1 col-sm-10 text-gray">
                        <i class="fa fa-user fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L00121"/><%--회원 가입--%></b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <%--user id--%>
                    <div class="form-group text-gray">
                        <div class="col-sm-offset-2 col-sm-8">
                            <div class="form-group col-sm-4 required">
                                <label for="userId" class="control-label"><spring:message code="L00122"/><%--아이디--%></label>
                            </div>
                            <div class="col-sm-8 nopadding">
                                <div class="input-group">
                                    <form:input type="text" cssClass="form-control userId" path="userId" maxlength="12" placeholder="${msg.getMessage('U00121')}" />  <%--입력 후 Check를 눌러 확인해주세요--%>
                                    <span class="input-group-btn">
                                        <button class="btn btn-info" id="available-check-button"><spring:message code="L00131"/><%--Check--%></button>
                                    </span>
                                </div>
                                <div class="input-info word-keep-all"><spring:message code="U00122"/></div>
                        <spring:bind path="userId">
                            <c:if test="${status.error}">
                                <div class="validation-error">${status.errorMessage}</div>
                            </c:if>
                        </spring:bind>
                            </div>
                        </div>
                    </div>
                    <%--password--%>
                    <div class="form-group text-gray">
                        <div class="col-sm-offset-2 col-sm-8">
                            <div class="form-group col-sm-4 required">
                                <label for="pswd1" class="control-label"><spring:message code="L00123"/><%--비밀번호--%></label>
                            </div>
                            <div class="col-sm-8 nopadding">
                                <div><form:input type="password" cssClass="form-control passwd" path="pswd" id="pswd1" maxlength="16" placeholder="${msg.getMessage('U00123')}"/></div>  <%--비밀 번호--%>
                                <div class="input-info word-keep-all"><spring:message code="U00124"/></div>
                        <spring:bind path="pswd">
                            <c:if test="${status.error}">
                                <div class="validation-error">${status.errorMessage}</div>
                            </c:if>
                        </spring:bind>
                            </div>
                        </div>
                    </div>
                    <div class="form-group text-gray">
                        <div class="col-sm-offset-2 col-sm-8">
                            <div class="form-group col-sm-4 required">
                                <label for="pswd2" class="control-label"><spring:message code="L00124"/><%--비밀번호 확인--%></label>
                            </div>
                            <div class="col-sm-8 nopadding">
                                <div><input type="password" class="form-control passwd" id="pswd2" maxlength="16" placeholder="${msg.getMessage('U00125')}" /></div>  <%--비밀 번호 확인--%>
                        <spring:bind path="pswd">
                            <c:if test="${status.error}">
                                <div class="validation-error">${status.errorMessage}</div>
                            </c:if>
                        </spring:bind>
                            </div>
                        </div>
                    </div>
                    <%--preferrence language--%>
                    <%--<div class="form-group text-gray">--%>
                        <%--<div class="col-sm-offset-2 col-sm-8">--%>
                            <%--<div class="form-group col-sm-4 required">--%>
                                <%--<label class="control-label"><spring:message code="L120" /></label>--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-8 nopadding">--%>
                                <%--<div>--%>
                                    <%--<div class="btn-group btn-group-justified" data-toggle="buttons">--%>
                                        <%--<label class="btn btn-default active">--%>
                                            <%--<input type="radio" name="prefLang" value="${app['lang.kr']}" checked /><spring:message code="L121" />--%>
                                        <%--</label>--%>
                                        <%--<label class="btn btn-default">--%>
                                            <%--<input type="radio" name="prefLang" value="${app['lang.en']}" /><spring:message code="L122" />--%>
                                        <%--</label>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<spring:bind path="prefLang">--%>
                                    <%--<c:if test="${status.error}">--%>
                                        <%--<div class="validation-error">${status.errorMessage}</div>--%>
                                    <%--</c:if>--%>
                                <%--</spring:bind>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--email--%>
                    <div class="form-group text-gray">
                        <div class="col-sm-offset-2 col-sm-8">
                            <div class="form-group col-sm-4 required">
                                <label for="mailAddr" class="control-label"><spring:message code="L00125"/><%--이메일--%></label>
                            </div>
                            <div class="col-sm-8 nopadding">
                                <div><form:input type="email" cssClass="form-control emailOnly" path="mailAddr" maxlength="50" placeholder="${msg.getMessage('U00126')}" /></div>  <%--이메일 주소를 입력해 주세요--%>
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
                            <div class="form-group col-sm-4">
                                <label for="mobiNum" class="control-label"><spring:message code="L00126"/><%--휴대폰--%></label>
                            </div>
                            <div class="col-sm-8 nopadding">
                                <div><form:input type="text" cssClass="form-control numOnly phone" path="mobiNum" maxlength="20" placeholder="${msg.getMessage('U00127')}" /></div>  <%--숫자로만 입력해 주세요--%>
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
                                <label for="name" class="control-label"><spring:message code="L00127"/><%--이름--%></label>
                            </div>
                            <div class="col-sm-8 nopadding">
                                <div><form:input type="text" cssClass="form-control" path="name" maxlength="20" placeholder="${msg.getMessage('U00128')}" /></div>  <%--실명을 입력해주세요--%>
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
                                <label class="control-label"><spring:message code="L00128"/><%--성별--%></label>
                            </div>
                            <div class="col-sm-8 nopadding">
                                <div>
                                    <div class="btn-group btn-group-justified" data-toggle="buttons">
                                        <label class="btn btn-default active">
                                            <input type="radio" name="gend" value="m" checked /><spring:message code="L114" />
                                        </label>
                                        <label class="btn btn-default">
                                            <input type="radio" name="gend" value="f" /><spring:message code="L115" />
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
                                <label for="bornDay" class="control-label"><spring:message code="L00129"/><%--생년월일--%></label>
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
                                <div class="btn-group col-sm-12" id="warn-id-check">
                                    <button id="btn-warn-id-check" class="btn btn-danger btn-lg"><spring:message code="U00129"/><%--ID 중복 체크를 해주세요.--%></button>
                                </div>
                            </div>
                            <div class="col-sm-12 btn-group btn-group-justified">
                                <div class="btn-group col-sm-12">
                                    <button id="sign-up-button" class="btn btn-primary btn-lg" disabled="disabled" ><spring:message code="L00130"/><%--회원 가입--%></button>
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
<script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery-ui.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        var isIDChecked = false;
        $("#sign-up-button").on("click", function(e){
//            $('#sign-up-form').bootstrapValidator('validate');
            e.preventDefault();
            var pswd1 = document.getElementById('pswd1'),
                pswd2 = document.getElementById('pswd2');
            if ( pswd1.value !== pswd2.value ) {
                alert("<spring:message code="U00130"/>"); <%--패스워드가 일치하지 않습니다--%>
                pswd1.focus();
                return;
            } else if (pswd1.value.length == 0) {
                alert("<spring:message code="U00124"/>"); <%--알파벳 대문자, 소문자, 숫자를 하나 이상 포함하여 8~16글자이어야 합니다.--%>
                pswd1.focus();
                return;
            } else if (pswd2.value.length == 0) {
                alert("<spring:message code="U00124"/>"); <%--알파벳 대문자, 소문자, 숫자를 하나 이상 포함하여 8~16글자이어야 합니다.--%>
                pswd2.focus();
                return;
            } else {
                apex.transKorPhoneNumber('phone');
                document.forms[0].action = "${contextPath}/user/signup/save";
                document.forms[0].submit();
            }
        });

        <%-- 아이디 체크 후 수정 시 재 체크 처리 --%>
        var setUnavailableID = function () {
            $("#sign-up-button").prop('disabled', true);
            isIDChecked = false;
            document.getElementById('btn-warn-id-check').style.display = 'block';
        };
        $('#userId').on('change', function(e) {
            if (isIDChecked) {
                setUnavailableID();
            }
        });
        <%-- 아이디 체크 후 수정 시 재 체크 처리 --%>

        <%-- 아이디 처리 --%>
        apex.idCheck('userId', '<spring:message code="U00122"/>');  <%--알파벳 대문자, 소문자, 숫자를 하나 이상 포함하여 6~12글자이어야 합니다.--%>
        <%-- 아이디 처리 --%>

        <%-- 아이디 중복 체크 --%>

        $("#available-check-button").on("click", function(e){
            e.preventDefault();
            var idValue = document.getElementById('userId').value;
            if (idValue.length > 5) {
                $.get("${contextPath}/user/idCheck",
                        $("#sign-up-form").serialize(),
                        function(data){
                            var container = JSON.parse(data);
                            if(container.result == "SUCCESS"){
                                alert("<spring:message code="U00131"/>");  <%--사용가능한 username 입니다.--%>
                                $("#sign-up-button").prop('disabled', false);
                                isIDChecked = true;
                                document.getElementById('btn-warn-id-check').style.display = 'none';
                                document.getElementById('pswd1').focus();
                            }else{
                                alert("<spring:message code="U00132"/>");  <%--이미 사용 중인 username 입니다.--%>
                                setUnavailableID();
                            }
                        }
                );
            } else {
                alert('<spring:message code="U00133"/>');  <%--아이디는 6자 이상이어야 합니다.--%>
            }
        });
        <%-- 아이디 중복 체크 --%>

        <%-- 비밀 번호 validation --%>
        apex.passwordCheck('passwd', '<spring:message code="U00124"/>');  <%--알파벳 대문자, 소문자, 숫자를 하나 이상 포함하여 8~16글자이어야 합니다.--%>
        <%-- 비밀 번호 validation --%>

        <%-- 메일 주소 validation --%>
        apex.emailCheck('emailOnly', '<spring:message code="U00134"/>');  <%--이메일 주소를 정확히 기재해 주세요--%>
        <%-- 메일 주소 validation --%>

        <%-- 숫자만 입력 - 주민번호, 휴대폰, 전화번호 --%>
        apex.numCheck('numOnly', '<spring:message code="U00135"/>');
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

        <%-- ID 중복 체크 경고 버튼 클릭 시 ID 입력란에 focus --%>
        $('#btn-warn-id-check').on('click', function(e) {
            e.preventDefault();
            $('#userId').focus();
        });
        <%-- ID 중복 체크 경고 버튼 클릭 시 ID 입력란에 focus --%>

        <%-- action 성공 여부 알림 처리 --%>
        var showActionResult = function() {
            var msg = '${resultMsg}';
            if (msg.length > 0) {
                confirm(msg);
            }
        };
        showActionResult();
        <%-- action 성공 여부 알림 처리 --%>

        <%-- 단어 잘림 방지 --%>
        $('.word-keep-all').wordBreakKeepAll();
    });
</script>
</content>
</body>
</html>