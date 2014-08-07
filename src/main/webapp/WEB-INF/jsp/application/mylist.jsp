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

                        <input type="hidden" name="CST_PLATFORM"                id="CST_PLATFORM"		value="test">                   <!-- 테스트, 서비스 구분 -->
                        <input type="hidden" name="CST_MID"                     id="CST_MID"			value="apex2739">                        <!-- 상점아이디 -->
                        <input type="hidden" name="LGD_MID"                     id="LGD_MID"			value="tapex2739">                        <!-- 상점아이디 -->
                        <input type="hidden" name="LGD_OID"                     id="LGD_OID"			value="2014080700001">                        <!-- 주문번호 -->
                        <input type="hidden" name="LGD_BUYER"                   id="LGD_BUYER"			value="오명운">                      <!-- 구매자 -->
                        <input type="hidden" name="LGD_PRODUCTINFO"             id="LGD_PRODUCTINFO"	value="연세대학교 일반대학원 원서 접수">                <!-- 상품정보 -->
                        <input type="hidden" name="LGD_AMOUNT"                  id="LGD_AMOUNT"			value="75000">                     <!-- 결제금액 -->
                        <input type="hidden" name="LGD_BUYEREMAIL"              id="LGD_BUYEREMAIL"		value="hanmomhanda@naver.com">                 <!-- 구매자 이메일 -->
                        <input type="hidden" name="LGD_CUSTOM_SKIN"             id="LGD_CUSTOM_SKIN" 	value="red">                <!-- 결제창 SKIN -->
                        <input type="hidden" name="LGD_WINDOW_VER"              id="LGD_WINDOW_VER" 	value="2.5">                 <!-- 결제창 버젼정보 -->
                        <input type="hidden" name="LGD_CUSTOM_PROCESSTYPE"      id="LGD_CUSTOM_PROCESSTYPE"		value="TWOTR">         <!-- 트랜잭션 처리방식 -->
                        <input type="hidden" name="LGD_TIMESTAMP"               id="LGD_TIMESTAMP"		value="20140808184832">                  <!-- 타임스탬프 -->
                        <input type="hidden" name="LGD_HASHDATA"                id="LGD_HASHDATA"		value="384aa17e60cacd90fd7fb14ba0fee337">                   <!-- MD5 해쉬암호값 -->
                        <input type="hidden" name="LGD_PAYKEY"                  id="LGD_PAYKEY">   							   <!-- LG유플러스 PAYKEY(인증후 자동셋팅)-->
                        <input type="hidden" name="LGD_VERSION"         		id="LGD_VERSION"		value="JSP_XPay_2.5">
                        <input type="hidden" name="LGD_BUYERIP"                 id="LGD_BUYERIP"		value="192.168.0.9">           			<!-- 구매자IP -->
                        <input type="hidden" name="LGD_BUYERID"                 id="LGD_BUYERID"		value="hanmomhanda">           			<!-- 구매자ID -->


                        <!-- 가상계좌(무통장) 결제연동을 하시는 경우  할당/입금 결과를 통보받기 위해 반드시 LGD_CASNOTEURL 정보를 LG 유플러스에 전송해야 합니다 . -->
                        <input type="hidden" name="LGD_CASNOTEURL"          id="LGD_CASNOTEURL"		value="cas_noteurl.jsp">                 <!-- 가상계좌 NOTEURL -->

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
