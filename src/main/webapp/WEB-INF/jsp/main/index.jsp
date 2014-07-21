<%--
  Created by IntelliJ IDEA.
  User: skplanet
  Date: 2014. 7. 20.
  Time: 오후 6:05
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
      <h1>index page!!</h1>
      <sec:authorize access="hasRole('ROLE_USER')">[ logined as <sec:authentication property="principal.username" />]</sec:authorize>

      <div>
          <ul>
              <li><a href="${contextPath}/user/login" >go to Login Page!</a></li>
          </ul>
      </div>
</body>
</html>