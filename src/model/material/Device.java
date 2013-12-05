package model.material;

import java.util.HashMap;

public abstract class Device extends MaterialType {

	private OS typeOs;

	public Device(String name, String brand, String description, int reference,
			OS type, int maxTimeLoan) {
		super(name, brand, description, reference, maxTimeLoan);
		typeOs = type;
	}

	public Device() {super();}

	public OS getTypeOS() {
		return typeOs;
	}

	public String toString() {
		return this.getName() + " marque: " + this.getBrand() + "ref: "
				+ this.getReference() + "OS: " + this.getTypeOS();
	}
	
	@Override
	public HashMap<String, Object> getSerializableDescription(){
		return null;
	}
	
	@Override
	public void setObject(HashMap<String, Object> description){
		typeOs = (OS)description.get("typeOS");
		description.remove("typeOS");
		super.setObject(description);
	}
}
