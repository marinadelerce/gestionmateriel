package model.manager;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
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

public class MaterialManager {

	private Stock stock;
	private Loans loans;
	//private Reservations reservations;
	private static int idLoan = 0;
	private final static int conversion = 86400000;

	public MaterialManager() {
		stock = new Stock();
		loans = new Loans();
		//reservations = new Reservations();
	}

	/*public int calculateMaxDurationLoan(MaterialType material,
			GregorianCalendar date) {
		return 0;
	}*/

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
		  
		  //génération de l'id du futur emprunt;
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
		 
		  // on récupère un materiel du stock
		  Material material = stock.getObject(materialT.getReference());
		  
		  // on crée l'emprunt 
		  Loan loan = new Loan(borrower, material, quantity, startDate, endDate, false, false); 
		  reservations.add(loan);
		 
		return true;
	}*/
	
	public Map<MaterialType, ArrayList<Material>> getStock(){
		return stock.getStock();
	}
	
	public void save(){
		loans.save();
		//reservations.save();
	}


	private int calculateDifference(GregorianCalendar startDate, GregorianCalendar endDate) {
		int result = 0;

		if (startDate.after(endDate))
			result = -1;
		else result = startDate.compareTo(endDate)/conversion;

		return result;
	}

	public boolean validateLoan(int loanId) {
		Loan loan = this.searchLoan(loanId);
		if(loan == null) {
			return false;
		} else {
			loan.setValidate(true);

			return true;
		}
	}
	
	public List<Loan> getReservations() {
		List<Loan> reservations = new ArrayList<Loan>();
		for(Loan loan: loans.getLoans()) {
			if(!loan.isValidate() || !loan.isEffective()) {
				reservations.add(loan);
			}
		}
		return reservations;
	}

	public Loan searchLoan(int nbReservation) {
		Loan loanToValidate = null;
		for(Loan loan : getReservations()){
			if(loan.getId() == nbReservation)
				loanToValidate = loan;
		}
		return loanToValidate;
	}

	public boolean deleteLoan(Loan loan) {
		if(loans.getLoans().contains(loan)) {
			loans.remove(loan);
			return true;
		}
		return false;
	}

	public Map<MaterialType, ArrayList<Material>> getAvailableStock(
			GregorianCalendar startDate, GregorianCalendar endDate) {
		Map<MaterialType, ArrayList<Material>> loansByMaterialType = new HashMap<MaterialType, ArrayList<Material>>();
		
		for(Loan loan: loans.getLoans()) {
			if(!loansByMaterialType.containsKey(loan.getMaterials().get(0).getMaterialType())) {
				loansByMaterialType.put(loan.getMaterials().get(0).getMaterialType(), new ArrayList<Material>(loan.getMaterials()));
			} else {
				for(Material material: loan.getMaterials()) {
					loansByMaterialType.get(loan.getMaterials().get(0).getMaterialType()).add(material);
				}
			}
		}
		
		Map<MaterialType, ArrayList<Material>> availableStock = new HashMap<MaterialType, ArrayList<Material>>();
		
		for(MaterialType materialType: stock.getStock().keySet()){
			if(stock.getStock(materialType) != null && loansByMaterialType.get(materialType) != null){
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
		}
		
		return false;
	}

	public void addMaterial(Material material) {
		stock.add(material);
		
	}

}
