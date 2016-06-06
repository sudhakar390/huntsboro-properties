//angular.module('floorPlanApp', ['ngAnimate', 'ui.bootstrap','ngTable'])
var floorPlanModule = angular.module('HuntsboroApp', ['ngTable','ui.bootstrap','ngSanitize']);
floorPlanModule.config(['$httpProvider',function($httpProvider){
	$httpProvider.defaults.useXDomain = true;
	$httpProvider.defaults.withCredentials = true;
	delete $httpProvider.defaults.headers.common["X-Requested-With"];
	$httpProvider.defaults.headers.common["Accept"] = "application/json";
	$httpProvider.defaults.headers.common["Content-Type"] = "application/json";
}]);
// services
floorPlanModule.factory('floorPlanService',['$http','$sce','$filter',function($http,$sce,$filter){
	var floorPlanFactory={};
	var urlBase='http://localhost:9090/PropertyAdminServices/applicationsandleases/search';
	
	floorPlanFactory.searchFloorPlans = function (moveDate,bedsize,bathsize) {
		//alert(moveDate);
		moveDate = $filter('date')(moveDate, 'yyyy/MM/dd');
		//alert(moveDate);
		//alert(bedsize.value);
		//alert(bathsize.value);
		var searchURL= urlBase+'?moveInDate='+moveDate+'&beds='+bedsize.value+'&baths='+bathsize.value;
		
		var req = {
				 method: 'GET',
				 url: searchURL,
				 headers: {
				   'Content-Type': 'application/json',
				   'Access-Control-Allow-Origin':'http://localhost:8082'
				 },
				 
				};
		
		return  $http(req);
		
/*		return searchResults = [{
	    	  "layoutLink":"../images/man1.jpg",
	    	  "propertyName":"XYZ Apartments",
	    	  "location":"Pflugerville",
	    	  "unitnumber": "G12",
	    	  "beds":2,
	          "baths":2,
	          "rentalAmount":800,
	          "securityDeposit":100,
	          "sqft":1000,
	          "propertyId":1,
	          "amenities":$sce.trustAsHtml("<li> Washer & Dryer</li><li> Valet Trash</li>")
	      },
	      {
	    	  "layoutLink":"../images/man2.jpg",
	    	  "propertyName":"ABC Apartments",
	    	  "location":"Austin",
	    	  "unitnumber": "123",
	    	  "beds":1,
	          "baths":1,
	          "rentalAmount":400,
	          "securityDeposit":50,
	          "sqft":600,
	          "propertyId":2,
	          "amenities":$sce.trustAsHtml("<li> Easy Online Rent payment</li><li> 24 hrs Maintenance</li>")
	      }];*/
	};
	return floorPlanFactory;
}]);




floorPlanModule.filter('getById', function() {
	  return function(input, id) {
	    var i=0, len=input.length;
	    for (; i<len; i++) {
	      if (+input[i].id == +id) {
	        return input[i];
	      }
	    }
	    return null;
	  }
	});




