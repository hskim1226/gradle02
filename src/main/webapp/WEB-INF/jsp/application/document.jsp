<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang="ko">
<head>
    <title>원서 작성 - 파일 첨부</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
    <style>
        section.application {
            padding: 160px 0 60px;
            background: #555555;
            color: #000;
            position:relative;
        }

        section.application h2.slogan {
            color: #000;
            font-size: 36px;
            font-weight: 900;
        }

        section.application .spacer-big {
            margin-bottom: 7em;
        }

        section.application .spacer-mid {
            margin-bottom: 5em;
        }

        section.application .spacer-small {
            height: 3em;
        }

        section.application .spacer-tiny {
            height: 1em;
        }

        section.application .tab-content {
            background-color: #d0d0d0;
            color: #000;
        }

        section.application .nav>li>a {
            display: block;
        }
        .apexMessage {
            color: #000;
            font-size: 12px;
            font-weight: 900;
        }

/*
        section.application .btn {
            border: 1px;
        }

        section.application .input-group-btn .btn {
            border-radius: 4px;
        }
*/

        .panel-darkgray > .panel-heading {
            background-image: -webkit-linear-gradient(top, #7a7a7a 0%, #888888 100%);
            background-image:      -o-linear-gradient(top, #7a7a7a 0%, #888888 100%);
            background-image: -webkit-gradient(linear, left top, left bottom, from(#7a7a7a), to(#888888));
            background-image:         linear-gradient(to bottom, #7a7a7a 0%, #888888 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#7a7a7a', endColorstr='#ff888888', GradientType=0);
            background-repeat: repeat-x;
            color: #fff;
            background-color: #7a7a7a;
            border-color: #7a7a7a;
        }

        .btn-file {
            position: relative;
            overflow: hidden;
        }
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
        /*a:hover, a:visited, a:link {*/
            /*text-decoration: none;*/
            /*color: #fdfdfd;*/
        /*}*/
        body {
            font-size: 14px;
        }
        .tab-gray {
            background-color: #444444;
        }
        .nav-tabs>li.active>a,
        .nav-tabs>li.active>a:hover,
        .nav-tabs>li.active>a:focus,
        .nav-tabs>li.active>a:link {
            background-color: #f0f0f0;
            color: #333;
            cursor: pointer;
        }

        .form-group.required .control-label:after {
            content:"*";
            color:red;
        }

        .form-group-block {
            position: relative;
            /*background-color: #3c3f41;*/
            border: solid 1px #65686a;
            margin-bottom: 5px;
            padding-top: 5px;
            /*color: #ffffff;*/
        }

        .form-group-block .form-group {
            margin-bottom: 5px;
        }

        .btn-remove {
            position: absolute;
            top: 0;
            right: 0;
        }

        .btn-add {
            position: relative;
            float: right;
        }
        .btn-lang {
            display: block;
        }
        .btn-lang-disabled {
            display: none;
        }

        .show-lang .show-lang, .hide-lang .hide-lang {
            display: block;
        }

        /*.show-lang .hide-lang, .hide-lang .show-lang {*/
            /*display: none;*/
        /*}*/
        section.application .form-control-static {
            padding-top: 6px;
            padding-bottom: 6px;
            margin-bottom: 0;
        }

        /* 팝업창이 보여질 부분 */
        .bpopContainer, #popup2, .bMulti {
            background-color: #fff;
            color: #111;
            display: none;
            min-width: 450px;
            padding: 25px;
        }

        .bpopContainer, .bMulti {
            min-height: 250px;
        }
        /* 클릭할 버튼 */
        .button {
            background-color: #2b91af;
            color: #fff;
            cursor: pointer;
            display: inline-block;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
        }
        /* 닫기 버튼 */
        .button.b-close, .button.bClose {
            box-shadow: none;
            font: bold 131% sans-serif;
            padding: 0 6px 2px;
            position: absolute;
            right: -7px;
            top: -7px;
        }

        #alert-container {
            position: fixed;
            top: 110px;
            right: 10px;
            text-align: right;
            z-index: 1029;
        }

        #alert-container .alert-box {
            padding: 5px;
            display:inline-block;
        }

        .btn-search {
            width: 100%;
        }

        .ime-mode-kr {
            -webkit-ime-mode: active !important;
            -moz-ime-mode: active !important;
            -ms-ime-mode: active !important;
            ime-mode: active !important;
        }

        .engExam {
            display: none;
        }

        .nopadding {
            padding: 0 !important;
            margin: 0 !important;
        }

        .stepEnabled {
            background: #227722;
            color: #eeeeee;
        }
        .stepDisabled {
            background: #888888;
            color: #bbbbbb;
        }
        #stepStatusTitle {
            color: #eeeeee;
            font-size: 20px;
        }
        .activeTab {
            background: #d0d0d0;
            color: #333333;
            font-weight: bold;
        }
        .inactiveTab {
            background: #777777;
            color: #eeeeee;
        }
        #tabTR {
            cursor: pointer;
        }
    </style>
