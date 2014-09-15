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
            cursor: default;
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
            <li><a href="#academy" data-toggle="tab">학력</a></li>
            <li><a href="#langcareer" data-toggle="tab">어학 및 경력</a></li>
            <li><a href="#fileupload" data-toggle="tab">첨부파일</a></li>
        </ul>
        <form:form commandName="entireApplication" cssClass="form-horizontal" method="post" enctype="multipart/form-data" role="form">
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
                                            <form:select path="application.applAttrCode" id="applAttrCode" cssClass="form-control">
                                                <form:options items="${common.applAttrList}" itemValue="code" itemLabel="codeVal"/>
                                            </form:select>
                                        </div>
                                    </div>
                                    <div id="applyKindDynamic">
                                        <div class="form-group hidden-apply-kind-2 required">
                                            <form:label path="campCode" cssClass="col-sm-2 control-label">캠퍼스</form:label>
                                            <div class="col-sm-3">
                                                <form:select path="campCode" cssClass="form-control">
                                                    <form:option value="" label="--선택--" />
                                                    <c:if test="${entireApplication.application.applAttrCode == '00001' || entireApplication.application.applAttrCode == '00003'}">
                                                    <form:options items="${common.campList}" itemValue="campCode" itemLabel="campName" />
                                                    </c:if>
                                                </form:select>
                                            </div>
                                            <form:label path="collCode" cssClass="col-sm-2 control-label">대학</form:label>
                                            <div class="col-sm-4">
                                                <form:select path="collCode" cssClass="form-control">
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
                                                <form:select path="application.ariInstCode" id="ariInstCode" cssClass="form-control">
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
                                                <form:select path="application.deptCode" id="deptCode" cssClass="form-control">
                                                    <form:option value="" label="--선택--" />
                                                    <form:options items="${common.deptList}" itemValue="deptCode" itemLabel="deptName" />
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group required">
                                            <label for="corsTypeCode" class="col-sm-2 control-label">지원 과정</label>
                                            <div class="col-sm-9">
                                                <form:select path="application.corsTypeCode" id="corsTypeCode" cssClass="form-control">
                                                    <form:option value="" label="--선택--" />
                                                    <form:options items="${common.corsTypeList}" itemValue="corsTypeCode" itemLabel="codeVal" />
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group required">
                                            <label for="detlMajCode" class="col-sm-2 control-label">세부 전공</label>
                                            <div class="col-sm-9">
                                                <form:select path="application.detlMajCode" id="detlMajCode" cssClass="form-control">
                                                    <form:option value="" label="--선택--" />
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
                                                <form:input path="application.engSur" cssClass="form-control" style="text-transform: uppercase;" />
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="input-group">
                                                <span class="input-group-addon">이름</span>
                                                <form:input path="application.engName" cssClass="form-control" style="text-transform: uppercase;" />
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
                                                <span class="input-group-addon"></span>
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
                                    <div class="form-group required">
                                        <label class="col-sm-2 control-label">장애 사항</label>
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
                                    <div class="form-group required">
                                        <label class="col-sm-2 control-label">병역 사항</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">병역구분</span>
                                                <form:select path="applicationGeneral.mltrServCode" cssClass="form-control">
                                                    <form:option value="" label="--선택--" />
                                                    <form:options items="${common.mltrServList}" itemValue="code" itemLabel="codeVal" />
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="col-sm-offset-2 col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">병역군별</span>
                                                <form:select path="applicationGeneral.mltrTypeCode" cssClass="form-control">
                                                    <form:option value="" label="--선택--" />
                                                    <form:options items="${common.mltrTypeList}" itemValue="code" itemLabel="codeVal" />
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="col-sm-offset-2 col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">병역계급</span>
                                                <form:select path="applicationGeneral.mltrRankCode" cssClass="form-control">
                                                    <form:option value="" label="--선택--" />
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
                                    <div class="form-group required">
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

                        </div>
                    </div>
                </div>


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
                                            <div class="form-group required">
                                                <label for="collegeList${stat.index}.schlCntrName" class="col-sm-2 control-label">소재 국가</label>
                                                <div class="col-sm-2">
                                                    <button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="collegeList${stat.index}.schlCntrCode" data-targetNode2='collegeList${stat.index}.schlCntrName' data-category="country">검색</button>
                                                </div>
                                                <div class="col-sm-6">
                                                    <form:hidden path="collegeList[${stat.index}].schlCntrCode" />
                                                    <input id="collegeList${stat.index}.schlCntrName" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group required">
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
                                            <div class="form-group required">
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
                                            <div class="form-group required">
                                                <label for="graduateList${stat.index}.schlCntrName" class="col-sm-2 control-label">소재 국가</label>
                                                <div class="col-sm-2">
                                                    <button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="graduateList${stat.index}.schlCntrCode" data-targetNode2='graduateList${stat.index}.schlCntrName' data-category="country">검색</button>
                                                </div>
                                                <div class="col-sm-6">
                                                    <form:hidden path="graduateList[${stat.index}].schlCntrCode" />
                                                    <input id="graduateList${stat.index}.schlCntrName" cssClass="form-control" />
                                                </div>
                                            </div>
                                            <div class="form-group required">
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
                                            <div class="form-group required">
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
                </div>

                <%--language & career--%>
                <div class="tab-pane fade" id="langcareer">
                    <div class="spacer-tiny"></div>
                    <div class="row">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="panel panel-default">
                                <div class="panel-heading">어학성적</div>
                                <div class="panel-body" id="english-score-list">
                                    <c:forEach items="${common.engExamList}" var="langExam" varStatus="stat">
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
                                                <label for="checkLang${stat.index}"><input type="checkbox" class="btn-lang-disabled" id="checkLang${stat.index}" <c:if test="entireApplication.applicationLanguageList['${stat.index}'] != null">checked</c:if>/>${langExam.examName}</label>
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
                                            <p class="form-control-static">인정 불가</p>
                                        </div>
                                        <div class="col-sm-3 show-lang">
                                            <div class="input-group date">
                                                <span class="input-group-addon">시험일</span>
                                                <form:input path="applicationLanguageList[${stat.index}].examDay" cssClass="form-control" />
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
                                                    </div>
                                                </div>
                                                <div class="col-sm-4 end-date-container">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon">퇴사일</span>
                                                        <form:input path="applicationExperienceList[${stat.index}].retrDay" cssClass="form-control" readonly="true" />
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
                </div>
                <%--첨부파일--%>
                <div class="tab-pane fade" id="fileupload">
                    <div class="spacer-tiny"></div>
                    <div class="row">
                        <div class="col-sm-offset-1 col-sm-10">

                            <c:if test="${common.geneDocList.size() > 0}">
                                <div class="panel panel-darkgray">
                                    <div class="panel-heading">기본 서류</div>
                                    <div class="panel-body" id="general-doc-list">
                                        <c:forEach items="${common.geneDocList}" var="customDoc" varStatus="stat">
                                            <hr/>
                                            <div class="form-group" id="generalDoc${customDoc.docItemCode}">
                                                <label class="col-sm-3 control-label word-keep-all">${customDoc.itemName}</label>
                                                <div class="col-sm-8">
                                                    <div class="input-group">
                                                        <div class="input-group-btn">
                                                            <input type="file" class="btn btn_lg btn-file" id="generalDocList${stat.index}.docName" name="generalDocList[${stat.index}].docName"/>
                                                        </div>
                                                        <div class="col-sm-4 nopadding"><input type="button" id="generalDoc${stat.index}.btn" name="generalDoc${stat.index}.btn"
                                                                                               class="btn btn-default btn-block btn-upload" value="올리기"
                                                                                               data-file-path="generalDocList${stat.index}.filePath"
                                                                                               data-file-name="generalDocList${stat.index}.fileName"
                                                                                               data-org-file-name="generalDocList${stat.index}.orgFileName"/>
                                                        </div>
                                                        <span class="col-sm-8" id="generalDoc${stat.index}" style="text-decoration: none;">
                                                            <a href="${contextPath}/filedownload/attached/${entireApplication.application.admsNo}/${entireApplication.application.applNo}/${entireApplication.generalDocList[stat.index].fileName}/${entireApplication.generalDocList[stat.index].orgFileName}">${entireApplication.generalDocList[stat.index].orgFileName}</a>
                                                        </span>

                                                    </div>
                                                </div>
                                                <form:hidden path="generalDocList[${stat.index}].docTypeCode" value="${customDoc.docTypeCode}" />
                                                <form:hidden path="generalDocList[${stat.index}].docGrp" value="0" />
                                                <form:hidden path="generalDocList[${stat.index}].docItemCode" value="${customDoc.docItemCode}" />
                                                <form:hidden path="generalDocList[${stat.index}].docItemName" value="${customDoc.itemName}" />
                                                <form:hidden path="generalDocList[${stat.index}].filePath"/>
                                                <form:hidden path="generalDocList[${stat.index}].fileName"/>
                                                <form:hidden path="generalDocList[${stat.index}].orgFileName"/>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="spacer-tiny"></div>
                            </c:if>

                            <c:if test="${common.fDegDocList.size() > 0}">
                                <div class="panel panel-darkgray">
                                    <div class="panel-heading">해외 학위 관련 서류</div>
                                    <div class="panel-body" id="foreignDegree-doc-list">
                                        <c:forEach items="${common.fDegDocList}" var="customDoc" varStatus="stat">
                                            <hr/>
                                            <div class="form-group" id="foreignDegreeDoc${customDoc.docItemCode}">
                                                <label class="col-sm-3 control-label word-keep-all">${customDoc.itemName}</label>
                                                <div class="col-sm-8">
                                                    <div class="input-group">
                                                        <div class="input-group-btn">
                                                            <input type="file" class="btn btn_lg btn-file" id="foreignDegreeDocList${stat.index}.docName" name="foreignDegreeDocList[${stat.index}].docName"/>
                                                        </div>
                                                        <div class="col-sm-4 nopadding"><input type="button" id="foreignDegreeDoc${stat.index}.btn" name="foreignDegreeDoc${stat.index}.btn"
                                                                                               class="btn btn-default btn-block btn-upload" value="올리기"
                                                                                               data-file-path="foreignDegreeDocList${stat.index}.filePath"
                                                                                               data-file-name="foreignDegreeDocList${stat.index}.fileName"
                                                                                               data-org-file-name="foreignDegreeDocList${stat.index}.orgFileName"/>
                                                        </div>
                                                        <span class="col-sm-8" id="foreignDegreeDoc${stat.index}" style="text-decoration: none;">
                                                            <a href="${contextPath}/filedownload/attached/${entireApplication.application.admsNo}/${entireApplication.application.applNo}/${entireApplication.foreignDegreeDocList[stat.index].fileName}/${entireApplication.foreignDegreeDocList[stat.index].orgFileName}">${entireApplication.foreignDegreeDocList[stat.index].orgFileName}</a>
                                                        </span>
                                                    </div>
                                                </div>
                                                <form:hidden path="foreignDegreeDocList[${stat.index}].docTypeCode" value="${customDoc.docTypeCode}" />
                                                <form:hidden path="foreignDegreeDocList[${stat.index}].docGrp" value="0" />
                                                <form:hidden path="foreignDegreeDocList[${stat.index}].docItemCode" value="${customDoc.docItemCode}" />
                                                <form:hidden path="foreignDegreeDocList[${stat.index}].docItemName" value="${customDoc.itemName}" />
                                                <form:hidden path="foreignDegreeDocList[${stat.index}].filePath"/>
                                                <form:hidden path="foreignDegreeDocList[${stat.index}].fileName"/>
                                                <form:hidden path="foreignDegreeDocList[${stat.index}].orgFileName"/>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="spacer-tiny"></div>
                            </c:if>

                            <c:if test="${common.collDocList.size() > 0}">
                                <div class="panel panel-darkgray">
                                    <div class="panel-heading">대학 학력 관련 서류</div>
                                    <div class="panel-body" id="college-doc-list">
                                        <div class="form-group-block-list" id="fuCollegeDocBlockList">
                                            <c:forEach items="${entireApplication.collegeList}" var="academy" varStatus="academyStat">
                                                <div class="form-group-block">
                                                    <c:forEach items="${common.collDocList}" var="customDoc" varStatus="stat">
                                                        <hr/>
                                                        <div class="form-group" id="collegeDoc${customDoc.docItemCode}">
                                                            <label class="col-sm-3 control-label word-keep-all">${customDoc.itemName}</label>
                                                            <div class="col-sm-8">
                                                                <div class="input-group">
                                                                    <div class="input-group-btn">
                                                                        <input type="file" class="btn btn_lg btn-file" id="collegeDocList${common.collDocList.size()*academyStat.index+stat.index}.docName" name="collegeDocList[${common.collDocList.size()*academyStat.index+stat.index}].docName"/>
                                                                    </div>
                                                                    <div class="col-sm-4 nopadding"><input type="button" id="collegeDoc${common.collDocList.size()*academyStat.index+stat.index}.btn" name="collegeDoc${common.collDocList.size()*academyStat.index+stat.index}.btn"
                                                                                                           class="btn btn-default btn-block btn-upload" value="올리기"
                                                                                                           data-file-path="collegeDocList${common.collDocList.size()*academyStat.index+stat.index}.filePath"
                                                                                                           data-file-name="collegeDocList${common.collDocList.size()*academyStat.index+stat.index}.fileName"
                                                                                                           data-org-file-name="collegeDocList${common.collDocList.size()*academyStat.index+stat.index}.orgFileName"/>
                                                                    </div>
                                                                    <span class="col-sm-8" id="collegeDoc${common.collDocList.size()*academyStat.index+stat.index}" style="text-decoration: none;">
                                                                        <a href="${contextPath}/filedownload/attached/${entireApplication.application.admsNo}/${entireApplication.application.applNo}/${entireApplication.collegeDocList[stat.index].fileName}/${entireApplication.collegeDocList[stat.index].orgFileName}">${entireApplication.collegeDocList[stat.index].orgFileName}</a>
                                                                    </span>
                                                                </div>
                                                            </div>
                                                            <form:hidden path="collegeDocList[${common.collDocList.size()*academyStat.index+stat.index}].docTypeCode" value="${customDoc.docTypeCode}" />
                                                            <form:hidden path="collegeDocList[${common.collDocList.size()*academyStat.index+stat.index}].docGrp" value="${academyStat.index}" />
                                                            <form:hidden path="collegeDocList[${common.collDocList.size()*academyStat.index+stat.index}].docItemCode" value="${customDoc.docItemCode}" />
                                                            <form:hidden path="collegeDocList[${common.collDocList.size()*academyStat.index+stat.index}].docItemName" value="${customDoc.itemName}" />
                                                            <form:hidden path="collegeDocList[${common.collDocList.size()*academyStat.index+stat.index}].filePath"/>
                                                            <form:hidden path="collegeDocList[${common.collDocList.size()*academyStat.index+stat.index}].fileName"/>
                                                            <form:hidden path="collegeDocList[${common.collDocList.size()*academyStat.index+stat.index}].orgFileName"/>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="spacer-tiny"></div>
                            </c:if>

                            <c:if test="${common.gradDocList.size() > 0}">
                                <div class="panel panel-darkgray">
                                    <div class="panel-heading">대학원 학력 관련 서류</div>
                                    <div class="panel-body" id="graduate-doc-list">
                                        <div class="form-group-block-list" id="fuGraduateDocBlockList">
                                            <c:forEach items="${entireApplication.graduateList}" var="academy" varStatus="academyStat">
                                                <div class="form-group-block">
                                                    <c:forEach items="${common.gradDocList}" var="customDoc" varStatus="stat">
                                                        <hr/>
                                                        <div class="form-group" id="graduateDoc${customDoc.docItemCode}">
                                                            <label class="col-sm-3 control-label word-keep-all">${customDoc.itemName}</label>
                                                            <div class="col-sm-8">
                                                                <div class="input-group">
                                                                    <div class="input-group-btn">
                                                                        <input type="file" class="btn btn_lg btn-file" id="graduateDocList${common.gradDocList.size()*academyStat.index+stat.index}.docName" name="graduateDocList[${common.gradDocList.size()*academyStat.index+stat.index}].docName"/>
                                                                    </div>
                                                                    <div class="col-sm-4 nopadding"><input type="button" id="graduateDoc${common.gradDocList.size()*academyStat.index+stat.index}.btn" name="graduateDoc${common.gradDocList.size()*academyStat.index+stat.index}.btn"
                                                                                                           class="btn btn-default btn-block btn-upload" value="올리기"
                                                                                                           data-file-path="graduateDocList${common.gradDocList.size()*academyStat.index+stat.index}.filePath"
                                                                                                           data-file-name="graduateDocList${common.gradDocList.size()*academyStat.index+stat.index}.fileName"
                                                                                                           data-org-file-name="graduateDocList${common.gradDocList.size()*academyStat.index+stat.index}.orgFileName"/>
                                                                    </div>
                                                                    <span class="col-sm-8" id="graduateDoc${common.gradDocList.size()*academyStat.index+stat.index}" style="text-decoration: none;">
                                                                        <a href="${contextPath}/filedownload/attached/${entireApplication.application.admsNo}/${entireApplication.application.applNo}/${entireApplication.graduateDocList[stat.index].fileName}/${entireApplication.graduateDocList[stat.index].orgFileName}">${entireApplication.graduateDocList[stat.index].orgFileName}</a>
                                                                    </span>
                                                                </div>
                                                            </div>
                                                            <form:hidden path="graduateDocList[${common.gradDocList.size()*academyStat.index+stat.index}].docTypeCode" value="${customDoc.docTypeCode}" />
                                                            <form:hidden path="graduateDocList[${common.gradDocList.size()*academyStat.index+stat.index}].docGrp" value="${academyStat.index}" />
                                                            <form:hidden path="graduateDocList[${common.gradDocList.size()*academyStat.index+stat.index}].docItemCode" value="${customDoc.docItemCode}" />
                                                            <form:hidden path="graduateDocList[${common.gradDocList.size()*academyStat.index+stat.index}].docItemName" value="${customDoc.itemName}" />
                                                            <form:hidden path="graduateDocList[${common.gradDocList.size()*academyStat.index+stat.index}].filePath"/>
                                                            <form:hidden path="graduateDocList[${common.gradDocList.size()*academyStat.index+stat.index}].fileName"/>
                                                            <form:hidden path="graduateDocList[${common.gradDocList.size()*academyStat.index+stat.index}].orgFileName"/>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="spacer-tiny"></div>
                            </c:if>

                            <%--<c:if test="${common.langDocList.size() > 0}">--%>
                                <%--<div class="panel panel-darkgray">--%>
                                    <%--<div class="panel-heading">어학 관련 서류</div>--%>
                                    <%--<div class="panel-body" id="language-doc-list">--%>
                                        <%--<c:forEach items="${common.langDocList}" var="customDoc" varStatus="stat">--%>
                                            <%--<hr/>--%>
                                            <%--<div class="form-group" id="languageDoc${customDoc.docItemCode}">--%>
                                                <%--<label class="col-sm-3 control-label word-keep-all">${customDoc.itemName}</label>--%>
                                                <%--<div class="col-sm-8">--%>
                                                    <%--<div class="input-group">--%>
                                                        <%--<div class="input-group-btn">--%>
                                                            <%--<input type="file" class="btn btn_lg btn-file" id="languageDocList${stat.index}.docName" name="languageDocList[${stat.index}].docName"/>--%>
                                                        <%--</div>--%>
                                                        <%--<div class="col-sm-4 nopadding"><input type="button" id="languageDoc${stat.index}.btn" name="languageDoc${stat.index}.btn"--%>
                                                                                               <%--class="btn btn-default btn-block btn-upload" value="올리기"--%>
                                                                                               <%--data-file-path="languageDocList${stat.index}.filePath"--%>
                                                                                               <%--data-file-name="languageDocList${stat.index}.fileName"--%>
                                                                                               <%--data-org-file-name="languageDocList${stat.index}.orgFileName"/>--%>
                                                        <%--</div>--%>
                                                        <%--<span class="col-sm-8" id="languageDoc${stat.index}" style="text-decoration: none;">--%>
                                                            <%--<a href="${contextPath}/filedownload/attached/${entireApplication.application.admsNo}/${entireApplication.application.applNo}/${entireApplication.languageDocList[stat.index].fileName}/${entireApplication.languageDocList[stat.index].orgFileName}">${entireApplication.languageDocList[stat.index].orgFileName}</a>--%>
                                                        <%--</span>--%>
                                                    <%--</div>--%>
                                                <%--</div>--%>
                                                <%--<form:hidden path="languageDocList[${stat.index}].docTypeCode" value="${customDoc.docTypeCode}" />--%>
                                                <%--<form:hidden path="languageDocList[${stat.index}].docGrp" value="0" />--%>
                                                <%--<form:hidden path="languageDocList[${stat.index}].docItemCode" value="${customDoc.docItemCode}" />--%>
                                                <%--<form:hidden path="languageDocList[${stat.index}].docItemName" value="${customDoc.itemName}" />--%>
                                                <%--<form:hidden path="languageDocList[${stat.index}].filePath"/>--%>
                                                <%--<form:hidden path="languageDocList[${stat.index}].fileName"/>--%>
                                                <%--<form:hidden path="languageDocList[${stat.index}].orgFileName"/>--%>
                                            <%--</div>--%>
                                        <%--</c:forEach>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="spacer-tiny"></div>--%>
                            <%--</c:if>--%>



                            <div class="spacer-tiny"></div>

                            <div class="panel panel-darkgray">
                                <div class="panel-heading">어학 관련 서류 업로드</div>
                                <div class="panel-body">
