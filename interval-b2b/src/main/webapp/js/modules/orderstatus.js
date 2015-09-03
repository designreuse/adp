app.controller('OrderStatusCtrl',
    function ($scope, orderStatusFactory) {
        console.log('inside orderStatus controller');
        $scope.newOrderStatus = {};
        $scope.status = null;
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
            data: $scope.status,
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
                        $scope.clearSelectedOrderStatus();
                    }
                });
            }
        };

        $scope.load = function () {
            orderStatusFactory.query(function (data) {
                $scope.status = data;
                $scope.gridOpts.data = $scope.status;
                //$scope.gridApi.core.refresh();
                console.log("Status : ", $scope.status);
                $scope.clearSelectedOrderStatus();
                $scope.clearNewOrderStatus();
                $scope.disableEdit = false;
            });
        }

        $scope.create = function () {
            orderStatusFactory.save($scope.newOrderStatus, function (data) {
                $scope.load();
            });
        }

        $scope.update = function () {
            orderStatusFactory.update($scope.selectedItem, function (data) {
                $scope.load();
            });
        }

        $scope.delete = function () {
            orderStatusFactory.delete({ id: $scope.selectedItem.id }, function (data) {
                $scope.load();
            });
        }

        $scope.clearNewOrderStatus = function () {
            $scope.newOrderStatus = {};
        }

        $scope.clearSelectedOrderStatus = function () {
            $scope.selectedItem = {};
        }

        $scope.load();

    }
);

app.factory("orderStatusFactory", function ($resource) {
    return $resource('v1/orderStatus/:id', null,
        {
            'update': { method: 'PUT' }
        });
})

