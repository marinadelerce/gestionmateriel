package model.material;

import java.util.HashMap;

public class HeadPhone extends MaterialType {

	public HeadPhone(String name, String brand, String description,
			int reference, int maxTimeLoan) {
		super(name, brand, description, reference, maxTimeLoan);
	}
	
	public HeadPhone(){super();}
	
	public String toString() {
		return this.getName() + " marque: " + this.getBrand() + "ref: "
				+ this.getReference();
	}
	
	@Override
	public Object clone(){
		return new HeadPhone(this.getName(), this.getBrand(), this.getDescription(), this.getReference(), this.getMaxTimeLoan());
	}
	
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
	
	@Override
	public void setObject(HashMap<String, Object> description){
		super.setObject(description);
	}
}
