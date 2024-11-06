# Rewards point

## Overview

This application is a rewards points system that allows retailers to calculate reward points for their customers based on their purchases. Customers earn points based on transaction amounts with specific condition.

## Features

- **Calculate Monthly Rewards**: Retrieve total reward points earned by a customer for each month.
- **Calculate Rewards with multiple months**: Retrieve total reward points earned .
- **RESTful API**: Expose endpoints for accessing rewards information.


## Tech Stack

- **Technology**: Java 21
- **Backend**: Spring Boot
- **Database**: H2 DB
- **Testing**: JUnit, Mockito
- **Build Tool**: Maven

## Requirements

- Java 8 or higher
- Maven
- H2 DB

## Installation

1. Clone the repository:
  ```bash

   git clone https://github.com/goyalDeepti/Java-Assignment-.git
   cd Java-Assignment-project

2. Build the project:
   mvn clean install
3. Run the application:
   mvn spring-boot-run

## Endpoints

 - ** 1. Get Monthly points example** : 

   -**Request** :
   http://localhost:8081/api/rewards/getRewards/1/May/2024

- **Response** :

  Status Code: `200 OK`
  
  Response Body: (Success):

```json
    {
    "monthsWithRewards": {
        "May": 90
    },
    "custId": 1
}
```    
- **Description** :

   Above transactions found for one month records only, for May month transaction amount is $90 for Customer with ID 1.


 - ** 2. Get data from multiple months example** : 

   -**Request** :
    http://localhost:8081/api/rewards/getRewardsForMultipleMonths

- **Response** :

    Status Code: `200 OK`
  
    Response Body: (Success):

```json
    {
    "rewards": {
        "1": 90,
        "2": 50
    },
    "totalRewardsPoints": 140
}
```    
- **Description** :

   Above transactions found for two months records only, total Rewards is $140.

   Author

   Deepti Goyal