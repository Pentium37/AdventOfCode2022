package solutions.day1;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author Yalesan Thayalan {@literal <yalesan2006@outlook.com>}
 */
public class Problem1 {
	public static void problem1() throws IOException {
		Scanner scanner = new Scanner(Paths.get("testdata/Problem1data.txt"));
		int greatestTotal = 0, secondGreatestTotal = 0, thirdGreatestTotal = 0;
		int currentTotal = 0;

		while (scanner.hasNextLine()) {
			String row = scanner.nextLine();
			if (row.isBlank() || row.isEmpty()) {
				if (currentTotal > greatestTotal) {
					thirdGreatestTotal = secondGreatestTotal;
					secondGreatestTotal = greatestTotal;
					greatestTotal = currentTotal;
				} else if (currentTotal > secondGreatestTotal) {
					thirdGreatestTotal = secondGreatestTotal;
					secondGreatestTotal = currentTotal;
				} else if (currentTotal > thirdGreatestTotal) {
					thirdGreatestTotal = currentTotal;
				}
				currentTotal = 0;
			} else {
				int currentRow = Integer.parseInt(row);
				currentTotal += currentRow;
			}
		}
		System.out.println(greatestTotal);
		System.out.println(greatestTotal + secondGreatestTotal + thirdGreatestTotal);
	}

	public static void main(String[] args) throws IOException {
		problem1();
	}
}
