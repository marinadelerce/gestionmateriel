/*
 * 
 */
package model.loan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import utils.DateUtils;
import model.material.Material;
import model.user.Borrower;
import model.user.Manager;
import model.user.User;

/**
 * The Class Loan.
 * 
 * @author Marina Delerce & Romain Guillot
 * @version 1.0.0
 */
public class Loan {

	/** The loan id. */
	public static int LOAN_ID = 0;

	/** The id. */
	private int id;

	/** The start date. */
	private GregorianCalendar startDate;

	/** The end date. */
	private GregorianCalendar endDate;

	/** The user. */
	private User user;

	/** The materials. */
	private List<Material> materials;

	/** The effective. */
	// private boolean effective;

	/** The validate. */
	private boolean validate;

	/**
	 * Instantiates a new loan.
	 * 
	 * @param user
	 *            the user
	 * @param materials
	 *            the materials
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @param effective
	 *            the effective
	 * @param validate
	 *            the validate
	 */
	private Loan(User user, List<Material> materials,
			GregorianCalendar startDate, GregorianCalendar endDate,
			boolean effective, boolean validate) {
		this.id = LOAN_ID;
		LOAN_ID = LOAN_ID + 1;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
		this.materials = materials;
		// this.effective = effective;
		this.validate = validate;
	}

	/**
	 * Instantiates a new loan.
	 * 
	 * @param borrower
	 *            the borrower
	 * @param materials
	 *            the materials
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 */
	public Loan(Borrower borrower, List<Material> materials,
			GregorianCalendar startDate, GregorianCalendar endDate) {
		this(borrower, materials, startDate, endDate, false, false);
	}

	/**
	 * Instantiates a new loan.
	 * 
	 * @param manager
	 *            the manager
	 * @param materials
	 *            the materials
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 */
	public Loan(Manager manager, List<Material> materials,
			GregorianCalendar startDate, GregorianCalendar endDate) {
		this(manager, materials, startDate, endDate, false, true);
	}

	/**
	 * Instantiates a new loan.
	 * 
	 * @param loanDescription
	 *            the loan description
	 */
	public Loan(HashMap<String, Object> loanDescription) {

		this.id = (int) loanDescription.get("id");
		// this.effective = (boolean) loanDescription.get("effective");
		this.validate = (boolean) loanDescription.get("validate");

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		try {
			Date start = format
					.parse((String) loanDescription.get("startDate"));
			startDate = new GregorianCalendar();
			startDate.setTime(start);

			Date end = format.parse((String) loanDescription.get("startDate"));
			endDate = new GregorianCalendar();
			endDate.setTime(end);
		} catch (Exception e) {
		}

		HashMap<String, Object> userDescription = (HashMap<String, Object>) loanDescription
				.get("user");
		user = (User) createObject((String) userDescription.get("className"));
		user.setObject(userDescription);

		// description de la liste de materiels
		List<HashMap<String, Object>> materialDescriptions = (List<HashMap<String, Object>>) loanDescription
				.get("materials");
		materials = new LinkedList<Material>();
		for (HashMap<String, Object> material : materialDescriptions) {
			materials.add(new Material(material));
		}

	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the start date.
	 * 
	 * @return the start date
	 */
	public GregorianCalendar getStartDate() {
		return startDate;
	}

	/**
	 * Gets the end date.
	 * 
	 * @return the end date
	 */
	public GregorianCalendar getEndDate() {
		return endDate;
	}

	/**
	 * Gets the user.
	 * 
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Gets the materials.
	 * 
	 * @return the materials
	 */
	public List<Material> getMaterials() {
		return materials;
	}

	/**
	 * Checks if is effective.
	 * 
	 * @return true, if is effective
	 */
	/*
	 * public boolean isEffective() { return effective; }
	 */

	/**
	 * Checks if is validate.
	 * 
	 * @return true, if is validate
	 */
	public boolean isValidate() {
		return validate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate
	 *            the new start date
	 */
	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate
	 *            the new end date
	 */
	public void setEndDate(GregorianCalendar endDate) {
		this.endDate = endDate;
	}

	/**
	 * Sets the user.
	 * 
	 * @param user
	 *            the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Sets the materials.
	 * 
	 * @param materials
	 *            the new materials
	 */
	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	/**
	 * Sets the effective.
	 * 
	 * @param effective
	 *            the new effective
	 */
	/*
	 * public void setEffective(boolean effective) { this.effective = effective;
	 * }
	 */

	/**
	 * Sets the validate.
	 * 
	 * @param validate
	 *            the new validate
	 */
	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	/**
	 * Gets the serializable description.
	 * 
	 * @return the serializable description
	 */
	public HashMap<String, Object> getSerializableDescription() {

		HashMap<String, Object> loanDescription = new HashMap<String, Object>();

		loanDescription.put("id", id);
		loanDescription.put("startDate", startDate.DAY_OF_MONTH + "/"
				+ startDate.MONTH + "/" + startDate.YEAR);
		loanDescription.put("endDate", endDate.DAY_OF_MONTH + "/"
				+ endDate.MONTH + "/" + endDate.YEAR);
		loanDescription.put("user", user.getSerializableDescription());
		// loanDescription.put("effective", effective);
		loanDescription.put("validate", validate);

		// description de la liste de materiels
		List<HashMap<String, Object>> materialDescriptions = new LinkedList<HashMap<String, Object>>();
		for (Material material : materials) {
			materialDescriptions.add(material.getSerializableDescription());
		}

		loanDescription.put("materials", materialDescriptions);
		return loanDescription;

	}

	/**
	 * Creates the object.
	 * 
	 * @param className
	 *            the class name
	 * @return the object
	 */
	static Object createObject(String className) {

		Object object = null;
		try {
			Class<?> classDefinition = Class.forName(className);
			object = classDefinition.newInstance();
		} catch (InstantiationException e) {
			System.out.println(e);
		} catch (IllegalAccessException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		return object;
	}

	@Override
	public String toString() {
		return "Id: " + this.getId() + " Date de début: "
				+ DateUtils.dateToString(this.getStartDate())
				+ " Date de fin: " + DateUtils.dateToString(this.getEndDate());
	}
}
