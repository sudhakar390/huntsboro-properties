    'use strict';
    angular.module('HuntsboroApp').factory('BroadcastMessagesService', BroadcastMessagesService);
 
    BroadcastMessagesService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout'];
    function BroadcastMessagesService($http, $cookieStore, $rootScope, $timeout) {
        var service = {};
        
        service.getMessages = getMessages;
        return service;
        
        function getMessages(callback) 
        {
            	var urlBase='http://localhost:9090/PropertyAdminServices/';
  
            	var req = {
       				 method: 'GET',
       				 url: urlBase +"tenantmessages/",
       				 headers: {
       				   'Content-Type': 'application/json',
       				   'Access-Control-Allow-Origin':'http://localhost:8082'
       				 }
       				};
            	

            	$http.get(urlBase+"tenantmessages/").success(function (data) {
            		//alert('successful -' + data);
                	callback(data);   
                  })
                   .error(function(error){
                	  // alert(error);
    	            	callback(error);   
                  });
            	
        }
        
      }   