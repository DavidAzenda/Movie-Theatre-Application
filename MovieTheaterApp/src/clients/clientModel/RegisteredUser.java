package clients.clientModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import clients.clientModel.PaymentInfo;

public class RegisteredUser extends User{
	private int userId;
	private String username;
	private String password;
	private String email;
	private LocalDate feePaid;
	private int infoID;


	/**
	 * @param userId
	 * @param username
	 * @param password
	 * @param email
	 * @param feePaid
	 */
	public RegisteredUser(int userId, String username, String password, String email, LocalDate feePaid,int infoID) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.feePaid = feePaid;
		this.infoID = infoID; 
	}
	public RegisteredUser(int userId, String username, String password, String email) {
		this.userId = userId;
		this.username = username;
		this.password = password;
	}

	public RegisteredUser() {
	}

	public RegisteredUser(String username, String password, String email, LocalDate feePaid, int infoID) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.feePaid = feePaid;
		this.infoID = infoID;
	}

	public int getInfoID() {
		return infoID;
	}

	public void setInfoID(int infoID) {
		this.infoID = infoID;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the feePaid
	 */
	public LocalDate getFeePaid() {
		return feePaid;
	}

	/**
	 * @param feePaid the feePaid to set
	 */
	public void setFeePaid(LocalDate feePaid) {
		this.feePaid = feePaid;
	}


	
	
}
