package model.material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import xml.ConfigXML;

import model.user.User;

public class Stock {
	
	private HashMap<MaterialType, ArrayList<Material>> stock;
	private final String SAVE_FILE_PATH = "stock";
	private final String SAVE_FILE_VERSION = "1.0.0";
	
	public Stock(){
		stock = new HashMap<MaterialType, ArrayList<Material>>();
	}
	
	public Stock(HashMap<MaterialType, ArrayList<Material>> stock2){
		this.stock = stock2;
	}
	
	public void add(Material material){
		if (!stock.containsKey(material.getMaterialType())){
			ArrayList<Material> materials = new ArrayList<Material>();
			materials.add(material);
			stock.put(material.getMaterialType(),materials);
		}
		else {
			stock.get(material.getMaterialType()).add(material);
			
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
	
	public void load(){
		
		List<HashMap<String, Object>> description = (List<HashMap<String, Object>>) ConfigXML.load(SAVE_FILE_PATH, SAVE_FILE_VERSION);
		
		for (HashMap<String, Object> mat : description){

			MaterialType m = new SmartPhone();
			MaterialType matType = (MaterialType) createObject((String) mat.get("typeClass"));
			matType.setObject((HashMap<String, Object>) mat.get("descriptionType"));
			List<HashMap<String, Object>> materials =  (List<HashMap<String, Object>>) mat.get("materials");
			ArrayList<Material> materialList = new ArrayList<Material>();
			for(HashMap<String, Object> materialD : materials){
				materialList.add(new Material(materialD));
			}
			
			stock.put(matType, materialList);
		}
		System.out.println("Stock loaded");
	}
	
	public void save(){
		
		// save stock in the file
		ConfigXML.store(getSerializableDescription(), SAVE_FILE_PATH, SAVE_FILE_VERSION);
	}
	
	public List<HashMap<String, Object>> getSerializableDescription(){
		
		List<HashMap<String, Object>> stockDescription = new LinkedList<HashMap<String, Object>>();
		
		for(Entry<MaterialType, ArrayList<Material>> entry : stock.entrySet()) {
			
			MaterialType key = entry.getKey();
			ArrayList<Material> value = entry.getValue();

			HashMap<String, Object> mat = new HashMap<String, Object>();
			//TODO erreur ici
			mat.put("typeClass", key.getClass().getName());
			mat.put("descriptionType", key.getSerializableDescription());
			
		    List<HashMap<String, Object>> materials = new LinkedList<HashMap<String, Object>>();
		    for (Material material : value){
		    	materials.add(material.getSerializableDescription());
		    }
	
		    mat.put("materials", materials);
		    
		    stockDescription.add(mat);
		}
		
		return stockDescription;
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
	
	static Object createObject(String className) {
		 
		Object object = null;
		try {
		    Class<?> classDefinition = Class.forName(className);
		    object = classDefinition.newInstance();
	    } catch (InstantiationException e) {
		          System.out.println(e);
		} catch (IllegalAccessException e) {
		          System.out.println(e);
		} catch (ClassNotFoundException e) {
		          System.out.println(e);
		}
		return object;
	}
}
