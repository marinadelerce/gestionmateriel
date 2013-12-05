/*
 * 
 */
package model.user;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import xml.ConfigXML;

/**
 * The Class Users.
 * @author Marina Delerce & Romain Guillot 
 * @version 1.0.0
 */
public class Users {

	/** The users. */
	private List<User> users;
	
	/** The save file path. */
	private final String SAVE_FILE_PATH = "users";
	
	/** The save file version. */
	private final String SAVE_FILE_VERSION = "1.0.0";
	
	/**
	 * Instantiates a new users.
	 */
	public Users(){
		users = new LinkedList<User>();
	}
	
	/**
	 * Adds a user
	 *
	 * @param user the user
	 */
	public void add(User user){
		users.add(user);
	}
	
	/**
	 * Removes a user
	 *
	 * @param user the user
	 */
	public void remove(User user){
		users.remove(user);
	}
	
	/**
	 * Check same user.
	 *
	 * @param login the login
	 * @return true, if successful
	 */
	public boolean checkSameUser(String login) {
		for (User user : users) {
			if (user.getLogin().equals(login))
				return true;
		}
		return false;
	}
	
	/**
	 * Check user password.
	 *
	 * @param login the login
	 * @param password the password
	 * @return the user
	 */
	public User checkUserPassword(String login, String password) {
		for (User user : users) {
			if ((user.getLogin().equals(login))
					&& (user.getPassword().equals(password))) {
				return user;
			}
		}
		return null;
	}
	
	/**
	 * Delete user.
	 *
	 * @param lastname the lastname
	 * @param firstname the firstname
	 * @param login the login
	 * @return true, if successful
	 */
	public boolean deleteUser(String lastname, String firstname, String login) {
		User deletedUser = null;
		boolean isDeleted = true;
		for(User user : users){
			if (user.getName().equals(lastname) && user.getFirstname().equals(firstname) && user.getLogin().equals(login))
				deletedUser = user;
		}
		if (deletedUser != null) 
			this.users.remove(deletedUser);
		else  
			isDeleted = false;
		
		return isDeleted;
	}
	
	/**
	 * Load.
	 */
	public void load(){
		List<HashMap<String, Object>> listUsers = (List<HashMap<String, Object>>) ConfigXML.load(SAVE_FILE_PATH, SAVE_FILE_VERSION);
		for (HashMap<String, Object> descriptionUser : listUsers){
			User user = (User) createObject((String) descriptionUser.get("className"));
			user.setObject(descriptionUser);
			users.add(user);
		}
		System.out.println("users loaded");
	}
	
	/**
	 * Save.
	 */
	public void save(){
		
		// save users in the save file
		ConfigXML.store(getSerializableDescription(), SAVE_FILE_PATH, SAVE_FILE_VERSION);
	}
	
	/**
	 * Gets the serializable description.
	 *
	 * @return the serializable description
	 */
	public List<HashMap<String, Object>> getSerializableDescription(){
		
		List<HashMap<String, Object>> usersDescription = new LinkedList<HashMap<String, Object>>();
		
		for (User user : users){
			usersDescription.add(user.getSerializableDescription());
		}
		
		return usersDescription;
	}
	
	/**
	 * Creates the object.
	 *
	 * @param className the class name
	 * @return the object
	 */
	static Object createObject(String className) {
		 
		Object object = null;
		try {
		    Class<?> classDefinition = Class.forName(className);
		    object = classDefinition.newInstance();
	    } catch (InstantiationException e) {
		          System.out.println(e);
		} catch (IllegalAccessException e) {
		          System.out.println(e);
		} catch (ClassNotFoundException e) {
		          System.out.println(e);
		}
		return object;
	}
}
