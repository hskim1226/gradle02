<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<%--// TODO 제3자 동의여부 : ${providePrivateInfo} - 0 : 동의, 1 : 비동의--%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${contextPath}/css/datepicker3.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
    <style>
        section.application {
            padding: 200px 0 60px;
            background: #555555;
            color: #000;
            position:relative;
        }

        section.application h2.slogan {
            color: #000;
            font-size: 36px;
            font-weight: 900;
        }

        section.application .spacer-big {
            margin-bottom: 7em;
        }

        section.application .spacer-mid {
            margin-bottom: 5em;
        }

        section.application .spacer-small {
            height: 3em;
        }

        section.application .spacer-tiny {
            height: 1em;
        }

        section.application .tab-content {
            background-color: #d0d0d0;
            color: #000;
        }

        section.application .nav>li>a {
            display: block;
        }

    </style>
    <%--body의 글자 속성을 #333333으로 강제 지정하여 Footer 글자가 안나옴, 꼭 필요하지 않으면 안쓰기로
    <link rel="stylesheet" href="${contextPath}/css/bootstrap-glyphicons.css" />--%>
</head>
<body>
<section class="application">
    <div>
        <form id="entireApplication" method="post">
            <input type="text" id='txt1' name="txt1"/>
            <input type="file" id='file1' name="file1"/><button id="btnFU1" class="btnFU" data-input-file-id="file1">업로드</button>
            <input type="file" id='file2' name="file2"/><button id="btnFU2" class="btnFU" data-input-file-id="file2">업로드</button>
            <input type="file" id='file3' name="file3"/><button id="btnFU3" class="btnFU" data-input-file-id="file3">업로드</button>
            <input type="file" id='file4' name="file4"/><button id="btnFU4" class="btnFU" data-input-file-id="file4">업로드</button>
            <input type="file" id='file5' name="file5"/><button id="btnFU5" class="btnFU" data-input-file-id="file5">업로드</button>
        </form>
    </div>
    <div>
        <button id="pureAjaxUpload" class="btn btn-lg btn-primary">AJAX 파일업로드</button>
        <button id="ajaxFileUpload" class="btn btn-lg btn-danger">ajaxfileupload 파일업로드</button>
        <button id="plainUpload" class="btn btn-lg btn-warning">일반 파일업로드</button>
    </div>
</section>

<content tag="local-script">
    <script type="text/javascript">
        $(document).ready(function() {
            <%-- TODO 파일업로드용 버튼 --%>
            $('#pureAjaxUpload').on('click', function() {
                var ea = document.getElementById('entireApplication'),
                    actionUrl = "${contextPath}/application/apply/savetest",
                    formData = $(ea).serialize();
                ea.setAttribute("enctype", "multipart/form-data");
                ea.setAttribute("action", actionUrl);

                $.ajax({
                    url: actionUrl,
                    type: 'POST',
                    data: formData,
                    contentType: 'multipart/form-data',
                    timeout: 5000,
                    success: function (data) {
                        alert(data);
                    },
                    error: function(e) {
                        alert("error");
                    }
                });
            });

            $('.btnFU').on('click', function() {
                var ea = document.getElementById('entireApplication'),
                    actionUrl = "${contextPath}/application/apply/savetest",
                    fileInputId = $(this).attr("data-input-file-id");
//                ea.setAttribute("enctype", "multipart/form-data");
//                ea.setAttribute("action", actionUrl);
                $.ajaxFileUpload({
                    url: actionUrl,
                    secureuri:false,
                    fileElementId:fileInputId,
                    dataType: 'json',
                    success: function (data, status) {
                        console.log("msg : ", data.message);
                        console.log("data : ", data.data);
                        console.log("status : ", status);

//                        if(typeof(data.error) != 'undefined')
//                        {
//                            if(data.error != '')
//                            {
//                                alert(data.error);
//                            }else
//                            {
//                                alert(data.msg);
//                            }
//                        }
                    },
                    error: function (data, status, e) {
                        alert(e);
                    }
                });

                return false;
            });



            $('#plainUpload').on('click', function() {
                var ea = document.getElementById('entireApplication');
                ea.setAttribute("action", "${contextPath}/application/apply/savetest");
                ea.setAttribute("enctype", "multipart/form-data");
                ea.submit();
            });
        });

    </script>
</content>
</body>
</html>
