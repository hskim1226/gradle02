<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <script type="text/javascript" >
        $(document).ready(function(){
            $("#sign-up-button").on("click", function(){
                $('#sign-up-form').bootstrapValidator('validate');
            });

            $("#available-check-button").on("click", function(){
                $.get("${contextPath}/user/available",
                        $("#sign-up-form").serialize(),
                        function(data){
                            if(data.result == "SUCCESS"){
                                alert("사용가능한 username 입니다.");
                                $("#sign-up-button").prop('disabled', false);
                            }else{
                                alert("이미 사용 중인 username 입니다.");
                                $("#sign-up-button").prop('disabled', true);
                            }
                        }
                );
            });

            $('#sign-up-form').bootstrapValidator({
                onError: function(e) {
                },
                onSuccess: function(e) {
                    $.post("${contextPath}/user/signup",
                        $("#sign-up-form").serialize(),
                        function(data){
                            if(data.result == "SUCCESS"){
                                alert("성공적으로 등록였습니다.");
                                location.href="${contextPath}/user/login";
                            }else{
                                alert("서비스에 문제가 발생하였습니다.");
                            }
                        }
                    );
                }
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
                    <div class="input-group">
                        <input type="text" class="form-control" id="username" name="username" placeholder="username"
                               data-bv-notempty data-bv-notempty-message="The user name is required" />
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button" id="available-check-button">Check Available</button>
                        </span>
                    </div><!-- /input-group -->

                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password"
                           data-bv-notempty data-bv-notempty-message="Password is required" />
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">email</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="email" name="email" placeholder="email"
                          data-bv-notempty data-bv-notempty-message="The e-mail is required" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" id="sign-up-button" class="btn btn-default" disabled="disabled" >Sign up</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>