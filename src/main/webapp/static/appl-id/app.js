// MODULE
var angularApp = angular.module('angularApp', ['ngRoute']);

// ROUTES
angularApp.config( function($routeProvider) {

    $routeProvider

        .when('/', {
            templateUrl: 'form.html',
            controller: 'mainController'
        })

        .when('/find', {
            templateUrl: 'result.html',
            controller: 'findController'
        })

});

// SERVICES
angularApp.service('applIdService', function() {

});

// CONTROLLERS
angularApp.controller('mainController', ['$scope', '$http', 'applIdService', function ($scope, $http, applIdService) {

    $scope.email = applIdService.email;

    $scope.$watch('email', function() {
        applIdService.email = $scope.email;
    });

}]);

angularApp.controller('findController', ['$scope', '$http', 'applIdService', function ($scope, $http, applIdService) {

    $scope.email = applIdService.email;

    $http({
        method: 'GET',
        url: "/yonsei/postApplication/applId?email=" + $scope.email
    })
    .success( function(data, status, headers, config) {
        if (data) {
            if (!data.applId)
                $scope.applId = "NOT FOUND";
            else
                $scope.applId = data.applId;
        } else {
            console.log(status);
            console.log(headers);
            console.log(config);
        }
    })
    .error( function(data, status, headers, config) {
        console.log(status);
    });


}]);


