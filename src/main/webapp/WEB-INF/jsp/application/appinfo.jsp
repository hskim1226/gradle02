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
        .btn-fill {
            width: 100%;
            white-space: normal;
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
    </style>
    <%--body의 글자 속성을 #333333으로 강제 지정하여 Footer 글자가 안나옴, 꼭 필요하지 않으면 안쓰기로
    <link rel="stylesheet" href="${contextPath}/css/bootstrap-glyphicons.css" />--%>
</head>
<body>
<section class="application">
    <div class="container">
        <ul id="myTab" class="nav nav-tabs nav-justified tab-gray">
            <li><a href="#appinfo" data-toggle="tab">기본정보</a></li>
            <li><a href="#selfintro" data-toggle="tab">자기소개서</a></li>
            <li><a href="#studyplan" data-toggle="tab">학업 및 연구 계획서</a></li>
            <li><a href="#fileupload" data-toggle="tab">첨부파일</a></li>
        </ul>
        <form:form commandName="entireApplication" cssClass="form-horizontal" action="apply/save" method="post" role="form">
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade" id="appinfo">
                    <div class="spacer-tiny"></div>
                    <div class="row">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="panel panel-default">
                                <div class="panel panel-default">
                                    <div class="panel-heading">지원 사항</div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label for="applAttrCode" class="col-sm-2 control-label">지원구분</label>
                                            <div class="col-sm-9">
                                                <form:select path="" id="applAttrCode" cssClass="form-control">
                                                    <form:options items="${applAttrList}" />
                                                </form:select>
                                            </div>
                                        </div>
                                        <div id="applyKindDynamic">
                                            <div class="form-group hidden-apply-kind-2">
                                                <label class="col-sm-2 control-label">캠퍼스</label>
                                                <div class="col-sm-3">
                                                    <select id="campCode" class="form-control">
                                                        <option value="-" label="--선택--" />
                                                    </select>
                                                </div>
                                                <label class="col-sm-2 control-label">대학</label>
                                                <div class="col-sm-4">
                                                    <select id="collCode" class="form-control">
                                                        <option value="-" label="--선택--" />
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group hidden-apply-kind-1 hidden-apply-kind-3">
                                                <label class="col-sm-2 control-label">학·연·산 연구기관</label>
                                                <div class="col-sm-9">
                                                    <select id="ariInstCode" class="form-control">
                                                        <option value="-" label="--선택--" />
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">지원학과</label>
                                                <div class="col-sm-9">
                                                    <select id="deptCode" class="form-control">
                                                        <option value="-" label="--선택--" />
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">지원과정</label>
                                                <div class="col-sm-9">
                                                    <select id="corsTypeCode" class="form-control">
                                                        <option value="-" label="--선택--" />
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">세부전공</label>
                                                <div class="col-sm-9">
                                                    <select id="detlMajCode" class="form-control">
                                                        <option value="-" label="--선택--" />
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-heading">지원자 정보</div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <form:label path="korName" cssClass="col-sm-2 control-label">한글이름</form:label>
                                        <div class="col-sm-9">
                                            <form:input path="korName" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">영문성명</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">&nbsp;성&nbsp;</span>
                                                <form:input path="engSur" cssClass="col-sm-6 form-control" />
                                            </div>
                                        </div>
                                        <div class="col-sm-offset-2 col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">이름</span>
                                                <form:input path="engName" cssClass="col-sm-6 form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label path="rgstNo" cssClass="col-sm-2 control-label">주민등록번호</form:label>
                                        <div class="col-sm-9">
                                            <form:input path="rgstNo" cssClass="form-control" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${application.deptCode == '086' || application.deptCode == '013'}">
                                <div class="panel panel-default">
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
                            </c:if>
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
                                                <form:input path="applicationGeneral.mltrJoinDay" cssClass="form-control" readonly="true" />
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
                                        <div class="col-sm-5">
                                            <div class="input-group">
                                                <input type="text" class="form-control" id="postcode1" />
                                                <span class="input-group-addon"> - </span>
                                                <input type="text" class="form-control" id="postcode2" />
                                            </div>
                                        </div>
                                        <input type="hidden" id="zipCode" name="zipCode"/>
                                        <div class="col-sm-4">
                                            <button type="button" class="btn btn-default" id="searchAddress">우편번호 찾기</button>
                                        </div>
                                        <div class="col-sm-offset-2 col-sm-4">
                                            <input type="text" class="form-control" id="address" name="addr"/>
                                        </div>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control" id="addressDetail" name="detlAddr" placeholder="세부주소">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label path="telNum" cssClass="col-sm-2 control-label">전화번호</form:label>
                                        <div class="col-sm-9">
                                            <%--TODO--%>
                                            <%--<form:select path="telNumFirst" cssClass="form-control">--%>
                                                <%--<form:options items="${common.telNumFirst}" />--%>
                                            <%--</form:select> --%>
                                            <form:input path="telNum" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label path="mobiNum" cssClass="col-sm-2 control-label">휴대폰</form:label>
                                        <div class="col-sm-9">
                                            <%--TODO--%>
                                            <%--<form:select path="mobiNumFirst" cssClass="form-control">--%>
                                                <%--<form:options items="${common.telNumFirst}" />--%>
                                            <%--</form:select>--%>
                                            <form:input path="mobiNum" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label path="mailAddr" cssClass="col-sm-2 control-label">E-mail</form:label>
                                        <div class="col-sm-9">
                                            <form:input path="mailAddr" cssClass="form-control" />
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
                            <div class="panel panel-default">
                                <div class="panel-heading">고등학교</div>
                                <div class="panel-body">
                                    <div id="highschoolContainer">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">졸업구분</label>
                                            <div class="col-sm-9">
                                                <div class="btn-group">
                                                    <label class="radio-inline">
                                                        <form:radiobutton path="highSchool.acadTypeCode" value="00001" />졸업
                                                    </label>
                                                    <label class="radio-inline">
                                                        <form:radiobutton path="highSchool.acadTypeCode" value="00005" />검정고시
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="highschoolDynamic">
                                            <div id="highschoolSubContainer1">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">재학기간</label>
                                                    <div class="col-sm-9">
                                                        <div class="input-daterange input-group">
                                                            <form:input path="highSchool.entrDay" cssClass="input-sm form-control" />
                                                            <span class="input-group-addon">to</span>
                                                            <form:input path="highSchool.grdaDay" cssClass="input-sm form-control" />
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <form:label path="highSchool.schlName" cssClass="col-sm-2 control-label">학교명</form:label>
                                                    <div class="col-sm-9">
                                                        <div class="input-group">
                                                            <span class="input-group-btn">
                                                                <button type="button" class="btn btn-default">검색</button>
                                                            </span>
                                                            <form:input path="highSchool.schlName" cssClass="form-control" />
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="highschoolSubContainer2">
                                                <div class="form-group">
                                                    <form:label path="highSchool.qualExamDay" cssClass="col-sm-2 control-label">검정고시합격일</form:label>
                                                    <div class="col-sm-9">
                                                        <div class="input-group date">
                                                            <span class="input-group-addon">합격일</span>
                                                            <form:input path="highSchool.qualExamDay" cssClass="form-control" />
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <form:label path="highSchool.schlName" cssClass="col-sm-2 control-label">검정고시합격지구</form:label>
                                                    <div class="col-sm-9">
                                                        <div class="input-group">
                                                        <span class="input-group-btn">
                                                            <button type="button" class="btn btn-default">검색</button>
                                                        </span>
                                                            <form:input path="highSchool.schlName" cssClass="form-control" />
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">대학교</div>
                                <div class="panel-body">
                                    <div class="form-group-block-list">
                                        <c:forEach begin="0" end="${collegeList.size() > 0 ? collegeList.size()-1 : collegeList.size()}" varStatus="stat">
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
                                                <div class="btn btn-default btn-md col-md-2">검색</div>
                                                <div class="col-sm-4">
                                                    <form:input path="collegeList[${stat.index}].schlName" cssClass="form-control" />
                                                </div>
                                                <div class="col-sm-2">
                                                    <label class="radio inline">
                                                        <form:radiobutton path="collegeList[${stat.index}].lastSchlYn" value="y" />&nbsp;&nbsp;최종 학교
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="collegeList[${stat.index}].collName" cssClass="col-sm-2 control-label">단과 대학</form:label>
                                                <div class="col-sm-8">
                                                    <form:input path="collegeList[${stat.index}].collName" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="collegeList[${stat.index}].majName" cssClass="col-sm-2 control-label">학과 이름</form:label>
                                                <div class="col-sm-8">
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
                                                        <span class="input-group-addon">졸업일</span>
                                                        <form:input path="collegeList[${stat.index}].grdaDay" cssClass="form-control" readonly="true" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">평균 평점</label>
                                                <div class="col-sm-2">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">평점</span>
                                                        <form:input path="collegeList[${stat.index}].gradAvr" cssClass="form-control" />
                                                    </div>
                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">만점</span>
                                                        <form:input path="collegeList[${stat.index}].gradFull" cssClass="form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="btn btn-remove"><button type="button" class="close" aria-hidden="true">×</button></div>
                                        </div>
                                        </c:forEach>
                                        <div class="btn btn-info btn-add">추가</div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">대학원</div>
                                <div class="panel-body">
                                    <div class="form-group-block-list">
                                        <c:forEach begin="0" end="${graduateList.size() > 0 ? graduateList.size()-1 : graduateList.size()}" varStatus="stat">
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
                                                <div class="btn btn-default btn-md col-md-2">검색</div>
                                                <div class="col-sm-4">
                                                    <form:input path="graduateList[${stat.index}].schlName" cssClass="form-control" />
                                                </div>
                                                <div class="col-sm-2">
                                                    <label class="radio inline">
                                                        <form:radiobutton path="graduateList[${stat.index}].lastSchlYn" value="y" />&nbsp;&nbsp;최종 학교
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="graduateList[${stat.index}].collName" cssClass="col-sm-2 control-label">단과 대학</form:label>
                                                <div class="col-sm-8">
                                                    <form:input path="graduateList[${stat.index}].collName" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="graduateList[${stat.index}].majName" cssClass="col-sm-2 control-label">학과 이름</form:label>
                                                <div class="col-sm-8">
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
                                                        <span class="input-group-addon">졸업일</span>
                                                        <form:input path="graduateList[${stat.index}].grdaDay" cssClass="form-control" readonly="true" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">평균 평점</label>
                                                <div class="col-sm-2">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">평점</span>
                                                        <form:input path="graduateList[${stat.index}].gradAvr" cssClass="form-control" />
                                                    </div>
                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">만점</span>
                                                        <form:input path="graduateList[${stat.index}].gradFull" cssClass="form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="btn btn-remove"><button type="button" class="close" aria-hidden="true">×</button></div>
                                        </div>
                                        </c:forEach>
                                        <div class="btn btn-info btn-add">추가</div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">어학성적</div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">영어</label>
                                        <div class="col-sm-3">
                                            <div class="input-group">
                                                <span class="input-group-addon"><input type="checkbox" />TOEFL</span>
                                                <select class="form-control">
                                                    <option value="-" label="--선택--" />
                                                    <option value="cbt" label="CBT" />
                                                    <option value="ibt" label="IBT" />
                                                    <option value="pbt" label="PBT" />
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="input-group">
                                                <span class="input-group-addon">시험일</span>
                                                <input type="text" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="input-group">
                                                <span class="input-group-addon">점수</span>
                                                <input type="text" class="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-3">
                                            <div class="input-group">
                                                <span class="input-group-addon">
                                                    <span class="pull-left"><input type="checkbox" />TOEIC</span>
                                                </span>
                                                <span class="input-group-addon"></span>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="input-group">
                                                <span class="input-group-addon">시험일</span>
                                                <input type="text" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="input-group">
                                                <span class="input-group-addon">점수</span>
                                                <input type="text" class="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-3">
                                            <div class="input-group">
                                                <span class="input-group-addon"><input type="checkbox" />TEPS</span>
                                                <select class="form-control">
                                                    <option value="-" label="--선택--" />
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="input-group">
                                                <span class="input-group-addon">시험일</span>
                                                <input type="text" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="input-group">
                                                <span class="input-group-addon">점수</span>
                                                <input type="text" class="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-4">
                                            <div class="checkbox">
                                                <label><input type="checkbox" />GRE</label>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="input-group">
                                                <span class="input-group-addon">시험일</span>
                                                <input type="text" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <div class="input-group">
                                                <span class="input-group-addon">점수</span>
                                                <input type="text" class="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-4">
                                            <div class="checkbox" style="float:left;">
                                                <label><input type="checkbox" />IELTS</label>
                                            </div>
                                            <div style="width:70%; float:right;">
                                                <select class="form-control">
                                                    <option value="-" label="--선택--" />
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="input-group">
                                                <span class="input-group-addon">시험일</span>
                                                <input type="text" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <div class="input-group">
                                                <span class="input-group-addon">점수</span>
                                                <input type="text" class="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-9">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" />외국어 성적 면제 해당자
                                                </label>
                                            </div>
                                            <div class="well">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="reasonOption" id="reasonOption1" />옵션 1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="reasonOption" id="reasonOption2" />옵션 2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="reasonOption" id="reasonOption3" />옵션 3
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">경력사항</div>
                                <div class="panel-body">
                                    <div id="career-container" class="form-group-block-list">
                                        <c:forEach varStatus="stat" begin="0" end="${applicationExperienceList.size() > 0 ? applicationExperienceList.size()-1 : applicationExperienceList.size()}">
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
                                                        Browse&hellip; <input type="file" name="" multiple>
                                                    </span>
                                                </span>
                                                <input type="text" class="form-control" readonly>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="spacer-tiny"></div>

                            <div class="panel panel-darkgray">
                                <div class="panel-heading">학력 관련 서류 업로드</div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">졸업(예정)증명서</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                    <span class="btn btn-default btn_lg btn-file">
                                                        Browse&hellip; <input type="file" multiple>
                                                    </span>
                                                </span>
                                                <input type="text" class="form-control" readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">성적증명서</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                    <span class="btn btn-default btn_lg btn-file">
                                                        Browse&hellip; <input type="file" multiple>
                                                    </span>
                                                </span>
                                                <input type="text" class="form-control" readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">성적증명서</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                    <span class="input-group-btn">
                                                        <span class="btn btn-default btn_lg btn-file">
                                                            Browse&hellip; <input type="file" multiple>
                                                        </span>
                                                    </span>
                                                <input type="text" class="form-control" readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">학력조회 동의서</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                    <span class="input-group-btn">
                                                        <span class="btn btn-default btn_lg btn-file">
                                                            Browse&hellip; <input type="file" multiple>
                                                        </span>
                                                    </span>
                                                <input type="text" class="form-control" readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">출신대학(원) 확인서</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                    <span class="input-group-btn">
                                                        <span class="btn btn-default btn_lg btn-file">
                                                            Browse&hellip; <input type="file" multiple>
                                                        </span>
                                                    </span>
                                                <input type="text" class="form-control" readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">출신대학(원) 확인서</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                    <span class="input-group-btn">
                                                        <span class="btn btn-default btn_lg btn-file">
                                                            Browse&hellip; <input type="file" multiple>
                                                        </span>
                                                    </span>
                                                <input type="text" class="form-control" readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">학위증명서 원본</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                    <span class="input-group-btn">
                                                        <span class="btn btn-default btn_lg btn-file">
                                                            Browse&hellip; <input type="file" multiple>
                                                        </span>
                                                    </span>
                                                <input type="text" class="form-control" readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">학위증 사본</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                    <span class="input-group-btn">
                                                        <span class="btn btn-default btn_lg btn-file">
                                                            Browse&hellip; <input type="file" multiple>
                                                        </span>
                                                    </span>
                                                <input type="text" class="form-control" readonly>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="spacer-tiny"></div>

                            <div class="panel panel-darkgray">
                                <div class="panel-heading">어학 관련 서류 업로드</div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">TOEFL</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                    <span class="btn btn-default btn_lg btn-file">
                                                        Browse&hellip; <input type="file" multiple>
                                                    </span>
                                                </span>
                                                <input type="text" class="form-control" readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">GRE</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                    <span class="btn btn-default btn_lg btn-file">
                                                        Browse&hellip; <input type="file" multiple>
                                                    </span>
                                                </span>
                                                <input type="text" class="form-control" readonly>
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
                                                        Browse&hellip; <input type="file" multiple>
                                                    </span>
                                                </span>
                                                <input type="text" class="form-control" readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">석사 학위 논문 초록</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                    <span class="btn btn-default btn_lg btn-file">
                                                        Browse&hellip; <input type="file" multiple>
                                                    </span>
                                                </span>
                                                <input type="text" class="form-control" readonly>
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
        <div id="postLayer" style="display:none;border:3px solid;position:fixed;width:300px;height:460px;left:50%;margin-left:-155px;top:50%;margin-top:-235px;overflow:hidden;-webkit-overflow-scrolling:touch;">
            <img src="${contextPath}/img/user/addr-close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px" alt="닫기 버튼">
        </div>
    </div> <%--container--%>

    <%--TODO 국가 검색 팝업 --%>
    <div id="bpopContainerCountry" class="bpopContainer">
        <span class="button b-close"><span>X</span></span>
        <div id="bpopContentCountry">
            <div class="form-group">
                <div class="col-sm-10">
                    <input type="text" id="bpopCntr" name="cntr" class="form-control" />
                </div>
                <button id="bpopBtnSearchCountry" class="btn btn-info col-sm-2">검색</button>
            </div>
            <div>
                <table class="table table-stripped">
                    <thead>
                    <tr>
                        <th>&nbsp;</th>
                        <th>한글 이름</th>
                        <th>영문 이름</th>
                    </tr>
                    </thead>
                    <tbody id="bpopResultCountry">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <%--TODO 국가 검색 팝업 --%>

    <%--TODO 학교 검색 팝업 --%>
    <div id="bpopContainerSchool" class="bpopContainer">
        <span class="button b-close"><span>X</span></span>
        <div id="bpopContentSchool">
            <div class="form-group">
                <div class="col-sm-10">
                    <input type="text" id="bpopSchl" name="schl" class="form-control" />
                </div>
                <button id="bpopBtnSearchSchool" class="btn btn-info col-sm-2">검색</button>
            </div>
            <div>
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
    <input type="hidden" id="targetNode1" />
    <input type="hidden" id="targetNode2" />
    <input type="hidden" id="targetNode3" />
    <%--TODO 학교 검색 팝업 --%>

</section>
<content tag="local-script">
    <%--<script src="${contextPath}/js/postcode.js"></script>--%>
    <script src="http://dmaps.daum.net/map_js_init/postcode.js"></script>
    <%--<script src="${contextPath}/js/bootstrap-datepicker.js"></script>--%>
    <%--<script src="${contextPath}/js/bootstrap-datepicker.kr.js"></script>--%>
    <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {

            <%-- TODO 국가 검색 시작 --%>
            $('.bpopperCntr').on('click', function (e) {
                e.preventDefault();
                $('#bpopResultCountry').empty();
                document.getElementById('bpopCntr').value="";
                $(this).attr('data-category') === "country" ? (
                        document.getElementById('targetNode1').value = $(this).attr('data-targetNode1'),
                                document.getElementById('targetNode2').value = $(this).attr('data-targetNode2'),
                                document.getElementById('targetNode3').value = $(this).attr('data-targetNode3')
                        ) : (
                        document.getElementById('targetNode1').value = $(this).attr('data-targetNode1'),
                                document.getElementById('targetNode2').value = $(this).attr('data-targetNode2'),
                                document.getElementById('targetNode3').value = null
                        );
                $('#bpopContainerCountry').bPopup();
            });

            $('#bpopBtnSearchCountry').on('click', function() {

                $.ajax({
                    type: 'GET',
                    url: '${contextPath}/common/code/country/'+encodeURIComponent($('#bpopCntr').val()),
                    success: function(data) {

                        var obj = JSON.parse(data.data);

                        for ( i = 0, l = obj.length ; i < l ; i++ ) {
                            var record = $('<tr>'+'<td><span style="display: none;" class="b-close">'+obj[i].cntrCode+'</span></td>'+'<td><span class="b-close">'+obj[i].korCntrName+'</span></td>'+'<td><span class="b-close">'+obj[i].engCntrName+'</span></td>'+'</tr>');
                            $('#bpopResultCountry').append(record);
                            $(record).on('click', function(e) {
                                var targetInputId = [ document.getElementById('targetNode1').value,
                                            document.getElementById('targetNode2').value,
                                            document.getElementById('targetNode3').value ],
                                        tr = e.target.parentNode.parentNode;
                                for ( var i = 0 , len = tr.children.length, t0 ; i < len ; i++ ) {
                                    document.getElementById(targetInputId[i]).value = tr.children[i].firstChild.innerText;
                                }

                            });
                        }
                    }
                });
            });
            <%-- TODO 국가 검색 끝 --%>

            <%-- TODO 학교 검색 시작 --%>
            $('#bpopperSchl').on('click', function (e) {
                e.preventDefault();
                $('#bpopResultSchool').empty();
                document.getElementById('bpopSchl').value="";
                $(this).attr('data-category') === "school" ? (
                        document.getElementById('targetNode1').value = $(this).attr('data-targetNode1'),
                                document.getElementById('targetNode2').value = $(this).attr('data-targetNode2')
                        ) : (
                        document.getElementById('targetNode1').value = $(this).attr('data-targetNode1'),
                                document.getElementById('targetNode2').value = $(this).attr('data-targetNode2')
                        );
                $('#bpopContainerSchool').bPopup();
            });

            $('#bpopBtnSearchSchool').on('click', function() {

                $.ajax({
                    type: 'GET',
                    url: '${contextPath}/common/code/school/'+encodeURIComponent($('#bpopSchl').val()),
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
            <%-- TODO 학교 검색 끝 --%>

            var datePickerOption = {
//                format: "yyyymmdd",
//                startView: 2,
//                language: "kr",
//                forceParse: false,
//                autoclose: true
                dateFormat: 'yymmdd',
                monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                dayNamesMin: ['일','월','화','수','목','금','토'],
                changeMonth: true, //월변경가능
                changeYear: true, //년변경가능
                showMonthAfterYear: true //년 뒤에 월 표시
            };

            <%-- 달력 시작 --%>
//            $('#entrDay1, #grdaDay1').datepicker(datePickerOption);
            $('.input-group.date>input').each(function() {
                $(this).datepicker(datePickerOption);
            });
//            $('.input-group.date>input').datepicker(datePickerOption);
            $('.input-daterange>input').datepicker(datePickerOption);
            <%-- 달력 끝 --%>

            <%-- 처음 탭 표시 --%>
            $('#myTab a:first').tab('show');

            $('#save').on('click', function() {
//                var $curPane = $('.tab-pane.active');
//                var $curForm = $curPane.find('form');
                var $curForm = $('#entireApplication');
                console.log($curForm.serializeArray());
                $curForm.submit();
            });

            $('#apply').on('click', function() {
                alert("작성완료 되었습니다.")
            });

            $('reset').on('click', function() {
                var $curPane = $('.tab-pane.active');
                var $curForm = $curPane.find('form');
                $curForm.each(function() {
                    this.reset();
                });
            });

            <%-- 최종 대학 체크 처리 시작 --%>
            var setLastSchool = function (e) {
                var i, l, radioList = document.getElementsByClassName('lastSchl');
                for ( i = 0, l = radioList.length ; i < l ;i++ ) {
                    radioList[i].value="N";
                }
                e.target.value="Y";
            }
            $('.lastSchl').on('click', setLastSchool);
            <%-- 최종 대학 체크 처리 끝 --%>

            /*form-group-block 추가/삭제에 대한 처리 시작*/
            $('.btn-add').click(function(e) {
                var target = e.currentTarget ? e.currentTarget : e.target;
                var container = target.parentNode;
                while (container && !container.classList.contains('form-group-block-list')) {
                    container = container.parentNode;
                }
                var blocks = container.querySelectorAll('.form-group-block');
                var originBlock = blocks[blocks.length - 1];
                var $cloneObj;
                if (originBlock) {
                    $cloneObj = $(originBlock).clone(true);
                    $cloneObj.find('.input-group.date>input').datepicker('destroy');
//                    incrementChildren($cloneObj, blocks.length);
                    updateIdAndName($cloneObj[0], blocks.length);
                    container.insertBefore($cloneObj[0], originBlock.nextSibling);
                    $cloneObj.find('.input-group.date>input').datepicker(datePickerOption);
                }
            });

            $('.btn-remove').click(function(e) {
                var target = e.currentTarget ? e.currentTarget : e.target;
                var blockToRemove = target.parentNode;
                while (blockToRemove && !blockToRemove.classList.contains('form-group-block')) {
                    blockToRemove = blockToRemove.parentNode;
                }
                var container = blockToRemove.parentNode;
                var length = container.querySelectorAll('.form-group-block').length;
                var $cloneObj
                if (length <= 1) {
//                    eraseChildren(blockToRemove);
                } else {
                    blockToRemove.parentNode.removeChild(blockToRemove);
                }
            });

            function updateIdAndName( block, index ) {
                var i, name, prefix, suffix, input, items, label;
                var input = block.querySelector('input');

                name = input.name;
                prefix = name.substring(0, name.indexOf('['))

                items = block.querySelectorAll('input, select');
                if (items) {
                    for (i = 0; i <items.length; i++) {
                        name = items[i].name;
                        suffix = name.substring(name.lastIndexOf(']') + 1, name.length);
                        items[i].name = prefix + '[' + index + ']' + suffix;
                        items[i].id = prefix + index + suffix;

                        label = block.querySelector('label[for="' + name + '"]');
                        if (label) {
                            label.for = items[i].id;
                        }
                    }
                }
            }

            /*form-group-block 추가/삭제에 대한 처리 끝*/

            <%-- 대학, 대학원 입력란 동적 처리 시작 --%>
            <%-- 추가/삭제 버튼의 부모의 부모가 복사할 블록이어야 하고--%>
            <%-- 복사한 블록은 바로 위의 부모에 append 함--%>
            var addInputBlock = function (e) {
                var originalBlock = e.target.parentNode.parentNode,
                    cloneObj, targetParent = originalBlock.parentNode;
                $('.calendar').datepicker('destroy');
                cloneObj = $(originalBlock).clone(true);
                incrementChildren(cloneObj, targetParent.children.length+1);
                $(targetParent).append(cloneObj);
                $('.calendar').datepicker(datePickerOption);

            };

            var removeInputBlock = function (e) {
                var blockToRemove, parentOfBlock, nOfBlocks;
                blockToRemove = e.target.parentNode.parentNode;
                parentOfBlock = blockToRemove.parentNode;
                nOfBlocks = parentOfBlock.children.length;
                if ((nOfBlocks-1) < 1) alert('더 이상 삭제할 수 없습니다.');
                else {
                    $(blockToRemove).remove();
                }
            }


            <%-- o 내의 모든 children의 id 값 마지막 숫자를 n으로 변경, value를 ""로 --%>
            var incrementChildren = function (o, n) {

                var childList = o instanceof jQuery ? o.children() : o.children, i, l, t0, tid;

                if ( childList ) {
                    for( i = 0, l = childList.length ; i < l ; i++ ) {
                        t0 = childList[i];
                        if ( t0.id ) {
                            tid = t0.id.toString();
                            t0.id = tid.substring(0, tid.length - 1) + n.toString();
                        }
                        if ( t0.value ) t0.value = "";
                        if ( t0.type == 'radio' ) t0.checked = false;

                        incrementChildren(t0, n);
                    }
                }
            };

            // IE8 처리
            var handleAddEventListener = function(target, event, handler) {
                if (window.addEventListener) {
                    target.addEventListener(event, handler);
                }
                else {
                    target.attachEvent('on'+event, handler);
                }
            };

            $('.addCollege').on('click', addInputBlock);
            $('.removeCollege').on('click', removeInputBlock);
            <%-- 대학 입력란 동적 처리 끝 --%>

            <%-- 다음 주소 검색 시작 --%>
            var postLayer = document.getElementById('postLayer');

            var closeDaumPostcode = function () {
                // iframe을 넣은 element를 안보이게 한다.
                postLayer.style.display = 'none';
            };

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
                        closeDaumPostcode();
                    },
                    width : '100%',
                    height : '100%'
                }).embed(postLayer);

                // iframe을 넣은 element를 보이게 한다.
                postLayer.style.display = 'block';
            };

            $('#btnCloseLayer').on('click', closeDaumPostcode);

            $('#searchAddress').on('click', showDaumPostcode);
            <%-- 다음 주소 검색 끝 -->

            <%-- 사진 업로드 시작 --%>
            $(document).on('change', '.btn-file :file', function() {
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

            <%-- 달력 시작 --%>
//            $('.input-group.date').datepicker({
//                format: "yyyy-mm-dd",
//                startView: 2,
//                language: "kr",
//                forceParse: false,
//                autoclose: true
//            });
            <%-- 달력 끝 --%>

            <%-- 재학기간 달력 시작 --%>
//            $('.input-daterange').datepicker({
//                format: "yyyy-mm-dd",
//                startView: 2,
//                language: "kr",
//                forceParse: false,
//                autoclose: true
//            });
            <%-- 재학기간 달력 끝 --%>

            $('input[name=acadType]').change(function() {
                var radioValue = $(this).val();
                if (radioValue == '00001') {
                    hideExclude('highschoolSubContainer1');
                } else {
                    hideExclude('highschoolSubContainer2');
                }
            });

            function hideExclude(excludeId) {
                $('#highschoolDynamic').children().each(function() {
                    $(this).hide();
                });
                $('#' + excludeId).show();
            }
            $('input[name=acadType]').eq(0).click();

            /*지원 구분 변경 처리 시작*/
            $('#applAttrCode').change(function() {
                var index = $(this).children('option:selected').index() + 1;
//                var index = $('#applyKind option:selected').index() + 1;
                hideByClassname('applyKindDynamic', 'hidden-apply-kind-' + index)
            });

            function hideByClassname(parentId, hideClassname) {
                $('#' + parentId).children().each(function() {
                    if ($(this).hasClass(hideClassname)) {
                        $(this).hide();
                    } else {
                        $(this).show();
                    }
                });
            }
            /*지원 구분 변경 처리 끝*/

            /*select 폼 change 이벤트 처리 시작*/
            function attachChangeEvent( sourceId, targetId, valueKey, labelKey, appendUrl ) {
                var source = $('#' + sourceId);
                var target = $('#' + targetId);
                source.change( function() {
                    var url = '${contextPath}/common/code';
                    if( appendUrl && typeof(appendUrl) === 'string' ) {
                        url += appendUrl;
                    } else if( appendUrl && typeof(appendUrl) === 'function' ) {
                        url += appendUrl(source.val());
                    }

                    $.ajax({
                        type: 'GET',
                        url: url,
                        success: function(e) {
                            if(e.result && e.result === 'SUCCESS') {
                                var data = JSON && JSON.parse(e.data) || $.parseJSON(e.data);
                                $(target).children('option').filter(function () {
                                    return this.value !== '-';
                                }).remove();
                                $(data).each(function (i, item) {
                                    var opt = $('<option>').attr('value', item[valueKey]).attr('label', item[labelKey]);
                                    target.append(opt);
                                });
                            }
                        },
                        error: function(e) {
                            console.log(e);
                        }
                    });
                });
            }
            /*select 폼 change 이벤트 처리 끝*/

            /*지원사항 select 폼 change 이벤트 핸들러 등록 시작*/
            // 지원구분 변경
            attachChangeEvent( 'applAttrCode', 'campCode', 'campCode', 'campName', '/campus' );

            // 캠퍼스 변경
            attachChangeEvent( 'campCode', 'collCode', 'collCode', 'collName',
                    function(args) {
                        return '/college/' + args;
                    }
            );

            // 대학변경
            attachChangeEvent( 'collCode', 'deptCode', 'deptCode', 'deptName',
                    function(args) {
                        <%--var admsNo = ${requestScope.admsNo};--%>
                        var admsNo = '15-A';
                        return '/general/department/' + admsNo + '/' + args;
                    }
            );

            // 지원학과 변경
            attachChangeEvent( 'deptCode', 'corsTypeCode', 'corsTypeCode', 'codeVal',
                    function(args) {
                        <%--var admsNo = ${requestScope.admsNo};--%>
                        var admsNo = '15-A';
                        return '/general/course/' + admsNo + '/' + args;
                    }
            );
            /*지원사항 select 폼 change 이벤트 핸들러 등록 끝*/

            /*병역사항 select 폼 change 이벤트 핸들러 등록 시작*/
            attachChangeEvent( 'mltrServCode', '')
            /*병역사항 select 폼 change 이벤트 핸들러 등록 끝*/

            $('#applAttrCode').change();

            function initOptions( selectId, valueKey, labelKey ) {
                var select = $('#' + selectId);
                var url;
                $.ajax({
                    type: 'GET',
                    url: url,
                    success: function(e) {
                        if(e.result && e.result === 'SUCCESS') {
                            var data = JSON && JSON.parse(e.data) || $.parseJSON(e.data);
                            $(target).children('option').filter(function () {
                                return this.value !== '-';
                            }).remove();
                            $(data).each(function (i, item) {
                                var opt = $('<option>').attr('value', item[valueKey]).attr('label', item[labelKey]);
                                target.append(opt);
                            });
                        }
                    },
                    error: function(e) {
                        console.log(e);
                    }
                });
            }

        });

    </script>
</content>
</body>
</html>
