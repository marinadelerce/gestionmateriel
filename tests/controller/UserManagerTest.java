package controller;

import static org.junit.Assert.*;
import junit.framework.TestCase;
import model.user.Manager;
import model.user.Student;
import model.user.Teacher;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.UserManager;

public class UserManagerTest extends TestCase {

	private UserManager userManager;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.userManager = new UserManager();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddNewManager() {
		boolean userCreated = this.userManager.addNewUser("Manager", "Sander", "Peter", "sanderManager", "ps");
		assertEquals(userCreated, true);
	}
	
	@Test
	public void testAddNewTeacher() {
		boolean userCreated = this.userManager.addNewUser("Teacher", "Sander", "Peter", "sanderTeacher", "ps");
		assertEquals(userCreated, true);
	}
	
	@Test
	public void testAddNewStudent() {
		boolean userCreated = this.userManager.addNewUser("Student", "Delerce", "Marina", "delerce", "md");
		assertEquals(userCreated, true);
	}
	
	@Test
	public void testDeleteUser() {
		boolean userCreated = this.userManager.addNewUser("Teacher", "Gotti", "Julia", "juju", "titi");
		assertEquals(userCreated, true);
		boolean isDeleted = this.userManager.deleteUser("Gotti", "Julia", "juju");
		assertEquals(isDeleted, true);
	}
	
	@Test
	public void testAddTwoUsersWithSameLogin() {
		boolean userCreated = this.userManager.addNewUser("Manager", "Sander", "Peter", "sanderManager", "ps");
		assertEquals(userCreated, true);
		boolean sameUserCreated = this.userManager.addNewUser("Manager", "Sander", "Peter", "sanderManager", "ps");
		assertEquals(sameUserCreated, false);
	}

	@Test
	public void testCheckUserPassword() {
		this.userManager.addNewUser("Manager", "Sander", "Peter", "sanderManager", "ps");
		boolean passwordOk = this.userManager.checkUserPassword("sanderManager", "ps");
		assertEquals(passwordOk, true);
	}
	
	@Test
	public void testLogInWithManager() {
		this.userManager.addNewUser("Manager", "Sander", "Peter", "sanderManager", "ps");
		this.userManager.checkUserPassword("sanderManager", "ps");
		assertNotNull(this.userManager.getConnectedUser());
		assertEquals(this.userManager.getConnectedUser().getClass(), Manager.class);
	}
	
	@Test
	public void testLogInWithTeacher() {
		this.userManager.addNewUser("Teacher", "Sander", "Peter", "sanderTeacher", "ps");
		this.userManager.checkUserPassword("sanderTeacher", "ps");
		assertNotNull(this.userManager.getConnectedUser());
		assertEquals(this.userManager.getConnectedUser().getClass(), Teacher.class);
	}
	
	@Test
	public void testLogInWithStudent() {
		this.userManager.addNewUser("Student", "Delerce", "Marina", "delerce", "md");
		this.userManager.checkUserPassword("delerce", "md");
		assertNotNull(this.userManager.getConnectedUser());
		assertEquals(this.userManager.getConnectedUser().getClass(), Student.class);
	}

}
