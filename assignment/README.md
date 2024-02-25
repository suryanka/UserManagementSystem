	1. The below ss shows the project structure.
   

		

	
	
	2. From the controller we call the APIs, then the controller interacts with the service layer.
	And the service layer interacts with the repository.
	
	3. All created APIs working listed below:

        POST /login - The user provides username and password .User is authenticated based on the provided info.
        POST/profile - The user provides username, password, name , address and phoneNo to get created or register itself.
        PUT/profile - The user is authenticated at first based on the username and password. If it is successful, then it is allowed to update its data. 
        DELETE/profile - The user is authenticated at first based on the username and password. If it is successful, then it is allowed to delete its profile.
        GET/profile - It return the list of all existing users.
	
4.  Junit tests are provided for the methods of controller, dao and service classes.
        
 
