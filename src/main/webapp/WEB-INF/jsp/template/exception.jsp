<%@ page contentType="text/html;charset=UTF-8"%>
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

    <form action="${contextPath}/template/exception" method="post" >
        <div class="text-center">
            <div class="row">
                <div class="input-group">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="submit">Html Exception!</button>
                    </span>
                </div><!-- /input-group -->
            </div><!-- /.row -->

            <div class="row">
                <div class="input-group">
                    <span class="input-group-btn">
                        <button id="ajax-exception-button" class="btn btn-default" type="button">Ajax Exception!</button>
                    </span>
                </div><!-- /input-group -->
            </div><!-- /.row -->
        </div>
    </form>

</div>

<script type="text/javascript">
    $(document).ready(function(){
        $("#ajax-exception-button").on("click", function(){
            $.ajax( "${contextPath}/template/exception/ajax" )
            .done(function(data) {
                alert( "success" );
            })
            .fail(function(data) {
                alert( data.responseJSON.message );
            });
        });
    });
</script>
</body>
</html>