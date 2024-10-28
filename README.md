Project Details:
This project contains an api which is used to calculate rewards for each customer as per month. This project handles the exceptions.

API Documentation:
http://localhost:8080/api/rewards/getRewards/{customerId}/{month}

Endpoints:
GET/endpoint
This endpoint is used to access the API which calculates rewards.

Parameters:
It is a mandatory parameter to calculate rewards for each customer.
@PathVariable customerId 
@PathVariable month 

Response:
{
"key":"value"
}