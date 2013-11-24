package model.general;

import java.util.LinkedList;

import model.emprunt.Emprunt;
import model.emprunt.Emprunts;
import model.emprunt.Reservations;
import model.materiel.Materiel;
import model.materiel.Stock;
import model.utilisateur.Emprunteur;

public class Gestion {
	
	private Stock stock;
	private Emprunts emprunts;
	private Reservations reservations;
	
	public Gestion(){
		stock = new Stock();
		emprunts = new Emprunts();
		reservations = new Reservations();
	}
	
	public int calculerDureeMaxEmprunt(Materiel materiel, Date date){
		return 0;
	}
	
	public Stock anticiperStock(Date date) throws Exception{
		
		// stock a l'instant
		Stock stock_clone = (Stock) stock.clone();
		
		// on ajoute les emprunts retournes a la date
		LinkedList<Emprunt> emprunts_termines = (LinkedList<Emprunt>) emprunts.getEmpruntsTermines(date);
		for (Emprunt emprunt : emprunts_termines){
			Materiel materiel = emprunt.getMateriel();
			Integer quantite = emprunt.getQuantite();
			for (int i=0; i<quantite; i++) stock_clone.add(materiel);
		}
		
		// on retire les emprunts encore actifs a la date
		LinkedList<Emprunt> emprunts_actifs = (LinkedList<Emprunt>) emprunts.getEmpruntsActifs(date);
		for (Emprunt emprunt : emprunts_actifs){
			Materiel materiel = emprunt.getMateriel();
			Integer quantite = emprunt.getQuantite();
			for (int i=0; i<quantite; i++) stock_clone.retirer(materiel);
		}
		
		// on retire les reservations a la date
		LinkedList<Emprunt> reserves = (LinkedList<Emprunt>) reservations.getReservationsActives(date);
		for (Emprunt emprunt : reserves){
			Materiel materiel = emprunt.getMateriel();
			Integer quantite = emprunt.getQuantite();
			for (int i=0; i<quantite; i++) stock_clone.retirer(materiel);
		}
		
		return stock_clone;
	}
	
	public boolean reserver(Materiel materiel, Emprunteur emprunteur, int quantite, Date dateDebut, Date dateFin) throws Exception{
		
		// stock prevu a la date t
		Stock stock_anticipe;
		
		// on verifie que la quantite de materiel est disponible pendant toute la duree de l'emprunt
		Date date_virtuelle = (Date) dateDebut.clone();
		
		do{
			
			stock_anticipe = anticiperStock(date_virtuelle);
			
			// si il n'y a pas assez de ce type de materiel a la date t  on refuse l'emprunt
			if (stock_anticipe.getStock(materiel) < quantite) return false;
			
			date_virtuelle.heureSuivante();
		} while (date_virtuelle.estInferieure(dateFin) || date_virtuelle.equals(dateFin));
		
		// on crée l'emprunt
		Emprunt emprunt = new Emprunt(emprunteur, materiel, quantite, dateDebut, dateFin, false);
		reservations.add(emprunt);
		
		return true;
	}
}
