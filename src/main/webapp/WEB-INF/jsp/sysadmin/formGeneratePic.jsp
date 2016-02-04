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
        .instruction {
            padding-left: 3em;
            font-size: 1.2em;
            font-weight: bold;
            text-align: left;
            line-height: 1.8em;
        }
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <form id="sysadminForm" name="user" cssClass="form-horizontal" role="form" method="post">
            <div class="col-md-offset-1 col-md-10">
                <div class="inner-container-white">
                    <div class="col-sm-12 text-gray">
                        <i class="fa fa-list fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b> 학번 사진 생성</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="col-sm-12 text-gray instruction">
                        1. 학교에서 받은 학번 엑셀 데이터 컬럼에 맞게 STUD_NO 테이블을 생성하고<br/>
                        2. SysAdminMapper.xml의 selectStudentPicInfo 쿼리를 컬럼에 맞게 수정하고<br/>
                        3. TOAD > Tools > Import > Import Wizard 로 엑셀 데이터를 STUD_NO에 insert 한 후<br/>
                        4. 아래의 버튼 클릭<br/>
                    </div>
                    <div class="spacer-tiny">&nbsp;</div>
                    <div class="form-group col-sm-12">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="col-sm-12">
                                <button class="btn btn-primary btn-lg btn-block btn-save input-text" id="download-picture"> 업로드 및 사진 파일 다운로드 </button>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-12 text-gray instruction">
                        서버 로컬의 ${picturePath} 에 '학번-이름.확장자'의 형식으로 다운로드 됩니다.
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

            $('#download-picture').click( function(e) {
                e.preventDefault();
                var form = document.getElementById('sysadminForm');
                form.action = "${contextPath}/sysadmin/rslt-generate-pic";
                form.submit();
            });

        });

    </script>
</content>

</body>
</html>
