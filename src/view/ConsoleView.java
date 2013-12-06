/*
 * 
 */
package view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import utils.DateUtils;
import model.loan.Loan;
import model.loan.Loans;
import model.material.Material;
import model.material.MaterialType;
import model.user.Borrower;
import model.user.Manager;
import model.user.User;
import controller.Controller;

/**
 * The Class ConsoleView.
 * 
 * @author Marina Delerce & Romain Guillot
 * @version 1.0.0
 */
public class ConsoleView {

	/** The controller. */
	private Controller controller;

	/** The read scanner */
	private Scanner read;

	/**
	 * Instantiates a new console view.
	 * 
	 * @param control
	 *            the controller
	 */
	public ConsoleView(Controller control) {
		controller = control;
		read = new Scanner(System.in);
	}

	/**
	 * Begin.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void begin() throws Exception {
		int result;

		System.out.println("Welcome!");
		System.out.println("1: Se connecter \n 2: Quitter");
		result = read.nextInt();
		read.nextLine();

		switch (result) {
		case 1:
			boolean isConnected = connect();
			if (isConnected)
				displayMenu();
			else
				begin();
			break;

		case 2:
			controller.exit();

		default:
			controller.exit();
		}
	}

	/**
	 * Display menu.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void displayMenu() throws Exception {
		System.out.println("Menu");
		User connectedUser = controller.getConnectedUser();
		if (connectedUser == null) {
			begin();
		} else {
			if (connectedUser instanceof Manager) {
				displayManagerMenu();
			}
			if (connectedUser instanceof Borrower) {
				displayBorrowerMenu();
			}
		}
	}

	/*
	 * public void displayStock(){
	 * 
	 * HashMap<MaterialType, Integer> stock_description= controller.getStock();
	 * 
	 * for(Entry<MaterialType, Integer> entry : stock_description.entrySet()) {
	 * 
	 * MaterialType materialtype = entry.getKey(); Integer nb =
	 * stock_description.get(materialtype);
	 * 
	 * displayMaterialType(materialtype); System.out.println(" -> " + nb); } }
	 */

	/**
	 * Display material type.
	 * 
	 * @param materialT
	 *            the material t
	 */
	public void displayMaterialType(MaterialType materialT) {
		System.out.print(materialT.toString());
	}

	/**
	 * Display users.
	 * 
	 * @param users
	 *            the users
	 */
	public void displayUsers(ArrayList<User> users) {
		for (User user : users) {
			System.out.println(user.toString());
		}
	}

