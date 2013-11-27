package model.user;

public class Teacher extends Borrower{
	
	private final static int loanDuration = 15;
	
	public Teacher(String name, String firstname, String login, String password){
		super(name, firstname, login,password, loanDuration);
	}

}
