<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<!DOCTYPE html>
<html lang='ko'>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 약관</title>
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
            padding: 200px 0 60px;
            background: #556699;
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
    </style>
</head>
<body>
<section class="signup">
    <div class="container">
        <div class="page-header">
            <h1 style="color: #fdfdfd">회원 가입</h1>
        </div>
        <form class="form-horizontal" role="form" id="sign-up-form" action="${contextPath}/user/signup" method="POST">
            <div class="form-group">
                <label for="terms-of-service" class="col-sm-2 control-label">연세어플라이 이용 약관에 대한 동의</label>
                <div class="col-sm-10">
                    <textarea id="terms-of-service" class="form-control" rows="10" readonly><%=request.getAttribute("terms-of-service")%></textarea>
                    <label for="terms-agree" class="control-label">
                        <input type="checkbox" name="userAgreYn" id="terms-agree" value="y" />&nbsp;&nbsp;동의합니다.
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label for="privacy-policy1" class="col-sm-2 control-label">개인 정보 수집 및 이용에 대한 동의</label>
                <div class="col-sm-10">
                    <textarea id="privacy-policy1" class="form-control" rows="10" readonly><%=request.getAttribute("privacy-policy1")%></textarea>
                    <textarea id="privacy-policy2" class="form-control" rows="10" readonly><%=request.getAttribute("privacy-policy2")%></textarea>
                    <textarea id="privacy-policy3" class="form-control" rows="10" readonly><%=request.getAttribute("privacy-policy3")%></textarea>
                    <label for="privacy-agree" class="control-label">
                        <input type="checkbox" name="privInfoYn" id="privacy-agree" value="y" />&nbsp;&nbsp;동의합니다.
                    </label>
                </div>
            </div>
        </form>
        <div class="col-sm-offset-2 col-sm-10">
            <button id="sign-up-button" class="btn btn-info btn-lg btn-block">계속하기</button>
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
<script src="${contextPath}/js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<script src="${contextPath}/js/jquery.easing.min.js"></script>
<script src="${contextPath}/js/jquery.nicescroll.min.js"></script>
<script src="${contextPath}/js/fancybox/jquery.fancybox.pack.js"></script>
<script src="${contextPath}/js/skrollr.min.js"></script>
<script src="${contextPath}/js/jquery.scrollTo.min.js"></script>
<script src="${contextPath}/js/jquery.localScroll.min.js"></script>
<script src="${contextPath}/js/jquery.appear.min.js"></script>
<script src="${contextPath}/js/jquery.stellar.min.js"></script>
<script src="${contextPath}/js/main.js"></script>

<script type="text/javascript">
$(document).ready(function(){

    $('#sign-up-button').on('click', function() {
        if ( !$("input:checkbox[id='terms-agree']").is(":checked") ) {
            alert("${msg1}");
            return;
        }

        if ( !$("input:checkbox[id='privacy-agree']").is(":checked") ) {
            alert("${msg2}");
            return;
        }
        $('#sign-up-form').submit();
    });
});
</script>

</body>
</html>