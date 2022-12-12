package solutions.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yalesan Thayalan {@literal <yalesan2006@outlook.com>}
 */
public class Problem8 {
	public static void part1() {
		int[][] file = readDataAsMatrix();
		int visited = 4 * file.length - 4;

		for (int i = 1; i < file.length - 1; i++) {
			for (int j = 1; j < file[0].length - 1; j++) {
				int currentNum = file[i][j];

				boolean[] couldBeVisited = { true, true, true, true };
				for (int k = 1; k < file.length; k++) {
					if (j + k < file.length) {
						if (file[i][j + k] >= currentNum) {
							couldBeVisited[0] = false;
						}
					}
					if (i + k < file.length) {
						if (file[i + k][j] >= currentNum) {
							couldBeVisited[2] = false;
						}
					}

					if (j - k < file.length && j - k >= 0) {
						if (file[i][j - k] >= currentNum) {
							couldBeVisited[1] = false;
						}
					}
					if (i - k >= 0) {
						if (file[i - k][j] >= currentNum) {
							couldBeVisited[3] = false;
						}
					}
				}

				for (int k = 0; k < couldBeVisited.length; k++) {
					if (couldBeVisited[k]) {
						visited++;
						break;
					}
				}

			}
		}
		System.out.println(visited);
	}

	public static void part2() {
		int[][] file = readDataAsMatrix();
		int highestScenicScore = 1;

		for (int i = 1; i < file.length - 1; i++) {
			for (int j = 1; j < file[0].length - 1; j++) {
				int currentNum = file[i][j];

				boolean[] couldBeVisited = { true, true, true, true };
				int[] visited = { 0, 0, 0, 0 };
				for (int k = 1; k < file.length; k++) {
					if (j + k < file.length && couldBeVisited[0]) {
						if (file[i][j + k] < currentNum) {
							visited[0]++;
						} else if (file[i][j + k] == currentNum) {
							visited[0]++;
							couldBeVisited[0] = false;
						}
					}
					if (i + k < file.length && couldBeVisited[1]) {
						if (file[i + k][j] < currentNum) {
							visited[1]++;
						} else if (file[i + k][j] == currentNum) {
							visited[1]++;
							couldBeVisited[1] = false;
						}
					}

					if (j - k < file.length && j - k >= 0 && couldBeVisited[2]) {
						if (file[i][j - k] < currentNum) {
							visited[2]++;
						} else if (file[i][j - k] == currentNum) {
							visited[2]++;
							couldBeVisited[2] = false;
						}
					}
					if (i - k >= 0 && couldBeVisited[3]) {
						if (file[i - k][j] < currentNum) {
							visited[3]++;
						} else if (file[i - k][j] == currentNum) {
							visited[3]++;
							couldBeVisited[3] = false;
						}
					}

				}

				int scenicScore = visited[0] * visited[1] * visited[2] * visited[3];
				if (scenicScore > highestScenicScore) {
					highestScenicScore = scenicScore;
				}

			}
		}
		System.out.println(highestScenicScore);
	}

	public static int[][] readDataAsMatrix() {
		List<String> lines = new ArrayList<>();

		try {
			Files.lines(Paths.get("testdata/Problem8data.txt"))
					.forEach(lines::add);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		int[][] matrix = new int[lines.size()][lines.get(0)
				.length()];
		for (int i = 0; i < lines.size(); i++) {
			String currentRow = lines.get(i);
			for (int j = 0; j < currentRow.length(); j++) {
				matrix[i][j] = currentRow.charAt(j) - 48;
			}
		}
		return matrix;
	}

	public static void main(String[] args) {
		part1();
		part2();
	}
}
