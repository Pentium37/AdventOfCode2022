package solutions.day4;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Yalesan Thayalan {@literal <yalesan2006@outlook.com>}
 */
public class Problem4 {
	public static void part1() throws IOException {
		Scanner scanner = new Scanner(Paths.get("testdata/Problem4data.txt"));

		int fullyContainedPairs = 0;
		while (scanner.hasNextLine()) {
			String[] idRanges = scanner.nextLine()
					.split(",");

			// boundaries for each of the elves -> LB & UB
			List<Integer> firstElf = Arrays.stream(idRanges[0].split("-"))
					.map(Integer::parseInt)
					.collect(Collectors.toCollection(ArrayList::new));

			List<Integer> secondElf = Arrays.stream(idRanges[1].split("-"))
					.map(Integer::parseInt)
					.collect(Collectors.toCollection(ArrayList::new));

			if (firstElf.get(0) >= secondElf.get(0) && firstElf.get(1) <= secondElf.get(1)) {
				fullyContainedPairs++;
			} else if (secondElf.get(0) >= firstElf.get(0) && secondElf.get(1) <= firstElf.get(1)) {
				fullyContainedPairs++;
			}
		}
		System.out.println(fullyContainedPairs);
	}

	public static void part2() throws IOException {
		Scanner scanner = new Scanner(Paths.get("testdata/Problem4data.txt"));

		int partiallyContainedPairs = 0;
		while (scanner.hasNextLine()) {
			String[] idRanges = scanner.nextLine()
					.split(",");

			// boundaries for each of the elves -> LB & UB
			List<Integer> firstElf = Arrays.stream(idRanges[0].split("-"))
					.map(Integer::parseInt)
					.collect(Collectors.toCollection(ArrayList::new));

			List<Integer> secondElf = Arrays.stream(idRanges[1].split("-"))
					.map(Integer::parseInt)
					.collect(Collectors.toCollection(ArrayList::new));

			if ((firstElf.get(0) >= secondElf.get(0) && firstElf.get(0) <= secondElf.get(1)) || (
					firstElf.get(1) <= secondElf.get(1) && firstElf.get(1) >= secondElf.get(0))) {
				partiallyContainedPairs++;
			} else if ((secondElf.get(0) >= firstElf.get(0) && secondElf.get(0) <= firstElf.get(1))
					|| (secondElf.get(1) <= firstElf.get(1) && secondElf.get(1) >= firstElf.get(0))) {
				partiallyContainedPairs++;
			}
		} System.out.println(partiallyContainedPairs);
	}

	public static void main(String[] args) throws IOException {
		part2();
	}
}
