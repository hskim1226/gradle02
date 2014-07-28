<%--
  Created by IntelliJ IDEA.
  User: hanmomhanda
  Date: 14. 7. 28
  Time: 오후 12:09
  To change this template use File | Settings | File Templates.
--%>
<%--@ page contentType="text/html;charset=UTF-8" language="java" --%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<html>
<head>
    <title></title>
</head>
<body>
<form id='recaptcha-form' method="post">
    <%
        ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LdClPcSAAAAAHWT6nvlZ7V_gLbRdjyJ5SdAZguu", "6LdClPcSAAAAAI4sVOUxiIEVUXUNJ0f7GcfLbK3A", false);
        out.print(c.createRecaptchaHtml(null, null));
    %>
</form>
<button id="recaptcha-button"> Verify 확인 </button>
<div id="result"></div>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" >
    $(document).ready(function(){
        $("#recaptcha-button").on("click", function(){
            var rData = $("#recaptcha-form").serialize();
            $.post("${contextPath}/recaptcha/verify",
                    rData,
                    function(data){
                        console.log(data);
                        $('#result').text(data);
                    });
        });
    });
</script>
</body>
</html>
