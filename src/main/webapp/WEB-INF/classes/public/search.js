function Search($scope, $http) {
    $http.get('http://localhost:8080/bicycles/search/serial/Defender').
    success(function(data) {
        $scope.searching = data;
    });
}