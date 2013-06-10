package ch.zhaw.regularLanguages.evolution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProblemSet<P,S> {
	private Map<P, S> map;
	
	public ProblemSet(List<P> problems, List<S> solutions){
		map = new HashMap<P, S>();
		
		if(problems.size() != solutions.size()){
			throw new IllegalArgumentException("Problems and solutions need to have the same size!");
		}
		
		for(int i = 0; i < problems.size();i++){
			map.put(problems.get(i), solutions.get(i));
		}
	}
	
	public boolean checkSolution(P problem, S solution){
		return map.get(problem).equals(solution);
	}	
	
	public Set<P> getProblemSet(){
		return map.keySet();
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
