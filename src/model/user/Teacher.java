package model.user;

public class Teacher extends Borrower{
	
	private final static int loanDuration = 24;
	
	public Teacher(String name, String firstname, int id){
		super(name, firstname, id,loanDuration);
	}

}
