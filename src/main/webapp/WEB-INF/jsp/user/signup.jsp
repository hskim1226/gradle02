<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<!DOCTYPE html>
<html lang='ko'>
<head>
    <title>회원 가입</title>
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
        <form class="form-horizontal" id="sign-up-form" action="${contextPath}/user/signup/save" method="post" role="form">
            <form:hidden path="users.userAgreYn" />
            <form:hidden path="users.privInfoYn" />
            <div class="col-md-offset-2 col-md-8">
                <div class="form-group inner-container-white">
                    <div class="col-sm-offset-1 col-sm-10 text-gray">
                        <i class="fa fa-user fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b>회원 가입</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <%--usertype--%>
                    <div class="form-group text-gray">
                        <label class="col-sm-4 control-label"><spring:message code="L100" /></label>
                        <div class="col-sm-6">
                            <div class="btn-group btn-group-justified" data-toggle="buttons">
                                <label class="btn btn-default active">
                                    <input type="radio" name="userType" id="usertype[]" value="g" checked /><spring:message code="L108" />
                                </label>
                                <%--<label class="btn btn-default">--%>
                                <%--<input type="radio" name="userType" id="usertype[]" value="c" /><spring:message code="L109" />--%>
                                <%--</label>--%>
                                <label class="btn btn-default">
                                    <input type="radio" name="userType" id="usertype[]" value="f" /><spring:message code="L110" />
                                </label>
                            </div>
                        </div>
                    </div>
                    <%--user id--%>
                    <div class="form-group text-gray">
                        <label for="userId" class="col-sm-4 control-label"><spring:message code="L101" /></label>
                        <div class="col-sm-6">
                            <div class="input-group">
                                <input type="text" class="form-control engName" name="userId" id="userId" placeholder="알파벳 대소문자와 숫자로 입력해 주세요"/>
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button" id="available-check-button">Check</button>
                        </span>
                            </div>
                        </div>
                    </div>
                    <%--password--%>
                    <div class="form-group text-gray">
                        <label for="pswd1" class="col-sm-4 control-label"><spring:message code="L102" /></label>
                        <div class="col-sm-6">
                            <input type="password" class="form-control" name="pswd" id="pswd1" placeholder="Password"/>
                        </div>
                    </div>
                    <div class="form-group text-gray">
                        <label for="pswd2" class="col-sm-4 control-label"><spring:message code="L117" /></label>
                        <div class="col-sm-6">
                            <input type="password" class="form-control" id="pswd2" placeholder="Password 확인" />
                        </div>
                    </div>
                    <%--email--%>
                    <div class="form-group text-gray">
                        <label for="mailAddr" class="col-sm-4 control-label"><spring:message code="L103" /></label>
                        <div class="col-sm-6">
                            <input type="email" class="form-control emailOnly" name="mailAddr" id="mailAddr" placeholder="이메일 주소를 입력해 주세요" />
                        </div>
                        <%--<label for="mailRecvYn" class="control-label">--%>
                            <%--<input type="checkbox" name="mailRecvYn" id="mailRecvYn" value="y"/><spring:message code="L112" />--%>
                        <%--</label>--%>
                    </div>
                    <%--mobiNum--%>
                    <div class="form-group text-gray">
                        <label for="mobiNum" class="col-sm-4 control-label"><spring:message code="L104" /></label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control numOnly" name="mobiNum" id="mobiNum" placeholder="숫자로만 입력해 주세요" />
                        </div>
                        <%--<label for="smsRecvYn" class="control-label">--%>
                            <%--<input type="checkbox" name="smsRecvYn" id="smsRecvYn" value="y" /><spring:message code="L113" />--%>
                        <%--</label>--%>
                    </div>
                    <%--name--%>
                    <div class="form-group text-gray">
                        <label for="name" class="col-sm-4 control-label"><spring:message code="L105" /></label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="name" id="name" placeholder="실명을 입력해주세요" />
                        </div>
                    </div>
                    <%--gend--%>
                    <div class="form-group text-gray">
                        <label class="col-sm-4 control-label"><spring:message code="L106" /></label>
                        <div class="col-sm-6">
                            <div class="btn-group btn-group-justified" data-toggle="buttons">
                                <label class="btn btn-default active">
                                    <input type="radio" name="gend" id="gend[]" value="m" checked /><spring:message code="L114" />
                                </label>
                                <label class="btn btn-default">
                                    <input type="radio" name="gend" id="gend[]" value="f" /><spring:message code="L115" />
                                </label>
                            </div>
                        </div>
                    </div>
                    <%--calendar--%>
                    <div class="form-group text-gray">
                        <label for="bornDay" class="col-sm-4 control-label"><spring:message code="L107" /></label>
                        <div class="col-sm-6">
                            <div class="input-group date">
                                <input type='text' id="bornDay" name="bornDay" class="form-control" readonly="true"/>
                                <span class="input-group-addon calendar-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <%--submit--%>
                    <div class="form-group">
                        <label class="col-sm-4 control-label"></label>
                        <div class="col-sm-6">
                            <div class="btn-group btn-group-justified">
                                <div class="btn-group">
                                    <button id="sign-up-button" class="btn btn-primary btn-lg" disabled="disabled" ><spring:message code="L116" /></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section>
<script type="text/javascript">
    $(document).ready(function(){
        $("#sign-up-button").on("click", function(e){
//            $('#sign-up-form').bootstrapValidator('validate');
            e.preventDefault();
            if ( document.getElementById('pswd1').value !== document.getElementById('pswd2').value ) {
                alert("패스워드가 일치하지 않습니다.");
                return;
            } else {
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
        $("#available-check-button").on("click", function(){
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
        });
        <%-- 아이디 중복 체크 --%>

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
        $('.numOnly').on('keyup', function () {
            var numCheckRegExp = /^[0-9]*$/,
                    val = this.value;
            if (!numCheckRegExp.test(val)) {
                this.value = val.substr(0, val.length-1);
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

        <%-- placeholder polyfill --%>
        $('input, textarea').placeholder();
        <%-- placeholder polyfill --%>
    });
</script>

</body>
</html>