<span style="color:red">작업 필요</span>
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

                            <c:if test="${common.ariInstDocList.size() > 0}">
                                <div class="panel panel-darkgray">
                                    <div class="panel-heading">학·연·산 관련 서류</div>
                                    <div class="panel-body" id="ariInst-doc-list">
                                        <c:forEach items="${common.ariInstDocList}" var="customDoc" varStatus="stat">
                                            <hr/>
                                            <div class="form-group" id="ariInstDoc${customDoc.docItemCode}">
                                                <label class="col-sm-3 control-label word-keep-all">${customDoc.itemName}</label>
                                                <div class="col-sm-8">
                                                    <div class="input-group">
                                                        <div class="input-group-btn">
                                                            <input type="file" class="btn btn_lg btn-file" id="ariInstDocList${stat.index}.docName" name="ariInstDocList[${stat.index}].docName"/>
                                                        </div>
                                                        <div class="col-sm-4 nopadding"><input type="button" id="ariInstDoc${stat.index}.btn" name="ariInstDoc${stat.index}.btn"
                                                                                               class="btn btn-default btn-block btn-upload" value="올리기"
                                                                                               data-file-path="ariInstDocList${stat.index}.filePath"
                                                                                               data-file-name="ariInstDocList${stat.index}.fileName"
                                                                                               data-org-file-name="ariInstDocList${stat.index}.orgFileName"/>
                                                        </div>
                                                        <span class="col-sm-8" id="ariInstDoc${stat.index}" style="text-decoration: none;">
                                                            <a href="${contextPath}/filedownload/attached/${entireApplication.application.admsNo}/${entireApplication.application.applNo}/${entireApplication.ariInstDocList[stat.index].fileName}/${entireApplication.ariInstDocList[stat.index].orgFileName}">${entireApplication.ariInstDocList[stat.index].orgFileName}</a>
                                                        </span>
                                                    </div>
                                                </div>
                                                <form:hidden path="ariInstDocList[${stat.index}].docTypeCode" value="${customDoc.docTypeCode}" />
                                                <form:hidden path="ariInstDocList[${stat.index}].docGrp" value="1" />
                                                <form:hidden path="ariInstDocList[${stat.index}].docItemCode" value="${customDoc.docItemCode}" />
                                                <form:hidden path="ariInstDocList[${stat.index}].docItemName" value="${customDoc.itemName}" />
                                                <form:hidden path="ariInstDocList[${stat.index}].filePath"/>
                                                <form:hidden path="ariInstDocList[${stat.index}].fileName"/>
                                                <form:hidden path="ariInstDocList[${stat.index}].orgFileName"/>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="spacer-tiny"></div>
                            </c:if>

                            <c:if test="${common.fDocList.size() > 0}">
                                <div class="panel panel-darkgray">
                                    <div class="panel-heading">외국인 관련 서류</div>
                                    <div class="panel-body" id="foreigner-doc-list">
                                        <c:forEach items="${common.fDocList}" var="customDoc" varStatus="stat">
                                            <hr/>
                                            <div class="form-group" id="foreignerDoc${customDoc.docItemCode}">
                                                <label class="col-sm-3 control-label word-keep-all">${customDoc.itemName}</label>
                                                <div class="col-sm-8">
                                                    <div class="input-group">
                                                        <div class="input-group-btn">
                                                            <input type="file" class="btn btn_lg btn-file" id="foreignerDocList${stat.index}.docName" name="foreignerDocList[${stat.index}].docName"/>
                                                        </div>
                                                        <div class="col-sm-4 nopadding"><input type="button" id="foreignerDoc${stat.index}.btn" name="foreignerDoc${stat.index}.btn"
                                                                                               class="btn btn-default btn-block btn-upload" value="올리기"
                                                                                               data-file-path="foreignerDocList${stat.index}.filePath"
                                                                                               data-file-name="foreignerDocList${stat.index}.fileName"
                                                                                               data-org-file-name="foreignerDocList${stat.index}.orgFileName"/>
                                                        </div>
                                                        <span class="col-sm-8" id="foreignerDoc${stat.index}" style="text-decoration: none;">
                                                            <a href="${contextPath}/filedownload/attached/${entireApplication.application.admsNo}/${entireApplication.application.applNo}/${entireApplication.foreignerDocList[stat.index].fileName}/${entireApplication.foreignerDocList[stat.index].orgFileName}">${entireApplication.foreignerDocList[stat.index].orgFileName}</a>
                                                        </span>
                                                    </div>
                                                </div>
                                                <form:hidden path="foreignerDocList[${stat.index}].docTypeCode" value="${customDoc.docTypeCode}" />
                                                <form:hidden path="foreignerDocList[${stat.index}].docGrp" value="0" />
                                                <form:hidden path="foreignerDocList[${stat.index}].docItemCode" value="${customDoc.docItemCode}" />
                                                <form:hidden path="foreignerDocList[${stat.index}].docItemName" value="${customDoc.itemName}" />
                                                <form:hidden path="foreignerDocList[${stat.index}].filePath"/>
                                                <form:hidden path="foreignerDocList[${stat.index}].fileName"/>
                                                <form:hidden path="foreignerDocList[${stat.index}].orgFileName"/>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="spacer-tiny"></div>
                            </c:if>

                            <c:if test="${common.deptDocList.size() > 0}">
                                <div class="panel panel-darkgray">
                                    <div class="panel-heading">학과별 서류</div>
                                    <div class="panel-body" id="dept-doc-list">
