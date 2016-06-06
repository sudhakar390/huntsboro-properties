'use strict';
var maintenanceModule = angular.module('HuntsboroApp');
maintenanceModule.config(['$httpProvider',function($httpProvider){
	$httpProvider.defaults.useXDomain = true;
	$httpProvider.defaults.withCredentials = true;
	delete $httpProvider.defaults.headers.common["X-Requested-With"];
	$httpProvider.defaults.headers.common["Accept"] = "application/json";
	$httpProvider.defaults.headers.common["Content-Type"] = "application/json";
}]);

maintenanceModule.controller('MaintenanceRequestController',['$scope','$http','$filter','$location', function ($scope,$http,$filter, $location){
	alert('in maintenance');
	$scope.getPropertiesList = function(){
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
				 alert(JSON.stringify(data));
				 $scope.propertyList=data;
			 })
			 .error(function(error){
				
			 });
	  }
	
	  $scope.getUnitsByProperty = function(){
		  alert('Retrieveing by ' + $scope.selectedPropertyId);
		  var urlBase='http://localhost:9090/PropertyAdminServices/properties/'+ $scope.selectedPropertyId+'/units/O';
			var req = {
					 method: 'GET',
					 url: urlBase,
					 headers: {
					   'Content-Type': 'application/json',
					   'Access-Control-Allow-Origin':'http://localhost:8082'
					 }
					};
			
			 $http(req).success(function(data){
				 alert(JSON.stringify(data));
				 $scope.unitsList=data;
			 })
			 .error(function(error){
				
			 });
	  }
	
	$scope.getPropertiesList();
	$scope.$watch('selectedPropertyId',function(newVal){
		if(newVal)
			{
			alert(newVal);
				$scope.getUnitsByProperty();
			}
	});
	
	 
	/* var urlBase='http://localhost:9090/PropertyAdminServices/properties/';
		var req = {
				 method: 'GET',
				 url: urlBase,
				 headers: {
				   'Content-Type': 'application/json',
				   'Access-Control-Allow-Origin':'http://localhost:8082'
				 }
				};
		
		 $http(req).success(function(data){
			 alert(JSON.stringify(data));
			 $scope.propertyList=data;
		 })
		 .error(function(error){
			
		 });
	 */
	  
	  
	  
	 $scope.submitMaintenanceRequest = function(){
		 alert($scope.selectedPropertyId);
		 alert($scope.name);
		 alert($scope.hasPermission);
		
			var postData =  {
					// requestId:1,
					 unit:$scope.apartmentNum,
					 requestedBy:$scope.name,
					 entryAllowed:$scope.hasPermission,
					 issueDescription:$scope.summary,
					 emergencyIndicator:$scope.isEmergency,
					 cellPhone:$scope.cellPhone,
					 workPhone:$scope.workPhone,
					 email :$scope.email,
					 property:$scope.selectedPropertyId,
					 maintenanceLocation:$scope.maintenanceLocation
				 };
				 
				 var urlBase1='http://localhost:9090/PropertyAdminServices/maintenancerequests/';
				 var res = $http.post(urlBase1,postData, { headers: {
					   'Content-Type': 'application/json',
					   'Access-Control-Allow-Origin':'http://localhost:8082'
					 }});
	
					 res.success(function(data,status, headers, config){
						 alert(JSON.stringify(data));
						 if(status ==200)
						  {
							// var emailSent = sendEmail(postData);
							// if(emailSent)
							// {
								 $scope.statusMsg='Maintenance Request has been submitted succesfully.';
							 	 $scope.showSuccessAlert=true;
							 	 $scope.showFailureAlert=false;
							// }
						 	 
						  }
					 });
					 res.error(function(error,status, headers, config){
						alert(error.message);
						$scope.errorMsg='Problem creating Maintenance Request.Please contact your leasing office at the standard office number.';
						$scope.showSuccessAlert=false;
						$scope.showFailureAlert=true;
					 });
					
			 };
			 
		 function sendEmail(reqData){
			 var urlBase1='http://localhost:9090/PropertyAdminServices/email/';
			 var emailData= {
					 'msgText' : 'Request Summary : ' + reqData.issueDescription,
					 'subject': 'Your maintenance request has been submitted succesfully',
					 'mailTo': reqData.email,
					 'mailFrom' : 'mgr@huntsboro-properties.com'
			 };
			 
			 var res = $http.post(urlBase1,emailData, { headers: {
				   'Content-Type': 'application/json',
				   'Access-Control-Allow-Origin':'http://localhost:8082'
				 }});
			 
			 
			 res.success(function(data,status,headers,config){
				if(status == 200)
					return true;
			 });
			 
			 res.error(function(error,status,headers,config){
				 return false;
			 });
		 } 
	 
}]);