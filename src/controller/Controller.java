package controller;

import java.util.HashMap;

import model.manager.GeneralManager;
import model.material.MaterialType;
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
	
	public void start(){
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
}
