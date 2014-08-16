<%@ taglib prefix="ui" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <style>
        section.detail {
            padding: 200px 0 60px;
            background: #556699;
            color: #fdfdfd;
        }

        section.detail h2.slogan {
            color: #fff;
            font-size: 48px;
            font-weight: 900;
        }

        /* inner heading */
        section.detail.inner {
            background: #eee;
            padding: 150px 0 50px;
        }

        section.detail .spacer-big {
            margin-bottom: 7em;
        }

        section.detail .spacer-mid {
            margin-bottom: 5em;
        }

        section.detail .spacer-small {
            margin-bottom: 3em;
        }

        section.detail .spacer-tiny {
            margin-bottom: 1em;
        }

        section.detail .form-control[disabled] {
            cursor: auto;
            background-color: #569;
            color: #fff;
        }
    </style>
</head>
<body>
<section class="detail">
    <div class="container">
        <div class="page-header">
            <h1>회원 정보 수정</h1>
        </div>
        <h3> detail </h3>

        <form:form commandName="usersVO" class="form-horizontal" action="${contextPath}/mypage" method="post">
            <form:errors path="*" css="errors" />
            <%--user id--%>
            <div class="form-group">
                <form:label path="username" cssClass="col-sm-2 control-label">User ID</form:label>
                <div class="col-sm-10">
                    <form:input path="username" cssClass="form-control" disabled="true" />
                </div>
            </div>
            <%--password--%>
<%--
            <div class="form-group">
                <label for="newPassword" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <div class="input-group">
                        <input type="password" class="form-control" id="password" name="password" />
                        <input type="password" class="form-control" id="newPassword" name="newPassword" />
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" />
                    </div>
                </div>
            </div>
--%>
            <%--email--%>
            <div class="form-group">
                <form:label path="email" cssClass="col-sm-2 control-label" cssErrorClass="errorMessage">E-Mail</form:label>
                <div class="col-sm-6">
                    <form:input path="email" cssClass="form-control" />
                </div>
                <form:label path="emailReceive" cssClass="control-label">
                    <form:checkbox path="emailReceive" value="y" label="I Agree" />
                </form:label>
            </div>
            <%--mobile--%>
            <div class="form-group">
                <form:label path="mobile" cssClass="col-sm-2 control-label">Mobile</form:label>
                <div class="col-sm-6">
                    <form:input path="mobile" cssClass="form-control" placeholder="###-####-####" />
                </div>
                <form:label path="smsReceive" cssClass="control-label">
                    <form:checkbox path="smsReceive" value="y" label="I Agree" />
                </form:label>
            </div>
            <%--name--%>
            <div class="form-group">
                <form:label path="name" cssClass="col-sm-2 control-label">User Name</form:label>
                <div class="col-sm-10">
                    <form:input path="name" cssClass="form-control" placeholder="Name" />
                </div>
            </div>
            <%--gender--%>
            <div class="form-group">
                <form:label path="gender" class="col-sm-2 control-label">Gender</form:label>
                <div class="col-sm-10">
                    <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-default">
                            <form:radiobutton path="gender" id="gender-male" value="m" />Male
                        </label>
                        <label class="btn btn-default">
                            <form:radiobutton path="gender" id="gender-female" value="f" />Female
                        </label>
                    </div>
                </div>
            </div>
            <%--calendar--%>
            <div class="form-group">
                <form:label path="birth" cssClass="col-sm-2 control-label">Birthday</form:label>
                <div class="col-sm-10" id="sandbox-container">
                    <div class="input-group date">
                        <form:input path="birth" class="form-control" />
                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                    </div>
                </div>
            </div>
            <%--submit--%>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-5 col-xs-6">
                    <button type="submit" id="update-button" class="btn btn-primary btn-lg btn-block">수정</button>
                </div>
                <div class="col-sm-5 col-xs-6">
                    <button type="reset" id="cancel-button" class="btn btn-default btn-lg btn-block">취소</button>
                </div>
            </div>
        </form:form>
    </div>
</section>
</body>
</html>