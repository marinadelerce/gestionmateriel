/*
 * 
 */
package model.manager;

import static org.junit.Assert.*;

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

/**
 * The Class MaterialManagerTest.
 */
public class MaterialManagerTest extends TestCase {

	/*private MaterialManager materialManager;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.materialManager = new MaterialManager();
	}

	@After
	public void tearDown() throws Exception {
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
	public void testPredictStock() {
		fail("Not yet implemented");
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
		MaterialType materialtype = new SmartPhone("Galaxy S", "Samsung", "  ",
				32, OS.ANDROID, 3);
		Material material = new Material(materialtype, "zede");
		Loan newLoan = new Loan(newManager, material, 3,
				new GregorianCalendar(2013, Calendar.DECEMBER, 2),
				new GregorianCalendar(2013, Calendar.DECEMBER, 3), false, false);
		materialManager.validateLoan(newLoan);
		assertTrue(newLoan.isValidate());
	}
	
	@Test
	public void testDeleteLoan(){
		Loans reservations = new Loans();
		Borrower newManager = new Student("Delerce", "Marina", "mutti", "dm");
		MaterialType materialtype = new SmartPhone("Galaxy S", "Samsung", "  ",
				32, OS.ANDROID, 3);
		Material material = new Material(materialtype, "zede");
		Loan newLoan = new Loan(newManager,material, 3,
				new GregorianCalendar(2013, Calendar.DECEMBER, 2),
				new GregorianCalendar(2013, Calendar.DECEMBER, 3), false, false);
		materialManager.getReservations().add(newLoan);
		assertNotNull(reservations);
		materialManager.deleteLoan(newLoan);
		assertNull(materialManager.searchLoan(newLoan.getId()));
	}

	@Test
	public void testGetReservations() {
		Loans reservations = new Loans();
		Borrower newManager = new Student("Delerce", "Marina", "mutti", "dm");
		MaterialType materialtype = new SmartPhone("Galaxy S", "Samsung", "  ",
				32, OS.ANDROID, 3);
		Material material = new Material(materialtype, "zede");
		Loan newLoan = new Loan(newManager,material, 3,
				new GregorianCalendar(2013, Calendar.DECEMBER, 2),
				new GregorianCalendar(2013, Calendar.DECEMBER, 3), false, false);
		materialManager.getReservations().add(newLoan);
		assertNotNull(reservations);
	}

	@Test
	public void testSearchLoan() {
		Borrower newManager = new Student("Delerce", "Marina", "mutti", "dm");
		MaterialType materialtype = new SmartPhone("Galaxy S", "Samsung", "  ",
				32, OS.ANDROID, 3);
		Material tel1 = new Material(materialtype, "gs21");
		Material tel2 = new Material(materialtype, "gs22");
		List<Material> materials = new ArrayList<Material>();
		materials.add(tel1);
		materials.add(tel2);
		//Loan newLoan = new Loan(newManager,materials,);
		materialManager.getReservations().add(newLoan);
		int id = newLoan.getId();
		Loan searchedLoan = materialManager.searchLoan(id);
		assertNotNull(searchedLoan);
		assertEquals(searchedLoan, newLoan); //FIXME
	}
*/
}
