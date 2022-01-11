package clients.payment;

import clients.clientModel.PaymentInfo;

public class Payment {
	 private PaymentAuthorizer p;
	 public Payment(PaymentAuthorizer p) {
		 this.p = p;
	 }
	 public boolean communicate(PaymentInfo info, double amount) {
	        boolean result = p.verify(info);
	        return result;
	 }
}
