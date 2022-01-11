package clients.clientModel;

import java.time.LocalDateTime;

public class Ticket {
	private int ticketId;
	private int showtimeId; //MULTIPLE TICKETS??
	private int theatreId;
	private int seat;
	private int userId;
	/**
	 * @param ticketId
	 * @param showtimeId
	 * @param theatreId
	 * @param userId
	 */
	public Ticket(int ticketId, int showtimeId, int theatreId, int seat,int userId) {
		this.ticketId = ticketId;
		this.showtimeId = showtimeId;
		this.theatreId = theatreId;
		this.seat = seat;
		this.userId = userId;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getShowtimeId() {
		return showtimeId;
	}

	public void setShowtimeId(int showtimeId) {
		this.showtimeId = showtimeId;
	}

	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(int theaterId) {
		this.theatreId = theaterId;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		String s = "";
		s += "Ticket ID: " + ticketId;
		s += " Showtime ID: " + showtimeId;
		s += " Theatre ID: " + theatreId;
		s += " seat: " + seat;
		if(userId != -1)
			s += " userId: " + userId;
		return s;
	}
	//	/**
//	 * @return the ticketId
//	 */
//	public int getTicketId() {
//		return ticketId;
//	}
//
//	/**
//	 * @param ticketId the ticketId to set
//	 */
//	public void setTicketId(int ticketId) {
//		this.ticketId = ticketId;
//	}
//
//	/**
//	 * @return the movieId
//	 */
//	public int getMovieId() {
//		return movieId;
//	}
//
//	/**
//	 * @param movieId the movieId to set
//	 */
//	public void setMovieId(int movieId) {
//		this.movieId = movieId;
//	}
//
//	/**
//	 * @return the theaterId
//	 */
//	public int getTheaterId() {
//		return theaterId;
//	}
//
//	/**
//	 * @param theaterId the theaterId to set
//	 */
//	public void setTheaterId(int theaterId) {
//		this.theaterId = theaterId;
//	}
//
//	/**
//	 * @return the time
//	 */
//	public LocalDateTime getTime() {
//		return time;
//	}
//
//	/**
//	 * @param time the time to set
//	 */
//	public void setTime(LocalDateTime time) {
//		this.time = time;
//	}

//	public String print() {
//		String s = "";
//		s += "Ticket ID: " + ticketId;
//		s += "Movie ID: " + movieId;
//		s += "Theater ID: " + theaterId;
//		s += "Time: " + time;
//		return s;
//	}

//	/**
//	 * @return the seatNumber
//	 */
//	public int getSeatNumber() {
//		return seatNumber;
//	}
//
//	/**
//	 * @param seatNumber the seatNumber to set
//	 */
//	public void setSeatNumber(int seatNumber) {
//		this.seatNumber = seatNumber;
//	}
//
}
