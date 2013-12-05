/*
 * 
 */
package model.loan;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import xml.ConfigXML;


/**
 * The Class Loans.
 * @author Marina Delerce & Romain Guillot 
 * @version 1.0.0
 */
public class Loans {
	
	/** The loans. */
	private List<Loan> loans;
	
	/** The save file path. */
	private final String SAVE_FILE_PATH = "loans";
	
	/** The save file version. */
	private final String SAVE_FILE_VERSION = "1.0.0";
	
	/**
	 * Instantiates a new loans.
	 */
	public Loans(){
		loans = new LinkedList<Loan>();
	}
	
	/**
	 * Adds a loan
	 *
	 * @param loan the loan
	 */
	public void add(Loan loan){
		
		loan.setEffective(false);
		loans.add(loan);
	}
	
	/**
	 * Removes a loan
	 *
	 * @param loan the loan
	 */
	public void remove(Loan loan){
		loans.remove(loan);
	}
	
	/**
	 * Gets the active reservations.
	 *
	 * @param date the date
	 * @return the active reservations
	 */
	public List<Loan> getActiveReservations(GregorianCalendar date){
		
		List<Loan> active_reservations = new LinkedList<Loan>();
		
		for(Loan loan : loans) {
			
			if (((loan.getStartDate().before(date)||(loan.getStartDate().equals(date))) && (loan.getEndDate().after(date) || loan.getEndDate().equals(date)))) 
					active_reservations.add(loan);
		}
		
		return active_reservations;
		
	}
	
	/**
	 * Gets the finished loans.
	 *
	 * @param date the date
	 * @return the finished loans
	 */
	public List<Loan> getFinishedLoans(GregorianCalendar date){
		
		List<Loan> finished_loans = new LinkedList<Loan>();
		
		for(Loan loan : loans) {
			
			if (loan.getEndDate().before(date)) finished_loans.add(loan);
		}
		
		return finished_loans;
	}
	
	/**
	 * Save.
	 */
	public void save(){
		
		// save reservations in the save file
		ConfigXML.store(getSerializableDescription(), SAVE_FILE_PATH, SAVE_FILE_VERSION);
		
	}
	
	/**
	 * Load.
	 */
	public void load(){
		List<HashMap<String, Object>> listLoans = (List<HashMap<String, Object>>) ConfigXML.load(SAVE_FILE_PATH, SAVE_FILE_VERSION);
		for (HashMap<String, Object> descriptionLoan : listLoans){
			loans.add(new Loan(descriptionLoan));
		}
		System.out.println("Loans loaded");
	}

	/**
	 * Gets the serializable description.
	 *
	 * @return the serializable description
	 */
	public List<HashMap<String, Object>> getSerializableDescription(){
		
		List<HashMap<String, Object>> loansDescription = new LinkedList<HashMap<String, Object>>();
		
		for (Loan loan : loans){
			loansDescription.add(loan.getSerializableDescription());
		}
		
		return loansDescription;
	}
	
	/**
	 * Gets the loans.
	 *
	 * @return the loans
	 */
	public List<Loan> getLoans() {
		
		return loans;
	}
}
