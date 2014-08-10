<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
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
                <h2 class="slogan">미결제 원서</h2>
                <div class="align-center">
                    <form class="form-horizontal" id="LGD_PAYINFO" role="form" action="${contextPath}/pay/confirm" method="post">
                        <table class="table table-stripped">
                            <thead>
                            <tr>
                                <th>대학원</th>
                                <th>신청과정</th>
                                <th>접수마감</th>
                                <th>원서수정</th>
                                <th>결제하기</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>일반</td>
                                <td><a href="${contextPath}/application/create">2015학년도 연세대학교 일반대학원 일반 전형</a></td>
                                <td>2014-10-08</td>
                                <td><button type="button" class="btn btn-info">수정하기</button></td>
                                <td><button type="button" class="btn btn-primary" id="p1" value="80000">결제하기</button></td>
                            </tr>
                            <tr>
                                <td>의학</td>
                                <td><a href="${contextPath}/application/create">2015학년도 연세대학교 일반대학원 외국인 전형</a></td>
                                <td>2014-10-08</td>
                                <td><button type="button" class="btn btn-info">수정하기</button></td>
                                <td><button type="button" class="btn btn-primary" id="p2하루살이" value="60000">결제하기</button></td>
                            </tr>
                            </tbody>
                        </table>

                        <input type="hidden" name="LGD_PRODUCTINFO" id="LGD_PRODUCTINFO">
                        <input type="hidden" name="LGD_AMOUNT" id="LGD_AMOUNT">
                        <input type="hidden" name="LGD_TIMESTAMP" id="LGD_TIMESTAMP">
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<content tag="local-script">
    <script>
    $(document).ready( function() {
        $('.btn-primary').click(function(){
            document.getElementById('LGD_PRODUCTINFO').value = $(this)[0].id;
            document.getElementById('LGD_AMOUNT').value = $(this)[0].value;
            $('#LGD_PAYINFO').submit();
        });
    })
    </script>
</content>
</body>
</html>
