package ch.ractive;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.junit.Test;

/**
 * Find missing number in an array where all numbers occur three times. If we
 * have an integer array with 3 * n elements like: int input[] = {1, 1, 1, 3, 3,
 * 3, 25, 25, 25, 88, 88, 88} They are position, sorted, not continuous. Now we
 * 1. take one number of the array. 2. shuffle the array to make it random. So
 * the array can be {88, 1, 3, 25, 1, 3, 88, 3, 1, 25, 88} // missing number 25
 */
public class MissingNumber {

	private int missingNumber(int numbers[]) {
		Map<Integer, Integer> intCount = new HashMap<>();
		for (int i : numbers) {
			intCount.compute(i, (key, value) -> (value == null ? 1 : ++value));
		}
		
		Optional<Entry<Integer,Integer>> min = intCount.entrySet().stream().min(new Comparator<Entry<Integer, Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> o1,
					Entry<Integer, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		return min.get().getKey();
	}
	
	@Test
	public void test() {
		assertEquals(25, missingNumber(new int[] {88, 1, 3, 25, 1, 3, 88, 3, 1, 25, 88}));
	}
}
