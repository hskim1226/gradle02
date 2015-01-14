<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang="ko">
<head>
    <title>원서 작성 - 학력 정보</title>
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
            font-size: 20px;
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
    </style>
</head>
<body>
<section class="application">
    <div class="container">
        <div id="stepContainer">
            <table width="100%">
                <tr>
                    <td id="stepStatusTitle" colspan=4 align="center" height="50px">원서 작성 현황</td>
                </tr>
                <tr id="stepTR">
                    <td id="stepBasis" width="25%" height="30px" align="center" class="stepDisabled">1. 기본 정보</td>
                    <td id="stepAcademy" width="25%" height="30px" align="center" class="stepDisabled">2. 학력 정보</td>
                    <td id="stepLangCareer" width="25%" height="30px" align="center" class="stepDisabled">3. 어학/경력 정보</td>
                    <td id="stepFileUpload" width="25%" height="30px" align="center" class="stepDisabled">4. 파일 첨부</td>
                </tr>
            </table>
        </div>
        <div class="spacer-mid"></div>
        <div class="row">
            <div class="col-sm-12">
                <table width="100%">
                    <tr id="tabTR">
                        <td id="tab-basis" width="25%" height="35px" align="center" class="inactiveTab" data-target-tab="basis" data-tab-available="true">기본 정보</td>
                        <td id="tab-academy" width="25%" height="35px" align="center" class="inactiveTab" data-target-tab="academy" data-tab-available="false" data-unavailable-msg='<spring:message code="U321"/>'>학력 정보</td>
                        <td id="tab-langCareer" width="25%" height="35px" align="center" class="inactiveTab" data-target-tab="langCareer" data-tab-available="false" data-unavailable-msg='<spring:message code="U322"/>'>어학/경력 정보</td>
                        <td id="tab-fileUpload" width="25%" height="35px" align="center" class="inactiveTab" data-target-tab="fileUpload" data-tab-available="false" data-unavailable-msg='<spring:message code="U323"/>'>파일 첨부</td>
                    </tr>
                </table>
            </div>
        </div>
        <form:form commandName="academy" cssClass="form-horizontal" method="post" role="form">
            <form:hidden path="application.applNo" id="applNo" />
            <form:hidden path="application.applStsCode" id="applStsCode" />
            <form:hidden path="application.admsNo" id="admsNo" />
            <form:hidden path="application.entrYear" id="entrYear" />
            <form:hidden path="application.admsTypeCode" id="admsTypeCode" />
            <div id="myTabContent" class="tab-content">
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
                        <button id="saveAcademy" type="button" class="btn btn-primary btn-lg btn-save">학력 저장</button>
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

</section>
<content tag="local-script">
    <script src="${contextPath}/js/jquery-ui.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
        var applNo = document.getElementById('applNo').value = '${academy.application.applNo}',
            admsNo = document.getElementById('admsNo').value = '${academy.application.admsNo}',
            applStsCode = document.getElementById('applStsCode').value = '${academy.application.applStsCode}',
            entrYear = document.getElementById('entrYear').value = '${academy.application.entrYear}',
            admsTypeCode = document.getElementById('admsTypeCode').value = '${academy.application.admsTypeCode}';

        <%-- 원서 작성 현황 처리 --%>
        var processCurrentStep = function (applStsCode) {
            var code = Number(applStsCode),
                    stepTR = document.getElementById('stepTR'),
                    l = stepTR.children.length, i,
                    tabTR = document.getElementById('tabTR');
            for ( i = 0 ; i < code && i < l ; i++ ) {
                stepTR.children[i].className = 'stepEnabled';
                tabTR.children[i].setAttribute('data-tab-available', 'true');
                tabTR.children[i+1].setAttribute('data-tab-available', 'true');
            }
        };
        processCurrentStep(document.getElementById('applStsCode').value);
        <%-- 원서 작성 현황 처리 --%>

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

        <%-- 하단 버튼 처리 --%>
        var formProcess = function(event) {
            var form = document.forms[0];

            form.action = "${contextPath}/application/academy/save";
            form.submit();
        };
        $('.btn-save').on('click', formProcess);
        <%-- 하단 버튼 처리 --%>

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

        <%-- bootstrapValidator --%>
        $('#academy').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
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
        <%-- bootstrapValidator --%>

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

//            // 파일업로드 부분
//            var fileUploadContainer = document.getElementById(target.getAttribute("data-fileupload-block-list"));
//            var fuBlocks = fileUploadContainer.querySelectorAll('.form-group-block');
//            var fuOriginBlock = fuBlocks[fuBlocks.length - 1];
//            var $fuCloneObj;
//            if (fuOriginBlock) {
//                $fuCloneObj = $(fuOriginBlock).clone(true);
//                updateIdAndName($fuCloneObj[0], fuBlocks.length);
//                eraseContents($fuCloneObj[0]);
//                fileUploadContainer.insertBefore($fuCloneObj[0], fuOriginBlock.nextSibling);
//            }
//
////                $('#entireApplication').bootstrapValidator('addFiend', $cloneObj);
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
