/*
 * 
 */
package model.manager;

import java.util.GregorianCalendar;
import model.user.Borrower;
import model.user.Manager;
import model.user.Student;
import model.user.Teacher;
import model.user.User;
import model.user.Users;

/**
 * The Class UserManager.
 * @author Marina Delerce & Romain Guillot 
 * @version 1.0.0
 */
public class UserManager {
	
	/** The users. */
	private Users users;
	
	/** The connected user. */
	private User connectedUser;
	
	/** The manager. */
	private Manager manager;
	
	/** The Constant conversion. */
	private final static int conversion = 3600*24*1000;

	/**
	 * Instantiates a new user manager.
	 */
	public UserManager() {
		this.users = new Users();
		this.connectedUser = null;
	}

	/**
	 * Adds the new user.
	 *
	 * @param userType the user type
	 * @param lastname the lastname
	 * @param firstname the firstname
	 * @param login the login
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean addNewUser(String userType, String lastname,
			String firstname, String login, String password) {
		if (!checkSameUser(login)) {
			switch (userType) {
			case "Manager":
				this.users
						.add(new Manager(lastname, firstname, login, password));
				break;
			case "Teacher":
				this.users
						.add(new Teacher(lastname, firstname, login, password));
				break;
			case "Student":
				this.users
						.add(new Student(lastname, firstname, login, password));
				break;
			default:

			}
			return true;
		} else
			return false;
	}

	/**
	 * Check same user.
	 *
	 * @param login the login
	 * @return true, if successful
	 */
	private boolean checkSameUser(String login) {
		return users.checkSameUser(login);
	}

	/**
	 * Check user password.
	 *
	 * @param login the login
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean checkUserPassword(String login, String password) {
		if ((connectedUser = users.checkUserPassword(login, password))!=null) return true;
		else return false;
	}

	/**
	 * Gets the connected user.
	 *
	 * @return the connected user
	 */
	public User getConnectedUser() {
		return this.connectedUser;
	}

	/**
	 * Sign off.
	 */
	public void signOff() {
		connectedUser = null;

	}

	/**
	 * Delete user.
	 *
	 * @param lastname the lastname
	 * @param firstname the firstname
	 * @param login the login
	 * @return true, if successful
	 */
	public boolean deleteUser(String lastname, String firstname, String login) {
		return users.deleteUser(lastname, firstname, login);
	}
	
	/**
	 * Save.
	 */
	public void save(){
		users.save();
	}
	
	/**
	 * Load.
	 */
	public void load(){
		users.load();
	}

	/**
	 * Can book.
	 *
	 * @param connectedUser the connected user
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return true, if successful
	 */
	public boolean canBook(User connectedUser, GregorianCalendar startDate,
			GregorianCalendar endDate) {
		if(connectedUser instanceof Manager){
			return true;
		}
		
		if(connectedUser instanceof Borrower){
			Borrower borrower = (Borrower) connectedUser;
			boolean book = true;
			long duration;
			// verifier les dates
			if (startDate.after(endDate))
				book = false;
			// verifier les durees
			else {
				duration = (endDate.getTimeInMillis() - startDate.getTimeInMillis()) / conversion;
				if (duration > borrower.getLoanDuration())
					book = false;
			}
			
			if(book){
				if(connectedUser instanceof Student){
					GregorianCalendar today = new GregorianCalendar();
					today.add(GregorianCalendar.DAY_OF_MONTH, 7);
					if(startDate.after(today)) {
						book = false;
					}
				}
			}

			return book;
		}
		
		return false;
	}
}
