package grephy;

import java.util.*;

public class Grephy {
	
	public static void main(String[] args) {
		// Introduction
		System.out.println("Welcome to Grephy, a variation of the grep utility!\n\n" + "Use the format \"grep [-n NFA-file][-d DFA-file] REGEX file.txt\" where [-n NFA-file][-d DFA-file] are optional parameters.\nAlso, please ensure that there is no white space within your regular expression. :)");
		init();
	}
	
	// Responsible for initializing Grephy and the cli.
	public static void init() {
		// Reading user input.
		Scanner scanner = new Scanner(System.in);
		
		while (scanner.hasNext()) {
			// Read the command the user issued.
			String line = scanner.nextLine();
			// Determine action after receiving user input.
			if (line.toLowerCase().startsWith("grep ")) {
				// Set variables for the command by splitting commands on whitespace into an array.
				String[] splited = line.split("\\s+");
				// Ensure the user entered 3 arguments after the grep command.
				if (splited.length == 4) {
					String automata = splited[1];
					String regex = splited[2];
					String fileName = splited[3];
					// Verify which automata is being specified, either NFA or DFA.
					if (automata.toLowerCase().equals("-n") || automata.toLowerCase().equals("-d")) {
						// Create an instantiation of the RegexReader object, passing through the filename, expression and the automata type specified.
						FileHandler reader = new FileHandler(automata);
						reader.readFile(fileName, regex);
					} else {
						System.out.println("\"" + automata + "\"" + " is not a valid automata type.\nUse -n for NFA and -d for DFA.");
					}
				} else if (splited.length == 3) {
					String regex = splited[1];
					String fileName = splited[2];
					FileHandler reader = new FileHandler(null);
					reader.readFile(fileName, regex);
				} else {
					System.out.println("Invalid command: not enough arguments, please use the format \"grep [-n NFA-file][-d DFA-file] REGEX file.txt\"");
				}
			} else {
				System.out.println("Invalid command: please use the format \"grep [-n NFA-file][-d DFA-file] REGEX file.txt\"");
			}
		}
	}
	
}
