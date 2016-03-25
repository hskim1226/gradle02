<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title>Final PDF 파일 분석 결과</title>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-sm-offset-1 col-sm-10">
                <div class="form-group inner-container-white">
                    <div class="col-sm-offset-1 col-sm-10 text-gray">
                        <i class="fa fa-list fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b> 추천서 처리 독려 메일 발송 결과</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="col-sm-offset-1 col-sm-10 align-center">
                        <table class="table table-stripped text-gray">
                            <thead>
                            <tr>
                                <th>메일 발송 대상 추천자 수</th>
                                <th>메일 발송 실패 추천자 수<br/>(메일 주소 오류는 여기에 포함되지 않음)</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${allList.size()}</td>
                                    <td>${failedList.size()}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <c:if test="${failedList.size() > 0}">
                    <div class="spacer-tiny">&nbsp;</div>
                    <div class="col-sm-offset-1 col-sm-10 align-center">
                        <table class="table table-stripped text-gray">
                            <thead>
                            <tr>
                                <th>applNo</th>
                                <th>지원자 이름</th>
                                <th>추천인 이름</th>
                                <th>추천인 메일</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${failedList}" var="item" varStatus="status">
                            <tr>
                                <td>${item.applNo}</td>
                                <td>${item.applicantName}</td>
                                <td>${item.profName}</td>
                                <td>${item.profMailAddr}</td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    </c:if>
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
