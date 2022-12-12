package solutions.day6;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Yalesan Thayalan {@literal <yalesan2006@outlook.com>}
 */
public class Problem6 {
	public static void part1() throws IOException {
		final int[] prime =
				{ 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101 };
		Scanner scanner = new Scanner(Paths.get("testdata/Problem6data.txt"));
		String data = scanner.nextLine();
		long product = prime[asc(data.charAt(0))] * prime[asc(data.charAt(1))] * prime[asc(data.charAt(2))];
		for (int i = 3; i < data.length() - 3; i++) {
			int currentNum = prime[asc(data.charAt(i))];
			if (product % currentNum != 0) {
				if (data.charAt(i - 3) != data.charAt(i - 2) && data.charAt(i - 1) != data.charAt(i - 2)
						&& data.charAt(i - 3) != data.charAt(i - 1)) {
					System.out.println(i + 1);
					break;
				}
			}
			product = product / prime[asc(data.charAt(i - 3))];
			product = product * currentNum;
		}
	}

	public static int asc(char c) {
		return c - 'a';
	}

	public static void part2() throws IOException {
		Scanner scanner = new Scanner(Paths.get("testdata/Problem6data.txt"));
		String data = scanner.nextLine();
		for (int i = 0; i < data.length() - 14; i++) {
			String line = data.substring(i, i+14);
			String[] split = line.split("");
			Set<String> mySet = new HashSet<>(Arrays.asList(split));
			if (mySet.size() == 14) {
				System.out.println(i + 14);
				break;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		part1();
		part2();
	}
}
