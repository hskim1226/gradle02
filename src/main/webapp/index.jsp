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
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty('path.static')" />/css/layout.css"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery.min.js"></script>
    <meta name="description" content="연세대학교 대학원">
</head>
<body data-spy="scroll" data-target="#scrollTarget" data-offset="150" style="background:#fff;padding-top:0;">
<!-- globalWrapper -->
<div id="globalWrapper" class="localscroll">

    <!-- main content -->
    <div id="paralaxSlice1" data-stellar-background-ratio="0.5">
        <div class="maskParent">
            <div class="paralaxMask"></div>
            <!--div class="paralaxText container">텍스트
            </div-->
        </div>
    </div>
    <section class="slice">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="mtit1"><spring:message code="L00001"/> <spring:message code="L00002"/><%--연세대학교 대학원 입학원서 접수 시스템--%></h1>
                    <h2 class="sub_tit1 fs100"><a href="#" class="pr15 lang" datg-lang="ko">Korean</a><img src="img/bu_vline.png" alt=""/><a href="#" class="pl15 lang" data-lang="en">English</a> </h2>
                </div>
                <article class="col-md-4">
                    <a href="#">
                        <section id="toList" class="main_box1">
                            <h2 class="mtit1"><spring:message code="L00003"/><%--원서접수--%></h2>
                            <p class="txt1 word-keep-all"><spring:message code="L00004"/><%--연세대학교 대학원 입학 신청서를 작성할 수 있습니다.--%></p>
                        </section>
                    </a>
                </article>
                <article class="col-md-4">
                    <a href="#">
                        <section id="toMyList" class="main_box1">
                            <h2 class="mtit1"><spring:message code="L00005"/><%--내 원서--%></h2>
                            <p class="txt1 word-keep-all"><spring:message code="L00006"/><%--작성 중이거나 신청 중인 내 원서를 확인할 수 있습니다.--%></p>
                        </section>
                    </a>
                </article>
                <article class="col-md-4">
                    <a href="#">
                        <section id="toSignUp" class="main_box1">
                            <h2 class="mtit1"><spring:message code="L00007"/><%--회원가입--%></h2>
                            <p class="txt1 word-keep-all"><spring:message code="L00008"/><%--연세대학교 대학원 입학 신청 시스템의 회원으로 가입할 수 있습니다.--%></p>
                        </section>
                    </a>
                </article>
            </div>
        </div>
        <form id="introForm">
            <input type="hidden" id="lang" name="lang"/>
        </form>
    </section>
    <!-- /main_content -->

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
                        <p class="ft_right"><a href="http://bootstraptaste.com/" target="_blank" title="새창이동"><img src="img/footer_img1.png" alt="Designed by Bootstraptaste"></a></p>
                    </div>
                </div>
            </div>
        </section>
    </footer>
    <!-- /footer -->
</div>
<!-- /global wrapper -->

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
