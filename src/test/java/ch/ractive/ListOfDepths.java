package ch.ractive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import ch.ractive.tree.BinaryTree.Node;

/**
 * 4.3
 */
public class ListOfDepths {
	@Test
	public void test() {
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
		
		listOfDepths(root, 0);
		
		assertThat(l.get(0)).containsExactly(10);
		assertThat(l.get(1)).containsExactly(5, -3);
		assertThat(l.get(2)).containsExactly(3, 2, 11);
		assertThat(l.get(3)).containsExactly(3, -2, 1);
	}
	
	private List<List<Integer>> l = new ArrayList<>();
	
	public void listOfDepths(Node<Integer> n, int d) {
		if (n != null) {
			visit(n, d);
			listOfDepths(n.getLeft(), d + 1);
			listOfDepths(n.getRight(), d + 1);
		}
	}
	
	public void visit(Node<Integer> n, int d) {
		if (l.size() < d + 1) {
			l.add(new LinkedList<>());
		}
		l.get(d).add(n.getKey());
	}
}
