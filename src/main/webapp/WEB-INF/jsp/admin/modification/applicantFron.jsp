<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<h3 class="tit1">외국인 상세정보</h3>
<table class="tbl_typeA mb15" summary="외국인 상세정보">
    <caption>
        외국인 상세정보
    </caption>
    <colgroup>
        <col width="20%" />
        <col width="33%" />
        <col width="14%" />
        <col width="33%" />
    </colgroup>
    <tbody>
    <c:if test="${appl.citzCntrCode == '118'}" >
        <tr>
            <td colspan="7">해당 정보 없음</td>
        </tr>
    </c:if>
    <c:if test="${applForn.applNo != null}" >
        <tr>
            <th>국적</th>
            <td>${cntr.korCntrName}</td>
            <th>외국인구분</th>
            <c:choose>
                <c:when test="${applInfo.fornTypeCode=='00001'}">
                    <td>외국인</td>
                </c:when>
                <c:when test="${applInfo.fornTypeCode=='00002'}">
                    <td>해외 16년 거주</td>
                </c:when>
                <c:otherwise>
                    <td>${applInfo.fornTypeCode}</td>
                </c:otherwise>

            </c:choose>
        </tr>
        <tr>
            <th>본국주소</th>
            <td>${applForn.homeAddr}</td>
            <th>본국연락처</th>
            <td>${applForn.homeTel}</td>
        </tr>
        <tr>
            <th>본국비상연락자</th>
            <td>${applForn.homeEmrgName}</td>
            <th>본국비상연락처</th>
            <td>${applForn.homeEmrgTel}</td>
        </tr>
    </c:if>
    </tbody>
</table>

