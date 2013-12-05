/*
 * 
 */
package model.user;

import java.util.HashMap;

/**
 * The Class Borrower.
 * @author Marina Delerce & Romain Guillot 
 * @version 1.0.0
 */
public abstract class Borrower extends User{

	/** The loan duration. */
	private static int loanDuration;
	
	/**
	 * Instantiates a new borrower.
	 *
	 * @param name the name
	 * @param firstname the firstname
	 * @param login the login
	 * @param password the password
	 * @param loanDuration the loan duration
	 */
	public Borrower(String name, String firstname, String login, String password, int loanDuration){
		super(name, firstname, login, password);
		setLoanDuration(loanDuration);
	}

	/**
	 * Instantiates a new borrower.
	 */
	public Borrower(){super();}
	
	/**
	 * Gets the loan duration.
	 *
	 * @return the loan duration
	 */
	public int getLoanDuration() {
		return loanDuration;
	}

	/**
	 * Sets the loan duration.
	 *
	 * @param loanDuration the new loan duration
	 */
	public static void setLoanDuration(int loanDuration) {
		Borrower.loanDuration = loanDuration;
	}
	
	/* (non-Javadoc)
	 * @see model.user.User#toString()
	 */
	@Override
	public String toString(){
		return "User: " + this.getFirstname() + " " + this.getName() + "id: " + this.getLogin();
	}
	
	/* (non-Javadoc)
	 * @see model.user.User#getSerializableDescription()
	 */
	@Override
	public HashMap<String, Object> getSerializableDescription(){
		return null;
	}
	
	/* (non-Javadoc)
	 * @see model.user.User#setObject(java.util.HashMap)
	 */
	@Override
	public void setObject(HashMap<String, Object> description){
	}
}
