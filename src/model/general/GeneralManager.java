package model.general;

import java.util.GregorianCalendar;

import model.material.Material;
import model.user.Borrower;

public class GeneralManager {
	
	private MaterialManager materialManager;
	private UserManager userManager;
	private GregorianCalendar currentDate;

	public boolean book(Borrower borrower, Material material,
			GregorianCalendar startDate, GregorianCalendar endDate, int quantity) throws Exception {
		if (userManager.book(borrower, material, startDate, endDate, quantity)) {
			materialManager.book(material, borrower, quantity, startDate,
					endDate);
			return true;
		} else
			return false;
	}
	
	public GregorianCalendar getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(GregorianCalendar currentDate) {
		this.currentDate = currentDate;
	}
}
