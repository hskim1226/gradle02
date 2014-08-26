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
            background-color: #FFFFB4;
            border: solid 1px #edd061;
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
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade" id="appinfo">
                <form:form commandName="entireApplication" cssClass="form-horizontal" action="apply/save" role="form">
                    <div class="spacer-tiny"></div>
                    <div class="row">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="panel panel-default">
                                <div class="panel panel-default">
                                    <div class="panel-heading">지원 사항</div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label for="applyKind" class="col-sm-2 control-label">지원구분</label>
                                            <div class="col-sm-9">
                                                <form:select path="" id="applyKind" cssClass="form-control">
                                                    <form:options items="${applyKindList}" />
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
                                                <span class="input-group-addon">[ 성 ]</span>
                                                <form:input path="engSur" cssClass="col-sm-6 form-control" />
                                            </div>
                                        </div>
                                        <div class="col-sm-offset-2 col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">[이름]</span>
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
                                                <span class="input-group-addon">[장애유형]</span>
                                                <form:input path="applicationGeneral.hndcGrad" cssClass="col-sm-6 form-control" />
                                            </div>
                                        </div>
                                        <div class="col-sm-offset-2 col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">[장애등급]</span>
                                                <form:input path="applicationGeneral.hndcType" cssClass="col-sm-6 form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">병역사항</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">[병역구분]</span>
                                                <select id="applicationGeneral.mltrServCode">
                                                    <option value="-">선택</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">[병역군별]</span>
                                                <select id="applicationGeneral.mltrTypeCode">
                                                    <option value="-">선택</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">[병역계급]</span>
                                                <select id="applicationGeneral.mltrRankCode">
                                                    <option value="-">선택</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-sm-offset-2 col-sm-4 start-date-container">
                                            <div class="input-group date">
                                                <span class="input-group-addon">입대일자</span>
                                                <input type="text" class="form-control" name="applicationGeneral.mltrJoinDay" id="applicationGeneral.mltrJoinDay" readonly/>
                                            </div>
                                        </div>
                                        <div class="col-sm-4 end-date-container">
                                            <div class="input-group date">
                                                <span class="input-group-addon">제대일자</span>
                                                <input type="text" class="form-control" name="applicationGeneral.mltrJoinDay" id="applicationGeneral.mltrDschDay" readonly/>
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
                                        <input type="text" id="postcode1"> - <input type="text" id="postcode2"/>
                                        <input type="hidden" id="zipCode" name="zipCode"/>
                                        <button type="button" id="searchAddress">우편번호 찾기</button>
                                        <input type="text" id="address" name="addr"/>
                                        <input type="text" id="addressDetail" name="detlAddr">
                                        <%--<div class="col-sm-1">--%>
                                            <%--<form:input path="zipCode" cssClass="form-control" />--%>
                                        <%--</div>--%>
                                        <%--<div class="col-sm-8">--%>
                                            <%--<div class="input-group">--%>
                                                <%--<span class="input-group-btn">--%>
                                                    <%--<button type="button" class="btn btn-default" id="search-zipcode">--%>
                                                        <%--<span class="glyphicon glyphicon-search"></span> 검색--%>
                                                    <%--</button>--%>
                                                <%--</span>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="col-sm-offset-3 col-sm-8">--%>
                                            <%--<form:input path="addr" cssClass="form-control" />--%>
                                        <%--</div>--%>
                                        <%--<div class="col-sm-offset-3 col-sm-8">--%>
                                            <%--<form:input path="detlAddr" cssClass="form-control" />--%>
                                        <%--</div>--%>
                                    </div>
                                    <div class="form-group">
                                        <form:label path="telNum" cssClass="col-sm-2 control-label">전화번호</form:label>
                                        <div class="col-sm-9">
                                            <%--TODO--%>
                                            <%--<form:select path="telNumFirst" cssClass="form-control">--%>
                                                <%--<form:options items="${common.telNumFirst}" />--%>
                                            <%--</form:select> --%>
                                            <form:input path="telNum" />
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
                                                <form:options items="${common.emerCont}" />
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
                                                        <input type="radio" id="acadType1" name="acadType" value="00001" />졸업
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" id="acadType2" name="acadType" value="00005" />검정고시
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="highschoolDynamic">
                                        <div id="highschoolSubContainer1">
                                            <div class="form-group">
                                                <div class="col-sm-2 control-label">재학기간</div>
                                                <div class="col-sm-9">
                                                    <div class="input-daterange input-group">
                                                        <input type="text" class="input-sm form-control" name="entrDay" />
                                                        <span class="input-group-addon">to</span>
                                                        <input type="text" class="input-sm form-control" name="grdaDay" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div for="schlName" class="col-sm-2 control-label">학교명</div>
                                                <div class="col-sm-9">
                                                    <div class="input-group">
                                                        <span class="input-group-btn">
                                                            <button type="button" class="btn btn-default">검색</button>
                                                        </span>
                                                        <input type="text" class="form-control" id="schlName">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                            <div id="highschoolSubContainer2">
                                                <div class="form-group">
                                                    <div class="col-sm-2 control-label">검정고시합격일</div>
                                                    <div class="col-sm-9">
                                                        <div class="input-group date">
                                                            <span class="input-group-addon">합격일</span>
                                                            <input type="text" class="form-control" name="qualExamDay" id="qualExamDay" />
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div for="schlName" class="col-sm-2 control-label">검정고시합격지구</div>
                                                    <div class="col-sm-9">
                                                        <div class="input-group">
                                                        <span class="input-group-btn">
                                                            <button type="button" class="btn btn-default">검색</button>
                                                        </span>
                                                            <input type="text" class="form-control" id="schlName">
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
                                    <div id="college-container" class="form-group-block-list">
                                        <div id="college-info" class="form-group-block">
                                            <hr/>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">소재 국가</label>
                                                <div class="btn btn-default btn-md col-md-2">검색</div>
                                                <div class="col-sm-6">
                                                    <input name="applicationAcademyList[0].korCntrName" class="form-control" id="korCntrName201"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">학교 이름</label>
                                                <div class="btn btn-default btn-md col-md-2">검색</div>
                                                <div class="col-sm-4">
                                                    <input name="applicationAcademyList[0].schlName" class="form-control" id="schlName1" />
                                                </div>
                                                <div class="col-sm-2">
                                                    <label class="radio inline">
                                                        <input type="radio" name="applicationAcademyList[0].lastSchlYn" id="lastSchlYn1" class="lastSchl">&nbsp;&nbsp;최종 학교
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">단과 대학</label>
                                                <div class="col-sm-8">
                                                    <input name="applicationAcademyList[0].collName" class="form-control" id="collName1"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">학과 이름</label>
                                                <div class="col-sm-8">
                                                    <input name="applicationAcademyList[0].majName" class="form-control" id="majName1" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">재학 기간</label>
                                                <div class="col-sm-4 start-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">입학일</span>
                                                        <input type="text" class="form-control" name="applicationAcademyList[0].entrDay" id="entrDay1" readonly/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4 end-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">졸업일</span>
                                                        <input type="text" class="form-control" name="applicationAcademyList[0].grdaDay" id="grdaDay1" readonly/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">평균 평점</label>
                                                <div class="col-sm-2">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">평점</span>
                                                        <input class="form-control" name="applicationAcademyList[0].gradAvr" id="gradAvr1"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">만점</span>
                                                        <input class="form-control" name="applicationAcademyList[0].gradFull" id="gradFull1"/>
                                                    </div>
                                                </div>
                                                <div id="addCollege1" class="addCollege col-sm-2 btn btn-info">입력란 추가</div>
                                                <div id="removeCollege1" class="removeCollege col-sm-2 btn btn-danger">입력란 삭제</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">대학원</div>
                                <div class="panel-body">
                                    <div id="gradschool-container" class="form-group-block-list">
                                        <div id="gradschool-info" class="form-group-block">
                                            <hr/>
                                            <div class="form-group">
                                                <label class="col-xs-6 col-sm-2 control-label">소재 국가</label>
                                                <div class="co-xs-6 col-sm-2">
                                                    <button type="button" class="btn btn-default">검색</button>
                                                </div>
                                                <div class="col-xs-12 col-sm-7">
                                                    <input type="text" class="form-control" id="korCntrName301" name="korCntrName" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">학교 이름</label>
                                                <div class="btn btn-default btn-md col-md-2">검색</div>
                                                <div class="col-sm-4">
                                                    <input name="schlName" class="form-control" id="schlName1" />
                                                </div>
                                                <div class="col-sm-2">
                                                    <label class="radio inline">
                                                        <input type="radio" name="lastSchlYn" id="lastSchlYn1" class="lastSchl">&nbsp;&nbsp;최종 학교
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">단과 대학</label>
                                                <div class="col-sm-8">
                                                    <input name="collName" class="form-control" id="collName1"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">학과 이름</label>
                                                <div class="col-sm-8">
                                                    <input name="majName" class="form-control" id="majName1" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">재학 기간</label>
                                                <div class="col-sm-4 start-date-container">
                                                    <div class="input-group date" id="divCalEntrDay1">
                                                        <span class="input-group-addon">입학일</span>
                                                        <input type="text" class="form-control calendar" name="entrDay" id="entrDay2" readonly/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4 end-date-container">
                                                    <div class="input-group date" id="divCalGrdaDay1">
                                                        <span class="input-group-addon">졸업일</span>
                                                        <input type="text" class="form-control calendar" name="grdaDay" id="grdaDay2" readonly/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">평균 평점</label>
                                                <div class="col-sm-2">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">평점</span>
                                                        <input class="form-control" name="gradAvr" id="gradAvr1"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">만점</span>
                                                        <input class="form-control" name="gradFull" id="gradFull1"/>
                                                    </div>
                                                </div>
                                                <div id="addCollege1" class="addCollege col-sm-2 btn btn-info">입력란 추가</div>
                                                <div id="removeCollege1" class="removeCollege col-sm-2 btn btn-danger">입력란 삭제</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">어학성적</div>
                                <div class="panel-body">

                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">경력사항</div>
                                <div class="panel-body">
                                    <div id="career-container" class="form-group-block-list">
                                        <c:forEach items="${entireApplication.applicationExperienceList}" var="applicationExperiences" varStatus="exprStatus">
                                        <div id="career-info" class="form-group-block">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">재직 기간</label>
                                                <div class="col-sm-4 start-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">입사일</span>
                                                        <form:input path="applicationExperienceList[${exprStatus.index}].joinDay" cssClass="form-control" readonly="true" />
                                                    </div>
                                                </div>
                                                <div class="col-sm-4 end-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">퇴사일</span>
                                                        <form:input path="applicationExperienceList[${exprStatus.index}].retrDay" cssclass="form-control" readonly="true" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="applicationExperienceList[${exprStatus.index}].corpName" cssClass="col-sm-2 control-label">기관명</form:label>
                                                <div class="col-sm-9">
                                                    <form:input path="applicationExperienceList[${exprStatus.index}].corpName" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="applicationExperienceList[${exprStatus.index}].exprDesc" cssClass="col-sm-2 control-label">직위명</form:label>
                                                <div class="col-sm-9">
                                                    <form:input path="applicationExperienceList[${exprStatus.index}].exprDesc" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div id="removeCollege1" class="removeCollege col-sm-2 btn btn-danger" style="position: absolute; top:0; right:0;">입력란 삭제</div>
                                            </div>
                                        </div>
                                        </c:forEach>
                                        <div id="career-info" class="form-group-block">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">재직 기간</label>
                                                <div class="col-sm-4 start-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">입사일</span>
                                                        <input type="text" class="form-control" name="joinDay" id="joinDay1" readonly/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4 end-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">퇴사일</span>
                                                        <input type="text" class="form-control" name="retrDay" id="retrDay1" readonly/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="applicationExperienceList[0].corpName" cssClass="col-sm-2 control-label">기관명</form:label>
                                                <div class="col-sm-9">
                                                    <form:input path="applicationExperienceList[0].corpName" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="applicationExperienceList[0].exprDesc" cssClass="col-sm-2 control-label">직위명</form:label>
                                                <div class="col-sm-9">
                                                    <form:input path="applicationExperienceList[0].exprDesc" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div id="removeCollege1" class="removeCollege col-sm-2 btn btn-danger">입력란 삭제</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="spacer-tiny"></div>
                </form:form>
            </div>
            <%--selfintro--%>
            <div class="tab-pane fade" id="selfintro">
                <%--<form:form commandName="selfIntro" cssClass="form-horizontal" role="form" id="formSeflIntro" action="${contextPath}/application/selfintro/save">--%>
                    <%--<div class="spacer-tiny"></div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="ta1" cssClass="col-sm-3 control-label">주요 경력 사항</form:label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<form:textarea path="ta1" cssClass="form-control" rows="12" placeholder="주요 경력사항을 작성해주세요" />--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="ta2" cssClass="col-sm-3 control-label">지원 동기 및 장례 계획</form:label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<form:textarea path="ta2" cssClass="form-control" rows="12" placeholder="지원 동기와 장래 계획을 작성해주세요" />--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="ta3" cssClass="col-sm-3 control-label">성격 및 특기</form:label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<form:textarea path="ta3" cssClass="form-control" rows="12" placeholder="성격의 장단점과 특기를 작성해주세요" />--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="ta4" cssClass="col-sm-3 control-label">수상 내역</form:label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<form:textarea path="ta4" cssClass="form-control" rows="12" placeholder="주요 수상 내역을 작성해주세요" />--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="ta5" cssClass="col-sm-3 control-label">기타</form:label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<form:textarea path="ta5" cssClass="form-control" rows="12" placeholder="본인을 잘 설명할 수 있는 내용을 작성해주세요"/>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="spacer-tiny"></div>--%>
                <%--</form:form>--%>
                <div class="spacer-tiny"></div>
            </div>
            <%--studyplan--%>
            <div class="tab-pane fade" id="studyplan">
                <%--<form:form commandName="studyPlan" cssClass="form-horizontal" role="form" action="${contextPath}/application/studyplan/save">--%>
                    <%--<div class="spacer-tiny"></div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="ta1" cssClass="col-sm-3 control-label">희망 연구 분야 및 연구 계획</form:label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<form:textarea path="ta1" cssClass="form-control" rows="12" placeholder="희망 연구 분야와 연구 계획을 작성해주세요" />--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="ta2" cssClass="col-sm-3 control-label">학부/대학원 이수과목 중 관심과목</form:label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<form:textarea path="ta2" cssClass="form-control" rows="12" placeholder="학부와 대학원에서 관심있게 수강한 과목에 대해 작성해주세요" />--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="ta3" cssClass="col-sm-3 control-label">석/박사 이후의 계획</form:label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<form:textarea path="ta3" cssClass="form-control" rows="12" placeholder="석/박사 이후의 진로 계획에 대해 작성해주세요" />--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="spacer-tiny"></div>--%>
                <%--</form:form>--%>
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
        <%--<div id="postLayer" style="display:none;border:3px solid;position:fixed;width:300px;height:460px;left:50%;margin-left:-155px;top:50%;margin-top:-235px;overflow:hidden;-webkit-overflow-scrolling:touch;">--%>
            <%--<img src="${contextPath}/img/user/addr-close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px" alt="닫기 버튼">--%>
        <%--</div>--%>
    </div> <%--container--%>
