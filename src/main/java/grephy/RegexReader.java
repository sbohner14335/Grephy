package grephy;

import java.io.*;
import java.util.*;

public class RegexReader {
	// Scanner created for reading a file.
	private Scanner fileScanner;
	
	// Reads each line of the given file, checks the characters from the regular expression and .
	public void readFile(String file, String regex) {
		openFile(file);
		validateLines(file, regex);
		closeFile();
	}
	// Responsible for handling the opening of a specified file.
	public void openFile(String file) {
		try {
			fileScanner = new Scanner(new File("src/test/resources/" + file));
		} catch (Exception e) {
			// If the file is not found, let the user know and restart.
			System.out.println("File " + "\"" + file + "\"" + " not found.");
			Grephy.init();
		}
	}
	// Closes the file scanner.
	public void closeFile() {
		fileScanner.close();
	}
	// Ensures that at least of the characters from the regex is on the line we are about to test.
	public void validateLines(String file, String regex) {
		if (fileScanner.hasNext()) {
			NFA nfa = new NFA(regex);  // If the file has contents, create an NFA for the regular expression.
			// Use this NFA to create a DFA.
			DFA dfa = new DFA(nfa.states, nfa.alphabet, nfa.delta, nfa.acceptedStates);
			// If a character is found on a line, this becomes true.
			Boolean charFound = false;
			while (fileScanner.hasNext()) {
				String line = fileScanner.nextLine();
				// Get all REGEX characters that are not parens, + or kleene star.
				String regexChars = regex.replaceAll("\\*", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\+", "");
				// Loop through each character of the regex.
				for (int i = 0; i < regexChars.length(); i++) {
					// Determine if each character is contained within the line.
					String character = regexChars.substring(i, i+1);
					if (line.contains(character)) {
						// If the character is found, we can test that line.
						charFound = true;
						break;
					} else {
						charFound = false;
					}
				}
				// If characters matched on a given line, test that line.
				if (charFound) {
					// Test the line utilizing a test method (DFA).
					if (dfa.testLine(line)) {
						System.out.println(line);
					}
				}
			}
		} else {
			System.out.println("There is no content in this file to match.");
		}
	}
}
