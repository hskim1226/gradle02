<!DOCTYPE html>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
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
    <!--<link rel="stylesheet" href="../css/bootstrap.css">-->
    <!--<link rel="stylesheet" href="../css/bootstrap-theme.css">-->
    <!-- Bootstrap -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/css/bootstrapValidator.min.css"/>
    <!-- Optional theme -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
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
                <h1><a class="navbar-brand" href="intro.html" data-0="line-height:90px;" data-300="line-height:50px;">			YonseiApply
                </a></h1>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav" data-0="margin-top:20px;" data-300="margin-top:5px;">
                    <li class="active"><a href="intro.html">Home</a></li>
                    <li><a href="list.html">모집 공고</a></li>
                    <li><a href="mylist.html">내 원서</a></li>
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

            <div class="row align-center copyright">
                <div class="col-sm-12"><p>Copyright &copy; 2014 Amoeba - by <a href="http://bootstraptaste.com">Bootstraptaste</a></p></div>
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


    <script src="js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <script src="js/jquery.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.isotope.min.js"></script>
    <script src="js/jquery.nicescroll.min.js"></script>
    <script src="js/fancybox/jquery.fancybox.pack.js"></script>
    <script src="js/skrollr.min.js"></script>
    <script src="js/jquery.scrollTo-1.4.3.1-min.js"></script>
    <script src="js/jquery.localscroll-1.2.7-min.js"></script>
    <script src="js/stellar.js"></script>
    <script src="js/jquery.appear.js"></script>
    <script src="js/validate.js"></script>
    <script src="js/main.js"></script>
</body>
</html>