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
                <li><span><a href="#">지원변경/취소관리</a></span></li>
                <li class="Llast"><span>변경처리현황</span></li>
            </ul>
        </div>

        <h1>변경처리현황</h1>
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
                            <tr><th class="Cat">처리현황검색 : </th>
                                <th><label for="sChgType">변경요청구분</label></th>
                                <td>
                                    <select id="sChgType">
                                        <option value="01">-- 전체 --</option>
                                        <option value="02">정보변경</option>
                                        <option value="02">지원단위변경</option>
                                        <option value="02">지원취소</option>
                                    </select>
                                </td>
                                <th><label for="sChgSts">처리결과</label></th>
                                <td>
                                    <select id="sChgSts">
                                        <option value="01">-- 전체 --</option>
                                        <option value="02">접수</option>
                                        <option value="02">처리중</option>
                                        <option value="02">처리완료</option>
                                        <option value="02">반영취소</option>
                                    </select>
                                </td>
                            </tr>
                            <tr><th class="Cat">변경요청번호검색 : </th>
                                <th><label for="sApplyNo">변경요청번호</label></th>
                                <td><input type="text" class="Ltext" id="sApplyNo" size="15" /></td>
                            </tr>
                            <tr>
                            <tr><th class="Cat">지원자검색 : </th>
                                <th><label for="sApplyNm">성명</label></th>
                                <td><input type="text" class="Ltext" id="sApplyNm" size="15" /></td>
                                <th colspan=2><label for="sRsdnNo" >생년월일(YYMMDD)</label></th>
                                <td><input type="text" class="Ltext" id="sRsdnNo" size="6" /></td>
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
                    <th class="Lfirst">요청번호</th>
                    <th>변경대상자</th>
                    <th>변경구분</th>
                    <th>변경전</th>
                    <th>변경후</th>
                    <th>요청일자</th>
                    <th>반영일자</th>
                    <th>처리결과</th>
                    <th class="Llast" >비고</th>
                </tr>
                </thead>
                <tbody>
                <tr class="Lfirst">
                    <td class="Lfirst">YS-14-0001</td>
                    <td >홍길동<br>850101</td>
                    <td>정보변경</td>
                    <td>한글이름 흥길동</td>
                    <td>한글이름 홍길동</td>
                    <td>2014-09-29<br>15:00:11</td>
                    <td>2014-09-29<br>15:00:11</td>
                    <td>처리완료</td>
                    <td class="Llast"></td>
                </tr>
                <tr>
                    <td class="Lfirst">YS-14-0002</td>
                    <td >김일남<br>830101</td>
                    <td>정보변경</td>
                    <td>상세주소 1121호</td>
                    <td>상세주소 1111호</td>
                    <td>2014-09-29<br>15:00:11</td>
                    <td>2014-09-29<br>15:00:11</td>
                    <td>처리완료</td>
                    <td class="Llast"></td>
                </tr>
                <tr>
                    <td class="Lfirst">YS-14-0003</td>
                    <td>권율<br>900101</td>
                    <td>지원단위변경</td>
                    <td>일반<br>의학과</td>
                    <td>외국인<br>의과학과</td>
                    <td>2014-09-29<br>15:00:11</td>
                    <td>2014-09-29<br>15:00:11</td>
                    <td>접수</td>
                    <td class="Llast"></td>
                </tr>
                <tr class="Llast">
                    <td class="Lfirst">YS-14-0004</td>
                    <td>이순신<br>850101</td>
                    <td>지원취소</td>
                    <td>결제완료</td>
                    <td>지원취소</td>
                    <td>2014-09-29<br>15:00:11</td>
                    <td>2014-09-29<br>15:00:11</td>
                    <td>처리완료</td>
                    <td class="Llast">환급완료</td>
                </tr>
                </tbody>
            </table>
        </div>

    </div>

    <div id="LblockButton">
        <a href="#"><input type="button" value="엑셀파일 다운로드" onclick="doSomething(); return false;" /></a>
        <a href="#"><input type="button" value="신규요청등록" onclick="doSomething(); return false;" /></a>
    </div>
</div>

<content tag="local-script">
    <script>

    </script>
</content>
</body>
</html>
