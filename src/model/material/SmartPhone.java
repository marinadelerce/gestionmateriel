/*
 * 
 */
package model.material;

import java.util.HashMap;

/**
 * The Class SmartPhone.
 * @author Marina Delerce & Romain Guillot 
 * @version 1.0.0
 */
public class SmartPhone extends Device {

	/**
	 * Instantiates a new smart phone.
	 *
	 * @param name the name
	 * @param brand the brand
	 * @param description the description
	 * @param reference the reference
	 * @param type the type
	 * @param maxTimeLoan the max time loan
	 */
	public SmartPhone(String name, String brand, String description,
			int reference, OS type, int maxTimeLoan) {
		super(name, brand, description, reference, type, maxTimeLoan);
	}
	
	/**
	 * Instantiates a new smart phone.
	 */
	public SmartPhone() {super();}

	/* (non-Javadoc)
	 * @see model.material.MaterialType#clone()
	 */
	@Override
	public Object clone(){
		return new SmartPhone(this.getName(), this.getBrand(), this.getDescription(), this.getReference(), this.getTypeOS(), this.getMaxTimeLoan());
	}
	
	/* (non-Javadoc)
	 * @see model.material.Device#getSerializableDescription()
	 */
	@Override
	public HashMap<String, Object> getSerializableDescription(){

		HashMap<String, Object> smartphoneDescription = new HashMap<String, Object>();
		smartphoneDescription.put("name", getName());
		smartphoneDescription.put("brand", getBrand());
		smartphoneDescription.put("description", getDescription());
		smartphoneDescription.put("typeOS", getTypeOS());
		smartphoneDescription.put("reference", getReference());
		smartphoneDescription.put("maxTimeLoan", getMaxTimeLoan());
		smartphoneDescription.put("className", this.getClass().getName());
		
		return smartphoneDescription;
	}
	
	/* (non-Javadoc)
	 * @see model.material.Device#setObject(java.util.HashMap)
	 */
	public void setObject(HashMap<String, Object> description){
		this.name = (String)description.get("name");
		this.brand = (String)description.get("brand");
		this.description = (String)description.get("description");
		this.reference = (int)description.get("reference");
		this.maxTimeLoan = (int)description.get("maxTimeLoan");
		this.typeOs = (OS) description.get("typeOS");
	}
	
	@Override
	public boolean equals(Object o){
		if (!(o instanceof SmartPhone)) return false;
		SmartPhone m = (SmartPhone)o;
		if (name!=m.name || brand != m.brand || description != m.description || reference != m.reference || maxTimeLoan != m.maxTimeLoan || typeOs != m.typeOs) return false; 
		return true;
	}
}
