app.controller('HomeCtrl',
    function ($scope){
        $scope.addScreen = function(){
            $scope.center.screens.push({createdTime : new Date(), updatedTime : new Date()});
        };
    });