package ch.zhaw.regularLanguages.evolution;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ch.zhaw.regularLanguages.dfa.DeterministicFiniteAutomaton;

public class SimpleEvolutionaryAlgorithm<AUTOMATON extends DeterministicFiniteAutomaton & Mutable> implements EvolutionaryAlgorithm<AUTOMATON>{
	private final int CYCLE_LIMIT = 1000;
	private final int NO_START_AUTOMATONS = 1000;
	
	private AUTOMATON winner;
	private Set<AUTOMATON>best;
	private int maxC = 0;
	private int maxFitness;
	
	private List<AUTOMATON> objects; 
	private List<EvolutionCandidate<AUTOMATON>> fitness;
	
	private List<Character> alphabet;
	
	private ProblemSet<List<Character>, Boolean> problemSet;
	long[] counter;
	private Class<AUTOMATON> classTypeDef;
	
	public SimpleEvolutionaryAlgorithm(ProblemSet<List<Character>, Boolean> problemSet, List<Character> alphabet, Class<AUTOMATON> classTypeDef) {
		this.problemSet = problemSet;	
		this.maxFitness = problemSet.getProblemSet().size();
		counter = new long[problemSet.getProblemSet().size()];
		this.classTypeDef = classTypeDef;
		this.alphabet = alphabet;
		initTestList();
	}
	
	public AUTOMATON getWinner(){
		return winner;
	}
	
	private void initTestList(){
		objects = new LinkedList<AUTOMATON>();
		
		for(int i = 0;i < NO_START_AUTOMATONS;i++){
			try {
				objects.add(classTypeDef.getConstructor(new Class[] { List.class, Integer.TYPE}).newInstance(new Object[] {alphabet, 3}));
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Set<AUTOMATON> getBest(){
		return best;
	}
	
	public long[] getCounter(){
		return counter;
	}
	
	public int getMaxC(){
		return maxC;
	}
	
	public int getProblemSetSize(){
		return problemSet.getProblemSet().size();
	}

	@Override
	public void startEvolution() {
		int cycle = 0;
		boolean finalForm = false;
		List <AUTOMATON>newList = null;
		
		while(cycle < CYCLE_LIMIT && finalForm == false){
			System.out.println("Cycle: "+cycle +" objects: " + objects.size() + "newList : " + (newList != null ? newList.size() : "null"));
			
			if(fitness == null){
				fitness = new LinkedList<EvolutionCandidate<AUTOMATON>>();
			}else{
				fitness.clear();
			}
			
			for(AUTOMATON obj : objects){
				fitness.add(new EvolutionCandidate<AUTOMATON>(obj, automatonFitness(obj)));
			}
			
			Collections.sort(fitness);
			
			//take the fitter half and clone & mutate all elements and put them into a new list
			
			if(newList == null){
				newList = new LinkedList<AUTOMATON>();
			}else{
				newList.clear();
			}
			if(best == null){
				best = new HashSet<AUTOMATON>();
			}else{
				best.clear();
			}
			for(int i = 0;i < (fitness.size())/2;i++){
				if(fitness.get(i).getFitness() == maxFitness){
					winner = fitness.get(i).getObj();
					break;
				}else{
					if(fitness.get(i).getFitness() > maxC){
						maxC = fitness.get(i).getFitness();
						System.out.println("new maxC: " + maxC);
						best.add(fitness.get(i).getObj());
					}else if(fitness.get(i).getFitness() == maxC){
						best.add(fitness.get(i).getObj());
					}
					newList.add(fitness.get(i).getObj()); //add old object
					AUTOMATON newClone = (AUTOMATON)fitness.get(i).getObj().clone();
					newClone.mutate(1);
					newList.add(newClone); //add mutated clone
				}
			}
			
			//continue with new list
			objects.clear();
			objects.addAll(newList);

			cycle++;
		}
	}

	public int automatonFitness(AUTOMATON obj){
		int c = 0;
		int i = 0;
		for(List<Character> problem : problemSet.getProblemSet()){
			if(problemSet.checkSolution(problem, obj.isAcceptingState(obj.process(problem)))){
				c++;
				counter[i]++;
			}
			i++;
		}
		return c;
	}

	@Override
	public EvolutionResult evolve(AUTOMATON obj1, AUTOMATON obi2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	@Override
	public EvolutionResult evolve(AUTOMATON obj1,
			AUTOMATON obj2) {
		int c1 = 0;
		int c2 = 0;
		
		
		
		
		int i = 0;
		for(List<Character> problem : problemSet.getProblemSet()){
			if(problemSet.checkSolution(problem, obj1.isAcceptingState(obj1.process(problem)))){
				c1++;
				counter[i]++;
			}
			if(problemSet.checkSolution(problem, obj2.isAcceptingState(obj2.process(problem)))){
				c2++;
				counter[i]++;
			}
			i++;
		}
		
		
		maxC = (c1 > maxC ? c1 : maxC);
		maxC = (c2 > maxC ? c2 : maxC);
		
		if(c1 == problemSet.getProblemSet().size()){
			winner = obj1;
			return EvolutionResult.FINAL_FORM;
		}else if(c2 == problemSet.getProblemSet().size()){
			winner = obj2;
			return EvolutionResult.FINAL_FORM;
		}else{
			if(c1 == c2){
				//add obj2 at the end
				objects.remove(obj2);
				objects.add(obj2);
			}else if(c1 > c2){ //c1 > c2
				//clone obj1 & mutate the new clone. kill obj2 & register the mutated new clone.
				AUTOMATON newClone = (AUTOMATON)obj1.clone();
				newClone.mutate(1);
				
				objects.remove(obj2);
				objects.add(newClone);
			}else{ //c1 < c2
				//clone obj2 & mutate the new clone. kill obj1 & register the mutated new clone.
				AUTOMATON newClone = (AUTOMATON)obj2.clone();
				newClone.mutate(1);
				
				objects.remove(obj1);
				objects.add(newClone);
			}
			return EvolutionResult.IN_PROGRESS;
		}
	}
	*/
}