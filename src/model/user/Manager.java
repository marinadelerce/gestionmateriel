package model.user;

import java.util.HashMap;

public class Manager extends User {

	public Manager(String name, String firstname, String login, String password) {
		super(name, firstname, login, password);
	}

	public Manager(HashMap<String, Object> description){
		super((String)description.get("name"), (String)description.get("firstname"), (String)description.get("login"), (String)description.get("password"));
	}
	
	public Manager(){super();}
	
	@Override
	public String toString(){
		return "Admin: " + this.getFirstname() + " " + this.getName() + "id: " + this.getLogin();
	}
	
	
	@Override
	public HashMap<String, Object> getSerializableDescription(){
		
		HashMap<String, Object> userDescription = new HashMap<String, Object>();
		userDescription.put("name", getName());
		userDescription.put("firstname", getFirstname());
		userDescription.put("login", getLogin());
		userDescription.put("password", getPassword());
		userDescription.put("className", this.getClass().getName());
		
		return userDescription;
	}

	@Override
	public void setObject(HashMap<String, Object> description){
		this.lastname = (String) description.get("name");
		this.firstname = (String) description.get("firstname");
		this.login = (String) description.get("login");
		this.password = (String) description.get("password");
	}
}
