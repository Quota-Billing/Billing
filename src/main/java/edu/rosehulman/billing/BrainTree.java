package edu.rosehulman.billing;

import static spark.Spark.get;
import static spark.Spark.post;

import java.awt.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.braintreegateway.ValidationError;

public class BrainTree {
	BrainTree br;
	ArrayList<TransactionRequest> transrequest = new ArrayList();
	ArrayList<Result> results = new ArrayList();
	ArrayList<Transaction> transactions = new ArrayList();

	public BrainTree() {
		BrainTreeSetUp();
	}

	public void BrainTreeSetUp() {
		BraintreeGateway gateway = new BraintreeGateway(Environment.SANDBOX, "your_merchant_id", "your_public_key",
				"your_private_key");
		get("/client_token", (request, response) -> {
			return gateway.clientToken().generate();
		});

		post("/checkout", (request, response) -> {
			String nonceFromTheClient = request.queryParams("payment_method_nonce");
			return nonceFromTheClient;
		});

		TransactionRequest request = new TransactionRequest().amount(new BigDecimal("10.00"))
				.paymentMethodNonce("???paymentMethodNonce??").options().submitForSettlement(true).done();

		Result<Transaction> result = gateway.transaction().sale(request);
		if (result.isSuccess()) {
			Transaction transaction = result.getTarget();
			this.transrequest.add(request);
			this.results.add(result);
			this.transactions.add(transaction);
		} else {
			CustomerRequest request1 = new CustomerRequest().email("invalid_email").creditCard().number("not_numeric")
					.done();

			for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
				System.out.println(error.getAttribute());
				System.out.println(error.getCode());
				System.out.println(error.getMessage());
			}

			LinkedList<ValidationError> customerErrors = (LinkedList<ValidationError>) result.getErrors().forObject("customer").getAllValidationErrors();
			for (ValidationError error : customerErrors) {
				System.out.println(error.getAttribute());
				System.out.println(error.getCode());
				System.out.println(error.getMessage());
			}

			LinkedList<ValidationError> creditCardErrors = (LinkedList<ValidationError>) result.getErrors().forObject("customer").forObject("creditCard")
					.getAllValidationErrors();
			for (ValidationError error : creditCardErrors) {
				System.out.println(error.getAttribute());
				System.out.println(error.getCode());
				System.out.println(error.getMessage());
			}
		}
	}

	public ArrayList<TransactionRequest> getTransactionRequest() {
		return this.transrequest;
	}

	public ArrayList<Transaction> getTransaction() {
		return this.transactions;
	}

	public ArrayList<Result> getResults() {
		return this.results;
	}

	public boolean isSuccess(TransactionRequest request) {
		int index = 0;
		this.transrequest.indexOf(request);
		return this.results.get(index).isSuccess();
	}

}
