package model.material;

import java.util.HashMap;

public class Tablet extends Device {

	public Tablet(String name, String brand, String description, int reference,
			OS type, int maxTimeLoan) {
		super(name, brand, description, reference, type, maxTimeLoan);
	}
	
	public Tablet(HashMap<String, Object> description){
		super((String)description.get("name"), (String)description.get("brand"), (String)description.get("description"), (int)description.get("reference"), (OS)description.get("typeOS"), (int)description.get("maxTimeLoan"));
	}
	
	public Tablet(){super();}
	
	@Override
	public Object clone(){
		return new Tablet(this.getName(), this.getBrand(), this.getDescription(), this.getReference(), this.getTypeOS(), this.getMaxTimeLoan());
	}
	
	@Override
	public HashMap<String, Object> getSerializableDescription(){

		HashMap<String, Object> tabletDescription = new HashMap<String, Object>();
		tabletDescription.put("name", getName());
		tabletDescription.put("brand", getBrand());
		tabletDescription.put("description", getDescription());
		tabletDescription.put("typeOS", getTypeOS());
		tabletDescription.put("reference", getReference());
		tabletDescription.put("maxTimeLoan", getMaxTimeLoan());
		tabletDescription.put("className", this.getClass().getName());
		
		return tabletDescription;
	}
	
	@Override
	public void setObject(HashMap<String, Object> description){
		super.setObject(description);
	}
}
