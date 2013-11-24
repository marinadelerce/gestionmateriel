package model.materiel;

public abstract class Materiel {

	private String nom;
	private String marque;
	private String description;
	private int reference;
	
	public Materiel(String nom, String marque, String description, int reference){
		this.nom = nom;
		this.marque = marque;
		this.description = description;
		this.reference = reference;
	}
	
	public String getNom(){
		return nom;
	}
	
	public String getMarque(){
		return marque;
	}
	
	public String getDescription(){
		return description;
	}
	
	public int getReference(){
		return reference;
	}
}
