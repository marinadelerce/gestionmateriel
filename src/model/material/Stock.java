package model.material;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Stock {
	
	private Map<MaterialType, Integer> stock;
	private int currentSerialNumber;
	private final String SAVE_FILE = "";
	
	public Stock(){
		stock = new HashMap<MaterialType, Integer>();
		currentSerialNumber=0;
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
	
	public Material getObject(int reference) throws Exception{
		
		for(Entry<MaterialType, Integer> entry : stock.entrySet()) {
			
		    MaterialType materialtype = entry.getKey();
		    
		    if (materialtype.getReference() == reference){
		    	
		    	return new Material(materialtype, Integer.toString(getNewSerialNumber()));

		    }
		}
		
		throw new Exception("Référence inexistante dans le stock");
		
	}
	
	public HashMap<MaterialType, Integer> getStock(){
		return (HashMap<MaterialType, Integer>) stock;
	}
	
	public int getNewSerialNumber(){
		return currentSerialNumber++;
	}
	
	public void save(){
		// save stock in the file
	}
	
	@Override
	public Object clone(){
		return new Stock(this.stock);
	}
}
