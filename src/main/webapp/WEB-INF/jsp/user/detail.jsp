<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
<section class="featured">
    <div class="container">
        <div class="page-header">
            <h1>YS Project <small>template pages</small></h1>
        </div>
        <h3> sign up </h3>

        <form class="form-horizontal" role="form" id="sign-up-form" action="${contextPath}/mypage" method="post">
            <%--usertype--%>
            <div class="form-group">
                <label class="col-sm-2 control-label">User Type</label>
                <div class="col-sm-10">
                    <c:if test="${usersVO.userType == 'g'}">
                        <c:out value="General" />
                    </c:if>
                </div>
            </div>
        </form>
    </div>
</section>
</body>
</html>