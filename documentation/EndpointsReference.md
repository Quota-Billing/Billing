# API Endpoints
1. [GET Request](/documentation/EndpointsReference.md#get-request)
2. [POST Request](/documentation/EndpointsReference.md#post-request)
3. [PUT Request](/documentation/EndpointsReference.md#put-request)
4. [DELETE Request](/documentation/EndpointsReference.md#delete-request)

## GET Request
| HTTP Request Type       | URI| What does it do  |
| ------------- |:-------------:| -----:|




## POST Request
| HTTP Request Type       | URI| What does it do  |
| ------------- |:-------------:| -----:|
| POST | "/partner/:partnerId/product/:productId/addUser/:userId"|   Add user to billing database |
| POST | "/partner/:partnerId/produc/:productId/user/:userId/quotaReached/:quotaId" | Call by Quota when a quota is reached its limit |
| POST | "/partner/:partnerId/product/:productId/quota/:quotaId/name/:name/type/:type" | Add quota to billing database|
| POST | "/userId/:userID/plan/:plan/partnerId/:partnerId/productId/:productId/fee/:fee"| Add billing info to database|
| POST | "/billingId/:billingId/fee/:fee" | Add billing info to database|
| POST | "/partner/:partnerId/name/:name/key/:api_key/password/:password" | Add a partner to database|
| POST | "/partner/:partnerId/name/:name/product/:productId" | Add a product to partner in database|
| POST | "/partner/:partnerId/product/:productId/quota/:quotaId/name/:name/tier/:tierId/price/:price/max/:max" | Add a tier in the database|


## PUT Request
| HTTP Request Type       | URI| What does it do  |
| ------------- |:-------------:| -----:|



## DELETE Request
| HTTP Request Type       | URI| What does it do  |
| ------------- |:-------------:| -----:|
| DELETE | "/partner/:partnerId/product/:productId/deleteUser/:userId" | Delete a user from billing database|
