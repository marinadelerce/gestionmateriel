package model.materiel;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Stock {
	
	private Map<Materiel, Integer> stock;
	
	public Stock(){
		stock = new HashMap<Materiel, Integer>();
	}
	
	public Stock(Map<Materiel, Integer> stock2){
		this.stock = stock2;
	}
	
	public void add(Materiel materiel){
		if (!stock.containsKey(materiel)){
			stock.put(materiel, new Integer(1));
		}
		else {
			Integer precedent = stock.get(materiel);
			stock.put(materiel, precedent+1);
		}
	}
	
	public void retirer(Materiel materiel) throws Exception{
		
		if(!stock.containsKey(materiel)){
			throw new Exception("L'objet demandé est absent du stock");
		} else if (stock.get(materiel).equals(new Integer(0))){
			throw new Exception("Il ne reste plus de ce matériel en stock");
		} else {
			Integer precedent = stock.get(materiel);
			stock.put(materiel, precedent+1);
		}
	}
	
	public void retirer(int reference) throws Exception{
		
		for(Entry<Materiel, Integer> entry : stock.entrySet()) {
			
		    Materiel materiel = entry.getKey();
		    
		    if (materiel.getReference() == reference){
		    	retirer(materiel);
		    	return;
		    }
		}
		
		throw new Exception("Référence inexistante dans en stock");
	}
	
	public Integer getStock(Materiel materiel){
		if (!stock.containsKey(materiel)) return new Integer(0);
		else return stock.get(materiel);
	}
	
	public Integer getStock(int reference){
		
		for(Entry<Materiel, Integer> entry : stock.entrySet()) {
			
		    Materiel materiel = entry.getKey();
		    
		    if (materiel.getReference() == reference){
		    	return getStock(materiel);
		    }
		}
		
		return new Integer(0);
	}
	
	public Materiel getObjet(int reference) throws Exception{
		
		for(Entry<Materiel, Integer> entry : stock.entrySet()) {
			
		    Materiel materiel = entry.getKey();
		    
		    if (materiel.getReference() == reference){
		    	return materiel;
		    }
		}
		
		throw new Exception("Référence inexistante dans le stock");
		
	}
	
	@Override
	public Object clone(){
		return new Stock(this.stock);
	}
}
