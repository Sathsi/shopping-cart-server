# ShoppingCart Server

## Prerequisites ##
* Java 1.8+
* Maven 3.x
* MySQL 5.7

#Db setup
* Configure the db connection in the shopping-cart/modules/server/src/main/resources/application.properties
* Export the database script (database/shopingCart.sql) into your MySql.


## Build the server ##
` mvn clean install `


## Run the server ##
* Go to the shoppingcart-server/target/
* Run the jar file : `java -jar com.shoppingcart.server-0.0.1-SNAPSHOT.jar`
* Navigate to `http://localhost:8080/swagger-ui.html`.
