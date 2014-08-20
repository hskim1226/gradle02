<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <style>
        section.application-selfintro {
            padding: 200px 0 60px;
            background: #5f5f5f;
            color: #fdfdfd;
            position:relative;
        }

        section h2.slogan {
            color: #fff;
            font-size: 36px;
            font-weight: 900;
        }

        section h3.slogan {
            color: #fdfdfd;
            font-size: 24px;
            font-weight: 500;
            text-align: left;
        }

        section.notice-detail h4.slogan {
            color: #ff8d8d;
            font-size: 18px;
            font-weight: 500;
            text-align: left;
        }

        section .slogan {
            font-weight: 900;
        }

        section .big-font {
            color: #fdfdfd;
            font-size: 20px;
            font-weight: 500;
            text-align: left;
        }

        section .mid-font {
            color: #fdfdfd;
            font-size: 18px;
            text-align: left;
        }

        section .small-font {
            color: #fdfdfd;
            font-size: 12px;
            text-align: left;
        }

        section .text-center {
            text-align: center;
        }


    </style>
</head>
<body>
<section class="application-selfintro" id="application-create">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-md-offset-0 col-md-11">
                <h2 class="slogan">Form Test</h2>

                <div class="spacer-small"></div>

                <table class="table table-default">
                    <tr>
                        <td>${application}</td>
                    </tr>
                    <tr>
                        <td>${joinedApplication}</td>
                    </tr>
                    <tr>
                        <td>${entireApplication}</td>
                    </tr>
                    <c:forEach items="${entireApplication}" var="ea">
                        <tr>
                            <td>&nbsp;&nbsp;&nbsp;${ea}</td>
                            <td>&nbsp;&nbsp;&nbsp;${ea.career}</td>
                            <td>&nbsp;&nbsp;&nbsp;${ea.academy}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</section>
<content tag="local-script">
    <script>

    </script>
</content>
</body>
</html>
