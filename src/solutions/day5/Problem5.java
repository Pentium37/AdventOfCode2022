package solutions.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Yalesan Thayalan {@literal <yalesan2006@outlook.com>}
 */
public class Problem5 {
	public static void part1() {
		List<String> moves = new ArrayList<>();
		List<String> storage = new ArrayList<>();
		List<Stack<Character>> stackList = new ArrayList<>();

		try {
			Files.lines(Paths.get("testdata/Problem5data.txt"))
					.forEach(x -> {
						if (!x.contains("[") && x.contains("move")) {
							moves.add(x);
						} else if (x.contains("[")) {
							storage.add(x);
						}
					});
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		for (int i = 0; i < storage.size(); i++) {
			String currentRow = storage.get(i);
			StringBuilder filteredRow = new StringBuilder();
			for (int j = 1; j < currentRow.length(); j += 4) {
				filteredRow.append(currentRow.charAt(j));
			}
			storage.set(i, filteredRow.toString());
		}

		for (int i = 0; i < storage.size(); i++) {
			String currentRow = storage.get(storage.size() - i - 1);
			for (int j = 0; j < currentRow.length(); j++) {
				char currentChar = currentRow.charAt(j);
				if (currentChar != ' ') {
					while (j >= stackList.size()) {
						stackList.add(new Stack<>());
					}
					stackList.get(j)
							.push(currentChar);
				}
			}
		}

		List<int[]> newMoves = new ArrayList<>();
		for (String move : moves) {
			String[] moveStrip = move.split(" ");
			int[] numberToMove =
					{ Integer.parseInt(moveStrip[1]), Integer.parseInt(moveStrip[3]), Integer.parseInt(moveStrip[5]) };
			newMoves.add(numberToMove);
		}

		for (int i = 0; i < newMoves.size(); i++) {
			int[] current = newMoves.get(i);
			for (int j = 0; j < current[0]; j++) {
				stackList.get(current[2] - 1)
						.push(stackList.get(current[1] - 1)
								.pop());
			}
		}

		for (Stack s : stackList) {
			System.out.print(s.pop());
		}
		System.out.println();
	}

	public static void part2() {

		List<String> moves = new ArrayList<>();
		List<String> storage = new ArrayList<>();
		String[] stacks = new String[9];
		for (int i = 0; i < stacks.length; i++) {
			stacks[i] = "";
		}

		try {
			Files.lines(Paths.get("testdata/Problem5data.txt"))
					.forEach(x -> {
						if (!x.contains("[") && x.contains("move")) {
							moves.add(x);
						} else if (x.contains("[")) {
							storage.add(x);
						}
					});
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		for (int i = 0; i < storage.size(); i++) {
			String currentRow = storage.get(i);
			StringBuilder filteredRow = new StringBuilder();
			for (int j = 1; j < currentRow.length(); j += 4) {
				filteredRow.append(currentRow.charAt(j));
			}
			storage.set(i, filteredRow.toString());
		}

		for (int i = 0; i < storage.size(); i++) {
			String currentRow = storage.get(i);
			for (int j = 0; j < currentRow.length(); j++) {
				char currentChar = currentRow.charAt(j);
				if (currentChar != ' ') {
					stacks[j] += currentChar;
				}
			}
		}

		System.out.println(Arrays.toString(stacks));

		List<int[]> newMoves = new ArrayList<>();
		for (String move : moves) {
			String[] moveStrip = move.split(" ");
			int[] numberToMove =
					{ Integer.parseInt(moveStrip[1]), Integer.parseInt(moveStrip[3]), Integer.parseInt(moveStrip[5]) };
			newMoves.add(numberToMove);
		}

		for (int i = 0; i < newMoves.size(); i++) {
			int[] current = newMoves.get(i);
			String sub = stacks[current[1]-1].substring(0, current[0]);
			stacks[current[1]-1] = stacks[current[1]-1].substring(current[0]);
			stacks[current[2] - 1] = sub + stacks[current[2]-1];
		}

		for (String s : stacks) {
			System.out.print(s.charAt(0));
		}
	}

	public static void main(String[] args) {
		part1();
		part2();
	}
}
