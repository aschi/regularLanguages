package ch.zhaw.regularLanguages.evolution;

public class EvolutionCandidate<T> implements Comparable<EvolutionCandidate<T>>{
	private T obj;
	private int fitness;
	
	public EvolutionCandidate(T obj, int fitness) {
		super();
		this.obj = obj;
		this.fitness = fitness;
	}
	
	public T getObj() {
		return obj;
	}
	public int getFitness() {
		return fitness;
	}

	@Override
	public int compareTo(EvolutionCandidate<T> o) {
		// TODO Auto-generated method stub
		return o.getFitness()-this.getFitness();
	}
}
