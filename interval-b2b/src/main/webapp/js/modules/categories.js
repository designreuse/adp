app.controller('CategoriesCtrl',
    function ($scope, categoriesFactory) {
        console.log('inside categories controller');
        $scope.newCategory = {};
        $scope.categories = null;
        $scope.gridApi = {};

        var columnDef = [
            {name : 'Id', field : 'id'},
            {name : 'Name', field : 'name'},
            {name : 'Description', field : 'description'}
        ];

        $scope.gridOpts = {
            columnDefs : columnDef,
            data : $scope.categories,
            onRegisterApi : function (gridApi) {
                $scope.gridApi = gridApi;
            }
        };

        $scope.load = function(){
            categoriesFactory.query(function(data){
                $scope.categories = data;
                $scope.gridOpts.data = $scope.categories;
                //$scope.gridApi.core.refresh();
                console.log("categories : ",$scope.categories);
            });
        }

        $scope.create = function(){
            console.log("newCategory : ",$scope.newCategory);
            categoriesFactory.save($scope.newCategory);
            $scope.load();
        }

        $scope.update = function(){
            console.log("updateCategory : ",$scope.newCategory);
            categoriesFactory.update($scope.newCategory);
            $scope.load();
        }

        $scope.clear = function(){
            $scope.newCategory = {};
        }

        $scope.load();

    }
);

app.factory("categoriesFactory", function ($resource) {
    return $resource('v1/category/', null,
        {
            'update': { method:'PUT' }
        });
})

