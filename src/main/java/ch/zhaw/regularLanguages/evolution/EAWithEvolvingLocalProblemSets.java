package ch.zhaw.regularLanguages.evolution;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ch.zhaw.regularLanguages.evolution.candidates.EvolutionCandidate;
import ch.zhaw.regularLanguages.evolution.problems.ProblemSet;

public class EAWithEvolvingLocalProblemSets<E extends EvolutionCandidate, PSI, PSO>{
	private final int CYCLE_LIMIT = 10000;
		
	private List<E> candidates;
	
	private E winner;
	
	private int maxFitness;
	private int maxC;
	
	
	private ProblemSet<PSI, PSO> problemSet;
	private ProblemSet<PSI, PSO> stressTestProblems;
	
	public EAWithEvolvingLocalProblemSets(ProblemSet<PSI, PSO> problemSet, ProblemSet<PSI, PSO> stressTestProblems, List<E> candidates) {
		this.problemSet = problemSet;	
		this.candidates = candidates;
		this.maxFitness = problemSet.getProblemSet().size();
		this.stressTestProblems = stressTestProblems;
		
		System.out.println("Max Fitness: " + maxFitness);
	}
	
	public E getWinner(){
		return winner;
	}
	
	public int getMaxC(){
		return maxC;
	}
	
	public ProblemSet<PSI, PSO> getProblemSet(){
		return problemSet;
	}
		
	public List<E> getCandidates(){
		return candidates;
	}
	
	public int getProblemSetSize(){
		return problemSet.getProblemSet().size();
	}

	/*public void startEvolution() {
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
	}*/

	public void startEvolution() {
		int cycle = 0;
		boolean finalForm = false;
		List <E>newList = null;
		
		long countEq = 0;
		long countNbig = 0;
		long countIbig = 0;
		
		A : while(cycle < CYCLE_LIMIT && finalForm == false){
			System.out.println("Cycle: "+cycle);
			
			if(newList == null){
				newList = new LinkedList<E>();
			}else{
				newList.clear();
			}
			
			for(int i = 0;i < candidates.size();i+=2){
				int n = (i <= 0 ? candidates.size()-1 : i-1);
				
				int fitnessI = candidates.get(i).fitness(problemSet);
				int fitnessN = candidates.get(n).fitness(problemSet);
				
				if(fitnessI == maxFitness){
					System.out.println("Winner candidate found..stresstesting it");
					if(candidates.get(i).stressTest(stressTestProblems)){
						winner = candidates.get(i);
						finalForm = true;
						break A;
					}
				}
				if(fitnessN == maxFitness){
					System.out.println("Winner candidate found..stresstesting it");
					if(candidates.get(n).stressTest(stressTestProblems)){
						winner = candidates.get(n);
						finalForm = true;
						break A;
					}
				}
				
				if(fitnessI > maxC){
					maxC = fitnessI;
					System.out.println("new maxC: " + maxC);
				}
				
				if(fitnessN > maxC){
					maxC = fitnessN;
					System.out.println("new maxC: " + maxC);
				}
				
				
				int x = fitnessI - fitnessN;
				if(x == 0){
					countEq++;
					//candidate n = candidate i
					if(Math.random() < 0.5){
						newList.add(candidates.get(i));
						newList.add((E)candidates.get(i).cloneWithMutation());
					}else{
						newList.add(candidates.get(n));
						newList.add((E)candidates.get(n).cloneWithMutation());						
					}
				}else if(x < 0){
					countNbig++;
					//candidate n > candidate i
					newList.add(candidates.get(n));
					newList.add((E)candidates.get(n).cloneWithMutation());
				}else if(x > 0){
					countIbig++;
					//candidate i > candidate n
					newList.add(candidates.get(i));
					newList.add((E)candidates.get(i).cloneWithMutation());	
				}
			}
			
			System.out.println("clear list & set newlist");
			candidates.clear();
			candidates.addAll(newList);
			
			Collections.shuffle(candidates);
			
			cycle++;
		}
		System.out.println("EQ:"+ countEq);
		System.out.println("NBiger:"+ countNbig);
		System.out.println("IBiger:"+ countIbig);
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