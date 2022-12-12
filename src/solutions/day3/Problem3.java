package solutions.day3;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author Yalesan Thayalan {@literal <yalesan2006@outlook.com>}
 */
public class Problem3 {
	public static void part2() throws IOException {
		final int[] prime =
				{ 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101 };
		Scanner scanner = new Scanner(Paths.get("testdata/Problem3data.txt"));
		int totalSum = 0;
		final int referencePrime = 239;

		while (scanner.hasNextLine()) {

			String firstRucksack = scanner.nextLine();
			String secondRucksack = scanner.nextLine();
			String thirdRucksack = scanner.nextLine();
			long[] totalUpperCaseProduct = { 1, 1, 1, 1, 1, 1, 1 };
			long[] totalLowerCaseProduct = { 1, 1, 1, 1, 1, 1, 1 };

			for (int i = 0; i < firstRucksack.length(); i++) {
				char currentChar = firstRucksack.charAt(i);
				int priority = getPriority(currentChar);
				if (priority > 26) {
					if (i <= 6) {
						totalUpperCaseProduct[0] *= prime[(priority - 26) - 1];
					} else if (i <= 13) {
						totalUpperCaseProduct[1] *= prime[(priority - 26) - 1];
					} else if (i <= 20) {
						totalUpperCaseProduct[2] *= prime[(priority - 26) - 1];
					} else if (i <= 26) {
						totalUpperCaseProduct[3] *= prime[(priority - 26) - 1];
					} else if (i <= 31) {
						totalUpperCaseProduct[4] *= prime[(priority - 26) - 1];
					} else if (i <= 36) {
						totalUpperCaseProduct[5] *= prime[(priority - 26) - 1];
					} else {
						totalUpperCaseProduct[6] *= prime[(priority - 26) - 1];
					}

				} else {

					if (i <= 6) {
						totalLowerCaseProduct[0] *= prime[priority - 1];
					} else if (i <= 13) {
						totalLowerCaseProduct[1] *= prime[priority - 1];
					} else if (i <= 20) {
						totalLowerCaseProduct[2] *= prime[priority - 1];
					} else if (i <= 26) {
						totalLowerCaseProduct[3] *= prime[priority - 1];
					} else if (i <= 31) {
						totalLowerCaseProduct[4] *= prime[priority - 1];
					} else if (i <= 36){
						totalLowerCaseProduct[5] *= prime[priority - 1];
					} else {
						totalLowerCaseProduct[6] *= prime[priority - 1];
					}
				}
			}

			for (int i = 0; i < 7; i++) {
				if (totalUpperCaseProduct[i] == 1) {
					totalUpperCaseProduct[i] = 239;
				}
				if (totalLowerCaseProduct[i] == 1) {
					totalLowerCaseProduct[i] = 239;
				}
			}

			long[] newUppercaseProduct = { 1, 1 , 1};
			long[] newLowerCaseProduct = { 1, 1, 1};
			for (int i = 0; i < secondRucksack.length(); i++) {
				char currentChar = secondRucksack.charAt(i);
				int priority = getPriority(currentChar);

				if (priority > 26) {
					boolean doesContain = totalUpperCaseProduct[0] % prime[(priority - 26) - 1] == 0
							|| totalUpperCaseProduct[1] % prime[(priority - 26) - 1] == 0
							|| totalUpperCaseProduct[2] % prime[(priority - 26) - 1] == 0
							|| totalUpperCaseProduct[3] % prime[(priority - 26) - 1] == 0
							|| totalUpperCaseProduct[4] % prime[(priority - 26) - 1] == 0
							|| totalUpperCaseProduct[5] % prime[(priority - 26) - 1] == 0
							|| totalUpperCaseProduct[6] % prime[(priority - 26) - 1] == 0;

					if (doesContain) {
						if (newUppercaseProduct[0] <= 41137805) {
							newUppercaseProduct[0] *= prime[(priority - 26) - 1];
						} else if (newUppercaseProduct[1] <= 41137805){
							newUppercaseProduct[1] *= prime[(priority - 26) - 1];
						} else {
							newUppercaseProduct[2] *= prime[(priority - 26) - 1];
						}
					}
				} else {
					boolean doesContain = totalLowerCaseProduct[0] % prime[priority - 1] == 0
							|| totalLowerCaseProduct[1] % prime[priority - 1] == 0
							|| totalLowerCaseProduct[2] % prime[priority - 1] == 0
							|| totalLowerCaseProduct[3] % prime[priority - 1] == 0
							|| totalLowerCaseProduct[4] % prime[priority - 1] == 0
							|| totalLowerCaseProduct[5] % prime[priority - 1] == 0
							|| totalLowerCaseProduct[6] % prime[priority - 1] == 0;

					if (doesContain) {
						if (newLowerCaseProduct[0] <= 41137805) {
							newLowerCaseProduct[0] *= prime[priority - 1];
						} else if (newLowerCaseProduct[1] <= 41137805){
							newLowerCaseProduct[1] *= prime[priority - 1];
						} else {
							newLowerCaseProduct[2] *= prime[priority - 1];
						}
					}
				}
			}

			outer:
			for (int i = 0; i < thirdRucksack.length(); i++) {
				char currentChar = thirdRucksack.charAt(i);
				int priority = getPriority(currentChar);

				boolean doesContain;
				if (priority > 26) {
					doesContain = newUppercaseProduct[0] % prime[(priority - 26) - 1] == 0
							|| newUppercaseProduct[1] % prime[(priority - 26) - 1] == 0
							|| newUppercaseProduct[2] % prime[(priority - 26) - 1] == 0;
				} else {
					doesContain = newLowerCaseProduct[0] % prime[priority - 1] == 0
							|| newLowerCaseProduct[1] % prime[priority - 1] == 0
							|| newLowerCaseProduct[2] % prime[priority - 1] == 0;					;
				}
				if (doesContain) {
					totalSum += priority;
					break outer;
				}
			}
		}

		System.out.println(totalSum);

	}

