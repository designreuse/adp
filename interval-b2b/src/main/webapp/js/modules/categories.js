app.controller('CategoriesCtrl',
    function ($scope, categoriesFactory) {
        console.log('inside categories controller');
        $scope.newCategory = {};
        $scope.categories = null;
        $scope.gridApi = {};
        $scope.selectedItem = {};
        $scope.disableEdit = false;
        var columnDef = [
            {name : 'Id', field : 'id'},
            {name : 'Name', field : 'name'},
            {name : 'Description', field : 'description'}
        ];

        $scope.gridOpts = {
            columnDefs : columnDef,
            data : $scope.categories,
            enableRowSelection: true,
            enableSelectAll: false,
            multiSelect : false,
            onRegisterApi : function (gridApi) {
                $scope.gridApi = gridApi;
                gridApi.selection.on.rowSelectionChanged($scope,function(row){
                    $scope.selectedItem = row.entity;
                    $scope.disableEdit = row.isSelected;
                    console.log($scope.selectedItem);
                    console.log(row.isSelected);
                });
            }
        };

        $scope.load = function(){
            categoriesFactory.query(function(data){
                $scope.categories = data;
                $scope.gridOpts.data = $scope.categories;
                //$scope.gridApi.core.refresh();
                console.log("categories : ",$scope.categories);
                $scope.clear();
            });
        }

        $scope.create = function(){
            console.log("newCategory : ",$scope.newCategory);
            categoriesFactory.save($scope.newCategory, function(data){
                $scope.load();
            });

        }

        $scope.update = function(){
            console.log("updateCategory : ",$scope.selectedItem);
            categoriesFactory.update($scope.selectedItem, function(data){
                $scope.load();
            });
            $scope.load();
        }

        $scope.delete = function(){
            console.log("deleteCategory : ",$scope.selectedItem);
            categoriesFactory.delete({ id : $scope.selectedItem.id }, function(data){
                $scope.load();
            });
            $scope.load();
        }

        $scope.clear = function(){
            $scope.newCategory = {};
            $scope.selectedItem = {};
        }

        $scope.load();

    }
);

app.factory("categoriesFactory", function ($resource) {
    return $resource('v1/category/:id', null,
        {
            'update': { method:'PUT' }
        });
})

