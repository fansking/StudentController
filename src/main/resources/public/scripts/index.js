(function (angular) {
    // 根据ng-app的值新建一个模块
    var API_index = angular.module("API_index", ['ui.router','treeGrid']);


    API_index.controller("indexCtrl", ['$scope', '$state', '$http', '$window', function ($scope, $state, $http, $window) {
        $scope.teamUrl="http://localhost:8080/team";
        $scope.refereeUrl="http://localhost:8080/team/referee";
        $scope.athleteUrl="http://localhost:8080/athlete";
        $scope.competitionUrl="http://localhost:8080/competition";
        $scope.athleteCompetitionUrl="http://localhost:8080/athleteCompetition";
        $scope.findAllTeams=function(){
            $http({
                method: 'GET',
                url: $scope.teamUrl,
            }).then(function successCallback(response) {
                $scope.teams=response.data;
            })
        };
        $scope.findAllTeams();
        $state.go('login');
    }]);



    API_index.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider
            .when('', '/login');
        $stateProvider.state('login', {
            url: '/login',
            cache: false,
            templateUrl: '/page/login.html',
            // views: {
            //     '': {
            //         templateUrl: '/page/home/main.html',
            //     }
            // }
        }).state('main', {
            url: '/main',
            cache: false,
            templateUrl: '/page/main.html',
            params:{team:null}
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
        }).state('assignAC', {
            url: '/assignAC',
            cache: false,
            templateUrl: '/page/assignAC.html',
            // views: {
            //     '': {
            //         templateUrl: '/page/home/main.html',
            //     }
            // }
        });


    }]);
})(angular);
