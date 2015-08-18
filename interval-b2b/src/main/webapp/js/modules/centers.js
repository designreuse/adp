app.controller('CentersCtrl', function ($scope, centersFactory) {
    $scope.centers = centersFactory.query();
    console.log('inside centers controller');
});

app.factory("centersFactory", function ($resource) {
    return $resource('v1/center/', null,
        {
            'update' : {method : 'PUT'}
        });
});
