<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title><spring:message code="L00601"/><%--일반 전형--%></title>
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
                    <i class="fa fa-calendar fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L00601"/><%--2015년도 일반 전형 주요 일정 안내--%></b></span>
                </div>
                <div class="spacer-small">&nbsp;</div>
                <div class="col-sm-offset-1 col-md-10 align-center text-gray">
                    <div class="spacer-tiny">&nbsp;</div>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th colspan="2"><spring:message code="L00602"/><%--지원 절차--%></th>
                            <th><spring:message code="L00603"/><%--2015년 전기전형(2015.3월 입학)--%></th>
                            <th><spring:message code="L00604"/><%--2015년 후기전형(2015.9월 입학)--%></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td rowspan="3" valign="center"><spring:message code="L00605"/><%--기관 토플--%><br/><spring:message code="L00606"/><%--(필요자만 응시)--%></td>
                            <td><spring:message code="L00607"/><%--접수일--%></td>
                            <td>2014. 9. 1(월) ~ 9.21(일)</td>
                            <td>2015. 3. 2(월) ~ 3.22(일)</td>
                        </tr>
                        <tr>
                            <td><spring:message code="L00608"/><%--시험일--%></td>
                            <td>2014. 9.27(토)</td>
                            <td>2015. 3.28(토)</td>
                        </tr>
                        <tr>
                            <td><spring:message code="L00609"/><%--성적발표--%></td>
                            <td>2014.10. 1(수) ~</td>
                            <td>2015. 4. 2(목)</td>
                        </tr>
                        <tr>
                            <td colspan="2"><spring:message code="L00610"/><%--입학원서 접수--%></td>
                            <td>2014. 9. 29(월) ~ 10. 8(수)</td>
                            <td>2015. 4.  8(수) ~  4.10(금)</td>
                        </tr>
                        <tr>
                            <td colspan="2"><spring:message code="L00611"/><%--구술시험 대상자 발표--%></td>
                            <td>2014.10.31(금)</td>
                            <td>2015. 5. 8(금)</td>
                        </tr>
                        <tr>
                            <td colspan="2"><spring:message code="L00612"/><%--구술면접 및 음대실기--%></td>
                            <td>2014.11. 8(토)</td>
                            <td>2015. 5.16(토)</td>
                        </tr>
                        <tr>
                            <td colspan="2"><spring:message code="L00613"/><%--최종 합격자 발표--%></td>
                            <td>2014.11.28(금)</td>
                            <td>2015. 5.29(금)</td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="col-sm-12 text-gray">
                        <i class="fa fa-download fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L00614"/><%--2015학년도 대학원 전기전형 모집 요강 다운로드--%></b></span>
                    </div>
                    <div class="spacer-tiny">&nbsp;</div>
                    <div class="col-sm-12"><spring:message code="L00615"/><%--2015학년도 후기 모집요강은 3월 업로드 예정--%></div>
                    <div class="spacer-tiny">&nbsp;</div>
                    <div>
                        <table class="table table-stripped">
                            <tr>
                                <td align="left"><img src="${contextPath}/img/common/button_pdf.gif"/><a href="http://graduate.yonsei.ac.kr/download/sub03/info(2015-1).pdf" target="_blank"><spring:message code="L00616"/><%--2015학년도 3월 입학 대학원 일반 전형 모집 요강(한국어, PDF)--%></a></td>
                            </tr>
                            <tr>
                                <td align="left"><img src="${contextPath}/img/common/button_pdf.gif"/><a href="http://graduate.yonsei.ac.kr/download/sub03/D2006form4.pdf" target="_blank"><spring:message code="L00618"/><%--학업 및 연구계획서(PDF)--%></a>&nbsp;&nbsp;&nbsp;<img src="${contextPath}/img/common/button_hwp.gif"/><a href="http://graduate.yonsei.ac.kr/download/sub03/D2006form4.hwp" target="_blank"><spring:message code="L00619"/><%--(아래한글)--%></a></td>
                            </tr>
                            <tr>
                                <td align="left"><img src="${contextPath}/img/common/button_pdf.gif"/><a href="http://graduate.yonsei.ac.kr/download/sub03/D2006form1.pdf" target="_blank"><spring:message code="L00620"/><%--외국어시험면제승인서(PDF)  ※ 연세대 석사를 마치고 3년이내에 박사과정 진학하는 자만 작성(건축공학과 제외)--%></a></td>
                            </tr>
                            <tr>
                                <td align="left"><img src="${contextPath}/img/common/button_pdf.gif"/><a href="http://graduate.yonsei.ac.kr/download/sub03/Letter_of_Consent.pdf" targert="_blank"><spring:message code="L00621"/><%--학력조회 동의서--%></a></td>
                            </tr>
                            <tr>
                                <td align="left"><img src="${contextPath}/img/common/button_pdf.gif"/><a href="http://graduate.yonsei.ac.kr/download/sub03/info_scholar.pdf" target="_blank"><spring:message code="L00622"/><%--원주의과대학 전일제 대학원생 장학금 지급 신청서--%></a></td>
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
                        <form id=generalApplyForm" action="${contextPath}/application/agreement" method="post">
                            <input type="hidden" name="admsNo" value="${admsGeneral.admsNo}" />
                            <input type="hidden" name="entrYear" value="${admsGeneral.entrYear}" />
                            <input type="hidden" name="admsTypeCode" value="${admsGeneral.admsType}" />
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
                $('#generalApplyForm').submit();
            });
        })
    </script>
</content>
</body>
</html>
