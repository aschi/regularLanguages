package ch.zhaw.regularLanguages.evolution;

import java.lang.reflect.InvocationTargetException;

import ch.zhaw.regularLanguages.dfa.DeterministicFiniteAutomaton;
import ch.zhaw.regularLanguages.dfa.State;
import ch.zhaw.regularLanguages.languages.CharArrayWrapper;


public class DeterministicFiniteAutomatonEvolutionCandidate<AUTOMATON extends DeterministicFiniteAutomaton & Mutable> extends EvolutionCandidate<AUTOMATON, ProblemSet<CharArrayWrapper, Boolean>>{
	private char[] alphabet;
	
	public DeterministicFiniteAutomatonEvolutionCandidate(Class<AUTOMATON> classTypeDef, char[] alphabet){
		super();
		this.alphabet = alphabet;
		setClassTypeDef(classTypeDef);
		setObj(initObj());
	}
	
	public DeterministicFiniteAutomatonEvolutionCandidate(Class<AUTOMATON> classTypeDef, char[] alphabet, AUTOMATON obj){
		setClassTypeDef(classTypeDef);
		setObj(obj);
		this.alphabet = alphabet;
	}
	
	@Override
	public int fitness(ProblemSet<CharArrayWrapper, Boolean> problemSet) {
		AUTOMATON obj = getObj();
		
		int c = 0;
		int i = 0;
		for(CharArrayWrapper problem : problemSet.getProblemSet()){
			State state = null;
			state = obj.process(problem.getData());
			boolean isAccepting = obj.isAcceptingState(state);
			if(problemSet.checkSolution(problem, isAccepting)){
				c++;
			}
			i++;
		}
		return c;
	}

	@Override
	public AUTOMATON initObj() {
		Class<AUTOMATON> classTypeDef = getClassTypeDef();
		try {
			return classTypeDef.getConstructor(new Class[] { Class.forName("[C"), Integer.TYPE}).newInstance(new Object[] {alphabet, 2});
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Object cloneWithMutation(){
		AUTOMATON newClone = (AUTOMATON)this.getObj().clone();
		newClone.mutate(1);
		return new DeterministicFiniteAutomatonEvolutionCandidate(getClassTypeDef(), alphabet, newClone);
	}
	
	public boolean stressTest(ProblemSet<CharArrayWrapper, Boolean> stressTestProblems){
		AUTOMATON obj = getObj();
		
		for(CharArrayWrapper problem : stressTestProblems.getProblemSet()){
			State state = null;
			state = obj.process(problem.getData());
			boolean isAccepting = obj.isAcceptingState(state);
			if(!stressTestProblems.checkSolution(problem, isAccepting)){
				return false;
			}
		}
		return true;
	}
}
