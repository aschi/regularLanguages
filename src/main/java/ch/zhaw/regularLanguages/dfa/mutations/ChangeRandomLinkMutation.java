package ch.zhaw.regularLanguages.dfa.mutations;

import java.util.Random;

import ch.zhaw.regularLanguages.dfa.DeterministicFiniteAutomaton;
import ch.zhaw.regularLanguages.dfa.State;

public class ChangeRandomLinkMutation implements RandomMutation{

	@Override
	public boolean mutate(DeterministicFiniteAutomaton dfa) {
		Random rnd = new Random();
		
		Character c = dfa.getAlphabet()[rnd.nextInt(dfa.getAlphabet().length)];
		State origin = dfa.getState(new State("q"+rnd.nextInt(dfa.getStates().size())));
		State target = dfa.getState(new State("q"+rnd.nextInt(dfa.getStates().size())));
		
		dfa.changeLink(origin, c, target);
		
		//return true as it can not fail
		return true;
	}

}
