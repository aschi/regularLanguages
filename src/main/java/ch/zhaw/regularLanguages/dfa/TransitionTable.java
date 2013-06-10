package ch.zhaw.regularLanguages.dfa;

import java.util.HashMap;
import java.util.Map;

public class TransitionTable {
	Map<Character, State> transitionTable;
	
	public TransitionTable(){
		transitionTable = new HashMap<Character, State>();
	}
	
	public void addTransition(Character character, State state){
		transitionTable.put(character, state);
	}
	
	public void updateTransition(Character character, State state){
		transitionTable.remove(character);
		transitionTable.put(character, state);
	}
	
	/**
	 * Remove all links to the old target and replace it with a link to a new target
	 * @param oldTarget
	 * @param newTarget
	 */
	public void replaceTarget(State oldTarget, State newTarget){
		for(Character c : transitionTable.keySet()){
			if(transitionTable.get(c) == oldTarget){
				transitionTable.remove(c);
				transitionTable.put(c, newTarget);
			}
		}
	}
	
	public State process(Character character){
		return transitionTable.get(character);		
	}
	
	public Map<Character, State> getTransitionTable(){
		return transitionTable;
	}
}
