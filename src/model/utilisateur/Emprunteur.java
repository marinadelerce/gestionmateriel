package model.utilisateur;

public abstract class Emprunteur extends Utilisateur{

	private static int dureeEmprunt;
	
	public Emprunteur(String nom, String prenom, int identifiant, int dureeEmprunt){
		super(nom, prenom, identifiant);
		setDureeEmprunt(dureeEmprunt);
	}

	public int getDureeEmprunt() {
		return dureeEmprunt;
	}

	public static void setDureeEmprunt(int dureeEmprunt) {
		Emprunteur.dureeEmprunt = dureeEmprunt;
	}
	
}
