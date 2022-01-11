package clients.clientModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import businessLogic.Movie;
import businessLogic.Showtime;
import clients.payment.Payment;
import clients.payment.PaymentAuthorizer;
import frontend.GUIController;
import frontend.PurchaseView;
import project.DBController;

public class UserController {
	private DBController db;
	private GUIController gui;
	private User currentUser;

	// Constructor, adds action listeners
	public UserController(DBController db, GUIController gui) {
		this.db = db;
		this.gui = gui;

		gui.getLoginView().addLoginButtonListener(new LoginListener());
		gui.getLoginView().addRegButtonListener(new RegListener());
		gui.getLoginView().addGuestButtonListener(new GuestListener());

		gui.getRegisterView().addSubmitButtonListener(new SubmitRegListener());
		gui.getRegisterView().addGoBackButtonListener(new GoBackRegListener());

		gui.getMovieView().accountInfoBtnListener(new AccountInfoListener());

		gui.getSeatView().addPurchaseButtonListener(new PurchaseListener());
		gui.getSeatView().addReturnBtnListener(new SeatViewReturnBtnListener());
		gui.getCancelTicket().addReturnBtnListener(new returnCancelTicketListener());
		gui.getCancelTicket().addCancelBtnListener(new CancelTicketListener());
		gui.getAccount().addGoBackButtonListener(new returnAccountListener());
		gui.getAccount().addSaveButtonListener(new SaveAccountInfoListener());
		gui.getCancelTicket().addCancelBtnListener(new CancleTickebtntListener());



		gui.getEmail().addReturnBtnListener(new EmailReturnBtnListener());
	}

	// Checks login information
	public boolean login(String username, String password) {
		RegisteredUser ru = getUserFromDB(username);
		if (ru != null && ru.getPassword().equals(password)) {
			currentUser = ru;
			return true;
		} else
			return false;
	}

	// gets user from DB
	private RegisteredUser getUserFromDB(String username) {
		try {
			return db.readRUser(username);
		} catch (SQLException e) {
			return null;
		}
	}

	// Updates User in DB
	private boolean updateRUInDB(RegisteredUser ru) {
		try {
			db.updateRUser(ru);
			return true;
		} catch (SQLException e) {
			// e.printStackTrace();
			return false;
		}
	}

	// Insert user in DB
	private boolean insertRUInDB(RegisteredUser ru) {
		try {
			db.insertRUser(ru);
			return true;
		} catch (SQLException e) {
			// e.printStackTrace();
			return false;
		}
	}

	// Insert Ticket in DB
	private boolean insertTicketInDB(Ticket t) {
		try {
			db.insertTicket(t);
			db.seatReserved(t.getShowtimeId(), t.getSeat());
			// db.updateSeat()
			return true;
		} catch (SQLException e) {
			// e.printStackTrace();
			return false;
		}
	}

