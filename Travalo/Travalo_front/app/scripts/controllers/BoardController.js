'use strict';

angular.module('angularBootstrapGruntBowerApp')
    .controller('BoardController', function ($scope, $rootScope, $location, $http, urls, $cookies, $routeParams, AuthSharedService) {
        $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
        $scope.areSuggestionsShowed = false;
        $scope.areSuggestionsReturnShowed = false;
        $scope.adultCounter = 1;
        $scope.childCounter = 0;
        $scope.roomsCounter = 1;
        $scope.suggestions = [];
        $scope.carData = {};
        $scope.returnSuggestions = [];
        $scope.isReturnAndPickUpTheSame = false;

        $scope.moveToInviteFriendsPage = function () {
            $location.path("/inviteFriends");
        }
        $scope.decreaseAdultCounter = function () {
            if ($scope.adultCounter > 0)
                $scope.adultCounter = $scope.adultCounter - 1;
        }
        $scope.decreaseRoomsCounter = function () {
            if ($scope.roomsCounter > 1)
                $scope.roomsCounter = $scope.roomsCounter - 1;
        }
        $scope.getOriginPlace = function () {
            $scope.suggestions = [];
            $http.post(urls.backendUrl + "/suggestion", $scope.carData.originCity).then(function successCallback(response) {
                if (response !== undefined) {
                    $scope.suggestions = response.data.places;
                    $scope.areSuggestionsShowed = true;
                } else {
                    $scope.areSuggestionsShowed = false;
                }
                console.log(response);
            }, function errorCallback(response) {
                console.log("2");
            });

        };
        $scope.getReturnPlace = function () {
            $scope.suggestions = [];
            $http.post(urls.backendUrl + "/suggestion", $scope.carData.returnCity).then(function successCallback(response) {
                if (response !== undefined) {
                    $scope.returnSuggestions = response.data.places;
                    $scope.areSuggestionsReturnShowed = true;
                } else {
                    $scope.areSuggestionsReturnShowed = false;
                }
                console.log(response);
            }, function errorCallback(response) {
                console.log("2");
            });

        };
        $scope.onSuggestionOriginClicked = function (suggestion) {
            $scope.carData.originCity = suggestion.label;
            $scope.carData.originCountry = suggestion.location;
            $scope.areSuggestionsShowed = false;
        };
        $scope.onSuggestionReturnClicked = function (suggestion) {
            $scope.carData.returnCity = suggestion.label;
            $scope.carData.returnCountry = suggestion.location;
            $scope.areSuggestionsReturnShowed = false;
        };
        $scope.searchCars = function () {
            console.log($scope.carData.pickUpDate);

            $scope.carData.pickUpDate = $('#datetimepicker5').val();
            //$rootScope.flights = response.data.flights;
            $scope.carData.dropOffDate = $('#datetimepicker6').val();
            console.log($scope.carData.pickUpDate);
            $http.post(urls.backendUrl + "/cars", $scope.carData).then(function successCallback(response) {
                if (response !== undefined) {
                    // $scope.suggestions = response.data.places;
                    //$scope.areSuggestionsShowed = true;
                    console.log(response.data.offers);
                    $rootScope.cars = response.data.offers;
                    $location.path("/searchCar/result");

                } else {
                    //$scope.areSuggestionsShowed = false;
                }
                console.log(response);
            }, function errorCallback(response) {
                console.log("2");
            });

        };
    });
