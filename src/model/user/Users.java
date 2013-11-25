package model.user;

import java.util.LinkedList;
import java.util.List;

public class Users {

	private List<User> users;
	
	public Users(){
		users = new LinkedList<User>();
	}
	
	public void add(User user){
		users.add(user);
	}
	
	public void remove(User user){
		users.remove(user);
	}
	
}
