/*
 * 
 */
package model.user;

import java.util.HashMap;

/**
 * The Class User.
 * @author Marina Delerce & Romain Guillot 
 * @version 1.0.0
 */
public abstract class User {
	
	/** The lastname. */
	protected String lastname;
	
	/** The firstname. */
	protected String firstname;
	
	/** The login. */
	protected String login;
	
	/** The password. */
	protected String password;
	
	
	/**
	 * Instantiates a new user.
	 *
	 * @param name the name
	 * @param firstname the firstname
	 * @param login the login
	 * @param password the password
	 */
	public User(String name, String firstname, String login, String password){
		this.lastname = name;
		this.firstname = firstname;
		this.login = login;
		this.password = password;
	}
	
	/**
	 * Instantiates a new user.
	 */
	public User() {}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return lastname;
	}
	
	/**
	 * Gets the firstname.
	 *
	 * @return the firstname
	 */
	public String getFirstname(){
		return firstname;
	}
	
	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin(){
		return login;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Sets the object.
	 *
	 * @param description the description
	 */
	public void setObject(HashMap<String, Object> description){
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return lastname + " " + firstname;
	}
	
	/**
	 * Gets the serializable description.
	 *
	 * @return the serializable description
	 */
	public HashMap<String, Object> getSerializableDescription(){
		return null;
	}
	
}
