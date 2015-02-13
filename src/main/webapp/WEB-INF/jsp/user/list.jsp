<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title>INDEX</title>
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h1>YS Project <small>template pages</small></h1>
        </div>


        <div class="panel panel-default">
            <div class="panel-heading">
                <h2 class="panel-title">user management</h2>
            </div>
        </div>

        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>#</th>
                    <th>User Name</th>
                    <th>Enabled</th>
                    <th>Join Date</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users}" varStatus="status">
                        <tr>
                            <td>${status.index+1}</td>
                            <td>${user.username}</td>
                            <td>${user.enabled}</td>
                            <td>${user.regDate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="text-center">
            <ul class="pagination">
                <li><a href="?pageNum=1&pageRows=30">&laquo;</a></li>
                <c:forEach begin="1" end="${user.usersTotal+30}" step="30" varStatus="status">
                    <li><a href="?pageNum=${status.index}&pageRows=30">1</a></li>
                </c:forEach>
                <li><a href="#">&raquo;</a></li>
            </ul>
        </div>

    </div>

</body>
</html>