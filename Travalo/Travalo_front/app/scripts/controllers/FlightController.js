'use strict';

angular.module('angularBootstrapGruntBowerApp')
    .controller('FlightController', function ($scope, $rootScope, $location, $http, urls, $cookies, $routeParams, AuthSharedService) {
        $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
        $scope.flightForm = {};
        $scope.flightForm.adults = 1;
        $scope.flightForm.children = 0;
        $scope.flightForm.isNonStop = false;
        $scope.isOneWayTrip = false;
        $scope.areOriginsShowed = false;
        $scope.areDestinationsShowed = false;

        $scope.decreaseChildCounter = function () {
            if ($scope.flightForm.children > 0)
                $scope.flightForm.children = $scope.flightForm.children - 1;
        }
        $scope.decreaseAdultCounter = function () {
            if ($scope.flightForm.adults > 0)
                $scope.flightForm.adults = $scope.flightForm.adults - 1;
        }
        $scope.getOrigins = function () {
            $scope.origins = [];
            $http.post(urls.backendUrl + "/airports", $scope.flightForm.origin.name).then(function successCallback(response) {
                if (response !== undefined) {
                    $scope.origins = response.data.airports;
                    $scope.areOriginsShowed = true;
                } else {
                    $scope.areOriginsShowed = false;
                }
                console.log(response);
            }, function errorCallback(response) {
                console.log("2");
            });

        };
        $scope.getDestinations = function () {
            $scope.destinations = [];
            $http.post(urls.backendUrl + "/airports", $scope.flightForm.destination.name).then(function successCallback(response) {
                if (response !== undefined) {
                    $scope.destinations = response.data.airports;
                    $scope.areDestinationsShowed = true;
                } else {
                    $scope.areDestinationsShowed = false;
                }
                console.log(response);
            }, function errorCallback(response) {
                console.log("2");
            });

        };
        $scope.searchFlights = function () {
            //  $scope.flightForm={children: $scope.flightForm.children};
            console.log($scope.flightForm.departureDate);

            $scope.flightForm.departureDate = $('#datetimepicker4').val();
            //$rootScope.flights = response.data.flights;
            $scope.flightForm.returnDate = $('#datetimepicker3').val();
            console.log($scope.flightForm.departureDate);



            $scope.json = {
    "travels": [{
        "mainFlight": {
            "origin": "BCN",
            "destination": "WAW",
            "price": null,
            "duration": "7h 15m",
            "departureDate": "2017-06-21",
            "departureTime": "15:55",
            "arrivalDate": "2017-06-21",
            "arrivalTime": "23:10",
            "through": "BRU",
            "carrier": "Brussels Airlines",
            "direct": false
        },
        "returnFlight": {
            "origin": "WAW",
            "destination": "BCN",
            "price": null,
            "duration": "4h 40m",
            "departureDate": "2017-06-29",
            "departureTime": "14:35",
            "arrivalDate": "2017-06-29",
            "arrivalTime": "19:15",
            "through": "ZRH",
            "carrier": "Swiss International Air Lines",
            "direct": false
        },
        "price": "USD291.80"
    }, {
        "mainFlight": {
            "origin": "BCN",
            "destination": "WAW",
            "price": null,
            "duration": "7h 15m",
            "departureDate": "2017-06-21",
            "departureTime": "15:55",
            "arrivalDate": "2017-06-21",
            "arrivalTime": "23:10",
            "through": "BRU",
            "carrier": "Brussels Airlines",
            "direct": false
        },
        "returnFlight": {
            "origin": "WAW",
            "destination": "BCN",
            "price": null,
            "duration": "4h 25m",
            "departureDate": "2017-06-29",
            "departureTime": "09:40",
            "arrivalDate": "2017-06-29",
            "arrivalTime": "14:05",
            "through": "ZRH",
            "carrier": "Swiss International Air Lines",
            "direct": false
        },
        "price": "USD291.80"
    }, {
        "mainFlight": {
            "origin": "BCN",
            "destination": "WAW",
            "price": null,
            "duration": "3h 5m",
            "departureDate": "2017-06-21",
            "departureTime": "13:15",
            "arrivalDate": "2017-06-21",
            "arrivalTime": "16:20",
            "through": null,
            "carrier": "LOT Polish Airlines",
            "direct": true
        },
        "returnFlight": {
            "origin": "WAW",
            "destination": "BCN",
            "price": null,
            "duration": "8h 25m",
            "departureDate": "2017-06-29",
            "departureTime": "12:15",
            "arrivalDate": "2017-06-29",
            "arrivalTime": "20:40",
            "through": "BRU",
            "carrier": "Brussels Airlines",
            "direct": false
        },
        "price": "USD296.80"
    }, {
        "mainFlight": {
            "origin": "BCN",
            "destination": "WAW",
            "price": null,
            "duration": "3h 5m",
            "departureDate": "2017-06-21",
            "departureTime": "13:15",
            "arrivalDate": "2017-06-21",
            "arrivalTime": "16:20",
            "through": null,
            "carrier": "LOT Polish Airlines",
            "direct": true
        },
        "returnFlight": {
            "origin": "WAW",
            "destination": "BCN",
            "price": null,
            "duration": "8h 35m",
            "departureDate": "2017-06-29",
            "departureTime": "06:35",
            "arrivalDate": "2017-06-29",
            "arrivalTime": "15:10",
            "through": "BRU",
            "carrier": "Brussels Airlines",
            "direct": false
        },
        "price": "USD313.80"
    }, {
        "mainFlight": {
            "origin": "BCN",
            "destination": "WAW",
            "price": null,
            "duration": "4h 40m",
            "departureDate": "2017-06-21",
            "departureTime": "07:50",
            "arrivalDate": "2017-06-21",
            "arrivalTime": "12:30",
            "through": "MUC",
            "carrier": "Lufthansa Cargo",
            "direct": false
        },
        "returnFlight": {
            "origin": "WAW",
            "destination": "BCN",
            "price": null,
            "duration": "4h 25m",
            "departureDate": "2017-06-29",
            "departureTime": "09:40",
            "arrivalDate": "2017-06-29",
            "arrivalTime": "14:05",
            "through": "ZRH",
            "carrier": "Swiss International Air Lines",
            "direct": false
        },
        "price": "USD314.20"
    }, {
        "mainFlight": {
            "origin": "BCN",
            "destination": "WAW",
            "price": null,
            "duration": "4h 40m",
            "departureDate": "2017-06-21",
            "departureTime": "07:50",
            "arrivalDate": "2017-06-21",
            "arrivalTime": "12:30",
            "through": "MUC",
            "carrier": "Lufthansa Cargo",
            "direct": false
        },
        "returnFlight": {
            "origin": "WAW",
            "destination": "BCN",
            "price": null,
            "duration": "4h 40m",
            "departureDate": "2017-06-29",
            "departureTime": "14:35",
            "arrivalDate": "2017-06-29",
            "arrivalTime": "19:15",
            "through": "ZRH",
            "carrier": "Swiss International Air Lines",
            "direct": false
        },
        "price": "USD314.20"
    }, {
        "mainFlight": {
            "origin": "BCN",
            "destination": "WAW",
            "price": null,
            "duration": "4h 40m",
            "departureDate": "2017-06-21",
            "departureTime": "07:50",
            "arrivalDate": "2017-06-21",
            "arrivalTime": "12:30",
            "through": "MUC",
            "carrier": "Lufthansa Cargo",
            "direct": false
        },
        "returnFlight": {
            "origin": "WAW",
            "destination": "BCN",
            "price": null,
            "duration": "4h 35m",
            "departureDate": "2017-06-29",
            "departureTime": "10:30",
            "arrivalDate": "2017-06-29",
            "arrivalTime": "15:05",
            "through": "FRA",
            "carrier": "Lufthansa Cargo",
            "direct": false
        },
        "price": "USD373.30"
    }, {
        "mainFlight": {
            "origin": "BCN",
            "destination": "WAW",
            "price": null,
            "duration": "4h 20m",
            "departureDate": "2017-06-21",
            "departureTime": "14:50",
            "arrivalDate": "2017-06-21",
            "arrivalTime": "19:10",
            "through": "ZRH",
            "carrier": "Swiss International Air Lines",
            "direct": false
        },
        "returnFlight": {
            "origin": "WAW",
            "destination": "BCN",
            "price": null,
            "duration": "4h 25m",
            "departureDate": "2017-06-29",
            "departureTime": "09:40",
            "arrivalDate": "2017-06-29",
            "arrivalTime": "14:05",
            "through": "ZRH",
            "carrier": "Swiss International Air Lines",
            "direct": false
        },
        "price": "USD381.60"
    }, {
        "mainFlight": {
            "origin": "BCN",
            "destination": "WAW",
            "price": null,
            "duration": "4h 20m",
            "departureDate": "2017-06-21",
            "departureTime": "14:50",
            "arrivalDate": "2017-06-21",
            "arrivalTime": "19:10",
            "through": "ZRH",
            "carrier": "Swiss International Air Lines",
            "direct": false
        },
        "returnFlight": {
            "origin": "WAW",
            "destination": "BCN",
            "price": null,
            "duration": "4h 40m",
            "departureDate": "2017-06-29",
            "departureTime": "14:35",
            "arrivalDate": "2017-06-29",
            "arrivalTime": "19:15",
            "through": "ZRH",
            "carrier": "Swiss International Air Lines",
            "direct": false
        },
        "price": "USD381.60"
    }, {
        "mainFlight": {
            "origin": "BCN",
            "destination": "WAW",
            "price": null,
            "duration": "3h 5m",
            "departureDate": "2017-06-21",
            "departureTime": "13:15",
            "arrivalDate": "2017-06-21",
            "arrivalTime": "16:20",
            "through": null,
            "carrier": "LOT Polish Airlines",
            "direct": true
        },
        "returnFlight": {
            "origin": "WAW",
            "destination": "BCN",
            "price": null,
            "duration": "3h 10m",
            "departureDate": "2017-06-29",
            "departureTime": "09:15",
            "arrivalDate": "2017-06-29",
            "arrivalTime": "12:25",
            "through": null,
            "carrier": "LOT Polish Airlines",
            "direct": true
        },
        "price": "USD448.10"
    }]
}
;






/*             $rootScope.travels = $scope.json.travels;
             console.log( $rootScope.travels);
             $location.path("/bookFlight/result");*/
              $http.post(urls.backendUrl + "/flights", $scope.flightForm).then(function successCallback(response) {
                  if (response !== undefined) {
                      $location.path("/bookFlight/result");

                      console.log(response);
                      $rootScope.travels = response.data.travels;


                  } else {}
                  console.log(response);
              }, function errorCallback(response) {
                  console.log("2");
              });
        };

        $scope.onOriginsClicked = function (choosenOrigin) {
            $scope.flightForm.origin = choosenOrigin;
            $scope.areOriginsShowed = false;
        };
        $scope.onDestinationsClicked = function (choosenDestination) {
            $scope.flightForm.destination = choosenDestination;
            $scope.areDestinationsShowed = false;
        };

    });
