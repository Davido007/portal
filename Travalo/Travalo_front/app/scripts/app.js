'use strict';

angular.module('angularBootstrapGruntBowerApp', [
  'ngCookies',
  'ngResource',
  'ngSanitize',
  'ngRoute'
])
    .constant('urls', {
        backendUrl: 'http://localhost:2222',
        baseUrl: '/',
        enableDebug: true
    })
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });
