<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<%--// TODO 제3자 동의여부 : ${providePrivateInfo} - 0 : 동의, 1 : 비동의--%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${contextPath}/css/datepicker3.css">
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

        section.application .btn {
            border: 1px;
        }

        section.application .input-group-btn .btn {
            border-radius: 4px;
        }

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

    </style>
    <%--body의 글자 속성을 #333333으로 강제 지정하여 Footer 글자가 안나옴, 꼭 필요하지 않으면 안쓰기로
    <link rel="stylesheet" href="${contextPath}/css/bootstrap-glyphicons.css" />--%>
</head>
<body>
<section class="application">
    <div class="container">
    <form:form commandName="application" cssClass="form-horizontal" role="form">
        <ul id="myTab" class="nav nav-tabs nav-justified tab-gray">
            <li><a href="#appinfo" data-toggle="tab">기본정보</a></li>
            <li><a href="#selfintro" data-toggle="tab">자기소개서</a></li>
            <li><a href="#studyplan" data-toggle="tab">학업 및 연구 계획서</a></li>
            <li><a href="#fileupload" data-toggle="tab">첨부파일</a></li>
        </ul>
        <div id="myTabContent" class="tab-content">

                <div class="tab-pane fade" id="appinfo">
                <div class="spacer-tiny"></div>

                <div class="row">
                    <div class="col-sm-offset-1 col-sm-10">
                        <div class="panel panel-default">
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
                                            <span class="input-group-addon">[성]</span>
                                            <form:input path="engSur" cssClass="col-sm-6 form-control" />
                                        </div>
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
                                    <form:label path="currWrkpName" cssClass="col-sm-2 control-label">회사이름</form:label>
                                    <div class="col-sm-9">
                                        <form:input path="currWrkpName" cssClass="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">입사일</label>
                                    <div class="col-sm-9">
                                        <div class="input-group">
                                            <form:input path="currWrkpDay" cssClass="col-sm-6 form-control" />
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="currWrkpTel" cssClass="col-sm-2 control-label">연락처</form:label>
                                    <div class="col-sm-9">
                                        <form:input path="currWrkpTel" cssClass="form-control" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        </c:if>
                        <div class="panel panel-default">
                            <div class="panel-heading">지원자 상세정보</div>
                            <div class="panel-body">
                                <%--<div class="form-group">--%>
                                    <%--<form:label path="citzCntrCode" cssClass="col-sm-3 control-label">국적</form:label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<div class="input-group">--%>
                                            <%--<form:input path="citzCntrCode" cssClass="form-control" />--%>
                                            <%--<span class="input-group-btn">--%>
                                                <%--<button type="button" class="btn btn-default" id="search-citz-cntr-code">--%>
                                                    <%--<span class="glyphicon glyphicon-search"></span> 검색--%>
                                                <%--</button>--%>
                                            <%--</span>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-3 control-label">장애사항</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<div class="input-group">--%>
                                            <%--<span class="input-group-addon">[장애유형]</span>--%>
                                            <%--<form:input path="hndcGrad" cssClass="col-sm-6 form-control" />--%>
                                        <%--</div>--%>
                                        <%--<div class="input-group">--%>
                                            <%--<span class="input-group-addon">[장애등급]</span>--%>
                                            <%--<form:input path="hndcType" cssClass="col-sm-6 form-control" />--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">지원자 연락처</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">주소</label>
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
                                    <form:label path="telNum" cssClass="col-sm-3 control-label">전화번호</form:label>
                                    <div class="col-sm-9">
                                        <%--TODO--%>
                                        <%--<form:select path="telNumFirst" cssClass="form-control">--%>
                                            <%--<form:options items="${common.telNumFirst}" />--%>
                                        <%--</form:select> --%>
                                        <form:input path="telNum" cssClass="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="mobiNum" cssClass="col-sm-3 control-label">휴대폰</form:label>
                                    <div class="col-sm-9">
                                        <%--TODO--%>
                                        <%--<form:select path="mobiNumFirst" cssClass="form-control">--%>
                                            <%--<form:options items="${common.telNumFirst}" />--%>
                                        <%--</form:select>--%>
                                        <form:input path="mobiNum" cssClass="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="mailAddr" cssClass="col-sm-3 control-label">E-mail</form:label>
                                    <div class="col-sm-9">
                                        <form:input path="mailAddr" cssClass="form-control" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">비상연락처</div>
                            <div class="panel-body">
                                <%--<div class="form-group">--%>
                                    <%--<form:label path="emerContName" cssClass="col-sm-3 control-label">이름</form:label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<form:input path="emerContName" cssClass="form-control" />--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<form:label path="emerContCode" cssClass="col-sm-3 control-label">관계</form:label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<form:select path="emerContCode" cssClass="form-control">--%>
                                            <%--<form:options items="${common.emerCont}" />--%>
                                        <%--</form:select>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<form:label path="emerContTel" cssClass="col-sm-3 control-label">전화번호</form:label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--&lt;%&ndash;TODO&ndash;%&gt;--%>
                                        <%--<form:select path="telNumFirst" cssClass="form-control">--%>
                                            <%--<form:options items="${common.telNumFirst}" />--%>
                                        <%--</form:select> --%>
                                        <%--<form:input path="emerContTel" cssClass="form-control" />--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">고등학교</div>
                            <div class="panel-body">

                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">대학교</div>
                            <div class="panel-body">
                                <div id="college-container">
                                    <div class="collegeInfo">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">소재 국가</label>
                                            <div class="btn btn-default btn-md col-md-2">검색</div>
                                            <div class="col-sm-6">
                                                <input name="korCntrName" class="form-control" id="korCntrName1"/>
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
                                                    <input type="radio" name="lastSchlYn" id="lastSchlYn1" value="Y" checked>&nbsp;&nbsp;최종 학교
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
                                                <div class="input-group date">
                                                    <span class="input-group-addon">입학일</span>
                                                    <input type="text" class="form-control" name="entrDay" id="entrDay1"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4 start-date-container">
                                                <div class="input-group date">
                                                    <span class="input-group-addon">졸업일</span>
                                                    <input type="text" class="form-control" name="grdaDay" id="grdaDay1"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">평균 평점</label>
                                            <div class="col-sm-2">
                                                <div class="input-group date">
                                                    <span class="input-group-addon">평점</span>
                                                    <input class="form-control" name="gradAvr" id="gradAvr1"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="input-group date">
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
                            <div class="panel-heading">대학원</div>
                            <div class="panel-body">

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

                            </div>
                        </div>
                    </div>
                </div>


                <%--<div class="form-group">--%>
                    <%--<form:label path="campus" cssClass="col-sm-3 control-label">지원캠퍼스</form:label>--%>
                    <%--<div class="col-sm-6">--%>
                        <%--<form:select path="campus" cssClass="form-control">--%>
                            <%--<form:option value="-" label="--Please Select" />--%>
                            <%--<form:options items="${campuses}" />--%>
                        <%--</form:select>--%>
                    <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<form:label path="course" cssClass="col-sm-3 control-label">지원과정</form:label>--%>
                <%--<div class="col-sm-6">--%>
                    <%--<form:select path="course" cssClass="form-control">--%>
                        <%--<form:option value="-" label="--Please Select" />--%>
                        <%--<form:options items="${courses}" />--%>
                    <%--</form:select>--%>
                <%--</div>--%>
            <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="institution" cssClass="col-sm-3 control-label">학연산 연구기관명</form:label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<form:input path="institution" cssClass="form-control" />--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="department" cssClass="col-sm-3 control-label">지원학과</form:label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<form:select path="department" cssClass="form-control">--%>
                                <%--<form:option value="-" label="--Please Select" />--%>
                                <%--<form:options items="${departments}" />--%>
                            <%--</form:select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="detailMajor" cssClass="col-sm-3 control-label">세부전공</form:label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<form:select path="detailMajor" cssClass="form-control">--%>
                                <%--<form:option value="-" label="--Please Select" />--%>
                                <%--<form:options items="${detailMajors}" />--%>
                            <%--</form:select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="korName" cssClass="col-sm-3 control-label">이름</form:label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<form:input path="korName" cssClass="form-control" />--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="engName" cssClass="col-sm-3 control-label">영문</form:label>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<form:input path="engName" cssClass="form-control" />--%>
                        <%--</div>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<form:input path="engSur" cssClass="form-control" />--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="rgstNo" cssClass="col-sm-3 control-label">주민등록번호</form:label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<form:input path="rgstNo" cssClass="form-control" />--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="mailAddr" cssClass="col-sm-3 control-label">E-mail</form:label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<form:input path="mailAddr" cssClass="form-control" />--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="telNum" cssClass="col-sm-3 control-label">전화번호</form:label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<form:input path="telNum" cssClass="form-control" />--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="mobiNum" cssClass="col-sm-3 control-label">휴대폰</form:label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<form:input path="mobiNum" cssClass="form-control" />--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="addr" cssClass="col-sm-3 control-label">주소</form:label>--%>
                        <%--<div class="col-sm-1">--%>
                            <%--<form:input path="zipCode" cssClass="form-control" />--%>
                        <%--</div>--%>
                        <%--<div class="col-sm-5">--%>
                            <%--<div class="input-group">--%>
                                <%--<form:input path="addr" cssClass="form-control" />--%>
                                <%--<span class="input-group-btn">--%>
                                    <%--<button type="button" class="btn btn-default" id="search-zipcode">--%>
                                        <%--<span class="glyphicon glyphicon-search"></span> Search--%>
                                    <%--</button>--%>
                                <%--</span>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-sm-offset-3 col-sm-6">--%>
                            <%--<form:input path="detlAddr" cssClass="form-control" />--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="spacer-small"></div>--%>
                    <%--<hr/>--%>
                    <%--<h2 class="slogan">학력 사항</h2>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-sm-offset-2 col-sm-8">--%>
                            <%--<table class="table table-hover table-responsive table-condensed table-bordered">--%>
                                <%--<thead>--%>
                                    <%--<tr>--%>
                                        <%--<td><input type="checkbox" id="select-all-academies" /></td>--%>
                                        <%--<td>학력</td>--%>
                                        <%--<td>학교명</td>--%>
                                        <%--<td>입학일자</td>--%>
                                        <%--<td>졸업일자</td>--%>
                                        <%--<td>졸업구분</td>--%>
                                    <%--</tr>--%>
                                <%--</thead>--%>
                                <%--<tbody>--%>
                                <%--<c:forEach items="${application.academies}" var="academy" varStatus="status">--%>
                                    <%--<tr>--%>
                                        <%--<td><input type="checkbox" class="selectRow" /></td>--%>
                                        <%--<td><form:select path="academies[${status.index}].acadTypeCode" items="${requestScope.schoolTypes}" /></td>--%>
                                        <%--<td><form:input path="academies[${status.index}].schlName" /></td>--%>
                                        <%--<td><form:input path="academies[${status.index}].entrDay" /></td>--%>
                                        <%--<td><form:input path="academies[${status.index}].grdaDay" /></td>--%>
                                        <%--<td><form:select path="academies[${status.index}].grdaTypeCode" items="${requestScope.graduationTypes}" /></td>--%>
                                    <%--</tr>--%>
                                <%--</c:forEach>--%>
                                <%--<c:if test="${application.academies.size() < 4}">--%>
                                    <%--<c:forEach begin="${application.academies.size()}" end="3" varStatus="status">--%>
                                        <%--<tr>--%>
                                            <%--<td><input type="checkbox" class="selectRow" /></td>--%>
                                            <%--<td><select id="academies${status.index}.acadTypeCode" name="academies[${status.index}].acadTypeCode" items="${requestScope.acadTypeCode}" /></td>--%>
                                            <%--<td><input id="academies${status.index}.schlName" name="academies[${status.index}].schlName" /></td>--%>
                                            <%--<td><input id="academies${status.index}.entrDay" name="academies[${status.index}].entrDay" /></td>--%>
                                            <%--<td><input id="academies${status.index}.grdaDay" name="academies[${status.index}].grdaDay" /></td>--%>
                                            <%--<td><select id="academies${status.index}.grdaTypeCode" name="academies[${status.index}].grdaTypeCode" items="${requestScope.grdaTypeCode}" /></td>--%>
                                        <%--</tr>--%>
                                    <%--</c:forEach>--%>
                                <%--</c:if>--%>
                                <%--</tbody>--%>
                            <%--</table>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-sm-offset-2 col-sm-8">--%>
                            <%--<div class="btn-group pull-right">--%>
                                <%--<button type="button" class="btn btn-default" id="academy-up">--%>
                                    <%--<span class="glyphicon glyphicon-chevron-up"></span>--%>
                                <%--</button>--%>
                                <%--<button type="button" class="btn btn-default" id="academy-down">--%>
                                    <%--<span class="glyphicon glyphicon-chevron-down"></span>--%>
                                <%--</button>--%>
                                <%--<button type="button" class="btn btn-default" id="academy-add">--%>
                                    <%--<span class="glyphicon glyphicon-plus"></span>--%>
                                <%--</button>--%>
                                <%--<button type="button" class="btn btn-default" id="academy-del">--%>
                                    <%--<span class="glyphicon glyphicon-minus"></span>--%>
                                <%--</button>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="spacer-small"></div>--%>
                    <%--<hr/>--%>
                    <%--<h2 class="slogan">경력 사항</h2>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-sm-offset-2 col-sm-8">--%>
                            <%--<table class="table table-hover table-responsive table-condensed table-bordered">--%>
                                <%--<thead>--%>
                                <%--<tr>--%>
                                    <%--<td><input type="checkbox" id="select-all-careers" /></td>--%>
                                    <%--<td colspan="3">기간</td>--%>
                                    <%--<td>기관명</td>--%>
                                    <%--<td>직위(또는 상벌내용, 연수내용 등)</td>--%>
                                <%--</tr>--%>
                                <%--</thead>--%>
                                <%--<tbody>--%>
                                <%--<c:forEach items="${application.careers}" var="career" varStatus="status">--%>
                                    <%--<tr>--%>
                                        <%--<td><input type="checkbox" class="selectRow" /></td>--%>
                                        <%--<td><form:input path="careers[${status.index}].joinYmd" /></td>--%>
                                        <%--<td><span>~</span></td>--%>
                                        <%--<td><form:input path="careers[${status.index}].retrYmd" /></td>--%>
                                        <%--<td><form:input path="careers[${status.index}].corpName" /></td>--%>
                                        <%--<td><form:input path="careers[${status.index}].exprDesc" /></td>--%>
                                    <%--</tr>--%>
                                <%--</c:forEach>--%>
                                <%--<c:if test="${application.academies.size() < 4}">--%>
                                    <%--<c:forEach begin="${application.careers.size()}" end="3" varStatus="status">--%>
                                        <%--<tr>--%>
                                            <%--<td><input type="checkbox" class="selectRow" /></td>--%>
                                            <%--<td><input id="careers${status.index}.joinYmd" name="careers[${status.index}].joinYmd"/></td>--%>
                                            <%--<td><span>~</span></td>--%>
                                            <%--<td><input id="careers${status.index}.retrYmd" name="careers[${status.index}].retrYmd"/></td>--%>
                                            <%--<td><input id="careers${status.index}.corpName" name="careers[${status.index}].corpName"/></td>--%>
                                            <%--<td><input id="careers${status.index}.exprDesc" name="careers[${status.index}].exprDesc"/></td>--%>
                                        <%--</tr>--%>
                                    <%--</c:forEach>--%>
                                <%--</c:if>--%>
                                <%--</tbody>--%>
                            <%--</table>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-sm-offset-2 col-sm-8">--%>
                            <%--<div class="btn-group pull-right">--%>
                                <%--<button type="button" class="btn btn-default" id="career-up">--%>
                                    <%--<span class="glyphicon glyphicon-chevron-up"></span>--%>
                                <%--</button>--%>
                                <%--<button type="button" class="btn btn-default" id="career-down">--%>
                                    <%--<span class="glyphicon glyphicon-chevron-down"></span>--%>
                                <%--</button>--%>
                                <%--<button type="button" class="btn btn-default" id="career-add">--%>
                                    <%--<span class="glyphicon glyphicon-plus"></span>--%>
                                <%--</button>--%>
                                <%--<button type="button" class="btn btn-default" id="career-del">--%>
                                    <%--<span class="glyphicon glyphicon-minus"></span>--%>
                                <%--</button>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="spacer-small"></div>--%>
                    <%--<hr/>--%>
                    <%--<h2 class="slogan">전형료 환불 계좌</h2>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="bank" cssClass="col-sm-3 control-label">은행명</form:label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<form:select path="bank" cssClass="form-control">--%>
                                <%--<form:option value="-" label="--Please Select" />--%>
                                <%--<form:options items="${bankList}" itemvalue="code" itemlabel="name" />--%>
                            <%--</form:select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="accountNumber" cssClass="col-sm-3 control-label">계좌 번호</form:label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<form:input path="accountNumber" cssClass="form-control" placeholder="Bank Account Number" />--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="accountOwner" cssClass="col-sm-3 control-label">예금주</form:label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<form:input path="accountOwner" cssClass="form-control" placeholder="Bank Account Owner" />--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="spacer-tiny"></div>
            <%--</form:form>--%>
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



