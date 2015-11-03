angular.module('app.services', ["ngResource"])

.factory("categoriesFactory", function ($resource) {
    return $resource('v1/category/:id', null,
        {
            'update': { method:'PUT' }
        });
})

.factory("centersFactory", function ($resource) {
    return $resource('v1/center/:id', null,
        {
            'update' : {method : 'PUT'}
        });
})

.factory("inventoryFactory", function ($resource) {
    return $resource('v1/inventory/:id', null,
        {
            'update': { method:'PUT' },
            'getInventoryByCenter' : { url: 'v1/inventory/:id?type=:type', method: 'GET' ,isArray:'true'}
        });
})

.factory("orderStatusFactory", function ($resource) {
    return $resource('v1/orderStatus/:id', null,
        {
            'update': { method: 'PUT' }
        });
})

.factory("productFactory", function ($resource) {
    return $resource('v1/product/:id', null,
        {
            'update': { method: 'PUT' },
            'uploadImage' : {method: 'POST', headers: {'Content-Type' : undefined}},
            'getProductByCenter' : { url : 'v1/product/:id?type=:type', method: 'GET' ,isArray:'true'}
        });
})

.factory("orderFactory", function ($resource) {
    return $resource('v1/order/:id', null,
        {
            'updateStatus': { url : 'v1/order/:id?status=:status', method: 'PUT' },
            'getOrderByCenter' : { url : 'v1/order/:id?type=:type&status=:status', method: 'GET' ,isArray:'true'}
        });
})

.factory("dashboardFactory", function ($resource) {
    return $resource('v1/dashboard/', null,
        {
            'productCountByCenter': { url : 'v1/dashboard/products', method: 'GET' ,isArray:'true' },
            'orderCountByCenter': { url : 'v1/dashboard/orders', method: 'GET' ,isArray:'true' }
        });
})