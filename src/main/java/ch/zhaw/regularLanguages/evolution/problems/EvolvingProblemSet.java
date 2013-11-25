package ch.zhaw.regularLanguages.evolution.problems;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ch.zhaw.regularLanguages.helpers.ObjectWithCounter;
import ch.zhaw.regularLanguages.helpers.Tuple;

public class EvolvingProblemSet<P, S> extends ProblemSet<P, S>{
	private ProblemGenerator<P, S> problemGenerator;
	
	public EvolvingProblemSet(List<P> problems, List<S> solutions, ProblemGenerator<P, S> problemGenerator) {
		super(problems, solutions);
		this.problemGenerator = problemGenerator;
	}

	public void evolve(){
		//Step 1: Create a sortable list
		List<ObjectWithCounter<Tuple<P,S>>> sortableList = new LinkedList<ObjectWithCounter<Tuple<P,S>>>();
		
		List<P> newProblems = new LinkedList<P>();
		List<S> newSolutions = new LinkedList<S>();
		
		for(int i = 0; i < getProblems().size();i++){
			Tuple<P,S> obj = new Tuple<P, S>(getProblems().get(i), getSolutions().get(i));
			sortableList.add(new ObjectWithCounter<Tuple<P, S>>(obj, getSolvedCounter()[i]));
		}
		
		//Step 2: Sort
		Collections.sort(sortableList); //highest count on top
		Collections.reverse(sortableList); //highest count at bottom (easiest problem)
		
		
		//Step 3: Use "hardest" problems
		int half = sortableList.size()/2;
		for(int i = 0;i < half;i++){
			newProblems.add(sortableList.get(i).getObject().getFirst());
			newSolutions.add(sortableList.get(i).getObject().getSecond());
		}
		
		//Step 4: Fill the bottom half of the lists with new problems
		for(int i = half;i < sortableList.size();i++){
			Tuple<P,S> newProb = null;
			do{
				newProb = problemGenerator.generateProblem();
			}while(!newProblems.contains(newProb.getFirst())); //avoid duplicates. may lead to endless loops!
			
			newProblems.add(newProb.getFirst());
			newSolutions.add(newProb.getSecond());
		}
		
		//Step 5: Update ProblemSet
		setProblems(newProblems);
		setSolutions(newSolutions);
		updateMap();
	}
}
