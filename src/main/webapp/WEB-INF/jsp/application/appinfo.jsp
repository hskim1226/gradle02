<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<%--// TODO 제3자 동의여부 : ${providePrivateInfo} - 0 : 동의, 1 : 비동의--%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${contextPath}/css/datepicker3.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
    <style>
        section.application {
            padding: 200px 0 60px;
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
        a:hover, a:visited, a:link {
            text-decoration: none;
            color: #fdfdfd;
        }
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
            cursor: default;
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
    </style>
    <%--body의 글자 속성을 #333333으로 강제 지정하여 Footer 글자가 안나옴, 꼭 필요하지 않으면 안쓰기로
    <link rel="stylesheet" href="${contextPath}/css/bootstrap-glyphicons.css" />--%>
</head>
<body>
<section class="application">
    <div id="alert-container"></div>
    <div class="container">
        <ul id="myTab" class="nav nav-tabs nav-justified tab-gray">
            <li><a href="#appinfo" data-toggle="tab">기본정보</a></li>
            <li><a href="#selfintro" data-toggle="tab">자기소개서</a></li>
            <li><a href="#studyplan" data-toggle="tab">학업 및 연구 계획서</a></li>
            <li><a href="#fileupload" data-toggle="tab">첨부파일</a></li>
        </ul>
        <form:form commandName="entireApplication" cssClass="form-horizontal" action="apply/saveandupload" method="post" enctype="multipart/form-data" role="form">
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
                                <div class="form-group">
                                    <label for="applAttrCode" class="col-sm-2 control-label">지원구분</label>
                                    <div class="col-sm-9">
                                        <form:select path="application.applAttrCode" id="applAttrCode" cssClass="form-control">
                                            <form:options items="${common.applAttrList}" itemValue="code" itemLabel="codeVal"/>
                                        </form:select>
                                    </div>
                                </div>
                                <div id="applyKindDynamic">
                                    <div class="form-group hidden-apply-kind-2">
                                        <label for="campCode" class="col-sm-2 control-label">캠퍼스</label>
                                        <div class="col-sm-3">
                                            <form:select path="campCode" cssClass="form-control">
                                                <form:option value="-" label="--선택--" />
                                                <form:options items="${common.campList}" itemValue="campCode" itemLabel="campName" />
                                            </form:select>
                                        </div>
                                        <label for="collCode" class="col-sm-2 control-label">대학</label>
                                        <div class="col-sm-4">
                                            <form:select path="collCode" cssClass="form-control">
                                                <form:option value="-" label="--선택--" />
                                                <form:options items="${common.collList}" itemValue="collCode" itemLabel="collName" />
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="form-group hidden-apply-kind-1 hidden-apply-kind-3">
                                        <label for="ariInstCode" class="col-sm-2 control-label">학·연·산 연구기관</label>
                                        <div class="col-sm-9">
                                            <form:select path="application.ariInstCode" id="ariInstCode" cssClass="form-control">
                                                <form:option value="-" label="--선택--" />
                                                <form:options items="${common.ariInstList}" itemValue="ariInstCode" itemLabel="ariInstName" />
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="deptCode" class="col-sm-2 control-label">지원학과</label>
                                        <div class="col-sm-9">
                                            <form:select path="application.deptCode" id="deptCode" cssClass="form-control">
                                                <form:option value="-" label="--선택--" />
                                                <form:options items="${common.deptList}" itemValue="deptCode" itemLabel="deptName" />
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="corsTypeCode" class="col-sm-2 control-label">지원과정</label>
                                        <div class="col-sm-9">
                                            <form:select path="application.corsTypeCode" id="corsTypeCode" cssClass="form-control">
                                                <form:option value="-" label="--선택--" />
                                                <form:options items="${common.corsTypeList}" itemValue="corsTypeCode" itemLabel="codeVal" />
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="detlMajCode" class="col-sm-2 control-label">세부전공</label>
                                        <div class="col-sm-9">
                                            <form:select path="application.detlMajCode" id="detlMajCode" cssClass="form-control">
                                                <form:option value="-" label="--선택--" />
                                                <form:options items="${common.detlMajList}" itemValue="detlMajCode" itemLabel="detlMajName" />
                                            </form:select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                                <div class="panel-heading">지원자 정보</div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <form:label path="application.korName" cssClass="col-sm-2 control-label">한글이름</form:label>
                                        <div class="col-sm-9">
                                            <form:input path="application.korName" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">영문성명</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">&nbsp;성&nbsp;</span>
                                                <form:input path="application.engSur" cssClass="col-sm-6 form-control" style="text-transform: uppercase;" />
                                            </div>
                                        </div>
                                        <div class="col-sm-offset-2 col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">이름</span>
                                                <form:input path="application.engName" cssClass="col-sm-6 form-control" style="text-transform: uppercase;" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label path="application.rgstNo" cssClass="col-sm-2 control-label">주민등록번호</form:label>
                                        <div class="col-sm-9">
                                            <form:input path="application.rgstNo" cssClass="form-control" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default" id="currentCompany" hidden>
                                <div class="panel-heading">현재 근무처</div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <form:label path="applicationGeneral.currWrkpName" cssClass="col-sm-2 control-label">회사이름</form:label>
                                        <div class="col-sm-9">
                                            <form:input path="applicationGeneral.currWrkpName" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label path="applicationGeneral.currWrkpDay" cssClass="col-sm-2 control-label">입사일</form:label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <form:input path="applicationGeneral.currWrkpDay" cssClass="col-sm-6 form-control" />
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
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">지원자 상세정보</div>
                                <div class="panel-body">
                                    <%--<div class="form-group">--%>
                                        <%--<form:label path="applicationGeneral.citzCntrCode" cssClass="col-sm-3 control-label">국적</form:label>--%>
                                        <%--<div class="col-sm-9">--%>
                                            <%--<div class="input-group">--%>
                                                <%--<form:input path="applicationGeneral.citzCntrCode" cssClass="form-control" />--%>
                                                <%--<span class="input-group-btn">--%>
                                                    <%--<button type="button" class="btn btn-default" id="search-citz-cntr-code">--%>
                                                        <%--<span class="glyphicon glyphicon-search"></span> 검색--%>
                                                    <%--</button>--%>
                                                <%--</span>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">장애사항</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">장애유형</span>
                                                <form:input path="applicationGeneral.hndcGrad" cssClass="col-sm-6 form-control" />
                                            </div>
                                        </div>
                                        <div class="col-sm-offset-2 col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">장애등급</span>
                                                <form:input path="applicationGeneral.hndcType" cssClass="col-sm-6 form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">병역사항</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">병역구분</span>
                                                <form:select path="applicationGeneral.mltrServCode" cssClass="form-control">
                                                    <form:option value="-" label="--선택--" />
                                                    <form:options items="${common.mltrServList}" itemValue="code" itemLabel="codeVal" />
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="col-sm-offset-2 col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">병역군별</span>
                                                <form:select path="applicationGeneral.mltrTypeCode" cssClass="form-control">
                                                    <form:option value="-" label="--선택--" />
                                                    <form:options items="${common.mltrTypeList}" itemValue="code" itemLabel="codeVal" />
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="col-sm-offset-2 col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">병역계급</span>
                                                <form:select path="applicationGeneral.mltrRankCode" cssClass="form-control">
                                                    <form:option value="-" label="--선택--" />
                                                    <form:options items="${common.mltrRankList}" itemValue="code" itemLabel="codeVal" />
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="col-sm-offset-2 col-sm-4 start-date-container">
                                            <div class="input-group date">
                                                <span class="input-group-addon">입대일자</span>
                                                <form:input path="applicationGeneral.mltrJoinDay" cssClass="form-control" readonly="true" />
                                            </div>
                                        </div>
                                        <div class="col-sm-4 end-date-container">
                                            <div class="input-group date">
                                                <span class="input-group-addon">제대일자</span>
                                                <form:input path="applicationGeneral.mltrDschDay" cssClass="form-control" readonly="true" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">지원자 연락처</div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">주소</label>
                                        <div class="col-sm-2">
                                            <button type="button" class="btn btn-default btn-block btn-search" id="searchAddress">우편번호 찾기</button>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="input-group">
                                                <input type="text" class="form-control" id="postcode1" />
                                                <span class="input-group-addon"> - </span>
                                                <input type="text" class="form-control" id="postcode2" />
                                            </div>
                                        </div>
                                        <form:hidden path="application.zipCode" />
                                        <div class="col-sm-offset-2 col-sm-4">
                                            <form:input path="application.addr" cssClass="form-control" id="address" />
                                        </div>
                                        <div class="col-sm-5">
                                            <form:input path="application.detlAddr" cssClass="form-control" id="addressDetail" placeholder="세부주소" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label path="application.telNum" cssClass="col-sm-2 control-label">전화번호</form:label>
                                        <div class="col-sm-9">
                                            <%--TODO--%>
                                            <%--<form:select path="telNumFirst" cssClass="form-control">--%>
                                                <%--<form:options items="${common.telNumFirst}" />--%>
                                            <%--</form:select> --%>
                                            <form:input path="application.telNum" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label path="application.mobiNum" cssClass="col-sm-2 control-label">휴대폰</form:label>
                                        <div class="col-sm-9">
                                            <%--TODO--%>
                                            <%--<form:select path="mobiNumFirst" cssClass="form-control">--%>
                                                <%--<form:options items="${common.telNumFirst}" />--%>
                                            <%--</form:select>--%>
                                            <form:input path="application.mobiNum" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
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
                                    <div class="form-group">
                                        <form:label path="applicationGeneral.emerContName" cssClass="col-sm-2 control-label">이름</form:label>
                                        <div class="col-sm-9">
                                            <form:input path="applicationGeneral.emerContName" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label path="applicationGeneral.emerContCode" cssClass="col-sm-2 control-label">관계</form:label>
                                        <div class="col-sm-9">
                                            <form:select path="applicationGeneral.emerContCode" cssClass="form-control">
                                                <form:option value="-" label="--선택--" />
                                                <form:options items="${common.emerContList}" itemValue="code" itemLabel="codeVal" />
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="form-group">
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
                            <%--<div class="panel panel-default">--%>
                                <%--<div class="panel-heading">고등학교</div>--%>
                                <%--<div class="panel-body">--%>
                                    <%--<div id="highschoolContainer">--%>
                                        <%--<div class="form-group">--%>
                                            <%--<label class="col-sm-2 control-label">졸업구분</label>--%>
                                            <%--<div class="col-sm-9">--%>
                                                <%--<label class="radio-inline"><form:radiobutton path="highSchool.acadTypeCode" id="highSchoolAcadTypeCode1" value="00001" />&nbsp;졸업</label>--%>
                                                <%--&nbsp;&nbsp;&nbsp;--%>
                                                <%--<label class="radio-inline"><form:radiobutton path="highSchool.acadTypeCode" id="highSchoolAcadTypeCode2" value="00005" />&nbsp;검정고시</label>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div id="highschoolDynamic">--%>
                                            <%--<div id="highschoolSubContainer1">--%>
                                                <%--<div class="form-group">--%>
                                                    <%--<label class="col-sm-2 control-label">재학기간</label>--%>
                                                    <%--<div class="col-sm-9">--%>
                                                        <%--<div class="input-daterange input-group">--%>
                                                            <%--<form:input path="highSchool.entrDay" cssClass="input-sm form-control" />--%>
                                                            <%--<span class="input-group-addon">to</span>--%>
                                                            <%--<form:input path="highSchool.grdaDay" cssClass="input-sm form-control" />--%>
                                                        <%--</div>--%>
                                                    <%--</div>--%>
                                                <%--</div>--%>
                                                <%--<div class="form-group">--%>
                                                    <%--<form:label path="highSchool.schlName" cssClass="col-sm-2 control-label">학교명</form:label>--%>
                                                    <%--<div class="col-sm-2">--%>
                                                        <%--<button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="highSchool.schlCode" data-targetNode2='highSchool.schlName' data-category="school-h">검색</button>--%>
                                                    <%--</div>--%>
                                                    <%--<div class="col-sm-7">--%>
                                                        <%--<form:hidden path="highSchool.schlCode" />--%>
                                                        <%--<form:input path="highSchool.schlName" cssClass="form-control" />--%>
                                                    <%--</div>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                            <%--<div id="highschoolSubContainer2">--%>
                                                <%--<div class="form-group">--%>
                                                    <%--<form:label path="highSchool.qualExamDay" cssClass="col-sm-2 control-label">검정고시합격일</form:label>--%>
                                                    <%--<div class="col-sm-9">--%>
                                                        <%--<div class="input-group date">--%>
                                                            <%--<span class="input-group-addon">합격일</span>--%>
                                                            <%--<form:input path="highSchool.qualExamDay" cssClass="form-control" />--%>
                                                        <%--</div>--%>
                                                    <%--</div>--%>
                                                <%--</div>--%>
                                                <%--<div class="form-group">--%>
                                                    <%--<label for="qualAreaName" class="col-sm-2 control-label">검정고시합격지구</label>--%>
                                                    <%--<div class="col-sm-9">--%>
                                                        <%--<form:select path="highSchool.qualAreaCode" id="qualAreaName" cssClass="form-control">--%>
                                                            <%--<form:option value="-" label="--선택--" />--%>
                                                            <%--<form:options items="${common.qualAreaList}" itemValue="code" itemLabel="codeVal" />--%>
                                                        <%--</form:select>--%>
                                                    <%--</div>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <div class="panel panel-default">
                                <div class="panel-heading">대학교</div>
                                <div class="panel-body">
                                    <div class="form-group-block-list">
                                        <c:forEach begin="0" end="${entireApplication.collegeList.size() > 0 ? entireApplication.collegeList.size() - 1 : 0}" varStatus="stat">
                                        <div class="form-group-block">
                                            <form:hidden path="collegeList[${stat.index}].acadTypeCode" value="00002" />
                                            <%--TODO 테이블에 국가이름 누락--%>
                                            <%--<div class="form-group">--%>
                                                <%--<form:label path="collegeList[${stat.index}]korCntrName" cssClass="col-sm-2 control-label">소재 국가</form:label>--%>
                                                <%--<div class="btn btn-default btn-md col-md-2 bpopperCntr">검색</div>--%>
                                                <%--<div class="col-sm-6">--%>
                                                    <%--<form:input path="collegeList[${stat.index}].korCntrName" cssClass="form-control" />--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                            <%--TODO 테이블에 국가이름 누락--%>
                                            <div class="form-group">
                                                <form:label path="collegeList[${stat.index}].schlName" cssClass="col-sm-2 control-label">학교 이름</form:label>
                                                <div class="col-sm-2">
                                                    <button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="collegeList${stat.index}.schlCode" data-targetNode2='collegeList${stat.index}.schlName' data-category="school-u">검색</button>
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
                                            <div class="form-group">
                                                <form:label path="collegeList[${stat.index}].collName" cssClass="col-sm-2 control-label">단과 대학</form:label>
                                                <div class="col-sm-9">
                                                    <form:input path="collegeList[${stat.index}].collName" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="collegeList[${stat.index}].majName" cssClass="col-sm-2 control-label">학과 이름</form:label>
                                                <div class="col-sm-9">
                                                    <form:input path="collegeList[${stat.index}].majName" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">재학 기간</label>
                                                <div class="col-sm-4 start-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">입학일</span>
                                                        <form:input path="collegeList[${stat.index}].entrDay" cssClass="form-control" readonly="true" />
                                                    </div>
                                                </div>
                                                <div class="col-sm-4 end-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">졸업(예정)일</span>
                                                        <form:input path="collegeList[${stat.index}].grdaDay" cssClass="form-control" readonly="true" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">평균 평점</label>
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
                                            <%--TODO 테이블에 국가이름 누락--%>
                                            <%--<div class="form-group">--%>
                                                <%--<form:label path="graduateList[${stat.index}]korCntrName" cssClass="col-sm-2 control-label">소재 국가</form:label>--%>
                                                <%--<div class="co-xs-6 col-sm-2">--%>
                                                    <%--<button type="button" class="btn btn-default">검색</button>--%>
                                                <%--</div>--%>
                                                <%--<div class="col-sm-6">--%>
                                                    <%--<form:input path="graduateList[${stat.index}].korCntrName" cssClass="form-control" />--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                            <%--TODO 테이블에 국가이름 누락--%>
                                            <div class="form-group">
                                                <form:label path="graduateList[${stat.index}].schlName" cssClass="col-sm-2 control-label">학교 이름</form:label>
                                                <div class="col-sm-2">
                                                    <button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="graduateList${stat.index}.schlCode" data-targetNode2='graduateList${stat.index}.schlName' data-category="school-g">검색</button>
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
                                            <div class="form-group">
                                                <form:label path="graduateList[${stat.index}].collName" cssClass="col-sm-2 control-label">단과 대학</form:label>
                                                <div class="col-sm-9">
                                                    <form:input path="graduateList[${stat.index}].collName" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="graduateList[${stat.index}].majName" cssClass="col-sm-2 control-label">학과 이름</form:label>
                                                <div class="col-sm-9">
                                                    <form:input path="graduateList[${stat.index}].majName" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">재학 기간</label>
                                                <div class="col-sm-4 start-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">입학일</span>
                                                        <form:input path="graduateList[${stat.index}].entrDay" cssClass="form-control" readonly="true" />
                                                    </div>
                                                </div>
                                                <div class="col-sm-4 end-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">졸업(예정)일</span>
                                                        <form:input path="graduateList[${stat.index}].grdaDay" cssClass="form-control" readonly="true" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
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
                            <div class="panel panel-default">
                                <div class="panel-heading">어학성적</div>
                                <div class="panel-body" id="english-score-list">
                                    <c:forEach items="${common.langExamList}" var="langExam" varStatus="stat">
                                    <div class="form-group">
                                        <c:if test="${stat.index == 0}">
                                        <label class="col-sm-2 control-label">영어</label>
                                        <div class="col-sm-2">
                                        </c:if>
                                        <c:if test="${stat.index != 0}">
                                        <div class="col-sm-offset-2 col-sm-2">
                                        </c:if>
                                            <input type="hidden" name="applicationLanguageList[${stat.index}].langExamCode" id="applicationLanguageList${stat.index}.langExamCode" value="${langExam.examCode}" />
                                            <div class="checkbox">
                                                <label for="checkLang${stat.index}">
                                                    <input type="checkbox" class="btn-lang" id="checkLang${stat.index}" <c:if test="${entireApplication.applicationLanguageList[stat.index] != null}">checked</c:if>/>${langExam.examName}
                                                </label>
                                            </div>
                                        </div>
                                        <c:if test="${langExam.examCode == '00001'}">
                                        <div class="col-sm-2">
                                            <form:select path="applicationLanguageList[${stat.index}].toflTypeCode" cssClass="form-control">
                                                <form:option value="-" label="--선택--" />
                                                <form:options items="${common.toflTypeList}" itemValue="code" itemLabel="codeVal" />
                                            </form:select>
                                        </div>
                                        </c:if>
                                        <c:if test="${langExam.examCode != '00001'}">
                                        <div class="col-sm-2"></div>
                                        </c:if>
                                        <div class="col-sm-3">
                                            <div class="input-group date">
                                                <span class="input-group-addon">시험일</span>
                                                <form:input path="applicationLanguageList[${stat.index}].examDay" cssClass="form-control" />
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <div class="input-group">
                                                <span class="input-group-addon">점수</span>
                                                <form:input path="applicationLanguageList[${stat.index}].langGrad" cssClass="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    </c:forEach>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-4">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" />외국어 성적 면제 해당자
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <form:select path="applicationGeneral.forlExmpCode" cssClass="form-control" items="${common.fornExmpList}" itemValue="code" itemLabel="codeVal" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">경력사항</div>
                                <div class="panel-body">
                                    <div id="career-container" class="form-group-block-list">
                                        <c:forEach varStatus="stat" begin="0" end="${entireApplication.applicationExperienceList.size() > 0 ? entireApplication.applicationExperienceList.size() - 1 : 0}">
                                        <div id="career-info" class="form-group-block">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">재직 기간</label>
                                                <div class="col-sm-4 start-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">입사일</span>
                                                        <form:input path="applicationExperienceList[${stat.index}].joinDay" cssClass="form-control" readonly="true" />
                                                    </div>
                                                </div>
                                                <div class="col-sm-4 end-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">퇴사일</span>
                                                        <form:input path="applicationExperienceList[${stat.index}].retrDay" cssClass="form-control" readonly="true" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="applicationExperienceList[${stat.index}].corpName" cssClass="col-sm-2 control-label">기관명</form:label>
                                                <div class="col-sm-9">
                                                    <form:input path="applicationExperienceList[${stat.index}].corpName" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group">
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
                    <div class="spacer-tiny"></div>
                </div>
                <%--selfintro--%>
                <div class="tab-pane fade" id="selfintro">
                    <div class="spacer-tiny"></div>
                    <div class="row">
                        <div class="col-sm-11">
                            <div class="form-group">
                                <form:label path="applicationETCWithBLOBs.covLett" cssClass="col-sm-2 control-label">자기소개</form:label>
                                <div class="col-sm-10">
                                    <form:textarea path="applicationETCWithBLOBs.covLett" cssClass="form-control" rows="24" placeholder="성격의 장단점과 특기 등 본인을 잘 설명할 수 있는 내용을 작성해주세요" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="spacer-tiny"></div>
                </div>
                <%--studyplan--%>
                <div class="tab-pane fade" id="studyplan">
                    <div class="spacer-tiny"></div>
                    <div class="row">
                        <div class="col-sm-11">
                            <div class="form-group">
                                <form:label path="applicationETCWithBLOBs.studPlan" cssClass="col-sm-2 control-label">학업 및 연구계획서</form:label>
                                <div class="col-sm-10">
                                    <form:textarea path="applicationETCWithBLOBs.studPlan" cssClass="form-control" rows="24" placeholder="희망 연구 분야와 연구 계획을 작성해주세요" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="spacer-tiny"></div>
                </div>

                <div class="tab-pane fade" id="fileupload">
                    <div class="spacer-tiny"></div>
                    <div class="row">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="panel panel-darkgray">
                                <div class="panel-heading">사진 업로드</div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">파일 선택</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                    <span class="btn btn-default btn_lg btn-file">
                                                        Browse&hellip; <input type="file" id="fuPicture" name="picture"/>
                                                    </span>
                                                </span>
                                                <span class="col-sm-8 nopadding"><input type="text" class="form-control" readonly/></span>
                                                <span class="col-sm-4 nopadding"><input type="button" id="btnPicture" class="btn btn-default btn-block btn-upload" value="올리기"/></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="spacer-tiny"></div>

                            <div class="panel panel-darkgray">
                                <div class="panel-heading">학력 관련 서류 업로드</div>
                                <div class="panel-body">
                                    <div class="form-group-block-list" id="fuCollegeDocBlockList">
                                        <div class="form-group-block">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">졸업(예정)증명서</label>
                                                <div class="col-sm-9">
                                                    <div class="input-group">
                                                        <div class="input-group-btn">
                                                            <input type="file" class="btn btn_lg btn-file" id="collegeDiploma0.file" name="collegeDiploma[0].file"/>
                                                        </div>
                                                        <div class="col-sm-4 nopadding"><input type="button" id="collegeDiploma0.btn" name="collegeDiploma[0].btn" class="btn btn-default btn-block btn-upload" value="올리기"/></div>
                                                        <label style="vertical-align: middle;">업로드된파일명</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">성적증명서</label>
                                                <div class="col-sm-9">
                                                    <div class="input-group">
                                                        <div class="input-group-btn">
                                                            <input type="file" class="btn btn_lg btn-file" id="collegeGrade0.file" name="collegeGrade[0].file"/>
                                                        </div>
                                                        <div class="col-sm-4 nopadding"><input type="button" id="collegeGrade0.btn" name="collegeGrade[0].btn" class="btn btn-default btn-block btn-upload" value="올리기"/></div>
                                                        <label style="vertical-align: middle;">업로드된파일명</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group block-china">
                                                <label class="col-sm-2 control-label">학력조회 동의서</label>
                                                <div class="col-sm-9">
                                                    <div class="input-group">
                                                        <div class="input-group-btn">
                                                            <input type="file" class="btn btn_lg btn-file" id="retrieveGradeAgree0.file" name="retrieveGradeAgree[0].file"/>
                                                        </div>
                                                        <div class="col-sm-4 nopadding"><input type="button" id="retrieveGradeAgree0.btn" name="retrieveGradeAgree[0].btn" class="btn btn-default btn-block btn-upload" value="올리기"/></div>
                                                        <label style="vertical-align: middle;">업로드된파일명</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group block-foreign-common">
                                                <label class="col-sm-2 control-label">출신대학(원) 확인서</label>
                                                <div class="col-sm-9">
                                                    <div class="input-group">
                                                        <div class="input-group-btn">
                                                            <input type="file" class="btn btn_lg btn-file" id="verifySchool0.file" name="verifySchool[0].file"/>
                                                        </div>
                                                        <div class="col-sm-4 nopadding"><input type="button" id="verifySchool0.btn" name="verifySchool[0].btn" class="btn btn-default btn-block btn-upload" value="올리기"/></div>
                                                        <label style="vertical-align: middle;">업로드된파일명</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group block-non-china">
                                                <label class="col-sm-2 control-label">학위증명서 원본</label>
                                                <div class="col-sm-9">
                                                    <div class="input-group">
                                                        <div class="input-group-btn">
                                                            <input type="file" class="btn btn_lg btn-file" id="originDegree0.file" name="originDegree[0].file"/>
                                                        </div>
                                                        <div class="col-sm-4 nopadding"><input type="button" id="originDegree0.btn" name="originDegree[0].btn" class="btn btn-default btn-block btn-upload" value="올리기"/></div>
                                                        <label style="vertical-align: middle;">업로드된파일명</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group block-non-china">
                                                <label class="col-sm-2 control-label">학위증 사본</label>
                                                <div class="col-sm-9">
                                                    <div class="input-group">
                                                        <div class="input-group-btn">
                                                            <input type="file" class="btn btn_lg btn-file" id="copyDegree0.file" name="copyDegree[0].file"/>
                                                        </div>
                                                        <div class="col-sm-4 nopadding"><input type="button" id="copyDegree0.btn" name="copyDegree[0].btn" class="btn btn-default btn-block btn-upload" value="올리기"/></div>
                                                        <label style="vertical-align: middle;">업로드된파일명</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="spacer-tiny"></div>

                            <div class="panel panel-darkgray">
                                <div class="panel-heading">어학 관련 서류 업로드</div>
                                <div class="panel-body">
                                    <div class="form-group engExam" id="examTOEFL">
                                        <label class="col-sm-2 control-label">TOEFL</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                    <span class="btn btn-default btn_lg btn-file">
                                                        Browse&hellip; <input type="file" name="fileTOEFL"/>
                                                    </span>
                                                </span>
                                                <input type="text" class="form-control" readonly/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group engExam" id="examTOEIC">
                                        <label class="col-sm-2 control-label">TOEIC</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                    <span class="btn btn-default btn_lg btn-file">
                                                        Browse&hellip; <input type="file" name="fileTOEIC"/>
                                                    </span>
                                                </span>
                                                <input type="text" class="form-control" readonly/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group engExam" id="examTEPS">
                                        <label class="col-sm-2 control-label">TEPS</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                    <span class="btn btn-default btn_lg btn-file">
                                                        Browse&hellip; <input type="file" name="fileTEPS"/>
                                                    </span>
                                                </span>
                                                <input type="text" class="form-control" readonly/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group engExam" id="examIELTS">
                                        <label class="col-sm-2 control-label">IELTS</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                    <span class="btn btn-default btn_lg btn-file">
                                                        Browse&hellip; <input type="file" name="fileIELTS"/>
                                                    </span>
                                                </span>
                                                <input type="text" class="form-control" readonly/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group engExam" id="examGRE">
                                        <label class="col-sm-2 control-label">GRE</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                    <span class="btn btn-default btn_lg btn-file">
                                                        Browse&hellip; <input type="file" name="fileGRE"/>
                                                    </span>
                                                </span>
                                                <input type="text" class="form-control" readonly/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group engExam" id="examTOKIC">
                                        <label class="col-sm-2 control-label">TOKIC</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                    <span class="btn btn-default btn_lg btn-file">
                                                        Browse&hellip; <input type="file" name="fileTOKIC"/>
                                                    </span>
                                                </span>
                                                <input type="text" class="form-control" readonly/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="spacer-tiny"></div>

                            <div class="panel panel-darkgray">
                                <div class="panel-heading">지원 과정/학과별 제출 서류</div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">경력증명서</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                    <span class="btn btn-default btn_lg btn-file">
                                                        Browse&hellip; <input type="file" multiple/>
                                                    </span>
                                                </span>
                                                <input type="text" class="form-control" readonly/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">석사 학위 논문 초록</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                    <span class="btn btn-default btn_lg btn-file">
                                                        Browse&hellip; <input type="file" multiple/>
                                                    </span>
                                                </span>
                                                <input type="text" class="form-control" readonly/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">석사 학위 논문</label>
                                        <div class="col-sm-9 control-label" style="text-align: left;">
                                                <h4>학교로 원본을 제출해 주세요</h4>
                                        </div>
                                    </div>

                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="spacer-tiny"></div>
                </div>
            </div> <%--myTabContent--%>
        </form:form>

        <div class="btn-group btn-group-justified">
            <div class="btn-group">
                <button id="save" type="button" class="btn btn-info btn-lg">저장</button>
            </div>
            <div class="btn-group">
                <button id="appply" type="button" class="btn btn-primary btn-lg">작성완료</button>
            </div>
            <div class="btn-group">
                <button id="reset" type="button" class="btn btn-warning btn-lg">되돌리기</button>
            </div>
        </div>
    </div> <%--container--%>

    <%-- 학교 검색 팝업 --%>
    <div id="bpopContainerSchool" class="bpopContainer">
        <span class="button b-close"><span>X</span></span>
        <div id="bpopContentSchool">
            <div class="form-group">
                <label id="searchTitle"></label>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <input type="text" id="bpopSchl" name="schl" class="form-control ime-mode-kr" />
                </div>
                <button id="bpopBtnSearchSchool" class="btn btn-info col-sm-2">검색</button>
            </div>
            <div class="form-group">
                <div class="col-sm-12" style="overflow-y: auto; height: 300px;">
                    <table class="table table-stripped">
                        <thead>
                        <tr>
                            <th>&nbsp;</th>
                            <th>학교 이름</th>
                        </tr>
                        </thead>
                        <tbody id="bpopResultSchool">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="targetNode1" />
    <input type="hidden" id="targetNode2" />
    <input type="hidden" id="targetNode3" />
    <%-- 학교 검색 팝업 --%>

    <%-- 다음 주소 검색 팝업 --%>
    <div id="postLayer" style="display:none;border:5px solid;position:fixed;width:300px;height:460px;left:50%;margin-left:-155px;top:50%;margin-top:-235px;overflow:hidden;-webkit-overflow-scrolling:touch;z-index:2;background-color:#fff;color: #111;">
        <img src="${contextPath}/img/user/addr-close.png" id="btnClosePostLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px" alt="닫기 버튼">
    </div>

</section>
<content tag="local-script">
    <%--<script src="${contextPath}/js/postcode.js"></script>--%>
    <script src="http://dmaps.daum.net/map_js_init/postcode.js"></script>
    <%--<script src="${contextPath}/js/bootstrap-datepicker.js"></script>--%>
    <%--<script src="${contextPath}/js/bootstrap-datepicker.kr.js"></script>--%>
    <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {

            <%-- 학교 검색 시작 --%>
            $('.bpopper').on('click', function(e) {
                e.preventDefault();
                $('#bpopResultSchool').empty();
                document.getElementById('bpopSchl').value="";

                var dataCategory = this.getAttribute('data-category');
                var title = null;
                document.getElementById('targetNode1').value = $(this).attr('data-targetNode1');
                document.getElementById('targetNode2').value = $(this).attr('data-targetNode2');
                if (dataCategory === 'school-h') {
                    document.getElementById('bpopContentSchool').setAttribute('data-category', 'H');
                    title = '고등학교 검색';
                } else if (dataCategory === 'school-u') {
                    document.getElementById('bpopContentSchool').setAttribute('data-category', 'U');
                    title = '대학교 검색';
                } else if (dataCategory === 'school-g') {
                    document.getElementById('bpopContentSchool').setAttribute('data-category', 'U');
                    title = '대학원 검색';
                } else {
                }

                if (title != null) {
                    $('#bpopContentSchool').find('#searchTitle').text(title);
                }
                $('#bpopContainerSchool').bPopup();
                document.getElementById('bpopSchl').focus();
            });

            $('#bpopBtnSearchSchool').on('click', function(e) {
                var c = $('#bpopContentSchool').attr('data-category');
                $('#bpopResultSchool').empty();
                $.ajax({
                    type: 'GET',
                    url: '${contextPath}/common/code/school/' + c + '/' + encodeURIComponent($('#bpopSchl').val()),
                    success: function(data) {

                        var obj = JSON.parse(data.data);

                        for ( i = 0, l = obj.length ; i < l ; i++ ) {
                            var record = $('<tr>'+'<td><span style="display: none;" class="b-close">'+obj[i].schlCode+'</span></td>'+'<td><span class="b-close">'+obj[i].schlName+'</span></td>'+'</tr>');
                            $('#bpopResultSchool').append(record);
                            $(record).on('click', function(e) {
                                var targetInputId = [ document.getElementById('targetNode1').value,
                                            document.getElementById('targetNode2').value ],
                                        tr = e.target.parentNode.parentNode;
                                for ( var i = 0 , len = tr.children.length, t0 ; i < len ; i++ ) {
                                    document.getElementById(targetInputId[i]).value = tr.children[i].firstChild.innerText;
                                }
                            });
                        }
                    }
                });
            });

            $('#bpopSchl').on('keyup', function(e) {
                if(e.keyCode == 13) {
                    $('#bpopBtnSearchSchool').trigger('click');
                }
            });
            <%-- 학교 검색 끝 --%>

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
                        document.getElementById('postcode1').value = data.postcode1;
                        document.getElementById('postcode2').value = data.postcode2;
                        document.getElementById('address').value = data.address;
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
            <%-- 다음 주소 검색 끝 -->

            <%-- 파일 업로드 시작 - 한 번 업로드 후 동작 안해서 안쓰기고 함--%>
            $('.btn-file :file').on('change', function() {
                var input = $(this),
                        numFiles = input.get(0).files ? input.get(0).files.length : 1,
                        label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
                input.trigger('fileselect', [numFiles, label]);
            });

            $('.btn-file :file').on('fileselect', function(event, numFiles, label) {
                var input = $(this).parents('.input-group').find(':text'),
                        log = numFiles > 1 ? numFiles + ' files selected' : label;

                if( input.length ) {
                    input.val(log);
                } else {
                    if( log ) alert(log);
                }

            });
            <%-- 사진 업로드 끝 --%>

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
            $('.input-group.date>input').each(function() {
                $(this).datepicker(datePickerOption);
            });
            $('.input-daterange>input').datepicker(datePickerOption);
            <%-- 달력 끝 --%>

            <%-- 처음 탭 표시 --%>
            $('#myTab a:first').tab('show');

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
                                    var regexp = /^([0-9]*(.)?([0-9])?)$/;
                                    if (!regexp.test(value)) {
                                        return {
                                            value: false,
                                            message: '${msgPhoneNo}'
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
                                    var regexp = /^([0-9]*(.)?([0-9])?)$/;
                                    if (!regexp.test(value)) {
                                        return {
                                            value: false,
                                            message: '${msgPhoneNo}'
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
                    }
                }
            });

            <%-- 지원정보 submit 이벤트 --%>
            $('#entireApplication').on('submit', function(event) {
                var $form = $(this),
                    $formUrl = $form.attr('action'),
                    $formData = $form.serializeArray();

                $form.find('input.radio-group').filter(function() {
                    return this.checked == false;
                }).each(function() {
                    $formData.push({name: this.name, value: 'N'});
                });

//                $formData = $formData.concat(getEnglishScoreSerializeArray());
                $.ajax({
                    url: $formUrl,
                    type: 'POST',
                    data: $formData,
                    timeout: 5000,
                    success: function (context) {
                        if (context.result == 'SUCCESS') {
                            var message = context.message;
                            var alert = createAlert(message);
                            $('#alert-container').append(alert);
                            window.setTimeout(function() { alert.alert('close') }, 2000);
                        }
                    },
                    error: function(e) {
                    }
                });
                event.preventDefault();
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

            $('#save').on('click', function() {
                $('#entireApplication').trigger('submit');
            });

            $('#apply').on('click', function() {
                alert("작성완료 되었습니다.")
            });

            $('#reset').on('click', function() {
                var $curPane = $('.tab-pane.active');
                var $curForm = $curPane.find('form');
                $curForm.each(function() {
                    this.reset();
                });
            });

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

                // 파일업로드 부분 제거
                var fileUploadContainer = document.getElementById(target.getAttribute("data-fileupload-block-list")),
                    indexOfBlockToRemove = target.getAttribute('data-block-index'),
                    blockToRemove = fileUploadContainer.children[indexOfBlockToRemove];
                fileUploadContainer.removeChild(blockToRemove);

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
                        var oldid = items[i].id;
                        prefix = name.substring(0, name.indexOf('['));
                        suffix = name.substring(name.indexOf(']') + 1, name.length);
                        items[i].name = prefix + '[' + index + ']' + suffix;
                        items[i].id = prefix + index + suffix;
                        label = block.querySelector('label[for="' + oldid + '"]');
                        if (label) {
                            label.setAttribute('for', items[i].id);
                        }
                    }
                }

                var removeBtn = block.querySelector('.btn-remove');
                if (removeBtn) {
                   removeBtn.setAttribute('data-block-index', index);
                }
console.log(removeBtn);

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
                var i, items;
                items = block.querySelectorAll('input, select');
                if (items) {
                    for (i = 0; i <items.length; i++) {
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
                                return this.value !== '-';
                            }).remove();
                            if (oldVal !== $clean.val()) {
                                $clean.trigger('change');
                            }
                        }
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
                                    for (var key in item) {
                                        if (key !== valueKey && key !== labelKey) {
                                            $op.attr(key, item[key]);
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
                            var admsNo = '${entireApplication.application.admsNo}';
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
                            var admsNo = '${entireApplication.application.admsNo}';
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
                            var admsNo = '${entireApplication.application.admsNo}';
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
                            var admsNo = '${entireApplication.application.admsNo}';
                            var applAttrCode = $('#applAttrCode').val();
                            if (applAttrCode == '00001') {
                                return '/general/detailMajor/' + admsNo + '/' + $('#deptCode').val() + '/' + arg;
                            } else if (applAttrCode == '00002') {
                                return '/ariInst/detailMajor/' + admsNo + "/" + $('#deptCode').val() + "/" + $('#ariInstCode').val() + '/' + arg;
                            } else if (applAttrCode == '00003') {
                                // nothing
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
                var $divNode, $childNode, $childNode2;

                $(parent).find('#detlMajRadio').remove();
                $(parent).find('#detlMajText').remove();
                if (detlMajCode.slice(0, 1) == '9') {   // 직전 학위과정의 학과명 입력
                    $divNode = $('<div/>').addClass('col-sm-offset-2 col-sm-9').attr({
                        'id': 'detlMajText'
                    });
                    $childNode = $('<input/>').addClass('form-control').attr({
                        'type': 'text',
                        'id': 'detlMajDesc',
                        'name': 'detlMajDesc'
                    });
                    $childNode.appendTo($divNode)
                    $divNode.appendTo($(parent));
                } else {
                    if ($(selected).attr('partTimeYn') === 'Y' || $(selected).attr('partTimeYn') === 'y') { // 세부전공 PART_TIME_YN이 Y인 경우
                        $divNode = $('<div/>').addClass('col-sm-offset-2 col-sm-9').attr({
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
                }
            });

            <%-- 세부전공 변경 시 어학 변경 --%>
            $('#detlMajCode').on('change', function(event) {
                var detlMajCode = this.options[this.selectedIndex].value;
                if (detlMajCode === '-') {
                    return;
                }
                var baseUrl = '${contextPath}/common/code';
                var url;
                var admsNo = $('#admsNo').val();;
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
                            $('#applicationGeneral\\.forlExmpCode').children('option').each(function () {
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
                            var langExamCode, item, val, check, isExist, cn;
                            for (var i = 0, len = groups.length; i < len; i++) {
                                langExamCode = $(groups[i]).find('input').filter('[name$="langExamCode"]')[0];
                                check = $(groups[i]).find('.btn-lang, .btn-lang-disabled')[0];
                                if (!check) {
                                    continue;
                                }
                                val = langExamCode ? langExamCode.value : null;
                                isExist = false;
                                for (var j = 0; j < data.length; j++) {
                                    item = data[j];
                                    if (val == item['examCode']) {
                                        if ('Y' == item['canYn'] || 'y' == item['canYn']) {
                                            check.className = 'btn-lang';
                                            check.removeAttribute('disabled');
                                            isExist = true;
                                        }
                                        break;
                                    }
                                }
                                if (!isExist) {
                                    check.className = 'btn-lang-disabled';
                                    check.setAttribute('disabled', 'disabled');
                                }
                            }
                        }
                    },
                    error: function(e) {}
                })
            });

            $('#detlMajCode').trigger('change');
            <%-- 지원사항 select 폼 change 이벤트 핸들러 등록 끝 --%>

            <%-- 어학 선택 시 어학 증빙 파일 첨부 양식 처리 --%>
            $('.checkbox').on('change', function(e) {

                var childCheckbox = $(this).find("input[type=checkbox]"),
                    targetExamName = "#exam"+ $(this).find("label").text().trim();
                childCheckbox.is(":checked") ?
                        $(targetExamName).css("display", "block") :
                        $(targetExamName).css("display", "none");

            });

            <%-- 파일 업로드 버튼 이벤트 --%>
            $('.btn-upload').on('click', function (e) {
                var actionUrl = "${contextPath}/application/apply/savetest",
                    fileInputId = e.target.parentNode.parentNode.querySelector('input').getAttribute('id'),
                    fileInput = document.getElementById(fileInputId),
                    fileInputName = fileInput.getAttribute("name")
                    ;
                if ((fileInput.files && fileInput.files.length) || fileInput.value != "") {
                    $.ajaxFileUpload({
                        url: actionUrl,
                        secureuri:false,
                        fileElementId:fileInputId,
                        dataType: 'json',
                        data: {fieldName: fileInputName,targetButton: $(this).attr('id')},
                        success: function (data, status) {
                            if (console) {
                                console.log("msg : ", data.message);
                                console.log("data : ", data.data);
                                console.log("status : ", status);
                            }
                            var targetBtnId = data.message,
                                    targetBtn = document.getElementById(targetBtnId),
                                    $targetBtn = $(targetBtn);
                            $targetBtn.removeClass("btn-default");
                            $targetBtn.addClass("btn-info");
                            $targetBtn.val("올리기 성공");
                        },
                        error: function (data, status, e) {
                            if(console) {
                                console.log("data : ", data);
                                console.log("status : ", status);
                                console.log("e : ", e);
                            }
                        }
                    });
                } else {
                    alert("파일을 선택해 주십시오");
                }


                return false;
            });
            <%-- 파일 업로드 버튼 이벤트 --%>
        });

    </script>
</content>
</body>
</html>
