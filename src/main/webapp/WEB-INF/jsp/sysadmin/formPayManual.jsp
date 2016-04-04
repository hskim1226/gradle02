<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>

<html>
<head>
    <title></title>
    <style>
        .input-text {
            height: 35px;
            font-size: 100%;
            opacity: 1.0;
            margin-bottom: 5%;
        }
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <form id="PAY_DO" name="user" cssClass="form-horizontal" role="form" method="post">
            <div class="col-md-offset-2 col-md-8">
                <div class="inner-container-white">
                    <div class="col-sm-12 text-gray">
                        <i class="fa fa-check-square-o fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b> 결제 처리</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="col-sm-12 text-gray">
                        <span style="font-size: 25px; vertical-align: middle; line-height:70px;"><b> 지원번호 기준 결제완료 처리</b></span>
                    </div>
                    <div class="form-group required col-sm-12">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-3 text-gray">
                                <label for="ApplNo" class="control-label"> APPL_NO : </label>
                            </div>
                            <div class="col-sm-9">
                                <input type="text" id="applNo" name="ApplNo" class="form-control input-text" />
                            </div>
                            <div class="col-sm-3 text-gray">
                                <label for="payAmt" class="control-label"> 결제금액 : </label>
                            </div>
                            <div class="col-sm-9">
                                <input type="text" id="payAmt" name="payAmt" class="form-control input-text" />
                            </div>
                            <div class="col-sm-3 text-gray">
                                <label for="HndrName" class="control-label"> 설명 / 처리자 </label>
                            </div>
                            <div class="col-sm-9">
                                <input type="text" id="HndrName" name="HndrName" class="form-control input-text" />
                            </div>
                            <div class="col-sm-3 text-gray">
                                <label for="payTypeCode" class="control-label"> 수작업 사유 : </label>
                            </div>
                            <div class="col-sm-9">
                                <select id="payTypeCode" name="payTypeCode" class="form-control base-info">
                                    <option value="">--선택--</option>
                                    <option value="PAY001">계좌 입금 (국내)</option>
                                    <option value="PAY002">계좌 입금 (해외)</option>
                                    <option value="PAY003">Paypal 결제</option>
                                    <option value="PAY004">가상계좌 오류 처리</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="spacer-tiny">&nbsp;</div>
                    <div class="form-group col-sm-12">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-12">
                                <button class="btn btn-primary btn-lg btn-block btn-save input-text" id="processPayment"> 결제 처리하기 </button>
                            </div>
                        </div>
                    </div>
                    <div class="spacer-tiny">&nbsp;</div>
                </div>
            </div>
        </form>
    </div>
</section>

<content tag="local-script">
    <script type="text/javascript">
        $(document).ready( function() {

            $('#processPayment').click( function(e) {
                e.preventDefault();

                var form = document.getElementById('PAY_DO');
                form.action = "${contextPath}/sysadmin/pay-manual";
                form.submit();
            });

        });

    </script>
</content>

</body>
</html>
