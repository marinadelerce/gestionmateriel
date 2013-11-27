package model.material;

public class VideoCamera extends Material {

	public VideoCamera(String name, String brand, String description,
			int reference, int maxTimeLoan, int serialNumber) {
		super(name, brand, description, reference, maxTimeLoan, serialNumber);
	}
	
	public String toString() {
		return this.getName() + " marque: " + this.getBrand() + "ref: "
				+ this.getReference();
	}//FIXME même méthode pour videocam et headPhone

	@Override
	public Object clone(){
		return new VideoCamera(this.getName(), this.getBrand(), this.getDescription(), this.getReference(), this.getMaxTimeLoan(), this.getSerialNumber());
	}
}
