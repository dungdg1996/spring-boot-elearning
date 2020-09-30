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

const app = angular.module("app", ["ngRoute"]);

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
app.controller('category-all', function ($scope, $http) {
    $http.get("/api/categories")
        .then(function (response) {
            $scope.categories = response.data;
        });
});
app.controller('courses', function ($scope, $http) {
    $http.get("/api/courses")
        .then(function (response) {
            $scope.courses = response.data;
        });
});

app.controller(function ($scope, $http, $cookies) {
    $scope.login = function () {
        $http.post("/api/auth/login",
            {
                "email": $scope.email,
                "password": $scope.password
            })
            .then(function (response) {
                $cookies.put('token', response.data);
            });
    }
});
app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "../index-fr.html"
        })
        .when("/details", {
            templateUrl: "../details-fr.html"
        })
        .when("/course", {
            templateUrl: "../course-fr.html"
        })
        .when("/profile", {
            templateUrl: "../profile-fr.html"
        })
    ;
});
