package model.material;

import java.util.HashMap;

public class VideoCamera extends MaterialType {

	public VideoCamera(String name, String brand, String description,
			int reference, int maxTimeLoan) {
		super(name, brand, description, reference, maxTimeLoan);
	}
	
	public VideoCamera(HashMap<String, Object> description){
		super((String)description.get("name"), (String)description.get("brand"), (String)description.get("description"), (int)description.get("reference"), (int)description.get("maxTimeLoan"));
	}
	
	public VideoCamera(){super();}
	
	public String toString() {
		return this.getName() + " marque: " + this.getBrand() + "ref: "
				+ this.getReference();
	}

	@Override
	public Object clone(){
		return new VideoCamera(this.getName(), this.getBrand(), this.getDescription(), this.getReference(), this.getMaxTimeLoan());
	}
	
	@Override
	public HashMap<String, Object> getSerializableDescription(){
		
		HashMap<String, Object> videocameraDescription = new HashMap<String, Object>();
		videocameraDescription.put("name", getName());
		videocameraDescription.put("brand", getBrand());
		videocameraDescription.put("description", getDescription());
		videocameraDescription.put("reference", getReference());
		videocameraDescription.put("maxTimeLoan", getMaxTimeLoan());
		videocameraDescription.put("className", this.getClass().getName());
		
		return videocameraDescription;
	}
	
	@Override
	public void setObject(HashMap<String, Object> description){
		super.setObject(description);
	}
}
