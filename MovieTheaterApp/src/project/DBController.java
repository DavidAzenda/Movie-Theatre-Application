package project;


import businessLogic.Showtime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.io.FileReader;
import java.sql.*;

import businessLogic.Movie;
//import businessLogic.Theatre;
import businessLogic.Showtime;
import businessLogic.Seat;
//import clients.clientModel.PaymentInfo;
import clients.clientModel.PaymentInfo;
import clients.clientModel.User;
import clients.clientModel.RegisteredUser;
import clients.clientModel.Receipt;
import clients.clientModel.Credit;
import clients.clientModel.Ticket;

import javax.management.Query;


public class DBController {
	public final String DBURL;
	public final String USERNAME;
	public final String PASSWORD;
	private Connection dbConnect;
	//	private Properties dbDetails;
	private ResultSet results;
	//private ArrayList<Seat> seats;

	public DBController(String url,String user, String pw) {
		this.DBURL = url;
		this.USERNAME = user;
		this.PASSWORD = pw;
		this.initializeConnection();
		//seats = new ArrayList<Seat>();
	}

	public void initializeConnection() {
		try {
			dbConnect = DriverManager.getConnection(this.DBURL,this.USERNAME,this.PASSWORD);



//			String dbDetailsLocation = "config/db_details.properties";
//
//			this.dbDetails = new Properties();
//			this.dbDetails.load(new FileReader(dbDetailsLocation));
//
//			String connectionString = String.format("jdbc:mysql://%s:%s/%s", this.dbDetails.getProperty("db.host"),
//					this.dbDetails.getProperty("db.port"), this.dbDetails.getProperty("db.schema"));
//
//			this.dbConnect = DriverManager.getConnection(connectionString, this.dbDetails.getProperty("db.user"),
//					this.dbDetails.getProperty("db.password"));
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getDBURL() {
		return DBURL;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	/*******
	START
	THIS SECTION PERTAINS TO ALL METHODS THAT INVOLVING USER TABLE.
	START
	 ********/

	public RegisteredUser readRUser(String username) throws SQLException{

		String query = "SELECT * FROM user WHERE username = ?";
		PreparedStatement myStmt = this.dbConnect.prepareStatement(query);
		myStmt.setString(1, username);

		ResultSet results = myStmt.executeQuery();

		while (results.next()) {
			if (results.getString("username").equals(username)) {

				RegisteredUser ru = new RegisteredUser();
				ru.setUserId(results.getInt("userID"));
				ru.setUsername(username);
				ru.setPassword(results.getString("password"));
				ru.setEmail(results.getString("email"));
				ru.setFeePaid(results.getDate("feepaid").toLocalDate());
				ru.setInfoID(results.getInt("infoID"));

				return ru;
			}
		}
		return null;
	}

	public void updateRUser(RegisteredUser ru) throws SQLException {
		String query = "UPDATE USER SET password = ?, email = ?, username=?  WHERE userId = ?";
		PreparedStatement stmt = dbConnect.prepareStatement(query);


		stmt.setString(1,ru.getPassword());
		stmt.setString(2,ru.getEmail());
		stmt.setString(3,ru.getUsername());
		stmt.setInt(4, ru.getUserId());

		int rowCount = stmt.executeUpdate();
		//System.out.println(rowCount);
		stmt.close();
	}

	public void insertRUser(RegisteredUser ru) throws SQLException{

		String query = "INSERT INTO USER(userID,username,password,email,feePaid,infoID) VALUES(?,?,?,?,?,?)";
		PreparedStatement stmt = dbConnect.prepareStatement(query);

		stmt.setInt(1,ru.getUserId());
		stmt.setString(2, ru.getUsername());
		stmt.setString(3,ru.getPassword());
		stmt.setString(4,ru.getEmail());
		stmt.setDate(5,java.sql.Date.valueOf(ru.getFeePaid()));
		stmt.setInt(6,ru.getInfoID());

		int rowCount = stmt.executeUpdate();
		//System.out.println(rowCount);
		stmt.close();
	}

	/*******
	END
	 THIS SECTION PERTAINS TO ALL METHODS THAT INVOLVING USER TABLE.
	END
	 ********/

	/*******
	 START
	 THIS SECTION PERTAINS TO ALL METHODS THAT INVOLVING PAYMENT INFO TABLE.
	 START
	 ********/

	public ArrayList<PaymentInfo> readPaymentInfo(int cardNum) throws SQLException{

		ArrayList<PaymentInfo> info = new ArrayList<PaymentInfo>();
		
		String query = "SELECT * FROM PAYMENTINFO WHERE cardNumber = ?";
		PreparedStatement stmt = this.dbConnect.prepareStatement(query);
		stmt.setInt(1, cardNum);
		//System.out.println(cardNum);
		ResultSet results = stmt.executeQuery();

		while (results.next()) {
			if (results.getInt("cardNumber") == (cardNum)) {

				PaymentInfo pi = new PaymentInfo();
				pi.setInfoID(results.getInt("infoId"));
				pi.setfName(results.getString("fName"));
				pi.setlName(results.getString("lName"));
				pi.setAddress(results.getString("address"));
				pi.setCardNum(cardNum);
				pi.setExpiry(results.getDate("expiry").toLocalDate());
				pi.setCvv(results.getInt("cvv"));

				info.add(pi);
			}
		}
		return info;
	}



	public void updatePaymentInfo(PaymentInfo pi) throws SQLException {
		String query = "UPDATE PAYMENTINFO SET fName = ?, lName = ?, address = ?, cardNumber = ?, expiry = ?, cvv =? WHERE infoID = ?";
		PreparedStatement stmt = dbConnect.prepareStatement(query);


		stmt.setString(1,pi.getfName());
		stmt.setString(2,pi.getlName());
		stmt.setString(3, pi.getAddress());
		stmt.setInt(4,pi.getCardNum());
		stmt.setDate(5,java.sql.Date.valueOf(pi.getExpiry()));
		stmt.setInt(6, pi.getCvv());
		stmt.setInt(7, pi.getInfoID());

		int rowCount = stmt.executeUpdate();
		//System.out.println(rowCount);
		stmt.close();
	}


	public void insertPaymentInfo(PaymentInfo pi) throws SQLException{

		String query = "INSERT INTO PAYMENTINFO(infoID,fName,lName,address,cardNumber,expiry,cvv) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement stmt = dbConnect.prepareStatement(query);

		stmt.setInt(1, pi.getInfoID());
		stmt.setString(2,pi.getfName());
		stmt.setString(3,pi.getlName());
		stmt.setString(4,pi.getAddress());
		stmt.setInt(5,pi.getCardNum());
		stmt.setDate(6,java.sql.Date.valueOf(pi.getExpiry()));
		stmt.setInt(7,pi.getCvv());

		int rowCount = stmt.executeUpdate();
		//System.out.println(rowCount);
		stmt.close();
	}
	public PaymentInfo getUserPaymentInfo(int userId) throws SQLException{

		PaymentInfo info = new PaymentInfo();
		String query = "SELECT * FROM PAYMENTINFO WHERE infoID = ?";
		PreparedStatement stmt = this.dbConnect.prepareStatement(query);
		stmt.setInt(1, userId);

		ResultSet results = stmt.executeQuery();

		while (results.next()) {

				PaymentInfo pi = new PaymentInfo();
				pi.setInfoID(results.getInt("infoId"));
				pi.setfName(results.getString("fName"));
				pi.setlName(results.getString("lName"));
				pi.setAddress(results.getString("address"));
				pi.setCardNum(results.getInt("cardNumber"));
				pi.setExpiry(results.getDate("expiry").toLocalDate());
				pi.setCvv(results.getInt("cvv"));

				info = pi ;
		
		}
		return info;
	}
	/*******
	 END
	 THIS SECTION PERTAINS TO ALL METHODS THAT INVOLVING PAYMENT INFO TABLE.
	 END
	 ********/


	/*******
	 START
	 THIS SECTION PERTAINS TO ALL METHODS THAT INVOLVING THEATRE TABLE.
	 START
	 ********/


	/*******
	 END
	 THIS SECTION PERTAINS TO ALL METHODS THAT INVOLVING THEATRE TABLE.
	 END
	 ********/

	/*******
	 START
	 THIS SECTION PERTAINS TO ALL METHODS THAT INVOLVING MOVIE TABLE.
	 START
	 ********/
	public ArrayList<String> selectMovieFromTheatre(String name) throws SQLException{
		ArrayList searchedmovies = new ArrayList<String>();

		String query = "SELECT DISTINCT M.title FROM THEATRESHOWTIMES AS T, SHOWTIME AS S, MOVIE AS M, THEATRE AS TH \n " +
				"WHERE TH.name = ? AND T.showtimeID = S.showtimeID AND S.movieID = M.movieID;";
		PreparedStatement stmt =dbConnect.prepareStatement(query);

		stmt.setString(1, name);

		ResultSet results = stmt.executeQuery();

		while(results.next()){
			searchedmovies.add(results.getString("title"));
		}
		stmt.close();


		return searchedmovies;
	}

	public ArrayList<Movie> getMovies() throws SQLException{
		ArrayList movies = new ArrayList<Movie>();

		Statement stmt = dbConnect.createStatement();
		results =stmt.executeQuery("SELECT * FROM MOVIE");

		while(results.next()){
			Movie movie = new Movie(results.getString("title"),
					results.getInt("movieID"),
					results.getString("news"),
					results.getBoolean("earlyRelease"));
			movies.add(movie);
		}
		stmt.close();

		return movies;
	}
	public ArrayList<Movie> getReleasedMovies() throws SQLException{
		ArrayList movies = new ArrayList<Movie>();

		Statement stmt = dbConnect.createStatement();
		results =stmt.executeQuery("SELECT * FROM MOVIE WHERE earlyRelease = 0");

		while(results.next()){
			Movie movie = new Movie(results.getString("title"),
					results.getInt("movieID"),
					results.getString("news"),
					results.getBoolean("earlyRelease"));
			movies.add(movie);
		}
		stmt.close();

		return movies;
	}
	public int getMovieIdByName(String name) throws SQLException{
		int movieId = 0;

		String query = "SELECT movieID FROM MOVIE WHERE title = ?";

		PreparedStatement stmt = dbConnect.prepareStatement(query);
		stmt.setString(1, name);

		ResultSet results = stmt.executeQuery();

		while (results.next()) {
			movieId = results.getInt("movieID");
		}

		stmt.close();


		return movieId;
	}

	/*******
	 END
	 THIS SECTION PERTAINS TO ALL METHODS THAT INVOLVING MOVIE TABLE.
	 END
	 ********/

	/*******
	 START
	 THIS SECTION PERTAINS TO ALL METHODS THAT INVOLVING SHOWTIME TABLE.
	 START
	 ********/
	public ArrayList<Showtime> getShowtimesByMovie(int movieId) throws SQLException{

		ArrayList showtimes = new ArrayList<Showtime>();

		String query = "SELECT * FROM SHOWTIME AS S WHERE S.movieID = ? ";
		PreparedStatement stmt = dbConnect.prepareStatement(query);

		stmt.setInt(1, movieId);

		ResultSet results = stmt.executeQuery();

		while(results.next()){
			Showtime showtime = new Showtime(results.getInt("showtimeID"),
					results.getInt("movieID"),
					results.getDate("date").toLocalDate(),
					results.getTime("time").toLocalTime());

			showtimes.add(showtime);
		}
		stmt.close();

		return showtimes;
	}

	public ArrayList<Showtime> getShowtimesByShowtimeId(int showtimeId) throws SQLException{

		ArrayList showtimes = new ArrayList<Showtime>();

		String query = "SELECT * FROM SHOWTIME AS S WHERE S.showtimeID = ? ";
		PreparedStatement stmt = dbConnect.prepareStatement(query);

		stmt.setInt(1, showtimeId);

		ResultSet results = stmt.executeQuery();

		while(results.next()){
			Showtime showtime = new Showtime(results.getInt("showtimeID"),
					results.getInt("movieID"),
					results.getDate("date").toLocalDate(),
					results.getTime("time").toLocalTime());

			showtimes.add(showtime);
		}
		stmt.close();

		return showtimes;
	}


	/*******
	 END
	 THIS SECTION PERTAINS TO ALL METHODS THAT INVOLVING SHOWTIME TABLE.
	 END
	 ********/

	/*******
	 START
	 THIS SECTION PERTAINS TO ALL METHODS THAT INVOLVING SEAT TABLE.
	 START
	 ********/
	public ArrayList<Seat> getSeats( int showtimeId) throws SQLException{

		ArrayList<Seat> seats = new ArrayList<Seat>();

		String query = "SELECT * FROM SEAT WHERE showtimeID = ?";
		PreparedStatement stmt = dbConnect.prepareStatement(query);


		stmt.setInt(1, showtimeId);

		results = stmt.executeQuery();

		while(results.next()){
			Seat seat = new Seat(results.getInt("number"),
					results.getBoolean("reserved"),
					results.getInt("showtimeID"),
					results.getInt("price"));

			seats.add(seat);
		}
		stmt.close();


		return seats;
	}
	public  void seatReserved(int seatnumber, int showtimeId) throws SQLException{

		String query = "UPDATE SEAT SET reserved = TRUE WHERE number = ? AND showtimeID =?";
		PreparedStatement stmt = dbConnect.prepareStatement(query);

		stmt.setInt(1, seatnumber);
		stmt.setInt(2, showtimeId);

		int rowCount = stmt.executeUpdate();
		//System.out.println(rowCount);
		stmt.close();

	}

	public  void seatNotReserved(int seatnumber, int showtimeId) throws SQLException{


		String query = "UPDATE SEAT SET reserved = FALSE WHERE number = ? AND showtimeID =?";
		PreparedStatement stmt = dbConnect.prepareStatement(query);

		stmt.setInt(1, seatnumber);
		stmt.setInt(2, showtimeId);

		int rowCount = stmt.executeUpdate();
		//System.out.println(rowCount);
		stmt.close();

	}

	/*******
	 END
	 THIS SECTION PERTAINS TO ALL METHODS THAT INVOLVING SEAT TABLE.
	 END
	 ********/

	/*******
	 START
	 THIS SECTION PERTAINS TO ALL METHODS THAT INVOLVING TICKET TABLE.
	 START
	 ********/
	public void insertTicket(Ticket t) throws SQLException {

		String query = "INSERT INTO TICKET (ticketID,showtimeID,theatreID,seat,userID) VALUES(?,?,?,?,?)";
		PreparedStatement stmt = dbConnect.prepareStatement(query);

		stmt.setInt(1, t.getTicketId());
		stmt.setInt(2, t.getShowtimeId());
		stmt.setInt(3,t.getTheatreId());
		stmt.setInt(4,t.getSeat());
		
		if(t.getUserId() != -1)
			stmt.setInt(5, t.getUserId());
		else
			stmt.setNull(5,1);

		int rowCount = stmt.executeUpdate();
		//System.out.println(rowCount);
		stmt.close();


	}
	public int cancelTicket(int ticketId) throws SQLException{
		String query = "DELETE FROM TICKET WHERE ticketID = ?";
		PreparedStatement stmt = this.dbConnect.prepareStatement(query);

		stmt.setInt(1, ticketId);
//		results = stmt.executeQuery();

		int rowCount = stmt.executeUpdate();
		//System.out.println(rowCount);
		stmt.close();
		return rowCount;
	}

	public ArrayList<Ticket> getTickets(int userId) throws SQLException{
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();

		String query = "SELECT * FROM TICKET WHERE userID = ?";
		PreparedStatement stmt = this.dbConnect.prepareStatement(query);
		results = stmt.executeQuery();

		stmt.setInt(1,userId);

		while(results.next()){
			Ticket ticket = new Ticket(results.getInt("ticketID"),
					results.getInt("showtimeID"),
					results.getInt("theatreID"),
					results.getInt("seat"),
					results.getInt("userID"));
			tickets.add(ticket);
		}
		stmt.close();
		return tickets;
	}
	public Ticket getTicket(int ticketId) throws SQLException{
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();

		String query = "SELECT * FROM TICKET WHERE ticketID = ?";
		PreparedStatement stmt = this.dbConnect.prepareStatement(query);
		stmt.setInt(1,ticketId);
		results = stmt.executeQuery();

		
		Ticket ticket = null;
		while(results.next()){
			ticket = new Ticket(results.getInt("ticketID"),
					results.getInt("showtimeID"),
					results.getInt("theatreID"),
					results.getInt("seat"),
					results.getInt("userID"));
			
		}
		stmt.close();
		return ticket;
	}

	/*******
	 END
	 THIS SECTION PERTAINS TO ALL METHODS THAT INVOLVING TICKET TABLE.
	 END
	 ********/

	/*******
	 START
	 THIS SECTION PERTAINS TO ALL METHODS THAT INVOLVING RECEIPT TABLE.
	 START
	 ********/
	public ArrayList<Receipt> getReceipts(int userId) throws SQLException{
		ArrayList<Receipt> receipts = new ArrayList<Receipt>();

		String query = "SELECT * FROM RECEIPT WHERE userID = ?";
		PreparedStatement stmt = this.dbConnect.prepareStatement(query);
		results = stmt.executeQuery();

		stmt.setInt(1,userId);


		while(results.next()){
			Receipt receipt = new Receipt(results.getInt("receiptID"),
					results.getInt("ticketID"),
					results.getInt("price"),
					results.getInt("userID"));
			receipts.add(receipt);
		}
		stmt.close();
		return receipts;
	}
	public void insertReceipt(Receipt r) throws SQLException {


		String query = "INSERT INTO RECEIPT (receiptID, ticketID,price,userID) VALUES(?,?,?,?)";
		PreparedStatement stmt = dbConnect.prepareStatement(query);
		stmt.setInt(1, r.getTicketId());
		stmt.setInt(2, r.getTicketId());
		stmt.setInt(3, ((int) r.getPrice()));
		if(r.getUserId() != -1)
			stmt.setInt(4, r.getUserId());
		else
			stmt.setNull(4,1);

		int rowCount = stmt.executeUpdate();
		stmt.close();
	}

	/*******
	 END
	 THIS SECTION PERTAINS TO ALL METHODS THAT INVOLVING RECEIPT TABLE.
	 END
	 ********/

	/*******
	 START
	 THIS SECTION PERTAINS TO ALL METHODS THAT INVOLVING CREDIT TABLE.
	 START
	 ********/
	public ArrayList<Credit> getCredits(int creditId) throws SQLException{
		ArrayList<Credit> credits = new ArrayList<Credit>();

		String query = "SELECT * FROM CREDIT WHERE creditID = ?";
		PreparedStatement stmt = this.dbConnect.prepareStatement(query);
		results = stmt.executeQuery();

		while(results.next()){
			Credit credit = new Credit(results.getInt("creditID"),
					results.getDate("expiry").toLocalDate(),
					results.getDouble("value"),
					results.getInt("userID"));
			credits.add(credit);
		}
		stmt.close();
		return credits;
	}
	public void cancelCredit(int creditId) throws SQLException{
		String query = "DELETE FROM CREDIT WHERE creditID = ?";
		PreparedStatement stmt = this.dbConnect.prepareStatement(query);
		results = stmt.executeQuery();

		stmt.setInt(1,creditId);

		int rowCount = stmt.executeUpdate();
		//System.out.println(rowCount);
		stmt.close();
	}
	public void insertCredit(Credit c) throws SQLException{
		String query = "INSERT INTO CREDIT (creditID, expiry,value,userID) VALUES(?,?,?,?)";
		PreparedStatement stmt = dbConnect.prepareStatement(query);
		stmt.setInt(1, c.getCreditId());
		stmt.setDate(2, java.sql.Date.valueOf(c.getExpiry()));
		stmt.setDouble(3,  c.getAmount());
		if(c.getUserId() != -1)
			stmt.setInt(4, c.getUserId());
		else
			stmt.setNull(4,1);

		int rowCount = stmt.executeUpdate();
		//System.out.println(rowCount);
		stmt.close();
	}
	public Credit getCredit(int creditId) throws SQLException{
		Credit credits = new Credit();

		String query = "SELECT * FROM CREDIT WHERE creditID = ?";
		PreparedStatement stmt = this.dbConnect.prepareStatement(query);
		stmt.setInt(1, creditId);
		results = stmt.executeQuery();

		while(results.next()){
			Credit credit = new Credit(results.getInt("creditID"),
					results.getDate("expiry").toLocalDate(),
					results.getDouble("value"),
					results.getInt("userID"));
			credits =credit;
		}
		stmt.close();
		return credits;
	}
	public ArrayList<Credit> getUserCredit(int userId) throws SQLException{
		ArrayList<Credit> credits = new ArrayList<>();

		String query = "SELECT * FROM CREDIT WHERE userID = ?";
		PreparedStatement stmt = this.dbConnect.prepareStatement(query);
		stmt.setInt(1, userId);
		results = stmt.executeQuery();

		while(results.next()){
			Credit credit = new Credit(results.getInt("creditID"),
					results.getDate("expiry").toLocalDate(),
					results.getDouble("value"),
					results.getInt("userID"));
			credits.add(credit);
		}
		stmt.close();
		return credits;
	}

	public  void updateCredit(int creditId, double value) throws SQLException{

		String query = "UPDATE CREDIT  SET value = ? WHERE creditID =?";
		PreparedStatement stmt = dbConnect.prepareStatement(query);

		stmt.setDouble(1, value);
		stmt.setInt(2, creditId);

		int rowCount = stmt.executeUpdate();
		//System.out.println(rowCount);
		stmt.close();

	}

	/*******
	 END
	 THIS SECTION PERTAINS TO ALL METHODS THAT INVOLVING CREDIT TABLE.
	 END
	 ********/






//
//	public ArrayList<Seat> getSeats(){
//		ArrayList<Seat> seats = new ArrayList<Seat>();
//		try{
//			Statement stmt = dbConnect.createStatement();
//			results =stmt.executeQuery("SELECT * FROM SEAT");
//
//			while(results.next()){
//				Seat seat = new Seat(results.getInt("number"),
//						results.getBoolean("reserved"));
//
//				seats.add(seat);
//			}
//			stmt.close();
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//
//		return seats;
//	}
//
//	/*******
//
//	  END OF ENTITY LISTS (unfinished)
//
//	 *******/
//
//
//
//
//
//

	
	//
//
////	public ArrayList<Movie> searchMovie(String title, int TheatreId) throws SQLException {
////
////		return null;
////	}
//
//
//
//
//
////	public ArrayList<Theatre> searchTheatre(String title) throws SQLException {
////		// TODO Auto-generated method stub
////		return null;
////	}
//




//
//
//
//	public boolean isValidTicket(int showTimeId){
//		boolean isTicket = true;
//
//		try{
//			Statement stmt = dbConnect.createStatement();
//
//			results = stmt.executeQuery("SELECT * FROM SHOWTIME");
//
//			while (results.next()){
//				if (results.getInt("showtimeID")==showTimeId)
//					isTicket = false;
//			}
//			stmt.close();
//		}catch (SQLException e){
//			e.printStackTrace();
//		}
//		return isTicket;
//	}
//
//
//
//	public void close() {
//		try {
//			results.close();
//			dbConnect.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	/*****
	 THIS SECTION PERTAINS TO ALL *SEARCHES*
	 *****/

	/**
	 * This function is going to search the database for a movie based on the user given name.
	 */









	public static void main(String[] args) {
		DBController db = new DBController("jdbc:mysql://localhost/ENSF614PROJECT","user","12345");
	}



}