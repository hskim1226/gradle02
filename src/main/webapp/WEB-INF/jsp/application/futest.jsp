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
            <input type="file" multiple/>
            <input type="file" multiple/>
            <input type="file" multiple/>
            <input type="file" multiple/>
            <input type="file" multiple/>
        </form>
    </div>
    <div>
        <button id="saveandupload" class="btn btn-lg btn-primary">파일업로드</button>
    </div>
</section>

<content tag="local-script">
    <script type="text/javascript">
        $(document).ready(function() {
            <%-- TODO 파일업로드용 버튼 --%>
            $('#saveandupload').on('click', function() {
                var ea = document.getElementById('entireApplication'),
                    actionUrl = "${contextPath}/application/apply/saveandupload";
                ea.setAttribute("enctype", "multipart/form-data");
                ea.setAttribute("action", actionUrl);

//                console.dir(ea);
//                ea.submit();
//                $.ajax({
//                    url: actionUrl,
//                    type: 'POST',
//                    data: formData3,
//                    timeout: 5000,
//                    success: function (context) {
//                        alert("done");
//                    },
//                    error: function(e) {
//                    }
//                });
                ea.submit();
            });

            $('#entireApplication').submit(function() {
                // inside event callbacks 'this' is the DOM element so we first
                // wrap it in a jQuery object and then invoke ajaxSubmit
                $(this).ajaxSubmit({
                    success: function (context) {
                        alert("done");
                    },
                    error: function(e) {
                        alert(e);
                    }
                });

                // !!! Important !!!
                // always return false to prevent standard browser submit and page navigation
                return false;
            });

//            $('#entireApplication').ajaxForm({
//                success: function (context) {
//                    alert("done");
//                },
//                error: function(e) {
//                    alert(e);
//                }
//            });

//            $('#entireApplication').ajaxSubmit({
//                success: function (context) {
//                    alert("done");
//                },
//                error: function(e) {
//                    alert(e);
//                }
//            });
        });

    </script>
</content>
</body>
</html>
