// angular.module("API_index"); 表明获取index.js中创建的模块
// 如果写成angular.module("API_index", []);则会创建一个新的模块， 会把之前创建的模块覆盖。
var API_index = angular.module("API_index");
// 主页控制器
API_index.controller("mainCtrl", ['$scope', '$http', '$state' ,function ($scope, $http, $state) {
    $scope.team={"id":7,"name": "梦", "passWord": "123456","account":"654321"};
    $scope.leader={"identityNum":"","name":"","phoneNum":""};
    $scope.doctor={"identityNum":"","name":"","phoneNum":""};
    $scope.referee={"identityNum":"","name":"","phoneNum":""};
    $scope.coach={"identityNum":"","name":"","phoneNum":"",'male':"男"};
    $scope.isMale=["男","女"];
    $scope.maleCompetitions=['单杠双杠','吊环','跳马','自由体操','鞍马','蹦床'];
    $scope.femaleCompetitions=['高低杠','平衡木','跳马','自由体操','蹦床'];
    $scope.athlete={
        "age": "",
        "competitionStr":"",
        "identityNum": "",
        "male": true,
        "name": "",
        "teamName": $scope.team.name
    };
    $scope.athletes=[];
    let ath={};
    angular.copy($scope.athlete,ath);
    $scope.athletes.push(ath);
    $scope.selectCompet=function(compet,x){
        x.competitionStr+=compet+" ";
    };
    $scope.addAthlete=function(){
        let a={};
        angular.copy($scope.athlete,a);
        $scope.athletes.push(a);
    };
    $scope.insert=function(){
        let num={"male0":0,"male1":0,"male2":0,"female0":0,"female1":0,"female2":0};
        $scope.coach.male=($scope.coach.male==="男");
        for(let i=0;i<$scope.athletes.length;i++){
            $scope.athletes[i].male=($scope.athletes[i].male==="男");
            $scope.athletes[i].team=$scope.team;
            if($scope.athletes[i].male){
                if($scope.athletes[i].age>=7&&$scope.athletes[i].age<=8){
                    num.male0++;
                }else if($scope.athletes[i].age>=9&&$scope.athletes[i].age<=10){
                    num.male1++;
                }else if($scope.athletes[i].age>=11&&$scope.athletes[i].age<=12){
                    num.male2++;
                }else{
                    alert("运动员年龄有不在7-12范围内，请检查");
                    return;
                }
            }else{
                if($scope.athletes[i].age>=7&&$scope.athletes[i].age<=8){
                    num.female0++;
                }else if($scope.athletes[i].age>=9&&$scope.athletes[i].age<=10){
                    num.female1++;
                }else if($scope.athletes[i].age>=11&&$scope.athletes[i].age<=12){
                    num.female2++;
                }else{
                    alert("运动员年龄有不在7-12范围内，请检查");
                    return;
                }
            }
        }
        for(let j in num){
            if(num[j]>6){
                alert("男女两个年龄组最多有六人，已超出，请检查");
                return;
            }
        }
        $scope.leader.team=$scope.team;
        $scope.coach.team=$scope.team;
        $scope.doctor.team=$scope.team;
        $scope.referee.team=$scope.team;
       $scope.data={"leader":$scope.leader,
                    "coach":$scope.coach,
                    "doctor":$scope.doctor,
                    "referee":$scope.referee,
                    "athletes":$scope.athletes
                    };
        $http({
            method: 'POST',
            url: $scope.teamUrl+'/all',
            data:$scope.data
        }).then(function successCallback(response) {
            alert("插入成功");
        })
    };

}]);