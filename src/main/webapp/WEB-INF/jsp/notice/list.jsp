<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <style>
        section.notice-detail {
            padding: 200px 0 60px;
            background: #4f4f9f;
            color: #fdfdfd;
            position:relative;
            min-height: 615px;
        }

        section.notice-detail h2.slogan {
            color: #fff;
            font-size: 36px;
            font-weight: 900;
        }

        /* inner heading */
        section.notice-detail.inner {
            background: #eee;
            padding: 150px 0 50px;
        }

        a { color: #fdfdfd; }
    </style>
</head>
<body>
<section class="notice-detail" id="app-list">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-md-10 col-md-offset-1">
                <h2 class="slogan">공고 목록</h2>
                <div class="align-center">
                    <table class="table table-stripped">
                        <thead>
                        <tr>
                            <th>전형 구분</th>
                            <th>공고명</th>
                            <th>모집 요강</th>
                            <th>원서 작성</th>
                            <th>접수 기간</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <form id="generalApplyForm" method="post">
                                <td>일반</td>
                                <td>2015학년도 전기 연세대학교 대학원 일반 전형</td>
                                <td><button type="button" id="toGeneralInfo" class="btn btn-info">모집 요강</button></td>
                                <td><button type="submit" id="toGeneralApply" class="btn btn-primary">원서 작성</button></td>
                                <td>2014-09-28(월) / 2014-10-08(수)</td>
                                <input type="hidden" name="admsNo" value="<spring:eval expression="@app.getProperty('adms.general')" />" />
                                <input type="hidden" name="entrYear" value="${admsGeneral.entrYear}" />
                                <input type="hidden" name="admsTypeCode" value="${admsGeneral.admsType}" />
                            </form>
                        </tr>
                        <tr>
                            <form id="foreignApplyForm" method="post">
                                <td>외국인</td>
                                <td>2015학년도 전기 연세대학교 대학원 외국인 전형</td>
                                <td><button type="button" id="toForeignInfo" class="btn btn-info">모집 요강</button></td>
                                <td><button type="submit" id="toForeignApply" class="btn btn-primary">원서 작성</button></td>
                                <td>2014-09-28(월) / 2014-10-08(수)</td>
                                <input type="hidden" name="admsNo" value="<spring:eval expression="@app.getProperty('adms.foreign')" />" />
                                <input type="hidden" name="entrYear" value="${admsForeign.entrYear}" />
                                <input type="hidden" name="admsTypeCode" value="${admsForeign.admsType}" />
                            </form>
                        </tr>
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
                foreignForm = document.getElementById('foreignApplyForm');
            $('#toGeneralInfo').on('click', function(){
                generalForm.action = "${contextPath}/notice/general";
                generalForm.submit();
            });
            $('#toGeneralApply').on('click', function(){
                generalForm.action = "${contextPath}/application/agreement";
                generalForm.submit();
            });
            $('#toForeignInfo').on('click', function(){
                foreignForm.action = "${contextPath}/notice/foreign";
                foreignForm.submit();
            });
            $('#toForeignApply').on('click', function(){
                foreignForm.action = "${contextPath}/application/agreement";
                foreignForm.submit();
            });
        });
    </script>
</content>
</body>
</html>
