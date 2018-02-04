package Paypal;

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

public class InteriorInvoiceHandler extends SampleBase<Invoice> {

	public InteriorInvoiceHandler() throws PayPalRESTException, JsonSyntaxException, JsonIOException, FileNotFoundException {
		super(new Invoice());
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

	public Invoices search(APIContext context, String startTime, String endTime) throws PayPalRESTException {
		Search search = new Search();
		search.setStartInvoiceDate(startTime);
		search.setEndInvoiceDate(endTime);
		search.setPage(1);
		search.setPageSize(20);
		search.setTotalCountRequired(true);
		return super.instance.search(context, search);
	}

	public void cancelInvoice(APIContext context, int index, String title, String content)
			throws PayPalRESTException, IOException {
		ArrayList<Invoice> i = (ArrayList<Invoice>) Invoice.getAll(context).getInvoices();
		CancelNotification cancelNotification = new CancelNotification();
		cancelNotification.setSubject(title);
		cancelNotification.setNote(content);
		cancelNotification.setSendToMerchant(true);
		cancelNotification.setSendToPayer(true);
		i.get(index).cancel(context, cancelNotification);
	}

	public void cancelAllInvoice(APIContext context, String title, String content)
			throws PayPalRESTException, IOException {
		ArrayList<Invoice> i = (ArrayList<Invoice>) Invoice.getAll(context).getInvoices();
		CancelNotification cancelNotification = new CancelNotification();
		cancelNotification.setSubject(title);
		cancelNotification.setNote(content);
		cancelNotification.setSendToMerchant(true);
		cancelNotification.setSendToPayer(true);
		for (Invoice x : i) {
			System.out.println(x.getStatus());
			if (x.getStatus().equals("SENT")) {
				x.cancel(context, cancelNotification);
			}
		}
	}

	public void generateQRCode(APIContext context, int index, String filename) throws PayPalRESTException, IOException {
		ArrayList<Invoice> i = (ArrayList<Invoice>) Invoice.getAll(context).getInvoices();
		Map<String, String> options = new HashMap<String, String>() {
			{
				put("width", "400");
				put("height", "350");
			}
		};
		Image image = Invoice.qrCode(context, i.get(index).getId(), options);
		image.saveToFile(filename);
	}

	public void sendReminder(APIContext context, int index, String title, String content) throws PayPalRESTException {
		Notification notification = new Notification();
		notification.setSubject(title);
		notification.setNote(content);
		notification.setSendToMerchant(true);
		ArrayList<Invoice> i = (ArrayList<Invoice>) Invoice.getAll(context).getInvoices();
		i.get(index).remind(context, notification);
	}

	public void deleteAllDraft(APIContext context) throws PayPalRESTException {
		ArrayList<Invoice> i = (ArrayList<Invoice>) Invoice.getAll(context).getInvoices();
		for (Invoice x : i) {
			System.out.println(x.getStatus());
			if (x.getStatus().equals("DRAFT")) {
				x.delete(context);
			}
		}
	}
}