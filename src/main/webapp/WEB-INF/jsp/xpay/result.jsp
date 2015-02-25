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
        <form id="resultForm" method="post">
            <div class="row mar-bot40">
                <div class="col-md-6 col-md-offset-3">
                    <h2 class="slogan">결제 처리</h2>
                    <hr>
                        <%--${transactionVO.sysMsg}--%>
                    <h3 class="pay">${transactionVO.userMsg}</h3>
                    <%--<div>--%>
                        <%--<button class="btn btn-primary btn-lg btn-block" id="goMain">내 원서 보기</button>--%>
                    <%--</div>--%>
                    <div>수험표 및 원서 파일 생성 후 내 원서 화면으로 자동으로 이동합니다.</div>
                    <div>잠시만 기다려 주십시오.</div>
                        <%--<c:forEach var="item" items="${transactionVO.txMap}" varStatus="status">--%>
                            <%--${status.count}<br/>--%>
                            <%--${item.key} : ${item.value}<br/>--%>
                        <%--</c:forEach>--%>
                </div>
            </div>
        </form>
    </div>
</section>
<content tag="local-script">
<script>
$(document).ready(function() {

    // 결제 완료 후 BirtController를 호출해야 수험표와 원서를 물리적 파일로 저장할 수 있음
    var genFile = function () {
        $.ajax({
            type: 'GET',
            url: '${contextPath}/application/generate/application/${transactionVO.applNo}',
            success: function (data) {
                console.log('원서 파일 생성 완료');
                $.ajax({
                    type: 'GET',
                    url: '${contextPath}/pdf/merge/applicant/${transactionVO.applNo}',
                    success: function (data) {
                        console.log('머지 파일 생성 완료');
                        var form = document.getElementById('resultForm');
                        form.action = '${contextPath}/application/mylist';
                        form.submit();
                    },
                    error: function (data, status, e) {

                    }
                });
            },
            error: function (data, status, e) {

            }
        });
        $.ajax({
            type: 'GET',
            url: '${contextPath}/application/generate/slip/${transactionVO.applNo}',
            success: function (data) {
                console.log('수험표 파일 생성 완료');
            },
            error: function (data, status, e) {

            }
        });
    };
    genFile();

    $('#goMain').click( function () { location.href='${contextPath}/application/mylist'; });
});
</script>
</content>
</body>
</html>
