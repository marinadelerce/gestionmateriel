package model.emprunt;

import java.util.LinkedList;
import java.util.List;
import model.general.Date;

public class Emprunts {
	
	private List<Emprunt> emprunts;
	
	public Emprunts(){
		emprunts = new LinkedList<Emprunt>();
	}
	
	public void add(Emprunt emprunt){
		
		emprunt.setEffectif(true);
		emprunts.add(emprunt);
	}
	
	public void remove(Emprunt emprunt){
		emprunts.remove(emprunt);
	}
	
	public List<Emprunt> getEmpruntsActifs(Date date){
		
		List<Emprunt> emprunts_actifs = new LinkedList<Emprunt>();
		
		for(Emprunt emprunt : emprunts) {
			
			if (emprunt.getDateFin().estSuperieure(date) || emprunt.getDateFin().equals(date)) emprunts_actifs.add(emprunt);
		}
		
		return emprunts_actifs;
		
	}
	
	public List<Emprunt> getEmpruntsTermines(Date date){
		
		List<Emprunt> emprunts_termines = new LinkedList<Emprunt>();
		
		for(Emprunt emprunt : emprunts) {
			
			if (emprunt.getDateFin().estInferieure(date)) emprunts_termines.add(emprunt);
		}
		
		return emprunts_termines;
	}
}
