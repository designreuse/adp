app.controller('ProductCtrl',
    function ($scope, productFactory, categoriesFactory, centersFactory) {
        console.log('inside product controller');
        $scope.newProduct = {};
        $scope.products = null;
        $scope.categories = null;
        $scope.gridApi = {};
        $scope.selectedItem = {};
        $scope.disableEdit = false;
        var columnDef = [
            {name: 'Name', field: 'name'},
            {name: 'Description', field: 'description'},
            {name: 'Price', field: 'price'},
            {name: 'Active', field: 'active', cellFilter : 'formatBoolean:row.entity.active'}
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
            var image = document.getElementById('updImage').files[0],formData = new FormData();
            formData.append('imageFile', image);
            formData.append('imageFileName', image.name);
            formData.append('imageFileType', image.type);
            formData.append('product',  angular.toJson($scope.selectedItem));

            productFactory.uploadImage({id: $scope.selectedItem.id},formData, function (data) {
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

        $scope.loadCenters = function () {
            centersFactory.query(function (data) {
                $scope.centers = data;
            });
        }

        $scope.load();
        $scope.loadCategories();
        $scope.loadCenters();
    }
);

app.factory("productFactory", function ($resource) {
    return $resource('v1/product/:id', null,
        {
            'update': { method: 'PUT' },
            'uploadImage' : {method: 'POST', headers: {'Content-Type' : undefined}}
        });
})


