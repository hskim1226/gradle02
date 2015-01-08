<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<%--// TODO 제3자 동의여부 : ${providePrivateInfo} - 0 : 동의, 1 : 비동의--%>
<html>
<head>
    <title></title>
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

        /*a[disabled] {*/
            /*pointer-events: none;*/
        /*}*/
    </style>
    <%--body의 글자 속성을 #333333으로 강제 지정하여 Footer 글자가 안나옴, 꼭 필요하지 않으면 안쓰기로
    <link rel="stylesheet" href="${contextPath}/css/bootstrap-glyphicons.css" />--%>
</head>
<body>
<section class="application">
    <div id="alert-container"></div>
    <div class="container">
        <ul id="myTab" class="nav nav-tabs nav-justified tab-gray">
            <li><a href="#appinfo" data-toggle="tab">기본 정보</a></li>
            <li><a id="tabAcademy" href="#academy" data-toggle="tab">학력</a></li>
            <li><a id="tabLangCareer" href="#langcareer" data-toggle="tab">어학 및 경력</a></li>
            <li><a id="tabFileUpload" href="#fileupload" data-toggle="tab">첨부파일</a></li>
        </ul>
        <form:form commandName="entireApplication" cssClass="form-horizontal" method="post" role="form">
            <form:hidden path="application.applNo" id="applNo" />
            <form:hidden path="application.applStsCode" id="applStsCode" />
            <form:hidden path="application.admsNo" id="admsNo" />
            <form:hidden path="application.entrYear" id="entrYear" />
            <form:hidden path="application.admsTypeCode" id="admsTypeCode" />
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade" id="appinfo">
                    <div class="spacer-tiny"></div>
                    <div class="row">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="panel panel-default">
                                <div class="panel-heading">지원 사항</div>
                                <div class="panel-body">
                                    <div class="form-group required">
                                        <label for="applAttrCode" class="col-sm-2 control-label">지원 구분</label>
                                        <div class="col-sm-9">
                                            <form:select path="application.applAttrCode" id="applAttrCode" cssClass="form-control base-info">
                                                <form:options items="${common.applAttrList}" itemValue="code" itemLabel="codeVal"/>
                                            </form:select>
                                        </div>
                                    </div>
                                    <div id="applyKindDynamic">
                                        <div class="form-group hidden-apply-kind-2 required">
                                            <label path="campCode" class="col-sm-2 control-label">캠퍼스</label>
                                            <div class="col-sm-3">
                                                <form:select path="application.campCode" id="campCode" cssClass="form-control base-info">
                                                    <form:option value="" label="--선택--" />
                                                    <c:if test="${entireApplication.application.applAttrCode == '00001' || entireApplication.application.applAttrCode == '00003'}">
                                                    <form:options items="${common.campList}" itemValue="campCode" itemLabel="campName" />
                                                    </c:if>
                                                </form:select>
                                            </div>
                                            <label path="collCode" class="col-sm-2 control-label">대학</label>
                                            <div class="col-sm-4">
                                                <form:select path="application.collCode" id="collCode" cssClass="form-control base-info">
                                                    <form:option value="" label="--선택--" />
                                                    <c:if test="${entireApplication.application.applAttrCode == '00001' || entireApplication.application.applAttrCode == '00003'}">
                                                    <form:options items="${common.collList}" itemValue="collCode" itemLabel="collName" />
                                                    </c:if>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group hidden-apply-kind-1 hidden-apply-kind-3 required">
                                            <label for="ariInstCode" class="col-sm-2 control-label">학·연·산 연구기관</label>
                                            <div class="col-sm-9">
                                                <form:select path="application.ariInstCode" id="ariInstCode" cssClass="form-control base-info">
                                                    <form:option value="" label="--선택--" />
                                                    <c:if test="${entireApplication.application.applAttrCode == '00002'}">
                                                    <form:options items="${common.ariInstList}" itemValue="ariInstCode" itemLabel="ariInstName" />
                                                    </c:if>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group required">
                                            <label for="deptCode" class="col-sm-2 control-label">지원 학과</label>
                                            <div class="col-sm-9">
                                                <form:select path="application.deptCode" id="deptCode" cssClass="form-control base-info">
                                                    <form:option value="" label="--선택--" />
                                                    <form:options items="${common.deptList}" itemValue="deptCode" itemLabel="deptName" />
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group required">
                                            <label for="corsTypeCode" class="col-sm-2 control-label">지원 과정</label>
                                            <div class="col-sm-9">
                                                <form:select path="application.corsTypeCode" id="corsTypeCode" cssClass="form-control base-info">
                                                    <form:option value="" label="--선택--" />
                                                    <form:options items="${common.corsTypeList}" itemValue="corsTypeCode" itemLabel="codeVal" />
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group required">
                                            <label for="detlMajCode" class="col-sm-2 control-label">세부 전공</label>
                                            <div class="col-sm-9">
                                                <form:select path="application.detlMajCode" id="detlMajCode" cssClass="form-control base-info">
                                                    <form:option value="" label="--선택--" />
                                                    <form:options items="${common.detlMajList}" itemValue="detlMajCode" itemLabel="detlMajName" />
                                                </form:select>
                                                <label id="detMajDesc" class="apexMessage"></label>
                                                <form:input path="application.inpDetlMaj" cssClass="form-control" style="display:none" />
                                                <div class="col-sm-offset-2 col-sm-9" id="detlMajRadio">
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox" id="partTimeYn" name="partTimeYn" value="unchecked">파트타임 여부</label>
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
                            <div class="panel panel-default">
                                <div class="panel-heading">지원자 정보</div>
                                <div class="panel-body">
                                    <div class="form-group required">
                                        <form:label path="application.korName" cssClass="col-sm-2 control-label">한글 이름</form:label>
                                        <div class="col-sm-9">
                                            <form:input path="application.korName" cssClass="form-control requiredInput" />
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <label class="col-sm-2 control-label">영문 이름</label>
                                        <div class="col-sm-4">
                                            <div class="input-group">
                                                <span class="input-group-addon">&nbsp;성&nbsp;</span>
                                                <form:input path="application.engSur" cssClass="form-control engName" />
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="input-group">
                                                <span class="input-group-addon">이름</span>
                                                <form:input path="application.engName" cssClass="form-control engName" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <form:label path="application.rgstNo" cssClass="col-sm-2 control-label">주민등록번호</form:label>
                                        <div class="col-sm-9">
                                            <form:input path="application.rgstNo" cssClass="form-control" />
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="panel panel-default">
                                <div class="panel-heading">지원자 상세정보</div>
                                <div class="panel-body">
                                    <div class="form-group required">
                                        <label for="citzCntrName" class="col-sm-2 control-label">국적</label>
                                        <div class="col-sm-2">
                                            <button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="citzCntrCode" data-targetNode2='citzCntrName' data-category="country">
                                                <span class="glyphicon glyphicon-search"></span> 검색
                                            </button>
                                        </div>
                                        <div class="col-sm-7">
                                            <form:hidden path="application.citzCntrCode" id="citzCntrCode" cssClass="form-control" />
                                            <input id="citzCntrName" class="form-control" value="${common.country.korCntrName}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">장애 사항</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">장애유형</span>
                                                <form:input path="applicationGeneral.hndcGrad" cssClass="col-sm-6 form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">장애등급</span>
                                                <form:input path="applicationGeneral.hndcType" cssClass="col-sm-6 form-control" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">지원자 연락처</div>
                                <div class="panel-body">
                                    <div class="form-group required">
                                        <label class="col-sm-2 control-label">주소</label>
                                        <div class="col-sm-2">
                                            <button type="button" class="btn btn-default btn-block btn-search" id="searchAddress">
                                                <span class="glyphicon glyphicon-search"></span> 우편번호 찾기
                                            </button>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="input-group">
                                                <%--<input type="text" class="form-control" id="postcode1" />--%>
                                                <%--<span class="input-group-addon"> - </span>--%>
                                                <%--<input type="text" class="form-control" id="postcode2" />--%>
                                                <form:input path="application.zipCode" cssClass="form-control" id="zipCode" readonly="true"/>
                                            </div>
                                        </div>
                                        <%--<form:hidden path="application.zipCode" id="zipCode"/>--%>
                                        <div class="col-sm-offset-2 col-sm-4">
                                            <form:input path="application.addr" cssClass="form-control" id="address" readonly="true" />
                                        </div>
                                        <div class="col-sm-5">
                                            <form:input path="application.detlAddr" cssClass="form-control" id="addressDetail" placeholder="세부주소" />
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <form:label path="application.telNum" cssClass="col-sm-2 control-label">전화번호</form:label>
                                        <div class="col-sm-9">
                                            <%--TODO--%>
                                            <%--<form:select path="telNumFirst" cssClass="form-control">--%>
                                                <%--<form:options items="${common.telNumFirst}" />--%>
                                            <%--</form:select> --%>
                                            <form:input path="application.telNum" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <form:label path="application.mobiNum" cssClass="col-sm-2 control-label">휴대폰</form:label>
                                        <div class="col-sm-9">
                                            <%--TODO--%>
                                            <%--<form:select path="mobiNumFirst" cssClass="form-control">--%>
                                                <%--<form:options items="${common.telNumFirst}" />--%>
                                            <%--</form:select>--%>
                                            <form:input path="application.mobiNum" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <form:label path="application.mailAddr" cssClass="col-sm-2 control-label">E-mail</form:label>
                                        <div class="col-sm-9">
                                            <form:input path="application.mailAddr" type="email" cssClass="form-control" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">비상연락처</div>
                                <div class="panel-body">
                                    <div class="form-group required">
                                        <form:label path="applicationGeneral.emerContName" cssClass="col-sm-2 control-label">이름</form:label>
                                        <div class="col-sm-9">
                                            <form:input path="applicationGeneral.emerContName" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <form:label path="applicationGeneral.emerContCode" cssClass="col-sm-2 control-label">관계</form:label>
                                        <div class="col-sm-9">
                                            <form:select path="applicationGeneral.emerContCode" cssClass="form-control">
                                                <form:option value="" label="--선택--" />
                                                <form:options items="${common.emerContList}" itemValue="code" itemLabel="codeVal" />
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <form:label path="applicationGeneral.emerContTel" cssClass="col-sm-2 control-label">전화번호</form:label>
                                        <div class="col-sm-9">
                                            <%--TODO--%>
                                            <%--<form:select path="telNumFirst" cssClass="form-control">--%>
                                                <%--<form:options items="${common.telNumFirst}" />--%>
                                            <%--</form:select>--%>
                                            <form:input path="applicationGeneral.emerContTel" cssClass="form-control" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default" id="currentCompany" hidden>
                                <div class="panel-heading">현재 근무처</div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <form:label path="applicationGeneral.currWrkpName" cssClass="col-sm-2 control-label">회사 이름</form:label>
                                        <div class="col-sm-9">
                                            <form:input path="applicationGeneral.currWrkpName" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label path="applicationGeneral.currWrkpDay" cssClass="col-sm-2 control-label">입사 일자</form:label>
                                        <div class="col-sm-9">
                                            <div class="input-group date">
                                                <form:input path="applicationGeneral.currWrkpDay" cssClass="col-sm-6 form-control" readonly="true" />
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label path="applicationGeneral.currWrkpTel" cssClass="col-sm-2 control-label">연락처</form:label>
                                        <div class="col-sm-9">
                                            <form:input path="applicationGeneral.currWrkpTel" cssClass="form-control" />
                                        </div>
                                    </div>
                                </div>
                            </div><%--panel--%>
                        </div>
                    </div><%--row--%>
                    <div class="btn-group btn-group-justified">
                        <div class="btn-group">
                            <button id="saveAppInfo" type="button" class="btn btn-info btn-lg btnAppl" data-saveType="appInfo">기본 정보 저장</button>
                        </div>
                    </div>
                </div><%--appinfo--%>


                <%-- Academy --%>
                <div class="tab-pane fade" id="academy">
                    <div class="spacer-tiny"></div>
                    <div class="row">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="panel panel-default">
                                <div class="panel-heading">대학교</div>
                                <div class="panel-body">
                                    <div class="form-group-block-list">
                                        <c:forEach begin="0" end="${entireApplication.collegeList.size() > 0 ? entireApplication.collegeList.size() - 1 : 0}" varStatus="stat">
                                        <div class="form-group-block">
                                            <form:hidden path="collegeList[${stat.index}].acadTypeCode" value="00002" />
                                            <form:hidden path="collegeList[${stat.index}].acadSeq" />
                                            <div class="form-group required">
                                                <label for="collegeList${stat.index}.schlCntrName" class="col-sm-2 control-label">소재 국가</label>
                                                <div class="col-sm-2">
                                                    <button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="collegeList${stat.index}.schlCntrCode" data-targetNode2='collegeList${stat.index}.korCntrName' data-category="country">
                                                        <span class="glyphicon glyphicon-search"></span> 검색
                                                    </button>
                                                </div>
                                                <div class="col-sm-6">
                                                    <form:hidden path="collegeList[${stat.index}].schlCntrCode" />
                                                    <form:input path="collegeList[${stat.index}].korCntrName" class="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group required">
                                                <label class="col-sm-2 control-label">재학 기간</label>
                                                <div class="col-sm-4 start-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">입학일</span>
                                                        <form:input path="collegeList[${stat.index}].entrDay" cssClass="form-control" readonly="true" />
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4 end-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">졸업(예정)일</span>
                                                        <form:input path="collegeList[${stat.index}].grdaDay" cssClass="form-control" readonly="true" />
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group required">
                                                <label class="col-sm-2 control-label">졸업 구분</label>
                                                <div class="col-sm-10">
                                                    <label class="radio-inline degr-radio"><form:radiobutton path="collegeList[${stat.index}].grdaTypeCode" value="00001" />졸업</label>
                                                    &nbsp;&nbsp;&nbsp;
                                                    <label class="radio-inline degr-radio"><form:radiobutton path="collegeList[${stat.index}].grdaTypeCode" value="00002" />졸업 예정</label>
                                                </div>
                                                <label class="col-sm-2 control-label degr-div">학위등록번호</label>
                                                <div class="col-sm-8 degr-no">
                                                    <form:input path="collegeList[${stat.index}].degrNo" cssClass="degr-no form-control"/>
                                                </div>
                                                <label class="col-sm-10 apexMessage degr-message" style="display:none" >* 졸업증명서(혹은 졸업관련 서류)를 추후 제출</label>
                                            </div>
                                            <div class="form-group required">
                                                <form:label path="collegeList[${stat.index}].schlName" cssClass="col-sm-2 control-label">학교 이름</form:label>
                                                <div class="col-sm-2">
                                                    <button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="collegeList${stat.index}.schlCode" data-targetNode2='collegeList${stat.index}.schlName' data-category="school-u">
                                                        <span class="glyphicon glyphicon-search"></span> 검색
                                                    </button>
                                                </div>
                                                <div class="col-sm-4">
                                                    <form:hidden path="collegeList[${stat.index}].schlCode" />
                                                    <form:input path="collegeList[${stat.index}].schlName" cssClass="form-control" />
                                                </div>
                                                <div class="col-sm-2">
                                                    <label class="radio-inline">
                                                        <form:radiobutton path="collegeList[${stat.index}].lastSchlYn" cssClass="radio-group" value="Y" />&nbsp;&nbsp;최종 학교
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group required">
                                                <form:label path="collegeList[${stat.index}].collName" cssClass="col-sm-2 control-label">단과 대학</form:label>
                                                <div class="col-sm-9">
                                                    <form:input path="collegeList[${stat.index}].collName" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group required">
                                                <form:label path="collegeList[${stat.index}].majName" cssClass="col-sm-2 control-label">학과 이름</form:label>
                                                <div class="col-sm-9">
                                                    <form:input path="collegeList[${stat.index}].majName" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group required">
                                                <label class="col-sm-2 control-label">평량 평균</label>
                                                <div class="col-sm-4">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">평점</span>
                                                        <form:input path="collegeList[${stat.index}].gradAvr" cssClass="form-control" />
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">만점</span>
                                                        <form:input path="collegeList[${stat.index}].gradFull" cssClass="form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="btn btn-remove" data-block-index="${stat.index}" data-fileupload-block-list="fuCollegeDocBlockList"><button type="button" class="close" aria-hidden="true">×</button></div>
                                        </div>
                                        </c:forEach>
                                        <div class="btn btn-info btn-add" data-fileupload-block-list="fuCollegeDocBlockList">추가</div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">대학원</div>
                                <div class="panel-body">
                                    <div class="form-group-block-list">
                                        <c:forEach begin="0" end="${entireApplication.graduateList.size() > 0 ? entireApplication.graduateList.size() - 1 : 0}" varStatus="stat">
                                        <div class="form-group-block">
                                            <form:hidden path="graduateList[${stat.index}].acadTypeCode" value="00003" />
                                            <form:hidden path="graduateList[${stat.index}].acadSeq" />
                                            <div class="form-group required">
                                                <label for="graduateList${stat.index}.schlCntrName" class="col-sm-2 control-label">소재 국가</label>
                                                <div class="col-sm-2">
                                                    <button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="graduateList${stat.index}.schlCntrCode" data-targetNode2='graduateList${stat.index}.korCntrName' data-category="country">
                                                        <span class="glyphicon glyphicon-search"></span> 검색
                                                    </button>
                                                </div>
                                                <div class="col-sm-6">
                                                    <form:hidden path="graduateList[${stat.index}].schlCntrCode" />
                                                    <form:input path="graduateList[${stat.index}].korCntrName" class="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group required">
                                                <label class="col-sm-2 control-label">재학 기간</label>
                                                <div class="col-sm-4 start-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">입학일</span>
                                                        <form:input path="graduateList[${stat.index}].entrDay" cssClass="form-control" readonly="true" />
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4 end-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">졸업(예정)일</span>
                                                        <form:input path="graduateList[${stat.index}].grdaDay" cssClass="form-control" readonly="true" />
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group required">
                                                <label class="col-sm-2 control-label">졸업 구분</label>
                                                <div class="col-sm-10">
                                                    <label class="radio-inline degr-radio"><form:radiobutton path="graduateList[${stat.index}].grdaTypeCode" value="00001" />졸업</label>
                                                    &nbsp;&nbsp;&nbsp;
                                                    <label class="radio-inline degr-radio"><form:radiobutton path="graduateList[${stat.index}].grdaTypeCode" value="00002" />졸업 예정</label>
                                                </div>
                                                <label class="col-sm-2 control-label degr-div">학위등록번호</label>
                                                <div class="col-sm-8 degr-no">
                                                    <form:input path="graduateList[${stat.index}].degrNo" cssClass="degr-no form-control"/>
                                                </div>
                                                <label class="col-sm-10 apexMessage degr-message" style="display:none">* 졸업증명서(혹은 졸업관련 서류)를 추후 제출</label>
                                            </div>
                                            <div class="form-group required">
                                                <form:label path="graduateList[${stat.index}].schlName" cssClass="col-sm-2 control-label">학교 이름</form:label>
                                                <div class="col-sm-2">
                                                    <button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="graduateList${stat.index}.schlCode" data-targetNode2='graduateList${stat.index}.schlName' data-category="school-u">
                                                        <span class="glyphicon glyphicon-search"></span> 검색
                                                    </button>
                                                </div>
                                                <div class="col-sm-4">
                                                    <form:hidden path="graduateList[${stat.index}].schlCode" />
                                                    <form:input path="graduateList[${stat.index}].schlName" cssClass="form-control" />
                                                </div>
                                                <div class="col-sm-2">
                                                    <label class="radio-inline">
                                                        <form:radiobutton path="graduateList[${stat.index}].lastSchlYn" cssClass="radio-group" value="Y" />&nbsp;&nbsp;최종 학교
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group required">
                                                <form:label path="graduateList[${stat.index}].collName" cssClass="col-sm-2 control-label">단과 대학</form:label>
                                                <div class="col-sm-9">
                                                    <form:input path="graduateList[${stat.index}].collName" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group required">
                                                <form:label path="graduateList[${stat.index}].majName" cssClass="col-sm-2 control-label">학과 이름</form:label>
                                                <div class="col-sm-9">
                                                    <form:input path="graduateList[${stat.index}].majName" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group required">
                                                <label class="col-sm-2 control-label">평균 평점</label>
                                                <div class="col-sm-4">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">평점</span>
                                                        <form:input path="graduateList[${stat.index}].gradAvr" cssClass="form-control" />
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">만점</span>
                                                        <form:input path="graduateList[${stat.index}].gradFull" cssClass="form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="btn btn-remove" data-block-index="${stat.index}" data-fileupload-block-list="fuGraduateDocBlockList"><button type="button" class="close" aria-hidden="true">×</button></div>
                                        </div>
                                        </c:forEach>
                                        <div class="btn btn-info btn-add" data-fileupload-block-list="fuGraduateDocBlockList">추가</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="btn-group btn-group-justified">
                        <div class="btn-group">
                            <button id="saveAcademy" type="button" class="btn btn-primary btn-lg btnAppl disabled" data-saveType="academy">학력 저장</button>
                        </div>
                    </div>
                </div>

                <%--language & career--%>
                <div class="tab-pane fade" id="langcareer">
                    <div class="spacer-tiny"></div>
                    <div class="row">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="panel panel-default">
                                <div class="panel-heading">어학성적</div>
                                <div class="panel-body" id="english-score-list">
                                    <c:forEach items="${common.langExamList}" var="langExam" varStatus="stat">
                                    <div class="form-group hide-lang required">
                                        <c:choose>
                                        <c:when test="${stat.index == 0}">
                                        <label class="col-sm-2 control-label">영어</label>
                                        <div class="col-sm-2">
                                        </c:when>
                                        <c:otherwise>
                                        <div class="col-sm-offset-2 col-sm-2">
                                        </c:otherwise>
                                        </c:choose>
                                            <input type="hidden" name="applicationLanguageList[${stat.index}].langExamCode" id="applicationLanguageList${stat.index}.langExamCode" value="${langExam.examCode}" />
                                            <div class="checkbox">
                                                <label for="checkLang${stat.index}"><input type="checkbox" class="btn-lang-disabled lang-checkbox" id="checkLang${stat.index}" <c:if test="entireApplication.applicationLanguageList['${stat.index}'] != null">checked</c:if>/>${langExam.examName}</label>
                                            </div>
                                        <c:choose>
                                        <c:when test="${stat.index == 0}">
                                        </div>
                                        </c:when>
                                        <c:otherwise>
                                        </div>
                                        </c:otherwise>
                                        </c:choose>
                                        <div class="col-sm-2 show-lang">
                                            <c:if test="${langExam.examCode == '00001'}">
                                            <form:select path="applicationLanguageList[${stat.index}].toflTypeCode" cssClass="form-control">
                                                <form:option value="" label="--선택--" />
                                                <form:options items="${common.toflTypeList}" itemValue="code" itemLabel="codeVal" />
                                            </form:select>
                                            </c:if>
                                        </div>
                                        <div class="col-sm-2 hide-lang">
                                            <label class="lbl-lang" id="checkLangLabel${stat.index}" >인정 불가</label>
                                        </div>
                                        <div class="col-sm-3 show-lang">
                                            <div class="input-group date">
                                                <span class="input-group-addon">시험일</span>
                                                <form:input path="applicationLanguageList[${stat.index}].examDay" cssClass="form-control" />
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                        <div class="col-sm-2 show-lang">
                                            <div class="input-group">
                                                <span class="input-group-addon">점수</span>
                                                <form:input path="applicationLanguageList[${stat.index}].langGrad" cssClass="form-control" />
                                            </div>
                                        </div>
                                    </div>
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
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">경력 사항</div>
                                <div class="panel-body">
                                    <div id="career-container" class="form-group-block-list">
                                        <c:forEach varStatus="stat" begin="0" end="${entireApplication.applicationExperienceList.size() > 0 ? entireApplication.applicationExperienceList.size() - 1 : 0}">
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
                            <button id="saveLangCareer" type="button" class="btn btn-info btn-lg btnAppl disabled" data-saveType="langCareer">어학 및 경력 저장</button>
                        </div>
                    </div>
                </div>
                <%--첨부파일--%>
                <div class="tab-pane fade" id="fileupload">
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
                            <c:forEach items="${entireApplication.docGroupList}" var="docGroup" varStatus="grpStat">
                                <c:if test = "${docGroup.subGrp.size()==0  &&  docGroup.mandDocList.size()>0}">
                                    <div class="panel panel-darkgray">
                                        <div class="panel-heading">${docGroup.fileGroupName} 서류</div>
                                        <div class="panel-body" id="docGroupList${grpStat.index}.list">
                                            <c:forEach items="${docGroup.mandDocList}" var="mandDoc" varStatus="docStat">
                                                <hr/>
                                                <div class="form-group" id="docGroupList${grpStat.index}.mandDocList${docStat.index}.${mandDoc.docItemCode}">
                                                    <label class="col-sm-3 control-label word-keep-all">${mandDoc.docItemName}</label>
                                                    <div class="col-sm-8">
                                                        <div class="input-group">
                                                            <div class="input-group-btn">
                                                                <input type="file" class="btn btn_lg btn-file" id="docGroupList${grpStat.index}.mandDocList${docStat.index}.docName" name="docGroupList[${grpStat.index}].mandDocList[${docStat.index}].docName"/>
                                                            </div>
                                                            <c:if test="${mandDoc.orgnSendYn =='Y' || mandDoc.orgnSendYn =='y'}">
                                                               <div class="apexMessage">${mandDoc.msgNo}</div>
                                                            </c:if>
                                                            <c:if test="${mandDoc.orgnSendYn =='N' || mandDoc.orgnSendYn !='n'}">
                                                                <div class="col-sm-4 nopadding"><input type="button" id="docGroupList${grpStat.index}.mandDocList${docStat.index}.btn" name="docGroupList[${grpStat.index}].mandDocList[${docStat.index}].btn"
                                                                                                       class="btn btn-default btn-block btn-upload" value="올리기"
                                                                                                       data-file-path="docGroupList${grpStat.index}.mandDocList${docStat.index}.filePath"
                                                                                                       data-file-name="docGroupList${grpStat.index}.mandDocList${docStat.index}.fileName"
                                                                                                       data-org-file-name="docGroupList${grpStat.index}.mandDocList${docStat.index}.orgFileName"/>
                                                                </div>
                                                                <span class="col-sm-8" id="docGroupList${grpStat.index}.mandDocList${docStat.index}" style="text-decoration: none;">
                                                                    <a href="${contextPath}/filedownload/attached/${entireApplication.application.admsNo}/${entireApplication.application.applNo}/${mandDoc.fileName}/${mandDoc.orgFileName}">${mandDoc.orgFileName}</a>
                                                                </span>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                    <form:hidden path="docGroupList[${grpStat.index}].mandDocList[${docStat.index}].docTypeCode" value="${mandDoc.docTypeCode}" />
                                                    <form:hidden path="docGroupList[${grpStat.index}].mandDocList[${docStat.index}].docGrp" value="${mandDoc.docGrp}" />
                                                    <form:hidden path="docGroupList[${grpStat.index}].mandDocList[${docStat.index}].docItemCode" value="${mandDoc.docItemCode}" />
                                                    <form:hidden path="docGroupList[${grpStat.index}].mandDocList[${docStat.index}].docItemName" value="${mandDoc.docItemName}" />
                                                    <form:hidden path="docGroupList[${grpStat.index}].mandDocList[${docStat.index}].filePath"  value="${mandDoc.filePath}"/>
                                                    <form:hidden path="docGroupList[${grpStat.index}].mandDocList[${docStat.index}].fileName"  value="${mandDoc.fileName}"/>
                                                    <form:hidden path="docGroupList[${grpStat.index}].mandDocList[${docStat.index}].orgFileName"  value="${mandDoc.orgFileName}"/>
                                                </div>
                                            </c:forEach>

                                        </div>
                                    </div>
                                    <div class="spacer-tiny"></div>
                                </c:if>

                                <c:if test = "${docGroup.subGrp.size()>0}">
                                    <div class="panel panel-darkgray">
                                        <div class="panel-heading">${docGroup.fileGroupName} 서류</div>
                                        <div class="panel-body" id="docGroupList${grpStat.index}.list">
                                            <div class="form-group-block-list" id="fuCollegeDocBlockList">
                                                <c:forEach items="${docGroup.subGrp}" var="subGrp" varStatus="subGrpStat">
                                                    <div class="form-group-block">
                                                        <c:forEach items="${subGrp.mandDocList}" var="mandDoc" varStatus="docStat">
                                                            <hr/>
                                                            <div class="form-group" id="docGroupList${grpStat.index}.subGrp${subGrpStat.index}.mandDocList${docStat.index}.${mandDoc.docItemCode}">
                                                                <label class="col-sm-3 control-label word-keep-all">${mandDoc.docItemName}</label>
                                                                <div class="col-sm-8">
                                                                    <div class="input-group">
                                                                        <div class="input-group-btn">
                                                                            <input type="file" class="btn btn_lg btn-file" id="docGroupList${grpStat.index}.subGrp${subGrpStat.index}.mandDocList${docStat.index}.docName" name="docGroupList[${grpStat.index}].subGrp[${subGrpStat.index}].mandDocList[${docStat.index}].docName"/>
                                                                        </div>
                                                                        <c:if test="${mandDoc.orgnSendYn =='Y' || mandDoc.orgnSendYn =='y'}">
                                                                            <div class="apexMessage">${mandDoc.msgNo}</div>
                                                                        </c:if>
                                                                        <c:if test="${mandDoc.orgnSendYn =='N' || mandDoc.orgnSendYn !='n'}">
                                                                            <div class="col-sm-4 nopadding"><input type="button" id="docGroupList${grpStat.index}.subGrp${subGrpStat.index}.mandDocList${docStat.index}.btn" name="docGroupList[${grpStat.index}].mandDocList[${docStat.index}].btn"
                                                                                                                   class="btn btn-default btn-block btn-upload" value="올리기"
                                                                                                                   data-file-path="docGroupList${grpStat.index}.subGrp${subGrpStat.index}.mandDocList${docStat.index}.filePath"
                                                                                                                   data-file-name="docGroupList${grpStat.index}.subGrp${subGrpStat.index}.mandDocList${docStat.index}.fileName"
                                                                                                                   data-org-file-name="docGroupList${grpStat.index}.subGrp${subGrpStat.index}.mandDocList${docStat.index}.orgFileName"/>
                                                                            </div>
                                                                            <span class="col-sm-8" id="docGroupList${grpStat.index}.subGrp${subGrpStat.index}.mandDocList${docStat.index}" style="text-decoration: none;">
                                                                                <a href="${contextPath}/filedownload/attached/${entireApplication.application.admsNo}/${entireApplication.application.applNo}/${mandDoc.fileName}/${mandDoc.orgFileName}">${mandDoc.orgFileName}</a>
                                                                            </span>
                                                                        </c:if>
                                                                    </div>
                                                                </div>
                                                                <form:hidden path="docGroupList[${grpStat.index}].subGrp[${subGrpStat.index}].mandDocList[${docStat.index}].docTypeCode" value="${mandDoc.docTypeCode}" />
                                                                <form:hidden path="docGroupList[${grpStat.index}].subGrp[${subGrpStat.index}].mandDocList[${docStat.index}].docGrp" value="${mandDoc.docGrp}" />
                                                                <form:hidden path="docGroupList[${grpStat.index}].subGrp[${subGrpStat.index}].mandDocList[${docStat.index}].docItemCode" value="${mandDoc.docItemCode}" />
                                                                <form:hidden path="docGroupList[${grpStat.index}].subGrp[${subGrpStat.index}].mandDocList[${docStat.index}].docItemName" value="${mandDoc.docItemName}" />
                                                                <form:hidden path="docGroupList[${grpStat.index}].subGrp[${subGrpStat.index}].mandDocList[${docStat.index}].filePath"  value="${mandDoc.filePath}"/>
                                                                <form:hidden path="docGroupList[${grpStat.index}].subGrp[${subGrpStat.index}].mandDocList[${docStat.index}].fileName"  value="${mandDoc.fileName}"/>
                                                                <form:hidden path="docGroupList[${grpStat.index}].subGrp[${subGrpStat.index}].mandDocList[${docStat.index}].orgFileName"  value="${mandDoc.orgFileName}"/>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="spacer-tiny"></div>
                            </c:forEach>
                            <div class="panel panel-darkgray">
                                <div class="panel-body" id="docGroupList[${grpStat.index}].list">
                                    <label class="col-sm-10 apexMessage">- 파일 첨부시 주의사항
                                        <br>1. 문서별로 1개의 파일만 첨부 가능합니다.
                                        <br>2. 사진 및 문서의 해상도와 가독성 여부를 반드시 확인하세요.
                                        <br>3. 스캔시에는 300dpi 이상으로 스캔하시기 바랍니다.
                                        <br>4. 문서 크기는 A4 크기로 생성하여 첨부하셔야 합니다.</label>
                                    <label class="col-sm-10 apexMessage">- 여러개의 PDF파일을 합치는 방법
                                        <br>여러개 PDF 은 하나의 PDF 파일로 만들어서 업로드 하시기 바랍니다.
                                        <br>PDF 통합은 전용프로그램이나 인터넷 서비스를 이용하시기 바랍니다.
                                        <br>  예) http://convert.neevia.com/pdfmerge/
                                        <br>  ( 예시 사이트에서 발생할 수 있는 문제는 당사에서 책임지지 않습니다 )</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="btn-group btn-group-justified">
                        <div class="btn-group">
                            <button id="saveFileUpload" type="button" class="btn btn-primary btn-lg btnAppl disabled" data-saveType="fileUpload">첨부 파일 저장</button>
                        </div>
                        <div class="btn-group">
                            <button id="apply" type="button" class="btn btn-primary btn-lg btnAppl disabled" data-saveType="apply">제출</button>
                        </div>
                    </div>
                </div><%--첨부파일--%>
            </div> <%--myTabContent--%>
        </form:form>

        <%--<div class="btn-group btn-group-justified">--%>
            <%--<div class="btn-group">--%>
                <%--<button id="saveAppInfo" type="button" class="btn btn-info btn-lg btnAppl" data-saveType="appInfo">기본 정보 저장</button>--%>
            <%--</div>--%>
            <%--<div class="btn-group">--%>
                <%--<button id="saveAcademy" type="button" class="btn btn-primary btn-lg btnAppl disabled" data-saveType="academy">학력 저장</button>--%>
            <%--</div>--%>
            <%--<div class="btn-group">--%>
                <%--<button id="saveLangCareer" type="button" class="btn btn-info btn-lg btnAppl disabled" data-saveType="langCareer">어학 및 경력 저장</button>--%>
            <%--</div>--%>
            <%--<div class="btn-group">--%>
                <%--<button id="saveFileUpload" type="button" class="btn btn-primary btn-lg btnAppl disabled" data-saveType="fileUpload">첨부 파일 저장</button>--%>
            <%--</div>--%>
            <%--<div class="btn-group">--%>
                <%--<button id="apply" type="button" class="btn btn-primary btn-lg btnAppl disabled" data-saveType="apply">작성완료</button>--%>
            <%--</div>--%>
            <%--<div class="btn-group">--%>
                <%--<button id="reset" type="button" class="btn btn-warning btn-lg">작성 내용 비우기</button>--%>
            <%--</div>--%>
        <%--</div>--%>
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

    <%-- 다음 주소 검색 팝업 --%>
    <div id="postLayer" style="display:none;border:5px solid;position:fixed;width:310px;height:510px;left:50%;margin-left:-155px;top:50%;margin-top:-235px;overflow:hidden;-webkit-overflow-scrolling:touch;z-index:2;background-color:#fff;color: #111;">
        <img src="${contextPath}/img/user/addr-close.png" id="btnClosePostLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px" alt="닫기 버튼">
    </div>

