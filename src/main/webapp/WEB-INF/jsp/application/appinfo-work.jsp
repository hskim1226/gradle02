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

        /* 팝업창이 보여질 부분 */
        #bpopContainerCountry, #popup2, .bMulti {
            background-color: #fff;
            color: #111;
            display: none;
            min-width: 450px;
            padding: 25px;
        }

        #bpopContainerCountry, .bMulti {
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
                                        <hr/>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">소재 국가</label>
                                            <div class="btn btn-default btn-md col-md-2 bpopper" data-textnode="korCntrName1">검색</div>
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
                                                    <input type="text" class="form-control calendar" name="entrDay" id="entrDay1" readonly/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4 end-date-container">
                                                <div class="input-group date" id="divCalGrdaDay1">
                                                    <span class="input-group-addon">졸업일</span>
                                                    <input type="text" class="form-control calendar" name="grdaDay" id="grdaDay1" readonly/>
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


                    <div class="spacer-tiny"></div>
            <%--</form:form>--%>
            </div>
            <%--selfintro--%>
            <div class="tab-pane fade" id="selfintro">

                    <div class="spacer-tiny"></div>
            </div>
            <%--studyplan--%>
            <div class="tab-pane fade" id="studyplan">

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


    <div id="bpopContainerCountry">
        <span class="button b-close"><span>X</span></span>
        <div id="bpopContentCountry">
            <input type="text" id="bpopCntr" name="cntr" />
            <button id="bpopBtnSearchCountry">검색</button>
            <button id="bpopBtnResultCountry">호출처에 값세팅</button>
            <div id="bpopResultCountry"></div>
        </div>
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
            $('#entrDay1, #grdaDay1').datepicker(datePickerOption);
            <%-- 달력 끝 --%>


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

            <%-- bpopup --%>
            $('.bpopper').on('click', function(e) {
                e.preventDefault();
                var popup = $('#bpopContainerCountry'),
                        content = $('#bpopContentCountry'),
                        self = $(this),
                        targetInputId = self.attr('data-textnode');

//                popup.bPopup(self.data('bpopup') || {});
                popup.bPopup(self.data('targetInputId') || {});
            });

            $('#bpopBtnResultCountry').on('click', function(e) {
console.log(e.target);
console.log(e.target.getAttribute('data-textnode'));
                var targetInputId = e.target.getAttribute("data-textnode");
                document.getElementById('korCntrName1').value=document.getElementById(targetInputId).value;
            })
        });

    </script>
</content>
</body>
</html>
