'use strict';
var resHomeModule = angular.module('HuntsboroApp');
resHomeModule.config(['$httpProvider',function($httpProvider){
	$httpProvider.defaults.useXDomain = true;
	$httpProvider.defaults.withCredentials = true;
	delete $httpProvider.defaults.headers.common["X-Requested-With"];
	$httpProvider.defaults.headers.common["Accept"] = "application/json";
	$httpProvider.defaults.headers.common["Content-Type"] = "application/json";
}]);

resHomeModule.controller('ResidentHomeController',ResidentHomeController);
ResidentHomeController.$inject=['$location'];

function ResidentHomeController($location) {
	
}