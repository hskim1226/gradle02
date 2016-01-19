<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang="ko">
<head>
    <title><spring:message code="L04101"/><%--원서 작성 - 파일 첨부--%></title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
    <style>
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

        section.application .nav>li>a {
            display: block;
        }
        .apexMessage {
            color: #000;
            font-size: 12px;
            font-weight: 900;
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

        .control-label {
            color: #333;
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

        /*.show-lang .hide-lang, .hide-lang .show-lang {*/
            /*display: none;*/
        /*}*/
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
        .activeTab {
            background: #d0d0d0;
            color: #333333;
            font-weight: bold;
        }
        #tabTR {
            cursor: pointer;
        }

        .checkbox-upload {
            min-height: 20px;
            margin-top: 0px;
            margin-bottom: 10px;
            color: #222;
            /*vertical-align: middle;*/
        }
        .validation-error {
            background: #ffdddd;
            color: #f55;
        }
        .warn-info {
            color: #f00;
        }
        /* spinner */
        /* http://projects.lukehaas.me/css-loaders/ */
        .loader {
            font-size: 12px;
            margin: 4em auto;
            width: 1em;
            height: 1em;
            border-radius: 50%;
            position: relative;
            /*text-indent: -9999em;*/
            text-align: center;
            -webkit-animation: load4 1.3s infinite linear;
            animation: load4 1.3s infinite linear;
        }
        @-webkit-keyframes load4 {
            0%,
            100% {
                box-shadow: 0em -3em 0em 0.2em #55aaff, 2em -2em 0 0em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 0em #55aaff;
            }
            12.5% {
                box-shadow: 0em -3em 0em 0em #55aaff, 2em -2em 0 0.2em #55aaff, 3em 0em 0 0em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            25% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 0em #55aaff, 3em 0em 0 0.2em #55aaff, 2em 2em 0 0em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            37.5% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 0em #55aaff, 2em 2em 0 0.2em #55aaff, 0em 3em 0 0em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            50% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 0em #55aaff, 0em 3em 0 0.2em #55aaff, -2em 2em 0 0em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            62.5% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 0em #55aaff, -2em 2em 0 0.2em #55aaff, -3em 0em 0 0em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            75% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 0em #55aaff, -3em 0em 0 0.2em #55aaff, -2em -2em 0 0em #55aaff;
            }
            87.5% {
                box-shadow: 0em -3em 0em 0em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 0em #55aaff, -3em 0em 0 0em #55aaff, -2em -2em 0 0.2em #55aaff;
            }
        }
        @keyframes load4 {
            0%,
            100% {
                box-shadow: 0em -3em 0em 0.2em #55aaff, 2em -2em 0 0em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 0em #55aaff;
            }
            12.5% {
                box-shadow: 0em -3em 0em 0em #55aaff, 2em -2em 0 0.2em #55aaff, 3em 0em 0 0em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            25% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 0em #55aaff, 3em 0em 0 0.2em #55aaff, 2em 2em 0 0em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            37.5% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 0em #55aaff, 2em 2em 0 0.2em #55aaff, 0em 3em 0 0em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            50% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 0em #55aaff, 0em 3em 0 0.2em #55aaff, -2em 2em 0 0em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            62.5% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 0em #55aaff, -2em 2em 0 0.2em #55aaff, -3em 0em 0 0em #55aaff, -2em -2em 0 -0.5em #55aaff;
            }
            75% {
                box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 0em #55aaff, -3em 0em 0 0.2em #55aaff, -2em -2em 0 0em #55aaff;
            }
            87.5% {
                box-shadow: 0em -3em 0em 0em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 0em #55aaff, -3em 0em 0 0em #55aaff, -2em -2em 0 0.2em #55aaff;
            }
        }
        .inline-notice {
            font-size: 1.5em;
            color: blue;
            font-weight: 900;
        }
        .inline-notice-small {
            font-size: 1.2em;
            color: blue;
            font-weight: 600;
        }
        .inline-notice-red {
            font-size: 1.5em;
            color: red;
            font-weight: 900;
        }
        .inline-notice-red-big {
            font-size: 1.7em;
            color: red;
            font-weight: 900;
        }
    </style>
</head>
<body>
<div id="overlay" class="web_dialog_overlay"></div>
<%-- SYSADMIN 일 경우 안내 배너 표시 --%>
<c:if test="${isSYSADMIN}">
    <div>
        <span style="position: fixed; z-index: 900; text-align: center;" class="btn-group-justified btn-lg btn-danger">
            THIS IS SYSADMIN, Real USER_ID : ${document.application.userId}, APPL_NO : ${document.application.applNo}
        </span>
    </div>
</c:if>
<div class="spacer-tiny">&nbsp;</div>
<%-- SYSADMIN 일 경우 안내 배너 표시 --%>
<section class="application">
    <div class="container">

        <p id="stepStatusTitle" colspan=4 align="center" height="70px">${msg.getMsg('L01001', locale)}<%--원서 작성 현황--%></p>
        <!-- 진행상태바 시작 -->
        <div class="step_wrap">
            <ul class="step_box" id="step_box">
                <li class="inactive"><span class="step"><strong>1</strong></span>
                    <p class="txt1"><spring:message code="L01002"/><%--기본 정보--%></p>
                </li>
                <li class="inactive"><span class="step"><strong>2</strong></span>
                    <p class="txt1"><spring:message code="L01003"/><%--학력 정보--%></p>
                </li>
                <li class="inactive"><span class="step"><strong>3</strong></span>
                    <p class="txt1"><spring:message code="L01004"/><%--어학/경력 정보--%></p>
                </li>
                <li class="inactive"><span class="step"><strong>4</strong></span>
                    <p class="txt1"><spring:message code="L01005"/><%--파일 첨부 및 제출--%></p>
                </li>
                <li class="inactive"><span class="step"><strong>5</strong></span>
                    <p class="txt1"><spring:message code="L01006"/><%--결제--%></p>
                </li>
            </ul>
        </div>
        <!-- /진행상태바 끝 -->
        <!-- 데스크탑 탭메뉴 시작 -->
        <div id="pc_tab" class="nav_wrap clearfix tab-container">
            <ul id="navPcTabUL" class="nav nav-tabs nav-justified">
                <li id="tab-pc-basis" class="inactive inactiveTab" data-target-tab="basis" data-tab-available="true"><a><spring:message code="L01002"/><%--1. 기본 정보--%></a></li>
                <li id="tab-pc-academy" class="inactive inactiveTab" data-target-tab="academy" data-tab-available="false" data-unavailable-msg='<spring:message code="U321"/>'><a><spring:message code="L01003"/><%--2. 학력 정보--%></a></li>
                <li id="tab-pc-langCareer" class="inactive inactiveTab" data-target-tab="langCareer" data-tab-available="false" data-unavailable-msg='<spring:message code="U322"/>'><a><spring:message code="L01004"/><%--3. 어학/경력 정보--%></a></li>
                <li id="tab-pc-document" class="inactive inactiveTab" data-target-tab="document" data-tab-available="false" data-unavailable-msg='<spring:message code="U323"/>'><a><spring:message code="L01005"/><%--4. 파일 첨부--%></a></li>
            </ul>
        </div>
        <!-- /데스크탑 탭메뉴 끝 -->
        <!-- 모바일 탭메뉴 시작 -->
        <div id="mb_tab" class="nav_wrap clearfix tab-container">
            <ul id="navMbTabUL" class="nav nav-pills nav-stacked">
                <li id="tab-mb-basis" class="inactive inactiveTab" data-target-tab="basis" data-tab-available="true"><a><spring:message code="L01002"/><%--1. 기본 정보--%></a></li>
                <li id="tab-mb-academy" class="inactive inactiveTab" data-target-tab="academy" data-tab-available="false" data-unavailable-msg='<spring:message code="U321"/>'><a><spring:message code="L01003"/><%--2. 학력 정보--%></a></li>
                <li id="tab-mb-langCareer" class="inactive inactiveTab" data-target-tab="langCareer" data-tab-available="false" data-unavailable-msg='<spring:message code="U322"/>'><a><spring:message code="L01004"/><%--3. 어학/경력 정보--%></a></li>
                <li id="tab-mb-document" class="inactive inactiveTab" data-target-tab="document" data-tab-available="false" data-unavailable-msg='<spring:message code="U323"/>'><a><spring:message code="L01005"/><%--4. 파일 첨부--%></a></li>
            </ul>
        </div>
        <!-- 모바일 탭메뉴 끝 -->
        <form:form commandName="document" cssClass="form-horizontal" method="post" role="form">
            <form:hidden path="application.applNo" id="applNo" />
            <form:hidden path="application.applStsCode" id="applStsCode" />
            <form:hidden path="application.admsNo" id="admsNo" />
            <form:hidden path="application.entrYear" id="entrYear" />
            <form:hidden path="application.admsTypeCode" id="admsTypeCode" />
            <div id="myTabContent" class="tab-content">
                <div class="spacer-tiny"></div>
                <div class="row">
                    <div class="col-sm-offset-1 col-sm-10">
                        <div class="panel panel-darkgray0">
                            <div class="panel-heading"><spring:message code="L04102"/><%--첨부 파일 안내--%></div>
                            <div class="panel-body">
                                <p><spring:message code="U04101"/><%--사진 외의 모든 첨부 파일은 서류 종류별로 하나의 PDF 파일만 업로드 가능합니다.--%></p>

                                <p><spring:message code="U04102"/><%--만약 한 가지 종류의 서류가 여러 개의 PDF 파일로 되어 있다면,
                                    하나의 PDF 파일로 합친 후 업로드 하시기 바랍니다.--%></p>

                                <p><spring:message code="U04103"/><%--PDF 파일 합치기는 전용프로그램이나 인터넷 서비스를 이용하시기 바랍니다.--%></p>

                                <spring:message code="U04104"/><%--예)--%><br/>
                                <a href="http://convert.neevia.com/pdfmerge/" target="_blank"><spring:message code="U04105"/><%--http://convert.neevia.com/pdfmerge/--%></a><br/>
                                <a href="http://www.pdfmerge.com/" target="_blank"><spring:message code="U04106"/><%--http://www.pdfmerge.com/--%></a><br/>

                                <p><spring:message code="U04107"/><%--예시 사이트에서 발생하는 모든 문제는 당사에서 책임지지 않습니다.--%></p>

                                <p><spring:message code="U04108"/><%--사진 파일은 JPG, GIF, PNG 파일만 업로드 가능합니다.--%></p>

                                <p><spring:message code="U04109"/><%--사진 파일의 편집은 전용 프로그램이나 인터넷 서비스를 이용하시기 바랍니다.--%></p>

                                <spring:message code="U04104"/><%--예)--%><br/>
                                <a href="http://apps.pixlr.com/editor/" target="_blank"><spring:message code="U04110"/><%--http://apps.pixlr.com/editor/--%></a><br/>
                                <a href="http://www.fotor.com/" target="_blank"><spring:message code="U04111"/><%--http://www.fotor.com/--%></a><br/>
                                <p><spring:message code="U04112"/><%--예시 사이트에서 발생하는 모든 문제는 당사에서 책임지지 않습니다.--%></p>
                                <hr/>
                                <p class="inline-notice"><spring:message code="U04113"/><%--파일 첨부 시 주의사항--%></p>
                                <span class="inline-notice"><spring:message code="U04114"/></span><%--1. 문서별로 1개의 파일만 첨부 가능합니다.--%><br/>
                                <span class="inline-notice"><spring:message code="U04115"/></span><%--2. 사진 및 문서의 해상도와 가독성 여부를 반드시 확인하세요.--%><br/>
                                <span class="inline-notice-red"><spring:message code="U04116"/></span><%--3. 스캔시에는 300dpi 이상으로 스캔하시기 바랍니다.--%><br/>
                                <span class="inline-notice"><spring:message code="U04117"/></span><%--4. 문서 크기는 A4 크기로 생성하여 첨부하셔야 합니다.--%><br/>
                                <span class="inline-notice-red-big"><spring:message code="U04124"/></span><%--5. 암호화 된 PDF는 제출하시면 안됩니다.--%><br/>
                                <span class="inline-notice-small"><spring:message code="U04125"/></span><%--예를 들어 TOEFL iBT 성적표의 경우 비밀번호는 없지만, 파일 자체는 암호화 되어 있습니다.--%><br/>
                                <span class="inline-notice-red-big"><spring:message code="U04126"/></span><%--6. 파일명에 '/'나 '\'와 같은 특수문자가 있으면 정상 동작하지 않을 수 있으니, 제거 후 올려 주세요.--%><br/>
                                </p>

                                <p><spring:message code="U04118"/><%--인터넷에서 '한글 문서 PDF 변환'으로 검색하면 아래 한글 문서를 PDF 파일로 변환할 수 있는--%><br/>
                                    <spring:message code="U04119"/><%--다양한 방법이 있으니 참고하시기 바랍니다.--%></p>

                                <p><spring:message code="U04120"/><%--파일 업로드 후 오른쪽의 '내려받기' 버튼을 클릭해서 파일이 올바르게 업로드 되었는지 확인할 수 있습니다.--%></p>

                                <p style="font-size: 14px; color: blue"><b><spring:message code="U04121"/></b><%--입학 신청과 관련하여 제출 또는 업로드 해야 할 서류나 파일에 대한 정보는 모집 요강을 반드시 참고하시기 바랍니다.--%><br/>
                                    <b><spring:message code="U04122"/></b><%--제출 해야 할 서류나 파일의 누락에 대한 책임은 지원 신청자 본인에게 있습니다.--%></p>

                                <div class="col-md-12 text-center mid-font slogan"><label style="font-size: 15px; color: red;"><spring:message code="U04123"/><%--위 내용을 확인하였으며, 입학 신청과 관련한 서류나 파일의 누락 및 적정 여부에 대한 책임은 지원 신청자에게 있음을 확인합니다.--%> <input type="checkbox" name="application.docChckYn" id="docChckYn" value="${document.application.docChckYn == 'Y' ? 'on' : 'off'}" ${document.application.docChckYn == 'Y' ? 'checked' : ''}/></label></div>
                            <spring:bind path="application.docChckYn">
                                <c:if test="${status.error}">
                                    <div class="col-sm-12 validation-container">
                                        <div class="validation-error">${status.errorMessage}</div>
                                    </div>
                                </c:if>
                            </spring:bind>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="spacer-tiny"></div>
                <div class="row">
                    <div class="col-sm-offset-1 col-sm-10">
                <c:forEach items="${document.documentContainerList}" var="lv1Container" varStatus="lv1Status">
                    <c:choose>
                        <c:when test="${lv1Container.lastYn == 'N'}">
                        <div class="form-group"><label class="col-sm-3 control-label word-keep-all">${pageContext.response.locale == 'en' ? lv1Container.docItemNameXxen : lv1Container.docItemName}</label></div>
                        </c:when>
                        <c:otherwise>
                        <div class="panel panel-darkgray0">
                            <div class="panel-heading">${msg.getMsg(lv1Container.grpLabel)}</div>
                            <form:hidden path="documentContainerList[${lv1Status.index}].grpLabel" value="${lv1Container.grpLabel}" />
                            <form:hidden path="documentContainerList[${lv1Status.index}].docTypeCode" value="${lv1Container.docTypeCode}" />
                            <form:hidden path="documentContainerList[${lv1Status.index}].docItemCode" value="${lv1Container.docItemCode}" />
                            <div class="panel-body" id="docContainerList${lv1Status.index}.list">

                            <c:forEach items="${lv1Container.subContainer}" var="lv2Container" varStatus="lv2Status">
                                <c:choose>
                                    <c:when test="${lv2Container.lastYn == 'Y'}">
                                <div class="form-group">
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].applNo" value="${lv2Container.applNo}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].docSeq" value="${lv2Container.docSeq}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].docTypeCode" value="${lv2Container.docTypeCode}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].docGrp" value="${lv2Container.docGrp}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].docItemCode" value="${lv2Container.docItemCode}" />
                                    <c:if test="${lv2Container.docTypeCode != '00009'}">
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].docItemName" value="${lv2Container.docItemName}" />
                                    </c:if>
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].grpLabel" value="${lv2Container.grpLabel}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].fileExt" value="${lv2Container.fileExt}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].imgYn" value="${lv2Container.imgYn}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].filePath" value="${lv2Container.filePath}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].fileName" value="${lv2Container.fileName}"
                                                 class="hidden-file-name"
                                                 data-file-input-id="file-input-name${lv1Status.index}-${lv2Status.index}-${lv3Status.index}"
                                                 data-doc-item-name="${lv2Container.docItemName}"/>
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].orgFileName" value="${lv2Container.orgFileName}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].docItemNameXxen" value="${lv2Container.docItemNameXxen}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].docGrpName" value="${lv2Container.docGrpName}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].fileUploadFg" value="${lv2Container.fileUploadFg}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].displayGrpFg" value="${lv2Container.displayGrpFg}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].admsNo" value="${lv2Container.admsNo}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].admsCorsNo" value="${lv2Container.admsCorsNo}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].detlMajCode" value="${lv2Container.detlMajCode}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].admsCodeGrp" value="${lv2Container.admsCodeGrp}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].admsCode" value="${lv2Container.admsCode}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].grpLevel" value="${lv2Container.grpLevel}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].docItemGrp" value="${lv2Container.docItemGrp}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].upCodeGrp" value="${lv2Container.upCodeGrp}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].upCode" value="${lv2Container.upCode}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].lastYn" value="${lv2Container.lastYn}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].mdtYn" value="${lv2Container.mdtYn}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].uploadYn" value="${lv2Container.uploadYn}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].sendCnt" value="${lv2Container.sendCnt}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].msgNo" value="${lv2Container.msgNo}" />
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].msg" value="${lv2Container.msg}" />
                            <c:choose>
                                <c:when test="${document.documentContainerList[lv1Status.index].subContainer[lv2Status.index].uploadYn == 'N'}">
                                    <div class="col-sm-3"><label>${pageContext.response.locale == 'en' ? lv2Container.docItemNameXxen : lv2Container.docItemName}</label></div>
                                    <div class="col-sm-9 warn-info"><label>${lv2Container.msg}</label></div>
                                </c:when>
                                <c:otherwise>
                                    <div class="col-sm-3">
                                        <div class="checkbox-upload">
                                            <label class="word-keep-all" for="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.checkedFg">
                                                <div class="col-sm-2">
                                                    <input type="checkbox"
                                                           id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.checkedFg"
                                                           name="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].checkedFg"
                                                           data-upload-button-id="upload-button-${lv1Status.index}-${lv2Status.index}"
                                                           <c:if test="${lv2Container.fileUploadFg == true}">checked</c:if> />
                                                </div>
                                                <div class="col-sm-10 nopadding">
                                                    <c:choose>
                                                        <c:when test="${lv2Container.docTypeCode != '00009'}">${pageContext.response.locale == 'en' ? lv2Container.docItemNameXxen : lv2Container.docItemName}</c:when>
                                                        <c:otherwise>
                                                            <form:input path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].docItemName"
                                                                        value="${lv2Container.docItemName}"
                                                                        cssClass="form-control" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-sm-5 nopadding">
                                        <div class="col-sm-12 nopadding"><input type="file" class="btn btn-file" id="file-input-${lv1Status.index}-${lv2Status.index}" name="file-input-name-${lv1Status.index}-${lv2Status.index}" data-upload-button-id="upload-button-${lv1Status.index}-${lv2Status.index}"/></div>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="col-sm-4 nopadding">
                                            <button id="upload-button-${lv1Status.index}-${lv2Status.index}"
                                                   class="btn btn-default btn-upload"

                                                   data-checkbox-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.checkedFg"

                                                   data-file-input-id="file-input-${lv1Status.index}-${lv2Status.index}"
                                                   data-img-yn-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.imgYn"
                                                   data-file-download-link-id="file-download-link-${lv1Status.index}-${lv2Status.index}"
                                                   data-file-delete-link-id="file-delete-link-${lv1Status.index}-${lv2Status.index}"
                                                   data-org-filename-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.orgFileName"
                                                   data-target-subcontainer-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}."

                                                   data-doc-prop-docSeq="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.docSeq"
                                                   data-doc-prop-docTypeCode="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.docTypeCode"
                                                   data-doc-prop-docGrp="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.docGrp"
                                                   data-doc-prop-docItemCode="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.docItemCode"
                                                   data-doc-prop-docItemName="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.docItemName"
                                                   data-doc-prop-grpLabel="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.grpLabel"
                                                   data-doc-prop-fileExt="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.fileExt"
                                                   data-doc-prop-imgYn="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.imgYn"
                                                   data-doc-prop-filePath="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.filePath"
                                                   data-doc-prop-fileName="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.fileName"
                                                   data-doc-prop-orgFileName="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.orgFileName"
                                                   data-doc-prop-docItemNameXxen="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.docItemNameXxen"
                                                   data-doc-prop-docGrpName="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.docGrpName"
                                                   data-doc-prop-fileUploadFg="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.fileUploadFg"
                                                   data-doc-prop-displayGrpFg="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.displayGrpFg"
                                                   data-doc-prop-checkedFg="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.checkedFg"
                                                   data-doc-prop-admsNo="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.admsNo"
                                                   data-doc-prop-admsCorsNo="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.admsCorsNo"
                                                   data-doc-prop-detlMajCode="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.detlMajCode"
                                                   data-doc-prop-admsCodeGrp="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.admsCodeGrp"
                                                   data-doc-prop-admsCode="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.admsCode"
                                                   data-doc-prop-grpLevel="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.grpLevel"
                                                   data-doc-prop-docItemGrp="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.docItemGrp"
                                                   data-doc-prop-upCodeGrp="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.upCodeGrp"
                                                   data-doc-prop-upCode="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.upCode"
                                                   data-doc-prop-lastYn="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.lastYn"
                                                   data-doc-prop-mdtYn="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.mdtYn"
                                                   data-doc-prop-uploadYn="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.uploadYn"
                                                   data-doc-prop-sendCnt="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.sendCnt"
                                                   ><spring:message code="L04104"/><%--올리기--%></button>
                                        </div>
                                        <div class="col-sm-4 upload-delete-button-container-${lv1Status.index}-${lv2Status.index}" style='display: <c:choose><c:when test="${lv2Container.fileUploadFg == true}">block;</c:when><c:otherwise>none;</c:otherwise></c:choose>'>
                                            <a class="btn btn-default file-download" id="file-download-link-${lv1Status.index}-${lv2Status.index}"
                                               href="${contextPath}/application/document/fileDownload/${lv2Container.applNo}/${lv2Container.docSeq}"><spring:message code="L04105"/><%--내려받기--%></a>
                                        </div>
                                        <div class="col-sm-4 upload-delete-button-container-${lv1Status.index}-${lv2Status.index}" style='display: <c:choose><c:when test="${lv2Container.fileUploadFg == true}">block;</c:when><c:otherwise>none;</c:otherwise></c:choose>'>
                                            <a class="btn btn-default file-delete" id="file-delete-link-${lv1Status.index}-${lv2Status.index}"
                                               data-checkbox-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.checkedFg"
                                               data-docitemname-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.docItemName"
                                               data-upload-button-id="upload-button-${lv1Status.index}-${lv2Status.index}"
                                               data-button-container-class="upload-delete-button-container-${lv1Status.index}-${lv2Status.index}"
                                               data-fileUploadFg-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.fileUploadFg"
                                               href="${contextPath}/application/document/fileDelete/${lv2Container.applNo}/${lv2Container.docSeq}"><spring:message code="L04106"/><%--삭제--%></a>
                                        </div>
                                    </div>
                                    <spring:bind path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].fileUploadFg">
                                        <c:if test="${status.error}">
                                            <div class="col-sm-12 validation-container">
                                                <div class="validation-error">${status.errorMessage}</div>
                                            </div>
                                        </c:if>
                                    </spring:bind>
                                </c:otherwise>
                            </c:choose>
                                </div>

                                    </c:when>
                                    <c:otherwise>
                                <div class="panel panel-darkgray1">
                                    <div class="panel-heading">${pageContext.response.locale == 'en' ? lv2Container.grpLabelXxen : lv2Container.grpLabel}</div>
                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].grpLabel" value="${lv2Container.grpLabel}" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].docTypeCode" value="${lv2Container.docTypeCode}" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].docItemCode" value="${lv2Container.docItemCode}" />
                                    <div class="panel-body" id="docContainerList${lv2Status.index}.list">

                                        <c:forEach items="${lv2Container.subContainer}" var="lv3Container" varStatus="lv3Status">
                                            <c:choose>
                                                <c:when test="${lv3Container.lastYn == 'Y'}">
                                        <div class="form-group">
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].applNo" value="${lv3Container.applNo}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].docSeq" value="${lv3Container.docSeq}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].docTypeCode" value="${lv3Container.docTypeCode}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].docGrp" value="${lv3Container.docGrp}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].docItemCode" value="${lv3Container.docItemCode}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].docItemName" value="${lv3Container.docItemName}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].grpLabel" value="${lv3Container.grpLabel}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].fileExt" value="${lv3Container.fileExt}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].imgYn" value="${lv3Container.imgYn}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].filePath" value="${lv3Container.filePath}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].fileName" value="${lv3Container.fileName}"
                                                         class="hidden-file-name"
                                                         data-file-input-id="file-input-name-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}"
                                                         data-doc-item-name="${lv3Container.docItemName}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].orgFileName" value="${lv3Container.orgFileName}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].docItemNameXxen" value="${lv3Container.docItemNameXxen}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].docGrpName" value="${lv3Container.docGrpName}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].fileUploadFg" value="${lv3Container.fileUploadFg}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].displayGrpFg" value="${lv3Container.displayGrpFg}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].admsNo" value="${lv3Container.admsNo}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].admsCorsNo" value="${lv3Container.admsCorsNo}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].detlMajCode" value="${lv3Container.detlMajCode}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].admsCodeGrp" value="${lv3Container.admsCodeGrp}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].admsCode" value="${lv3Container.admsCode}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].grpLevel" value="${lv3Container.grpLevel}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].docItemGrp" value="${lv3Container.docItemGrp}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].upCodeGrp" value="${lv3Container.upCodeGrp}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].upCode" value="${lv3Container.upCode}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].lastYn" value="${lv3Container.lastYn}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].mdtYn" value="${lv3Container.mdtYn}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].uploadYn" value="${lv3Container.uploadYn}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].sendCnt" value="${lv3Container.sendCnt}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].msgNo" value="${lv3Container.msgNo}" />
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].msg" value="${lv3Container.msg}" />
                                    <c:choose>
                                        <c:when test="${document.documentContainerList[lv1Status.index].subContainer[lv2Status.index].subContainer[lv3Status.index].uploadYn == 'N'}">
                                            <div class="col-sm-3"><label>${pageContext.response.locale == 'en' ? lv3Container.docItemNameXxen : lv3Container.docItemName}</label></div>
                                            <div class="col-sm-9 warn-info"><label>${lv3Container.msg}</label></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="col-sm-3">
                                                <div class="checkbox-upload">
                                                    <label class="word-keep-all" for="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.checkedFg">
                                                        <input type="checkbox"
                                                               id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.checkedFg"
                                                               name="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].checkedFg"
                                                               data-upload-button-id="upload-button-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}"
                                                               <c:if test="${lv3Container.fileUploadFg == true}">checked</c:if> />${pageContext.response.locale == 'en' ? lv3Container.docItemNameXxen : lv3Container.docItemName}
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="col-sm-5 nopadding">
                                                <div class="col-sm-12 nopadding"><input type="file" class="btn btn-file" id="file-input-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}" name="file-input-name-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}" data-upload-button-id="upload-button-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}"/></div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div class="col-sm-4 nopadding">
                                                    <button id="upload-button-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}"
                                                           class="btn btn-default btn-upload"

                                                           data-checkbox-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.checkedFg"

                                                           data-file-input-id="file-input-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}"
                                                           data-img-yn-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.imgYn"
                                                           data-file-download-link-id="file-download-link-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}"
                                                           data-file-delete-link-id="file-delete-link-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}"
                                                           data-org-filename-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.orgFileName"
                                                           data-target-subcontainer-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}."

                                                           data-doc-prop-docSeq="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.docSeq"
                                                           data-doc-prop-docTypeCode="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.docTypeCode"
                                                           data-doc-prop-docGrp="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.docGrp"
                                                           data-doc-prop-docItemCode="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.docItemCode"
                                                           data-doc-prop-docItemName="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.docItemName"
                                                           data-doc-prop-grpLabel="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.grpLabel"
                                                           data-doc-prop-fileExt="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.fileExt"
                                                           data-doc-prop-imgYn="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.imgYn"
                                                           data-doc-prop-filePath="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.filePath"
                                                           data-doc-prop-fileName="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.fileName"
                                                           data-doc-prop-orgFileName="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.orgFileName"
                                                           data-doc-prop-docItemNameXxen="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.docItemNameXxen"
                                                           data-doc-prop-docGrpName="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.docGrpName"
                                                           data-doc-prop-fileUploadFg="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.fileUploadFg"
                                                           data-doc-prop-displayGrpFg="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.displayGrpFg"
                                                           data-doc-prop-checkedFg="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.checkedFg"
                                                           data-doc-prop-admsNo="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.admsNo"
                                                           data-doc-prop-admsCorsNo="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.admsCorsNo"
                                                           data-doc-prop-detlMajCode="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.detlMajCode"
                                                           data-doc-prop-admsCodeGrp="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.admsCodeGrp"
                                                           data-doc-prop-admsCode="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.admsCode"
                                                           data-doc-prop-grpLevel="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.grpLevel"
                                                           data-doc-prop-docItemGrp="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.docItemGrp"
                                                           data-doc-prop-upCodeGrp="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.upCodeGrp"
                                                           data-doc-prop-upCode="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.upCode"
                                                           data-doc-prop-lastYn="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.lastYn"
                                                           data-doc-prop-mdtYn="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.mdtYn"
                                                           data-doc-prop-uploadYn="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.uploadYn"
                                                           data-doc-prop-sendCnt="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.sendCnt"
                                                           ><spring:message code="L04104"/><%--올리기--%></button>
                                                </div>
                                                <div class="col-sm-4 upload-delete-button-container-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}"
                                                     style='display: <c:choose><c:when test="${lv3Container.fileUploadFg == true}">block;</c:when><c:otherwise>none;</c:otherwise></c:choose>'>
                                                    <a class="btn btn-default file-download" id="file-download-link-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}"
                                                       href="${contextPath}/application/document/fileDownload/${lv3Container.applNo}/${lv3Container.docSeq}"><spring:message code="L04105"/><%--내려받기--%></a>
                                                </div>
                                                <div class="col-sm-4 upload-delete-button-container-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}"
                                                     style='display: <c:choose><c:when test="${lv3Container.fileUploadFg == true}">block;</c:when><c:otherwise>none;</c:otherwise></c:choose>'>
                                                    <a class="btn btn-default file-delete" id="file-delete-link-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}"
                                                       data-checkbox-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.checkedFg"
                                                       data-docitemname-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.docItemName"
                                                       data-upload-button-id="upload-button-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}"
                                                       data-button-container-class="upload-delete-button-container-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}"
                                                       data-fileUploadFg-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.fileUploadFg"
                                                       href="${contextPath}/application/document/fileDelete/${lv3Container.applNo}/${lv3Container.docSeq}"><spring:message code="L04106"/><%--삭제--%></a>
                                                </div>
                                            </div>
                                    <spring:bind path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].fileUploadFg">
                                        <c:if test="${status.error}">
                                            <div class="col-sm-12 validation-container">
                                                <div class="validation-error">${status.errorMessage}</div>
                                            </div>
                                        </c:if>
                                    </spring:bind>
                                        </c:otherwise>
                                    </c:choose>
                                        </div>

                                                </c:when>
                                                <c:otherwise>
                                        <div class="panel panel-default">
                                            <div class="panel-heading">${pageContext.response.locale == 'en' ? lv3Container.grpLabelXxen : lv3Container.grpLabel}</div>
                                            <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].grpLabel" value="${lv3Container.grpLabel}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].docTypeCode" value="${lv3Container.docTypeCode}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].docItemCode" value="${lv3Container.docItemCode}" />
                                            <div class="panel-body" id="docContainerList${lv3Status.index}.list">

                                                    <c:forEach items="${lv3Container.subContainer}" var="lv4Container" varStatus="lv4Status">
                                                        <c:choose>
                                                            <c:when test="${lv4Container.lastYn == 'Y'}">
                                                <div class="form-group">
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].applNo" value="${lv4Container.applNo}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].docSeq" value="${lv4Container.docSeq}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].docTypeCode" value="${lv4Container.docTypeCode}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].docGrp" value="${lv4Container.docGrp}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].docItemCode" value="${lv4Container.docItemCode}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].docItemName" value="${lv4Container.docItemName}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].grpLabel" value="${lv4Container.grpLabel}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].fileExt" value="${lv4Container.fileExt}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].imgYn" value="${lv4Container.imgYn}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].filePath" value="${lv4Container.filePath}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].fileName" value="${lv4Container.fileName}"
                                                                 class="hidden-file-name"
                                                                 data-file-input-id="file-input-name-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}-${lv4Status.index}"
                                                                 data-doc-item-name="${lv4Container.docItemName}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].orgFileName" value="${lv4Container.orgFileName}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].docItemNameXxen" value="${lv4Container.docItemNameXxen}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].docGrpName" value="${lv4Container.docGrpName}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].fileUploadFg" value="${lv4Container.fileUploadFg}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].displayGrpFg" value="${lv4Container.displayGrpFg}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].admsNo" value="${lv4Container.admsNo}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].admsCorsNo" value="${lv4Container.admsCorsNo}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].detlMajCode" value="${lv4Container.detlMajCode}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].admsCodeGrp" value="${lv4Container.admsCodeGrp}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].admsCode" value="${lv4Container.admsCode}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].grpLevel" value="${lv4Container.grpLevel}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].docItemGrp" value="${lv4Container.docItemGrp}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].upCodeGrp" value="${lv4Container.upCodeGrp}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].upCode" value="${lv4Container.upCode}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].lastYn" value="${lv4Container.lastYn}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].mdtYn" value="${lv4Container.mdtYn}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].uploadYn" value="${lv4Container.uploadYn}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].sendCnt" value="${lv4Container.sendCnt}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].msgNo" value="${lv4Container.msgNo}" />
                                                    <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].msg" value="${lv4Container.msg}" />
                                            <c:choose>
                                                <c:when test="${document.documentContainerList[lv1Status.index].subContainer[lv2Status.index].subContainer[lv3Status.index].subContainer[lv4Status.index].uploadYn == 'N'}">
                                                    <div class="col-sm-3"><label>${pageContext.response.locale == 'en' ? lv4Container.docItemNameXxen : lv4Container.docItemName}</label></div>
                                                    <div class="col-sm-9 warn-info"><label>${lv4Container.msg}</label></div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="col-sm-3">
                                                        <div class="checkbox-upload">
                                                            <label class="word-keep-all" for="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.checkedFg">
                                                                <input type="checkbox"
                                                                       id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.checkedFg"
                                                                       name="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].checkedFg"
                                                                       data-upload-button-id="upload-button-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}-${lv4Status.index}"
                                                                       <c:if test="${lv4Container.fileUploadFg == true}">checked</c:if> />${pageContext.response.locale == 'en' ? lv4Container.docItemNameXxen : lv4Container.docItemName}
                                                            </label>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-5 nopadding">
                                                        <div class="col-sm-12 nopadding"><input type="file" class="btn btn-file" id="file-input-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}-${lv4Status.index}" name="file-input-name-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}-${lv4Status.index}" data-upload-button-id="upload-button-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}-${lv4Status.index}"/></div>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <div class="col-sm-4 nopadding">
                                                            <button id="upload-button-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}-${lv4Status.index}"
                                                                   class="btn btn-default btn-upload"

                                                                   data-checkbox-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.checkedFg"

                                                                   data-file-input-id="file-input-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}-${lv4Status.index}"
                                                                   data-img-yn-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.imgYn"
                                                                   data-file-download-link-id="file-download-link-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}-${lv4Status.index}"
                                                                   data-file-delete-link-id="file-delete-link-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}-${lv4Status.index}"
                                                                   data-org-filename-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.orgFileName"
                                                                   data-target-subcontainer-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}."

                                                                   data-doc-prop-docSeq="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.docSeq"
                                                                   data-doc-prop-docTypeCode="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.docTypeCode"
                                                                   data-doc-prop-docGrp="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.docGrp"
                                                                   data-doc-prop-docItemCode="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.docItemCode"
                                                                   data-doc-prop-docItemName="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.docItemName"
                                                                   data-doc-prop-grpLabel="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.grpLabel"
                                                                   data-doc-prop-fileExt="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.fileExt"
                                                                   data-doc-prop-imgYn="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.imgYn"
                                                                   data-doc-prop-filePath="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.filePath"
                                                                   data-doc-prop-fileName="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.fileName"
                                                                   data-doc-prop-orgFileName="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.orgFileName"
                                                                   data-doc-prop-docItemNameXxen="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.docItemNameXxen"
                                                                   data-doc-prop-docGrpName="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.docGrpName"
                                                                   data-doc-prop-fileUploadFg="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.fileUploadFg"
                                                                   data-doc-prop-displayGrpFg="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.displayGrpFg"
                                                                   data-doc-prop-checkedFg="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.checkedFg"
                                                                   data-doc-prop-admsNo="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.admsNo"
                                                                   data-doc-prop-admsCorsNo="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.admsCorsNo"
                                                                   data-doc-prop-detlMajCode="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.detlMajCode"
                                                                   data-doc-prop-admsCodeGrp="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.admsCodeGrp"
                                                                   data-doc-prop-admsCode="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.admsCode"
                                                                   data-doc-prop-grpLevel="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.grpLevel"
                                                                   data-doc-prop-docItemGrp="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.docItemGrp"
                                                                   data-doc-prop-upCodeGrp="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.upCodeGrp"
                                                                   data-doc-prop-upCode="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.upCode"
                                                                   data-doc-prop-lastYn="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.lastYn"
                                                                   data-doc-prop-mdtYn="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.mdtYn"
                                                                   data-doc-prop-uploadYn="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.uploadYn"
                                                                   data-doc-prop-sendCnt="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.sendCnt"
                                                                   ><spring:message code="L04104"/><%--올리기--%></button>
                                                        </div>
                                                        <div class="col-sm-4 upload-delete-button-container-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}-${lv4Status.index}"
                                                             style='display: <c:choose><c:when test="${lv4Container.fileUploadFg == true}">block;</c:when><c:otherwise>none;</c:otherwise></c:choose>'>
                                                            <a class="btn btn-default file-download" id="file-download-link-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}-${lv4Status.index}"
                                                               href="${contextPath}/application/document/fileDownload/${lv4Container.applNo}/${lv4Container.docSeq}"><spring:message code="L04105"/><%--내려받기--%></a>
                                                        </div>
                                                        <div class="col-sm-4 upload-delete-button-container-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}-${lv4Status.index}"
                                                             style='display: <c:choose><c:when test="${lv4Container.fileUploadFg == true}">block;</c:when><c:otherwise>none;</c:otherwise></c:choose>'>
                                                            <a class="btn btn-default file-delete" id="file-delete-link-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}-${lv4Status.index}"
                                                               data-checkbox-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.checkedFg"
                                                               data-docitemname-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.docItemName"
                                                               data-upload-button-id="upload-button-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}-${lv4Status.index}"
                                                               data-button-container-class="upload-delete-button-container-${lv1Status.index}-${lv2Status.index}-${lv3Status.index}-${lv4Status.index}"
                                                               data-fileUploadFg-id="documentContainerList${lv1Status.index}.subContainer${lv2Status.index}.subContainer${lv3Status.index}.subContainer${lv4Status.index}.fileUploadFg"
                                                               href="${contextPath}/application/document/fileDelete/${lv4Container.applNo}/${lv4Container.docSeq}"><spring:message code="L04106"/><%--삭제--%></a>
                                                        </div>
                                                    </div>
                                            <spring:bind path="documentContainerList[${lv1Status.index}].subContainer[${lv2Status.index}].subContainer[${lv3Status.index}].subContainer[${lv4Status.index}].fileUploadFg">
                                                <c:if test="${status.error}">
                                                    <div class="col-sm-12 validation-container">
                                                        <div class="validation-error">${status.errorMessage}</div>
                                                    </div>
                                                </c:if>
                                            </spring:bind>
                                                </c:otherwise>
                                            </c:choose>
                                                </div>

                                                            </c:when>
                                                            <c:otherwise>
                                                <div class="panel panel-default">
                                                    <div class="panel-heading">${lv4Container.grpLabel}</div>
                                                    <div class="panel-body" id="docContainerList${lv4Status.index}.list">

                                                                <c:forEach items="${lv4Container.subContainer}" var="lv5Container" varStatus="lv5Status">
                                                                    <c:choose>
                                                                        <c:when test="${lv5Container.lastYn == 'Y'}">
                                                        <div class="form-group"><label class="col-sm-3 control-label word-keep-all">${pageContext.response.locale == 'en' ? lv5Container.docItemNameXxen : lv5Container.docItemName}</label></div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                        <div class="panel panel-default">
                                                            <div class="panel-heading">${lv5Container.grpLabel}</div>
                                                            <div class="panel-body" id="docContainerList${lv5Status.index}.list">

                                                            <%-- 한 Depth 추가하려면 여기에 <c:forEach> ~ </c:forEach> 삽입 --%>

                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                    <c:if test="${lv5Container.lastYn == null || lv5Container.lastYn == 'N'}">
                                                             </div>
                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>

                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:if test="${lv4Container.lastYn == null || lv4Container.lastYn == 'N'}">
                                                    </div>
                                                </div>
                                                        </c:if>
                                                    </c:forEach>

                                                </c:otherwise>
                                            </c:choose>
                                            <c:if test="${lv3Container.lastYn == null || lv3Container.lastYn == 'N'}">
                                            </div>
                                        </div>
                                            </c:if>
                                        </c:forEach>

                                    </c:otherwise>
                                </c:choose>
                                <c:if test="${lv2Container.lastYn == null || lv2Container.lastYn == 'N'}">
                                    </div>
                                </div>
                                </c:if>
                            </c:forEach>

                            <%-- level2인 기타 첨부 서류 입력 란 --%>
                            <c:if test="${lv1Container.docTypeCode == '00009'}">
                                <c:forEach begin="${lv1Container.subContainer.size()}" end="9" varStatus="lv2EtcStatus">
                                    <div class="form-group">
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].applNo" value="${document.application.applNo}"/>
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].docSeq" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].docTypeCode" value="00009" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].docGrp" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].docItemCode" value="99999" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].grpLabel" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].fileExt" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].imgYn" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].filePath" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].fileName"
                                                     class="hidden-file-name"
                                                     data-file-input-id="file-input-name-${lv1Status.index}-${lv2EtcStatus.index}"
                                                     data-doc-item-name="${documentContainerList[lv1Status.index].subContainer[lv2EtcStatus.index].docItemName}"/>
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].orgFileName" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].docItemNameXxen" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].docGrpName" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].fileUploadFg" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].displayGrpFg" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].admsNo" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].admsCorsNo" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].detlMajCode" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].admsCodeGrp" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].admsCode" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].grpLevel" value="1" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].docItemGrp" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].upCodeGrp" value="NO" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].upCode" value="NO" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].lastYn" value="Y" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].mdtYn" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].uploadYn" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].sendCnt" value="1" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].msgNo" />
                                        <form:hidden path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].msg" />
                                        <div class="col-sm-3">
                                            <div class="checkbox-upload">
                                                <label class="word-keep-all" for="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.checkedFg">
                                                    <div class="col-sm-2">
                                                        <input type="checkbox"
                                                               id="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.checkedFg"
                                                               name="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].checkedFg"
                                                               data-upload-button-id="upload-button-${lv1Status.index}-${lv2EtcStatus.index}" />
                                                    </div>
                                                    <div class="col-sm-10 nopadding">
                                                        <form:input path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].docItemName"
                                                                    placeholder="${msg.getMsg('L04404', locale)}"
                                                                    cssClass="form-control"
                                                                    maxlength="60" />
                                                    </div>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 nopadding">
                                            <div class="col-sm-12 nopadding"><input type="file" class="btn btn-file" id="file-input-${lv1Status.index}-${lv2EtcStatus.index}" name="file-input-name-${lv1Status.index}-${lv2EtcStatus.index}" data-upload-button-id="upload-button-${lv1Status.index}-${lv2EtcStatus.index}"/></div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="col-sm-4 nopadding">
                                                <button id="upload-button-${lv1Status.index}-${lv2EtcStatus.index}"
                                                        class="btn btn-default btn-upload"

                                                        data-checkbox-id="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.checkedFg"

                                                        data-file-input-id="file-input-${lv1Status.index}-${lv2EtcStatus.index}"
                                                        data-img-yn-id="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.imgYn"
                                                        data-file-download-link-id="file-download-link-${lv1Status.index}-${lv2EtcStatus.index}"
                                                        data-file-delete-link-id="file-delete-link-${lv1Status.index}-${lv2EtcStatus.index}"
                                                        data-org-filename-id="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.orgFileName"
                                                        data-target-subcontainer-id="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}."

                                                        data-doc-prop-docSeq="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.docSeq"
                                                        data-doc-prop-docTypeCode="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.docTypeCode"
                                                        data-doc-prop-docGrp="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.docGrp"
                                                        data-doc-prop-docItemCode="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.docItemCode"
                                                        data-doc-prop-docItemName="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.docItemName"
                                                        data-doc-prop-grpLabel="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.grpLabel"
                                                        data-doc-prop-fileExt="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.fileExt"
                                                        data-doc-prop-imgYn="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.imgYn"
                                                        data-doc-prop-filePath="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.filePath"
                                                        data-doc-prop-fileName="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.fileName"
                                                        data-doc-prop-orgFileName="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.orgFileName"
                                                        data-doc-prop-docItemNameXxen="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.docItemNameXxen"
                                                        data-doc-prop-docGrpName="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.docGrpName"
                                                        data-doc-prop-fileUploadFg="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.fileUploadFg"
                                                        data-doc-prop-displayGrpFg="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.displayGrpFg"
                                                        data-doc-prop-checkedFg="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.checkedFg"
                                                        data-doc-prop-admsNo="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.admsNo"
                                                        data-doc-prop-admsCorsNo="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.admsCorsNo"
                                                        data-doc-prop-detlMajCode="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.detlMajCode"
                                                        data-doc-prop-admsCodeGrp="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.admsCodeGrp"
                                                        data-doc-prop-admsCode="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.admsCode"
                                                        data-doc-prop-grpLevel="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.grpLevel"
                                                        data-doc-prop-docItemGrp="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.docItemGrp"
                                                        data-doc-prop-upCodeGrp="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.upCodeGrp"
                                                        data-doc-prop-upCode="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.upCode"
                                                        data-doc-prop-lastYn="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.lastYn"
                                                        data-doc-prop-mdtYn="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.mdtYn"
                                                        data-doc-prop-uploadYn="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.uploadYn"
                                                        data-doc-prop-sendCnt="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.sendCnt"
                                                ><spring:message code="L04104"/><%--올리기--%></button>
                                            </div>
                                            <div class="col-sm-4 upload-delete-button-container-${lv1Status.index}-${lv2EtcStatus.index}" style='display: none;'>
                                                <a class="btn btn-default file-download" id="file-download-link-${lv1Status.index}-${lv2EtcStatus.index}"><spring:message code="L04105"/><%--내려받기--%></a>
                                            </div>
                                            <div class="col-sm-4 upload-delete-button-container-${lv1Status.index}-${lv2EtcStatus.index}" style='display: none;'>
                                                <a class="btn btn-default file-delete" id="file-delete-link-${lv1Status.index}-${lv2EtcStatus.index}"
                                                    data-checkbox-id="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.checkedFg"
                                                    data-docitemname-id="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.docItemName"
                                                    data-upload-button-id="upload-button-${lv1Status.index}-${lv2EtcStatus.index}"
                                                    data-button-container-class="upload-delete-button-container-${lv1Status.index}-${lv2EtcStatus.index}"
                                                    data-fileUploadFg-id="documentContainerList${lv1Status.index}.subContainer${lv2EtcStatus.index}.fileUploadFg"><spring:message code="L04106"/><%--삭제--%></a>
                                            </div>
                                        </div>
                                        <spring:bind path="documentContainerList[${lv1Status.index}].subContainer[${lv2EtcStatus.index}].fileUploadFg">
                                            <c:if test="${status.error}">
                                                <div class="col-sm-12 validation-container">
                                                    <div class="validation-error">${status.errorMessage}</div>
                                                </div>
                                            </c:if>
                                        </spring:bind>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <%-- level2인 기타 첨부 서류 입력 란 --%>

                        </c:otherwise>
                    </c:choose>
                    <c:if test="${lv1Container.lastYn == null || lv1Container.lastYn == 'N'}">
                            </div>
                        </div>
                    </c:if>
                </c:forEach>

                    </div>
                </div>
                <div class="spacer-tiny"></div>
                <div class="btn-group btn-group-justified">
                    <div class="btn-group">
                        <button id="saveDocument" type="button" class="btn btn-primary btn-lg btn-save" data-saveType="document"><spring:message code="L04201"/><%--첨부 파일 저장--%></button>
                    </div>
                </div>
            </div>
            <div class="spacer-tiny"></div>
            <div class="panel-body tab-content">
                <span style="color: blue; font-size: 20px; font-weight: 900;"><spring:message code="U04521"/><%--첨부 파일 저장 버튼을 눌러서 저장한 후에만 원서 미리보기 생성 버튼이 활성화 됩니다.--%></span>
            </div>
            <div class="btn-group btn-group-justified">
                <div class="btn-group">
                    <button id="generateApplication" type="button" class="btn btn-warning btn-lg btn-save" data-saveType="generate" ${document.application.applStsCode != '00004' || resultMsg != msg.getMsg('U325') ? 'disabled="disabled"' : ''}><spring:message code="L04202"/><%--원서 미리보기 생성--%></button>
                </div>
            </div>
            <div id="spinner" class="btn-group btn-group-justified" style="display: none;">
                <div class="btn-group">
                    <div class="col-sm-12">
                        <div class="loader"></div>
                    </div>
                    <div class="col-sm-12" style="font-size: 24px; color: #55aaff; text-align: center;"><spring:message code="U04512"/></div>  <%--Loading...--%>
                </div>
            </div>

            <div class="btn-group btn-group-justified">
                <div class="btn-group">
                    <button id="previewApplication" type="button" style="display: none;" class="btn btn-info btn-lg btn-save" data-saveType="preview" <c:if test="${document.application.applStsCode != '00004'}">disabled</c:if> ><spring:message code="L04203"/><%--원서 미리보기 다운로드--%></button>
                </div>
            </div>
            <div class="spacer-tiny"></div>
            <div class="panel-body tab-content">
                <span style="color: blue; font-size: 20px; font-weight: 900;"><spring:message code="U04522"/><%--첨부 파일 저장 버튼을 눌러서 저장한 후에만 원서 제출 버튼이 활성화 됩니다.--%></span><br/>
                <span style="color: blue; font-size: 20px; font-weight: 900;"><spring:message code="U04511"/><%--원서 제출 후에는 지원 정보를 수정할 수 없습니다. 입력한 정보가 정확한 지 확인 후에 원서 제출을 진행해 주세요.--%></span>
            </div>
            <div class="btn-group btn-group-justified">
                <div class="btn-group">
                    <button id="submitApplication" type="button" class="btn btn-success btn-lg btn-save" data-saveType="submit" ${document.application.applStsCode != '00004' || resultMsg != msg.getMsg('U325') ? 'disabled="disabled"' : ''}><spring:message code="L04204"/><%--원서 제출--%></button>
                </div>
            </div>
            <div id="spinner2" class="btn-group btn-group-justified" style="display: none;">
                <div class="btn-group">
                    <div class="col-sm-12">
                        <div class="loader"></div>
                    </div>
                    <div class="col-sm-12" style="font-size: 24px; color: #55aaff; text-align: center;"><spring:message code="U04512"/></div>  <%--Loading...--%>
                </div>
            </div>
        </div> <%--myTabContent--%>
            <input type="hidden" name="application.userId" id="userId" value="${document.application.userId}"/>
        </form:form>
    </div> <%--container--%>
