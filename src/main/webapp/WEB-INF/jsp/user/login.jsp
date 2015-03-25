<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<!DOCTYPE html>
<html lang='ko'>
<head>
    <!-- BASICS -->
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="description" content="연세대학교 대학원">
    <title><spring:message code="L00081"/><%--로그인--%></title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty(\"path.static\")" />/css/bootstrap.min.css"/>
    <%--<link rel="stylesheet" href="<spring:eval expression="@app.getProperty('path.static')" />/css/bootstrap-theme.min.css"/>--%>
    <!-- Font-awesome -->
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty('path.static')" />/css/font-awesome.css"/>
    <!-- Overwrite Bootstrap -->
    <%--<link rel="stylesheet" href="<spring:eval expression="@app.getProperty('path.static')" />/css/overwrite.css"/>--%>
    <%--<link rel="stylesheet" href="<spring:eval expression="@app.getProperty('path.static')" />/css/animate.css"/>--%>
    <%--<!-- skin -->--%>
    <%--<link rel="stylesheet" href="<spring:eval expression="@app.getProperty('path.static')" />/skin/default.css">--%>
    <!-- custom style -->
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty('path.static')" />/css/style.css"/>
    <%--<link rel="stylesheet" href="<spring:eval expression="@app.getProperty('path.static')" />/css/layout.css"/>--%>
    <link rel="stylesheet" href="${contextPath}/static/css/layout.css"/>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src='<spring:eval expression="@app.getProperty(\"path.static\")" />/js/html5shiv.min.js'></script>
    <script src='<spring:eval expression="@app.getProperty(\"path.static\")" />/js/respond.min.js'></script>
    <script src='<spring:eval expression="@app.getProperty(\"path.static\")" />/js/html5.js'></script>
    <script src='<spring:eval expression="@app.getProperty(\"path.static\")" />/js/css3-mediaqueries.js'></script>
    <![endif]-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.min.js"></script>
    <style>
        body {
            padding-top: 0px;
        }
        section.login {
            padding: 100px 0 60px;
            /*background: #336655;*/
            background-image: url(<spring:eval expression="@app.getProperty('path.static')" />/img/yonsei-underwood.jpg);
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
                        <%--<img src="<spring:eval expression="@app.getProperty('path.static')" />/img/common/yonsei-logo01.png" align="center">--%>
                        <span style="font-size: 35px; vertical-align: middle; line-height:40px; color: black;"><b><spring:message code="L00001"/><%--연세대학교 대학원--%><br/><spring:message code="L00002"/><%--입학신청 시스템--%></b></span>
                    </div>
                    <div class="spacer-tiny col-sm-12">&nbsp;</div>
                    <div>
                        <div class="col-sm-offset-1 col-sm-10">
                            <input type="text" class="form-control logintext" id="username" name="j_username" maxlength="12" placeholder="User ID">
                        </div>
                    </div>
                    <div class="spacer-tiny col-sm-12">&nbsp;</div>
                    <div>
                        <div class="col-sm-offset-1 col-sm-10">
                            <input type="password" class="form-control logintext" id="password" name="j_password" maxlength="16" placeholder="Password">
                        </div>
                    </div>
                    <div class="spacer-tiny col-sm-12">&nbsp;</div>
                    <div>
                        <div class="col-sm-offset-1 col-sm-10">
                            <button class="btn btn-primary btn-lg btn-block btn-login"><spring:message code="L00089"/><%--로그인--%></button>
                        </div>
                    </div>
                    <%-- 로케일 설정을 로그인이 아닌 인트로에서 하기로 하여 아래 내용 제거 --%>
                    <%--<div class="spacer-tiny col-sm-12">&nbsp;</div>--%>
                    <%--<div>--%>
                        <%--<div class="col-sm-offset-1 col-sm-10">--%>
                            <%--<button class="btn btn-primary btn-lg btn-block btn-login" data-lang="ko"><spring:message code="L00087"/>&lt;%&ndash;로그인(한국어)&ndash;%&gt;</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="spacer-tiny col-sm-12">&nbsp;</div>                    --%>
                    <%--<div>--%>
                        <%--<div class="col-sm-offset-1 col-sm-10">--%>
                            <%--<button class="btn btn-success btn-lg btn-block btn-login" data-lang="en"><spring:message code="L00088"/>&lt;%&ndash;로그인(영어)&ndash;%&gt;</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>
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
<!-- footer -->
<footer>
    <section id="mainFooter">
        <div class="container">
            <div class="row">
                <div class="col-md-9">

                    <ul class="txt_list1">
                        <li><span>(주)에이펙스소프트</span></li>
                        <li>서울 마포구 양화로 156, 1121(동교동, 엘지팰리스)</li>
                        <li>대표이사 <span>김도훈</span></li>
                        <li>사업자등록번호 <span>105-87-66045</span></li>
                        <li>전화 <span>1899-1016</span></li>
                    </ul>

                    <ul class="txt_list1">
                        <li>통신판매업신고번호 <span>서울마포-1109호</span></li>
                        <li>개인정보관리책임자 <span>김도훈</span></li>
                        <li>개인정보보유기간 <span>회원 탈퇴시까지</span></li>
                    </ul>
                </div>
                <div class="col-md-3">
                    <p class="ft_right"><a href="http://bootstraptaste.com/" target="_blank" title="새창이동"><img src="<spring:eval expression="@app.getProperty('path.static')" />/img/footer_img1.png" alt="Designed by Bootstraptaste"></a></p>
                </div>
            </div>
        </div>
    </section>
</footer>
<!-- /footer -->
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
        // 로케일을 인트로에서 하기로 해서 아래 내용 제거
//        var lang = this.getAttribute('data-lang'),
//            form = document.getElementById('loginForm');
        <%--form.action = "${contextPath}/j_spring_security_check.do?lang=" + lang;--%>
        var form = document.getElementById('loginForm');
        form.action = "${contextPath}/j_spring_security_check.do";
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