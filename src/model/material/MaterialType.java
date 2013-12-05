/*
 * 
 */
package model.material;

import java.util.HashMap;

/**
 * The Class MaterialType.
 * @author Marina Delerce & Romain Guillot 
 * @version 1.0.0
 */
public class MaterialType{

	/** The name. */
	protected String name;
	
	/** The brand. */
	protected String brand;
	
	/** The description. */
	protected String description;
	
	/** The reference. */
	protected int reference;
	
	/** The max time loan. */
	protected int maxTimeLoan;

	/**
	 * Instantiates a new material type.
	 *
	 * @param name the name
	 * @param brand the brand
	 * @param description the description
	 * @param reference the reference
	 * @param maxTimeLoan the max time loan
	 */
	public MaterialType(String name, String brand, String description,
			int reference, int maxTimeLoan) {
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.reference = reference;
		this.maxTimeLoan = maxTimeLoan;
	}

	/**
	 * Instantiates a new material type.
	 */
	public MaterialType() {}

	/**
	 * Sets the object.
	 *
	 * @param description the description
	 */
	public void setObject(HashMap<String, Object> description){}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the brand.
	 *
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	public int getReference() {
		return reference;
	}

	/**
	 * Gets the max time loan.
	 *
	 * @return the max time loan
	 */
	public int getMaxTimeLoan() {
		return maxTimeLoan;
	}

	/**
	 * Sets the max time loan.
	 *
	 * @param maxTimeLoan the new max time loan
	 */
	public void setMaxTimeLoan(int maxTimeLoan) {
		this.maxTimeLoan = maxTimeLoan;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone(){
		return null;
	}
	
	/**
	 * Gets the serializable description.
	 *
	 * @return the serializable description
	 */
	public HashMap<String, Object> getSerializableDescription(){
		return null;
	}
	
	@Override
	public boolean equals(Object o){
		if (!(o instanceof MaterialType)) return false;
		MaterialType m = (MaterialType)o;
		if (name!=m.name || brand != m.brand || description != m.description || reference != m.reference || maxTimeLoan != m.maxTimeLoan) return false; 
		return true;
	}
}
