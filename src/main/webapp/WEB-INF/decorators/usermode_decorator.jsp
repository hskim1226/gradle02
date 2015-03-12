<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><decorator:title default="연세대학교 대학원 입학원서 접수 시스템"/></title>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/html5shiv.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/respond.min.js"></script>
    <![endif]-->
    <!--<link rel="stylesheet" type="text/css" href="../css/isotope.css" media="screen" />-->
    <!--<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/jquery.isotope/2.0.0/isotope.pkgd.min.js" media="screen" />-->
    <!--<link rel="stylesheet" href="../js/fancybox/jquery.fancybox.css" type="text/css" media="screen" />-->
    <!--<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.pack.js" type="text/css" media="screen" />-->
    <!-- Bootstrap -->
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty('path.static')" />/css/bootstrap.min.css"/>
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
    <decorator:head />
</head>
<body>
    <!-- HEADER -->
    <section id="header" class="appear"></section>
    <div class="navbar navbar-fixed-top" role="navigation" data-0="line-height:60px; height:60px; background-color:#555555;" data-300="line-height:60px; height:60px; background-color:#555555;">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="fa fa-bars color-white"></span>
                </button>
                <h1><a class="navbar-brand" href="${contextPath}" data-0="line-height:60px;" data-300="line-height:60px;"><img src="<spring:eval expression="@app.getProperty('path.static')" />/img/common/yonsei-logo02.png"/></a></h1>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav" style="float: right;" data-0="margin-top:5px;" data-300="margin-top:5px;">
                    <li class="active"><a href="${contextPath}">Home</a></li>
                    <li><a href="${contextPath}/application/admsList">원서 접수</a></li>
                    <li><a href="${contextPath}/application/mylist">내 원서</a></li>
                    <li><a href="${contextPath}/user/info">내 정보</a></li>
                    <sec:authorize access="!isAuthenticated()">
                        <li><a href="${contextPath}/user/agreement">회원 가입</a></li>
                    </sec:authorize>
                    <sec:authorize access="!isAuthenticated()">
                        <li><a href="${contextPath}/user/login">로그인</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <li><a href="${contextPath}/j_spring_security_logout.do">[<sec:authentication property="principal.name" />]sign out</a></li>
                    </sec:authorize>
                    <li>
                        <c:set var="clientLocale" value="${pageContext.response.locale}" />
                        ${clientLocale.displayLanguage}
                    </li>
                </ul>
            </div><!--/.navbar-collapse -->
        </div>
    </div>
    <decorator:body />
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

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/bootstrap.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/bootstrapValidator.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.easing.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/isotope.pkgd.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.nicescroll.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/fancybox/jquery.fancybox.pack.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/skrollr.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.scrollTo.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.localScroll.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.stellar.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.appear.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.bpopup.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.form.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/ajaxfileupload.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/validate.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.placeholder.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.word-break-keep-all.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/json2.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/main.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/apex.js"></script>
    <%--<script type="text/javascript">--%>
        <%--$(document).ready(function() {--%>
            <%--$('div[role=locale]').on('click', function(e) {--%>
                <%--var target = e.target, dataTarget, f, input;--%>
                <%--if (target) {--%>
                    <%--dataTarget = target.getAttribute('data-target');--%>
                    <%--if (dataTarget) {--%>
                        <%--&lt;%&ndash;location.href = UpdateQueryString('${localeParam}', dataTarget, location.href);&ndash;%&gt;--%>
                        <%--f = $('form')[0];--%>
                        <%--if (f) {--%>
                            <%--f.action = location.href;--%>
                            <%--input = document.createElement('input');--%>
                            <%--input.setAttribute('type', 'hidden');--%>
                            <%--input.setAttribute('name', '${localeParam}')--%>
                            <%--input.setAttribute('value', dataTarget);--%>
                            <%--f.appendChild(input);--%>
                            <%--f.submit();--%>
                        <%--}--%>
                    <%--}--%>
                <%--}--%>
            <%--});--%>

            <%--function UpdateQueryString(key, value, url) {--%>
                <%--if (!url) url = window.location.href;--%>
                <%--var re = new RegExp("([?&])" + key + "=.*?(&|#|$)(.*)", "gi");--%>

                <%--if (re.test(url)) {--%>
                    <%--if (typeof value !== 'undefined' && value !== null)--%>
                        <%--return url.replace(re, '$1' + key + "=" + value + '$2$3');--%>
                    <%--else {--%>
                        <%--var hash = url.split('#');--%>
                        <%--url = hash[0].replace(re, '$1$3').replace(/(&|\?)$/, '');--%>
                        <%--if (typeof hash[1] !== 'undefined' && hash[1] !== null)--%>
                            <%--url += '#' + hash[1];--%>
                        <%--return url;--%>
                    <%--}--%>
                <%--}--%>
                <%--else {--%>
                    <%--if (typeof value !== 'undefined' && value !== null) {--%>
                        <%--var separator = url.indexOf('?') !== -1 ? '&' : '?',--%>
                                <%--hash = url.split('#');--%>
                        <%--url = hash[0] + separator + key + '=' + value;--%>
                        <%--if (typeof hash[1] !== 'undefined' && hash[1] !== null)--%>
                            <%--url += '#' + hash[1];--%>
                        <%--return url;--%>
                    <%--}--%>
                    <%--else--%>
                        <%--return url;--%>
                <%--}--%>
            <%--}--%>
        <%--});--%>
    <%--</script>--%>
    <decorator:getProperty property="page.local-script"/>
    <script>
    <%-- placeholder polyfill --%>
    $('input, textarea').placeholder();
    <%-- placeholder polyfill --%>
    </script>
</body>
</html>