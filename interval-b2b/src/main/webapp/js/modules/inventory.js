app.controller('InventoryCtrl',
    function ($scope, inventoryFactory, categoriesFactory, productFactory) {
        console.log('inside inventory controller');
        $scope.newInventory = {};
        $scope.inventories = null;
        $scope.categories = null;
        $scope.products = null;
        $scope.gridApi = {};
        $scope.selectedItem = {};
        $scope.disableEdit = false;
        $scope.editItem = {};
        var columnDef = [
            {name : 'Product', field : 'product.name', enableColumnMenu: false},
            {name : 'Available Quantity', field : 'availableQuantity', enableColumnMenu: false},
            {name : 'Safety Stock', field : 'safetyStock', enableColumnMenu: false},
            {name : 'Availability', field : 'availability', enableColumnMenu: false,cellFilter : 'formatBoolean:row.entity.availability'}
        ];

        $scope.gridOpts = {
            columnDefs : columnDef,
            data : $scope.inventories,
            gridMenuShowHideColumns: false,
            enableGridMenu: true,
            exporterMenuPdf: false,
            exporterCsvFilename: 'inventory.csv',
            enableRowSelection: true,
            enableSelectAll: false,
            multiSelect : false,
            onRegisterApi : function (gridApi) {
                $scope.gridApi = gridApi;
                gridApi.selection.on.rowSelectionChanged($scope,function(row){
                    $scope.disableEdit = row.isSelected;
                    if(row.isSelected){
                        $scope.selectedItem = row.entity;
                        $scope.editItem = angular.copy($scope.selectedItem);
                    }else{
                        $scope.clearSelectedInventory();
                    }
                });
            }
        };

        $scope.load = function() {
            if ($scope.checkForAdmin()) {
                inventoryFactory.query(function (data) {
                    $scope.inventories = data;
                    $scope.gridOpts.data = $scope.inventories;
                    //$scope.gridApi.core.refresh();
                    console.log("inventories : ", $scope.inventories);
                    $scope.clearSelectedInventory();
                    $scope.clearNewInventory();
                    $scope.disableEdit = false;
                });
            } else {
                inventoryFactory.getInventoryByCenter({id: $scope.centerId, type: 'center'}, function (data) {
                    $scope.inventories = data;
                    $scope.gridOpts.data = $scope.inventories;
                    //$scope.gridApi.core.refresh();
                    console.log("getting inventories by centerID:", $scope.centerId);
                    console.log("inventories  : ", $scope.inventories);
                    $scope.clearSelectedInventory();
                    $scope.clearNewInventory();
                    $scope.disableEdit = false;
                });
            }
        }

        $scope.create = function () {
            inventoryFactory.save($scope.newInventory, function (data) {
                $scope.load();
            });
        }

        $scope.update = function () {
            inventoryFactory.update($scope.editItem, function (data) {
                $scope.load();
            });
        }

        $scope.delete = function () {
            inventoryFactory.delete({id: $scope.selectedItem.id}, function (data) {
                $scope.load();
            });
        }

        $scope.clearNewInventory = function () {
            $scope.newInventory = {};
        }

        $scope.clearSelectedInventory = function () {
            $scope.selectedItem = {};
        }

        $scope.loadCategories = function () {
            categoriesFactory.query(function (data) {
                $scope.categories = data;
            });
        }

        $scope.loadProducts = function () {
            if ($scope.checkForAdmin()) {
                productFactory.query(function (data) {
                    $scope.products = data;
                });
            } else {
                productFactory.getProductByCenter({id: $scope.centerId, type: 'center'}, function (data) {
                    $scope.products = data;
                });
            }
        }
        $scope.clearEditItem = function () {
            $scope.editItem = angular.copy($scope.selectedItem);
        }

        $scope.load();
        $scope.loadCategories();
        $scope.loadProducts();

    });


