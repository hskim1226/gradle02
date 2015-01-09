<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
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
        <form class="form-horizontal" id="sign-up-form" action="${contextPath}/user/signup/save" method="post" role="form">
            <form:hidden path="users.userAgreYn" />
            <form:hidden path="users.privInfoYn" />
            <%--usertype--%>
            <div class="form-group">
                <label class="col-sm-4 control-label"><spring:message code="L100" /></label>
                <div class="col-sm-4">
                    <div class="btn-group btn-group-justified" data-toggle="buttons">
                        <label class="btn btn-default active">
                            <input type="radio" name="userType" id="usertype[]" value="g" checked /><spring:message code="L108" />
                        </label>
                        <label class="btn btn-default">
                            <input type="radio" name="userType" id="usertype[]" value="c" /><spring:message code="L109" />
                        </label>
                        <label class="btn btn-default">
                            <input type="radio" name="userType" id="usertype[]" value="f" /><spring:message code="L110" />
                        </label>
                    </div>
                </div>
            </div>
            <%--user id--%>
            <div class="form-group">
                <label for="userId" class="col-sm-4 control-label"><spring:message code="L101" /></label>
                <div class="col-sm-4">
                    <div class="input-group">
                        <input type="text" class="form-control" name="userId" id="userId" placeholder="User ID"
                               data-bv-notempty data-bv-notempty-message="The user id is required" />
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button" id="available-check-button">Check</button>
                        </span>
                    </div>
                </div>
            </div>
            <%--password--%>
            <div class="form-group">
                <label for="pswd" class="col-sm-4 control-label"><spring:message code="L102" /></label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" name="pswd" id="pswd" placeholder="Password"
                           data-bv-notempty data-bv-notempty-message="Password is required" />
                </div>
            </div>
            <div class="form-group">
                <label for="confirm" class="col-sm-4 control-label"><spring:message code="L117" /></label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="confirm" placeholder="Confirm"
                           data-bv-notempty data-bv-notempty-message="Confirm is required" />
                </div>
            </div>
            <%--email--%>
            <div class="form-group">
                <label for="mailAddr" class="col-sm-4 control-label"><spring:message code="L103" /></label>
                <div class="col-sm-4">
                    <input type="email" class="form-control" name="mailAddr" id="mailAddr" placeholder="email" />
                </div>
                <label for="mailRecvYn" class="control-label">
                    <input type="checkbox" name="mailRecvYn" id="mailRecvYn" value="y"/><spring:message code="L112" />
                </label>
            </div>
            <%--mobiNum--%>
            <div class="form-group">
                <label for="mobiNum" class="col-sm-4 control-label"><spring:message code="L104" /></label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" name="mobiNum" id="mobiNum" placeholder="###-####-####" />
                </div>
                <label for="smsRecvYn" class="control-label">
                    <input type="checkbox" name="smsRecvYn" id="smsRecvYn" value="y" /><spring:message code="L113" />
                </label>
            </div>
            <%--name--%>
            <div class="form-group">
                <label for="name" class="col-sm-4 control-label"><spring:message code="L105" /></label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" name="name" id="name" placeholder="Name" />
                </div>
            </div>
            <%--gend--%>
            <div class="form-group">
                <label class="col-sm-4 control-label"><spring:message code="L106" /></label>
                <div class="col-sm-4">
                    <div class="btn-group btn-group-justified" data-toggle="buttons">
                        <label class="btn btn-default active">
                            <input type="radio" name="gend" id="gend[]" value="m" checked /><spring:message code="L114" />
                        </label>
                        <label class="btn btn-default">
                            <input type="radio" name="gend" id="gend[]" value="f" /><spring:message code="L115" />
                        </label>
                    </div>
                </div>
            </div>
            <%--calendar--%>
            <div class="form-group">
                <label for="bornDay" class="col-sm-4 control-label"><spring:message code="L107" /></label>
                <div class="col-sm-4">
                    <div class="input-group date">
                        <input type="text" class="form-control" name="bornDay" id="bornDay" />
                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                    </div>
                </div>
            </div>
            <%--submit--%>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <button type="button" id="sign-up-button" class="btn btn-default" disabled="disabled" ><spring:message code="L116" /></button>
                </div>
            </div>
        </form>
    </div>
</section>
<content tag="local-script">
    <%--<script src="${contextPath}/js/bootstrap-datepicker.js"></script>--%>
    <%--<script src="${contextPath}/js/bootstrap-datepicker.kr.js"></script>--%>
    <script src="${contextPath}/js/jquery-ui.min.js"></script>
    <script type="text/javascript">
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
                    $.post("${contextPath}/user/signup/save",
                            $("#sign-up-form").serialize(),
                            function(data){
                                if(data.result == "SUCCESS"){
                                    alert("성공적으로 등록되었습니다.");
                                    location.href="${contextPath}/user/login";
                                }else{
                                    alert("서비스에 문제가 발생하였습니다.");
                                }
                            }
                    );
                }
            });

            <%-- 달력 시작 --%>
            var datePickerOption = {
                dateFormat: 'yymmdd',
                yearRange: "1950:",
                monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                dayNamesMin: ['일','월','화','수','목','금','토'],
                changeMonth: true, //월변경가능
                changeYear: true, //년변경가능
                showMonthAfterYear: true //년 뒤에 월 표시
            };

            $('.input-group.date>input').each(function() {
                $(this).datepicker(datePickerOption);
            });
            <%-- 달력 끝 --%>

        });
    </script>
</content>
</body>
</html>