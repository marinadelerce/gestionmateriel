package model.loan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import model.material.Material;
import model.material.MaterialType;
import model.user.Borrower;
import model.user.Manager;
import model.user.User;

public class Loan {
	
	public static int LOAN_ID = 0;
	private int id;
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private User user;
	private List<Material> materials;
	private boolean effective;
	private boolean validate;

	private Loan(User user, List<Material> materials,
			GregorianCalendar startDate, GregorianCalendar endDate,
			boolean effective, boolean validate) {
		this.id = LOAN_ID;
		LOAN_ID = LOAN_ID + 1;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
		this.materials = materials;
		this.effective = effective;
		this.validate = validate;
	}
	
	public Loan(Borrower borrower, List<Material> materials,
			GregorianCalendar startDate, GregorianCalendar endDate) {
		this(borrower, materials, startDate, endDate, false, false);
	}
	
	public Loan(Manager manager, List<Material> materials,
			GregorianCalendar startDate, GregorianCalendar endDate) {
		this(manager, materials, startDate, endDate, false, true);
	}

	public Loan(HashMap<String, Object> loanDescription){
		
		this.id = (int) loanDescription.get("id");
		this.effective = (boolean) loanDescription.get("effective");
		this.validate = (boolean) loanDescription.get("validate");
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			Date start = format.parse((String) loanDescription.get("startDate"));
			startDate = new GregorianCalendar();
			startDate.setTime(start);
			
			Date end = format.parse((String) loanDescription.get("startDate"));
			endDate = new GregorianCalendar();
			endDate.setTime(end);
		} catch (Exception e){}
		
		HashMap<String, Object> userDescription = (HashMap<String, Object>) loanDescription.get("user");
		user = (User) createObject((String) userDescription.get("className"));
		user.setObject(userDescription);

		
		// description de la liste de materiels
		List<HashMap<String, Object>> materialDescriptions = (List<HashMap<String, Object>>) loanDescription.get("materials");
		materials = new LinkedList<Material>();
		for (HashMap<String, Object> material : materialDescriptions){
			materials.add(new Material(material));
		}

	}
	
	public int getId() {
		return id;
	}

	public GregorianCalendar getStartDate() {
		return startDate;
	}

	public GregorianCalendar getEndDate() {
		return endDate;
	}

	public User getUser() {
		return user;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public boolean isEffective() {
		return effective;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(GregorianCalendar endDate) {
		this.endDate = endDate;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	public void setEffective(boolean effective) {
		this.effective = effective;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public HashMap<String, Object> getSerializableDescription(){
		
		HashMap<String, Object> loanDescription = new HashMap<String, Object>();
		
		loanDescription.put("id", id);
		loanDescription.put("startDate", startDate.DAY_OF_MONTH + "/" + startDate.MONTH + "/" + startDate.YEAR);
		loanDescription.put("endDate", endDate.DAY_OF_MONTH + "/" + endDate.MONTH + "/" + endDate.YEAR);
		loanDescription.put("user", user.getSerializableDescription());
		loanDescription.put("effective", effective);
		loanDescription.put("validate", validate);
		
		// description de la liste de materiels
		List<HashMap<String, Object>> materialDescriptions = new LinkedList<HashMap<String, Object>>();
		for (Material material : materials){
			materialDescriptions.add(material.getSerializableDescription());
		}
		
		loanDescription.put("materials", materialDescriptions);
		return loanDescription;
		
	}
	
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
}
