// angular.module("API_index"); 表明获取index.js中创建的模块
// 如果写成angular.module("API_index", []);则会创建一个新的模块， 会把之前创建的模块覆盖。
var API_index = angular.module("API_index");
// 主页控制器
API_index.controller("assignAccountCtrl", ['$scope', '$http', '$state' ,function ($scope, $http, $state) {

    $scope.teamModel={"name":"","account":"","password":"123456"};
    $scope.addTeam=function(){
        let team={};
        angular.copy($scope.teamModel,team)
        $scope.teams.push(team);
    };
    $scope.insertTeams=function () {
        $http({
            method: 'POST',
            url: $scope.teamUrl+"/insertMany",
            data:$scope.teams
        }).then(function successCallback(response) {
            alert("更新成功");
        });
    }
}]);