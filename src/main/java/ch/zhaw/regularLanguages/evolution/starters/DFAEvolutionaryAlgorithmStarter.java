package ch.zhaw.regularLanguages.evolution.starters;

public interface DFAEvolutionaryAlgorithmStarter<EAType, CandidateType> extends	EvolutionaryAlgorithmStarter<EAType, CandidateType> {
	public void initLanguage(char[] alphabet, int maxWordLength, String regexp);
}
