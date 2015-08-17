package ch.ractive;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Find length of longest sequence of adjacent consecutive numbers in grid -
You are given a square grid of size dxd. It contains each of the numbers
1..n, where n = d^2. The numbers each occur once and only once, in
arbitrary order, find the length of the longest sequence of adjacent
consecutive numbers in the grid.
 */
public class GridMaxSeqLen {

	@Test
	public void test() {
		int[][] g = {
			{1, 2, 3},
			{9, 5, 6},
			{7, 8, 4}
		};
		assertEquals(4, maxSeqLen(g));
	}
	
	public int maxSeqLen(int[][] g) {
		int maxLen = 0;
		int currentLen = 0;
		int prev = 0;
		
		for (int i = 0; i < g[0].length; ++i) {
			for (int j = 0; j < g[0].length; ++j) {
				int x = g[i][j];
				if (prev == 0) {
					currentLen = 1;
				} else if (x == prev + 1) {
					++currentLen;
				} else {
					currentLen = 1;
				}
				prev = x;
				maxLen = Math.max(maxLen, currentLen);
			}
		}
		
		return maxLen;
	}
}
