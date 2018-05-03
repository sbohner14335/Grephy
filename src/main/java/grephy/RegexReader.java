package grephy;

import java.io.*;
import java.util.*;

public class RegexReader {
	// Scanner created for reading a file.
	private Scanner fileScanner;
	
	// Reads each line of the given file, checks the characters from the regular expression and .
	public void readFile(String file, String regex) {
		openFile(file);
		Boolean charFound = false;
		while (fileScanner.hasNext()) {
			String line = fileScanner.nextLine();
			// Get all REGEX characters that are not parens or star.
			String regexChars = regex.replaceAll("\\*", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\+", "");
			// Loop through each character of the regex.
			for (int i = 0; i < regexChars.length(); i++) {
				// Determine if each character is contained within the string.
				String character = regexChars.substring(i, i+1);
				if (line.contains(character)) {
					charFound = true;
					break;  // Once we find one character we do not have to check the other characters.
				}
			}
			// If no characters matched with any of the file lines, the regular expression automatically fails.
			if (!charFound) {
				System.out.println("The characters in " + regex + " are not in " + file);
				Grephy.init();
			} else {
				// TODO: Logic for validating each line of the file. (REGEX -> NFA -> DFA -> test by line)
				System.out.println(line);
			}
		}
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
	// Constructs an NFA from a given regular expression.
	public void ingestRegex(String regex) {
		// Loop through the regex string and interpret each character.
		Boolean openParens = false;
		String betweenParens = "";
		String prevChar; // Used to store the previous character.
		String nextChar = "";
		for (int i = 1; i < regex.length()+1; i++) {
			String currChar = regex.substring(i-1, i);
			if (i != regex.length()) {
				nextChar = regex.substring(i, i+1); // This is out of bounds on the last character.
			}
			
			System.out.println("Current- " + currChar + "  Next- " + nextChar);
			
			if (currChar.equals(")")) {
				betweenParens = "";
				openParens = false;
				ingestRegex(betweenParens);
			}
			// Logic for determining whether we are inside parenthesis.
			if (openParens) {
				betweenParens += currChar;  // Continue to build the string inside of the parens.
			// Check if the next character is an open parens
			} else if (currChar.equals("(")) {
				openParens = true;
			} else if (currChar.equals("*")) {
				// TODO: Pass last character as argument.
			} else if (currChar.equals("+")) {
				
			}
			prevChar = currChar;
		}
	}
	// Closes the file scanner.
	public void closeFile() {
		fileScanner.close();
	}

}
