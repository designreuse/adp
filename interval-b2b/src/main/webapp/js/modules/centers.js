app.controller('CentersCtrl',
    function ($scope, centersFactory, $interval) {
        console.log('inside centers controller');
        $scope.newCenter = {};
        $scope.centers = null;
        $scope.gridApi = {};
        $scope.selectedItem = {};
        $scope.selectedScreen = {};
        $scope.disableEdit = false;
        var columnDef = [
            {name : 'Id', field : 'id'},
            {name : 'Name', field : 'name'},
            {name : 'Address', field : 'address1'},
            {name : 'Phone', field : 'phone'},
            {name : 'Email', field : 'email'},
            {name : 'Screens', cellFilter : 'formatScreens:row.entity', cellTemplate : "<span class='ui-grid-cell-contents' ng-repeat='screen in row.entity.screens'>{{screen.name}}</span>"}
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
                $scope.disableEdit = false;
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
            console.log("deleteCenter : ",$scope.selectedItem);
            centersFactory.delete({ id : $scope.selectedItem.id }, function(data){
                $scope.load();
            });
        }

        $scope.clearNewCenter = function(){
            $scope.newCenter = {};
        }

        $scope.clearSelectedCenter = function(){
            $scope.selectedItem = {};
            $scope.selectedScreen = {};
        }

        $scope.onEditScreen = function(screen){
            console.log(screen);
            $scope.selectedScreen = screen;
            if(!$scope.selectedScreen.shows){
                $scope.selectedScreen.shows = [];
            }
        }

        $scope.addScreen = function(){
            $scope.selectedItem.screens.push({});
        };

        $scope.addShow = function(){
            $scope.selectedScreen.shows.push({});
        };

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
            shows += "," + row.shows[index].time;
        }
        return shows;
    };
});

app.filter('formatScreens', function () {
    return function (value, row) {
        var screens = "", index;
        for	(index = 0; index < row.screens.length; index++) {
            screens += row.screens[index].name + (row.screens[index].shows.length);
        }
        return screens;
    };
});
