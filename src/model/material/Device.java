/*
 * 
 */
package model.material;

import java.util.HashMap;

/**
 * The Class Device.
 * @author Marina Delerce & Romain Guillot 
 * @version 1.0.0
 */
public abstract class Device extends MaterialType {

	/** The type os. */
	private OS typeOs;

	/**
	 * Instantiates a new device.
	 *
	 * @param name the name
	 * @param brand the brand
	 * @param description the description
	 * @param reference the reference
	 * @param type the type
	 * @param maxTimeLoan the max time loan
	 */
	public Device(String name, String brand, String description, int reference,
			OS type, int maxTimeLoan) {
		super(name, brand, description, reference, maxTimeLoan);
		typeOs = type;
	}

	/**
	 * Instantiates a new device.
	 */
	public Device() {super();}

	/**
	 * Gets the type os.
	 *
	 * @return the type os
	 */
	public OS getTypeOS() {
		return typeOs;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.getName() + " marque: " + this.getBrand() + "ref: "
				+ this.getReference() + "OS: " + this.getTypeOS();
	}
	
	/* (non-Javadoc)
	 * @see model.material.MaterialType#getSerializableDescription()
	 */
	@Override
	public HashMap<String, Object> getSerializableDescription(){
		return null;
	}
	
	/* (non-Javadoc)
	 * @see model.material.MaterialType#setObject(java.util.HashMap)
	 */
	@Override
	public void setObject(HashMap<String, Object> description){
		typeOs = (OS)description.get("typeOS");
		description.remove("typeOS");
		super.setObject(description);
	}
}
