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
    .config(function ($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
        //  $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        $httpProvider.defaults.useXDomain = true;
        $httpProvider.defaults.withCredentials = true;
        delete $httpProvider.defaults.headers.common["X-Requested-With"];
        $httpProvider.defaults.headers.common["Accept"] = "application/json";
        $httpProvider.defaults.headers.common["Content-Type"] = "application/json";
    });
