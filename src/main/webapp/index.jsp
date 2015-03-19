<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<%@ page import="org.springframework.web.util.WebUtils" %>
<%@ page import="org.springframework.web.servlet.i18n.SessionLocaleResolver" %>
<%@ page import="java.util.Locale" %>
<%--<%--%>
<%--//    Object localeObj = WebUtils.getSessionAttribute(request, SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);--%>
<%--//    if (localeObj == null || "".equals(localeObj)) {--%>
<%--WebUtils.setSessionAttribute(request, SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("kr"));--%>
<%--//    }--%>
<%--%>--%>
<c:if test="${WebUtils.getSessionAttribute(request, SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME) == null}">
    <fmt:setLocale value="ko_kr"/>
</c:if>
<!DOCTYPE html>
<html lang='ko'>
<head>
    <!-- BASICS -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code="L00001"/>&nbsp;<spring:message code="L00002"/><%--연세대학교 대학원 입학원서 접수 시스템--%></title>
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
    <meta name="description" content="연세대학교 대학원">
    <style>
        #toList, #toMyList, #toSignUp {
            cursor: pointer;
            cursor: hand;
        }

        #main-text-container {
            background-color: rgba(55, 55, 55, 0.8);
            filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#a7ffffff', endColorstr='#a7ffffff'); /* IE */
            padding-top: 10%;
            padding-bottom: 6%;
        }

        section.intro {
            padding: 200px 0 60px;
            background: #70B9B0;
            color: #fdfdfd;
        }

        section.intro h1.slogan {
            color: #fff;
            font-size: 64px;
            font-weight: 900;
        }

        section.intro h2.slogan {
            color: #fff;
            font-size: 48px;
            font-weight: 900;
        }

        /* inner heading */
        section.intro.inner {
            background: #eee;
            padding: 150px 0 50px;
        }

        section.intro .spacer-big {
            margin-bottom: 7em;
        }

        section.intro .spacer-mid {
            margin-bottom: 5em;
        }

        section.intro .spacer-small {
            margin-bottom: 3em;
        }

        section.intro .spacer-tiny {
            margin-bottom: 1em;
        }
    </style>
</head>

<body>
<section class="intro" id="intro" data-stellar-background-ratio="0.5">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-sm-offset-2 col-sm-8 align-center">
                <div class="form-group" id="main-text-container">
                    <h1 class="slogan"><spring:message code="L00001"/><%--연세대학교 대학원--%></h1>
                    <br/>
                    <h1 class="slogan"><spring:message code="L00002"/><%--입학원서 접수 시스템--%></h1>
                    <p>&nbsp;</p>
                    <span class="lang" data-lang="kr">Korean</span> | <span class="lang" data-lang="en">English</span>
                </div>
            </div>
        </div>
    </div>
</section>


<!-- services -->
<section id="section-services" class="section pad-bot30 bg-white">
    <div class="container">

        <div class="row mar-bot40">
            <div class="col-lg-4" >
                <div class="align-center" id="toList">
                    <i class="fa fa-pencil-square-o fa-5x mar-bot20"></i>
                    <h2 class="text-bold"><spring:message code="L00003"/><%--원서 접수--%></h2>
                    <p class="word-keep-all"><spring:message code="L00004"/><%--연세대학교 대학원 입학 신청서를 작성할 수 있습니다.--%>
                    </p>
                </div>
            </div>

            <div class="col-lg-4" >
                <div class="align-center" id="toMyList">
                    <i class="fa fa-list-alt fa-5x mar-bot20"></i>
                    <h2 class="text-bold"><spring:message code="L00005"/><%--내 원서--%></h2>
                    <p class="word-keep-all"><spring:message code="L00006"/><%--작성 중이거나 신청 중인 내 원서를 확인할 수 있습니다.--%>
                    </p>
                </div>
            </div>

            <div class="col-lg-4" >
                <div class="align-center" id="toSignUp">
                    <i class="fa fa-user fa-5x mar-bot20"></i>
                    <h2 class="text-bold"><spring:message code="L00007"/><%--회원 가입--%></h2>
                    <p class="word-keep-all"><spring:message code="L00008"/><%--연세대학교 대학원 입학 신청 시스템의 회원으로 가입할 수 있습니다.--%>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <form id="introForm">
        <input type="hidden" id="lang" name="lang"/>
    </form>
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
<script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.word-break-keep-all.min.js"></script>
<script src="<spring:eval expression="@app.getProperty('path.static')" />/js/main.js"></script>
<script>
    $('.lang').click( function() {
        var lang = this.getAttribute('data-lang'),
                form = document.getElementById('introForm'),
                hiddenLang = document.getElementById('lang');
        hiddenLang.value = lang;
        form.action = '${contextPath}/index';
        form.method = 'post';
        form.submit();
    });
    $('#toList').click(function(){location.href='${contextPath}/application/admsList'});
    $('#toMyList').click(function(){location.href='${contextPath}/application/mylist'});
    $('#toSignUp').click(function(){location.href='${contextPath}/user/agreement'});
    <%-- 단어 잘림 방지 --%>
    $('.word-keep-all').wordBreakKeepAll();
</script>
</body>
</html>
