package ch.zhaw.regularLanguages.dfa;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import ch.zhaw.regularLanguages.dfa.mutations.MutationRegister;
import ch.zhaw.regularLanguages.evolution.Mutable;

public class RandomDeterministicFiniteAutomaton extends DeterministicFiniteAutomaton implements Mutable{
	private MutationRegister mr = new MutationRegister();
	
	
	/**
	 * Generates a random state machine.
	 * 
	 * Number of generated states: between 1 and noSymbols * 2
	 * Number of accepting states: noStates / 5. If there are less then 5 states we start with 1
	 * 
	 * @param alphabet list of symbols
	 * @return a random deterministic finite automaton for the given alphabet
	 */
	public RandomDeterministicFiniteAutomaton(char[] alphabet, int complexity){
		Random rnd = new Random();
		int noSymbols = alphabet.length;
		int maxStates = noSymbols*complexity*2;
		int noStates = rnd.nextInt(maxStates)+1;
		int noAcceptingStates = (noStates < 5 ? 1 : rnd.nextInt(noStates / 5));
		
		Set<State> states = new HashSet<State>();
		Set<State> acceptingStates = new HashSet<State>();
		
		for(int i = 0; i < noStates;i++){
			State state = new State("q"+i);
			states.add(state);
		}
		
		State[] stateArr = states.toArray(new State[0]);
		
		for(int i = 0; i < noStates;i++){
			TransitionTable tt = new TransitionTable();
			for(int n = 0;n < noSymbols;n++){
				tt.addTransition(alphabet[n], stateArr[rnd.nextInt(noStates)]);
			}
			stateArr[i].setTransitionTable(tt);
		}
		
		for(int i = 0;i < noAcceptingStates;i++){
			State state;
			do{
				state = stateArr[rnd.nextInt(noStates)];
			}while(acceptingStates.contains(state));
			
			acceptingStates.add(state);
		}
		
		setStates(states);
		setStartState(stateArr[0]);
		setAcceptingStates(acceptingStates);
		setAlphabet(alphabet);
		
	}

	
	
	public RandomDeterministicFiniteAutomaton(DeterministicFiniteAutomaton dfa) {
		setAcceptingStates(dfa.getAcceptingStates());
		setAlphabet(dfa.getAlphabet());
		setStartState(dfa.getStartState());
		setStates(dfa.getStates());
	}

	@Override
	public void mutate(int nochanges) {
		if(mr == null){
			mr = new MutationRegister();
		}
		
		boolean check = false;
		do{
			check = mr.mutate(this);
		}while(check == false);
	}
	
	@Override
	public Object clone(){
		DeterministicFiniteAutomaton dfa = (DeterministicFiniteAutomaton)super.clone();
		return new RandomDeterministicFiniteAutomaton(dfa);
	}
		
}
