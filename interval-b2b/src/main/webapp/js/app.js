var app = angular.module('app', ["ngRoute","ngResource","ui.grid","ui.grid.selection","ui.bootstrap","angularValidator"])
    .config(function ($routeProvider, $locationProvider, $httpProvider) {

        $routeProvider.when('/home',
            {
                templateUrl: 'templates/home.html',
                controller: 'HomeCtrl'
            });
        $routeProvider.when('/about',
            {
                templateUrl: 'templates/about.html',
                controller: 'AboutCtrl'
            });
        $routeProvider.when('/contact',
            {
                templateUrl: 'templates/contact.html',
                controller: 'ContactCtrl'
            });
        $routeProvider.when('/categories',
            {
                templateUrl: 'templates/categories.html',
                controller: 'CategoriesCtrl'
            });
        $routeProvider.when('/centers',
            {
                templateUrl: 'templates/centers.html',
                controller: 'CentersCtrl'
            });
        $routeProvider.when('/orderStatus',
            {
                templateUrl: 'templates/orderstatus.html',
                controller: 'OrderStatusCtrl'
            });
        $routeProvider.when('/product',
            {
                templateUrl: 'templates/product.html',
                controller: 'ProductCtrl'
            });
        $routeProvider.when('/inventory',
            {
                templateUrl: 'templates/inventory.html',
                controller: 'InventoryCtrl'
            });
        $routeProvider.when('/dashboard',
            {
                templateUrl: 'templates/dashboard.html',
                controller: 'DashboardCtrl'
            });
        $routeProvider.when('/sales',
            {
                templateUrl: 'templates/sales.html',
                controller: 'SalesCtrl'
            });
        $routeProvider.when('/services',
            {
                templateUrl: 'templates/services.html',
                controller: 'ServicesCtrl'
            });
        $routeProvider.when('/login',
            {
                templateUrl: 'templates/login.html',
                controller: 'LoginCtrl'
            });
        $routeProvider.otherwise(
            {
                redirectTo: '/login',
                controller: 'LoginCtrl'
            }
        );
    });
app.controller('NavCtrl', ['$scope', '$location', function ($scope, $location,USER_ROLES,AuthService) {

    $scope.showNav = false;
    $scope.currentUser = {};
    $scope.userRoles = USER_ROLES;
    $scope.roleId = null;
    $scope.centerId = null;
    $scope.center = null;
    // $scope.isAuthorized = AuthService.isAuthorized;
    $scope.setCurrentUser = function (user) {
        $scope.currentUser = user;
    };
    $scope.setShowNav = function (nav) {
        $scope.showNav = nav;
    };
    $scope.setRoleId = function (roleId) {
        $scope.roleId = roleId;
    };
    $scope.setCenterId = function (centerId) {
        $scope.centerId = centerId;
    };
    $scope.setCenter = function (center) {
        $scope.center = center;
    };

    $scope.navClass = function (page) {
        var currentRoute = $location.path().substring(1) || 'home';
        return page === currentRoute ? 'active' : '';
    };

    $scope.loadHome = function () {
        if($scope.center != null){
            $scope.pageHeader = $scope.center.name;
        }else{
            $scope.pageHeader = "Home";
        }
        $location.url('/home');
    };

    $scope.loadDashboard = function () {
        $scope.pageHeader = "Dashboard";
        $location.url('/dashboard');
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

    $scope.loadOrderStatus = function () {
        $scope.pageHeader = "Order Status";
        $location.url('/orderStatus');
    }

    $scope.loadProduct = function () {
        $scope.pageHeader = "Product";
        $location.url('/product');
    }

    $scope.loadInventory = function () {
        $scope.pageHeader = "Inventory";
        $location.url('/inventory');
    }

    $scope.checkForAdmin = function (){
        if($scope.roleId == 1){
            return true;
        }else{
            return false;
        }
    }

    $scope.checkForVendor = function (){
        if($scope.roleId == 2){
            return true;
        }else{
            return false;
        }
    }

    $scope.checkForVendorAdmin = function (){
        if($scope.roleId <= 2){
            return true;
        }else{
            return false;
        }
    }

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

app.directive('convertToNumber', function() {
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, ngModel) {
            ngModel.$parsers.push(function(val) {
                return parseInt(val, 10);
            });
            ngModel.$formatters.push(function(val) {
                return '' + val;
            });
        }
    };
});

app.filter('formatBoolean', function () {
    return function (value, fieldVal) {
        var value = "";
        if(fieldVal == true){
            value = "Yes";
        }else if(fieldVal == false){
            value = "No";
        }
        return value;
    };
});

