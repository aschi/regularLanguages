package ch.zhaw.regularLanguages.evolution;

import ch.zhaw.regularLanguages.helpers.Tuple;

public interface ProblemGenerator<P, S> {
	public Tuple<P,S> generateProblem();
}
