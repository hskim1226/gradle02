<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lgdacom.XPayClient.XPayClient"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title><spring:message code="L05301"/><%--결제 및 신청 완료--%></title>
    <style>
    /* spinner */
    /* http://projects.lukehaas.me/css-loaders/ */
    .loader {
        font-size: 7px;
        margin: 4em auto;
        width: 1em;
        height: 1em;
        border-radius: 50%;
        position: relative;
        text-indent: -9999em;
        -webkit-animation: load4 1.3s infinite linear;
        animation: load4 1.3s infinite linear;
    }
    @-webkit-keyframes load4 {
        0%,
        100% {
            box-shadow: 0em -3em 0em 0.2em #55aaff, 2em -2em 0 0em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 0em #55aaff;
        }
        12.5% {
            box-shadow: 0em -3em 0em 0em #55aaff, 2em -2em 0 0.2em #55aaff, 3em 0em 0 0em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
        }
        25% {
            box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 0em #55aaff, 3em 0em 0 0.2em #55aaff, 2em 2em 0 0em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
        }
        37.5% {
            box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 0em #55aaff, 2em 2em 0 0.2em #55aaff, 0em 3em 0 0em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
        }
        50% {
            box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 0em #55aaff, 0em 3em 0 0.2em #55aaff, -2em 2em 0 0em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
        }
        62.5% {
            box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 0em #55aaff, -2em 2em 0 0.2em #55aaff, -3em 0em 0 0em #55aaff, -2em -2em 0 -0.5em #55aaff;
        }
        75% {
            box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 0em #55aaff, -3em 0em 0 0.2em #55aaff, -2em -2em 0 0em #55aaff;
        }
        87.5% {
            box-shadow: 0em -3em 0em 0em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 0em #55aaff, -3em 0em 0 0em #55aaff, -2em -2em 0 0.2em #55aaff;
        }
    }
    @keyframes load4 {
        0%,
        100% {
            box-shadow: 0em -3em 0em 0.2em #55aaff, 2em -2em 0 0em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 0em #55aaff;
        }
        12.5% {
            box-shadow: 0em -3em 0em 0em #55aaff, 2em -2em 0 0.2em #55aaff, 3em 0em 0 0em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
        }
        25% {
            box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 0em #55aaff, 3em 0em 0 0.2em #55aaff, 2em 2em 0 0em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
        }
        37.5% {
            box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 0em #55aaff, 2em 2em 0 0.2em #55aaff, 0em 3em 0 0em #55aaff, -2em 2em 0 -0.5em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
        }
        50% {
            box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 0em #55aaff, 0em 3em 0 0.2em #55aaff, -2em 2em 0 0em #55aaff, -3em 0em 0 -0.5em #55aaff, -2em -2em 0 -0.5em #55aaff;
        }
        62.5% {
            box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 0em #55aaff, -2em 2em 0 0.2em #55aaff, -3em 0em 0 0em #55aaff, -2em -2em 0 -0.5em #55aaff;
        }
        75% {
            box-shadow: 0em -3em 0em -0.5em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 0em #55aaff, -3em 0em 0 0.2em #55aaff, -2em -2em 0 0em #55aaff;
        }
        87.5% {
            box-shadow: 0em -3em 0em 0em #55aaff, 2em -2em 0 -0.5em #55aaff, 3em 0em 0 -0.5em #55aaff, 2em 2em 0 -0.5em #55aaff, 0em 3em 0 -0.5em #55aaff, -2em 2em 0 0em #55aaff, -3em 0em 0 0em #55aaff, -2em -2em 0 0.2em #55aaff;
        }
    }
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <form id="resultForm" method="post">
            <div class="row mar-bot40">
                <div class="col-md-offset-1 col-md-10">
                    <div class="form-group inner-container-white">
                        <div class="col-md-offset-1 col-md-10">
                            <div class="col-sm-12 text-gray">
                                <i class="fa fa-thumbs-o-up fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L05301"/><%--결제 및 신청 완료--%></b></span>
                            </div>
                            <div class="spacer-small">&nbsp;</div>
                            <div class="col-sm-12 text-gray">
                                <h3 class="pay">${transactionVO.userMsg}</h3>
                                <div class="col-sm-12 file-gen">
                                    <div class="col-sm-12"><spring:message code="U05201"/><%--수험표 및 원서 파일 생성 중 입니다.--%></div>
                                    <div class="col-sm-12"><spring:message code="U05202"/><%--잠시만 기다리시면 내 원서 보기 버튼이 나타납니다.--%></div>
                                </div>
                            </div>
                            <div class="spacer-tiny">&nbsp;</div>
                            <div id="spinner" class="col-sm-12">
                                <div class="loader"></div>
                                <div class="col-sm-12" style="font-size: 24px; color: #55aaff; text-align: center;"><spring:message code="U04512"/></div>  <%--Loading...--%>
                            </div>

                            <div class="col-sm-12">
                                <button class="btn btn-primary btn-lg btn-block" id="goMain" style="display: none;"><spring:message code="L05302"/><%--내 원서 보기--%></button>
                            </div>
                            <%--${transactionVO.sysMsg}--%>



                            <%--<c:forEach var="item" items="${transactionVO.txMap}" varStatus="status">--%>
                            <%--${status.count}<br/>--%>
                            <%--${item.key} : ${item.value}<br/>--%>
                            <%--</c:forEach>--%>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" name="application.applNo" value="${transactionVO.applNo}"/>
        </form>
    </div>
</section>
<content tag="local-script">
<script>
$(document).ready(function() {

    // 결제 완료 후 BirtController를 호출해야 수험표와 원서를 물리적 파일로 저장할 수 있음
    var genFile = function () {

        var form = document.getElementById('resultForm'),
            formData = $(form).serialize();
        $.ajax({
            type: 'POST',
            url: '${contextPath}/application/generate/application',
            data: formData,
            success: function (data) {
                if (console) {
                    console.log('원서 파일 생성 완료');
                }

                $.ajax({
                    type: 'POST',
                    url: '${contextPath}/pdf/merge/applicant',
                    data: formData,
                    success: function (data) {
                        if (console) {
                            console.log('머지 파일 생성 완료');
                        }

                        document.getElementById('spinner').style.display = 'none';
                        document.getElementById('goMain').style.display = 'block';
                    },
                    error: function (data, status, e) {
                        if (console) {
                            console.log('머지 파일 생성 실패');
                        }

                    }
                });
            },
            error: function (data, status, e) {
                if (console) {
                    console.log('원서 파일 생성 실패');
                }

            }
        });
        $.ajax({
            type: 'POST',
            url: '${contextPath}/application/generate/slip',
            data: formData,
            success: function (data) {
                if (console) {
                    console.log('수험표 파일 생성 완료');
                }

            },
            error: function (data, status, e) {
                if (console) {
                    console.log('수험표 파일 생성 실패');
                }

            }
        });
    };
    genFile();

    $('#goMain').click( function () {
        var form = document.getElementById('resultForm');
        form.action = '${contextPath}/application/mylist';
        form.submit();
    });
});
</script>
</content>
</body>
</html>