<span style="color:red">동적 처리 필요</span>
                                        <c:forEach items="${common.deptDocList}" var="customDoc" varStatus="stat">
                                            <hr/>
                                            <div class="form-group" id="deptDoc${customDoc.docItemCode}">
                                                <label class="col-sm-3 control-label word-keep-all">${customDoc.itemName}</label>
                                                <div class="col-sm-8">
                                                    <div class="input-group">
                                                        <div class="input-group-btn">
                                                            <input type="file" class="btn btn_lg btn-file" id="deptDocList${stat.index}.docName" name="deptDocList[${stat.index}].docName"/>
                                                        </div>
                                                        <div class="col-sm-4 nopadding"><input type="button" id="deptDoc${stat.index}.btn" name="deptDoc${stat.index}.btn"
                                                                                               class="btn btn-default btn-block btn-upload" value="올리기"
                                                                                               data-file-path="deptDocList${stat.index}.filePath"
                                                                                               data-file-name="deptDocList${stat.index}.fileName"
                                                                                               data-org-file-name="deptDocList${stat.index}.orgFileName"/>
                                                        </div>
                                                        <span class="col-sm-8" id="deptDoc${stat.index}" style="text-decoration: none;">
                                                            <a href="${contextPath}/filedownload/attached/${entireApplication.application.admsNo}/${entireApplication.application.applNo}/${entireApplication.deptDocList[stat.index].fileName}/${entireApplication.deptDocList[stat.index].orgFileName}">${entireApplication.deptDocList[stat.index].orgFileName}</a>
                                                        </span>
                                                    </div>
                                                </div>
                                                <form:hidden path="deptDocList[${stat.index}].docTypeCode" value="${customDoc.docTypeCode}" />
                                                <form:hidden path="deptDocList[${stat.index}].docGrp" value="0" />
                                                <form:hidden path="deptDocList[${stat.index}].docItemCode" value="${customDoc.docItemCode}" />
                                                <form:hidden path="deptDocList[${stat.index}].docItemName" value="${customDoc.itemName}" />
                                                <form:hidden path="deptDocList[${stat.index}].filePath"/>
                                                <form:hidden path="deptDocList[${stat.index}].fileName"/>
                                                <form:hidden path="deptDocList[${stat.index}].orgFileName"/>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="spacer-tiny"></div>
                            </c:if>

                            <%--<div class="panel panel-darkgray">--%>
                                <%--<div class="panel-heading">지원 과정/학과별 제출 서류</div>--%>
                                <%--<div class="panel-body" id="attach-doc-list">--%>
                                    <%--<c:forEach items="${common.docItemList}" var="attachDoc" varStatus="stat">--%>
                                    <%--<hr/>--%>
                                    <%--<div class="form-group" id="attachDoc${attachDoc.code}">--%>
                                        <%--<label class="col-sm-3 control-label word-keep-all">${attachDoc.codeVal}</label>--%>
                                        <%--<div class="col-sm-8">--%>
                                            <%--<div class="input-group">--%>
                                                <%--<div class="input-group-btn">--%>
                                                    <%--<input type="file" class="btn btn_lg btn-file" id="applicationDocumentList${stat.index}.docName" name="applicationDocumentList${stat.index}.docName"/>--%>
                                                <%--</div>--%>
                                                <%--<div class="col-sm-4 nopadding"><input type="button" id="attachDoc${stat.index}.btn" name="attachDoc${stat.index}.btn" class="btn btn-default btn-block btn-upload" value="올리기"/></div>--%>
                                                <%--<span class="col-sm-8" id="uploadedFileLabel${stat.index}" style="text-decoration: none;"><!--TODO DB에서 가져오기--></span>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <%--&lt;%&ndash;<form:hidden path="docItemList[${stat.index}].docItemCode" value="${attachDoc.code}"/>&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;<form:hidden path="docItemList[${stat.index}].docItemName" value="${attachDoc.codeVal}"/>&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;<form:hidden path="docItemList[${stat.index}].filePath"/>&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;<form:hidden path="docItemList[${stat.index}].fileName"/>&ndash;%&gt;--%>
                                        <%--<input type="hidden" name="docItemList[${stat.index}].docItemCode" id="applicationDocumentList${stat.index}.docItemCode" value="${attachDoc.code}" />--%>
                                        <%--<input type="hidden" name="docItemList[${stat.index}].docItemName" id="applicationDocumentList${stat.index}.docItemName" value="${attachDoc.codeVal}" />--%>
                                        <%--<input type="hidden" name="docItemList[${stat.index}].filePath" id="applicationDocumentList${stat.index}.filePath"/>--%>
                                        <%--<input type="hidden" name="docItemList[${stat.index}].fileName" id="applicationDocumentList${stat.index}.fileName"/>--%>
                                    <%--</div>--%>
                                    <%--</c:forEach>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        </div>
                    </div>
                    <div class="spacer-tiny"></div>
                </div>
            </div> <%--myTabContent--%>
            <input type="hidden" id="applNo"/>
            <input type="hidden" id="admsNo"/>
        </form:form>

        <div class="btn-group btn-group-justified">
            <div class="btn-group">
                <button id="save" type="button" class="btn btn-info btn-lg">저장</button>
            </div>
            <div class="btn-group">
                <button id="apply" type="button" class="btn btn-primary btn-lg">작성완료</button>
            </div>
            <div class="btn-group">
                <button id="reset" type="button" class="btn btn-warning btn-lg">되돌리기</button>
            </div>
        </div>
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
    <div id="postLayer" style="display:none;border:5px solid;position:fixed;width:300px;height:460px;left:50%;margin-left:-155px;top:50%;margin-top:-235px;overflow:hidden;-webkit-overflow-scrolling:touch;z-index:2;background-color:#fff;color: #111;">
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
//            $('.btn-file :file').on('change', function() {
//                var input = $(this),
//                        numFiles = input.get(0).files ? input.get(0).files.length : 1,
//                        label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
//                input.trigger('fileselect', [numFiles, label]);
//            });
//
//            $('.btn-file :file').on('fileselect', function(event, numFiles, label) {
//                var input = $(this).parents('.input-group').find(':text'),
//                        log = numFiles > 1 ? numFiles + ' files selected' : label;
//
//                if( input.length ) {
//                    input.val(log);
//                } else {
//                    if( log ) alert(log);
//                }
//
//            });
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

            <%-- 지원정보 submit 이벤트 --%>


            var formProcess = function(event) {
                var $form = $(this),
                    $formUrl = event.type==='save'?"apply/save":"apply/apply",
                    gotoMyList = event.type==='apply'?true:false,
                    $formData = $form.serializeArray();
                if (gotoMyList) {
                    $('#save').prop('disabled', true);
                    $('#apply').prop('disabled', true);
                    $('#reset').prop('disabled', true);
                }
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
                            var message = context.message,
                                alert = createAlert(message),
                                applNo = context.data;
                            $('#alert-container').append(alert);
                            document.getElementById('applNo').value = applNo;
                            window.setTimeout(function() {
                                alert.alert('close');
                                if (gotoMyList) {
                                    location.href="${contextPath}/application/mylist";
                                }
                            }, 2000);
                        }
                    },
                    error: function(e) {
console.log(e.statusText);
                    }
                });
                event.preventDefault();
            };

            $('#entireApplication').on('save', formProcess);

            $('#entireApplication').on('apply', formProcess);

            $('#save').on('click', function() {
                $('#entireApplication').trigger('save');
            });

            $('#apply').on('click', function() {
                $('#entireApplication').trigger('apply');
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
                                return this.value !== '';
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
                    $divNode = $('<div></div>').addClass('col-sm-offset-2 col-sm-9').attr({
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
                }
            });

            <%-- 세부전공 변경 시 어학 변경 --%>
//            $('#detlMajCode').on('change', function(event) {
//                updateLanguageForm();
//            });

            $('#detlMajCode').trigger('change');
            <%-- 지원사항 select 폼 change 이벤트 핸들러 등록 끝 --%>

            <%-- 어학 및 경력 탭 선택 시 어학 변경 시작 --%>
            $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
                var currTab = e.target; // 활성화된 탭
                var prevTab = e.relatedTarget; // 이전 탭
                var currPanel = $(currTab.getAttribute('href'))[0];
                if (currPanel.getAttribute('id') === 'langcareer') {
                    updateLanguagePanel();
                }
            });

            function updateLanguagePanel() {
                var detlMajCode = $('#detlMajCode').val();
                if (detlMajCode === '-') {
                    var groups = $('#english-score-list').children('.form-group');
                    for (var i = 0, len = groups.length; i < len; i++) {
                        updateLanguageGroup(groups[i], []);
                    }
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

            <%-- checkbox hide/show --%>
            function updateLanguageGroup(group, data) {
                var langExamCode = $(group).find('input').filter('[name$="langExamCode"]')[0];
                var check = $(group).find('.btn-lang, .btn-lang-disabled')[0];
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
                            }
                            break;
                        }
                    }
                    if (!isExist) {
                        check.className = 'btn-lang-disabled';
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
                        $(targetExamName).css("display", "block") :
                        $(targetExamName).css("display", "none");

            });

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
                    if (fileInputId === 'generalDocList0.docName') {
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
                                if (console) {
                                    console.log("targetButton : ", d.targetButton);
                                    console.log("targetLabel : ", d.targetLabel);
                                    console.log("applNo : ", d.applNo);
                                    console.log("admsNo : ", d.admsNo);
                                    console.log("originalFileName : ", d.originalFileName);
                                    console.log("filePath : ", d.filePath);
                                    console.log("fileName : ", d.fileName);
                                    console.log("data : ", data.data);
                                    console.log("status : ", status);
                                }
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


    </script>
</content>
</body>
</html>
