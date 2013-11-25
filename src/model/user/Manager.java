package model.user;

import model.general.Date;
import model.loan.Loan;

public class Manager extends User {

	public Manager(String name, String firstname, int id) {
		super(name, firstname, id);
	}

	public int calculateDifference(Date startDate, Date endDate) {
		int result = 0;

		if (startDate.isPosterior(endDate))
			result = -1;
		else if (startDate.isPrevious(endDate)) {
			if (startDate.getMonth() == endDate.getMonth()) {
				result = endDate.getDay() - startDate.getDay();
			} else
				result = endDate.getDay() + (startDate.getDay() - 30);
		}

		// On compte des journées de 8 heures
		return result * 8;
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
