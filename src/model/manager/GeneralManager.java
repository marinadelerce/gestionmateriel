package model.manager;

import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.loan.Loan;
import model.material.Material;
import model.material.MaterialType;
import model.user.Borrower;
import model.user.Manager;
import model.user.User;

public class GeneralManager {
	
	private MaterialManager materialManager;
	private UserManager userManager;
	private GregorianCalendar currentDate;

	public GeneralManager(){
		materialManager = new MaterialManager();
		userManager = new UserManager();
	}
	
	public boolean addNewUser(String userType, String lastname, String firstname, String login, String password){
		return userManager.addNewUser(userType, lastname, firstname, login, password);
	}
	
	public Loan book(int ref, GregorianCalendar startDate,
			GregorianCalendar endDate, int quantity) {
		if (userManager.canBook(userManager.getConnectedUser(), startDate, endDate)
				&& materialManager.maxTimeLoanNotReach(ref, startDate, endDate)) {
			return materialManager.book(ref, userManager.getConnectedUser(), startDate, endDate, quantity);
		}
		return null;
	}

	public boolean checkUserPassword(String login, String password) {
		return userManager.checkUserPassword(login,password);
	}

	public User getConnectedUser() {
		return userManager.getConnectedUser();
	}

	public void signOff() {
		userManager.signOff();
	}
	
	public GregorianCalendar getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(GregorianCalendar currentDate) {
		this.currentDate = currentDate;
	}

	public boolean deleteUser(String lastname, String firstname, String login) {
		return userManager.deleteUser(lastname, firstname, login);
	}
	
	public Map<MaterialType, ArrayList<Material>> getStock(){
		return materialManager.getStock();
	}
	
	public void save(){
		materialManager.save();
		userManager.save();
	}

	public List<Loan> getReservations() {
		return materialManager.getReservations();
	}

	public boolean validateReservation(int nbReservation) {
		return materialManager.validateLoan(nbReservation);
	}

	public boolean deleteReservation(int nbReservation) {
		Loan loan = materialManager.searchLoan(nbReservation);
		return materialManager.deleteLoan(loan);
	}

	public Map<MaterialType, ArrayList<Material>> getAvailableStock(
			GregorianCalendar startDate, GregorianCalendar endDate) {
		return materialManager.getAvailableStock(startDate, endDate);
	}

	public boolean addMaterial(Material material) {
		return materialManager.addMaterial(material);
	}

	

}
