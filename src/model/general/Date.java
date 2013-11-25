package model.general;

public class Date {
	private int heure;
	private int jour;
	private int mois;
	private int annee;
	
	public Date(){
		heure = 0;
		jour = 0;
		mois = 0;
		annee = 0;
	}
	
	public Date(int heure, int jour, int mois, int annee){
		this.heure = heure;
		this.jour=jour;
		this.mois=mois;
		this.annee=annee;
	}
	
	public boolean estSuperieure(Date date){
		if (annee>date.annee) return true;
		if (annee<date.annee) return false;
		if (mois>date.mois) return true;
		if (mois<date.mois) return false;
		if (jour>date.jour) return true;
		if (jour<date.jour) return false;
		if (heure>date.heure) return  true;
		if (heure<date.heure) return false;
		return false;
	}
	
	public boolean estInferieure(Date date){
		if (estSuperieure(date) || equals(date)) return false;
		else return true;
	}
	
	public void heureSuivante(){
		heure++;
		if (heure>=24){
			heure=0;
			jour++;
		}
		if (jour>30){
			jour=1;
			mois++;
		}
		if (mois>12){
			mois=1;
			annee++;
		}
	}
	
	
	public int getHeure() {
		return heure;
	}

	public int getJour() {
		return jour;
	}

	public int getMois() {
		return mois;
	}

	public int getAnnee() {
		return annee;
	}

	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Date)) return false;
		Date d = (Date) obj;
		if (annee == d.annee && mois == d.mois && jour == d.jour && heure == d.heure) return true;
		else return false;
	}
	
	@Override
	public Object clone(){
		return new Date(heure, jour, mois, annee);
	}
}
