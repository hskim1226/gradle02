<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title>결제 완료 수험표 백업 결과</title>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-sm-offset-1 col-sm-10">
                <div class="form-group inner-container-white">
                    <div class="col-sm-offset-1 col-sm-10 text-gray">
                        <i class="fa fa-list fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b> 결제 완료 수험표 백업 결과</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="col-sm-offset-1 col-sm-10 align-center">
                        <table class="table table-stripped text-gray">
                            <thead>
                            <tr>
                                <th>지원 완료된 수험표 수</th>
                                <th>백업 완료된 수험표 수</th>
                                <th>백업 소요 시간</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${requestedCount}</td>
                                    <td>${downloadedCount}</td>
                                    <td>${elpasedTime}</td>
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
