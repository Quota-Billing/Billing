package fakecompany;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import Paypal.InvoiceHandler;
import Paypal.JsonPharser;
import edu.rosehulman.billing.models.Tier;
import spark.Request;
import spark.Response;
import spark.Route;

public class ChargeUserController implements Route {

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String userId = request.params("userId");
		String tierId = request.params("tierId");
		// String token = Database.getInstance().getUser(userId).getPaymentToken();
		// connecting braintree or other payment tools api here
		Database db = Database.getInstance();
		User user = db.getUser(userId);
		Tier tier = new Tier("ThePaidTierId", "ThePaidTierName", 5, 10.0, 10);
		ArrayList<String> merchant = new ArrayList<String>();
		merchant.add("yukariyukari-facilitator@126.com");
		merchant.add("Mercury");
		merchant.add("Ding");
		merchant.add("Mitchell & Murray");
		merchant.add("001");
		merchant.add("4085551234");
		merchant.add("1234 First Street");
		merchant.add("Anytown");
		merchant.add("CA");
		merchant.add("98765");
		merchant.add("US");
		ArrayList<String> billing = new ArrayList<String>();
		billing.add("yukariyukari-buyer@126.com");
		billing.add("Mercury");
		billing.add("Ding");
		billing.add("Stephanie");
		billing.add("Meyers");
		billing.add("1234 Main Street");
		billing.add("Anytown");
		billing.add("CA");
		billing.add("98765");
		billing.add("US");

		int[] amounts = new int[1];
		int[] value = new int[1];
		int[] tax = new int[1];
		String[] names = new String[1];
		names[0] = tier.getName();
		value[0] = 10;
		amounts[0] = 1;
		tax[0] = 8;

		ArrayList<String> discount = new ArrayList<String>();
		int dispercent = 1;
		discount.add("USD");
		discount.add("10");

		String[] notes = new String[2];
		notes[0] = "Thank you for your business.";
		notes[1] = "No refunds after 30 days.";
		JsonPharser.pharse(merchant, billing, amounts, value, tax, names, discount, dispercent, notes, "FakeCompany/2.json");
		TimeUnit.SECONDS.sleep(5);

		InvoiceHandler x = new InvoiceHandler();
		x.createNewContext("ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM",
				"ECXxIJXBQkbkJn3yv9A8lIqsRGUjtzOY4i1UklqFbesdZiKDYJxoN_HgU1rJ-Ot5-zqguqQ4AHIw_UIT");

		x.createNewInvoiceAndSend("2.json",
				"ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM");

		System.out.println("charge user" + userId);
		return "ok";
	}

}
