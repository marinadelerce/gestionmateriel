package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import model.loan.Loan;
import model.manager.GeneralManager;
import model.material.MaterialType;
import model.user.Borrower;
import model.user.Manager;
import model.user.User;
import view.ConsoleView;

public class Controller {

	private ConsoleView view;
	private GeneralManager model;
	
	public Controller(){
		model = new GeneralManager();
		view = new ConsoleView(this);
		populate();
	}
	
	public void start() throws Exception{
		view.displayStart();
		view.begin();
	}
	
	// DEBUG
	private void populate() {
		model.addNewUser("Manager", "Sander", "Peter", "sander","ps");
		model.addNewUser("Student", "Delerce", "Marina", "mutti", "md");
	}
	
	public User getConnectedUser(){
		return model.getConnectedUser();
	}
	
	public boolean deleteUser(String lastname, String firstname, String login) {
		return model.deleteUser(lastname, firstname, login);
	}
	
	public boolean addNewUser(String userType, String lastname, String firstname, String login, String password){
		return model.addNewUser(userType, lastname, firstname, login, password);
	}
	
	public void signOff() {
		model.signOff();
	}
	
	public boolean checkUserPassword(String login, String password) {
		return model.checkUserPassword(login, password);
	}
	
	public HashMap<MaterialType, Integer> getStock(){
		return model.getStock();
	}
	
	public void save(){
		model.save();
	}
	
	public void exit(){
		save();
		view.displayExit();
		System.exit(0);
	}

	public List<Loan> getReservations() {
		return  model.getReservations();
	}

	public boolean validateReservation(int nbReservation) {
		return model.validateReservation(nbReservation);
	}

	public boolean deleteReservation(int nbReservation) {
		return model.deleteReservation(nbReservation);
	}

	public GregorianCalendar convertDate(String date) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date oldDate = format.parse(date);
		GregorianCalendar newDate = new GregorianCalendar();
		newDate.setTime(oldDate);
		
		return newDate;
	}
	
	public boolean addReservation(int ref, int amount, String startDate, String endDate) throws Exception {
		MaterialType materialType = null;
		for(Entry<MaterialType, Integer> entry : getStock().entrySet()) {
			
		    MaterialType material = entry.getKey();
		    
		    if (material.getReference() == ref){
		    	materialType = material;
		    }
		}
		GregorianCalendar newStartDate = convertDate(startDate);
		GregorianCalendar newEndDate = convertDate(endDate);
		
		if(materialType != null){
			if(getConnectedUser() instanceof Manager)
				return model.book((Manager)getConnectedUser(), materialType, newStartDate, newEndDate, amount);
			else
				return model.book((Borrower)getConnectedUser(), materialType, newStartDate, newEndDate, amount);
		}
		else 
			return false;
	}

}
