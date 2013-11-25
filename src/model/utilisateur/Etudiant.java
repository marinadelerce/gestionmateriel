package model.utilisateur;

public class Etudiant extends Emprunteur{

	private final static int dureeEmprunt = 4;
	
	public Etudiant(String nom, String prenom, int identifiant){
		super(nom, prenom, identifiant,dureeEmprunt);
	}
	
	
}
