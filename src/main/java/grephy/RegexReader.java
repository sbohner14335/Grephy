package grephy;

import java.io.*;
import java.util.*;

public class RegexReader {
	// Scanner created for reading a file.
	private Scanner fileScanner;
	String automata;
	
	// Constructor created for the regex and automata type.
	public RegexReader(String a) {
		this.automata = a;
	}
	
	// Reads each line of the given file.
	public void readFile(String file, String regex) {
		openFile(file);
		ingestRegex(regex); // Regex -> NFA
		while (fileScanner.hasNext()) {
			String line = fileScanner.nextLine();
			System.out.println(line);
			// TODO: Logic for validating each line of the file. (REGEX -> NFA -> DFA -> test by line)
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
		for (int i = 1; i < regex.length()+1; i++) {
			String currChar = regex.substring(i-1, i);
			String nextChar = regex.substring(i, i+1); // This is out of bounds on the last character.
			System.out.println("Current- " + currChar + "  Next- " + nextChar);
			
			if (currChar.equals(")")) {
				ingestRegex(betweenParens);
				betweenParens = "";
				openParens = false;
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
	
	public void ingestSplat(String string) {
		
	}
	// Closes the file scanner.
	public void closeFile() {
		fileScanner.close();
	}

}
