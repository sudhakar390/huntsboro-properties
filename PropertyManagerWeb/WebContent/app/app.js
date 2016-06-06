
    'use strict';
 
    angular
        .module('HuntsboroApp', ['ngRoute', 'ngCookies'])
        .config(config)
        .run(run);
    
    config.$inject = ['$routeProvider', '$locationProvider','$httpProvider'];
    function config($routeProvider, $locationProvider,$httpProvider) {
    	$httpProvider.defaults.useXDomain = true;
    	$httpProvider.defaults.withCredentials = true;
    	delete $httpProvider.defaults.headers.common["X-Requested-With"];
    	$httpProvider.defaults.headers.common["Accept"] = "application/json";
    	$httpProvider.defaults.headers.common["Content-Type"] = "application/json";
    	
        $routeProvider
           /* .when('/', {
                controller: 'HomeController',
                templateUrl: 'resident-home.html',
                controllerAs: 'vm'
            })*/
 
            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'login.html',
                controllerAs: 'vm'
            })
 
            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'register.html',
                controllerAs: 'vm'
            })
            
            .when('/maintenance', {
                controller: 'MaintenanceRequestController',
                templateUrl: 'maintenance.html'
                //controllerAs: 'vm'
            })
            .when('/residenthome', {
                controller: 'ResidentHomeController',
                templateUrl: 'resident-home.html',
                controllerAs: 'vm'
            })
            .when('/contactus', {
                controller: 'ContactUsController',
                templateUrl: 'contactus.html'
               // controllerAs: 'vm'
            })
      /*       .when('/features', {
                controller: 'FeaturesController',
                templateUrl: 'features.html'
               // controllerAs: 'vm'
            })
      */      .when('/aboutus', {
                controller: 'AboutUsController',
                templateUrl: 'about-us.html'
               // controllerAs: 'vm'
            })
            .when('/floorplans-search', {
                controller: 'FloorplansearchCtrl',
                templateUrl: 'floorplans-search.html'
               // controllerAs: 'vm'
            })
            .otherwise({ redirectTo: '/login' });
    }
 
    run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];
    function run($rootScope, $location, $cookieStore, $http) {
    	//alert($location.path());
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }
 
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
        	
        	// redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/register','/contactus','/features','/aboutus','floorplans.html','index.html','photogallery.html']) === -1;
          //  alert('restrictedPage- ' + restrictedPage);
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                $location.path('/login');
            }
        });
    }
 
