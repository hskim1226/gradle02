<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<% String a = "abcde"; %>
<html>
<head>
    <title></title>
</head>
<body>
<section class="application-mylist" id="app-mylist">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-md-10 col-md-offset-1">
                <h2 class="slogan">미결제 원서</h2>
                <div class="align-center">
                    <form class="form-horizontal" id="myListForm" role="form" action="${contextPath}/pay/request" method="post">
                        <table class="table table-stripped">
                            <thead>
                            <tr>
                                <th>대학원</th>
                                <th>공고명</th>
                                <th>접수마감</th>
                                <th>원서수정</th>
                                <th>결제하기</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>일반</td>
                                <td><a href="${contextPath}/application/create">2015학년도 연세대학교 일반대학원 석사과정 수시 모집</a></td>
                                <td>2014-10-03</td>
                                <td><button type="button" class="btn btn-info">수정하기</button></td>
                                <td><button type="button" class="btn btn-primary" id="p1">결제하기</button></td>
                            </tr>
                            <tr>
                                <td>의학</td>
                                <td><a href="${contextPath}/application/create">2015학년도 연세대학교 의학대학원 박사과정 수시 모집</a></td>
                                <td>2014-10-03</td>
                                <td><button type="button" class="btn btn-info">수정하기</button></td>
                                <td><button type="button" class="btn btn-primary" id="p2">결제하기</button></td>
                            </tr>
                            </tbody>
                        </table>

                        <input type="hidden" name="LGD_PRODUCTINFO" id="LGD_PRODUCTINFO" value="연세대학교 일반대학원 원서 접수"> <!-- 상품정보 -->
                        <input type="hidden" name="LGD_AMOUNT" id="LGD_AMOUNT" value="75000"> <!-- 결제금액 -->
                        <input type="hidden" name="LGD_TIMESTAMP" id="LGD_TIMESTAMP" value="20140808184832"> <!-- 타임스탬프 -->
                        <input type="hidden" name="LGD_PAYKEY" id="LGD_PAYKEY"> <!-- LG유플러스 PAYKEY(인증후 자동셋팅)-->
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<content tag="local-script">
    <script>
        $('.btn-primary').click(function(){
            $('#myListForm').submit();
        });
    </script>
</content>
</body>
</html>
