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
            padding: 6px 12px;
            font-size: 14px;
            line-height: 1.42857143;
            background-color: white;
            color: #555;
            border: 1px solid #ccc;
            overflow: auto;
            width: 690px;
            height: 180px;
        }
    </style>
</head>
<body>
<div id="overlay" class="web_dialog_overlay"></div>
<section class="normal-white">
    <div class="container">
        <form:form id="reqRec" commandName="recommendation" cssClass="form-horizontal" role="form">
        <%--<form id="reqRec" class="form-horizontal">--%>
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
                                    <form:input path="profName" cssClass="form-control input-text" maxlength="40" placeholder='${msg.getMsg("L06502")}' />  <%--교수 이름--%>
                                </div>
                        <spring:bind path="profName">
                            <c:if test="${status.error}">
                                <div class="col-sm-12 nopadding">
                                    <div class="validation-error">${status.errorMessage}</div>
                                </div>
                            </c:if>
                        </spring:bind>
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
                                    <form:input path="profMailAddr" cssClass="form-control input-text emailOnly" maxlength="40" placeholder='${msg.getMsg("L06503")}' />  <%--교수 e-mail--%>
                                </div>
                        <spring:bind path="profMailAddr">
                            <c:if test="${status.error}">
                                <div class="col-sm-12 nopadding">
                                    <div class="validation-error">${status.errorMessage}</div>
                                </div>
                            </c:if>
                        </spring:bind>
                            </div>
                        </div>
                    </div>
                    <div class="form-group required">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-3 text-gray">
                                <label for="profInst" class="control-label"><spring:message code="L06506"/><%--교수 소속 학교--%></label>
                            </div>
                            <div class="col-sm-9">
                                <div class="col-sm-12 nopadding input-group">
                                    <form:input path="profInst" cssClass="form-control input-text" maxlength="60" placeholder='${msg.getMsg("L06506")}' />  <%--교수 소속 학교--%>
                                </div>
                                <spring:bind path="profInst">
                                    <c:if test="${status.error}">
                                        <div class="col-sm-12 nopadding">
                                            <div class="validation-error">${status.errorMessage}</div>
                                        </div>
                                    </c:if>
                                </spring:bind>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-3 text-gray">
                                <label for="profMaj" class="control-label"><spring:message code="L06507"/><%--교수 전공--%></label>
                            </div>
                            <div class="col-sm-9">
                                <div class="col-sm-12 nopadding input-group">
                                    <form:input path="profMaj" cssClass="form-control input-text" maxlength="60" placeholder='${msg.getMsg("L06507")}' />  <%--교수 전공--%>
                                </div>
                                <%--<spring:bind path="profMaj">--%>
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
                                <label for="profPhone" class="control-label"><spring:message code="L06508"/><%--교수 연락처--%></label>
                            </div>
                            <div class="col-sm-9">
                                <div class="col-sm-12 nopadding input-group">
                                    <form:input path="profPhone" cssClass="form-control input-text" maxlength="40" placeholder='${msg.getMsg("L06508")}' />  <%--교수 연락처--%>
                                </div>
                                <%--<spring:bind path="profPhone">--%>
                                    <%--<c:if test="${status.error}">--%>
                                        <%--<div class="col-sm-12 nopadding">--%>
                                            <%--<div class="validation-error">${status.errorMessage}</div>--%>
                                        <%--</div>--%>
                                    <%--</c:if>--%>
                                <%--</spring:bind>--%>
                            </div>
                        </div>
                    </div>
                    <%-- 메일 제목과 요청 내용은 지원자가 작성하지 않고 시스템에서 작성하는 것으로 결정 --%>
                    <%--<div class="form-group required">--%>
                        <%--<div class="col-sm-offset-1 col-sm-10">--%>
                            <%--<div class="col-sm-3 text-gray">--%>
                                <%--<label for="reqSubject" class="control-label"><spring:message code="L06536"/>&lt;%&ndash;메일 제목&ndash;%&gt;</label>--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-9">--%>
                                <%--<div class="col-sm-12 nopadding input-group">--%>
                                    <%--<form:input path="reqSubject" cssClass="form-control input-text" maxlength="40" placeholder='${msg.getMsg("U06511")}' />  &lt;%&ndash;메일 제목을 입력해 주세요&ndash;%&gt;--%>
                                <%--</div>--%>
                                <%--<spring:bind path="reqSubject">--%>
                                    <%--<c:if test="${status.error}">--%>
                                        <%--<div class="col-sm-12 nopadding">--%>
                                            <%--<div class="validation-error">${status.errorMessage}</div>--%>
                                        <%--</div>--%>
                                    <%--</c:if>--%>
                                <%--</spring:bind>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group required">--%>
                        <%--<div class="col-sm-offset-1 col-sm-10">--%>
                            <%--<div class="col-sm-3 text-gray">--%>
                                <%--<label for="reqText" class="control-label"><spring:message code="L06504"/>&lt;%&ndash;요청 내용&ndash;%&gt;</label>--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-9">--%>
                                <%--<div class="col-sm-12 nopadding input-group">--%>
                                    <%--<form:textarea path="reqText" cssClass="form-control" rows="10" placeholder='${msg.getMsg("U06503")}'></form:textarea>  &lt;%&ndash;교수님께 보낼 메일 내용을 500자 이내로 입력해주세요.&ndash;%&gt;--%>
                                <%--</div>--%>
                        <%--<spring:bind path="reqText">--%>
                            <%--<c:if test="${status.error}">--%>
                                <%--<div class="col-sm-12 nopadding">--%>
                                    <%--<div class="validation-error">${status.errorMessage}</div>--%>
                                <%--</div>--%>
                            <%--</c:if>--%>
                        <%--</spring:bind>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="col-sm-offset-1 col-sm-10">
                        <%--<div class="col-sm-3">--%>
                            <%--<button class="preview btn btn-info btn-lg btn-block btn-save input-text"><spring:message code="L06531"/>&lt;%&ndash;미리 보기&ndash;%&gt;</button>--%>
                        <%--</div>--%>
                        <div class="col-sm-4">
                            <button class="save btn btn-success btn-lg btn-block btn-save input-text"><spring:message code="L06532"/><%--임시 저장--%></button>
                        </div>
                        <div class="col-sm-4">
                            <button class="cancel btn btn-danger btn-lg btn-block btn-save input-text"><spring:message code="L06535"/><%--취소--%></button>
                        </div>
                        <div class="col-sm-4">
                            <button class="send btn btn-primary btn-lg btn-block btn-save input-text"><spring:message code="L06533"/><%--추천서 요청 메일 보내기--%></button>
                        </div>
                    </div>
                </div>
            </div>
            <form:hidden path="recNo"/>
            <form:hidden path="applNo"/>
            <form:hidden path="recSeq"/>
        </form:form>
    </div>

    <%-- 미리보기 팝업 --%>
    <%--<div id="modal_popup1" class="popup0_wrap" style="display:none;">--%>
        <%--<div id="bpopContent" class="popuphead">--%>
            <%--<h1>--%>
                <%--<label id="searchTitle"> <spring:message code="L06531"/>&lt;%&ndash;미리 보기&ndash;%&gt; </label>--%>
            <%--</h1>--%>
        <%--</div>--%>

        <%--<div class="form-group">--%>
            <%--<div class="col-sm-12">--%>
                <%--<div class="col-sm-2 text-gray">--%>
                    <%--<label for="profName" class="control-label"><spring:message code="L06502"/>&lt;%&ndash;교수 이름&ndash;%&gt;</label>--%>
                <%--</div>--%>
                <%--<div class="col-sm-10">--%>
                    <%--<div class="col-sm-12 nopadding input-group">--%>
                        <%--<input type="text" id="pv-profName" class="form-control input-text" maxlength="120" placeholder="<spring:message code="L06502"/>" readonly="true"/>  &lt;%&ndash;교수 이름&ndash;%&gt;--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="spacer-tiny">&nbsp;</div>--%>
        <%--<div class="form-group">--%>
            <%--<div class="col-sm-12">--%>
                <%--<div class="col-sm-2 text-gray">--%>
                    <%--<label for="profMailAddr" class="control-label"><spring:message code="L06503"/>&lt;%&ndash;교수 e-mail&ndash;%&gt;</label>--%>
                <%--</div>--%>
                <%--<div class="col-sm-10">--%>
                    <%--<div class="col-sm-12 nopadding input-group">--%>
                        <%--<input type="text" id="pv-profMailAddr" class="form-control input-text" maxlength="120" placeholder="<spring:message code="L06503"/>" readonly="true" />  &lt;%&ndash;교수 e-mail&ndash;%&gt;--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="spacer-tiny">&nbsp;</div>--%>
        <%--<div class="form-group">--%>
            <%--<div class="col-sm-12">--%>
                <%--<div class="col-sm-2 text-gray">--%>
                    <%--<label for="pv-reqSubject" class="control-label"><spring:message code="L06536"/>&lt;%&ndash;메일 제목&ndash;%&gt;</label>--%>
                <%--</div>--%>
                <%--<div class="col-sm-10">--%>
                    <%--<div class="col-sm-12 nopadding input-group">--%>
                        <%--<input type="text" id="pv-reqSubject" class="form-control input-text" maxlength="120" placeholder="<spring:message code="U06511"/>" readonly="true" />  &lt;%&ndash;메일 제목을 입력해 주세요&ndash;%&gt;--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="spacer-tiny">&nbsp;</div>--%>
        <%--<div class="form-group">--%>
            <%--<div class="col-sm-12">--%>
                <%--<div class="col-sm-2 text-gray">--%>
                    <%--<label for="pv-reqText" class="control-label"><spring:message code="L06504"/>&lt;%&ndash;요청 내용&ndash;%&gt;</label>--%>
                <%--</div>--%>
                <%--<div class="col-sm-10">--%>
                    <%--<div class="col-sm-12 nopadding input-group">--%>
                        <%--<span id="pv-reqText" class="span-box"></span>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<a class="btn_close b-close" title="닫기"><img src="<spring:eval expression="@app.getProperty('path.static')" />/img/btn_close1.png" alt="닫기"></a>--%>
    <%--</div>--%>
    <%-- 미리보기 팝업 --%>
