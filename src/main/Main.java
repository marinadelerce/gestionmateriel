package main;

import controller.Controller;

public class Main {
	
	public static void main(String[] args) {
		
		// chargement des fichiers de configuration
		// chargement des fichiers de données
		try{
		Controller c = new Controller();
		c.start();
		} catch(Exception e){e.printStackTrace();}
	}

}
