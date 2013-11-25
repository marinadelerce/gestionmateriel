package model.general;

import model.material.MaterialType;
import model.user.Borrower;
import model.user.Users;

public class UserManager {
	
	private Users users;
	private Date currentDate;
	private MaterialManager manager;
	
	public boolean book(Borrower borrower, MaterialType material, Date startDate, Date endDate, int quantity){
		
		boolean book = true;
		int duration;
		// verifier les dates
		if (startDate.isPosterior(endDate) || startDate.equals(endDate))
			book = false;
		// verifier les durees
		else {
			if (startDate.getMonth() == endDate.getMonth()) {
				duration = endDate.getDay() - startDate.getDay();
			} else
				duration = endDate.getDay() + (startDate.getDay() - 30);
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

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public MaterialManager getManager() {
		return manager;
	}

	public void setManager(MaterialManager manager) {
		this.manager = manager;
	}
}
