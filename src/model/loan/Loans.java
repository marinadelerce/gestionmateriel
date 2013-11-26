package model.loan;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


public class Loans {
	
	private List<Loan> loans;
	
	public Loans(){
		loans = new LinkedList<Loan>();
	}
	
	public void add(Loan loan){
		
		loan.setEffective(true);
		loans.add(loan);
	}
	
	public void remove(Loan loan){
		loans.remove(loan);
	}
	
	public List<Loan> getActiveLoans(GregorianCalendar date){
		
		List<Loan> active_loans = new LinkedList<Loan>();
		
		for(Loan loan : loans) {
			
			if (loan.getEndDate().after(date) || loan.getEndDate().equals(date)) active_loans.add(loan);
		}
		
		return active_loans;
		
	}
	
	public List<Loan> getFinishedLoans(GregorianCalendar date){
		
		List<Loan> finished_loans = new LinkedList<Loan>();
		
		for(Loan loan : loans) {
			
			if (loan.getEndDate().before(date)) finished_loans.add(loan);
		}
		
		return finished_loans;
	}
}
