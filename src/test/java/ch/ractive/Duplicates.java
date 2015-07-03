package ch.ractive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
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
	
	@Test
	public void test() {
		assertThat(dups(new int[] {1,2,3,4,5,6})).isEmpty();
		assertThat(dups(new int[] {1,2,3,4,2,3,5,6})).containsOnly(2, 3);
		assertThat(dupsSort(new int[] {1,2,3,4,5,6})).isEmpty();
		assertThat(dupsSort(new int[] {1,2,3,4,2,3,5,6})).containsOnly(2, 3);
	}
}
