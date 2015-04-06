<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
<div class="main_con">
    <div class="spot"> <img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/img_banner.jpg" alt="에이펙스소프트가 온라인 입학지원에 혁신을 가져오겠습니다 !" /> </div>
    <!-- /spot -->

    <div class="main_sec1">
        <div id="notice_box" class="mboard">
            <h2><span>지원현황</span></h2>
            <div class="mboard_con">
                <table class="tbl_list1" summary="지원현황">
                    <caption>
                        원서수정/취소관리
                    </caption>
                    <colgroup>
                        <col width="25%" />
                        <col width="15%" />
                        <col width="15%" />
                        <col width="15%" />
                        <col width="15%" />
                        <col width="15%" />

                    </colgroup>
                    <tbody>
                    <tr>
                        <th scope="row" class="text-left"><span class="tit1">일반-일반</a></span></th>
                        <td>석사 0명</td>
                        <td>박사 0명</td>
                        <td>통합 0명</td>
                        <td>연구 0명</td>
                        <td>총계 0명</td>

                    </tr>
                    <tr>
                        <th scope="row" class="text-left"><span class="tit1">일반-학연산</a></span></th>
                        <td>석사 0명</td>
                        <td>박사 0명</td>
                        <td>통합 0명</td>
                        <td>연구 0명</td>
                        <td>총계 0명</td>

                    </tr>
                    <tr>
                        <th scope="row" class="text-left"><span class="tit1">일반-위탁</a></span></th>
                        <td>석사 0명</td>
                        <td>박사 0명</td>
                        <td>통합 0명</td>
                        <td>연구 0명</td>
                        <td>총계 0명</td>

                    </tr>
                    <tr>
                        <th scope="row" class="text-left"><span class="tit1">일반-새터민</a></span></th>
                        <td>석사 0명</td>
                        <td>박사 0명</td>
                        <td>통합 0명</td>
                        <td>연구 0명</td>
                        <td>총계 0명</td>

                    </tr>
                    <tr>
                        <th scope="row" class="text-left"><span class="tit1">외국인</a></span></th>
                        <td>석사 0명</td>
                        <td>박사 0명</td>
                        <td>통합 0명</td>
                        <td>연구 0명</td>
                        <td>총계 0명</td>

                    </tr>
                    <tr>
                        <th scope="row" class="text-left"><span class="tit1">조기</a></span></th>
                        <td>석사 0명</td>
                        <td>박사 0명</td>
                        <td>통합 0명</td>
                        <td>연구 0명</td>
                        <td>총계 0명</td>

                    </tr>
                    </tbody>
                </table>
            </div>
            <a href="#" class="btn_more"><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/btn_more.png" alt="더보기" /></a> </div>
        <!-- /notice_board -->

        <div id="admin_box" class="mboard">
            <h2><span>원서수정/취소관리</span></h2>
            <div class="state">
                <h3>처리현황</h3>
                <ul>
                    <li><strong>요청: <span class="color-org">000</span></strong>건</li>
                    <li><strong>완료: <span class="color-org">000</span></strong>건</li>
                    <li><strong>처리중: <span class="color-org">000</span></strong>건</li>
                    <li><strong>불가: <span class="color-org">000</span></strong>건</li>
                </ul>
            </div>
            <div class="mboard_con">
                <table class="tbl_list1" summary="원서수정/취소관리">
                    <caption>
                        원서수정/취소관리
                    </caption>
                    <colgroup>
                        <col width="20%" />
                        <col width="15%" />
                        <col />
                        <col width="20%" />
                        <col width="18%" />
                    </colgroup>
                    <tbody>

                    </tbody>
                </table>
            </div>
            <a href="#" class="btn_more"><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/btn_more.png" alt="더보기" /></a> </div>
        <!-- /admin_board -->

    </div>
    <!-- /main_sec1 -->

    <div id="counter_box" class="mboard mb20">
        <h2><span>일자별 접수 건수 누계</span></h2>
        <div class="mboard_con">
            <table class="tbl_count1" summary="일자별 접수 건수 누계">
                <caption>
                    일자별 접수 건수 누계
                </caption>
                <colgroup>
                    <col width="78" />
                    <col width="" />
                </colgroup>
                <tbody>
                <tr>
                    <th scope="row">2015-04-07</th>
                    <td><img src='<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/bu_box1.png' width="1%" height="10" alt="">&nbsp;00명</td>
                </tr>
                <tr>
                    <th scope="row">2015-04-06</th>
                    <td><img src='<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/bu_box1.png' width="1%" height="10" alt="">&nbsp;00명</td>
                </tr>
                <tr>
                    <th scope="row">2015-04-05</th>
                    <td><img src='<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/bu_box1.png' width="1%" height="10" alt="">&nbsp;00명</td>
                </tr>
                <tr>
                    <th scope="row">2015-04-04</th>
                    <td><img src='<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/bu_box1.png' width="1%" height="10" alt="">&nbsp;00명</td>
                </tr>
                <tr>
                    <th scope="row">2015-04-03</th>
                    <td><img src='<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/bu_box1.png' width="1%" height="10" alt="">&nbsp;00명</td>
                </tr>
                <tr>
                    <th scope="row">2015-04-02</th>
                    <td><img src='<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/bu_box1.png' width="1%" height="10" alt="">&nbsp;00명</td>
                </tr>
                </tbody>
            </table>
        </div>
        <a href="#" class="btn_more"><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/btn_more.png" alt="더보기" /></a> </div>
    <!-- /counter -->

</div>
<!-- /main_con -->
</body>
</html>