</section>
<content tag="local-script">
<script type="text/javascript">
$(document).ready(function() {

    var form = document.getElementById('reqRec');

    <%-- 화면 가리개 --%>
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
    <%-- 화면 가리개 --%>

    <%-- 미리 보기 팝업 처리 --%>
//    $('.b-close').on('click', function(e) {
//        e.preventDefault();
//        hideDialog('#modal_popup1');
//    });
    <%-- 미리 보기 팝업 처리 --%>

    <%-- 하단 버튼 처리 --%>
    <%--$('.preview').click(function(e){--%>
        <%--e.preventDefault();--%>
        <%--showDialog(true, '#modal_popup1');--%>
        <%--// ajax로 보내서 recKey 포함 링크 내용을 받아오고--%>
        <%--// 현재의 input text, textarea에 있는 값 + ajax로 받아온 값을 modal 창에 뿌림--%>
        <%--var formData = $('#reqRec').serialize(),--%>
            <%--profName = document.getElementById('profName').value,--%>
            <%--profMailAddr = document.getElementById('profMailAddr').value;--%>
<%--//            reqSubject = document.getElementById('reqSubject').value,--%>
<%--//            reqText = document.getElementById('reqText').value;--%>

        <%--document.getElementById('pv-profName').value = profName,--%>
        <%--document.getElementById('pv-profMailAddr').value = profMailAddr;--%>
<%--//        document.getElementById('pv-reqSubject').value = reqSubject,--%>
<%--//        document.getElementById('pv-reqText').value = reqText;--%>

        <%--$.ajax({--%>
            <%--type: 'POST',--%>
            <%--url: "${contextPath}/application/recReq/preview",--%>
            <%--data: formData,--%>
            <%--success: function(data) {--%>
                <%--var container = JSON.parse(data),--%>
                    <%--obj = JSON.parse(container.data);--%>
                <%--document.getElementById('pv-reqSubject').value = apex.newLine2Br(obj.subject);--%>
                <%--document.getElementById('pv-reqText').innerHTML = apex.newLine2Br(obj.contents);--%>
            <%--}--%>
        <%--});--%>

    <%--});--%>

    $('.save').click(function(e) {
        $("#overlay").show();
        e.preventDefault();
        form.action = "${contextPath}/application/recReq/save";
        form.method = "post";
        form.submit();
    });

    $('.cancel').click(function(e) {
        $("#overlay").show();
        e.preventDefault();
        form.action = "${contextPath}/application/recReq/cancel";
        form.method = "post";
        if (confirm('<spring:message code="U06512"/>')) {
            form.submit();
        } else {
            $("#overlay").hide();
        }
    });

    $('.send').click(function(e) {
        $("#overlay").show();
        e.preventDefault();
        form.action = "${contextPath}/application/recReq/send";
        form.method = "post";
        if (confirm('<spring:message code="U06501"/>')) { // 미리보기로 최종 확인을 하셨습니까?\\n\\n"확인"을 누르시면 교수님께 메일이 발송됩니다.
            form.submit();
        } else {
            $("#overlay").hide();
        }
    });

    <%-- 메일 주소 validation --%>
    apex.emailCheck('emailOnly', '<spring:message code="APEXJS_0003"/>'); // 이메일 주소를 정확히 기재해 주세요.
    <%-- 메일 주소 validation --%>

    <%-- 하단 버튼 처리 --%>

    <%-- action 성공 여부 알림 처리 --%>
    var showActionResult = function() {
        var msg = '${resultMsg}',
            result = '${result}';
        if (msg.length > 0) {
            confirm(msg);
            $("#overlay").hide();
        }
    };
    showActionResult();
    <%-- action 성공 여부 알림 처리 --%>
});
</script>
</content>
</body>
</html>
