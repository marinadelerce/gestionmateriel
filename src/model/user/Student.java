package model.user;

import java.util.HashMap;

public class Student extends Borrower{

	private final static int loanDuration = 5;
	
	public Student(String name, String firstname, String login, String password){
		super(name, firstname, login,password, loanDuration);
	}
	
	public Student(HashMap<String, Object> description){
		super((String)description.get("name"), (String)description.get("firstname"), (String)description.get("login"), (String)description.get("password"), loanDuration);
	}
	
	public Student(){super();}
	
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
