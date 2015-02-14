<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang="ko">
<head>
    <title>원서 작성 - 기본 정보</title>
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
            /*color: #fff;*/
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
        <form:form commandName="basis" cssClass="form-horizontal" method="post" role="form">
            <form:hidden path="application.applNo" id="applNo" />
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
                        <div class="panel panel-darkgray">
                            <div class="panel-heading">지원 사항</div>
                            <div class="panel-body">
                                <div class="form-group required">
                                    <label for="applAttrCode" class="col-sm-2 control-label">지원 구분</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-12">
                                            <form:select path="application.applAttrCode" id="applAttrCode" cssClass="form-control base-info">
                                                <form:option value="" label="--선택--" />
                                                <form:options items="${selection.applAttrList}" itemValue="code" itemLabel="codeVal"/>
                                            </form:select>
                                        </div>
                                    </div>
                                </div>
                                <div id="applyKindDynamic">
                                    <div class="form-group hidden-apply-kind-2 required">
                                        <label path="campCode" class="col-sm-2 control-label">캠퍼스</label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-3">
                                                <form:select path="application.campCode" id="campCode" cssClass="form-control base-info">
                                                    <form:option value="" label="--선택--" />
                                                    <c:if test="${basis.application.applAttrCode == '00001' || basis.application.applAttrCode == '00003'}">
                                                        <form:options items="${selection.campList}" itemValue="campCode" itemLabel="campName" />
                                                    </c:if>
                                                </form:select>
                                            </div>
                                            <label path="collCode" class="col-sm-2 control-label">대학</label>
                                            <div class="col-sm-7">
                                                <form:select path="application.collCode" id="collCode" cssClass="form-control base-info">
                                                    <form:option value="" label="--선택--" />
                                                    <c:if test="${basis.application.applAttrCode == '00001' || basis.application.applAttrCode == '00003'}">
                                                        <form:options items="${selection.collList}" itemValue="collCode" itemLabel="collName" />
                                                    </c:if>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group hidden-apply-kind-0 hidden-apply-kind-1 hidden-apply-kind-3 required">
                                        <label for="ariInstCode" class="col-sm-2 control-label">학·연·산 연구기관</label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-12">
                                                <form:select path="application.ariInstCode" id="ariInstCode" cssClass="form-control base-info">
                                                    <form:option value="" label="--선택--" />
                                                    <c:if test="${basis.application.applAttrCode == '00002'}">
                                                    <form:options items="${selection.ariInstList}" itemValue="ariInstCode" itemLabel="ariInstName" />
                                                    </c:if>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <label for="deptCode" class="col-sm-2 control-label">지원 학과</label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-12">
                                                <form:select path="application.deptCode" id="deptCode" cssClass="form-control base-info">
                                                    <form:option value="" label="--선택--" />
                                                    <form:options items="${selection.deptList}" itemValue="deptCode" itemLabel="deptName" />
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <label for="corsTypeCode" class="col-sm-2 control-label">지원 과정</label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-12">
                                                <form:select path="application.corsTypeCode" id="corsTypeCode" cssClass="form-control base-info">
                                                    <form:option value="" label="--선택--" />
                                                    <form:options items="${selection.corsTypeList}" itemValue="corsTypeCode" itemLabel="codeVal" />
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <label for="detlMajCode" class="col-sm-2 control-label">세부 전공</label>
                                        <div class="col-sm-9">
                                            <div class="col-sm-12">
                                                <form:select path="application.detlMajCode" id="detlMajCode" cssClass="form-control base-info">
                                                    <form:option value="" label="--선택--" />
                                                    <form:options items="${selection.detlMajList}" itemValue="detlMajCode" itemLabel="detlMajName" />
                                                </form:select>
                                            </div>
                                            <div class="col-sm-7">
                                                <label id="detMajDesc" class="apexMessage"></label>
                                            </div>
                                            <div class="col-sm-5">
                                                <form:input path="application.inpDetlMaj" cssClass="form-control" style="display:none" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <div class="col-sm-11" id="baseSave">
                                        <span class="col-sm-8"><spring:message code="U310"/></span>
                                        <button id="btnBaseSave" class="btn btn-info btn-lg col-sm-4">지원사항 저장</button>
                                    </div>
                                    <div class="col-sm-11" id="baseCancel" style="display:none;">
                                        <span class="col-sm-8"><spring:message code="U311"/></span>
                                        <button id="btnBaseCancel" class="btn btn-warning btn-lg col-sm-4">지원사항 취소</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="panel panel-darkgray">
                            <div class="panel-heading">지원자 정보</div>
                            <div class="panel-body">
                                <div class="form-group required">
                                    <label for="application.korName" class="col-sm-2 control-label">한글 이름</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-12">
                                            <form:input path="application.korName" cssClass="form-control" placeholder="한글 이름을 공백 없이 입력해주세요"/>
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
                                    <label class="col-sm-2 control-label">영문 이름</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <span class="input-group-addon">&nbsp;성&nbsp;</span>
                                                <form:input path="application.engSur" cssClass="form-control engName" />
                                            </div>
                                    <spring:bind path="application.engSur">
                                        <c:if test="${status.error}">
                                            <div>
                                                <div class="validation-error">${status.errorMessage}</div>
                                            </div>
                                        </c:if>
                                    </spring:bind>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <span class="input-group-addon">이름</span>
                                                <form:input path="application.engName" cssClass="form-control engName" />
                                            </div>
                                    <spring:bind path="application.engName">
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
                                    <label for="application.rgstNo" class="col-sm-2 control-label">주민등록번호</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-12">
                                            <form:input path="application.rgstNo" cssClass="form-control numOnly" maxlength="13" placeholder="주민등록번호를 13자리 숫자로 입력해주세요"/>
                                        </div>
                                <spring:bind path="application.rgstNo">
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
                    <c:when test="${basis.application.admsTypeCode != 'C'}">
                        <div class="panel panel-darkgray">
                            <div class="panel-heading">지원자 상세정보</div>
                            <div class="panel-body">
                                <div class="form-group required">
                                    <label for="citzCntrName" class="col-sm-2 control-label">국적</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-3">
                                            <button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="citzCntrCode" data-targetNode2='citzCntrName' data-category="country">
                                                <span class="glyphicon glyphicon-search"></span> 검색
                                            </button>
                                        </div>
                                        <div class="col-sm-9">
                                            <form:hidden path="application.citzCntrCode" id="citzCntrCode" cssClass="form-control" />
                                            <input id="citzCntrName" class="form-control" value="${cntrCntr.korCntrName}" readonly="true"/>
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
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">장애 사항</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-12">
                                            <div class="input-group">
                                                <span class="input-group-addon">장애유형</span>
                                                <form:input path="applicationGeneral.hndcGrad" cssClass="col-sm-6 form-control" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-9">
                                        <div class="col-sm-12">
                                            <div class="input-group">
                                                <span class="input-group-addon">장애등급</span>
                                                <form:input path="applicationGeneral.hndcType" cssClass="col-sm-6 form-control" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise> <%-- 외국인 --%>
                        <div class="panel panel-darkgray">
                            <div class="panel-heading">지원자 상세정보</div>
                            <div class="panel-body">
                                <div class="form-group required">
                                    <label for="citzCntrName" class="col-sm-2 control-label">출생국</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-3">
                                            <button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="bornCntrCode" data-targetNode2='bornCntrName' data-category="country">
                                                <span class="glyphicon glyphicon-search"></span> 검색
                                            </button>
                                        </div>
                                        <div class="col-sm-9">
                                            <form:hidden path="applicationForeigner.bornCntrCode" id="bornCntrCode" cssClass="form-control" />
                                            <input id="bornCntrName" class="form-control" value="${bornCntr.korCntrName}" readonly="true"/>
                                        </div>
                                        <spring:bind path="applicationForeigner.bornCntrCode">
                                            <c:if test="${status.error}">
                                                <div class="col-sm-12">
                                                    <div class="validation-error">${status.errorMessage}</div>
                                                </div>
                                            </c:if>
                                        </spring:bind>
                                    </div>
                                </div>
                                <div class="form-group required">
                                    <label for="citzCntrName" class="col-sm-2 control-label">국적</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-3">
                                            <button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="citzCntrCode" data-targetNode2='citzCntrName' data-category="country">
                                                <span class="glyphicon glyphicon-search"></span> 검색
                                            </button>
                                        </div>
                                        <div class="col-sm-9">
                                            <form:hidden path="application.citzCntrCode" id="citzCntrCode" cssClass="form-control" />
                                            <input id="citzCntrName" class="form-control" value="${ctznCntr.korCntrName}" readonly="true"/>
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
                                <div class="form-group required">
                                    <label class="col-sm-2 control-label">외국인 구분</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-4">
                                            <form:select path="application.fornTypeCode" id="fornTypeCode" cssClass="form-control">
                                                <form:option value="" label="--선택--" />
                                                <form:options items="${foreign.foreignTypeList}" itemValue="code" itemLabel="codeVal" />
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
                                    <label class="col-sm-2 control-label">본국 주소</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-12">
                                            <form:input path="applicationForeigner.homeAddr" cssClass="form-control" placeholder="본국 주소를 입력해 주세요."/>
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
                                    <label class="col-sm-2 control-label">본국 연락처</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-12">
                                            <form:input path="applicationForeigner.homeTel" cssClass="form-control" placeholder="본국 연락처를 입력해 주세요."/>
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


                    <c:if test="${basis.application.admsTypeCode == 'C'}">
                        <div class="panel panel-darkgray">
                            <div class="panel-heading">체류 정보</div>
                            <div class="panel-body">
                                <div class="form-group required">
                                    <label for="applicationForeigner.paspNo" class="col-sm-2 control-label">여권 번호</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-12">
                                            <form:input path="applicationForeigner.paspNo" cssClass="form-control" placeholder="여권 번호를 입력해주세요"/>
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
                                    <label class="col-sm-2 control-label">비자</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-12">
                                            <div class="input-group">
                                                <span class="input-group-addon">&nbsp;번호&nbsp;</span>
                                                <form:input path="applicationForeigner.visaNo" cssClass="form-control" />
                                            </div>
                                            <spring:bind path="applicationForeigner.visaNo">
                                                <c:if test="${status.error}">
                                                    <div>
                                                        <div class="validation-error">${status.errorMessage}</div>
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-9">
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <span class="input-group-addon">종류</span>
                                                <form:input path="applicationForeigner.modId" cssClass="form-control engName" />
                                            </div>
                                            <spring:bind path="applicationForeigner.modId">
                                                <c:if test="${status.error}">
                                                    <div>
                                                        <div class="validation-error">${status.errorMessage}</div>
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                        </div>
                                        <div class="col-sm-6 start-date-container">
                                            <div class="input-group date">
                                                <span class="input-group-addon">만료일</span>
                                                <form:input path="applicationForeigner.visaExprDay" cssClass="form-control" readonly="true" />
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
                                <div class="form-group required">
                                    <label for="applicationForeigner.creId" class="col-sm-2 control-label">외국인등록번호</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-12">
                                            <form:input path="applicationForeigner.creId" cssClass="form-control numOnly" maxlength="13" placeholder="외국인등록번호를 13자리 숫자로 입력해주세요"/>
                                        </div>
                                        <spring:bind path="applicationForeigner.creId">
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
                    </c:if>

                        <div class="panel panel-darkgray">
                            <div class="panel-heading">지원자 연락처</div>
                            <div class="panel-body">
                                <div class="form-group required">
                                    <label class="col-sm-2 control-label">주소</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-3">
                                            <button type="button" class="btn btn-default btn-block btn-search" id="searchAddress">
                                                <span class="glyphicon glyphicon-search"></span> 우편번호 찾기
                                            </button>
                                        </div>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <form:input path="application.zipCode" cssClass="form-control" id="zipCode" readonly="true"/>
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
                                            <form:input path="application.addr" cssClass="form-control" id="address" readonly="true" />
                                        </div>
                                        <div class="col-sm-6">
                                            <form:input path="application.detlAddr" cssClass="form-control" id="addressDetail" placeholder="세부주소" />
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
                                <div class="form-group required">
                                    <label for="application.telNum" class="col-sm-2 control-label">전화번호</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-12">
                                            <form:input path="application.telNum" cssClass="form-control numOnly" maxlength="20" placeholder="전화번호를 '-'와 숫자로만 입력해주세요"/>
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
                                <div class="form-group required">
                                    <label for="application.mobiNum" class="col-sm-2 control-label">휴대폰</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-12">
                                            <form:input path="application.mobiNum" cssClass="form-control numOnly" maxlength="20" placeholder="휴대폰번호를 '-'와 숫자로만 입력해주세요"/>
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
                                    <label for="application.mailAddr" class="col-sm-2 control-label">E-mail</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-12">
                                            <form:input path="application.mailAddr" type="email" cssClass="form-control emailOnly" placeholder="이메일 주소를 입력해주세요"/>
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
                    <c:when test="${basis.application.admsTypeCode != 'C'}">
                        <div class="panel panel-darkgray">
                            <div class="panel-heading">비상연락처</div>
                            <div class="panel-body">
                                <div class="form-group required">
                                    <label for="applicationGeneral.emerContName" class="col-sm-2 control-label">이름</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-12">
                                            <form:input path="applicationGeneral.emerContName" cssClass="form-control" />
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
                                <div class="form-group">
                                    <label for="applicationGeneral.emerContCode" class="col-sm-2 control-label">관계</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-12">
                                            <form:select path="applicationGeneral.emerContCode" cssClass="form-control">
                                                <form:option value="" label="--선택--" />
                                                <form:options items="${selection.emerContList}" itemValue="code" itemLabel="codeVal" />
                                            </form:select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group required">
                                    <label for="applicationGeneral.emerContTel" class="col-sm-2 control-label">전화번호</label>
                                    <div class="col-sm-9">
                                        <div class="col-sm-12">
                                            <form:input path="applicationGeneral.emerContTel" cssClass="form-control numOnly" maxlength="20" placeholder="전화번호를 '-'와 숫자로만 입력해주세요"/>
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
                        <div class="panel panel-darkgray">
                            <div class="panel-heading">비상연락처</div>
                            <div class="panel-body">
                                <div class="panel panel-darkgray1">
                                    <div class="panel-heading">국내</div>
                                    <div class="panel-body">
                                        <div class="form-group required">
                                            <label for="applicationForeigner.korEmrgName" class="col-sm-2 control-label">이름</label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-12">
                                                    <form:input path="applicationForeigner.korEmrgName" cssClass="form-control" />
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
                                        <div class="form-group">
                                            <label for="applicationForeigner.korEmrgRela" class="col-sm-2 control-label">관계</label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-12">
                                                    <form:select path="applicationForeigner.korEmrgRela" cssClass="form-control">
                                                        <form:option value="" label="--선택--" />
                                                        <form:options items="${selection.emerContList}" itemValue="code" itemLabel="codeVal" />
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group required">
                                            <label for="applicationForeigner.korEmrgTel" class="col-sm-2 control-label">전화번호</label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-12">
                                                    <form:input path="applicationForeigner.korEmrgTel" cssClass="form-control numOnly" maxlength="20" placeholder="전화번호를 '-'와 숫자로만 입력해주세요"/>
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
                                    <div class="panel-heading">본국</div>
                                    <div class="panel-body">
                                        <div class="form-group required">
                                            <label for="applicationForeigner.homeEmrgName" class="col-sm-2 control-label">이름</label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-12">
                                                    <form:input path="applicationForeigner.homeEmrgName" cssClass="form-control" />
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
                                        <div class="form-group">
                                            <label for="applicationForeigner.homeEmrgRela" class="col-sm-2 control-label">관계</label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-12">
                                                    <form:select path="applicationForeigner.homeEmrgRela" cssClass="form-control">
                                                        <form:option value="" label="--선택--" />
                                                        <form:options items="${selection.emerContList}" itemValue="code" itemLabel="codeVal" />
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group required">
                                            <label for="applicationForeigner.homeEmrgTel" class="col-sm-2 control-label">전화번호</label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-12">
                                                    <form:input path="applicationForeigner.homeEmrgTel" cssClass="form-control numOnly" maxlength="20" placeholder="전화번호를 '-'와 숫자로만 입력해주세요"/>
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

                        <div class="panel panel-default" id="currentCompany" hidden>
                            <div class="panel-heading">현재 근무처</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label for="applicationGeneral.currWrkpName" class="col-sm-2 control-label">회사 이름</label>
                                    <div class="col-sm-9">
                                        <form:input path="applicationGeneral.currWrkpName" cssClass="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label path="applicationGeneral.currWrkpDay" cssClass="col-sm-2 control-label">입사 일자</label>
                                    <div class="col-sm-9">
                                        <div class="input-group date">
                                            <form:input path="applicationGeneral.currWrkpDay" cssClass="col-sm-6 form-control" readonly="true" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="applicationGeneral.currWrkpTel" class="col-sm-2 control-label">연락처</label>
                                    <div class="col-sm-9">
                                        <form:input path="applicationGeneral.currWrkpTel" cssClass="form-control numOnly" maxlength="20" placeholder="연락처를 '-'와 숫자로만 입력해주세요"/>
                                    </div>
                                </div>
                            </div>
                        </div><%--panel--%>
                    </div>
                </div><%--row--%>
                <div class="btn-group btn-group-justified">
                    <div class="btn-group">
                        <button id="saveBasis" type="button" class="btn btn-primary btn-lg btn-save">기본 정보 저장</button>
                    </div>
                </div>
            </div> <%--myTabContent--%>
        </form:form>
    </div> <%--container--%>

    <%-- 국가/학교 검색 팝업 --%>
    <div id="bpopContainer" class="bpopContainer">
        <span class="button b-close"><span>X</span></span>
        <div id="bpopContent">
            <div class="form-group">
                <label id="searchTitle"></label>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <input type="text" id="bpop" name="bpop" class="form-control ime-mode-kr" />
                </div>
                <button id="bpopBtnSearch" class="btn btn-info col-sm-2">검색</button>
            </div>
            <div class="form-group">
                <div class="col-sm-12" style="overflow-y: auto; height: 300px;">
                    <table class="table table-stripped">
                        <thead id="bpopHead">
                        </thead>
                        <tbody id="bpopResult">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="targetNode1" />
    <input type="hidden" id="targetNode2" />
    <input type="hidden" id="targetNode3" />
    <%-- 국가/학교 검색 팝업 --%>

    <%-- 도로명 주소 사용 안내 팝업 --%>
    <div id="street-name-notice" title="도로명 주소 사용 안내" style="display:none;">
        <p>주소 검색 결과에서 '지번 주소'를 클릭하지 마시고,<br/>아래와 같이 <b>도로명 주소</b>를 사용해 주시기 바랍니다.</p>
        <p align="center"><img src="${contextPath}/img/application/street-name-capture.png"/></p>
    </div>
    <%-- 도로명 주소 사용 안내 팝업 --%>

    <%-- 다음 주소 검색 팝업 --%>
    <div id="postLayer" style="display:none;border:5px solid;position:fixed;width:310px;height:510px;left:50%;margin-left:-155px;top:50%;margin-top:-235px;overflow:hidden;-webkit-overflow-scrolling:touch;z-index:2;background-color:#fff;color: #111;">
        <img src="${contextPath}/img/user/addr-close.png" id="btnClosePostLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px" alt="닫기 버튼">
    </div>
    <%-- 다음 주소 검색 팝업 --%>


