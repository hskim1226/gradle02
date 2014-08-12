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
                            <th>접수 기간</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>일반</td>
                            <td><a href="${contextPath}/notice/2015-1-general">2015학년도 전기 연세대학교 대학원 일반 전형</a></td>
                            <td>2014-09-28(월) / 2014-10-03(수)</td>
                        </tr>
                        <tr>
                            <td>외국인</td>
                            <td><a href="${contextPath}/application/create">2015학년도 전기 연세대학교 대학원 외국인 전형</a></td>
                            <td>2014-09-28(월) / 2014-10-03(수)</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
