package solutions.day2;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author Yalesan Thayalan {@literal <yalesan2006@outlook.com>}
 */
public class Problem2 {
	public static void part1() throws IOException {
		Scanner scanner = new Scanner(Paths.get("testdata/Problem2data.txt"));
		int totalWinPoints = 0;

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			int firstPlay = line.charAt(0) - 64;
			int secondPlay = line.charAt(2) - 87;

			if (firstPlay == secondPlay) {
				totalWinPoints += 3 + secondPlay;
			} else if (firstPlay == secondPlay - 1 || firstPlay - 2 == secondPlay) {
				totalWinPoints += 6 + secondPlay;
			} else {
				totalWinPoints += secondPlay;
			}
		}

		System.out.println(totalWinPoints);
	}

	public static void part2() throws IOException {
		Scanner scanner = new Scanner(Paths.get("testdata/Problem2data.txt"));
		int totalWinPoints = 0;

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			int firstPlay = line.charAt(0) - 64;
			int winRequirement = line.charAt(2) - 87;

			if (winRequirement == 2) {
				totalWinPoints += firstPlay + 3;
			} else if (winRequirement == 3) {
				totalWinPoints += 6 + ((firstPlay == 3) ? 1 : firstPlay + 1);
			} else {
				totalWinPoints += ((firstPlay == 1) ? 3 : firstPlay - 1);
			}
		}

		System.out.println(totalWinPoints);
	}

	public static void main(String[] args) throws IOException {
		part1();
		part2();
	}
}
