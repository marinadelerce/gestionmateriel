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
	private String name;
	
	/** The brand. */
	private String brand;
	
	/** The description. */
	private String description;
	
	/** The reference. */
	private int reference;
	
	/** The max time loan. */
	private int maxTimeLoan;

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
	public void setObject(HashMap<String, Object> description){
		this.name = (String)description.get("name");
		this.brand = (String)description.get("brand");
		this.description = (String)description.get("description");
		this.reference = (int)description.get("reference");
		this.maxTimeLoan = (int)description.get("maxTimeLoan");
	}
	
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
}