floorPlanModule.controller('FloorplansearchCtrl',['$scope','$sce','$filter','$location','$anchorScroll','$uibModal','ngTableParams','floorPlanService', function ($scope,$sce,$filter, $location,$anchorScroll,$uibModal,ngTableParams,floorPlanService){
	//alert('asda');
	
	 $scope.bedsizes = [
	                  {value:'-1', name:'Any'},
	                  {value:'1', name:'1'},
	                  {value:'2', name:'2'},
	                  {value:'3', name:'3'}
	                ];
	 $scope.bedsize = $scope.bedsizes[0]; //default it to 1st value
	 $scope.bathsizes = [
		                  {value:'-1', name:'Any'},
		                  {value:'1', name:'1'},
		                  {value:'2', name:'2'},
		                  {value:'3', name:'3'}
		                ];
	 $scope.bathsize = $scope.bathsizes[0]; //default it to 1st value
	
  $scope.today = function() {
		$scope.dt = new Date();
  };
  $scope.today();

  $scope.clear = function() {
	  $scope.dt = null;
  };

  // Disable weekend selection
  $scope.disabled = function(date, mode) {
    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
  };

  $scope.toggleMin = function() {
    $scope.minDate = $scope.minDate ? null : new Date();
  };

  $scope.toggleMin();
  $scope.maxDate = new Date(2020, 5, 22);

  $scope.open1 = function() {
	  $scope.popup1.opened = true;
  };

  $scope.open2 = function() {
    $scope.popup2.opened = true;
  };

  $scope.setDate = function(year, month, day) {
	  $scope.dt = new Date(year, month, day);
  };

  $scope.dateOptions = {
    formatYear: 'yy',
    startingDay: 1
  };

  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy','dd/MM/yyyy', 'shortDate'];
  $scope.format = $scope.formats[1];
  $scope.altInputFormats = ['M!/d!/yyyy'];

  $scope.popup1 = {
    opened: false
  };

  $scope.popup2 = {
    opened: false
  };

  var tomorrow = new Date();
  tomorrow.setDate(tomorrow.getDate() + 1);
  var afterTomorrow = new Date();
  afterTomorrow.setDate(tomorrow.getDate() + 1);
  $scope.events =
    [
      {
        date: tomorrow,
        status: 'full'
      },
      {
        date: afterTomorrow,
        status: 'partially'
      }
    ];

  $scope.getDayClass = function(date, mode) {
    if (mode === 'day') {
      var dayToCheck = new Date(date).setHours(0,0,0,0);

      for (var i = 0; i < $scope.events.length; i++) {
        var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

        if (dayToCheck === currentDay) {
          return $scope.events[i].status;
        }
      }
    }

    return '';
  };
  
   
  $scope.findFloorPlan = function(){
      dataLoading = true;
     /* alert($scope.dt);
      alert($scope.bedsize);
      alert($scope.bathsize);*/
     // alert('searched');
     /* $location.hash('fp-search-results');
      $anchorScroll();*/
      
     floorPlanService.searchFloorPlans($scope.dt,$scope.bedsize,$scope.bathsize)
      .success(function(data,status,headers,config){
    	  		if(status == 204)
    	  		{
    	  			//alert(status);
    	  			$scope.statusMsg = ' No floor plans found. Please refine your search parameters and try again.';
    	  			$scope.showSuccessAlert=true;
					$scope.showFailureAlert=false;
					$scope.searchResults={}
	    	        $scope.hasAvailableProperties = $scope.searchResults && $scope.searchResults.length;
					
    	  		} else {
    	  			$scope.searchResults = data;
        	  		//alert(JSON.stringify(data));
        	  		$scope.searchResultsTable = new ngTableParams({
    	    	       	 page:1,
    	    	       	 count:10
    	    	         }, {
    	    	       	  total: $scope.searchResults.length,
    	    	       	  getData: function ($defer, params) {
    	    	               $scope.data = $scope.searchResults.slice((params.page() - 1) * params.count(), params.page() * params.count());
    	    	               $defer.resolve($scope.data);
    	    	           }
    	    	         });
    	    	        
    	    	        $scope.hasAvailableProperties = $scope.searchResults && $scope.searchResults.length;
    	    	        $scope.statusMsg = 'Found '+ $scope.searchResults.length + ' floor plans.'
        	  			$scope.showSuccessAlert=true;
    					$scope.showFailureAlert=false;
    	  		}
    	  	
    	       
			})
			.error(function(error,status,headers,config){
				$scope.searchResults = 'Unable to load Floor plans data: ' + error;
				$scope.errorMsg = 'Unable to load Floor plans data: Please contact our staff.';
				$scope.showSuccessAlert=false;
				$scope.showFailureAlert=true;
			});
		
     // alert($scope.searchResults);
      


  };
  $location.hash('fp-search-results');
  $anchorScroll();
  
  // view details
  $scope.detail =null;
  
  $scope.viewDetails = function(propId){
	  alert(propId);
	  
	  var rowData = $filter('getById')($scope.searchResults, propId)
	 // alert(JSON.stringify(rowData));
	  $scope.detail = rowData;
	  $location.hash('fp-view-details');
	  $anchorScroll();
	  
	  
  };
  
  // create application
  
  $scope.createApplication = function(propId){
	  alert('Launch Bluemoon site here');

  };

  
  $scope.zoomImage = function(imgPath){
	  $scope.imagePath=imgPath;
	 // alert($scope.imagePath);
	  var modalInstance = $uibModal.open({
		  templateUrl:'zoomimage.html',
		  controller: 'ModalZoomController',
		  size:'lg',
		  resolve: {
			  imagePath : function(){
				  return $scope.imagePath;
			  }
		  }
		  });
  };
  
  
  
}]);


angular.module('HuntsboroApp').controller('ModalZoomController', function ($scope, $uibModalInstance, imagePath) {
	  $scope.imagePath = imagePath;
	});



//directives

/*floorPlanModule.directive('modal', function () {
    return {
      template: '<div class="modal fade">' + 
          '<div class="modal-dialog">' + 
            '<div class="modal-content">' + 
              '<div class="modal-header">' + 
                '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' + 
                '<h4 class="modal-title">{{ title }}</h4>' + 
              '</div>' + 
              '<div class="modal-body" ng-transclude></div>' + 
            '</div>' + 
          '</div>' + 
        '</div>',
      restrict: 'E',
      transclude: true,
      replace:true,
      scope:true,
      link: function postLink(scope, element, attrs) {
        scope.title = attrs.title;

        scope.$watch(attrs.visible, function(value){
          if(value == true)
            $(element).modal('show');
          else
            $(element).modal('hide');
        });

        $(element).on('shown.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = true;
          });
        });

        $(element).on('hidden.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = false;
          });
        });
      }
    };
  });*/


floorPlanModule.directive('ngMouseWheelUp', function() {
    return function(scope, element, attrs) {
        element.bind("DOMMouseScroll mousewheel onmousewheel", function(event) {
               
        	        // cross-browser wheel delta
        	        var event = window.event || event; // old IE support
        	        var delta = Math.max(-1, Math.min(1, (event.wheelDelta || -event.detail)));
        	
        	        if(delta > 0) {
                        scope.$apply(function(){
                            scope.$eval(attrs.ngMouseWheelUp);
                        });
                    
                      // for IE
                      event.returnValue = false;
                      // for Chrome and Firefox
                      if(event.preventDefault) event.preventDefault();                        
                   }
        });
    };
});


floorPlanModule.directive('ngMouseWheelDown', function() {
    return function(scope, element, attrs) {
        element.bind("DOMMouseScroll mousewheel onmousewheel", function(event) {
               
        	        // cross-browser wheel delta
        	        var event = window.event || event; // old IE support
        	        var delta = Math.max(-1, Math.min(1, (event.wheelDelta || -event.detail)));
        	
        	        if(delta < 0) {
                        scope.$apply(function(){
                            scope.$eval(attrs.ngMouseWheelDown);
                        });

                      // for IE
                      event.returnValue = false;
                      // for Chrome and Firefox
                      if(event.preventDefault) event.preventDefault();                        
                   }
        });
    };
});



