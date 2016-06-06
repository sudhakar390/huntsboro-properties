'use strict';
 
    angular
        .module('HuntsboroApp')
        .controller('RegisterController', RegisterController);
 
    RegisterController.$inject = ['UserService', '$location', '$rootScope','$scope','$http'];
    function RegisterController(UserService, $location, $rootScope,$scope,$http) {
        var vm = this;
        vm.user={};
        
        vm.register = register;
        vm.getPropertiesList= getPropertiesList;
        vm.getUnitsByProperty=getUnitsByProperty;
        
        getPropertiesList();
        
        function sendMail(userobj){
        	
			var emailData= {
				 'msgText' : 'Thank you ' + userobj.firstName + ' ' + userobj.lastName + ' for registering on the Resident Portal. \n\n\n\n\n Huntsboro Properties\nPO Box 43 \n\nStephenville, TX 76401\nwww.huntsboro-properties.com\ninfo@huntsboro-properties.com\n Ph: 254-592-6925\n mgr@huntsboro-properties.com\nPh: 254-592-2122',
				 'subject': 'Registration succesful ',
				 'mailTo': userobj.email,
				 'mailFrom' : 'info@huntsboro-properties.com'
		 };
//			alert(emailData.msgText);
			 $http({
		        method  : 'POST',
		        url     : 'http://localhost:9090/PropertyAdminServices/email/',
		        data    : emailData,  
		        headers: {
					   'Content-Type': 'application/json',
					   'Access-Control-Allow-Origin':'http://localhost:8082'
					 }
		     }).success(function(data,status, headers, config){
	//	        alert('sdsdadada' + status);
		        if (status == 200) { //success comes from the return json object
		        	alert('You have been succesfully registered. An email notification has been sent to you.', true);
		        	
		        } else {
		        	
		        }
		    })
		    .error(function(data,status, headers, config){
		    }) ;
	        	
        }
        
        function register() {
            vm.dataLoading = true;
            UserService.Create(vm.user)
                .then(function (response) {
                    if (response.success) {
                        
                        sendMail(vm.user);
                        $location.path('/login');
                    } else {
                    	alert('Registration failed.Please contact your leasing office.', true);
                        vm.dataLoading = false;
                    }
                });
        }
        
        
        function getPropertiesList(){
      //  	alert('getPropertiesList');
  		  var urlBase='http://localhost:9090/PropertyAdminServices/properties/';
  			var req = {
  					 method: 'GET',
  					 url: urlBase,
  					 headers: {
  					   'Content-Type': 'application/json',
  					   'Access-Control-Allow-Origin':'http://localhost:8082'
  					 }
  					};
  			
  			 $http(req).success(function(data){
  		//		 alert(JSON.stringify(data));
  				 vm.user.propertyList=data;
  			 })
  			 .error(function(error){
  				
  			 });
  	  }
  	
  	  function getUnitsByProperty(){
  		 // alert('Retrieveing by ' + vm.user.selectedPropertyId);
  		  var urlBase='http://localhost:9090/PropertyAdminServices/properties/'+ vm.user.selectedPropertyId+'/units';
  			var req = {
  					 method: 'GET',
  					 url: urlBase,
  					 headers: {
  					   'Content-Type': 'application/json',
  					   'Access-Control-Allow-Origin':'http://localhost:8082'
  					 }
  					};
  			
  			 $http(req).success(function(data){
  			//	 alert(JSON.stringify(data));
  				vm.user.unitsList = data;
  			 })
  			 .error(function(error){
  				
  			 });
  	  }
  	
  	
  	$scope.$watch('vm.user.selectedPropertyId',function(newVal){
  		if(newVal)
  			{
  				//alert(newVal);
  				vm.getUnitsByProperty();
  			}
  	});
        
        
    }