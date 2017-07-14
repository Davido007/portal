'use strict';

angular.module('angularBootstrapGruntBowerApp')
    .controller('FriendsController', function ($scope, $rootScope, $http, urls, $cookies, $routeParams, AuthSharedService) {
        $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

        $scope.searchFriends = function () {

            console.log("yep");
            $http.post(urls.backendUrl + "/searchFriends", $scope.name).then(function successCallback(response) {
                if (response !== undefined) {
                    // $scope.suggestions = response.data.places;
                    //$scope.areSuggestionsShowed = true;
                    console.log(response.data.offers);
                    $rootScope.cars = response.data.offers;
                    //$location.path("/searchCar/result");

                } else {
                    //$scope.areSuggestionsShowed = false;
                }
                console.log(response);
            }, function errorCallback(response) {
                console.log("2");
            });

        };
    });
