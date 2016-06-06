'use strict';
var loginModule = angular.module('HuntsboroApp');
loginModule.config(['$httpProvider',function($httpProvider){
	$httpProvider.defaults.useXDomain = true;
	$httpProvider.defaults.withCredentials = true;
	delete $httpProvider.defaults.headers.common["X-Requested-With"];
	$httpProvider.defaults.headers.common["Accept"] = "application/json";
	$httpProvider.defaults.headers.common["Content-Type"] = "application/json";
}]);

loginModule.controller('LoginController',LoginController);
LoginController.$inject=['$location', 'AuthenticationService'];

function LoginController($location, AuthenticationService) {
		var vm = this;
		 
	    vm.login = login;
	
	    (function initController() {
	        // reset login status
	        AuthenticationService.ClearCredentials();
	    })();
	
	    function login() {
            vm.dataLoading = true;
            AuthenticationService.Login(vm.username, vm.password, function (response) {
                if (response.success) {
                    AuthenticationService.SetCredentials(vm.username, vm.password);
                    $location.path('/residenthome');
                } else {
                	alert('Username or Password is incorrect.')
                    vm.dataLoading = false;
                }
            });
        };
	
};