</section>
<content tag="local-script">
    <script src="http://dmaps.daum.net/map_js_init/postcode.js"></script>
    <script src="${contextPath}/js/jquery-ui.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
        var applNo = document.getElementById('applNo').value = '${basis.application.applNo}',
            admsNo = document.getElementById('admsNo').value = '${basis.application.admsNo}',
            applStsCode = document.getElementById('applStsCode').value = '${basis.application.applStsCode}',
            entrYear = document.getElementById('entrYear').value = '${basis.application.entrYear}',
            admsTypeCode = document.getElementById('admsTypeCode').value = '${basis.application.admsTypeCode}',
            corsTypeCode = '${basis.application.corsTypeCode}';

        <%-- 원서 작성 현황 및 사용 가능한 탭 처리 --%>
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
        <%-- 원서 작성 현황 및 사용 가능한 탭 처리 --%>

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

        <%-- 지원 사항 저장 버튼 처리 --%>
        var baseInfoSaved = function() {
            $('.base-info>option').filter( function() {
                return !this.selected;
            }).each( function() {
                $(this).prop('disabled', true);
            });

            $('#baseCancel').css('display', 'block');
            $('#baseSave').css('display', 'none');
        };

        // 입력 검증 오류로 DB에 저장되지 않고 다시 돌아왔을 때 지원사항 저장 상태로 설정
        if (document.getElementById('detlMajCode').value.length > 0) baseInfoSaved();
        // TODO : 지원사항 저장 시 applNo 따서 DB에 저장하는 것으로 로직 수정
        <%-- 지원 사항 저장 버튼 처리 --%>

        <%-- 기본 정보 > 지원 사항 처리 --%>
        $('#btnBaseSave').on('click', function(e) {
            e.preventDefault();
            if ( confirm('<spring:message code="U313"/>') ) {
                baseInfoSaved();
            } else {
                return false;
            }
        });

        $('#btnBaseCancel').on('click', function(e) {
            e.preventDefault();
            var form, applStsCode;
            if ( confirm('<spring:message code="U314"/>') ) {
                //TODO 지원 취소로 상태 변경 후 공고 목록으로 이동
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
            var $form = $(this),
                form = document.getElementById('basis'),
                isValidProcess = true;

            if ($('#baseSave').css('display') == 'block') {
                isValidProcess = false;
                alert('<spring:message code="U329"/>');
                $('#applAttrCode').focus();
            } else {
                form.action = "${contextPath}/application/basis/save";
                form.submit();
            }
        };
        $('.btn-save').on('click', formProcess);
        <%-- 하단 버튼 처리 --%>

        <%-- 한글 이름 공백 제거 --%>
        var removeSpaceInKorName = function () {
            var korName = document.getElementById('application.korName');
            $(korName).on('blur', function () {
                this.value = this.value.replace(/(\s*)/gi,"");
            });
        };
        removeSpaceInKorName();
        <%-- 한글 이름 공백 제거 --%>

        <%-- 영문 이름 처리 시작 --%>
        $('.engName').on('keyup', function() {
            this.value = this.value.toUpperCase().replace(/([^0-9A-Z])/g,"");
        });
        <%-- 영문 이름 처리 끝 --%>

        <%-- 숫자만 입력 - 주민번호, 휴대폰, 전화번호 --%>
        $('.numOnly').on('keyup', function () {
            var numCheckRegExp = this.id == 'application.rgstNo' ? /^[0-9]*$/ : /^[0-9-]*$/,
                val = this.value;
            if (!numCheckRegExp.test(val)) {
                this.value = val.substr(0, val.length-1);
            }
        });
        <%-- 숫자만 입력 - 주민번호, 휴대폰, 전화번호 --%>

        <%-- 메일 주소 validation --%>
        $('.emailOnly').on('blur', function () {
            var emailRegExp = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
                val = this.value;
            if (!emailRegExp.test(val) && val != '') {
                alert("이메일 주소를 정확히 기재해 주세요")
                this.value = "";
                this.focus();
            }
        });
        <%-- 메일 주소 validation --%>

        <%-- 국가/학교 검색 시작 --%>
        $('.bpopper').on('click', function(e) {
            e.preventDefault();
            $('#bpopResult').empty();
            document.getElementById('bpop').value="";

            var dataCategory = this.getAttribute('data-category');
            var targetNode = null, title = null, columnHead = [];
            for (var i = 1; i < 4; i++) {
                targetNode = this.getAttribute('data-targetNode' + i);
                document.getElementById('targetNode' + i).value = targetNode ? targetNode : null;
            }
            if (dataCategory.indexOf('country') > -1) {
                title = '국가 검색';
                columnHead = ['', '한글 이름', '영문 이름'];
                document.getElementById('bpopContent').setAttribute('data-category', dataCategory);
            } else if (dataCategory.indexOf('school') > -1) {
                columnHead = ['', '학교 이름'];
                document.getElementById('bpopContent').setAttribute('data-category', dataCategory);
                var suffix = dataCategory.split('-')[1];
                if (suffix == 'h') {
                    title = '고등학교 검색';
                } else if (suffix == 'u') {
                    title = '대학교 검색';
                } else if (suffix == 'g') {
                    title = '대학원 검색';
                } else {
                }
            }

            if (title != null) {
                $('#bpopContent').find('#searchTitle').text(title);
            }
            var $thead = $('<tr></tr>');
            for (var i = 0, len = columnHead.length; i < len; i++) {
                $thead.append($('<td>' + columnHead[i] + '</td>'));
            }
            $('#bpopHead').empty();
            $('#bpopHead').append($thead);
            $('#bpopContainer').bPopup();
            document.getElementById('bpop').focus();
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

            $.ajax({
                type: 'GET',
                url: url,
                success: function(data) {

//                    var obj = JSON.parse(data.data), record, i, l;
                    var container = JSON.parse(data),
                        obj = JSON.parse(container.data),
                        record, i, l;

                    if (obj.length > 0) {
                        for ( i = 0, l = obj.length ; i < l ; i++ ) {
                            if (category.isCountry) {
                                record = $('<tr>' + '<td><span style="display: none;" class="b-close">' + obj[i].cntrCode + '</span></td>' + '<td><span class="b-close" style="cursor: pointer">' + obj[i].korCntrName + '</span></td>' + '<td><span class="b-close" style="cursor: pointer">' + obj[i].engCntrName + '</span></td>' + '</tr>');
                            } else if (category.isSchool) {
                                record = $('<tr>' + '<td><span style="display: none;" class="b-close">' + obj[i].schlCode + '</span></td>' + '<td><span class="b-close" style="cursor: pointer">' + obj[i].schlName + '</span></td>' + '</tr>');
                            }
                            $('#bpopResult').append(record);
                            $(record).on('click', function(e) {
                                var targetInputId = [ document.getElementById('targetNode1').value,
                                    document.getElementById('targetNode2').value,
                                    document.getElementById('targetNode3').value ];
                                var tr = this;
                                for ( var i = 0 , len = tr.children.length; i < len; i++ ) {
                                    if (document.getElementById(targetInputId[i])) {
                                        document.getElementById(targetInputId[i]).value = tr.children[i].firstChild.innerText;
                                    }
                                }
                            });
                        }
                    } else {
                        if (category.isCountry) {
                            record = $('<tr>' + '<td><span style="display: none;" class="b-close">' + '999' + '</span></td>' + '<td colspan="2"><span class="b-close" style="cursor: pointer">' + '검색 결과가 없습니다. 다시 검색해 주세요' + '</span></td>' + '</tr>');
                        } else if (category.isSchool) {
                            record = $('<tr>' + '<td><span style="display: none;" class="b-close">' + '999' + '</span></td>' + '<td><span class="b-close" style="cursor: pointer">' + '검색 결과가 없습니다. 여기를 눌러 직접 입력해 주세요' + '</span></td>' + '</tr>');
                        }
                        $('#bpopResult').append(record);
                        $(record).on('click', function(e) {
                            var targetInputId = [ document.getElementById('targetNode1').value,
                                document.getElementById('targetNode2').value,
                                document.getElementById('targetNode3').value ];
                            var tr = this, resultInputText;

                            if (document.getElementById(targetInputId[0])) {
                                document.getElementById(targetInputId[0]).value = tr.children[0].firstChild.innerText;
                            }
                            if (document.getElementById(targetInputId[1])) {
                                resultInputText = document.getElementById(targetInputId[1]);
                                resultInputText.value = '';
                                if (category.isCountry)
                                    resultInputText.placeholder = '다시 검색해주세요';
                                if (category.isSchool) {
                                    resultInputText.placeholder = '직접 입력해주세요';
                                    resultInputText.readOnly = false;
                                    resultInputText.focus();
                                }
                            }
                        });
                    }
                }
            });
        });

        $('#bpop').on('keyup', function(e) {
            if(e.keyCode == 13) {
                $('#bpopBtnSearch').trigger('click');
            }
        });
        <%-- 국가/학교 검색 끝 --%>

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
                    // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                    // 우편번호와 주소 및 영문주소 정보를 해당 필드에 넣는다.
//                        document.getElementById('postcode1').value = data.postcode1;
//                        document.getElementById('postcode2').value = data.postcode2;
                    if (isStreetAddress(data.address1)) {
                        document.getElementById('zipCode').value = data.postcode1 + data.postcode2;
                        document.getElementById('address').value = data.address1;
                        document.getElementById('addressDetail').focus();
                        // iframe을 넣은 element를 안보이게 한다.
                        closeDaumPostCode();
                    } else {
                        // iframe을 넣은 element를 안보이게 한다.
                        closeDaumPostCode();
                        confirm('주소를 다시 검색해서 도로명 주소를 사용해 주시기 바랍니다.');
                        document.getElementById('zipCode').value = '';
                        document.getElementById('zipCode').setAttribute('value', '');
                        document.getElementById('address').value = '';
                        document.getElementById('address').setAttribute('value', '');
                        $('#searchAddress').trigger('click');
                    }


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
            $( "#street-name-notice" ).dialog({
                modal: true,
                width: 380,
                buttons: [{
                        text: "확인",
                        click: function() {
                            $(this).dialog("close");
                            showDaumPostcode();
                        }
                }]
            });
        });

        <%-- 다음 주소 검색 끝 --%>

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
                                    'label': item[labelKey]}
                                )
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
                    labelKey: 'campName',
                    clean: ['collCode', 'ariInstCode', 'deptCode', 'corsTypeCode', 'detlMajCode'],
                    url: '/campus'
                }
        );

        <%-- 캠퍼스 변경 --%>
        attachChangeEvent( 'campCode',
                {
                    targetId: 'collCode',
                    valueKey: 'collCode',
                    labelKey: 'collName',
                    // clean: ['ariInstCode', 'deptCode', 'corsTypeCode', 'detlMajCode'],
                    url: function(arg) {
                        return '/college/' + arg;
                    }
                }
        );

        <%-- 대학변경 --%>
        attachChangeEvent( 'collCode',
                {
                    targetId: 'deptCode',
                    valueKey: 'deptCode',
                    labelKey: 'deptName',
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
                    labelKey: 'deptName',
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
                    labelKey: 'codeVal',
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
                        }
                    }
                }
        );

        <%-- 지원과정 변경 --%>
        attachChangeEvent( 'corsTypeCode',
                {
                    targetId: 'detlMajCode',
                    valueKey: 'detlMajCode',
                    labelKey: 'detlMajName',
                    url: function(arg) {
                        var admsNo = $('#admsNo').val();
                        var applAttrCode = $('#applAttrCode').val();
                        if (applAttrCode == '00001') {
                            return '/general/detailMajor/' + admsNo + '/' + $('#deptCode').val() + '/' + arg;
                        } else if (applAttrCode == '00002') {
                            return '/ariInst/detailMajor/' + admsNo + "/" + $('#deptCode').val() + "/" + $('#ariInstCode').val() + '/' + arg;
                        } else if (applAttrCode == '00003') {
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

            $(parent).find('#detlMajRadio').remove();
            $(parent).find('#detlMajText').remove();
            $(parent).find('#detlMajDescDiv').remove();
            $(parent).find('#detlMajDescLabel').remove();
            if (detlMajCode == '99999') {   // 세부전공 직접입력
                $divNode = $('<div></div>').addClass('col-sm-offset-2 col-sm-9').attr({
                    'id': 'detlMajText'
                });
                $childNode = $('<input/>').addClass('form-control').attr({
                    'type': 'text',
                    'id': 'detlMajDesc',
                    'name': 'detlMajDesc'
                });
                $('#DetlMajDesc').text('');
                $childNode.appendTo($divNode);
                $divNode.appendTo($(parent));
            }
            if ($(selected).attr('partTimeYn') === 'Y' || $(selected).attr('partTimeYn') === 'y') { // 세부전공 PART_TIME_YN이 Y인 경우
                $divNode = $('<div></div>').addClass('col-sm-offset-2 col-sm-9').attr({
                    'id': 'detlMajRadio'
                });
                $childNode = $('<input/>').attr({
                    'type': 'checkbox',
                    'id': 'partTimeYn',
                    'name': 'partTimeYn'
                });
                $childNode2 = $('<label/>').addClass('checkbox-inline').text('파트타임 여부');
                $childNode.prependTo($childNode2);
                $childNode2.appendTo($divNode);
                $divNode.appendTo($(parent));
            }

            var temp = jQuery.type($(selected).attr('detlmajdesc'));
            var temp2 = $(selected).attr('detlmajdesc');
            if (jQuery.type($(selected).attr('detlmajdesc')) !=='undefined') { // 세부전공 desc 가 들어가 있는경우
                $divNode = $('<div></div>').addClass('col-sm-offset-2 col-sm-9').attr({
                    'id': 'detlMajDescDiv'
                });
                $childNode = $('<label/>').addClass('apexMsg').text(temp2).autoLink();
                $childNode.appendTo($divNode);
                $divNode.appendTo($(parent));
            }

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
