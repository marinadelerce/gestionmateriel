package model.loan;

import java.util.LinkedList;
import java.util.List;

import model.general.OurDate;

public class Reservations {
	
	private List<Loan> reservations;
	
	public Reservations(){
		reservations = new LinkedList<Loan>();
	}
	
	public void add(Loan loan){
		
		loan.setEffective(false);
		reservations.add(loan);
	}
	
	public void remove(Loan loan){
		reservations.remove(loan);
	}
	
	public List<Loan> getActiveReservations(OurDate date){
		
		List<Loan> active_reservations = new LinkedList<Loan>();
		
		for(Loan loan : reservations) {
			
			if (((loan.getStartDate().isPrevious(date)||(loan.getStartDate().equals(date))) && (loan.getEndDate().isPosterior(date) || loan.getEndDate().equals(date)))) 
					active_reservations.add(loan);
		}
		
		return active_reservations;
		
	}
}
