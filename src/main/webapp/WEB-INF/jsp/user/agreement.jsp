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
                        <input type="checkbox" name="userAgreYn" id="terms-agree" value="y" />I agree
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label for="privacy-policy" class="col-sm-2 control-label">Privacy Policy</label>
                <div class="col-sm-10">
                    <textarea id="privacy-policy" class="form-control" rows="5" readonly><%=request.getAttribute("privacy-policy")%></textarea>
                    <label for="privacy-agree" class="control-label">
                        <input type="checkbox" name="privInfoYn" id="privacy-agree" value="y" />I agree
                    </label>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" id="sign-up-button" class="btn btn-default">계속하기</button>
                </div>
            </div>
        </form>
    </div>
</section>
<content tag="local-script">
    <script type="text/javascript" >
        $(document).ready(function(){

        });
    </script>
</content>
</body>
</html>