package model.material;

import java.util.HashMap;

public class MaterialType{

	private String name;
	private String brand;
	private String description;
	private int reference;
	private int maxTimeLoan;

	public MaterialType(String name, String brand, String description,
			int reference, int maxTimeLoan) {
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.reference = reference;
		this.maxTimeLoan = maxTimeLoan;
	}

	public MaterialType() {
	}

	public void setObject(HashMap<String, Object> description){
		this.name = (String)description.get("name");
		this.brand = (String)description.get("brand");
		this.description = (String)description.get("description");
		this.reference = (int)description.get("reference");
		this.maxTimeLoan = (int)description.get("maxTimeLoan");
	}
	
	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public String getDescription() {
		return description;
	}

	public int getReference() {
		return reference;
	}

	public int getMaxTimeLoan() {
		return maxTimeLoan;
	}

	public void setMaxTimeLoan(int maxTimeLoan) {
		this.maxTimeLoan = maxTimeLoan;
	}
	
	@Override
	public Object clone(){
		return null;
	}
	
	public HashMap<String, Object> getSerializableDescription(){
		return null;
	}
}
