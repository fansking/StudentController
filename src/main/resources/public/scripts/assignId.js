// angular.module("API_index"); 表明获取index.js中创建的模块
// 如果写成angular.module("API_index", []);则会创建一个新的模块， 会把之前创建的模块覆盖。
var API_index = angular.module("API_index");
// 主页控制器
API_index.controller("assignCtrl", ['$scope', '$http', '$state' ,function ($scope, $http, $state) {
    $http({
        method: 'GET',
        url: $scope.athleteUrl+"/findAll"
    }).then(function successCallback(response) {
        $scope.athletes=response.data;
    });
    $scope.insertMany=function () {
        $http({
            method: 'POST',
            url: $scope.athleteUrl+"/insertMany",
            data:$scope.athletes
        }).then(function successCallback(response) {
            alert("更新成功");
        });
    }
}]);