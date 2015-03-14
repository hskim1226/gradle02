<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<!DOCTYPE html>
<html lang='ko'>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code="L00081"/><%--로그인--%></title>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src='<spring:eval expression="@app.getProperty(\"path.static\")" />/js/html5shiv.min.js'></script>
    <script src='<spring:eval expression="@app.getProperty(\"path.static\")" />/js/respond.min.js'></script>
    <![endif]-->
    <!--<link rel="stylesheet" type="text/css" href="../css/isotope.css" media="screen" />-->
    <!--<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/jquery.isotope/2.0.0/isotope.pkgd.min.js" media="screen" />-->
    <!--<link rel="stylesheet" href="../js/fancybox/jquery.fancybox.css" type="text/css" media="screen" />-->
    <!--<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.pack.js" type="text/css" media="screen" />-->
    <!-- Bootstrap -->
    <link rel="stylesheet" href="${contextPath}/static/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty('path.static')" />/css/bootstrap-theme.min.css"/>
    <!-- Font-awesome -->
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty('path.static')" />/css/font-awesome.css"/>
    <!-- Overwrite Bootstrap -->
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty('path.static')" />/css/overwrite.css"/>
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty('path.static')" />/css/animate.css"/>
    <!-- skin -->
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty('path.static')" />/skin/default.css">
    <!-- custom style -->
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty('path.static')" />/css/style.css"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.min.js"></script>
    <style>
        section.login {
            padding: 200px 0 60px;
            /*background: #336655;*/
            background-image: url(<spring:eval expression="@app.getProperty('path.static')" />/img/common/login-bg1.jpg);
            background-size: cover;
            color: #fdfdfd;
            min-height: 615px;
        }

        section.login h2.slogan {
            color: #fff;
            font-size: 48px;
            font-weight: 900;
        }

        /* inner heading */
        section.login.inner {
            background: #eee;
            padding: 150px 0 50px;
        }

        section.login .spacer-big {
            margin-bottom: 7em;
        }

        section.login .spacer-mid {
            margin-bottom: 5em;
        }

        section.login .spacer-small {
            margin-bottom: 3em;
        }

        section.login .spacer-tiny {
            margin-bottom: 0.2em;
        }

        #login-form-container {
            background-color: rgba(255, 255, 255, 0.7);
            filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#a7ffffff', endColorstr='#a7ffffff'); /* IE */
            padding-top: 10%;
            padding-bottom: 6%;
        }

        .logintext {
            height: 50px;
            font-size: 100%;
            opacity: 1.0;
        }

        .text-gray {
            color: #333333;
            opacity: 1.0;
            text-align: center;
        }
        .text-warning {
            color: #ff7777;
            text-align: center;
        }
        #logo-container {
            text-align: center;
            margin-bottom: 5%;
        }


    </style>
</head>
<body>
<section class="login">
    <div class="container">
        <div class="col-md-offset-3 col-md-6">
            <form class="form-horizontal" role="form" id="loginForm" name="user" method="post">
                <div class="form-group" id="login-form-container">
                    <div class="col-sm-offset-1 col-sm-10" id="logo-container">
                        <img src="<spring:eval expression="@app.getProperty('path.static')" />/img/common/yonsei-logo01.png" align="center">
                    </div>
                    <div class="spacer-tiny col-sm-12">&nbsp;</div>
                    <div>
                        <div class="col-sm-offset-1 col-sm-10">
                            <input type="text" class="form-control logintext" id="username" name="j_username" placeholder="User ID">
                        </div>
                    </div>
                    <div class="spacer-tiny col-sm-12">&nbsp;</div>
                    <div>
                        <div class="col-sm-offset-1 col-sm-10">
                            <input type="password" class="form-control logintext" id="password" name="j_password" placeholder="Password">
                        </div>
                    </div>
                    <div class="spacer-tiny col-sm-12">&nbsp;</div>
                    <div>
                        <div class="col-sm-offset-1 col-sm-10">
                            <button class="btn btn-primary btn-lg btn-block btn-login" data-lang="ko"><spring:message code="L00087"/><%--로그인(한국어)--%></button>
                        </div>
                    </div>
                    <div class="spacer-tiny col-sm-12">&nbsp;</div>
                    <div>
                        <div class="col-sm-offset-1 col-sm-10">
                            <button class="btn btn-success btn-lg btn-block btn-login" data-lang="en"><spring:message code="L00088"/><%--로그인(영어)--%></button>
                        </div>
                    </div>
                    <c:if test="${loginMessage.length() > 0}">
                        <div class="spacer-tiny col-sm-12">&nbsp;</div>
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="panel panel-danger text-warning">${loginMessage}</div>
                        </div>
                    </c:if>
                    <div class="spacer-tiny col-sm-12">&nbsp;</div>
                    <div class="col-sm-offset-1 col-sm-10">
                        <div class="text-gray"><spring:message code="L00082"/><%--아직 회원이 아니세요?--%> <a href="${contextPath}/user/agreement"><spring:message code="L00083"/><%--회원 가입--%></a></div>
                    </div>
                    <div class="spacer-tiny col-sm-12">&nbsp;</div>
                    <div class="col-sm-offset-1 col-sm-10">
                        <div class="text-gray"><spring:message code="L00084"/><%--아이디/비밀번호를 잊으셨나요?--%> <a href="${contextPath}/user/findId"><spring:message code="L00085"/><%--아이디 찾기--%></a>&nbsp;<a href="${contextPath}/user/findPwd"><spring:message code="L00086"/><%--비밀번호 찾기--%></a></div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
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
<script src="<spring:eval expression="@app.getProperty('path.static')" />/js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<script src="<spring:eval expression="@app.getProperty('path.static')" />/js/bootstrap.min.js"></script>
<script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.easing.min.js"></script>
<script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.nicescroll.min.js"></script>
<script src="<spring:eval expression="@app.getProperty('path.static')" />/js/fancybox/jquery.fancybox.pack.js"></script>
<script src="<spring:eval expression="@app.getProperty('path.static')" />/js/skrollr.min.js"></script>
<script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.scrollTo.min.js"></script>
<script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.localScroll.min.js"></script>
<script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.appear.min.js"></script>
<script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.stellar.min.js"></script>
<script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.placeholder.js"></script>
<script src="<spring:eval expression="@app.getProperty('path.static')" />/js/main.js"></script>

<script type="text/javascript">
$(document).ready(function() {

    $('.btn-login').on('click', function(e) {
        e.preventDefault();
        var lang = this.getAttribute('data-lang'),
            form = document.getElementById('loginForm');
        form.action = "${contextPath}/j_spring_security_check.do?lang=" + lang;
        form.submit();
    });
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

</body>
</html>