<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" >
        $(document).ready(function(){
             $("#sign-up-button").on("click", function(){
                 $.post("${contextPath}/user/signup",
                         $("#sign-up-form").serialize(),
                         function(data){
                             alert(data.result);
                             location.href="${contextPath}/user/login"
                         });
             });
        });
    </script>
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h1>YS Project <small>template pages</small></h1>
        </div>
        <h3> sign up </h3>

        <form class="form-horizontal" role="form" id="sign-up-form" action="${contextPath}/user/signup" method="post">
            <div class="form-group">
                <label for="username" class="col-sm-2 control-label">User Name</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="username" name="username" placeholder="Email" />
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password" />
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="email" name="email" placeholder="Password" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" id="sign-up-button" class="btn btn-default">Sign up</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>