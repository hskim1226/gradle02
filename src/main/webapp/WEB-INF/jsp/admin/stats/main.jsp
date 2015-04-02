<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
<div class="main_con">
    <div class="spot"> <img src="${contextPath}/img/admin/img_banner.jpg" alt="에이펙스소프트가 온라인 입학지원에 혁신을 가져오겠습니다 !" /> </div>
    <!-- /spot -->

    <div class="main_sec1">
        <div id="notice_box" class="mboard">
            <h2><span>공지사항</span></h2>
            <div class="mboard_con">
                <ul>
                    <li><a href="#"><span class="tit">공지사항입니다.공지사항입니다.공지사항입니다.공지사항입니다.공지사항입니다.</span><span class="date">2015-03-16</span></a></li>
                    <li><a href="#"><span class="tit">공지사항</span><span class="date">2015-03-16</span></a></li>
                    <li><a href="#"><span class="tit">공지사항</span><span class="date">2015-03-16</span></a></li>
                    <li><a href="#"><span class="tit">공지사항</span><span class="date">2015-03-16</span></a></li>
                    <li><a href="#"><span class="tit">공지사항</span><span class="date">2015-03-16</span></a></li>
                </ul>
            </div>
            <a href="#" class="btn_more"><img src="${contextPath}/img/admin/btn_more.png" alt="더보기" /></a> </div>
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
                    <tr>
                        <th scope="row" class="text-left"><span class="tit1"><a href="#">047A1001</a></span></th>
                        <td>홍길동</td>
                        <td>모집단위변경</td>
                        <td>2014-04-14</td>
                        <td><img src="${contextPath}/img/admin/img_step1.png" alt="처리요청"></td>
                    </tr>
                    <tr>
                        <th scope="row" class="text-left"><span class="tit1"><a href="#">047A1001</a></span></th>
                        <td>홍길동</td>
                        <td>모집단위변경</td>
                        <td>2014-04-14</td>
                        <td><img src="${contextPath}/img/admin/img_step2.png" alt="처리완료"></td>
                    </tr>
                    <tr>
                        <th scope="row" class="text-left"><span class="tit1"><a href="#">047A1001</a></span></th>
                        <td>홍길동</td>
                        <td>모집단위변경</td>
                        <td>2014-04-14</td>
                        <td><img src="${contextPath}/img/admin/img_step3.png" alt="처리중"></td>
                    </tr>
                    <tr>
                        <th scope="row" class="text-left"><span class="tit1"><a href="#">047A1001</a></span></th>
                        <td>홍길동</td>
                        <td>모집단위변경</td>
                        <td>2014-04-14</td>
                        <td><img src="${contextPath}/img/admin/img_step4.png" alt="처리불가"></td>
                    </tr>
                    <tr>
                        <th scope="row" class="text-left"><span class="tit1"><a href="#">047A1001</a></span></th>
                        <td>홍길동</td>
                        <td>모집단위변경</td>
                        <td>2014-04-14</td>
                        <td><img src="${contextPath}/img/admin/img_step1.png" alt="처리요청"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <a href="#" class="btn_more"><img src="${contextPath}/img/admin/btn_more.png" alt="더보기" /></a> </div>
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
                    <th scope="row">2015-03-19</th>
                    <td><img src='${contextPath}/img/admin/bu_box1.png' width="50%" height="10" alt="">&nbsp;60명</td>
                </tr>
                <tr>
                    <th scope="row">2015-03-19</th>
                    <td><img src='${contextPath}/img/admin/bu_box1.png' width="70%" height="10" alt="">&nbsp;100명</td>
                </tr>
                <tr>
                    <th scope="row">2015-03-19</th>
                    <td><img src='${contextPath}/img/admin/bu_box1.png' width="20%" height="10" alt="">&nbsp;65명</td>
                </tr>
                <tr>
                    <th scope="row">2015-03-19</th>
                    <td><img src='${contextPath}/img/admin/bu_box1.png' width="80%" height="10" alt="">&nbsp;65명</td>
                </tr>
                <tr>
                    <th scope="row">2015-03-19</th>
                    <td><img src='${contextPath}/img/admin/bu_box1.png' width="50%" height="10" alt="">&nbsp;65명</td>
                </tr>
                <tr>
                    <th scope="row">2015-03-19</th>
                    <td><img src='${contextPath}/img/admin/bu_box1.png' width="66%" height="10" alt="">&nbsp;65명</td>
                </tr>
                </tbody>
            </table>
        </div>
        <a href="#" class="btn_more"><img src="${contextPath}/img/admin/btn_more.png" alt="더보기" /></a> </div>
    <!-- /counter -->

</div>
<!-- /main_con -->
</body>
</html>
