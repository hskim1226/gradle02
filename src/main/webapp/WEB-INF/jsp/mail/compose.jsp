<%--
  Created by IntelliJ IDEA.
  User: hanmomhanda
  Date: 14. 7. 25
  Time: 오후 4:39
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>메일 작성</title>
</head>
<body>
<h1> 메일 작성 </h1>
<div>
    <form id="mail-form" action="${contextPath}/mail/send" method="post">
        제목<br/><input type="text" name="subject" /><p></p>
        수신<br/><input type="text" name="to" /><p></p>
        참조<br/><input type="text" name="cc" /><p></p>
        비밀참조<br/><input type="text" name="bc" /><p></p>
        내용<br/><textarea name="contents"></textarea>
    </form>
    <button id="mail-button"> 메일 보내기 </button>
</div>
</body>
</html>
