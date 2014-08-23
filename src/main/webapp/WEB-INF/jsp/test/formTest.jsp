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
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-md-offset-0 col-md-11" id="buttonContainer">
                <h2 class="slogan">Form Test</h2>

                <div class="spacer-small"></div>

                <hr/>
                <%--<c:forEach items="${campusList}" var="campus">--%>
                    <%--${campus.code} : ${campus.name}<br/>--%>
                <%--</c:forEach>--%>

                <%--<form:form commandName="campus">--%>
                    <%--<form:select path="name" items="${campusMap}"/>--%>
                        <%--&lt;%&ndash;<form:option value="**" label="---Choose---"/>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<form:options items="${campusMap}"/>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</form:select>&ndash;%&gt;--%>
                <%--</form:form>--%>

                <%--<form:form commandName="campus">--%>
                    <%--<form:select path="name">--%>
                        <%--<form:option value="**" label="---Choose---"/>--%>
                        <%--<form:options items="${campusList}" itemValue="${campus.code}" itemLabel="${campus.name}"/>--%>
                    <%--</form:select>--%>
                <%--</form:form>--%>
                <div class="abc btn btn-danger">버튼1</div>
                <div class="abc btn btn-danger">버튼2</div>
                <div class="abc btn btn-danger">버튼3</div>
            </div>
        </div>
    </div>
</section>
<content tag="local-script">
    <script>
        $(document).ready(function() {
            $('.abc').on('click', function(e) {
                console.log(e);
                $('#buttonContainer').append('<div class="abc btn btn-danger" onclick="javascript:alert(\"ddd\")">버튼3</div>');
            });

        });
    </script>
</content>
</body>
</html>
