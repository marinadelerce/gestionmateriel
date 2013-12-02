package main;

import controller.Controller;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		// chargement des fichiers de configuration
		// chargement des fichiers de données
		Controller c = new Controller();
		c.start();
	}

}
