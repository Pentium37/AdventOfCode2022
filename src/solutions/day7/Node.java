package solutions.day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yalesan Thayalan {@literal <yalesan2006@outlook.com>}
 */
public class Node {
	List<Node> nodes;
	String nodeName;
	Map<String, Integer> map;
	int dirSize;
	Node previousNode;

	public Node(Node previousNode, String name) {
		this.previousNode = previousNode;
		this.nodeName = name;
		nodes = new ArrayList<>();
		map = new HashMap<>();
		dirSize = 0;
	}

	public Node() {
		nodes = new ArrayList<>();
		map = new HashMap<>();
		previousNode = null;
		dirSize = 0;
	}

	public void addDir(Node node) {
		this.nodes.add(node);
	}

	public void addToDirSize(int size) {
		if (this.previousNode != null) {
			previousNode.addToDirSize(size);
		}
		this.dirSize += size;
	}

	public void addFile(String x, int size) {
		map.put(x, size);
		addToDirSize(size);
	}
}
