package model.user;

public abstract class Borrower extends User{

	private static int loanDuration;
	
	public Borrower(String name, String firstname, String login, String password, int loanDuration){
		super(name, firstname, login, password);
		setLoanDuration(loanDuration);
	}

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
	
}
