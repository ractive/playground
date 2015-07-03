package ch.ractive;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class HighestFrequencyMember {

	
	
	@Test
	public void test() {
		assertEquals(3, freq(new int[]{1,2,3,1,2,3,2,3,6,3}));
	}

	private int freq(int[] ints) {
		Map<Integer, Integer> freq = new HashMap<>();
		for (int i : ints) {
			freq.compute(i, (key, value) -> (value == null ? 1 : ++value));
		}

		return freq.entrySet().stream().max(new Comparator<Entry<Integer, Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> o1,
					Entry<Integer, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		}).get().getKey();
	}
}
