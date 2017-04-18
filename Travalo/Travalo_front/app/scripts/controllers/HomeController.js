'use strict';

angular.module('angularBootstrapGruntBowerApp')
    .controller('MainCtrl', function ($scope, $http, urls, $cookies) {
        $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
        $scope.selectedTab = 'login';
        $scope.registrationSuccess = false;
        $scope.registrationFail = false;
    $scope.credentials = {};
        $scope.errorMessages = [];
        $scope.loginTabClick = function () {
            $scope.selectedTab = 'login';
        };
        $scope.registerTabClick = function () {
            $scope.selectedTab = 'register';
        };
        $scope.reposneObject = {};
        $scope.registerValues = {};
        $scope.login = function () {
            /*            console.log(config.apiUrl);
                        console.log("in" + $scope.username + $scope.password);
                    };*/
            $http.post(urls.backendUrl + "/login","username=1&password=1&code=&submit=Submit").
            then(function (response) {
                                            $scope.value = $cookies.getAll();
            console.log($cookies.JSESSIONID);
            console.log($cookies.get('JSESSIONID'));
            console.log($cookies.getAll());
            console.log($cookies.getObject('JSESSIONID'));
                console.log(response);
                $scope.greeting = response.data;
                console.log($scope.greeting);
            })

        };

    
        $scope.register = function () {
            /*            console.log(config.apiUrl);
                        console.log("in" + $scope.username + $scope.password);
                    };*/
            console.log($scope.registerValues);
            $http.post(urls.backendUrl + "/user/registration",
                $scope.registerValues
            ).
            success(function (response) {
                $scope.reposneObject = response;
                if ($scope.reposneObject.message === 'success') {
                    $scope.registrationSuccess = true;
                    $scope.registrationFail = false;
                    $scope.errorMessages = [];
                    console.log("success");
                }
                $scope.loginTabClick();
            }).
            error(function (response) {
                if (!response) {
                    $scope.errorMessages = [];
                    $scope.registrationSuccess = false;
                    $scope.registrationFail = true;
                    $scope.errorMessages.push("Server Down");
                    return;
                }
                if (response.errors !== undefined) {
                    $scope.errorMessages = [];
                    var errors = response.errors;
                    angular.forEach(errors, function (value, key) {
                        $scope.errorMessages.push(value.defaultMessage);
                    });
                    $scope.registrationSuccess = false;
                    $scope.registrationFail = true;
                    return;
                }
                if (response.message !== undefined) {
                    $scope.errorMessages = [];
                    $scope.errorMessages.push(response.message);
                    $scope.registrationSuccess = false;
                    $scope.registrationFail = true;
                }

            });

        };

    });
