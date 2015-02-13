<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>

<html>
<head>
    <title></title>
    <style>
        section.test {
            padding: 150px 0 170px;
            background: #5f5f5f;
            color: #fdfdfd;
            position:relative;
        }
        section h2.slogan {
            color: #fff;
            font-size: 36px;
            font-weight: 900;
        }
        section h3.slogan {
            color: #fdfdfd;
            font-size: 24px;
            font-weight: 500;
            text-align: left;
        }
    </style>
</head>
<body>

<section class="test">
    <div class="col-sm-offset-1">
        <h3 class="slogan"> 작성완료 Payment 처리 Test </h3>
        <hr/>
    </div>
    <br>
    <div class="col-sm-offset-1 col-sm-5">
        <div class="panel panel-default">
            <div class="panel-heading"> 지원번호 기준 작성완료 처리</div>
            <div class="panel-body">
                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-6">
                        <form class="form-horizontal" id="PAY_DO" role="form" method="post">
                            <div class="input-group">
                                <span class="input-group-addon"> APPL_NO : </span>
                                <input id="applNo" name="applNo" class="col-sm-2 form-control" type="text" value=""/>
                            </div>
                        </form>
                    </div>
                    <div class="col-sm-offset-1 col-sm-1">
                        <button id="do_action" class="btn btn-primary"> 처리하기 </button>
                    </div>
                </div>
            </div>
        </div>

        <div>
            <button class="btn btn-primary btn-lg btn-block" id="processPayment">결제하기</button>
        </div>

    </div>



</section>

<content tag="local-script">
    <script>
        $(document).ready( function() {

            $('#do_action').click( function() {

                var msg = 'appl_no \'' + document.getElementById('applNo').value + '\' 처리합니다.';
                if ( confirm(msg) ) {
                    var form = document.getElementById('PAY_DO');
                    form.action = "${contextPath}/test/payment/test2";
                    form.submit();
                }
            });

            $('#processPayment').click( function() {

                var btn = document.getElementById('processPayment');
                alert(btn.innerHTML);

                btn.innerHTML = '결제 처리중... 기다려 주시기 바랍니다';
                delay(3000);
                btn.innerHTML = '결재하기2';
            });

        });

        function delay(gap){ /* gap is in millisecs */
            var then,now;
            then=new Date().getTime();
            now=then;
            while((now-then)<gap){
                now=new Date().getTime();  // 현재시간을 읽어 함수를 불러들인 시간과의 차를 이용하여 처리
            }
        }

    </script>
</content>

</body>
</html>
