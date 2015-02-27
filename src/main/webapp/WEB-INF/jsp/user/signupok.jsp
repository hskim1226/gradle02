<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang='ko'>
<head>
    <title>회원 가입 성공</title>
    <style>
        .input-text {
            height: 50px;
            font-size: 100%;
            opacity: 1.0;
            margin-bottom: 5%;
        }
        .text-red {
            color: #5555dd;
            opacity: 1.0;
            text-align: center;
        }
        input[readonly] {
            background-color: white !important;
            cursor: text !important;
        }
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <div class="form-horizontal">
            <div class="col-md-offset-3 col-md-6">
                <div class="form-group inner-container-white">
                    <div class="col-sm-offset-1 col-sm-10 text-gray">
                        <i class="fa fa-graduation-cap fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b>환영합니다</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="form-group required">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-12 text-gray"><h3>회원 가입을 축하드립니다.</h3></div>
                        </div>
                    </div>
                    <div class="col-sm-offset-1 col-sm-10">
                        <div class="col-sm-12 nopadding">
                            <button class="btn btn-primary btn-lg btn-block btn-save input-text">로그인</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<content tag="local-script">
<script type="text/javascript">
$(document).ready(function() {

    <%-- 하단 버튼 처리 --%>
    var formProcess = function(event) {
        event.preventDefault();
        document.location.href='${contextPath}/user/login';
    };
    $('.btn-save').on('click', formProcess);
    <%-- 하단 버튼 처리 --%>
});
</script>
</content>
</body>
</html>