package ch.ractive;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class IsAnagram {

	@Test
	public void test() {
		assertTrue(isAnagram("cat", "tac"));
		assertTrue(isAnagram("mary", "army"));
		assertFalse(isAnagram("cat", "tacx"));
		assertTrue(isAnagram("", ""));
	}

	private Map<Character, Integer> charFrequency(String s) {
		Map<Character, Integer> charFrequency = new HashMap<>();
		for (char c : s.toCharArray()) {
			charFrequency.compute(c, (key, value) -> (value == null ? 1 : ++value));
		}
		
		return charFrequency;
	}
	
	private boolean isAnagram(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		
		return charFrequency(s1).equals(charFrequency(s2));
	}
}
