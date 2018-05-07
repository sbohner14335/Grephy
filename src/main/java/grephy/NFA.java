package grephy;

import java.util.*;

public class NFA {
	String regex;  // Pass the regex string to the NFA object.
	// The 5 tuple for an NFA.
    ArrayList<Integer> states = new ArrayList();
    ArrayList<String> alphabet = new ArrayList();
	ArrayList<ArrayList<Integer>> delta = new ArrayList();
	static final int START_STATE = 0;
    ArrayList<Integer> acceptedStates = new ArrayList();
    
    public NFA(String regex) {
    	this.regex = regex;
    	this.createNFA(regex);
    }

	// Constructs an NFA from a given regular expression.
	public void createNFA(String regex) {
		// Recognize the language of the NFA.
		String regexChars = regex.replaceAll("\\*", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\+", "");
		// Loop through each character of the regex.
		for (int i = 0; i < regexChars.length(); i++) {
			// Determine if each character is contained within the line.
			String character = regexChars.substring(i, i+1);
			if (!this.alphabet.contains(character)) {
				this.alphabet.add(character);
			}
		}
		System.out.println(this.alphabet);

		// Loop through the regex string and interpret each character.
		Boolean openParens = false;
		String betweenParens = "";
		String prevChar = ""; // Used to store the previous character.
		String nextChar = "";
		for (int i = 1; i < regex.length()+1; i++) {
			String currChar = regex.substring(i-1, i);
			if (i != regex.length()) {
				nextChar = regex.substring(i, i+1); // This is out of bounds on the last character, so we use a condition.
			}
			
			System.out.println("Previous: " + prevChar + " Current- " + currChar + "  Next- " + nextChar);
			
			if (currChar.equals(")")) {
				betweenParens = "";
				openParens = false;
				createNFA(betweenParens);
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
				// TODO: Create another state using "" transition.
			}
			prevChar = currChar;
		}
	}
	
	// Ensures that at least one of the characters from the regex is on the line we are about to test.
	public void testLine(String line) {
		
	}
}
