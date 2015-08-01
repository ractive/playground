package ch.ractive;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.ractive.tree.BinaryTree.Node;

/**
 * 4.12
 */
public class PathsWithSum {
	
	@Test
	public void pathsWithSum() {
		Node<Integer> root = Node.of(10);
		Node<Integer> n5 = Node.of(5);
		Node<Integer> n3 = Node.of(3);
		Node<Integer> n3_1 = Node.of(3);
		Node<Integer> nm2 = Node.of(-2);
		Node<Integer> n2 = Node.of(2);
		Node<Integer> n1 = Node.of(1);
		Node<Integer> nm3 = Node.of(-3);
		Node<Integer> n11 = Node.of(11);
		
		root.left(n5);
		n5.left(n3);
		n5.right(n2);
		n3.left(n3_1);
		n3.right(nm2);
		
		n2.right(n1);
		
		root.right(nm3);
		nm3.right(n11);
		
		assertEquals(3, pathsWithSum(root, 8));
	}
	
	int pathsWithSum(Node<Integer> n, int x) {
		if (n == null) {
			return 0;
		}
		int node = visit(n, x, 0);
		int left = pathsWithSum(n.getLeft(), x);
		int right = pathsWithSum(n.getRight(), x);
		return node + left + right;
	}
	
	int visit(Node<Integer> n, int x, int sum) {
		if (n == null) {
			return 0;
		}
		
		int total = 0;
		if (n.getKey() + sum == x) {
			++total;
		}
		total += visit(n.getLeft(), x, n.getKey() + sum);
		total += visit(n.getRight(), x, n.getKey() + sum);
		return total;
	}
	
}
