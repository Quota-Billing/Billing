package Paypal;

import java.io.FileNotFoundException;
import java.io.IOException;
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

/**
 * This class shows code samples for invoicing. l
 * https://developer.paypal.com/webapps/developer/docs/api/#invoicing
 *
 */
public class InvoiceGenerator extends SampleBase<Invoice> {

	public static final String clientID = "ASVNc8isqUIaRsPYu-qVt8mjYCQcUaIEUdA_3DCWdYDawgo5Bg_mOW7UqdT7vFRBu6hqfSSoKWePm8LM";
	public static final String clientSecret = "ECXxIJXBQkbkJn3yv9A8lIqsRGUjtzOY4i1UklqFbesdZiKDYJxoN_HgU1rJ-Ot5-zqguqQ4AHIw_UIT";
	InvoiceGenerator invoiceSample = null;
	APIContext context = null;
	
	/**
	 * Initialize and instantiate an Invoice object
	 * 
	 * @throws PayPalRESTException
	 * @throws JsonSyntaxException
	 * @throws JsonIOException
	 * @throws FileNotFoundException
	 */
	public InvoiceGenerator() throws PayPalRESTException, JsonSyntaxException, JsonIOException, FileNotFoundException {
		super(new Invoice());
		try {
			invoiceSample = new InvoiceGenerator();
			context = new APIContext(clientID, clientSecret, "sandbox");
		}  catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (PayPalRESTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create an invoice.
	 * 
	 * https://developer.paypal.com/webapps/developer/docs/api/#create-an-invoice
	 * 
	 * @return newly created Invoice instance
	 * @throws PayPalRESTException
	 */
	public Invoice create(APIContext context) throws PayPalRESTException, IOException {
		// populate Invoice object that we are going to play with
		super.instance = load("invoice_create.json", Invoice.class);
		super.instance = super.instance.create(context);
		return super.instance;
	}

	/**
	 * Send an invoice.
	 * 
	 * https://developer.paypal.com/webapps/developer/docs/api/#send-an-invoice
	 * 
	 * @throws PayPalRESTException
	 */
	public void send(APIContext context) throws PayPalRESTException {
		super.instance.send(context);
	}

	/**
	 * Update an invoice.
	 * 
	 * https://developer.paypal.com/webapps/developer/docs/api/#update-an-invoice
	 * 
	 * @return updated Invoice instance
	 * @throws PayPalRESTException
	 */
	public Invoice update(APIContext context) throws PayPalRESTException, IOException {
		String id = super.instance.getId();
		super.instance = load("invoice_update.json", Invoice.class);
		super.instance.setId(id);
		// Changing the number of invoice to some random value
		super.instance.setNumber(UUID.randomUUID().toString().substring(0, 5));
		super.instance = super.instance.update(context);
		return super.instance;
	}

	/**
	 * Retrieve an invoice.
	 * 
	 * https://developer.paypal.com/webapps/developer/docs/api/#retrieve-an-invoice
	 * 
	 * @return retrieved Invoice instance
	 * @throws PayPalRESTException
	 */
	public Invoice retrieve(APIContext context) throws PayPalRESTException {
		super.instance = Invoice.get(context, super.instance.getId());
		return super.instance;
	}

	/**
	 * Get invoices of an merchant.
	 * 
	 * https://developer.paypal.com/webapps/developer/docs/api/#get-invoices-of-a-merchant
	 * 
	 * @return Invoices instance that contains invoices for merchant
	 * @throws PayPalRESTException
	 */
	public Invoices getMerchantInvoices(APIContext context) throws PayPalRESTException {
		// Lets add some options.
		Map<String, String> options = new HashMap<String, String>() {
			{
				put("total_count_required", "true");
			}
		};
		return Invoice.getAll(context, options);
	}

	/**
	 * Search for invoices.
	 * 
	 * https://developer.paypal.com/webapps/developer/docs/api/#search-for-invoices
	 * 
	 * @return Invoices instance that contains found invoices
	 * @throws PayPalRESTException
	 */
	public Invoices search(APIContext context) throws PayPalRESTException {
		Search search = new Search();
		search.setStartInvoiceDate("2010-05-10 PST");
		search.setEndInvoiceDate("2014-04-10 PST");
		search.setPage(1);
		search.setPageSize(20);
		search.setTotalCountRequired(true);
		return super.instance.search(context, search);
	}

	/**
	 * Send an invoice reminder.
	 * 
	 * https://developer.paypal.com/webapps/developer/docs/api/#send-an-invoice-reminder
	 * 
	 * @throws PayPalRESTException
	 */
	public void sendReminder(APIContext context) throws PayPalRESTException {
		Notification notification = new Notification();
		notification.setSubject("Past due");
		notification.setNote("Please pay soon");
		notification.setSendToMerchant(true);
		super.instance.remind(context, notification);
	}

	/**
	 * Cancel an invoice.
	 * 
	 * https://developer.paypal.com/webapps/developer/docs/api/#cancel-an-invoice
	 * 
	 * @throws PayPalRESTException
	 */
	public void cancel(APIContext context) throws PayPalRESTException {
		CancelNotification cancelNotification = new CancelNotification();
		cancelNotification.setSubject("Past due");
		cancelNotification.setNote("Canceling invoice");
		cancelNotification.setSendToMerchant(true);
		cancelNotification.setSendToPayer(true);
		super.instance.cancel(context, cancelNotification);
	}

	/**
	 * Delete an invoice.
	 * 
	 * https://developer.paypal.com/webapps/developer/docs/api/#delete-an-invoice
	 * 
	 * @throws PayPalRESTException
	 */
	public void delete(APIContext context) throws PayPalRESTException, IOException {
		Invoice newInvoice = this.create(context);
		newInvoice.delete(context);
	}

	/**
	 * Gets a QR Code Image object.
	 * 
	 * @param context
	 *            {@link APIContext}
	 * @return Image
	 * @throws PayPalRESTException
	 * @throws IOException
	 */
	public Image getQRCode(APIContext context) throws PayPalRESTException, IOException {

		// Lets add some options. These are optional, but added for demo purposes.
		Map<String, String> options = new HashMap<String, String>() {
			{
				put("width", "400");
				put("height", "350");
			}
		};
		// Replace the given Id with your invoice Id.
		Image image = Invoice.qrCode(context, super.instance.getId(), options);

		// You can also save the image to file as shown here.
		image.saveToFile("invoice.png");
		return image;
	}

	/**
	 * Main method that calls all methods above in a flow.
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws PayPalRESTException 
	 */
	
	public String createInvoiceOnly() throws PayPalRESTException, IOException {
		invoiceSample.create(context);
		return "create response:\n" + Invoice.getLastResponse();
	}
	
	public String updateInvoice() throws PayPalRESTException, IOException {
		invoiceSample.update(context);
		return "update response:\n" + Invoice.getLastResponse();
	}
	
	public String sendInvoice() throws PayPalRESTException, IOException {
		invoiceSample.send(context);
		return "send response:\n" + Invoice.getLastResponse();
	}
	
	public String cancelInvoice() throws PayPalRESTException, IOException {
		invoiceSample.cancel(context);
		return "cancel response:\n" + Invoice.getLastResponse();
	}
	
	public String deleteInvoice() throws PayPalRESTException, IOException {
		invoiceSample.delete(context);
		return "delete response:\n" + Invoice.getLastResponse();
	}
	
//	public static void main(String[] args) {
//		try {
//			InvoiceGenerator invoiceSample = new InvoiceGenerator();
//
//			APIContext context = new APIContext(clientID, clientSecret, "sandbox");
//
//			invoiceSample.create(context);
//			System.out.println("create response:\n" + Invoice.getLastResponse());
//			invoiceSample.update(context);
//			System.out.println("update response:\n" + Invoice.getLastResponse());
//			invoiceSample.send(context);
//			System.out.println("send response:\n" + Invoice.getLastResponse());
//			invoiceSample.getQRCode(context);
//			invoiceSample.retrieve(context);
//			System.out.println("retrieve response:\n" + Invoice.getLastResponse());
//			invoiceSample.getMerchantInvoices(context);
//			System.out.println("getall response:\n" + Invoice.getLastResponse());
//			invoiceSample.search(context);
//			System.out.println("search response:\n" + Invoice.getLastResponse());
//			invoiceSample.sendReminder(context);
//			System.out.println("remind response:\n" + Invoice.getLastResponse());
//			invoiceSample.cancel(context);
//			System.out.println("cancel response:\n" + Invoice.getLastResponse());
//			invoiceSample.delete(context);
//			System.out.println("delete response:\n" + Invoice.getLastResponse());
//	}
}