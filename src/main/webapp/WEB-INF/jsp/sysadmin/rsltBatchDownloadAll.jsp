<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title>Final PDF 업로드 안된 원서 첨부 파일 분석 결과</title>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-sm-offset-1 col-sm-10">
                <div class="form-group inner-container-white">
                    <div class="col-sm-offset-1 col-sm-10 text-gray">
                        <i class="fa fa-list fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b>Final PDF 업로드 안된 원서 첨부 파일 분석 결과</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="col-sm-offset-1 col-sm-10 align-center">
                        <table class="table table-stripped text-gray">
                            <thead>
                            <tr>
                                <th>번호</th>
                                <th>APPL_ID</th>
                                <th>USER_ID</th>
                                <th>업로드 파일 수</th>
                                <th>다운로드 파일 수</th>
                                <th>로딩 에러 파일 수</th>
                                <th>로딩 에러 파일이름</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${downList}" var="item" varStatus="itemStatus">
                                <tr>
                                    <td>${itemStatus.index + 1}</td>
                                    <td>${item.applNo}</td>
                                    <td>${item.userId}</td>
                                    <td>${item.upCount}</td>
                                    <td>${item.downCount}</td>
                                    <td>${item.errorCount}</td>
                                    <td>${item.errorFileName}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<content tag="local-script">
<script>
$(document).ready( function() {


});
</script>
</content>
</body>
</html>
