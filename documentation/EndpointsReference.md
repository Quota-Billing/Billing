# API Endpoints
1. [GET Request](/documentation/EndpointsReference.md#get-request)
2. [POST Request](/documentation/EndpointsReference.md#post-request)
3. [PUT Request](/documentation/EndpointsReference.md#put-request)
4. [DELETE Request](/documentation/EndpointsReference.md#delete-request)

## GET Request
| HTTP Request Type       | URI| What does it do  |
| ------------- |:-------------:| -----:|
| GET  | "/getdb" | Retrieve Database info |



## POST Request
| HTTP Request Type       | URI| What does it do  |
| ------------- |:-------------:| -----:|
| POST | "/addUser/partner/:partnerId/product/:productId/user/:userId"|   Add user to billing database |
| POST | "/partner/:partnerId/produc/:productId/user/:userId/quotaReached/:quotaId/" | Call by Quota when a quota is reached its limit |
| POST | "/addquota" | Add quota to billing database|



## PUT Request
| HTTP Request Type       | URI| What does it do  |
| ------------- |:-------------:| -----:|



## DELETE Request
| HTTP Request Type       | URI| What does it do  |
| ------------- |:-------------:| -----:|
