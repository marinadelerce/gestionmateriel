package model.user;

import java.util.GregorianCalendar;

import model.general.OurDate;
import model.loan.Loan;

public class Manager extends User {

	public Manager(String name, String firstname, int id) {
		super(name, firstname, id);
	}

	private int calculateDifference(GregorianCalendar startDate, OurDate endDate) {
		int result = 0;

		if (startDate.after(endDate))
			result = -1;
		else if (startDate.before(endDate)) {
			if (startDate.MONTH == endDate.MONTH) {
				result = endDate.DAY_OF_MONTH - startDate.DAY_OF_MONTH;
			} else
				result = endDate.DAY_OF_MONTH + (startDate.DAY_OF_MONTH - startDate.g30);
		}

		return startDate.compareTo(endDate)*;
	}

	public Loan validateLoan(Loan loan) {
		
		// lors de la création de l'emprunt on verifie l'état du stock, il n'y a
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

}
