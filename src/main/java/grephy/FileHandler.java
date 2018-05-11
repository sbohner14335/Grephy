package grephy;

import java.io.*;
import java.util.*;

import org.omg.Messaging.SyncScopeHelper;

public class FileHandler {
	Scanner fileScanner;
    ArrayList<String> fileAlphabet = new ArrayList<String>();
	String automata;
	
	public FileHandler(String automata) {
		this.automata = automata;
	}
	
	/*
	 * Reads each line of the given file and performs operations on each line.
	 * @param String file - a given file to test.
	 * @param String regex - a given regex to test a file with.
	 */
	public void readFile(String file, String regex) {
		openFile(file);
		learnFileAlphabet(file);
		validateFile(regex);
		closeFile();
	}
	
	/*
	 * Responsible for handling the opening of a specified file.
	 * @param String file - a given file to test.
	 */
	public void openFile(String file) {
		try {
			fileScanner = new Scanner(new File("src/test/resources/" + file));
		} catch (Exception e) {
			// If the file is not found, let the user know and restart.
			System.out.println("File " + "\"" + file + "\"" + " not found.");
			Grephy.init();
		}
	}
	
	/*
	 * Learns the alphabet of the given file.
	 * @param String file - a given file.
	 */
	public void learnFileAlphabet(String file) {
		// If the file has contents.
		if (fileScanner.hasNext()) {
			while (fileScanner.hasNext()) {
				String line = fileScanner.nextLine();
				for (int i = 0; i < line.length(); i++) {
					String character = line.substring(i, i+1);
					// Ensure the character is not already contained in the set.
					if (!this.fileAlphabet.contains(character)) {
						this.fileAlphabet.add(character);
					}
				}
			}
			// After the scanner is exhausted we must reconstruct it to refill the buffer.
			openFile(file);
		} else {
			System.out.println("There is no content in this file to test.");
		}
	}
	
	/*
	 * Ensures that at least of the characters from the regex is on the line we are about to test.
	 * @param String regex - the regular expression string from user input.
	 */
	public void validateFile(String regex) {
		NFA nfa = new NFA(regex);
		// Use this NFA to create a DFA.
		DFA dfa = new DFA(nfa.states, nfa.alphabet, nfa.delta, nfa.acceptedStates);
		// Test the line utilizing a test method (DFA).
		while (fileScanner.hasNext()) {
			String line = fileScanner.nextLine();
			if (dfa.testLine(line)) {
				System.out.println(line);
			}
		}
	}
	
	// Closes the file scanner.
	public void closeFile() {
		fileScanner.close();
	}
}
