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
        <form class="form-horizontal" role="form" id="sign-up-form" action="${contextPath}/user/signup" method="POST">
            <div class="form-group">
                <label for="terms-of-service" class="col-sm-2 control-label">연세어플라이 이용 약관에 대한 동의</label>
                <div class="col-sm-10">
                    <textarea id="terms-of-service" class="form-control" rows="10" readonly><%=request.getAttribute("terms-of-service")%></textarea>
                    <label for="terms-agree" class="control-label">
                        <input type="checkbox" name="userAgreYn" id="terms-agree" value="y" />&nbsp;&nbsp;동의합니다.
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label for="privacy-policy1" class="col-sm-2 control-label">개인 정보 수집 및 이용에 대한 동의</label>
                <div class="col-sm-10">
                    <textarea id="privacy-policy1" class="form-control" rows="10" readonly><%=request.getAttribute("privacy-policy1")%></textarea>
                    <textarea id="privacy-policy2" class="form-control" rows="10" readonly><%=request.getAttribute("privacy-policy2")%></textarea>
                    <textarea id="privacy-policy3" class="form-control" rows="10" readonly><%=request.getAttribute("privacy-policy3")%></textarea>
                    <label for="privacy-agree" class="control-label">
                        <input type="checkbox" name="privInfoYn" id="privacy-agree" value="y" />&nbsp;&nbsp;동의합니다.
                    </label>
                </div>
            </div>
            <div class="col-sm-offset-2 col-sm-10">
                <label for="agreeAll" class="control-label">
                    <input type="checkbox" name="agreeAll" id="agreeAll" value="y" />&nbsp;&nbsp;연세어플라이 이용 약관 및 개인 정보 수집 및 이용에 모두 동의합니다.
                </label>
            </div>
        </form>
        <div class="col-sm-offset-2 col-sm-10">
            <button id="sign-up-button" class="btn btn-info btn-lg btn-block">계속하기</button>
        </div>
    </div>
</section>
<content tag="local-script">
    <script type="text/javascript" >
        $(document).ready(function(){
            $('#agreeAll').on('click', function() {
                var isChecked = this.checked;
                $("input:checkbox").each(function() {
                    $(this).prop("checked", isChecked);
                });
            });

            $('#sign-up-button').on('click', function() {
                if ( !$("input:checkbox[id='terms-agree']").is(":checked") ) {
                    alert("${msg1}");
                    return;
                }

                if ( !$("input:checkbox[id='privacy-agree']").is(":checked") ) {
                    alert("${msg2}");
                    return;
                }
                $('#sign-up-form').submit();
            });
        });
    </script>
</content>
</body>
</html>