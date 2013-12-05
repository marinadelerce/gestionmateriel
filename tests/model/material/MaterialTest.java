package model.material;

import static org.junit.Assert.*;

import org.junit.Test;

public class MaterialTest {

	MaterialType m = new SmartPhone("Galaxy s3", "Samsung", "Old telephone", 123, OS.ANDROID, 10);
	Material tel = new Material(m, "rf24");
	
	@Test
	public void test() {
		Material tel2 = new Material(tel.getSerializableDescription());
		assertEquals(tel.getSerialNumber(), tel2.getSerialNumber());
		assertEquals(tel.getMaterialType().getName(), tel2.getMaterialType().getName());
		assertEquals(tel.getMaterialType().getBrand(), tel2.getMaterialType().getBrand());
		assertEquals(tel.getMaterialType().getDescription(), tel2.getMaterialType().getDescription());
		assertEquals(tel.getMaterialType().getMaxTimeLoan(), tel2.getMaterialType().getMaxTimeLoan());
		assertTrue(tel2.getMaterialType() instanceof SmartPhone);
	}

}
