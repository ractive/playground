package ch.ractive.tree;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ch.ractive.tree.BinaryTree.Node;

public class TreeTest {
	
	private <T extends Comparable<T>> List<T> inOrder(BinaryTree<T> tree) {
		List<T> elements = new ArrayList<>();
		tree.inOrder(elements::add);
		return elements;
	}
	
	private <T extends Comparable<T>> List<T> preOrder(BinaryTree<T> tree) {
		List<T> elements = new ArrayList<>();
		tree.preOrder(elements::add);
		return elements;
	}
	
	private <T extends Comparable<T>> List<T> postOrder(BinaryTree<T> tree) {
		List<T> elements = new ArrayList<>();
		tree.postOrder(elements::add);
		return elements;
	}
	
	@Test
	public void traversal() {
		Node<Integer> root = Node.of(2);
		Node<Integer> n7 = Node.of(7);
		Node<Integer> n2 = Node.of(2);
		Node<Integer> n6 = Node.of(6);
		Node<Integer> n5 = Node.of(5);
		Node<Integer> n5_2 = Node.of(5);
		Node<Integer> n9 = Node.of(9);
		Node<Integer> n4 = Node.of(4);
		Node<Integer> n11 = Node.of(11);
		
		root.setLeft(n7);
		n7.left(n2).right(n6);
		n6.left(n5).right(n11);
		
		root.setRight(n5_2);
		n5_2.right(n9);
		n9.left(n4);
		
		BinaryTree<Integer> intTree = new BinaryTree<>(root);
		
		assertThat(preOrder(intTree)).containsExactly(2, 7, 2, 6, 5, 11, 5, 9, 4);
		assertThat(inOrder(intTree)).containsExactly(2, 7, 5, 6, 11, 2, 5, 4, 9);
		assertThat(postOrder(intTree)).containsExactly(2, 5, 11, 6, 7, 4, 9, 5, 2);
	}
	
	@Test
	public void rotation() {
		Node<String> root = Node.of("r");
		root.left("a");
		
		Node<String> pivot = Node.of("p");
		Node<String> c = Node.of("c");
		pivot.left("b").right(c);
		root.right(pivot);

		BinaryTree<String> stringTree = new BinaryTree<>(root);
		
		assertThat(preOrder(stringTree)).containsExactly("r", "a", "p", "b", "c");

		// Inorder is the same after rotateLeft
		stringTree.rotateLeft(stringTree.getRoot());
		assertThat(preOrder(stringTree)).containsExactly("p", "r", "a", "b", "c");
		stringTree.rotateRight(stringTree.getRoot());
		assertThat(preOrder(stringTree)).containsExactly("r", "a", "p", "b", "c");
		
		stringTree.rotateLeft(pivot);
		assertThat(preOrder(stringTree)).containsExactly("r", "a", "c", "p", "b");
		stringTree.rotateRight(c);
		assertThat(preOrder(stringTree)).containsExactly("r", "a", "p", "b", "c");
	}
	
	@Test
	public void verifyFalse() {
		/*
		 *         20
		 *       /    \
		 *     10      30
		 *            /  \
		 *           5    40   
		 */
		Node<Integer> root = Node.of(20);
		Node<Integer> n30 = Node.of(30).left(5).right(40);
		
		root.left(10).right(n30);
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<>(root);
		assertFalse(bst.verify());
	}
	
	@Test
	public void verifyTrue() {
		/*
		 *         20
		 *       /    \
		 *     10      30
		 *            /  \
		 *          25    40   
		 */
		Node<Integer> root = Node.of(20);
		Node<Integer> n30 = Node.of(30).left(25).right(40);
		
		root.left(10).right(n30);
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<>(root);
		assertTrue(bst.verify());
	}
	
	@Test
	public void inOrderSuccessor() {
		/*
		 *           20
		 *       /       \
		 *     10         30
		 *   /   \       /  \
		 *  1    11    25    40
		 *            /  \
		 *          24    26
		 */
		Node<Integer> n20 = Node.of(20);
		Node<Integer> n24 = Node.of(25);
		Node<Integer> n26 = Node.of(25);
		Node<Integer> n25 = Node.of(25).left(n24).right(n26);
		Node<Integer> n40 = Node.of(40);
		Node<Integer> n30 = Node.of(30).left(n25).right(n40);
		Node<Integer> n1 = Node.of(1);
		Node<Integer> n11 = Node.of(11);
		Node<Integer> n10 = Node.of(10).left(n1).right(n11);
		n20.left(n10).right(n30);
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<>(n20);
		// 1 10 11 20 24 25 26 30 40
		assertThat(bst.inOrderSuccessor(n1)).isEqualTo(n10);
		assertThat(bst.inOrderSuccessor(n10)).isEqualTo(n11);
		assertThat(bst.inOrderSuccessor(n11)).isEqualTo(n20);
		assertThat(bst.inOrderSuccessor(n20)).isEqualTo(n24);
		assertThat(bst.inOrderSuccessor(n24)).isEqualTo(n25);
		assertThat(bst.inOrderSuccessor(n25)).isEqualTo(n26);
		assertThat(bst.inOrderSuccessor(n26)).isEqualTo(n30);
		assertThat(bst.inOrderSuccessor(n30)).isEqualTo(n40);
		assertThat(bst.inOrderSuccessor(n40)).isNull();;
	}
	
	@Test
	public void search() {
		Node<Integer> root = Node.of(20);
		Node<Integer> n30 = Node.of(30).left(25).right(40);
		
		root.left(10).right(n30);
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<>(root);
		assertEquals(Integer.valueOf(25), bst.search(25).getKey());
		assertNull(bst.search(99));
	}
}