	/**
	 * Display borrower menu.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private void displayBorrowerMenu() throws Exception {
		int choice;
		System.out.println("1: Emprunter");
		System.out.println("2: Afficher mes emprunts");
		System.out.println("3: Se déconnecter ");
		choice = read.nextInt();
		read.nextLine();
		switch (choice) {
		case 1:
			borrowMenu();
			break;

		case 2:
			displayLoans();
			displayBorrowerMenu();
			break;

		case 3:
			signOff();
			break;

		default:
			displayBorrowerMenu();
		}
	}

	private void displayLoans() {
		Loans userLoans = controller.getLoans(controller.getConnectedUser());

		System.out.println(">> Emprunts en cours: ");
		for (Loan loan : userLoans.getLoans()) {
			if ((loan.getStartDate().before(new GregorianCalendar()) && loan
					.getEndDate().after(new GregorianCalendar()))
					|| loan.getStartDate().equals(new GregorianCalendar())) {
				System.out.println("> " + loan.toString());
			}
		}

		System.out.println(">> Réservations acceptées: ");
		for (Loan loan : userLoans.getLoans()) {
			if ((loan.getStartDate().after(new GregorianCalendar()) && loan
					.isValidate())) {
				System.out.println("> " + loan.toString());
			}
		}

		System.out.println(">> Historique des emprunts: ");
		for (Loan loan : userLoans.getLoans()) {
			if (((loan.getEndDate().before(new GregorianCalendar()) || loan
					.getEndDate().equals(new GregorianCalendar())) && loan
					.isValidate())) {
				System.out.println("> " + loan.toString());
			}
		}

	}

	/**
	 * Display manager menu.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private void displayManagerMenu() throws Exception {
		int choice;
		System.out.println("1: Ajouter un utilisateur");
		System.out.println("2: Supprimer un utilisateur");
		System.out.println("3: Valider une réservation");
		System.out.println("4: Supprimer une réservation");
		System.out.println("5: Créer une réservation");
		System.out.println("6: Afficher le stock");
		System.out.println("7: Se déconnecter");
		choice = read.nextInt();
		read.nextLine();
		switch (choice) {
		case 1:
			addNewUser();
			break;

		case 2:
			deleteUser();
			break;

		case 3:
			validateReservation();
			break;

		case 4:
			deleteReservation();
			break;

		case 5:
			borrowMenu();
			break;

		case 6:
			displayCurrentStock();
			break;

		case 7:
			signOff();
			break;

		default:

		}
		displayManagerMenu();
	}

	private void displayCurrentStock() throws ParseException {
		GregorianCalendar currentDate = new GregorianCalendar();
		Map<MaterialType, ArrayList<Material>> availableStock = null;
		Map<MaterialType, ArrayList<Material>> borrowedStock = null;

		try {
			availableStock = controller.getAvailableStock(
					DateUtils.dateToString(currentDate),
					DateUtils.dateToString(currentDate));
		} catch (ParseException e) {
			System.out.println("Problème de format de date (jj/mm/aaaa) !");
		}

		System.out.println("Matériel(s) disponible(s): ");
		if (!availableStock.isEmpty()) {
			for (MaterialType mt : availableStock.keySet()) {
				System.out.println(">> " + mt.toString());
				for (Material m : availableStock.get(mt)) {
					System.out.println("> " + m.toString());
				}
			}
		} else {
			System.out.println("Aucun.");
		}

		borrowedStock = controller.getBorrowedStock(DateUtils.dateToString(currentDate),
				DateUtils.dateToString(currentDate));

		System.out.println("Matériel(s) emprunté(s): ");
		if (!borrowedStock.isEmpty()) {
			for (MaterialType mt : borrowedStock.keySet()) {
				System.out.println(">> " + mt.toString());
				for (Material m : borrowedStock.get(mt)) {
					System.out.println("> " + m.toString());
				}
			}
		} else {
			System.out.println("Aucun.");
		}
	}

	/**
	 * Delete reservation.
	 * 
	 * @return true, if successful
	 */
	private boolean deleteReservation() {
		boolean isDeleted = false;
		displayReservation();
		System.out
				.println("Entrez le numéro de la réservation que vous souhaitez supprimer");
		int nbReservation = read.nextInt();
		read.nextLine();
		isDeleted = controller.deleteReservation(nbReservation);

		if (!isDeleted) {
			System.out
					.println("Suppression de la réservation non effectuée...");
		} else
			System.out.println("Suppression de la réservation effectué !");

		return isDeleted;
	}

	/**
	 * Validate reservation.
	 * 
	 * @return true, if successful
	 */
	private boolean validateReservation() {
		boolean isValidate = false;
		displayReservation();
		System.out
				.println("Entrez le numéro de la réservation que vous souhaitez valider");
		int nbReservation = read.nextInt();
		read.nextLine();
		isValidate = controller.validateReservation(nbReservation);

		if (!isValidate) {
			System.out.println("Validation non effectuée...");
		} else
			System.out.println("Validation effectué !");

		return isValidate;

	}

	/**
	 * Display reservation.
	 */
	private void displayReservation() {
		List<Loan> reservations = controller.getReservations();
		for (Loan loan : reservations) {
			System.out.println(loan.toString());
		}
	}

