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
    <script src='<spring:eval expression="@app.getProperty(\"path.static\")" />/js/html5.js'></script>
    <script src='<spring:eval expression="@app.getProperty(\"path.static\")" />/js/css3-mediaqueries.js'></script>
    <script src='<spring:eval expression="@app.getProperty(\"path.static\")" />/js/respond.min.js'></script>
    <script src='<spring:eval expression="@app.getProperty(\"path.static\")" />/js/html5shiv.min.js'></script>
    <script src='<spring:eval expression="@app.getProperty(\"path.static\")" />/js/respond.min.js'></script>
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
    <%--<link rel="stylesheet" href="<spring:eval expression="@app.getProperty('path.static')" />/css/style.css"/>--%>
    <%--<link rel="stylesheet" href="<spring:eval expression="@app.getProperty('path.static')" />/css/layout.css"/>--%>
    <link rel="stylesheet" href="${contextPath}/static/css/style.css"/>
    <link rel="stylesheet" href="${contextPath}/static/css/layout.css"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.min.js"></script>
    <decorator:head />
</head>
<body data-spy="scroll" data-target="#scrollTarget" data-offset="150" style="background:#fff;">
<!-- globalWrapper -->
<div id="globalWrapper" class="localscroll">
    <!-- header -->
    <header id="mainHeader" class="navbar-fixed-top" role="banner">
        <div class="container">
            <nav class="navbar navbar-default scrollMenu" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"> <span class="sr-only">Toggle navigation</span> <img src="<spring:eval expression="@app.getProperty('path.static')" />/img/btn_menu1.png" alt="전체메뉴보기"> </button>
                    <a class="navbar-brand" href="${contextPath}"><img src="<spring:eval expression="@app.getProperty('path.static')" />/img/logo.png" alt="gradnet"></a>
                </div>
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <ul class="nav navbar-nav pull-right">
                        <li><a href="${contextPath}"><i class="fa fa-home"></i><spring:message code="L00009"/><%--Home--%></a> </li>
                        <li><a href="${contextPath}/application/admsList"><i class="fa fa-pencil-square-o"></i><spring:message code="L00003"/><%--원서접수--%></a> </li>
                        <li><a href="${contextPath}/application/mylist"><i class="fa fa-list"></i><spring:message code="L00005"/><%--내 원서--%></a> </li>
                        <li><a href="${contextPath}/user/info"><i class="fa fa-info-circle"></i><spring:message code="L00010"/><%--내 정보--%></a> </li>
                        <sec:authorize access="!isAuthenticated()">
                        <li><a href="${contextPath}/user/agreement"><i class="fa fa-user-plus"></i><spring:message code="L00083"/><%--회원가입--%></a> </li>
                        </sec:authorize>
                        <sec:authorize access="!isAuthenticated()">
                        <li><a href="${contextPath}/user/login"><i class="fa fa-sign-in"></i><spring:message code="L00081"/><%--로그인--%></a> </li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_USER')">
                        <li><a href="${contextPath}/j_spring_security_logout.do"><i class="fa fa-sign-out"></i><spring:message code="L00011"/><%--로그아웃--%></a> </li>
                        </sec:authorize>
                        <li><span class="nav_lang"><a href="#">&middot; Korean</a><a href="#">&middot; English</a></span></li>
                    </ul>
                </div>
            </nav>
        </div>
    </header>
    <!-- /header -->
    <decorator:body />
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
</div>
<!-- /global wrapper -->
    <a href="#header" class="scrollup"><i class="fa fa-chevron-up"></i></a>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/bootstrap.min.js"></script>
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
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/polyfill-console.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.word-break-keep-all.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/json2.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/main.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/apex.js"></script>

    <decorator:getProperty property="page.local-script"/>
    <script>
    <%-- placeholder polyfill --%>
    $('input, textarea').placeholder();
    <%-- placeholder polyfill --%>
    </script>
</body>
</html>