package ch.zhaw.regularLanguages.evolution;


public interface EvolutionaryAlgorithm <O extends Mutable>{
	public EvolutionResult evolve(O obj1, O obi2);
	public void startEvolution();
}
