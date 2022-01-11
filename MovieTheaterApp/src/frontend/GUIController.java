package frontend;

import javax.swing.JFrame;

import businessLogic.Movie;
import businessLogic.Seat;
import businessLogic.Showtime;
import businessLogic.Theatre;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//import frontend.CustomerGUI.TestGUI;

public class GUIController {
	private LoginView loginView;
	private RegisterView regView;
	private MovieView movieView;
	private SeatView seatView;
	private PurchaseView purchaseView;
	private CancelTicketView cancelTicketView;
	private EmailView emailView;
	private AccountView accountView;
	private boolean loggedIn;
	private Movie selectedMovie;
	private Theatre selectedTheatre;
	private Showtime selectedShowtime;
	/**
	 * @return the selectedMovie
	 */
	public Movie getSelectedMovie() {
		return selectedMovie;
	}
	/**
	 * @param selectedMovie the selectedMovie to set
	 */
	public void setSelectedMovie(Movie selectedMovie) {
		this.selectedMovie = selectedMovie;
	}
	/**
	 * @return the selectedTheatre
	 */
	public Theatre getSelectedTheatre() {
		return selectedTheatre;
	}
	/**
	 * @param selectedTheatre the selectedTheatre to set
	 */
	public void setSelectedTheatre(Theatre selectedTheatre) {
		this.selectedTheatre = selectedTheatre;
	}
	/**
	 * @return the selectedShowtime
	 */
	public Showtime getSelectedShowtime() {
		return selectedShowtime;
	}
	/**
	 * @param selectedShowtime the selectedShowtime to set
	 */
	public void setSelectedShowtime(Showtime selectedShowtime) {
		this.selectedShowtime = selectedShowtime;
	}

	
	// store frontend data , entered from a user
	//private String movieName,theaterName,showtime;
	//private List<String> selectedSeats = new ArrayList<String>();
	//private int numofSelectedSeats, price;



	public GUIController() {
		loginView = new LoginView();
		loginView.setTitle("Movie Theater System");
		loginView.setVisible(true);
		loginView.setBounds(10,10,800,600);
		loginView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginView.setResizable(true);

		regView = new RegisterView();
		regView.setTitle("Register View");
		regView.setVisible(false);
		regView.setBounds(10,10,700,700);
		regView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		regView.setResizable(true);

		movieView = new MovieView();
		movieView.setTitle("Movie Selection");
		movieView.setVisible(false);
		movieView.setBounds(10,10,800,600);
		movieView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		movieView.setResizable(true);

		seatView = new SeatView();
		seatView.setTitle("Seat Selection");
		seatView.setVisible(false);
		seatView.setBounds(10,10,1000,600);
		seatView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		seatView.setResizable(true);

/*
		purchaseView = new PurchaseView(1,"movie","1","1", 0, new ArrayList<String>());
		purchaseView.setTitle("Purchase Ticket");
		purchaseView.setVisible(true);
		purchaseView.setBounds(10,10,950,450);
		purchaseView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		purchaseView.setResizable(true);
*/
		emailView = new EmailView();
		emailView.setTitle("Show all Email");
		emailView.setVisible(false);
		emailView.setBounds(10,10,800,700);
		emailView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		emailView.setResizable(true);

		cancelTicketView = new CancelTicketView();
		cancelTicketView.setTitle("Cancel Ticket");
		cancelTicketView.setVisible(false);
		cancelTicketView.setBounds(10,10,800,500);
		cancelTicketView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cancelTicketView.setResizable(true);

		accountView = new AccountView();
		accountView.setTitle("Account Info");
		accountView.setVisible(false);
		accountView.setBounds(10,10,800,700);
		accountView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		accountView.setResizable(true);
		selectedTheatre = new Theatre(1, null,null);
	}
	public void login() {
		loggedIn = true;
	}
	public boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoginVisible(boolean condition){
		loginView.setVisible(condition);
	}
	public void setRegVisible(boolean condition){
		regView.setVisible(condition);
	}
	public void setMovieViewVisible(boolean condition){
		movieView.setVisible(condition);
	}
	public void setSeatViewVisible(boolean condition){
		seatView.setVisible(condition);
	}
	public void setEmailViewVisible(boolean condition){
		emailView.setVisible(condition);
	}
	public void setPurchaseViewVisible(boolean condition){
		purchaseView.setVisible(condition);
	}
	public void setCancleTicketViewVisible(boolean condition){
		cancelTicketView.setVisible(condition);
	}
	public void setAccountViewVisible(boolean condition){
		accountView.setVisible(condition);
	}
	public LoginView getLoginView() {
		return loginView;
	}	

	public RegisterView getRegisterView() {
		return regView;
	}	
	public MovieView getMovieView() {
		return movieView;
	}	
	public SeatView getSeatView() {
		return seatView;
	}
	public PurchaseView getPurchase() {
		return purchaseView;
	}
	public CancelTicketView getCancelTicket() {
		return cancelTicketView;
	}
	public AccountView getAccount() {
		return accountView;
	}
	public EmailView getEmail() {
		return emailView;
	}
	public void setPurchaseView(PurchaseView purchaseView) {
		this.purchaseView = purchaseView;
		
	}
	public PurchaseView getPurchaseView() {
		return purchaseView;
		
	}		
}
