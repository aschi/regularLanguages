package ch.zhaw.regularLanguages.dfa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ch.zhaw.regularLanguages.languages.Symbol;

public class RandomDeterministicFiniteAutomaton extends DeterministicFiniteAutomaton implements Mutable{
	
	/**
	 * Generates a random state machine.
	 * 
	 * Number of generated states: between 1 and noSymbols * 2
	 * Number of accept states: noStates / 5. If there are less then 5 states we start with 1
	 * 
	 * @param alphabet list of symbols
	 * @return a random deterministic finite automaton for the given alphabet
	 */
	public static DeterministicFiniteAutomaton generate(List<Symbol> alphabet){
		Random rnd = new Random();
		
		int noSymbols = alphabet.size();
		int noStates = rnd.nextInt(noSymbols*2)+1;
		int noAcceptStates = rnd.nextInt((noStates < 5 ? 1 : noSymbols / 5));
		
		List<State> states = new ArrayList<State>();
		List<State> acceptStates = new ArrayList<State>();
		
		for(int i = 0; i < noStates;i++){
			State state = new State("q"+i);
			states.add(state);
		}
		
		for(int i = 0; i < noStates;i++){
			TransitionTable tt = new TransitionTable();
			for(int n = 0;n < noSymbols;n++){
				tt.addTransition(alphabet.get(n), states.get(rnd.nextInt(noStates)));
			}
			states.get(i).setTransitionTable(tt);
		}
		
		for(int i = 0;i < noAcceptStates;i++){
			State state;
			do{
				state = states.get(rnd.nextInt(noStates));
			}while(acceptStates.contains(state));
			
			acceptStates.add(state);
		}
		
		return new DeterministicFiniteAutomaton(states, states.get(0), acceptStates, alphabet);
	}

	@Override
	public void mutate(int nochanges) {
		
		
	}
	
	private void changeRandomLink(){
		Random rnd = new Random();
		
		Symbol s = getAlphabet().get(rnd.nextInt(getAlphabet().size()-1));
		int origin = rnd.nextInt(getStates().size());
		int target = rnd.nextInt(getStates().size());
		
		changeLink(origin, s, target);
	}
	
	private void addRandomState(){
		
	}
	
	private void removeRandomState(){
		
	}
	
}
