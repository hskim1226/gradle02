<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title>회원 정보 수정</title>
    <style>
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <form:form commandName="users" class="form-horizontal" action="${contextPath}/mypage" method="post">
            <div class="col-md-offset-2 col-md-8">
                <div class="inner-container-white">
                    <div class="col-sm-12 text-gray">
                        <i class="fa fa-unlock fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b>비밀 번호 확인</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="form-group">
                        <label for="userId" class="col-sm-2 control-label">User ID</label>
                        <div class="col-sm-10">
                            <form:input path="userId" class="form-control" disabled="true" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label path="mailAddr" class="col-sm-2 control-label" cssErrorClass="errorMessage">E-Mail</label>
                        <div class="col-sm-6">
                            <form:input path="mailAddr" class="form-control" />
                        </div>
                        <label path="mailRecvYn" class="control-label">
                            <form:checkbox path="mailRecvYn" value="y" label="I Agree" />
                        </label>
                    </div>
                    <div class="form-group">
                        <label path="mobiNum" class="col-sm-2 control-label">Mobile</label>
                        <div class="col-sm-6">
                            <form:input path="mobiNum" class="form-control" placeholder="###-####-####" />
                        </div>
                        <label path="smsRecvYn" class="control-label">
                            <form:checkbox path="smsRecvYn" value="y" label="I Agree" />
                        </label>
                    </div>
                    <div class="form-group">
                        <label path="name" class="col-sm-2 control-label">User Name</label>
                        <div class="col-sm-10">
                            <form:input path="name" class="form-control" placeholder="Name" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label path="gend" class="col-sm-2 control-label">Gender</label>
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
                    <div class="form-group">
                        <label path="bornDay" class="col-sm-2 control-label">Birthday</label>
                        <div class="col-sm-10" id="sandbox-container">
                            <div class="input-group date">
                                <form:input path="bornDay" class="form-control" />
                                <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-5 col-xs-6">
                            <button type="submit" id="update-button" class="btn btn-primary btn-lg btn-block">수정</button>
                        </div>
                        <div class="col-sm-5 col-xs-6">
                            <button type="reset" id="cancel-button" class="btn btn-default btn-lg btn-block">비밀 번호 변경</button>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</section>
</body>
</html>