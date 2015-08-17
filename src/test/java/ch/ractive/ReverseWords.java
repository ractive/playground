package ch.ractive;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Write code to reverse the words in a string. For example, an input of "New York Times" should be transformed to "Times York New".
 */
public class ReverseWords {

	public String reverseWords(String s) {
		String[] words = s.split("\\s");
		StringBuffer sb = new StringBuffer();
		for (int i = words.length - 1; i >= 0; --i) {
			if (sb.length() > 0) {
				sb.append(' ');
			}
			sb.append(words[i]);
		}
		return sb.toString();
	}
	
	@Test
	public void test() {
		assertEquals("test a is This", reverseWords("This is a test"));
		assertEquals("Times York New", reverseWords("New York Times"));
	}
}
