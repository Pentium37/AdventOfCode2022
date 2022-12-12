package util;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Yalesan Thayalan {@literal <yalesan2006@outlook.com>}
 */
public class Files {
	public static List<String> readFile(String fileName) {
		List<String> lines = new ArrayList<>();

		try {
			java.nio.file.Files.lines(Paths.get(fileName))
					.forEach(lines::add);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return lines;
	}
}
