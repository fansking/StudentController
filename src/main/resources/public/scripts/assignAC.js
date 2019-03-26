var API_index = angular.module("API_index");
// 主页控制器
API_index.controller("assignACCtrl", ['$scope', '$http', '$state' ,function ($scope, $http, $state) {
    $http({
        method: 'GET',
        url: $scope.athleteUrl+"/findAll"
    }).then(function successCallback(response) {
        $scope.athletes=response.data;
    });
    $http({
        method: 'GET',
        url: $scope.competitionUrl+"/findAll"
    }).then(function successCallback(response) {
        $scope.competitions=response.data;
    });
    $scope.athleteFlag=true;
    $scope.competitionFlag=true;
    $scope.selectAthlete=function(index){
        $scope.athlete=$scope.athletes[index];
    };
    $scope.selectCom=function(index){
        $scope.competition=$scope.competitions[index];
    };
    $scope.athleteCompetition={'groupNum':0};
    $scope.competition={};
    $scope.athlete={};
    $scope.insertAC=function () {
        if($scope.competition==={}||$scope.athlete==={}){
            alert("尚未选择运动员或比赛项目");
            return;
        }
        $scope.athleteCompetition.competition=$scope.competition;
        $scope.athleteCompetition.athlete=$scope.athlete;
        $http({
            method: 'POST',
            url: $scope.athleteCompetitionUrl+"/insert",
            data:$scope.athleteCompetition
        }).then(function successCallback(response) {
            alert("添加成功");
        },function error(response) {
            alert("出错啦，请联系工作人员");
        });
    }
}]);