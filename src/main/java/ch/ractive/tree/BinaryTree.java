package ch.ractive.tree;

import java.util.function.Consumer;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class BinaryTree<T extends Comparable<T>> {
	@Data(staticConstructor = "of")
	public static class Node<T extends Comparable<T>> {
		private final T key;
		private Node<T> parent;
		private Node<T> left;
		private Node<T> right;
		public Node<T> left(Node<T> l) {
			setLeft(l);
			return this;
		}
		public Node<T> left(T value) {
			return left(Node.of(value));
		}
		public Node<T> right(Node<T> l) {
			setRight(l);
			return this;
		}
		public Node<T> right(T value) {
			return right(Node.of(value));
		}
		public boolean isRoot() {
			return parent == null;
		}
		
		public boolean isRightChild() {
			if (isRoot()) {
				return false;
			}
			return getParent().getRight() == this;
		}
		
		public boolean isLeftChild() {
			if (isRoot()) {
				return false;
			}
			return getParent().getLeft() == this;
		}
	}
	
	public BinaryTree(Node<T> root) {
		this.root = root;
	}
	
	private Node<T> root;
	
	public void setRoot(Node<T> root) {
		this.root = root;
		root.setParent(null);
	}
	
	public void preOrder(Consumer<T> operation) {
		preOrder(root, operation);
	}
	public void preOrder(Node<T> parent, Consumer<T> operation) {
		if (parent == null) {
			return;
		}
		operation.accept(parent.getKey());
		preOrder(parent.getLeft(), operation);
		preOrder(parent.getRight(), operation);
	}	
	
	public void postOrder(Consumer<T> operation) {
		postOrder(root, operation);
	}
	public void postOrder(Node<T> parent, Consumer<T> operation) {
		if (parent == null) {
			return;
		}
		postOrder(parent.getLeft(), operation);
		postOrder(parent.getRight(), operation);
		operation.accept(parent.getKey());
	}
		
	public void inOrder(Consumer<T> operation) {
		inOrder(root, operation);
	}
	public void inOrder(Node<T> parent, Consumer<T> operation) {
		if (parent == null) {
			return;
		}
		inOrder(parent.getLeft(), operation);
		operation.accept(parent.getKey());
		inOrder(parent.getRight(), operation);
	}
	
	public void rotateLeft(Node<T> node) {
		Node<T> rightNode = node.getRight();
		if (rightNode == null) {
			return; // noop
		}

		if (node.isRoot()) {
			setRoot(rightNode);
		} else if (node.isRightChild()) {
			node.getParent().setRight(rightNode);
		} else {
			node.getParent().setLeft(rightNode);
		}
		
		rightNode.setParent(node.getParent());
		node.setParent(rightNode);
		node.setRight(rightNode.getLeft());
		rightNode.setLeft(node);
	}
	
	public void rotateRight(Node<T> node) {
		Node<T> leftNode = node.getLeft();
		if (leftNode == null) {
			return; // noop
		}

		if (node.isRoot()) {
			setRoot(leftNode);
		} else if (node.isRightChild()) {
			node.getParent().setRight(leftNode);
		} else {
			node.getParent().setLeft(leftNode);
		}
		leftNode.setParent(node.getParent());
		node.setParent(leftNode);
		node.setLeft(leftNode.getRight());
		leftNode.setRight(node);
	}
}
