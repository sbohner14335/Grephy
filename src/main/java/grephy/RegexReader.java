package grephy;

import java.io.*;
import java.util.*;

public class RegexReader {
	// Scanner created for reading a file.
	private Scanner fileScanner;
	String regex;
	String automata;
	
	// Constructor created for the regex and automata type.
	public RegexReader (String reg, String a) {
		this.regex = reg;
		this.automata = a;
	}
	
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
			// TODO: Logic for validating each line of the file. (REGEX -> NFA -> DFA -> test by line)
		}
		closeFile();
	}

}
