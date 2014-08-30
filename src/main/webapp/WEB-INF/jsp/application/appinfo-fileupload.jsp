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
            </div>
            <div class="tab-pane fade" id="selfintro">
                <div class="spacer-tiny"></div>
            </div>
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
                    <div class="spacer-tiny"></div>
                </div>
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

    <%-- TODO 학교 검색 시작 --%>
    $('.bpoppear').on('click', function (e) {
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
        yearRange: "1950:",
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
                items[i].value = "";

                label = block.querySelector('label[for="' + name + '"]');
                if (label) {
                    label.setAttribute('for', items[i].id);
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
//                        closeDaumPostcode();
            },
            width : '100%',
            height : '100%'
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

    /*고등학교 졸업/검정고시 동적 변경 시작*/
    $('input[name="highSchool.acadTypeCode"]').change(function() {
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
    $('input[name="highSchool.acadTypeCode"]').eq(0).click();
    /*고등학교 졸업/검정고시 동적 변경 시작*/

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
    function attachChangeEvent( sourceId, context, valueKey, labelKey, appendUrl ) {
        var $source = $('#' + sourceId);
        $source.change( function(event) {
            var url = '${contextPath}/common/code', targetId, info;

            if( typeof(context) === 'string' ) {
                targetId = context;
            } else if( typeof(context) === 'object' ) {
                info = context[$source.val()];
                if( !info ) {
                    info = context.other;
                }
                if( info ) {
                    targetId = info.targetId ? info.targetId : '';
                    valueKey = info.valueKey ? info.valueKey : valueKey;
                    labelKey = info.labelKey ? info.labelKey : labelKey;
                    appendUrl = info.url ? info.url : appendUrl;
                }
            }

            if( !targetId ) {
                return;
            }

            if( appendUrl && typeof(appendUrl) === 'function' ) {
                url += appendUrl($source.val());
            } else if( appendUrl ) {
                url += appendUrl;
            }

            $.ajax({
                type: 'GET',
                url: url,
                success: function(e) {
                    if(e.result && e.result === 'SUCCESS') {
                        var target = $('#' + targetId);
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
    attachChangeEvent( 'applAttrCode',
            {
                other: {targetId: 'campCode', valueKey: 'campCode', labelKey: 'campName', url: '/campus'},
                '02': {targetId: 'ariInstCode', valueKey: 'ariInstCode', labelKey: 'ariInstName', url: '/ariInst'}
            }
    );

    // 캠퍼스 변경
    attachChangeEvent( 'campCode', 'collCode', 'collCode', 'collName',
            function(args) {
                return '/college/' + args;
            }
    );

    // 대학변경
    attachChangeEvent( 'collCode', 'deptCode', 'deptCode', 'deptName',
            function(args) {
                var admsNo = '${entireApplication.admsNo}';
                return '/general/department/' + admsNo + '/' + args;
            }
    );

    // 학연산 변경
    attachChangeEvent( 'ariInstCode', 'deptCode', 'deptCode', 'deptName',
            function(args) {
                var admsNo = '${entireApplication.admsNo}';
                return '/ariInst/department/' + admsNo + '/' + args;
            }
    );

    // 지원학과 변경
    attachChangeEvent( 'deptCode', 'corsTypeCode', 'corsTypeCode', 'codeVal',
            function(args) {
                var admsNo = '${entireApplication.admsNo}';
                var ariInstCode = $('#ariInstCode:visible').val();
                if( ariInstCode && ariInstCode != '-') {
                    return '/ariInst/course/' + admsNo + "/" + args + "/" + ariInstCode;
                }
                return '/general/course/' + admsNo + '/' + args;
            }
    );
    /*지원사항 select 폼 change 이벤트 핸들러 등록 끝*/

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