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
        <h3 class="slogan"> PDF 처리 결과 </h3>
        <hr/>
    </div>
    <br>
    <div class="col-sm-offset-1 col-sm-8">
        <div class="panel panel-default">
            <div class="panel-heading"> 성공일까요? DB를 확인하세요. </div>
        </div>
    </div>
    <div class="spacer-tiny">&nbsp;</div>
    <div class="spacer-tiny">&nbsp;</div>
    <div class="col-sm-offset-1 col-sm-8">
        <div class="col-sm-3">
            <button class="btn btn-primary btn-lg btn-block btn-save input-text" id="back"> PDF 처리 돌아가기 </button>
        </div>
    </div>
</section>

<content tag="local-script">
    <script type="text/javascript">
        $(document).ready( function() {

            $('#back').click( function(e) {
                e.preventDefault();

                location.href = "${contextPath}/sysadmin/form-pdf-manual";
            });

        });

    </script>
</content>


</body>
</html>
