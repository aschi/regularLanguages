package ch.zhaw.regularLanguages.evolution.starters;

import java.util.LinkedList;
import java.util.List;

import ch.zhaw.regularLanguages.dfa.RandomDeterministicFiniteAutomaton;
import ch.zhaw.regularLanguages.evolution.EAWithConsistentGlobalProblemSet;
import ch.zhaw.regularLanguages.evolution.candidates.DFAEvolutionCandidate;
import ch.zhaw.regularLanguages.evolution.problems.ProblemSet;
import ch.zhaw.regularLanguages.languages.BooleanWrapper;
import ch.zhaw.regularLanguages.languages.CharArrayWrapper;
import ch.zhaw.regularLanguages.languages.WordProblemGenerator;
import dk.brics.automaton.RegExp;

public class ConsistentGlobalProblemSetStarter implements DFAEvolutionaryAlgorithmStarter<EAWithConsistentGlobalProblemSet<DFAEvolutionCandidate<RandomDeterministicFiniteAutomaton>, CharArrayWrapper, BooleanWrapper, dk.brics.automaton.Automaton>, DFAEvolutionCandidate<RandomDeterministicFiniteAutomaton>> {	
	private List<DFAEvolutionCandidate<RandomDeterministicFiniteAutomaton>> candidates;
	private ProblemSet<CharArrayWrapper, BooleanWrapper> problemSet;
	private char[] alphabet;
	private String regexp;
	private dk.brics.automaton.Automaton refrence;
	private WordProblemGenerator wpg;
	private EAWithConsistentGlobalProblemSet<DFAEvolutionCandidate<RandomDeterministicFiniteAutomaton>, CharArrayWrapper, BooleanWrapper, dk.brics.automaton.Automaton> ea;
	
	@Override
	public void initLanguage(char[] alphabet, int maxWordLength, String regexp) {
		this.alphabet = alphabet;
		this.regexp = regexp;
		this.refrence = new RegExp(regexp).toAutomaton();
		wpg = new WordProblemGenerator(alphabet, maxWordLength, regexp);
	}

	public void initProblems(int noProblems){
		if(alphabet != null && regexp != null && wpg != null){
			problemSet = wpg.generateProblemSet(noProblems, true);
		}else{
			throw new IllegalAccessError("Language not yet initialised!");
		}
	}
	
	public void initCandidates(int noCandidates) {
		if(alphabet != null){
			candidates = new LinkedList<DFAEvolutionCandidate<RandomDeterministicFiniteAutomaton>>();
	    	for(int i = 0; i < noCandidates;i++){
	    		candidates.add(new DFAEvolutionCandidate<RandomDeterministicFiniteAutomaton>(RandomDeterministicFiniteAutomaton.class, alphabet));
	    	}
    	}else{
    		throw new IllegalAccessError("Language not yet initialised!");
    	}
	}

	@Override
	public long startEvolution(long cycleLimit) {
		if(alphabet == null || regexp == null || wpg == null){
			throw new IllegalAccessError("Language not yet initialised!");
		}
		if(candidates == null){
			throw new IllegalAccessError("Candidates not yet initialised!");
		}
		if(problemSet == null){
			throw new IllegalAccessError("ProblemSet not yet initialised!");
		}
		
		ea = new EAWithConsistentGlobalProblemSet<DFAEvolutionCandidate<RandomDeterministicFiniteAutomaton>, CharArrayWrapper, BooleanWrapper, dk.brics.automaton.Automaton>(problemSet, candidates, refrence);
		return ea.startEvolution(cycleLimit);
	}

	@Override
	public List<DFAEvolutionCandidate<RandomDeterministicFiniteAutomaton>> getCandidates() {
		// TODO Auto-generated method stub
		return candidates;
	}

	@Override
	public DFAEvolutionCandidate<RandomDeterministicFiniteAutomaton> getWinner() {
		// TODO Auto-generated method stub
		return ea.getWinner();
	}
}
