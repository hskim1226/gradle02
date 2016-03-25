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
        <form id="sysadminForm" name="user" cssClass="form-horizontal" role="form" method="post">
            <div class="col-md-offset-2 col-md-8">
                <div class="inner-container-white">
                    <div class="col-sm-12 text-gray">
                        <i class="fa fa-list fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b> 추천 요청을 받은 추천자 전체에게 추천 처리 독려 메일 발송 </b></span>
                    </div>
                    <div class="spacer-tiny">&nbsp;</div>
                    <div class="form-group col-sm-12">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-12">
                                <button class="btn btn-primary btn-lg btn-block btn-save input-text" id="sendMailToAll"> 전체 메일 발송 </button>
                            </div>
                        </div>
                    </div>
                    <div class="spacer-tiny">&nbsp;</div>
                </div>
            </div>
            <input type="hidden" name="sendkey" value="${sendkey}"/>
        </form>
    </div>
</section>

<content tag="local-script">
    <script type="text/javascript">
        $(document).ready( function() {

            $('#sendMailToAll').click( function(e) {
                e.preventDefault();
                var form = document.getElementById('sysadminForm');
                form.action = "${contextPath}/sysadmin/sendUrgeRecommendationMailToAll"
                form.submit();
            });

        });

    </script>
</content>

</body>
</html>
