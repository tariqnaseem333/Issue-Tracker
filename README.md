# Issue-Tracker
## It is a Java Enterprise Application maven based project using various concepts of OOPs and Java API(Regex API, DateTime API, Collection Framework). 
### Mont Trance Inc. is one of the leading imports and exports industry. They are operating across the globe. In order to facilitate the smooth running of their operations, they are in need of developing an application, through which any user can report any issue that they may encounter. In this project, 

### MobileService is an online application which a customer can use to send a service request for his mobile phone if there are any issues and get it fixed. In this project, the following operations are performed
### For Assignee
#### 1.) Fetch Assignee
#### 2.) Update Active Issue Count
### For Issue
#### 1.) Report An Issue
#### 2.) Update Status
#### 3.) Show Issues
#### 4.) Delete Issues

#### The different steps taking place in the application are explained below:- 
##### 1.) User performs CRUD operation for Issue and  in Tester Class (Presentation Tier). In this project, the client tier is not used. The inputs will be taken directly in the Tester Class.
##### 2.) The Tester class sends the Model class object to the Service Class (Business Tier).
##### 3.) The Service class sends the object to the Validator class to get the inputs validated. 
##### 4.) If the inputs are in valid format, the related CRUD operation will be performed with the help of DAO class (Persistence Tier). In this project, the database is not being used simply the CRUD operation will be performed on hard coded data.
##### 5.) Based on the responses from the Validator and the DAO classes, the Service class formulates  either a successful output or a failure output and return the same to the Tester class.
##### 6.) The Tester class then displays the output to the User.
