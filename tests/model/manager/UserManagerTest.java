/*
 * 
 */
package model.manager;

import junit.framework.TestCase;
import model.manager.UserManager;
import model.user.Manager;
import model.user.Student;
import model.user.Teacher;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


// TODO: Auto-generated Javadoc
/**
 * The Class UserManagerTest.
 */
public class UserManagerTest extends TestCase {

	/** The user manager. */
	private UserManager userManager;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.userManager = new UserManager();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test add new manager.
	 */
	@Test
	public void testAddNewManager() {
		boolean userCreated = this.userManager.addNewUser("Manager", "Sander", "Peter", "sanderManager", "ps");
		assertEquals(userCreated, true);
	}
	
	/**
	 * Test add new teacher.
	 */
	@Test
	public void testAddNewTeacher() {
		boolean userCreated = this.userManager.addNewUser("Teacher", "Sander", "Peter", "sanderTeacher", "ps");
		assertEquals(userCreated, true);
	}
	
	/**
	 * Test add new student.
	 */
	@Test
	public void testAddNewStudent() {
		boolean userCreated = this.userManager.addNewUser("Student", "Delerce", "Marina", "delerce", "md");
		assertEquals(userCreated, true);
	}
	
	/**
	 * Test delete user.
	 */
	@Test
	public void testDeleteUser() {
		boolean userCreated = this.userManager.addNewUser("Teacher", "Gotti", "Julia", "juju", "titi");
		assertEquals(userCreated, true);
		boolean isDeleted = this.userManager.deleteUser("Gotti", "Julia", "juju");
		assertEquals(isDeleted, true);
	}
	
	/**
	 * Test add two users with same login.
	 */
	@Test
	public void testAddTwoUsersWithSameLogin() {
		boolean userCreated = this.userManager.addNewUser("Manager", "Sander", "Peter", "sanderManager", "ps");
		assertEquals(userCreated, true);
		boolean sameUserCreated = this.userManager.addNewUser("Manager", "Sander", "Peter", "sanderManager", "ps");
		assertEquals(sameUserCreated, false);
	}

	/**
	 * Test check user password.
	 */
	@Test
	public void testCheckUserPassword() {
		this.userManager.addNewUser("Manager", "Sander", "Peter", "sanderManager", "ps");
		boolean passwordOk = this.userManager.checkUserPassword("sanderManager", "ps");
		assertEquals(passwordOk, true);
	}
	
	/**
	 * Test log in with manager.
	 */
	@Test
	public void testLogInWithManager() {
		this.userManager.addNewUser("Manager", "Sander", "Peter", "sanderManager", "ps");
		this.userManager.checkUserPassword("sanderManager", "ps");
		assertNotNull(this.userManager.getConnectedUser());
		assertEquals(this.userManager.getConnectedUser().getClass(), Manager.class);
	}
	
	/**
	 * Test log in with teacher.
	 */
	@Test
	public void testLogInWithTeacher() {
		this.userManager.addNewUser("Teacher", "Sander", "Peter", "sanderTeacher", "ps");
		this.userManager.checkUserPassword("sanderTeacher", "ps");
		assertNotNull(this.userManager.getConnectedUser());
		assertEquals(this.userManager.getConnectedUser().getClass(), Teacher.class);
	}
	
	/**
	 * Test log in with student.
	 */
	@Test
	public void testLogInWithStudent() {
		this.userManager.addNewUser("Student", "Delerce", "Marina", "delerce", "md");
		this.userManager.checkUserPassword("delerce", "md");
		assertNotNull(this.userManager.getConnectedUser());
		assertEquals(this.userManager.getConnectedUser().getClass(), Student.class);
	}
	
	/**
	 * Test sign off.
	 */
	@Test
	public void testSignOff(){
		this.userManager.addNewUser("Student", "Delerce", "Marina", "delercem", "mdm");
		this.userManager.checkUserPassword("delercem", "mdm");
		assertNotNull(this.userManager.getConnectedUser());
		this.userManager.signOff();
		assertNull(this.userManager.getConnectedUser());
	}

}
