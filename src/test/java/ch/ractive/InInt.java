package ch.ractive;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * check if an integer is inside a given array of integers using a for loop (5 minutes)
 * 38:20 - 40:26
 */
public class InInt {

	private boolean inArray(int n, int ints[]) {
		for(int i : ints) {
			if (i == n) {
				return true;
			}
		}
		return false;
	}
	
	@Test
	public void test() {
		assertTrue(inArray(5, new int[]{1,2,3,4,5}));
		assertFalse(inArray(6, new int[]{1,2,3,4,5}));
	}
}
