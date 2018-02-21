package grephy;

import java.util.*;

public class Grephy {
	
	public static void main(String[] args) {
		// Reading user input.
		Scanner scanner = new Scanner(System.in);
		// Introduction
		System.out.println("Welcome to Grephy, a variation of the grep utility!\n\n" + "Type \"help\" for well ... help :)");
		
		while (scanner.hasNext()) {
			// Read the command the user issued.
			String line = scanner.nextLine().toLowerCase();
			// Determine action after receiving user input.
			switch (line) {
				case "help":  //TODO Create a help method;
				  			  break;
				case "grep":  ReadFile file = new ReadFile();
							  file.openFile();
							  file.readFile();
							  file.closeFile();
							  break;
				default: System.out.println("Invalid command, type \"help\" for a list of commands.");
			}
		}
	}
	
}
