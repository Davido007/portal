'use strict';

angular.module('angularBootstrapGruntBowerApp')
    .controller('MainCtrl', function ($scope, $http, urls) {
        $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
        $scope.selectedTab = 'login';

        $scope.loginTabClick = function () {
            $scope.selectedTab = 'login';
        };
        $scope.registerTabClick = function () {
            $scope.selectedTab = 'register';
        };
        $scope.greeting = {};
        $scope.registerValues = {};
        $scope.login = function () {
            /*            console.log(config.apiUrl);
                        console.log("in" + $scope.username + $scope.password);
                    };*/
            $http.get(urls.backendUrl + "/login").
            then(function (response) {
                $scope.greeting = response.data;
                console.log($scope.greeting);
                $scope.loginTabClick1();
            })
            $scope.loginTabClick1 = function () {
                console.log($scope.greeting);
            };

        };
        $scope.register = function () {
            /*            console.log(config.apiUrl);
                        console.log("in" + $scope.username + $scope.password);
                    };*/
            console.log($scope.registerValues);
            $http.post(urls.backendUrl + "/user/registration", 
                $scope.registerValues
            ).
            then(function (response) {
                $scope.greeting = response.data;
                console.log($scope.greeting);
                $scope.loginTabClick1();
            })
            $scope.loginTabClick1 = function () {
                console.log($scope.greeting);
            };

        };

    });
