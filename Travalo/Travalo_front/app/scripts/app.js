'use strict';

angular.module('angularBootstrapGruntBowerApp', [
  'ngCookies',
  'ngResource',
  'ngSanitize',
  'ngRoute',
  'swaggerUi',
  'http-auth-interceptor',
  'ngAnimate',
  'angular-spinkit'
])
    .constant('urls', {
        backendUrl: 'http://localhost:2222',
        baseUrl: '/',
        enableDebug: true
    })
    .constant('USER_ROLES', {
        all: '*',
        admin: 'admin',
        user: 'user'
    })
    .config(function ($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
            }).when('/emailConfirmed', {
                templateUrl: 'views/emailConfirmed.html',
                controller: 'EmailConfirmedController'
            }).when('/bookFlight', {
                templateUrl: 'views/bookFlight.html',
                controller: 'FlightController'
            }).when('/bookFlight/result', {
                templateUrl: 'views/searchFlightsResult.html',
                controller: 'FlightController'
            }).when('/searchHotel', {
                templateUrl: 'views/searchHotel.html',
                controller: 'HotelController'
            }).when('/searchHotel/result', {
                templateUrl: 'views/searchHotelResult.html',
                controller: 'HotelController'
            }).when('/searchCar', {
                templateUrl: 'views/searchCar.html',
                controller: 'CarController'
            }).when('/searchCar/result', {
                templateUrl: 'views/searchCarResult.html',
                controller: 'CarController'
            }).when('/searchVacations', {
                templateUrl: 'views/searchVacation.html',
                controller: 'VacationController'
            }).when('/searchGuides', {
                templateUrl: 'views/searchGuides.html',
                controller: 'GuideController'
            }).when('/searchPlaces', {
                templateUrl: 'views/searchPlaces.html',
                controller: 'PlaceController'
            })
            .when('/inviteFriends', {
                templateUrl: 'views/inviteFriends.html',
                controller: 'FriendsController',
                access: {
                    loginRequired: true
                }
            }).when('/board', {
                templateUrl: 'views/board.html',
                controller: 'BoardController',
                access: {
                    loginRequired: true
                }
            }).when('/logout', {
                template: " ",
                controller: "LogoutController"
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
    })
    .run(function ($rootScope, $location, $http, AuthSharedService, Session, USER_ROLES, $q, $timeout) {

        $rootScope.$on('$routeChangeStart', function (event, next) {

            if (next.originalPath === "/login" && $rootScope.authenticated) {
                event.preventDefault();
            } else if (next.access && next.access.loginRequired && !$rootScope.authenticated) {
                event.preventDefault();
                $rootScope.$broadcast("event:auth-loginRequired", {});
            } else if (next.access && !AuthSharedService.isAuthorized(next.access.authorizedRoles)) {
                event.preventDefault();
                $rootScope.$broadcast("event:auth-forbidden", {});
            }
        });

        /*        $rootScope.$on('$routeChangeSuccess', function (scope, next, current) {
                    $rootScope.$evalAsync(function () {
                        $.material.init();
                    });
                });*/

        // Call when the the client is confirmed
        $rootScope.$on('event:auth-loginConfirmed', function (event, data) {
            console.log('login confirmed start ' + data);
            console.log(data);
            $rootScope.loadingAccount = false;
            var nextLocation = ($rootScope.requestedUrl ? $rootScope.requestedUrl : "/home");
            var delay = ($location.path() === "/loading" ? 1500 : 0);

            $timeout(function () {
                Session.create(data);
                $rootScope.account = Session;
                $rootScope.authenticated = true;
                $location.path(nextLocation).replace();
            }, delay);
        });

        // Call when the 401 response is returned by the server
        $rootScope.$on('event:auth-loginRequired', function (event, data) {
            console.log("on 2");
            console.log(data);
            console.log($location.path());
            if ($rootScope.loadingAccount && data.status !== 401) {
                $rootScope.requestedUrl = $location.path()
                $location.path('/loading');
            } else if ($location.path() === "/emailConfirmed") {
                console.log("ddd");
                Session.invalidate();
                $rootScope.authenticated = false;
                $rootScope.loadingAccount = false;
                $location.path('/emailConfirmed');
            } else {
                Session.invalidate();
                $rootScope.authenticated = false;
                $rootScope.loadingAccount = false;
                $location.path('/');
            }
        });

        // Call when the 403 response is returned by the server
        $rootScope.$on('event:auth-forbidden', function (rejection) {
            $rootScope.$evalAsync(function () {
                $location.path('/error/403').replace();
            });
        });

        // Call when the user logs out
        $rootScope.$on('event:auth-loginCancelled', function () {
            $location.path('/login').replace();
        });

        // Get already authenticated user account
        AuthSharedService.getAccount();


    });

