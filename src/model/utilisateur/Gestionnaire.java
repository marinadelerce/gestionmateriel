package model.utilisateur;

import model.emprunt.Emprunt;
import model.general.Date;

public class Gestionnaire extends Utilisateur {

	public Gestionnaire(String nom, String prenom, int identifiant) {
		super(nom, prenom, identifiant);
	}

	public int calculEcart(Date dateDeb, Date dateFin) {
		int result = 0;

		if (dateDeb.estSuperieure(dateFin))
			result = -1;
		else if (dateDeb.estInferieure(dateFin)) {
			if (dateDeb.getMois() == dateFin.getMois()) {
				result = dateFin.getJour() - dateDeb.getJour();
			} else
				result = dateFin.getJour() + (dateDeb.getJour() - 30);
		}

		// On compte des journées de 8 heures
		return result * 8;
	}

	public Emprunt validerEmprunt(Emprunt emprunt) {
		
		// lors de la création de l'emprunt on verifie l'état du stock, il n'y a
		// donc pas besoin de le verifier a nouveau
		
		int nbHeuresEmprunt = calculEcart(emprunt.getDateDebut(),
				emprunt.getDateFin());
		if (nbHeuresEmprunt <= emprunt.getMateriel().getTempsMaxEmprunt()) {
			if (nbHeuresEmprunt <= emprunt.getEmprunteur().getDureeEmprunt()) {
				emprunt.setValidate(true);
			}
		}

		return emprunt;
	}

}
