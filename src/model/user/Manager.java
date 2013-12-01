package model.user;

import java.util.GregorianCalendar;

import model.loan.Loan;

public class Manager extends User {

	public Manager(String name, String firstname, String login, String password) {
		super(name, firstname, login, password);
	}

	
	@Override
	public String toString(){
		return "Admin: " + this.getFirstname() + " " + this.getName() + "id: " + this.getLogin();
	}

}