	public static int getPriority(char c) {
		return (Character.isUpperCase(c)) ? c - 'A' + 27 : c - 'a' + 1;
	}

	public static void part1() throws IOException {
		final int[] prime =
				{ 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101 };
		Scanner scanner = new Scanner(Paths.get("testdata/Problem3data.txt"));
		int totalSum = 0;

		while (scanner.hasNextLine()) {
			String rucksack = scanner.nextLine();
			String firstCompartment = rucksack.substring(0, rucksack.length() / 2);
			long[] totalUpperCaseProduct = { 1, 1 };
			long[] totalLowerCaseProduct = { 1, 1 };

			String secondCompartment = rucksack.substring(rucksack.length() / 2);

			for (int i = 0; i < firstCompartment.length(); i++) {
				char currentChar = rucksack.charAt(i);
				int priority = getPriority(currentChar);
				if (priority > 26) {
					if (i <= 10) {
						totalUpperCaseProduct[0] *= prime[(priority - 26) - 1];
					} else {
						totalUpperCaseProduct[1] *= prime[(priority - 26) - 1];
					}

				} else {

					if (i <= 10) {
						totalLowerCaseProduct[0] *= prime[priority - 1];
					} else {
						totalLowerCaseProduct[1] *= prime[priority - 1];
					}
				}
			}

			outer:
			for (int i = 0; i < secondCompartment.length(); i++) {
				char currentChar = secondCompartment.charAt(i);
				int priority = getPriority(currentChar);

				if (priority > 26) {
					if (totalUpperCaseProduct[0] % prime[(priority - 26) - 1] == 0
							|| totalUpperCaseProduct[1] % prime[(priority - 26) - 1] == 0) {
						totalSum += priority;
						break outer;
					}
				} else {
					if (totalLowerCaseProduct[0] % prime[priority - 1] == 0
							|| totalLowerCaseProduct[1] % prime[priority - 1] == 0) {
						totalSum += priority;
						break outer;
					}
				}
			}
		}
		System.out.println(totalSum);
	}

	public static void main(String[] args) throws IOException {
		part1();
		part2();
	}

}
