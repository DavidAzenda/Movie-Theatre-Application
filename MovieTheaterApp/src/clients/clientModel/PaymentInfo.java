package clients.clientModel;

import java.time.LocalDate;

public class PaymentInfo {
	private int infoID;
	private String fName;
	private String lName;
	private String address;
	private int cardNum;
	private LocalDate expiry;
	private int cvv;



	/**
	 * @param infoID
	 * @param fName
	 * @param lName
	 * @param address
	 * @param cardNum
	 * @param expiry
	 * @param cvv
	 */
	public PaymentInfo(int infoID, String fName,String lName, String address, int cardNum, LocalDate expiry, int cvv) {
		this.infoID = infoID;
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.cardNum = cardNum;
		this.expiry = expiry;
		this.cvv = cvv;
	}

	public PaymentInfo() {
	}

	public int getInfoID() {
		return infoID;
	}

	public void setInfoID(int infoID) {
		this.infoID = infoID;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCardNum() {
		return cardNum;
	}

	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}

	public LocalDate getExpiry() {
		return expiry;
	}

	public void setExpiry(LocalDate expiry) {
		this.expiry = expiry;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	@Override
	public String toString() {
		return "infoID=" + infoID +
				"\nfName=" + fName +
				"\nlName=" + lName +
				"\naddress=" + address +
				"\ncardNum=" + cardNum +
				"\nexpiry=" + expiry +
				"\ncvv=" + cvv;
	}

	//
//	/**
//	 * @return the name
//	 */
//	public String getName() {
//		return name;
//	}
//
//	/**
//	 * @param name the name to set
//	 */
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	/**
//	 * @return the address
//	 */
//	public String getAddress() {
//		return address;
//	}
//	/**
//	 * @param address the address to set
//	 */
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	/**
//	 * @return the cardNum
//	 */
//	public long getCardNum() {
//		return cardNum;
//	}
//
//	/**
//	 * @param cardNum the cardNum to set
//	 */
//	public void setCardNum(long cardNum) {
//		this.cardNum = cardNum;
//	}
//
//	/**
//	 * @return the expiry
//	 */
//	public String getexpiry() {
//		return expiry;
//	}
//	/**
//	 * @param expiry the  to set
//	 */
//	public void setexpiry(String expiry) {
//		this.expiry = expiry;
//	}
//	/**
//	 * @return the cvv
//	 */
//	public String getCvv() {
//		return cvv;
//	}
//	/**
//	 * @param cvv the cvv to set
//	 */
//	public void setCvv(String cvv) {
//		this.cvv = cvv;
//	}
//
}
