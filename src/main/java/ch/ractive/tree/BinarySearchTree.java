package ch.ractive.tree;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public BinarySearchTree() {}
	
	public BinarySearchTree(Node<T> root) {
		super(root);
	}
	
	public void insert(Node<T> z) {
		Node<T> y = null;
		Node<T> x = getRoot();
		while (x != null) {
			y = x;
			if (z.getKey().compareTo(x.getKey()) < 0) {
				x = x.getLeft();
			} else {
				x = x.getRight();
			}
		}
		z.setParent(y);
		if (y == null) {
			setRoot(z);
		} else if (z.getKey().compareTo(y.getKey()) < 0) {
			y.setLeft(z);
		} else {
			y.setRight(z);
		}
	}
	
	public boolean verify() {
		return verify(getRoot(), null, null);
	}
	
	private boolean verify(Node<T> node, T min, T max) {
		if (node == null) {
			return true;
		}
		if ((min != null && node.getKey().compareTo(min) < 0) ||(max != null && node.getKey().compareTo(max) > 0)) {
			return false;
		}
		return verify(node.getLeft(), min, node.getKey()) && verify(node.getRight(), node.getKey(), max);
	}
	
	public Node<T> search(T key) {
		return search(getRoot(), key);
	}
	
	public Node<T> search(Node<T> x, T key) {
		if (x == null || x.getKey().equals(key)) {
			return x;
		}
		if (key.compareTo(x.getKey()) < 0) {
			return search(x.getLeft(), key);
		} else {
			return search(x.getRight(), key);
		}
	}
}
