'use strict';

angular.module('angularBootstrapGruntBowerApp')
    .controller('GuideController', function ($scope, $rootScope, $http, urls, $cookies, $routeParams, AuthSharedService) {
        $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
        $scope.adultCounter = 1;
        $scope.childCounter = 0;
        $scope.roomsCounter = 1;
        $scope.isOneWayTrip = false;

        $scope.decreaseChildCounter = function () {
            if ($scope.childCounter > 0)
                $scope.childCounter = $scope.childCounter - 1;
        }
        $scope.decreaseAdultCounter = function () {
            if ($scope.adultCounter > 0)
                $scope.adultCounter = $scope.adultCounter - 1;
        }
        $scope.decreaseRoomsCounter = function () {
            if ($scope.roomsCounter > 1)
                $scope.roomsCounter = $scope.roomsCounter - 1;
        }
    });
