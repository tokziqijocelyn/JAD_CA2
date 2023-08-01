package classes;

import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

public class PaymentServices {
	private static final String CLIENT_ID = "AR9GacZuxo4P6fl1YxOLp0L8YtwHywj85ACSsTtQ-elMmC6hwF8ClerGpeYaLVc3bj5eRfrqN6QHS8tG";
	private static final String CLIENT_SECRET = "ECIxp4drd-yDUGnTmVYGw41OaN6CObJAOR65QmmIxFg-okVh5kVR9DYHuxIjadjovQKWnjjP714KjN5O";
	private static final String MODE = "sandbox";

	public String authorizePayment(String subtotal, String total, String tax)			
			throws PayPalRESTException {		

		Payer payer = getPayerInformation();
		RedirectUrls redirectUrls = getRedirectURLs();
		List<Transaction> listTransaction = getTransactionInformation(subtotal, total, tax);
		
		Payment requestPayment = new Payment();
		requestPayment.setTransactions(listTransaction);
		requestPayment.setRedirectUrls(redirectUrls);
		requestPayment.setPayer(payer);
		requestPayment.setIntent("authorize");

		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

		Payment approvedPayment = requestPayment.create(apiContext);

		System.out.println(approvedPayment);

		return getApprovalLink(approvedPayment);

	}
	
	private Payer getPayerInformation() {
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		
		//probably need change this later
		
		PayerInfo payerInfo = new PayerInfo();
		payerInfo.setFirstName("John")
				 .setLastName("Cena")
				 .setEmail("sb-474rbk25888757@personal.example.com");
		
		payer.setPayerInfo(payerInfo);
		
		return payer;
	}
	
	private RedirectUrls getRedirectURLs() {
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("http://localhost:8080/JAD_CA2/loadCart");
		redirectUrls.setReturnUrl("http://localhost:8080/JAD_CA2/execute_payment");
		
		return redirectUrls;
	}
	
	private List<Transaction> getTransactionInformation(String subtotal, String total, String tax) {
		Details details = new Details();
		details.setShipping("0");
		details.setSubtotal(subtotal);
		details.setTax(tax);

		Amount amount = new Amount();
		amount.setCurrency("SGD");
		amount.setTotal(total);
		amount.setDetails(details);

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setDescription("book");
		
		ItemList itemList = new ItemList();
		List<Item> items = new ArrayList<>();
		
		Item item = new Item();
		item.setCurrency("SGD");
		item.setName("book");
		item.setPrice(subtotal);
		item.setTax(tax);
		item.setQuantity("1");
		
		items.add(item);
		itemList.setItems(items);
		transaction.setItemList(itemList);

		List<Transaction> listTransaction = new ArrayList<>();
		listTransaction.add(transaction);	
		
	    
	    return listTransaction;
	}


	
	private String getApprovalLink(Payment approvedPayment) {
		List<Links> links = approvedPayment.getLinks();
		String approvalLink = null;
		
		for (Links link : links) {
			if (link.getRel().equalsIgnoreCase("approval_url")) {
				approvalLink = link.getHref();
				break;
			}
		}		
		
		return approvalLink;
	}

	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);

		Payment payment = new Payment().setId(paymentId);

		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

		return payment.execute(apiContext, paymentExecution);
	}
	
	public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
		return Payment.get(apiContext, paymentId);
	}
}
