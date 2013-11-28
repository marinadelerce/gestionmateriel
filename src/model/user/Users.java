package model.user;

import java.util.LinkedList;
import java.util.List;

public class Users {

	private List<User> users;
	private final String SAVE_FILE = "";
	
	public Users(){
		users = new LinkedList<User>();
	}
	
	public void add(User user){
		users.add(user);
	}
	
	public void remove(User user){
		users.remove(user);
	}
	
	public boolean checkSameUser(String login) {
		for (User user : users) {
			if (user.getLogin().equals(login))
				return true;
		}
		return false;
	}
	
	public User checkUserPassword(String login, String password) {
		for (User user : users) {
			if ((user.getLogin().equals(login))
					&& (user.getPassword().equals(password))) {
				return user;
			}
		}
		return null;
	}
	
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
	
	public void save(){
		// save users in the save file
	}
}
