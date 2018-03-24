package Paypal;

import java.io.File;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.NullProgressMonitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.paypal.base.rest.PayPalRESTException;

import edu.rosehulman.billing.models.Tier;

public class main {
	public main() {

	}
	
	public static void dostuff() throws IOException, PayPalRESTException, InterruptedException {
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
		JsonPharser.pharse(merchant, billing, amounts, value, tax, names, discount, dispercent, notes,
				"FakeCompany/2.json");
		TimeUnit.SECONDS.sleep(5);
		// items[0][0] = "Zoom System wireless headphones";
		// items[0][1] = "2";
		// items[0][2] = "USD";
		// items[0][3] = "120";
		// items[0][4] = "8";
		// JsonPharser.pharse(merchant, billing, items2, discount, notes, "2.json");
		//
		InvoiceHandler x = new InvoiceHandler();
//		x.deleteAllDraft("ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM");
		x.createNewContext("ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM",
				"ECXxIJXBQkbkJn3yv9A8lIqsRGUjtzOY4i1UklqFbesdZiKDYJxoN_HgU1rJ-Ot5-zqguqQ4AHIw_UIT");
		// x.createNewInvoiceAndSend("invoice_create.json",
		// "ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM");
		// x.createNewInvoiceAndSend("2.json",
		// "ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM");
		//
		x.createNewInvoiceAndSend("2.json",
				"ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM");
		// x.createNewInvoiceAndSend("2.json",
		// "ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM");
		x.cancelAllInvoice("ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM", "p",
				"p");
	}

	public static void main(String[] args)
			throws JsonSyntaxException, JsonIOException, PayPalRESTException, IOException, InterruptedException {
		File file = new File("FakeCompany\\2.json");
		dostuff();
	}
}
