<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        section.application-mylist {
            padding: 200px 0 60px;
            background: #443355;
            color: #fdfdfd;
            position:relative;
        }

        section.application-mylist h2.slogan {
            color: #fff;
            font-size: 36px;
            font-weight: 900;
        }

        section.application-mylist .spacer-big {
            margin-bottom: 7em;
        }

        section.application-mylist .spacer-mid {
            margin-bottom: 5em;
        }

        section.application-mylist .spacer-small {
            margin-bottom: 3em;
        }

        section.application-mylist .spacer-tiny {
            margin-bottom: 1em;
        }

    </style>
</head>
<body>

<section class="application-mylist" id="application-mylist">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-md-10 col-md-offset-1">
                <h2 class="slogan">Q&A</h2>
                <div class="align-center">
                    <form class="form-horizontal" id="LGD_PAYINFO" role="form" action="${contextPath}/pay/confirm" method="post">
                        <table class="table table-stripped">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>제목</th>
                                <th>조회수</th>
                                <th>등록일</th>
                                <th>답변여부</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="qna" items="${qna}" varStatus="status">
                            <tr>
                                <td>${qna.id}</td>
                                <td>${qna.title}</td>
                                <td>${qna.readCnt}</td>
                                <td>${qna.creDate}</td>
                                <td><c:if test="${empty qna.answer}" >Not Yet</c:if><c:if test="${not empty qna.answer}" >Yes</c:if></td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </div>
                <div class="align-center">
                    <ul class="pagination pagination-sm">
                        <li><a href="#">&laquo;</a></li>
                        <c:forEach begin="1" end="${qnaTotal/30}" varStatus="status">
                            <li><a href="#">${status.index+1}</a></li>
                        </c:forEach>
                        <li><a href="#">&raquo;</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>