<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <style>
        section.application-selfintro {
            padding: 200px 0 60px;
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

        section.notice-detail h4.slogan {
            color: #ff8d8d;
            font-size: 18px;
            font-weight: 500;
            text-align: left;
        }

        section .slogan {
            font-weight: 900;
        }

        section .big-font {
            color: #fdfdfd;
            font-size: 20px;
            font-weight: 500;
            text-align: left;
        }

        section .mid-font {
            color: #fdfdfd;
            font-size: 18px;
            text-align: left;
        }

        section .small-font {
            color: #fdfdfd;
            font-size: 12px;
            text-align: left;
        }

        section .text-center {
            text-align: center;
        }

        /* 팝업창이 보여질 부분 */
        #popup, #popup2, .bMulti {
            background-color: #fff;
            color: #111;
            display: none;
            min-width: 450px;
            padding: 25px;
        }

        #popup, .bMulti {
            min-height: 250px;
        }
        /* 클릭할 버튼 */
        .button {
            background-color: #2b91af;
            color: #fff;
            cursor: pointer;
            display: inline-block;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
        }
        /* 닫기 버튼 */
        .button.b-close, .button.bClose {
            box-shadow: none;
            font: bold 131% sans-serif;
            padding: 0 6px 2px;
            position: absolute;
            right: -7px;
            top: -7px;
        }
    </style>
</head>
<body>
<section class="application-selfintro" id="application-create">
    <div class="container" id="container">
        <h2 class="slogan">Query String Test</h2>

        <div class="spacer-small"></div>

        <form id="testForm">
            <input type="text" id="p1" name="p1" value="v1" class="qs"/>
            <input type="text" id="p2" name="p2" value="v2" class="qs" readonly/>
            <input type="text" id="p3" name="p3" value="v3" class="qs" readonly/>
            <input type="text" id="p4" name="p4" value="v4" class="qs" readonly/>
        </form>

        <span id='spnbtn' class="btn btn-default btn-lg btn-block">GET</span>

        <div id='divbtn' class="btn btn-info btn-lg btn-block">POST</div>

        <div id="result" class="btn btn-primary btn-block"></div>
    </div>
</section>
<content tag="local-script">
    <script>
        $(document).ready( function() {
            $('#divbtn').on('click', function() {

                console.log($('#testForm').serialize());

                $.ajax({
                    type : 'POST',
                    url : "${contextPath}/test/qstest",
                    data : $('#testForm').serialize(),
                    success : function(data) {
                        $('#result').innerHTML(data);
                    }
                });
            });
            $('#spnbtn').on('click', function() {

                console.log($('#testForm').serialize());

                $.ajax({
                    type : 'GET',
                    url : "${contextPath}/test/qstest",
                    data : $('#testForm').serialize(),
                    success : function(data) {
                        $('#result').innerHTML(data);
                    }
                });
            });

        });
    </script>
</content>
</body>
</html>
