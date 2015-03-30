<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang="ko">
<head>
    <title>인가 되지 않은 접근</title>
</head>
<body>
<section class="normal-white">
    <div class="container" style="padding-top: 10em;">
        <div class="col-sm-12 text-gray">
            <i class="fa fa-warning fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="U903"/><%--인가되지 않은 접근입니다--%></b></span>
        </div>
    </div>
</section>
</body>
</html>