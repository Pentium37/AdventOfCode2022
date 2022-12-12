package solutions.day11;

import util.Files;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yalesan Thayalan {@literal <yalesan2006@outlook.com>}
 */
public class Problem11 {
	public static void part1() {
		List<String> input = Files.readFile("testdata/Problem11data.txt");
		List<List<Integer>> monkeyData = new ArrayList<>();
		List<String> monkeyOperations = new ArrayList<>();
		List<Integer> monkeyTest = new ArrayList<>();
		List<Integer> throwTrue = new ArrayList<>();
		List<Integer> throwFalse = new ArrayList<>();
		int[] monkeyInspections = { 0, 0, 0, 0, 0, 0, 0, 0 };

		for (int i = 0; i < input.size(); i++) {
			String[] line = input.get(i)
					.split(" ");
			if ((i - 1) % 7 == 0) {
				List<Integer> data = new ArrayList<>();
				for (int j = 0; j < line.length; j++) {
					if (line[j].matches("[0-9]+,")) {
						data.add(Integer.parseInt(line[j].substring(0, line[j].length() - 1)));
					} else if (line[j].matches("[0-9]+")) {
						data.add(Integer.parseInt(line[j]));
					}
				}
				monkeyData.add(data);
			} else if ((i - 2) % 7 == 0) {
				monkeyOperations.add(line[4 + 2] + line[5 + 2]);
			} else if ((i - 3) % 7 == 0) {
				monkeyTest.add(Integer.parseInt(line[3 + 2]));
			} else if ((i - 4) % 7 == 0) {
				throwTrue.add(Integer.parseInt(line[5 + 4]));
			} else if ((i - 5) % 7 == 0) {
				throwFalse.add(Integer.parseInt(line[5 + 4]));
			}
		}

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < monkeyOperations.size(); j++) {

				int currentWorry;
				if (monkeyData.get(j)
						.size() != 0) {
					for (int k = 0; k < monkeyData.get(j)
							.size(); k++) {
						currentWorry = monkeyData.get(j)
								.get(k);
						String add = monkeyOperations.get(j)
								.substring(1);

						int operand;
						if (add.equals("old")) {
							operand = currentWorry;
						} else {
							operand = Integer.parseInt(add);
						}

						switch (monkeyOperations.get(j)
								.charAt(0)) {
							case '+' -> currentWorry = currentWorry + operand;
							case '*' -> currentWorry = currentWorry * operand;
						}

						monkeyInspections[j]++;
						currentWorry = Math.floorDiv(currentWorry, 3);
						if (currentWorry % monkeyTest.get(j) == 0) {
							monkeyData.get(throwTrue.get(j))
									.add(currentWorry);
						} else {
							monkeyData.get(throwFalse.get(j))
									.add(currentWorry);
						}
					}
					monkeyData.get(j)
							.clear();

				}
			}
		}
		int largest = 0;
		int secondLargest = 0;
		for (final int monkeyInspection : monkeyInspections) {
			if (monkeyInspection > largest) {
				secondLargest = largest;
				largest = monkeyInspection;
			} else if (monkeyInspection > secondLargest) {
				secondLargest = monkeyInspection;
			}
		}
		int monkeyBusiness = largest * secondLargest;
		System.out.println(monkeyBusiness);
	}

	public static void part2() {
		List<String> input = Files.readFile("testdata/Problem11data.txt");
		List<List<Long>> monkeyData = new ArrayList<>();
		List<String> monkeyOperations = new ArrayList<>();
		List<Integer> monkeyTest = new ArrayList<>();
		List<Integer> throwTrue = new ArrayList<>();
		List<Integer> throwFalse = new ArrayList<>();
		int[] monkeyInspections = { 0, 0, 0, 0, 0, 0, 0, 0 };

		for (int i = 0; i < input.size(); i++) {
			String[] line = input.get(i)
					.split(" ");
			if ((i - 1) % 7 == 0) {
				List<Long> data = new ArrayList<>();
				for (int j = 0; j < line.length; j++) {
					if (line[j].matches("[0-9]+,")) {
						data.add(Long.parseLong(line[j].substring(0, line[j].length() - 1)));
					} else if (line[j].matches("[0-9]+")) {
						data.add(Long.parseLong(line[j]));
					}
				}
				monkeyData.add(data);
			} else if ((i - 2) % 7 == 0) {
				monkeyOperations.add(line[4 + 2] + line[5 + 2]);
			} else if ((i - 3) % 7 == 0) {
				monkeyTest.add(Integer.parseInt(line[3 + 2]));
			} else if ((i - 4) % 7 == 0) {
				throwTrue.add(Integer.parseInt(line[5 + 4]));
			} else if ((i - 5) % 7 == 0) {
				throwFalse.add(Integer.parseInt(line[5 + 4]));
			}
		}

		long totalMod = monkeyTest.stream().reduce(1, (a, b) -> a * b);

		for (int i = 0; i < 10000; i++) {
			for (int j = 0; j < monkeyOperations.size(); j++) {

				long currentWorry;
				if (monkeyData.get(j)
						.size() != 0) {
					for (int k = 0; k < monkeyData.get(j)
							.size(); k++) {
						currentWorry = monkeyData.get(j)
								.get(k);
						String add = monkeyOperations.get(j)
								.substring(1);

						long operand;
						if (add.equals("old")) {
							operand = currentWorry;
						} else {
							operand = Integer.parseInt(add);
						}

						switch (monkeyOperations.get(j)
								.charAt(0)) {
							case '+' -> {
								currentWorry = currentWorry + operand;
							}
							case '*' -> {
								if (currentWorry == 0) {
									currentWorry++;
								}
								currentWorry = currentWorry * operand;
							}
						}


						monkeyInspections[j]++;
						if (currentWorry % monkeyTest.get(j) == 0) {
							monkeyData.get(throwTrue.get(j))
									.add(currentWorry % totalMod);
						} else {
							monkeyData.get(throwFalse.get(j))
									.add(currentWorry % totalMod);
						}
					}
					monkeyData.get(j)
							.clear();
				}
			}
		}

		int largest = 0;
		int secondLargest = 0;
		for (final int monkeyInspection : monkeyInspections) {
			if (monkeyInspection > largest) {
				secondLargest = largest;
				largest = monkeyInspection;
			} else if (monkeyInspection > secondLargest) {
				secondLargest = monkeyInspection;
			}
		}

		long monkeyBusiness = (long) largest * secondLargest;
		System.out.println(monkeyBusiness);
	}

	public static void main(String[] args) {
		part1();
		part2();
	}
}
