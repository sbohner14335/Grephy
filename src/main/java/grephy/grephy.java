package grephy;

import java.util.*;

public class Grephy {
	
	public static void main(String[] args) {
		// Introduction
		System.out.println("Welcome to Grephy, a variation of the grep utility!\n\n" + "Use the format \"grep REGEX file.txt\"\nAlso, please ensure that there is no white space within your regular expression. :)");
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
				if (splited.length == 3) {
					// Set variables from the array.
					String regex = splited[1];
					String fileName = splited[2];
					// Create an instantiation of the RegexReader object, passing through the expression.
					RegexReader reader = new RegexReader();
					reader.readFile(fileName, regex);
				} else {
					System.out.println("Invalid command: not enough arguments, please use the format \"grep REGEX file.txt\"");
				}
			} else {
				System.out.println("Invalid command: please use the format \"grep REGEX file.txt\"");
			}
		}
	}
	
}
