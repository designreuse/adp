var app = angular.module('app', [])
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


app.controller('CategoriesCtrl', function ($scope, $compile) {
    console.log('inside categories controller');

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


