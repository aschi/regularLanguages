package ch.zhaw.regularLanguages.dfa.transformation;

import java.util.Set;

import ch.zhaw.regularLanguages.dfa.DeterministicFiniteAutomaton;
import ch.zhaw.regularLanguages.dfa.State;


public class TransformDFAToRegexp implements Transformer<DeterministicFiniteAutomaton, String>{
	private final char EMPTY = 0;
	private final char EPSILON = 1;
	
	
	@Override
	public String transform(DeterministicFiniteAutomaton input) {
		char[] B = generateB(input);
		String[][] A = generateA(input);
		
		return buildRegexp(A, B);
	}
	
	private char[] generateB(DeterministicFiniteAutomaton input){
		Set<State> states = input.getStates();
		char[] B = new char[states.size()];
		
		int i = 0;
		for(State s : states){
			if(input.getAcceptingStates().contains(s)){
				B[i] = EPSILON;
			}else{
				B[i] = EMPTY;
			}
			i++;
		}
		
		return B;
	}

	private String[][] generateA(DeterministicFiniteAutomaton input){
		Set<State> states = input.getStates();
		
		String[][] A = new String[states.size()][states.size()];
		
		int i = 0;
		int j = 0;
		for(State s : states){
			j=0;
			for(State t : states){
				for(char c : input.getAlphabet()){
					if(s.process(c).equals(t)){
						if(A[i][j].length() == 0 || (A[i][j].length() == 1 && A[i][j].charAt(0) == EMPTY)){
							A[i][j] = String.valueOf(c);
						}else{
							A[i][j] += c;
						}
						
					}else{
						if(A[i][j].length() == 0){
							A[i][j] += EMPTY;
						}
					}
				}
				j++;
			}
			i++;
		}
		
		return null;
	}
	
	private String buildRegexp(String[][] A, char[] B){
		return null;
	}
	
}