</section>
<content tag="local-script">
    <script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery-ui.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
        var applNo = document.getElementById('applNo').value = '${document.application.applNo}',
            admsNo = document.getElementById('admsNo').value = '${document.application.admsNo}',
            applStsCode = document.getElementById('applStsCode').value = '${document.application.applStsCode}',
            entrYear = document.getElementById('entrYear').value = '${document.application.entrYear}',
            admsTypeCode = document.getElementById('admsTypeCode').value = '${document.application.admsTypeCode}';

        <%-- 원서 작성 현황 처리 --%>
        var processCurrentStep = function (applStsCode) {
            var code = Number(applStsCode),
                    stepBox = document.getElementById('step_box'),
                    l = stepBox.children.length, i,
                    navPcTabUL = document.getElementById('navPcTabUL'),
                    navMbTabUL = document.getElementById('navMbTabUL');
            for ( i = 0 ; i < code && i < l ; i++ ) {
                stepBox.children[i].className = 'active';
                navPcTabUL.children[i].setAttribute('data-tab-available', 'true');
                if (navPcTabUL.children[i+1])
                    navPcTabUL.children[i+1].setAttribute('data-tab-available', 'true');
                navMbTabUL.children[i].setAttribute('data-tab-available', 'true');
                if (navMbTabUL.children[i+1])
                    navMbTabUL.children[i+1].setAttribute('data-tab-available', 'true');
            }
        };
        processCurrentStep(document.getElementById('applStsCode').value);
        <%-- 원서 작성 현황 처리 --%>

        <%-- active 탭 표시 --%>
        var setActiveTab = function () {
            var urlStr = document.location.pathname,
                    substrToLastSlash = urlStr.substring(0, urlStr.lastIndexOf("/")),
                    tabName = substrToLastSlash.substring(substrToLastSlash.lastIndexOf("/") + 1),
                    targetPcTabLI = document.getElementById('tab-pc-' + tabName),
                    targetMbTabLI = document.getElementById('tab-mb-' + tabName);

            targetPcTabLI.className = 'active activeTab';
            targetMbTabLI.className = 'active activeTab';
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
            event.preventDefault();

            var form = document.forms[0],
                formData, isSpecialCharactersInFileName,
                saveType = this.getAttribute('data-saveType');
            if (saveType == 'document') {
                $('#overlay').show();

                $('.hidden-file-name').each( function() {
                    var docItemName = this.getAttribute('data-doc-item-name');
                    var fileName = this.value;

//                    console.log('fileName', fileName);
                    var fileNameWithoutFake = fileName.substring((this.getAttribute('data-file-input-id').length+1));
//                    console.log(fileNameWithoutFake);
//                    console.log('-------------------');

                    if (fileNameWithoutFake.indexOf(':/')) {
                        fileNameWithoutFake = fileNameWithoutFake.substring(fileNameWithoutFake.lastIndexOf('/')+1);
//                        console.log(fileNameWithoutFake);
//                        console.log('======================================================');
                    }

                    if (fileNameWithoutFake.indexOf("\'") > -1 ||
                            fileNameWithoutFake.indexOf('\"') > -1 ||
                            fileNameWithoutFake.indexOf('&') > -1 ||
                            fileNameWithoutFake.indexOf('\\') > -1 ||
                            fileNameWithoutFake.indexOf('/') > -1 ||
                            fileNameWithoutFake.indexOf(':') > -1 ||
                            fileNameWithoutFake.indexOf('~') > -1 ||
                            fileNameWithoutFake.indexOf('!') > -1 ||
                            fileNameWithoutFake.indexOf('@') > -1 ||
                            fileNameWithoutFake.indexOf('#') > -1 ||
                            fileNameWithoutFake.indexOf('$') > -1 ||
                            fileNameWithoutFake.indexOf('%') > -1 ||
                            fileNameWithoutFake.indexOf('^') > -1 ||
                            fileNameWithoutFake.indexOf('*') > -1 ||
                            fileNameWithoutFake.indexOf('+') > -1

                    ) {
                        alert('<spring:message code="U04527"/> : ' + docItemName + " : " + fileNameWithoutFake);  // 업로드 된 파일 이름에 특수 문자가 있습니다.\\n업로드 된 파일을 삭제하고, 업로드 할 파일 이름에서 특수 문자 제거 후 다시 업로드 해주세요.\\n\\n문서명
                        $('#overlay').hide();
                        isSpecialCharactersInFileName = true;
                    }
                });

                var checkDocChckYn = document.getElementById('docChckYn');
                if (!checkDocChckYn.checked) {
                    alert('<spring:message code="U04501"/>');//첨부 파일 안내 사항 확인란에 체크 하셔야 합니다.
                    $('#overlay').hide();
                    checkDocChckYn.focus();
                } else {
                    if (!isSpecialCharactersInFileName) {
                        checkDocChckYn.value = "Y";
                        form.action = "${contextPath}/application/document/save";
                        <c:if test="${isSYSADMIN}">
                        form.action = "${contextPath}/sysadmin/document/save";
                        </c:if>
                        form.submit();
                    }
                }
            } else if (saveType == 'generate') {
                $('#overlay').show();
                alert('<spring:message code="U04525"/>'); // 원서 미리보기 생성 및 원서 미리 보기에서 오류 발생 시 업로드 된 파일 이름에서 특수 문자를 제거한 후 다시 시도해 주십시오.
                document.getElementById('spinner').style.display = 'block';
                formData = $(form).serialize();
                <%-- 지원서 파일 정보 DB 저장 --%>
                $.ajax({
                    type: 'POST',
                    url: <c:choose><c:when test="${isSYSADMIN}">'${contextPath}/sysadmin/document/generate/appl'</c:when><c:otherwise>'${contextPath}/application/document/generate/appl'</c:otherwise></c:choose>,
                    data: formData,
                    success: function (data) {
                        var ec = JSON.parse(data);
                        if (ec.result == 'SUCCESS') {
                            if (console) {
                                console.log('원서 파일 정보 저장 및 원서 파일 생성 완료');
                            }
                            <%-- 원서 파일 생성 및 업로드는 /document/generate/appl에서 모두 처리함 --%>
                            <%-- 파일 생성 --%>
                            <%--$.ajax({--%>
                                <%--type: 'POST',--%>
                                <%--url: <c:choose><c:when test="${isSYSADMIN}">'${contextPath}/sysadmin/document/generate/tempMergedApplicationForm'</c:when><c:otherwise>'${contextPath}/pdf/generate/tempMergedApplicationForm'</c:otherwise></c:choose>,--%>
                                <%--data: formData,--%>
                                <%--success: function (data) {--%>
                                    <%--var result = JSON.parse(data);--%>
                                    <%--if (result.result === 'FAIL') { // 파일 합치기 실패--%>
                                        <%--document.getElementById('spinner').style.display = 'none';--%>
                                        <%--alert(result.message);--%>
                                    <%--} else { //--%>
                                        <%--document.getElementById('spinner').style.display = 'none';--%>
                                        <%--document.getElementById('previewApplication').style.display = 'block';--%>
                                    <%--}--%>
                                    <%--$('#overlay').hide();--%>
                                <%--},--%>
                                <%--error: function (data, status, e) {--%>
                                    <%--document.getElementById('spinner').style.display = 'none';--%>
<%--//                                    alert(result.message);--%>
                                    <%--$('#overlay').hide();--%>
                                <%--}--%>
                            <%--});--%>
                            <%-- 파일 생성 --%>
                        }
                    },
                    error: function (data, status, e) {
                        if (console) {
                            console.log('원서 파일 정보 저장 및 원서 파일 생성 실패');
                        }
                        $('#overlay').hide();
                    }
                });
                <%-- 지원서 파일 정보 DB 저장 --%>

            } else if (saveType == 'preview') {
                alert('<spring:message code="U04525"/>'); // 원서 미리보기 생성 및 원서 미리 보기에서 오류 발생 시 업로드 된 파일 이름에서 특수 문자를 제거한 후 다시 시도해 주십시오.
                <%--form.action = <c:choose><c:when test="${isSYSADMIN}">'${contextPath}/sysadmin/document/download/tempMergedApplicationForm'</c:when><c:otherwise>"${contextPath}/pdf/download/tempMergedApplicationForm"</c:otherwise></c:choose>;--%>
                form.action = <c:choose><c:when test="${isSYSADMIN}">'${contextPath}/sysadmin/document/download/tempMergedApplicationForm'</c:when><c:otherwise>"${contextPath}/pdf/download/applForm"</c:otherwise></c:choose>;
                form.submit();
            } else if (saveType == 'submit') {
                $('#overlay').show();
                if (confirm('<spring:message code="U04526"/>\n\n<spring:message code="U04509"/>')) {// 원서 제출에서 오류 발생 시 업로드 된 파일 이름에서 특수 문자를 제거한 후 다시 시도해 주십시오. 원서 제출 후에는 원서 내용을 수정할 수 없습니다.\n\n계속하시겠습니까?
                    document.getElementById('spinner2').style.display = 'block';
                    form.action = "${contextPath}/application/document/submit";
                    <c:if test="${isSYSADMIN}">
                    form.action = "${contextPath}/sysadmin/document/submit";
                    </c:if>
                    form.target = "_self";
                    form.submit();
                } else {
                    $('#overlay').hide();
                }
            }
        };
        $('.btn-save').on('click', formProcess);
        <%-- 하단 버튼 처리 --%>

        <%-- 파일 선택 버튼 이벤트 --%>
        $('.btn-file').on('change', function (e) { // 한번 업로드한 inputfile은 이벤트가 발생 안한다.
//            var uploadButton = $(this.dataset.uploadButtonId);
            var uploadButton = $(document.getElementById(this.getAttribute('data-upload-button-id')));
            $(uploadButton).removeClass('disabled');
            $(uploadButton).val('올리기');
        });
        <%-- 파일 선택 버튼 이벤트 --%>

        <%-- 파일 업로드 버튼 이벤트 --%>
        $('.btn-upload').on('click', function (e) {
            e.preventDefault();
            $("#overlay").show();
            // 원서 미리보기 생성 비활성화
            document.getElementById('generateApplication').setAttribute('disabled', 'disabled');
            // 원서 미리보기 비활성화
            document.getElementById('previewApplication').setAttribute('disabled', 'disabled');
            // 원서 제출 비활성화
            document.getElementById('submitApplication').setAttribute('disabled', 'disabled');
            var actionUrl =
                    <c:choose>
                        <c:when test="${isSYSADMIN}">"${contextPath}/sysadmin/document/fileUpload"</c:when>
                        <c:otherwise>"${contextPath}/application/document/fileUpload"</c:otherwise>
                    </c:choose>,
                    docItemName = document.getElementById(this.getAttribute('data-doc-prop-docItemName')),
                    fileInputId = this.getAttribute('data-file-input-id'),
                    fileInput = document.getElementById(fileInputId),
                    fileInputName = fileInput.getAttribute("name"),
                    fileName = fileInput.files[0].name,
                    imgYn = document.getElementById(this.getAttribute('data-img-yn-id')).value,
                    targetFileDownloadLinkId = this.getAttribute('data-file-download-link-id'),
                    targetFileDeleteLinkId = this.getAttribute('data-file-delete-link-id'),
                    targetOrgFileNameHiddenId = this.getAttribute('data-org-filename-id'),
                    targetSubContainerId = this.getAttribute('data-target-subcontainer-id'),
                    regexpImage = (/\.(gif|jpg|png)$/i),
                    regexpPDF = (/\.(pdf)$/i),
                    extIsOk = false,
                    checkboxId = this.getAttribute('data-checkbox-id'),
                    targetButton = this,
                    fileNameWithoutFake = fileInput.files[0].name,
                    fileSize = fileInput.files[0].size
                    ;
            if (fileName.length > 80) {
                alert('<spring:message code="U04513"/>');  /*파일 경로가 너무 깁니다. \\n\\n파일을 PC의 바탕화면이나 D: 드라이브 바로 아래로 복사하신 후에 업로드해 주세요.*/
                $('#overlay').hide();
                return false;
            }
            if (fileNameWithoutFake.indexOf("\'") > -1 ||
                    fileNameWithoutFake.indexOf('\"') > -1 ||
                    fileNameWithoutFake.indexOf('&') > -1 ||
                    fileNameWithoutFake.indexOf('\\') > -1 ||
                    fileNameWithoutFake.indexOf('/') > -1 ||
                    fileNameWithoutFake.indexOf(':') > -1 ||
                    fileNameWithoutFake.indexOf('~') > -1 ||
                    fileNameWithoutFake.indexOf('!') > -1 ||
                    fileNameWithoutFake.indexOf('@') > -1 ||
                    fileNameWithoutFake.indexOf('#') > -1 ||
                    fileNameWithoutFake.indexOf('$') > -1 ||
                    fileNameWithoutFake.indexOf('%') > -1 ||
                    fileNameWithoutFake.indexOf('^') > -1 ||
                    fileNameWithoutFake.indexOf('*') > -1 ||
                    fileNameWithoutFake.indexOf('+') > -1
            ) {
                alert('<spring:message code="U04523"/>');  /*파일 이름에 특수 문자가 있습니다.\\n\\n파일 이름에서 특수 문자를 제거해 주세요.*/
                $('#overlay').hide();
                return false;
            }
             if (fileSize > 1024 * 1024 * 30) {
                 alert('<spring:message code="U04524" arguments="30"/>');
                 $('#overlay').hide();
                 return false;
             }
            if (docItemName && !docItemName.value.length > 0) {
                alert('<spring:message code="U04502"/>');//서류명을 직접 입력해 주세요.
                $('#overlay').hide();
                docItemName.focus();
                return false;
            }
            if ((fileInput.files && fileInput.files.length) || fileInput.value != "") {
                if (imgYn == 'Y') {
                    if (regexpImage.test(fileName)) {
                        extIsOk = true;
                    } else {
                        alert('<spring:message code="U04503"/>');//사진은 GIF, JPG, PNG 만 업로드 할 수 있습니다.
                        $('#overlay').hide();
                        return false;
                    }
                } else if (regexpPDF.test(fileName)) {
                    extIsOk = true;
                } else {
                    alert('<spring:message code="U04504"/>');//첨부파일은 PDF 파일만 업로드 할 수 있습니다.
                    $('#overlay').hide();
                    return false;
                }

                if (extIsOk) {
                    document.getElementById(checkboxId).checked = true;
                    $.ajaxFileUpload({
                        url: actionUrl,
                        secureuri: false,
                        fileElementId: fileInputId,
                        dataType: 'json',
                        data: {
                            docSeq: document.getElementById(this.getAttribute('data-doc-prop-docSeq')).value,
                            docTypeCode: document.getElementById(this.getAttribute('data-doc-prop-docTypeCode')).value,
                            docGrp: document.getElementById(this.getAttribute('data-doc-prop-docGrp')).value,
                            docItemCode: document.getElementById(this.getAttribute('data-doc-prop-docItemCode')).value,
                            docItemName: document.getElementById(this.getAttribute('data-doc-prop-docItemName')).value,
                            grpLabel: document.getElementById(this.getAttribute('data-doc-prop-grpLabel')).value,
                            fileExt: document.getElementById(this.getAttribute('data-doc-prop-fileExt')).value,
                            imgYn: document.getElementById(this.getAttribute('data-doc-prop-imgYn')).value,
                            filePath: document.getElementById(this.getAttribute('data-doc-prop-filePath')).value,
                            fileName: document.getElementById(this.getAttribute('data-doc-prop-fileName')).value,
                            orgFileName: document.getElementById(this.getAttribute('data-doc-prop-orgFileName')).value,
                            docItemNameXxen: document.getElementById(this.getAttribute('data-doc-prop-docItemNameXxen')).value,
                            docGrpName: document.getElementById(this.getAttribute('data-doc-prop-docGrpName')).value,
                            fileUploadFg: document.getElementById(this.getAttribute('data-doc-prop-fileUploadFg')).value,
                            displayGrpFg: document.getElementById(this.getAttribute('data-doc-prop-displayGrpFg')).value,
                            checkedFg: document.getElementById(this.getAttribute('data-doc-prop-checkedFg')).value,
                            admsCorsNo: document.getElementById(this.getAttribute('data-doc-prop-admsCorsNo')).value,
                            detlMajCode: document.getElementById(this.getAttribute('data-doc-prop-detlMajCode')).value,
                            admsCodeGrp: document.getElementById(this.getAttribute('data-doc-prop-admsCodeGrp')).value,
                            admsCode: document.getElementById(this.getAttribute('data-doc-prop-admsCode')).value,
                            grpLevel: document.getElementById(this.getAttribute('data-doc-prop-grpLevel')).value,
                            docItemGrp: document.getElementById(this.getAttribute('data-doc-prop-docItemGrp')).value,
                            upCodeGrp: document.getElementById(this.getAttribute('data-doc-prop-upCodeGrp')).value,
                            upCode: document.getElementById(this.getAttribute('data-doc-prop-upCode')).value,
                            lastYn: document.getElementById(this.getAttribute('data-doc-prop-lastYn')).value,
                            mdtYn: document.getElementById(this.getAttribute('data-doc-prop-mdtYn')).value,
                            uploadYn: document.getElementById(this.getAttribute('data-doc-prop-uploadYn')).value,
                            sendCnt: document.getElementById(this.getAttribute('data-doc-prop-sendCnt')).value,

                            fieldName: fileInputName,
                            targetButton: this.id,
                            targetFileDownloadLinkId: targetFileDownloadLinkId,
                            targetFileDeleteLinkId: targetFileDeleteLinkId,
                            applNo: document.getElementById('applNo').value,
                            admsNo: document.getElementById('admsNo').value,

                            userId : '${document.application.userId}'
                        },
                        success: function (data, status) {
                            var d = JSON.parse(data.data);
                            if (data.result == 'SUCCESS') {
                                var targetBtnId = d.targetButton,
                                        targetBtn = document.getElementById(targetBtnId),
                                        $targetBtn = $(targetBtn),
                                        originalFileName = d.originalFileName,
                                        targetFileDownloadLinkId = d.targetFileDownloadLinkId,
                                        targetFileDeleteLinkId = d.targetFileDeleteLinkId,
                                        applNo = d.applNo,
                                        oneDocument = d.oneDocument,
                                        docSeq = oneDocument.docSeq,
                                        oneDocumentHidden;
                                $targetBtn.removeClass("btn-default");
                                $targetBtn.removeClass("btn-danger");
                                $targetBtn.addClass("btn-info");
                                $targetBtn.val("<spring:message code="U04508"/>");//올리기 성공

                                document.getElementById(targetFileDownloadLinkId).parentNode.style.display = 'block';
                                document.getElementById(targetFileDownloadLinkId).setAttribute('href', '${contextPath}/application/document/fileDownload/' + applNo + '/' + docSeq);

                                document.getElementById(targetFileDeleteLinkId).parentNode.style.display = 'block';
                                document.getElementById(targetFileDeleteLinkId).setAttribute('href', '${contextPath}/application/document/fileDelete/' + applNo + '/' + docSeq);

                                document.getElementById(targetOrgFileNameHiddenId).value = originalFileName;

                                for (var key in oneDocument) {
                                    oneDocumentHidden = document.getElementById(targetSubContainerId + key);
                                    if (oneDocumentHidden) {
                                        oneDocumentHidden.value = oneDocument[key];
                                    }
                                }
                                alert(d.resultMessage);
                            } else {
                                alert(data.message);
                                document.getElementById(checkboxId).checked = false;
                                docItemName.value = '';
                            }

                            $('#overlay').hide();
                        },
                        error: function (data, status, e) {
//                            var d = JSON.parse(data.data);
                            $(targetButton).removeClass("btn-default"),
                            $(targetButton).addClass("btn-danger"),
                            $(targetButton).val("<spring:message code="U04506"/>");//올리기 실패
                            if(console) {
                                console.log("data : ", data);
                                console.log("status : ", status);
                                console.log("e : ", e);
                            }
                            $('#overlay').hide();
                        }
                    });
                }

            } else {
                alert("<spring:message code="U04505"/>");//파일을 선택해 주십시오
                $('#overlay').hide();
            }


            return false;
        });
        <%-- 파일 업로드 버튼 이벤트 --%>

        <%-- 파일 삭제 링크 이벤트 --%>
        $('.file-delete').on('click', function (e) {
            e.preventDefault();
            $("#overlay").show();
            // 원서 미리보기 생성 비활성화
            document.getElementById('generateApplication').setAttribute('disabled', 'disabled');
            // 원서 미리보기 비활성화
            document.getElementById('previewApplication').setAttribute('disabled', 'disabled');
            // 원서 제출 비활성화
            document.getElementById('submitApplication').setAttribute('disabled', 'disabled');
            var targetCheckBox = document.getElementById(this.getAttribute('data-checkbox-id')),
                targetDocItemName = document.getElementById(this.getAttribute('data-docitemname-id')),
                targetUploadButton = document.getElementById(this.getAttribute('data-upload-button-id')),
                targetButtonContainerClass = '.' + this.getAttribute('data-button-container-class'),
                targetFileUploadFg = document.getElementById(this.getAttribute('data-fileUploadFg-id'));
            if (confirm('<spring:message code="U04510"/>')) {//첨부한 파일을 삭제하시겠습니까?
                $.ajax({
                    type: 'POST',
                    url: this.href,
                    success: function (data) {
                        var data = JSON.parse(data),
                            $targetUploadButton = $(targetUploadButton);
                        if (data.result == 'SUCCESS') {
                            targetCheckBox.checked = false,
                            targetDocItemName.type == 'text' ? (targetDocItemName.value = '', targetDocItemName.placeholder = '${msg.getMsg('L04404', locale)}' ) : true,
                            $targetUploadButton.removeClass('btn-info'),
                            $targetUploadButton.addClass('btn-default'),
                            $targetUploadButton.val("올리기"),
                            $(targetButtonContainerClass).css('display', 'none'),
                            targetFileUploadFg.value = false;
                            alert(data.message);
                        } else {
                            alert('<spring:message code="U04507"/>');//파일 삭제에 실패했습니다.
                        }
                        $('#overlay').hide();
                    },
                    error: function (data, status, e) {
                        alert('<spring:message code="U04507"/>');//파일 삭제에 실패했습니다.
                    }
                });
            } else {
                $('#overlay').hide();
            }
        });
        <%-- 파일 삭제 링크 이벤트 --%>

        <%-- form-group-block 추가/삭제에 대한 처리 시작 --%>
        <%-- id, name 재설정 시작 --%>
        var updateIdAndName = function ( block, index ) {
            var i, name, prefix, suffix, input, items, label;
            var input = block.querySelector('input');

            name = input.name;

            items = block.querySelectorAll('input, select, label');
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
                    if (items[i].id.indexOf('userCUDType') > 0) {
                        items[i].value = "INSERT";
                    }
                }
            }
            resetCalendar(block, '.input-group.date>input');

            var removeBtn = block.querySelector('.btn-remove');
            if (removeBtn) {
                removeBtn.setAttribute('data-block-index', index);
            }
        };
        <%-- id, name 재설정 끝 --%>

        <%-- 복제된 입력폼 내용 초기화 시작 --%>
        var resetBlockContents = function ( block ) {
            var i, items, itemName;
            block.style.display = 'block';
            items = block.querySelectorAll('input, select');
            if (items) {
                for (i = 0; i <items.length; i++) {
                    if (items[i].type == 'hidden') {
                        itemName = items[i].name;
                        if (itemName.indexOf('userCUDType') > 0) {
                            items[i].value = "INSERT";
                        }
                    }
                    if (items[i].type != 'hidden' && items[i].type != 'radio' && items[i].type != 'checkbox' && items[i].type != 'button') {
                        items[i].value = '';
                        items[i].setAttribute('value', '');
                    }
                    if (items[i].checked != null) {
                        items[i].checked = false;
                    }
                }
            }
            resetCalendar(block, '.input-group.date>input');
        };
        <%-- 복제된 입력폼 내용 초기화 끝 --%>

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
                updateIdAndName($cloneObj[0], blocks.length);
                resetBlockContents($cloneObj[0]);
                container.insertBefore($cloneObj[0], originBlock.nextSibling);
            }
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
//            var blockIndex = target.dataset.blockIndex;
            var blockIndex = target.getAttribute('data-block-index');
            var listName = target.getAttribute('data-list-name');
            var userCUDType = document.getElementById(listName + blockIndex + '.userCUDType');

            switch (userCUDType.value) {
                case 'INSERT' :
                    for (i = parseInt(blockIndex) + 1; i < length; i++) {
                        updateIdAndName(blocks[i], i - 1);
                    }
                    if (length <= 1) {
                        resetBlockContents(blockToRemove);
                    } else {
                        blockToRemove.parentNode.removeChild(blockToRemove);
                    }
                    break;
                case 'UPDATE' :
                    userCUDType.value = 'DELETE';
                    blockToRemove.style.display = 'none';
                    break;
            }
        });
        <%-- form-group-block 추가/삭제에 대한 처리 끝 --%>

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
