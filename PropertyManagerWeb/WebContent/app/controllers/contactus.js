'use strict';
var appModule = angular.module('HuntsboroApp');
appModule.config(['$httpProvider',function($httpProvider){
	$httpProvider.defaults.useXDomain = true;
	$httpProvider.defaults.withCredentials = true;
	delete $httpProvider.defaults.headers.common["X-Requested-With"];
	$httpProvider.defaults.headers.common["Accept"] = "application/json";
	$httpProvider.defaults.headers.common["Content-Type"] = "application/json";
}]);

//Data
var cities = [
              {
                  city : 'PO Box 43, Stephenville, TX 76401',
                  desc : 'Huntsboro Properties',
                  lat : 32.2150988,
                  long : -98.2533026
              }];
appModule.controller('ContactUsController',['$scope','$http','$location', function ($scope,$http,$location) {
	var mapOptions = {
	        zoom: 4,
	        center: new google.maps.LatLng(40.0000, -98.0000),
	        mapTypeId: google.maps.MapTypeId.TERRAIN
	    }
	
	  $scope.map = new google.maps.Map(document.getElementById('map'), mapOptions);
	
	  $scope.markers = [];
	  var infoWindow = new google.maps.InfoWindow();
	  
	  var createMarker = function (info){
	     
	      var marker = new google.maps.Marker({
	          map: $scope.map,
	          position: new google.maps.LatLng(info.lat, info.long),
	          title: info.city
	      });
	      marker.content = '<div class="infoWindowContent">' + info.desc + '</div>';
	      
	      google.maps.event.addListener(marker, 'click', function(){
	          infoWindow.setContent('<h2>' + marker.title + '</h2>' + marker.content);
	          infoWindow.open($scope.map, marker);
	      });
	      
	      $scope.markers.push(marker);
	      
	  }  
	  for (var i = 0; i < cities.length; i++){
	      createMarker(cities[i]);
	  }
	
	  $scope.openInfoWindow = function(e, selectedMarker){
	      e.preventDefault();
	      google.maps.event.trigger(selectedMarker, 'click');
	  }

	    $scope.result='hidden';
	    $scope.resultMessage;
		$scope.submitButtonDisabled=false;
		$scope.submitted=false;
		$scope.formData;
		
		$scope.submitContactForm = function(){
			$scope.submitted=true;
			$scope.submitButtonDisabled=true;
			//alert('fomrData -' + $scope.formData.inputName);
			//alert('valid ? - '+ contactform.$valid);
			//if(contactform.$valid){
				var reqdata = $scope.formData;
				var emailData= {
					 'msgText' : 'Name : ' + reqdata.inputName +'\nPhone No. : '+ reqdata.inputPhone + '\nEmail : '+ reqdata.inputEmail +'\nMessage :'+reqdata.inputMessage,
					 'subject': 'Request for Information : '+reqdata.inputSubject,
					 'mailTo': 'info@huntsboro-properties.com',
					 'mailFrom' : 'info@huntsboro-properties.com'
			 };
			//alert(emailData.msgText);
			 $http({
		        method  : 'POST',
		        url     : 'http://localhost:9090/PropertyAdminServices/email/',
		        data    : emailData,  
		        headers: {
					   'Content-Type': 'application/json',
					   'Access-Control-Allow-Origin':'http://localhost:8082'
					 }
		     }).success(function(data,status, headers, config){
		       // alert('sdsdadada' + status);
		        if (status == 200) { //success comes from the return json object
		            $scope.submitButtonDisabled = true;
		            $scope.resultMessage = 'Thank you for contacting us.';
		            $scope.result='alert-success';
		        } else {
		            $scope.submitButtonDisabled = false;
		            $scope.resultMessage = 'Failed to send the message. Please contact our office by phone or try again later.';
		            $scope.result='alert-danger';
		        }
		    })
		    .error(function(data,status, headers, config){
		    	//alert('error'+ data);
		    }) ;
		/*}else {
		    $scope.submitButtonDisabled = false;
		    $scope.resultMessage = 'Failed <img src="http://www.chaosm.net/blog/wp-includes/images/smilies/icon_sad.gif" alt=":(" class="wp-smiley">  Please fill out all the fields.';
		    $scope.result='bg-danger';
		    }
		}*/
			// alert($scope.resultMessage);
		}
 }]);



