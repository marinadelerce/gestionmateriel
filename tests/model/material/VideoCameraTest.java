package model.material;

import static org.junit.Assert.*;

import org.junit.Test;

public class VideoCameraTest {

	MaterialType m1 = new VideoCamera("Super", "LG", "Cool", 1, 1);
	MaterialType m2 = new VideoCamera("Super", "LG", "Cool", 1, 1);
	MaterialType m3 = new VideoCamera("Dre", "Beats", "Bluetooth", 13, 1);
	MaterialType m4 = new SmartPhone("Iphone 5", "Apple", "Good", 14, OS.IOS, 1);
	
	@Test
	public void testEquals() {
		assertTrue(m1.equals(m2));
		assertTrue(m1.equals(m1));
		assertFalse(m1.equals(m3));
		assertFalse(m1.equals(m4));
	}

}