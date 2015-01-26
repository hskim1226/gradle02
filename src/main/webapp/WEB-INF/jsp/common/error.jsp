<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang="ko">
<head>
    <title>시스템 오류</title>
</head>
<body>
<h2>사용에 불편을 드려 죄송합니다.</h2>
<p>${ec.result}</p>
<p>${ec.message}</p>
<p>${ec.errCode}</p>
</body>
</html>