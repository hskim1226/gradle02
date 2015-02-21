<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lgdacom.XPayClient.XPayClient"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <style>
        th.header {
            background-color: #444488;
        }
        section.resultPayment {
            padding: 200px 0 60px;
            background: #333377;
            color: #fdfdfd;
            position:relative;
            min-height: 615px;
        }

        section.resultPayment h2.slogan {
            color: #fff;
            font-size: 36px;
            font-weight: 900;
        }

        section.resultPayment h3.pay {
            color: #fff;
            font-size: 24px;
            font-weight: 200;
        }

        section.resultPayment .spacer-big {
            margin-bottom: 7em;
        }

        section.resultPayment .spacer-mid {
            margin-bottom: 5em;
        }

        section.resultPayment .spacer-small {
            margin-bottom: 3em;
        }

        section.resultPayment .spacer-tiny {
            margin-bottom: 1em;
        }
    </style>
</head>
<body>
<section class="resultPayment" id="resultPayment">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-md-6 col-md-offset-3">
                <h2 class="slogan">결제 처리</h2>
                <hr>
                    <%--${transactionVO.sysMsg}--%>
                <h3 class="pay">${transactionVO.userMsg}</h3>
                <div>
                    <button class="btn btn-primary btn-lg btn-block" id="goMain">내 원서 보기</button>
                </div>
                <div>
                    <%--<c:forEach var="item" items="${transactionVO.txMap}" varStatus="status">--%>
                        <%--${status.count}<br/>--%>
                        <%--${item.key} : ${item.value}<br/>--%>
                    <%--</c:forEach>--%>
                </div>
            </div>
        </div>
    </div>
</section>
<content tag="local-script">
    <script>
        $('#goMain').click( function () { location.href='${contextPath}/application/mylist'; });
    </script>
</content>
</body>
</html>
