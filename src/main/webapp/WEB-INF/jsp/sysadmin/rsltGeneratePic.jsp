<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title>학생증 사진 다운로드 결과</title>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-sm-offset-1 col-sm-10">
                <div class="form-group inner-container-white">
                    <div class="col-sm-offset-1 col-sm-10 text-gray">
                        <i class="fa fa-list fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b>학생증 사진 다운로드 결과</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="col-sm-offset-1 col-sm-10 align-center">
                        <table class="table table-stripped text-gray">
                            <thead>
                            <tr>
                                <th>대상자 수</th>
                                <th>다운로드 성공 계</th>
                                <th>다운로드 실패 계</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${totalCount}</td>
                                    <td>${successCount}</td>
                                    <td>${failureCount}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="spacer-tiny">&nbsp;</div>
                        <div class="col-sm-12 text-gray instruction">
                            서버 로컬의 ${picturePath} 에 '학번-이름.확장자'의 형식으로 다운로드 되었습니다.
                        </div>
                    <div class="spacer-small">&nbsp;</div>
                <c:if test="${failureList.size() > 0}">
                    <div class="col-sm-offset-1 col-sm-10 align-center">
                        <table class="table table-stripped text-gray">
                            <thead>
                            <tr>
                                <th>No</th>
                                <th>다운로드 실패 수험번호</th>
                            </tr>
                            </thead>
                            <tbody>
                        <c:forEach items="${failureList}" var="applId" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>${applId}</td>
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
