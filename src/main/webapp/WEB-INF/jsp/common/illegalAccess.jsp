<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang="ko">
<head>
    <title>인가 되지 않은 접근</title>
    <style>
        section.application {
            padding: 160px 0 60px;
            background: #555555;
            color: #ddd;
            position:relative;
        }

        section.application h2 {
            color: #ddd;
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
        <h2>인가 되지 않은 접근입니다.</h2>
    </div>
</section>
</body>
</html>