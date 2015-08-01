package ch.ractive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class MyHashMapTest {

	@Getter @Setter
	@RequiredArgsConstructor
	private static class Dummy {
		private final int value;
		
		@Override
		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			
			if (!(other instanceof Dummy)) {
				return false;
			}
			
			Dummy otherDummy = (Dummy)other;
			return otherDummy.value == value;
		}
		
		@Override
		public int hashCode() {
			if (value > 10) {
				return 10;
			} else {
				return 5;
			}
		}
	}
	
	@Test
	public void empty() {
		MyHashMap<String, String> myHashMap = new MyHashMap<>(10);
		assertNull(myHashMap.get("foo"));
	}
	
	@Test
	public void simple() {
		MyHashMap<String, String> myHashMap = new MyHashMap<>(10);
		assertFalse(myHashMap.put("foo", "FooValue"));
		assertEquals("FooValue", myHashMap.get("foo"));
		
		assertTrue(myHashMap.put("foo", "NewFooValue"));
		assertEquals("NewFooValue", myHashMap.get("foo"));
		
		assertNull(myHashMap.get("bar"));
	}
	
	@Test
	public void hashCodeCollisions() {
		MyHashMap<Dummy, String> myHashMap = new MyHashMap<>(10);
		
		assertFalse(myHashMap.put(new Dummy(1), "1"));
		assertFalse(myHashMap.put(new Dummy(2), "2"));
		assertFalse(myHashMap.put(new Dummy(3), "3"));
		assertFalse(myHashMap.put(new Dummy(4), "4"));
		assertFalse(myHashMap.put(new Dummy(5), "5"));
		assertFalse(myHashMap.put(new Dummy(6), "6"));
		assertFalse(myHashMap.put(new Dummy(7), "7"));
		assertFalse(myHashMap.put(new Dummy(8), "8"));
		assertFalse(myHashMap.put(new Dummy(9), "9"));
		assertFalse(myHashMap.put(new Dummy(10), "10"));
		assertFalse(myHashMap.put(new Dummy(11), "11"));
		assertFalse(myHashMap.put(new Dummy(12), "12"));
		
		assertTrue(myHashMap.put(new Dummy(11), "new11"));
		
		assertEquals(myHashMap.get(new Dummy(1)), "1");
		assertEquals(myHashMap.get(new Dummy(2)), "2");
		assertEquals(myHashMap.get(new Dummy(3)), "3");
		assertEquals(myHashMap.get(new Dummy(4)), "4");
		assertEquals(myHashMap.get(new Dummy(5)), "5");
		assertEquals(myHashMap.get(new Dummy(6)), "6");
		assertEquals(myHashMap.get(new Dummy(7)), "7");
		assertEquals(myHashMap.get(new Dummy(8)), "8");
		assertEquals(myHashMap.get(new Dummy(9)), "9");
		assertEquals(myHashMap.get(new Dummy(10)), "10");
		assertEquals(myHashMap.get(new Dummy(11)), "new11");
		assertEquals(myHashMap.get(new Dummy(12)), "12");
	}
}
