package Paypal;

import java.io.IOException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.paypal.base.rest.PayPalRESTException;

public class main {
	public main() {

	}

	public static void main(String[] args)
			throws JsonSyntaxException, JsonIOException, PayPalRESTException, IOException {
		InvoiceServer x = new InvoiceServer();
		x.createNewContext("ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM",
				"ECXxIJXBQkbkJn3yv9A8lIqsRGUjtzOY4i1UklqFbesdZiKDYJxoN_HgU1rJ-Ot5-zqguqQ4AHIw_UIT");
		x.createNewInvoiceAndSend("invoice_update.json",
				"ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM");
		x.createNewInvoiceAndSend("invoice_create.json",
				"ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM");

		// x.cancelInvoice("ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM",
		// 0);
		// x.cancelInvoice("ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM",
		// 1);
		//
		// x.createNewInvoiceAndSend("invoice_update.json",
		// "ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM");
		// x.createNewInvoiceAndSend("invoice_create.json",
		// "ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM");
		x.cancelAllInvoice("ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM", "p",
				"p");
	}
}
