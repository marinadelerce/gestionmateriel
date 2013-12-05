/*
 * 
 */
package main;

import controller.Controller;

/**
 * The Class Main.
 * @author Marina Delerce & Romain Guillot 
 * @version 1.0.0
 */
public class Main {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		// chargement des fichiers de configuration
		// chargement des fichiers de données
		try{
		Controller c = new Controller();
		c.start();
		} catch(Exception e){e.printStackTrace();}
	}

}
