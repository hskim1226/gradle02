<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <style>
        section.notice-detail {
            padding: 200px 0 60px;
            background: #55555f;
            color: #fdfdfd;
            position:relative;
        }

        section.notice-detail h2.slogan {
            color: #fff;
            font-size: 36px;
            font-weight: 900;
        }

        section.notice-detail h3.slogan {
            color: #fdfdfd;
            font-size: 24px;
            font-weight: 500;
            text-align: left;
        }

        section.notice-detail h4.slogan {
            color: #ff8d8d;
            font-size: 18px;
            font-weight: 500;
            text-align: left;
        }

        /* inner heading */
        section.notice-detail.inner {
            background: #eee;
            padding: 150px 0 50px;
        }
    </style>
</head>
<body>
<section class="notice-detail" id="notice-detail">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-md-10 col-md-offset-1">
                <h2 class="slogan">일반 전형</h2>
                <div class="spacer-small"></div>
                <div class="align-center">
                    <div class="col-md-offset-1 col-md-10">
                        <h3 class="slogan">2015년도 일반전형 주요 일정 안내</h3>
                        <div class="spacer-tiny"></div>
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
                                <td colspan="2">서류심사 결과발표</td>
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
                        <div class="spacer-mid"></div>
                        <h3 class="slogan">모집 요강 다운로드</h3>
                        <h4 class="slogan">아래는 참고용 2014년 후기 자료이며, 2015년 전기 요강은 2014. 9월초 업로드 예정</h4>
                        <div class="spacer-tiny"></div>
                        <table class="table table-stripped">
                            <tr>
                                <td align="left">2014학년도 9월입학 대학원 일반 전형 요강<a href=""><img src="${contextPath}/img/common/button_pdf.gif"/></a><a href=""><img src="${contextPath}/img/common/button_hwp.gif"/></a><a href=""><img src="${contextPath}/img/common/button_word.gif"/></a></td>
                            </tr>
                            <tr>
                                <td align="left">학업 및 연구계획서</td>
                            </tr>
                            <tr>
                                <td align="left">외국어시험면제승인서  ※ 연세대 석사를 마치고 3년이내에 박사과정 진학하는 자만 작성(건축공학과 제외)</td>
                            </tr>
                            <tr>
                                <td align="left">학력조회 동의서</td>
                            </tr>
                            <tr>
                                <td align="left">최종 출신대학 확인서</td>
                            </tr>
                            <tr>
                                <td align="left">치과대학, 원주의과대학(의학,간호학,치위생학) 전공교실 주임교수 확인서</td>
                            </tr>
                            <tr>
                                <td align="left">원주의과대학 전일제 대학원생 장학금 지급 신청서</td>
                            </tr>
                        </table>
                        <div>
                            <button class="btn btn-primary btn-lg btn-block" id="composePaper">원서 작성</button>
                        </div>
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
                location.href="${contextPath}/application/agreement";
            });
        })
    </script>
</content>
</body>
</html>
