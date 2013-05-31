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
	
	public State process(Symbol symbol){
		return transitionTable.get(symbol);		
	}
}
