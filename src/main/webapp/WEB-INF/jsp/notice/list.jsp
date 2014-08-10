<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <style>
        section.application-list {
            padding: 200px 0 60px;
            background: #4f4f9f;
            color: #fdfdfd;
            position:relative;
        }

        section.application-list h2.slogan {
            color: #fff;
            font-size: 36px;
            font-weight: 900;
        }

        /* inner heading */
        section.application-list.inner {
            background: #eee;
            padding: 150px 0 50px;
        }
    </style>
</head>
<body>
<section class="application-list" id="app-list">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-md-10 col-md-offset-1">
                <h2 class="slogan">공고 목록</h2>
                <div class="align-center">
                    <table class="table table-stripped">
                        <thead>
                        <tr>
                            <th>대학원</th>
                            <th>공고명</th>
                            <th>접수 기간</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>일반</td>
                            <td><a href="${contextPath}/application/create">2015학년도 연세대학교 일반대학원 석사과정 수시 모집</a></td>
                            <td>2014-09-28 / 2014-10-03</td>
                        </tr>
                        <tr>
                            <td>행정</td>
                            <td><a href="${contextPath}/application/create">2015학년도 연세대학교 행정대학원 석사과정 수시 모집</a></td>
                            <td>2014-09-28 / 2014-10-03</td>
                        </tr>
                        <tr>
                            <td>공학</td>
                            <td><a href="${contextPath}/application/create">2015학년도 연세대학교 공학대학원 석박사 통합 과정 수시 모집</a></td>
                            <td>2014-09-28 / 2014-10-03</td>
                        </tr>
                        <tr>
                            <td>의학</td>
                            <td><a href="${contextPath}/application/create">2015학년도 연세대학교 의학대학원 박사과정 수시 모집</a></td>
                            <td>2014-09-28 / 2014-10-03</td>
                        </tr>
                        <tr>
                            <td>교육</td>
                            <td><a href="${contextPath}/application/create">2015학년도 연세대학교 교육대학원 석사과정(야간) 수시 모집</a></td>
                            <td>2014-09-28 / 2014-10-03</td>
                        </tr>
                        <tr>
                            <td>음악</td>
                            <td><a href="${contextPath}/application/create">2015학년도 연세대학교 음악대학원 석박사 통합 과정 수시 모집</a></td>
                            <td>2014-09-28 / 2014-10-03</td>
                        </tr>
                        <tr>
                            <td>음악</td>
                            <td><a href="${contextPath}/application/create">2015학년도 연세대학교 음악대학원 석박사 통합 과정 수시 모집</a></td>
                            <td>2014-09-28 / 2014-10-03</td>
                        </tr>
                        <tr>
                            <td>음악</td>
                            <td><a href="${contextPath}/application/create">2015학년도 연세대학교 음악대학원 석박사 통합 과정 수시 모집</a></td>
                            <td>2014-09-28 / 2014-10-03</td>
                        </tr>
                        <tr>
                            <td>음악</td>
                            <td><a href="${contextPath}/application/create">2015학년도 연세대학교 음악대학원 석박사 통합 과정 수시 모집</a></td>
                            <td>2014-09-28 / 2014-10-03</td>
                        </tr>
                        <tr>
                            <td>음악</td>
                            <td><a href="${contextPath}/application/create">2015학년도 연세대학교 음악대학원 석박사 통합 과정 수시 모집</a></td>
                            <td>2014-09-28 / 2014-10-03</td>
                        </tr>
                        <tr>
                            <td>음악</td>
                            <td><a href="${contextPath}/application/create">2015학년도 연세대학교 음악대학원 석박사 통합 과정 수시 모집</a></td>
                            <td>2014-09-28 / 2014-10-03</td>
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
