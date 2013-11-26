package model.material;

public class HeadPhone extends MaterialType {

	public HeadPhone(String name, String brand, String description,
			int reference, int maxTimeLoan, int serialNumber) {
		super(name, brand, description, reference, maxTimeLoan, serialNumber);
	}
	
	public String toString() {
		return this.getName() + " marque: " + this.getBrand() + "ref: "
				+ this.getReference();
	} //FIXME m�me methode pour videoCam et headPhones
}
