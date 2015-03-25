<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang="ko">
<head>
    <title><spring:message code="L02101"/><%--원서 작성 - 학력 정보--%></title>
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
        .grda-not {
            color: #f00;
            font-size: 16px;
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
        .grad-notice {
            margin-top: 5px;
            margin-bottom: 5px;
            padding-bottom: 5px;
            color: blue;
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
                    <p class="txt1">기본 정보</p>
                </li>
                <li class="inactive"><span class="step"><strong>2</strong></span>
                    <p class="txt1">학력 정보</p>
                </li>
                <li class="inactive"><span class="step"><strong>3</strong></span>
                    <p class="txt1">어학/경력 정보</p>
                </li>
                <li class="inactive"><span class="step"><strong>4</strong></span>
                    <p class="txt1">파일 첨부</p>
                </li>
            </ul>
        </div>
        <!-- /진행상태바 끝 -->
        <!-- 데스크탑 탭메뉴 시작 -->
        <div id="pc_tab" class="nav_wrap clearfix tab-container">
            <ul id="navTabUL" class="nav nav-tabs nav-justified">
                <li id="tab-basis" class="inactive inactiveTab" data-target-tab="basis" data-tab-available="true"><a><spring:message code="L01002"/><%--1. 기본 정보--%></a></li>
                <li id="tab-academy" class="inactive inactiveTab" data-target-tab="academy" data-tab-available="false" data-unavailable-msg='<spring:message code="U321"/>'><a><spring:message code="L01003"/><%--2. 학력 정보--%></a></li>
                <li id="tab-langCareer" class="inactive inactiveTab" data-target-tab="langCareer" data-tab-available="false" data-unavailable-msg='<spring:message code="U322"/>'><a><spring:message code="L01004"/><%--3. 어학/경력 정보--%></a></li>
                <li id="tab-document" class="inactive inactiveTab" data-target-tab="document" data-tab-available="false" data-unavailable-msg='<spring:message code="U323"/>'><a><spring:message code="L01005"/><%--4. 파일 첨부--%></a></li>
            </ul>
        </div>
        <!-- /데스크탑 탭메뉴 끝 -->
        <form:form commandName="academy" cssClass="form-horizontal" method="post" role="form">
            <form:hidden path="application.applNo" id="applNo" />
            <form:hidden path="application.applStsCode" id="applStsCode" />
            <form:hidden path="application.admsNo" id="admsNo" />
            <form:hidden path="application.entrYear" id="entrYear" />
            <form:hidden path="application.admsTypeCode" id="admsTypeCode" />
            <form:hidden path="application.corsTypeCode" id="corsTypeCode" />
        <c:set var="corsTypeCode" value="${academy.application.corsTypeCode}"/>
            <div id="myTabContent" class="tab-content">
                <div class="spacer-tiny"></div>
                <div class="row">
                    <div class="col-sm-offset-1 col-sm-10">
                        <%--<div>--%>
                            <%--<div class="validation-error"><form:errors path="*"/></div>--%>
                        <%--</div>--%>
                        <div class="panel panel-darkgray0">
                            <div class="panel-heading"><spring:message code="L02102"/><%--대학교--%></div>
                            <div class="panel-body">
                                <div class="form-group-block-list">
                                    <c:forEach begin="0" end="${academy.collegeList.size() > 0 ? academy.collegeList.size() - 1 : 0}" varStatus="stat">
                                    <div class="form-group-block">
                                        <form:hidden path="collegeList[${stat.index}].acadTypeCode" value="00002" />
                                        <form:hidden path="collegeList[${stat.index}].acadSeq" />
                                        <form:hidden path="collegeList[${stat.index}].userCUDType" value='${academy.collegeList[stat.index].userCUDType == null ? "INSERT" : academy.collegeList[stat.index].userCUDType}'/>
                                        <div class="form-group required">
                                            <label class="col-sm-2 control-label"><spring:message code="L02103"/><%--소재 국가--%></label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-3">
                                                    <button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="collegeList${stat.index}.schlCntrCode" data-targetNode2='collegeList${stat.index}.korCntrName' data-category="country">
                                                        <span class="glyphicon glyphicon-search"></span> <spring:message code="L02104"/><%--검색--%>
                                                    </button>
                                                </div>
                                                <div class="col-sm-9">
                                                    <form:hidden path="collegeList[${stat.index}].schlCntrCode" />
                                                    <form:input path="collegeList[${stat.index}].korCntrName" class="form-control" readonly="true"/>
                                                </div>
                                        <spring:bind path="collegeList[${stat.index}].schlCntrCode">
                                            <c:if test="${status.error}">
                                                <div class="col-sm-12 validation-container">
                                                    <div class="validation-error">${status.errorMessage}</div>
                                                </div>
                                            </c:if>
                                        </spring:bind>
                                            </div>
                                        </div>
                                        <div class="form-group required">
                                            <label class="col-sm-2 control-label"><spring:message code="L02105"/><%--재학 기간--%></label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-6 start-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon"><spring:message code="L02106"/><%--시작일--%></span>
                                                        <form:input path="collegeList[${stat.index}].entrDay" cssClass="form-control checkDate" readonly="true"
                                                                    data-entrDate="collegeList${stat.index}.entrDay"
                                                                    data-exprDate="collegeList${stat.index}.grdaDay"/>
                                                        <span class="input-group-addon calendar-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                            <spring:bind path="collegeList[${stat.index}].entrDay">
                                                <c:if test="${status.error}">
                                                    <div class="validation-error validation-container">
                                                            ${status.errorMessage}
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                                </div>
                                                <div class="col-sm-6 end-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon"><spring:message code="L02107"/><%--종료일--%></span>
                                                        <form:input path="collegeList[${stat.index}].grdaDay" cssClass="form-control checkDate" readonly="true"
                                                                    data-entrDate="collegeList${stat.index}.entrDay"
                                                                    data-exprDate="collegeList${stat.index}.grdaDay"/>
                                                        <span class="input-group-addon calendar-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                            <spring:bind path="collegeList[${stat.index}].grdaDay">
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
                                            <label class="col-sm-2 control-label"><spring:message code="L02108"/><%--졸업 구분--%></label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-4">
                                                    <div>
                                                        <%--<label class="radio-inline degr-radio"><form:radiobutton path="collegeList[${stat.index}].grdaTypeCode" cssClass="grad-yn" value="00001" /><spring:message code="L02109"/>&lt;%&ndash;졸업&ndash;%&gt;</label>--%>
                                                        <%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
                                                        <%--<label class="radio-inline degr-radio"><form:radiobutton path="collegeList[${stat.index}].grdaTypeCode" cssClass="grad-yn" value="00002" /><spring:message code="L02110"/>&lt;%&ndash;졸업 예정&ndash;%&gt;</label>--%>
                                                        <form:select path="collegeList[${stat.index}].grdaTypeCode" cssClass="form-control grad-type">
                                                            <form:option value="" label="--${msg.getMessage('L01006', locale)}--" />
                                                            <form:options items="${selection.grdaTypeList}" itemValue="code"
                                                                          itemLabel="${pageContext.response.locale == 'en' ? 'codeValXxen' : 'codeVal'}"/>
                                                        </form:select>
                                                    </div>
                                            <spring:bind path="collegeList[${stat.index}].grdaTypeCode">
                                                <c:if test="${status.error}">
                                                    <div class="col-sm-12 nopadding validation-container">
                                                        <div class="validation-error">${status.errorMessage}</div>
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                                </div>
                                                <div class="col-sm-8">
                                                    <div>
                                                        <form:input path="collegeList[${stat.index}].degrNo" cssClass="degr-no form-control erase-hide" placeholder="학위등록번호를 입력해주세요"
                                                                style="display: ${academy.collegeList[stat.index].grdaTypeCode == '00001' ? 'block;' : 'none;'}"/>
                                                        <label id='collegeList${stat.index}.label-grad-02' class="col-sm-10 grda-not degr-message erase-hide word-keep-all"
                                                               style="display: ${academy.collegeList[stat.index].grdaTypeCode == '00002' ? 'block;' : 'none;'}" >합격 후 입학 시 졸업증명서를 대학원 사무실로 반드시 제출하세요</label>
                                                        <label id='collegeList${stat.index}.label-grad-03' class="col-sm-10 grda-not degr-message erase-hide"
                                                               style="display: ${academy.collegeList[stat.index].grdaTypeCode == '00003' ? 'block;' : 'none;'}" ></label>
                                                        <label id='collegeList${stat.index}.label-grad-04' class="col-sm-10 grda-not degr-message erase-hide"
                                                               style="display: ${academy.collegeList[stat.index].grdaTypeCode == '00004' ? 'block;' : 'none;'}" ></label>
                                                    </div>
                                            <spring:bind path="collegeList[${stat.index}].degrNo">
                                                <c:if test="${status.error}">
                                                    <div class="col-sm-12 nopadding validation-container">
                                                        <div class="validation-error">${status.errorMessage}</div>
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="form-group required">
                                            <form:label path="collegeList[${stat.index}].schlName" cssClass="col-sm-2 control-label"><spring:message code="L02112"/><%--학교 이름--%></form:label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-3">
                                                    <button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="collegeList${stat.index}.schlCode" data-targetNode2='collegeList${stat.index}.schlName' data-category="school-u">
                                                        <span class="glyphicon glyphicon-search"></span> <spring:message code="L02113"/><%--검색--%>
                                                    </button>
                                                </div>
                                                <div class="col-sm-6">
                                                    <form:hidden path="collegeList[${stat.index}].schlCode" />
                                                    <form:input path="collegeList[${stat.index}].schlName" cssClass="form-control" readonly="true"/>
                                                </div>
                                                <div class="col-sm-3">
                                                    <label class="radio-inline">
                                                            <%--<form:radiobutton path="collegeList[${stat.index}].lastSchlYn" cssClass="radio-group" value="${academy.collegeList[stat.index].lastSchlYn}"/>&nbsp;&nbsp;최종 학교--%>
                                                        <input type="radio" class="college-radio" id="college-radio-${stat.index}" name="collegeRadio" data-last-radio-id="collegeList${stat.index}.lastSchlYn" ${academy.collegeList[stat.index].lastSchlYn == 'Y' ? 'checked' : ''} />&nbsp;&nbsp;<spring:message code="L02114"/><%--학부 최종 학교--%>
                                                        <form:hidden path="collegeList[${stat.index}].lastSchlYn"/>
                                                    </label>
                                                </div>
                                        <spring:bind path="collegeList[${stat.index}].schlName">
                                            <c:if test="${status.error}">
                                                <div class="col-sm-12 validation-container">
                                                    <div class="validation-error">${status.errorMessage}</div>
                                                </div>
                                            </c:if>
                                        </spring:bind>
                                            </div>
                                        </div>
                                        <div class="form-group required">
                                            <form:label path="collegeList[${stat.index}].collName" cssClass="col-sm-2 control-label"><spring:message code="L02115"/><%--단과 대학--%></form:label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-12">
                                                    <form:input path="collegeList[${stat.index}].collName" cssClass="form-control" />
                                                </div>
                                        <spring:bind path="collegeList[${stat.index}].collName">
                                            <c:if test="${status.error}">
                                                <div class="col-sm-12 validation-container">
                                                    <div class="validation-error">${status.errorMessage}</div>
                                                </div>
                                            </c:if>
                                        </spring:bind>
                                            </div>
                                        </div>
                                        <div class="form-group required">
                                            <form:label path="collegeList[${stat.index}].majName" cssClass="col-sm-2 control-label"><spring:message code="L02116"/><%--학과 이름--%></form:label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-12">
                                                    <form:input path="collegeList[${stat.index}].majName" cssClass="form-control" placeholder="다수 전공은 컴마로 구분하여 모두 입력해 주세요." />
                                                </div>
                                        <spring:bind path="collegeList[${stat.index}].majName">
                                            <c:if test="${status.error}">
                                                <div class="col-sm-12 validation-container">
                                                    <div class="validation-error">${status.errorMessage}</div>
                                                </div>
                                            </c:if>
                                        </spring:bind>
                                            </div>
                                        </div>
                                        <div class="form-group required">
                                            <label class="col-sm-2 control-label"><spring:message code="L02117"/><%--평량 평균--%></label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-6">
                                                    <div class="input-group">
                                                        <span class="input-group-addon"><spring:message code="L02118"/><%--평점--%></span>
                                                        <form:input path="collegeList[${stat.index}].gradAvr" cssClass="form-control gradAvr" maxlength="4" placeholder="#.##"/>
                                                    </div>
                                            <spring:bind path="collegeList[${stat.index}].gradAvr">
                                                <c:if test="${status.error}">
                                                    <div class="validation-error validation-container">
                                                            ${status.errorMessage}
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                                </div>
                                                <div class="col-sm-6">
                                                    <div class="input-group">
                                                        <span class="input-group-addon"><spring:message code="L02119"/><%--만점--%></span>
                                                        <form:input path="collegeList[${stat.index}].gradFull" cssClass="form-control gradFull" maxlength="4" placeholder="#.#" data-gradAvr-id="collegeList${stat.index}.gradAvr"/>
                                                    </div>
                                            <spring:bind path="collegeList[${stat.index}].gradFull">
                                                <c:if test="${status.error}">
                                                    <div class="validation-error validation-container">
                                                            ${status.errorMessage}
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="btn btn-remove" data-list-name="collegeList" data-block-index="${stat.index}"><button type="button" class="close" aria-hidden="true">×</button></div>
                                    </div>
                                    </c:forEach>
                                    <div class="btn btn-info btn-add"><spring:message code="L02120"/><%--추가--%></div>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-darkgray0">
                            <div class="panel-heading"><spring:message code="L02201"/><%--대학원--%></div>
                            <div class="col-sm-12 grad-notice"><label><spring:message code="U02201"/></label></div>    <%--박사 과정에 지원한 경우에는 대학원 최종 학교가 사정 기준이 됩니다.--%>
                            <div class="panel-body">
                                <div class="form-group-block-list">
                                    <c:forEach begin="0" end="${academy.graduateList.size() > 0 ? academy.graduateList.size() - 1 : 0}" varStatus="stat">
                                    <div class="form-group-block">
                                        <form:hidden path="graduateList[${stat.index}].acadTypeCode" value="00003" />
                                        <form:hidden path="graduateList[${stat.index}].acadSeq" />
                                        <form:hidden path="graduateList[${stat.index}].userCUDType" value='${academy.graduateList[stat.index].userCUDType == null ? "INSERT" : academy.graduateList[stat.index].userCUDType}'/>
                                        <div class="form-group <c:if test="${corsTypeCode.equals('2') || corsTypeCode.equals('6') || corsTypeCode.equals('8')}">required</c:if>">
                                            <label class="col-sm-2 control-label"><spring:message code="L02103"/><%--소재 국가--%></label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-3">
                                                    <button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="graduateList${stat.index}.schlCntrCode" data-targetNode2='graduateList${stat.index}.korCntrName' data-category="country">
                                                        <span class="glyphicon glyphicon-search"></span> <spring:message code="L02104"/><%--검색--%>
                                                    </button>
                                                </div>
                                                <div class="col-sm-9">
                                                    <form:hidden path="graduateList[${stat.index}].schlCntrCode" />
                                                    <form:input path="graduateList[${stat.index}].korCntrName" class="form-control" readonly="true"/>
                                                </div>
                                        <spring:bind path="graduateList[${stat.index}].schlCntrCode">
                                            <c:if test="${status.error}">
                                                <div class="col-sm-12 validation-container">
                                                    <div class="validation-error">${status.errorMessage}</div>
                                                </div>
                                            </c:if>
                                        </spring:bind>
                                            </div>
                                        </div>
                                        <div class="form-group <c:if test="${corsTypeCode.equals('2') || corsTypeCode.equals('6') || corsTypeCode.equals('8')}">required</c:if>">
                                            <label class="col-sm-2 control-label"><spring:message code="L02105"/><%--재학 기간--%></label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-6 start-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon"><spring:message code="L02106"/><%--시작일--%></span>
                                                        <form:input path="graduateList[${stat.index}].entrDay" cssClass="form-control checkDate" readonly="true"
                                                                data-entrDate="graduateList${stat.index}.entrDay"
                                                                data-exprDate="graduateList${stat.index}.grdaDay"/>
                                                        <span class="input-group-addon calendar-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                            <spring:bind path="graduateList[${stat.index}].entrDay">
                                                <c:if test="${status.error}">
                                                    <div class="validation-error validation-container">
                                                            ${status.errorMessage}
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                                </div>
                                                <div class="col-sm-6 end-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon"><spring:message code="L02107"/><%--종료일--%></span>
                                                        <form:input path="graduateList[${stat.index}].grdaDay" cssClass="form-control checkDate" readonly="true"
                                                                data-entrDate="graduateList${stat.index}.entrDay"
                                                                data-exprDate="graduateList${stat.index}.grdaDay"/>
                                                        <span class="input-group-addon calendar-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                            <spring:bind path="graduateList[${stat.index}].grdaDay">
                                                <c:if test="${status.error}">
                                                    <div class="validation-error validation-container">
                                                            ${status.errorMessage}
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group <c:if test="${corsTypeCode.equals('2') || corsTypeCode.equals('6') || corsTypeCode.equals('8')}">required</c:if>">
                                            <label class="col-sm-2 control-label"><spring:message code="L02108"/><%--졸업 구분--%></label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-4">
                                                    <%--<label class="radio-inline degr-radio"><form:radiobutton path="graduateList[${stat.index}].grdaTypeCode" cssClass="grad-yn" value="00001" /><spring:message code="L02109"/>&lt;%&ndash;졸업&ndash;%&gt;</label>--%>
                                                    <%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
                                                    <%--<label class="radio-inline degr-radio"><form:radiobutton path="graduateList[${stat.index}].grdaTypeCode" cssClass="grad-yn" value="00002" /><spring:message code="L02110"/>&lt;%&ndash;졸업 예정&ndash;%&gt;</label>--%>
                                                    <form:select path="graduateList[${stat.index}].grdaTypeCode" cssClass="form-control grad-type">
                                                        <form:option value="" label="--${msg.getMessage('L01006', locale)}--" />
                                                        <form:options items="${selection.grdaTypeList}" itemValue="code"
                                                                      itemLabel="${pageContext.response.locale == 'en' ? 'codeValXxen' : 'codeVal'}"/>
                                                    </form:select>
                                                </div>
                                                <div class="col-sm-8">
                                                    <form:input path="graduateList[${stat.index}].degrNo" cssClass="degr-no form-control erase-hide" placeholder="${msg.getMessage('U02104')}"
                                                            style="display: ${academy.graduateList[stat.index].grdaTypeCode == '00001' ? 'block;' : 'none;'}"/> <%--학위등록번호를 입력해주세요--%>
                                                    <label id='graduateList${stat.index}.label-grad-02' class="col-sm-10 grda-not degr-message erase-hide"
                                                           style="display: ${academy.graduateList[stat.index].grdaTypeCode == '00002' ? 'block;' : 'none;'}" ><spring:message code="U02105"/></label>    <%--합격 후 입학 시 졸업증명서를 대학원 사무실로 반드시 제출하세요--%>
                                                    <label id='graduateList${stat.index}.label-grad-03' class="col-sm-10 grda-not degr-message erase-hide"
                                                           style="display: ${academy.graduateList[stat.index].grdaTypeCode == '00003' ? 'block;' : 'none;'}" ></label>
                                                    <label id='graduateList${stat.index}.label-grad-04' class="col-sm-10 grda-not degr-message erase-hide"
                                                           style="display: ${academy.graduateList[stat.index].grdaTypeCode == '00004' ? 'block;' : 'none;'}" ></label>
                                                </div>
                                        <spring:bind path="graduateList[${stat.index}].grdaTypeCode">
                                            <c:if test="${status.error}">
                                                <div class="col-sm-12 validation-container">
                                                    <div class="validation-error">${status.errorMessage}</div>
                                                </div>
                                            </c:if>
                                        </spring:bind>
                                            </div>
                                        </div>
                                        <div class="form-group <c:if test="${corsTypeCode.equals('2') || corsTypeCode.equals('6') || corsTypeCode.equals('8')}">required</c:if>">
                                            <form:label path="graduateList[${stat.index}].schlName" cssClass="col-sm-2 control-label"><spring:message code="L02112"/><%--학교 이름--%></form:label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-3">
                                                    <button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="graduateList${stat.index}.schlCode" data-targetNode2='graduateList${stat.index}.schlName' data-category="school-u">
                                                        <span class="glyphicon glyphicon-search"></span> <spring:message code="L02113"/><%--검색--%>
                                                    </button>
                                                </div>
                                                <div class="col-sm-6">
                                                    <form:hidden path="graduateList[${stat.index}].schlCode" />
                                                    <form:input path="graduateList[${stat.index}].schlName" cssClass="form-control" readonly="true"/>
                                                </div>
                                                <div class="col-sm-3">
                                                    <label class="radio-inline">
                                                            <%--<form:radiobutton path="graduateList[${stat.index}].lastSchlYn" cssClass="radio-group" value="${academy.graduateList[stat.index].lastSchlYn}"/>&nbsp;&nbsp;최종 학교--%>
                                                        <input type="radio" class="graduate-radio" id="graduate-radio-${stat.index}" name="graduateRadio" data-last-radio-id="graduateList${stat.index}.lastSchlYn" ${academy.graduateList[stat.index].lastSchlYn == 'Y' ? 'checked' : ''} />&nbsp;&nbsp;<spring:message code="L02202"/><%--대학원 최종 학교--%>
                                                        <form:hidden path="graduateList[${stat.index}].lastSchlYn"/>
                                                    </label>
                                                </div>
                                        <spring:bind path="graduateList[${stat.index}].schlName">
                                            <c:if test="${status.error}">
                                                <div class="col-sm-12 validation-container">
                                                    <div class="validation-error">${status.errorMessage}</div>
                                                </div>
                                            </c:if>
                                        </spring:bind>
                                            </div>
                                        </div>
                                        <div class="form-group <c:if test="${corsTypeCode.equals('2') || corsTypeCode.equals('6') || corsTypeCode.equals('8')}">required</c:if>">
                                            <form:label path="graduateList[${stat.index}].collName" cssClass="col-sm-2 control-label"><spring:message code="L02115"/><%--단과 대학--%></form:label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-12">
                                                    <form:input path="graduateList[${stat.index}].collName" cssClass="form-control" />
                                                </div>
                                        <spring:bind path="graduateList[${stat.index}].collName">
                                            <c:if test="${status.error}">
                                                <div class="col-sm-12 validation-container">
                                                    <div class="validation-error">${status.errorMessage}</div>
                                                </div>
                                            </c:if>
                                        </spring:bind>
                                            </div>
                                        </div>
                                        <div class="form-group <c:if test="${corsTypeCode.equals('2') || corsTypeCode.equals('6') || corsTypeCode.equals('8')}">required</c:if>">
                                            <form:label path="graduateList[${stat.index}].majName" cssClass="col-sm-2 control-label"><spring:message code="L02116"/><%--학과 이름--%></form:label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-12">
                                                    <form:input path="graduateList[${stat.index}].majName" cssClass="form-control" placeholder="${msg.getMessage('U02108')}" />   <%--다수 전공은 컴마로 구분하여 모두 입력해 주세요.--%>
                                                </div>
                                        <spring:bind path="graduateList[${stat.index}].majName">
                                            <c:if test="${status.error}">
                                                <div class="col-sm-12 validation-container">
                                                    <div class="validation-error">${status.errorMessage}</div>
                                                </div>
                                            </c:if>
                                        </spring:bind>
                                            </div>
                                        </div>
                                        <div class="form-group <c:if test="${corsTypeCode.equals('2') || corsTypeCode.equals('6') || corsTypeCode.equals('8')}">required</c:if>">
                                            <label class="col-sm-2 control-label"><spring:message code="L02117"/><%--평량 평균--%></label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-6">
                                                    <div class="input-group">
                                                        <span class="input-group-addon"><spring:message code="L02118"/><%--평점--%></span>
                                                        <form:input path="graduateList[${stat.index}].gradAvr" cssClass="form-control gradAvr" maxlength="4" placeholder="${msg.getMessage('U02109')}"/>    <%--#.##--%>
                                                    </div>
                                            <spring:bind path="graduateList[${stat.index}].gradAvr">
                                                <c:if test="${status.error}">
                                                    <div class="validation-error validation-container">
                                                            ${status.errorMessage}
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                                </div>
                                                <div class="col-sm-6">
                                                    <div class="input-group">
                                                        <span class="input-group-addon"><spring:message code="L02119"/><%--만점--%></span>
                                                        <form:input path="graduateList[${stat.index}].gradFull" cssClass="form-control gradFull" maxlength="4" placeholder="#.#" data-gradAvr-id="graduateList${stat.index}.gradAvr"/>
                                                    </div>
                                            <spring:bind path="graduateList[${stat.index}].gradFull">
                                                <c:if test="${status.error}">
                                                    <div class="validation-error validation-container">
                                                            ${status.errorMessage}
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="btn btn-remove" data-list-name="graduateList" data-block-index="${stat.index}"><button type="button" class="close" aria-hidden="true">×</button></div>
                                    </div>
                                    </c:forEach>
                                    <div class="btn btn-info btn-add"><spring:message code="L02120"/><%--추가--%></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="btn-group btn-group-justified">
                    <div class="btn-group">
                        <button id="saveAcademy" type="button" class="btn btn-primary btn-lg btn-save"><spring:message code="L02301"/><%--학력 저장--%></button>
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
                <%--<button id="bpopBtnSearch" class="btn btn-info col-sm-2">검색</button>--%>
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

    <%-- 입력값 valid flag --%>
    <input type='hidden' id="validFlag" />

</section>
<content tag="local-script">
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery-ui.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
        var applNo = document.getElementById('applNo').value = '${academy.application.applNo}',
            admsNo = document.getElementById('admsNo').value = '${academy.application.admsNo}',
            applStsCode = document.getElementById('applStsCode').value = '${academy.application.applStsCode}',
            entrYear = document.getElementById('entrYear').value = '${academy.application.entrYear}',
            admsTypeCode = document.getElementById('admsTypeCode').value = '${academy.application.admsTypeCode}',
            validFlag = document.getElementById('validFlag');
        validFlag.value = true;

        <%-- 원서 작성 현황 처리 --%>
        var processCurrentStep = function (applStsCode) {
            var code = Number(applStsCode),
                stepBox = document.getElementById('step_box'),
                l = stepBox.children.length, i,
                navTabUL = document.getElementById('navTabUL');
            for ( i = 0 ; i < code && i < l ; i++ ) {
                stepBox.children[i].className = 'active';
                navTabUL.children[i].setAttribute('data-tab-available', 'true');
                if (navTabUL.children[i+1])
                    navTabUL.children[i+1].setAttribute('data-tab-available', 'true');
            }
        };
        processCurrentStep(document.getElementById('applStsCode').value);
        <%-- 원서 작성 현황 처리 --%>

        <%-- active 탭 표시 --%>
        var setActiveTab = function () {
            var urlStr = document.location.pathname,
                substrToFirstSlash = urlStr.substring(0, urlStr.lastIndexOf("/")),
                targetTabLI = document.getElementById('tab-' + substrToFirstSlash.substring(substrToFirstSlash.lastIndexOf("/") + 1));

            targetTabLI.className = 'active activeTab';
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
        var formProcess = function(e) {
            e.preventDefault();
            var isCollegeLastSchlChecked = false,
                isGraduateLastSchlChecked = true;
            $('.college-radio').each( function () {
                if (this.value == 'on' && this.checked == true)
                    isCollegeLastSchlChecked = true;
            });

            <c:if test="${corsTypeCode.equals('2') || corsTypeCode.equals('6') || corsTypeCode.equals('8')}">
            isGraduateLastSchlChecked = false;
            $('.graduate-radio').each( function () {
                if (this.value == 'on' && this.checked == true)
                    isGraduateLastSchlChecked = true;
            });
            </c:if>

            if (isCollegeLastSchlChecked && isGraduateLastSchlChecked) {
                var form = document.forms[0];
                form.action = "${contextPath}/application/academy/save";
                form.submit();
            } else {
                alert('최종학교를 선택해 주세요.');
            }
        };
        $('.btn-save').on('click', formProcess);
        <%-- 하단 버튼 처리 --%>

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
            var bpopSearchText = document.getElementById('bpop');
            bpopSearchText.value="";


            var dataCategory = this.getAttribute('data-category');
            var targetNode = null, title = null, columnHead = [];
            for (var i = 1; i < 4; i++) {
                targetNode = this.getAttribute('data-targetNode' + i);
                document.getElementById('targetNode' + i).value = targetNode ? targetNode : null;
            }
            if (dataCategory.indexOf('country') > -1) {
                title = '<spring:message code="L02121"/>';  /*국가 검색*/
                columnHead = ['', '<spring:message code="L02122"/>', '<spring:message code="L02123"/>'];   /*한글 이름, 영문 이름*/
                document.getElementById('bpopContent').setAttribute('data-category', dataCategory);
            } else if (dataCategory.indexOf('school') > -1) {
                columnHead = ['', '<spring:message code="L02127"/>'];    /*학교 이름*/
                document.getElementById('bpopContent').setAttribute('data-category', dataCategory);
                var suffix = dataCategory.split('-')[1];
                if (suffix == 'h') {
                    title = '<spring:message code="L02124"/>';  /*고등학교 검색*/
                } else if (suffix == 'u') {
                    title = '<spring:message code="L02125"/>';  /*대학교 검색*/
                } else if (suffix == 'g') {
                    title = '<spring:message code="L02126"/>';  /*대학원 검색*/
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
//            document.getElementById('bpop').focus();

            $(bpopSearchText).focus().focus();
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

                    var container = JSON.parse(data),
                        obj = JSON.parse(container.data),
                        record, i, l;
//                    var obj = JSON.parse(data.data), record, i, l;

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
                                hideDialog('#modal_popup3');
                            });
                        }
                    } else {
                        if (category.isCountry) {
                            record = $('<tr>' + '<td><span style="display: none;" class="b-close">' + '999' + '</span></td>' + '<td colspan="2"><span class="b-close" style="cursor: pointer">' + '<spring:message code="U02101"/>' + '</span></td>' + '</tr>'); /*검색 결과가 없습니다. 다시 검색해 주세요*/
                        } else if (category.isSchool) {
                            record = $('<tr>' + '<td><span style="display: none;" class="b-close">' + '999' + '</span></td>' + '<td><span class="b-close" style="cursor: pointer">' + '<spring:message code="U02106"/>' + '</span></td>' + '</tr>'); /*검색 결과가 없습니다. 여기를 눌러 직접 입력해 주세요*/
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
                                    resultInputText.placeholder = '<spring:message code="U02102"/>';  /*다시 검색해주세요*/
                                if (category.isSchool) {
                                    resultInputText.placeholder = '<spring:message code="U02107"/>';  /*직접 입력해주세요*/
                                    resultInputText.readOnly = false;
                                    resultInputText.focus();
                                }
                            }
                            hideDialog('#modal_popup3');
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

        $('.b-close').on('click', function(e) {
            e.preventDefault();
            hideDialog('#modal_popup3');
        });
        <%-- 국가/학교 검색 끝 --%>

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
        <%-- 달력 옵션 --%>

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
            var entrDate = document.getElementById(this.getAttribute('data-entrDate')).value,
                exprDate = document.getElementById(this.getAttribute('data-exprDate')).value;
            if ( entrDate != '' && exprDate != '') {
                if (parseInt(entrDate) > parseInt(exprDate)) {
                    alert('<spring:message code="U02103"/>');    /*시작일은 종료일보다 앞선 날짜여야 합니다.*/
                    this.value = '';
                }
            }

        });
        <%-- 달력 선후 관계 체크 --%>


        <%-- 졸업/졸업 예정 처리 --%>
        $('.grad-type').on('change', function () {
//            var id = this.id,
//                prefix = id.substr(0, id.lastIndexOf('.')),
//                showObj, hideObj;
//            if (this.checked && this.value == "00001") {
//                showObj = document.getElementById(prefix + '.degrNo');
//                hideObj = document.getElementById(prefix + '.label-grad-not');
//            } else if (this.checked && this.value == "00002") {
//                hideObj = document.getElementById(prefix + '.degrNo');
//                showObj = document.getElementById(prefix + '.label-grad-not');
//            }
            var id = this.id,
                    prefix = id.substr(0, id.lastIndexOf('.')),
                    showObj, hideObj = [],
                    degrNoObj = document.getElementById(prefix + '.degrNo');
            if (this[this.selectedIndex].value == "00001") {
                showObj = degrNoObj;
                hideObj.push(document.getElementById(prefix + '.label-grad-02'));
                hideObj.push(document.getElementById(prefix + '.label-grad-03'));
                hideObj.push(document.getElementById(prefix + '.label-grad-04'));
                showObj.style.display = "block";
                degrNoObj.placeholder = "<spring:message code="U02104"/>";    /*학위등록번호를 입력해주세요*/
                $(degrNoObj).placeholder();
            } else if (this[this.selectedIndex].value == "00002") {
                hideObj.push(degrNoObj);
                hideObj.push(document.getElementById(prefix + '.label-grad-03'));
                hideObj.push(document.getElementById(prefix + '.label-grad-04'));
                showObj = document.getElementById(prefix + '.label-grad-02');
                showObj.style.display = "block";
            } else if (this[this.selectedIndex].value == "00003") {
                hideObj.push(degrNoObj);
                hideObj.push(document.getElementById(prefix + '.label-grad-02'));
                hideObj.push(document.getElementById(prefix + '.label-grad-04'));
                showObj = document.getElementById(prefix + '.label-grad-03');
                showObj.style.display = "block";
            } else if (this[this.selectedIndex].value == "00004") {
                hideObj.push(degrNoObj);
                hideObj.push(document.getElementById(prefix + '.label-grad-02'));
                hideObj.push(document.getElementById(prefix + '.label-grad-03'));
                showObj = document.getElementById(prefix + '.label-grad-04');
                showObj.style.display = "block";
            } else if (this[this.selectedIndex].value == "") {
                hideObj.push(degrNoObj);
                hideObj.push(document.getElementById(prefix + '.label-grad-02'));
                hideObj.push(document.getElementById(prefix + '.label-grad-03'));
                hideObj.push(document.getElementById(prefix + '.label-grad-04'));
            }

            for (var i = 0, hideObjL = hideObj.length ; i < hideObjL ; i++) {
                hideObj[i].value = '';
                hideObj[i].setAttribute('value', '');
                hideObj[i].style.display = 'none';
            }
        });
        <%-- 졸업/졸업 예정 처리 --%>

        <%-- 최종학교 처리 --%>
        var checkLastSchool = function (radioClass) {
            $(radioClass).each( function () {
                var lastSchoolRadioId = this.getAttribute('data-last-radio-id'),
                        lastYn = document.getElementById(lastSchoolRadioId);
                if (this.checked) {
                    lastYn.value = 'Y';
                    this.value = 'on';
                } else {
                    lastYn.value = 'N';
                    this.value = 'off';
                }
            });
        };
        $('.college-radio').on('click', function () {
            checkLastSchool('.college-radio');
        });
        $('.graduate-radio').on('click', function () {
            checkLastSchool('.graduate-radio');
        });
        <%-- 최종학교 처리 --%>

        <%-- 성적 입력 validation --%>
        $('.gradAvr').on('keyup', function () {
            var regexp = /^[0-9]\.?[0-9]*$/,
                val = this.value;
            if (!regexp.test(this.value)) {
                this.value = val.substr(0, val.length-1);
            }
        });
        $('.gradAvr').on('blur', function () {
            if (this.value.length > 0) {
                var regexp = /\d\.\d{2}/;
                if (!regexp.test(this.value) && this.value != '') {
                    validFlag.value = false;
                    alert('<spring:message code="U02110"/>');    /*소수점 둘째자리까지 작성해 주세요*/
                    this.focus();
                } else {
                    validFlag.value = true;
                }
            }
        });
        $('.gradFull').on('keyup', function () {
            var regexp = /^[0-9]\.?[0-9]*$/,
                    val = this.value;
            if (!regexp.test(this.value)) {
                this.value = val.substr(0, val.length-1);
            }
        });
        $('.gradFull').on('blur', function () {
            if (this.value.length > 0) {
                var regexp = /\d.\d{2}/,
                        gradAvgInput = document.getElementById(this.getAttribute('data-gradAvr-id'));
                if (!regexp.test(this.value) && this.value != '') {
                    validFlag.value = false;
                    alert('<spring:message code="U02110"/>');    /*소수점 둘째자리까지 작성해 주세요*/
                    this.focus();
                } else {
                    if (parseFloat(this.value) < parseFloat(gradAvgInput.value)) {
                        validFlag.value = false;
                        alert('<spring:message code="U02111"/>');   /*평점은 만점 이하여야 합니다*/
                        gradAvgInput.focus();
                    } else {
                        validFlag.value = true;
                    }
                }
            }
        });
        <%-- 성적 입력 validation --%>

        <%-- form-group-block 추가/삭제에 대한 처리 시작 --%>
        <%-- id, name 재설정 시작 --%>
        var updateIdAndName = function ( block, index ) {
            var i, name, prefix, suffix, input, items, label,
                    itemsl, label, attrs, attrsl, j, dataVId, element, datasetValue, oldid, gradAvrId;
            items = block.querySelectorAll('input, select, label');
            if (items) {
                itemsl = items.length;
                for (i = 0; i <itemsl; i++) {
                    element = items[i];
                    name = element.name;
                    attrs = element.attributes;
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
                        if (element.type != 'radio' || element.id.indexOf('grdaTypeCode') > 0) {
                            prefix = name.substring(0, name.indexOf('['));
                            suffix = name.substring(name.indexOf(']') + 1);
                            element.name = prefix + '[' + index + ']' + suffix;
                        }
                    }
                    oldid = element.id;
                    if (oldid) {
                        prefix = oldid.substring(0, oldid.indexOf('.'));
                        prefix = prefix.replace(/[0-9]/g, '');
                        suffix = oldid.substring(oldid.indexOf('.'));
                        element.id = prefix + index + suffix;

                        label = block.querySelector('label[for="' + oldid + '"]');
                        if (label) {
                            label.setAttribute('for', element.id);
                        }
                    }

                    gradAvrId = element.getAttribute('data-gradAvr-id');
                    if (gradAvrId) {
                        datasetValue = gradAvrId;
                        prefix = datasetValue.substring(0, datasetValue.indexOf('.'));
                        prefix = prefix.replace(/[0-9]/g, '');
                        suffix = datasetValue.substring(datasetValue.indexOf('.'));
//                        element.dataset.gradavrId = prefix + index + suffix;
                        element.setAttribute('data-gradAvr-id', prefix + index + suffix);
                    }
                    if (element.id.indexOf('grdaTypeCode') > 0) {
                        if (element.checked)
                            $(element).prop('checked', true);
                    }
                    if (element.id.indexOf('userCUDType') > 0) {
                        element.value = "INSERT";
                    }
                }
            }
            resetCalendar(block, '.input-group.date>input');

            // bpopper data-targetNode
            var bpopperBtns = block.querySelectorAll('.bpopper');
            if (bpopperBtns) {
                for (i = 0; i < bpopperBtns.length; i++) {
                    for (var j = 1; j < 4; j++) {
                        var t = bpopperBtns[i].getAttribute('data-targetNode' + j);
                        if (t) {
                            t = t.split('.');
                            t[0] = t[0].replace(/[0-9]/g, '');
                            bpopperBtns[i].setAttribute('data-targetNode' + j, t[0] + index + '.' + t[1]);
                        }
                    }
                }
            }

            var removeBtn = block.querySelector('.btn-remove');
            if (removeBtn) {
                removeBtn.setAttribute('data-block-index', index);
            }

            var searchBtn = block.querySelector('[data-targetNode1]');
            if (searchBtn) {
                var target1 = searchBtn.getAttribute('data-targetNode1');
                var target2 = searchBtn.getAttribute('data-targetNode2');
                suffix = target1.substring(target1.indexOf('.') + 1, target1.length);
                searchBtn.setAttribute('data-targetNode1', prefix + index + '.' + suffix);
                suffix = target2.substring(target2.indexOf('.') + 1, target2.length);
                searchBtn.setAttribute('data-targetNode2', prefix + index + '.' + suffix);
            }
        };
        <%-- id, name 재설정 끝 --%>

        <%-- 복제된 입력폼 내용 초기화 시작 --%>
        var resetBlockContents = function ( block ) {
            var i, items, itemsL, itemName, element;
            block.style.display = 'block';
            items = block.querySelectorAll('input, select');
            if (items) {
                itemsL = items.length;
                for (i = 0; i <itemsL ; i++) {
                    element = items[i];
                    if (element.type == 'hidden') {
                        itemName = element.name;
                        if (itemName.indexOf('userCUDType') > 0) {
                            element.value = "INSERT";
                        } else if (itemName.indexOf('acadType') < 0) {
                            element.setAttribute('value', '');
                            element.value = '';
                        }
                    }
                    if (element.type != 'hidden' && element.type != 'radio' && element.type != 'checkbox' && element.type != 'button') {
                        element.setAttribute('value', '');
                        element.value = '';
                        itemName = element.name;
                        if (itemName.indexOf('schlName') > 0) {
                            element.placeholder = '';
                            element.setAttribute('readonly', 'true');
                        }
                    }
//                    if (element.type == 'button') {
//                        $(element).removeClass('btn-info');
//                        $(element).addClass('btn-default');
//                        $(element).val('올리기');
//                    }
//                    if (element.type == 'file') {
//                        $(element).val('');
//                    }
                    if (element.type == 'radio' ) {
                        if (element.id.indexOf('radio') > 0) {
                            element.checked = false;
                            element.value = 'off';
                        }
//                        else if (element.id.indexOf('grdaTypeCode') > 0 && element.checked)
//                            element.checked = true;
//                        }
                    }


                }
            }
            items = block.querySelectorAll('label');
            if (items) {
                for (i = 0, itemsL = items.length ; i < itemsL; i++) {
                    if (items[i].className.indexOf('erase-hide') > 0) {
if (console) {
    console.log(items[i].id);
    console.log(items[i].value);
    console.log(items[i].style.display);
}
                        items[i].value = 0;
                        items[i].setAttribute('value', '');
                        items[i].style.display = 'none';
                    }
                }
            }

            resetCalendar(block, '.input-group.date>input');

//            var validationContainers = block.querySelectorAll('.validation-container');
//            Array.prototype.forEach.call(validationContainers, function (validationContainer) {
//                validationContainer.parentNode.removeChild(validationContainer);
//            });
            var validationContainers = block.querySelectorAll('.validation-container'),
                validationContainersL = validationContainers.length;
            while(validationContainersL-- > 0) {
                validationContainers[validationContainersL].parentNode.removeChild(validationContainers[validationContainersL]);
            }
        };
        <%-- 복제된 입력폼 내용 초기화 끝 --%>

        <%-- 추가 버튼 --%>
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
        <%-- 추가 버튼 --%>

        <%-- 삭제 버튼 --%>
        $('.btn-remove').on('click', function(e) {
            if (confirm('<spring:message code="U02112"/>\n\n<spring:message code="U02113"/>')) {    /*학력 정보를 삭제하시면 관련 첨부 파일도 함께 삭제 됩니다.\n\n계속 하시겠습니까?*/
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
                var userCUDType = document.getElementById(listName + blockIndex + '.userCUDType');

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
            }
        });
        <%-- 삭제 버튼 --%>
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