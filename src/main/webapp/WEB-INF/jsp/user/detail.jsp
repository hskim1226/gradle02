<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <style>
        section.detail .form-control[disabled] {
            cursor: auto;
            background-color: #569;
            color: #fff;
        }
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <div class="page-header">
            <h1>회원 정보 수정</h1>
        </div>
        <h3> detail </h3>

        <form:form commandName="users" class="form-horizontal" action="${contextPath}/mypage" method="post">
            <form:errors path="*" css="errors" />
            <%--user id--%>
            <div class="form-group">
                <form:label path="userId" cssClass="col-sm-2 control-label">User ID</form:label>
                <div class="col-sm-10">
                    <form:input path="userId" cssClass="form-control" disabled="true" />
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
                <form:label path="mailAddr" cssClass="col-sm-2 control-label" cssErrorClass="errorMessage">E-Mail</form:label>
                <div class="col-sm-6">
                    <form:input path="mailAddr" cssClass="form-control" />
                </div>
                <form:label path="mailRecvYn" cssClass="control-label">
                    <form:checkbox path="mailRecvYn" value="y" label="I Agree" />
                </form:label>
            </div>
            <%--mobiNum--%>
            <div class="form-group">
                <form:label path="mobiNum" cssClass="col-sm-2 control-label">Mobile</form:label>
                <div class="col-sm-6">
                    <form:input path="mobiNum" cssClass="form-control" placeholder="###-####-####" />
                </div>
                <form:label path="smsRecvYn" cssClass="control-label">
                    <form:checkbox path="smsRecvYn" value="y" label="I Agree" />
                </form:label>
            </div>
            <%--name--%>
            <div class="form-group">
                <form:label path="name" cssClass="col-sm-2 control-label">User Name</form:label>
                <div class="col-sm-10">
                    <form:input path="name" cssClass="form-control" placeholder="Name" />
                </div>
            </div>
            <%--gend--%>
            <div class="form-group">
                <form:label path="gend" class="col-sm-2 control-label">Gender</form:label>
                <div class="col-sm-10">
                    <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-default">
                            <form:radiobutton path="gend" id="gend-male" value="m" />Male
                        </label>
                        <label class="btn btn-default">
                            <form:radiobutton path="gend" id="gend-female" value="f" />Female
                        </label>
                    </div>
                </div>
            </div>
            <%--calendar--%>
            <div class="form-group">
                <form:label path="bornDay" cssClass="col-sm-2 control-label">Birthday</form:label>
                <div class="col-sm-10" id="sandbox-container">
                    <div class="input-group date">
                        <form:input path="bornDay" class="form-control" />
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