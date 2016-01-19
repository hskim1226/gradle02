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
        <form:form id="regRec" commandName="applInfo" cssClass="form-horizontal" role="form" enctype="multipart/form-data">
            <div class="col-md-offset-1 col-md-10">
                <div class="form-group inner-container-white">
                    <div class="col-sm-offset-1 col-sm-10 text-gray">
                        <i class="fa fa-envelope-o fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L06731"/><%--추천서 등록--%></b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-12 text-gray">
                                <label class="control-label"><spring:message code="U06731" arguments="${applInfo.recommenderName}"/><%--안녕하십니까...--%></label>
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
                                        <td>${applInfo.engName} ${applInfo.korName == "" || applInfo.korName == null ? "" : (applInfo.korName)}</td>
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
                            <div class="col-sm-6 text-gray">
                                <label class="text-gray"><spring:message code="L06736"/><%--추천서 양식--%></label>
                            </div>
                            <div class="col-sm-6 text-gray">
                                <a style='vertical-align: bottom;' href="<spring:eval expression="@app.getProperty(\"path.static\")" />/etc/LetterOfRecommendation.docx"><img src="<spring:eval expression="@app.getProperty('path.static')" />/img/logo-ms-word.jpg"/> LetterOfRecommendation.docx</a>
                            </div>
                            <div class="spacer-tiny">&nbsp;</div>
                            <div class="col-sm-6 text-gray">
                                <label class="text-gray"><spring:message code="L06731"/><%--추천서 등록--%></label>
                            </div>
                            <div class="col-sm-6 text-gray">
                                <input type="file" class="btn btn-file" id="fileRec" name="fileRec"/>
                            </div>
                            <%--<div class="col-sm-3">--%>
                                <%--<button id="btnUpload" class="btn btn-lg btn-primary btn-group-justified btn-upload"><spring:message code="L06732"/>&lt;%&ndash;업로드&ndash;%&gt;</button>--%>
                                <%--<button id="btnDownload" class="btn btn-lg btn-primary btn-group-justified btn-upload"><spring:message code="L06732"/>&lt;%&ndash;업로드&ndash;%&gt;</button>--%>
                                <%--<button id="btnDelete" class="btn btn-lg btn-primary btn-group-justified btn-upload"><spring:message code="L06732"/>&lt;%&ndash;업로드&ndash;%&gt;</button>--%>
                            <%--</div>--%>
                            <div class="spacer-tiny">&nbsp;</div>
                            <div class="col-sm-12 text-gray">
                                <label class="text-gray" style="text-align: left"><spring:message code="U06733"/><%--지원자에게 추천서 등록 완료...--%></label>
                            </div>
                            <div class="spacer-tiny">&nbsp;</div>
                            <div class="col-sm-12 text-gray">
                                <button id="btnComplete" class="btn btn-lg btn-primary btn-upload btn-group-justified"><spring:message code="L06733"/><%--추천서 등록 완료--%></button>
                            </div>
                            <div id='waitMsg' class="col-sm-12" style="font-size: 24px; color: #55aaff; text-align: center; display: none;"><spring:message code="U06904"/></div>  <%--File is being generated. Over 1 min--%>
                        </div>
                    </div>
                </div>
            </div>
            <form:hidden path="recNo"/>
            <form:hidden path="applNo"/>
            <form:hidden path="recSeq"/>
            <form:hidden path="admsNo"/>
            <form:hidden path="fileUploadedYn"/>
            <form:hidden path="userId"/>

            <input type="hidden" name="docTypeCode" value="${docTypeCode}"/>
            <input type="hidden" name="docItemCode" value="${docItemCode}"/>
            <input type="hidden" name="docItemName" value="${docItemName}"/>
            <input type="hidden" name="docItemNameXxen" value="${docItemNameXxen}"/>
            <input type="hidden" id="lang" name="lang" value="${lang}"/>

            <input type="hidden" id="recKey"/>
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

    <%-- 파일 업로드 버튼 이벤트 --%>
    <%--$('.btn-upload').on('click', function (e) {--%>
        <%--e.preventDefault();--%>
        <%--$("#overlay").show();--%>
        <%--var actionUrl = "${contextPath}/application/recommend/fileupload",--%>
                <%--fileInputId = 'fileRec',--%>
                <%--fileInput = document.getElementById(fileInputId),--%>
                <%--fileInputName = fileInput.getAttribute("name"),--%>
                <%--fileName = fileInput.value,--%>
<%--//                targetFileDownloadLinkId = 'targetFileDownloadLinkId',--%>
<%--//                targetFileDeleteLinkId = 'targetFileDeleteLinkId',--%>
                <%--targetOrgFileNameHiddenId = 'orgFileName',--%>
                <%--targetSubContainerId = 'targetSubContainerId',--%>
                <%--regexpPDF = (/\.(pdf)$/i),--%>
                <%--extIsOk = false,--%>
                <%--targetButton = this;--%>
        <%--if (fileName.length > 80) {--%>
            <%--alert('<spring:message code="U04513"/>');  /*파일 경로가 너무 깁니다. \\n\\n파일을 PC의 바탕화면이나 D: 드라이브 바로 아래로 복사하신 후에 업로드해 주세요.*/--%>
            <%--$('#overlay').hide();--%>
            <%--return false;--%>
        <%--}--%>

        <%--if ((fileInput.files && fileInput.files.length) || fileInput.value != "") {--%>
            <%--if (regexpPDF.test(fileName)) {--%>
                <%--extIsOk = true;--%>
            <%--} else {--%>
                <%--alert('<spring:message code="U04504"/>');//첨부파일은 PDF 파일만 업로드 할 수 있습니다.--%>
                <%--$('#overlay').hide();--%>
                <%--return false;--%>
            <%--}--%>

            <%--if (extIsOk) {--%>
                <%--$.ajaxFileUpload({--%>
                    <%--url: actionUrl,--%>
                    <%--secureuri: false,--%>
                    <%--fileElementId: fileInputId,--%>
                    <%--dataType: 'json',--%>
                    <%--data: {--%>
                        <%--docSeq: '0',--%>
                        <%--docTypeCode: '00007',--%>
                        <%--docGrp: '-1',--%>
                        <%--docItemCode: '00050',--%>
                        <%--docItemName: '이메일추천서',--%>
                        <%--grpLabel: '',--%>
                        <%--fileExt: '',--%>
                        <%--imgYn: 'N',--%>
                        <%--filePath: 'filePath',--%>
                        <%--fileName: 'fileName',--%>
                        <%--orgFileName: 'orgFileName',--%>
                        <%--docItemNameXxen: 'E-mail Recommendation Letter',--%>
                        <%--docGrpName: '',--%>
                        <%--fileUploadFg: false,--%>
                        <%--displayGrpFg: false,--%>
                        <%--checkedFg: '',--%>
                        <%--admsCorsNo: '',--%>
                        <%--detlMajCode: '',--%>
                        <%--admsCodeGrp: '',--%>
                        <%--admsCode: '',--%>
                        <%--grpLevel: '2',--%>
                        <%--docItemGrp: 'DOC_ITEM',--%>
                        <%--upCodeGrp: 'DOC_TYPE',--%>
                        <%--upCode: '00007',--%>
                        <%--lastYn: 'Y',--%>
                        <%--mdtYn: 'Y',--%>
                        <%--uploadYn: document.getElementById('fileUploadedYn').value == 'Y' ? true : false,--%>
                        <%--sendCnt: '2',--%>

                        <%--fieldName: fileInputName,--%>
                        <%--targetButton: this.id,--%>
<%--//                        targetFileDownloadLinkId: targetFileDownloadLinkId,--%>
<%--//                        targetFileDeleteLinkId: targetFileDeleteLinkId,--%>
                        <%--applNo: document.getElementById('applNo').value,--%>
                        <%--admsNo: document.getElementById('admsNo').value,--%>
                        <%--recKey: '${recKey}',--%>
                        <%--pplicantId: '${applicantId}'--%>
                    <%--},--%>
                    <%--success: function (data, status) {--%>
                        <%--var d = JSON.parse(data.data);--%>
                        <%--if (data.result == 'SUCCESS') {--%>
                            <%--var targetBtnId = d.targetButton,--%>
                                    <%--targetBtn = document.getElementById(targetBtnId),--%>
                                    <%--$targetBtn = $(targetBtn),--%>
                                    <%--originalFileName = d.originalFileName,--%>
<%--//                                    targetFileDownloadLinkId = d.targetFileDownloadLinkId,--%>
<%--//                                    targetFileDeleteLinkId = d.targetFileDeleteLinkId,--%>
                                    <%--applNo = d.applNo,--%>
                                    <%--oneDocument = d.oneDocument,--%>
                                    <%--docSeq = oneDocument.docSeq,--%>
                                    <%--oneDocumentHidden;--%>
                            <%--$targetBtn.removeClass("btn-default");--%>
                            <%--$targetBtn.removeClass("btn-danger");--%>
                            <%--$targetBtn.addClass("btn-info");--%>
                            <%--$targetBtn.val("<spring:message code="U04508"/>");//올리기 성공--%>

                            <%--&lt;%&ndash;document.getElementById(targetFileDownloadLinkId).parentNode.style.display = 'block';&ndash;%&gt;--%>
                            <%--&lt;%&ndash;document.getElementById(targetFileDownloadLinkId).setAttribute('href', '${contextPath}/application/document/fileDownload/' + applNo + '/' + docSeq);&ndash;%&gt;--%>

                            <%--&lt;%&ndash;document.getElementById(targetFileDeleteLinkId).parentNode.style.display = 'block';&ndash;%&gt;--%>
                            <%--&lt;%&ndash;document.getElementById(targetFileDeleteLinkId).setAttribute('href', '${contextPath}/application/document/fileDelete/' + applNo + '/' + docSeq);&ndash;%&gt;--%>

                            <%--document.getElementById('orgFileName').value = originalFileName;--%>

                            <%--for (var key in oneDocument) {--%>
                                <%--oneDocumentHidden = document.getElementById(targetSubContainerId + key);--%>
                                <%--if (oneDocumentHidden) {--%>
                                    <%--oneDocumentHidden.value = oneDocument[key];--%>
                                <%--}--%>
                            <%--}--%>
                            <%--alert(d.resultMessage);--%>
                        <%--} else {--%>
                            <%--alert(data.message);--%>
                        <%--}--%>

                        <%--$('#overlay').hide();--%>
                    <%--},--%>
                    <%--error: function (data, status, e) {--%>
<%--//                            var d = JSON.parse(data.data);--%>
                        <%--$(targetButton).removeClass("btn-default"),--%>
                                <%--$(targetButton).addClass("btn-danger"),--%>
                                <%--$(targetButton).val("<spring:message code="U04506"/>");//올리기 실패--%>
<%--//                            if(console) {--%>
<%--//                                console.log("data : ", data);--%>
<%--//                                console.log("status : ", status);--%>
<%--//                                console.log("e : ", e);--%>
<%--//                            }--%>
                        <%--$('#overlay').hide();--%>
                    <%--}--%>
                <%--});--%>
            <%--}--%>

        <%--} else {--%>
            <%--alert("<spring:message code="U04505"/>");//파일을 선택해 주십시오--%>
            <%--$('#overlay').hide();--%>
        <%--}--%>


        <%--return false;--%>
    <%--});--%>
    <%-- 파일 업로드 버튼 이벤트 --%>

    <%-- 하단 버튼 처리 --%>
    $('#btnComplete').click(function(e) {
        var fileInput = document.getElementById('fileRec'),
                fileName = fileInput.value,
                regexpPDF = (/\.(pdf)$/i);
        if ((fileInput.files && fileInput.files.length) || fileInput.value != "") {
            if (regexpPDF.test(fileName)) {
                $("#overlay").show();
                e.preventDefault();
                form.action = "${contextPath}/application/recommend";
                form.method = "post";
                console.dir(document.getElementById('fileRec'));
                if (confirm('<spring:message code="U06738"/>')) { // 추천서를 등록하시겠습니까?\\n\\n"확인"을 누르시면 추천서가 등록되며 다시 수정할 수 없습니다.
                    document.getElementById('waitMsg').style.display = 'block';
                    form.submit();
                } else {
                    $("#overlay").hide();
                }
            } else {
                alert('<spring:message code="U04504"/>');//첨부파일은 PDF 파일만 업로드 할 수 있습니다.
                $('#overlay').hide();
                return false;
            }
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
