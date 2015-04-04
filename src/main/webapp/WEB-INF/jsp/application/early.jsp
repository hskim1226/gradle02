<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title><spring:message code="L00801"/><%--조기 전형--%></title>
    <style>
        .my-tooltip {
            display: none;
            background: #505050;
            color: #e0e0e0;
            margin-left: 5px;
            padding: 10px;
            position: absolute;
            z-index: 2;
            width:400px;
            height:30px;
        }
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <div class="row mar-bot10">
            <div class="col-md-offset-1 col-md-10 form-group inner-container-white">
                <div class="col-sm-offset-1 col-sm-10 text-gray">
                    <i class="fa fa-calendar fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L00801"/><%--2015년도 조기 전형 주요 일정 안내--%></b></span>
                </div>
                <div class="spacer-small">&nbsp;</div>
                <div class="col-sm-offset-1 col-md-10 align-center text-gray">
                    <div class="spacer-tiny">&nbsp;</div>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th colspan="2"><spring:message code="L00802"/><%--지원 절차--%></th>
                            <th><spring:message code="L00803"/><%--2015년 후기전형(2016.3월 입학)--%></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td rowspan="3" valign="center"><spring:message code="L00605"/><%--기관 토플--%><br/><spring:message code="L00606"/><%--(필요자만 응시)--%></td>
                            <td><spring:message code="L00607"/><%--접수일--%></td>
                            <td><spring:message code="L00511"/></td>
                        </tr>
                        <tr>
                            <td><spring:message code="L00608"/><%--시험일--%></td>
                            <td><spring:message code="L00512"/></td>
                        </tr>
                        <tr>
                            <td><spring:message code="L00609"/><%--성적발표--%></td>
                            <td><spring:message code="L00513"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><spring:message code="L00610"/><%--입학원서 접수--%></td>
                            <td><spring:message code="L00514"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><spring:message code="L00611"/><%--구술시험 대상자 발표--%></td>
                            <td><spring:message code="L00515"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><spring:message code="L00612"/><%--구술면접 및 음대실기--%></td>
                            <td><spring:message code="L00516"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><spring:message code="L00613"/><%--최종 합격자 발표--%></td>
                            <td><spring:message code="L00517"/></td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="col-sm-12 text-gray">
                        <i class="fa fa-download fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L00814"/><%--모집 요강 다운로드--%></b></span>
                    </div>
                    <div class="spacer-tiny">&nbsp;</div>
                    <div class="col-sm-12"><spring:message code="L00815"/><%--아래는 참고용 2014년 후기 자료이며, 2015년 전기 요강은 2014. 9월초 업로드 예정--%></div>
                    <div class="spacer-tiny">&nbsp;</div>
                    <div>
                        <table class="table table-stripped">
                            <tr>
                                <td align="left"><img src="${contextPath}/img/common/button_pdf.gif"/>
                                    <a href="<spring:message code="L00816URL"/>" target="_blank">
                                        <spring:message code="L00816"/><%--2014학년도 9월입학 대학원 조기 전형 요강--%></a></td>
                            </tr>
                            <tr>
                                <td align="left"><img src="${contextPath}/img/common/button_pdf.gif"/>
                                    <a href="<spring:message code="L00818URL"/>" target="_blank">
                                        <spring:message code="L00818"/><%--학업 및 연구계획서(PDF)--%></a>&nbsp;&nbsp;&nbsp;<img src="${contextPath}/img/common/button_hwp.gif"/>
                                    <a href="<spring:message code="L00819URL"/>" target="_blank">
                                        <spring:message code="L00819"/><%--(아래한글)--%></a></td>
                            </tr>
                            <tr>
                                <td align="left"><img src="${contextPath}/img/common/button_pdf.gif"/>
                                    <a href="<spring:message code="L00821URL"/>" targert="_blank">
                                        <spring:message code="L00821"/><%--학력조회 동의서--%></a></td>
                            </tr>

                        </table>
                    </div>
                    <div>
                        <a href="http://www.hancom.com/downLoad.downPU.do?mcd=002" target="_blank"><img src="${contextPath}/img/common/button_hwp.gif"/> <spring:message code="L00502"/><%--한글 뷰어 다운로드--%></a>
                        <span style="display:inline-block; width:50px"></span>
                        <a href="http://get.adobe.com/kr/reader/" target="_blank"><img src="${contextPath}/img/common/button_pdf.gif"/> <spring:message code="L00503"/><%--PDF 뷰어 다운로드--%></a>
                    </div>
                    <div class="spacer-tiny">&nbsp;</div>
                    <div>
                        <form id="applyForm" action="${contextPath}/application/agreement" method="post">
                            <input type="hidden" name="admsNo" value="${admsEarly.admsNo}" />
                            <input type="hidden" name="entrYear" value="${admsEarly.entrYear}" />
                            <input type="hidden" name="admsTypeCode" value="${admsEarly.admsType}" />
                            <button type="submit" class="btn btn-primary btn-lg btn-block" id="composePaper"><spring:message code="L00506"/><%--원서 작성--%></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<content tag="local-script">
    <script>
        $(document).ready( function() {
            $('#composePaper').click(function(){
                $('#applyForm').submit();
            });
        })
    </script>
</content>
</body>
</html>
