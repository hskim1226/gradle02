<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang="ko">
<head>
    <title>${msg.getMsg('L01101')}<%--원서 작성 - 기본 정보--%></title>
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

        .show-lang .hide-lang, .hide-lang .show-lang {
            display: none;
        }
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
        .validation-error {
            background: #ffdddd;
            color: #f55;
        }
        .input-info {
            background: #ddddff;
            color: #55c;
        }
    </style>
</head>
<body>
<div id="overlay" class="web_dialog_overlay"></div>
<%-- SYSADMIN 일 경우 안내 배너 표시 --%>
<c:if test="${isSYSADMIN}">
    <div>
        <span style="position: fixed; z-index: 900; text-align: center;" class="btn-group-justified btn-lg btn-danger">
            THIS IS SYSADMIN, Real USER_ID : ${basis.application.userId}, APPL_NO : ${basis.application.applNo}
        </span>
    </div>
</c:if>
<div class="spacer-tiny">&nbsp;</div>
<%-- SYSADMIN 일 경우 안내 배너 표시 --%>
<section class="application">
    <div class="container">
        <p id="stepStatusTitle" colspan=4 align="center" height="70px">${msg.getMsg('L01001', locale)}<%--원서 작성 현황--%></p>
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
        <form:form commandName="basis" cssClass="form-horizontal" method="post" role="form">
            <form:hidden path="application.applNo" id="applNo" />
            <form:hidden path="application.userId" id="userId" />
            <form:hidden path="application.applStsCode" id="applStsCode" />
            <form:hidden path="application.admsNo" id="admsNo" />
            <form:hidden path="application.entrYear" id="entrYear" />
            <form:hidden path="application.admsTypeCode" id="admsTypeCode" />
            <div id="myTabContent" class="tab-content">
                <div class="spacer-tiny"></div>
                <div class="row">
                    <div class="col-sm-offset-1 col-sm-10">
                        <%--<div>--%>
                            <%--<div class="validation-error"><form:errors path="*"/></div>--%>
                        <%--</div>--%>
                        <div class="panel panel-darkgray0">
                            <div class="panel-heading"><spring:message code="L01102"/><%--지원 사항--%></div>
                            <div class="panel-body">
                                <div class="form-group required">
                                    <label for="applAttrCode" class="col-sm-2 control-label"><spring:message code="L01103"/><%--지원 구분--%></label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-12">
                                            <form:select path="application.applAttrCode" id="applAttrCode" cssClass="form-control base-info">
                                                <form:option value="" label="--${msg.getMsg('L01011', locale)}--" />
                                                <form:options items="${selection.applAttrList}" itemValue="code"
                                                              itemLabel="${pageContext.response.locale == 'en' ? 'codeValXxen' : 'codeVal'}"/>
                                            </form:select>
                                        </div>
                                    </div>
                                </div>
                                <div id="applyKindDynamic">
                                    <div class="form-group hidden-apply-kind-2 required">
                                        <label path="campCode" class="col-sm-2 control-label"><spring:message code="L01104"/><%--캠퍼스--%></label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-3">
                                                <form:select path="application.campCode" id="campCode" cssClass="form-control base-info base-non-ariInst">
                                                    <form:option value="" label="--${msg.getMsg('L01011', locale)}--" />
                                                    <c:if test="${basis.application.applAttrCode == '00001' || basis.application.applAttrCode == '00003' || basis.application.applAttrCode == '00004'}">
                                                        <form:options items="${selection.campList}" itemValue="campCode"
                                                                      itemLabel="${pageContext.response.locale == 'en' ? 'campNameXxen' : 'campName'}"/>
                                                    </c:if>
                                                </form:select>
                                            </div>
                                            <label path="collCode" class="col-sm-2 control-label"><spring:message code="L01105"/><%--대학--%></label>
                                            <div class="col-sm-7">
                                                <form:select path="application.collCode" id="collCode" cssClass="form-control base-info base-non-ariInst">
                                                    <form:option value="" label="--${msg.getMsg('L01011', locale)}--" />
                                                    <c:if test="${basis.application.applAttrCode == '00001' || basis.application.applAttrCode == '00003' || basis.application.applAttrCode == '00004'}">
                                                        <form:options items="${selection.collList}" itemValue="collCode"
                                                                      itemLabel="${pageContext.response.locale == 'en' ? 'collNameXxen' : 'collName'}"/>
                                                    </c:if>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group hidden-apply-kind-0 hidden-apply-kind-1 hidden-apply-kind-3 hidden-apply-kind-4 required">
                                        <label for="ariInstCode" class="col-sm-2 control-label"><spring:message code="L01106"/><%--학·연·산 연구기관--%></label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-12">
                                                <form:select path="application.ariInstCode" id="ariInstCode" cssClass="form-control base-info base-ariInst">
                                                    <form:option value="" label="--${msg.getMsg('L01011', locale)}--" />
                                                    <c:if test="${basis.application.applAttrCode == '00002'}">
                                                    <form:options items="${selection.ariInstList}" itemValue="ariInstCode"
                                                                  itemLabel="${pageContext.response.locale == 'en' ? 'ariInstNameXxen' : 'ariInstName'}"/>
                                                    </c:if>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <label for="deptCode" class="col-sm-2 control-label"><spring:message code="L01107"/><%--지원 학과--%></label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-12">
                                                <form:select path="application.deptCode" id="deptCode" cssClass="form-control base-info base-non-ariInst base-ariInst">
                                                    <form:option value="" label="--${msg.getMsg('L01011', locale)}--" />
                                                    <form:options items="${selection.deptList}" itemValue="deptCode"
                                                                  itemLabel="${pageContext.response.locale == 'en' ? 'deptNameXxen' : 'deptName'}"/>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <label for="corsTypeCode" class="col-sm-2 control-label"><spring:message code="L01108"/><%--지원 과정--%></label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-12">
                                                <form:select path="application.corsTypeCode" id="corsTypeCode" cssClass="form-control base-info base-non-ariInst base-ariInst">
                                                    <form:option value="" label="--${msg.getMsg('L01011', locale)}--" />
                                                    <form:options items="${selection.corsTypeList}" itemValue="corsTypeCode"
                                                                  itemLabel="${pageContext.response.locale == 'en' ? 'codeValXxen' : 'codeVal'}"/>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <label for="detlMajCode" class="col-sm-2 control-label"><spring:message code="L01109"/><%--세부 전공--%></label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-12">
                                                <%--<form:select path="application.detlMajCode" cssClass="form-control base-info base-non-ariInst base-ariInst" id="detlMajCode">--%>
                                                    <%--<form:option value="" label="--${msg.getMsg('L01011', locale)}--" />--%>
                                                    <%--<form:options items="${detlList.detlMajCode}" itemValue="detlMajCode"--%>
                                                                  <%--itemLabel="${pageContext.response.locale == 'en' ? detlList.detlMajNameXxen : detlList.detlMajName}"/>--%>
                                                <%--</form:select>--%>
                                                <select name="application.detlMajCode" class="form-control base-info base-non-ariInst base-ariInst" id="detlMajCode">
                                                    <option value="">--<spring:message code="L01011"/>--</option>
                                                    <c:forEach var="detlList" items="${selection.detlMajList}" varStatus="status">
                                                        <c:choose>
                                                        <c:when test='${basis.application.detlMajCode == detlList.detlMajCode}'>
                                                        <option value="${detlList.detlMajCode}" selected data-partTimeYn="${detlList.partTimeYn}">${pageContext.response.locale == 'en' ? detlList.detlMajNameXxen : detlList.detlMajName}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${detlList.detlMajCode}" data-partTimeYn="${detlList.partTimeYn}">${pageContext.response.locale == 'en' ? detlList.detlMajNameXxen : detlList.detlMajName}</option>
                                                        </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-7">
                                                <label id="detMajDesc" class="apexMessage"></label>
                                            </div>
                                            <div class="col-sm-offset-2 col-sm-9">
                                                <form:input path="application.inpDetlMaj" id="inpDetlMaj" cssClass="form-control base-info-input" style="display:none" disabeld="true"/>
                                            </div>
                                            <div class="col-sm-9" id="partTimeYnGrp" style="display:none" disabeld="true">
                                                <div class="col-sm-12">
                                                    <form:select path="application.partTimeYn" id="partTimeYn" cssClass="form-control base-info">
                                                        <form:option value="" label="--${msg.getMsg('L01011', locale)}--" />
                                                        <form:option value="Y" label="${msg.getMsg('L01113')}" /><%--파트타임--%>
                                                        <form:option value="N" label="${msg.getMsg('L01114')}" /><%--풀타임--%>
                                                    </form:select>
                                                <%--
                                                    <label class="radio-inline base-info-input">
                                                        <form:radiobutton path="application.partTimeYn" readonly="true" cssClass="base-info-input" value="Y" label="${msg.getMsg('L01113')}"/>
                                                    </label>
                                                    <label class="radio-inline base-info-input">
                                                        <form:radiobutton path="application.partTimeYn" cssClass="base-info-input" value=" " label="${msg.getMsg('L01114')}"/>
                                                    </label>
                                                --%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                <%--
                                    <div id="partTimeYnGrp" style="display:none">
                                        <label class="col-sm-2 control-label"></label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-12">
                                                <label class="radio-inline">
                                                    <form:radiobutton path="application.partTimeYn"  value="Y" label="${msg.getMsg('L01113')}"/>
                                                </label>
                                                <label class="radio-inline">
                                                    <form:radiobutton path="application.partTimeYn"  value=" " label="${msg.getMsg('L01114')}"/>
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                --%>
                                </div>
                                <div>
                                    <div class="col-sm-11" id="baseSave">
                                        <span class="col-sm-8"><spring:message code="U01101"/></span>
                                        <button id="btnBaseSave" class="btn btn-info btn-lg col-sm-4"><spring:message code="L01110"/><%--지원사항 저장--%></button>
                                    </div>
                                    <div class="col-sm-11" id="baseCancel" style="display:none;">
                                        <span class="col-sm-8"><spring:message code="U01105"/></span>
                                        <button id="btnBaseCancel" class="btn btn-warning btn-lg col-sm-4"><spring:message code="L01111"/><%--지원사항 취소--%></button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div id="basis2Container" style="display: ${basis.application.applNo == null || basis.application.applNo <= 0 ? 'none;' : 'block;'}">
                            <div class="panel panel-darkgray0">
                                <div class="panel-heading"><spring:message code="L01201"/><%--지원자 정보--%></div>
                                <div class="panel-body">
                                    <div class="form-group ${basis.application.admsTypeCode == "C" || basis.application.admsTypeCode == "D" ? '' : 'required' }">
                                        <label for="application.korName" class="col-sm-2 control-label"><spring:message code="L01202"/><%--한글 이름--%></label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-12">
                                                <form:input path="application.korName" cssClass="form-control korName" maxlength="20" placeholder="${msg.getMsg('U01201')}"/><%--한글 이름을 공백 없이 입력해주세요--%>
                                            </div>
                                            <spring:bind path="application.korName">
                                                <c:if test="${status.error}">
                                                    <div class="col-sm-12">
                                                        <div class="validation-error">${status.errorMessage}</div>
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <label class="col-sm-2 control-label"><spring:message code="L01203"/><%--영문 이름--%></label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-6">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><spring:message code="L01205"/><%--이름--%></span>
                                                    <form:input path="application.engName" cssClass="form-control engName" maxlength="20" />
                                                </div>
                                                <spring:bind path="application.engName">
                                                    <c:if test="${status.error}">
                                                        <div>
                                                            <div class="validation-error">${status.errorMessage}</div>
                                                        </div>
                                                    </c:if>
                                                </spring:bind>
                                            </div>
                                            <div class="col-sm-6">
                                                <div class="input-group">
                                                    <span class="input-group-addon">&nbsp;<spring:message code="L01204"/><%--성--%>&nbsp;</span>
                                                    <form:input path="application.engSur" cssClass="form-control engName" maxlength="20" />
                                                </div>
                                                <spring:bind path="application.engSur">
                                                    <c:if test="${status.error}">
                                                        <div>
                                                            <div class="validation-error">${status.errorMessage}</div>
                                                        </div>
                                                    </c:if>
                                                </spring:bind>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <label for="citzCntrName" class="col-sm-2 control-label"><spring:message code="L01206"/><%--국적--%></label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-3">
                                                <button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="citzCntrCode" data-targetNode2='citzCntrName' data-category="country">
                                                    <span class="glyphicon glyphicon-search"></span> <spring:message code="L01207"/><%--검색--%>
                                                </button>
                                            </div>
                                            <div class="col-sm-9">
                                                <form:hidden path="application.citzCntrCode" id="citzCntrCode" maxlength='5' cssClass="form-control" />
                                                <input id="citzCntrName" class="form-control" value="${pageContext.response.locale == 'en' ? ctznCntr.engCntrName : ctznCntr.korCntrName}" readonly="true"/>
                                            </div>
                                            <spring:bind path="application.citzCntrCode">
                                                <c:if test="${status.error}">
                                                    <div class="col-sm-12">
                                                        <div class="validation-error">${status.errorMessage}</div>
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                        </div>
                                    </div>
                                    <%--<div class="form-group required" id="divRgstNo" style="display: ${basis.application.citzCntrCode == "118" ? 'block;' : 'none;'}">--%>
                                    <div class="form-group required" id="divRgstNo">
                                        <label class="col-sm-2 control-label" id="dateOfBirthLabel">
                                            ${basis.application.admsTypeCode != 'C' && basis.application.admsTypeCode != 'D' && basis.application.citzCntrCode == '118' ? msg.getMsg('L01208') : msg.getMsg('L01216')}<%--주민등록번호 or 생년월일--%>
                                        </label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-12">
                                                <div class="col-sm-4 nopadding">
                                                    <div><form:input path="application.rgstBornDate" cssClass="form-control numOnly lenCheck-6"
                                                                     maxlength="6" placeholder="${basis.application.citzCntrCode == '118' ? msg.getMsg('U01206') : msg.getMsg('U01207')}"/></div>
                                            <spring:bind path="application.rgstBornDate">
                                                <c:if test="${status.error}">
                                                    <div class="validation-error">${status.errorMessage}</div>
                                                </c:if>
                                            </spring:bind>
                                                </div>
                                    <c:if test="${basis.application.admsTypeCode != 'C' && basis.application.admsTypeCode != 'D'}">
                                        <c:choose>
                                            <c:when test="${basis.application.applStsCode == null || basis.application.applStsCode.length() == 0}">
                                                <div class="col-sm-1 warn-rgstEncr" style="text-align: center; display: ${basis.application.citzCntrCode == '118' ? 'block;' : 'none;'}">
                                                    <label>-</label>
                                                </div>
                                                <div class="col-sm-4 nopadding warn-rgstEncr" style="display: ${basis.application.citzCntrCode == '118' ? 'block;' : 'none;'}">
                                                    <div><form:input path="application.rgstEncr" cssClass="form-control numOnly lenCheck-7" maxlength="7" placeholder="${msg.getMsg('U01208')}"/></div>  <%--주민등록번호 뒤 7자리--%>
                                                    <div class="input-info word-keep-all"><spring:message code="U01209"/><%--보안을 위해 추후 수정할 수 없으므로 정확히 입력해 주십시오.--%></div>
                                                    <spring:bind path="application.rgstEncr">
                                                        <c:if test="${status.error}">
                                                            <div class="validation-error">${status.errorMessage}</div>
                                                        </c:if>
                                                    </spring:bind>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class='col-sm-6'
                                                     style="color: #337799; vertical-align: middle; display: ${basis.application.citzCntrCode == '118' ? 'block;' : 'none;'}"><spring:message code="U01210"/><%--주민등록번호 뒷자리는 보안을 위해 화면에 노출하지 않습니다.--%></div>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group required">
                                        <label class="col-sm-2 control-label" id="genderLabel">
                                            <spring:message code="L00128"/><%--성별--%>
                                        </label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-12">
                                                <div class="col-sm-4 nopadding">
                                                     <label class="radio-inline">
                                                     <form:radiobutton path="application.gend" value="m" label="${msg.getMsg('L114')}" cssClass="gendRadio"/>
                                                     </label>
                                                     <label class="radio-inline">
                                                     <form:radiobutton path="application.gend" value="f" label="${msg.getMsg('L115')}" cssClass="gendRadio"/>
                                                     </label>
                                                         <spring:bind path="application.gend">
                                                         <c:if test="${status.error}">
                                                         <div class="validation-error">${status.errorMessage}</div>
                                                         </c:if>
                                                         </spring:bind>
                                                </div>
                                            </div>
                                        </div>

                                    </div>



                                </div>
                            </div>

                            <c:choose>
                                <c:when test="${basis.application.admsTypeCode != 'C' && basis.application.admsTypeCode != 'D'}">
                                    <div class="panel panel-darkgray0">
                                        <div class="panel-heading"><spring:message code="L01301"/><%--지원자 상세정보--%></div>
                                        <div class="panel-body">

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"><spring:message code="L01302"/><%--장애 사항--%></label>
                                                <div class="col-sm-9">
                                                    <div class="col-sm-12">
                                                        <button class="btn btn-default btn-block" id="disadvantageYn"><spring:message code="L01303"/><%--장애 사항 입력하기--%></button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="disAdv"
                                                 style="display: ${( (basis.applicationGeneral.hndcGrad != null && basis.applicationGeneral.hndcGrad.length() > 0) ||
                                                                     (basis.applicationGeneral.hndcType != null && basis.applicationGeneral.hndcType.length() > 0) ) ? 'block;' : 'none;'}">
                                                <div class="form-group">
                                                    <div class="col-sm-offset-2 col-sm-9">
                                                        <div class="col-sm-12">
                                                            <div class="input-group">
                                                                <span class="input-group-addon"><spring:message code="L01304"/><%--장애 유형--%></span>
                                                                <form:input path="applicationGeneral.hndcGrad" cssClass="col-sm-6 form-control" maxlength="50" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-offset-2 col-sm-9">
                                                        <div class="col-sm-12">
                                                            <div class="input-group">
                                                                <span class="input-group-addon"><spring:message code="L01305"/><%--장애 등급--%></span>
                                                                <form:input path="applicationGeneral.hndcType" cssClass="col-sm-6 form-control" maxlength="10" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise> <%-- 외국인 --%>
                                    <div class="panel panel-darkgray0">
                                        <div class="panel-heading"><spring:message code="L01301"/><%--지원자 상세정보--%></div>
                                        <div class="panel-body">
                                                <%--<div class="form-group required">--%>
                                                <%--<label for="citzCntrName" class="col-sm-2 control-label">출생국</label>--%>
                                                <%--<div class="col-sm-9">--%>
                                                <%--<div class="col-sm-3">--%>
                                                <%--<button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="bornCntrCode" data-targetNode2='bornCntrName' data-category="country">--%>
                                                <%--<span class="glyphicon glyphicon-search"></span> 검색--%>
                                                <%--</button>--%>
                                                <%--</div>--%>
                                                <%--<div class="col-sm-9">--%>
                                                <%--<form:hidden path="applicationForeigner.bornCntrCode" id="bornCntrCode" cssClass="form-control" />--%>
                                                <%--<input id="bornCntrName" class="form-control" value="${bornCntr.korCntrName}" readonly="true"/>--%>
                                                <%--</div>--%>
                                                <%--<spring:bind path="applicationForeigner.bornCntrCode">--%>
                                                <%--<c:if test="${status.error}">--%>
                                                <%--<div class="col-sm-12">--%>
                                                <%--<div class="validation-error">${status.errorMessage}</div>--%>
                                                <%--</div>--%>
                                                <%--</c:if>--%>
                                                <%--</spring:bind>--%>
                                                <%--</div>--%>
                                                <%--</div>--%>
                                            <div class="form-group required">
                                                <label class="col-sm-2 control-label"><spring:message code="L01306"/><%--외국인 구분--%></label>
                                                <div class="col-sm-9">
                                                    <div class="col-sm-4">
                                                        <form:select path="application.fornTypeCode" id="fornTypeCode" cssClass="form-control">
                                                            <form:option value="" label="--${msg.getMsg('L01011', locale)}--" />
                                                            <form:options items="${foreign.foreignTypeList}" itemValue="code"
                                                                          itemLabel="${pageContext.response.locale == 'en' ? 'codeValXxen' : 'codeVal'}"/>
                                                        </form:select>
                                                    </div>
                                                    <div class="col-sm-8">&nbsp;</div>
                                                    <spring:bind path="application.fornTypeCode">
                                                        <c:if test="${status.error}">
                                                            <div class="col-sm-12">
                                                                <div class="validation-error">${status.errorMessage}</div>
                                                            </div>
                                                        </c:if>
                                                    </spring:bind>
                                                </div>
                                            </div>
                                            <div class="form-group required">
                                                <label class="col-sm-2 control-label"><spring:message code="L01307"/><%--본국 주소--%></label>
                                                <div class="col-sm-9">
                                                    <div class="col-sm-12">
                                                        <form:input path="applicationForeigner.homeAddr" cssClass="form-control" maxlength="500" placeholder="${msg.getMsg('U01302')}"/> <%--본국 주소를 입력해 주세요.--%>
                                                    </div>
                                                    <spring:bind path="applicationForeigner.homeAddr">
                                                        <c:if test="${status.error}">
                                                            <div class="col-sm-12">
                                                                <div class="validation-error">${status.errorMessage}</div>
                                                            </div>
                                                        </c:if>
                                                    </spring:bind>
                                                </div>
                                            </div>
                                            <div class="form-group required">
                                                <label class="col-sm-2 control-label"><spring:message code="L01308"/><%--본국 연락처--%></label>
                                                <div class="col-sm-9">
                                                    <div class="col-sm-12">
                                                        <form:input path="applicationForeigner.homeTel" cssClass="form-control" maxlength="16" placeholder="${msg.getMsg('U01303')}" /> <%--본국 연락처를 입력해 주세요.--%>
                                                    </div>
                                                    <spring:bind path="applicationForeigner.homeTel">
                                                        <c:if test="${status.error}">
                                                            <div class="col-sm-12">
                                                                <div class="validation-error">${status.errorMessage}</div>
                                                            </div>
                                                        </c:if>
                                                    </spring:bind>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>


                                <%--<c:if test="${basis.application.admsTypeCode == 'C'}">--%>
                            <div class="panel panel-darkgray0" id="stayInfo" style="display: ${basis.application.admsTypeCode != 'C' && basis.application.admsTypeCode != 'D' ? 'none;' : basis.application.citzCntrCode != '118' ? 'block;' : 'none;' }">
                                <div class="panel-heading"><spring:message code="L01401"/><%--체류 정보--%></div>
                                <div class="panel-body">
                                    <div class="form-group required">
                                        <label for="applicationForeigner.paspNo" class="col-sm-2 control-label"><spring:message code="L01402"/><%--여권 번호--%></label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-12">
                                                <form:input path="applicationForeigner.paspNo" cssClass="form-control" maxlength="50" placeholder="${msg.getMsg('U01401')}"/>  <%--여권 번호를 입력해주세요--%>
                                            </div>
                                            <spring:bind path="applicationForeigner.paspNo">
                                                <c:if test="${status.error}">
                                                    <div class="col-sm-12">
                                                        <div class="validation-error">${status.errorMessage}</div>
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <label class="col-sm-2 control-label"><spring:message code="L01403"/><%--비자--%></label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-6">
                                                <div class="input-group">
                                                    <span class="input-group-addon">&nbsp;<spring:message code="L01404"/><%--종류--%>&nbsp;</span>
                                                    <form:select path="applicationForeigner.visaTypeCode" cssClass="form-control">
                                                        <form:option value="" label="--${msg.getMsg('L01011', locale)}--" />
                                                        <form:options items="${foreign.visaTypeList}" itemValue="code"
                                                                      itemLabel="${pageContext.response.locale == 'en' ? 'codeValXxen' : 'codeVal'}"/>
                                                    </form:select>
                                                </div>
                                                <spring:bind path="applicationForeigner.visaTypeCode">
                                                    <c:if test="${status.error}">
                                                        <div>
                                                            <div class="validation-error">${status.errorMessage}</div>
                                                        </div>
                                                    </c:if>
                                                </spring:bind>
                                            </div>
                                            <div class="col-sm-6">
                                                <div class="input-group" id="tmpVisaTypeCode" style="display: ${basis.applicationForeigner.visaTypeCode == '00099' ? 'block;' : 'none;'}">
                                                    <form:input path="applicationForeigner.visaTypeEtc" cssClass="form-control" maxlength="20" placeholder="${msg.getMsg('U01402')}" /> <%--예) D-2, D-4--%>
                                                </div>
                                                <spring:bind path="applicationForeigner.visaTypeEtc">
                                                    <c:if test="${status.error}">
                                                        <div>
                                                            <div class="validation-error">${status.errorMessage}</div>
                                                        </div>
                                                    </c:if>
                                                </spring:bind>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group required" id="visaInfoContainer"
                                         style="display: ${basis.applicationForeigner.visaTypeCode == '00999' ? 'none;' : 'block;'}">
                                        <div class="col-sm-offset-2 col-sm-9">
                                            <div class="col-sm-6">
                                                <div class="input-group">
                                                    <span class="input-group-addon">&nbsp;<spring:message code="L01405"/><%--번호--%>&nbsp;</span>
                                                    <form:input path="applicationForeigner.visaNo" cssClass="form-control" maxlength="50" />
                                                </div>
                                                <spring:bind path="applicationForeigner.visaNo">
                                                    <c:if test="${status.error}">
                                                        <div>
                                                            <div class="validation-error">${status.errorMessage}</div>
                                                        </div>
                                                    </c:if>
                                                </spring:bind>
                                            </div>
                                            <div class="col-sm-6" id="expr-date-container"
                                                 style="display: ${basis.applicationForeigner.visaTypeCode == '00999' || basis.applicationForeigner.visaTypeCode == 'F-5' ? 'none;' : 'block;' }">
                                                <div class="input-group date">
                                                    <span class="input-group-addon"><spring:message code="L01406"/><%--만료일--%></span>
                                                    <input id="applicationForeigner.visaExprDay" name="applicationForeigner.visaExprDay"
                                                           class="form-control" readonly="true" value="${basis.applicationForeigner.visaExprDay}"
                                                           <c:if test="${basis.applicationForeigner.visaTypeCode == '00999'}">disabled</c:if> />
                                                    <span class="input-group-addon calendar-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                                <spring:bind path="applicationForeigner.visaExprDay">
                                                    <c:if test="${status.error}">
                                                        <div class="validation-error validation-container">
                                                                ${status.errorMessage}
                                                        </div>
                                                    </c:if>
                                                </spring:bind>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="applicationForeigner.fornRgstNo" class="col-sm-2 control-label"><spring:message code="L01407"/><%--외국인등록번호--%></label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-12">
                                                <input id="applicationForeigner.fornRgstNo" name="applicationForeigner.fornRgstNo" class="form-control numOnly rgstNo"
                                                       maxlength="13" placeholder="${msg.getMsg('U01403')}" <%--외국인등록번호를 13자리 숫자로 입력해주세요--%>
                                                       value="${basis.applicationForeigner.fornRgstNo}"
                                                       <%--<c:if test="${basis.applicationForeigner.visaTypeCode == '00999'}">disabled</c:if> />--%>
                                                        />
                                            </div>
                                            <spring:bind path="applicationForeigner.fornRgstNo">
                                                <c:if test="${status.error}">
                                                    <div class="col-sm-12">
                                                        <div class="validation-error">${status.errorMessage}</div>
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                        </div>
                                    </div>
                                </div>
                            </div>
                                <%--</c:if>--%>

                            <div class="panel panel-darkgray0">
                                <div class="panel-heading"><spring:message code="L01501"/><%--지원자 연락처--%></div>
                                <div class="panel-body">
                                    <div class="form-group ${basis.application.admsTypeCode == "C" || basis.application.admsTypeCode == "D" ? '' : 'required' }">
                                        <label class="col-sm-2 control-label"><spring:message code="L01502"/><%--주소--%></label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-3">
                                                <button type="button" class="btn btn-default btn-block btn-search" id="searchAddress">
                                                    <span class="glyphicon glyphicon-search"></span> <spring:message code="L01503"/><%--우편번호 찾기--%>
                                                </button>
                                            </div>
                                            <div class="col-sm-9">
                                                <div class="input-group">
                                                    <%--<form:input path="application.zipCode" cssClass="form-control" id="zipCode" readonly="true"/>--%>
                                                    <input type="text" id="zipCode" name="application.zipCode" value="${basis.application.zipCode}" maxlength="6" class="form-control"
                                                           ${basis.application.admsTypeCode == 'C' || basis.application.admsTypeCode == 'D' ? '' : 'readonly'}/>
                                                </div>
                                            </div>
                                            <spring:bind path="application.zipCode">
                                                <c:if test="${status.error}">
                                                    <div class="col-sm-12">
                                                        <div class="validation-error">${status.errorMessage}</div>
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-2"></div>
                                        <div class="col-sm-9">
                                            <div class="col-sm-6">
                                                <%--<form:input path="application.addr" cssClass="form-control" id="address" readonly="true" />--%>
                                                <input type="text" name="application.addr" class="form-control" id="address" value="${basis.application.addr}"
                                                    ${basis.application.admsTypeCode == 'C' || basis.application.admsTypeCode == 'D' ? '' : 'readonly'}/>
                                            </div>
                                            <div class="col-sm-6">
                                                <form:input path="application.detlAddr" cssClass="form-control" id="addressDetail" maxlength="500" placeholder="${msg.getMsg('U01502')}" /> <%--세부주소--%>
                                            </div>
                                            <spring:bind path="application.addr">
                                                <c:if test="${status.error}">
                                                    <div class="col-sm-12">
                                                        <div class="validation-error">${status.errorMessage}</div>
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                        </div>
                                    </div>
                                    <div class="form-group ${basis.application.admsTypeCode == 'C' || basis.application.admsTypeCode == 'D' ? '' : 'required'}">
                                        <label for="application.telNum" class="col-sm-2 control-label"><spring:message code="L01504"/><%--전화번호--%></label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-12">
                                                <form:input path="application.telNum" cssClass="form-control numOnly phone" maxlength="18" placeholder="${msg.getMsg('U01503')}"/> <%--전화번호를 숫자로만 입력해주세요--%>
                                            </div>
                                            <spring:bind path="application.telNum">
                                                <c:if test="${status.error}">
                                                    <div class="col-sm-12">
                                                        <div class="validation-error">${status.errorMessage}</div>
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                        </div>
                                    </div>
                                    <%--<div class="form-group ${basis.application.admsTypeCode == "C" || basis.application.admsTypeCode == "D" ? '' : 'required' }">--%> <%--학교측 요구로 필수화 4.8--%>
                                    <div class="form-group required">
                                        <label for="application.mobiNum" class="col-sm-2 control-label"><spring:message code="L01505"/><%--휴대폰--%></label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-12">
                                                <form:input path="application.mobiNum" cssClass="form-control numOnly phone" maxlength="18" placeholder="${msg.getMsg('U01504')}"/> <%--휴대폰번호를 숫자로만 입력해주세요--%>
                                            </div>
                                            <spring:bind path="application.mobiNum">
                                                <c:if test="${status.error}">
                                                    <div class="col-sm-12">
                                                        <div class="validation-error">${status.errorMessage}</div>
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <label for="application.mailAddr" class="col-sm-2 control-label"><spring:message code="L01506"/><%--이메일--%></label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-12">
                                                <form:input path="application.mailAddr" type="email" cssClass="form-control emailOnly" maxlength="50" placeholder="${msg.getMsg('U01505')}"/> <%--이메일 주소를 입력해주세요--%>
                                            </div>
                                            <spring:bind path="application.mailAddr">
                                                <c:if test="${status.error}">
                                                    <div class="col-sm-12">
                                                        <div class="validation-error">${status.errorMessage}</div>
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <c:choose>
                                <c:when test="${basis.application.admsTypeCode != 'C' && basis.application.admsTypeCode != 'D'}">
                                    <div class="panel panel-darkgray0">
                                        <div class="panel-heading"><spring:message code="L01601"/><%--비상연락처--%></div>
                                        <div class="panel-body">
                                            <div class="form-group required">
                                                <label for="applicationGeneral.emerContName" class="col-sm-2 control-label"><spring:message code="L01604"/><%--이름--%></label>
                                                <div class="col-sm-9">
                                                    <div class="col-sm-12">
                                                        <form:input path="applicationGeneral.emerContName" cssClass="form-control" maxlength="50" placeholder="${msg.getMsg('U01601')}"/> <%--보호자 이름을 입력해 주세요.--%>
                                                    </div>
                                                    <spring:bind path="applicationGeneral.emerContName">
                                                        <c:if test="${status.error}">
                                                            <div class="col-sm-12">
                                                                <div class="validation-error">${status.errorMessage}</div>
                                                            </div>
                                                        </c:if>
                                                    </spring:bind>
                                                </div>
                                            </div>
                                            <div class="form-group required">
                                                <label for="applicationGeneral.emerContCode" class="col-sm-2 control-label"><spring:message code="L01605"/><%--관계--%></label>
                                                <div class="col-sm-9">
                                                    <div class="col-sm-12">
                                                        <form:select path="applicationGeneral.emerContCode" cssClass="form-control">
                                                            <form:option value="" label="--${msg.getMsg('L01011', locale)}--" />
                                                            <form:options items="${selection.emerContList}" itemValue="code"
                                                                          itemLabel="${pageContext.response.locale == 'en' ? 'codeValXxen' : 'codeVal'}"/>
                                                        </form:select>
                                                    </div>
                                                    <spring:bind path="applicationGeneral.emerContCode">
                                                        <c:if test="${status.error}">
                                                            <div class="col-sm-12">
                                                                <div class="validation-error">${status.errorMessage}</div>
                                                            </div>
                                                        </c:if>
                                                    </spring:bind>
                                                </div>
                                            </div>
                                            <div class="form-group required">
                                                <label for="applicationGeneral.emerContTel" class="col-sm-2 control-label"><spring:message code="L01606"/><%--전화번호--%></label>
                                                <div class="col-sm-9">
                                                    <div class="col-sm-12">
                                                        <form:input path="applicationGeneral.emerContTel" cssClass="form-control numOnly phone" maxlength="18" placeholder="${msg.getMsg('U01602')}"/> <%--전화(또는 휴대폰)번호를 숫자로만 입력해 주세요.--%>
                                                    </div>
                                                    <spring:bind path="applicationGeneral.emerContTel">
                                                        <c:if test="${status.error}">
                                                            <div class="col-sm-12">
                                                                <div class="validation-error">${status.errorMessage}</div>
                                                            </div>
                                                        </c:if>
                                                    </spring:bind>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise> <%-- 외국인 전형 --%>
                                    <div class="panel panel-darkgray0">
                                        <div class="panel-heading"><spring:message code="L01601"/><%--비상연락처--%></div>
                                        <div class="panel-body">
                                            <div class="panel panel-darkgray1">
                                                <div class="panel-heading"><spring:message code="L01602"/><%--국내--%></div>
                                                <div class="panel-body">
                                                    <div class="form-group ${basis.application.admsTypeCode == 'C' || basis.application.admsTypeCode == 'D' ? '' : 'required'}">
                                                        <label for="applicationForeigner.korEmrgName" class="col-sm-2 control-label"><spring:message code="L01604"/><%--이름--%></label>
                                                        <div class="col-sm-9">
                                                            <div class="col-sm-12">
                                                                <form:input path="applicationForeigner.korEmrgName" maxlength="50" cssClass="form-control" placeholder="${msg.getMsg('U01601')}" /> <%--보호자 이름을 입력해 주세요.--%>
                                                            </div>
                                                            <spring:bind path="applicationForeigner.korEmrgName">
                                                                <c:if test="${status.error}">
                                                                    <div class="col-sm-12">
                                                                        <div class="validation-error">${status.errorMessage}</div>
                                                                    </div>
                                                                </c:if>
                                                            </spring:bind>
                                                        </div>
                                                    </div>
                                                    <div class="form-group ${basis.application.admsTypeCode == 'C' || basis.application.admsTypeCode == 'D' ? '' : 'required'}">
                                                        <label for="applicationForeigner.korEmrgRela" class="col-sm-2 control-label"><spring:message code="L01605"/><%--관계--%></label>
                                                        <div class="col-sm-9">
                                                            <div class="col-sm-12">
                                                                <form:select path="applicationForeigner.korEmrgRela" cssClass="form-control">
                                                                    <form:option value="" label="--${msg.getMsg('L01011', locale)}--" />
                                                                    <form:options items="${selection.emerContList}" itemValue="code"
                                                                                  itemLabel="${pageContext.response.locale == 'en' ? 'codeValXxen' : 'codeVal'}"/>
                                                                </form:select>
                                                            </div>
                                                            <spring:bind path="applicationForeigner.korEmrgRela">
                                                                <c:if test="${status.error}">
                                                                    <div class="col-sm-12">
                                                                        <div class="validation-error">${status.errorMessage}</div>
                                                                    </div>
                                                                </c:if>
                                                            </spring:bind>
                                                        </div>
                                                    </div>
                                                    <div class="form-group ${basis.application.admsTypeCode == 'C' || basis.application.admsTypeCode == 'D' ? '' : 'required'}">
                                                        <label for="applicationForeigner.korEmrgTel" class="col-sm-2 control-label"><spring:message code="L01606"/><%--전화번호--%></label>
                                                        <div class="col-sm-9">
                                                            <div class="col-sm-12">
                                                                <form:input path="applicationForeigner.korEmrgTel" cssClass="form-control numOnly phone" maxlength="18" placeholder="${msg.getMsg('U01602')}"/> <%--전화(또는 휴대폰)번호를 숫자로만 입력해 주세요.--%>
                                                            </div>
                                                            <spring:bind path="applicationForeigner.korEmrgTel">
                                                                <c:if test="${status.error}">
                                                                    <div class="col-sm-12">
                                                                        <div class="validation-error">${status.errorMessage}</div>
                                                                    </div>
                                                                </c:if>
                                                            </spring:bind>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel panel-darkgray1">
                                                <div class="panel-heading"><spring:message code="L01603"/><%--본국--%></div>
                                                <div class="panel-body">
                                                    <div class="form-group required">
                                                        <label for="applicationForeigner.homeEmrgName" class="col-sm-2 control-label"><spring:message code="L01604"/><%--이름--%></label>
                                                        <div class="col-sm-9">
                                                            <div class="col-sm-12">
                                                                <form:input path="applicationForeigner.homeEmrgName" cssClass="form-control" maxlength="50" placeholder="${msg.getMsg('U01601')}" /> <%--보호자 이름을 입력해 주세요.--%>
                                                            </div>
                                                            <spring:bind path="applicationForeigner.homeEmrgName">
                                                                <c:if test="${status.error}">
                                                                    <div class="col-sm-12">
                                                                        <div class="validation-error">${status.errorMessage}</div>
                                                                    </div>
                                                                </c:if>
                                                            </spring:bind>
                                                        </div>
                                                    </div>
                                                    <div class="form-group required">
                                                        <label for="applicationForeigner.homeEmrgRela" class="col-sm-2 control-label"><spring:message code="L01605"/><%--관계--%></label>
                                                        <div class="col-sm-9">
                                                            <div class="col-sm-12">
                                                                <form:select path="applicationForeigner.homeEmrgRela" cssClass="form-control">
                                                                    <form:option value="" label="--${msg.getMsg('L01011', locale)}--" />
                                                                    <form:options items="${selection.emerContList}" itemValue="code"
                                                                                  itemLabel="${pageContext.response.locale == 'en' ? 'codeValXxen' : 'codeVal'}"/>
                                                                </form:select>
                                                            </div>
                                                            <spring:bind path="applicationForeigner.homeEmrgRela">
                                                                <c:if test="${status.error}">
                                                                    <div class="col-sm-12">
                                                                        <div class="validation-error">${status.errorMessage}</div>
                                                                    </div>
                                                                </c:if>
                                                            </spring:bind>
                                                        </div>
                                                    </div>
                                                    <div class="form-group required">
                                                        <label for="applicationForeigner.homeEmrgTel" class="col-sm-2 control-label"><spring:message code="L01606"/><%--전화번호--%></label>
                                                        <div class="col-sm-9">
                                                            <div class="col-sm-12">
                                                                <form:input path="applicationForeigner.homeEmrgTel" cssClass="form-control numOnly" maxlength="16" placeholder="${msg.getMsg('U01602')}"/> <%--전화(또는 휴대폰)번호를 숫자로만 입력해 주세요.--%>
                                                            </div>
                                                            <spring:bind path="applicationForeigner.homeEmrgTel">
                                                                <c:if test="${status.error}">
                                                                    <div class="col-sm-12">
                                                                        <div class="validation-error">${status.errorMessage}</div>
                                                                    </div>
                                                                </c:if>
                                                            </spring:bind>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>

                    </div>
                </div><%--row--%>
                <div class="btn-group btn-group-justified">
                    <div class="btn-group">
                        <button id="saveBasis" type="button" class="btn btn-primary btn-lg btn-save"><spring:message code="L01112"/><%--기본 정보 저장--%></button>
                    </div>
                </div>
            </div> <%--myTabContent--%>
        </form:form>
    </div> <%--container--%>

    <%-- 국가/학교 검색 팝업 --%>
    <%--<div id="bpopContainer" class="bpopContainer">--%>
        <%--<span class="button b-close"><span>X</span></span>--%>
        <%--<div id="bpopContent">--%>
            <%--<div class="form-group">--%>
                <%--<label id="searchTitle"></label>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<div class="col-sm-10">--%>
                    <%--<input type="text" id="bpop" name="bpop" class="form-control ime-mode-kr" />--%>
                <%--</div>--%>
                <%--<button id="bpopBtnSearch" class="btn btn-info col-sm-2"><spring:message code="L01207"/></button>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<div class="col-sm-12" style="overflow-y: auto; height: 300px;">--%>
                    <%--<table class="table table-stripped">--%>
                        <%--<thead id="bpopHead">--%>
                        <%--</thead>--%>
                        <%--<tbody id="bpopResult">--%>
                        <%--</tbody>--%>
                    <%--</table>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <!-- 모달팝업:팝업창 시작 -->
    <div id="modal_popup3" class="popup1_wrap" style="display:none; margin-top:-240px; margin-left:-250px;">
        <div id="bpopContent" class="popuphead">
            <h1>
                <label id="searchTitle"></label>
            </h1>
        </div>
        <div class="popupbody">
            <div class="form-group clearfix">
                <div class="col-sm-10">
                    <input type="text" id="bpop" name="bpop" class="form-control ime-mode-kr">
                </div>
                <button id="bpopBtnSearch" class="btn btn-info col-sm-2" style="margin-left:-14px;">검색</button>
            </div>
            <div class="col-sm-12" style="overflow-y: auto; height: 300px;">
                <table class="table table-stripped">
                    <thead id="bpopHead">
                    </thead>
                    <tbody id="bpopResult">
                    </tbody>
                </table>
            </div>
        </div>
        <a class="btn_close b-close" title="닫기"><img src="<spring:eval expression="@app.getProperty('path.static')" />/img/btn_close1.png" alt="닫기"></a>
    </div>
    <!-- /모달팝업:팝업창 끝 -->
    <input type="hidden" id="targetNode1" />
    <input type="hidden" id="targetNode2" />
    <input type="hidden" id="targetNode3" />
    <%-- 국가/학교 검색 팝업 --%>

    <%-- 도로명 주소 사용 안내 팝업 --%>
    <div id="street-name-notice" title="도로명 주소 사용 안내" style="display:none;">
        <p><spring:message code="U01506"/></p> <%-- 주소 검색 결과에서 '지번 주소'를 클릭하지 마시고,<br/>아래와 같이 <b>도로명 주소</b>를 사용해 주시기 바랍니다. --%>
        <p align="center"><img src="<spring:eval expression="@app.getProperty('path.static')" />/img/application/street-name-capture.png"/></p>
    </div>
    <!-- 모달팝업:우편번호 찾기 시작 -->
    <%--<div id="modal_popup4" class="popup1_wrap" style="display:none; margin-top:-240px; margin-left:-250px;">--%>
        <%--<div class="popuphead">--%>
            <%--<h1>도로명 주소 사용 안내</h1>--%>
        <%--</div>--%>
        <%--<div class="popupbody">--%>
            <%--<div class="text-center"> 주소 검색 결과에서 '지번 주소'를 클릭하지 마시고,<br>--%>
                <%--아래와 같이 <strong>도로명 주소</strong>를 사용해 주시기 바랍니다.<br>--%>
                <%--<br>--%>
                <%--<img src="<spring:eval expression="@app.getProperty('path.static')" />/img/street-name-capture.png" alt="우편번호 찾기 참고사진"> </div>--%>
            <%--<div class="popup_btn">--%>
                <%--<button onclick="HideDialog('#modal_popup4')" type="button" class="btn btn-default">확인</button>--%>
            <%--</div>--%>

        <%--</div>--%>
        <%--<a class="btn_close" title="닫기"><img src="<spring:eval expression="@app.getProperty('path.static')" />/img/btn_close1.png" alt="닫기"></a>--%>
    <%--</div>--%>
    <!-- /모달팝업:우편번호 찾기 끝 -->
    <%-- 도로명 주소 사용 안내 팝업 --%>

    <%-- 다음 주소 검색 팝업 --%>
    <div id="postLayer" style="display:none;border:5px solid;position:fixed;width:720px;height:510px;left:50%;margin-left:-360px;top:50%;margin-top:-235px;overflow:hidden;-webkit-overflow-scrolling:touch;z-index:2;background-color:#fff;color: #111;">
        &nbsp;<img src="<spring:eval expression="@app.getProperty('path.static')" />/img/user/addr-close.png" id="btnClosePostLayer" style="cursor:pointer;position:absolute;right:-6px;top:-6px" alt="닫기 버튼">
    </div>
    <%-- 다음 주소 검색 팝업 --%>


