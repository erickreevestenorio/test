# test
ISR Test - RESTful Web Service

<b>Steps to run the application in DEV environment:</b>
1. Run maven command -> mvn clean package
2. Run the jar including the dev profile-> java -jar -Dspring.profiles.active=dev test-0.0.1-SNAPSHOT.jar

Access SwaggerUI for API Documentation- http://localhost:8881/test/

<b>Endpoints</b>

<b>http://server/test/dates</b>
Retrieves a JSON array of all the unique dates (ignoring time) in the table
The resulting JSON array needs to be sorted ascending.

<b>http://server/test/users?start=YYYYMMDD&end=YYYYMMDD</b>
Retrieves a JSON array of all the unique users for which there is a login record between the start and end date.
Both parameters are optional, so there can be a start date, an end date, or both.
The resulting JSON array needs to be sorted ascending.
As the performance of this operation is critical, implement optimizations to ensure it performs well.
	
<b>http://server/test/logins?start=YYYYMMDD&end=YYYYMMDD&attribute1=AAA&attribute2=BBB&attribute3=CCC&attribute4=DDD</b>
Retrieves a JSON object where the key is the user name and the value is the number of times a user logged on between the start and the end date.
All parameters are optional.
The values used for the attributes are used as filters, i.e. only the records should be counted for which the attribute values are equal to the ones specified in the parameters.
For one attribute, multiple values might be present, e.g. http://server/test/logins?attribute1=AA1&attribute1=AA2&attribute1=AA3
