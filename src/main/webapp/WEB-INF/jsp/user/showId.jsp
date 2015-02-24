<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang='ko'>
<head>
    <title>아이디 찾기</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
    <style>
        section.findInfo {
            padding: 150px 0 60px;
            /*background: #336655;*/
            <%--background-image: url(${contextPath}/img/common/login-bg1.jpg);--%>
            background-size: cover;
            color: #fdfdfd;
            min-height: 615px;
        }

        section.findInfo h2.slogan {
            color: #fff;
            font-size: 48px;
            font-weight: 900;
        }

        section.findInfo .spacer-big {
            margin-bottom: 7em;
        }

        section.findInfo .spacer-mid {
            margin-bottom: 5em;
        }

        section.findInfo .spacer-small {
            margin-bottom: 3em;
        }

        section.findInfo .spacer-tiny {
            margin-bottom: 0.5em;
        }

        .input-text {
            height: 50px;
            font-size: 100%;
            opacity: 1.0;
            margin-bottom: 5%;
        }

        .text-gray {
            color: #333333;
            opacity: 1.0;
            text-align: center;
        }

        .text-red {
            color: #5555dd;
            opacity: 1.0;
            text-align: center;
        }

        .nopadding {
            padding: 0 !important;
            margin: 0 !important;
        }

        input[readonly] {
            background-color: white !important;
            cursor: text !important;
        }

        .validation-error {
            background: #ffdddd;
            color: #f55;
        }

        .required .control-label:after {
            content:"*";
            color:red;
        }
    </style>
</head>
<body>
<section class="findInfo">
    <div class="container">
        <div class="form-horizontal">
            <div class="col-md-offset-3 col-md-6">
                <div class="form-group inner-container-white">
                    <div class="col-sm-offset-1 col-sm-10 text-gray">
                        <i class="fa fa-search fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b>아이디 찾기</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="form-group required">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-12 text-gray">회원님의 아이디는</div>
                            <div class="col-sm-12 text-red"><b>${userId}</b></div>
                            <div class="col-sm-12 text-gray">입니다</div>
                        <div class="col-sm-offset-1 col-sm-10">
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
<script src="${contextPath}/js/jquery-ui.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {

    <%-- 하단 버튼 처리 --%>
    var formProcess = function(event) {
        event.preventDefault();
        document.location.href='${contextPath}/user/login';
    };
    $('.btn-save').on('click', formProcess);
    <%-- 하단 버튼 처리 --%>

    <%-- 달력 옵션 --%>
    var datePickerOption = {
        dateFormat: 'yymmdd',
        yearRange: "1950:",
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        dayNamesMin: ['일','월','화','수','목','금','토'],
        changeMonth: true, //월변경가능
        changeYear: true, //년변경가능
        showMonthAfterYear: true //년 뒤에 월 표시
    };

    <%-- 달력 시작 --%>
    $('.input-group.date>input').datepicker(datePickerOption);
    $('.calendar-addon').on('click', function () {
        $(this.parentNode).children('input')[0].focus();
    });
    <%-- 달력 끝 --%>

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