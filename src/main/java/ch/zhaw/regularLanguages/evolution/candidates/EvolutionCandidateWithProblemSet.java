package ch.zhaw.regularLanguages.evolution.candidates;

public abstract class EvolutionCandidateWithProblemSet<T, PS, R> extends EvolutionCandidate<T, PS, R>{
	private PS problemSet;
	
	public EvolutionCandidateWithProblemSet(T obj, PS problemSet){
		super();
		this.problemSet = problemSet;
		setObj(obj);		
	}
	
	public PS getProblemSet(){
		return problemSet;
	}
}