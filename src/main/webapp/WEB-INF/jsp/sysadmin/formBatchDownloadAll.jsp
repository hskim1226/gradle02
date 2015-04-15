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
        <form id="batch-download" name="user" cssClass="form-horizontal" role="form" method="post">
            <div class="col-md-offset-2 col-md-8">
                <div class="inner-container-white">
                    <div class="col-sm-12 text-gray">
                        <i class="fa fa-check-square-o fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b>원서 별 업로드 한 모든 파일 다운로드</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="col-sm-12 text-gray">
                        <span style="font-size: 25px; vertical-align: middle; line-height:70px;"><b> 업로드 한 모든 파일 다운로드 </b></span>
                    </div>
                    <div class="form-group required col-sm-12">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-3 text-gray">
                                <label for="applNoList" class="control-label"> APPL_NO 목록을 줄바꿈으로 구분해서 입력 : </label>
                            </div>
                            <div class="col-sm-9">
                                <textarea id="applNoList" name="applNoList" class="form-control" rows="20">abcde<br/>12345</textarea>
                                <%--<input type="text" id="applNo" name="ApplNo" class="form-control input-text" />--%>
                            </div>
                        </div>
                    </div>
                    <div class="spacer-tiny">&nbsp;</div>
                    <div class="form-group col-sm-12">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-12">
                                <button class="btn btn-primary btn-lg btn-block btn-save input-text" id="downloadAll"> 다운로드 </button>
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

            $('#downloadAll').click( function(e) {
                e.preventDefault();

                var form = document.getElementById('batch-download');
                form.action = "${contextPath}/sysadmin/batch-download-all";
                form.submit();
            });

        });

    </script>
</content>

</body>
</html>
