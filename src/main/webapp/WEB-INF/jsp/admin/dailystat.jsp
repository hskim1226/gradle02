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
                <li class="Llast"><span>일자별지원현황</span></li>
            </ul>
        </div>

        <h1>일자별지원현황</h1>
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
                                <th><label for="sCampus">모집캠퍼스</label></th>
                                <td>
                                    <select id="sCampus">
                                        <option value="01">-- 전체 --</option>
                                        <option value="02">서울캠퍼스</option>
                                        <option value="02">국제캠퍼스</option>
                                        <option value="02">원주캠퍼스</option>
                                    </select>
                                </td>
                                <th><label for="sDetailType">모집전형</label></th>
                                <td>
                                    <select id="sDetailType">
                                        <option value="01">-- 전체 --</option>
                                        <option value="02">석사학위과정</option>
                                        <option value="02">박사학위과정</option>
                                        <option value="02">통합학위과정</option>

                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th><label>지원일자</label></th>
                                <td>
                                    <input type="radio" class="Lradio" id="dApplyDate_1" name="dApplyDate" /><label for="dApplyDate_1">금주</label>
                                    <input type="radio" class="Lradio" id="dApplyDate_2" name="dApplyDate" checked="checked" /><label for="dApplyDate_2">전주</label>
                                    <input type="radio" class="Lradio" id="dApplyDate_3" name="dApplyDate" /><label for="dApplyDate_3">오늘까지</label>
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
            <table summary="일자별 지원현황">
                <caption>일자별 지원현황</caption>
                <thead>
                <tr>
                    <th class="Lfirst">캠퍼스</th>
                    <th>전형</th>
                    <th class="LHdata">09/28</th>
                    <th class="LHdata">09/29</th>
                    <th class="LHdata">09/30</th>
                    <th class="LHdata">10/01</th>
                    <th class="LHdata">10/01</th>
                    <th class="LHdata">10/03</th>
                    <th class="LHdata">10/04</th>
                    <th class="LHSum">합계</th>
                </tr>
                </thead>
                <tbody>
                <tr class="Lfirst">
                    <td class="Lfirst" rowspan=4>서울캠퍼스</td>
                    <td>석사학위과정</td>
                    <td class="LRdata">10</td>
                    <td class="LRdata">11</td>
                    <td class="LRdata">12</td>
                    <td class="LRdata">10</td>
                    <td class="LRdata">11</td>
                    <td class="LRdata">12</td>
                    <td class="LRdata">12</td>
                    <td class="LCSum">33</td>
                </tr>
                <tr>
                    <td>박사학위과정</td>
                    <td class="LRdata">10</td>
                    <td class="LRdata">11</td>
                    <td class="LRdata">12</td>
                    <td class="LRdata">10</td>
                    <td class="LRdata">11</td>
                    <td class="LRdata">12</td>
                    <td class="LRdata">12</td>
                    <td class="LCSum">33</td>
                </tr>
                <tr>
                    <td>통합학위과정</td>
                    <td class="LRdata">10</td>
                    <td class="LRdata">11</td>
                    <td class="LRdata">12</td>
                    <td class="LRdata">10</td>
                    <td class="LRdata">11</td>
                    <td class="LRdata">12</td>
                    <td class="LRdata">12</td>
                    <td class="LCSum">33</td>
                </tr>
                <tr class="Llast">
                    <td class="LRSum">소계</td>
                    <td class="LRSum">50</td>
                    <td class="LRSum">53</td>
                    <td class="LRSum">56</td>
                    <td class="LRSum">50</td>
                    <td class="LRSum">53</td>
                    <td class="LRSum">56</td>
                    <td class="LRSum">56</td>
                    <td class="LTSum">159</td>
                </tr>
                <tr class="Lfirst">
                    <td class="Lfirst" rowspan=4>원주캠퍼스</td>
                    <td>석사학위과정</td>
                    <td class="LRdata">10</td>
                    <td class="LRdata">11</td>
                    <td class="LRdata">12</td>
                    <td class="LRdata">10</td>
                    <td class="LRdata">11</td>
                    <td class="LRdata">12</td>
                    <td class="LRdata">12</td>
                    <td class="LCSum">33</td>
                </tr>
                <tr>
                    <td>박사학위과정</td>
                    <td class="LRdata">10</td>
                    <td class="LRdata">11</td>
                    <td class="LRdata">12</td>
                    <td class="LRdata">10</td>
                    <td class="LRdata">11</td>
                    <td class="LRdata">12</td>
                    <td class="LRdata">12</td>
                    <td class="LCSum">33</td>
                </tr>
                <tr>
                    <td>통합학위과정</td>
                    <td class="LRdata">10</td>
                    <td class="LRdata">11</td>
                    <td class="LRdata">12</td>
                    <td class="LRdata">10</td>
                    <td class="LRdata">11</td>
                    <td class="LRdata">12</td>
                    <td class="LRdata">12</td>
                    <td class="LCSum">33</td>
                </tr>
                <tr class="Llast">
                    <td class="LRSum">소계</td>
                    <td class="LRSum">50</td>
                    <td class="LRSum">53</td>
                    <td class="LRSum">56</td>
                    <td class="LRSum">50</td>
                    <td class="LRSum">53</td>
                    <td class="LRSum">56</td>
                    <td class="LRSum">56</td>
                    <td class="LTSum">159</td>
                </tr>
                <tr class="LTdata">
                    <td colspan = 2>총계</td>
                    <td >50</td>
                    <td >53</td>
                    <td >50</td>
                    <td >53</td>
                    <td >56</td>
                    <td >50</td>
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
