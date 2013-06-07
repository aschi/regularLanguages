package ch.zhaw.regularLanguages.dfa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ch.zhaw.regularLanguages.languages.Symbol;

public class DeterministicFiniteAutomaton {
	private List<State> states;
	private State startState;
	private List<State> acceptStates;
	private List<Symbol> alphabet;
	
	public DeterministicFiniteAutomaton(){
		initSimpleAutomaton();
	}	

	public DeterministicFiniteAutomaton(List<State> states, State startState,
			List<State> acceptStates, List<Symbol> alphabet) {
		super();
		this.states = states;
		this.startState = startState;
		this.acceptStates = acceptStates;
		this.alphabet = alphabet;
	}


	public State getStartState() {
		return startState;
	}

	public void setStartState(State startState) {
		this.startState = startState;
	}

	public List<State> getStates() {
		return states;
	}

	public List<State> getAcceptStates() {
		return acceptStates;
	}

	public List<Symbol> getAlphabet() {
		return alphabet;
	}

	
	/**
	 * 
	 */
	public void initSimpleAutomaton(){
		alphabet = new ArrayList<Symbol>();
		alphabet.add(Symbol.epsilon);
		alphabet.add(Symbol.a);
		alphabet.add(Symbol.b);
		
		states = new ArrayList<State>();
		acceptStates = new ArrayList<State>();
		
		State q0 = new State("q0");
		State q1 = new State("q1");
		
		TransitionTable ttq0 = new TransitionTable();
		ttq0.addTransition(Symbol.epsilon, q0);
		ttq0.addTransition(Symbol.a, q0);
		ttq0.addTransition(Symbol.b, q1);
		
		TransitionTable ttq1 = new TransitionTable();
		ttq1.addTransition(Symbol.epsilon, q1);
		ttq1.addTransition(Symbol.a, q0);
		ttq1.addTransition(Symbol.b, q1);
		
		q0.setTransitionTable(ttq0);
		q1.setTransitionTable(ttq1);
		
		states.add(q0);
		states.add(q1);
		
		acceptStates.add(q1);
		startState = q0;
	}
	
	public State process(List<Symbol> input){
		State current = this.startState;
		System.out.println("Start: " + current);
		for(int i = 0;i < input.size();i++){
			
			if(current != null){
				current = current.process(input.get(i));
				System.out.println("Step " + i + ":" + current);
			}
		}
		return current;
	}
	
	public void changeLink(int origin, Symbol symbol, int target){
		states.get(origin).getTransitionTable().updateTransition(symbol, states.get(target));
	}
	
	public boolean isAcceptState(State state){
		if(acceptStates != null && acceptStates.contains(state)){
			return true;
		}else{
			return false;
		}
	}
	
	public void minimizeAutomaton(){
		//TODO: Implement
	}
}
