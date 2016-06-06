
    'use strict';
 
	// UserService
   angular.module('HuntsboroApp').factory('UserService', UserService);
 
    UserService.$inject = ['$http'];
    function UserService($http) {
        
		var urlBase='http://localhost:9090/PropertyAdminServices/';

		var service = {};
 		
        service.GetAll = GetAll;
        service.GetById = GetById;
        service.Create = Create;
/*        service.GetByUsername = GetByUsername;

        service.Update = Update;*/
        //service.Delete = Delete;
 
        return service;
 
        function GetAll() {
            return $http.get(urlBase+ '/applicantsandtenants/').then(handleSuccess, handleError('Error getting all users'));
        }
 
        function GetById(id) {
            return $http.get(urlBase+ '/applicantsandtenants/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }
 
	    function Create(user) {
	            return $http.post(urlBase+ '/applicantsandtenants/', user).then(handleSuccess, handleError('Error creating user'));
	        }

/*        function GetByUsername(username) {
            return $http.get(urlBase+ '/api/users/' + username).then(handleSuccess, handleError('Error getting user by username'));
        }
 
      
 
        function Update(user) {
            return $http.put(urlBase+ '/api/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
        }
 
*/      /*  function Delete(id) {
            return $http.delete('/api/users/' + id).then(handleSuccess, handleError('Error deleting user'));
        }*/
 
        // private functions
 
        function handleSuccess(res) {
			if(res.data !=null)
			{
				return {success: true};
			}
//            return res.data;
        }
 
        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }
 
