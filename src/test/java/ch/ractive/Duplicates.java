package ch.ractive;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Find duplicate in positive integer array of size N, every integer in range (1, N-1) appears at least once.
 */
public class Duplicates {

	public Collection<Integer> dups(int ints[]) {
		Set<Integer> occured = new HashSet<>(ints.length);
		Set<Integer> duplicates = new HashSet<>();
		for(int i : ints) {
			if (occured.contains(i)) {
				duplicates.add(i);
			} else {
				occured.add(i);
			}
		}
		
		return duplicates;
	}
	
	public Collection<Integer> dupsSort(int ints[]) {
		Arrays.sort(ints);
		Set<Integer> duplicates = new HashSet<>();
		int previous = 0;
		for(int i : ints) {
			if (i == previous) {
				duplicates.add(i);
			}
			previous = i;
		}
		
		return duplicates;
	}
	
	public int dup(int[] a) {
		BitSet bs = new BitSet(a.length);
		for (int i : a) {
			if (bs.get(i + 1)) {
				return i;
			}
			bs.set(i + 1);
		}
		return 0;
	}
	
	@Test
	public void bsTest() {
		assertEquals(3, dup(new int[] {1, 2, 3, 4, 5, 3, 6, 7, 8}));
	}
	
	@Test
	public void test() {
		assertThat(dups(new int[] {1,2,3,4,5,6})).isEmpty();
		assertThat(dups(new int[] {1,2,3,4,2,3,5,6})).containsOnly(2, 3);
		assertThat(dupsSort(new int[] {1,2,3,4,5,6})).isEmpty();
		assertThat(dupsSort(new int[] {1,2,3,4,2,3,5,6})).containsOnly(2, 3);
	}
}
