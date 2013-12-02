package model.loan;

import java.util.GregorianCalendar;
import java.util.List;

import model.material.Material;
import model.material.MaterialType;
import model.user.Borrower;
import model.user.Manager;
import model.user.User;

public class Loan {
	
	public static int LOAN_ID = 0;
	private int id;
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private User user;
	private List<Material> materials;
	private boolean effective;
	private boolean validate;

	private Loan(User user, List<Material> materials,
			GregorianCalendar startDate, GregorianCalendar endDate,
			boolean effective, boolean validate) {
		this.id = LOAN_ID;
		LOAN_ID = LOAN_ID + 1;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
		this.materials = materials;
		this.effective = effective;
		this.validate = validate;
	}
	
	public Loan(Borrower borrower, List<Material> materials,
			GregorianCalendar startDate, GregorianCalendar endDate) {
		this(borrower, materials, startDate, endDate, false, false);
	}
	
	public Loan(Manager manager, List<Material> materials,
			GregorianCalendar startDate, GregorianCalendar endDate) {
		this(manager, materials, startDate, endDate, false, true);
	}

	public int getId() {
		return id;
	}

	public GregorianCalendar getStartDate() {
		return startDate;
	}

	public GregorianCalendar getEndDate() {
		return endDate;
	}

	public User getUser() {
		return user;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public boolean isEffective() {
		return effective;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(GregorianCalendar endDate) {
		this.endDate = endDate;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	public void setEffective(boolean effective) {
		this.effective = effective;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

}
