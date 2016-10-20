<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang='ko'>
<head>
    <title>수험번호 찾기<%-- <spring:message code="L00201"/>아이디 찾기--%></title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
    <style>
        .input-text {
            height: 50px;
            font-size: 100%;
            opacity: 1.0;
            margin-bottom: 5%;
        }
        input[readonly] {
            background-color: white !important;
            cursor: text !important;
        }
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
       	<h1><spring:message code="U00051"/></h1>
       	<br/>
        <div class="col-md-offset-4 col-md-4" ng-controller="FindCtrl">
        	<div class="form-horizontal">
	        	<div class="form-group">
		        	<input type="email" class="form-control" ng-model="email" placeholder='<spring:message code="U00052"/>' />
	        	</div>
	        	<div class="form-group">
		        	<button class="btn btn-primary btn-block" ng-click="find()"><spring:message code="U00053"/></button>
	        	</div>
	        	<div class="form-group">
	        		<p ng-bind-html="applIdHtml"></p>
	        	</div>
        	</div>
        </div>
    </div>
</section>
<content tag="local-script">
<script src="<spring:eval expression="@app.getProperty('path.static')" />/js/jquery-ui.min.js"></script>
    <!-- load angular via CDN -->
	<script src="//code.angularjs.org/1.5.8/angular.min.js"></script>
	<script src="//code.angularjs.org/1.5.8/angular-route.min.js"></script>
	<script src="//code.angularjs.org/1.5.8/angular-sanitize.min.js"></script>
	<script src="<spring:eval expression="@app.getProperty('path.static')" />/angularjs/app.js"></script>
</content>
</body>
</html>
