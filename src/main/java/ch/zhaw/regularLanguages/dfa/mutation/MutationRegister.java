package ch.zhaw.regularLanguages.dfa.mutation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ch.zhaw.regularLanguages.dfa.DeterministicFiniteAutomaton;

public class MutationRegister implements RandomMutation{
	private List<RandomMutation> mutList = new ArrayList<RandomMutation>();
	
	public MutationRegister() {
		mutList.add(new AddRandomStateMutation());
		mutList.add(new ChangeRandomLinkMutation());
		mutList.add(new RemoveRandomStateMutation());
		mutList.add(new RemoveRandomAcceptingStateMutation());
		mutList.add(new AddRandomAcceptingStateMutation());
	}

	@Override
	public boolean mutate(DeterministicFiniteAutomaton dfa) {
		Random rnd = new Random();
		return mutList.get(rnd.nextInt(mutList.size())).mutate(dfa);
	}
}
