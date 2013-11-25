package model.user;

public abstract class Borrower extends User{

	private static int loanDuration;
	
	public Borrower(String name, String firstname, int id, int loanDuration){
		super(name, firstname, id);
		setLoanDuration(loanDuration);
	}

	public int getLoanDuration() {
		return loanDuration;
	}

	public static void setLoanDuration(int loanDuration) {
		Borrower.loanDuration = loanDuration;
	}
	
}
