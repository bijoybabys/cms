## Project
CMS - Cow Management System.
This Application is developed using Java and Spring Boot framework.

## Installation
To deploy this project, run the following command:

Preequisite:
 The machine must have Docker installed.
  
1. Open the terminal and navigate to cms folder.
2. Run the following command in the terminal.
3. docker network create cms-net
4. docker-compose up


## Usage
Launch an HTTP client, preferably Postman

1.To add a cow 
  URL :  http://localhost:8080/cows
  Method : POST
  Request:
    {
    "collarId": "<COLLAR_ID>",
    "cowNumber": "<COW_NUMBER>"
	}
	
2.To update a cow 
  URL :  http://localhost:8080/cows/<ID>
  Method : PUT
  Request:
    {
    "collarId": "<COLLAR_ID>",
    "cowNumber": "<COW_NUMBER>"
	}
	
3.To List cows
  URL :  http://localhost:8080/cows
  Method : GET
  
  