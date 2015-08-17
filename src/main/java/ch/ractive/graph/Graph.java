package ch.ractive.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Graph<T> {
	@Getter @Setter
	@NoArgsConstructor
	public static class Node<T> {
		public Node(T t) {
			this.value = t;
		}
		private boolean visited;
		private T value;
		private List<Node<T>> adj = new ArrayList<>();
		private Queue<Node<T>> path = new LinkedList<>();
			
		@SafeVarargs
		public final Node<T> to(Node<T>... other) {
			this.adj.addAll(Arrays.asList(other));
			return this;
		}
		
		@Override
		public String toString() {
			String s= value.toString();
			//s += path.stream().map((v) -> { return v.getValue(); }).collect(Collectors.toList());
			return s;
		}
	}
	
	public Node<T> createNode(T t) {
		return new Node<T>(t);
	}
	
	public void bfs(Node<T> root, Consumer<Node<T>> visitor) {
		Queue<Node<T>> q = new LinkedList<>();
		
		root.visited = true;
		q.add(root);
		while(!q.isEmpty()) {
			Node<T> u = q.remove();
			visitor.accept(u);
			for (Node<T> n : u.adj) {
				if (!n.visited) {
					q.add(n);
					n.visited = true;
				}
			}
		}
	}
	
	public void dfs(Node<T> root, Consumer<Node<T>> visitor) {
		if (root == null) {
			return;
		}
		
		visitor.accept(root);
		root.visited = true;
		
		for (Node<T> n : root.adj) {
			if (!n.visited) {
				dfs(n, visitor);
			}
		}
	}
	
	public void dfs2(Node<T> root, Consumer<Node<T>> visitor) {
		if (root == null) {
			return;
		}
		
		Stack<Node<T>> q = new Stack<>();
		q.push(root);
		root.visited = true;
		
		while(!q.isEmpty()) {
			Node<T> n = q.pop();
			visitor.accept(n);
			
			ListIterator<Node<T>> it = n.adj.listIterator(n.adj.size());
			while(it.hasPrevious()) {
				Node<T> a = it.previous();

//			for (Node<T> a : n.adj) {
				if (!a.visited) {
					q.push(a);
					a.visited = true;
				}
			}
		}
	}
}
	