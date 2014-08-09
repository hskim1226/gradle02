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
                                <td><button type="button" class="btn btn-primary" id="p2" value="60000">결제하기</button></td>
                            </tr>
                            </tbody>
                        </table>


                        <input type="hidden" name="LGD_PRODUCTINFO" id="LGD_PRODUCTINFO"> <!-- 상품정보 -->
                        <input type="hidden" name="LGD_AMOUNT" id="LGD_AMOUNT"> <!-- 결제금액 -->
                        <input type="hidden" name="LGD_TIMESTAMP" id="LGD_TIMESTAMP"> <!-- 타임스탬프 -->
                        <input type="hidden" name="LGD_PAYKEY" id="LGD_PAYKEY"> <!-- LG유플러스 PAYKEY(인증후 자동셋팅)-->
                    </form>
                </div>
                <div id="LGD_ACTIVEX_DIV"/> <!-- ActiveX 설치 안내 Layer 입니다. 수정하지 마세요. -->
                <div id="xpayLoad"></div>
            </div>
        </div>
    </div>
</section>
<content tag="local-script">
    <script language="javascript" src="http://xpay.uplus.co.kr:7080/xpay/js/xpay_utf-8.js" type="text/javascript"></script>
    <script>
    $(document).ready( function() {
        /*
         * 상점결제 인증요청후 PAYKEY를 받아서 최종결제 요청.
         */
        function doPay_ActiveX(){

            ret = xpay_check(document.getElementById('LGD_PAYINFO'), 'test');

            if (ret=="00"){     //ActiveX 로딩 성공
                var LGD_RESPCODE        = dpop.getData('LGD_RESPCODE');       //결과코드
                var LGD_RESPMSG         = dpop.getData('LGD_RESPMSG');        //결과메세지
alert('LGD_RESPCODE : ' + LGD_RESPCODE + '\n' + 'LGD_RESPMSG : ' + LGD_RESPMSG);
                if( "0000" == LGD_RESPCODE ) { //인증성공
                    var LGD_PAYKEY      = dpop.getData('LGD_PAYKEY');         //LG유플러스 인증KEY
                    var msg = "인증결과 : " + LGD_RESPMSG + "\n";
                    msg += "LGD_PAYKEY : " + LGD_PAYKEY +"\n\n";
                    document.getElementById('LGD_PAYKEY').value = LGD_PAYKEY;
                    alert(msg);
//                    document.getElementById('LGD_PAYINFO').submit();
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
            if(lgdacom_atx_flag != true){
                $('#xpayLoad').text("전자결제 모듈이 로딩되지 않았습니다.");
            }
        }

        function add0(d, l) {
            var r = '', t0 = d.toString().length;
            if ( l > t0 ) {
                while( l-- > t0 ) {
                    r += '0';
                }
                return r + d;
            }
            else return d;
        }

        function dateToFormat(d, f) {
            if ( d instanceof Date ) {
                return f.replace(/(yyyy|MM|dd|hh|mm|ss)/gi, function(t) {
                    switch (t) {
                        case 'yyyy' : return d.getFullYear();
                        case 'MM'   : return add0(d.getMonth() + 1, 2);
                        case 'dd'   : return add0(d.getDate(), 2);
                        case 'hh'   : return add0(d.getHours(), 2);
                        case 'mm'   : return add0(d.getMinutes(), 2);
                        case 'ss'   : return add0(d.getSeconds(), 2);
                        default     : return t;
                    }
                });
            }
        }

        $('.btn-primary').click(function(){
            document.getElementById('LGD_PRODUCTINFO').value = $(this)[0].id;
            document.getElementById('LGD_AMOUNT').value = $(this)[0].value;
            document.getElementById('LGD_TIMESTAMP').value = dateToFormat(new Date(), 'yyyyMMddhhmmss');
//alert( 'LGD_PRODUCTINFO : ' + document.getElementById('LGD_PRODUCTINFO').value + '\n' +
//        'LGD_AMOUNT : ' + document.getElementById('LGD_AMOUNT').value + '\n' +
//        'LGD_TIMESTAMP : ' + document.getElementById('LGD_TIMESTAMP').value );
//            doPay_ActiveX();
            $('#LGD_PAYINFO').submit();
        });

        isActiveXOK();
    })
    </script>
</content>
</body>
</html>
