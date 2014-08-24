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


    </style>
</head>
<body>
<section class="application-selfintro" id="application-create">
    <div class="container" id="container">
        <h2 class="slogan">Calendar Test</h2>

        <div class="spacer-small"></div>

        <div class="col-sm-12 start-date-container">
            <div class="input-group date">
                <span class="input-group-addon">입학일</span>
                <input type="text" class="form-control" name="entrDay" id="entrDay1"/>
            </div>
        </div>

        <div class="col-sm-12 end-date-container">
            <div class="input-group date">
                <span class="input-group-addon">졸업일</span>
                <input type="text" class="form-control" name="grdaDay" id="grdaDay1"/>
            </div>
        </div>

        <div class="col-sm-12" id="org">
            <div class="input-group date" id="dp3">
                <span class="input-group-addon">날짜</span>
                <input type="text" class="form-control" name="vDate" id="vDate"/>
            </div>
        </div>

        <div class="col-md-12">
            <div id="addCalendar" class="btn btn-primary btn-block">달력복사</div>
        </div>
    </div>
</section>
<content tag="local-script">
    <script src="${contextPath}/js/bootstrap-datepicker.js"></script>
    <script src="${contextPath}/js/bootstrap-datepicker.kr.js"></script>
    <script>
        $(document).ready( function() {
            var addCalender = function() {
                var clonedElement = $('#org').clone(true);
                clonedElement.attr('id', 'cloned');
                $('#container').append(clonedElement);
            };

            $('#addCalendar').on('click', addCalender);

            <%-- 달력 시작 --%>
            $('.input-group.date').datepicker({
                format: "yyyymmdd",
                startView: 2,
                language: "kr",
                forceParse: false,
                autoclose: true
            });

            $('.input-append.date').datepicker({
                format: "yyyymmdd",
                startView: 2,
                language: "kr",
                forceParse: false,
                autoclose: true
            });
            <%-- 달력 끝 --%>
        });
    </script>
</content>
</body>
</html>
