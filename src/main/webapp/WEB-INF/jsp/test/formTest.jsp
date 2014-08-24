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
            <div class="col-md-offset-0 col-md-11" id="innerContainer">
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
                <div id="acadUnit1">
                    <hr/>
                    <input type="text" id="t1" name="t1"/>
                    <select id="s1" name="s1">
                        <option name="r1" value="0">라디오0</option>
                        <option name="r1" value="1">라디오1</option>
                        <option name="r1" value="2">라디오2</option>
                    </select>
                    <div class="btn btn-primary btn-block addUnit">유닛추가</div>
                </div>
                <div id="acadUnit2">
                    <hr/>
                    <input type="text" id="t2" name="t1"/>
                    <select id="s2" name="s1">
                        <option name="r1" value="0">라디오0</option>
                        <option name="r1" value="1">라디오1</option>
                        <option name="r1" value="2">라디오2</option>
                    </select>
                    <div class="btn btn-primary btn-block addUnit">유닛추가</div>
                </div>
            </div>
        </div>
    </div>
</section>
<content tag="local-script">
    <script>
        $(document).ready( function() {
            var addUnit = function (e) {
                var ele, clo, parent;
                ele = e.target.parentNode;
//console.log(ele);
                clo = ele.cloneNode(true);

                renameChildren(clo);
//console.log(clo);
                clo.addEventListener('click', addUnit);
                parent = document.getElementById('innerContainer');


//console.log(parent);
                parent.appendChild(clo);
                console.log($('.addUnit'));
            }

            var renameChildren = function (o) {
                var children = o.childNodes, l, t0;

                if ( children ) {
                    for( l = 0 ; l < children.length ; l++ ) {
                        t0 = children[l];

                    }
                    return;
                }
            };

            $('.addUnit').on('click', addUnit);
        });
    </script>
</content>
</body>
</html>
