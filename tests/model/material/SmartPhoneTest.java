package model.material;

import static org.junit.Assert.*;

import org.junit.Test;

public class SmartPhoneTest {


	MaterialType m1 = new SmartPhone("Iphone 5", "Apple", "Good", 14, OS.IOS, 1);
	MaterialType m2 = new SmartPhone("Iphone 5", "Apple", "Good", 14, OS.IOS, 1);
	MaterialType m3 = new SmartPhone("Iphone 4", "Apple", "Bad", 13, OS.IOS, 1);
	MaterialType m4 = new VideoCamera("Super", "LG", "Cool", 1, 1);
	
	@Test
	public void testEquals() {
		assertTrue(m1.equals(m2));
		assertTrue(m1.equals(m1));
		assertFalse(m1.equals(m3));
		assertFalse(m1.equals(m4));
	}

}
