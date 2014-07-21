<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
    <h1> Login
        <sec:authorize access="hasRole('ROLE_USER')">[ logined as <sec:authentication property="principal.username" />]</sec:authorize>
    </h1>
    <div>
        <form action="${contextPath}/j_spring_security_check.do" method="post">
            <input type="text" name="j_username" />
            <input type="password" name="j_password" />
            <button> login </button>
        </form>
    </div>
</body>
</html>