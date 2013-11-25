package model.material;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Stock {
	
	private Map<MaterialType, Integer> stock;
	
	public Stock(){
		stock = new HashMap<MaterialType, Integer>();
	}
	
	public Stock(Map<MaterialType, Integer> stock2){
		this.stock = stock2;
	}
	
	public void add(MaterialType material){
		if (!stock.containsKey(material)){
			stock.put(material, new Integer(1));
		}
		else {
			Integer previous = stock.get(material);
			stock.put(material, previous+1);
		}
	}
	
	public void remove(MaterialType material) throws Exception{
		
		if(!stock.containsKey(material)){
			throw new Exception("L'objet demandé est absent du stock");
		} else if (stock.get(material).equals(new Integer(0))){
			throw new Exception("Il ne reste plus de ce matériel en stock");
		} else {
			Integer previous = stock.get(material);
			stock.put(material, previous+1);
		}
	}
	
	public void remove(int reference) throws Exception{
		
		for(Entry<MaterialType, Integer> entry : stock.entrySet()) {
			
		    MaterialType material = entry.getKey();
		    
		    if (material.getReference() == reference){
		    	remove(material);
		    	return;
		    }
		}
		
		throw new Exception("Référence inexistante dans en stock");
	}
	
	public Integer getStock(MaterialType material){
		if (!stock.containsKey(material)) return new Integer(0);
		else return stock.get(material);
	}
	
	public Integer getStock(int reference){
		
		for(Entry<MaterialType, Integer> entry : stock.entrySet()) {
			
		    MaterialType material = entry.getKey();
		    
		    if (material.getReference() == reference){
		    	return getStock(material);
		    }
		}
		
		return new Integer(0);
	}
	
	public MaterialType getObject(int reference) throws Exception{
		
		for(Entry<MaterialType, Integer> entry : stock.entrySet()) {
			
		    MaterialType material = entry.getKey();
		    
		    if (material.getReference() == reference){
		    	return material;
		    }
		}
		
		throw new Exception("Référence inexistante dans le stock");
		
	}
	
	@Override
	public Object clone(){
		return new Stock(this.stock);
	}
}
