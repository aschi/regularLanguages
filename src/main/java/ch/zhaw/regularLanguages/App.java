package ch.zhaw.regularLanguages;

import java.util.ArrayList;
import java.util.List;

import ch.zhaw.regularLanguages.dfa.DeterministicFiniteAutomaton;
import ch.zhaw.regularLanguages.dfa.State;
import ch.zhaw.regularLanguages.languages.Symbol;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	DeterministicFiniteAutomaton dfa = new DeterministicFiniteAutomaton();
    	dfa.initSimpleAutomaton();
    	
    	List<Symbol> input = new ArrayList<Symbol>();
    	input.add(Symbol.b);
    	input.add(Symbol.a);
    	input.add(Symbol.b);
    	input.add(Symbol.a);
    	input.add(Symbol.a);
    	input.add(Symbol.a);
    	input.add(Symbol.b);
    	input.add(Symbol.a);
    	input.add(Symbol.b);
    	input.add(Symbol.b);
    	input.add(Symbol.b);
    	
    	State result = dfa.process(input);
        System.out.println(dfa.isAcceptState(result));
    }
}
