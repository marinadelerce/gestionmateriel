package model.loan;

import java.util.GregorianCalendar;

import model.material.Material;
import model.material.MaterialType;
import model.user.Borrower;

public class Loan {
	
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private Borrower borrower;
	private Material material;
	private int quantity;
	private boolean effective;
	private boolean validate;

	public Loan(Borrower borrower, Material material, int quantity,
			GregorianCalendar startDate, GregorianCalendar endDate,
			boolean effective, boolean validate) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.borrower = borrower;
		this.material = material;
		this.quantity = quantity;
		this.effective = effective;
		this.validate = validate;
	}

	public GregorianCalendar getStartDate() {
		return startDate;
	}

	public GregorianCalendar getEndDate() {
		return endDate;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public Material getMaterial() {
		return material;
	}

	public int getQuantity() {
		return quantity;
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

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setEffective(boolean effective) {
		this.effective = effective;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

}
