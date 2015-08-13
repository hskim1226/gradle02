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
                        <i class="fa fa-list fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b> 학번 사진 생성</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="col-sm-12 text-gray">
                        <span style="font-size: 25px; vertical-align: middle; line-height:70px;"><b> 학번 엑셀 파일 업로드 </b></span>
                    </div>
                    <div class="col-sm-12 text-gray">
                        <span style="font-size: 18px; vertical-align: middle; line-height:70px;"><b> 학번 엑셀 파일 데이터를 STUD_NO 테이블에 INSERT 한 후 아래의 버튼을 눌러주세요.</b></span>
                    </div>
                    <div class="spacer-tiny">&nbsp;</div>
                    <div class="form-group col-sm-12">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-12">
                                <button class="btn btn-primary btn-lg btn-block btn-save input-text" id="showMyList"> 업로드 및 사진 파일 다운로드 </button>
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
