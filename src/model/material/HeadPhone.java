package model.material;

public class HeadPhone extends Material {

	public HeadPhone(String name, String brand, String description,
			int reference, int maxTimeLoan, int serialNumber) {
		super(name, brand, description, reference, maxTimeLoan, serialNumber);
	}
	
	public String toString() {
		return this.getName() + " marque: " + this.getBrand() + "ref: "
				+ this.getReference();
	} //FIXME même methode pour videoCam et headPhones
	
	@Override
	public Object clone(){
		return new HeadPhone(this.getName(), this.getBrand(), this.getDescription(), this.getReference(), this.getMaxTimeLoan(), this.getSerialNumber());
	}
}
