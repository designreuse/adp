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
                    $scope.disableEdit = row.isSelected;
                    if(row.isSelected){
                        $scope.selectedItem = row.entity;
                    }else{
                        $scope.clearSelectedCategory();
                    }
                });
            }
        };

        $scope.load = function(){
            categoriesFactory.query(function(data){
                $scope.categories = data;
                $scope.gridOpts.data = $scope.categories;
                //$scope.gridApi.core.refresh();
                console.log("categories : ",$scope.categories);
                $scope.clearSelectedCategory();
                $scope.clearNewCategory();
            });
        }

        $scope.create = function(){
            categoriesFactory.save($scope.newCategory, function(data){
                $scope.load();
            });
        }

        $scope.update = function(){
            categoriesFactory.update($scope.selectedItem, function(data){
                $scope.load();
            });
        }

        $scope.delete = function(){
            categoriesFactory.delete({ id : $scope.selectedItem.id }, function(data){
                $scope.load();
            });
        }

        $scope.clearNewCategory = function(){
            $scope.newCategory = {};
        }

        $scope.clearSelectedCategory = function(){
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

