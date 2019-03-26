(function (angular) {
    // 根据ng-app的值新建一个模块
    var API_index = angular.module("API_index", ['ui.router','treeGrid']);


    API_index.controller("indexCtrl", ['$scope', '$state', '$http', '$window', function ($scope, $state, $http, $window) {
        $scope.teamUrl="http://localhost:8080/team";
        $scope.athleteUrl="http://localhost:8080/athlete";
        $scope.competitionUrl="http://localhost:8080/competition";
        $scope.findAllTeams=function(){
            $http({
                method: 'GET',
                url: $scope.teamUrl,
            }).then(function successCallback(response) {
                $scope.teams=response.data;
            })
        };
        $scope.findAllTeams();
    }]);



    API_index.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider
            .when('', '/main');
        $stateProvider.state('main', {
            url: '/main',
            cache: false,
            templateUrl: '/page/main.html',
            // views: {
            //     '': {
            //         templateUrl: '/page/home/main.html',
            //     }
            // }
        }).state('assignId', {
            url: '/assignId',
            cache: false,
            templateUrl: '/page/assignId.html',
            // views: {
            //     '': {
            //         templateUrl: '/page/home/main.html',
            //     }
            // }
        }).state('assignAccount', {
            url: '/assignAccount',
            cache: false,
            templateUrl: '/page/assignAccount.html',
            // views: {
            //     '': {
            //         templateUrl: '/page/home/main.html',
            //     }
            // }
        }).state('assignCompetition', {
            url: '/assignCompetition',
            cache: false,
            templateUrl: '/page/assignCompetition.html',
            // views: {
            //     '': {
            //         templateUrl: '/page/home/main.html',
            //     }
            // }
        });


    }]);
})(angular);
