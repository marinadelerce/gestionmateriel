/*
 * 
 */
package model.material;

import java.util.HashMap;

/**
 * The Class Tablet.
 * @author Marina Delerce & Romain Guillot 
 * @version 1.0.0
 */
public class Tablet extends Device {

	/**
	 * Instantiates a new tablet.
	 *
	 * @param name the name
	 * @param brand the brand
	 * @param description the description
	 * @param reference the reference
	 * @param type the type
	 * @param maxTimeLoan the max time loan
	 */
	public Tablet(String name, String brand, String description, int reference,
			OS type, int maxTimeLoan) {
		super(name, brand, description, reference, type, maxTimeLoan);
	}
	
	/**
	 * Instantiates a new tablet.
	 */
	public Tablet(){super();}
	
	/* (non-Javadoc)
	 * @see model.material.MaterialType#clone()
	 */
	@Override
	public Object clone(){
		return new Tablet(this.getName(), this.getBrand(), this.getDescription(), this.getReference(), this.getTypeOS(), this.getMaxTimeLoan());
	}
	
	/* (non-Javadoc)
	 * @see model.material.Device#getSerializableDescription()
	 */
	@Override
	public HashMap<String, Object> getSerializableDescription(){

		HashMap<String, Object> tabletDescription = new HashMap<String, Object>();
		tabletDescription.put("name", getName());
		tabletDescription.put("brand", getBrand());
		tabletDescription.put("description", getDescription());
		tabletDescription.put("typeOS", getTypeOS());
		tabletDescription.put("reference", getReference());
		tabletDescription.put("maxTimeLoan", getMaxTimeLoan());
		tabletDescription.put("className", this.getClass().getName());
		
		return tabletDescription;
	}
	
	/* (non-Javadoc)
	 * @see model.material.Device#setObject(java.util.HashMap)
	 */
	@Override
	public void setObject(HashMap<String, Object> description){
		super.setObject(description);
	}
}
