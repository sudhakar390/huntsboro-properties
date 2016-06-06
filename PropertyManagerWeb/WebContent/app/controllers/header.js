'use strict';
var headModule = angular.module('HuntsboroApp');
headModule.config(['$httpProvider',function($httpProvider){
	$httpProvider.defaults.useXDomain = true;
	$httpProvider.defaults.withCredentials = true;
	delete $httpProvider.defaults.headers.common["X-Requested-With"];
	$httpProvider.defaults.headers.common["Accept"] = "application/json";
	$httpProvider.defaults.headers.common["Content-Type"] = "application/json";
}]);

headModule.controller('HeaderController',['$scope','$http','$location', function ($scope,$http,$location){
		/*$scope.getClass = function(path) {
			alert($location.path() + 'path-'+ path);
		  if ($location.path().substr(0, path.length) === path) {
		    return 'active';
		  } else {
		    return '';
		  }
		}*/
}]);