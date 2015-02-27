<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title>일반 전형</title>
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
                    <i class="fa fa-calendar fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b>2015년도 일반 전형 주요 일정 안내</b></span>
                </div>
                <div class="spacer-small">&nbsp;</div>
                <div class="col-sm-offset-1 col-md-10 align-center text-gray">
                    <div class="spacer-tiny">&nbsp;</div>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th colspan="2">지원 절차</th>
                            <th>2015년 전기전형(2015.3월 입학)</th>
                            <th>2015년 후기전형(2015.9월 입학)</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td rowspan="3" valign="center">기관 토플<br/>(필요자만 응시)</td>
                            <td>접수일</td>
                            <td>2014. 9. 1(월) ~ 9.21(일)</td>
                            <td>미정</td>
                        </tr>
                        <tr>
                            <td>시험일</td>
                            <td>2014. 9.27(토)</td>
                            <td>미정</td>
                        </tr>
                        <tr>
                            <td>성적발표</td>
                            <td>2014.10. 1(수) ~</td>
                            <td>미정</td>
                        </tr>
                        <tr>
                            <td colspan="2">입학원서 접수</td>
                            <td>2014. 9. 29(월) ~ 10. 8(수)</td>
                            <td>2015. 3. 30(월) ~  4. 8(수)</td>
                        </tr>
                        <tr>
                            <td colspan="2">구술시험 대상자 발표</td>
                            <td>2014.10.31(금)</td>
                            <td>2015. 4.24(금)</td>
                        </tr>
                        <tr>
                            <td colspan="2">구술면접 및 음대실기</td>
                            <td>2014.11. 8(토)</td>
                            <td>2015. 5. 2(토)</td>
                        </tr>
                        <tr>
                            <td colspan="2">최종 합격자 발표</td>
                            <td>2014.11.28(금)</td>
                            <td>2015. 5.29(금)</td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="col-sm-12 text-gray">
                        <i class="fa fa-download fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b>모집 요강 다운로드</b></span>
                    </div>
                    <div class="spacer-tiny">&nbsp;</div>
                    <div class="col-sm-12">아래는 참고용 2014년 후기 자료이며, 2015년 전기 요강은 2014. 9월초 업로드 예정</div>
                    <div class="spacer-tiny">&nbsp;</div>
                    <div>
                        <table class="table table-stripped">
                            <tr>
                                <td align="left">2014학년도 9월입학 대학원 일반 전형 요강<a href="http://graduate.yonsei.ac.kr/download/sub03/info(2014-2).pdf" target="_blank"><img src="${contextPath}/img/common/button_pdf.gif"/></a><a href=""><img src="${contextPath}/img/common/button_word.gif"/></a></td>
                            </tr>
                            <tr>
                                <td align="left">학업 및 연구계획서<a href="http://graduate.yonsei.ac.kr/download/sub03/D2006form4.pdf" target="_blank"><img src="${contextPath}/img/common/button_pdf.gif"/></a><a href="http://graduate.yonsei.ac.kr/download/sub03/D2006form4.hwp" target="_blank"><img src="${contextPath}/img/common/button_hwp.gif"/></a></td>
                            </tr>
                            <tr>
                                <td align="left">외국어시험면제승인서  ※ 연세대 석사를 마치고 3년이내에 박사과정 진학하는 자만 작성(건축공학과 제외)</td>
                            </tr>
                            <tr>
                                <td align="left">학력조회 동의서</td>
                            </tr>
                            <%--<tr>--%>
                            <%--<td align="left">최종 출신대학 확인서</td>--%>
                            <%--</tr>--%>
                            <%--<tr>--%>
                            <%--<td align="left">치과대학, 원주의과대학(의학,간호학,치위생학) 전공교실 주임교수 확인서</td>--%>
                            <%--</tr>--%>
                            <tr>
                                <td align="left">원주의과대학 전일제 대학원생 장학금 지급 신청서</td>
                            </tr>
                        </table>
                    </div>
                    <div>
                        <a href="http://www.hancom.com/downLoad.downPU.do?mcd=002" target="_blank"><img src="${contextPath}/img/common/button_hwp.gif"/> 한글 뷰어 다운로드</a>
                        <span style="display:inline-block; width:50px"></span>
                        <a href="http://get.adobe.com/kr/reader/" target="_blank"><img src="${contextPath}/img/common/button_pdf.gif"/> PDF 뷰어 다운로드</a>
                    </div>
                    <div class="spacer-tiny">&nbsp;</div>
                    <div>
                        <form id=generalApplyForm" action="${contextPath}/application/agreement" method="post">
                            <input type="hidden" name="admsNo" value="${admsGeneral.admsNo}" />
                            <input type="hidden" name="entrYear" value="${admsGeneral.entrYear}" />
                            <input type="hidden" name="admsTypeCode" value="${admsGeneral.admsType}" />
                            <button type="submit" class="btn btn-primary btn-lg btn-block" id="composePaper">원서 작성</button>
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
