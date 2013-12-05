package model.manager;

import java.util.ArrayList;
import java.util.GregorianCalendar;



import model.loan.Loan;
import model.material.MaterialType;
import model.user.Borrower;
import model.user.Manager;
import model.user.Student;
import model.user.Teacher;
import model.user.User;
import model.user.Users;

public class UserManager {
	
	private Users users;
	private User connectedUser;
	private Manager manager;
	private GregorianCalendar currentDate;
	private final static int conversion = 86400000;

	public UserManager() {
		this.users = new Users();
		this.connectedUser = null;
	}

	public boolean addNewUser(String userType, String lastname,
			String firstname, String login, String password) {
		if (!checkSameUser(login)) {
			switch (userType) {
			case "Manager":
				this.users
						.add(new Manager(lastname, firstname, login, password));
				break;
			case "Teacher":
				this.users
						.add(new Teacher(lastname, firstname, login, password));
				break;
			case "Student":
				this.users
						.add(new Student(lastname, firstname, login, password));
				break;
			default:

			}
			return true;
		} else
			return false;
	}

	private boolean checkSameUser(String login) {
		return users.checkSameUser(login);
	}

	public boolean checkUserPassword(String login, String password) {
		if ((connectedUser = users.checkUserPassword(login, password))!=null) return true;
		else return false;
	}

	public User getConnectedUser() {
		return this.connectedUser;
	}

	public void signOff() {
		connectedUser = null;

	}

	public boolean deleteUser(String lastname, String firstname, String login) {
		return users.deleteUser(lastname, firstname, login);
	}
	
	public void save(){
		users.save();
	}
	
	public void load(){
		users.load();
	}

	public boolean canBook(User connectedUser, GregorianCalendar startDate,
			GregorianCalendar endDate) {
		if(connectedUser instanceof Manager){
			return true;
		}
		
		if(connectedUser instanceof Borrower){
			Borrower borrower = (Borrower) connectedUser;
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
		
		return false;
	}
}
