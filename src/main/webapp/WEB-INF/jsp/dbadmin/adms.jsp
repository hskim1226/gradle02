<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>

<html>
<head>
    <style>

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            text-align: center;
            padding: 5px;
            font-size: 13px;
        }

        th {
            background-color: #AAAAAA;
        }

    </style>
</head>
<body>

<div id="LblockMain">
    <div id="LblockPageTitle">
        <h1>입학전형(ADMS) 관리</h1>
    </div>
    <div id="LblockMainBody" >

        <br><h1>입학전형 현황</h1>

        <table>
            <tr>
                <th>전형번호</th>
                <th>입학년도</th>
                <th>전형구분</th>
                <th>전형설명</th>
                <th>모집상태</th>
                <th>마감일자</th>
            </tr>

            <c:forEach items="${admsList}" var="item">
            <tr>
                <td>${item.admsNo}</td>
                <td>${item.entrYear}</td>
                <td>${item.admsType}</td>
                <td>${item.admsDesc}</td>
                <td>${item.admsSts}</td>
                <td>${item.closeDate}</td>
            </tr>
            </c:forEach>

        </table>

        <br><br><h1>신규 입학전형 입력</h1>

    </div>
</div>

</body>
</html>
