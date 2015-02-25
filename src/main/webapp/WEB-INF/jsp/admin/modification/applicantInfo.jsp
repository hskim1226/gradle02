<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="LblockPageSubtitle02" class="LblockPageSubtitle">
    <h2>지원서 상세정보</h2>
</div>

<div id="LblockDetail02" class="LblockDetail">
    <table summary="지원서 상세정보">
        <caption>지원자개인정보</caption>

        <tbody>
        <c:if test="${applInfo.applNo == null}" >
            <tr >
                <td colspan="7">해당 정보 없음</td>
            </tr>
        </c:if>
        <c:if test="${applInfo.applNo != null}" >
        <tr>
            <th>성명</th>
            <td>${applInfo.korName}</td>
            <th>Name</th>
            <td>${applInfo.engName}</td>
        </tr>
        <tr>
            <th>생년월일/주민번호</th>
            <td>${applInfo.rgstNo}</td>
            <th>Sur Name</th>
            <td>${applInfo.engSur}</td>
        </tr>
        <tr>
            <th>전화번호</th>
            <td>${applInfo.telNum}</td>
            <th>핸드폰 번호</th>
            <td>${applInfo.mobiNum}</td>
        </tr>
        <tr>
            <th>주소</th>
            <td>${applInfo.detlAddr}</td>
            <th>E-mail</th>
            <td>${applInfo.mailAddr}</td>
        </tr>
        <tr>
            <th>비상연락대상</th>
            <td>${applInfo.emerContName}</td>
            <th>비상연락처</th>
            <td>${applInfo.emerContTel}</td>
        </tr>
        <tr></tr>
        </c:if>
        </tbody>
    </table>
</div>