package ch.zhaw.regularLanguages.dfa.mutations;

import java.util.List;
import java.util.Random;

import ch.zhaw.regularLanguages.dfa.DeterministicFiniteAutomaton;
import ch.zhaw.regularLanguages.dfa.State;
import ch.zhaw.regularLanguages.dfa.TransitionTable;
import ch.zhaw.regularLanguages.languages.Symbol;

public class AddRandomStateMutation implements RandomMutation{
	@Override
	public boolean mutate(DeterministicFiniteAutomaton dfa) {
		Random rnd = new Random();
		
		List<State> states = dfa.getStates();
		List<Symbol> alphabet = dfa.getAlphabet();
		
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
		
		//add link from a random node to this new one
		dfa.changeLink(rnd.nextInt(i-1), alphabet.get(rnd.nextInt(noSymbols)), i);
		
		//return true as it can not fail
		return true;
	}
}
