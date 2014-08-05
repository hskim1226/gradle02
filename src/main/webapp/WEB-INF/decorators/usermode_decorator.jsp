<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><decorator:title default="연세대학교 대학원 입학 신청 시스템"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--<link rel="stylesheet" type="text/css" href="../css/isotope.css" media="screen" />-->
    <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/jquery.isotope/2.0.0/isotope.pkgd.min.js" media="screen" />
    <!--<link rel="stylesheet" href="../js/fancybox/jquery.fancybox.css" type="text/css" media="screen" />-->
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.pack.js" type="text/css" media="screen" />
    <!-- Bootstrap -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/css/bootstrapValidator.min.css"/>
    <!-- Optional theme -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- custom style -->
    <link rel="stylesheet" href="${contextPath}/css/style.css">
    <!-- skin -->
    <link rel="stylesheet" href="${contextPath}/skin/default.css">
    <decorator:head />
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
                <h1><a class="navbar-brand" href="/intro.jsp" data-0="line-height:90px;" data-300="line-height:50px;">			YonseiApply
                </a></h1>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav" data-0="margin-top:20px;" data-300="margin-top:5px;">
                    <li class="active"><a href="${contextPath}/intro.jsp">Home</a></li>
                    <li><a href="${contextPath}/notice/list">모집 공고</a></li>
                    <li><a href="${contextPath}/application/mylist">내 원서</a></li>
                    <li><a href="#section-contact">회원 가입</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <a href="${contextPath}/j_spring_security_logout.do">[<sec:authentication property="principal.username" />]sign out</a>
                        </sec:authorize>
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
                <div class="col-sm-12 legalnotice"><p>(주)에이펙스소프트 | 서울 마포구 양화로 156 | 대표이사 김도훈 | 사업자등록번호 105-87-66045 | 전화 ####-####</p></div>
                <div class="col-sm-12 legalnotice"><p>통신판매업신고번호 마포####호 | 개인정보관리책임자 김도훈 | 개인정보보유기간 회원 탈퇴시까지</p></div>
            </div>
            <div class="row align-center copyright">
                <div class="col-sm-12"><p>Designed by <a href="http://bootstraptaste.com">Bootstraptaste</a></p></div>
            </div>
        </div>
    </section>
    <a href="#header" class="scrollup"><i class="fa fa-chevron-up"></i></a>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>


    <script src="${contextPath}/js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery.isotope/2.0.0/isotope.pkgd.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery.nicescroll/3.5.1/jquery.nicescroll.min.js"></script>
    <script src="${contextPath}/js/fancybox/jquery.fancybox.pack.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/skrollr/0.6.26/skrollr.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-scrollTo/1.4.11/jquery.scrollTo.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-localScroll/1.3.5/jquery.localScroll.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/stellar.js/0.6.2/jquery.stellar.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery.appear/0.3.3/jquery.appear.min.js"></script>
    <script src="${contextPath}/js/validate.min.js"></script>
    <script src="${contextPath}/js/main.js"></script>
    <decorator:getProperty property="page.local-script"/>
</body>
</html>