</section>
<content tag="local-script">
    <%--<script src="${contextPath}/js/postcode.js"></script>--%>
    <script src="http://dmaps.daum.net/map_js_init/postcode.js"></script>
    <%--<script src="${contextPath}/js/bootstrap-datepicker.js"></script>--%>
    <%--<script src="${contextPath}/js/bootstrap-datepicker.kr.js"></script>--%>
    <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {

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

            /*처음 탭 표시*/
            $('#myTab a:first').tab('show');

            $('#save').on('click', function() {
                var $curPane = $('.tab-pane.active');
                var $curForm = $curPane.find('form');
                console.log($curForm.serialize());
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
//                }).embed(postLayer);
                }).open();

                // iframe을 넣은 element를 보이게 한다.
//                postLayer.style.display = 'block';
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
            $('#applyKind').change(function() {
                var index = $('#applyKind option:selected').index() + 1;
                hideByClassname('applyKindDynamic', 'hidden-apply-kind-' + index)
            });
            $('#applyKind').change();

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

            /*ajax 처리 시작*/
            <%--$('#applyKind').change(function() {--%>
                <%--$.ajax({--%>
                    <%--type: 'GET',--%>
                    <%--url: '${contextPath}/common/code/campus',--%>
                    <%--dataType: 'json',--%>
                    <%--success: function(data) {--%>
                        <%--$.each(data, function(i, item) {--%>
                            <%--$('<option>').attr('value', item.campCode).attr('label', item.campName).appendTo('#campCode');--%>
                        <%--});--%>
                    <%--}--%>
                <%--});--%>
            <%--});--%>

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
                        dataType: 'JSON',
                        success: function(data) {
                            $(target).children('option').filter(function() {
                                return this.value !== '-';
                            }).remove();
                            $(data).each( function(i, item) {
                                target.append( createOption( item[valueKey], item[labelKey] ) );
                            });
                        }
                    });
                });
            }

            function createOption( value, label ) {
                return $('<option>').attr('value', value).attr('label', label);
            }

            attachChangeEvent( 'applyKind', 'campCode', 'campCode', 'campName', '/campus' );
            attachChangeEvent( 'campCode', 'collCode', 'collCode', 'collName',
                    function(args) {
                        return '/college/' + args;
                    }
            );
            attachChangeEvent( 'collCode', 'deptCode', 'deptCode', 'deptName',
                    function(args) {
                        <%--var admsNo = ${requestScope.admsNo};--%>
                        var admsNo = '15-A';
                        return '/general/department/' + admsNo + '/' + args;
                    }
            );
            attachChangeEvent( 'deptCode', 'corsTypeCode', 'corsTypeCode', 'codeVal',
                    function(args) {
                        <%--var admsNo = ${requestScope.admsNo};--%>
                        var admsNo = '15-A';
                        return '/general/course/' + admsNo + '/' + args;
                    }
            );
            /*ajax 처리 끝*/

        });

    </script>
</content>
</body>
</html>
