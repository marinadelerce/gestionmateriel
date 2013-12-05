package model.material;

import java.util.HashMap;

public class SmartPhone extends Device {

	public SmartPhone(String name, String brand, String description,
			int reference, OS type, int maxTimeLoan) {
		super(name, brand, description, reference, type, maxTimeLoan);
	}
	
	public SmartPhone() {super();}

	@Override
	public Object clone(){
		return new SmartPhone(this.getName(), this.getBrand(), this.getDescription(), this.getReference(), this.getTypeOS(), this.getMaxTimeLoan());
	}
	
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
	
	@Override
	public void setObject(HashMap<String, Object> description){
		super.setObject(description);
	}
}
