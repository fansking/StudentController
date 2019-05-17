var API_index = angular.module("API_index");
// 主页控制器
API_index.controller("logInCtrl", ['$scope', '$http', '$state' ,function ($scope, $http, $state) {
    $http({
        method: 'GET',
        url: $scope.refereeUrl
    }).then(function successCallback(response) {
        $scope.referees=response.data;
    });
    $scope.suFlag=false;
    $scope.account="";
    $scope.passWords="";
    $scope.status="管理员";
    $scope.select=function (x) {
        $scope.status=x;
    };

    $scope.logIn=function(){
        if($scope.status==='管理员'){
            if($scope.account==='superUser'&&$scope.passWords==='sudo'){
                alert("登陆成功");
                // $scope.suFlag=true;
                $state.go('adminControl');
            }else{
                alert('账号密码不正确');
            }
        }else if($scope.status==='领队'){
            // if($scope.teams[i].account===$scope.account&&$scope.teams[i].passWord===$scope.passWords){
            //     alert("登陆成功");
            //     $state.go('main',{team:$scope.teams[i]});
            //     return;
            // for(let i=0;i<$scope.teams.length;i++){
            //     }
            // }
            $http({
                method: 'GET',
                url: $scope.loginUrl+'/leader'+'/'+$scope.account+'/'+$scope.passWords,
            }).then(function successCallback(response) {
                if (response.data != null){
                    alert("登陆成功");
                    $state.go('main',{team:response.data});
                } else {
                    alert("账号或密码不正确，请检查")
                }
            });
        }else{
            // for(let i=0;i<$scope.referees.length;i++){
            //     if($scope.referees[i].team.account===$scope.account&&$scope.referees[i].name===$scope.passWords){
            //         alert("登陆成功");
            //         $state.go('judge',{referee:$scope.referees[i]});
            //         return;
            //     }
            // }
            // alert("账号或密码不正确，请检查")
            $http({
                method: 'GET',
                url: $scope.loginUrl+'/referee'+'/'+$scope.account+'/'+$scope.passWords,
            }).then(function successCallback(response) {
                if (response.data != null){
                    alert("登陆成功");
                    $state.go('judge',{referee:response.data});
                } else {
                    alert("账号或密码不正确，请检查")
                }
            });
        }
    }

    $scope.goto=function(page){
        if(page!=='auto'){
            $state.go(page);
        }else{
            $http({
                method: 'GET',
                url: $scope.gradesUrl+'/autoCalculate'
            }).then(function successCallback(response) {
                if(response.data===true){
                    alert('计算成功，请到数据库查看');
                }
            });
        }
    };

    $scope.keypress = function (e) {
        var keycode = window.event ? e.keyCode : e.which;
        if (keycode == 13){
            $scope.logIn()
        }
    }
}]);