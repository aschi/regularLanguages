package ch.zhaw.regularLanguages.dfa.mutation;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import ch.zhaw.regularLanguages.dfa.DeterministicFiniteAutomaton;
import ch.zhaw.regularLanguages.dfa.State;
import ch.zhaw.regularLanguages.dfa.TransitionTable;
import ch.zhaw.regularLanguages.dfa.mutations.ChangeRandomLinkMutation;
import ch.zhaw.regularLanguages.languages.LanguageHelper;

public class ChangeRandomLinkMutationTest extends TestCase {
	DeterministicFiniteAutomaton dfa;
	ChangeRandomLinkMutation crlm;
	
	public void setUp(){
		crlm = new ChangeRandomLinkMutation();
		
	}
	
	public void mutateTest(){
		crlm.mutate(dfa);
	}
	
	
}
