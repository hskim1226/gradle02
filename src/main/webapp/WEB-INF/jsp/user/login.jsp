<%--
  Created by IntelliJ IDEA.
  User: skplanet
  Date: 2014. 7. 20.
  Time: 오후 6:19
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
    <h1> Login </h1>
    <div>
        <form action="${contextPath}/j_spring_security_check.do" method="post">
            <input type="text" name="j_username" />
            <input type="password" name="j_password" />
            <button> login </button>
        </form>
    </div>
</body>
</html>