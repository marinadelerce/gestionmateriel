package model.material;

import static org.junit.Assert.*;

import org.junit.Test;

public class StockTest {

	Stock stock = new Stock();
	
	@Test
	public void testAdd() {
		
		MaterialType m = new SmartPhone("Galaxy s3", "Samsung", "Old telephone", 123, OS.ANDROID, 10);
		Material tel = new Material(m, "tl1");
		Material tel2 = new Material(m, "tl2");
		Material tel3 = new Material(m, "tl3");
		Material tel4 = new Material(m, "tl4");
		
		stock.add(tel);
		stock.add(tel2);
		stock.add(tel3);
		
		MaterialType m2 = new Tablet("Galaxy note", "Samsung", "Old tablet", 124, OS.ANDROID, 3);
		Material tablet = new Material(m2, "tb1");
		
		stock.add(tablet);
		
		assertEquals(2, stock.getStock().size());
		assertEquals(3, stock.getStock(m).size());
		assertTrue(stock.getStock(m).contains(tel));
		assertTrue(stock.getStock(m).contains(tel2));
		assertFalse(stock.getStock(m).contains(tel4));
		
		assertTrue(stock.getStock(m2).contains(tablet));
		assertFalse(stock.getStock(m2).contains(tel));
		
		}

	@Test
	public void testRemove() {
		
		MaterialType m = new SmartPhone("Galaxy s3", "Samsung", "Old telephone", 123, OS.ANDROID, 10);
		Material tel = new Material(m, "tl1");
		
		Material tel2 = new Material(m, "tl1");
		
		stock.add(tel);

		assertEquals(1, stock.getStock(m).size());

		try {
			stock.remove(tel2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(0, stock.getStock(m).size());

		
		}
	
	@Test
	public void testGetMaterialType() {
		
		MaterialType m = new SmartPhone("Galaxy s3", "Samsung", "Old telephone", 123, OS.ANDROID, 10);
		MaterialType m2 = new SmartPhone("Galaxy s4", "Samsung", "Niew telephone", 124, OS.ANDROID, 10);
		Material tel = new Material(m, "tl1");
		
		stock.add(tel);

		assertEquals(m, stock.getMaterialType(123));
		assertEquals(null, stock.getMaterialType(124));

		
		}
}