	// Remove ticket from DB
	private boolean removeTicketFromDB(int ticketID) {
		try {
			Ticket t = db.getTicket(ticketID);
			if (db.cancelTicket(ticketID) == 1) {
				setSeatNotReserved(t);
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return false;
		}
	}

	// Insert Receipt in DB
	public void insertReceipt(Receipt r) {
		try {
			db.insertReceipt(r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	// Insert Payment in DB
	private boolean insertPaymentInfoInDB(PaymentInfo p) {
		try {
			db.insertPaymentInfo(p);
			return true;
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}

	// Update payment in DB
	private boolean updatePaymentInfoInDB(PaymentInfo p) {
		try {
			db.updatePaymentInfo(p);
			return true;
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}

	// Insert Credit in DB
	private boolean insertCreditInDB(Credit c) {
		try {
			db.insertCredit(c);
			return true;
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}

	// Sets seat reserved for ticket
	public void setSeatNotReserved(Ticket t) {
		try {
			db.seatNotReserved(t.getSeat(), t.getShowtimeId());
		} catch (SQLException e) {

		}
	}

	// Insert credit in db
	public Credit getCreditFromDB(int couponId) {
		try {
			return db.getCredit(couponId);
		} catch (SQLException e) {
			// e.printStackTrace();
			return null;
		}

	}

	// Gets credit for the current user
	public Credit getUserCredit() {
		try {
			if (currentUser instanceof RegisteredUser) {
				Credit credit = new Credit();
				
				 ArrayList<Credit> credits=  db.getUserCredit(((RegisteredUser) currentUser).getUserId());
				 for (Credit c: credits) {
					 credit.setAmount(credit.getAmount()+c.getAmount());
				 }
				 return credit;
			}
			return null;
		} catch (SQLException e) {
			return null;
		}

	}

	// Update Credit in DB
	public void updateCreditInDB(int couponId, double value) {
		try {
			db.updateCredit(couponId, value);
		} catch (SQLException e) {
			 e.printStackTrace();
		}

	}

	// Checks credit expiry date
	private boolean creditValid(Credit credit) {
		if (credit.getExpiry().compareTo(LocalDate.now()) >= 0) {
			return true;
		}
		return false;
	}

	// Gets users payment info from DB
	private PaymentInfo getUserPaymentInfo(int userId) {
		try {
			return db.getUserPaymentInfo(userId);
		} catch (SQLException e) {
			return null;
		}

	}

	// Populates payment info with info from DB for that user
	private void populateUserPaymentInfo() {
		PaymentInfo p = getUserPaymentInfo(((RegisteredUser) currentUser).getUserId());
		if (p != null) {
			String[] s = new String[6];
			s[0] = p.getfName();
			s[1] = p.getlName();
			s[2] = p.getAddress();
			s[3] = String.valueOf(p.getCardNum());
			s[4] = YearMonth.from(p.getExpiry()).format(DateTimeFormatter.ofPattern("MM/yy")).toString();
			s[5] = String.valueOf(p.getCvv());
			gui.getPurchaseView().setPaymentInfo(s);
		}
	}

	// Populates current user info on edit account page
	public void populateAccontInfo() {
		PaymentInfo p = getUserPaymentInfo(((RegisteredUser) currentUser).getUserId());
		if (p != null) {
			String[] s = new String[10];
			if (currentUser instanceof RegisteredUser) {
				s[0] = ((RegisteredUser) currentUser).getUsername();
				s[1] = ((RegisteredUser) currentUser).getPassword();
				s[2] = ((RegisteredUser) currentUser).getEmail();
				s[3] = p.getfName();
				s[4] = p.getlName();
				s[5] = p.getAddress();
				s[6] = String.valueOf(p.getCardNum());
				s[7] = YearMonth.from(p.getExpiry()).format(DateTimeFormatter.ofPattern("MM/yy")).toString();
				s[8] = String.valueOf(p.getCvv());
				currentUser.setCredit(getUserCredit());
				if (currentUser.getCredit() != null) {
					s[9] = String.valueOf(currentUser.getCredit().getAmount());
				} else {
					s[9] = "0";
				}
			}
			gui.getAccount().setUserInfo(s);

		}
	}

	// Verifies user payment (only checks if info matches DB)
	public boolean verifyPayment(PaymentInfo p) {
		Payment pay = new Payment(new PaymentAuthorizer(db));
		return pay.communicate(p, 0);

	}

	// Sets Seat reserved
	public void setSeatReserved(int seatNum, int showtime) {
		try {
			db.seatReserved(seatNum, showtime);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	// Enter Payment btn on PurchaseView
	class EnterPaymentListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// String[] r = gui.showPurchase();
			// call showPaymentSuccessful or showPaymentFailed method
		}
	}

	// email btn on PurchaseView
	class EmailInfoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// String[] r = gui.showPurchase();
			gui.setPurchaseViewVisible(false);
			gui.setEmailViewVisible(true);
		}
	}

	// Listener for login button
	class LoginListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String s[] = gui.getLoginView().getLoginInfo();
			if (login(s[0], s[1])) {
				gui.login();
				gui.getLoginView().showLoginSuccessful();
				gui.setLoginVisible(false);
				gui.setMovieViewVisible(true);
			} else {
				gui.getLoginView().showLoginFailed();
			}

		}
	}

	// Listener for guest button
	class GuestListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.setLoginVisible(false);
			gui.setMovieViewVisible(true);
			currentUser = new User();
		}
	}

	// Listener for register on LoginView
	class RegListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.setLoginVisible(false);
			gui.setRegVisible(true);
		}
	}

	// Listener for submit registration on register view
	class SubmitRegListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Random rand = new Random(); // instance of random class
			int int_random = rand.nextInt(999999);

			String s[] = gui.getRegisterView().getRegInfo();
			RegisteredUser r = new RegisteredUser();
			r.setUserId(int_random);
			r.setInfoID(int_random);
			r.setUserId(int_random);
			r.setUsername(s[0]);
			r.setPassword(s[1]);
			r.setEmail(s[2]);
			r.setFeePaid(LocalDate.now());
			YearMonth ym = YearMonth.parse(s[7], DateTimeFormatter.ofPattern("MM/yy"));
			PaymentInfo p = new PaymentInfo(int_random, s[3], s[4], s[5], Integer.parseInt(s[6]), ym.atDay(1),
					Integer.parseInt(s[8]));
			if (insertPaymentInfoInDB(p)) {
				if (verifyPayment(p)) {
					if (insertRUInDB(r)) {
						gui.getRegisterView().showRegSuccessful();
						gui.setRegVisible(false);
						gui.setLoginVisible(true);
					} else {
						gui.getRegisterView().showRegFailed();
					}
				}
			} else {
				gui.getRegisterView().showRegFailed();
			}

		}
	}

	// Listener for goBack on register view
	class GoBackRegListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.setLoginVisible(true);
			gui.setRegVisible(false);

		}
	}

	// return btn on CalcelTicket View
	class returnCancelTicketListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.setCancleTicketViewVisible(false);
			gui.setMovieViewVisible(true);
		}
	}
	public Showtime getShowtime(int showtimeID) {
		try {
			return db.getShowtimesByShowtimeId(showtimeID).get(0);
		} catch (SQLException e) {
			return null;
		}
		
	}
	// Listener for cancel ticket button
	class CancelTicketListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String ticketID = gui.getCancelTicket().getTicketId();
			//System.out.println(ticketID);
			try {
				Ticket t = db.getTicket(Integer.parseInt(ticketID));
				Showtime s = getShowtime(t.getShowtimeId());
				LocalDateTime cancelDateTime = LocalDateTime.of(s.getDate().minusDays(3),s.getTime());
				if(cancelDateTime.isAfter(LocalDateTime.now())) {
					if (removeTicketFromDB(Integer.parseInt(ticketID))) {

						Random rand = new Random();
						int int_random = rand.nextInt(999999);
						int userId;
						if (currentUser instanceof RegisteredUser) {
							userId = ((RegisteredUser) currentUser).getUserId();
						} else {
							userId = -1;
						}
						Credit c = new Credit(int_random, LocalDate.now().plusYears(1), 10, userId);
						if (t.getUserId() == 0) {
							c.setAmount(c.getAmount() * 0.85);
						}
						insertCreditInDB(c);

						gui.getCancelTicket().showCredit(c.toString());
						gui.getCancelTicket().showCancelled();
					} else {
						gui.getCancelTicket().showError("Cancel Failed");
					}
				}else {
					gui.getCancelTicket().showError("Can not cancel within 72 hours");
				}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			gui.getCancelTicket().showError("Ticket ID must be a number");
		} catch (SQLException e2) {
				//e2.printStackTrace();
				gui.getCancelTicket().showError("Can not find ticket");
			}

		}

	}

	// return btn on Account View
	class returnAccountListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.setAccountViewVisible(false);
			gui.setMovieViewVisible(true);
		}
	}

	// Email receipt/Ticket btn on Purchase Ticket View
	class PurchaseEmailbtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String[][] ticketInfo = new String[currentUser.getTickets().size()][1];
			String[][] reciptInfo = new String[1][1];

			int i = 0;
			for (Ticket t : currentUser.getTickets()) {
				ticketInfo[i] = new String[] { String.valueOf(t.getTicketId()), gui.getSelectedMovie().getTitle(),
						String.valueOf(t.getTheatreId()), String.valueOf(gui.getSelectedShowtime().getDate()) + " " +String.valueOf(gui.getSelectedShowtime().getTime()),
						String.valueOf(t.getSeat()), "$10" };
				i++;
			}
			Receipt r = currentUser.getReceipt();
			reciptInfo[0] = new String[] { String.valueOf(r.getReceiptId()),
					"$" + String.valueOf(currentUser.getTickets().size() * 10) };

			gui.getEmail().setTicketInfoFromDB(ticketInfo);
			gui.getEmail().setReceiptInfo(reciptInfo);
			gui.getEmail().setup();
			gui.setPurchaseViewVisible(false);
			gui.setEmailViewVisible(true);

		}
	}

	// return btn on EmailView
	class EmailReturnBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.setEmailViewVisible(false);
			gui.setPurchaseViewVisible(true);
		}
	}

	// cancleTicketbtn on Cancle Ticket View
	class CancleTickebtntListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.getCancelTicket().setTicketId(gui.getCancelTicket().getTicketIDTextField().getTextField().getText());
			String ticketNo = gui.getCancelTicket().getTicketId();
		}
	}

	// Creates a purchase view with passed info
	public void createPurchaseView(int numofSelectedSeats, String movieName, String theaterName, String showtime,
			int price, List<String> selectedSeats) {
		gui.setSeatViewVisible(false);
		gui.setPurchaseView(
				new PurchaseView(numofSelectedSeats, movieName, theaterName, showtime, price, selectedSeats));
		gui.getPurchaseView().setTitle("Purchase Ticket");
		gui.getPurchaseView().setVisible(true);
		gui.getPurchaseView().setBounds(10, 10, 950, 450);
		gui.getPurchaseView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.getPurchaseView().setResizable(true);
		gui.getPurchaseView().applyCouponBtnListener(new ApplyCouponListener());
		gui.getPurchaseView().addConfirmPaymentBtnListener(new ConfirmPaymentListener());
		gui.getPurchaseView().emailBtnListener(new PurchaseEmailbtnListener());
		gui.getPurchaseView().addreturnBtnListener(new PurchaseReturnbtntListener());
		if (currentUser instanceof RegisteredUser) {
			populateUserPaymentInfo();
		}
	}

	// Listener for purchase ticket button on seatView
	class PurchaseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(gui.getSeatView().getNumofSelectedSeats() > 0) {
				//System.out.print(gui.selectedMovie());
				if ((gui.getSelectedMovie().isEarlyRelease()
						&& ((gui.getSeatView().getNumofAvailableSeats() - gui.getSeatView().getNumofSelectedSeats())
						/ 10.0 >= 0.9) )|| gui.getSelectedMovie().isEarlyRelease() == false) {
				String movieName, theaterName, showtime;
				Movie m = gui.getSelectedMovie();
				movieName = m.getTitle();
				theaterName = "Calgary Theater"; // String.valueOf(gui.selectedTheatre.getTheatreId());
				showtime = gui.getSelectedShowtime().getTime().toString();
				List<String> selectedSeats = gui.getSeatView().getSelectedSeats();
				
						// THIS SHOULD TO BE CHANGED, need to change how ticketID is created.
						Random rand = new Random(); // instance of random class
						int int_random = rand.nextInt(999999);
						int userId;
						if (currentUser instanceof RegisteredUser) {
							userId = ((RegisteredUser) currentUser).getUserId();
						} else {
							userId = -1;
						}
		
						for (String seat : selectedSeats) {
							Ticket t = new Ticket(rand.nextInt(999999), gui.getSelectedShowtime().getShowtimeId(),
									gui.getSelectedTheatre().getTheatreId(), Integer.parseInt(seat), userId);
							currentUser.getTickets().add(t);
						}
						int numofSelectedSeats, price;
						price = 10; // Hardcoded price of $10 per seat
						numofSelectedSeats = selectedSeats.size();
		
						// pass all movie, theater, showtime, seats info to this view (mostly frontend data, user selection)
						createPurchaseView(numofSelectedSeats, movieName, theaterName, showtime, price,
								selectedSeats);
				}else {
					gui.getSeatView().showError("Only 10% of early release seats can be purchased");
				}
			}else {
				gui.getSeatView().showError("Please select a seat");
			}
				

		}
	}

	// Listener for apply coupon button
	class ApplyCouponListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String coupon = gui.getPurchaseView().getCouponCode();
			int id = -1;
			try {
				id = Integer.parseInt(coupon);
			} catch (NumberFormatException err) {
				gui.getPurchaseView().showPaymentFailed();
			}
			Credit credit = getCreditFromDB(id);
			//System.out.println(credit);
			if (creditValid(credit)) {
				gui.getPurchaseView().setCoupon(String.valueOf(credit.getAmount()));
				currentUser.setCredit(credit);
			}
			else {
				gui.getPurchaseView().showCreditError();
			}

		}
	}
	//Return view on seatView
	class SeatViewReturnBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.getSeatView().reset();
			
			gui.setSeatViewVisible(false);
			gui.setMovieViewVisible(true);

		}
	}

	// Listener for confirm payment button
	class ConfirmPaymentListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] info = gui.getPurchaseView().getPaymentInfo();
			PaymentInfo p = new PaymentInfo();
			double total = Double.parseDouble(gui.getPurchaseView().getTotal());
			boolean paid = false;
			if(currentUser.getCredit() != null && currentUser.getCredit().getAmount() > Double.parseDouble(gui.getPurchaseView().getTotal())) {
				if (currentUser.getCredit() != null) {
					updateCreditInDB(currentUser.getCredit().getCreditId(),
							 (currentUser.getCredit().getAmount() - total));
					currentUser.getCredit().setAmount(currentUser.getCredit().getAmount()-total);
					paid = true;
					gui.getPurchaseView().showMessage("Remaining Credit: \n" + currentUser.getCredit().toString());
				}
			}
			else {
				try {
					p.setfName(info[0]);
					p.setlName(info[1]);
					p.setAddress(info[2]);
					p.setCardNum(Integer.parseInt(info[3]));
					YearMonth ym = YearMonth.parse(info[4], DateTimeFormatter.ofPattern("MM/yy"));
					p.setExpiry(ym.atDay(1));
					p.setCvv(Integer.parseInt(info[5]));
					if(verifyPayment(p)) {
						paid = true;
						//gui.getPurchaseView().showPaymentSuccessful();
					} else {
						gui.getPurchaseView().showPaymentFailed();
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					gui.getPurchaseView().showPaymentFailed();

				}
			}
			if(paid) {
				if (currentUser.getCredit() != null) {
					updateCreditInDB(currentUser.getCredit().getCreditId(),
							(int) (currentUser.getCredit().getAmount() - total));
				}
				int ticketId = 0;
				for (Ticket t : currentUser.getTickets()) {
					ticketId = t.getTicketId();
					insertTicketInDB(t);
					setSeatReserved(t.getSeat(), t.getShowtimeId());
				}
				Random rand = new Random(); // instance of random class
				int int_random = rand.nextInt(999999);
				int userId;

				if (currentUser instanceof RegisteredUser) {
					userId = ((RegisteredUser) currentUser).getUserId();
				} else {
					userId = -1;
				}
				Receipt r = new Receipt(int_random, ticketId, currentUser.getTickets().size() * 10, userId);
				currentUser.setReceipt(r);
				insertReceipt(r);
				gui.getPurchaseView().showPaymentSuccessful();
			}	
		}

	}

	// Listener for account info button
	class AccountInfoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (currentUser instanceof RegisteredUser) {
				gui.setMovieViewVisible(false);
				populateAccontInfo();
				gui.setAccountViewVisible(true);
			} else {
				gui.getMovieView().showErrorMessage("Please login first");
			}
		}
	}

	

	// Return btn for purchase view
	class PurchaseReturnbtntListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
				gui.getSeatView().reset();
				gui.getPurchaseView().reset();
				currentUser.getTickets().clear();
				gui.setPurchaseViewVisible(false);
				gui.setMovieViewVisible(true);
				

		}
	}

	// Listener for save button on account info page
	class SaveAccountInfoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String s[] = gui.getAccount().getUserInfo();
			RegisteredUser r = new RegisteredUser();
			r.setUserId(((RegisteredUser) currentUser).getUserId());
			r.setInfoID(((RegisteredUser) currentUser).getUserId());
			r.setUsername(s[0]);
			r.setPassword(s[1]);
			r.setEmail(s[2]);
			currentUser.getCredit().setAmount(Double.parseDouble(s[9]));
			r.setCredit(currentUser.getCredit());
			PaymentInfo p = new PaymentInfo();
			p.setfName(s[3]);
			p.setlName(s[4]);
			p.setAddress(s[5]);
			p.setCardNum(Integer.parseInt(s[6]));
			YearMonth ym = YearMonth.parse(s[7], DateTimeFormatter.ofPattern("MM/yy"));
			p.setExpiry(ym.atDay(1));
			p.setCvv(Integer.parseInt(s[8]));
			p.setInfoID(((RegisteredUser) currentUser).getUserId());

				if (updateRUInDB(r)) {
					if (updatePaymentInfoInDB(p)) {
						gui.getAccount().showUserUpdateSuccessful();
					} else {
						gui.getAccount().showUserUpdateFailed();
					}
				} else {
					gui.getAccount().showUserUpdateFailed();
				}
			}

		}

	}