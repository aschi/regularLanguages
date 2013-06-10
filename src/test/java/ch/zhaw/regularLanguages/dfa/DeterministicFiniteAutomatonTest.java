package ch.zhaw.regularLanguages.dfa;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import ch.zhaw.regularLanguages.languages.LanguageHelper;

public class DeterministicFiniteAutomatonTest extends TestCase {
	DeterministicFiniteAutomaton dfa;
	
	public void setUp(){
		dfa = new DeterministicFiniteAutomaton();
		dfa.setAlphabet(LanguageHelper.generateSymbolList("ab"));
				 
		List<State> states = new ArrayList<State>();
		List<State> acceptingStates = new ArrayList<State>();
		
		State q0 = new State("q0");
		State q1 = new State("q1");
		
		TransitionTable ttq0 = new TransitionTable();
		ttq0.addTransition('a', q0);
		ttq0.addTransition('b', q1);
		
		TransitionTable ttq1 = new TransitionTable();
		ttq1.addTransition('a', q0);
		ttq1.addTransition('b', q1);
		
		q0.setTransitionTable(ttq0);
		q1.setTransitionTable(ttq1);
		
		states.add(q0);
		states.add(q1);
		
		acceptingStates.add(q1);
		State startState = q0;
		
		dfa.setStates(states);
		dfa.setAcceptingStates(acceptingStates);
		dfa.setStartState(startState);
	}
	
	public void changeLinkTest(){
		dfa.changeLink(0, 'a', 1);
		int keySetSize = dfa.getStates().get(0).getTransitionTable().getTransitionTable().keySet().size();
		State newTarget = dfa.getStates().get(0).getTransitionTable().process('a');
		assertTrue(newTarget == dfa.getStates().get(1) && keySetSize == 2);
				
	}
	
}
