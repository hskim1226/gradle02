<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang='ko'>
<head>
    <title>회원 가입</title>
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
                        <i class="fa fa-user fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b>회원 가입</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <%--user id--%>
                    <div class="form-group text-gray">
                        <div class="col-sm-offset-2 col-sm-8">
                            <div class="form-group col-sm-4">
                                <label for="userId" class="control-label"><spring:message code="L101" /></label>
                            </div>
                            <div class="col-sm-8 nopadding">
                                <div class="input-group">
                                    <form:input type="text" cssClass="form-control engName" path="userId" placeholder="알파벳 대소문자와 숫자로 입력해 주세요" />
                                    <span class="input-group-btn">
                                        <button class="btn btn-info" id="available-check-button">Check</button>
                                    </span>
                                </div>
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
                            <div class="form-group col-sm-4">
                                <label for="pswd1" class="control-label"><spring:message code="L102" /></label>
                            </div>
                            <div class="col-sm-8 nopadding">
                                <div><form:input type="password" cssClass="form-control passwd" path="pswd" id="pswd1" placeholder="Password"/></div>
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
                            <div class="form-group col-sm-4">
                                <label for="pswd2" class="control-label"><spring:message code="L117" /></label>
                            </div>
                            <div class="col-sm-8 nopadding">
                                <div><input type="password" class="form-control passwd" id="pswd2" placeholder="Password 확인" /></div>
                        <spring:bind path="pswd">
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
                            <div class="form-group col-sm-4">
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
                            <div class="form-group col-sm-4">
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
                            <div class="form-group col-sm-4">
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
                            <div class="form-group col-sm-4">
                                <label class="control-label"><spring:message code="L106" /></label>
                            </div>
                            <div class="col-sm-8 nopadding">
                                <div>
                                    <div class="btn-group btn-group-justified" data-toggle="buttons">
                                        <label class="btn btn-default active">
                                            <input type="radio" name="gend" id="gend[]" value="m" checked /><spring:message code="L114" />
                                        </label>
                                        <label class="btn btn-default">
                                            <input type="radio" name="gend" id="gend[]" value="f" /><spring:message code="L115" />
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
                            <div class="form-group col-sm-4">
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
                                <div class="btn-group col-sm-12">
                                    <button id="sign-up-button" class="btn btn-primary btn-lg" disabled="disabled" ><spring:message code="L116" /></button>
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
        $("#sign-up-button").on("click", function(e){
//            $('#sign-up-form').bootstrapValidator('validate');
            e.preventDefault();
            if ( document.getElementById('pswd1').value !== document.getElementById('pswd2').value ) {
                alert("패스워드가 일치하지 않습니다.");
                return;
            } else {
                $('.phone').each( function() {
                    this.value = this.value.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3");
                });
                document.forms[0].action = "${contextPath}/user/signup/save";
                document.forms[0].submit();
            }
        });

        <%-- 아이디 영대소문자 처리 시작 --%>
        $('.engName').on('keyup', function() {
            this.value = this.value.replace(/([^0-9A-Za-z])/g,"");
        });
        <%-- 아이디 영대소문자 처리 시작 --%>

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
                                alert("사용가능한 username 입니다.");
                                $("#sign-up-button").prop('disabled', false);
                            }else{
                                alert("이미 사용 중인 username 입니다.");
                                $("#sign-up-button").prop('disabled', true);
                            }
                        }
                );
            } else {
                alert('아이디는 6자 이상이어야 합니다.');
            }
        });
        <%-- 아이디 중복 체크 --%>

        <%-- 비밀 번호 validation --%>
        $('.passwd').on('blur', function () {
            var passwdRegExp = /^(?=\w{6,}$)(?=.*\d)(?=.*[A-Z]).*/,
                    val = this.value;
            if (!passwdRegExp.test(val) && val != '') {
                alert("비밀번호는 6자리 이상, 영 대/소문자와 숫자가 포함되어야 합니다.");
                this.value = "";
                this.focus();
            }
        });
        <%-- 비밀 번호 validation --%>

        <%-- 메일 주소 validation --%>
        $('.emailOnly').on('blur', function () {
            var emailRegExp = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
                    val = this.value;
            if (!emailRegExp.test(val) && val != '') {
                alert("이메일 주소를 정확히 기재해 주세요")
                this.value = "";
                this.focus();
            }
        });
        <%-- 메일 주소 validation --%>

        <%-- 숫자만 입력 - 주민번호, 휴대폰, 전화번호 --%>
        $('.numOnly').on('blur', function () {
            var numCheckRegExp = /^[0-9]*$/,
                    val = this.value;
            if (!numCheckRegExp.test(val) && val != '') {
                alert("형식에 맞게 정확히 기재해 주세요");
                this.value = "";
                this.focus();
            }
        });
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