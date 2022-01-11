package clients.payment;

import java.sql.SQLException;

import clients.clientModel.PaymentInfo;
import project.DBController;

public class PaymentAuthorizer {
	private DBController db;

	public PaymentAuthorizer(DBController db) {
		this.db = db;
	}

	private PaymentInfo readDB(int cardNum) {
		 try{
			return db.readPaymentInfo(cardNum).get(0);
		 }catch(Exception e) { // check this error
			 //e.printStackTrace();
			 return null;
		 }
	}
	public boolean verify(PaymentInfo info) {
		PaymentInfo p = readDB(info.getCardNum());
		if(p!= null) {
			if (p.getExpiry().compareTo(info.getExpiry())==0){
				if(p.getCvv() == info.getCvv())
					return true;
			}
		}
		return false;
	}
}
