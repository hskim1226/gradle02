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
                        지원현황
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
                    <c:forEach var="corsCntList" items="${corsCntList}" varStatus="status">
                    <tr>
                        <c:choose>
                            <c:when test="${corsCntList.admsNo == app['adms.general']}">
                                <c:choose>
                                <c:when test="${corsCntList.applAttrCode == '00001'}">
                                    <th scope="row" class="text-left"><span class="tit1">일반-일반</span></th>
                                </c:when>
                                <c:when test="${corsCntList.applAttrCode == '00002'}">
                                    <th scope="row" class="text-left"><span class="tit1">일반-학연산</span></th>
                                </c:when>
                                <c:when test="${corsCntList.applAttrCode == '00003'}">
                                    <th scope="row" class="text-left"><span class="tit1">일반-위탁</span></th>
                                </c:when>
                                <c:when test="${corsCntList.applAttrCode == '00004'}">
                                    <th scope="row" class="text-left"><span class="tit1">일반-새터민</span></th>
                                </c:when>
                                </c:choose>
                            </c:when>
                            <c:when test="${corsCntList.admsNo == app['adms.foreign']}">
                                <th scope="row" class="text-left"><span class="tit1">외국인전형</span></th>
                            </c:when>
                            <c:when test="${corsCntList.admsNo == app['adms.early']}">
                                <th scope="row" class="text-left"><span class="tit1">조기전형</span></th>
                            </c:when>
                        </c:choose>
                        <td>석사 ${corsCntList.cnt1}명</td>
                        <td>박사 ${corsCntList.cnt2}명</td>
                        <td>통합 ${corsCntList.cnt3}명</td>
                        <td>연구 ${corsCntList.cnt4}명</td>
                        <td>총계 ${corsCntList.totalCnt}명</td>
                    </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <%-- 아래 링크에 해당하는 컨트롤러 없어서 주석 처리 --%>
            <%--<a href="${contextPath}/admin/stats/category" class="btn_more" id="" ><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/btn_more.png" alt="더보기" /></a>--%>
        </div>

        <!-- /notice_board -->

        <div id="admin_box" class="mboard">
            <h2><span>원서수정/취소관리</span></h2>
            <div class="state">
                <h3>처리현황</h3>
                <ul>
                    <c:forEach var="chgCntList" items="${chgCntList}" varStatus="status">

                            <li><strong>${chgCntList.codeVal}: <span class="color-org">${chgCntList.cnt}</span></strong>건</li>

                    </c:forEach>
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
                    <c:forEach var="chgList" items="${chgList}" varStatus="status">
                        <tr>
                            <th scope="row" class="text-left"><span class="tit1"><a href="#">${chgList.applId}</a></span></th>
                            <td>${chgList.korName}</td>
                            <td>${chgList.chgStsCodeName}</td>
                            <td>${chgList.reqDay}</td>
                            <td><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/img_step1.png" alt="처리요청"></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="${contextPath}/admin/modification/changeList"  class="btn_more"><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/btn_more.png" alt="더보기" /></a> </div>
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
                <c:forEach var="weekCntList" items="${weekCntList}" varStatus="status">
                <tr>
                    <th scope="row">${weekCntList.applDate}</th>
                    <td><img src='<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/bu_box1.png' width="${(weekCntList.totalCnt)/(1000)}%" height="10" alt="">&nbsp;${weekCntList.totalCnt}명</td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <%-- 아래 링크에 해당하는 컨트롤러 없어서 주석 처리 --%>
        <%--<a href="${contextPath}/admin/stats/daily" class="btn_more"><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/btn_more.png" alt="더보기" /></a> </div>--%>
    <!-- /counter -->

</div>
<!-- /main_con -->
    </content>
</body>


</html>
