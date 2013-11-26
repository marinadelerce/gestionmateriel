package model.general;

import model.material.MaterialType;
import model.user.Borrower;
import model.user.Users;

public class UserManager {
	
	private Users users;
	private OurDate currentDate;
	private MaterialManager manager;
	
	public boolean book(Borrower borrower, MaterialType material, OurDate startDate, OurDate endDate, int quantity){
		
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

	public OurDate getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(OurDate currentDate) {
		this.currentDate = currentDate;
	}

	public MaterialManager getManager() {
		return manager;
	}

	public void setManager(MaterialManager manager) {
		this.manager = manager;
	}
}
