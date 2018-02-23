package grephy;

import java.io.*;
import java.util.*;

public class ReadFile {
	// Scanner created for reading a file.
	private Scanner fileScanner;
	
	// Responsible for handling the opening of a specified file.
	public void openFile(String file) {
		try {
			fileScanner = new Scanner(new File("src/test/resources/" + file));
		} catch (Exception e) {
			System.out.println("File " + "\"" + file + "\"" + " not found.");
			Grephy.init();
		}
	}
	// Closes the file scanner.
	public void closeFile() {
		fileScanner.close();
	}
	// Reads each line of the given file.
	public void readFile(String file) {
		openFile(file);
		while (fileScanner.hasNext()) {
			String line = fileScanner.nextLine();
			System.out.println(line);
		}
		closeFile();
	}

}
