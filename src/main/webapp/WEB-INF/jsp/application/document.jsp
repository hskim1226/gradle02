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
            /*background-image: -webkit-linear-gradient(top, #7a7a7a 0%, #888888 100%);*/
            /*background-image:      -o-linear-gradient(top, #7a7a7a 0%, #888888 100%);*/
            /*background-image: -webkit-gradient(linear, left top, left bottom, from(#7a7a7a), to(#888888));*/
            /*background-image:         linear-gradient(to bottom, #7a7a7a 0%, #888888 100%);*/
            /*filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#7a7a7a', endColorstr='#ff888888', GradientType=0);*/
            /*background-repeat: repeat-x;*/
            color: #fff;
            background-color: #7a7a7a;
            border-color: #7a7a7a;
        }

        .panel-darkgray1, .panel-darkgray2, .panel-darkgray3, .panel-darkgray4 {
            /*background-color: #8c8c8c;*/
            color: #fff;
            border-color: #333333;
        }

        .panel-darkgray1 > .panel-heading {
            background-image: -webkit-linear-gradient(left, #888888 0%, #cccccc 100%);
            background-image:      -o-linear-gradient(left, #888888 0%, #cccccc 100%);
            background-image: -webkit-gradient(linear, left top, right bottom, from(#888888), to(#cccccc));
            background-image:         linear-gradient(to right, #888888 0%, #cccccc 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#888888', endColorstr='#cccccc', GradientType=1);
            background-repeat: repeat-x;
            color: #fff;
            /*background-color: #7a7a7a;*/
            border-color: #cccccc;
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

        .control-label {
            color: #333;
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

        .checkbox {
            min-height: 20px;
            margin-top: 0px;
            margin-bottom: 10px;
            color: #222;
            /*vertical-align: middle;*/
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
                <%--<div class="row">--%>
                    <%--<div class="col-sm-offset-1 col-sm-10">--%>
                        <%--<div class="panel panel-darkgray">--%>
                            <%--<div class="panel-heading">첨부 파일 안내</div>--%>
                            <%--<div class="panel-body">--%>
                                <%--<p>사진 외의 모든 첨부 파일은 서류 종류별로 하나의 PDF 파일만 업로드 가능합니다.</p>--%>

                                <%--<p>만약 한가지 종류의 서류가 여러개의 PDF 파일로 되어 있다면,--%>
                                    <%--하나의 PDF 파일로 합친 후 업로드 하시기 바랍니다.</p>--%>

                                <%--<p>PDF 파일 합치기는 전용프로그램이나 인터넷 서비스를 이용하시기 바랍니다.</p>--%>

                                <%--예)<br/>--%>
                                <%--<a href="http://convert.neevia.com/pdfmerge/" target="_blank">http://convert.neevia.com/pdfmerge/</a><br/>--%>
                                <%--<a href="http://www.pdfmerge.com/" target="_blank">http://www.pdfmerge.com/</a><br/>--%>

                                <%--<p>예시 사이트에서 발생하는 모든 문제는 당사에서 책임지지 않습니다.</p>--%>

                                <%--<p>사진 파일은 JPG, GIF, PNG 파일만 업로드 가능합니다.</p>--%>

                                <%--<p>사진 파일의 편집은 전용 프로그램이나 인터넷 서비스를 이용하시기 바랍니다.</p>--%>

                                <%--예)<br/>--%>
                                <%--<a href="http://apps.pixlr.com/editor/" target="_blank">http://apps.pixlr.com/editor/</a><br/>--%>
                                <%--<a href="http://www.fotor.com/" target="_blank">http://www.fotor.com/</a><br/>--%>
                                <%--<p>예시 사이트에서 발생하는 모든 문제는 당사에서 책임지지 않습니다.</p>--%>
                                <%--<hr/>--%>
                                <%--<p>파일 첨부 시 주의사항</p>--%>
                                <%--1. 문서별로 1개의 파일만 첨부 가능합니다.<br/>--%>
                                <%--2. 사진 및 문서의 해상도와 가독성 여부를 반드시 확인하세요.<br/>--%>
                                <%--3. 스캔시에는 300dpi 이상으로 스캔하시기 바랍니다.<br/>--%>
                                <%--4. 문서 크기는 A4 크기로 생성하여 첨부하셔야 합니다.<br/>--%>
                                <%--</p>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="spacer-tiny"></div>
                <div class="row">
                    <div class="col-sm-offset-1 col-sm-10">
                <c:forEach items="${document.documentContainerList}" var="lv1Container" varStatus="lv1Status">
                    <c:choose>
                        <c:when test="${lv1Container.lastYn == 'N'}">
                        <div class="form-group"><label class="col-sm-3 control-label word-keep-all">${lv1Container.docItemName}</label></div>
                        </c:when>
                        <c:otherwise>
                        <div class="panel panel-darkgray">
                            <div class="panel-heading">${lv1Container.grpLabel}</div>
                            <div class="panel-body" id="docContainerList${lv1Status.index}.list">

                            <c:forEach items="${lv1Container.subContainer}" var="lv2Container" varStatus="lv2Status">
                                <c:choose>
                                    <c:when test="${lv2Container.lastYn == 'Y'}">
                                <div class="form-group"><label class="col-sm-3 control-label word-keep-all">${lv2Container.docItemName}</label></div>
                                    </c:when>
                                    <c:otherwise>
                                <div class="panel panel-darkgray1">
                                    <div class="panel-heading">${lv2Container.grpLabel}</div>
                                    <div class="panel-body" id="docContainerList${lv2Status.index}.list">

                                        <c:forEach items="${lv2Container.subContainer}" var="lv3Container" varStatus="lv3Status">
                                            <c:choose>
                                                <c:when test="${lv3Container.lastYn == 'Y'}">
                                        <div class="form-group">
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].docTypeCode" value="${lv3Container.docTypeCode}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].docGrp" value="${lv3Container.docGrp}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].docItemCode" value="${lv3Container.docItemCode}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].docItemName" value="${lv3Container.docItemName}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].docName" value="${lv3Container.docName}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].fileExt" value="${lv3Container.fileExt}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].imgYn" value="${lv3Container.imgYn}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].filePath" value="${lv3Container.filePath}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].fileName" value="${lv3Container.fileName}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].orgFileName" value="${lv3Container.orgFileName}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].docItemNameXxen" value="${lv3Container.docItemNameXxen}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].docGrpName" value="${lv3Container.docGrpName}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].fileUploadFg" value="${lv3Container.fileUploadFg}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].displayGrpFg" value="${lv3Container.displayGrpFg}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].admsNo" value="${lv3Container.admsNo}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].admsCorsNo" value="${lv3Container.admsCorsNo}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].detlMajCode" value="${lv3Container.detlMajCode}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].admsCodeGrp" value="${lv3Container.admsCodeGrp}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].admsCode" value="${lv3Container.admsCode}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].grpLevel" value="${lv3Container.grpLevel}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].docItemGrp" value="${lv3Container.docItemGrp}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].upCodeGrp" value="${lv3Container.upCodeGrp}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].upCode" value="${lv3Container.upCode}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].lastYn" value="${lv3Container.lastYn}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].mdtYn" value="${lv3Container.mdtYn}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].uploadYn" value="${lv3Container.uploadYn}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].sendCnt" value="${lv3Container.sendCnt}" />
                                            <div class="col-sm-3">
                                                <div class="checkbox"><label class="word-keep-all" for="filelist0.yn"><input type="checkbox" id="filelist0.yn" name="filelist[0].yn">${lv3Container.docItemName}</label></div>
                                            </div>
                                            <div class="col-sm-5 nopadding">
                                                <div class="col-sm-5 nopadding"><input class="form-control" type="text" placeholder="서류 명 직접 입력"></div>
                                                <div class="col-sm-7 nopadding"><input type="file" class="btn btn-file" id="fileList0.docName" name="fileList[0].docName"/></div>
                                            </div>
                                            <div class="col-sm-1 nopadding">
                                                <input type="button" id="fileList0.btn" name="fileList[0].btn"
                                                       class="btn btn-default btn-upload" value="올리기"
                                                       data-file-path="fileList0.filePath"
                                                       data-file-name="fileList0.fileName"
                                                       data-org-file-name="fileList0.orgFileName"/>
                                            </div>
                                            <div class="col-sm-3 control-label">파일이름</div>
                                        </div>

                                                </c:when>
                                                <c:otherwise>
                                        <div class="panel panel-default">
                                            <div class="panel-heading">${lv3Container.grpLabel}</div>
                                            <div class="panel-body" id="docContainerList${lv3Status.index}.list">

                                                    <c:forEach items="${lv3Container.subContainer}" var="lv4Container" varStatus="lv4Status">
                                                        <c:choose>
                                                            <c:when test="${lv4Container.lastYn == 'Y'}">
                                                <div class="form-group"><label class="col-sm-3 control-label word-keep-all">${lv4Container.docItemName}</label></div>
                                                            </c:when>
                                                            <c:otherwise>
                                                <div class="panel panel-default">
                                                    <div class="panel-heading">${lv4Container.grpLabel}</div>
                                                    <div class="panel-body" id="docContainerList${lv4Status.index}.list">

                                                                <c:forEach items="${lv4Container.subContainer}" var="lv5Container" varStatus="lv5Status">
                                                                    <c:choose>
                                                                        <c:when test="${lv5Container.lastYn == 'Y'}">
                                                        <div class="form-group"><label class="col-sm-3 control-label word-keep-all">${lv5Container.docItemName}</label></div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                        <div class="panel panel-default">
                                                            <div class="panel-heading">${lv5Container.grpLabel}</div>
                                                            <div class="panel-body" id="docContainerList${lv5Status.index}.list">

                                                            <%-- 한 Depth 추가하려면 여기에 <c:forEach> ~ </c:forEach> 삽입 --%>

                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                    <c:if test="${lv5Container.lastYn == null || lv5Container.lastYn == 'N'}">
                                                             </div>
                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>

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

        <%-- 어학 성적 입력란 show/hide 처리 --%>
        $('.lang-checkbox').on('change', function () {
            var id = this.id,
                currentIndex, classToToggle;
            currentIndex = id.substr(id.lastIndexOf('-')+1);
            classToToggle = '.lang-detail-' + currentIndex;
            if (this.checked) {
                $(classToToggle).css('display', 'block');
            } else {
                $(classToToggle).css('display', 'none');
            }
        });
        $('.lang-radio').on('change', function () {
            var id = this.id,
                    currentIndex, classToShow;
            currentIndex = id.substr(id.lastIndexOf('-')+1);
            classToShow = '.lang-detail-' + currentIndex;
            $('.lang-radio').each( function () {
                if (this.checked) {
                    $(classToShow).css('display', 'block');
                } else {
                    $(classToShow).css('display', 'none');
                }
            });

        });
        <%-- 어학 성적 입력란 show/hide 처리 --%>

        <%-- 숫자, 소수점 1개만 입력 - 어학 성적 입력 --%>
        $('.lang-score').on('keyup', function () {
            var numCheckRegExp = /^[0-9]*\.?[0-9]*$/,
                    val = this.value;
            if (!numCheckRegExp.test(val)) {
                this.value = val.substr(0, val.length-1);
            }
        });
        <%-- 숫자, 소수점 1개만 입력 - 어학 성적 입력 --%>

        <%-- 어학 성적 validation --%>
        var getToeflMaxScore = function (id) {
            var toeflTypeSelectId = id.substr(0, id.lastIndexOf('.')) + '.toflTypeCode',
                toeflTypeSelect = document.getElementById(toeflTypeSelectId),
                toeflType = toeflTypeSelect.options[toeflTypeSelect.selectedIndex].innerHTML,
                maxScore;
            switch(toeflType) {
                case 'IBT':
                    maxScore = 120;
                    break;
                case 'CBT':
                    maxScore = 300;
                    break;
                case 'PBT':
                    maxScore = 677;
                    break;
            }
            return maxScore;
        };
        $('.lang-score').on('blur', function () {
            var examName = this.dataset.langExamName,
                maxScore;
            switch(examName) {
                case 'TOEFL':
                    maxScore = getToeflMaxScore(this.id);
                    break;
                case 'TOEIC':
                    maxScore = 990;
                    break;
                case 'TEPS':
                    maxScore = 990;
                    break;
                case 'IELTS':
                    maxScore = 9.0;
                    break;
                case 'GRE':
                    maxScore = 9999;
                    break;
            }
            if (this.value > maxScore) {
                alert( maxScore + '점 이하의 숫자를 입력해주세요.');
                this.focus();
            }
        });
        <%-- 어학 성적 validation --%>

        <%-- 달력 옵션 --%>
        var datePickerOption = {
            dateFormat: 'yymmdd',
            yearRange: "1950:",
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            dayNamesMin: ['일','월','화','수','목','금','토'],
            changeMonth: true, //월변경가능
            changeYear: true, //년변경가능
            showMonthAfterYear: true //년 뒤에 월 표시
        };

        <%-- 달력 시작 --%>
        $('.input-group.date>input').datepicker(datePickerOption);
        $('.calendar-addon').on('click', function () {
            $(this.parentNode).children('input')[0].focus();
        });
        <%-- 달력 끝 --%>

        <%-- 달력 reset 함수 --%>
        var resetCalendar = function (block, calendarClass) {
            $(block).find(calendarClass).datepicker('destroy');
            $(block).find(calendarClass).datepicker(datePickerOption);
        };
        <%-- 달력 reset 함수 --%>

        <%-- 외국어 성적 면제 해당 처리 --%>
        var checkForlExmp = function (isExmp) {
            $('.forlInput').each(function () {
                this.value = '';
                this.setAttribute('value', '');
                this.disabled = isExmp;
                if (this.selectedIndex) this.selectedIndex = 0;
            });
            $('.lang-checkbox, .lang-radio').each(function () {
                this.checked = false;
                this.disabled = isExmp;
            });
            document.getElementById('forlExmpCode').disabled = !isExmp;
        };

        $('#checkForlExmp').on('click', function () {
            if (this.checked) {
                if (confirm('외국어 성적 면제 해당자를 선택하면\n외국어 성적을 입력할 수 없으며,\n이미 입력한 외국어 성적도 삭제됩니다.\n\n외국어 성적 면제 해당자를 선택하시겠습니까?')) {
                    checkForlExmp(true);
                } else {
                    this.checked = false;
                }
            } else {
                checkForlExmp(false);
            }
        });
        <%-- 외국어 성적 면제 해당 처리 --%>

        <%-- form-group-block 추가/삭제에 대한 처리 시작 --%>
        <%-- id, name 재설정 시작 --%>
        var updateIdAndName = function ( block, index ) {
            var i, name, prefix, suffix, input, items, label;
            var input = block.querySelector('input');

            name = input.name;

            items = block.querySelectorAll('input, select, label');
            if (items) {
                for (i = 0; i <items.length; i++) {
                    name = items[i].name;
                    if (name) {
                        prefix = name.substring(0, name.indexOf('['));
                        suffix = name.substring(name.indexOf(']') + 1);
                        items[i].name = prefix + '[' + index + ']' + suffix;
                    }
                    var oldid = items[i].id;
                    if (oldid) {
                        prefix = oldid.substring(0, oldid.indexOf('.'));
                        prefix = prefix.replace(/[0-9]/g, '');
                        suffix = oldid.substring(oldid.indexOf('.'));
                        items[i].id = prefix + index + suffix;

                        label = block.querySelector('label[for="' + oldid + '"]');
                        if (label) {
                            label.setAttribute('for', items[i].id);
                        }
                    }
                    if (items[i].id.indexOf('userCUDType') > 0) {
                        items[i].value = "INSERT";
                    }
                }
            }
            resetCalendar(block, '.input-group.date>input');

            var removeBtn = block.querySelector('.btn-remove');
            if (removeBtn) {
                removeBtn.setAttribute('data-block-index', index);
            }
        };
        <%-- id, name 재설정 끝 --%>

        <%-- 복제된 입력폼 내용 초기화 시작 --%>
        var resetBlockContents = function ( block ) {
            var i, items, itemName;
            block.style.display = 'block';
            items = block.querySelectorAll('input, select');
            if (items) {
                for (i = 0; i <items.length; i++) {
                    if (items[i].type == 'hidden') {
                        itemName = items[i].name;
                        if (itemName.indexOf('userCUDType') > 0) {
                            items[i].value = "INSERT";
                        }
                    }
                    if (items[i].type != 'hidden' && items[i].type != 'radio' && items[i].type != 'checkbox' && items[i].type != 'button') {
                        items[i].value = '';
                        items[i].setAttribute('value', '');
                    }
                    if (items[i].checked != null) {
                        items[i].checked = false;
                    }
                }
            }
            resetCalendar(block, '.input-group.date>input');
        };
        <%-- 복제된 입력폼 내용 초기화 끝 --%>

        $('.btn-add').on('click', function(e) {
            var target = e.currentTarget ? e.currentTarget : e.target;
            var container = target.parentNode;
            while (container && !$(container).hasClass('form-group-block-list')) {
                container = container.parentNode;
            }
            var blocks = container.querySelectorAll('.form-group-block');
            var originBlock = blocks[blocks.length - 1];
            var $cloneObj;
            if (originBlock) {
                $cloneObj = $(originBlock).clone(true);
                updateIdAndName($cloneObj[0], blocks.length);
                resetBlockContents($cloneObj[0]);
                container.insertBefore($cloneObj[0], originBlock.nextSibling);
            }
        });

        $('.btn-remove').on('click', function(e) {
            var target = e.currentTarget ? e.currentTarget : e.target;
            var blockToRemove = target.parentNode;
            while (blockToRemove && !$(blockToRemove).hasClass('form-group-block')) {
                blockToRemove = blockToRemove.parentNode;
            }
            var container = blockToRemove.parentNode;
            var blocks = container.querySelectorAll('.form-group-block');
            var length = blocks.length, i;
            var blockIndex = target.dataset.blockIndex;
            var userCUDType = document.getElementById(target.dataset.listName + blockIndex + '.userCUDType');

            switch (userCUDType.value) {
                case 'INSERT' :
                    for (i = parseInt(blockIndex) + 1; i < length; i++) {
                        updateIdAndName(blocks[i], i - 1);
                    }
                    if (length <= 1) {
                        resetBlockContents(blockToRemove);
                    } else {
                        blockToRemove.parentNode.removeChild(blockToRemove);
                    }
                    break;
                case 'UPDATE' :
                    userCUDType.value = 'DELETE';
                    blockToRemove.style.display = 'none';
                    break;
            }
        });
        <%-- form-group-block 추가/삭제에 대한 처리 끝 --%>

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
