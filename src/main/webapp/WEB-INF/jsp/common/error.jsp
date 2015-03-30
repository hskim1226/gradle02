<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang="ko">
<head>
    <title>시스템 오류</title>
</head>
<body>
<section class="normal-white">
    <div class="container" style="padding-top: 10em;">
        <div class="col-sm-12 text-gray">
            <i class="fa fa-frown-o fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="U999"/><%--사용에 불편을 드려 죄송합니다--%></b></span>
        </div>
        <div class="spacer-small">&nbsp;</div>
        <div class="col-sm-offset-3 col-sm-6">
            <table class="table">
                <tr>
                    <td><p class="error-msg"><spring:message code="L00091"/></p></td>
                    <td><p class="error-msg">${ec.result}</p></td>
                </tr>
                <tr>
                    <td><p class="error-msg"><spring:message code="L00092"/></p></td>
                    <td><p class="error-msg">${ec.message}</p></td>
                </tr>
                <tr>
                    <td><p class="error-msg"><spring:message code="L00093"/></p></td>
                    <td><p class="error-msg">${ec.errCode}</p></td>
                </tr>
            </table>
        </div>
    </div>
</section>
</body>
</html>