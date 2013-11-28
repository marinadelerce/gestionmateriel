package model.material;

public class SmartPhone extends Device {

	public SmartPhone(String name, String brand, String description,
			int reference, OS type, int maxTimeLoan) {
		super(name, brand, description, reference, type, maxTimeLoan);
	}
	
	@Override
	public Object clone(){
		return new SmartPhone(this.getName(), this.getBrand(), this.getDescription(), this.getReference(), this.getTypeOS(), this.getMaxTimeLoan());
	}
}
