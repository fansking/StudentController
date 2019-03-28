var API_index = angular.module("API_index");
// 主页控制器
API_index.controller("judgeCtrl", ['$scope', '$http', '$state','$stateParams' ,function ($scope, $http, $state,$stateParams) {
    $scope.referee=$stateParams.referee;
    $http({
        method: 'GET',
        url: $scope.gradesUrl+"/findByRefereeIdentityNum/"+$scope.referee.identityNum
    }).then(function successCallback(response) {
        $scope.grades=[];
        for(let i=0;i<response.data.length;i++){
            if(response.data[i].pass===null||response.data[i].pass===false){
                $scope.grades.push(response.data[i]);
            }
        }
    });

    $scope.findAll=function(AId,CId){
        $http({
            method: 'GET',
            url: $scope.gradesUrl+"/find/"+AId+'/'+CId
        }).then(function successCallback(response) {
            $scope.AllGrades=response.data;
        });
    };
    $scope.parseBool=function(x,y){
        x[y]=(x[y]==='t');
    };
    $scope.showModel=function (grade) {
        $scope.findAll(grade.athleteCompetition.athlete.id,grade.athleteCompetition.competition.id);

    };
    $scope.updateGrade=function (grade) {
        $http({
            method: 'POST',
            url: $scope.gradesUrl+"/insert",
            data:grade
        }).then(function successCallback(response) {
            alert("添加成功");
        },function error(response) {
            alert("出错啦，请联系工作人员");
        });
    }
    $scope.updateAll=function () {
        $http({
            method: 'POST',
            url: $scope.gradesUrl+"/insertAll",
            data:$scope.AllGrades
        }).then(function successCallback(response) {
            alert("添加成功");
        },function error(response) {
            alert("出错啦，请联系工作人员");
        });
    }
  /*  $http({
        method: 'GET',
        url: $scope.competitionUrl+"/findAll"
    }).then(function successCallback(response) {
        $scope.competitions=response.data;
    });

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
    }*/
}]);