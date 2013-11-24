package model.emprunt;

import java.util.LinkedList;
import java.util.List;

import model.general.Date;

public class Reservations {
	
	private List<Emprunt> reservations;
	
	public Reservations(){
		reservations = new LinkedList<Emprunt>();
	}
	
	public void add(Emprunt emprunt){
		
		emprunt.setEffectif(false);
		reservations.add(emprunt);
	}
	
	public void remove(Emprunt emprunt){
		reservations.remove(emprunt);
	}
	
	public List<Emprunt> getReservationsActives(Date date){
		
		List<Emprunt> reservations_actives = new LinkedList<Emprunt>();
		
		for(Emprunt emprunt : reservations) {
			
			if (((emprunt.getDateDebut().estInferieure(date)||(emprunt.getDateDebut().equals(date))) && (emprunt.getDateFin().estSuperieure(date) || emprunt.getDateFin().equals(date)))) 
					reservations_actives.add(emprunt);
		}
		
		return reservations_actives;
		
	}
}
