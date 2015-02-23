<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>시스템 오류</title>
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
        section.application {
            padding: 160px 0 330px;
            background: #555555;
            color: #ddd;
            position:relative;
        }

        section.application h2 {
            color: #ddd;
            font-size: 36px;
            font-weight: 900;
        }

        section.application h3 {
            color: #ddd;
            font-size: 24px;
            font-weight: 300;
        }

        section.application .spacer-big {
            margin-bottom: 7em;
        }

        section.application .spacer-mid {
            margin-bottom: 5em;
        }

        section.application .spacer-small {
            height: 3em;
        }

        section.application .spacer-tiny {
            height: 1em;
        }
    </style>
</head>
<body>
<!-- HEADER -->
<section id="header" class="appear"></section>
<div class="navbar navbar-fixed-top" role="navigation" data-0="line-height:100px; height:100px; background-color:rgba(0,0,0,0.3);" data-300="line-height:60px; height:60px; background-color:rgba(0,0,0,1);">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="fa fa-bars color-white"></span>
            </button>
            <h1><a class="navbar-brand" href="${contextPath}" data-0="line-height:90px;" data-300="line-height:50px;"><img src="${contextPath}/img/common/yonsei-logo01.png"/></a></h1>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav" style="float: right;" data-0="margin-top:20px;" data-300="margin-top:5px;">
                <%--<div class="btn-group navbar-form navbar-left" data-toggle="buttons" role="locale">--%>
                <%--<label class="btn btn-link" data-target="ko"><input type="radio" />한글</label>--%>
                <%--<label class="btn btn-link" data-target="en"><input type="radio" />영어</label>--%>
                <%--</div>--%>
                <li class="active"><a href="${contextPath}">Home</a></li>
                <li><a href="${contextPath}/application/list">원서 접수</a></li>
                <li><a href="${contextPath}/application/mylist">내 원서</a></li>
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
<section class="application">
    <div class="container">
        <h2>사용에 불편을 드려 죄송합니다.</h2>
        <h3>요청하신 페이지가 존재하지 않습니다.</h3>
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

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${contextPath}/js/bootstrap.min.js"></script>
<script src="${contextPath}/js/bootstrapValidator.min.js"></script>
<script src="${contextPath}/js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<script src="${contextPath}/js/jquery.easing.min.js"></script>
<script src="${contextPath}/js/isotope.pkgd.min.js"></script>
<script src="${contextPath}/js/jquery.nicescroll.min.js"></script>
<script src="${contextPath}/js/fancybox/jquery.fancybox.pack.js"></script>
<script src="${contextPath}/js/skrollr.min.js"></script>
<script src="${contextPath}/js/jquery.scrollTo.min.js"></script>
<script src="${contextPath}/js/jquery.localScroll.min.js"></script>
<script src="${contextPath}/js/jquery.stellar.min.js"></script>
<script src="${contextPath}/js/jquery.appear.min.js"></script>
<script src="${contextPath}/js/jquery.bpopup.min.js"></script>
<script src="${contextPath}/js/jquery.form.min.js"></script>
<script src="${contextPath}/js/ajaxfileupload.js"></script>
<script src="${contextPath}/js/validate.min.js"></script>
<script src="${contextPath}/js/jquery.placeholder.js"></script>
<script src="${contextPath}/js/jquery.word-break-keep-all.min.js"></script>
<script src="${contextPath}/js/json2.js"></script>
<script src="${contextPath}/js/main.js"></script>
</body>
</html>