package grephy;

import java.util.*;

public class Grephy {
	
	public static void main(String[] args) {
		// Introduction
		System.out.println("Welcome to Grephy, a variation of the grep utility!\n\n" + "Please use the format \"grep [-n NFA-file][-d DFA-file] REGEX file.txt\" :)");
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
				String automata = splited[1];
				String regex = splited[2];
				String fileName = splited[3];
				// Ensure the user entered 3 arguments after the grep command.
				if (splited.length == 4) {
					// Verify which automata is being specified, either NFA or DFA.
					if (automata.toLowerCase().equals("-n") || automata.toLowerCase().equals("-d")) {
						RegexReader file = new RegexReader(regex, automata);
						file.readFile(fileName);
					} else {
						System.out.println("\"" + splited[1] + "\"" + " is not a valid automata type.\nUse -n for NFA and -d for DFA.");
					}
				} else {
					System.out.println("Invalid command: not enough arguments, please use the format \"grep [-n NFA-file][-d DFA-file] REGEX file.txt\"");
				}
			} else {
				System.out.println("Invalid command: please use the format \"grep [-n NFA-file][-d DFA-file] REGEX file.txt\"");
			}
		}
	}
	
}
