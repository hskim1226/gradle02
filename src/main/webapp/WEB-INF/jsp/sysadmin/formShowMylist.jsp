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
                        <i class="fa fa-list fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b> 사용자 '내 원서' 보기</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="col-sm-12 text-gray">
                        <span style="font-size: 25px; vertical-align: middle; line-height:70px;"><b> ID로 사용자 내 원서 조회 </b></span>
                    </div>
                    <div class="form-group required col-sm-12">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-3 text-gray">
                                <label for="userId" class="control-label"> USER_ID : </label>
                            </div>
                            <div class="col-sm-9">
                                <input type="text" id="userId" name="userId" class="form-control input-text" />
                            </div>
                        </div>
                    </div>
                    <div class="spacer-tiny">&nbsp;</div>
                    <div class="form-group col-sm-12">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-12">
                                <button class="btn btn-primary btn-lg btn-block btn-save input-text" id="showMyList"> 내 원서 보기 </button>
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

            $('#userId').focus();

            $('body').on('keyup', function (e) {
                if (e.keyCode == 13)
                $('#showMyList').click();
            });

            $('#showMyList').click( function(e) {
                e.preventDefault();
                var userId = document.getElementById('userId').value;
                var form = document.getElementById('sysadminForm');
                form.action = "${contextPath}/sysadmin/showlist/" + userId;
                form.submit();
            });

        });

    </script>
</content>

</body>
</html>
