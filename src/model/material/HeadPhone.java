/*
 * 
 */
package model.material;

import java.util.HashMap;

/**
 * The Class HeadPhone.
 * @author Marina Delerce & Romain Guillot 
 * @version 1.0.0
 */
public class HeadPhone extends MaterialType {

	/**
	 * Instantiates a new head phone.
	 *
	 * @param name the name
	 * @param brand the brand
	 * @param description the description
	 * @param reference the reference
	 * @param maxTimeLoan the max time loan
	 */
	public HeadPhone(String name, String brand, String description,
			int reference, int maxTimeLoan) {
		super(name, brand, description, reference, maxTimeLoan);
	}
	
	/**
	 * Instantiates a new head phone.
	 */
	public HeadPhone(){super();}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.getName() + " marque: " + this.getBrand() + "ref: "
				+ this.getReference();
	}
	
	/* (non-Javadoc)
	 * @see model.material.MaterialType#clone()
	 */
	@Override
	public Object clone(){
		return new HeadPhone(this.getName(), this.getBrand(), this.getDescription(), this.getReference(), this.getMaxTimeLoan());
	}
	
	/* (non-Javadoc)
	 * @see model.material.MaterialType#getSerializableDescription()
	 */
	@Override
	public HashMap<String, Object> getSerializableDescription(){
		
		HashMap<String, Object> headphoneDescription = new HashMap<String, Object>();
		headphoneDescription.put("name", getName());
		headphoneDescription.put("brand", getBrand());
		headphoneDescription.put("description", getDescription());
		headphoneDescription.put("reference", getReference());
		headphoneDescription.put("maxTimeLoan", getMaxTimeLoan());
		headphoneDescription.put("className", this.getClass().getName());
		
		return headphoneDescription;
	}
	
	/* (non-Javadoc)
	 * @see model.material.MaterialType#setObject(java.util.HashMap)
	 */
	@Override
	public void setObject(HashMap<String, Object> description){
		super.setObject(description);
	}
}
