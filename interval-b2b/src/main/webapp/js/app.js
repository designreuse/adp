var app = angular.module('app', ["ngRoute","ngResource","ui.grid"])
    .config(function ($routeProvider, $locationProvider, $httpProvider) {

        $routeProvider.when('/home',
            {
                templateUrl: '/templates/home.html',
                controller: 'HomeCtrl'
            });
        $routeProvider.when('/about',
            {
                templateUrl: '/templates/about.html',
                controller: 'AboutCtrl'
            });
        $routeProvider.when('/contact',
            {
                templateUrl: '/templates/contact.html',
                controller: 'ContactCtrl'
            });
        $routeProvider.when('/categories',
            {
                templateUrl: '/templates/categories.html',
                controller: 'CategoriesCtrl'
            });
        $routeProvider.when('/centers',
            {
                templateUrl: '/templates/centers.html',
                controller: 'CentersCtrl'
            });
        $routeProvider.when('/dashboard',
            {
                templateUrl: '/templates/dashboard.html',
                controller: 'DashboardCtrl'
            });
        $routeProvider.when('/sales',
            {
                templateUrl: '/templates/sales.html',
                controller: 'SalesCtrl'
            });
        $routeProvider.when('/services',
            {
                templateUrl: '/templates/services.html',
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
            $scope.pageHeader = "Home";
            $location.url('/home');
        };

        $scope.loadAbout = function () {
            $scope.pageHeader = "About";
            $location.url('/about');
        };

        $scope.loadContact = function () {
            $scope.pageHeader = "Contact";
            $location.url('/contact');
        };

        $scope.loadCategories = function () {
            $scope.pageHeader = "Categories";
            $location.url('/categories');
        };

        $scope.loadCenters = function () {
            $scope.pageHeader = "Centers";
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

app.controller('DashboardCtrl', function ($scope, $compile) {
    console.log('inside dashboard controller');
});

app.controller('SalesCtrl', function ($scope, $compile) {
    console.log('inside sales controller');
});

app.controller('ServicesCtrl', function ($scope, $compile) {
    console.log('inside Services controller');
});

