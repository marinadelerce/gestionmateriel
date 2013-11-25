package model.general;

import model.materiel.Materiel;
import model.utilisateur.Emprunteur;
import model.utilisateur.Utilisateurs;

public class Systeme {
	
	private Utilisateurs utilisateurs;
	private Date dateActuelle;
	private Gestion gestion;
	
	public boolean reserver(Emprunteur emprunteur, Materiel materiel, Date dateDebut, Date dateFin, int quantite){
		
		boolean reserver = true;
		int duree;
		// verifier les dates
		if (dateDebut.estSuperieure(dateFin) || dateDebut.equals(dateFin))
			reserver = false;
		// verifier les durees
		else {
			if (dateDebut.getMois() == dateFin.getMois()) {
				duree = dateFin.getJour() - dateDebut.getJour();
			} else
				duree = dateFin.getJour() + (dateDebut.getJour() - 30);
			if(duree > emprunteur.getDureeEmprunt()) reserver = false;
		}
		
		return reserver;
	}
}
