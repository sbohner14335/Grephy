package grephy;

import java.io.*;
import java.util.*;

public class ReadFile {
	// Scanner created for reading a file.
	private Scanner fileScanner;
	
	// Responsible for handling the opening of a specified file.
	public void openFile() {
		try {
			fileScanner = new Scanner(new File("src/test/resources/test1.txt"));
		} catch (Exception e) {
			System.out.println("File not found.");
		}
	}
	// Reads each line of the given file.
	public void readFile() {
		while (fileScanner.hasNext()) {
			String line = fileScanner.nextLine();
			System.out.println(line);
		}
	}
	
	public void closeFile() {
		fileScanner.close();
	}

}
