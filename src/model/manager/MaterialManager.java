package model.manager;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;

import model.loan.Loans;
import model.loan.Loan;
import model.loan.Reservations;
import model.material.Material;
import model.material.MaterialType;
import model.material.Stock;
import model.user.Borrower;

public class MaterialManager {

	private Stock stock;
	private Loans loans;
	private Reservations reservations;

	public MaterialManager() {
		stock = new Stock();
		loans = new Loans();
		reservations = new Reservations();
	}

	public int calculateMaxDurationLoan(MaterialType material,
			GregorianCalendar date) {
		return 0;
	}

	public Stock predictStock(GregorianCalendar date) throws Exception {

		// stock a l'instant
		Stock stock_clone = (Stock) stock.clone();

		// on ajoute les emprunts retournes a la date
		LinkedList<Loan> finished_loans = (LinkedList<Loan>) loans
				.getFinishedLoans(date);
		for (Loan emprunt : finished_loans) {
			MaterialType material = emprunt.getMaterial().getMaterialType();
			Integer quantity = emprunt.getQuantity();
			for (int i = 0; i < quantity; i++)
				stock_clone.add(material);
		}

		// on retire les emprunts encore actifs a la date
		LinkedList<Loan> active_loans = (LinkedList<Loan>) loans
				.getActiveLoans(date);
		for (Loan emprunt : active_loans) {
			MaterialType material = emprunt.getMaterial().getMaterialType();
			Integer quantity = emprunt.getQuantity();
			for (int i = 0; i < quantity; i++)
				stock_clone.remove(material);
		}

		// on retire les reservations a la date
		LinkedList<Loan> reserves = (LinkedList<Loan>) reservations
				.getActiveReservations(date);
		for (Loan emprunt : reserves) {
			MaterialType material = emprunt.getMaterial().getMaterialType();
			Integer quantity = emprunt.getQuantity();
			for (int i = 0; i < quantity; i++)
				stock_clone.remove(material);
		}

		return stock_clone;
	}

	public boolean book(MaterialType materialT, Borrower borrower, int quantity, GregorianCalendar startDate, GregorianCalendar endDate) throws Exception {

		
		  // stock prevu a la date t 
		  Stock predicted_stock;
		  
		  // on verifie que la quantite de materiel est disponible pendant toute la duree de l'emprunt 
		  GregorianCalendar virtual_date =  (GregorianCalendar) startDate.clone();
		  
		  do{
		  
			  predicted_stock = predictStock(virtual_date);
			  
			  // si il n'y a pas assez de ce type de materiel a la date t on refuse l'emprunt 
			  if (predicted_stock.getStock(materialT) < quantity) 
				  return false;
			  
			  virtual_date.add(GregorianCalendar.DAY_OF_MONTH, 1);
		  
		  } while (virtual_date.before(endDate) || virtual_date.equals(endDate));
		 
		  // on récupère un materiel du stock
		  Material material = stock.getObject(materialT.getReference());
		  
		  // on crée l'emprunt 
		  Loan loan = new Loan(borrower, material, quantity, startDate, endDate, false, false); 
		  reservations.add(loan);
		 
		return true;
	}
	
	public HashMap<MaterialType, Integer> getStock(){
		return stock.getStock();
	}
	
	public void save(){
		loans.save();
		reservations.save();
	}
}
