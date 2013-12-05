package model.material;

import static org.junit.Assert.*;

import org.junit.Test;

public class HeadPhoneTest {

	MaterialType m1 = new HeadPhone("Head", "Philips", "Bluetooth", 12, 1);
	MaterialType m2 = new HeadPhone("Head", "Philips", "Bluetooth", 12, 1);
	MaterialType m3 = new HeadPhone("Dre", "Beats", "Bluetooth", 13, 1);
	MaterialType m4 = new SmartPhone("Iphone 5", "Apple", "Good", 14, OS.IOS, 1);
	
	@Test
	public void testEquals() {
		assertTrue(m1.equals(m2));
		assertTrue(m1.equals(m1));
		assertFalse(m1.equals(m3));
		assertFalse(m1.equals(m4));
	}

}
