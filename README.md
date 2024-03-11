# Role-Base-Acces-Control-Application

Objective: Implement basic role-based access control (RBAC) functionality in a web 
application using Java and Spring Boot.

Description: Your task is to develop a simple web application that manages user roles 
and permissions.

Requirements:

• The application should be built using Java and Spring Boot.
• Define the following entities:
• User: Represents a user of the system. Properties include name, email, and phone 
number.
• Role: Represents a role assigned to a user. Two roles are defined: "user" and 
"admin."
• Implement basic authentication (you can choose any authentication mechanism) and
authorization mechanisms:
• Users should be able to register with a username and password.
• Users should be able to log in with their credentials.
• Implement a simple role-based authorization mechanism where users have one role 
("user" or "admin") with predefined permissions.
• Provide CRUD (Create, Read, Update, Delete) operations for the User entity:
• Users with the "user" role should be able to perform CRUD operations on their 
own user entity only (i.e., they have permission to perform CRUD operations on 
their own entity)
• Users with the "admin" role should be able to perform CRUD operations on any 
user entity (i.e., they have permission to perform CRUD operations on any user 
entity)
• Secure the application against common security vulnerabilities, such as SQL injection.


### NOTE: For a Better Understanding of the project, I am considering this as a Bank Application and I am taking the user as a Customer




# ANSWER 

1 For Registration I can use DTO for registration
2. Hence as Mentioned in the question The User entity will allowed only a few attributes(username, password not part of them)
3. To avoid such case errors, I am creating a new Entity called CustomerRegistration this is Specially for Registration
4. I created one API to Create a customer, This API is an Authenticated API, Based on the Login details provided we can update the customer details.
5. While I am adding the customers, I attached the customer registration and Role to the customer
6. Retrieve the Customer Details, This API is also authenticated, And can be accessed by both the user and admin
7. For getting the Customer Details initially we have an Authentication while I am doing the Authentication as I created my Authentication Provider in my application
8. Once the authentication is successful I Store the CustomerRegistration object.
9. As we already connected the Customer and CustomerRegistration based on Bi-directional mapping i can easily get the Customer details.
10.For updating and Deleting the acces was given to only the admin.
11. Registration is open for all , Intailly while registering i am taken the role as user by defualt
12. So the client will need to give the username and Password as inputs 
13.  And passwords are Bcrypted for secutity purposes.

