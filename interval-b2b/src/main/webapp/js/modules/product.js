app.controller('ProductCtrl',
    function ($scope, productFactory, categoriesFactory) {
        console.log('inside product controller');
        $scope.newProduct = {};
        $scope.products = null;
        $scope.categories = null;
        $scope.gridApi = {};
        $scope.selectedItem = {};
        $scope.disableEdit = false;
        var columnDef = [
            {name: 'Id', field: 'id'},
            {name: 'Name', field: 'name'},
            {name: 'Description', field: 'description'}
        ];

        $scope.gridOpts = {
            columnDefs: columnDef,
            data: $scope.products,
            enableRowSelection: true,
            enableSelectAll: false,
            multiSelect: false,
            onRegisterApi: function (gridApi) {
                $scope.gridApi = gridApi;
                gridApi.selection.on.rowSelectionChanged($scope, function (row) {
                    $scope.disableEdit = row.isSelected;
                    if (row.isSelected) {
                        $scope.selectedItem = row.entity;
                    } else {
                        $scope.clearSelectedProduct();
                    }
                });
            }
        };

        $scope.load = function () {
            productFactory.query(function (data) {
                $scope.products = data;
                $scope.gridOpts.data = $scope.products;
                //$scope.gridApi.core.refresh();
                console.log("products : ", $scope.products);
                $scope.clearSelectedProduct();
                $scope.clearNewProduct();
                $scope.disableEdit = false;
            });
        }

        $scope.create = function () {
            productFactory.save($scope.newProduct, function (data) {
                $scope.load();
            });
        }

        $scope.update = function () {
            productFactory.update($scope.selectedItem, function (data) {
                $scope.load();
            });
        }

        $scope.delete = function () {
            productFactory.delete({ id: $scope.selectedItem.id }, function (data) {
                $scope.load();
            });
        }

        $scope.clearNewProduct = function () {
            $scope.newProduct = {};
        }

        $scope.clearSelectedProduct = function () {
            $scope.selectedItem = {};
        }

        $scope.loadCategories = function () {
            categoriesFactory.query(function (data) {
                $scope.categories = data;
            });
        }

        $scope.load();
        $scope.loadCategories();
    }
);

app.factory("productFactory", function ($resource) {
    return $resource('v1/product/:id', null,
        {
            'update': { method: 'PUT' }
        });
})


