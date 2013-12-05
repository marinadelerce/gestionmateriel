/*
 * 
 */
package model.manager;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.TestCase;
import model.loan.Loan;
import model.loan.Loans;
import model.material.Material;
import model.material.MaterialType;
import model.material.OS;
import model.material.SmartPhone;
import model.user.Borrower;
import model.user.Manager;
import model.user.Student;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.DateUtils;
import controller.Controller;

/**
 * The Class MaterialManagerTest.
 */
public class MaterialManagerTest extends TestCase {

	private MaterialManager materialManager;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.materialManager = new MaterialManager();

	}

	@After
	public void tearDown() throws Exception {
	}

	private Loans init() {
		Loans loans = new Loans();
		Borrower newManager = new Student("Delerce", "Marina", "mutti", "dm");
		MaterialType materialtype = new SmartPhone("Galaxy S", "Samsung", "  ",
				32, OS.ANDROID, 3);
		Material tel1 = new Material(materialtype, "gs21");
		Material tel2 = new Material(materialtype, "gs22");
		Material tel3 = new Material(materialtype, "gs23");
		Material tel4 = new Material(materialtype, "gs24");
		materialManager.addMaterial(tel1);
		materialManager.addMaterial(tel2);
		materialManager.addMaterial(tel3);
		materialManager.addMaterial(tel4);
		List<Material> materials = new ArrayList<Material>();
		materials.add(tel1);
		materials.add(tel2);
		List<Material> materials2 = new ArrayList<Material>();
		materials2.add(tel3);
		materials2.add(tel4);
		Loan newLoan1 = null;
		try {
			newLoan1 = new Loan(newManager, materials,
					DateUtils.convertDate("04/12/2013"),
					DateUtils.convertDate("05/12/2013"));
		} catch (ParseException e) {
			System.out.println("Problème de conversion de date (jj/mm/aaaa) !");
		}
		loans.add(newLoan1);
		Loan newLoan2 = null;
		try {
			newLoan2 = new Loan(newManager, materials,
					DateUtils.convertDate("04/12/2013"),
					DateUtils.convertDate("05/12/2013"));
		} catch (ParseException e) {
			System.out.println("Problème de conversion de date (jj/mm/aaaa) !");
		}
		loans.add(newLoan2);

		return loans;
	}

	@Test
	public void testAddMaterial() {
		MaterialType materialtype = new SmartPhone("Galaxy S", "Samsung", "  ",
				32, OS.ANDROID, 3);
		Material tel1 = new Material(materialtype, "gs21");
		materialManager.addMaterial(tel1);
		assertNotNull(materialManager.getStock());
	}

	@Test
	public void testMaterialManager() {
		assertNotNull(materialManager);
	}

	@Test
	public void testCalculateMaxDurationLoan() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAvailableStock(){
		
	}

	@Test
	public void testBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStock() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateLoan() {
		Borrower newManager = new Student("Delerce", "Marina", "mutti", "dm");
		Loan booked = null;
		MaterialType materialtype = new SmartPhone("Galaxy S", "Samsung", "  ",
				32, OS.ANDROID, 3);
		Material tel1 = new Material(materialtype, "gs21");
		materialManager.addMaterial(tel1);
		try {
			booked = materialManager.book(32, newManager,
					DateUtils.convertDate("04/12/2013"),
					DateUtils.convertDate("05/12/2013"), 1);
		} catch (ParseException e) {
			System.out.println("Problème de conversion de date (jj/mm/aaaa) !");
		}
		assertNotNull(booked);
		materialManager.validateLoan(booked.getId());
		assertTrue(booked.isValidate());
	}

	@Test
	public void testDeleteLoan() {
		Borrower newManager = new Student("Delerce", "Marina", "mutti", "dm");
		MaterialType materialtype = new SmartPhone("Galaxy S", "Samsung", "  ",
				32, OS.ANDROID, 3);
		Material tel1 = new Material(materialtype, "gs21");
		ArrayList<Material> materials = new ArrayList<Material>();
		materials.add(tel1);
		Loan newLoan = null;
		try {
			newLoan = new Loan(newManager, materials,
					DateUtils.convertDate("04/12/2013"),
					DateUtils.convertDate("05/12/2013"));
		} catch (ParseException e) {
			System.out.println("Problème de conversion de date (jj/mm/aaaa) !");
		}
		materialManager.getReservations().add(newLoan);
		assertNotNull(materialManager.getReservations());
		materialManager.deleteLoan(newLoan);
		assertNull(materialManager.searchLoan(newLoan.getId()));
	}

	@Test
	public void testGetReservations() throws ParseException {
		Loans loans = init();
		assertNotNull(loans.getLoans());
	}

	@Test
	public void testSearchLoan() throws ParseException {
		Borrower newManager = new Student("Delerce", "Marina", "mutti", "dm");
		MaterialType materialtype = new SmartPhone("Galaxy S", "Samsung", "  ",
				32, OS.ANDROID, 3);
		Material tel1 = new Material(materialtype, "gs21");
		Material tel2 = new Material(materialtype, "gs22");
		List<Material> materials = new ArrayList<Material>();
		materials.add(tel1);
		materials.add(tel2);
		Loan newLoan = new Loan(newManager, materials,
				DateUtils.convertDate("04/12/2013"),
				DateUtils.convertDate("05/12/2013"));
		materialManager.getReservations().add(newLoan);
		int id = newLoan.getId();
		Loan searchedLoan = materialManager.searchLoan(id);
		assertNotNull(searchedLoan);
		assertEquals(searchedLoan, newLoan); //FIXME
	}

}
