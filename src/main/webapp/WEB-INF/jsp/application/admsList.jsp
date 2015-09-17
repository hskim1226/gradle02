<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title><spring:message code="L00401"/><%--전형 목록--%></title>
    <style>
        a { color: #fdfdfd; }
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-sm-12 form-group inner-container-white">
                <div class="col-sm-12 text-gray">
                    <i class="fa fa-list-alt fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L00401"/><%--전형 목록--%></b></span>
                </div>
                <div class="spacer-small">&nbsp;</div>
                <div class="col-sm-12 align-center">
                    <table class="table table-stripped text-gray">
                        <thead>
                        <tr>
                            <th><spring:message code="L00402"/><%--전형 구분--%></th>
                            <th><spring:message code="L00403"/><%--공고명--%></th>
                            <th><spring:message code="L00404"/><%--모집 요강--%></th>
                            <th><spring:message code="L00405"/><%--원서 작성--%></th>
                            <th><spring:message code="L00406"/><%--접수 기간--%></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <form id="generalApplyForm" method="post">
                                <td><spring:message code="L00407"/><%--일반--%></td>
                                <td><spring:message code="L00410"/><%--2015학년도 후기 연세대학교 대학원 일반 전형--%></td>
                                <td><button type="button" id="toGeneralInfo" class="btn btn-info"><spring:message code="L00404"/><%--모집 요강--%></button></td>
                                <td><button type="submit" id="toGeneralApply" class="btn btn-primary"><spring:message code="L00405"/><%--원서 작성--%></button></td>
                                <td><spring:message code="L00413"/><%--2015-04-08(수) / 2015-04-10(금)--%></td>
                                <input type="hidden" name="admsNo" value="${admsGeneral.admsNo}" />
                                <input type="hidden" name="entrYear" value="${admsGeneral.entrYear}" />
                                <input type="hidden" name="admsTypeCode" value="${admsGeneral.admsType}" />
                            </form>
                        </tr>
                        <tr>
                            <form id="foreignApplyForm" method="post">
                                <td><spring:message code="L00408"/><%--외국인--%></td>
                                <td><spring:message code="L00411"/><%--2015학년도 후기 연세대학교 대학원 외국인 전형--%></td>
                                <td><button type="button" id="toForeignInfo" class="btn btn-info"><spring:message code="L00404"/><%--모집 요강--%></button></td>
                                <td><button type="submit" id="toForeignApply" class="btn btn-primary"><spring:message code="L00405"/><%--원서 작성--%></button></td>
                                <td><spring:message code="L00414"/><%--2015-04-08(수) / 2015-04-10(금)--%></td>
                                <input type="hidden" name="admsNo" value="${admsForeign.admsNo}" />
                                <input type="hidden" name="entrYear" value="${admsForeign.entrYear}" />
                                <input type="hidden" name="admsTypeCode" value="${admsForeign.admsType}" />
                            </form>
                        </tr>
                        <%--2016-01은 조기 전형 없음--%>
                        <%--<tr>--%>
                            <%--<form id="earlyApplyForm" method="post">--%>
                                <%--<td><spring:message code="L00409"/>&lt;%&ndash;조기&ndash;%&gt;</td>--%>
                                <%--<td><spring:message code="L00412"/>&lt;%&ndash;2016학년도 전기 연세대학교 대학원 조기 전형&ndash;%&gt;</td>--%>
                                <%--<td><button type="button" id="toEarlyInfo" class="btn btn-info"><spring:message code="L00404"/>&lt;%&ndash;모집 요강&ndash;%&gt;</button></td>--%>
                                <%--<td><button type="submit" id="toEarlyApply" class="btn btn-primary"><spring:message code="L00405"/>&lt;%&ndash;원서 작성&ndash;%&gt;</button></td>--%>
                                <%--<td><spring:message code="L00415"/>&lt;%&ndash;2015-04-08(수) / 2015-04-10(금)&ndash;%&gt;</td>--%>
                                <%--<input type="hidden" name="admsNo" value="${admsEarly.admsNo}" />--%>
                                <%--<input type="hidden" name="entrYear" value="${admsEarly.entrYear}" />--%>
                                <%--<input type="hidden" name="admsTypeCode" value="${admsEarly.admsType}" />--%>
                            <%--</form>--%>
                        <%--</tr>--%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
<content tag="local-script">
    <script>
        $(document).ready( function() {
            var generalForm = document.getElementById('generalApplyForm'),
                foreignForm = document.getElementById('foreignApplyForm'),
                earlyApplyForm = document.getElementById('earlyApplyForm');
            $('#toGeneralInfo').on('click', function(){
                generalForm.action = "${contextPath}/application/general";
                generalForm.submit();
            });
            $('#toGeneralApply').on('click', function(){
                generalForm.action = "${contextPath}/application/agreement";
                generalForm.submit();
            });
            $('#toForeignInfo').on('click', function(){
                foreignForm.action = "${contextPath}/application/foreign";
                foreignForm.submit();
            });
            $('#toForeignApply').on('click', function(){
                foreignForm.action = "${contextPath}/application/agreement";
                foreignForm.submit();
            });
            <%-- 2016-01은 조기 전형 없음 --%>
            <%--$('#toEarlyInfo').on('click', function(){--%>
                <%--earlyApplyForm.action = "${contextPath}/application/early";--%>
                <%--earlyApplyForm.submit();--%>
            <%--});--%>
            <%--$('#toEarlyApply').on('click', function(){--%>
                <%--earlyApplyForm.action = "${contextPath}/application/agreement";--%>
                <%--earlyApplyForm.submit();--%>
            <%--});--%>
        });
    </script>
</content>
</body>
</html>
