package ch.zhaw.regularLanguages;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ch.zhaw.regularLanguages.dfa.RandomDeterministicFiniteAutomaton;
import ch.zhaw.regularLanguages.evolution.DeterministicFiniteAutomatonEvolutionCandidate;
import ch.zhaw.regularLanguages.evolution.ProblemSet;
import ch.zhaw.regularLanguages.evolution.SimpleEvolutionaryAlgorithm;
import ch.zhaw.regularLanguages.graphicalOutput.GraphvizRenderer;
import ch.zhaw.regularLanguages.languages.CharArrayWrapper;
import ch.zhaw.regularLanguages.languages.LanguageHelper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	final int NO_AUTOMATONS = 100;
    	
    	/*
    	DeterministicFiniteAutomaton dfa = new DeterministicFiniteAutomaton();
    	dfa.initSimpleAutomaton();
    	
    	List<Character> input = LanguageHelper.generateSymbolList("babaaababbb");
    	
    	State result = dfa.process(input);
        System.out.println(dfa.isAcceptingState(result));
        
        GraphvizRenderer.renderGraph(dfa, "testOutput.svg");
        */
    	
    	Set<CharArrayWrapper> words = new HashSet<CharArrayWrapper>();
    	words.add(new CharArrayWrapper("".toCharArray()));
    	words.add(new CharArrayWrapper("a".toCharArray()));
    	words.add(new CharArrayWrapper("b".toCharArray()));
    	words.add(new CharArrayWrapper("ab".toCharArray()));
    	words.add(new CharArrayWrapper("ba".toCharArray()));
    	words.add(new CharArrayWrapper("aa".toCharArray()));
    	words.add(new CharArrayWrapper("ab".toCharArray()));
    	words.add(new CharArrayWrapper("bb".toCharArray()));
    	words.add(new CharArrayWrapper("aaa".toCharArray()));
    	words.add(new CharArrayWrapper("aab".toCharArray()));
    	words.add(new CharArrayWrapper("aba".toCharArray()));
    	words.add(new CharArrayWrapper("abb".toCharArray()));
    	words.add(new CharArrayWrapper("baa".toCharArray()));
    	words.add(new CharArrayWrapper("bab".toCharArray()));
    	words.add(new CharArrayWrapper("bbb".toCharArray()));
    	words.add(new CharArrayWrapper("abab".toCharArray()));
    	words.add(new CharArrayWrapper("baba".toCharArray()));
    	words.add(new CharArrayWrapper("aaaa".toCharArray()));
    	words.add(new CharArrayWrapper("aaab".toCharArray()));
    	words.add(new CharArrayWrapper("aaba".toCharArray()));
    	words.add(new CharArrayWrapper("aabb".toCharArray()));
    	words.add(new CharArrayWrapper("abaa".toCharArray()));
    	words.add(new CharArrayWrapper("abba".toCharArray()));
    	words.add(new CharArrayWrapper("abbb".toCharArray()));
    	words.add(new CharArrayWrapper("baaa".toCharArray()));
    	words.add(new CharArrayWrapper("baab".toCharArray()));
    	words.add(new CharArrayWrapper("bbaa".toCharArray()));
    	words.add(new CharArrayWrapper("bbab".toCharArray()));
    	words.add(new CharArrayWrapper("bbbb".toCharArray()));
    	words.add(new CharArrayWrapper("aabab".toCharArray()));
    	words.add(new CharArrayWrapper("ababa".toCharArray()));
    	words.add(new CharArrayWrapper("bbbaabab".toCharArray()));
    	words.add(new CharArrayWrapper("ababaab".toCharArray()));
    	words.add(new CharArrayWrapper("aaaaababaaaaaababab".toCharArray()));
    	words.add(new CharArrayWrapper("ababaaaabbbbaaaabbbbaaa".toCharArray()));
    	words.add(new CharArrayWrapper("aababbabbabbababbabab".toCharArray()));
    	words.add(new CharArrayWrapper("bbbbbb".toCharArray()));
    	words.add(new CharArrayWrapper("aaaaaaaa".toCharArray()));
    	ProblemSet<CharArrayWrapper, Boolean> testProblems = LanguageHelper.generateLanguageProblemSet(".*abab$", words);

    	
    	//setup candidates
    	char[] alphabet = {'a','b'};

    	Set<CharArrayWrapper> stressTestWords = LanguageHelper.generateAllWords(alphabet, 12);
    	
    	System.out.println("StressTestWords count: " + stressTestWords.size());
    	
		ProblemSet<CharArrayWrapper, Boolean> stressTestProblems = LanguageHelper.generateLanguageProblemSet(".*abab$", stressTestWords);
    	
    	List<DeterministicFiniteAutomatonEvolutionCandidate<RandomDeterministicFiniteAutomaton>> candidates = new LinkedList<DeterministicFiniteAutomatonEvolutionCandidate<RandomDeterministicFiniteAutomaton>>();
    	for(int i = 0; i < NO_AUTOMATONS;i++){
    		candidates.add(new DeterministicFiniteAutomatonEvolutionCandidate<RandomDeterministicFiniteAutomaton>(RandomDeterministicFiniteAutomaton.class, alphabet));
    	}
    	
    	int n = 0;
		for(DeterministicFiniteAutomatonEvolutionCandidate<RandomDeterministicFiniteAutomaton> o : candidates){
			GraphvizRenderer.renderGraph(o.getObj(), n+".svg");
			System.out.println("Generating: " + n+".svg");
			n++;
		}
    	
    	/*
    	SimpleEvolutionaryAlgorithm<DeterministicFiniteAutomatonEvolutionCandidate<RandomDeterministicFiniteAutomaton>, CharArrayWrapper, Boolean> sea = new SimpleEvolutionaryAlgorithm<DeterministicFiniteAutomatonEvolutionCandidate<RandomDeterministicFiniteAutomaton>, CharArrayWrapper, Boolean>(testProblems, stressTestProblems, candidates);
    	sea.startEvolution();
    	if(sea.getWinner() != null){
    		System.out.println("Winner!");
    		sea.getWinner().getObj().minimizeAutomaton();
    		GraphvizRenderer.renderGraph(sea.getWinner().getObj(), "winner.svg");
    		
    		int n = 0;
    		for(DeterministicFiniteAutomatonEvolutionCandidate<RandomDeterministicFiniteAutomaton> o : sea.getCandidates()){
    			GraphvizRenderer.renderGraph(o.getObj(), n+".svg");
    			System.out.println("Generating: " + n+".svg");
    			n++;
    		}
    		
    		System.out.println(testProblems);
    		System.out.println("Max C: "+ sea.getMaxC() + " / ProblemSetSize : " + sea.getProblemSetSize());
    		for(int i = 0; i < sea.getCounter().length;i++){
    			System.out.println("["+i+"] " + sea.getCounter()[i]);
    		}
    		
    		System.out.println("StressTestWords count: " + stressTestWords.size());
    	    long sum = 0;
    		for(CharArrayWrapper cw : stressTestWords){
    	    	sum +=cw.getData().length;
    	    }
    		System.out.println("Average Length: " + sum / stressTestWords.size());
    	}else{
    		System.out.println("No winner!");
    		System.out.println(testProblems);
    		System.out.println("Max C: "+ sea.getMaxC() + " / ProblemSetSize : " + sea.getProblemSetSize());
    		for(int i = 0; i < sea.getCounter().length;i++){
    			System.out.println("["+i+"] " + sea.getCounter()[i]);
    		}
    		
    		int n = 0;
    		for(DeterministicFiniteAutomatonEvolutionCandidate<RandomDeterministicFiniteAutomaton> o : sea.getCandidates()){
    			GraphvizRenderer.renderGraph(o.getObj(), n+".svg");
    			System.out.println("Generating: " + n+".svg");
    			n++;
    		}
    	}
    	*/
    }
}
