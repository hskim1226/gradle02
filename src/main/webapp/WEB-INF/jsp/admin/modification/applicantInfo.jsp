<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<h3 class="tit1">지원자 상세정보</h3>
<table class="tbl_typeA mb15" summary="지원자 상세정보">
    <caption>
        지원서 상세정보
    </caption>
    <colgroup>
        <col width="20%" />
        <col width="33%" />
        <col width="14%" />
        <col width="33%" />
    </colgroup>
    <tbody>
        <c:if test="${applInfo.applNo == null}" >
            <tr>
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
            <th>생년월일</th>
             <td>${applInfo.rgstBornDate}</td>
            <th>Sur Name</th>
            <td>${applInfo.engSur}</td>
        </tr>
        <tr>
            <th>성별</th>
            <c:choose>
                <c:when test="${applInfo.gend == 'f'}">
                    <td>여자</td>
                </c:when>
                <c:otherwise>
                    <td>남자</td>
                </c:otherwise>
            </c:choose>
            <th>전화번호</th>
            <td>${applInfo.telNum}</td>

        </tr>
        <tr>
            <th>주소</th>
            <td>${applInfo.addr} ${applInfo.detlAddr}</td>
            <th>비상연락대상</th>
            <td>${applGene.emerContName}</td>
        </tr>
        <tr>
            <th>E-mail</th>
            <td>${applInfo.mailAddr}</td>
            <th>비상연락처</th>
            <td>${applGene.emerContTel}</td>
        </tr>
        </c:if>
    </tbody>
</table>

