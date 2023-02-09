# Kata bank-account

This project was created with Spring Boot 3 and use an embedded database (h2), and openjdk 17 
We organized layers with the help of Hexagonal Architecture, and BDD
We used Cucumber to integration test.

## Getting Started

To run this project, please follow those instructions

### Prerequises

Clone this repository:


Then open the project with the IDE of your choice, Right click on the main class named BankAccountApplication, and then press "run Application".

You can also execute the project with maven:
```text
    mvn package && java -jar target/bank-account-1.0-SNAPSHOT.jar
```

To execute the unit tests alone:
```text
    mvn test
```

## Documentation

When the server is running you can access the API Swagger at this URL:

```text
    http://localhost:8080/swagger-ui/index.html
```

## API

With this API, you can register deposit and withdrawal operations on your bank account.

When the API is running, an embedded Apache Tomcat Server will be running at :  

```text
    http://localhost:8080/
```  
First of all, you need to get a user token with your credentials

With Swagger, use api POST "create a new account" :

```text
http://localhost:8080/accounts/ 
```  

Then you are able to make POST and GET request on the bank-account with an Authorization Bearer and the user token.

You can make POST Request at this URL in order to make a deposit, or a withdrawal (testing account is 111):  
to deposit amount is a positive number, ex  : 2500
to withdrawal amount is a negative number, ex  : -500

```text
    http://localhost:8080/operations
```

You can make GET Request at this URL in order to retrieve your operation history :  
In this example the account number is 111.
```text
    http://localhost:8080/operations/111&page=0&size=10
```  

You can also check account position making with GET Request at this URL :  
In this example the account number is 111.
```text
    http://localhost:8080/accounts/111/balance
```  
