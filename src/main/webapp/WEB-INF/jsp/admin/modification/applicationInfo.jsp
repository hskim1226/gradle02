<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3 class="tit1">지원정보</h3>
<table class="tbl_typeA mb15" summary="지원 기본정보">
    <caption>
        지원 기본정보
    </caption>
    <colgroup>
        <col width="12%" />
        <col width="20%" />
        <col width="12%" />
        <col />
        <col width="12%" />
        <col width="25%" />
    </colgroup>
    <tbody>
    <c:if test="${applInfo.applNo == null}" >
        <tr>
            <td colspan="7">해당 정보 없음</td>
        </tr>
    </c:if>
    <c:if test="${applInfo.applNo != null}" >
        <tr>
            <th>수험번호</th>
            <td>${applInfo.applId}</td>
            <th>전형구분</th>
            <td>${applInfo.admsTypeName}</td>
            <th>학연산 기관</th>
            <td><c:if test="${empty applInfo.ariInstName}" >해당없음</c:if><c:if test="${not empty applInfo.ariInstName}" >${applInfo.ariInstName}</c:if></td>
        </tr>
        <tr>
            <th>캠퍼스</th>
            <td>${applInfo.campName}</td>
            <th>지원구분</th>
            <td>${applInfo.applAttrName}</td>
            <th>지원일자</th>
            <td><fmt:formatDate value="${applInfo.applDate}" pattern="yyyy년 MM월 dd일 HH시 mm분" /></td>
        </tr>
        <tr>
            <th>대학</th>
            <td>${applInfo.collName}</td>
            <th>지원과정</th>
            <td>${applInfo.corsTypeName}</td>
            <th>전형료</th>
            <td><fmt:formatNumber type="currency"   maxFractionDigits="3" value="${applInfo.admsFee}" /></td>
        </tr>
        <tr>
            <th>학과</th>
            <td>${applInfo.deptName}</td>
            <th>세부전공</th>
            <td>${applInfo.detlMajName}</td>
            <th>결제방법</th>
            <td>${applInfo.payTypeName}</td>
        </tr>
    </c:if>
    </tbody>
</table>



