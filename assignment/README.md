	1. The below ss shows the project structure.
   ![image](https://github.com/suryanka/UserManagementSystem/assets/120125240/dc0cc169-f6e4-485d-ac3f-9f4bc286f3c3)

	2. From the controller we call the APIs, then the controller interacts with the service layer.
	And the service layer interacts with the repository.
	
	3. All created APIs working listed below:

        POST /login - The user provides username and password .User is authenticated based on the provided info.
        POST/profile - The user provides username, password, name , address and phoneNo to get created or registered.
        PUT/profile - The user is authenticated at first based on the username and password. If it is successful, then it is 
        allowed to update its data. 
        DELETE/profile - The user is authenticated at first based on the username and password. If it is successful, then it 
        is allowed to delete its profile.
        GET/profile - It returns the list of all existing users.
    4.  Junit tests are provided for the methods of controller, dao and service classes.
![image](https://github.com/suryanka/UserManagementSystem/assets/120125240/2684816f-7668-4932-a4cb-ba609627ab3d)


     ................................................................................................

 
