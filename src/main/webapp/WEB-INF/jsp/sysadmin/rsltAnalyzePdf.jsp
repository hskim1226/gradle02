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
                        <i class="fa fa-list fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b>최종 PDF 파일 분석 결과</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="col-sm-offset-1 col-sm-10 align-center">
                        <table class="table table-stripped text-gray">
                            <thead>
                            <tr>
                                <th rowspan="2">총 결제 완료 계</th>
                                <th colspan="2">최종 파일 계</th>
                                <th rowspan="2">누락 계</th>
                            </tr>
                            <tr>
                                <th>APPL_ID 있음</th>
                                <th>APPL_ID 없음</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${totalPaidAppl}</td>
                                    <td>${fileWithApplId}</td>
                                    <td>${fileWithoutApplId}</td>
                                    <td>${fileNotFound}</td>
                                </tr>
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
