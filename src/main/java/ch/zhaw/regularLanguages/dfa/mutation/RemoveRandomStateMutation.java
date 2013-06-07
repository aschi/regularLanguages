package ch.zhaw.regularLanguages.dfa.mutation;

import java.util.List;
import java.util.Random;

import ch.zhaw.regularLanguages.dfa.DeterministicFiniteAutomaton;
import ch.zhaw.regularLanguages.dfa.State;

public class RemoveRandomStateMutation implements RandomMutation {

	@Override
	public boolean mutate(DeterministicFiniteAutomaton dfa) {
		Random rnd = new Random();
		
		List<State> states = dfa.getStates();
		
		if(states.size() <= 1){
			return false;
		}else{
			//index of the state to be removed
			int i = rnd.nextInt(states.size());
			State oldState = states.get(i);
			
			//replace links to the to-be-removed state from all other states
			for(State s : states){
				//TODO: Evaluate if self link or random link is to be prefered
				//s.getTransitionTable().replaceTarget(oldState, s); //self link
				
				//get a replacement state
				State newState = null;
				do{
					newState = states.get(rnd.nextInt(states.size()));	
				}while(newState == oldState);
				s.getTransitionTable().replaceTarget(oldState, newState); // random link
			}
			
			//remove the old state
			states.remove(oldState);
			
			return true;
		}
	}
}
