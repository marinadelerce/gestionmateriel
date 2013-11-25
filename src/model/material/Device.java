package model.material;

public abstract class Device extends MaterialType{

	private OS typeOs;
	
	public Device(String name, String brand, String description, int reference, OS type){
		super(name, brand, description, reference);
		typeOs = type;
	}
	
	public OS getTypeOS(){
		return typeOs;
	}
}
