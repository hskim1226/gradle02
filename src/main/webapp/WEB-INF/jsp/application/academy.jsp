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

        /*a[disabled] {*/
            /*pointer-events: none;*/
        /*}*/
    </style>
</head>
<body>
<section class="application">
    <div class="container">
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
                        <button id="saveAcademy" type="button" class="btn btn-primary btn-lg btnAppl disabled" data-saveType="academy">학력 저장</button>
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
        document.getElementById('applNo').value='${basis.application.applNo}';
        document.getElementById('admsNo').value='${basis.application.admsNo}';
        document.getElementById('applStsCode').value='${basis.application.applStsCode}';
        document.getElementById('entrYear').value='${basis.application.entrYear}';
        document.getElementById('admsTypeCode').value='${basis.application.admsTypeCode}';

        <%-- 하단 버튼 처리 --%>
        var formProcess = function(event) {
            var $form = $(this),
                form = document.getElementById('academy'),
                isValidProcess = true;

            if ($('#baseSave').css('display') == 'block') {
                isValidProcess = false;
                alert('<spring:message code="U329"/>');
                $('#applAttrCode').focus();
            } else {
                form.action = "${contextPath}/application/academy/save";
                form.submit();
            }
            event.preventDefault();
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

        <%-- 처음 탭 표시 --%>
        $('#myTab a:first').tab('show');

        <%-- BootStrap Validator --%>
        var numericValidator = {
            numeric: {
                separator: '',
                message: '<spring:message code="U305"/>'
            }
        };

        <%-- bootstrapValidator --%>
        $('#basis').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                "application.rgstNo": {
                    validators: {
                        regexp: {
                            regexp: '/^\d{13}/',
                            message: '<spring:message code="U304"/>'
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
                }
            }
        });

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
