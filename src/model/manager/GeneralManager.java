package model.manager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import utils.DateUtils;
import model.loan.Loan;
import model.loan.Loans;
import model.material.Material;
import model.material.MaterialType;
import model.material.OS;
import model.material.SmartPhone;
import model.user.Manager;
import model.user.Student;
import model.user.User;

/**
 * The Class GeneralManager.
 * @author Marina Delerce & Romain Guillot 
 * @version 1.0.0
 */
public class GeneralManager {
	
	/** The material manager. */
	private MaterialManager materialManager;
	
	/** The user manager. */
	private UserManager userManager;
	
	/** The current date. */
	private GregorianCalendar currentDate;

	/**
	 * Instantiates a new general manager.
	 */
	public GeneralManager(){
		materialManager = new MaterialManager();
		userManager = new UserManager();
		currentDate = new GregorianCalendar();
	}
	
	public void populate() {
		userManager.addNewUser("Manager","Delerce", "Marina", "muttiMan", "md");
		userManager.addNewUser("Student","Delerce", "Marina", "mutti", "md");
		
		SmartPhone smartphone = new SmartPhone("Galaxy S3", "Samsung", "Super tel!", 72, OS.ANDROID, 7);
		materialManager.addMaterial(new Material(smartphone, "gs31"));
		materialManager.addMaterial(new Material(smartphone, "gs32"));
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
	public boolean addNewUser(String userType, String lastname, String firstname, String login, String password){
		return userManager.addNewUser(userType, lastname, firstname, login, password);
	}
	
	/**
	 * Book.
	 *
	 * @param ref the reference
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param quantity the quantity
	 * @return the loan created
	 */
	public Loan book(int ref, GregorianCalendar startDate,GregorianCalendar endDate, int quantity) {
		if (userManager.canBook(userManager.getConnectedUser(), startDate, endDate)
				&& materialManager.maxTimeLoanNotReach(ref, startDate, endDate)) {
			return materialManager.book(ref, userManager.getConnectedUser(), startDate, endDate, quantity);
		}else{
			return null;
		}
	}

	/**
	 * Check user password.
	 *
	 * @param login the login
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean checkUserPassword(String login, String password) {
		return userManager.checkUserPassword(login,password);
	}

	/**
	 * Gets the connected user.
	 *
	 * @return the connected user
	 */
	public User getConnectedUser() {
		return userManager.getConnectedUser();
	}

	/**
	 * Sign off.
	 */
	public void signOff() {
		userManager.signOff();
	}
	
	/**
	 * Gets the current date.
	 *
	 * @return the current date
	 */
	public GregorianCalendar getCurrentDate() {
		return currentDate;
	}

	/**
	 * Sets the current date.
	 *
	 * @param currentDate the new current date
	 */
	public void setCurrentDate(GregorianCalendar currentDate) {
		this.currentDate = currentDate;
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
		return userManager.deleteUser(lastname, firstname, login);
	}
	
	/**
	 * Gets the stock.
	 *
	 * @return the stock
	 */
	public Map<MaterialType, ArrayList<Material>> getStock(){
		return materialManager.getStock();
	}
	
	/**
	 * Save.
	 */
	public void save(){
		materialManager.save();
		userManager.save();
	}

	/**
	 * Load.
	 */
	public void load(){
		materialManager.load();
		userManager.load();
	}
	
	/**
	 * Gets the reservations.
	 *
	 * @return the reservations
	 */
	public List<Loan> getReservations() {
		return materialManager.getReservations();
	}

	/**
	 * Validate reservation.
	 *
	 * @param nbReservation the nb reservation
	 * @return true, if successful
	 */
	public boolean validateReservation(int nbReservation) {
		return materialManager.validateLoan(nbReservation);
	}

	/**
	 * Delete reservation.
	 *
	 * @param nbReservation the nb reservation
	 * @return true, if successful
	 */
	public boolean deleteReservation(int nbReservation) {
		Loan loan = materialManager.searchLoan(nbReservation);
		return materialManager.deleteLoan(loan);
	}

	/**
	 * Gets the available stock.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the available stock
	 */
	public Map<MaterialType, ArrayList<Material>> getAvailableStock(
			GregorianCalendar startDate, GregorianCalendar endDate) {
		return materialManager.getAvailableStock(startDate, endDate);
	}

	/**
	 * Adds the material.
	 *
	 * @param material the material
	 * @return true if successful
	 */
	public boolean addMaterial(Material material) {
		return materialManager.addMaterial(material);
	}

	public Map<MaterialType, ArrayList<Material>> getBorrowedStock(
			String startDate, String endDate) {
		try {
			return materialManager.getBorrowedMaterial(DateUtils.convertDate(startDate), DateUtils.convertDate(endDate));
		} catch (ParseException e) {
			System.out.println("Probleme de format de date (jj/mm/aaaa) !");
		}
		return null;
	}

	public Loans getLoans(User connectedUser) {
		return materialManager.getLoans(connectedUser);
	}

	

}
