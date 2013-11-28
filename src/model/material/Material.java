package model.material;

public class Material {

	private MaterialType material_type;
	private String serial_number;
	
	public Material(MaterialType type, String serial){
		material_type = type;
		serial_number = serial;
	}
	
	public MaterialType getMaterialType(){
		return material_type;
	}
	
	public String getSerialNumber(){
		return serial_number;
	}
}
