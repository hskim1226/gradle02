<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" >
        $(document).ready(function(){
             $("#sign-up-button").on("click", function(){
                 $.post("${contextPath}/user/signup",
                         $("#sign-up-form").serialize(),
                         function(data){
                            alert(data.result);
                         });
             });
        });
    </script>
</head>
<body>
    <h1> sign up </h1>
    <div>
        <form id="sign-up-form" action="${contextPath}/user/signup" method="post">
            <input type="text" name="username" />
            <input type="password" name="password" />
        </form>
        <button id="sign-up-button"> sign up </button>
    </div>
</body>
</html>