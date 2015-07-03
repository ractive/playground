package ch.ractive;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LargestIndex {

	private int largestIndex(int ints[]) {
		int largestIndex = 0;
		for (int i = 1; i < ints.length - 1; ++i) {
			if (ints[i] > ints[largestIndex]) {
				largestIndex = i;
			}
		}
		return largestIndex;
	}
	
	@Test
	public void test() {
		assertEquals(3, largestIndex(new int[] {0, 1, 2, 99, 4, 5, 6}));
	}
}
