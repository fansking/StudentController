var API_index = angular.module("API_index");
// 主页控制器
API_index.controller("assignGradeCtrl", ['$scope', '$http', '$state','$stateParams' ,function ($scope, $http, $state,$stateParams) {
    /*$scope.referee=$stateParams.referee;*/
    $scope.refereeFlag=true;
    $scope.ACFlag=true;
    $http({
        method: 'POST',
        url: $scope.athleteCompetitionUrl
    }).then(function successCallback(response) {
        $scope.ACs=response.data;
    });
    $http({
        method: 'GET',
        url: $scope.refereeUrl
    }).then(function successCallback(response) {
        $scope.referees=response.data;
    });
    $scope.selectAC=function(index){
        $scope.AC=$scope.ACs[index];
    };
    $scope.selectReferee=function(index){
        $scope.referee=$scope.referees[index];
    };
      $scope.AC={};
      $scope.referee={};
      $scope.grades={};

      $scope.insertAC=function () {
          if($scope.AC==={}||$scope.referee==={}){
              alert("尚未选择运动员或比赛项目");
              return;
          }
          $scope.grades.athleteCompetition=$scope.AC;
          $scope.grades.referee=$scope.referee;
          $http({
              method: 'POST',
              url: $scope.gradesUrl+"/insert",
              data:$scope.grades
          }).then(function successCallback(response) {
              alert("添加成功");
          },function error(response) {
              alert("出错啦，请联系工作人员");
          });
      }
}]);