package model.material;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Stock {
	
	private Map<Material, Integer> stock;
	//private int nbRef;
	
	public Stock(){
		stock = new HashMap<Material, Integer>();
	}
	
	public Stock(Map<Material, Integer> stock2){
		this.stock = stock2;
	}
	
	public void add(Material material){
		if (!stock.containsKey(material)){
			stock.put(material, new Integer(1));
		}
		else {
			Integer previous = stock.get(material);
			stock.put(material, previous+1);
		}
	}
	
	public void remove(Material material) throws Exception{
		
		if(!stock.containsKey(material)){
			throw new Exception("L'objet demand� est absent du stock");
		} else if (stock.get(material).equals(new Integer(0))){
			throw new Exception("Il ne reste plus de ce mat�riel en stock");
		} else {
			Integer previous = stock.get(material);
			stock.put(material, previous+1);
		}
	}
	
	public void remove(int reference) throws Exception{
		
		for(Entry<Material, Integer> entry : stock.entrySet()) {
			
		    Material material = entry.getKey();
		    
		    if (material.getReference() == reference){
		    	remove(material);
		    	return;
		    }
		}
		
		throw new Exception("R�f�rence inexistante dans en stock");
	}
	
	public Integer getStock(Material material){
		if (!stock.containsKey(material)) return new Integer(0);
		else return stock.get(material);
	}
	
	public Integer getStock(int reference){
		
		for(Entry<Material, Integer> entry : stock.entrySet()) {
			
		    Material material = entry.getKey();
		    
		    if (material.getReference() == reference){
		    	return getStock(material);
		    }
		}
		
		return new Integer(0);
	}
	
	public Material getObject(int reference) throws Exception{
		
		for(Entry<Material, Integer> entry : stock.entrySet()) {
			
		    Material material = entry.getKey();
		    
		    if (material.getReference() == reference){
		    	
		    	Material newMaterial = (Material) material.clone();
		    	
		    	return material;
		    }
		}
		
		throw new Exception("R�f�rence inexistante dans le stock");
		
	}
	
	@Override
	public Object clone(){
		return new Stock(this.stock);
	}
}
