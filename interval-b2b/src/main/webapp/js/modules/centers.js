app.controller('CentersCtrl',
    function ($scope, centersFactory, $interval) {
        console.log('inside centers controller');
        $scope.newCenter = {};
        $scope.centers = null;
        $scope.gridApi = {};
        $scope.selectedItem = {};
        $scope.selectedScreen = {};
        $scope.disableEdit = false;
        $scope.checkboxSelection = '0';
        $scope.editItem = {};
        var columnDef = [
            {name : 'Name', field : 'name', enableColumnMenu: false},
            {name : 'Address', field : 'address1', enableColumnMenu: false},
            {name : 'Phone', field : 'phone', enableColumnMenu: false},
            {name : 'Email', field : 'email', enableColumnMenu: false},
            {name : 'Screens', enableColumnMenu: false, cellTemplate :
                "<div class='ui-grid-cell-contents'>" +
                "<span ng-repeat='screen in row.entity.screens'>{{screen.name}},</span>" +
                "</div>"}
        ];

        $scope.gridOpts = {
            columnDefs : columnDef,
            data : $scope.centers,
            gridMenuShowHideColumns: false,
            enableGridMenu: true,
            exporterMenuPdf: false,
            exporterCsvFilename: 'centers.csv',
            enableRowSelection: true,
            enableSelectAll: false,
            multiSelect : false,
            onRegisterApi : function (gridApi) {
                $scope.gridApi = gridApi;
                gridApi.selection.on.rowSelectionChanged($scope,function(row){
                    $scope.disableEdit = row.isSelected;
                    if(row.isSelected){
                        $scope.selectedItem = (row.entity);
                        $scope.editItem = angular.copy($scope.selectedItem);
                    }else{
                        $scope.clearSelectedCenter();
                    }
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
            console.log("updateCenter : ",$scope.editItem);
            centersFactory.update($scope.editItem, function(data){
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

        $scope.clearEditItem = function(){
            $scope.editItem = angular.copy($scope.selectedItem);
        }

        $scope.onEditScreen = function(screen,index){
            console.log(screen);
            $scope.selectedScreen = screen;
            $scope.checkboxSelection = index;
            if(!$scope.selectedScreen.shows){
                $scope.selectedScreen.shows = [];
            }
        }

        $scope.addScreen = function(){
            $scope.editItem.screens.push({createdTime : new Date(), updatedTime : new Date()});
        };

        $scope.addShow = function(){
            $scope.selectedScreen.shows.push({createdTime : new Date(), updatedTime : new Date(), time : new Date()});
        };

        $scope.deleteScreen = function(index){
            if(!$scope.editItem.deleteScreenList){
                $scope.editItem.deleteScreenList = [];
            }
            var screen = $scope.editItem.screens[index];
            if(screen && screen.id){
                $scope.editItem.deleteScreenList.push(screen.id);
            }
            $scope.editItem.screens.splice(index,1);
        }

        $scope.deleteShow = function(index){
            if(!$scope.editItem.deleteShowList){
                $scope.editItem.deleteShowList = [];
            }
            var show = $scope.selectedScreen.shows[index];
            if(show && show.id){
                $scope.editItem.deleteShowList.push(show.id);
            }
            $scope.selectedScreen.shows.splice(index,1);
        }

        $scope.load();

        $scope.isCheckboxSelected = function(index) {
            return index === $scope.checkboxSelection;
        };

    }
);