package ch.ractive;

import org.junit.Test;

import ch.ractive.tree.BinaryTree;
import ch.ractive.tree.BinaryTree.Node;

public class MinimalTree {

	@Test
	public void test() {
		minimalTree(new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 });
	}
	
	public void minimalTree(int[] a) {
		Node<Integer> root = createTree(a, 0, a.length - 1);
		BinaryTree<Integer> tree = new BinaryTree<>(root);
		//tree.inOrder(System.out::print);
		tree.print();
	}
	
	public Node<Integer> createTree(int[] a, int left, int right) {
		if (left > right) {
			return null;
		}
		
		int mid = left + (right - left) / 2;
		Node<Integer> n = Node.of(a[mid]);
		n.setLeft(createTree(a, left, mid - 1));
		n.setRight(createTree(a, mid + 1, right));
		return n;
	}
}
