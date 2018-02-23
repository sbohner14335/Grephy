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
			String line = scanner.nextLine().toLowerCase();
			// Determine action after receiving user input.
			if (line.startsWith("grep ")) {
				// Check if the user wants to use NFA.
				String[] splited = line.split("\\s+");
				// Ensure the user entered 3 arguments after the grep command.
				if (splited.length == 4) {
					// Verify which automata is being specified, either NFA or DFA.
					if (splited[1].equals("-n") || splited[1].equals("-d")) {
						// TODO: Check for REGEX then...
						ReadFile file = new ReadFile();
						file.readFile(splited[3]);
						// Check if the user want to use NFA.
						if (splited[1] == "-n") {
							System.out.println("NFA");
						// Check if the user want to use DFA.
						} else if (splited[1] == "-d") {
							System.out.println("DFA");
						}
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