</div>
</section>
    <content tag="local-script">
    <%--<script src="${contextPath}/js/postcode.js"></script>--%>
    <script src="http://dmaps.daum.net/map_js_init/postcode.js"></script>
    <script src="${contextPath}/js/bootstrap-datepicker.js"></script>
    <script src="${contextPath}/js/bootstrap-datepicker.kr.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {


            $('#myTab a:first').tab('show');

            $('#save').on('click', function() {
                var $curPane = $('.tab-pane.active');
                var $curForm = $curPane.find('form');
                console.log($curForm.serialize());
                alert("저장 되었습니다.")
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

            <%-- 대학 입력란 동적 처리 시작 --%>
            var createCollegeHtml = function(n) {
                return '<div class="collegeInfo"><hr/>'+
                        '<div class="form-group">'+
                        '<label class="col-sm-2 control-label">소재 국가</label>'+
                        '<div class="btn btn-default btn-md col-md-2">검색</div>'+
                        '<div class="col-sm-6"><input name="korCntrName" class="form-control" id="korCntrName'+n+'"/></div>'+
                        '</div>'+
                        '<div class="form-group">'+
                        '<label class="col-sm-2 control-label">학교 이름</label>'+
                        '<div class="btn btn-default btn-md col-md-2">검색</div>'+
                        '<div class="col-sm-4"><input name="schlName" class="form-control" id="schlName'+n+'"/></div>'+
                        '<div class="col-sm-2"><label class="radio inline"><input type="radio" name="lastSchlYn" value="N">&nbsp;&nbsp;최종 학교</label>'+
                        '</div>'+
                        '</div>'+
                        '<div class="form-group">'+
                        '<label class="col-sm-2 control-label">단과 대학</label>'+
                        '<div class="col-sm-8"><input name="collName" class="form-control" id="collName'+n+'"/></div>'+
                        '</div>'+
                        '<div class="form-group">'+
                        '<label class="col-sm-2 control-label">학과 이름</label>'+
                        '<div class="col-sm-8"><input name="majName" class="form-control" id="majName'+n+'"/></div>'+
                        '</div>'+
                        '<div class="form-group">'+
                        '<label class="col-sm-2 control-label">재학 기간</label>'+
                        '<div class="col-sm-4 start-date-container">'+
                        '<div class="input-group date"><span class="input-group-addon">입학일</span>'+
                        '<input type="text" class="form-control" name="entrDay" id="entrDay'+n+'"/>'+
                        '</div>'+
                        '</div>'+
                        '<div class="col-sm-4 start-date-container">'+
                        '<div class="input-group date"><span class="input-group-addon">졸업일</span>'+
                        '<input type="text" class="form-control" name="grdaDay" id="grdaDay'+n+'"/>'+
                        '</div>'+
                        '</div>'+
                        '</div>'+
                        '<div class="form-group">'+
                        '<label class="col-sm-2 control-label">평균 평점</label>'+
                        '<div class="col-sm-2">'+
                        '<div class="input-group date"><span class="input-group-addon">평점</span>'+
                        '<input class="form-control" name="gradAvr" id="gradAvr'+n+'"/>'+
                        '</div>'+
                        '</div>'+
                        '<div class="col-sm-2">'+
                        '<div class="input-group date"><span class="input-group-addon">만점</span>'+
                        '<input class="form-control" name="gradFull id="gradFull'+n+'"/>'+
                        '</div>'+
                        '</div>'+
                        '<div id="addCollege'+n+'" class="addCollege col-sm-2 btn btn-info">입력란 추가</div>'+
                        '<div id="removeCollege'+n+'" class="removeCollege col-sm-2 btn btn-danger">입력란 삭제</div>'+
                        '</div>'+
                        '</div>';
            };


            $('.addCollege').on('click', function addCollege(e) {
                var nColleges = ($('.collegeInfo').length + 1).toString();
                $('#college-container').append(createCollegeHtml(nColleges));
            });

            $('.removeCollege').on('click', function removeCollege(e) {
                var nColleges = $('.collegeInfo').length;
                if ((nColleges-1) < 1) alert('더 이상 삭제할 수 없습니다.');
                else {
                    e.target.parentElement.parentElement.remove();
                }

            });
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
<%-- 달력 끝 --%>
            $('.start-date-container .input-group.date').datepicker({
                format: "yyyymmdd",
                startView: 2,
                language: "kr",
                forceParse: false,
                autoclose: true
            });
            <%-- 달력 끝 --%>
        });

    </script>
</content>
</body>
</html>
