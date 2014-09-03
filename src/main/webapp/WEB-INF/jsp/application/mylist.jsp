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
                                    <button id="modify" class="btn btn-default btn-block" data-applNo="${item.applNo}" ${item.applStsCode=="00001"?"":"disabled"}>수정하기</button>
                                    <%--<button id="verify" class="btn btn-info btn-block" ${item.applStsCode=="00010"?"":"disabled"}>확인하기</button>--%>
                                    <button id="verify" class="btn btn-info btn-block" data-applNo="${item.applNo}">확인하기</button>
                                    <button id="pay" class="btn btn-primary btn-block"
                                            name="2015학년도 ${item.campName} ${item.admsTypeName} ${item.deptName} ${item.corsTypeName}"
                                            <%--value="80000" ${item.applStsCode=="00010"?"":(item.applStsCode=="00021"?"":"disabled")}>결제하기</button>--%>
                                            value="80000">결제하기</button>
                                    <button id="showApplicationBirt" class="btn btn-success btn-block" ${item.applStsCode=='00020'?"":"disabled"}>지원서보기</button>
                                    <button id="showAppLableBirt" class="btn btn-success btn-block" ${item.applStsCode=='00020'?"":"disabled"}>수험표출력</button>
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
            $('#modify').click(function(){
                <%--location.href="${contextPath}/application/modify";--%>
                return;
            });
            $('#verify').click(function(e){
                e.preventDefault();
                var applNo = e.target.getAttribute("data-applNo");
                location.href="${contextPath}/application/show/" + applNo;
                return;
            });
            $('#pay').click(function(){
                document.getElementById('LGD_PRODUCTINFO').value = $(this).name;
                document.getElementById('LGD_AMOUNT').value = $(this).value;
                $('#LGD_PAYINFO').submit();
            });
            $('#showApplicationBirt').click(function(){
                <%--location.href="${contextPath}/application/show";--%>
                return;
            });
            $('#showAppLabel').click(function(){
                <%--location.href="${contextPath}/application/show";--%>
                return;
            });
        })
    </script>
</content>
</body>
</html>
