package businessLogic;

public class Seat {
	private int number;
	private boolean reserved;
	private int showtimeId;
	private int price;

	public Seat(int number, boolean reserved, int showtimeId, int price) {
		this.number = number;
		this.reserved = reserved;
		this.showtimeId = showtimeId;
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public int getShowtimeId() {
		return showtimeId;
	}

	public void setShowtimeId(int showtimeId) {
		this.showtimeId = showtimeId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "number:" + number +
				"\nreserved:" + reserved +
				"\nshowtimeId:" + showtimeId +
				"\nprice:" + price +
				"\n.";
	}
	//	public Seat(int number, boolean reserved) {
//		setNumber(number);
//		setReserved(reserved);
//	}
//
//	/**
//	 * @return the number
//	 */
//	public int getNumber() {
//		return number;
//	}
//
//	/**
//	 * @param number the number to set
//	 */
//	public void setNumber(int number) {
//		this.number = number;
//	}
//
//	/**
//	 * @return the reserved
//	 */
//	public boolean isReserved() {
//		return reserved;
//	}
//
//	/**
//	 * @param reserved the reserved to set
//	 */
//	public void setReserved(boolean reserved) {
//		this.reserved = reserved;
//	}
//
//	@Override
//	public String toString() {
//		return "\nseat number=" + number +
//				"\nreserved=" + reserved;
//	}
}
