package model.user;

public class Student extends Borrower{

	private final static int loanDuration = 5;
	
	public Student(String name, String firstname, String login, String password){
		super(name, firstname, login,password, loanDuration);
	}
	
	
}
