package model.loan;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


public class Loans {
	
	private List<Loan> loans;
	private final String SAVE_FILE = "";
	
	public Loans(){
		loans = new LinkedList<Loan>();
	}
	
	public void add(Loan loan){
		
		loan.setEffective(false);
		loans.add(loan);
	}
	
	public void remove(Loan loan){
		loans.remove(loan);
	}
	
	public List<Loan> getActiveReservations(GregorianCalendar date){
		
		List<Loan> active_reservations = new LinkedList<Loan>();
		
		for(Loan loan : loans) {
			
			if (((loan.getStartDate().before(date)||(loan.getStartDate().equals(date))) && (loan.getEndDate().after(date) || loan.getEndDate().equals(date)))) 
					active_reservations.add(loan);
		}
		
		return active_reservations;
		
	}
	
	public List<Loan> getFinishedLoans(GregorianCalendar date){
		
		List<Loan> finished_loans = new LinkedList<Loan>();
		
		for(Loan loan : loans) {
			
			if (loan.getEndDate().before(date)) finished_loans.add(loan);
		}
		
		return finished_loans;
	}
	
	public void save(){
		// save reservations in the save file
	}

	public List<Loan> getLoans() {
		
		return loans;
	}
}
