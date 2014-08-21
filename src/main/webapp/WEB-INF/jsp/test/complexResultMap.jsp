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
            <div class="col-md-offset-0 col-md-12">
                <div>EntireApplication Size : ${entireApplication.size()}</div>
                <div>EntireApplication.Department : ${entireApplication.get(0).department.code}</div>
                <div>EntireApplication.Department : ${entireApplication.get(0).department.name}</div>
                <div>EntireApplication.Department : ${entireApplication.get(0).department.phoneNumber}</div>
                <div>EntireApplication.CareerList Size : ${entireApplication.get(0).careerList.size()}</div>
                <div>EntireApplication.CareerList[0].applNo : ${entireApplication.get(0).careerList.get(0).applNo}</div>
                <div>EntireApplication.CareerList[0].seqNo : ${entireApplication.get(0).careerList.get(0).seqNo}</div>
                <div>EntireApplication.CareerList[0].name : ${entireApplication.get(0).careerList.get(0).name}</div>
                <div>EntireApplication.CareerList[0].phoneNumber : ${entireApplication.get(0).careerList.get(0).phoneNumber}</div>
                <div>EntireApplication.AcademyList Size : ${entireApplication.get(0).academyList.size()}</div>
                <div>EntireApplication.AcademyList[0].applNo : ${entireApplication.get(0).academyList.get(0).applNo}</div>
                <div>EntireApplication.AcademyList[0].seqNo : ${entireApplication.get(0).academyList.get(0).seqNo}</div>
                <div>EntireApplication.AcademyList[0].name : ${entireApplication.get(0).academyList.get(0).name}</div>
                <div>EntireApplication.AcademyList[0].phoneNumber : ${entireApplication.get(0).academyList.get(0).phoneNumber}</div>
            </div>
<hr/>
            <div class="col-md-offset-0 col-md-12">
                <div>EntireApplication Size : ${entireApplication.size()}</div>
                <div>EntireApplication.Department : ${entireApplication.get(0).department}</div>
                <div>EntireApplication.CareerList Size : ${entireApplication.get(0).careerList.size()}</div>
                <div>EntireApplication.AcademyList Size : ${entireApplication.get(0).academyList.size()}</div>
            </div>

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
                        <tr><td>&nbsp;&nbsp;&nbsp;${ea}</td></tr>
                            <c:forEach items="${ea.careerList}" var="career">
                                <tr><td>&nbsp;&nbsp;&nbsp;${career}</td></tr>
                            </c:forEach>
                            <c:forEach items="${ea.academyList}" var="academy">
                                <tr><td>&nbsp;&nbsp;&nbsp;${academy}</td></tr>
                            </c:forEach>
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
