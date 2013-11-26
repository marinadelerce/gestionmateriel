package model.loan;

import model.general.Date;
import model.material.MaterialType;
import model.user.Borrower;


public class Loan {
	private Date startDate;
	private Date endDate;
	private Borrower borrower;
	private MaterialType material;
	private int quantity;
	private boolean effective;
	private boolean validate;
	//nfdcdsc

	public Loan(Borrower borrower, MaterialType material, int quantity, Date startDate, Date endDate, boolean effective, boolean validate){
		this.startDate = startDate;
		this.endDate = endDate;
		this.borrower = borrower;
		this.material = material;
		this.quantity = quantity;
		this.effective = effective;
		this.validate = validate;
	}


	public Date getStartDate() {
		return startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public Borrower getBorrower() {
		return borrower;
	}


	public MaterialType getMaterial() {
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


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}


	public void setMaterial(MaterialType material) {
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
