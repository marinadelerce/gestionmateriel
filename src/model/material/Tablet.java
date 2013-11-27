package model.material;

public class Tablet extends Device {

	public Tablet(String name, String brand, String description, int reference,
			OS type, int maxTimeLoan, int serialNumber) {
		super(name, brand, description, reference, type, maxTimeLoan, serialNumber);
	}
	
	@Override
	public Object clone(){
		return new Tablet(this.getName(), this.getBrand(), this.getDescription(), this.getReference(), this.getTypeOS(), this.getMaxTimeLoan(), this.getSerialNumber());
	}
}
