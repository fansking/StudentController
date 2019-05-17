var API_index = angular.module("API_index");
API_index.controller("personalRankCtrl", ['$scope', '$http', '$state' ,function ($scope, $http, $state) {
    console.log("enter1")
    $scope.getData = function () {
        $http({
            method: 'GET',
            url: $scope.gradesUrl + '/allRank'
        }).then(function successCallback(response) {
            $scope.competitions = response.data
            console.log(response)
        });
    };
    $scope.getData();
    console.log("enter")
}]);