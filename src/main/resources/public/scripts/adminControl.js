var API_index = angular.module("API_index");
API_index.controller("adminControlCtrl", ['$scope', '$http', '$state' ,function ($scope, $http, $state) {
    $scope.goto=function(page){
        if(page!=='auto'){
            $state.go(page);
        }else{
            $http({
                method: 'GET',
                url: $scope.gradesUrl+'/autoCalculate'
            }).then(function successCallback(response) {
                if(response.data===true){
                    alert('计算成功!!');
                    $state.go("personalRank");
                }
            });
        }
    };
}]);