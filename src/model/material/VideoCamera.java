package model.material;

public class VideoCamera extends MaterialType {

	public VideoCamera(String name, String brand, String description,
			int reference, int maxTimeLoan, int serialNumber) {
		super(name, brand, description, reference, maxTimeLoan, serialNumber);
	}
	
	public String toString() {
		return this.getName() + " marque: " + this.getBrand() + "ref: "
				+ this.getReference();
	}//FIXME même méthode pour videocam et headPhone

}
