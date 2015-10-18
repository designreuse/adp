app.controller('LoginCtrl', function ($scope, $rootScope,AUTH_EVENTS,AuthService) {
        $scope.credentials = {
            email: '',
            password: ''
        };
        $scope.invalidCredentials=false;
    $scope.login = function (credentials) {
        AuthService.login(credentials).then(function (user) {
            $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
            $scope.loadHome();
            $scope.setCurrentUser(user);
            $scope.setShowNav(true);
            $scope.setRoleId(user.role.id);
            $scope.setCenterId(user.vendor.center.id);
            $scope.setCenter(user.vendor.center);
        }, function () {
            $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
            $scope.invalidCredentials=true;
        });
    };
});

app.factory('AuthService', function ($http, Session) {
    var authService = {};
    var user={};
    authService.login = function (credentials) {
        return $http
            .post('v1/profile/login', credentials)
            .then(function (res) {
                user=res.data;
                Session.createSession(user.id,user.email,user.role.id);
                return user;
            });
    };
    authService.isAuthenticated = function () {
        return !!Session.userId;
    };
    authService.isAuthorized = function (authorizedRoles) {
        if (!angular.isArray(authorizedRoles)) {
            authorizedRoles = [authorizedRoles];
        }
        return (authService.isAuthenticated() &&
        authorizedRoles.indexOf(Session.userRole) !== -1);
    };
    return authService;
});
app.service('Session', function () {
    this.createSession = function (sessionId, userId, userRole) {
        this.id = sessionId;
        this.userId = userId;
        this.userRole = userRole;
    };
    this.destroy = function () {
        this.id = null;
        this.userId = null;
        this.userRole = null;
    };
})

app.constant('AUTH_EVENTS', {
    loginSuccess: 'auth-login-success',
    loginFailed: 'auth-login-failed',
    logoutSuccess: 'auth-logout-success',
    sessionTimeout: 'auth-session-timeout',
    notAuthenticated: 'auth-not-authenticated',
    notAuthorized: 'auth-not-authorized'
})

app.constant('USER_ROLES', {
    admin: '0',
    vendor: '1',
    customer: '2'
})