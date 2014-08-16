<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>

<div id="LblockMain">
    <div id="LblockPageTitle">
        <div id="LblockPageLocation">
            <ul>
                <li class="Lfirst"><span><a href="#">HOME</a></span></li>
                <li><span><a href="#">지원자관리</a></span></li>
                <li class="Llast"><span>지원자검색</span></li>
            </ul>
        </div>

        <h1>지원자검색</h1>
    </div>

    <div id="LblockMainBody" >
        <div id="LblockSearch">
            <div>
                <div>
                    <form action="">
                        <table summary="지원현황 검색조건">
                            <caption>지원현황 검색조건</caption>
                            <tbody>
                            <tr><th class="Cat">수험번호검색 : </th>
                                <th><label for="sApplyNo">수험번호</label></th>
                                <td><input type="text" class="Ltext" id="sApplyNo" size="15" /></td>
                            </tr>
                            <tr class="HLine">
                            </tr>
                            <tr>
                            <tr><th class="Cat">지원자검색 : </th>
                                <th><label for="sApplyNm">성명</label></th>
                                <td><input type="text" class="Ltext" id="sApplyNm" size="15" /></td>
                                <th colspan=2><label for="sRsdnNo" >생년월일(YYMMDD)</label></th>
                                <td><input type="text" class="Ltext" id="sRsdnNo" size="6" /></td>
                            </tr>
                            <tr>
                            <tr><th class="Cat">지원단위검색 : </th>
                                <th><label for="sCampus">&nbsp; &nbsp; 모집캠퍼스</label></th>
                                <td>
                                    <select id="sCampus">
                                        <option value="01">-- 전체 --</option>
                                        <option value="02">서울캠퍼스</option>
                                        <option value="02">국제캠퍼스</option>
                                        <option value="02">원주캠퍼스</option>
                                    </select>
                                </td>
                                <th><label for="sGruop">모집단위</label></th>
                                <td>
                                    <select id="sGruop">
                                        <option value="01">-- 전체 --</option>
                                        <option value="02">문과대학</option>
                                        <option value="02">상과대학</option>
                                        <option value="02">이과대학</option>
                                        <option value="02">공과대학</option>
                                    </select>
                                </td>
                                <th><label for="sType">모집전형</label></th>
                                <td>
                                    <select id="sType">
                                        <option value="01">-- 전체 --</option>
                                        <option value="02">일반</option>
                                        <option value="02">외국인</option>
                                    </select>
                                </td>

                            </tr>
                            </tbody>
                        </table>
                        <input type="image" class="Limage" src="${contextPath}/img/admin/btn_search.gif" /></a>
                    </form>
                </div>
            </div>
        </div>

        <div id="LblockListTable01" class="LblockListTable">
            <table summary="전형별 지원현황">
                <caption>전형별 지원현황</caption>
                <thead>
                <tr>
                    <th class="Lfirst">수험번호</th>
                    <th>캠퍼스</th>
                    <th>지원학과</th>
                    <th>지원전형</th>
                    <th>학생정보</th>
                    <th>연락처</th>
                    <th>접수일시</th>
                    <th>결제내역</th>
                    <th>지원서내역</th>
                    <th class="Llast">수정요청</th>
                </tr>
                </thead>
                <tbody>
                <tr class="Lfirst">
                    <td>15-A010001</td>
                    <td>서울캠퍼스</td>
                    <td>토목환경공학과</td>
                    <td>일반<br>석사학위과정</td>
                    <td>홍길동<br>850101</td>
                    <td>010-5555-****<br>hongildong@<br>yonsei.kr</td>
                    <td>2014-09-29<br>15:00:11</td>
                    <td>계좌이체<br>85,00000</td>
                    <td>수험표<img src="${contextPath}/img/admin/docView.gif">
                        <br>입학지원서<img src="${contextPath}/img/admin/docView.gif">
                        <br>자기소개서<img src="${contextPath}/img/admin/docView.gif">
                        <br>연구계획서<img src="${contextPath}/img/admin/docView.gif">
                        <br>성적증명서<img src="${contextPath}/img/admin/docView.gif">
                        <br>기타<img src="${contextPath}/img/admin/docView.gif"></td>
                    <td class="Llast"><a href="#"><input type="button" value="수정요청" onclick="doSomething(); return false;" /></a></td>
                </tr>
                <tr>
                    <td class="Lfirst">15-A010002</td>
                    <td>서울캠퍼스</td>
                    <td>토목공학과</td>
                    <td>일반<br>박사학위과정</td>
                    <td>김일남<br>830101</td>
                    <td>010-5555-****<br>hongil@<br>yonsei.kr</td>
                    <td>2014-09-29<br>15:00:11</td>
                    <td>계좌이체<br>85,00000</td>
                    <td>수험표<img src="${contextPath}/img/admin/docView.gif">
                        <br>입학지원서<img src="${contextPath}/img/admin/docView.gif">
                        <br>자기소개서<img src="${contextPath}/img/admin/docView.gif">
                        <br>연구계획서<img src="${contextPath}/img/admin/docView.gif">
                        <br>성적증명서<img src="${contextPath}/img/admin/docView.gif">
                        <br>기타<img src="${contextPath}/img/admin/docView.gif"></td>
                    <td class="Llast"><a href="#"><input type="button" value="수정요청" onclick="doSomething(); return false;" /></a></td>
                </tr>
                <tr>
                    <td class="Lfirst">15-A020001</td>
                    <td>서울캠퍼스</td>
                    <td>건축공학과</td>
                    <td>일반<br>석사학위과정</td>
                    <td>이순신<br>840101</td>
                    <td>010-5555-****<br>ngildong@<br>yonsei.kr</td>
                    <td>2014-09-29<br>15:00:11</td>
                    <td>계좌이체<br>85,00000</td>
                    <td>수험표<img src="${contextPath}/img/admin/docView.gif">
                        <br>입학지원서<img src="${contextPath}/img/admin/docView.gif">
                        <br>자기소개서<img src="${contextPath}/img/admin/docView.gif">
                        <br>연구계획서<img src="${contextPath}/img/admin/docView.gif">
                        <br>성적증명서<img src="${contextPath}/img/admin/docView.gif">
                        <br>기타<img src="${contextPath}/img/admin/docView.gif"></td>
                    <td class="Llast"><a href="#"><input type="button" value="수정요청" onclick="doSomething(); return false;" /></a></td>
                </tr>
                <tr class="Llast">
                    <td class="Lfirst">15-A010003</td>
                    <td>서울캠퍼스</td>
                    <td>토목공학과</td>
                    <td>일반<br>석사학위과정</td>
                    <td>권율<br>900101</td>
                    <td>010-5555-****<br>ongildong@<br>yonsei.kr</td>
                    <td>2014-09-29<br>15:00:11</td>
                    <td>계좌이체<br>85,00000</td>
                    <td>수험표<img src="${contextPath}/img/admin/docView.gif">
                        <br>입학지원서<img src="${contextPath}/img/admin/docView.gif">
                        <br>자기소개서<img src="${contextPath}/img/admin/docView.gif">
                        <br>연구계획서<img src="${contextPath}/img/admin/docView.gif">
                        <br>성적증명서<img src="${contextPath}/img/admin/docView.gif">
                        <br>기타<img src="${contextPath}/img/admin/docView.gif"></td>
                    <td class="Llast"><a href="#"><input type="button" value="수정요청" onclick="doSomething(); return false;" /></a></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<content tag="local-script">
    <script>

    </script>
</content>
</body>
</html>
