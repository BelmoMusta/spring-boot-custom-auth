    var app = angular.module("securityExample", ['ui.router','ngCookies'/*,'permission','permission.ui'*/,'cr.acl','angular-oauth2']);
 // If we implement the basic security in spring boot then the response will
 // contains the header 'WWW-Authenticate: Basic'. So the browser will popup a
 // alert to get the user credentials. To avoid that we have to set a header in
 // every request we are making using AngularJs.
    app.config(function ($stateProvider, $locationProvider, $urlRouterProvider, $urlMatcherFactoryProvider,$httpProvider,OAuthProvider) {
        /*$locationProvider.html5Mode(true);*/
        $urlMatcherFactoryProvider.caseInsensitive(true);
     // $urlRouterProvider.otherwise('/login');
        $urlRouterProvider.otherwise( function($injector) {
            var $state = $injector.get("$state");
            $state.go('login');
        });
  	//$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
       /* $urlRouterProvider.otherwise(function ($injector, $location) {
            $injector.invoke(['$state', function ($state) { $state.go('error'); }]);
            return true;
        });*/
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
        $httpProvider.defaults.headers.post['Authorization'] =
            'Basic ' + btoa("fooClientIdPassword:secret");
        OAuthProvider.configure({
            baseUrl: 'http://localhost:8081/',
            clientId: 'fooClientIdPassword',
            clientSecret: 'secret',// optional
            grantPath: '/oauth/token?scope=read'
        });
        $stateProvider
        .state('login', {
            url: '/login',
            templateUrl: 'pages/login.html',
           /* resolve: {
                skipIfAuthenticated: _skipIfAuthenticated
            }*/
        })
            .state('error', {
                url: '/error',
                templateUrl: 'pages/error.html',
                /* resolve: {
                 skipIfAuthenticated: _skipIfAuthenticated
                 }*/
            })
        .state('helloAdmin', {
            url: '/helloAdmin',
            templateUrl: 'pages/helloAdmin.html',
            controller: 'helloController',
            controllerUrl:'controllers/helloController',
           /* resolve: {
                redirectIfNotAuthenticated: _redirectIfNotAuthenticated
            }*/
            /*data: {
                permissions: {
                    only: 'ADMIN'
                }
            }*/
            data:{
                is_granted: ["ROLE_ADMIN"]
            }
        })
        .state('helloUser', {
            url: '/helloUser',
            templateUrl: 'pages/helloUser.html',
            controller: 'helloController',
            controllerUrl:'controllers/helloController',
            /*resolve: {
                redirectIfNotAuthenticated: _redirectIfNotAuthenticated
            }*/
            /*data: {
                permissions: {
                    only: ['ADMIN', 'USER']
                }
            }*/
            data:{
                is_granted: ["ROLE_USER"]
            }
        })
    });
    app.run(['crAcl', function run(crAcl) {
        crAcl.setInheritanceRoles({

            "ROLE_ADMIN" : ["ROLE_USER"]

        });
        crAcl.setRedirect("error");
    }])
       /* app.run(['$rootScope', '$window', 'OAuth', function($rootScope, $window, OAuth) {
            $rootScope.$on('oauth:error', function(event, rejection) {
                // Ignore `invalid_grant` error - should be catched on `LoginController`.
                if ('invalid_grant' === rejection.data.error) {
                    return;
                }

                // Refresh token when a `invalid_token` error occurs.
                if ('invalid_token' === rejection.data.error) {
                    return OAuth.getRefreshToken();
                }

            });
        }])*/
