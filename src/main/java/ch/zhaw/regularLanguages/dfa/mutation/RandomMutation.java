package ch.zhaw.regularLanguages.dfa.mutation;

import ch.zhaw.regularLanguages.dfa.DeterministicFiniteAutomaton;

public interface RandomMutation {
	public boolean mutate(DeterministicFiniteAutomaton dfa);
}
