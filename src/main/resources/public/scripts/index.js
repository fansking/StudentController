(function (angular) {
    // 根据ng-app的值新建一个模块
    var API_index = angular.module("API_index", ['ui.router','treeGrid']);


    API_index.controller("indexCtrl", ['$scope', '$state', '$http', '$window', function ($scope, $state, $http, $window) {
        $scope.teamUrl="http://localhost:8080/team";
        $scope.refereeUrl="http://localhost:8080/team/referee";
        $scope.athleteUrl="http://localhost:8080/athlete";
        $scope.competitionUrl="http://localhost:8080/competition";
        $scope.athleteCompetitionUrl="http://localhost:8080/athleteCompetition";
        $scope.gradesUrl="http://localhost:8080/grades";
        $scope.loginUrl="http://localhost:8080/login";
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
        }).state('main', {
            url: '/main',
            cache: false,
            templateUrl: '/page/main.html',
            params:{team:null}
        }).state('assignId', {
            url: '/assignId',
            cache: false,
            templateUrl: '/page/assignId.html',
        }).state('assignAccount', {
            url: '/assignAccount',
            cache: false,
            templateUrl: '/page/assignAccount.html',
        }).state('assignCompetition', {
            url: '/assignCompetition',
            cache: false,
            templateUrl: '/page/assignCompetition.html',
        }).state('assignAC', {
            url: '/assignAC',
            cache: false,
            templateUrl: '/page/assignAC.html',
        }).state('assignGrade', {
            url: '/assignGrade',
            cache: false,
            templateUrl: '/page/assignGrade.html',
        }).state('judge', {
            url: '/judge',
            cache: false,
            templateUrl: '/page/judge.html',
            params:{referee:null}
            // views: {
            //     '': {
            //         templateUrl: '/page/home/main.html',
            //     }
            // }
        }).state('adminControl', {
            url: '/adminControl',
            cache: false,
            templateUrl: '/page/adminControl.html',
        }).state('personalRank', {
            url: '/personalRank',
            cache: false,
            templateUrl: '/page/personalRank.html',
        });


    }]);
})(angular);
