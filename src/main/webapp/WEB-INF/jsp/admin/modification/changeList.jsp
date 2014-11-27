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
                                <th><label for="applChgCode">변경요청구분</label></th>
                                <td>
                                    <select id="applChgCode">
                                        <option value="">-- 전체 --</option>
                                        <option value="00001">정보변경</option>
                                        <option value="00002">지원변경</option>
                                        <option value="00003">지원취소</option>
                                    </select>
                                </td>
                                <th><label for="chgStsCode">처리결과</label></th>
                                <td>
                                    <select id="chgStsCode">
                                        <option value="01">-- 전체 --</option>
                                        <option value="00001">접수</option>
                                        <option value="00002">반영중</option>
                                        <option value="00003">반영완료</option>
                                        <option value="00004">반영후결제필요</option>
                                        <option value="00005">반영취소</option>
                                    </select>
                                </td>
                            </tr>
                            <tr><th class="Cat">지원단위검색 : </th>
                                <th><label for="admsNo">지원전형</label></th>
                                <td>
                                    <select id="admsNo" name="admsNo" >
                                        <option value="" >-- 전체 --</option>
                                        <option value="15A">15 전기일반</option>
                                        <option value="15C">15 전기외국인</option>
                                    </select>
                                </td>
                                <th><label for="campCode">캠퍼스</label></th>
                                <td>
                                    <select id="campCode" name="campCode"  >
                                        <option value="">-- 전체 --</option>
                                        <option value="10">서울</option>
                                        <option value="11">원주</option>
                                        <option value="12">국제</option>
                                    </select>
                                </td>
                                <th><label for="collCode"  >대학</label></th>
                                <td>
                                    <select id="collCode" name="collCode" >
                                        <option value="" label="--전체--" />
                                    </select>
                                </td>
                            </tr>
                            <tr>
                            <tr><th class="Cat">지원자검색 : </th>
                                <th><label for="korName">성명</label></th>
                                <td><input type="text" class="Ltext" id="korName" name="korName" size="15" /></td>
                                <th ><label for="rsdnNo" >생년월일(주민번호 앞6자리)</label></th>
                                <td><input type="text" class="Ltext" id="rsdnNo" name="rsdnNo" size="20" /></td>
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
                <c:forEach var="chgList" items="${chgList}" varStatus="status">
                    <tr class="<c:if test="${status.index == 0}">Lfirst </c:if>chgList" chgList="${chgList.applNo}">
                        <td>${chgList.applId}</td>
                        <td>${chgList.campName}</td>
                        <td>${chgList.deptName}</td>
                        <td >${chgList.corsTypeCode}</td>
                        <td >${chgList.korName} <br> ${chgList.rgstNo}</td>
                        <td >${chgList.mobiNum} <br>${chgList.mailAddr} </td>
                        <td >${chgList.applStsCode}</td>

                    </tr>
                </c:forEach>
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
