<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<!DOCTYPE html>
<html lang='ko'>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 가입</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${contextPath}/js/html5shiv.min.js"></script>
    <script src="${contextPath}/js/respond.min.js"></script>
    <![endif]-->
    <!--<link rel="stylesheet" type="text/css" href="../css/isotope.css" media="screen" />-->
    <!--<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/jquery.isotope/2.0.0/isotope.pkgd.min.js" media="screen" />-->
    <!--<link rel="stylesheet" href="../js/fancybox/jquery.fancybox.css" type="text/css" media="screen" />-->
    <!--<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.pack.js" type="text/css" media="screen" />-->
    <!-- Bootstrap -->
    <link rel="stylesheet" href="${contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${contextPath}/css/bootstrap-theme.min.css"/>
    <!-- Font-awesome -->
    <link rel="stylesheet" href="${contextPath}/css/font-awesome.css"/>
    <!-- Overwrite Bootstrap -->
    <link rel="stylesheet" href="${contextPath}/css/overwrite.css"/>
    <link rel="stylesheet" href="${contextPath}/css/animate.css"/>
    <!-- skin -->
    <link rel="stylesheet" href="${contextPath}/skin/default.css">
    <!-- custom style -->
    <link rel="stylesheet" href="${contextPath}/css/style.css"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${contextPath}/js/jquery.min.js"></script>
    <style>
        section.signup {
            padding: 50px 0 10px 0;
            background: #777777;
            color: #fdfdfd;
        }

        section.signup h2.slogan {
            color: #fff;
            font-size: 48px;
            font-weight: 900;
        }

        /* inner heading */
        section.signup.inner {
            background: #eee;
            padding: 150px 0 50px;
        }

        section.signup .spacer-big {
            margin-bottom: 7em;
        }

        section.signup .spacer-mid {
            margin-bottom: 5em;
        }

        section.signup .spacer-small {
            margin-bottom: 3em;
        }

        section.signup .spacer-tiny {
            margin-bottom: 1em;
        }

        section.signup div.btn-group>label.btn {
            max-width: none;
        }
        
        section.signup textarea.form-control[readonly] {
            cursor: default;
            resize: none;
            -moz-user-select: none;
            -webkit-user-select: none;
            -khtml-user-select: none;
            user-select: none;
        }
        .nopadding {
            padding: 0 !important;
            margin: 0 !important;
        }
        input[readonly] {
            background-color: white !important;
            cursor: text !important;
        }
    </style>
