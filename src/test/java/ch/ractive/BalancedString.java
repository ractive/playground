package ch.ractive;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Given a string consisting of open and close parentheses consider as
 * “balanced” strings like this: “(())”, “(()())”, and “()(()())” and as
 * “unbalanced” strings like this “(()”, “))”. Write a function to detect if a
 * string is balanced.
 */
public class BalancedString {
	private boolean isBalanced(String s) {
		int openBrackets = 0;
		for (char c : s.toCharArray()) {
			if (c == '(') {
				++openBrackets;
			} else if (c == ')') {
				--openBrackets;
			}
			if (openBrackets < 0) {
				return false;
			}
		}
		return openBrackets == 0;
	}
	
	@Test
	public void test() {
		assertTrue(isBalanced(""));
		assertFalse(isBalanced(")"));
		assertTrue(isBalanced("()"));
		assertFalse(isBalanced("()("));
		assertTrue(isBalanced("(()())"));
		assertFalse(isBalanced("())"));
		assertTrue(isBalanced("()(()())"));
	}
}
