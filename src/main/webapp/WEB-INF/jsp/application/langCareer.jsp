<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang="ko">
<head>
    <title><spring:message code="L03101"/><%--원서 작성 - 어학/경력 정보--%></title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
    <style>
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

        section.application .nav>li>a {
            display: block;
        }
        .apexMessage {
            color: #000;
            font-size: 12px;
            font-weight: 900;
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
        .activeTab {
            background: #d0d0d0;
            color: #333333;
            font-weight: bold;
        }
        .checkbox {
            min-height: 20px;
            margin-top: 0px;
            margin-bottom: 10px;
            color: #222;
            /*vertical-align: middle;*/
        }
        .validation-error {
            background: #ffdddd;
            color: #f55;
        }
    </style>
</head>
<body>
<section class="application">
    <div class="container">
        <p id="stepStatusTitle" colspan=4 align="center" height="70px">${msg.getMessage('L01001', locale)}<%--원서 작성 현황--%></p>
        <!-- 진행상태바 시작 -->
        <div class="step_wrap">
            <ul class="step_box" id="step_box">
                <li class="inactive"><span class="step"><strong>1</strong></span>
                    <p class="txt1"><spring:message code="L01002"/><%--기본 정보--%></p>
                </li>
                <li class="inactive"><span class="step"><strong>2</strong></span>
                    <p class="txt1"><spring:message code="L01003"/><%--학력 정보--%></p>
                </li>
                <li class="inactive"><span class="step"><strong>3</strong></span>
                    <p class="txt1"><spring:message code="L01004"/><%--어학/경력 정보--%></p>
                </li>
                <li class="inactive"><span class="step"><strong>4</strong></span>
                    <p class="txt1"><spring:message code="L01005"/><%--파일 첨부 및 제출--%></p>
                </li>
                <li class="inactive"><span class="step"><strong>5</strong></span>
                    <p class="txt1"><spring:message code="L01006"/><%--결제--%></p>
                </li>
            </ul>
        </div>
        <!-- /진행상태바 끝 -->
        <!-- 데스크탑 탭메뉴 시작 -->
        <div id="pc_tab" class="nav_wrap clearfix tab-container">
            <ul id="navPcTabUL" class="nav nav-tabs nav-justified">
                <li id="tab-pc-basis" class="inactive inactiveTab" data-target-tab="basis" data-tab-available="true"><a><spring:message code="L01002"/><%--1. 기본 정보--%></a></li>
                <li id="tab-pc-academy" class="inactive inactiveTab" data-target-tab="academy" data-tab-available="false" data-unavailable-msg='<spring:message code="U321"/>'><a><spring:message code="L01003"/><%--2. 학력 정보--%></a></li>
                <li id="tab-pc-langCareer" class="inactive inactiveTab" data-target-tab="langCareer" data-tab-available="false" data-unavailable-msg='<spring:message code="U322"/>'><a><spring:message code="L01004"/><%--3. 어학/경력 정보--%></a></li>
                <li id="tab-pc-document" class="inactive inactiveTab" data-target-tab="document" data-tab-available="false" data-unavailable-msg='<spring:message code="U323"/>'><a><spring:message code="L01005"/><%--4. 파일 첨부--%></a></li>
            </ul>
        </div>
        <!-- /데스크탑 탭메뉴 끝 -->
        <!-- 모바일 탭메뉴 시작 -->
        <div id="mb_tab" class="nav_wrap clearfix tab-container">
            <ul id="navMbTabUL" class="nav nav-pills nav-stacked">
                <li id="tab-mb-basis" class="inactive inactiveTab" data-target-tab="basis" data-tab-available="true"><a><spring:message code="L01002"/><%--1. 기본 정보--%></a></li>
                <li id="tab-mb-academy" class="inactive inactiveTab" data-target-tab="academy" data-tab-available="false" data-unavailable-msg='<spring:message code="U321"/>'><a><spring:message code="L01003"/><%--2. 학력 정보--%></a></li>
                <li id="tab-mb-langCareer" class="inactive inactiveTab" data-target-tab="langCareer" data-tab-available="false" data-unavailable-msg='<spring:message code="U322"/>'><a><spring:message code="L01004"/><%--3. 어학/경력 정보--%></a></li>
                <li id="tab-mb-document" class="inactive inactiveTab" data-target-tab="document" data-tab-available="false" data-unavailable-msg='<spring:message code="U323"/>'><a><spring:message code="L01005"/><%--4. 파일 첨부--%></a></li>
            </ul>
        </div>
        <!-- 모바일 탭메뉴 끝 -->
        <form:form commandName="langCareer" cssClass="form-horizontal" method="post" role="form">
            <form:hidden path="application.applNo" id="applNo" />
            <form:hidden path="application.applStsCode" id="applStsCode" />
            <form:hidden path="application.admsNo" id="admsNo" />
            <form:hidden path="application.entrYear" id="entrYear" />
            <form:hidden path="application.admsTypeCode" id="admsTypeCode" />
            <div id="myTabContent" class="tab-content">
                <div class="spacer-tiny"></div>
                <div class="row">
                    <div class="col-sm-offset-1 col-sm-10">
                        <div class="panel panel-darkgray0">
                            <div class="panel-heading"><spring:message code="L03102"/><%--어학성적--%></div>
                            <div class="panel-body" id="english-score-list">
                                <c:forEach items="${langCareer.languageGroupList}" var="langGroup" varStatus="langGroupStat">
                                    <div class="panel panel-darkgray1">
                                        <div class="panel-heading">${pageContext.response.locale == 'en' ? langGroup.examGrpNameXxen : langGroup.examGrpName}</div>
                                        <div class="panel-body" id="languageGroupList${langGroupStat.index}.list">
                                            <form:hidden path="languageGroupList[${langGroupStat.index}].examGrpName" value="${langGroup.examGrpName}" />
                                            <form:hidden path="languageGroupList[${langGroupStat.index}].examGrpNameXxen" value="${langGroup.examGrpNameXxen}" />
                                            <form:hidden path="languageGroupList[${langGroupStat.index}].examCodeGrp" value="${langGroup.examCodeGrp}"/>
                                            <form:hidden path="languageGroupList[${langGroupStat.index}].multiYn" value="${langGroup.multiYn}"/>
                                            <c:forEach items="${langGroup.langList}" var="langList" varStatus="langListStat">
                                                <div class="panel panel-default">
                                                    <div class="panel-heading">${pageContext.response.locale == 'en' ? langList.itemNameXxen : langList.itemName}</div>
                                                    <div class="panel-body" id="languageGroupList${langGroupStat.index}.langList${langListStat.index}.list">
                                                        <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].itemName" value="${langList.itemName}"/>
                                                        <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].itemNameXxen" value="${langList.itemNameXxen}"/>
                                                        <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].itemCode" value="${langList.itemCode}"/>
                                                        <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].itemGrpCode" value="${langList.itemGrpCode}"/>
                                                        <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].selGrpCode" value="${langList.selGrpCode}"/>
                                                        <c:forEach items="${langList.subContainer}" var="subContainer" varStatus="subContainerStat">
                                                            <div class="form-group">
                                                                <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].applNo" value="${subContainer.applNo}"/>
                                                                <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].langSeq" value="${subContainer.langSeq}"/>
                                                                <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].langExamGrp" value="${subContainer.langExamGrp}"/>
                                                                <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].docItemCode" value="${subContainer.docItemCode}"/>
                                                                <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].itemGrpCode" value="${subContainer.itemGrpCode}"/>
                                                                <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].itemCode" value="${subContainer.itemCode}"/>
                                                                <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].docGrp" value="${subContainer.docGrp}"/>
                                                                <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].docSeq" value="${subContainer.docSeq}"/>
                                                                <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].fileUploadFg" value="${subContainer.fileUploadFg}"/>
                                                                <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].langInfoSaveFg" value="${subContainer.langInfoSaveFg}"/>
                                                                <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].canYn" value="${subContainer.canYn}"/>
                                                                <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].lastYn" value="${subContainer.lastYn}"/>
                                                                <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].selGrpCode" value="${subContainer.selGrpCode}"/>
                                                                <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].itemName" value="${subContainer.itemName}"/>
                                                                <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].itemNameXxen" value="${subContainer.itemNameXxen}"/>
                                                                <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].subCodeGrp" value="${subContainer.subCodeGrp}"/>
                                                                <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].exmpYn" value="${subContainer.exmpYn}"/>
                                                                <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].mdtSeq" value="${subContainer.mdtSeq}"/>
                                                                <div class="col-sm-3">
                                                                    <%--<c:if test="${langList.selGrpCode != 'ENG_EXMP1' && langList.selGrpCode != 'KOR_EXMP1'}">--%>
                                                                        <div class="checkbox">
                                                                            <%--<c:if test='${subContainer.canYn == "Y"}'>--%>
                                                                                <c:choose>
                                                                                    <c:when test='${langList.itemCode == "00002"}'>
                                                                                        <label for="checkForlExmp-${langGroupStat.index}">
                                                                                            <input type="checkbox" class="checkboxForlExmp"
                                                                                                   id="checkForlExmp-${langGroupStat.index}"
                                                                                                   name="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].checkedFg"
                                                                                                   ${subContainer.checkedFg == true ? 'checked' : ''}/>
                                                                                            ${pageContext.response.locale == 'en' ? subContainer.itemNameXxen : subContainer.itemName}
                                                                                        </label>
                                                                                    </c:when>
                                                                                    <c:when test='${langGroup.multiYn == "Y"}'>
                                                                                        <label for="checkLang-${langGroupStat.index}-${langListStat.index}-${subContainerStat.index}">
                                                                                            <input type="checkbox" class="langCheckbox-${langGroupStat.index}-${langListStat.index}-${subContainerStat.index} langCheckbox-${langGroupStat.index} langCheckbox"
                                                                                                   id="checkLang-${langGroupStat.index}-${langListStat.index}-${subContainerStat.index}"
                                                                                                   name="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].checkedFg"
                                                                                                    ${subContainer.checkedFg == true ? 'checked' : ''}/>
                                                                                            ${pageContext.response.locale == 'en' ? subContainer.itemNameXxen : subContainer.itemName}
                                                                                        </label>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <label for="checkLang-${langGroupStat.index}-${langListStat.index}-${subContainerStat.index}">
                                                                                            <input type="radio" class="langRadio-${langGroupStat.index}-${langListStat.index}-${subContainerStat.index} langCheckbox-${langGroupStat.index} langCheckbox"
                                                                                                   id="radioLang-${langGroupStat.index}-${langListStat.index}-${subContainerStat.index}"
                                                                                                   name="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].checkedFg"
                                                                                                    ${subContainer.checkedFg == true ? 'checked' : ''}/>
                                                                                            ${pageContext.response.locale == 'en' ? subContainer.itemNameXxen : subContainer.itemName}
                                                                                        </label>
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            <%--</c:if>--%>
                                                                        </div>
                                                                    <%--</c:if>--%>
                                                                </div>
                                                                <c:choose>
                                                                    <%--<c:when test='${subContainer.canYn == "Y"}'>--%>
                                                                    <c:when test='${langList.itemCode == "00001"}'>
                                                                        <div class="col-sm-2 langDetail-${langGroupStat.index}-${langListStat.index}-${subContainerStat.index} langDetail-${langGroupStat.index}" style='display: ${subContainer.checkedFg == true ? 'block;' : 'none;'}'>
                                                                            <%--<c:if test="${subContainer.itemGrpCode == 'LANG_EXAM' && subContainer.itemCode == '00001'}">--%>
                                                                                <%--<div class="input-group">--%>
                                                                                    <%--<form:select path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].toflTypeCode" cssClass="form-control forlInput-${langGroupStat.index}">--%>
                                                                                        <%--<form:option value="" label="--선택--" />--%>
                                                                                        <%--<form:options items="${common.toflTypeList}" itemValue="code"--%>
                                                                                                      <%--itemLabel="${pageContext.response.locale == 'en' ? 'codeValXxen' : 'codeVal'}"/>--%>
                                                                                    <%--</form:select>--%>
                                                                                <%--</div>--%>
                                                                                <%--<spring:bind path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].toflTypeCode">--%>
                                                                                    <%--<c:if test="${status.error}">--%>
                                                                                        <%--<div class="validation-container">--%>
                                                                                            <%--<div class="validation-error">${status.errorMessage}</div>--%>
                                                                                        <%--</div>--%>
                                                                                    <%--</c:if>--%>
                                                                                <%--</spring:bind>--%>
                                                                                <c:if test="${subContainer.subCodeList.size() > 0}">
                                                                                <div class="input-group">
                                                                                    <form:select path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].subCode"
                                                                                            cssClass="form-control forlInput-${langGroupStat.index}">
                                                                                        <form:option value="" label="--${msg.getMessage('L01011', locale)}--" />
                                                                                        <form:options items="${langCareer.languageGroupList[langGroupStat.index].langList[langListStat.index].subContainer[subContainerStat.index].subCodeList}" itemValue="code"
                                                                                                      itemLabel="${pageContext.response.locale == 'en' ? 'codeValXxen' : 'codeVal'}"/>
                                                                                    </form:select>
                                                                                </div>
                                                                                </c:if>
                                                                        <%--<spring:bind path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].toflTypeCode">--%>
                                                                            <%--<c:if test="${status.error}">--%>
                                                                                <%--<div class="validation-container">--%>
                                                                                    <%--<div class="validation-error">${status.errorMessage}</div>--%>
                                                                                <%--</div>--%>
                                                                            <%--</c:if>--%>
                                                                        <%--</spring:bind>--%>
                                                                        <spring:bind path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].subCode">
                                                                            <c:if test="${status.error}">
                                                                                <div class="validation-container">
                                                                                    <div class="validation-error">${status.errorMessage}</div>
                                                                                </div>
                                                                            </c:if>
                                                                        </spring:bind>
                                                                            <%--</c:if>--%>
                                                                        </div>
                                                                        <div class="col-sm-4 langDetail-${langGroupStat.index}-${langListStat.index}-${subContainerStat.index} langDetail-${langGroupStat.index}" style='display: ${subContainer.checkedFg == true ? 'block;' : 'none;'}'>
                                                                            <div class="input-group date">
                                                                                <span class="input-group-addon"><spring:message code="L03103"/><%--시험일--%></span>
                                                                                <form:input path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].examDay" cssClass="form-control forlInput-${langGroupStat.index}" readonly="true" />
                                                                                <span class="input-group-addon calendar-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                                            </div>
                                                                            <spring:bind path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].examDay">
                                                                                <c:if test="${status.error}">
                                                                                    <div class="validation-container">
                                                                                        <div class="validation-error">${status.errorMessage}</div>
                                                                                    </div>
                                                                                </c:if>
                                                                            </spring:bind>
                                                                        </div>
                                                                        <div class="col-sm-3 langDetail-${langGroupStat.index}-${langListStat.index}-${subContainerStat.index} langDetail-${langGroupStat.index}" style='display: ${subContainer.checkedFg == true ? 'block;' : 'none;'}'>
                                                                            <div class="input-group">
                                                                                <span class="input-group-addon"><spring:message code="L03104"/><%--점수--%></span>
                                                                                <c:choose>
                                                                                    <c:when test="${subContainer.itemName == 'IELTS'}">
                                                                                        <form:select path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].langGrad" cssClass="form-control">
                                                                                            <form:option value="" label="--${msg.getMessage('L01011', locale)}--" />
                                                                                            <form:options items="${common.ieltsLevelList}" itemValue="code" itemLabel="codeVal" />
                                                                                        </form:select>
                                                                                    </c:when>
                                                                                    <c:when test="${subContainer.itemName == 'TOPIK'}">
                                                                                        <form:select path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].langGrad" cssClass="form-control">
                                                                                            <form:option value="" label="--${msg.getMessage('L01011', locale)}--" />
                                                                                            <form:options items="${common.topikLevelList}" itemValue="code" itemLabel="codeVal" />
                                                                                        </form:select>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <form:input path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].langGrad" cssClass="form-control lang-score forlInput-${langGroupStat.index}" data-lang-exam-name="${subContainer.itemName}" maxlength="4"/>
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </div>
                                                                            <spring:bind path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].langGrad">
                                                                                <c:if test="${status.error}">
                                                                                    <div class="validation-container">
                                                                                        <div class="validation-error">${status.errorMessage}</div>
                                                                                    </div>
                                                                                </c:if>
                                                                            </spring:bind>
                                                                        </div>
                                                                    </c:when>
                                                                    <%--<c:when test="${langList.selGrpCode == 'ENG_EXMP1' || langList.selGrpCode == 'ENG_EXMP2' || langList.selGrpCode == 'KOR_EXMP1' }">--%>
                                                                    <c:when test="${langList.itemCode == '00002'}">
                                                                        <c:if test='${langCareer.application.deptCode != "10403"}'> <%-- 건축공학과는 면제 없음 --%>
                                                                            <div class="col-sm-9">
                                                                                <div id="forlExmpSelect-${langGroupStat.index}" style="display: ${subContainer.checkedFg ? 'block;' : 'none;'}" >
                                                                                    <%--<form:select path="applicationGeneral.forlExmpCode" id="forlExmpCode-${langGroupStat.index}" cssClass="form-control forlExmpCode"--%>
                                                                                    <form:select path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].subCode"
                                                                                                 id="forlExmpCode-${langGroupStat.index}" cssClass="form-control forlExmpCode">
                                                                                                 <%--data-selGrpCode-id="languageGroupList${langGroupStat.index}.langList${langListStat.index}.subContainer${subContainerStat.index}.selGrpCode">--%>
                                                                                        <form:option value="" label="--${msg.getMessage('L01011', locale)}--" />
                                                                                        <%--<form:options items="${common.fornExmpList}" itemValue="code"--%>
                                                                                        <form:options items="${langCareer.languageGroupList[langGroupStat.index].langList[langListStat.index].subContainer[subContainerStat.index].subCodeList}" itemValue="code"
                                                                                                      itemLabel="${pageContext.response.locale == 'en' ? 'codeValXxen' : 'codeVal'}"/>
                                                                                    </form:select>
                                                                                </div>
                                                                                <spring:bind path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].selGrpCode">
                                                                                    <c:if test="${status.error}">
                                                                                        <div class="validation-container">
                                                                                            <div class="validation-error">${status.errorMessage}</div>
                                                                                        </div>
                                                                                    </c:if>
                                                                                </spring:bind>
                                                                            </div>
                                                                        </c:if>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <div class="col-sm-2">
                                                                            <label class="lbl-lang" id="checkLangLabel${subContainerStat.index}" ><spring:message code="L03105"/><%--인정 불가--%></label>
                                                                        </div>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                    <spring:bind path="languageGroupList[${langGroupStat.index}]">
                                        <c:if test="${status.error}">
                                            <div class="validation-container">
                                                <div class="validation-error">${status.errorMessage}</div>
                                            </div>
                                        </c:if>
                                    </spring:bind>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="panel panel-darkgray0">
                            <div class="panel-heading"><spring:message code="L03201"/><%--경력 사항--%></div>
                            <div class="panel-body">
                                <div id="career-container" class="form-group-block-list">
                                    <c:forEach varStatus="stat" begin="0" end="${langCareer.applicationExperienceList.size() > 0 ? langCareer.applicationExperienceList.size() - 1 : 0}">
                                        <div class="form-group-block">
                                            <form:hidden path="applicationExperienceList[${stat.index}].exprSeq"/>
                                            <form:hidden path="applicationExperienceList[${stat.index}].applNo"/>
                                            <form:hidden path="applicationExperienceList[${stat.index}].saveFg"/>
                                            <form:hidden path="applicationExperienceList[${stat.index}].checkedFg" value="true"/>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"><spring:message code="L03202"/><%--재직 기간--%></label>
                                                <div class="col-sm-9">
                                                    <div class="col-sm-4 start-date-container">
                                                        <div class="input-group date">
                                                            <span class="input-group-addon"><spring:message code="L03203"/><%--입사일--%></span>
                                                            <form:input path="applicationExperienceList[${stat.index}].joinDay" cssClass="form-control checkDate" readonly="true"
                                                                    data-startDate="applicationExperienceList${stat.index}.joinDay"
                                                                    data-endDate="applicationExperienceList${stat.index}.retrDay"/>
                                                            <span class="input-group-addon calendar-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                        </div>
                                                <spring:bind path="applicationExperienceList[${stat.index}].joinDay">
                                                    <c:if test="${status.error}">
                                                        <div class="validation-container">
                                                            <div class="validation-error">${status.errorMessage}</div>
                                                        </div>
                                                    </c:if>
                                                </spring:bind>
                                                    </div>
                                                    <div class="col-sm-4 end-date-container">
                                                        <div class="input-group date">
                                                            <span class="input-group-addon"><spring:message code="L03204"/><%--퇴사일--%></span>
                                                            <form:input path="applicationExperienceList[${stat.index}].retrDay" cssClass="form-control checkDate" readonly="true"
                                                                    data-startDate="applicationExperienceList${stat.index}.joinDay"
                                                                    data-endDate="applicationExperienceList${stat.index}.retrDay"/>
                                                            <span class="input-group-addon calendar-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                        </div>
                                                <spring:bind path="applicationExperienceList[${stat.index}].retrDay">
                                                    <c:if test="${status.error}">
                                                        <div class="validation-container">
                                                            <div class="validation-error">${status.errorMessage}</div>
                                                        </div>
                                                    </c:if>
                                                </spring:bind>
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <label class="radio-inline"><input type="radio" class="curr-radio" id="radioCurr-${stat.index}" name="radioCurrWork"
                                                                                           data-curr-work-id="applicationExperienceList${stat.index}.currYn"
                                                                                           ${langCareer.applicationExperienceList[stat.index].currYn == 'Y' ? 'checked' : ''} /><spring:message code="L03205"/><%--재직중--%></label>
                                                        <form:hidden path="applicationExperienceList[${stat.index}].currYn"/>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="form-group">
                                                <form:label path="applicationExperienceList[${stat.index}].corpName" cssClass="col-sm-2 control-label"><spring:message code="L03206"/><%--기관명--%></form:label>
                                                <div class="col-sm-9">
                                                    <div class="col-sm-12">
                                                        <form:input path="applicationExperienceList[${stat.index}].corpName" maxlength="100" cssClass="form-control" />
                                                    </div>
                                            <spring:bind path="applicationExperienceList[${stat.index}].corpName">
                                                <c:if test="${status.error}">
                                                    <div class="col-sm-12 validation-container">
                                                        <div class="validation-error">${status.errorMessage}</div>
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="applicationExperienceList[${stat.index}].exprDesc" cssClass="col-sm-2 control-label"><spring:message code="L03207"/><%--경력 내용--%></form:label>
                                                <div class="col-sm-9">
                                                    <div class="col-sm-12">
                                                        <form:input path="applicationExperienceList[${stat.index}].exprDesc" maxlength="300" cssClass="form-control" />
                                                    </div>
                                            <spring:bind path="applicationExperienceList[${stat.index}].exprDesc">
                                                <c:if test="${status.error}">
                                                    <div class="col-sm-12 validation-container">
                                                        <div class="validation-error">${status.errorMessage}</div>
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                                </div>
                                            </div>
                                            <div class="btn btn-remove" data-block-index="${stat.index}" data-list-name="applicationExperienceList">
                                                <button type="button" class="close" aria-hidden="true">×</button>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    <div class="btn btn-info btn-add"><spring:message code="L03106"/><%--추가--%></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="btn-group btn-group-justified">
                    <div class="btn-group">
                        <button id="saveLangCareer" type="button" class="btn btn-primary btn-lg btn-save" data-saveType="langCareer"><spring:message code="L03301"/><%--어학 및 경력 저장--%></button>
                    </div>
                </div>
            </div> <%--myTabContent--%>
        </form:form>
    </div> <%--container--%>

