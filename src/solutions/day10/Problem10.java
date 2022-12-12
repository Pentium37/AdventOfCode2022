package solutions.day10;

import util.Files;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yalesan Thayalan {@literal <yalesan2006@outlook.com>}
 */
public class Problem10 {
	static int cycleCount = 0;

	public static void part1() {
		List<String> instructionSet = Files.readFile("testdata/Problem10data.txt");
		List<Integer> signalStrengths = new ArrayList<>();
		int X = 1;

		int cycleCount = 0;
		for (int i = 0; i < instructionSet.size(); i++) {
			String currentInstruction = instructionSet.get(i);
			if (currentInstruction.equals("noop")) {
				cycleCount++;
				if ((cycleCount - 20) % 40 == 0) {
					signalStrengths.add(X * cycleCount);
				}
				continue;
			}

			String[] dividedInstruction = currentInstruction.split(" ");
			cycleCount++;
			if ((cycleCount - 20) % 40 == 0) {
				signalStrengths.add(X * cycleCount);
			}
			cycleCount++;
			if ((cycleCount - 20) % 40 == 0) {
				signalStrengths.add(X * cycleCount);
			}
			X += Integer.parseInt(dividedInstruction[1]);
		}

		int sum = signalStrengths.stream()
				.mapToInt(Integer::intValue)
				.sum();
		System.out.println(sum);
	}

	public static void part2() {
		List<String> instructionSet = Files.readFile("testdata/Problem10data.txt");
		StringBuilder consoleOutput = new StringBuilder();
		int X = 1;

		int rowCount = 0;
		for (int i = 0; i < instructionSet.size(); i++) {
			String currentInstruction = instructionSet.get(i);
			if (currentInstruction.equals("noop")) {
				consoleOutput(X, consoleOutput);
				cycleCount++;
				continue;
			}

			String[] dividedInstruction = currentInstruction.split(" ");
			consoleOutput(X, consoleOutput);
			cycleCount++;

			consoleOutput(X, consoleOutput);
			cycleCount++;
			X += Integer.parseInt(dividedInstruction[1]);
		}
		System.out.println(consoleOutput);
	}

	public static void consoleOutput(int X, StringBuilder consoleOutput) {
		if (cycleCount == X || cycleCount == X - 1 || cycleCount == X + 1) {
			consoleOutput.append("##");
		} else {
			consoleOutput.append("  ");
		}
		if (cycleCount % 40 == 0 && cycleCount != 0) {
			consoleOutput.append("\n##");
			cycleCount = 0;
		}
	}

	public static void main(String[] args) {
		part1();
		part2();
	}
}
