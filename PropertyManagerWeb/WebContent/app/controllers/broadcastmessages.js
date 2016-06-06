'use strict';
var msgModule = angular.module('HuntsboroApp');
msgModule.config(['$httpProvider',function($httpProvider){
	$httpProvider.defaults.useXDomain = true;
	$httpProvider.defaults.withCredentials = true;
	delete $httpProvider.defaults.headers.common["X-Requested-With"];
	$httpProvider.defaults.headers.common["Accept"] = "application/json";
	$httpProvider.defaults.headers.common["Content-Type"] = "application/json";
}]);

msgModule.controller('BroadcastMessagesController',BroadcastMessagesController);
BroadcastMessagesController.$inject=['$location', '$scope','BroadcastMessagesService'];

function BroadcastMessagesController($location, $scope, BroadcastMessagesService) {
		var vm = this;
		
	    retrieveMessages();
	    var messages=null;
	    
	    function retrieveMessages() {
	    	alert('Retrieveing messages'); 
            BroadcastMessagesService.getMessages(function (response) {
            //		alert('retrieved message -' + response);
            		messages=response;
            	//	alert(messages[0]);
                	$scope.messageText = messages[0].messageText;
             });
        };
	
};