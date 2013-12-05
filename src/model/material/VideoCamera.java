/*
 * 
 */
package model.material;

import java.util.HashMap;

/**
 * The Class VideoCamera.
 * @author Marina Delerce & Romain Guillot 
 * @version 1.0.0
 */
public class VideoCamera extends MaterialType {

	/**
	 * Instantiates a new video camera.
	 *
	 * @param name the name
	 * @param brand the brand
	 * @param description the description
	 * @param reference the reference
	 * @param maxTimeLoan the max time loan
	 */
	public VideoCamera(String name, String brand, String description,
			int reference, int maxTimeLoan) {
		super(name, brand, description, reference, maxTimeLoan);
	}
	
	/**
	 * Instantiates a new video camera.
	 */
	public VideoCamera(){super();}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.getName() + " marque: " + this.getBrand() + "ref: "
				+ this.getReference();
	}

	/* (non-Javadoc)
	 * @see model.material.MaterialType#clone()
	 */
	@Override
	public Object clone(){
		return new VideoCamera(this.getName(), this.getBrand(), this.getDescription(), this.getReference(), this.getMaxTimeLoan());
	}
	
	/* (non-Javadoc)
	 * @see model.material.MaterialType#getSerializableDescription()
	 */
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
	
	/* (non-Javadoc)
	 * @see model.material.MaterialType#setObject(java.util.HashMap)
	 */
	@Override
	public void setObject(HashMap<String, Object> description){
		super.setObject(description);
	}
}
