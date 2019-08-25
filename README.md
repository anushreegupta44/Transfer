# Transfer
Account transfers
Created a basic account transfer application that has 3 accounts pre initialised.
You can get information about those accounts and transfer money from/to those accounts.

There are the following endpoints:
1. GET `/webapi/accounts/{accountNumber}` to get information about your account
2. POST `/webapi/accounts/transfers` that transfers the amount between two accounts 
and accepts a payload with the following information.
{"fromAccNum":"1",
	"toAccNum":"2",
	"amount":"22.0"
}

Account with id 1 is initialised with a balance of 200.0
Account with id 2 is initialised with a balance of 300.0
Account with id 3 is initialised with a balance of 100.0

Used the following tech stack:
Jersey implementation of JaxRs for creating basic endpoints.
In-memory h2 db connected through simple JDBC connections.
Dagger for dependency injection.
Junit and mockito for unit testing.
Docker to run the application within a tomcat server.

To start the application:
You should have docker installed and running on your machine.
Clone the code and cd into the project.
Run `sh run.sh` to build the image and you can access the api on port 8080 on your local machine.