</section>
<content tag="local-script">
    <%--<script src="<spring:eval expression="@app.getProperty('path.static')" />/js/daum-postcode.min.js"></script>--%>
    <script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery-ui.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
        var applNo = document.getElementById('applNo').value = '${basis.application.applNo}',
            admsNo = document.getElementById('admsNo').value = '${basis.application.admsNo}',
            applStsCode = document.getElementById('applStsCode').value = '${basis.application.applStsCode}',
            entrYear = document.getElementById('entrYear').value = '${basis.application.entrYear}',
            admsTypeCode = document.getElementById('admsTypeCode').value = '${basis.application.admsTypeCode}',
            corsTypeCode = '${basis.application.corsTypeCode}';
        var detlMaj = $('#detlMajCode').find('option:selected');

        if( detlMaj.attr('data-parttimeyn') ==="Y"){
//            document.getElementById('partTimeYnGrp').setAttribute("style", "display");
            document.getElementById('partTimeYnGrp').style.display = 'block';
        }
        if( detlMaj.attr('value')=== "99999" ){
//            document.getElementById('inpDetlMaj').setAttribute("style", "display");
            document.getElementById('inpDetlMaj').style.display = 'block';
        }

        <%-- 원서 작성 현황 및 사용 가능한 탭 처리 --%>
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
        <%-- 원서 작성 현황 및 사용 가능한 탭 처리 --%>

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

        <%-- 지원 사항 저장 버튼 처리 --%>
        var baseInfoSaved = function() {
            var applAttrCode = document.getElementById('applAttrCode'),
                $targetGroup,
                isValid = true;

            if (applAttrCode.value == null || applAttrCode.value == '') {
                alert('<spring:message code="U01103"/>'); // 지원 구분을 선택해 주세요.
                applAttrCode.focus();
                isValid = false;
                return false;
            }

            if (applAttrCode.value == '00002') {
                $targetGroup = $('.base-ariInst');
            } else {
                $targetGroup = $('.base-non-ariInst');
            }

            $targetGroup.each( function () {
                if (this.selectedIndex == 0) {
                    alert('<spring:message code="U01104"/>'); // 지원 사항을 모두 선택해주세요.
                    this.focus();
                    isValid = false;
                    return false;
                }
            });

            if (isValid) {

                $('.base-info>option').filter( function() {
                    return !this.selected;
                }).each( function() {
                    $(this).prop('disabled', true);
                });

                $('.base-info-input').each( function() {
                    $(this).attr('readonly', true);
                });

                $('#baseCancel').css('display', 'block');
                $('#baseSave').css('display', 'none');
                $('#basis2Container').css('display', 'block');
                $('#overlay').hide();
            }
        };

        // 입력 검증 오류로 DB에 저장되지 않고 다시 돌아왔을 때 지원사항 저장 상태로 설정
