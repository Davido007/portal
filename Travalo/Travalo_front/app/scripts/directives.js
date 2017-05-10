angular.module('angularBootstrapGruntBowerApp').directive('access', [
    'AuthSharedService',
    function (AuthSharedService) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var roles = attrs.access.split(',');
                if (roles.length > 0) {
                    if (AuthSharedService.isAuthorized(roles)) {
                        element.removeClass('hide');
                    } else {
                        element.addClass('hide');
                    }
                }
            }
        };
    }]).directive('rightMenuItemOnHover', [
   function () {
        return {
            link: function (scope, element, attrs) {
                var aElement = angular.element(element[0]);
                aElement.stop().animate({
                    'marginLeft': '+55%'
                }, 1000);
                element.hover(
                    function () {
                        $($(this)).stop().animate({
                            'marginLeft': '+15%'
                        }, 200);
                    },
                    function () {
                        $($(this)).stop().animate({
                            'marginLeft': '+55%'
                        }, 200);
                    }
                );
            }
        };
}]);

/*
$(function () {
    $('#mySidenav').hide();
    var height = $(window).scrollTop();


    $('#mySidenav a').stop().animate({
        'marginLeft': '+55%'
    }, 1000);

    $('#mySidenav > a').hover(
        function () {
            $($(this)).stop().animate({
                'marginLeft': '+15%'
            }, 200);
        },
        function () {
            $($(this)).stop().animate({
                'marginLeft': '+55%'
            }, 200);
        }
    );
});
*/
