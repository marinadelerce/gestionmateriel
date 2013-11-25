package model.general;

import java.util.LinkedList;

import model.loan.Loans;
import model.loan.Loan;
import model.loan.Reservations;
import model.material.MaterialType;
import model.material.Stock;
import model.user.Borrower;

public class MaterialManager {
	
	private Stock stock;
	private Loans loans;
	private Reservations reservations;
	
	public MaterialManager(){
		stock = new Stock();
		loans = new Loans();
		reservations = new Reservations();
	}
	
	public int calculateMaxDurationLoan(MaterialType material, Date date){
		return 0;
	}
	
	public Stock predictStock(Date date) throws Exception{
		
		// stock a l'instant
		Stock stock_clone = (Stock) stock.clone();
		
		// on ajoute les emprunts retournes a la date
		LinkedList<Loan> finished_loans = (LinkedList<Loan>) loans.getFinishedLoans(date);
		for (Loan emprunt : finished_loans){
			MaterialType material = emprunt.getMaterial();
			Integer quantity = emprunt.getQuantity();
			for (int i=0; i<quantity; i++) stock_clone.add(material);
		}
		
		// on retire les emprunts encore actifs a la date
		LinkedList<Loan> active_loans = (LinkedList<Loan>) loans.getActiveLoans(date);
		for (Loan emprunt : active_loans){
			MaterialType material = emprunt.getMaterial();
			Integer quantity = emprunt.getQuantity();
			for (int i=0; i<quantity; i++) stock_clone.remove(material);
		}
		
		// on retire les reservations a la date
		LinkedList<Loan> reserves = (LinkedList<Loan>) reservations.getActiveReservations(date);
		for (Loan emprunt : reserves){
			MaterialType material = emprunt.getMaterial();
			Integer quantity = emprunt.getQuantity();
			for (int i=0; i<quantity; i++) stock_clone.remove(material);
		}
		
		return stock_clone;
	}
	
	public boolean reserver(MaterialType material, Borrower borrower, int quantity, Date startDate, Date endDate) throws Exception{
		
		// stock prevu a la date t
		Stock stock_anticipe;
		
		// on verifie que la quantite de materiel est disponible pendant toute la duree de l'emprunt
		Date date_virtuelle = (Date) startDate.clone();
		
		do{
			
			stock_anticipe = predictStock(date_virtuelle);
			
			// si il n'y a pas assez de ce type de materiel a la date t  on refuse l'emprunt
			if (stock_anticipe.getStock(material) < quantity) return false;
			
			date_virtuelle.nextHour();
		} while (date_virtuelle.isPrevious(endDate) || date_virtuelle.equals(endDate));
		
		// on crée l'emprunt
		Loan loan = new Loan(borrower, material, quantity, startDate, endDate, false, false);
		reservations.add(loan);
		
		return true;
	}
}
