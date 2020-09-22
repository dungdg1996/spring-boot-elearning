const ROLES_ALL = "http://localhost:8080/api/roles";

function accessTokenHttpInterceptor($cookies) {
    return {
        request: function ($config) {
            const token = $cookies.get['token'];
            $config.headers['Authorization'] = 'Bearer ' + token;
            return $config;
        },
        response: function (response) {
            if (response.config.headers.access_token) {
                const token = response.config.headers.access_token;
                $cookies.put('token', token);
            }
            return response;
        }
    };
}

accessTokenHttpInterceptor.$inject = ['$cookies'];

function httpInterceptorRegistry($httpProvider) {
    $httpProvider.interceptors.push('accessTokenHttpInterceptor');
}

httpInterceptorRegistry.$inject = ['$httpProvider'];

const app = angular.module("dataRemote", []);

app.controller("role-all", function ($scope, $http) {
    $http.get(ROLES_ALL)
        .then(function (response) {
            $scope.roles = response.data;
        });
});

app.controller("role-one", function ($scope, $http, $location) {
    const roleId = $location.search("?").replace("id=");
    const url = ROLES_ALL + "/" + roleId;
    $http.get(url)
        .then(function (response) {
            $scope.role = response.data;
        });
});