</head>
<body>
<section class="signup">
    <div class="container">
        <div class="page-header">
            <h1 style="color: #fdfdfd">회원 가입</h1>
        </div>
        <form class="form-horizontal" id="sign-up-form" action="${contextPath}/user/signup/save" method="post" role="form">
            <form:hidden path="users.userAgreYn" />
            <form:hidden path="users.privInfoYn" />
            <%--usertype--%>
            <div class="form-group">
                <label class="col-sm-4 control-label"><spring:message code="L100" /></label>
                <div class="col-sm-4">
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
            <div class="form-group">
                <label for="userId" class="col-sm-4 control-label"><spring:message code="L101" /></label>
                <div class="col-sm-4">
                    <div class="input-group">
                        <input type="text" class="form-control engName" name="userId" id="userId" placeholder="알파벳 대소문자와 숫자로 입력해 주세요"/>
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button" id="available-check-button">Check</button>
                        </span>
                    </div>
                </div>
            </div>
            <%--password--%>
            <div class="form-group">
                <label for="pswd1" class="col-sm-4 control-label"><spring:message code="L102" /></label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" name="pswd" id="pswd1" placeholder="Password"/>
                </div>
            </div>
            <div class="form-group">
                <label for="pswd2" class="col-sm-4 control-label"><spring:message code="L117" /></label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="pswd2" placeholder="Password 확인" />
                </div>
            </div>
            <%--email--%>
            <div class="form-group">
                <label for="mailAddr" class="col-sm-4 control-label"><spring:message code="L103" /></label>
                <div class="col-sm-4">
                    <input type="email" class="form-control emailOnly" name="mailAddr" id="mailAddr" placeholder="이메일 주소를 입력해 주세요" />
                </div>
                <label for="mailRecvYn" class="control-label">
                    <input type="checkbox" name="mailRecvYn" id="mailRecvYn" value="y"/><spring:message code="L112" />
                </label>
            </div>
            <%--mobiNum--%>
            <div class="form-group">
                <label for="mobiNum" class="col-sm-4 control-label"><spring:message code="L104" /></label>
                <div class="col-sm-4">
                    <input type="text" class="form-control numOnly" name="mobiNum" id="mobiNum" placeholder="숫자로만 입력해 주세요" />
                </div>
                <label for="smsRecvYn" class="control-label">
                    <input type="checkbox" name="smsRecvYn" id="smsRecvYn" value="y" /><spring:message code="L113" />
                </label>
            </div>
            <%--name--%>
            <div class="form-group">
                <label for="name" class="col-sm-4 control-label"><spring:message code="L105" /></label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" name="name" id="name" placeholder="실명을 입력해주세요" />
                </div>
            </div>
            <%--gend--%>
            <div class="form-group">
                <label class="col-sm-4 control-label"><spring:message code="L106" /></label>
                <div class="col-sm-4">
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
            <div class="form-group">
                <label for="bornDay" class="col-sm-4 control-label"><spring:message code="L107" /></label>
                <div class="col-sm-4">
                    <div class="input-group date">
                        <input type='text' id="bornDay" name="bornDay" class="form-control" readonly="true"/>
                        <span class="input-group-addon calendar-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>
                </div>
            </div>
            <%--submit--%>
            <div class="form-group">
                <label class="col-sm-4 control-label"></label>
                <div class="col-sm-4">
                    <div class="btn-group btn-group-justified">
                        <div class="btn-group">
                            <button id="sign-up-button" class="btn btn-primary btn-lg" disabled="disabled" ><spring:message code="L116" /></button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section>
<!-- FOOTER -->
<section id="footer" class="section footer">
    <div class="container">
        <div class="row align-center">
            <div class="col-sm-12 legalnotice"><p>(주)에이펙스소프트 | 서울 마포구 양화로 156, 1121(동교동, 엘지팰리스) | 대표이사 김도훈 | 사업자등록번호 105-87-66045 | 전화 <spring:eval expression="@app.getProperty('site.tel')" /></p></div>
            <div class="col-sm-12 legalnotice"><p>통신판매업신고번호 서울마포-1109호 | 개인정보관리책임자 김도훈 | 개인정보보유기간 회원 탈퇴시까지</p></div>
        </div>
        <div class="row align-center copyright">
            <div class="col-sm-12"><p>Designed by <a href="http://bootstraptaste.com" style="color: darkseagreen;">Bootstraptaste</a></p></div>
        </div>
    </div>
</section>
<a href="#header" class="scrollup"><i class="fa fa-chevron-up"></i></a>
<script src="${contextPath}/js/bootstrap.min.js"></script>
<script src="${contextPath}/js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<script src="${contextPath}/js/jquery.easing.min.js"></script>
<script src="${contextPath}/js/jquery.nicescroll.min.js"></script>
<script src="${contextPath}/js/fancybox/jquery.fancybox.pack.js"></script>
<script src="${contextPath}/js/skrollr.min.js"></script>
<script src="${contextPath}/js/jquery.scrollTo.min.js"></script>
<script src="${contextPath}/js/jquery.localScroll.min.js"></script>
<script src="${contextPath}/js/jquery.appear.min.js"></script>
<script src="${contextPath}/js/jquery.stellar.min.js"></script>
<script src="${contextPath}/js/jquery.placeholder.js"></script>
<script src="${contextPath}/js/main.js"></script>
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