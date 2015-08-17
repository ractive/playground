package ch.ractive.graph;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.EvictingQueue;
import com.google.common.collect.Sets;

import ch.ractive.graph.Graph.Node;

public class GraphTest {
	Graph<Character> g = new Graph<>();

	Node<Character> r = g.createNode('r');
	Node<Character> s = g.createNode('s');
	Node<Character> t = g.createNode('t');
	Node<Character> u = g.createNode('u');
	Node<Character> v = g.createNode('v');
	Node<Character> w = g.createNode('w');
	Node<Character> x = g.createNode('x');
	Node<Character> y = g.createNode('y');

	@Before
	public void init() {
		
		r.to(s, v);
		s.to(r, w);
		t.to(u, w, x);
		u.to(t, x, y);
		v.to(r);
		w.to(s, t, x);
		x.to(t, u, w, y);
		y.to(u, x);		
	}
	
	@Test
	public void bfs() {
		System.out.println("BFS");
		System.out.println("===============");
		g.bfs(s, System.out::println);
	}
	
	@Test
	public void dfs() {
		System.out.println("DFS");
		System.out.println("===============");
		g.dfs(s, System.out::println);
	}
	
	@Test	
	public void dfs2() {
		System.out.println("DFS2");
		System.out.println("===============");
		g.dfs2(s, System.out::println);
	}
	
	private <T> void countTriangles(Node<T> root) {
		Queue<Node<T>> path = EvictingQueue.create(3);
		
		countTriangles(root, path);
	}
	
	private int triangleCounter = 0;
	
	private <T> void countTriangles(Node<T> root, Queue<Node<T>> path) {
		if (root == null) {
			return;
		}

		root.setVisited(true);
		path.add(root);
		
		for (Node<T> n : root.getAdj()) {
			
			isTriangle(path, n);
			
			if (!n.isVisited()) {
				countTriangles(n, path);
			}
		}
	}

	private <T> void isTriangle(Queue<Node<T>> path, Node<T> n) {
		if (path.size() == 3) {
			Node<T> start = path.peek();
			if (start.getValue() == n.getValue()) {
				++triangleCounter;
				System.out.println(path);
			}
		}
	}
	
	@Test
	public void triangles() {
		//countTriangles(s);
		assertEquals(3, countTrianglesBfs(s, System.out::println));
	}
	
	
	
	@SuppressWarnings("unchecked")
	public <T> int countTrianglesBfs(Node<T> root, Consumer<Node<T>> visitor) {
		Queue<Node<T>> q = new LinkedList<>();
		
		root.setVisited(true);
		q.add(root);
		
		Set<Set<Node<T>>> triangles = new HashSet<>();;
		
		while(!q.isEmpty()) {
			Node<T> u = q.remove();
			visitor.accept(u);
			
			for (Node<T> n : u.getAdj()) {
				if (!n.isVisited()) {
					q.add(n);
					n.setVisited(true);
				}
				
				for (Node<T> m : n.getAdj()) {
					for (Node<T> o : m.getAdj()) {
						if (o == u && u != m) {
							triangles.add(Sets.newHashSet(u, n, m));
						}
					}
				}
			}
		}
		System.out.println(triangles);
		return triangles.size();
	}
}
