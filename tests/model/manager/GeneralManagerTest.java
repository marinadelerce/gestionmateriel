package model.manager;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.GregorianCalendar;

import model.loan.Loan;
import model.material.Material;
import model.material.MaterialType;
import model.material.OS;
import model.material.SmartPhone;
import model.user.Borrower;
import model.user.Student;
import model.user.Teacher;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.DateUtils;

public class GeneralManagerTest {

	private GeneralManager generalManager;
	
	@Before
	public void setUp() throws Exception {
		this.generalManager = new GeneralManager();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBook() {
		generalManager.addNewUser("Manager","Delerce", "Marina", "muttiMan", "md");
		generalManager.addNewUser("Student","Delerce", "Marina", "mutti", "md");
		generalManager.addNewUser("Teacher","Delerce", "Marina", "muttiTea", "md");
		
		SmartPhone smartphone = new SmartPhone("Galaxy S3", "Samsung", "Super tel!", 32, OS.ANDROID, 20);
		generalManager.addMaterial(new Material(smartphone, "gs31"));
		generalManager.addMaterial(new Material(smartphone, "gs32"));
		
		generalManager.checkUserPassword("mutti", "md");
		
		Loan booked = null;
		try {
			booked = this.generalManager.book(32,
					DateUtils.convertDate("04/12/2013"),
					DateUtils.convertDate("05/12/2013"), 1);
		} catch (ParseException e) {
			System.out.println("Problème de conversion de date (jj/mm/aaaa) !");
		}
		assertNotNull(booked);
		//materialManager.getReservations().add(booked);
		assertNotEquals(0, this.generalManager.getReservations().size());
	
		
		
		GregorianCalendar today10 = new GregorianCalendar();
		today10.add(GregorianCalendar.DAY_OF_MONTH, 10);
		GregorianCalendar today20 = new GregorianCalendar();
		today20.add(GregorianCalendar.DAY_OF_MONTH, 20);
		booked = this.generalManager.book(32,
				today10,
				today20, 1);
		assertNull(booked);
		
		generalManager.signOff();
		generalManager.checkUserPassword("muttiTea", "md");
		
		booked = this.generalManager.book(32,
				today10,
				today20, 1);
		assertNotNull(booked);
	}
	
	@Test
	public void testDeleteReservation() {
		generalManager.addNewUser("Manager","Delerce", "Marina", "muttiMan", "md");
		
		SmartPhone smartphone = new SmartPhone("Galaxy S3", "Samsung", "Super tel!", 32, OS.ANDROID, 20);
		generalManager.addMaterial(new Material(smartphone, "gs31"));
		generalManager.addMaterial(new Material(smartphone, "gs32"));
		
		generalManager.checkUserPassword("muttiMan", "md");
		
		Loan booked = null;
		try {
			booked = this.generalManager.book(32,
					DateUtils.convertDate("04/12/2013"),
					DateUtils.convertDate("05/12/2013"), 1);
		} catch (ParseException e) {
			System.out.println("Problème de conversion de date (jj/mm/aaaa) !");
		}
		assertNotNull(booked);
		
		generalManager.deleteReservation(booked.getId());
		assertEquals(0, generalManager.getReservations().size());
	}

}
