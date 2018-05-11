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
    	this.acceptedStates.add(START_STATE);
    	this.createNFA(regex);
    }

	// Constructs an NFA from a given regular expression.
	public void createNFA(String regex) {
		learnRegexAlphabet();
		// Loop through the regex string and interpret each character.
		int currState = START_STATE;
		Boolean openParens = false;
		String betweenParens = "";
		String prevChar = "";
		String nextChar = "";
		for (int i = 1; i < regex.length()+1; i++) {
			String currChar = regex.substring(i-1, i);
			// This is out of bounds on the last character, so we use this condition.
			if (i != regex.length()) {
				nextChar = regex.substring(i, i+1);
			}
			if (currChar.equals("*")) {
				// Kleene star is handled when it is the nextChar in the addTransition function, no actions needed here.
				prevChar = currChar;
				continue;
			} else {  // In this case the current character has to be part of the alphabet.
				currState = addTransition(currState, prevChar, currChar, nextChar);
			}
			prevChar = currChar;
		}
	}
	
	/*
	 * Adds a transition to the delta function for the current state.
	 * @param currState - current state we are in
	 * @param currChar - current character we are looking at in the regular expression.
	 * @param nextChar - the next character we will be looking at in the regular expression.
	 * @return int currState - current state after the computation.
	 * 
	 */
	public int addTransition(int currState, String prevChar, String currChar, String nextChar) {
		// Create a HashMap to interpret the transition for this current state.
	    HashMap<String, Integer> transition = new HashMap<String, Integer>();
		if (!(nextChar.equals("*") || nextChar.equals("+"))) {
			this.states.add(++currState);  // Add another state to the set of states.
			// Add new state to the list of accepted states.
			this.acceptedStates.add(currState);
		}
	    // Create a transition for this character to the next state and add the new transition to the delta function array list.
		if (prevChar.equals("*")) {
			// Get the original transition, modify it then put it back into the delta list.
			transition = this.delta.get(currState-1);
			transition.put(currChar, currState);
			this.delta.remove(currState-1);
		} else {
			transition.put(currChar, currState);
		}
		// Assigns null  to all transitions that are undefined, as per an NFA.
		for (int j = 0; j < this.alphabet.size(); j++) {
			if (!transition.containsKey(this.alphabet.get(j))) {
				transition.put(this.alphabet.get(j), null);
			}
		}
		transition.put("empty", null);
		// Add the set of transitions to the delta function (the index of the ArrayList represents a state for the delta transitions).
		this.delta.add(transition);
		// Remove all previous accepted states.
		for (int i = 0; i < this.acceptedStates.size(); i++) {
			if (!this.acceptedStates.isEmpty() && this.acceptedStates.get(i) != currState) {
				this.acceptedStates.remove(i);
			}
		}
		return currState;
	}
	
	// Learn the alphabet from a given regular expression.
	public void learnRegexAlphabet() {
		String regexChars = regex.replaceAll("\\*", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\+", "");
		// Loop through each character of the regex.
		for (int i = 0; i < regexChars.length(); i++) {
			String character = regexChars.substring(i, i+1);
			// Ensure the character is not already contained in the set.
			if (!this.alphabet.contains(character)) {
				this.alphabet.add(character);
			}
		}
	}
}
