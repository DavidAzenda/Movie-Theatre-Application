package clients.clientModel;

import java.util.List;

public class Receipt {
	private int receiptId;
	private int ticketId;
	private double price;
	private int userId;

	public Receipt(int receiptId, int ticketId, double price, int userId) {
		this.receiptId = receiptId;
		this.ticketId = ticketId;
		this.price = price;
		this.userId = userId;
	}

	public int getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "receiptId=" + receiptId +
				"\nticketId=" + ticketId +
				"\nprice=" + price +
				"\nuserId=" + userId;
	}
	//	/**
//	 * @param receiptId
//	 * @param tickets
//	 * @param price
//	 */
//	public Receipt(int receiptId, List<Ticket> tickets, double price) {
//		this.receiptId = receiptId;
//		this.tickets = tickets;
//		this.price = price;
//	}
//
//	/**
//	 * @return the receiptId
//	 */
//	public int getReceiptId() {
//		return receiptId;
//	}
//
//	/**
//	 * @param receiptId the receiptId to set
//	 */
//	public void setReceiptId(int receiptId) {
//		this.receiptId = receiptId;
//	}
//
//	/**
//	 * @return the tickets
//	 */
//	public List<Ticket> getTickets() {
//		return tickets;
//	}
//
//	/**
//	 * @param tickets the tickets to set
//	 */
//	public void setTickets(List<Ticket> tickets) {
//		this.tickets = tickets;
//	}
//
//	/**
//	 * @return the price
//	 */
//	public double getPrice() {
//		return price;
//	}
//
//	/**
//	 * @param price the price to set
//	 */
//	public void setPrice(double price) {
//		this.price = price;
//	}
//
	
}
