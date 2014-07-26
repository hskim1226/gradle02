<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: skplanet
  Date: 2014. 7. 23.
  Time: 오후 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h1>YS Project <small>template pages</small></h1>
        </div>


        <div class="panel panel-default">
            <div class="panel-heading">
                <h2 class="panel-title">Uploaded Files</h2>
            </div>
        </div>

        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Seq</th>
                    <th>Server Directory</th>
                    <th>File Name</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="file" items="${files}" varStatus="status">
                    <tr>
                        <td>${file.seq}</td>
                        <td>${file.path}</td>
                        <td>${file.fileName}</td>
                        <td><button class="btn btn-default" type="button" onclick="location.href='${contextPath}/template/download/${file.seq}'">Download!</button></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>
</body>
</html>