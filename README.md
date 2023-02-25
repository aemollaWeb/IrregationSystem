## Irrigation system

### requirement to set up the service

- Java 8
- maven
- postman to test the application

### Step 1  build the service using maven (packaging)
bash
    mvn clean package

### Step 2 - *run the service locally *
bash
    java -jar ./target/PlotOfLand-service-0.0.1-SNAPSHOT.jar



### Step 4  test the service
- import `Irrigation Rest Calls.postman_collection.json` as postman collection
- change the baseUrl var on postman with your server url and running port number example
http://localhost:9192/plotOfLand/confguration/3/irrigate
- test all endpoints

