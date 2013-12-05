/*
 * 
 */
package model.manager;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.loan.Loans;
import model.loan.Loan;
//import model.loan.Reservations;
import model.material.Material;
import model.material.MaterialType;
import model.material.Stock;
import model.user.Borrower;
import model.user.Manager;
import model.user.User;

/**
 * The Class MaterialManager.
 * @author Marina Delerce & Romain Guillot 
 * @version 1.0.0
 */
public class MaterialManager {

	/** The stock. */
	private Stock stock;
	
	/** The loans. */
	private Loans loans;
	
	/** The id loan. */
	private static int idLoan = 0;
	
	/** The Constant conversion. */
	private final static int conversion = 86400000;

	/**
	 * Instantiates a new material manager.
	 */
	public MaterialManager() {
		stock = new Stock();
		loans = new Loans();
	}


	/*public Stock predictStock(GregorianCalendar date) throws Exception {

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
		  
		  //g�n�ration de l'id du futur emprunt;
		  idLoan ++;
		  
		  // on verifie que la quantite de materiel est disponible pendant toute la duree de l'emprunt 
		  GregorianCalendar virtual_date =  (GregorianCalendar) startDate.clone();
		  
		  do{
		  
			  predicted_stock = predictStock(virtual_date);
			  
			  // si il n'y a pas assez de ce type de materiel a la date t on refuse l'emprunt 
			  if (predicted_stock.getStock(materialT) < quantity) 
				  return false;
			  
			  virtual_date.add(GregorianCalendar.DAY_OF_MONTH, 1);
		  
		  } while (virtual_date.before(endDate) || virtual_date.equals(endDate));
		 
		  // on r�cup�re un materiel du stock
		  Material material = stock.getObject(materialT.getReference());
		  
		  // on cr�e l'emprunt 
		  Loan loan = new Loan(borrower, material, quantity, startDate, endDate, false, false); 
		  reservations.add(loan);
		 
		return true;
	}*/
	
	/**
	 * Gets the stock.
	 *
	 * @return the stock
	 */
	public Map<MaterialType, ArrayList<Material>> getStock(){
		return stock.getStock();
	}
	
	/**
	 * Save.
	 */
	public void save(){
		loans.save();
		stock.save();
	}
	
	/**
	 * Load.
	 */
	public void load(){
		loans.load();
		stock.load();
	}


	/**
	 * Calculate difference.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the int
	 */
	private int calculateDifference(GregorianCalendar startDate, GregorianCalendar endDate) {
		int result = 0;

		if (startDate.after(endDate))
			result = -1;
		else result = startDate.compareTo(endDate)/conversion;

		return result;
	}

	/**
	 * Validate loan.
	 *
	 * @param loanId the loan id
	 * @return true, if successful
	 */
	public boolean validateLoan(int loanId) {
		Loan loan = this.searchLoan(loanId);
		if(loan == null) {
			return false;
		} else {
			loan.setValidate(true);

			return true;
		}
	}
	
	/**
	 * Gets the reservations.
	 *
	 * @return the reservations
	 */
	public List<Loan> getReservations() {
		List<Loan> reservations = new ArrayList<Loan>();
		for(Loan loan: loans.getLoans()) {
			if(!loan.isValidate() || !loan.isEffective()) {
				reservations.add(loan);
			}
		}
		return reservations;
	}

	/**
	 * Search loan.
	 *
	 * @param nbReservation the nb reservation
	 * @return the loan
	 */
	public Loan searchLoan(int nbReservation) {
		Loan loanToValidate = null;
		for(Loan loan : getReservations()){
			if(loan.getId() == nbReservation)
				loanToValidate = loan;
		}
		return loanToValidate;
	}

	/**
	 * Delete loan.
	 *
	 * @param loan the loan
	 * @return true, if successful
	 */
	public boolean deleteLoan(Loan loan) {
		if(loans.getLoans().contains(loan)) {
			loans.remove(loan);
			return true;
		}
		return false;
	}

	/**
	 * Gets the borrowed material.
	 *
	 * @return the borrowed material
	 */
	public Map<MaterialType, ArrayList<Material>> getBorrowedMaterial()
	{
		Map<MaterialType, ArrayList<Material>> loansByMaterialType = new HashMap<MaterialType, ArrayList<Material>>();
		
		//recuperation du materiel emprunt�
		for(Loan loan: loans.getLoans()) {
			if(!loansByMaterialType.containsKey(loan.getMaterials().get(0).getMaterialType())) {
				loansByMaterialType.put(loan.getMaterials().get(0).getMaterialType(), new ArrayList<Material>(loan.getMaterials()));
			} else {
				for(Material material: loan.getMaterials()) {
					loansByMaterialType.get(loan.getMaterials().get(0).getMaterialType()).add(material);
				}
			}
		}
		
		return loansByMaterialType;
	}
	
	/**
	 * Gets the available stock.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the available stock
	 */
	public Map<MaterialType, ArrayList<Material>> getAvailableStock(
			GregorianCalendar startDate, GregorianCalendar endDate) {
		
		Map<MaterialType, ArrayList<Material>> loansByMaterialType = new HashMap<MaterialType, ArrayList<Material>>();
		
		loansByMaterialType = getBorrowedMaterial();
		Map<MaterialType, ArrayList<Material>> availableStock = new HashMap<MaterialType, ArrayList<Material>>();
		
		for(MaterialType materialType: stock.getStock().keySet()){
			if(loansByMaterialType.get(materialType) == null) {
				ArrayList<Material> availableMaterials = stock.getStock(materialType);
				availableStock.put(materialType, availableMaterials);
			} else {
				if(stock.getStock(materialType).size() > loansByMaterialType.get(materialType).size()) {
					ArrayList<Material> availableMaterials = new ArrayList<Material>();
					for(Material material: stock.getStock(materialType)) {
						if(!loansByMaterialType.get(materialType).contains(material)) {
							availableMaterials.add(material);
						}
					}
					
					availableStock.put(materialType, availableMaterials);
				}
			}
			
		}
		return availableStock;
	}

	/**
	 * Max time loan not reach.
	 *
	 * @param ref the ref
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return true, if successful
	 */
	public boolean maxTimeLoanNotReach(int ref, GregorianCalendar startDate,
			GregorianCalendar endDate) {
		MaterialType materialType = stock.getMaterialType(ref);
		if(materialType != null) {
			if(calculateDifference(startDate, endDate) < materialType.getMaxTimeLoan()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Book.
	 *
	 * @param ref the ref
	 * @param connectedUser the connected user
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param quantity the quantity
	 * @return true, if successful
	 */
	public boolean book(int ref, User connectedUser,
			GregorianCalendar startDate, GregorianCalendar endDate, int quantity) {
		Map<MaterialType, ArrayList<Material>> availableStock = this.getAvailableStock(startDate, endDate);
		MaterialType materialType = stock.getMaterialType(ref);
		
		if(availableStock.get(materialType).size() >= quantity) {
			List<Material> materials = new ArrayList<Material>();
			for(int i = 0; i < quantity; i++) {
				materials.add(availableStock.get(materialType).get(i));
			}
			Loan book = null;
			if(connectedUser instanceof Manager)
				book = new Loan((Manager)connectedUser, materials, startDate, endDate);
			if(connectedUser instanceof Borrower)
				book = new Loan((Borrower)connectedUser, materials, startDate, endDate);
			loans.add(book);
			return true;
		}
		
		return false;
	}

	/**
	 * Adds the material.
	 *
	 * @param material the material
	 */
	public void addMaterial(Material material) {
		stock.add(material);
		
	}

}
