<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang="ko">
<head>
    <title>시스템 오류</title>
    <style>
        section.application {
            padding: 160px 0 60px;
            background: #555555;
            color: #bbb;
            position:relative;
        }

        section.application h2 {
            color: #bbb;
            font-size: 36px;
            font-weight: 900;
        }

        section.application .spacer-big {
            margin-bottom: 7em;
        }

        section.application .spacer-mid {
            margin-bottom: 5em;
        }

        section.application .spacer-small {
            height: 3em;
        }

        section.application .spacer-tiny {
            height: 1em;
        }
    </style>
</head>
<body>
<section class="application">
    <div class="container">
        <h2>사용에 불편을 드려 죄송합니다.</h2>
        <h3>요청하신 페이지가 존재하지 않습니다.</h3>
    </div>
</section>
</body>
</html>