package businessLogic;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

public class Showtime {
	private int showtimeId;
	private int movieId;
	private LocalDate date;
	private LocalTime time;

	public Showtime(int showtimeId, int movieId, LocalDate date, LocalTime time) {
		this.showtimeId = showtimeId;
		this.movieId = movieId;
		this.date = date;
		this.time = time;
	}

	public Showtime(int showtimeId) {
		this.showtimeId = showtimeId;
	}

	public Showtime() {
	}


	public int getShowtimeId() {
		return showtimeId;
	}

	public void setShowtimeId(int showtimeId) {
		this.showtimeId = showtimeId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return  "showtimeId:" + showtimeId +
				"\nmovieId:" + movieId +
				"\ndate:" + date +
				"\ntime:" + time;
	}
	//	public Showtime (Movie movie,List<LocalDateTime> times) {
//		setMovie(movie);
//		setTimes(times);
//	}
//
//	/**
//	 * @return the movie
//	 */
//	public Movie getMovie() {
//		return movie;
//	}
//
//	/**
//	 * @param movie the movie to set
//	 */
//	public void setMovie(Movie movie) {
//		this.movie = movie;
//	}
//
//	/**
//	 * @return the times
//	 */
//	public List<LocalDateTime> getTimes() {
//		return times;
//	}
//
//	/**
//	 * @param times the times to set
//	 */
//	public void setTimes(List<LocalDateTime> times) {
//		this.times = times;
//	}
//
	
}
