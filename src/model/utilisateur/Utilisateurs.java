package model.utilisateur;

import java.util.LinkedList;
import java.util.List;

public class Utilisateurs {

	private List<Utilisateur> utilisateurs;
	
	public Utilisateurs(){
		utilisateurs = new LinkedList<Utilisateur>();
	}
	
	public void add(Utilisateur utilisateur){
		utilisateurs.add(utilisateur);
	}
	
	public void remove(Utilisateur utilisateur){
		utilisateurs.remove(utilisateur);
	}
	
}
