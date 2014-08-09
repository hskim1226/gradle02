<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lgdacom.XPayClient.XPayClient"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
<section class="application-mylist" id="app-mylist">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-md-6 col-md-offset-3">
                <h2 class="slogan">결제 처리</h2>
                <div>
                    ${transactionVO.msg}
                </div>
                <div>
                    <c:forEach var="item" items="${transactionVO.txMap}" varStatus="status">
                        ${status.count}<br/>
                        ${item.key} : ${item.value}<br/>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
