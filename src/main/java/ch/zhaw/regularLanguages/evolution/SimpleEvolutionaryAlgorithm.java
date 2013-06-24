package ch.zhaw.regularLanguages.evolution;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SimpleEvolutionaryAlgorithm<E extends EvolutionCandidate, PSI, PSO>{
	private final int CYCLE_LIMIT = 1000;
		
	private List<E> candidates;
	
	private E winner;
	
	private int maxFitness;
	private int maxC;
	
	
	private ProblemSet<PSI, PSO> problemSet;
	private ProblemSet<PSI, PSO> stressTestProblems;
	private long[] counter;
	
	public SimpleEvolutionaryAlgorithm(ProblemSet<PSI, PSO> problemSet, ProblemSet<PSI, PSO> stressTestProblems, List<E> candidates) {
		this.problemSet = problemSet;	
		this.candidates = candidates;
		this.maxFitness = problemSet.getProblemSet().size();
		this.stressTestProblems = stressTestProblems;
		
		System.out.println("Max Fitness: " + maxFitness);
		counter = new long[problemSet.getProblemSet().size()];
	}
	
	public E getWinner(){
		return winner;
	}
	
	public int getMaxC(){
		return maxC;
	}
	
	public long[] getCounter(){
		return counter;
	}
	
	public List<E> getCandidates(){
		return candidates;
	}
	
	public int getProblemSetSize(){
		return problemSet.getProblemSet().size();
	}

	public void startEvolution() {
		int cycle = 0;
		boolean finalForm = false;
		List <E>newList = null;
		
		A : while(cycle < CYCLE_LIMIT && finalForm == false){
			System.out.println("Cycle: "+cycle);
			
			//calculate fitness values
			for(E c : candidates){
				c.setFitness(c.fitness(problemSet, counter));
			}
			System.out.println("fitness values calculated..");
			
			Collections.sort(candidates);
			
			System.out.println("fitness values sorted..");
			
			//take the fitter half and clone & mutate all elements and put them into a new list
			
			if(newList == null){
				newList = new LinkedList<E>();
			}else{
				newList.clear();
			}
			for(int i = 0;i < (candidates.size())/2;i++){
				if(candidates.get(i).getFitness() == maxFitness){
					System.out.println("Winner candidate found..stresstesting it");
					if(candidates.get(i).stressTest(stressTestProblems)){
						winner = candidates.get(i);
						finalForm = true;
						break A;
					}
				}
				if(candidates.get(i).getFitness() > maxC){
					maxC = candidates.get(i).getFitness();
					System.out.println("new maxC: " + maxC);
				}
				
				newList.add(candidates.get(i)); //add old object
				//System.out.println("candidate added");
				newList.add((E)candidates.get(i).cloneWithMutation()); //add mutated clone
				//System.out.println("clone added");
			}
			
			System.out.println("clear list & set newlist");
			
			//continue with new list
			candidates.clear();
			candidates.addAll(newList);

			cycle++;
		}
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