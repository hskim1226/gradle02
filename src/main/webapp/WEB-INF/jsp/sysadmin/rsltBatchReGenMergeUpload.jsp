<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <style>
    .spacer-small {
        height: 2em;
    }
    .table-title {
        color: #eeeeee;
        background-color: #3355bb;
        font-weight: 600;
        font-size: 1.2em;
        margin-top: 1.5em;
    }
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-sm-offset-1 col-sm-10">
                <div class="form-group inner-container-white">
                    <div class="col-sm-offset-1 col-sm-10 text-gray">
                        <i class="fa fa-list fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b class="word-keep-all">결제 완료지만 최종 파일에 수험 번호가 없는 원서 재생성 및 업로드 결과</b></span>
                    </div>
                    <div class="col-sm-offset-1 col-sm-10 align-center table-title">전체 통계</div>
                    <div class="col-sm-offset-1 col-sm-10 align-center">
                        <table class="table table-stripped text-gray">
                            <thead>
                            <tr>
                                <th>대상 원서 수</th>
                                <th>작업 성공 수</th>
                                <th>원서 생성 오류 수</th>
                                <th>수험표 생성 오류 수</th>
                                <th>합치기 및 업로드 오류 수</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${totalCount}</td>
                                    <td>${successCount}</td>
                                    <td>${failGenApplList.size()}</td>
                                    <td>${failGenAdmsList.size()}</td>
                                    <td>${failMergePdfList.size()}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="col-sm-offset-1 col-sm-10 align-center table-title">원서 생성 오류</div>
                    <div class="col-sm-offset-1 col-sm-10 align-center">
                        <table class="table table-stripped text-gray">
                            <thead>
                            <tr>
                                <th>번호</th>
                                <th>APPL_NO</th>
                                <th>ADMS_NO</th>
                                <th>USER_ID</th>
                                <th>APPL_ID</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${failGenApplList.size() == 0}">
                                <tr>
                                    <td colspan="5">목록이 없습니다.</td>
                                </tr>
                            </c:if>
                            <c:forEach items="${failGenApplList}" var="item" varStatus="itemStatus">
                                <tr>
                                    <td>${itemStatus.index + 1}</td>
                                    <td>${item.applNo}</td>
                                    <td>${item.admsNo}</td>
                                    <td>${item.userId}</td>
                                    <td>${item.applId}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="col-sm-offset-1 col-sm-10 align-center table-title">수험표 생성 오류</div>
                    <div class="col-sm-offset-1 col-sm-10 align-center">
                        <table class="table table-stripped text-gray">
                            <thead>
                            <tr>
                                <th>번호</th>
                                <th>APPL_NO</th>
                                <th>ADMS_NO</th>
                                <th>USER_ID</th>
                                <th>APPL_ID</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${failGenAdmsList.size() == 0}">
                                <tr>
                                    <td colspan="5">목록이 없습니다.</td>
                                </tr>
                            </c:if>
                            <c:forEach items="${failGenAdmsList}" var="item" varStatus="itemStatus">
                                <tr>
                                    <td>${itemStatus.index + 1}</td>
                                    <td>${item.applNo}</td>
                                    <td>${item.admsNo}</td>
                                    <td>${item.userId}</td>
                                    <td>${item.applId}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="col-sm-offset-1 col-sm-10 align-center table-title">Merge 및 업로드 오류</div>
                    <div class="col-sm-offset-1 col-sm-10 align-center">
                        <table class="table table-stripped text-gray">
                            <thead>
                            <tr>
                                <th>번호</th>
                                <th>APPL_NO</th>
                                <th>ADMS_NO</th>
                                <th>USER_ID</th>
                                <th>APPL_ID</th>
                                <th>Encrypted PDF File</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${failMergePdfList.size() == 0}">
                                <tr>
                                    <td colspan="5">목록이 없습니다.</td>
                                </tr>
                            </c:if>
                            <c:forEach items="${failMergePdfList}" var="item" varStatus="itemStatus">
                                <tr>
                                    <td>${itemStatus.index + 1}</td>
                                    <td>${item.applNo}</td>
                                    <td>${item.admsNo}</td>
                                    <td>${item.userId}</td>
                                    <td>${item.applId}</td>
                                    <td>
                                    <c:forEach items="${encryptedPdfListList.get(itemStatus.index)}" var="encryptedDoc" varStatus="encDocStatus">
                                        ${encryptedDoc.filePath}<br/>
                                    </c:forEach>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="col-sm-offset-1 col-sm-10 align-center"><span style="color: #333333; font-size: 1.1em;">${elapsedTime}<span></div>
                </div>
            </div>
        </div>
    </div>
</section>
<content tag="local-script">
<script>
$(document).ready( function() {
    <%-- 단어 잘림 방지 --%>
    $('.word-keep-all').wordBreakKeepAll();
});
</script>
</content>
</body>
</html>
