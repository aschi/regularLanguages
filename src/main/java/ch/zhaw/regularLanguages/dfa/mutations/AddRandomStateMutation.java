package ch.zhaw.regularLanguages.dfa.mutations;

import java.util.List;
import java.util.Random;

import ch.zhaw.regularLanguages.dfa.DeterministicFiniteAutomaton;
import ch.zhaw.regularLanguages.dfa.State;
import ch.zhaw.regularLanguages.dfa.TransitionTable;

public class AddRandomStateMutation implements RandomMutation{
	@Override
	public boolean mutate(DeterministicFiniteAutomaton dfa) {
		Random rnd = new Random();
		
		List<State> states = dfa.getStates();
		List<Character> alphabet = dfa.getAlphabet();
		
		int noSymbols = alphabet.size();
		int i = states.size();
		
		State state = new State("q"+i);
		TransitionTable tt = new TransitionTable();
		
		//generate transition table		
		for(int n = 0;n < noSymbols;n++){
			tt.addTransition(alphabet.get(n), states.get(rnd.nextInt(i)));
		}
		
		//add the new state
		state.setTransitionTable(tt);
		states.add(state);
		
		int avg = calcNOAverageIncomingEdges(states);
		for(int n = 0;n < ((avg/2) + rnd.nextInt(avg*2 - avg/2 + 1));n++){
			dfa.changeLink(rnd.nextInt((i<1?1:i-1)), alphabet.get(rnd.nextInt(noSymbols)), i);
		}
		
		//return true as it can not fail
		return true;
	}
	
	private int calcNOAverageIncomingEdges(List<State> states){
		int[] counter = new int[states.size()];
		
		for(State s : states){
			for(Character c : s.getTransitionTable().getTransitionTable().keySet()){
				int i = states.indexOf(s.getTransitionTable().getTransitionTable().get(c));
				counter[i]++;
			}
		}
		
		int total = 0;
		for(int c : counter){
			total+=c;
		}
		
		int avg = total/states.size();
		
		return avg;
	}
}
