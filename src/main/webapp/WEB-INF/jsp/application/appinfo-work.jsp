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
                                        <form:label path="mailAddr" cssClass="col-sm-3 control-label">E-mail</form:label>
                                        <div class="col-sm-9">
                                            <form:input path="mailAddr" cssClass="form-control" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">대학교</div>
                                <div class="panel-body">
                                    <div id="college-container">
                                        <div class="collegeInfo">
                                            <hr/>
<%-- TODO 국가 검색 팝업 블록 --%>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">소재 국가<br/>Country</label>
                                                <div class="btn btn-default btn-md col-md-2 bpopper" data-targetNode1="cntrCode1" data-targetNode2='korCntrName1' data-targetNode3='engCntrName1' data-category="country">검색</div>
                                                <div class="col-sm-6">
                                                    <input type="hidden" name="cntrCode" id="cntrCode1"/>
                                                    <input name="korCntrName" class="form-control" id="korCntrName1" readonly/>
                                                    <input name="engCntrName" class="form-control" id="engCntrName1" readonly/>
                                                </div>
                                            </div>
<%-- TODO 국가 검색 팝업 블록 --%>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">학교 이름</label>
                                                <div class="btn btn-default btn-md col-md-2">검색</div>
                                                <div class="col-sm-4">
                                                    <input type="hidden" name="schlCode" id="schlCode1" />
                                                    <input name="schlName" class="form-control" id="schlName1" readonly/>
                                                </div>
                                                <div class="col-sm-2">
                                                    <label class="radio inline">
                                                        <input type="radio" name="lastSchlYn" id="lastSchlYn1" class="lastSchl">&nbsp;&nbsp;최종 학교
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div id="addCollege1" class="addCollege col-sm-2 btn btn-info">입력란 추가</div>
                                                <div id="removeCollege1" class="removeCollege col-sm-2 btn btn-danger">입력란 삭제</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="spacer-tiny"></div>
                        <%--</form:form>--%>
                </div>
                    <%--selfintro--%>

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
        <%--<div id="postLayer" style="display:none;border:3px solid;position:fixed;width:300px;height:460px;left:50%;margin-left:-155px;top:50%;margin-top:-235px;overflow:hidden;-webkit-overflow-scrolling:touch;">--%>
        <%--<img src="${contextPath}/img/user/addr-close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px" alt="닫기 버튼">--%>
        <%--</div>--%>
    </div> <%--container--%>

    <%--TODO 국가 검색 팝업 --%>
    <div id="bpopContainerCountry">
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
    <input type="hidden" id="targetNode1" />
    <input type="hidden" id="targetNode2" />
    <input type="hidden" id="targetNode3" />
    <%--TODO 국가 검색 팝업 --%>

</section>
<content tag="local-script">
    <%--<script src="${contextPath}/js/postcode.js"></script>--%>
    <script src="http://dmaps.daum.net/map_js_init/postcode.js"></script>
    <%--<script src="${contextPath}/js/bootstrap-datepicker.js"></script>--%>
    <%--<script src="${contextPath}/js/bootstrap-datepicker.kr.js"></script>--%>
    <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {


            $('#myTab a:first').tab('show');

<%-- TODO 국가 검색 시작 --%>
            $('.bpopper').on('click', function (e) {
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
                    url: '${contextPath}/common/code/country/'+$('#bpopCntr').val(),
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
                                    document.getElementById(targetInputId[i]).value = tr.children[i].firstChild.textContent;
                                }

                            });
                        }
                    }
                });
            });
<%-- TODO 국가 검색 끝 --%>


            <%-- 최종 대학 체크 처리 시작 --%>
            var setLastSchool = function (e) {
                var i, l, radioList = document.getElementsByClassName('lastSchl');
                for (i = 0, l = radioList.length; i < l; i++) {
                    radioList[i].value = "N";
                }
                e.target.value = "Y";
            }
            $('.lastSchl').on('click', setLastSchool);
            <%-- 최종 대학 체크 처리 끝 --%>


            <%-- 대학, 대학원 입력란 동적 처리 시작 --%>
            <%-- 추가/삭제 버튼의 부모의 부모가 복사할 블록이어야 하고--%>
            <%-- 복사한 블록은 바로 위의 부모에 append 함--%>
            var addInputBlock = function (e) {
                var originalBlock = e.target.parentNode.parentNode,
                        cloneObj, targetParent = originalBlock.parentNode;
//                $('.calendar').datepicker('destroy');
                cloneObj = $(originalBlock).clone(true);
                incrementChildren(cloneObj, targetParent.children.length + 1);
                $(targetParent).append(cloneObj);
//                $('.calendar').datepicker(datePickerOption);

            };

            var removeInputBlock = function (e) {
                var blockToRemove, parentOfBlock, nOfBlocks;
                blockToRemove = e.target.parentNode.parentNode;
                parentOfBlock = blockToRemove.parentNode;
                nOfBlocks = parentOfBlock.children.length;
                if ((nOfBlocks - 1) < 1) alert('더 이상 삭제할 수 없습니다.');
                else {
                    $(blockToRemove).remove();
                }
            };


            <%-- o 내의 모든 children의 id 값 마지막 숫자를 n으로 변경, value를 ""로 --%>
            var incrementChildren = function (o, n) {

                var childList = o instanceof jQuery ? o.children() : o.children,
                        i, j, l, t0, tid, targetNode = [],
                        targetNodeId1, targetNodeId2, targetNodeId3;

                if (childList) {
                    for (i = 0, l = childList.length; i < l; i++) {
                        t0 = childList[i];
                        if (t0.id) {
                            tid = t0.id.toString();
                            t0.id = tid.substring(0, tid.length - 1) + n.toString();
                        }
                        if (t0.value) t0.value = "";
                        if (t0.type == 'radio') t0.checked = false;
                        if (t0.hasAttribute('data-targetNode1')) {
                            for ( j = 0 ; j <= 2 ; j++ ) {
                                targetNode[i] = t0.getAttribute('data-targetNode'+(j+1));
                                if ( targetNode[i] )
                                    t0.setAttribute('data-targetNode'+(j+1),targetNode[i].substring(0, targetNode[i].length-1) + n.toString());
                            }
                        }
                        incrementChildren(t0, n);
                    }
                }
            };

            // IE8 처리
            var handleAddEventListener = function (target, event, handler) {
                if (window.addEventListener) {
                    target.addEventListener(event, handler);
                }
                else {
                    target.attachEvent('on' + event, handler);
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
                    oncomplete: function (data) {
                        // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                        // 우편번호와 주소 및 영문주소 정보를 해당 필드에 넣는다.
                        document.getElementById('postcode1').value = data.postcode1;
                        document.getElementById('postcode2').value = data.postcode2;
                        document.getElementById('address').value = data.address;
                        document.getElementById('addressDetail').focus();
                        // iframe을 넣은 element를 안보이게 한다.
//                        closeDaumPostcode();
                    },
                    width: '100%',
                    height: '100%'
                }).open();

                // iframe을 넣은 element를 보이게 한다.
//                postLayer.style.display = 'block';
            };

            $('#btnCloseLayer').on('click', closeDaumPostcode);

            $('#searchAddress').on('click', showDaumPostcode);
            <%-- 다음 주소 검색 끝 --%>

        });


    </script>
</content>
</body>
</html>
