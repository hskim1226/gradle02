<%@ page contentType="text/html; charset=utf-8"%>
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

        <form action="${contextPath}/template/upload" method="post" enctype="multipart/form-data">
            <div class="text-center">
                <div class="row">
                    <div class="input-group">
                        <input type="file" name="file" class="form-control">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="submit">Upload!</button>
                        </span>
                    </div><!-- /input-group -->
                </div><!-- /.row -->
            </div>
        </form>

    </div>
</body>
</html>