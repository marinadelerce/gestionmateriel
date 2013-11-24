package model.emprunt;

import model.general.Date;
import model.materiel.Materiel;
import model.utilisateur.Emprunteur;


public class Emprunt {
	private Date dateDebut;
	private Date dateFin;
	private Emprunteur emprunteur;
	private Materiel materiel;
	private int quantite;
	private boolean effectif;
	
	public Emprunt(Emprunteur emprunteur, Materiel materiel, int quantite, Date debut, Date fin, boolean effectif){
		dateDebut = debut;
		dateFin = fin;
		this.emprunteur = emprunteur;
		this.materiel = materiel;
		this.quantite = quantite;
		this.effectif = effectif;
	}
	
	public Date getDateDebut(){
		return dateDebut;
	}
	
	public Date getDateFin(){
		return dateFin;
	}
	
	public Emprunteur getEmprunteur(){
		return emprunteur;
	}
	
	public Materiel getMateriel(){
		return materiel;
	}
	
	public int getQuantite(){
		return quantite;
	}
	
	public boolean estEffectif(){
		return effectif;
	}
	
	public void setEffectif(boolean b){
		effectif = b;
	}
}