</head>
<body>
<section class="application">
    <div class="container">
        <div id="stepContainer">
            <table width="100%">
                <tr>
                    <td id="stepStatusTitle" colspan=4 align="center" height="50px">원서 작성 현황</td>
                </tr>
                <tr id="stepTR">
                    <td id="stepBasis" width="25%" height="30px" align="center" class="stepDisabled">1. 기본 정보</td>
                    <td id="stepAcademy" width="25%" height="30px" align="center" class="stepDisabled">2. 학력 정보</td>
                    <td id="stepLangCareer" width="25%" height="30px" align="center" class="stepDisabled">3. 어학/경력 정보</td>
                    <td id="stepDocument" width="25%" height="30px" align="center" class="stepDisabled">4. 파일 첨부</td>
                </tr>
            </table>
        </div>
        <div class="spacer-mid"></div>
        <div class="row">
            <div class="col-sm-12">
                <table width="100%">
                    <tr id="tabTR">
                        <td id="tab-basis" width="25%" height="35px" align="center" class="inactiveTab" data-target-tab="basis" data-tab-available="true">기본 정보</td>
                        <td id="tab-academy" width="25%" height="35px" align="center" class="inactiveTab" data-target-tab="academy" data-tab-available="false" data-unavailable-msg='<spring:message code="U321"/>'>학력 정보</td>
                        <td id="tab-langCareer" width="25%" height="35px" align="center" class="inactiveTab" data-target-tab="langCareer" data-tab-available="false" data-unavailable-msg='<spring:message code="U322"/>'>어학/경력 정보</td>
                        <td id="tab-document" width="25%" height="35px" align="center" class="inactiveTab" data-target-tab="document" data-tab-available="false" data-unavailable-msg='<spring:message code="U323"/>'>파일 첨부</td>
                    </tr>
                </table>
            </div>
        </div>
        <form:form commandName="document" cssClass="form-horizontal" method="post" role="form">
            <form:hidden path="application.applNo" id="applNo" />
            <form:hidden path="application.applStsCode" id="applStsCode" />
            <form:hidden path="application.admsNo" id="admsNo" />
            <form:hidden path="application.entrYear" id="entrYear" />
            <form:hidden path="application.admsTypeCode" id="admsTypeCode" />
            <div id="myTabContent" class="tab-content">
                <div class="spacer-tiny"></div>
                <div class="row">
                    <div class="col-sm-offset-1 col-sm-10">
                        <div class="panel panel-darkgray">
                            <div class="panel-heading">첨부 파일 안내</div>
                            <div class="panel-body">
                                <p>사진 외의 모든 첨부 파일은 서류 종류별로 하나의 PDF 파일만 업로드 가능합니다.</p>

                                <p>만약 한가지 종류의 서류가 여러개의 PDF 파일로 되어 있다면,
                                    하나의 PDF 파일로 합친 후 업로드 하시기 바랍니다.</p>

                                <p>PDF 파일 합치기는 전용프로그램이나 인터넷 서비스를 이용하시기 바랍니다.</p>

                                예)<br/>
                                <a href="http://convert.neevia.com/pdfmerge/" target="_blank">http://convert.neevia.com/pdfmerge/</a><br/>
                                <a href="http://www.pdfmerge.com/" target="_blank">http://www.pdfmerge.com/</a><br/>

                                <p>예시 사이트에서 발생하는 모든 문제는 당사에서 책임지지 않습니다.</p>

                                <p>사진 파일은 JPG, GIF, PNG 파일만 업로드 가능합니다.</p>

                                <p>사진 파일의 편집은 전용 프로그램이나 인터넷 서비스를 이용하시기 바랍니다.</p>

                                예)<br/>
                                <a href="http://apps.pixlr.com/editor/" target="_blank">http://apps.pixlr.com/editor/</a><br/>
                                <a href="http://www.fotor.com/" target="_blank">http://www.fotor.com/</a><br/>
                                <p>예시 사이트에서 발생하는 모든 문제는 당사에서 책임지지 않습니다.</p>
                                <hr/>
                                <p>파일 첨부 시 주의사항</p>
                                1. 문서별로 1개의 파일만 첨부 가능합니다.<br/>
                                2. 사진 및 문서의 해상도와 가독성 여부를 반드시 확인하세요.<br/>
                                3. 스캔시에는 300dpi 이상으로 스캔하시기 바랍니다.<br/>
                                4. 문서 크기는 A4 크기로 생성하여 첨부하셔야 합니다.<br/>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="spacer-tiny"></div>
                <div class="row">
                    <div class="col-sm-offset-1 col-sm-10">
                <c:forEach items="${document.documentContainerList}" var="lv1Container" varStatus="lv1Status">
                    <c:choose>
                        <c:when test="${lv1Container.lastYn == 'N'}">
                        <div class="form-group"><label class="col-sm-3 control-label word-keep-all">${lv1Container.docItemName}-${lv1Container.grpLevel}</label></div>
                        </c:when>
                        <c:otherwise>
                        <div class="panel panel-darkgray">
                            <div class="panel-heading">${lv1Container.grpLabel}-${lv1Container.grpLevel}</div>
                            <div class="panel-body" id="docContainerList${lv1Status.index}.list">
                            <c:forEach items="${lv1Container.subContainer}" var="lv2Container" varStatus="lv2Status">
                                <c:choose>
                                    <c:when test="${lv2Container.lastYn == 'Y'}">
                                <div class="form-group"><label class="col-sm-3 control-label word-keep-all">${lv2Container.docItemName}-${lv2Container.grpLevel}</label></div>
                                    </c:when>
                                    <c:otherwise>
                                <div class="panel panel-darkgray">
                                    <div class="panel-heading">${lv2Container.grpLabel}-${lv2Container.grpLevel}</div>
                                    <div class="panel-body" id="docContainerList${lv2Status.index}.list">

                                        <c:forEach items="${lv2Container.subContainer}" var="lv3Container" varStatus="lv3Status">
                                            <c:choose>
                                                <c:when test="${lv3Container.lastYn == 'Y'}">
                                        <div class="form-group"><label class="col-sm-3 control-label word-keep-all">${lv3Container.docItemName}-${lv3Container.grpLevel}</label></div>
                                                </c:when>
                                                <c:otherwise>
                                        <div class="panel panel-darkgray">
                                            <div class="panel-heading">${lv3Container.grpLabel}-${lv3Container.grpLevel}</div>
                                            <div class="panel-body" id="docContainerList${lv3Status.index}.list">

                                                    <c:forEach items="${lv3Container.subContainer}" var="lv4Container" varStatus="lv4Status">
                                                        <c:choose>

                                                            <c:when test="${lv4Container.lastYn == 'Y'}">
                                                <div class="form-group"><label class="col-sm-3 control-label word-keep-all">${lv4Container.docItemName}-${lv4Container.grpLevel}</label></div>
                                                            </c:when>
                                                            <c:otherwise>
                                                <div class="panel panel-darkgray">
                                                    <div class="panel-heading">${lv4Container.grpLabel}-${lv4Container.grpLevel}</div>
                                                    <div class="panel-body" id="docContainerList${lv4Status.index}.list">

                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:if test="${lv4Container.lastYn == null || lv4Container.lastYn == 'N'}">

                                                    </div>
                                                </div>
                                                        </c:if>

                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:if test="${lv3Container.lastYn == null || lv3Container.lastYn == 'N'}">

                                            </div>
                                        </div>
                                            </c:if>

                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                                <c:if test="${lv2Container.lastYn == null || lv2Container.lastYn == 'N'}">

                                    </div>
                                </div>
                                </c:if>

                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${lv1Container.lastYn == null || lv1Container.lastYn == 'N'}">
                            </div>
                        </div>
                    </c:if>


                </c:forEach>
                    </div>
                </div>
            <div class="spacer-tiny"></div>


                    <%--<c:if test="${lv1Container.subContainer.size() > 0}">--%>
                        <%--<div class="panel panel-darkgray">--%>
                            <%--<div class="panel-heading"><c:if test="${lv1Container.lastYn == null || lv1Container.lastYn == 'N'}">${lv1Container.grpLabel}</c:if></div>--%>
                            <%--<div class="panel-body" id="docContainerList${lv1Status.index}.list">--%>
                        <%--<c:forEach items="${lv1Container.subContainer}" var="lv2Container" varStatus="lv2Status">--%>
                            <%--<c:if test="${lv1Container.subContainer.size() > 0}">--%>
                            <%--<div class="panel panel-darkgray">--%>
                                <%--<div class="panel-heading"><c:if test="${lv1Container.lastYn == null || lv1Container.lastYn == 'N'}">${lv1Container.grpLabel}</c:if></div>--%>
                                <%--<div class="panel-body" id="docContainerList${lv1Status.index}.list">--%>




                                <%--<div class="form-group" id="docContainerList${lv1Status.index}.subContainer${lv2Status.index}.${lv2Container.docItemCode}">--%>
                                    <%--<label class="col-sm-3 control-label word-keep-all">${lv2Container.docItemName}</label>--%>
                                    <%--<div class="col-sm-8">--%>
                                        <%--<div class="input-group">--%>
                                            <%--<div class="input-group-btn">--%>
                                                <%--<input type="file" class="btn btn_lg btn-file" id="docContainerList${grpStat.index}.mandDocList${docStat.index}.docName" name="docContainerList[${grpStat.index}].mandDocList[${docStat.index}].docName"/>--%>
                                            <%--</div>--%>
                                            <%--<c:if test="${mandDoc.orgnSendYn =='Y' || mandDoc.orgnSendYn =='y'}">--%>
                                                <%--<div class="apexMessage">${mandDoc.msgNo}</div>--%>
                                            <%--</c:if>--%>
                                            <%--<c:if test="${mandDoc.orgnSendYn =='N' || mandDoc.orgnSendYn !='n'}">--%>
                                                <%--<div class="col-sm-4 nopadding"><input type="button" id="docContainerList${grpStat.index}.mandDocList${docStat.index}.btn" name="docContainerList[${grpStat.index}].mandDocList[${docStat.index}].btn"--%>
                                                                                       <%--class="btn btn-default btn-block btn-upload" value="올리기"--%>
                                                                                       <%--data-file-path="docContainerList${grpStat.index}.mandDocList${docStat.index}.filePath"--%>
                                                                                       <%--data-file-name="docContainerList${grpStat.index}.mandDocList${docStat.index}.fileName"--%>
                                                                                       <%--data-org-file-name="docContainerList${grpStat.index}.mandDocList${docStat.index}.orgFileName"/>--%>
                                                <%--</div>--%>
                                                    <%--<span class="col-sm-8" id="docContainerList${grpStat.index}.mandDocList${docStat.index}" style="text-decoration: none;">--%>
                                                        <%--<a href="${contextPath}/filedownload/attached/${entireApplication.application.admsNo}/${entireApplication.application.applNo}/${mandDoc.fileName}/${mandDoc.orgFileName}">${mandDoc.orgFileName}</a>--%>
                                                    <%--</span>--%>
                                            <%--</c:if>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<form:hidden path="documentContainerList[${grpStat.index}].mandDocList[${docStat.index}].docTypeCode" value="${mandDoc.docTypeCode}" />--%>
                                    <%--<form:hidden path="documentContainerList[${grpStat.index}].mandDocList[${docStat.index}].docGrp" value="${mandDoc.docGrp}" />--%>
                                    <%--<form:hidden path="documentContainerList[${grpStat.index}].mandDocList[${docStat.index}].docItemCode" value="${mandDoc.docItemCode}" />--%>
                                    <%--<form:hidden path="documentContainerList[${grpStat.index}].mandDocList[${docStat.index}].docItemName" value="${mandDoc.docItemName}" />--%>
                                    <%--<form:hidden path="documentContainerList[${grpStat.index}].mandDocList[${docStat.index}].filePath"  value="${mandDoc.filePath}"/>--%>
                                    <%--<form:hidden path="documentContainerList[${grpStat.index}].mandDocList[${docStat.index}].fileName"  value="${mandDoc.fileName}"/>--%>
                                    <%--<form:hidden path="documentContainerList[${grpStat.index}].mandDocList[${docStat.index}].orgFileName"  value="${mandDoc.orgFileName}"/>--%>
                                <%--</div>--%>
                        <%--</c:forEach>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="spacer-tiny"></div>--%>
                    <%--</c:if>--%>

                    <%--<c:if test = "${docContainer.subGrp.size()>0}">--%>
                        <%--<div class="panel panel-darkgray">--%>
                            <%--<div class="panel-heading">${docContainer.fileGroupName} 서류</div>--%>
                            <%--<div class="panel-body" id="docContainerList${grpStat.index}.list">--%>
                                <%--<div class="form-group-block-list" id="fuCollegeDocBlockList">--%>
                                    <%--<c:forEach items="${docContainer.subGrp}" var="subGrp" varStatus="subGrpStat">--%>
                                        <%--<div class="form-group-block">--%>
                                            <%--<c:forEach items="${subGrp.mandDocList}" var="mandDoc" varStatus="docStat">--%>
                                                <%--<hr/>--%>
                                                <%--<div class="form-group" id="docContainerList${grpStat.index}.subGrp${subGrpStat.index}.mandDocList${docStat.index}.${mandDoc.docItemCode}">--%>
                                                    <%--<label class="col-sm-3 control-label word-keep-all">${mandDoc.docItemName}</label>--%>
                                                    <%--<div class="col-sm-8">--%>
                                                        <%--<div class="input-group">--%>
                                                            <%--<div class="input-group-btn">--%>
                                                                <%--<input type="file" class="btn btn_lg btn-file" id="docContainerList${grpStat.index}.subGrp${subGrpStat.index}.mandDocList${docStat.index}.docName" name="docContainerList[${grpStat.index}].subGrp[${subGrpStat.index}].mandDocList[${docStat.index}].docName"/>--%>
                                                            <%--</div>--%>
                                                            <%--<c:if test="${mandDoc.orgnSendYn =='Y' || mandDoc.orgnSendYn =='y'}">--%>
                                                                <%--<div class="apexMessage">${mandDoc.msgNo}</div>--%>
                                                            <%--</c:if>--%>
                                                            <%--<c:if test="${mandDoc.orgnSendYn =='N' || mandDoc.orgnSendYn !='n'}">--%>
                                                                <%--<div class="col-sm-4 nopadding"><input type="button" id="docContainerList${grpStat.index}.subGrp${subGrpStat.index}.mandDocList${docStat.index}.btn" name="docContainerList[${grpStat.index}].mandDocList[${docStat.index}].btn"--%>
                                                                                                       <%--class="btn btn-default btn-block btn-upload" value="올리기"--%>
                                                                                                       <%--data-file-path="docContainerList${grpStat.index}.subGrp${subGrpStat.index}.mandDocList${docStat.index}.filePath"--%>
                                                                                                       <%--data-file-name="docContainerList${grpStat.index}.subGrp${subGrpStat.index}.mandDocList${docStat.index}.fileName"--%>
                                                                                                       <%--data-org-file-name="docContainerList${grpStat.index}.subGrp${subGrpStat.index}.mandDocList${docStat.index}.orgFileName"/>--%>
                                                                <%--</div>--%>
                                                                    <%--<span class="col-sm-8" id="docContainerList${grpStat.index}.subGrp${subGrpStat.index}.mandDocList${docStat.index}" style="text-decoration: none;">--%>
                                                                        <%--<a href="${contextPath}/filedownload/attached/${entireApplication.application.admsNo}/${entireApplication.application.applNo}/${mandDoc.fileName}/${mandDoc.orgFileName}">${mandDoc.orgFileName}</a>--%>
                                                                    <%--</span>--%>
                                                            <%--</c:if>--%>
                                                        <%--</div>--%>
                                                    <%--</div>--%>
                                                    <%--<form:hidden path="docContainerList[${grpStat.index}].subGrp[${subGrpStat.index}].mandDocList[${docStat.index}].docTypeCode" value="${mandDoc.docTypeCode}" />--%>
                                                    <%--<form:hidden path="docContainerList[${grpStat.index}].subGrp[${subGrpStat.index}].mandDocList[${docStat.index}].docGrp" value="${mandDoc.docGrp}" />--%>
                                                    <%--<form:hidden path="docContainerList[${grpStat.index}].subGrp[${subGrpStat.index}].mandDocList[${docStat.index}].docItemCode" value="${mandDoc.docItemCode}" />--%>
                                                    <%--<form:hidden path="docContainerList[${grpStat.index}].subGrp[${subGrpStat.index}].mandDocList[${docStat.index}].docItemName" value="${mandDoc.docItemName}" />--%>
                                                    <%--<form:hidden path="docContainerList[${grpStat.index}].subGrp[${subGrpStat.index}].mandDocList[${docStat.index}].filePath"  value="${mandDoc.filePath}"/>--%>
                                                    <%--<form:hidden path="docContainerList[${grpStat.index}].subGrp[${subGrpStat.index}].mandDocList[${docStat.index}].fileName"  value="${mandDoc.fileName}"/>--%>
                                                    <%--<form:hidden path="docContainerList[${grpStat.index}].subGrp[${subGrpStat.index}].mandDocList[${docStat.index}].orgFileName"  value="${mandDoc.orgFileName}"/>--%>
                                                <%--</div>--%>
                                            <%--</c:forEach>--%>
                                        <%--</div>--%>
                                    <%--</c:forEach>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</c:if>--%>
                    <%--<div class="spacer-tiny"></div>--%>
                <%--</c:forEach>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="btn-group btn-group-justified">
                    <div class="btn-group">
                        <button id="saveDocument" type="button" class="btn btn-info btn-lg btn-save" data-saveType="document">작성 완료</button>
                    </div>
                </div>
            </div> <%--myTabContent--%>
        </form:form>
    </div> <%--container--%>

