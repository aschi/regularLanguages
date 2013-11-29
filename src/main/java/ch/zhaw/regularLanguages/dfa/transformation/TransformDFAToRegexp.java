package ch.zhaw.regularLanguages.dfa.transformation;

import java.util.Set;

import ch.zhaw.regularLanguages.dfa.DeterministicFiniteAutomaton;
import ch.zhaw.regularLanguages.dfa.State;


public class TransformDFAToRegexp implements Transformer<DeterministicFiniteAutomaton, String>{
	private final String EMPTY = "#";
	private final String EPSILON = "()";
	
	
	@Override
	public String transform(DeterministicFiniteAutomaton input) {
		String[][][] R = initR(input);
		transitiveClosure(R);
		return buildRegexp(R, input);
	}
	
	private String[][][] initR(DeterministicFiniteAutomaton input){
		/*
	for i = 1 to n:
	  for j = 1 to n:
	    if i == j:
	      R[i,j,0] := ε
	    else:
	      R[i,j,0] := ∅
	    for a in Σ:
	      if trans(i, a, j):
	        R[i,j,0] := R[i,j,0] + a
        */
		
		Set<State> states = input.getStates();
		String[][][] R = new String[states.size()+1][states.size()+1][states.size()+1];
		StringBuffer b;
		
		int i = 1;
		for(State s : states){
			int j = 1;
			for(State t : states){
				if(s.equals(t)){
					R[i][j][0] = EPSILON;
				}else{
					b = new StringBuffer();
					b.append(EMPTY);
					for(char c : input.getAlphabet()){
						if(s.process(c).equals(t)){
							b.append('+');
							b.append(c);
						}
					}
					R[i][j][0] = b.toString();
				}
				j++;
			}
			i++;
		}
		
		return R;
	}
	
	private void transitiveClosure(String[][][] R){
		/*
		 for k = 1 to n:
		  for i = 1 to n:
		    for j = 1 to n:
		      R[i,j,k] := R[i,j,k-1] + R[i,k,k-1] . star(R[k,k,k-1]) . R(k,j,k-1)
		 */
		for(int k=1;k < R[0][0].length;k++){
			for(int i=1;i < R[0][0].length;i++){
				for(int j=1;j < R[0][0].length;j++){
					R[i][j][k] = R[i][j][k-1] +"+"+ R[i][k][k-1] + star(R[k][k][k-1] + R[k][j][k-1]);
				}
			}	
		}	
	}
	
	private String star(String in){
		return "("+in+")*";
	}
	
	/*
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
	*/
	
	private String buildRegexp(String[][][] R, DeterministicFiniteAutomaton input){
		/*
	 	e := ∅
		for i = 1 to n:
		  if final(i):
		    e := e + R[s,i,n]

		 */
		
		StringBuffer b = new StringBuffer();
		b.append(EMPTY);
		
		int i = 1;
		for(State s : input.getStates()){
			if(input.getAcceptingStates().contains(s)){
				b.append('+');
				b.append(R[1][1][input.getStates().size()]);
			}
			i++;
		}
		
		return b.toString();
	}
	
}
