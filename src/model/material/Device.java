package model.material;

public abstract class Device extends MaterialType{

	private OS typeOs;
	
	public Device(String name, String brand, String description, int reference, OS type, int maxTimeLoan){
		super(name, brand, description, reference, maxTimeLoan);
		typeOs = type;
	}
	
	public OS getTypeOS(){
		return typeOs;
	}
}