</section>
<content tag="local-script">
    <%--<script src="${contextPath}/js/postcode.js"></script>--%>
    <script src="http://dmaps.daum.net/map_js_init/postcode.js"></script>
    <%--<script src="${contextPath}/js/bootstrap-datepicker.js"></script>--%>
    <%--<script src="${contextPath}/js/bootstrap-datepicker.kr.js"></script>--%>
    <script src="${contextPath}/js/jquery-ui.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            document.getElementById('applNo').value='${entireApplication.application.applNo}';
            document.getElementById('admsNo').value='${entireApplication.application.admsNo}';
            document.getElementById('applStsCode').value='${entireApplication.application.applStsCode}';
            document.getElementById('entrYear').value='${entireApplication.application.entrYear}';
            document.getElementById('admsTypeCode').value='${entireApplication.application.admsTypeCode}';


            <%-- 탭 컨트롤 --%>
            var disableTab = function(anchorObj, msg) {
                var $anchor = $(anchorObj);
                $anchor.prop('disabled', true);
                $anchor.prop('data-href', $anchor.attr('href'));
                $anchor.attr('href', '#');
                $anchor.addClass('disabled-link');
                $anchor.on('click', {msg: msg}, tabMsgHandler);
            };
            var enableTab = function(anchorObj) {
                var $anchor = $(anchorObj);
                $anchor.prop('disabled', false);
                $anchor.attr('href', $anchor.prop('data-href')); // restore href
                $anchor.removeClass('disabled-link');
                $anchor.off('click', tabMsgHandler);
            };
            var tabMsgHandler = function(event) {
                alert(event.data.msg);
            };
            var tabControl = function(applStsCode) {
                switch (applStsCode) {
                    case '':
                    case '00001' :
                        disableTab(document.getElementById('tabAcademy'), '<spring:message code="U321"/>');
                        disableTab(document.getElementById('tabLangCareer'), '<spring:message code="U322"/>');
                        disableTab(document.getElementById('tabFileUpload'), '<spring:message code="U323"/>');
                        break;
                    case '00002' :
                        disableTab(document.getElementById('tabLangCareer'), '<spring:message code="U322"/>');
                        disableTab(document.getElementById('tabFileUpload'), '<spring:message code="U323"/>');
                        break;
                    case '00003' :
                        disableTab(document.getElementById('tabFileUpload'), '<spring:message code="U323"/>');
                        break;
                }
            };
            tabControl(document.getElementById('applStsCode').value);
            <%-- 탭 컨트롤 --%>

            var baseInfoSaved = function() {
//                $('.base-info').prop('disabled', 'true');
                $('.base-info>option').filter( function() {
                    return !this.selected;
                }).each( function() {
                    $(this).prop('disabled', true);
                });

                $('#baseCancel').css('display', 'block');
                $('#baseSave').css('display', 'none');
            };

            var btnEnable = function(applStsCode) {
                switch(applStsCode) {
                    case "00001" :
                        $('#saveAcademy').removeClass('disabled');
                        enableTab(document.getElementById('tabAcademy'));
                        break;
                    case "00002" :
                        $('#saveAcademy').removeClass('disabled');
                        $('#saveLangCareer').removeClass('disabled');
                        enableTab(document.getElementById('tabAcademy'));
                        enableTab(document.getElementById('tabLangCareer'));
                        break;
                    case "00003" :
                        $('#saveAcademy').removeClass('disabled');
                        $('#saveLangCareer').removeClass('disabled');
                        $('#saveFileUpload').removeClass('disabled');
                        enableTab(document.getElementById('tabAcademy'));
                        enableTab(document.getElementById('tabLangCareer'));
                        enableTab(document.getElementById('tabFileUpload'));
                        break;
                    case "00004" :
                        $('.btnAppl').removeClass('disabled');
                        enableTab(document.getElementById('tabAcademy'));
                        enableTab(document.getElementById('tabLangCareer'));
                        enableTab(document.getElementById('tabFileUpload'));
                        break;
                }
            };

            if (document.getElementById('applNo').value != "") {
                baseInfoSaved();
            }

            btnEnable(document.getElementById('applStsCode').value);

            <%-- 기본 정보 > 지원 사항 처리 --%>
            $('#btnBaseSave').on('click', function(e) {
                if ( confirm('<spring:message code="U313"/>') ) {
                    baseInfoSaved();
                } else {
                    return false;
                }
                event.preventDefault();
            });

            $('#btnBaseCancel').on('click', function(e) {
                if ( confirm('<spring:message code="U314"/>') ) {
                    //TODO DB 삭제 후 공고 목록으로 이동
                } else {
                    return false;
                }
                event.preventDefault();
            });
            <%-- 기본 정보 > 지원 사항 처리 --%>

            <%-- alert 생성 --%>
            function createAlert(message) {
                var alert = $('<div></div>').addClass('alert alert-success alert-dismissable fade in');
                alert.append($('<button></button>').attr({
                    'type': 'button',
                    'data-dismiss': 'alert',
                    'aria-hidden': 'true'
                }).addClass('close').text('✖'));
                alert.append($('<span></span>').text(message));
                return alert;
            }

            <%-- 하단 버튼 처리 --%>
            var formProcess = function(event) {
                var $form = $(this),
                        isApply = event.type ==='apply'?true:false,
                        isLangCareer = event.type ==='langCareer'?true:false,
                        $formData = $form.serializeArray(),
                        ajaxObj = {
                            type: 'POST',
                            data: $formData,
//                            timeout: 5000,
                            success: function (context) {
                                if (context.result == 'SUCCESS') {
                                    var message = context.message,
                                            alert = createAlert(message),
                                            applNo = context.data.applNo,
                                            applStsCode = context.data.applStsCode,
                                            admsNo = context.data.admsNo,
                                            entrYear = context.data.entrYear,
                                            admsTypeCode = context.data.admsTypeCode;
                                    $('#alert-container').append(alert);
                                    document.getElementById('applNo').value = applNo;
                                    document.getElementById('applStsCode').value = applStsCode;
                                    document.getElementById('admsNo').value = admsNo;
                                    document.getElementById('entrYear').value = entrYear;
                                    document.getElementById('admsTypeCode').value = admsTypeCode;

                                    btnEnable(applStsCode);
                                    window.setTimeout(function() {
                                        alert.alert('close');
                                        if (isApply) {
                                            location.href="${contextPath}/application/mylist";
                                        }
                                        /* 어학 경력 저장 시 왜 별도처리하는 지 알 수 없음
                                        else if (isLangCareer) {
                                            var form = document.getElementById('entireApplication');
                                            form.action = '${contextPath}/application/apply';
                                            form.submit();
                                        }
                                        */
                                    }, 1000);
                                }
                            },
                            error: function(e) {
                                console.log(e.statusText);
                            }
                        };

                $form.find('input.radio-group').filter(function() {
                    return this.checked == false;
                }).each(function() {
                    $formData.push({name: this.name, value: 'N'});
                });
                switch (event.type) {
                    case 'appInfo':
                        ajaxObj.url = "${contextPath}/application/save/appInfo";
                        break;
                    case 'academy':
                        ajaxObj.url = "${contextPath}/application/save/academy";
                        break;
                    case 'langCareer':
                        ajaxObj.url = "${contextPath}/application/save/langCareer";
                        break;
                    case 'fileUpload':
                        ajaxObj.url = "${contextPath}/application/save/fileUpload";
                        break;
                    case 'apply':
                        if ( !confirm('첨부 파일을 포함한 모든 내용을 확인하였으며,\n사용자의 잘못으로 발생한 문제는 사용자에게 책임이 있음에 동의하십니까?')) return false;
                        $('.btnAppl').prop('disabled', true);
                        ajaxObj.url = "${contextPath}/application/apply/apply";
                        break;
                    case 'reset':
                        break;
                }
                $.ajax(ajaxObj);
                event.preventDefault();
            };

            $('.btnAppl').each( function () {
                var saveType = this.getAttribute('data-saveType');
                $(this).on('click', function() {
                    $('#entireApplication').trigger(saveType);
                });
                $('#entireApplication').on(saveType, formProcess);
            });

            $('#reset').on('click', function() {
//                var $curPane = $('.tab-pane.active');
//                var $curForm = $curPane.find('form');
//                $curForm.each(function() {
//                    this.reset();
//                });
                document.getElementById('entireApplication').reset(); //TODO reset 안됨
            });
            <%-- 하단 버튼 처리 --%>

            <%-- 영문 이름 처리 시작 --%>
            $('.engName').on('keyup', function() {
                this.value = this.value.toUpperCase().replace(/([^0-9A-Z])/g,"");
            });
            <%-- 영문 이름 처리 끝 --%>

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

                        var obj = JSON.parse(data.data);

                        for ( i = 0, l = obj.length ; i < l ; i++ ) {
                            var record;
                            if (category.isCountry) {
                                record = $('<tr>' + '<td><span style="display: none;" class="b-close">' + obj[i].cntrCode + '</span></td>' + '<td><span class="b-close">' + obj[i].korCntrName + '</span></td>' + '<td><span class="b-close">' + obj[i].engCntrName + '</span></td>' + '</tr>');
                            } else if (category.isSchool) {
                                record = $('<tr>' + '<td><span style="display: none;" class="b-close">' + obj[i].schlCode + '</span></td>' + '<td><span class="b-close">' + obj[i].schlName + '</span></td>' + '</tr>');
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

            var showDaumPostcode = function () {
                new daum.Postcode({
                    oncomplete: function(data) {
                        // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                        // 우편번호와 주소 및 영문주소 정보를 해당 필드에 넣는다.
//                        document.getElementById('postcode1').value = data.postcode1;
//                        document.getElementById('postcode2').value = data.postcode2;
                        document.getElementById('zipCode').value = data.postcode1 + data.postcode2;
                        document.getElementById('address').value = data.address1;
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

            $('#searchAddress').on('click', showDaumPostcode);
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
            $('.input-daterange>input').datepicker(datePickerOption);
            <%-- 달력 끝 --%>

            <%-- 처음 탭 표시 --%>
//            $("#myTab").tabs();
            $('#myTab a:first').tab('show');
//            $("#myTab").tabs({ disabled: [ 1, 2, 3 ] });




            <%-- BootStrap Validator --%>
            var numericValidator = {
                numeric: {
                    separator: '',
                    message: '${msgPhoneNo}'
                }
            }

            function checkGrad(validator, $field, options) {
                if ($field) {
                    return {
                        valid: true,
                        message: "The error message"
                    }
                }
                return {
                    valid: false,
                    message: 'Other error message'
                }
            }

            <%-- bootstrapValidator --%>
            $('#entireApplication').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    "application.rgstNo": {
                        validators: {
                            regexp: {
                                regexp: /^\d{13}/,
                                message: '${msgRgstNo}'
                            }
                        }
                    },
                    "application.telNum": {
                        validators: numericValidator
                    },
                    "application.mobiNum": {
                        validators: numericValidator
                    },
                    "applicationGeneral.emerContTel": {
                        validators: numericValidator
                    },
                    gradAvr: {
                        selector: '[name$="gradAvr"]',
                        validators: {
                            callback: {
                                callback: function (value, validator, $field) {
                                    if (value === '') {
                                        return true;
                                    }
                                    var regexp = /^[0-9]+(.[0-9]{1,2})?$/;
                                    if (!regexp.test(value)) {
                                        return {
                                            value: false,
                                            message: '소수점 2자리까지 입력가능합니다'
                                        }
                                    }

                                    var $parent = $field.parents('.form-group');
                                    var name = $field.attr('name');
                                    name = name.substring(0, name.indexOf('.')) + '.gradFull';
                                    var $gradFull = $parent.find('[name="' + name + '"]');
                                    var gradFullValue = $gradFull.val();
                                    if (gradFullValue === '' || !regexp.test(gradFullValue)) {
                                        return true;
                                    }
                                    if (Number(value) > Number(gradFullValue)) {
                                        return {
                                            valid: false,
                                            message: '평점보다 만점이 커야합니다'
                                        };
                                    }

                                    return true;
                                }
                            }
                        }
                    },
                    gradFull: {
                        selector: '[name$="gradFull"]',
                        validators: {
                            callback: {
                                callback: function (value, validator, $field) {
                                    if (value === '') {
                                        return true;
                                    }
                                    var regexp = /^[0-9]+(.[0-9]{1,2})?$/;
                                    if (!regexp.test(value)) {
                                        return {
                                            value: false,
                                            message: '소수점 2자리까지 입력가능합니다'
                                        }
                                    }

                                    var $parent = $field.parents('.form-group');
                                    var name = $field.attr('name');
                                    name = name.substring(0, name.indexOf('.')) + '.gradAvr';
                                    var $gradFull = $parent.find('[name="' + name + '"]');
                                    var gradFullValue = $gradFull.val();
                                    if (gradFullValue === '' || !regexp.test(gradFullValue)) {
                                        return true;
                                    }
                                    if (Number(value) < Number(gradFullValue)) {
                                        return {
                                            valid: false,
                                            message: '평점보다 만점이 커야합니다'
                                        };
                                    }

                                    return true;
                                }
                            }
                        }
                    },
                    requiredInput: {
                        selector: '.requiredInput',
                        validators: {
                            notEmpty: '필수 입력 사항입니다.'
                        }
                    }
                }
            });

            function getEnglishScoreSerializeArray(form) {
                var array = [], $groups, $items, val, id, name, i, j;
                $groups = $('#english-score-list').find('.form-group').filter(function() {
                    var check = $(this).find('input.btn-lang')[0];
                    return check ? check.checked : false;
                });

                for (i = 0; i < $groups.length; i++) {
                    $items = $($groups[i]).find('input, select');

                    for (j = 0; j < $items.length; j++) {
                        val = $items[j].value;
                        if (val === '' || val === '-') {
                            continue;
                        }
                        id = $items[j].id;
                        if (!id) {
                            continue;
                        }
                        id = id.substring(0, id.indexOf('.'));
                        name = 'applicationLanguageList[' + i + '].' + id;
                        array.push({name: name, value: $items[j].value})
                    }
                }

                return array;
            }


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

                // 파일업로드 부분
                var fileUploadContainer = document.getElementById(target.getAttribute("data-fileupload-block-list"));
                var fuBlocks = fileUploadContainer.querySelectorAll('.form-group-block');
                var fuOriginBlock = fuBlocks[fuBlocks.length - 1];
                var $fuCloneObj;
                if (fuOriginBlock) {
                    $fuCloneObj = $(fuOriginBlock).clone(true);
                    updateIdAndName($fuCloneObj[0], fuBlocks.length);
                    eraseContents($fuCloneObj[0]);
                    fileUploadContainer.insertBefore($fuCloneObj[0], fuOriginBlock.nextSibling);
                }

//                $('#entireApplication').bootstrapValidator('addFiend', $cloneObj);
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

                // 파일업로드 부분 입력란 함께 제거
//                var fileUploadContainer = document.getElementById(target.getAttribute("data-fileupload-block-list")),
//                    indexOfBlockToRemove = target.getAttribute('data-block-index'),
//                    blockToRemove = fileUploadContainer.children[indexOfBlockToRemove];
//                fileUploadContainer.removeChild(blockToRemove);

            });

            <%-- id, name 재설정 시작 --%>
            function updateIdAndName( block, index ) {
                var i, name, prefix, suffix, input, items, label;
                var input = block.querySelector('input');

                name = input.name;
//                prefix = name.substring(0, name.indexOf('['));

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

            <%-- 고등학교 졸업/검정고시 동적 변경 시작 --%>
            $('input[name="highSchool.acadTypeCode"]').on('change', function() {
                var radioValue = $(this).val();
                if (this.checked) {
                    if (radioValue == '00001') {
                        hideExclude('highschoolSubContainer1');
                    } else {
                        hideExclude('highschoolSubContainer2');
                    }
                }
            });

            function hideExclude(excludeId) {
                $('#highschoolDynamic').children().each(function() {
                    $(this).hide();
                });
                $('#' + excludeId).show();
            }

            $('input[name="highSchool.acadTypeCode"]').trigger('change');
            <%--<c:choose>--%>
                <%--<c:when test="${entireApplication.highSchool.acadTypeCode == '00005'}">--%>
            <%--$('#highSchoolAcadTypeCode2').trigger('change');--%>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
            <%--$('#highSchoolAcadTypeCode1').trigger('change');--%>
                <%--</c:otherwise>--%>
            <%--</c:choose>--%>
            <%-- 고등학교 졸업/검정고시 동적 변경 시작 --%>

            <%-- 최종 대학 체크 처리 시작 --%>
            $('.radio-group').on('click', function(e) {
                var $target = $(this);
                var $container = $target.parents('.form-group-block-list');
                $container.find('.radio-group').each(function() {
                    $(this).attr('checked', $target[0] === $(this)[0]);
                });
            });

            function mustCheckedOneRadio() {
                var list = document.querySelectorAll('.form-group-block-list'), i, j, radioGroup, checkedCount = 0;
                for (i = 0; i < list.length; i++) {
                    radioGroup = list[i].querySelectorAll('.radio-group');
                    if (radioGroup && radioGroup.length > 0) {
                        for (j = 0; j < radioGroup.length; j++) {
                            if (radioGroup.checked) {
                                checkedCount++;
                            }
                        }
                        if (checkedCount == 0) {
                            radioGroup[0].checked = true;
                        }
                    }
                }
            };
            mustCheckedOneRadio();
            <%-- 최종 대학 체크 처리 끝 --%>

            <%-- 학연산 선택에 따른 화면 변경 시작 --%>
            $('#applAttrCode').on('change', changeApplAttrCode);

            function changeApplAttrCode() {
                var index = $('#applAttrCode').find('option').filter(':selected').index() + 1;
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
                            if(e.result && e.result === 'SUCCESS') {
                                var $target = $('#' + targetId);
                                var data = JSON && JSON.parse(e.data) || $.parseJSON(e.data);
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


                if (detlMajCode == '99999') {   // 세부전공 직접입력
                    $('#DetlMajDesc').show('');
                    $('#DetlMajDesc').text('');
                }else{
                    $('#DetlMajDesc').hide('');
                    $('#DetlMajDesc').text('');
                }
                if ($(selected).attr('partTimeYn') === 'Y' || $(selected).attr('partTimeYn') === 'y') { // 세부전공 PART_TIME_YN이 Y인 경우
                    $('#DetlMajDesc').show('');
                    $('#DetlMajDesc').text('');
                }else{
                    $('#DetlMajDesc').hide('');
                    $('#DetlMajDesc').text('');
                }
                if (jQuery.type($(selected).attr('detlmajdesc')) !=='undefined') { // 세부전공 desc 가 들어가 있는경우
                    $('#DetlMajDesc').show('');
                    $('#DetlMajDesc').text('');
                }else{
                    $('#DetlMajDesc').hide('');
                    $('#DetlMajDesc').text('');
                }
            });

            function retreiveDetlMajDesc( adms, dept, applAttrCode, detlMajCode ) {
                    var msg;
                    var baseUrl = '${contextPath}/common/code?';

                    $.ajax({
                        type: 'GET',
                        url: baseUrl,
                        success: function(e) {
                            if(e.result && e.result === 'SUCCESS') {
                                var data = JSON && JSON.parse(e.data) || $.parseJSON(e.data);
                                msg = $(data);
                            }
                        },
                        error: function(e) {
                            if(console) console.log(e);
                        }
                    });
                return msg;
             }


            <%-- 세부전공 변경 시 어학 변경 --%>
            $('#detlMajCode').on('change', function(event) {
                updateLanguagePanel();
            });

            $('#detlMajCode').trigger('change');
            <%-- 지원사항 select 폼 change 이벤트 핸들러 등록 끝 --%>

            <%-- 어학 및 경력 탭 선택 시 어학 변경 시작 --%>
//            $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
//                var currTab = e.target; // 활성화된 탭
//                var prevTab = e.relatedTarget; // 이전 탭
//                var currPanel = $(currTab.getAttribute('href'))[0];
//                if (currPanel.getAttribute('id') === 'langcareer') {
//                    updateLanguagePanel();
//                }
//            });

            function updateLanguagePanel() {
                var detlMajCode = $('#detlMajCode').val();
                if (detlMajCode === '-' || detlMajCode === '') {
                    var groups = $('#english-score-list').children('.form-group');
                    for (var i = 0, len = groups.length; i < len; i++) {
                        updateLanguageGroup(groups[i], []);
                    }
                    return;
                }
                var baseUrl = '${contextPath}/common/code';
                var url;
                var admsNo = $('#admsNo').val();
                var applAttrCode = $('#applAttrCode').val();
                if (applAttrCode == '00001') {
                    baseUrl += '/general';
                    url = admsNo + '/' + $('#deptCode').val() + '/' + $('#corsTypeCode').val() + '/' + detlMajCode;
                } else if (applAttrCode == '00002') {
                    baseUrl += '/ariInst';
                    url = admsNo + '/' + $('#deptCode').val() + '/' + $('#ariInstCode').val() + '/' + $('#corsTypeCode').val() + '/' + detlMajCode;
                } else if (applAttrCode == '00003') {
                    baseUrl += '/general';
                    url = admsNo + '/' + $('#deptCode').val() + '/' + $('#corsTypeCode').val() + '/' + detlMajCode;
                }

                $.ajax({
                    type: 'GET',
                    url: baseUrl + '/engMdtYn/' + url,
                    success: function(e) {
                        if (e.result == 'SUCCESS') {
                            <%-- 학과면제인정 보이지 않게 처리 --%>
                            $('#forlExmpCode').children('option').each(function () {
                                if (this.value && this.value == '6') {
                                    $(this).hide(e.data == 'Y' || e.data == 'y');
                                }
                            });
                        }
                    },
                    error: function(e) {}
                });
                $.ajax({
                    type: 'GET',
                    url: baseUrl + "/availableEngExam/" + url,
                    success: function(e) {
                        if (e.result == 'SUCCESS') {
                            var data = JSON && JSON.parse(e.data) || $.parseJSON(e.data);
                            var groups = $('#english-score-list').children('.form-group');
                            for (var i = 0, len = groups.length; i < len; i++) {
                                updateLanguageGroup(groups[i], data);
                            }
                        }
                    },
                    error: function(e) {}
                })
            }

            <%-- 졸업구분의 졸업선택시 --%>
            $('.degr-radio').on('change', function(e) {
                var target =this;
                var parent = $(target).parents('.form-group')[0];
                var childRadioVal = $(parent).find("input[type=radio]:checked").val();
                <%-- 졸업인 경우  --%>
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

            <%-- checkbox hide/show --%>
            function updateLanguageGroup(group, data) {
                var langExamCode = $(group).find('input').filter('[name$="langExamCode"]')[0];
                var check = $(group).find('.btn-lang, .btn-lang-disabled')[0];
                var checkLabel = $(group).find('.lbl-lang')[0];
                if (check) {
                    var val = langExamCode ? langExamCode.value : null;
                    var isExist = false, item;
                    for (var j = 0, len = data.length; j < len; j++) {
                        item = data[j];
                        if (val == item['examCode']) {
                            if ('Y' == item['canYn'] || 'y' == item['canYn']) {
                                check.className = 'btn-lang';
                                check.removeAttribute('disabled');
                                isExist = true;
                                $(checkLabel).text('제출 가능');
                            }
                            break;
                        }
                    }
                    if (!isExist) {
                        check.className = 'btn-lang-disabled';
                        checkLabel.text ='제출 불가';
                        check.setAttribute('disabled', 'disabled');
                        $(group).removeClass('show-lang');
                        $(group).addClass('hide-lang');
                    }
                }
            }
            <%-- 어학 및 경력 탭 선택 시 어학 변경 끝 --%>

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
            <%-- 어학 체크박스 클릭 시 처리 끝 --%>

            <%-- 어학 선택 시 어학 증빙 파일 첨부 양식 처리 --%>
            $('.checkbox').on('change', function(e) {

                var childCheckbox = $(this).find("input[type=checkbox]"),
                    targetExamName = "#exam"+ $(this).find("label").text().trim();
                childCheckbox.is(":checked") ?
                        $(targetExamName).css("display", "block"):
                        $(targetExamName).css("display", "none");

            });

            <%-- 원서 수정 모드에서 어학 정보 유무에 따라 checkbox 이벤트 자동 발생 --%>
            var langArr = [];
            <c:forEach items="${entireApplication.applicationLanguageList}" var="item" varStatus="stat">
            langArr[${stat.index}] = '${item.langGrad}';
            </c:forEach>
            for ( var i = 0, len = langArr.length ; i < len ; i++ ) {
                langArr[i] != "" ? $('#checkLang'+i).prop('checked', true).triggerHandler('click') : $('#checkLang'+i).prop('checked', false);
            }
            <%-- 원서 수정 모드에서 어학 정보 유무에 따라 checkbox 이벤트 자동 발생 --%>

            <%-- 파일 선택 버튼 이벤트 --%>
            $('.btn-file').on('change', function (e) { // 한번 업로드한 inputfile은 이벤트가 발생 안한다.
                var target = e.target,
                    inputGroup = target.parentNode.parentNode,
                    uploadButton = $(inputGroup).find('input[type="button"]');
                $(uploadButton).removeClass('disabled');
                $(uploadButton).val('올리기');

            });
            <%-- 파일 선택 버튼 이벤트 --%>

            <%-- 파일 업로드 버튼 이벤트 --%>
            $('.btn-upload').on('click', function (e) {
                var actionUrl = "${contextPath}/application/apply/fileUpload",
                    fileInputId = e.target.parentNode.parentNode.querySelector('input').getAttribute('id'),
                    fileInput = document.getElementById(fileInputId),
                    fileInputName = fileInput.getAttribute("name"),
                    fileName = fileInput.value,
                    targetLabelId = e.target.parentNode.parentNode.querySelector('span').getAttribute('id'),
                    targetFilePathHiddenId = e.target.getAttribute('data-file-path'),
                    targetFileNameHiddenId = e.target.getAttribute('data-file-name'),
                    targetOrgFileNameHiddenId = e.target.getAttribute('data-org-file-name'),
                    regexpImage = (/\.(gif|jpg|png)$/i),
                    regexpPDF = (/\.(pdf)$/i),
                    extIsOk = false
                    ;
                if ((fileInput.files && fileInput.files.length) || fileInput.value != "") {
                    if (fileInputId === 'docGroupList[0].mandDocList[0].docName') {
                        if (regexpImage.test(fileName)) {
                            extIsOk = true;
                        } else {
                            alert('${msgImageOnly}');
                            return false;
                        }
                    } else if (regexpPDF.test(fileName)) {
                        extIsOk = true;
                    } else {
                        alert('${msgPDFOnly}')
                        return false;
                    }

                    if (extIsOk) {
                        $.ajaxFileUpload({
                            url: actionUrl,
                            secureuri:false,
                            fileElementId: fileInputId,
                            dataType: 'json',
                            data: {
                                fieldName: fileInputName,
                                targetButton: $(this).attr('id'),
                                targetLabel: targetLabelId,
                                applNo: document.getElementById('applNo').value,
                                admsNo: document.getElementById('admsNo').value
                            },
                            success: function (data, status) {
                                var d = JSON.parse(data.data);
//                                if (console) {
//                                    console.log("targetButton : ", d.targetButton);
//                                    console.log("targetLabel : ", d.targetLabel);
//                                    console.log("applNo : ", d.applNo);
//                                    console.log("admsNo : ", d.admsNo);
//                                    console.log("originalFileName : ", d.originalFileName);
//                                    console.log("filePath : ", d.path);
//                                    console.log("fileName : ", d.fileName);
//                                    console.log("data : ", data.data);
//                                    console.log("status : ", status);
//                                }
                                var targetBtnId = d.targetButton,
                                        targetBtn = document.getElementById(targetBtnId),
                                        $targetBtn = $(targetBtn),
                                        originalFileName = d.originalFileName,
                                        filePath = d.path,
                                        fileName = d.fileName,
                                        targetLabel = d.targetLabel,
                                        applNo = d.applNo,
                                        admsNo = d.admsNo,
                                        downloadURL,
                                        linkHtml;
                                $targetBtn.removeClass("btn-default");
                                $targetBtn.addClass("btn-info");
                                $targetBtn.val("올리기 성공");
                                downloadURL = '${contextPath}/filedownload/attached/'+admsNo+'/'+applNo+'/'+fileName+'/'+originalFileName;
                                linkHtml = '<a href="' + downloadURL + '">' + originalFileName + '</a>';
                                document.getElementById(targetLabel).innerHTML = linkHtml;
                                document.getElementById(targetFilePathHiddenId).value = filePath;
                                document.getElementById(targetFileNameHiddenId).value = fileName;
                                document.getElementById(targetOrgFileNameHiddenId).value = originalFileName;
                            },
                            error: function (data, status, e) {
                                if(console) {
                                    console.log("data : ", data);
                                    console.log("status : ", status);
                                    console.log("e : ", e);
                                }
                            }
                        });
                    }

                } else {
                    alert("파일을 선택해 주십시오");
                }


                return false;
            });
            <%-- 파일 업로드 버튼 이벤트 --%>

            <%-- 단어 잘림 방지 --%>
            $('.word-keep-all').wordBreakKeepAll();

        });

        $.fn.autoLink = function(){
            var regURL = new RegExp("(http|https|ftp|telnet|news|irc)://([-/.a-zA-Z0-9_~#%$?&=:200-377()]+)","gi");
            this.html(this.html().replace(regURL,"<a href='$1://$2' target='_blank'>$1://$2</a>"));
            return this;
        };

    </script>
</content>
</body>
</html>
