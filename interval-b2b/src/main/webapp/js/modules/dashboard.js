app.controller('DashboardCtrl',
    function ($scope, dashboardFactory) {
        console.log('inside dashboard controller');

        $scope.load = function(){
            dashboardFactory.query(function(data){

            });
        }

        $scope.load();

    }
);

app.factory("dashboardFactory", function ($resource) {
    return $resource('v1/dashboard/:id', null,
        {
            'update': { method:'PUT' }
        });
})

