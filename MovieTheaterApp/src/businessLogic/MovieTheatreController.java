package businessLogic;

import java.sql.SQLException;
import java.util.ArrayList;
import frontend.GUIController;
import project.DBController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieTheatreController {
	private DBController db;
	private GUIController gui;
	private boolean loggedIn = false;
	private ArrayList<Movie> movies;
	private ArrayList<Theatre> theatres; // NOT used since we only have one theatre
	private ArrayList<Showtime> times;

	/**
	 * MovieTheatreController constructor, takes DBController and GUIController Adds
	 * ActionListeners to buttons
	 * 
	 * @param db
	 * @param gui
	 */
	public MovieTheatreController(DBController db, GUIController gui) {
		this.db = db;
		this.gui = gui;
		gui.getMovieView().addMovieMenuListener(new SelectMoviesListener());
		gui.getMovieView().addTheaterMenuListener(new SelectTheatreListener());
		gui.getMovieView().addShowtimeMenuListener(new SelectShowTimeListener());
		gui.getMovieView().sSeatBtnListener(new SelectSeatListener());
		gui.getMovieView().cTicketBtnListener(new CancelTicketListener());
		gui.getMovieView().addShowNewsBtnListener(new NewsListener());
	}

	// Checks if user is logged in
	public void getIsloggedIn() {
		loggedIn = gui.getLoggedIn();
		;
	}

	// Returns list of show times for a movie
	public ArrayList<Showtime> getShowTimesByMovie(int movieId) {
		ArrayList<Showtime> times = null;
		try {
			times = db.getShowtimesByMovie(movieId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return times;
	}

	// Gets movie ID by movie name
	public int getGetMovieIdByName(String movieName) {
		try {
			return db.getMovieIdByName(movieName);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}

	}

	// Returns list of all movies including unreleased ones
	public ArrayList<Movie> getMovies() {
		try {
			return db.getMovies();
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	}

	// Returns list of released movies
	public ArrayList<Movie> getReleasedMovies() {
		try {
			return db.getReleasedMovies();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// returns true if user has made a selection for theater, movie and showtime.
	public boolean validSelections() {
		int s[] = gui.getMovieView().getSelectionIndex();
		if (s[0] == -1) {
			gui.getMovieView().showErrorMessage("Please select a movie");
		} else if (s[1] == -1) {
			gui.getMovieView().showErrorMessage("Please select a theater");
		} else if (s[2] == -1) {
			gui.getMovieView().showErrorMessage("Please select a showtime");
		} else
			return true;
		return false;
	}

	// Returns array of available seats for given showtime
	private ArrayList<Integer> getAvailableSeats(int showtimeId) {
		ArrayList<Integer> availSeatNum = new ArrayList<Integer>();
		try {
			ArrayList<Seat> allSeats = db.getSeats(showtimeId);// showtimeId);
			for (Seat s : allSeats) {
				if (!s.isReserved())
					availSeatNum.add(s.getNumber());
			}
			return availSeatNum;
		} catch (Exception e) {
			return null;
		}

	}

	// from moive view (select seat button)
	class SelectSeatListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (validSelections()) {
				gui.getSeatView().setAvailableSeats(getAvailableSeats(gui.getSelectedShowtime().getShowtimeId()));
				gui.setMovieViewVisible(false);
				// gui.getSeatView().setup();
				gui.setSeatViewVisible(true);

			}
		}
	}

	// Listener for Select Movie drop down
	class SelectMoviesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int s[] = gui.getMovieView().getSelectionIndex();
			int movieId = movies.get(s[0]).getMovieId();
			gui.setSelectedMovie(movies.get(s[0]));
			if (movieId != -1) {
				times = getShowTimesByMovie(movieId);
				if (times != null)
					gui.getMovieView().setShowtimes(times);
			}
		}

	}

	// Listener for Select Theater drop down
	class SelectTheatreListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// int s[] = gui.getMovieView().getSelectionIndex(); FOR multiple theatres
			// gui.selectedTheatre = theatres.get(s[1]);
			if (gui.getLoggedIn()) {
				movies = getMovies();
				gui.getMovieView().setMovies(movies);
			} else {
				movies = getReleasedMovies();
				gui.getMovieView().setMovies(movies);
			}

		}
	}

	// Listener for SelectShowtime dropdown
	class SelectShowTimeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int s[] = gui.getMovieView().getSelectionIndex();
			gui.setSelectedShowtime(times.get(s[2]));

		}
	}

	// listener for return button on seat view page
	class SeatReturnBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.setSeatViewVisible(false);
			gui.setMovieViewVisible(true);
		}
	}

	// Listener for cancel ticket button
	class CancelTicketListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.setMovieViewVisible(false);
			gui.setCancleTicketViewVisible(true);
		}
	}

	// Listener for news button
	class NewsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int s[] = gui.getMovieView().getSelectionIndex();
			if (s[0] == -1) {
				gui.getMovieView().showErrorMessage("Please select a movie");
			} else {
				gui.getMovieView().showNews(gui.getSelectedMovie().getNews());
			}
		}

	}

}
