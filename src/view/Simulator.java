package view;

import java.util.List;
import java.util.Scanner;

import model.general.GeneralManager;
import model.material.MaterialType;
import model.material.Stock;
import model.user.Borrower;
import model.user.Manager;
import model.user.User;

public class Simulator {

	private List<User> users;
	private GeneralManager generalManager;
	private Scanner read;
	public Simulator(GeneralManager generalManager){
		this.generalManager = generalManager;
		this.read = new Scanner(System.in);
		run();
	}
	
	public void run(){
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
				run();
			break;
			
		case 2:
			exit();
			
		default:
			exit();
		}
	}
	
	private void displayMenu() {
		System.out.println("Menu");
		User connectedUser = generalManager.getConnectedUser();
		if(connectedUser == null){
			run();
		}
		else{
			if(connectedUser instanceof Manager){
				displayManagerMenu();
			}
			if(connectedUser instanceof Borrower){
				displayBorrowerMenu();
			}
		}
	}

	private void displayBorrowerMenu() {
		int choice;
		System.out.println("1: Quitter");
		choice = read.nextInt();
		read.nextLine();
		switch(choice){
		case 1:
			exit();
			break;
			
		default:
			displayBorrowerMenu();
		}
	}

	private void displayManagerMenu() {
		int choice;
		System.out.println("1: Ajouter un utilisateur");
		System.out.println("2: Se déconnecter");
		choice = read.nextInt();
		read.nextLine();
		switch(choice){
		case 1:
			addNewUser();
			break;
			
		case 2:
			signOff();
			break;
			
		default:
			
		} 
		displayManagerMenu();
	}

	private void signOff() {
		generalManager.signOff();
		run();
		
	}

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
			type = "Etudiant";
			break;
			
		case 3:
			type = "Enseignant";
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
		isSaved = generalManager.addNewUser(type, lastname, firstname, login, password);
		
		if(!isSaved){
			System.out.println("Enregistrement non effectué...");
		}
		else 
			System.out.println("Enregistrement effectué !");
		
		return isSaved;
		
	}

	public void exit(){
		this.read.close();
		System.exit(0);
	}
	
	
	public boolean connect() {
		String login;
		String password;
		boolean userConnected = false;

		
		System.out.println("Veuillez saisir votre identifiant: ");
		login = read.nextLine();
		System.out.println("Veuillez saisir votre mot de passe: ");
		password = read.nextLine();

		userConnected = generalManager.checkUserPassword(login, password);
		if (userConnected)
			System.out.println("Vous etes connecté !");
		else
			System.out.println("Couple login/password incorrect !");

		return userConnected;
	}

	public MaterialType chooseMaterial() {
		String name;
		Stock stock = new Stock();
		List<MaterialType> materials;
		MaterialType chosenMaterial = null;
		int nb;

		Scanner read = new Scanner(System.in);
		System.out
				.println("Veuillez choisir le type de materiel que vous souhaiter emprunter: ");
		name = read.nextLine();

		/*for (MaterialType material : materials) {
			if (material.getName().equals(name)) {
				chosenMaterial = material;
			}
		}*/
		nb = stock.getStock(chosenMaterial);

		System.out.println("Stock de " + chosenMaterial.toString());

		return chosenMaterial;
	}
}