	/**
	 * Borrow menu.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private void borrowMenu() throws Exception {
		System.out
				.println("Entrez la date de début d'emprunt sous la forme suivante JJ/MM/AAAA");
		String startDate = read.nextLine();
		System.out
				.println("Entrez la date de fin d'emprunt sous la forme suivante JJ/MM/AAAA");
		String endDate = read.nextLine();
		System.out.println("Types de matériel disponibles dans cette période:");
		displayStock(startDate, endDate);
		System.out
				.println("Entrez la reference du type materiel que vous souhaitez emprunter");
		int ref = read.nextInt();
		read.nextLine();
		System.out.println("Entrez la quantite que vous souhaitez");
		int amount = read.nextInt();
		read.nextLine();

		boolean achieve = false;

		try {
			achieve = controller.book(ref, amount, startDate, endDate);
		} catch (Exception e) {
			System.out.println("Erreur à l'enregistrement");
		}

		if (achieve)
			System.out.println("Emprunt enregistré");
		else
			System.out.println("Erreur à l'enregistrement");

		displayMenu();
	}

	/**
	 * Display stock between the two dates.
	 * 
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @throws ParseException
	 *             the parse exception
	 */
	private void displayStock(String startDate, String endDate)
			throws ParseException {
		Map<MaterialType, ArrayList<Material>> availableStock = controller
				.getAvailableStock(startDate, endDate);
		for (MaterialType materialType : availableStock.keySet()) {
			System.out.println("Nom: " + materialType.getName() + " Marque: "
					+ materialType.getBrand() + " Reference: "
					+ materialType.getReference() + " -> Quantité: "
					+ availableStock.get(materialType).size());
		}
	}

	/**
	 * Adds the new user.
	 * 
	 * @return true, if successful
	 */
	private boolean addNewUser() {

		String type;
		boolean isSaved = false;
		System.out.println("1: Manager \n2:Etudiant \n3:Enseignant");
		int choice = read.nextInt();
		switch (choice) {
		case 1:
			type = "Manager";
			break;

		case 2:
			type = "Student";
			break;

		case 3:
			type = "Teacher";
			break;

		default:
			System.out.println("Erreur ce type n'existe pas!");
			return false;
		}
		read.nextLine();
		System.out.println("Entrez le nom:");
		String lastname = read.nextLine();
		System.out.println("Entrez le prénom:");
		String firstname = read.nextLine();
		System.out.println("Entrez le login:");
		String login = read.nextLine();
		System.out.println("Entrez le mot de passe:");
		String password = read.nextLine();
		isSaved = controller.addNewUser(type, lastname, firstname, login,
				password);

		if (!isSaved) {
			System.out.println("Enregistrement non effectué...");
		} else
			System.out.println("Enregistrement effectué !");

		return isSaved;

	}

	/**
	 * Delete user.
	 * 
	 * @return true, if successful
	 */
	private boolean deleteUser() {

		boolean isDeleted = false;
		System.out.println("Entrez le nom:");
		String lastname = read.nextLine();
		System.out.println("Entrez le prénom:");
		String firstname = read.nextLine();
		System.out.println("Entrez le login:");
		String login = read.nextLine();

		isDeleted = controller.deleteUser(lastname, firstname, login);

		if (!isDeleted)
			System.out.println("Echec de la suppression.");
		else
			System.out.println("Suppression effectuée !");

		return isDeleted;
	}

	/**
	 * Connect.
	 * 
	 * @return true, if successful
	 */
	public boolean connect() {

		String login;
		String password;
		boolean userConnected = false;

		System.out.println("Veuillez saisir votre identifiant: ");
		login = read.nextLine();
		System.out.println("Veuillez saisir votre mot de passe: ");
		password = read.nextLine();

		userConnected = controller.checkUserPassword(login, password);
		if (userConnected)
			System.out.println("Vous etes connecté !");
		else
			System.out.println("Couple login/password incorrect !");

		return userConnected;
	}

	/**
	 * Sign off.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private void signOff() throws Exception {
		controller.signOff();
		begin();

	}

	/**
	 * Display exit.
	 */
	public void displayExit() {
		System.out.println("MaterialManagement simulation will finish");
		this.read.close();
	}

	/**
	 * Display start.
	 */
	public void displayStart() {
		System.out.println("MaterialManagement simulation will start");
	}
}
