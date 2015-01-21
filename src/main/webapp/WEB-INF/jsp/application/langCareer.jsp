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
                    <td id="stepFileUpload" width="25%" height="30px" align="center" class="stepDisabled">4. 파일 첨부</td>
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
                        <td id="tab-fileUpload" width="25%" height="35px" align="center" class="inactiveTab" data-target-tab="fileUpload" data-tab-available="false" data-unavailable-msg='<spring:message code="U323"/>'>파일 첨부</td>
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
                        <div class="panel panel-default">
                            <div class="panel-heading">어학성적</div>
                            <div class="panel-body" id="english-score-list">
                            <c:forEach items="${langCareer.languageGroupList}" var="langGroup" varStatus="langGroupStat">
                                <c:forEach items="${langGroup.langList}" var="langList" varStatus="langListStat">
                                <div class="form-group required">
                                    <form:hidden path="languageGroupList[${langGroupStat.index}]" value="${langGroup.examCodeGrp}"/>
                                    <label class="col-sm-2 control-label">${langGroup.examGrpName}</label>
                                    <div class="col-sm-2">
                                        <form:hidden path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].itemCode" value="${langList.docItemCode}"/>
                                        <div class="checkbox">
                                            <label for="checkLang-${langListStat.index}"><c:if test='${langList.canYn == "Y"}'><input type="checkbox" class="lang-checkbox" id="checkLang-${langListStat.index}" <c:if test="${langList.langGrad != null && langList.langGrad.length() > 0}">checked</c:if>/></c:if>${langList.itemName}</label>
                                        </div>
                                    </div>
                                    <c:choose>
                                    <c:when test='${langList.canYn == "Y"}'>

                                    <div class="col-sm-2 lang-detail-${langListStat.index}" style='display: <c:choose><c:when test="${langList.langGrad != null && langList.langGrad.length() > 0}">block;</c:when><c:otherwise>none;</c:otherwise></c:choose>'>
                                            <c:if test="${langList.itemCode == '00001'}">
                                        <form:select path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].toflTypeCode" cssClass="form-control">
                                            <form:option value="" label="--선택--" />
                                            <form:options items="${common.toflTypeList}" itemValue="code" itemLabel="codeVal" />
                                        </form:select>
                                            </c:if>
                                    </div>
                                    <div class="col-sm-3 lang-detail-${langListStat.index}" style='display: <c:choose><c:when test="${langList.langGrad != null && langList.langGrad.length() > 0}">block;</c:when><c:otherwise>none;</c:otherwise></c:choose>'>
                                        <div class="input-group date">
                                            <span class="input-group-addon">시험일</span>
                                            <form:input path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].examDay" cssClass="form-control" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                    <div class="col-sm-2 lang-detail-${langListStat.index}" style='display: <c:choose><c:when test="${langList.langGrad != null && langList.langGrad.length() > 0}">block;</c:when><c:otherwise>none;</c:otherwise></c:choose>'>
                                        <div class="input-group">
                                            <span class="input-group-addon">점수</span>
                                            <form:input path="languageGroupList[${langGroupStat.index}].langList[${langListStat.index}].langGrad" cssClass="form-control" />
                                        </div>
                                    </div>

                                    </c:when>
                                    <c:otherwise>
                                    <div class="col-sm-2">
                                        <label class="lbl-lang" id="checkLangLabel${langListStat.index}" >인정 불가</label>
                                    </div>
                                    </c:otherwise>
                                    </c:choose>
                                </div>
                                </c:forEach>
                            </c:forEach>

                                <div class="form-group required">
                                    <div class="col-sm-offset-2 col-sm-4">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" />외국어 성적 면제 해당자
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-sm-5">
                                        <form:select path="applicationGeneral.forlExmpCode" id="forlExamCode" cssClass="form-control" items="${common.fornExmpList}" itemValue="code" itemLabel="codeVal" />
                                    </div>
                                </div>
                            </div>
                            <%--<div class="panel-body" id="english-score-list">--%>
                                <%--<c:forEach items="${common.langExamList}" var="langExam" varStatus="stat">--%>
                                    <%--<div class="form-group hide-lang required">--%>
                                    <%--<c:choose>--%>
                                        <%--<c:when test="${stat.index == 0}">--%>
                                        <%--<label class="col-sm-2 control-label">영어</label>--%>
                                        <%--<div class="col-sm-2">--%>
                                        <%--</c:when>--%>
                                        <%--<c:otherwise>--%>
                                            <%--<div class="col-sm-offset-2 col-sm-2">--%>
                                        <%--</c:otherwise>--%>
                                    <%--</c:choose>--%>
                                                <%--<input type="hidden" name="languageGroupList[${stat.index}].langExamCode" id="languageGroupList${stat.index}.langExamCode" value="${langExam.examCode}" />--%>
                                                <%--<div class="checkbox">--%>
                                                    <%--<label for="checkLang${stat.index}"><input type="checkbox" class="btn-lang-disabled lang-checkbox" id="checkLang${stat.index}" <c:if test="langCareer.languageGroupList['${stat.index}'] != null">checked</c:if>/>${langExam.examName}</label>--%>
                                                <%--</div>--%>
                                    <%--<c:choose>--%>
                                        <%--<c:when test="${stat.index == 0}">--%>
                                            <%--</div>--%>
                                        <%--</c:when>--%>
                                        <%--<c:otherwise>--%>
                                        <%--</div>--%>
                                        <%--</c:otherwise>--%>
                                    <%--</c:choose>--%>
                                        <%--<div class="col-sm-2 show-lang">--%>
                                    <%--<c:if test="${langExam.examCode == '00001'}">--%>
                                                <%--<form:select path="languageGroupList[${stat.index}].toflTypeCode" cssClass="form-control">--%>
                                                    <%--<form:option value="" label="--선택--" />--%>
                                                    <%--<form:options items="${common.toflTypeList}" itemValue="code" itemLabel="codeVal" />--%>
                                                <%--</form:select>--%>
                                    <%--</c:if>--%>
                                        <%--</div>--%>
                                        <%--<div class="col-sm-2 hide-lang">--%>
                                            <%--<label class="lbl-lang" id="checkLangLabel${stat.index}" >인정 불가</label>--%>
                                        <%--</div>--%>
                                        <%--<div class="col-sm-3 show-lang">--%>
                                            <%--<div class="input-group date">--%>
                                                <%--<span class="input-group-addon">시험일</span>--%>
                                                <%--<form:input path="languageGroupList[${stat.index}].examDay" cssClass="form-control" />--%>
                                                <%--<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="col-sm-2 show-lang">--%>
                                            <%--<div class="input-group">--%>
                                                <%--<span class="input-group-addon">점수</span>--%>
                                                <%--<form:input path="languageGroupList[${stat.index}].langGrad" cssClass="form-control" />--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</c:forEach>--%>
                                <%--<div class="form-group required">--%>
                                    <%--<div class="col-sm-offset-2 col-sm-4">--%>
                                        <%--<div class="checkbox">--%>
                                            <%--<label>--%>
                                                <%--<input type="checkbox" />외국어 성적 면제 해당자--%>
                                            <%--</label>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="col-sm-5">--%>
                                        <%--<form:select path="applicationGeneral.forlExmpCode" id="forlExamCode" cssClass="form-control" items="${common.fornExmpList}" itemValue="code" itemLabel="codeVal" />--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">경력 사항</div>
                            <div class="panel-body">
                                <div id="career-container" class="form-group-block-list">
                                    <c:forEach varStatus="stat" begin="0" end="${langCareer.applicationExperienceList.size() > 0 ? langCareer.applicationExperienceList.size() - 1 : 0}">
                                        <div id="career-info" class="form-group-block">
                                            <div class="form-group required">
                                                <label class="col-sm-2 control-label">재직 기간</label>
                                                <div class="col-sm-4 start-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">입사일</span>
                                                        <form:input path="applicationExperienceList[${stat.index}].joinDay" cssClass="form-control" readonly="true" />
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4 end-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">퇴사일</span>
                                                        <form:input path="applicationExperienceList[${stat.index}].retrDay" cssClass="form-control" readonly="true" />
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group required">
                                                <form:label path="applicationExperienceList[${stat.index}].corpName" cssClass="col-sm-2 control-label">기관명</form:label>
                                                <div class="col-sm-9">
                                                    <form:input path="applicationExperienceList[${stat.index}].corpName" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group required">
                                                <form:label path="applicationExperienceList[${stat.index}].exprDesc" cssClass="col-sm-2 control-label">직위명</form:label>
                                                <div class="col-sm-9">
                                                    <form:input path="applicationExperienceList[${stat.index}].exprDesc" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div class="btn btn-remove">
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
                        <button id="saveLangCareer" type="button" class="btn btn-info btn-lg btn-save" data-saveType="langCareer">어학 및 경력 저장</button>
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

            form.action = "${contextPath}/application/langCareer/save";
            form.submit();
        };
        $('.btn-save').on('click', formProcess);
        <%-- 하단 버튼 처리 --%>

        <%-- lang detail --%>
        $('.lang-checkbox').on('click', function () {
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
        <%-- lang detail --%>

        <%-- 어학 목록 시작 --%>
//        var updateLanguagePanel= function () {
            <%--var detlMajCode = $('#detlMajCode').val();--%>
            <%--if (detlMajCode === '-' || detlMajCode === '') {--%>
                <%--var groups = $('#english-score-list').children('.form-group');--%>
                <%--for (var i = 0, len = groups.length; i < len; i++) {--%>
                    <%--updateLanguageGroup(groups[i], []);--%>
                <%--}--%>
                <%--return;--%>
            <%--}--%>
            <%--var baseUrl = '${contextPath}/common/code';--%>
            <%--var url;--%>
            <%--var admsNo = $('#admsNo').val();--%>
            <%--var applAttrCode = $('#applAttrCode').val();--%>
            <%--if (applAttrCode == '00001') {--%>
                <%--baseUrl += '/general';--%>
                <%--url = admsNo + '/' + $('#deptCode').val() + '/' + $('#corsTypeCode').val() + '/' + detlMajCode;--%>
            <%--} else if (applAttrCode == '00002') {--%>
                <%--baseUrl += '/ariInst';--%>
                <%--url = admsNo + '/' + $('#deptCode').val() + '/' + $('#ariInstCode').val() + '/' + $('#corsTypeCode').val() + '/' + detlMajCode;--%>
            <%--} else if (applAttrCode == '00003') {--%>
                <%--baseUrl += '/general';--%>
                <%--url = admsNo + '/' + $('#deptCode').val() + '/' + $('#corsTypeCode').val() + '/' + detlMajCode;--%>
            <%--}--%>

            <%--$.ajax({--%>
                <%--type: 'GET',--%>
                <%--url: baseUrl + '/engMdtYn/' + url,--%>
                <%--success: function(e) {--%>
                    <%--if (e.result == 'SUCCESS') {--%>
                        <%--&lt;%&ndash; 학과면제인정 보이지 않게 처리 &ndash;%&gt;--%>
                        <%--$('#forlExmpCode').children('option').each(function () {--%>
                            <%--if (this.value && this.value == '6') {--%>
                                <%--$(this).hide(e.data == 'Y' || e.data == 'y');--%>
                            <%--}--%>
                        <%--});--%>
                    <%--}--%>
                <%--},--%>
                <%--error: function(e) {}--%>
            <%--});--%>
            <%--$.ajax({--%>
                <%--type: 'GET',--%>
                <%--url: baseUrl + "/availableEngExam/" + url,--%>
                <%--success: function(e) {--%>
                    <%--if (e.result == 'SUCCESS') {--%>
                        <%--var data = JSON && JSON.parse(e.data) || $.parseJSON(e.data);--%>
                        <%--var groups = $('#english-score-list').children('.form-group');--%>
                        <%--for (var i = 0, len = groups.length; i < len; i++) {--%>
                            <%--updateLanguageGroup(groups[i], data);--%>
                        <%--}--%>
                    <%--}--%>
                <%--},--%>
                <%--error: function(e) {}--%>
            <%--});--%>
        <%--};--%>
        <%--updateLanguagePanel();--%>

//        var updateLanguageGroup = function (group, data) {
//            var langExamCode = $(group).find('input').filter('[name$="langExamCode"]')[0];
//            var check = $(group).find('.btn-lang, .btn-lang-disabled')[0];
//            var checkLabel = $(group).find('.lbl-lang')[0];
//            if (check) {
//                var val = langExamCode ? langExamCode.value : null;
//                var isExist = false, item;
//                for (var j = 0, len = data.length; j < len; j++) {
//                    item = data[j];
//                    if (val == item['examCode']) {
//                        if ('Y' == item['canYn'] || 'y' == item['canYn']) {
//                            check.className = 'btn-lang';
//                            check.removeAttribute('disabled');
//                            isExist = true;
//                            $(checkLabel).text('제출 가능');
//                        }
//                        break;
//                    }
//                }
//                if (!isExist) {
//                    check.className = 'btn-lang-disabled';
//                    checkLabel.text ='제출 불가';
//                    check.setAttribute('disabled', 'disabled');
//                    $(group).removeClass('show-lang');
//                    $(group).addClass('hide-lang');
//                }
//            }
//        };

        <%-- 졸업구분의 졸업선택시 --%>
        $('.degr-radio').on('change', function(e) {
            var target =this;
            var parent = $(target).parents('.form-group')[0];
            var childRadioVal = $(parent).find("input[type=radio]:checked").val();

            if (childRadioVal =='00001') {
                $(parent).find('.degr-div').show();
                $(parent).find('.degr-no').show('');
                $(parent).find('.degr-message').hide('');
            } else {
                $(parent).find('.degr-div').hide();
                $(parent).find('.degr-no').hide();
                $(parent).find('.degr-no').val('');
                $(parent).find('.degr-message').show('');
            }
        });

        <%-- 어학 체크박스 클릭 시 처리 시작 --%>
        $('.btn-lang, .btn-lang-disabled').on('click', function(e) {
            var target = this;
            var parent = $(target).parents('.form-group')[0];
            var cn;
            if (parent) {
                if (target.checked) {
                    $(parent).removeClass('hide-lang');
                    $(parent).addClass('show-lang');
                } else {
                    $(parent).removeClass('show-lang');
                    $(parent).addClass('hide-lang');
                }
            }
        });
        <%-- 어학 목록 --%>

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
        $('.input-daterange>input').datepicker(datePickerOption);
        <%-- 달력 끝 --%>

        <%-- form-group-block 추가/삭제에 대한 처리 시작 --%>
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
                $cloneObj.find('.input-group.date>input').datepicker('destroy');
                updateIdAndName($cloneObj[0], blocks.length);
                eraseContents($cloneObj[0]);
                container.insertBefore($cloneObj[0], originBlock.nextSibling);
                $cloneObj.find('.input-group.date>input').datepicker(datePickerOption);
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

            for (i = 0; i < length; i++) {
                if (blockToRemove == blocks[i]) {
                    break;
                }
            }

            for (i = i + 1; i < length; i++) {
                updateIdAndName(blocks[i], i - 1);
            }

            if (length <= 1) {
                eraseContents(blockToRemove);
            } else {
                blockToRemove.parentNode.removeChild(blockToRemove);
            }

            mustCheckedOneRadio();
        });

        <%-- id, name 재설정 시작 --%>
        function updateIdAndName( block, index ) {
            var i, name, prefix, suffix, input, items, label;
            var input = block.querySelector('input');

            name = input.name;

            items = block.querySelectorAll('input, select');
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
                }
            }

            var removeBtn = block.querySelector('.btn-remove');
            if (removeBtn) {
                removeBtn.setAttribute('data-block-index', index);
            }
        }
        <%-- id, name 재설정 끝 --%>

        <%-- 복제된 입력폼 내용 초기화 시작 --%>
        function eraseContents( block ) {
            var i, items, itemName;
            items = block.querySelectorAll('input, select');
            if (items) {
                for (i = 0; i <items.length; i++) {
                    if (items[i].type == 'hidden') {
                        itemName = items[i].name;
                        items[i].value = itemName.indexOf('acadType') < 0 ? '' : items[i].value ;
                    }
                    if (items[i].type != 'hidden' && items[i].type != 'radio' && items[i].type != 'checkbox' && items[i].type != 'button') {
                        items[i].value = '';
                    }
                    if (items[i].checked != null) {
                        items[i].checked = false;
                    }
                    if (items[i].type == 'button') {
                        $(items[i]).removeClass('btn-info');
                        $(items[i]).addClass('btn-default');
                        $(items[i]).val('올리기');
                    }
                    if (items[i].type == 'file') {
                        $(items[i]).val('');
                    }
                }
            }
        }
        <%-- 복제된 입력폼 내용 초기화 끝 --%>
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
