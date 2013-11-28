package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

import model.material.MaterialType;
import model.material.Stock;
import model.user.Borrower;
import model.user.Manager;
import model.user.User;
import controller.Controller;

public class ConsoleView {

	private Controller controller;
	private Scanner read;
	
	public ConsoleView(Controller control){
		controller=control;
		read = new Scanner(System.in);
	}
	
	public void begin(){
		int result;
	
		System.out.println("Welcome!");
		System.out.println("1: Se connecter \n 2: Quitter");
		result = read.nextInt();
		read.nextLine();
		
		switch(result){
		case 1:
			boolean isConnected = connect();
			if(isConnected)
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
	
	public void displayMenu() {
		System.out.println("Menu");
		User connectedUser = controller.getConnectedUser();
		if(connectedUser == null){
			begin();
		}
		else{
			//TODO ON VA ESSAYER D'EVITER LES INSTANCEOF
			if(connectedUser instanceof Manager){
				displayManagerMenu();
			}
			if(connectedUser instanceof Borrower){
				displayBorrowerMenu();
			}
		}
	}
	
	public void displayStock(){
		
		HashMap<MaterialType, Integer> stock_description= controller.getStock();
		
		for(Entry<MaterialType, Integer> entry : stock_description.entrySet()) {
			
		    MaterialType materialtype = entry.getKey();
		    Integer nb = stock_description.get(materialtype);
		    
		    displayMaterialType(materialtype);
		    System.out.println(" -> " + nb);
		}
	}
	
	public void displayMaterialType(MaterialType materialT){
		System.out.print(materialT.toString());
	}
	
	public void displayUsers(ArrayList<User> users){
		for(User user : users){
			System.out.println(user.toString());
		}
	}
	
	private void displayBorrowerMenu() {
		int choice;
		System.out.println("1: Se déconnecter");
		System.out.println("2: Emprunter");
		choice = read.nextInt();
		read.nextLine();
		switch(choice){
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

	private void displayManagerMenu() {
		int choice;
		System.out.println("1: Ajouter un utilisateur");
		System.out.println("2: Supprimer un utilisateur");
		System.out.println("3: Se déconnecter");
		choice = read.nextInt();
		read.nextLine();
		switch(choice){
		case 1:
			addNewUser();
			break;
			
		case 2:
			deleteUser();
			break;
			
		case 3:
			signOff();
			break;
			
		default:
			
		} 
		displayManagerMenu();
	}
	
	private void borrowMenu(){
		System.out.println("Entrez la reference du materiel que vous souhaitez emprunter");
		displayStock();
		String ref = read.nextLine();
		System.out.println("Entrez la quantite que vous souhaitez");
		String amount = read.nextLine();
		System.out.println("Entrez la date de début d'emprunt sous la forme suivante DD/MM/YYYY");
		String dateD = read.nextLine();
		System.out.println("Entrez la date de fin d'emprunt sous la forme suivante DD/MM/YYYY");
		String dateF = read.nextLine();
	}
	
	/*public MaterialType chooseMaterial() {
		String name;
		Stock stock = new Stock();
		List<MaterialType> materials;
		MaterialType chosenMaterial = null;
		int nb;
		
		System.out
				.println("Veuillez choisir le type de materiel que vous souhaiter emprunter: ");
		name = read.nextLine();

		for (MaterialType material : materials) {
			if (material.getName().equals(name)) {
				chosenMaterial = material;
			}
		}
		nb = stock.getStock(chosenMaterial);

		System.out.println("Stock de " + chosenMaterial.toString());

		return chosenMaterial;
	}*/

	private boolean addNewUser() {
		
		String type;
		boolean isSaved = false;
		System.out.println("1: Manager \n2:Etudiant \n3:Enseignant");
		int choice = read.nextInt();
		switch(choice){
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
		isSaved = controller.addNewUser(type, lastname, firstname, login, password);
		
		if(!isSaved){
			System.out.println("Enregistrement non effectué...");
		}
		else 
			System.out.println("Enregistrement effectué !");
		
		return isSaved;
		
	}
	
	private boolean deleteUser() {
		
		boolean isDeleted = false;
		System.out.println("Entrez le nom:");
		String lastname = read.nextLine();
		System.out.println("Entrez le prénom:");
		String firstname = read.nextLine();
		System.out.println("Entrez le login:");
		String login = read.nextLine();
		
		isDeleted = controller.deleteUser(lastname,firstname,login);
		
		if(!isDeleted) System.out.println("Echec de la suppression.");
		else
			System.out.println("Suppression effectuée !");
		
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
			System.out.println("Vous etes connecté !");
		else
			System.out.println("Couple login/password incorrect !");

		return userConnected;
	}
	
	private void signOff() {
		controller.signOff();
		begin();
		
	}
	
	public void displayExit(){
		System.out.println("MaterialManagement simulation will finish");
		this.read.close();
	}
	
	public void displayStart(){
		System.out.println("MaterialManagement simulation will start");
	}
}
