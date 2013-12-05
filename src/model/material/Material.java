package model.material;

import java.util.HashMap;

public class Material {

	private MaterialType material_type;
	private String serial_number;
	
	public Material(MaterialType type, String serial){
		material_type = type;
		serial_number = serial;
	}
	
	public Material(HashMap<String, Object> description){
		serial_number = (String) description.get("serialNumber");
		HashMap<String, Object> materialTypeDescription = (HashMap<String, Object>) description.get("materialType");
		material_type = (MaterialType) createObject((String) materialTypeDescription.get("className"));
		material_type.setObject(materialTypeDescription);
	}
	
	public MaterialType getMaterialType(){
		return material_type;
	}
	
	public String getSerialNumber(){
		return serial_number;
	}
	
	public HashMap<String, Object> getSerializableDescription(){

		HashMap<String, Object> materialDescription = new HashMap<String, Object>();
		materialDescription.put("materialType", material_type.getSerializableDescription());
		materialDescription.put("serialNumber", serial_number);
		
		return materialDescription;
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
