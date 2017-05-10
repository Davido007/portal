'use strict';

angular.module('angularBootstrapGruntBowerApp')
    .controller('LogoutController', function ($scope, $rootScope, $http, urls, $cookies, AuthSharedService) {
        $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
        AuthSharedService.logout();
    });
