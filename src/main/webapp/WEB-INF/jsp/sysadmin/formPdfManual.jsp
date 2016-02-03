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
        <form id="PDF_DO" name="user" cssClass="form-horizontal" role="form" method="post">
            <div class="col-md-offset-2 col-md-8">
                <div class="inner-container-white">
                    <div class="col-sm-12 text-gray">
                        <i class="fa fa-check-square-o fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b>결제 완료 건 PDF 처리</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="col-sm-12 text-gray">
                        <span style="font-size: 25px; vertical-align: middle; line-height:70px;"><b> 원서 및 수험표 재생성 및 업로드 </b></span>
                    </div>
                    <div class="form-group required col-sm-12">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-3 text-gray">
                                <label for="applNo" class="control-label"> APPL_NO : </label>
                            </div>
                            <div class="col-sm-9">
                                <input type="text" id="applNo" name="applNo" class="form-control input-text" />
                            </div>
                        </div>
                    </div>
                    <div class="spacer-tiny">&nbsp;</div>
                    <div class="form-group col-sm-12">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-12">
                                <button class="btn btn-primary btn-lg btn-block btn-save input-text" id="processPDF"> PDF 재생성 및 업로드 </button>
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

            $('#processPDF').click( function(e) {
                e.preventDefault();

                var form = document.getElementById('PDF_DO');
                form.action = "${contextPath}/sysadmin/pdf-manual";
                form.submit();
            });

        });

    </script>
</content>

</body>
</html>
