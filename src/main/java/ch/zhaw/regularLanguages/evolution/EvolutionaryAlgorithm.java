package ch.zhaw.regularLanguages.evolution;

import java.util.List;

import ch.zhaw.regularLanguages.evolution.candidates.EvolutionCandidate;

public interface EvolutionaryAlgorithm<E extends EvolutionCandidate> {
	public void startEvolution();
	public E getWinner();
	public List<E> getCandidates();

}
