package ch.ractive;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import lombok.Data;

/**
 * - Given a set of points on a graph determine the line that will intersect the most points.
 */
public class Lines {

	@Data(staticConstructor = "of")
	private static class Point {
		private final int x;
		private final int y;
	}
	
	@Data
	private static class LinearFunction {
		private final double a;
		private final double c;
		
		private static double a(Point p1, Point p2) {
			return (double)(p2.y - p1.y) / (p2.x - p1.x);
		}
		
		private static double c(double a, Point p) {
			return p.y - p.x * a;
		}
		
		public LinearFunction(Point p1, Point p2) {
			a = a(p1, p2);
			c = c(a, p1);
		}
	}
	
	private boolean onLine(Point p, LinearFunction f) {
		return f.a * p.x + f.c == p.y;
	}
	
	private int hits(LinearFunction f, Point... points) {
		if (hits.containsKey(f)) {
			return hits.get(f);
		}
		int counter = 0;
		for (Point p : points) {
			if (onLine(p, f)) {
				++counter;
			}
		}
		hits.put(f, counter);
		return counter;
	}
	
	private Map<LinearFunction, Integer> hits = new HashMap<>();
	
	LinearFunction res = null;
	private void line(Point... points) {
		int max = 0;
		
		for (int i = 0; i < points.length - 2; ++i) {
			for (int j = i + 1; j < points.length - 1; ++j) {
				Point p1 = points[i];
				Point p2 = points[j];
				LinearFunction f = new LinearFunction(p1, p2);
				int hits = hits(f, points);
				System.out.println(p1 + " - " + p2 + ": " + f + " - Hits: " + hits);
				if (hits > max) {
					System.out.println(p1 + " - " + p2 + ": " + f);
					res = f;
					max = hits;
				}
			}
		}
	}
	
	@Test
	public void test() {
		line(
			Point.of( 1,  1),
			Point.of( 2,  3),
			Point.of( 6,  5),
			Point.of( 8,  1),
			Point.of(10,  7),
			Point.of(12,  6),
			Point.of(16, 10),
			Point.of(17,  4)
		);
		
		assertEquals(0.5d, res.a, 0.0001d);
		assertEquals(2d, res.c, 0.0001d);
	}
}
