package model.material;

public abstract class MaterialType {

	private String name;
	private String brand;
	private String description;
	private int reference;
	private int maxTimeLoan;
	
	public MaterialType(String name, String brand, String description, int reference, int maxTimeLoan){
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.reference = reference;
		this.maxTimeLoan = maxTimeLoan;
	}
	
	public String getName(){
		return name;
	}
	
	public String getBrand(){
		return brand;
	}
	
	public String getDescription(){
		return description;
	}
	
	public int getReference(){
		return reference;
	}

	public int getMaxTimeLoan() {
		return maxTimeLoan;
	}

	public void setMaxTimeLoan(int maxTimeLoan) {
		this.maxTimeLoan = maxTimeLoan;
	}
}