/*'use strict';

var myapp = angular
    .module('myApp', ['ngResource', 'ngRoute', 'swaggerUi', 'http-auth-interceptor', 'ngAnimate', 'angular-spinkit']);


myapp.constant('USER_ROLES', {
    all: '*',
    admin: 'admin',
    user: 'user'
});*/


/*myapp.config(function ($routeProvider, $httpProvider, USER_ROLES) {

    $routeProvider.when("/home", {
        templateUrl: "views/home.html",
        controller: 'HomeController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/', {
        redirectTo: '/home'
    }).when('/users', {
        templateUrl: 'views/users.html',
        controller: 'UsersController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.admin]
        }
    }).when('/apiDoc', {
        templateUrl: 'views/apiDoc.html',
        controller: 'ApiDocController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/tokens', {
        templateUrl: 'views/tokens.html',
        controller: 'TokensController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginController',
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/loading', {
        templateUrl: 'views/loading.html',
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when("/logout", {
        template: " ",
        controller: "LogoutController",
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when("/error/:code", {
        templateUrl: "views/error.html",
        controller: "ErrorController",
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).otherwise({
        redirectTo: '/error/404',
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    });
    $httpProvider.defaults.useXDomain = true;
    $httpProvider.defaults.withCredentials = true;
    delete $httpProvider.defaults.headers.common["X-Requested-With"];
    $httpProvider.defaults.headers.common["Accept"] = "application/json";
    $httpProvider.defaults.headers.common["Content-Type"] = "application/json";
});*/

/*
myapp.run(function ($rootScope, $location, $http, AuthSharedService, Session, USER_ROLES, $q, $timeout) {

    $rootScope.$on('$routeChangeStart', function (event, next) {

        if (next.originalPath === "/login" && $rootScope.authenticated) {
            event.preventDefault();
        } else if (next.access && next.access.loginRequired && !$rootScope.authenticated) {
            event.preventDefault();
            $rootScope.$broadcast("event:auth-loginRequired", {});
        } else if (next.access && !AuthSharedService.isAuthorized(next.access.authorizedRoles)) {
            event.preventDefault();
            $rootScope.$broadcast("event:auth-forbidden", {});
        }
    });

    $rootScope.$on('$routeChangeSuccess', function (scope, next, current) {
        $rootScope.$evalAsync(function () {
            $.material.init();
        });
    });

    // Call when the the client is confirmed
    $rootScope.$on('event:auth-loginConfirmed', function (event, data) {
        console.log('login confirmed start ' + data);
        $rootScope.loadingAccount = false;
        var nextLocation = ($rootScope.requestedUrl ? $rootScope.requestedUrl : "/home");
        var delay = ($location.path() === "/loading" ? 1500 : 0);

        $timeout(function () {
            Session.create(data);
            $rootScope.account = Session;
            $rootScope.authenticated = true;
            $location.path(nextLocation).replace();
        }, delay);

    });

    // Call when the 401 response is returned by the server
    $rootScope.$on('event:auth-loginRequired', function (event, data) {
        if ($rootScope.loadingAccount && data.status !== 401) {
            $rootScope.requestedUrl = $location.path()
            $location.path('/loading');
        } else {
            Session.invalidate();
            $rootScope.authenticated = false;
            $rootScope.loadingAccount = false;
            $location.path('/login');
        }
    });

    // Call when the 403 response is returned by the server
    $rootScope.$on('event:auth-forbidden', function (rejection) {
        $rootScope.$evalAsync(function () {
            $location.path('/error/403').replace();
        });
    });

    // Call when the user logs out
    $rootScope.$on('event:auth-loginCancelled', function () {
        $location.path('/login').replace();
    });

    // Get already authenticated user account
    //AuthSharedService.getAccount();


});
*/
