package ch.ractive;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IsPowerOfTwo {

	private boolean isPowerOfTwo(int n) {
		return Math.log(n) / Math.log(2) % 1 == 0;
	}

	@Test
	public void test() {
		assertTrue(isPowerOfTwo(1));
		assertTrue(isPowerOfTwo(2));
		assertFalse(isPowerOfTwo(3));
		assertTrue(isPowerOfTwo(4));
		assertFalse(isPowerOfTwo(5));
		assertTrue(isPowerOfTwo(1024));
	}
}
