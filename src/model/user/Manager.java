package model.user;

import java.util.GregorianCalendar;

import model.loan.Loan;

public class Manager extends User {
	
	private final static int conversion = 86400000;

	public Manager(String name, String firstname, int id) {
		super(name, firstname, id);
	}

	private int calculateDifference(GregorianCalendar startDate, GregorianCalendar endDate) {
		int result = 0;

		if (startDate.after(endDate))
			result = -1;
		else result = startDate.compareTo(endDate)/conversion;

		return result;
	}

	public Loan validateLoan(Loan loan) {
		
		// lors de la cr�ation de l'emprunt on verifie l'�tat du stock, il n'y a
		// donc pas besoin de le verifier a nouveau
		
		int loanHours = calculateDifference(loan.getStartDate(),
				loan.getEndDate());
		if (loanHours <= loan.getMaterial().getMaxTimeLoan()) {
			if (loanHours <= loan.getBorrower().getLoanDuration()) {
				loan.setValidate(true);
			}
		}

		return loan;
	}
	
	@Override
	public String toString(){
		return "Admin: " + this.getFirstname() + " " + this.getName() + "id: " + this.getId();
	}

}
