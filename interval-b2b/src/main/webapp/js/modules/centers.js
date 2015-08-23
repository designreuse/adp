app.controller('CentersCtrl',
    function ($scope, centersFactory) {
        console.log('inside centers controller');
        $scope.newCenter = {};
        $scope.centers = null;
        $scope.gridApi = {};
        $scope.selectedItem = {};

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
            enableRowSelection: true,
            enableSelectAll: false,
            multiSelect : false,
            onRegisterApi : function (gridApi) {
                $scope.gridApi = gridApi;
                gridApi.selection.on.rowSelectionChanged($scope,function(row){
                    $scope.selectedItem = row.entity;
                    console.log($scope.selectedItem);
                });
            }
        };

        $scope.load = function(){
            centersFactory.query(function(data){
                $scope.centers = data;
                $scope.gridOpts.data = $scope.centers;
                //$scope.gridApi.core.refresh();
                console.log("centers : ",$scope.centers);
                $scope.clear();
            });
        }

        $scope.create = function(){
            console.log("newCenter : ",$scope.newCenter);
            centersFactory.save($scope.newCenter, function(data){
                $scope.load();
            });
        }

        $scope.update = function(){
            console.log("updateCenter : ",$scope.selectedItem);
            centersFactory.update($scope.selectedItem, function(data){
                $scope.load();
            });
            $scope.load();
        }

        $scope.delete = function(){
            console.log("deleteCentre : ",$scope.selectedItem);
            centersFactory.delete({ id : $scope.selectedItem.id }, function(data){
                $scope.load();
            });
            $scope.load();
        }

        $scope.clear = function(){
            $scope.newCenter = {};
            $scope.selectedItem = {};
        }

        $scope.load();

    }
);

app.factory("centersFactory", function ($resource) {
    return $resource('v1/center/:id', null,
        {
            'update' : {method : 'PUT'}
        });
});
