package businessLogic;

import java.util.HashSet;
import java.util.List;

public class Theatre {
	private int TheatreId;
	private HashSet<Showtime> showtimes;
	private List<Seat> seats;

	public Theatre(int TheatreId, HashSet<Showtime> showtimes, List<Seat> seats) {
		setTheatreId(TheatreId);
		setShowtimes(showtimes);
		setSeats(seats);
	}

	/**
	 * @return the TheatreId
	 */
	public int getTheatreId() {
		return TheatreId;
	}

	/**
	 * @param TheatreId the TheatreId to set
	 */
	public void setTheatreId(int TheatreId) {
		this.TheatreId = TheatreId;
	}

	/**
	 * @return the showtimes
	 */
	public HashSet<Showtime> getShowtimes() {
		return showtimes;
	}

	/**
	 * @param showtimes the showtimes to set
	 */
	public void setShowtimes(HashSet<Showtime> showtimes) {
		this.showtimes = showtimes;
	}

	/**
	 * @return the seats
	 */
	public List<Seat> getSeats() {
		return seats;
	}

	/**
	 * @param seats the seats to set
	 */
	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

}
