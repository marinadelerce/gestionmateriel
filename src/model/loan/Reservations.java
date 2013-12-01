package model.loan;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


public class Reservations {
	
	private List<Loan> reservations;
	private final String SAVE_FILE = "";
	
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
	
	public List<Loan> getActiveReservations(GregorianCalendar date){
		
		List<Loan> active_reservations = new LinkedList<Loan>();
		
		for(Loan loan : reservations) {
			
			if (((loan.getStartDate().before(date)||(loan.getStartDate().equals(date))) && (loan.getEndDate().after(date) || loan.getEndDate().equals(date)))) 
					active_reservations.add(loan);
		}
		
		return active_reservations;
		
	}
	
	public void save(){
		// save reservations in the save file
	}

	public List<Loan> getReservations() {
		
		return reservations;
	}
}
