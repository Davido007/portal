'use strict';

angular.module('angularBootstrapGruntBowerApp')
    .controller('EmailConfirmedController', function ($scope, $location) {

        $scope.goLogin = function () {
            $location.path("/");
        }
    });
