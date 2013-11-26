package model.general;

import model.material.MaterialType;
import model.user.Borrower;

public class GeneralManager {
	MaterialManager materialManager;
	UserManager userManager;

	public boolean book(Borrower borrower, MaterialType material,
			OurDate startDate, OurDate endDate, int quantity) throws Exception {
		if (userManager.book(borrower, material, startDate, endDate, quantity)) {
			materialManager.book(material, borrower, quantity, startDate,
					endDate);
			return true;
		} else
			return false;
	}
}
