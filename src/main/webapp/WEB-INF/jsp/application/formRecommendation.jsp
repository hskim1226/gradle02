<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang='ko'>
<head>
    <title><spring:message code="L06731"/><%--추천서 등록--%></title>
    <style>
        .btn-file input[type=file] {
            position: absolute;
            top: 0;
            right: 0;
            min-width: 100%;
            min-height: 100%;
            font-size: 100px;
            text-align: right;
            filter: alpha(opacity=0);
            opacity: 0;
            background: red;
            cursor: inherit;
            display: block;
        }
        input[readonly] {
            background-color: white !important;
            cursor: text !important;
        }
    </style>
</head>
<body>
<div id="overlay" class="web_dialog_overlay"></div>
<section class="normal-white">
    <div class="container">
        <form:form id="regRec" commandName="applInfo" cssClass="form-horizontal" role="form">
            <div class="col-md-offset-1 col-md-10">
                <div class="form-group inner-container-white">
                    <div class="col-sm-offset-1 col-sm-10 text-gray">
                        <i class="fa fa-envelope-o fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L06731"/><%--추천서 등록--%></b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-12 text-gray">
                                <label class="control-label"><spring:message code="U06731"/><%--안녕하십니까...--%></label>
                            </div>
                            <div class="col-sm-12 text-gray">
                                <label class="control-label"><spring:message code="U06732"/><%--지원자의 지원 내용을 확인...--%></label>
                            </div>
                            <div class="spacer-tiny">&nbsp;</div>
                            <div class="col-sm-12 text-gray">
                                <label class="control-label"><spring:message code="L06701"/><%--지원자 정보--%></label>
                            </div>
                            <div class="col-sm-12 text-gray">
                                <table class="table">
                                    <thead>
                                        <th><spring:message code="L06702"/><%--지원자 이름--%></th>
                                        <th><spring:message code="L06703"/><%--국적--%></th>
                                        <th><spring:message code="L06704"/><%--학위--%></th>
                                        <th><spring:message code="L06705"/><%--전공--%></th>
                                    </thead>
                                    <tbody>
                                        <td>${applInfo.korName}</td>
                                        <td>${applInfo.nationality}</td>
                                        <td>${applInfo.degree}</td>
                                        <td>${applInfo.dept}</td>
                                    </tbody>
                                </table>
                            </div>

                            <%--<div class="col-sm-12 text-gray">--%>
                                <%--<label class="control-label"><spring:message code="L06702"/>&lt;%&ndash;지원자 이름&ndash;%&gt;</label> : 어쩌구--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-12 text-gray">--%>
                                <%--<label class="control-label"><spring:message code="L06703"/>&lt;%&ndash;국적&ndash;%&gt;</label> : 한국--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-12 text-gray">--%>
                                <%--<label class="control-label"><spring:message code="L06704"/>&lt;%&ndash;학위&ndash;%&gt;</label> : 석사--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-12 text-gray">--%>
                                <%--<label class="control-label"><spring:message code="L06705"/>&lt;%&ndash;전공&ndash;%&gt;</label> : 토목공학--%>
                            <%--</div>--%>
                            <div class="spacer-tiny">&nbsp;</div>
                            <div class="col-sm-3 text-gray">
                                <label class="control-label"><spring:message code="L06736"/><%--추천서 양식--%></label>
                            </div>
                            <div class="col-sm-6 text-gray">
                                <a style='vertical-align: bottom;' href="<spring:eval expression="@app.getProperty(\"path.static\")" />/etc/LetterOfRecommendation.docx">LetterOfRecommendation.docx <img src="<spring:eval expression="@app.getProperty('path.static')" />/img/logo-ms-word.png"/></a>
                            </div>
                            <div class="col-sm-3 text-gray">
                                &nbsp;
                            </div>
                            <div class="spacer-tiny">&nbsp;</div>
                            <div class="col-sm-3 text-gray">
                                <label class="control-label"><spring:message code="L06731"/><%--추천서 등록--%></label>
                            </div>
                            <div class="col-sm-6 text-gray">
                                <input type="file" class="btn btn-file" id="fileRec" name="fileRec"/>
                            </div>
                            <div class="col-sm-3">
                                <button id="btnUpload" class="btn btn-lg btn-primary btn-group-justified btn-upload"><spring:message code="L06732"/><%--업로드--%></button>
                            </div>
                            <div class="spacer-tiny">&nbsp;</div>
                            <div class="col-sm-12 text-gray">
                                <label class="control-label"><spring:message code="U06733"/><%--지원자에게 추천서 등록 완료...--%></label>
                            </div>
                            <div class="spacer-tiny">&nbsp;</div>
                            <div class="col-sm-12 text-gray">
                                <button id="btnComplete" class="btn btn-lg btn-info btn-upload btn-group-justified"><spring:message code="L06733"/><%--추천서 등록 완료--%></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <form:hidden path="recNo"/>
            <form:hidden path="applNo"/>
            <form:hidden path="recSeq"/>
        </form:form>
    </div>
</section>
<content tag="local-script">
<script type="text/javascript">
$(document).ready(function() {

    var form = document.getElementById('regRec');

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

    <%-- 하단 버튼 처리 --%>
    $('#btnComplete').click(function(e) {
        $("#overlay").show();
        e.preventDefault();
        form.action = "${contextPath}/application/recommend";
        form.method = "post";
        if (confirm('<spring:message code="U06738"/>')) { // 추천서를 등록하시겠습니까?\\n\\n"확인"을 누르시면 추천서가 등록되며 다시 수정할 수 없습니다.
            form.submit();
        } else {
            $("#overlay").hide();
        }
    });
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
