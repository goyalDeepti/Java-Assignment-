Overview

This application is a rewards points system that allows retailers to calculate reward points for their customers based on their purchases. Customers earn points based on transaction amounts with specific condition.

Features

Calculate Monthly Rewards: Retrieve total reward points earned by a customer for each month.
Calculate Rewards with multiple months: Retrieve total reward points earned .
RESTful API: Expose endpoints for accessing rewards information.


Tech Stack

Technology: Java 21
Backend: Spring Boot
Database: H2 DB
Testing: JUnit, Mockito
Build Tool: Maven

Requirements

Java 8 or higher
Maven
H2 DB

Installation

Clone the repository:

1. git clone https://github.com/goyalDeepti/Java-Assignment-.git
cd Java-Assignment-project

2. Build the project:
 mvn clean install
3. Run the application: mvn spring-boot-run

Endpoints

Get Monthly points example : http://localhost:8081/api/rewards/getRewards/1/May/2024
Get data from multiple months example : http://localhost:8081/api/rewards/getRewardsForMultipleMonths

Sample Data output:

This application includes sample data for demonstration purposes.
1. Get Monthly points example: 
input: api/rewards/getRewards/1/May/2024
output:
{
    "months": [
        "May"
    ],
    "rewards": 90,
    "custId": 1
}

2. Get data from multiple months example: 
input: {
    "months":["May","June"],
    "year": 2024
}
output:
{
    "months": [
        "May",
        "June"
    ],
    "rewards": 70
}


 




Author

Deepti Goyal