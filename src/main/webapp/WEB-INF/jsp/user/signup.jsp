<%@ page import="java.io.FileWriter" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.URLConnection" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${contextPath}/css/datepicker3.css">
    <style>
        section.signup {
            padding: 200px 0 60px;
            background: #556699;
            color: #fdfdfd;
        }

        section.signup h2.slogan {
            color: #fff;
            font-size: 48px;
            font-weight: 900;
        }

        /* inner heading */
        section.signup.inner {
            background: #eee;
            padding: 150px 0 50px;
        }

        section.signup .spacer-big {
            margin-bottom: 7em;
        }

        section.signup .spacer-mid {
            margin-bottom: 5em;
        }

        section.signup .spacer-small {
            margin-bottom: 3em;
        }

        section.signup .spacer-tiny {
            margin-bottom: 1em;
        }

        section.signup div.btn-group>label.btn {
            max-width: none;
        }
        
        section.signup textarea.form-control[readonly] {
            cursor: default;
            resize: none;
            -moz-user-select: none;
            -webkit-user-select: none;
            -khtml-user-select: none;
            user-select: none;
        }
    </style>
</head>
<body>
<section class="signup">
    <div class="container">
        <div class="page-header">
            <h1 style="color: #fdfdfd">회원 가입</h1>
        </div>
        <form class="form-horizontal" role="form" id="sign-up-form" action="${contextPath}/user/signup/save" method="post">
            <form:hidden path="userAgreYn" />
            <form:hidden path="privInfoYn" />
            <%--usertype--%>
            <div class="form-group">
                <label class="col-sm-2 control-label">User Type</label>
                <div class="col-sm-10">
                    <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-default active">
                            <input type="radio" name="userType" id="usertype-general" value="g" checked />General
                        </label>
                        <label class="btn btn-default">
                            <input type="radio" name="userType" id="usertype-child" value="c" />Child
                        </label>
                        <label class="btn btn-default">
                            <input type="radio" name="userType" id="usertype-foreign" value="f" />Foreign
                        </label>
                    </div>
                </div>
            </div>
            <%--user id--%>
            <div class="form-group">
                <label for="userId" class="col-sm-2 control-label">User ID</label>
                <div class="col-sm-6 col-md-4">
                    <div class="input-group">
                        <input type="text" class="form-control" id="userId" name="userId" placeholder="User ID"
                               data-bv-notempty data-bv-notempty-message="The user id is required" />
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button" id="available-check-button">Check</button>
                        </span>
                    </div>
                </div>
            </div>
            <%--password--%>
            <div class="form-group">
                <label for="pswd" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="pswd" name="pswd" placeholder="Password"
                           data-bv-notempty data-bv-notempty-message="Password is required" />
                    <input type="password" class="form-control" id="confirm" name="confirm" placeholder="Confirm Password"/>
                </div>
            </div>
            <%--email--%>
            <div class="form-group">
                <label for="mailAddr" class="col-sm-2 control-label">E-Mail</label>
                <div class="col-sm-6">
                    <input type="email" class="form-control" id="mailAddr" name="mailAddr" placeholder="email" />
                </div>
                <label for="mailRecvYn" class="control-label">
                    <input type="checkbox" name="mailRecvYn" id="mailRecvYn" value="y"/>Receive email
                </label>
            </div>
            <%--mobiNum--%>
            <div class="form-group">
                <label for="mobiNum" class="col-sm-2 control-label">Mobile</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="mobiNum" name="mobiNum" placeholder="###-####-####" />
                </div>
                <label for="sms-receive" class="control-label">
                    <input type="checkbox" name="smsRecvYn" id="sms-receive" value="y" />Receive sms
                </label>
            </div>
            <%--name--%>
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">User Name</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="name" name="name" placeholder="Name" />
                </div>
            </div>
            <%--gend--%>
            <div class="form-group">
                <label class="col-sm-2 control-label">Gender</label>
                <div class="col-sm-10">
                    <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-default active">
                            <input type="radio" name="gend" id="gend-male" value="m" checked />Male
                        </label>
                        <label class="btn btn-default">
                            <input type="radio" name="gend" id="gend-female" value="f" />Female
                        </label>
                    </div>
                </div>
            </div>
            <%--calendar--%>
            <div class="form-group">
                <label for="bornDay" class="col-sm-2 control-label">Birthday</label>
                <div class="col-sm-6 col-md-4" id="sandbox-container">
                    <div class="input-group date">
                        <input type="text" class="form-control" id="bornDay" name="bornDay"/>
                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                    </div>
                </div>
            </div>
            <%--submit--%>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" id="sign-up-button" class="btn btn-default" disabled="disabled" >Sign up</button>
                </div>
            </div>
        </form>
    </div>
</section>
<content tag="local-script">
    <script src="${contextPath}/js/bootstrap-datepicker.js"></script>
    <script src="${contextPath}/js/bootstrap-datepicker.kr.js"></script>

    <script type="text/javascript" >
        $(document).ready(function(){
            $("#sign-up-button").on("click", function(){
                $('#sign-up-form').bootstrapValidator('validate');
            });

            $("#available-check-button").on("click", function(){
                $.get("${contextPath}/user/idCheck",
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
console.log(e);
                },
                onSuccess: function(e) {
console.log(e);
                    $.post("${contextPath}/user/signup/save",
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

            $('#sandbox-container .input-group.date').datepicker({
                format: "yyyymmdd",
                startView: 2,
                language: "kr",
                forceParse: false,
                autoclose: true
            });

        });
    </script>
</content>
</body>
</html>