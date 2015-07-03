package ch.ractive;

import java.util.Collection;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

/**
 * Return the smallest 100 of a million integers.
 */
public class SmallestInts {

	private static Random R = new Random();
	private Collection<Integer> smallestInts() {
		SortedSet<Integer> ints = new TreeSet<>();
		for (int i = 0; i < 1_000_000; ++i) {
			int next = R.nextInt(Integer.MAX_VALUE);
//			System.out.println(next);
			if (ints.size() < 100) {
				ints.add(next);
			}
			else if (next < ints.last()) {
				ints.add(next);
				ints.remove(ints.last());
			}
		}
		System.out.println("------------------");		
		return ints;
	}
	
	@Test
	public void test() {
		smallestInts().stream().forEach(System.out::println);
	}
}
