package solutions.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yalesan Thayalan {@literal <yalesan2006@outlook.com>}
 */
public class Problem7 {

	public static long sum = 0;
	public static long smallest = Long.MAX_VALUE;

	public static void part1() {
		Node outermostDir = createTree();
		sum(outermostDir);
		System.out.println(sum);
	}

	public static void part2() {
		Node outermostDir = createTree();
		int unusedSpace = 70000000 - outermostDir.dirSize;
		update(outermostDir, unusedSpace);
		System.out.println(smallest);
	}

	public static void sum(Node node) {
		if (node.nodes.size() != 0) {
			for (Node n : node.nodes) {
				sum(n);
			}
		}
		if (node.dirSize <= 100000) {
			sum += node.dirSize;
		}
	}

	public static void update(Node node, int unusedSpace) {
		if (node.nodes.size() != 0) {
			for (Node n : node.nodes) {
				update(n, unusedSpace);
			}
		}

		if (((70000000 - unusedSpace) - node.dirSize) <= 40000000 && node.dirSize < smallest) {
			smallest = node.dirSize;
		}
	}

	public static Node createTree() {
		List<String> input = new ArrayList<>();
		try {
			Files.lines(Paths.get("testdata/Problem7data.txt"))
					.forEach(input::add);
		} catch (IOException e) {
			System.out.println("Error: " + e.getCause());
		}

		Node outermostDir = new Node();
		outermostDir.nodeName = "/";
		Node currentNode = outermostDir;
		for (int i = 1; i < input.size(); i++) {
			String[] currentInput = input.get(i)
					.split(" ");
			if (currentInput[0].equals("dir")) {
				currentNode.nodes.add(new Node(currentNode, currentInput[1]));
			} else if (currentInput[0].equals("$")) {
				if (currentInput[1].equals("cd")) {
					if (currentInput[2].equals("/")) {
						currentNode = outermostDir;
					} else if (currentInput[2].equals("..")) {
						currentNode = currentNode.previousNode;
					} else {
						for (Node n : currentNode.nodes) {
							if (n.nodeName.equals(currentInput[2])) {
								currentNode = n;
							}
						}
					}
				}
			} else {
				currentNode.addFile(currentInput[1], Integer.parseInt(currentInput[0]));
			}
		}
		return outermostDir;
	}

	public static void main(String[] args) throws IOException {
		part1();
		part2();
	}
}
