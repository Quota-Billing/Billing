package Paypal;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.paypal.api.payments.CancelNotification;
import com.paypal.api.payments.Image;
import com.paypal.api.payments.Invoice;
import com.paypal.api.payments.Invoices;
import com.paypal.api.payments.Notification;
import com.paypal.api.payments.Search;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

public class InvoiceServer extends SampleBase<Invoice> {
	HashMap<String, APIContext> clientinfo = new HashMap<String, APIContext>();
	InvoiceHandler invoiceSample = null;

	public InvoiceServer() throws JsonSyntaxException, JsonIOException, FileNotFoundException, PayPalRESTException {
		super(new Invoice());
		invoiceSample = new InvoiceHandler();
	}

	public void createNewContext(String clientid, String clientsecret) throws PayPalRESTException, IOException {
		APIContext context = new APIContext(clientid, clientsecret, "sandbox");
		if (!clientinfo.keySet().contains(clientid)) {
			clientinfo.put(clientid, context);
		}
	}

	public void createNewInvoiceAndSend(String filename, String clientid) throws PayPalRESTException, IOException {
		APIContext context = clientinfo.get(clientid);
		super.instance = load(filename, Invoice.class);
		super.instance = super.instance.create(context);
		super.instance.send(context);
	}

	public Invoices getMerchantInvoices(APIContext context) throws PayPalRESTException {
		// Lets add some options.
		Map<String, String> options = new HashMap<String, String>() {
			{
				put("total_count_required", "true");
			}
		};
		return Invoice.getAll(context, options);
	}

	public Invoices search(String clientid, String startTime, String endTime) throws PayPalRESTException {
		APIContext context = clientinfo.get(clientid);
		return invoiceSample.search(context, startTime, endTime);
	}

	public void cancelInvoice(String clientid, int index, String title, String content)
			throws PayPalRESTException, IOException {
		APIContext context = clientinfo.get(clientid);
		invoiceSample.cancelInvoice(context, index, title, content);
	}

	public void cancelAllInvoice(String clientid, String title, String content)
			throws PayPalRESTException, IOException {
		APIContext context = clientinfo.get(clientid);
		invoiceSample.cancelAllInvoice(context, title, content);
	}

	public void generateQRCode(String clientid, int index, String filename) throws PayPalRESTException, IOException {
		APIContext context = clientinfo.get(clientid);
		invoiceSample.generateQRCode(context, index, filename);
	}

	public void sendReminder(String clientid, int index, String title, String content) throws PayPalRESTException {
		APIContext context = clientinfo.get(clientid);
		invoiceSample.sendReminder(context, index, title, content);
	}

	public void sendReminderToAll(APIContext context, String title, String content) throws PayPalRESTException {

	}
}
