package grephy;

import java.util.*;

public class Grephy {
	
	public static void main(String[] args) {
		// Reading user input.
		Scanner input = new Scanner(System.in);
		// Introduction
		System.out.println("Welcome to Grephy, a variation of the grep utility!\n" + "Type \"help\" for a list of commands");
		
		if (input.hasNext()) {
			// Read the command the user issued.
			String line = input.nextLine();
			// Determine action after receiving user input.
			switch (line) {
				case "help":  //TODO Create a help method;
				  			  break;
				default: System.out.println("Invalid command, type \"help\" for a list of commands.");
			}
		}
	}
	
}
