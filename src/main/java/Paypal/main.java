package Paypal;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.paypal.base.rest.PayPalRESTException;

public class main {
	public main() {

	}

	public static void main(String[] args)
			throws JsonSyntaxException, JsonIOException, PayPalRESTException, IOException {
		// x.deleteAllDraft("ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM");
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

		int[] amounts = new int[2];
		int[] value = new int[2];
		int[] tax = new int[2];
		String[] names = new String[2];
		names[0] = "Zoom System wireless headphones";
		names[1] = "Bluetooth speaker";
		value[0] = 120;
		value[1] = 145;
		amounts[0] = 2;
		amounts[1] = 1;
		tax[0] = 8;
		tax[1] = 8;

		ArrayList<String> discount = new ArrayList<String>();
		int dispercent = 1;
		discount.add("USD");
		discount.add("10");

		String[] notes = new String[2];
		notes[0] = "Thank you for your business.";
		notes[1] = "No refunds after 30 days.";
		JsonPharser.pharse(merchant, billing, amounts, value, tax, names, discount, dispercent, notes, "1.json");

		// items[0][0] = "Zoom System wireless headphones";
		// items[0][1] = "2";
		// items[0][2] = "USD";
		// items[0][3] = "120";
		// items[0][4] = "8";
		// JsonPharser.pharse(merchant, billing, items2, discount, notes, "2.json");
		//
		 InvoiceServer x = new InvoiceServer();
		 x.createNewContext("ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM",
		 "ECXxIJXBQkbkJn3yv9A8lIqsRGUjtzOY4i1UklqFbesdZiKDYJxoN_HgU1rJ-Ot5-zqguqQ4AHIw_UIT");
		// x.createNewInvoiceAndSend("invoice_create.json",
		// "ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM");
		// x.createNewInvoiceAndSend("2.json",
		// "ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM");
		//
		 x.createNewInvoiceAndSend("1.json",
		 "ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM");
		// x.createNewInvoiceAndSend("2.json",
		// "ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM");
		// x.cancelAllInvoice("ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM",
		// "p",
		// "p");
	}
}
