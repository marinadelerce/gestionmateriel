package model.user;

public abstract class User {
	
	protected String name;
	protected String firstname;
	protected int id;
	
	public User(String name, String firstname, int id){
		this.name = name;
		this.firstname = firstname;
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getFirstname(){
		return firstname;
	}
	
	public int getId(){
		return id;
	}
}
