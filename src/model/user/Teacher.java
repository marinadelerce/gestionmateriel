/*
 * 
 */
package model.user;

import java.util.HashMap;

/**
 * The Class Teacher.
 * @author Marina Delerce & Romain Guillot 
 * @version 1.0.0
 */
public class Teacher extends Borrower{
	
	/** The Constant loanDuration. */
	private final static int loanDuration = 15;
	
	/**
	 * Instantiates a new teacher.
	 *
	 * @param name the name
	 * @param firstname the firstname
	 * @param login the login
	 * @param password the password
	 */
	public Teacher(String name, String firstname, String login, String password){
		super(name, firstname, login,password, loanDuration);
	}
	
	/**
	 * Instantiates a new teacher.
	 */
	public Teacher(){super();}
	
	/* (non-Javadoc)
	 * @see model.user.Borrower#getSerializableDescription()
	 */
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
	
	/* (non-Javadoc)
	 * @see model.user.Borrower#setObject(java.util.HashMap)
	 */
	@Override
	public void setObject(HashMap<String, Object> description){
		this.lastname = (String) description.get("name");
		this.firstname = (String) description.get("firstname");
		this.login = (String) description.get("login");
		this.password = (String) description.get("password");
	}
}
