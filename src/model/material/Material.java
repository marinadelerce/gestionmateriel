/*
 * 
 */
package model.material;

import java.util.HashMap;

/**
 * The Class Material.
 * @author Marina Delerce & Romain Guillot 
 * @version 1.0.0
 */
public class Material {

	/** The material_type. */
	private MaterialType material_type;
	
	/** The serial_number. */
	private String serial_number;
	
	/**
	 * Instantiates a new material.
	 *
	 * @param type the type
	 * @param serial the serial
	 */
	public Material(MaterialType type, String serial){
		material_type = type;
		serial_number = serial;
	}
	
	/**
	 * Instantiates a new material.
	 *
	 * @param description the description
	 */
	public Material(HashMap<String, Object> description){
		serial_number = (String) description.get("serialNumber");
		HashMap<String, Object> materialTypeDescription = (HashMap<String, Object>) description.get("materialType");
		material_type = (MaterialType) createObject((String) materialTypeDescription.get("className"));
		material_type.setObject(materialTypeDescription);
	}
	
	/**
	 * Gets the material type.
	 *
	 * @return the material type
	 */
	public MaterialType getMaterialType(){
		return material_type;
	}
	
	/**
	 * Gets the serial number.
	 *
	 * @return the serial number
	 */
	public String getSerialNumber(){
		return serial_number;
	}
	
	/**
	 * Gets the serializable description.
	 *
	 * @return the serializable description
	 */
	public HashMap<String, Object> getSerializableDescription(){

		HashMap<String, Object> materialDescription = new HashMap<String, Object>();
		materialDescription.put("materialType", material_type.getSerializableDescription());
		materialDescription.put("serialNumber", serial_number);
		
		return materialDescription;
	}
	
	/**
	 * Creates the object.
	 *
	 * @param className the class name
	 * @return the object
	 */
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
