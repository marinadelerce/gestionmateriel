package controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.material.Material;
import model.user.Borrower;
import model.user.Manager;
import model.user.Student;
import model.user.Teacher;
import model.user.User;
import model.user.Users;

public class UserManager {
	private ArrayList<User> users;
	private User connectedUser;
	private GregorianCalendar currentDate;
	private MaterialManager manager;
	private final static int conversion = 86400000;

	public UserManager() {
		this.users = new ArrayList<User>();
		this.connectedUser = null;
	}

	public boolean addNewUser(String userType, String lastname, String firstname,
			String login, String password) {
		if (!checkSameUser(login)) {
			switch (userType) {
			case "Manager":
				this.users.add(new Manager(lastname, firstname, login, password));
				break;
			case "Teacher":
				this.users.add(new Teacher(lastname, firstname, login, password));
				break;
			case "Student":
				this.users.add(new Student(lastname, firstname, login, password));
				break;
			default:

			}
			return true;
		} else
			return false;
	}

	private boolean checkSameUser(String login) {
		for(User user: users){
			if(user.getLogin().equals(login))
				return true;
		}
		return false;
	}

	public boolean book(Borrower borrower, Material material, GregorianCalendar startDate, GregorianCalendar endDate, int quantity){
		boolean book = true;
		int duration;
		// verifier les dates
		if (startDate.after(endDate) || startDate.equals(endDate))
			book = false;
		// verifier les durees
		else {
			duration = startDate.compareTo(endDate) / conversion;
			if (duration > borrower.getLoanDuration())
				book = false;
		}

		return book;
	}

	/*
	 * public Users getUsers() { return users; }
	 * 
	 * public void setUsers(Users users) { this.users = users; }
	 */

	public MaterialManager getManager() {
		return manager;
	}

	public void setManager(MaterialManager manager) {
		this.manager = manager;
	}

	public boolean checkUserPassword(String login, String password) {
		for (User user : users) {
			if ((user.getLogin().equals(login))
					&& (user.getPassword().equals(password))) {
				connectedUser = user;
				return true;
			}
		}
		return false;
	}

	public User getConnectedUser() {
		return this.connectedUser;
	}

	public void signOff() {
		connectedUser = null;
		
	}
}
