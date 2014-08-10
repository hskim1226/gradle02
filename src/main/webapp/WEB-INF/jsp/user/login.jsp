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
            <h1 style="color: #ffffff">로그인</h1>
        </div>
        <div class="col-md-9 col-md-offset-1">
            <form class="form-horizontal" role="form" action="${contextPath}/j_spring_security_check.do" method="post">
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">사용자 ID</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="username" name="j_username" placeholder="User ID">
                    </div>
                    <div class="spacer-small"></div>
                    <label for="password" class="col-sm-2 control-label">비밀 번호</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="password" name="j_password" placeholder="Password">
                    </div>
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Remember me
                            </label>
                        </div>
                    </div>
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default btn-lg btn-block">로그인</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
</body>
</html>