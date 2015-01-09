<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<%--// TODO 제3자 동의여부 : ${providePrivateInfo} - 0 : 동의, 1 : 비동의--%>
<html>
<head>
    <title></title>
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
    <%--body의 글자 속성을 #333333으로 강제 지정하여 Footer 글자가 안나옴, 꼭 필요하지 않으면 안쓰기로
    <link rel="stylesheet" href="${contextPath}/css/bootstrap-glyphicons.css" />--%>
</head>
<body>
<section class="application">
    <div class="container">
        <form:form commandName="basis" cssClass="form-horizontal" method="post" role="form">
            <form:hidden path="application.applNo" id="applNo" />
            <form:hidden path="application.applStsCode" id="applStsCode" />
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
                                            <form:select path="application.applAttrCode" id="applAttrCode" cssClass="form-control base-info">
                                                <form:option value="" label="--선택--" />
                                                <form:options items="${common.applAttrList}" itemValue="code" itemLabel="codeVal"/>
                                            </form:select>
                                        </div>
                                    </div>
                                    <div id="applyKindDynamic">
                                        <div class="form-group hidden-apply-kind-2 required">
                                            <label path="campCode" class="col-sm-2 control-label">캠퍼스</label>
                                            <div class="col-sm-3">
                                                <form:select path="application.campCode" id="campCode" cssClass="form-control base-info">
                                                    <form:option value="" label="--선택--" />
                                                    <c:if test="${entireApplication.application.applAttrCode == '00001' || entireApplication.application.applAttrCode == '00003'}">
                                                    <form:options items="${common.campList}" itemValue="campCode" itemLabel="campName" />
                                                    </c:if>
                                                </form:select>
                                            </div>
                                            <label path="collCode" class="col-sm-2 control-label">대학</label>
                                            <div class="col-sm-4">
                                                <form:select path="application.collCode" id="collCode" cssClass="form-control base-info">
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
                                                <form:select path="application.ariInstCode" id="ariInstCode" cssClass="form-control base-info">
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
                                                <form:select path="application.deptCode" id="deptCode" cssClass="form-control base-info">
                                                    <form:option value="" label="--선택--" />
                                                    <form:options items="${common.deptList}" itemValue="deptCode" itemLabel="deptName" />
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group required">
                                            <label for="corsTypeCode" class="col-sm-2 control-label">지원 과정</label>
                                            <div class="col-sm-9">
                                                <form:select path="application.corsTypeCode" id="corsTypeCode" cssClass="form-control base-info">
                                                    <form:option value="" label="--선택--" />
                                                    <form:options items="${common.corsTypeList}" itemValue="corsTypeCode" itemLabel="codeVal" />
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group required">
                                            <label for="detlMajCode" class="col-sm-2 control-label">세부 전공</label>
                                            <div class="col-sm-9">
                                                <form:select path="application.detlMajCode" id="detlMajCode" cssClass="form-control base-info">
                                                    <form:option value="" label="--선택--" />
                                                    <form:options items="${common.detlMajList}" itemValue="detlMajCode" itemLabel="detlMajName" />
                                                </form:select>
                                                <label id="detMajDesc" class="apexMessage"></label>
                                                <form:input path="application.inpDetlMaj" cssClass="form-control" style="display:none" />
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="col-sm-11" id="baseSave">
                                            <span class="col-sm-8"><spring:message code="U310"/></span>
                                            <button id="btnBaseSave" class="btn btn-info btn-lg col-sm-4">지원사항 저장</button>
                                        </div>
                                        <div class="col-sm-11" id="baseCancel" style="display:none;">
                                            <span class="col-sm-8"><spring:message code="U311"/></span>
                                            <button id="btnBaseCancel" class="btn btn-warning btn-lg col-sm-4">지원사항 취소</button>
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
                                                <form:input path="application.engSur" cssClass="form-control engName" />
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="input-group">
                                                <span class="input-group-addon">이름</span>
                                                <form:input path="application.engName" cssClass="form-control engName" />
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

                            <div class="panel panel-default">
                                <div class="panel-heading">지원자 상세정보</div>
                                <div class="panel-body">
                                    <div class="form-group required">
                                        <label for="citzCntrName" class="col-sm-2 control-label">국적</label>
                                        <div class="col-sm-2">
                                            <button type="button" class="btn btn-default btn-search bpopper" data-targetNode1="citzCntrCode" data-targetNode2='citzCntrName' data-category="country">
                                                <span class="glyphicon glyphicon-search"></span> 검색
                                            </button>
                                        </div>
                                        <div class="col-sm-7">
                                            <form:hidden path="application.citzCntrCode" id="citzCntrCode" cssClass="form-control" />
                                            <input id="citzCntrName" class="form-control" value="${common.country.korCntrName}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">장애 사항</label>
                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">장애유형</span>
                                                <form:input path="applicationGeneral.hndcGrad" cssClass="col-sm-6 form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-9">
                                            <div class="input-group">
                                                <span class="input-group-addon">장애등급</span>
                                                <form:input path="applicationGeneral.hndcType" cssClass="col-sm-6 form-control" />
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
                                            <button type="button" class="btn btn-default btn-block btn-search" id="searchAddress">
                                                <span class="glyphicon glyphicon-search"></span> 우편번호 찾기
                                            </button>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="input-group">
                                                <form:input path="application.zipCode" cssClass="form-control" id="zipCode" readonly="true"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-offset-2 col-sm-4">
                                            <form:input path="application.addr" cssClass="form-control" id="address" readonly="true" />
                                        </div>
                                        <div class="col-sm-5">
                                            <form:input path="application.detlAddr" cssClass="form-control" id="addressDetail" placeholder="세부주소" />
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <form:label path="application.telNum" cssClass="col-sm-2 control-label">전화번호</form:label>
                                        <div class="col-sm-9">
                                            <form:input path="application.telNum" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group required">
                                        <form:label path="application.mobiNum" cssClass="col-sm-2 control-label">휴대폰</form:label>
                                        <div class="col-sm-9">
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
                                            <form:input path="applicationGeneral.emerContTel" cssClass="form-control" />
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
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
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
                            </div><%--panel--%>
                        </div>
                    </div><%--row--%>
                    <div class="btn-group btn-group-justified">
                        <div class="btn-group">
                            <button id="saveBasis" type="button" class="btn btn-info btn-lg btn-save">기본 정보 저장</button>
                        </div>
                    </div>
                </div><%--basis--%>
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

    <%-- 다음 주소 검색 팝업 --%>
    <div id="postLayer" style="display:none;border:5px solid;position:fixed;width:310px;height:510px;left:50%;margin-left:-155px;top:50%;margin-top:-235px;overflow:hidden;-webkit-overflow-scrolling:touch;z-index:2;background-color:#fff;color: #111;">
        <img src="${contextPath}/img/user/addr-close.png" id="btnClosePostLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px" alt="닫기 버튼">
    </div>

