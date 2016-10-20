// MODULE
var angularApp = angular.module('angularApp', ['ngRoute', 'ngSanitize']);

angularApp.controller('FindCtrl', function($scope, $http, $sce) {
	$scope.applId = '';

	$scope.$watch('email'), function() {
		$scope.email2 = $scope.email;
	}

	$scope.find = function() {
	    $http({
	        method: 'GET',
	        url: '/yonsei/postApplication/applId?email=' + $scope.email
	    })
	    .success( function(data, status, headers, config) {
	        if (data) {
	            if (!data.applId)
	                $scope.applId = 'NOT FOUND';
	            else
	                $scope.applId = data.applId;
	            $scope.applIdHtml = $sce.trustAsHtml('<pre><h1>' + $scope.applId + '</h1></pre>');
	        } else {
	            console.log(status);
	            console.log(headers);
	            console.log(config);
	        }
	    })
	    .error( function(data, status, headers, config) {
	        console.log(status);
	    });
	}
});