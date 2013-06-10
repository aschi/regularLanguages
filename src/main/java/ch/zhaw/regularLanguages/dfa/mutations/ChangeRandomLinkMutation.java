package ch.zhaw.regularLanguages.dfa.mutations;

import java.util.Random;

import ch.zhaw.regularLanguages.dfa.DeterministicFiniteAutomaton;

public class ChangeRandomLinkMutation implements RandomMutation{

	@Override
	public boolean mutate(DeterministicFiniteAutomaton dfa) {
		Random rnd = new Random();
		
		Character c = dfa.getAlphabet().get(rnd.nextInt(dfa.getAlphabet().size()-1));
		int origin = rnd.nextInt(dfa.getStates().size());
		int target = rnd.nextInt(dfa.getStates().size());
		
		dfa.changeLink(origin, c, target);
		
		//return true as it can not fail
		return true;
	}

}
