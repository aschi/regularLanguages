package ch.zhaw.regularLanguages.evolution.problems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProblemSet<P,S> {
	private Map<P, S> map;
	private long solvedCounter[];
	private List<P> problems;
	private List<S> solutions;
	
	public ProblemSet(List<P> problems, List<S> solutions){
		this.problems = problems;
		this.solutions = solutions;
		this.solvedCounter = new long[problems.size()];
		
		updateMap();
	}
	
	public S getSolution(P problem){
		return map.get(problem);
	}
	
	public boolean checkSolution(P problem, S solution){
		boolean rv = map.get(problem).equals(solution);
		if(rv){
			solvedCounter[problems.indexOf(problem)]++;
		}
		return rv;
	}
	
	public void updateMap(){
		map = new HashMap<P, S>();
		
		if(problems.size() != solutions.size()){
			throw new IllegalArgumentException("Problems and solutions need to have the same size!");
		}
		
		for(int i = 0; i < problems.size();i++){
			map.put(problems.get(i), solutions.get(i));
		}
	}
	
	public Set<P> getProblemSet(){
		return map.keySet();
	}
	
	public long[] getSolvedCounter(){
		return solvedCounter;
	}
	
	public List<P> getProblems() {
		return problems;
	}

	public void setProblems(List<P> problems) {
		this.problems = problems;
	}

	public List<S> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<S> solutions) {
		this.solutions = solutions;
	}

	@Override
	public String toString(){
		String output = "";
		int i = 0;
		for(P problem : map.keySet()){
			output+= ("["+i+"]:" + problem +" => " + map.get(problem) + System.getProperty("line.separator"));
			i++;
		}
		
		return output;
	}
}
