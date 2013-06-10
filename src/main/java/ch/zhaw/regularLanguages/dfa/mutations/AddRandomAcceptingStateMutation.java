package ch.zhaw.regularLanguages.dfa.mutations;

import java.util.List;
import java.util.Random;

import ch.zhaw.regularLanguages.dfa.DeterministicFiniteAutomaton;
import ch.zhaw.regularLanguages.dfa.State;

public class AddRandomAcceptingStateMutation implements RandomMutation{

	@Override
	public boolean mutate(DeterministicFiniteAutomaton dfa) {
		Random rnd = new Random();
		
		List<State> states = dfa.getStates();
		State state = null;
		
		do{
			state = states.get(rnd.nextInt(states.size()));
		}while(dfa.getAcceptingStates().contains(state));
		
		dfa.getAcceptingStates().add(state);
		
		//return true as it can not fail
		return true;
	}

}
