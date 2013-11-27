package model.user;

public abstract class User {
	
	protected String lastname;
	protected String firstname;
	protected String login;
	protected String password;
	
	
	public User(String name, String firstname, String login, String password){
		this.lastname = name;
		this.firstname = firstname;
		this.login = login;
		this.password = password;
	}
	
	public String getName(){
		return lastname;
	}
	
	public String getFirstname(){
		return firstname;
	}
	
	public String getLogin(){
		return login;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
