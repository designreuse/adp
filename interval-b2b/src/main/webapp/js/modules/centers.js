app.controller('CentersCtrl',
    function ($scope, centersFactory) {
        console.log('inside centers controller');
        $scope.newCenter = {};
        $scope.centers = null;
        $scope.gridApi = {};

        var columnDef = [
            {name : 'Id', field : 'id'},
            {name : 'Name', field : 'name'},
            {name : 'Address', field : 'address1'},
            {name : 'Phone', field : 'phone'},
            {name : 'Email', field : 'email'}
        ];

        $scope.gridOpts = {
            columnDefs : columnDef,
            data : $scope.centers,
            onRegisterApi : function (gridApi) {
                $scope.gridApi = gridApi;
            }
        };

        $scope.load = function(){
            centersFactory.query(function(data){
                $scope.centers = data;
                $scope.gridOpts.data = $scope.centers;
                //$scope.gridApi.core.refresh();
                console.log("centers : ",$scope.centers);
            });
        }

        $scope.create = function(){
            console.log("newCenter : ",$scope.newCenter);
            centersFactory.save($scope.newCenter);
            $scope.load();
        }

        $scope.update = function(){
            console.log("updateCenter : ",$scope.newCenter);
            centersFactory.update($scope.newCenter);
            $scope.load();
        }

        $scope.clear = function(){
            $scope.newCenter = {};
        }

        $scope.load();
    }
);

app.factory("centersFactory", function ($resource) {
    return $resource('v1/center/', null,
        {
            'update' : {method : 'PUT'}
        });
});
