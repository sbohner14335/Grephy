package grephy;

import java.util.*;

public class NFA {
	String regex;  // Pass the regex string to the NFA object.
	// The 5 tuple for an NFA.
    ArrayList<Integer> states = new ArrayList<Integer>();
    ArrayList<String> alphabet = new ArrayList<String>();
    ArrayList<HashMap<String, Integer>> delta = new ArrayList<HashMap<String, Integer>>();  // All transitions for all states.
	static final int START_STATE = 0;
    ArrayList<Integer> acceptedStates = new ArrayList<Integer>();
    
    public NFA(String regex) {
    	this.regex = regex;
    	this.states.add(START_STATE);  // Add the inital state to the list of states.
    	this.acceptedStates.add(START_STATE);  // Add the inital state to the list of accepted states.
    	this.createNFA(regex);
    }

	// Constructs an NFA from a given regular expression.
	public void createNFA(String regex) {
		learnAlphabet();
		// Loop through the regex string and interpret each character.
		int currState = START_STATE;
		Boolean openParens = false;
		String betweenParens = "";
		String prevChar = ""; // Used to store the previous character.
		String nextChar = "";
		for (int i = 1; i < regex.length()+1; i++) {
			String currChar = regex.substring(i-1, i);
			if (i != regex.length()) {
				nextChar = regex.substring(i, i+1); // This is out of bounds on the last character, so we use this condition.
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
				// TODO: Pass last character(s) as argument.
			} else if (currChar.equals("+")) {
				// TODO: Create another state using "" transition.
			} else {
				this.states.add(++currState);  // Add another state to the set of states.
				if (!nextChar.equals("*") || !nextChar.equals("+")) {
				    HashMap<String, Integer> transition = new HashMap<String, Integer>();
				    // Create a transition for this character to the next state and add the new transition to the delta function array list.
					transition.put(currChar, currState);
					this.delta.add(transition);
					// Clear the accepted states and add the most recent state created an accepted state.
					this.acceptedStates.clear();
					this.acceptedStates.add(currState);
				}
			}
			prevChar = currChar;
		}
		System.out.println(this.states);
		System.out.println(this.acceptedStates);
		System.out.println(this.delta);
	}
	
	// Learn the alphabet from a given regular expression.
	public void learnAlphabet() {
		String regexChars = regex.replaceAll("\\*", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\+", "");
		// Loop through each character of the regex.
		for (int i = 0; i < regexChars.length(); i++) {
			// Determine if each character is contained within the line.
			String character = regexChars.substring(i, i+1);
			// Ensure the character is not already contained in the set.
			if (!this.alphabet.contains(character)) {
				this.alphabet.add(character);
			}
		}
	}
}
