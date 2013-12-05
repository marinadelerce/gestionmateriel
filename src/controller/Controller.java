/*
 * 
 */
package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import model.loan.Loan;
import model.manager.GeneralManager;
import model.material.Material;
import model.material.MaterialType;
import model.user.User;
import utils.DateUtils;
import view.ConsoleView;

/**
 * The Class Controller.
 * @author Marina Delerce & Romain Guillot 
 * @version 1.0.0
 */
public class Controller {

	/** The view. */
	private ConsoleView view;
	
	/** The model. */
	private GeneralManager model;
	
	/**
	 * Instantiates a new controller.
	 */
	public Controller(){
		model = new GeneralManager();
		view = new ConsoleView(this);
		model.load();
	}
	
	/**
	 * Start.
	 *
	 * @throws Exception the exception
	 */
	public void start() throws Exception{
		view.displayStart();
		view.begin();
	}
	
	/**
	 * Gets the connected user.
	 *
	 * @return the connected user
	 */
	public User getConnectedUser(){
		return model.getConnectedUser();
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
		return model.deleteUser(lastname, firstname, login);
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
		return model.addNewUser(userType, lastname, firstname, login, password);
	}
	
	/**
	 * Sign off.
	 */
	public void signOff() {
		model.signOff();
	}
	
	/**
	 * Check user password.
	 *
	 * @param login the login
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean checkUserPassword(String login, String password) {
		return model.checkUserPassword(login, password);
	}
	
	/**
	 * Gets the stock.
	 *
	 * @return the stock
	 */
	public Map<MaterialType, ArrayList<Material>> getStock(){
		return model.getStock();
	}
	
	/**
	 * Save.
	 */
	public void save(){
		model.save();
	}
	
	/**
	 * Exit.
	 */
	public void exit(){
		save();
		view.displayExit();
		System.exit(0);
	}

	/**
	 * Gets the reservations.
	 *
	 * @return the reservations
	 */
	public List<Loan> getReservations() {
		return  model.getReservations();
	}

	/**
	 * Validate reservation.
	 *
	 * @param nbReservation the nb reservation
	 * @return true, if successful
	 */
	public boolean validateReservation(int nbReservation) {
		return model.validateReservation(nbReservation);
	}

	/**
	 * Delete reservation.
	 *
	 * @param nbReservation the nb reservation
	 * @return true, if successful
	 */
	public boolean deleteReservation(int nbReservation) {
		return model.deleteReservation(nbReservation);
	}
	
	/**
	 * Book.
	 *
	 * @param ref the reference
	 * @param amount the amount
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean book(int ref, int amount, String startDate, String endDate) throws Exception {

		GregorianCalendar newStartDate = DateUtils.convertDate(startDate);
		GregorianCalendar newEndDate = DateUtils.convertDate(endDate);
		if (model.book(ref, newStartDate, newEndDate, amount) != null)
				return true;
		else
			return false;
		
		
	}
	
	/**
	 * Gets the available stock.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the available stock
	 * @throws ParseException the parse exception
	 */
	public Map<MaterialType, ArrayList<Material>> getAvailableStock(
			String startDate, String endDate) throws ParseException {
		return model.getAvailableStock(DateUtils.convertDate(startDate), DateUtils.convertDate(endDate));
	}

}
