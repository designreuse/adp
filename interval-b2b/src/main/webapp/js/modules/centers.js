app.controller('CentersCtrl',
    function ($scope, centersFactory, $interval) {
        console.log('inside centers controller');
        $scope.newCenter = {};
        $scope.centers = null;
        $scope.gridApi = {};
        $scope.selectedItem = {};
        $scope.disableEdit = false;
        var columnDef = [
            {name : 'Id', field : 'id'},
            {name : 'Name', field : 'name'},
            {name : 'Address', field : 'address1'},
            {name : 'Phone', field : 'phone'},
            {name : 'Email', field : 'email'}
        ];
        var screensColumnDef = [
            {name : 'Id', field : 'id'},
            {name : 'Name', field : 'name'},
            {name : 'Shows', cellFilter : 'formatShows:row.entity'}
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
                    $scope.disableEdit = row.isSelected;
                    if(row.isSelected){
                        $scope.selectedItem = row.entity;
                        $scope.editScreensGridOpts.data = $scope.selectedItem.screens;
                    }else{
                        $scope.clearSelectedCenter();
                    }
                });
            }
        };

        $scope.editScreensGridOpts = {
            columnDefs : screensColumnDef,
            data : $scope.selectedItem.screens,
            enableRowSelection: true,
            enableSelectAll: false,
            multiSelect : false,
            onRegisterApi : function (gridApi) {
                $scope.editScreenGridApi = gridApi;
                gridApi.selection.on.rowSelectionChanged($scope,function(row){

                });
            }
        };

        $scope.load = function(){
            centersFactory.query(function(data){
                $scope.centers = data;
                $scope.gridOpts.data = $scope.centers;
                //$scope.gridApi.core.refresh();
                console.log("centers : ",$scope.centers);
                $scope.clearSelectedCenter();
                $scope.clearNewCenter();
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
        }

        $scope.delete = function(){
            console.log("deleteCentre : ",$scope.selectedItem);
            centersFactory.delete({ id : $scope.selectedItem.id }, function(data){
                $scope.load();
            });
        }

        $scope.onEdit = function(){
            $interval( function() {
                $scope.editScreenGridApi.core.handleWindowResize();
            }, 10, 500);
        }

        $scope.clearNewCenter = function(){
            $scope.newCenter = {};
        }

        $scope.clearSelectedCenter = function(){
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

app.filter('formatShows', function () {
    return function (value, row) {
        var shows = "", index;
        for	(index = 0; index < row.shows.length; index++) {
            shows += row.shows[index].time;
        }
        return shows;
    };
});