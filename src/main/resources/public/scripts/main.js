// angular.module("API_index"); 表明获取index.js中创建的模块
// 如果写成angular.module("API_index", []);则会创建一个新的模块， 会把之前创建的模块覆盖。
var API_index = angular.module("API_index");
// 主页控制器
API_index.controller("mainCtrl", ['$scope', '$http', '$state' ,function ($scope, $http, $state) {
    $scope.team={"id":5,"name": "梦", "passWord": "123456"};
    $scope.leader={"identityNum":"","name":"","phoneNum":""};
    $scope.doctor={"identityNum":"","name":"","phoneNum":""};
    $scope.referee={"identityNum":"","name":"","phoneNum":""};
    $scope.coach={"identityNum":"","name":"","phoneNum":"",'isMale':"男"};
    $scope.isMale=["男","女"];
    $scope.maleCompetitions=['单杠双杠','吊环','跳马','自由体操','鞍马','蹦床'];
    $scope.femaleCompetitions=['高低杠','平衡木','跳马','自由体操','蹦床'];
    $scope.athlete={
        "age": "",
        "competitions": [
        ],
        "identityNum": "",
        "isMale": true,
        "name": "",
        "teamName": $scope.team.name
    };
    $scope.athletes=[];
    let ath={};
    angular.copy($scope.athlete,ath);
    $scope.athletes.push(ath);
    $scope.selectCompet=function(compet,x){
        x.competitions.push(compet);
    };
    $scope.addAthlete=function(){
        let a={};
        angular.copy($scope.athlete,a);
        $scope.athletes.push(a);
    };
    $scope.insert=function(){
        $scope.coach.isMale=($scope.coach.isMale==="男");
        for(let i=0;i<$scope.athletes.length;i++){
            $scope.athletes[i].isMale=($scope.athletes[i].isMale==="男");
        }
        $scope.team.leader=$scope.leader;
        $scope.team.coach=$scope.coach;
        $scope.team.doctor=$scope.doctor;
        $scope.team.referee=$scope.referee;
        $scope.team.athletes=$scope.athletes;
        $http({
            method: 'POST',
            url: $scope.teamUrl+'/insert',
            data:$scope.team
        }).then(function successCallback(response) {
            alert("插入成功");
        })
    };

}]);