package ch.zhaw.regularLanguages.dfa.mutations;

import java.util.List;
import java.util.Random;

import ch.zhaw.regularLanguages.dfa.DeterministicFiniteAutomaton;
import ch.zhaw.regularLanguages.dfa.State;

public class RemoveRandomAcceptingStateMutation implements RandomMutation{

	@Override
	public boolean mutate(DeterministicFiniteAutomaton dfa) {
		Random rnd = new Random();
		
		List<State> acceptingStates = dfa.getAcceptingStates();
		
		if(acceptingStates.size() <=1){
			return false;
		}else{
			acceptingStates.remove(rnd.nextInt(acceptingStates.size()));
			return true;
		}
	}

}
