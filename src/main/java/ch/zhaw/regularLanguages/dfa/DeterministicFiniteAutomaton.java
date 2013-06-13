package ch.zhaw.regularLanguages.dfa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ch.zhaw.regularLanguages.graphicalOutput.GraphvizRenderable;
import ch.zhaw.regularLanguages.languages.LanguageHelper;

public class DeterministicFiniteAutomaton implements GraphvizRenderable{
	private List<State> states;
	private State startState;
	private List<State> acceptingStates;
	private List<Character> alphabet;
	
	//temp used to avoid endless loops in minimisation loop
	private List<State> alreadyProcessed;
	
	public DeterministicFiniteAutomaton(){
		initSimpleAutomaton();
	}	

	public DeterministicFiniteAutomaton(List<State> states, State startState,
			List<State> acceptingStates, List<Character> alphabet) {
		super();
		
		if(alphabet.isEmpty()){
			throw new IllegalArgumentException("Alphabet can not be empty!");
		}
		if(states.isEmpty()){
			throw new IllegalArgumentException("States can not be empty!");	
		}
		if(startState == null){
			throw new IllegalArgumentException("Start state can not be null!");				
		}
		
		this.states = states;
		this.startState = startState;
		this.acceptingStates = acceptingStates;
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

	public List<State> getAcceptingStates() {
		return acceptingStates;
	}

	public List<Character> getAlphabet() {
		return alphabet;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public void setAcceptingStates(List<State> acceptingStates) {
		this.acceptingStates = acceptingStates;
	}

	public void setAlphabet(List<Character> alphabet) {
		this.alphabet = alphabet;
	}

	/**
	 * 
	 */
	public void initSimpleAutomaton(){
		alphabet = LanguageHelper.generateSymbolList("ab");
		
		states = new ArrayList<State>();
		acceptingStates = new ArrayList<State>();
		
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
		startState = q0;
	}
	
	public State process(List<Character> input) throws Exception{
		State current = this.startState;
		//System.out.println("Start: " + current);
		for(int i = 0;i < input.size();i++){
			if(current.getTransitionTable().getTransitionTable().keySet().size() != alphabet.size()){
				throw new Exception("TransitionTable is inconsistent!!!");
			}
			
			
			if(current != null){
				current = current.process(input.get(i));
				//System.out.println("Step " + i + ":" + current);
			}
		}
		return current;
	}
	
	public void changeLink(int origin, Character character, int target){
		states.get(origin).getTransitionTable().updateTransition(character, states.get(target));
	}
	
	public boolean isAcceptingState(State state){
		if(acceptingStates != null && acceptingStates.contains(state)){
			return true;
		}else{
			return false;
		}
	}
	
	public void minimizeAutomaton(){
		//remove unreachable states
		State  reachable;
		Set<State> reachableStates = new HashSet<State>();
		
		alreadyProcessed = new LinkedList<State>();
		
		reachableStates.add(this.startState);
		for(State current : reachableStates){
			
			for(int i = 0;i < alphabet.size();i++){
				if(current != null){
					reachable = current.process(alphabet.get(i));
					reachableStates.add(reachable);
					//System.out.println("Step " + i + ":" + current);
				}
			}
		}
	}
	
	public Set<State> getChildren(State s){
		if(!alreadyProcessed.contains(s)){
			Set<State> temp = new HashSet<State>();
			State tar;
			for(int i = 0;i < alphabet.size();i++){
				if(s != null){
					tar = s.process(alphabet.get(i));
					temp.addAll(getChildren(tar));
				}
			}
			alreadyProcessed.add(s);
			return temp;
		}else{
			return null;
		}
	}
	
	@Override
	public Object clone(){
		List<State> states = new ArrayList<State>();
		State startState;
		List<State> acceptingStates = new ArrayList<State>();
		List<Character> alphabet;
		
		
		//use the same alphabet
		alphabet = getAlphabet();
		
		//copy states
		for(int n = 0; n < getStates().size(); n++){
			State s = getStates().get(n);
			states.add(new State(s.getId()));
		}
		
		//copy transitionTable
		for(int n = 0; n < getStates().size(); n++){
			State s = getStates().get(n);
			
			TransitionTable tt = new TransitionTable();
			for(Character c : s.getTransitionTable().transitionTable.keySet()){
				int targetIndex = getStates().indexOf(s.getTransitionTable().transitionTable.get(c));
				tt.addTransition(c, states.get(targetIndex));
			}
			
			states.get(n).setTransitionTable(tt);
		}
		
		//startState
		startState = states.get(getStates().indexOf(getStartState()));
		
		//acceptingStates
		for(State s : getAcceptingStates()){
			if(getStates().indexOf(s) == -1){
				System.out.println("accepting state not in state list!!");
			}
			acceptingStates.add(states.get(getStates().indexOf(s)));
		}
		
		return new DeterministicFiniteAutomaton(states, startState,
				acceptingStates, alphabet);
	}
		
	public String generateDotString(){
		
		String output = "digraph G {" + System.getProperty("line.separator");
		for(State s : states){
			for(Character c : s.getTransitionTable().getTransitionTable().keySet()){
				output+= s + " -> " + s.getTransitionTable().process(c) + " [label=\""+ c +"\"]"+ System.getProperty("line.separator");
			}
		}
		for(State s : acceptingStates){
			output+= s + " [peripheries=2]" + System.getProperty("line.separator");
		}
		
		output += "}";
		
		return output;
	}
}
