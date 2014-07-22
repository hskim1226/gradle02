<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: skplanet
  Date: 2014. 7. 20.
  Time: 오후 6:05
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>INDEX</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>YS Project <small>template pages</small></h1>
    </div>


    <div class="panel panel-default">
        <div class="panel-heading">
            <h2 class="panel-title">user management</h2>
        </div>
    </div>
    <sec:authorize access="hasRole('ROLE_USER')">
        [ logined as <sec:authentication property="principal.username" />]
        <a href="${contextPath}/j_spring_security_logout.do">sign out</a>
    </sec:authorize>


</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>
</html>