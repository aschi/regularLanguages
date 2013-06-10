package ch.zhaw.regularLanguages.dfa.mutations;

import java.util.List;
import java.util.Random;

import ch.zhaw.regularLanguages.dfa.DeterministicFiniteAutomaton;
import ch.zhaw.regularLanguages.dfa.State;

public class RemoveRandomAcceptingStateMutation implements RandomMutation{

	@Override
	public boolean mutate(DeterministicFiniteAutomaton dfa) {
		Random rnd = new Random();
		
		List<State> acceptStates = dfa.getAcceptingStates();
		
		if(acceptStates.size() <=1){
			return false;
		}else{
			acceptStates.remove(rnd.nextInt(acceptStates.size()));
			return true;
		}
	}

}