//        if (document.getElementById('detlMajCode').value.length > 0) {
        if (document.getElementById('detlMajCode').selectedIndex > 0) {
            baseInfoSaved();
        }
        // TODO : 지원사항 저장 시 applNo 따서 DB에 저장하는 것으로 로직 수정
        <%-- 지원 사항 저장 버튼 처리 --%>

        <%-- 기본 정보 > 지원 사항 처리 --%>
        $('#btnBaseSave').on('click', function(e) {
            e.preventDefault();
            if ( confirm('<spring:message code="U01102"/>') ) { // 지원 사항을 정확히 지정 하셨습니까?
                baseInfoSaved();
            } else {
                return false;
            }
        });

        $('#btnBaseCancel').on('click', function(e) {
            e.preventDefault();
            var form, applStsCode;
            if ( confirm('<spring:message code="U01106"/>') ) { // 지원 사항을 취소하면 작성 중인 내용은 모두 삭제됩니다.
                //TODO 지원 취소로 상태 변경 후 공고 목록으로 이동
                apex.transKorPhoneNumber('phone');
                form = document.getElementById('basis');
                document.getElementById('applStsCode').value = '00022';
                form.action = "${contextPath}/application/basis/cancel";
                form.submit();
            } else {
                return false;
            }
        });
        <%-- 기본 정보 > 지원 사항 처리 --%>

        <%-- 하단 버튼 처리 --%>
        var formProcess = function(event) {
            event.preventDefault();
            $('#overlay').show();
            var $form = $(this),
                form = document.getElementById('basis'),
                isValidProcess = true;

            if ($('#baseSave').css('display') == 'block') {
                isValidProcess = false;
                alert('<spring:message code="U329"/>');
                $('#overlay').hide();
                $('#applAttrCode').focus();
            } else {
                apex.transKorPhoneNumber('phone');
//                apex.transKorRgstNumber('rgstNo');
                form.action = "${contextPath}/application/basis/save";
                form.submit();
            }
        };
        $('.btn-save').on('click', formProcess);
        <%-- 하단 버튼 처리 --%>

        <%-- 한글 이름 공백 제거 --%>
        apex.removeSpace('korName');
        <%-- 한글 이름 공백 제거 --%>

        <%-- 영문 이름 처리 시작 --%>
        apex.engNameCheck('engName', '<spring:message code="APEXJS_0001"/>'); // 영 대소문자와 공백, [.], [-], ['] 만 가능합니다.
        <%-- 영문 이름 처리 끝 --%>

        <%-- 숫자만 입력 - 주민번호, 휴대폰, 전화번호 --%>
        apex.numCheck('numOnly', '<spring:message code="APEXJS_0002"/>'); // 숫자만 입력해 주세요.
        <%-- 숫자만 입력 - 주민번호, 휴대폰, 전화번호 --%>

        <%-- 주민번호 길이 체크 --%>
        apex.lenCheck('lenCheck-6', 6, 6,
                '<spring:message code="APEXJS_0006"/>',
                '<spring:message code="APEXJS_0007"/>',
                '<spring:message code="APEXJS_0008"/>',
                '<spring:message code="APEXJS_0009"/>',
                '<spring:message code="APEXJS_0010"/>'
        );
        apex.lenCheck('lenCheck-7', 7, 7,
                '<spring:message code="APEXJS_0006"/>',
                '<spring:message code="APEXJS_0007"/>',
                '<spring:message code="APEXJS_0008"/>',
                '<spring:message code="APEXJS_0009"/>',
                '<spring:message code="APEXJS_0010"/>'
        );
        <%-- 주민번호 길이 체크 --%>

        <%-- 성별 체크 --%>
        var checkGender = function(event) {
            var thisRadio = event.target;
            var rgstLatterInput = document.getElementById('application.rgstEncr');
            var rgstLatterValue = '';
            var firstDigit;
            if (rgstLatterInput) {
                rgstLatterValue = rgstLatterInput.value;
            }
            if (rgstLatterValue.length > 0) {
                firstDigit = rgstLatterValue.substring(0, 1);

                if (
                    (firstDigit % 2 === 0 && thisRadio.value !== 'f') ||
                    (firstDigit % 2 === 1 && thisRadio.value !== 'm')
                ) {
                    alert('<spring:message code="U01211"/>');
                    thisRadio.checked = false;
                }

            }
        };
        $('.gendRadio').on('click', checkGender);
        <%-- 성별 체크 --%>

        <%-- 메일 주소 validation --%>
        apex.emailCheck('emailOnly', '<spring:message code="APEXJS_0003"/>'); // 이메일 주소를 정확히 기재해 주세요.
        <%-- 메일 주소 validation --%>

        <%-- 국가/학교 검색 시작 --%>
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

        $('.bpopper').on('click', function(e) {
            e.preventDefault();
            showDialog(true, "#modal_popup3");
            $('#bpopResult').empty();
            var bpopText = document.getElementById('bpop');
            bpopText.value="";

            var dataCategory = this.getAttribute('data-category');
            var targetNode = null, title = null, columnHead = [];
            for (var i = 1; i < 4; i++) {
                targetNode = this.getAttribute('data-targetNode' + i);
                document.getElementById('targetNode' + i).value = targetNode ? targetNode : null;
            }
            if (dataCategory.indexOf('country') > -1) {
                title = '<spring:message code="L01209"/>'; // 국가 검색
                columnHead = ['', '<spring:message code="L01210"/>', '<spring:message code="L01211"/>']; // 한글 이름, 영문 이름
                document.getElementById('bpopContent').setAttribute('data-category', dataCategory);
            } else if (dataCategory.indexOf('school') > -1) {
                columnHead = ['', '<spring:message code="L01215"/>']; // 학교 이름
                document.getElementById('bpopContent').setAttribute('data-category', dataCategory);
                var suffix = dataCategory.split('-')[1];
                if (suffix == 'h') {
                    title = '<spring:message code="L01212"/>'; // 고등학교 검색
                } else if (suffix == 'u') {
                    title = '<spring:message code="L01213"/>'; // 대학교 검색
                } else if (suffix == 'g') {
                    title = '<spring:message code="L01214"/>'; // 대학원 검색
                } else {
                }
            }

            if (title != null) {
                $('#bpopContent').find('#searchTitle').text(title);
            }
            var $thead = $('<tr></tr>');
            for (var i = 0, len = columnHead.length; i < len; i++) {
                $thead.append($('<th>' + columnHead[i] + '</th>'));
            }
            $('#bpopHead').empty();
            $('#bpopHead').append($thead);
            $('#bpopContainer').bPopup();
            bpopText.focus();
            $(bpopText).focus().focus();
        });

        $('#bpopBtnSearch').on('click', function(e) {
            e.preventDefault();
            var baseUrl = '${contextPath}/common/code', url;
            var dataCategory = $('#bpopContent').attr('data-category'), category;
            var category = {};
            category.isCountry = dataCategory.indexOf('country') > -1;
            category.isSchool = dataCategory.indexOf('school') > -1;
            category.url = dataCategory.replace('-', '/');
            url = baseUrl + '/' + category.url + '/' + encodeURIComponent($('#bpop').val());

            $('#bpopResult').empty();

            $('#bpopBtnSearch').attr('disabled', 'disabled');
            $.ajax({
                type: 'GET',
                url: url,
                success: function(data) {
                	$('#bpopBtnSearch').removeAttr('disabled');
//                    var obj = JSON.parse(data.data), record, i, l;
                    var container = JSON.parse(data),
                        obj = JSON.parse(container.data),
                        record, i, l;

                    if (obj.length > 0) {
                        for ( i = 0, l = obj.length ; i < l ; i++ ) {
                            if (category.isCountry) {
//                                record = $('<tr>' + '<td><span style="display: none;" class="b-close">' + obj[i].cntrCode + '</span></td>' + '<td><span class="b-close" style="cursor: pointer">' + obj[i].korCntrName + '</span></td>' + '<td><span class="b-close" style="cursor: pointer">' + obj[i].engCntrName + '</span></td>' + '</tr>');
                                record = $('<tr>' + '<td><span style="display: none;" class="b-close">' + obj[i].cntrCode + '</span></td>' + '<td><span class="b-close" style="cursor: pointer">' + obj[i].korCntrName + '</span></td>' + '<td><span class="b-close" style="cursor: pointer">' + obj[i].engCntrName + '</span></td>' + '</tr>');
                            } else if (category.isSchool) {
                                record = $('<tr>' + '<td><span style="display: none;" class="b-close">' + obj[i].schlCode + '</span></td>' + '<td><span class="b-close" style="cursor: pointer">' + obj[i].schlName + '</span></td>' + '</tr>');
                            }
                            $('#bpopResult').append(record);
                            $(record).on('click', function(e) {
                                var targetInputId = [
                                    document.getElementById('targetNode1').value,
                                    document.getElementById('targetNode2').value,
                                    document.getElementById('targetNode3').value
                                ];
                                var tr = this;

                                // 국가 검색이고 locale == en 일 때 targetNode2 인 citzCntrName에 영어 국가명을 넣게함
                                if ('${pageContext.response.locale == 'en'}' === 'true' && category.isCountry)
                                    targetInputId[2] = document.getElementById('targetNode2').value;

                                for ( var i = 0 , len = tr.children.length; i < len; i++ ) {
                                    if (document.getElementById(targetInputId[i])) {
                                        document.getElementById(targetInputId[i]).value = tr.children[i].firstChild.innerHTML;
                                        document.getElementById(targetInputId[i]).setAttribute('value', tr.children[i].firstChild.innerHTML);
                                        $(document.getElementById(targetInputId[i])).change();
                                    }
                                }
                                hideDialog('#modal_popup3');
                            });
                        }
                    } else {
                        if (category.isCountry) {
                            record = $('<tr>' + '<td><span style="display: none;" class="b-close">' + '999' + '</span></td>' + '<td colspan="2"><span class="b-close" style="cursor: pointer">' + '<spring:message code="U01202"/>' + '</span></td>' + '</tr>'); //
                        } else if (category.isSchool) {
                            record = $('<tr>' + '<td><span style="display: none;" class="b-close">' + '999' + '</span></td>' + '<td><span class="b-close" style="cursor: pointer; color: blue; font-weight: 300;">' + '<spring:message code="U01203"/>' + '</span></td>' + '</tr>');
                        }
                        $('#bpopResult').append(record);
                        $(record).on('click', function(e) {
                            var targetInputId = [ document.getElementById('targetNode1').value,
                                document.getElementById('targetNode2').value,
                                document.getElementById('targetNode3').value ];
                            var tr = this, resultInputText;

                            if (document.getElementById(targetInputId[0])) {
                                document.getElementById(targetInputId[0]).value = tr.children[0].firstChild.innerHTML;
                            }
                            if (document.getElementById(targetInputId[1])) {
                                resultInputText = document.getElementById(targetInputId[1]);
                                resultInputText.value = '';
                                if (category.isCountry)
                                    resultInputText.placeholder = '<spring:message code="U01204"/>'; // 다시 검색해주세요
                                if (category.isSchool) {
                                    resultInputText.placeholder = '<spring:message code="U01205"/>'; // 직접 입력해주세요
                                    resultInputText.readOnly = false;
                                    resultInputText.focus();
                                }
                            }
                            hideDialog('#modal_popup3');
                        });
                    }
                },
                error: function(xhr,status,error) {
                	$('#bpopBtnSearch').removeAttr('disabled');
                }
            });
        });

        $('#bpop').on('keyup', function(e) {
            if(e.keyCode == 13) {
                $('#bpopBtnSearch').trigger('click');
            }
        });

        $('.b-close').on('click', function(e) {
            e.preventDefault();
            hideDialog('#modal_popup3');
        });

        $('#citzCntrCode').on('change', function(e) {
            var divRgstNo = document.getElementById('divRgstNo'),
                rgstBornDate = document.getElementById('application.rgstBornDate'),
                rgstEncr = document.getElementById('application.rgstEncr'),
                divStayInfo = document.getElementById('stayInfo'),
                dateOfBirthLabel = document.getElementById('dateOfBirthLabel'),
                dateOfBirthLabel1 = '<spring:message code="L01208"/>',
                dateOfBirthLabel2 = '<spring:message code="L01216"/>',
                dateOfBirthPlaceholder1 = '<spring:message code="U01206"/>',
                dateOfBirthPlaceholder2 = '<spring:message code="U01207"/>',
                stayInfoItems, item, i, itemL;
            if (this.value == '118') {
                divRgstNo.style.display = 'block';

                if (rgstEncr) rgstEncr.style.display = 'block';
                $('.warn-rgstEncr').each( function() {
                    this.style.display = 'block';
                });

                if (admsTypeCode != 'C' && admsTypeCode != 'D') {
                    dateOfBirthLabel.innerHTML = dateOfBirthLabel1;
                    rgstBornDate.setAttribute('placeholder', dateOfBirthPlaceholder1);

                    divStayInfo.style.display = 'none';
                    stayInfoItems = divStayInfo.querySelectorAll('input');
                    itemL = stayInfoItems.length;
                    for (i = 0 ; i < itemL ; i++) {
                        item = stayInfoItems[i];
                        if (item.type == 'hidden' || item.type == 'text') {
                            item.setAttribute('value', '');
                            item.value = '';
                        }
                    }
                    stayInfoItems = divStayInfo.querySelectorAll('select');
                    itemL = stayInfoItems.length;
                    for (i = 0 ; i < itemL ; i++) {
                        stayInfoItems[i].selectedIndex = 0;
                    }
                }


            } else {
//                rgstBornDate.setAttribute('value', '');
//                rgstBornDate.value = '';
                if (rgstEncr) {
                    rgstEncr.setAttribute('value', '');
                    rgstEncr.value = '';
                    rgstEncr.style.display = 'none';
                }

                dateOfBirthLabel.innerHTML = dateOfBirthLabel2;
                rgstBornDate.setAttribute('placeholder', dateOfBirthPlaceholder2);

                $('.warn-rgstEncr').each( function() {
                    this.style.display = 'none';
                });
                divStayInfo.style.display = 'block';
            }
        });
        <%-- 국가/학교 검색 끝 --%>

        <%-- 외국인 구분 - 국적 대한민국이면 외국인 선택 불가--%>
        $('#fornTypeCode').on('change', function () {
            var citzCntrCode = document.getElementById('citzCntrCode').value;
            if (citzCntrCode == '118' && this.selectedIndex == 1) {
                alert('<spring:message code="U01301"/>'); // 국적이 대한민국이면 외국인을 선택할 수 없습니다.
                this.selectedIndex = 0;
            }
        });
        <%-- 외국인 구분 - 국적 대한민국이면 외국인 선택 불가--%>

        <%-- 장애 사항 입력란 toggle --%>
        $('#disadvantageYn').on('click', function(e) {
            e.preventDefault();
            $('#disAdv').css('display', 'block');
        });
        <%-- 장애 사항 입력란 toggle --%>

        <%-- 다음 주소 검색 시작 --%>
        var postLayer = document.getElementById('postLayer');

        var closeDaumPostCode = function () {
            // iframe을 넣은 element를 안보이게 한다.
            postLayer.style.display = 'none';
        };

        $('#btnClosePostLayer').on('click', closeDaumPostCode);

        var isStreetAddress = function (addressString) {
            var isValid = false;
            if (addressString.indexOf('로') >= 0 || addressString.indexOf('길') >= 0)
                isValid = true;
            return isValid;
        };

        var showDaumPostcode = function () {
            new daum.Postcode({
                oncomplete: function(data) {
                    // 다음 주소 검색 v2 적용으로 아래 제거

                    // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                    // 우편번호와 주소 및 영문주소 정보를 해당 필드에 넣는다.
//                        document.getElementById('postcode1').value = data.postcode1;
//                        document.getElementById('postcode2').value = data.postcode2;
                    <%--if (isStreetAddress(data.address1)) {--%>
                        <%--document.getElementById('zipCode').value = data.postcode1 + data.postcode2;--%>
                        <%--document.getElementById('address').value = data.address1;--%>
                        <%--document.getElementById('addressDetail').focus();--%>
                        <%--// iframe을 넣은 element를 안보이게 한다.--%>
                        <%--closeDaumPostCode();--%>
                    <%--} else {--%>
                        <%--// iframe을 넣은 element를 안보이게 한다.--%>
                        <%--closeDaumPostCode();--%>
                        <%--confirm('<spring:message code="U01501"/>'); // 주소를 다시 검색해서 도로명 주소를 사용해 주시기 바랍니다.--%>
                        <%--document.getElementById('zipCode').value = '';--%>
                        <%--document.getElementById('zipCode').setAttribute('value', '');--%>
                        <%--document.getElementById('address').value = '';--%>
                        <%--document.getElementById('address').setAttribute('value', '');--%>
                        <%--$('#searchAddress').trigger('click');--%>
                    <%--}--%>

//                    document.getElementById('zipCode').value = data.postcode1 + data.postcode2;
//                    document.getElementById('address').value = data.address;
//                    document.getElementById('addressDetail').focus();
//                    // iframe을 넣은 element를 안보이게 한다.
//                    closeDaumPostCode();

                    // 다음 주소 검색 v2 적용
                    // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                    // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var fullAddr = data.address; // 최종 주소 변수
                    var extraAddr = ''; // 조합형 주소 변수

                    // 기본 주소가 도로명 타입일때 조합한다.
                    if(data.addressType === 'R'){
                        //법정동명이 있을 경우 추가한다.
                        if(data.bname !== ''){
                            extraAddr += data.bname;
                        }
                        // 건물명이 있을 경우 추가한다.
                        if(data.buildingName !== ''){
                            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                        }
                        // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                        fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                    }

                    document.getElementById('zipCode').value = data.zonecode;
                    document.getElementById('address').value = fullAddr;
                    document.getElementById('addressDetail').focus();
                    // iframe을 넣은 element를 안보이게 한다.
                    closeDaumPostCode();



                },
                width : '100%',
                height : '100%'
            }).embed(postLayer);

            // iframe을 넣은 element를 보이게 한다.
            postLayer.style.display = 'block';
        };

//        $('#searchAddress').on('click', showDaumPostcode);
        $('#searchAddress').on('click', function (e) {
            e.preventDefault();
            // 다음 주소 검색 v2 적용으로 아래 내용 삭제
            <%--$( "#street-name-notice" ).dialog({--%>
                <%--modal: true,--%>
                <%--width: 380,--%>
                <%--buttons: [{--%>
                        <%--text: '<spring:message code="L01507"/>', // 확인--%>
                        <%--click: function() {--%>
                            <%--$(this).dialog("close");--%>
                            <%--showDaumPostcode();--%>
                        <%--}--%>
                <%--}]--%>
            <%--});--%>
            showDaumPostcode();
        });

        <%-- 다음 주소 검색 끝 --%>

        <%-- 달력 옵션 --%>
        var datePickerOption = {
            dateFormat: 'yymmdd',
            yearRange: "1950:2030",
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

        <%-- 비자 종류 처리 --%>
        $('#applicationForeigner\\.visaTypeCode').on('change', function () {
            var visaEtc = document.getElementById('applicationForeigner.visaTypeEtc'),
                visaEtcContainer = document.getElementById('tmpVisaTypeCode'),
                visaInfoContainer = document.getElementById('visaInfoContainer'),
                visaNo = document.getElementById('applicationForeigner.visaNo'),
                visaExprDate = document.getElementById('applicationForeigner.visaExprDay'),
                visaExprDateContainer = document.getElementById('expr-date-container'),
                fornRgstNo = document.getElementById('applicationForeigner.fornRgstNo');

            if (this.value == '00099') {
                visaEtcContainer.style.display = 'block';
                visaInfoContainer.style.display = 'block';
                visaNo.disabled = false;
                visaExprDate.disabled = false;
                visaExprDateContainer.style.display = 'block';
//                fornRgstNo.disabled = false;
            } else if (this.value == '00999') {
                visaEtc.value = '';
                visaEtcContainer.style.display = 'none';
                visaNo.value = '';
                visaExprDate.value = '';
                fornRgstNo.value = '';
                visaInfoContainer.style.display = 'none';
                visaNo.disabled = false;
                visaExprDate.disabled = false;
                visaExprDateContainer.style.display = 'none';
//                fornRgstNo.disabled = false;
            } else if (this.value == 'F-5') {
                visaEtc.value = '';
                visaEtcContainer.style.display = 'none';
                visaInfoContainer.style.display = 'block';
                visaNo.disabled = false;
                visaExprDate.value = '';
                visaExprDateContainer.style.display = 'none';
            }
            else {
                visaEtc.value = '';
                visaEtcContainer.style.display = 'none';
                visaInfoContainer.style.display = 'block';
                visaNo.disabled = false;
                visaExprDate.disabled = false;
                visaExprDateContainer.style.display = 'block';
//                fornRgstNo.disabled = false;
            }
        });
        <%-- 비자 종류 처리 --%>

        <%-- 학연산 선택에 따른 화면 변경 시작 --%>
        $('#applAttrCode').on('change', changeApplAttrCode);

        function changeApplAttrCode() {
            var index = $('#applAttrCode').find('option').filter(':selected').index();
            hideByClassname('applyKindDynamic', 'hidden-apply-kind-' + index);
        }

        function hideByClassname(parentId, hideClassname) {
            $('#' + parentId).children().each(function() {
                if ($(this).hasClass(hideClassname)) {
                    $(this).hide();
                } else {
                    $(this).show();
                }
            });
        }

        changeApplAttrCode('#applAttrCode');
        <%-- 학연산 선택에 따른 화면 변경 끝 --%>

        <%--**select 폼 change 이벤트 처리 시작
            * sourceId: 이벤트 발생되는 <select> id
            * context: 변하는 <select>정보 (json)
            *          targetId:변하는 <select> id
            *          valueKey: <option>의 value로 쓰인 property 명
            *          labelKey: <option>의 label로 쓰인 property 명
            *          clean: option 지울 <select> id
            *          url: url의 나머지 부분, string이거나 function
        --%>
        function attachChangeEvent( sourceId, context ) {
            var $source = $('#' + sourceId);

            $source.on('change', function(event) {
                var info, targetId, valueKey, labelKey, url, clean, addon, i;
                var baseUrl = '${contextPath}/common/code';
                var val = this.options[this.selectedIndex].value;

                info = context;
                if (context.hasOwnProperty($source.val())) {
                    info = context[$source.val()];
                }

                targetId = info.targetId ? info.targetId : context.targetId;
                if( !targetId ) {
                    return;
                }

                valueKey = info.valueKey ? info.valueKey : context.valueKey;
                labelKey = info.labelKey ? info.labelKey : context.labelKey;
                url = info.url ? info.url : context.url;
                if( url && typeof url === 'function' ) {
                    baseUrl += url(val);
                } else if( url ) {
                    baseUrl += url;
                }

                clean = info.clean ? info.clean : context.clean;
                clean = [].concat( targetId, clean );
                var $clean, oldVal;
                for (i = 0; i < clean.length; i++) {
                    if (clean[i]) {
                        $clean = $('#' + clean[i]);
                        oldVal = $clean.val();
                        $clean.children('option').filter(function() {
                            return this.value !== '';
                        }).remove();
                        if (oldVal !== $clean.val()) {
                            $clean.trigger('change');
                        }
                    }
                }

                if (!val || val == '') {
                    return;
                }

                $.ajax({
                    type: 'GET',
                    url: baseUrl,
                    success: function(e) {
                        var container = JSON.parse(e),
                                data = JSON.parse(container.data);
                        if(container.result && container.result === 'SUCCESS') {
                            var $target = $('#' + targetId);
//                            var data = JSON && JSON.parse(e.data) || $.parseJSON(e.data);

                            $(data).each(function (i, item) {
                                var $op = $('<option>').attr({
                                    'value': item[valueKey],
                                    'label': item[labelKey]
                                });
                                $op.html(item[labelKey]);
                                if ('detlMajCode' == targetId) {
                                    for (var key in item) {
                                        if (key !== valueKey && key !== labelKey) {
                                            $op.attr(key, item[key]);
                                        }
                                    }
                                }
                                $op.appendTo($target);
                            });
                        }
                    },
                    error: function(e) {
                        if(console) console.log(e);
                    }
                });
            });
        }
        <%-- select 폼 change 이벤트 처리 끝 --%>

        <%--지원사항 select 폼 change 이벤트 핸들러 등록 시작 --%>
        <%-- 지원구분 변경 --%>
        attachChangeEvent( 'applAttrCode',
                {
                    '00002': {targetId: 'ariInstCode', valueKey: 'ariInstCode', labelKey: 'ariInstName', url: '/ariInst'}, // applAttrCode == '02'
                    targetId: 'campCode',
                    valueKey: 'campCode',
                    labelKey: '${pageContext.response.locale == 'en' ? 'campNameXxen' : 'campName'}',
                    clean: ['collCode', 'ariInstCode', 'deptCode', 'corsTypeCode', 'detlMajCode'],
                    url: '/campus'
                }
        );

        <%-- 캠퍼스 변경 --%>
        attachChangeEvent( 'campCode',
                {
                    targetId: 'collCode',
                    valueKey: 'collCode',
//                    labelKey: 'collName',
                    labelKey: '${pageContext.response.locale == 'en' ? 'collNameXxen' : 'collName'}',
                    // clean: ['ariInstCode', 'deptCode', 'corsTypeCode', 'detlMajCode'],
                    url: function(arg) {
                        return '/admscollege/' + admsNo + '/' + arg;
                    }
                }
        );

        <%-- 대학변경 --%>
        attachChangeEvent( 'collCode',
                {
                    targetId: 'deptCode',
                    valueKey: 'deptCode',
//                    labelKey: 'deptName',
                    labelKey: '${pageContext.response.locale == 'en' ? 'deptNameXxen' : 'deptName'}',
                    // clean: ['corsTypeCode', 'detlMajCode'],
                    url: function(arg) {
                        var admsNo = $('#admsNo').val();
                        return '/general/department/' + admsNo + '/' + arg;
                    }
                }
        );

        <%-- 학연산 변경 --%>
        attachChangeEvent( 'ariInstCode',
                {
                    targetId: 'deptCode',
                    valueKey: 'deptCode',
//                    labelKey: 'deptName',
                    labelKey: '${pageContext.response.locale == 'en' ? 'deptNameXxen' : 'deptName'}',
                    // clean: ['corsTypeCode', 'detlMajCode'],
                    url: function(arg) {
                        var admsNo = $('#admsNo').val();
                        return '/ariInst/department/' + admsNo + '/' + arg;
                    }
            }
        );

        <%-- 지원학과 변경 --%>
        attachChangeEvent( 'deptCode',
                {
                    targetId: 'corsTypeCode',
                    valueKey: 'corsTypeCode',
//                    labelKey: 'codeVal',
                    labelKey: '${pageContext.response.locale == 'en' ? 'codeValXxen' : 'codeVal'}',
                    // clean: ['detlMajCode'],
                    url: function(arg) {   <%-- 지원과정 조회 --%>
                        var admsNo = $('#admsNo').val();
                        var applAttrCode = $('#applAttrCode').val();
                        if (applAttrCode == '00001') {
                            return '/general/course/' + admsNo + '/' + arg;
                        } else if (applAttrCode == '00002') {
                            return '/ariInst/course/' + admsNo + "/" + arg + "/" + $('#ariInstCode').val();
                        } else if (applAttrCode == '00003') {
                            return '/commission/course/' + admsNo + '/' + arg;
                        } else if (applAttrCode == '00004') {
                            return '/northDefector/course/' + admsNo + '/' + arg;
                        }
                    }
                }
        );

        <%-- 지원과정 변경 --%>
        attachChangeEvent( 'corsTypeCode',
                {
                    targetId: 'detlMajCode',
                    valueKey: 'detlMajCode',
//                    labelKey: 'detlMajName',
                    labelKey: '${pageContext.response.locale == 'en' ? 'detlMajNameXxen' : 'detlMajName'}',
                    url: function(arg) {
                        var admsNo = $('#admsNo').val();
                        var applAttrCode = $('#applAttrCode').val();
                        if (applAttrCode == '00001') {
                            return '/general/detailMajor/' + admsNo + '/' + $('#deptCode').val() + '/' + arg;
                        } else if (applAttrCode == '00002') {
                            return '/ariInst/detailMajor/' + admsNo + "/" + $('#deptCode').val() + "/" + $('#ariInstCode').val() + '/' + arg;
                        } else if (applAttrCode == '00003') {
                            return '/general/detailMajor/' + admsNo + '/' + $('#deptCode').val() + '/' + arg;
                        } else if (applAttrCode == '00004') {
                            return '/general/detailMajor/' + admsNo + '/' + $('#deptCode').val() + '/' + arg;
                        }
                    }
                }
        );

        <%-- 지원학과 경영대일 경우 --%>
        $('#deptCode').on('change', function() {
            var val = $(this).val();
            if (val === '10202' || val === '11202') {
                $('#currentCompany').show();
            } else {
                $('#currentCompany').hide();
            }
        });

        <%-- 세부전공 변경 시 세부전공 하위 변경 --%>
        $('#detlMajCode').on('change', function(event) {
            var selected = this.options[this.selectedIndex];
            var detlMajCode = selected.value;
            var parent = this.parentNode.parentNode;
            var $divNode,$divNode2, $childNode, $childNode2, $childNode3;

//            $(parent).find('#detlMajText').remove();
//            $(parent).find('#detlMajDescDiv').remove();
//            $(parent).find('#detlMajDescLabel').remove();

            if (detlMajCode == '99999') {   // 세부전공 직접입력
//                $(parent).find('#inpDetlMaj').attr("style", "display");
                document.getElementById('inpDetlMaj').style.display = 'block';
                document.getElementById('partTimeYnGrp').style.display = 'block';

//                $(parent).find('#inpDetlMaj').attr("style", "display");
//                $(parent).find('#partTimeYnGrp').attr("disabled", "false");
            }else{
                document.getElementById('inpDetlMaj').style.display = 'none';
                document.getElementById('partTimeYnGrp').style.display = 'none';
//                $(parent).find('#inpDetlMaj').attr("style", "display:none");
//                $(parent).find('#partTimeYnGrp').attr("disabled", "true");
            }
            if( selected.getAttribute('partTimeYn') === 'Y' || selected.getAttribute('partTimeYn') === 'y') { // 세부전공 PART_TIME_YN이 Y인 경우
                document.getElementById('partTimeYnGrp').style.display = 'block';

//                $(parent).find('#partTimeYnGrp').attr("style", "display");
//                $(parent).find('#partTimeYnGrp').attr("disabled", "false");
            }else{
                document.getElementById('partTimeYnGrp').style.display = 'none';
//                $(parent).find('#partTimeYnGrp').attr("style", "display:none");
//                $(parent).find('#partTimeYnGrp').attr("disabled", "true");
            }
//            var temp = jQuery.type($(selected).attr('detlmajdesc'));
//            var temp2 = $(selected).attr('detlmajdesc');
//            if (jQuery.type($(selected).attr('detlmajdesc')) !=='undefined') { // 세부전공 desc 가 들어가 있는경우
//                $divNode = $('<div></div>').addClass('col-sm-offset-2 col-sm-9').attr({
//                    'id': 'detlMajDescDiv'
//                });
//                $childNode = $('<label/>').addClass('apexMsg').text(temp2).autoLink();
//                $childNode.appendTo($divNode);
//                $divNode.appendTo($(parent));
//            }

        });
        <%-- 지원사항 select 폼 change 이벤트 핸들러 등록 끝 --%>

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
