package fakecompany;

import spark.Request;
import spark.Response;
import spark.Route;

public class ChargeUserController implements Route {

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String userId = request.params("userId");
		//String token = Database.getInstance().getUser(userId).getPaymentToken();
		// connecting braintree or other payment tools api here
		
		System.out.println("charge user"+userId);
		return "ok";
	}

}
