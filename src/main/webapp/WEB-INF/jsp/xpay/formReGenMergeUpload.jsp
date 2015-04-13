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
        <form id="sysAdminForm" name="user" cssClass="form-horizontal" role="form" method="post">
            <div class="col-md-offset-2 col-md-8">
                <div class="inner-container-white">
                    <div class="col-sm-12 text-gray">
                        <i class="fa fa-check-square-o fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b>결제 성공했으나 최종 파일에 수험번호가 안 찍힌 원서 재생성 및 업로드</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="col-sm-12 text-gray">
                        <span style="font-size: 25px; vertical-align: middle; line-height:70px;"><b> 합친 파일 생성 여부 선택 후 버튼 누르면 실행 </b></span>
                    </div>

                    <div class="col-sm-offset-1 col-sm-10">
                        <div class="col-sm-6 text-gray">
                            <label class="control-label">합친 파일 S3에 있나?</label>
                        </div>
                        <div class="col-sm-3">
                            <label class="text-gray"><input type="radio" id="fileY" name="fileYn" value="Y" checked>있음</label>
                        </div>
                        <div class="col-sm-3">
                            <label class="text-gray"><input type="radio" id="fileN" name="fileYn" value="N">없음</label>
                        </div>
                    </div>

                    <div class="spacer-tiny">&nbsp;</div>
                    <div class="form-group col-sm-12">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-12">
                                <button class="btn btn-primary btn-lg btn-block btn-save input-text" id="reGenMergeUpload"> 수험번호 반영 및 업로드 </button>
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

            $('#reGenMergeUpload').click( function(e) {
                e.preventDefault();

                var form = document.getElementById('sysAdminForm');
                form.action = "${contextPath}/payment/admin/re-generate-merge-upload";
                form.submit();
            });

        });

    </script>
</content>

</body>
</html>
