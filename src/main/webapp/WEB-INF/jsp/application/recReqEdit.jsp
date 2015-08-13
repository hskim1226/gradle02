<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang='ko'>
<head>
    <title><spring:message code="L06501"/><%--추천서 요청--%></title>
    <style>
        .input-text {
            height: 50px;
            font-size: 100%;
            opacity: 1.0;
            margin-bottom: 5%;
        }
        input[readonly] {
            background-color: white !important;
            cursor: text !important;
        }
        .span-box {
            display: inline-block;
            position: relative;
            width: 100%;
            padding: 6px 12px;
            font-size: 14px;
            line-height: 1.42857143;
            background-color: white;
            color: #555;
            border: 1px solid #ccc;
        }
    </style>
</head>
<body>
<div id="overlay" class="web_dialog_overlay"></div>
<section class="normal-white">
    <div class="container">
        <%--<form:form commandName="user" cssClass="form-horizontal" role="form" method="post">--%>
        <form id="reqRec" class="form-horizontal" method="post">
            <div class="col-md-offset-1 col-md-10">
                <div class="form-group inner-container-white">
                    <div class="col-sm-offset-1 col-sm-10 text-gray">
                        <i class="fa fa-envelope-o fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L06501"/><%--추천서 요청--%></b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="form-group required">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-3 text-gray">
                                <label for="profName" class="control-label"><spring:message code="L06502"/><%--교수 이름--%></label>
                            </div>
                            <div class="col-sm-9">
                                <div class="col-sm-12 nopadding input-group">
                                    <input type="text" id="profName" name="profName" class="form-control input-text" maxlength="16" placeholder="<spring:message code="L06502"/>" />  <%--교수 이름--%>
                                </div>
                        <%--<spring:bind path="profName">--%>
                            <%--<c:if test="${status.error}">--%>
                                <%--<div class="col-sm-12 nopadding">--%>
                                    <%--<div class="validation-error">${status.errorMessage}</div>--%>
                                <%--</div>--%>
                            <%--</c:if>--%>
                        <%--</spring:bind>--%>
                            </div>
                        </div>
                    </div>
                    <div class="form-group required">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-3 text-gray">
                                <label for="profMailAddr" class="control-label"><spring:message code="L06503"/><%--교수 e-mail--%></label>
                            </div>
                            <div class="col-sm-9">
                                <div class="col-sm-12 nopadding input-group">
                                    <input type="text" id="profMailAddr" name="profMailAddr" class="form-control input-text" maxlength="16" placeholder="<spring:message code="L06503"/>" />  <%--교수 e-mail--%>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group required">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-3 text-gray">
                                <label for="reqText" class="control-label"><spring:message code="L06504"/><%--요청 내용--%></label>
                            </div>
                            <div class="col-sm-9">
                                <div class="col-sm-12 nopadding input-group">
                                    <textarea id="reqText" name="reqText" class="form-control" rows="10" placeholder="<spring:message code="U06503"/>"></textarea>  <%--교수님께 보낼 메일 내용을 500자 이내로 입력해주세요.--%>
                                    <%--<input type="password" id="pswd2" name="pswd2" class="form-control input-text" maxlength="16" placeholder="<spring:message code="U06503"/>" />  &lt;%&ndash;교수님께 보낼 메일 내용을 입력해주세요.&ndash;%&gt;--%>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-offset-1 col-sm-10">
                        <div class="col-sm-4 nopadding">
                            <button class="preview btn btn-info btn-lg btn-block btn-save input-text"><spring:message code="L06531"/><%--미리 보기--%></button>
                        </div>
                        <div class="col-sm-4">
                            <button class="save btn btn-warning btn-lg btn-block btn-save input-text"><spring:message code="L06532"/><%--임시 저장--%></button>
                        </div>
                        <div class="col-sm-4 nopadding">
                            <button class="send btn btn-primary btn-lg btn-block btn-save input-text"><spring:message code="L06533"/><%--추천서 요청 메일 보내기--%></button>
                        </div>
                    </div>
                </div>
            </div>
            <%--<form:hidden path="userId" />--%>
            <input type="hidden" name="applNo"/>
        </form>
    </div>

    <%-- 미리보기 팝업 --%>
    <div id="modal_popup1" class="popup0_wrap" style="display:none; margin-top:-250px; margin-left:-250px;">
        <div id="bpopContent" class="popuphead">
            <h1>
                <label id="searchTitle"> <spring:message code="L06531"/><%--미리 보기--%> </label>
            </h1>
        </div>

        <div class="form-group">
            <div class="col-sm-12">
                <div class="col-sm-2 text-gray">
                    <label for="profName" class="control-label"><spring:message code="L06502"/><%--교수 이름--%></label>
                </div>
                <div class="col-sm-10">
                    <div class="col-sm-12 nopadding input-group">
                        <input type="text" id="pv-profName"class="form-control input-text" maxlength="50" placeholder="<spring:message code="L06502"/>" readonly="true"/>  <%--교수 이름--%>
                    </div>
                </div>
            </div>
        </div>
        <div class="spacer-tiny">&nbsp;</div>
        <div class="form-group">
            <div class="col-sm-12">
                <div class="col-sm-2 text-gray">
                    <label for="profMailAddr" class="control-label"><spring:message code="L06503"/><%--교수 e-mail--%></label>
                </div>
                <div class="col-sm-10">
                    <div class="col-sm-12 nopadding input-group">
                        <input type="text" id="pv-profMailAddr" class="form-control input-text" maxlength="50" placeholder="<spring:message code="L06503"/>" readonly="true" />  <%--교수 e-mail--%>
                    </div>
                </div>
            </div>
        </div>
        <div class="spacer-tiny">&nbsp;</div>
        <div class="form-group">
            <div class="col-sm-12">
                <div class="col-sm-2 text-gray">
                    <label for="reqText" class="control-label"><spring:message code="L06504"/><%--요청 내용--%></label>
                </div>
                <div class="col-sm-10">
                    <div class="col-sm-12 nopadding input-group">
                        <span id="pv-reqText" class="span-box"></span>
                    </div>
                </div>
            </div>
        </div>
        <div class="spacer-tiny">&nbsp;</div>
        <%-- TODO 첨부파일 --%>
        <a class="btn_close b-close" title="닫기"><img src="<spring:eval expression="@app.getProperty('path.static')" />/img/btn_close1.png" alt="닫기"></a>
    </div>
