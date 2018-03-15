package fakecompany;

import spark.Request;
import spark.Response;
import spark.Route;

public class RecurringBillHandler implements Route {

	@Override
	public Object handle(Request request, Response response) throws Exception {
		System.out.println("received bill for user " + request.body());
		response.status(200);
		return "200";
	}

}
