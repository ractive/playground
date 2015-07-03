package ch.ractive.tree;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public static <C extends Comparable<C>> boolean lt(C a, C b) {
		return a.compareTo(b) < 0;
	}
	
	public static <C extends Comparable<C>> boolean gt(C a, C b) {
		return a.compareTo(b) > 0;
	}
	
	public BinarySearchTree() {}
	
	public BinarySearchTree(Node<T> root) {
		super(root);
	}
	
	public void insert(Node<T> z) {
		Node<T> y = null;
		Node<T> x = getRoot();
		while (x != null) {
			y = x;
			if (lt(z.getKey(), x.getKey())) {
				x = x.getLeft();
			} else {
				x = x.getRight();
			}
		}
		z.setParent(y);
		if (y == null) {
			setRoot(z);
		} else if (lt(z.getKey(), y.getKey())) {
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
		if ((min != null && lt(node.getKey(), min)) ||(max != null && gt(node.getKey(), max))) {
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
		if (lt(key, x.getKey())) {
			return search(x.getLeft(), key);
		} else {
			return search(x.getRight(), key);
		}
	}
}
