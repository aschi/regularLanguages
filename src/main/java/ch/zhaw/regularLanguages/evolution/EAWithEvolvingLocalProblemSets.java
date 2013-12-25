package ch.zhaw.regularLanguages.evolution;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ch.zhaw.regularLanguages.evolution.candidates.EvolutionCandidate;
import ch.zhaw.regularLanguages.evolution.problems.ProblemSet;

public class EAWithEvolvingLocalProblemSets<E extends EvolutionCandidate, PSI, PSO, R> implements EvolutionaryAlgorithm<E>{
	private List<E> candidates;
	
	private E winner;
	
	private R reference;
	
	private int maxFitness;
	private int maxC;
	
	
	private ProblemSet<PSI, PSO> problemSet;
	
	public EAWithEvolvingLocalProblemSets(ProblemSet<PSI, PSO> problemSet, List<E> candidates, R reference) {
		this.problemSet = problemSet;	
		this.candidates = candidates;
		this.maxFitness = problemSet.getProblemSet().size();
		this.reference = reference;
		
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

	@Override
	public long startEvolution(long cycleLimit) {
		long cycle = 0;
		boolean finalForm = false;
		List <E>newList = null;
		
		long countEq = 0;
		long countNbig = 0;
		long countIbig = 0;
		
		A : while(cycle < cycleLimit && finalForm == false){
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
					if(candidates.get(i).checkValidity(reference)){
						winner = candidates.get(i);
						finalForm = true;
						break A;
					}
				}
				if(fitnessN == maxFitness){
					System.out.println("Winner candidate found..stresstesting it");
					if(candidates.get(n).checkValidity(reference)){
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
		
		return cycle;
	}
}