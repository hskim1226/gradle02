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
                <h2 class="slogan">지원 내역</h2>
                <div class="align-center">
                    <form class="form-horizontal" id="LGD_PAYINFO" role="form" action="${contextPath}/pay/confirm" method="post">
                        <table class="table table-stripped">
                            <thead>
                            <tr>
                                <th>캠퍼스</th>
                                <th>전형</th>
                                <th>학과</th>
                                <th>과정</th>
                                <th>세부전공</th>
                                <th>지원상태</th>
                                <th>작업</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${myList}" var="item">
                            <tr>
                                <td valign="middle" style="vertical-align: middle;">${item.campName}</td>
                                <td valign="middle" style="vertical-align: middle;">${item.admsTypeName}</td>
                                <td valign="middle" style="vertical-align: middle;">${item.deptName}</td>
                                <td valign="middle" style="vertical-align: middle;">${item.corsTypeName}</td>
                                <td valign="middle" style="vertical-align: middle;">${item.detlMajName}</td>
                                <td valign="middle" style="vertical-align: middle;">${item.applStsName}</td>
                                <td valign="middle" style="vertical-align: middle;">
                                    <button type="button" class="btn btn-default btn-block" disabled>수정하기</button>
                                    <button type="button" class="btn btn-info btn-block" disabled>확인하기</button>
                                    <button type="button" class="btn btn-primary btn-block"
                                            id="notice1" name="2015학년도 ${item.campName} ${item.admsTypeName} ${item.deptName} ${item.corsTypeName}"
                                            value="80000">결제하기</button>
                                    <button type="button" class="btn btn-success btn-block">지원서보기</button>
                                    <button type="button" class="btn btn-success btn-block">수험표출력</button>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                        <input type="hidden" name="LGD_PRODUCTINFO" id="LGD_PRODUCTINFO"/>
                        <input type="hidden" name="LGD_AMOUNT" id="LGD_AMOUNT"/>
                        <input type="hidden" name="LGD_TIMESTAMP" id="LGD_TIMESTAMP"/>
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
                document.getElementById('LGD_PRODUCTINFO').value = $(this)[0].name;
                document.getElementById('LGD_AMOUNT').value = $(this)[0].value;
                $('#LGD_PAYINFO').submit();
            });
        })
    </script>
</content>
</body>
</html>
