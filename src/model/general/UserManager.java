package model.general;

import java.util.GregorianCalendar;

import model.material.Material;
import model.user.Borrower;
import model.user.Users;

public class UserManager {
	
	private Users users;
	private MaterialManager manager;
	private final static int conversion = 86400000;
	
	public boolean book(Borrower borrower, Material material, GregorianCalendar startDate, GregorianCalendar endDate, int quantity){
		
		boolean book = true;
		int duration;
		// verifier les dates
		if (startDate.after(endDate) || startDate.equals(endDate))
			book = false;
		// verifier les durees
		else {
			duration = startDate.compareTo(endDate)/conversion;
			if(duration > borrower.getLoanDuration()) book = false;
		}
		
		return book;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public MaterialManager getManager() {
		return manager;
	}

	public void setManager(MaterialManager manager) {
		this.manager = manager;
	}
}
