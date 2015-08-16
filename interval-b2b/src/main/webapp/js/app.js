var app = angular.module('app', ["ngRoute","ngResource"])
    .config(function ($routeProvider, $locationProvider, $httpProvider) {

        $routeProvider.when('/home',
            {
                templateUrl: '/src/Navigation/home.html',
                controller: 'HomeCtrl'
            });
        $routeProvider.when('/about',
            {
                templateUrl: 'src/Navigation/about.html',
                controller: 'AboutCtrl'
            });
        $routeProvider.when('/contact',
            {
                templateUrl: 'src/Navigation/contact.html',
                controller: 'ContactCtrl'
            });
        $routeProvider.when('/categories',
            {
                templateUrl: '/src/Navigation/categories.html',
                controller: 'CategoriesCtrl'
            });
        $routeProvider.when('/centers',
            {
                templateUrl: '/src/Navigation/centers.html',
                controller: 'CentersCtrl'
            });
        $routeProvider.when('/dashboard',
            {
                templateUrl: '/src/Navigation/dashboard.html',
                controller: 'DashboardCtrl'
            });
        $routeProvider.when('/sales',
            {
                templateUrl: '/src/Navigation/sales.html',
                controller: 'SalesCtrl'
            });
        $routeProvider.when('/services',
            {
                templateUrl: '/src/Navigation/services.html',
                controller: 'ServicesCtrl'
            });
        $routeProvider.otherwise(
            {
                redirectTo: '/home',
                controller: 'HomeCtrl'
            }
        );
    });

app.controller('NavCtrl',
    ['$scope', '$location', function ($scope, $location) {
        $scope.navClass = function (page) {
            var currentRoute = $location.path().substring(1) || 'home';
            return page === currentRoute ? 'active' : '';
        };

        $scope.loadHome = function () {
            $location.url('/home');
        };

        $scope.loadAbout = function () {
            $location.url('/about');
        };

        $scope.loadContact = function () {
            $location.url('/contact');
        };

        $scope.loadCategories = function () {
            $location.url('/categories');
        };

        $scope.loadCenters = function () {
            $location.url('/centers');
        };

    }]);

app.controller('AboutCtrl', function ($scope, $compile) {
    console.log('inside about controller');

});

app.controller('HomeCtrl', function ($scope, $compile) {
    console.log('inside home controller');

});

app.controller('ContactCtrl', function ($scope, $compile) {
    console.log('inside contact controller');
});


app.controller('CategoriesCtrl', function ($scope, categoriesFactory) {
    $scope.categories = categoriesFactory.query();
    console.log('inside categories controller');
});

app.controller('CentersCtrl', function ($scope, centersFactory) {
    $scope.centers = centersFactory.query();
    console.log('inside centers controller');
});

app.controller('DashboardCtrl', function ($scope, $compile) {
    console.log('inside dashboard controller');

});

app.controller('SalesCtrl', function ($scope, $compile) {
    console.log('inside sales controller');

});

app.controller('ServicesCtrl', function ($scope, $compile) {
    console.log('inside Services controller');
});

app.factory("categoriesFactory", function ($resource) {
    return $resource('v1/category/');
});

app.factory("centersFactory", function ($resource) {
    return $resource('v1/center/');
});