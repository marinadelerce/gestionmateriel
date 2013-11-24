package model.materiel;

public abstract class MaterielOS extends Materiel{

	private OS typeOs;
	
	public MaterielOS(String nom, String marque, String description, int reference, OS type){
		super(nom, marque, description, reference);
		typeOs = type;
	}
	
	public OS getTypeOS(){
		return typeOs;
	}
}
