package solutions.day9;

import util.Files;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

/**
 * @author Yalesan Thayalan {@literal <yalesan2006@outlook.com>}
 */
public class Problem9 {

	public record vector(int x, int y) {
		@Override
		public boolean equals(final Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			final vector vector = (vector) o;
			return x == vector.x && y == vector.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}

	public static void part1() {
		List<String> input = Files.readFile("testdata/Problem9data.txt");

		Vector head = new Vector();
		Vector tail = new Vector();
		Set<vector> visited = new HashSet<>();

		for (String line : input) {
			int move = Integer.parseInt(line.substring(2) + "");
			for (int i = 0; i < move; i++) {
				switch (line.charAt(0)) {
					case 'U' -> head.moveUp();
					case 'D' -> head.moveDown();
					case 'L' -> head.moveLeft();
					case 'R' -> head.moveRight();
				}

				if (!inRange(head, tail)) {
					move(head, tail);
				}
				visited.add(new vector(tail.x, tail.y));
			}
		}
		System.out.println(visited.size());
		print(visited);
	}

	public static void part2() {
		List<String> input = Files.readFile("testdata/Problem9data.txt");
		Set<vector> visited = new HashSet<>();
		Vector[] vectors = new Vector[10];
		for (int i = 0; i < vectors.length; i++) {
			vectors[i] = new Vector();
		}

		for (String line : input) {
			int move = Integer.parseInt(line.substring(2) + "");
			for (int i = 0; i < move; i++) {
				switch (line.charAt(0)) {
					case 'U' -> vectors[0].moveUp();
					case 'D' -> vectors[0].moveDown();
					case 'L' -> vectors[0].moveLeft();
					case 'R' -> vectors[0].moveRight();
				}

				for (int j = 0; j < vectors.length - 1; j++) {
					if (!inRange(vectors[j], vectors[j + 1])) {
						vectors[j + 1] = move(vectors[j], vectors[j + 1]);
					}
				}

				visited.add(new vector(vectors[9].x, vectors[9].y));
			}
		}
		System.out.println(visited.size());
		print(visited);
	}

	public static Vector move(Vector h, Vector t) {
		t.x += Integer.signum(h.x - t.x);
		t.y += Integer.signum(h.y - t.y);
		return t;
	}

	public static boolean inRange(Vector a, Vector b) {
		return Math.abs(a.x - b.x) <= 1 && Math.abs(a.y - b.y) <= 1;
	}

	public static void print(Set<vector> visited) {
		int maxX = visited.stream()
				.mapToInt(v -> v.x)
				.max().orElseThrow();
		int minX = visited.stream()
				.mapToInt(v -> v.x)
				.min().orElseThrow();
		int maxY = visited.stream()
				.mapToInt(v -> v.y)
				.max().orElseThrow();
		int minY =  visited.stream()
				.mapToInt(v -> v.y)
				.min().orElseThrow();

		for(int y = maxY; y >= minY; y--) {
			for(int x = minX; x <= maxX; x++) {
				if(visited.contains(new vector(x, y))){
					System.out.print("#");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		System.out.println("PART 1: ");
		part1();
		System.out.println("PART 2: ");
		part2();
	}
}
