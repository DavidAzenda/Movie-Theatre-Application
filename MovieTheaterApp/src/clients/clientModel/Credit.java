package clients.clientModel;

import java.time.LocalDate;

public class Credit {
	private int creditId;
	private LocalDate expiry;
	private double amount;
	private int userId;

	public Credit(int creditId, LocalDate expiry, double amount, int userId) {
		this.creditId = creditId;
		this.expiry = expiry;
		this.amount = amount;
		this.userId = userId;
	}

	public Credit() {
	}

	public int getCreditId() {
		return creditId;
	}

	public void setCreditId(int creditId) {
		this.creditId = creditId;
	}

	public LocalDate getExpiry() {
		return expiry;
	}

	public void setExpiry(LocalDate expiry) {
		this.expiry = expiry;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String toString() {
		String s = "";
		s+= "Credit ID: " + creditId;
		s+= "\nExpiry : " + expiry.toString();
		s+= "\nAmount : " + amount;
		return s;
		
	}

	
}