</section>
<content tag="local-script">
<script type="text/javascript">
$(document).ready(function() {

    var hideDialog = function(obj) {
        $("#overlay").hide();
        $(obj).fadeOut(300);
    };

    var showDialog = function(modal, obj) {
        $("#overlay").show();
        $(obj).fadeIn(300);

        if (modal) {
            $("#overlay").unbind("click");
        }
        else {
            $("#overlay").click(function(e) {
                hideDialog(obj);
            });
        }
    };

    $('.b-close').on('click', function(e) {
        e.preventDefault();
        hideDialog('#modal_popup1');
    });

    $('.preview').click(function(e){
        e.preventDefault();
        showDialog(true, '#modal_popup1');
        // ajax로 보내서 recKey 포함 링크 내용을 받아오고
        // 현재의 input text, textarea에 있는 값 + ajax로 받아온 값을 modal 창에 뿌림
        var formData = $('#reqRec').serialize(),
            profName = document.getElementById('profName').value,
            profMailAddr = document.getElementById('profMailAddr').value,
            reqText = document.getElementById('reqText').value;

        document.getElementById('pv-profName').value = profName,
        document.getElementById('pv-profMailAddr').value = profMailAddr,
        document.getElementById('pv-reqText').value = reqText;

        $.ajax({
            type: 'POST',
            url: "${contextPath}/application/recReq/preview",
            data: formData,
            success: function(data) {
                var container = JSON.parse(data),
                    obj = JSON.parse(container.data);
                var info = "<br/><br/><spring:message code="L06534"/><br/>" + "<spring:message code="U06504"/><br/>";
                var linkString = "<spring:eval expression="@app.getProperty('site.url')" />" + "${contextPath}/application/recommend?key=" + obj.recKey;
                var link = "<a href='" + linkString + "' target='_blank'>" + linkString + "</a>";

                document.getElementById('pv-reqText').innerHTML = reqText + info + link;
            }
        });

    });

    <%-- 하단 버튼 처리 --%>
    var formProcess = function(event) {
        event.preventDefault();
        if (checkPwd()) {
            var form = document.getElementById('reqRec');
            form.action = "${contextPath}/user/savePwd";
            form.submit();
        }
    };
    $('.btn-save').on('click', formProcess);
    <%-- 하단 버튼 처리 --%>

    <%-- action 성공 여부 알림 처리 --%>
    var showActionResult = function() {
        var msg = '${resultMsg}',
            result = '${result}';
        if (msg.length > 0) {
            confirm(msg);
            if (result == 'SUCCESS') {
                document.location.href = '${contextPath}/user/login';
            }
        }
    };
    showActionResult();
    <%-- action 성공 여부 알림 처리 --%>

    $('#pswd').focus();
});
</script>
</content>
</body>
</html>
