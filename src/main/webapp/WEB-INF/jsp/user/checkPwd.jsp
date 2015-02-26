<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang='ko'>
<head>
    <title>비밀번호 확인</title>
    <style>
        .input-text {
            height: 50px;
            font-size: 100%;
            opacity: 1.0;
            margin-bottom: 5%;
        }
    </style>
</head>
    <body>
    <section class="normal-white">
        <div class="container">
            <form id="users" name="users" cssClass="form-horizontal" role="form" method="post">
                <div class="col-md-offset-2 col-md-8">
                    <div class="inner-container-white">
                        <div class="col-sm-12 text-gray">
                            <i class="fa fa-unlock fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b>비밀 번호 확인</b></span>
                        </div>
                        <div class="spacer-small">&nbsp;</div>
                        <div class="form-group required col-sm-12">
                            <div class="col-sm-offset-1 col-sm-10">
                                <div class="col-sm-3 text-gray">
                                    <label for="pswd" class="control-label">비밀 번호</label>
                                </div>
                                <div class="col-sm-9">
                                    <input type="password" id="pswd" name="pswd" class="form-control input-text" placeholder="비밀 번호를 입력해 주세요." />
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-sm-12">
                            <div class="col-sm-offset-1 col-sm-10">
                                <div class="col-sm-12">
                                    <button class="btn btn-primary btn-lg btn-block btn-save input-text">내 정보 확인</button>
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
    $(document).ready(function() {

        <%-- 하단 버튼 처리 --%>
        var formProcess = function(event) {
            event.preventDefault();
            var inputValue = document.getElementById('pswd').value,
                form = document.getElementById('users');

            if (inputValue.length > 0) {
                form.action = "${contextPath}/user/view";
                form.submit();
            } else {
                alert('비밀 번호를 입력해 주세요.')
            }
        };
        $('.btn-save').on('click', formProcess);
        <%-- 하단 버튼 처리 --%>

        <%-- action 성공 여부 알림 처리 --%>
        var showActionResult = function() {
            var msg = '${resultMsg}';
            if (msg.length > 0) {
                confirm(msg);
            }
        };
        showActionResult();
        <%-- action 성공 여부 알림 처리 --%>

        $('#pswd').focus();

        <%-- placeholder polyfill --%>
        $('input, textarea').placeholder();
        <%-- placeholder polyfill --%>
    });
    </script>
    </content>
</body>
</html>