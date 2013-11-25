package model.utilisateur;

public class Enseignant extends Emprunteur{
	
	private final static int dureeEmprunt = 24;
	
	public Enseignant(String nom, String prenom, int identifiant){
		super(nom, prenom, identifiant,dureeEmprunt);
	}

}
