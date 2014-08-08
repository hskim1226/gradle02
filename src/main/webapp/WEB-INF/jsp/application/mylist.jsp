<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
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
                                <td><a href="${contextPath}/application/create">2015학년도 연세대학교 일반대학원 일반 전형</a></td>
                                <td>2014-10-08</td>
                                <td><button type="button" class="btn btn-info">수정하기</button></td>
                                <td><button type="button" class="btn btn-primary" id="p1">결제하기</button></td>
                            </tr>
                            <tr>
                                <td>의학</td>
                                <td><a href="${contextPath}/application/create">2015학년도 연세대학교 일반대학원 외국인 전형</a></td>
                                <td>2014-10-08</td>
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
        /*
         * 상점결제 인증요청후 PAYKEY를 받아서 최종결제 요청.
         */
        function doPay_ActiveX(){

            ret = xpay_check(document.getElementById('LGD_PAYINFO'), '<%= CST_PLATFORM %>');

            if (ret=="00"){     //ActiveX 로딩 성공
                var LGD_RESPCODE        = dpop.getData('LGD_RESPCODE');       //결과코드
                var LGD_RESPMSG         = dpop.getData('LGD_RESPMSG');        //결과메세지

                if( "0000" == LGD_RESPCODE ) { //인증성공
                    var LGD_PAYKEY      = dpop.getData('LGD_PAYKEY');         //LG유플러스 인증KEY
                    var msg = "인증결과 : " + LGD_RESPMSG + "\n";
                    msg += "LGD_PAYKEY : " + LGD_PAYKEY +"\n\n";
                    document.getElementById('LGD_PAYKEY').value = LGD_PAYKEY;
                    alert(msg);
                    document.getElementById('LGD_PAYINFO').submit();
                } else { //인증실패
                    alert("인증이 실패하였습니다. " + LGD_RESPMSG);
                    /*
                     * 인증실패 화면 처리
                     */
                }
            } else {
                alert("LG U+ 전자결제를 위한 ActiveX Control이  설치되지 않았습니다.");
                /*
                 * 인증실패 화면 처리
                 */
            }
        }

        function isActiveXOK(){
            if(lgdacom_atx_flag == true){
                document.getElementById('LGD_BUTTON1').style.display='none';
                document.getElementById('LGD_BUTTON2').style.display='';
            }else{
                document.getElementById('LGD_BUTTON1').style.display='';
                document.getElementById('LGD_BUTTON2').style.display='none';
            }
        }

        $('.btn-primary').click(function(){
            $('#myListForm').submit();
            doPay_ActiveX();
        });
    </script>
</content>
</body>
</html>
