package view;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.TimeZone;

import model.loan.Loan;
import model.manager.GeneralManager;
import model.material.Material;
import model.material.MaterialType;
import model.material.Stock;
import model.user.Borrower;
import model.user.Manager;
import model.user.User;
import controller.Controller;

public class ConsoleView {

	private Controller controller;
	private Scanner read;

	public ConsoleView(Controller control) {
		controller = control;
		read = new Scanner(System.in);
	}

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

	public void displayMenu() throws Exception {
		System.out.println("Menu");
		User connectedUser = controller.getConnectedUser();
		if (connectedUser == null) {
			begin();
		} else {
			// TODO ON VA ESSAYER D'EVITER LES INSTANCEOF
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

	public void displayMaterialType(MaterialType materialT) {
		System.out.print(materialT.toString());
	}

	public void displayUsers(ArrayList<User> users) {
		for (User user : users) {
			System.out.println(user.toString());
		}
	}

	private void displayBorrowerMenu() throws Exception {
		int choice;
		System.out.println("1: Se d�connecter");
		System.out.println("2: Emprunter");
		choice = read.nextInt();
		read.nextLine();
		switch (choice) {
		case 1:
			signOff();
			break;
		case 2:
			borrowMenu();
			break;

		default:
			displayBorrowerMenu();
		}
	}

	private void displayManagerMenu() throws Exception {
		int choice;
		System.out.println("1: Ajouter un utilisateur");
		System.out.println("2: Supprimer un utilisateur");
		System.out.println("3: Valider une r�servation");
		System.out.println("4: Supprimer une r�servation");
		System.out.println("5: Cr�er une r�servation");
		System.out.println("6: Se d�connecter");
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
			signOff();
			break;

		default:

		}
		displayManagerMenu();
	}

	private boolean deleteReservation() {
		boolean isDeleted = false;
		displayReservation();
		System.out
				.println("Entrez le num�ro de la r�servation que vous souhaitez supprimer");
		int nbReservation = read.nextInt();
		read.nextLine();
		isDeleted = controller.deleteReservation(nbReservation);

		if (!isDeleted) {
			System.out
					.println("Suppression de la r�servation non effectu�e...");
		} else
			System.out.println("Suppression de la r�servation effectu� !");

		return isDeleted;
	}

	private boolean validateReservation() {
		boolean isValidate = false;
		displayReservation();
		System.out
				.println("Entrez le num�ro de la r�servation que vous souhaitez valider");
		int nbReservation = read.nextInt();
		read.nextLine();
		isValidate = controller.validateReservation(nbReservation);

		if (!isValidate) {
			System.out.println("Validation non effectu�e...");
		} else
			System.out.println("Validation effectu� !");

		return isValidate;

	}

	private void displayReservation() {
		List<Loan> reservations = controller.getReservations();
		for (Loan loan : reservations) {
			System.out.println(loan.toString());
		}
	}

	private void borrowMenu() throws Exception {
		System.out
				.println("Entrez la date de d�but d'emprunt sous la forme suivante JJ/MM/AAAA");
		String startDate = read.nextLine();
		System.out
				.println("Entrez la date de fin d'emprunt sous la forme suivante JJ/MM/AAAA");
		String endDate = read.nextLine();
		System.out.println("Types de mat�riel disponibles dans cette p�riode:");
		displayStock(startDate, endDate);
		System.out
				.println("Entrez la reference du type materiel que vous souhaitez emprunter");
		int ref = read.nextInt();
		read.nextLine();
		System.out.println("Entrez la quantite que vous souhaitez");
		int amount = read.nextInt();
		read.nextLine();

		controller.book(ref, amount, startDate, endDate);
	}

	/*
	 * public MaterialType chooseMaterial() { String name; Stock stock = new
	 * Stock(); List<MaterialType> materials; MaterialType chosenMaterial =
	 * null; int nb;
	 * 
	 * System.out .println(
	 * "Veuillez choisir le type de materiel que vous souhaiter emprunter: ");
	 * name = read.nextLine();
	 * 
	 * for (MaterialType material : materials) { if
	 * (material.getName().equals(name)) { chosenMaterial = material; } } nb =
	 * stock.getStock(chosenMaterial);
	 * 
	 * System.out.println("Stock de " + chosenMaterial.toString());
	 * 
	 * return chosenMaterial; }
	 */

	private void displayStock(String startDate, String endDate) throws ParseException {
		Map<MaterialType, ArrayList<Material>> availableStock = controller
				.getAvailableStock(startDate, endDate);
		for (MaterialType materialType : availableStock.keySet()) {
			System.out.println("Nom: " + materialType.getName() + " Marque: "
					+ materialType.getBrand() + " Reference: "
					+ materialType.getReference() + " -> Quantit�: "
					+ availableStock.get(materialType).size());
		}
	}

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
		System.out.println("Entrez le pr�nom:");
		String firstname = read.nextLine();
		System.out.println("Entrez le login:");
		String login = read.nextLine();
		System.out.println("Entrez le mot de passe:");
		String password = read.nextLine();
		isSaved = controller.addNewUser(type, lastname, firstname, login,
				password);

		if (!isSaved) {
			System.out.println("Enregistrement non effectu�...");
		} else
			System.out.println("Enregistrement effectu� !");

		return isSaved;

	}

	private boolean deleteUser() {

		boolean isDeleted = false;
		System.out.println("Entrez le nom:");
		String lastname = read.nextLine();
		System.out.println("Entrez le pr�nom:");
		String firstname = read.nextLine();
		System.out.println("Entrez le login:");
		String login = read.nextLine();

		isDeleted = controller.deleteUser(lastname, firstname, login);

		if (!isDeleted)
			System.out.println("Echec de la suppression.");
		else
			System.out.println("Suppression effectu�e !");

		return isDeleted;
	}

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
			System.out.println("Vous etes connect� !");
		else
			System.out.println("Couple login/password incorrect !");

		return userConnected;
	}

	private void signOff() throws Exception {
		controller.signOff();
		begin();

	}

	public void displayExit() {
		System.out.println("MaterialManagement simulation will finish");
		this.read.close();
	}

	public void displayStart() {
		System.out.println("MaterialManagement simulation will start");
	}
}
