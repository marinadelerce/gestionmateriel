package model.manager;

import java.util.GregorianCalendar;
import java.util.HashMap;

import model.material.MaterialType;
import model.user.Borrower;
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

	public boolean book(Borrower borrower, MaterialType material,
			GregorianCalendar startDate, GregorianCalendar endDate, int quantity) throws Exception {
		if (userManager.book(borrower, material, startDate, endDate, quantity)) {
			materialManager.book(material, borrower, quantity, startDate,
					endDate);
			return true;
		} else
			return false;
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
	
	public HashMap<MaterialType, Integer> getStock(){
		return materialManager.getStock();
	}
	
	public void save(){
		materialManager.save();
		userManager.save();
	}
}
