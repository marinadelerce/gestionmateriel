package model.user;

import java.util.HashMap;

public abstract class Borrower extends User{

	private static int loanDuration;
	
	public Borrower(String name, String firstname, String login, String password, int loanDuration){
		super(name, firstname, login, password);
		setLoanDuration(loanDuration);
	}

	public Borrower(){super();}
	
	public int getLoanDuration() {
		return loanDuration;
	}

	public static void setLoanDuration(int loanDuration) {
		Borrower.loanDuration = loanDuration;
	}
	
	@Override
	public String toString(){
		return "User: " + this.getFirstname() + " " + this.getName() + "id: " + this.getLogin();
	}
	
	@Override
	public HashMap<String, Object> getSerializableDescription(){
		return null;
	}
	
	@Override
	public void setObject(HashMap<String, Object> description){
	}
}
