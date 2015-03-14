<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang='ko'>
<head>
    <title><spring:message code="L00101"/><%--회원 약관 및 개인 정보 수집 이용 동의--%></title>
    <style>
        section.normal-white div.btn-group>label.btn {
            max-width: none;
        }
        section.normal-white textarea.form-control[readonly] {
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
<section class="normal-white">
    <div class="container">
        <%--<div class="page-header">--%>
            <%--<h1 style="color: #fdfdfd">회원 약관 및 개인 정보 수집 이용 동의</h1>--%>
        <%--</div>--%>
        <form class="form-horizontal" role="form" id="sign-up-form" action="${contextPath}/user/signup" method="POST">
            <div class="col-md-offset-1 col-md-10">
                <div class="form-group inner-container-white">
                    <div class="col-sm-12 text-gray">
                        <i class="fa fa-check fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L00101"/><%--회원 약관 및 개인 정보 수집 이용 동의--%></b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="form-group text-gray">
                        <label for="terms-of-service" class="col-sm-2 control-label word-keep-all text-gray"><spring:message code="L00102"/><%--그래드넷 이용 약관에 대한 동의--%></label>
                        <div class="col-sm-9">
                            <textarea id="terms-of-service" class="form-control" rows="10" readonly><%=request.getAttribute("terms-of-service")%></textarea>
                            <label for="terms-agree" class="control-label">
                                <input type="checkbox" name="userAgreYn" id="terms-agree" value="y" />&nbsp;&nbsp;<spring:message code="L00103"/><%--동의합니다.--%>
                            </label>
                        </div>
                    </div>
                    <div class="form-group text-gray">
                        <label for="privacy-policy1" class="col-sm-2 control-label word-keep-all"><spring:message code="L00104"/><%--개인 정보 수집 및 이용에 대한 동의--%></label>
                        <div class="col-sm-9">
                            <textarea id="privacy-policy1" class="form-control" rows="10" readonly><%=request.getAttribute("privacy-policy1")%></textarea>
                            <textarea id="privacy-policy2" class="form-control" rows="10" readonly><%=request.getAttribute("privacy-policy2")%></textarea>
                            <textarea id="privacy-policy3" class="form-control" rows="10" readonly><%=request.getAttribute("privacy-policy3")%></textarea>
                            <label for="privacy-agree" class="control-label">
                                <input type="checkbox" name="privInfoYn" id="privacy-agree" value="y" />&nbsp;&nbsp;<spring:message code="L00103"/><%--동의합니다.--%>
                            </label>
                        </div>
                    </div>
                    <div class="col-sm-offset-2 col-sm-9">
                        <button id="sign-up-button" class="btn btn-primary btn-lg btn-block"><spring:message code="L00105"/><%--회원 정보 입력--%></button>
                    </div>
                </div>
            </div>
        </form>

    </div>
</section>
<content tag="local-script">
<script type="text/javascript">
$(document).ready(function(){

    <%-- 단어 잘림 방지 --%>
    $('.word-keep-all').wordBreakKeepAll();

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