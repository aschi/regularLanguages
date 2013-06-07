package ch.zhaw.regularLanguages.dfa;

import java.util.HashMap;
import java.util.Map;

import ch.zhaw.regularLanguages.languages.Symbol;

public class TransitionTable {
	Map<Symbol, State> transitionTable;
	
	public TransitionTable(){
		transitionTable = new HashMap<Symbol, State>();
	}
	
	public void addTransition(Symbol symbol, State state){
		transitionTable.put(symbol, state);
	}
	
	public void updateTransition(Symbol symbol, State state){
		transitionTable.remove(symbol);
		transitionTable.put(symbol, state);
	}
	
	/**
	 * Remove all links to the old target and replace it with a link to a new target
	 * @param oldTarget
	 * @param newTarget
	 */
	public void replaceTarget(State oldTarget, State newTarget){
		for(Symbol s : transitionTable.keySet()){
			if(transitionTable.get(s) == oldTarget){
				transitionTable.remove(s);
				transitionTable.put(s, newTarget);
			}
		}
	}
	
	public State process(Symbol symbol){
		return transitionTable.get(symbol);		
	}
}