</section>
<content tag="local-script">
    <script src="${contextPath}/js/jquery-ui.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
        var applNo = document.getElementById('applNo').value = '${document.application.applNo}',
            admsNo = document.getElementById('admsNo').value = '${document.application.admsNo}',
            applStsCode = document.getElementById('applStsCode').value = '${document.application.applStsCode}',
            entrYear = document.getElementById('entrYear').value = '${document.application.entrYear}',
            admsTypeCode = document.getElementById('admsTypeCode').value = '${document.application.admsTypeCode}';

        <%-- 원서 작성 현황 처리 --%>
        var processCurrentStep = function (applStsCode) {
            var code = Number(applStsCode),
                    stepTR = document.getElementById('stepTR'),
                    l = stepTR.children.length, i,
                    tabTR = document.getElementById('tabTR');
            for ( i = 0 ; i < code && i < l ; i++ ) {
                stepTR.children[i].className = 'stepEnabled';
                tabTR.children[i].setAttribute('data-tab-available', 'true');
                tabTR.children[i+1].setAttribute('data-tab-available', 'true');
            }
        };
        processCurrentStep(document.getElementById('applStsCode').value);
        <%-- 원서 작성 현황 처리 --%>

        <%-- active 탭 표시 --%>
        var setActiveTab = function () {
            var urlStr = document.location.pathname,
                    substrToFirstSlash = urlStr.substring(0, urlStr.lastIndexOf("/")),
                    targetTD = document.getElementById('tab-' + substrToFirstSlash.substring(substrToFirstSlash.lastIndexOf("/") + 1));

            targetTD.className = "activeTab";
        };
        setActiveTab();
        <%-- active 탭 표시 --%>

        <%-- 탭 이동 처리 --%>
        var gotoTab = function (targetTab) {
            var form = document.forms[0];
            form.action = '${contextPath}/application/' + targetTab + '/edit';
            form.submit();
        };
        $('.inactiveTab').each( function(i) {
            $(this).on('click', function() {
                if (this.getAttribute('data-tab-available') === 'true') {
                    gotoTab(this.getAttribute("data-target-tab"));
                } else {
                    confirm(this.getAttribute('data-unavailable-msg'));
                }
            });
        });
        <%-- 탭 이동 처리 --%>

        <%-- 하단 버튼 처리 --%>
        var formProcess = function(event) {
            var form = document.forms[0];

            form.action = "${contextPath}/application/document/save";
            form.submit();
        };
        $('.btn-save').on('click', formProcess);
        <%-- 하단 버튼 처리 --%>

        <%-- 단어 잘림 방지 --%>
        $('.word-keep-all').wordBreakKeepAll();

        <%-- action 성공 여부 알림 처리 --%>
        var showActionResult = function() {
            var msg = '${resultMsg}';
            if (msg.length > 0) {
                confirm(msg);
            }
        };
        showActionResult();
        <%-- action 성공 여부 알림 처리 --%>
    });
    </script>
</content>
</body>
</html>
