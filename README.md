Prerequisite:
1. Java 8
2. Springboot 2.4.2
3. apache Maven 3.6.3
4. H2 Database (file will be created in home path)

Steps to run
1. Build the Distance finder Service project using mvn clean install
2. Run using mvn spring-boot:run
3. The web application is accessible via localhost:8080
4. Use username - Test and password - Test123 to login to demo

Application URL:
Swagger URL: http://localhost:8080/swagger-ui.html#/
DistanceCalculator Opertion: http://localhost:8080/distancefinder/distanceCalculator?
sourcePostCode=AB21 9HT&destinationPostCode=AB21 9HS
HttpMethod: get
PostcodeReform operation:http://localhost:8080/distancefinder/postcodeReform
HttpMethod: Put
Sample RequestBody
{
"postCode": "AB21 9HT",
"latitude": 57.185659760000000,
"longitude": -2.181431946000000
}"# DistanceFinderService" 
