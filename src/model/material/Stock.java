package model.material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Stock {
	
	private HashMap<MaterialType, ArrayList<Material>> stock;
	private final String SAVE_FILE = "";
	
	public Stock(){
		stock = new HashMap<MaterialType, ArrayList<Material>>();
	}
	
	public Stock(HashMap<MaterialType, ArrayList<Material>> stock2){
		this.stock = stock2;
	}
	
	public boolean add(Material material){
		if (!stock.containsKey(material.getMaterialType())){
			ArrayList<Material> materials = new ArrayList<Material>();
			materials.add(material);
			stock.put(material.getMaterialType(),materials);
			return true;
		}
		else {
			if(!stock.get(material.getMaterialType()).contains(material)){
				stock.get(material.getMaterialType()).add(material);
				return true;
			}
			return false;
		}
	}
	
	public void remove(Material material) throws Exception{
		
		
		if(!stock.containsKey(material.getMaterialType())){
			throw new Exception("L'objet demandé est absent du stock");
		} else {
			ArrayList<Material> materials = stock.get(material.getMaterialType());
			if(!materials.contains(material))
				throw new Exception("L'objet demandé est absent du stock");
			else
				materials.remove(material);
		}
	}
	
	/*public void remove(int reference) throws Exception{
		
		for(Entry<MaterialType, Integer> entry : stock.entrySet()) {
			
		    MaterialType material = entry.getKey();
		    
		    if (material.getReference() == reference){
		    	remove(material);
		    	return;
		    }
		}
		
		throw new Exception("Référence inexistante dans en stock");
	}*/
	
	public ArrayList<Material> getStock(MaterialType materialType){
		if (!stock.containsKey(materialType)) return null;
		else return stock.get(materialType);
	}
	
	public ArrayList<Material> getStock(int reference){
		
		for(MaterialType materialType : stock.keySet()) {
		    
		    if (materialType.getReference() == reference){
		    	return getStock(materialType);
		    }
		}
		
		return null;
	}
	
	/*public Material getObject(int reference) throws Exception{
		
		for(Entry<MaterialType, Integer> entry : stock.entrySet()) {
			
		    MaterialType materialtype = entry.getKey();
		    
		    if (materialtype.getReference() == reference){
		    	
		    	return new Material(materialtype, Integer.toString(getNewSerialNumber()));

		    }
		}
		
		throw new Exception("Référence inexistante dans le stock");
		
	}*/
	
	public Map<MaterialType, ArrayList<Material>> getStock(){
		return stock;
	}
	
	/*public int getNewSerialNumber(){
		return currentSerialNumber++;
	}*/
	
	public void save(){
		// save stock in the file
	}
	
	@Override
	public Object clone(){
		return new Stock(this.stock);
	}

	public MaterialType getMaterialType(int ref) {
		for(MaterialType materialType : stock.keySet()) {
		    if (materialType.getReference() == ref){
		    	return materialType;
		    }
		}
		return null;
	}
}
