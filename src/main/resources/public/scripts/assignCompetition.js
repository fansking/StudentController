var API_index = angular.module("API_index");
// 主页控制器
API_index.controller("assignCompetitionCtrl", ['$scope', '$http', '$state' ,function ($scope, $http, $state) {
    $http({
        method: 'GET',
        url: $scope.competitionUrl+"/findAll"
    }).then(function successCallback(response) {
        $scope.competitions=response.data;
    });
    $scope.competitionModel={
        "age": 7,
        "male": true,
        "name": "跳马",
        "preliminaryContest": true};
    $scope.maleCompetitions=['单杠双杠','吊环','跳马','自由体操','鞍马','蹦床'];
    $scope.femaleCompetitions=['高低杠','平衡木','跳马','自由体操','蹦床'];
    $scope.addCompetition=function(){
        let competition={};
        angular.copy($scope.competitionModel,competition);
        $scope.competitions.push(competition);
    };
    $scope.parseBool=function(x,y){
        x[y]=(x[y]==='t');
    };
    $scope.insertMany=function () {
        $http({
            method: 'POST',
            url: $scope.competitionUrl+"/insertMany",
            data:$scope.competitions
        }).then(function successCallback(response) {
            alert("更新成功");
        });
    }
}]);