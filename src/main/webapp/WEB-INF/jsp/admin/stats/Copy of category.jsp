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
                <li><span><a href="#">통계</a></span></li>
                <li class="Llast"><span>전형별 지원현황</span></li>
            </ul>
        </div>

        <h1>전형별지원현황</h1>
    </div>

    <div id="LblockMainBody" >
        <div id="LblockSearch">
            <div>
                <div>
                    <form action="">
                        <table summary="지원현황 검색조건">
                            <caption>지원현황 검색조건</caption>
                            <tbody>
                            <tr>
                                <th><label for="sCampus">지원캠퍼스</label></th>
                                <td>
                                    <select id="sCampus">
                                        <option value="01">-- 전체 --</option>
                                        <option value="02">서울캠퍼스</option>
                                        <option value="02">국제캠퍼스</option>
                                        <option value="02">원주캠퍼스</option>
                                    </select>
                                </td>
                                <th><label for="sGruop">지원단위</label></th>
                                <td>
                                    <select id="sGruop">
                                        <option value="01">-- 전체 --</option>
                                        <option value="02">문과대학</option>
                                        <option value="02">상과대학</option>
                                        <option value="02">이과대학</option>
                                        <option value="02">공과대학</option>
                                    </select>
                                </td>
                                <th><label for="sType">지원전형</label></th>
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
                    <th class="Lfirst">캠퍼스</th>
                    <th>지원단위</th>
                    <th>코드</th>
                    <th>전형</th>
                    <th class="LHdata">석사학위과정</th>
                    <th class="LHdata">박사학위과정</th>
                    <th class="LHdata">석박사통합과정</th>
                    <th class="LHSum">합계</th>
                </tr>
                </thead>
                <tbody>
                <tr class="Lfirst">
                    <td class="Lfirst" rowspan=4>서울캠퍼스</td>
                    <td>화학공학과</td>
                    <td>100100</td>
                    <td>일반</td>
                    <td class="LRdata">10</td>
                    <td class="LRdata">11</td>
                    <td class="LRdata">12</td>
                    <td class="LCSum">33</td>
                </tr>
                <tr>
                    <td>전기공학과</td>
                    <td>100102</td>
                    <td>일반</td>
                    <td class="LRdata">20</td>
                    <td class="LRdata">21</td>
                    <td class="LRdata">22</td>
                    <td class="LCSum">63</td>
                </tr>
                <tr>
                    <td>건축공학과</td>
                    <td>100103</td>
                    <td>일반</td>
                    <td class="LRdata">20</td>
                    <td class="LRdata">21</td>
                    <td class="LRdata">22</td>
                    <td class="LCSum">63</td>
                </tr>
                <tr class="Llast">
                    <td class="LRSum" colspan = 3>소계</td>
                    <td class="LRSum">50</td>
                    <td class="LRSum">53</td>
                    <td class="LRSum">56</td>
                    <td class="LTSum">159</td>
                </tr>
                <tr class="Lfirst">
                    <td class="Lfirst" rowspan=4>원주캠퍼스</td>
                    <td>화학공학과</td>
                    <td>100100</td>
                    <td>일반</td>
                    <td class="LRdata">10</td>
                    <td class="LRdata">11</td>
                    <td class="LRdata">12</td>
                    <td class="LCSum">33</td>
                </tr>
                <tr>
                    <td>전기공학과</td>
                    <td>100102</td>
                    <td>일반</td>
                    <td class="LRdata">20</td>
                    <td class="LRdata">21</td>
                    <td class="LRdata">22</td>
                    <td class="LCSum">63</td>
                </tr>
                <tr>
                    <td>건축공학과</td>
                    <td>100103</td>
                    <td>일반</td>
                    <td class="LRdata">20</td>
                    <td class="LRdata">21</td>
                    <td class="LRdata">22</td>
                    <td class="LCSum">63</td>
                </tr>
                <tr class="Llast">
                    <td class="LRSum" colspan = 3>소계</td>
                    <td class="LRSum">50</td>
                    <td class="LRSum">53</td>
                    <td class="LRSum">56</td>
                    <td class="LTSum">159</td>
                </tr>
                <tr class="LTdata">
                    <td colspan = 4>총계</td>
                    <td >50</td>
                    <td >53</td>
                    <td >56</td>
                    <td >159</td>
                </tr>
                </tbody>
            </table>
        </div>

    </div>

    <div id="LblockButton">
        <a href="#"><input type="button" value="엑셀파일 다운로드" onclick="doSomething(); return false;" /></a>
    </div>

</div>

<content tag="local-script">
    <script>

    </script>
</content>
</body>
</html>
