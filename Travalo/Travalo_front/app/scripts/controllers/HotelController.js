'use strict';

angular.module('angularBootstrapGruntBowerApp')
    .controller('HotelController', function ($scope, $rootScope, $location, $http, urls, $cookies, $routeParams, AuthSharedService) {
        $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
        $scope.adultCounter = 1;
        $scope.childCounter = 0;
        $scope.roomsCounter = 1;
        $scope.isOneWayTrip = false;
        $scope.hotelData = {};
        $scope.hotelData.city = {};
        $scope.areSuggestionsShowed = false;
        $scope.suggestions = [];
        // $scope.hotels = [];
        $scope.getPlace = function () {
            $scope.suggestions = [];
            $http.post(urls.backendUrl + "/suggestion", $scope.hotelData.city.label).then(function successCallback(response) {
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
        console.log($rootScope);
        $scope.searchHotels = function () {
            $http.post(urls.backendUrl + "/hotels", $scope.hotelData.city.href).then(function successCallback(response) {
                if (response !== undefined) {
                    // $scope.suggestions = response.data.places;
                    //$scope.areSuggestionsShowed = true;
                    console.log(response);
                    $rootScope.hotels = response.data.hotels;
                    $location.path("/searchHotel/result");
                    angular.forEach($rootScope.hotels, function (value, key) {
                        value.properties.price = Math.floor(80+ Math.random() * 40 + 1);

                    });

                    console.log($rootScope.hotels);
                } else {
                    //$scope.areSuggestionsShowed = false;
                }
                console.log(response);
            }, function errorCallback(response) {
                console.log("2");
            });

        };
        $scope.onSuggestionClicked = function (suggestion) {
            $scope.hotelData.city = suggestion;
            $scope.areSuggestionsShowed = false;
        };

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
