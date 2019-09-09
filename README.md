# test
ISR Test - RESTful Web Service

<b>Steps to run the application in DEV environment:</b>
1. Run maven command to build the application -> <b>mvn clean package</b>
2. Run the jar including the dev profile -> <b>java -jar -Dspring.profiles.active=dev test-0.0.1-SNAPSHOT.jar</b>

Access the application locally - http://localhost:8881/test

Access the application through heroku - http://ericktenorio.herokuapp.com/test

	1. API Documentation, access or click SwaggerUI
	
	2. H2 Console, access or click H2 Console
	
<b>Endpoints</b>
	
<b>dates</b>
Retrieves a JSON array of all the unique dates (ignoring time) in the table The resulting JSON is sorted in ascending.
* URL : /dates
* Method:  :GET
* URL Params :
	Required: None
* Data Params : None
* Success Response:
    * Code: 200 
    * Content: { “20190908”, “20190909”}
* Error Response:
    * Code: 404 NOT FOUND 
    * Content: { “” }
* Sample Call: curl -X GET "http://localhost:8881/test/dates" -H "accept: */*"

<b>users</b>
* Retrieves a list of user of all the unique users for which there is a login record between the start and end date. Both parameters are optional, so there can be a start date, an end date, or both. The resulted List of user is sorted in ascending order.
* URL : /users
* Method:  GET
* URL Params :
	Optional: start, end
* Data Params : None
* Success Response:
    * Code: 200 
    * Content: { {“key”:”user”, “value”:”1000”}}
* Error Response:
    * Code: 404 NOT FOUND 
    * Content: { “” }
* Sample Call:  curl -X GET "http://localhost:8881/test/users?end=20190908&start=20190907" -H "accept: */*"

<b>logins</b>
* Retrieves a List of {@link UserWithLoginCountDTO} where the key is the user name and the value is the number of times a user logged on between the start and the end date.All parameters are optional. The values used for the attributes are used as filters
* URL : /logins
* Method : GET
* URL Params : 
	Optional: start, end, attribute1, attribute2, attribute3, attribute4
* Data Params : None
* Success Response:
    * Code: 200 
    * Content: { “user”, ”user0”, “user1”}
* Error Response:
    * Code: 404 NOT FOUND 
    * Content: { “” }
* Sample Call: curl -X GET "http://localhost:8881/test/logins?attribute1=AA0&attribute1=AA1&attribute2=BB0&attribute2=BB1&attribute3=CC0&attribute3=CC1&attribute4=DD0&attribute4=DD1&end=20190909&start=20190907" -H "accept: */*"

