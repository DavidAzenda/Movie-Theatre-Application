package clients.clientModel;

import java.util.HashSet;
import clients.clientModel.Credit;
import clients.clientModel.Receipt;

public class User {
	private HashSet<Ticket> tickets;
	private Receipt receipt;
	private Credit credit;
	private PaymentInfo payment;
	
	/**
	 * @param tickets
	 * @param receipt
	 * @param credit
	 * @param payment
	 */
	public User() {
		tickets = new HashSet<Ticket>();	
	}
	
	/**
	 * @param tickets
	 * @param receipt
	 * @param credit
	 * @param payment
	 */
	public User(HashSet<Ticket> tickets, Receipt receipt, Credit credit, PaymentInfo payment) {
		this.tickets = tickets;
		this.receipt = receipt;
		this.credit = credit;
		this.payment = payment;
	}


	/**
	 * @return the tickets
	 */
	public HashSet<Ticket> getTickets() {
		return tickets;
	}

	/**
	 * @param tickets the tickets to set
	 */
	public void setTickets(HashSet<Ticket> tickets) {
		this.tickets = tickets;
	}

	/**
	 * @return the receipt
	 */
	public Receipt getReceipt() {
		return receipt;
	}

	/**
	 * @param receipt the receipt to set
	 */
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	/**
	 * @return the credit
	 */
	public Credit getCredit() {
		return credit;
	}

	/**
	 * @param credit the credit to set
	 */
	public void setCredit(Credit credit) {
		this.credit = credit;
	}

	/**
	 * @return the payment
	 */
	public PaymentInfo getPayment() {
		return payment;
	}

	/**
	 * @param payment the payment to set
	 */
	public void setPayment(PaymentInfo payment) {
		this.payment = payment;
	}
	
	
	
}
