<%--
  Created by IntelliJ IDEA.
  User: hanmomhanda
  Date: 14. 7. 25
  Time: 오후 4:39
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>메일 작성</title>
</head>
<body>
<h1> 메일 작성 </h1>
<div>
    <form:form id='mail-form' commandName="mail" action="${contextPath}/mail/send"  method="post">
    <!--<form id="mail-form" action="${contextPath}/mail/send" method="post">-->
        Subject 제목<br/><input type="text" name="subject" /><p></p>
        From 발신<br/><input type="text" name="from" /><p></p>
        To 수신<br/><input type="text" name="to" /><p></p>
        Cc 참조<br/><input type="text" name="cc" /><p></p>
        Bc 비밀참조<br/><input type="text" name="bc" /><p></p>
        Contents 내용<br/><textarea name="contents"></textarea>
    <!--</form>-->
    </form:form>
    <div id="notice"></div>
    <button id="mail-button"> Send Mail 메일 보내기 </button>
</div>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" >
    $(document).ready(function(){
        $("#mail-button").on("click", function(){
            $('#notice').text('Mail Sent');
//            alert('button clicked');
            $.post("${contextPath}/mail/send",
                    $("#mail-form").serialize(),
                    function(data){
                        alert(data.result);
                    });
        });
    });
</script>
</body>
</html>
