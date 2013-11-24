package model.utilisateur;

public abstract class Utilisateur {
	
	protected String nom;
	protected String prenom;
	protected int identifiant;
	
	public Utilisateur(String nom, String prenom, int identifiant){
		this.nom = nom;
		this.prenom = prenom;
		this.identifiant = identifiant;
	}
	
	public String getNom(){
		return nom;
	}
	
	public String getPrenom(){
		return prenom;
	}
	
	public int getIdentifiant(){
		return identifiant;
	}
}
