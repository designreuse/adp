app.controller('InventoryCtrl',
    function ($scope, inventoryFactory, categoriesFactory) {
        console.log('inside inventory controller');
        $scope.newInventory = {};
        $scope.inventories = null;
        $scope.categories = null;
        $scope.gridApi = {};
        $scope.selectedItem = {};
        $scope.disableEdit = false;
        var columnDef = [
            {name : 'Id', field : 'id'},
            {name : 'Product', field : 'product.name'},
            {name : 'Available Quantity', field : 'availableQuantity'},
            {name : 'Safety Stock', field : 'safetyStock'},
            {name : 'Availability', field : 'availability'}
        ];

        $scope.gridOpts = {
            columnDefs : columnDef,
            data : $scope.inventories,
            enableRowSelection: true,
            enableSelectAll: false,
            multiSelect : false,
            onRegisterApi : function (gridApi) {
                $scope.gridApi = gridApi;
                gridApi.selection.on.rowSelectionChanged($scope,function(row){
                    $scope.disableEdit = row.isSelected;
                    if(row.isSelected){
                        $scope.selectedItem = row.entity;
                    }else{
                        $scope.clearSelectedInventory();
                    }
                });
            }
        };

        $scope.load = function(){
            inventoryFactory.query(function(data){
                $scope.inventories = data;
                $scope.gridOpts.data = $scope.inventories;
                //$scope.gridApi.core.refresh();
                console.log("inventories : ",$scope.inventories);
                $scope.clearSelectedInventory();
                $scope.clearNewInventory();
                $scope.disableEdit = false;
            });
        }

        $scope.create = function(){
            inventoryFactory.save($scope.newInventory, function(data){
                $scope.load();
            });
        }

        $scope.update = function(){
            inventoryFactory.update($scope.selectedItem, function(data){
                $scope.load();
            });
        }

        $scope.delete = function(){
            inventoryFactory.delete({ id : $scope.selectedItem.id }, function(data){
                $scope.load();
            });
        }

        $scope.clearNewInventory = function(){
            $scope.newInventory = {};
        }

        $scope.clearSelectedInventory = function(){
            $scope.selectedItem = {};
        }

        $scope.loadCategories = function(){
            categoriesFactory.query(function(data){
                $scope.categories = data;
            });
        }

        $scope.load();
        $scope.loadCategories();
    }
);

app.factory("inventoryFactory", function ($resource) {
    return $resource('v1/inventory/:id', null,
        {
            'update': { method:'PUT' }
        });
})

