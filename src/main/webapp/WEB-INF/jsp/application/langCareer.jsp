<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang="ko">
<head>
    <title>원서 작성 - 어학/경력 정보</title>
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
            font-size: 30px;
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
        .validation-error {
            background: #ffdddd;
            color: #f55;
        }
    </style>
</head>
<body>
<section class="application">
    <div class="container">
        <div id="stepContainer">
            <table width="100%">
                <tr>
                    <td id="stepStatusTitle" colspan=4 align="center" height="70px">원서 작성 현황</td>
                </tr>
                <tr id="stepTR">
                    <td id="stepBasis" width="25%" height="50px" align="center" class="stepDisabled">1. 기본 정보</td>
                    <td id="stepAcademy" width="25%" height="50px" align="center" class="stepDisabled">2. 학력 정보</td>
                    <td id="stepLangCareer" width="25%" height="50px" align="center" class="stepDisabled">3. 어학/경력 정보</td>
                    <td id="stepDocument" width="25%" height="50px" align="center" class="stepDisabled">4. 파일 첨부</td>
                </tr>
            </table>
        </div>
        <div class="spacer-mid"></div>
        <div class="row">
            <div class="col-sm-12">
                <table width="100%">
                    <tr id="tabTR">
                        <td id="tab-basis" width="25%" height="35px" align="center" class="inactiveTab" data-target-tab="basis" data-tab-available="true">1. 기본 정보</td>
                        <td id="tab-academy" width="25%" height="35px" align="center" class="inactiveTab" data-target-tab="academy" data-tab-available="false" data-unavailable-msg='<spring:message code="U321"/>'>2. 학력 정보</td>
                        <td id="tab-langCareer" width="25%" height="35px" align="center" class="inactiveTab" data-target-tab="langCareer" data-tab-available="false" data-unavailable-msg='<spring:message code="U322"/>'>3. 어학/경력 정보</td>
                        <td id="tab-document" width="25%" height="35px" align="center" class="inactiveTab" data-target-tab="document" data-tab-available="false" data-unavailable-msg='<spring:message code="U323"/>'>4. 파일 첨부</td>
                    </tr>
                </table>
            </div>
        </div>
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
                        <div class="panel panel-darkgray">
                            <div class="panel-heading">어학성적</div>
                            <div class="panel-body" id="english-score-list">
                                <c:forEach items="${langCareer.languageGroupList}" var="langGroup" varStatus="langGroupStat">
                                    <div class="panel panel-darkgray1">
                                        <div class="panel-heading">${langGroup.examGrpName}</div>
                                        <div class="panel-body" id="languageGroupList${langGroupStat.index}.list">
                                            <form:hidden path="languageGroupList[${langGroupStat.index}].examGrpName" value="${langGroup.examGrpName}" />
                                            <form:hidden path="languageGroupList[${langGroupStat.index}].examCodeGrp" value="${langGroup.examCodeGrp}"/>
                                            <form:hidden path="languageGroupList[${langGroupStat.index}].multiYn" value="${langGroup.multiYn}"/>
                                            <c:forEach items="${langGroup.langList}" var="langList" varStatus="langListStat">
                                                <div class="panel panel-default">
                                                    <div class="panel-heading">${langList.itemName}</div>
                                                    <div class="panel-body" id="languageGroupList${langGroupStat.index}.langList${langListStat.index}.list">
                                                        <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].itemName" value="${langList.itemName}"/>
                                                        <c:forEach items="${langList.subContainer}" var="subContainer" varStatus="subContainerStat">
                                                            <div class="form-group">
                                                                <div class="col-sm-3">
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
                                                                    <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].itemName" value="${subContainer.itemName}"/>
                                                                    <div class="checkbox">
                                                                        <label for="checkLang-${subContainerStat.index}">
                                                                            <c:if test='${subContainer.canYn == "Y"}'>
                                                                                <c:choose>
                                                                                    <c:when test='${langGroup.multiYn == "Y"}'>
                                                                                        <input type="checkbox" class="lang-checkbox" id="checkLang-${subContainerStat.index}" name="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].checkedFg" <c:if test="${subContainer.checkedFg == true}">checked</c:if> />
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <input type="radio" class="lang-radio" id="radioLang-${subContainerStat.index}" name="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].checkedFg" <c:if test="${subContainer.checkedFg == true}">checked</c:if> />
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </c:if>${subContainer.itemName}
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                                <c:choose>
                                                                    <c:when test='${subContainer.canYn == "Y"}'>
                                                                        <div class="col-sm-2 lang-detail-${subContainerStat.index}" style='display: <c:choose><c:when test="${subContainer.checkedFg == true}">block;</c:when><c:otherwise>none;</c:otherwise></c:choose>'>
                                                                            <c:if test="${subContainer.itemCode == '00001'}">
                                                                                <div class="input-group">
                                                                                    <form:select path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].toflTypeCode" cssClass="form-control forlInput">
                                                                                        <form:option value="" label="--선택--" />
                                                                                        <form:options items="${common.toflTypeList}" itemValue="code" itemLabel="codeVal" />
                                                                                    </form:select>
                                                                                </div>
                                                                                <spring:bind path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].toflTypeCode">
                                                                                    <c:if test="${status.error}">
                                                                                        <div class="validation-container">
                                                                                            <div class="validation-error">${status.errorMessage}</div>
                                                                                        </div>
                                                                                    </c:if>
                                                                                </spring:bind>
                                                                            </c:if>
                                                                        </div>
                                                                        <div class="col-sm-4 lang-detail-${subContainerStat.index}" style='display: <c:choose><c:when test="${subContainer.checkedFg == true}">block;</c:when><c:otherwise>none;</c:otherwise></c:choose>'>
                                                                            <div class="input-group date">
                                                                                <span class="input-group-addon">시험일</span>
                                                                                <form:input path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].examDay" cssClass="form-control forlInput" readonly="true" />
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
                                                                        <div class="col-sm-3 lang-detail-${subContainerStat.index}" style='display: <c:choose><c:when test="${subContainer.checkedFg == true}">block;</c:when><c:otherwise>none;</c:otherwise></c:choose>'>
                                                                            <div class="input-group">
                                                                                <span class="input-group-addon">점수</span>
                                                                                <form:input path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].subContainer[${subContainerStat.index}].langGrad" cssClass="form-control lang-score forlInput" data-lang-exam-name="${subContainer.itemName}" maxlength="4"/>
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
                                                                    <c:otherwise>
                                                                        <div class="col-sm-2">
                                                                            <label class="lbl-lang" id="checkLangLabel${subContainerStat.index}" >인정 불가</label>
                                                                        </div>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>

                                            </c:forEach>
                                        </div>
                                    </div>
                                </c:forEach>
                                <c:if test='${langCareer.application.deptCode != "10403"}'> <%-- 건축공학과는 면제 없음 --%>
                                    <div class="form-group required">
                                        <div class="col-sm-offset-2 col-sm-4">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" id="checkForlExmp" name="checkForlExmp" <c:if test='${langCareer.applicationGeneral.forlExmpCode != null && langCareer.applicationGeneral.forlExmpCode.length() > 0}'>checked</c:if> />외국어 성적 면제 해당자
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <form:select path="applicationGeneral.forlExmpCode" id="forlExmpCode" cssClass="form-control" disabled="true">
                                                <form:option value="" label="--선택--" />
                                                <form:options items="${common.fornExmpList}" itemValue="code" itemLabel="codeVal" />
                                            </form:select>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <div class="panel panel-darkgray">
                            <div class="panel-heading">경력 사항</div>
                            <div class="panel-body">
                                <div id="career-container" class="form-group-block-list">
                                    <c:forEach varStatus="stat" begin="0" end="${langCareer.applicationExperienceList.size() > 0 ? langCareer.applicationExperienceList.size() - 1 : 0}">
                                        <div class="form-group-block">
                                            <form:hidden path="applicationExperienceList[${stat.index}].exprSeq"/>
                                            <form:hidden path="applicationExperienceList[${stat.index}].applNo"/>
                                            <form:hidden path="applicationExperienceList[${stat.index}].saveFg"/>
                                            <form:hidden path="applicationExperienceList[${stat.index}].checkedFg" value="true"/>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">재직 기간</label>
                                                <div class="col-sm-9">
                                                    <div class="col-sm-4 start-date-container">
                                                        <div class="input-group date">
                                                            <span class="input-group-addon">입사일</span>
                                                            <form:input path="applicationExperienceList[${stat.index}].joinDay" cssClass="form-control" readonly="true" />
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
                                                            <span class="input-group-addon">퇴사일</span>
                                                            <form:input path="applicationExperienceList[${stat.index}].retrDay" cssClass="form-control" readonly="true" />
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
                                                        <label class="radio-inline"><input type="radio" class="curr-radio" id="radioCurr-${stat.index}" name="radioCurrWork" data-curr-work-id="applicationExperienceList${stat.index}.currYn" <c:if test="${langCareer.applicationExperienceList[stat.index].currYn == 'Y'}">checked</c:if> />재직중</label>
                                                        <form:hidden path="applicationExperienceList[${stat.index}].currYn"/>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="form-group">
                                                <form:label path="applicationExperienceList[${stat.index}].corpName" cssClass="col-sm-2 control-label">기관명</form:label>
                                                <div class="col-sm-9">
                                                    <div class="col-sm-12">
                                                        <form:input path="applicationExperienceList[${stat.index}].corpName" cssClass="form-control" />
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
                                                <form:label path="applicationExperienceList[${stat.index}].exprDesc" cssClass="col-sm-2 control-label">경력 내용</form:label>
                                                <div class="col-sm-9">
                                                    <div class="col-sm-12">
                                                        <form:input path="applicationExperienceList[${stat.index}].exprDesc" cssClass="form-control" />
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
                                    <div class="btn btn-info btn-add">추가</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="btn-group btn-group-justified">
                    <div class="btn-group">
                        <button id="saveLangCareer" type="button" class="btn btn-primary btn-lg btn-save" data-saveType="langCareer">어학 및 경력 저장</button>
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
        var applNo = document.getElementById('applNo').value = '${langCareer.application.applNo}',
            admsNo = document.getElementById('admsNo').value = '${langCareer.application.admsNo}',
            applStsCode = document.getElementById('applStsCode').value = '${langCareer.application.applStsCode}',
            entrYear = document.getElementById('entrYear').value = '${langCareer.application.entrYear}',
            admsTypeCode = document.getElementById('admsTypeCode').value = '${langCareer.application.admsTypeCode}';

        <%-- 원서 작성 현황 처리 --%>
        var processCurrentStep = function (applStsCode) {
            var code = Number(applStsCode),
                    stepTR = document.getElementById('stepTR'),
                    l = stepTR.children.length, i,
                    tabTR = document.getElementById('tabTR');
            for ( i = 0 ; i < code && i < l ; i++ ) {
                stepTR.children[i].className = 'stepEnabled';
                tabTR.children[i].setAttribute('data-tab-available', 'true');
                if (tabTR.children[i+1])
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
            event.preventDefault();
            var form = document.forms[0];

            form.action = "${contextPath}/application/langCareer/save";

            form.submit();
        };
        $('.btn-save').on('click', formProcess);
        <%-- 하단 버튼 처리 --%>

        <%-- 어학 성적 입력란 show/hide 처리 --%>
        $('.lang-checkbox').on('change', function () {
            var id = this.id,
                currentIndex, classToToggle;

            currentIndex = id.substr(id.lastIndexOf('-')+1),
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

            currentIndex = id.substr(id.lastIndexOf('-')+1),
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

        <%-- 외국어 성적 면제 해당 처리 --%>
        var checkForlExmp = function (isExmp) {
            $('.forlInput').each(function () {
                this.value = '';
                this.setAttribute('value', '');
                this.disabled = isExmp;
                if (this.selectedIndex)
                    this.selectedIndex = 0;
            });
            $('.lang-checkbox, .lang-radio').each(function () {
                this.checked = false;
                this.disabled = isExmp;
            });

            document.getElementById('forlExmpCode').disabled = !isExmp;
            if (!isExmp)
                document.getElementById('forlExmpCode').selectedIndex = 0;
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
