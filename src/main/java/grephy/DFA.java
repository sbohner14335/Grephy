package grephy;

import java.util.*;

public class DFA {
	// The 5 tuple for an DFA.
    ArrayList<Integer> states = new ArrayList<Integer>();
    ArrayList<String> alphabet = new ArrayList<String>();
    ArrayList<HashMap<String, Integer>> delta = new ArrayList<HashMap<String, Integer>>();  // All transitions for all states.
	static final int START_STATE = 0;
    ArrayList<Integer> acceptedStates = new ArrayList<Integer>();
    
	public DFA(ArrayList<Integer> states, ArrayList<String> alphabet, ArrayList<HashMap<String, Integer>> delta, ArrayList<Integer> acceptedStates) {
		this.states = states;
		this.alphabet = alphabet;
		this.delta = delta;
		this.acceptedStates = acceptedStates;
		minimizeDFA();
	}
	
	// Remove unnecessary states and transitions.
	public void minimizeDFA() {
		for (int i = 0; i < delta.size(); i++) {
			for (int j = 0; j < this.alphabet.size(); j++) {
				if (delta.get(i).get(this.alphabet.get(j)) == null) {
					delta.get(i).remove(this.alphabet.get(j));
				}
			}
		}
		System.out.println(this.delta);
	}
	
	// TODO: Test method
	public Boolean testLine(String line) {
		int currState = START_STATE;
		// Start at the initial state and loop through each character of the line.
		for (int i = 0; i < line.length(); i++) {
			String character = line.substring(i, i+1);
			// If the delta of the current state has a character that maps to another state, set the current state to the state it is mapped to.
			if (delta.get(currState).containsKey(character)) {
				currState = delta.get(currState).get(character);
			} else {
				currState = START_STATE;
			}
			// Check if the state is contained within the accepted states.
			if (this.acceptedStates.contains(currState)) {
				return true;
			}
		}
		return false;
	}

}