</section>
<content tag="local-script">
    <script src="http://dmaps.daum.net/map_js_init/postcode.js"></script>
    <script src="${contextPath}/js/jquery-ui.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
        document.getElementById('applNo').value='${entireApplication.application.applNo}';
        document.getElementById('admsNo').value='${entireApplication.application.admsNo}';
        document.getElementById('applStsCode').value='${entireApplication.application.applStsCode}';
        document.getElementById('entrYear').value='${entireApplication.application.entrYear}';
        document.getElementById('admsTypeCode').value='${entireApplication.application.admsTypeCode}';

        <%-- 지원 사항 저장 버튼 처리 --%>
        var baseInfoSaved = function() {
            $('.base-info>option').filter( function() {
                return !this.selected;
            }).each( function() {
                $(this).prop('disabled', true);
            });

            $('#baseCancel').css('display', 'block');
            $('#baseSave').css('display', 'none');
        };

        if (document.getElementById('applNo').value != "") {
            baseInfoSaved();
        }
        <%-- 지원 사항 저장 버튼 처리 --%>

        <%-- 기본 정보 > 지원 사항 처리 --%>
        $('#btnBaseSave').on('click', function(e) {
            if ( confirm('<spring:message code="U313"/>') ) {
                baseInfoSaved();
            } else {
                return false;
            }
            event.preventDefault();
        });

        $('#btnBaseCancel').on('click', function(e) {
            if ( confirm('<spring:message code="U314"/>') ) {
                //TODO DB 삭제 후 공고 목록으로 이동
            } else {
                return false;
            }
            event.preventDefault();
        });
        <%-- 기본 정보 > 지원 사항 처리 --%>

        <%-- 하단 버튼 처리 --%>
        var formProcess = function(event) {
            var $form = $(this),
                form = document.getElementById('basis'),
                isValidProcess = true;

            if ($('#baseSave').css('display') == 'block') {
                isValidProcess = false;
                alert('<spring:message code="U329"/>');
                $('#applAttrCode').focus();
            } else {
                form.action = "${contextPath}/application/basis/save";
                form.submit();
            }
            event.preventDefault();
        };
        $('.btn-save').on('click', formProcess);
        <%-- 하단 버튼 처리 --%>

        <%-- 영문 이름 처리 시작 --%>
        $('.engName').on('keyup', function() {
            this.value = this.value.toUpperCase().replace(/([^0-9A-Z])/g,"");
        });
        <%-- 영문 이름 처리 끝 --%>

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
//                        document.getElementById('postcode1').value = data.postcode1;
//                        document.getElementById('postcode2').value = data.postcode2;
                    document.getElementById('zipCode').value = data.postcode1 + data.postcode2;
                    document.getElementById('address').value = data.address1;
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
        <%-- 다음 주소 검색 끝 --%>

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
                message: '${msgPhoneNo}'
            }
        };

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
                }
            }
        });

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

    });
    </script>
</content>
</body>
</html>