</section>
<content tag="local-script">
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery-ui.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
        var applNo = document.getElementById('applNo').value = '${langCareer.application.applNo}',
            admsNo = document.getElementById('admsNo').value = '${langCareer.application.admsNo}',
            applStsCode = document.getElementById('applStsCode').value = '${langCareer.application.applStsCode}',
            entrYear = document.getElementById('entrYear').value = '${langCareer.application.entrYear}',
            admsTypeCode = document.getElementById('admsTypeCode').value = '${langCareer.application.admsTypeCode}';

        <%-- 원서 작성 현황 처리 --%>
        var processCurrentStep = function (applStsCode) {
            var code = Number(applStsCode),
                    stepBox = document.getElementById('step_box'),
                    l = stepBox.children.length, i,
                    navPcTabUL = document.getElementById('navPcTabUL'),
                    navMbTabUL = document.getElementById('navMbTabUL');
            for ( i = 0 ; i < code && i < l ; i++ ) {
                stepBox.children[i].className = 'active';
                navPcTabUL.children[i].setAttribute('data-tab-available', 'true');
                if (navPcTabUL.children[i+1])
                    navPcTabUL.children[i+1].setAttribute('data-tab-available', 'true');
                navMbTabUL.children[i].setAttribute('data-tab-available', 'true');
                if (navMbTabUL.children[i+1])
                    navMbTabUL.children[i+1].setAttribute('data-tab-available', 'true');
            }
        };
        processCurrentStep(document.getElementById('applStsCode').value);
        <%-- 원서 작성 현황 처리 --%>

        <%-- active 탭 표시 --%>
        var setActiveTab = function () {
            var urlStr = document.location.pathname,
                    substrToLastSlash = urlStr.substring(0, urlStr.lastIndexOf("/")),
                    tabName = substrToLastSlash.substring(substrToLastSlash.lastIndexOf("/") + 1),
                    targetPcTabLI = document.getElementById('tab-pc-' + tabName),
                    targetMbTabLI = document.getElementById('tab-mb-' + tabName);

            targetPcTabLI.className = 'active activeTab';
            targetMbTabLI.className = 'active activeTab';
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
            event.preventDefault();
            var form = document.forms[0];

            form.action = "${contextPath}/application/langCareer/save";

            form.submit();
        };
        $('.btn-save').on('click', formProcess);
        <%-- 하단 버튼 처리 --%>

        <%-- 어학 성적 입력란 show/hide 처리 --%>
        $('.langCheckbox').on('change', function () {
            var id = this.id,
                currentIndex, classToToggle;

            currentIndex = id.substr(id.indexOf('-')),
            classToToggle = '.langDetail' + currentIndex;
            if (this.checked) {
                $(classToToggle).css('display', 'block');
            } else {
                $(classToToggle).css('display', 'none');
            }
        });
        $('.langRadio').on('change', function () {
            var id = this.id,
                currentIndex, classToShow;

            currentIndex = id.substr(id.indexOf('-')),
            classToShow = '.langDetail' + currentIndex;

            $('.langRadio').each( function () {
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
//            var examName = this.dataset.langExamName,
            var examName = this.getAttribute('data-lang-exam-name'),
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

        <%-- 달력 선후 관계 체크 --%>
        $('.checkDate').on('change', function(e) {
            var entrDate = document.getElementById(this.getAttribute('data-startDate')).value,
                    exprDate = document.getElementById(this.getAttribute('data-endDate')).value;
            if ( entrDate != '' && exprDate != '') {
                if (parseInt(entrDate) > parseInt(exprDate)) {
                    alert('시작일은 종료일보다 앞선 날짜여야 합니다.');
                    this.value = '';
                }
            }

        });
        <%-- 달력 선후 관계 체크 --%>

        <%-- 외국어 성적 면제 해당 처리 --%>
        var checkForlExmp = function (isExmp, checkbox) {
            var basePosition = checkbox.id.indexOf('-'),
                langGroupIndex = checkbox.id.substr(basePosition),
                targetClassString = '.langCheckbox' + langGroupIndex + ', .langRadio' + langGroupIndex,
                targetLangDetailString = '.langDetail' + langGroupIndex;
            $('.forlInput' + langGroupIndex).each(function () {
                this.value = '';
                this.setAttribute('value', '');
                this.disabled = isExmp;
                if (this.selectedIndex)
                    this.selectedIndex = 0;
            });
            $(targetClassString).each(function () {
                this.checked = false;
                this.disabled = isExmp;
            });
            $(targetLangDetailString).each(function () {
                this.style.display = 'none';
            });

            document.getElementById('forlExmpSelect' + langGroupIndex).style.display = isExmp ? 'block' : 'none';
            document.getElementById('forlExmpCode' + langGroupIndex).disabled = !isExmp;
            if (!isExmp)
                document.getElementById('forlExmpCode' + langGroupIndex).selectedIndex = 0;
        };

        $('.checkboxForlExmp').on('click', function () {
            if (this.checked) {
                if (confirm('<spring:message code="U03101"/>\n<spring:message code="U03102"/>\n<spring:message code="U03103"/>\n\n<spring:message code="U03104"/>')) {
                <%--외국어 성적 면제 해당자를 선택하면 외국어 성적을 입력할 수 없으며, 이미 입력한 외국어 성적도 삭제됩니다. 외국어 성적 면제 해당자를 선택하시겠습니까?--%>
                    checkForlExmp(true, this);
                } else {
                    this.checked = false;
                }
            } else {
                checkForlExmp(false, this);
            }
        });

//        $('.forlExmpCode').on('change', function () {
//            var selGrpCodeHidden = document.getElementById(this.getAttribute('data-selGrpCode-id'));
//            selGrpCodeHidden.value = this[this.selectedIndex].value;
//        });
        <%-- 외국어 성적 면제 해당 처리 --%>

        <%-- 재직중 처리 --%>
        var checkCurrentWorking = function () {
            $('.curr-radio').each( function () {
                var currWorkId = this.getAttribute('data-curr-work-id'),
                    currYn = document.getElementById(currWorkId);
                if (this.checked) {
                    currYn.value = 'Y';
                    this.value = 'on';
                } else {
                    currYn.value = 'N';
                    this.value = 'off';
                }
            });
        };
        $('.curr-radio').on('click', checkCurrentWorking);
        <%-- 재직중 처리 --%>

        <%-- form-group-block 추가/삭제에 대한 처리 시작 --%>
        <%-- id, name 재설정 시작 --%>
        var updateIdAndName = function ( block, index ) {
            var i, name, prefix, suffix, input, items, itemsl, label, attrs, attrsl, j, dataVId;
            var input = block.querySelector('input');

            name = input.name;

            items = block.querySelectorAll('input, select, label');

            if (items) {
                itemsl = items.length;
                for (i = 0; i <itemsl ; i++) {
                    name = items[i].name;
                    attrs = items[i].attributes;
                    attrsl = attrs.length;
                    for ( j = 0 ; j < attrsl ; j++ ) {
                        if (attrs[j].name.indexOf('data-') === 0) {
                            dataVId = attrs[j].value;
                            prefix = dataVId.substring(0, dataVId.indexOf('.'));
                            prefix = prefix.replace(/[0-9]/g, '');
                            suffix = dataVId.substring(dataVId.indexOf('.'));
                            attrs[j].value = prefix + index + suffix;
                        }
                    }
                    if (name) {
                        if (items[i].type != 'radio') {
                            prefix = name.substring(0, name.indexOf('['));
                            suffix = name.substring(name.indexOf(']') + 1);
                            items[i].name = prefix + '[' + index + ']' + suffix;
                        }
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
                        if (itemName.indexOf('saveFg') > 0) {
                            items[i].value = "false";
                            items[i].setAttribute('value', 'false');
                        }
                        if (itemName.indexOf('checkedFg') > 0) {
                            items[i].value = "true";
                            items[i].setAttribute('value', 'true');
                        }
                        if (itemName.indexOf('exprSeq') > 0) {
                            items[i].value = "0";
                            items[i].setAttribute('value', '0');
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
//            var blockIndex = target.dataset.blockIndex;
            var blockIndex = target.getAttribute('data-block-index');
            var listName = target.getAttribute('data-list-name');
            var saveFg = document.getElementById(listName + blockIndex + '.saveFg');
            var checkedFg = document.getElementById(listName + blockIndex + '.checkedFg');

            if (saveFg.value == 'true') {
                checkedFg.value = 'false';
                checkedFg.setAttribute('value', 'false');
                blockToRemove.style.display = 'none';
            } else {
                for (i = parseInt(blockIndex) + 1; i < length; i++) {
                    updateIdAndName(blocks[i], i - 1);
                }
                if (length <= 1) {
                    resetBlockContents(blockToRemove);
                } else {
                    blockToRemove.parentNode.removeChild(blockToRemove);
                }
            }

            // switch (checkedFg) {
            //     case 'INSERT' :
            //         for (i = parseInt(blockIndex) + 1; i < length; i++) {
            //             updateIdAndName(blocks[i], i - 1);
            //         }
            //         if (length <= 1) {
            //             resetBlockContents(blockToRemove);
            //         } else {
            //             blockToRemove.parentNode.removeChild(blockToRemove);
            //         }
            //         break;
            //     case 'UPDATE' :
            //         userCUDType.value = 'DELETE';
            //         blockToRemove.style.display = 'none';
            //         break;
            // }
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
