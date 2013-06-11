package ch.zhaw.regularLanguages;

import java.util.LinkedList;
import java.util.List;

import ch.zhaw.regularLanguages.dfa.RandomDeterministicFiniteAutomaton;
import ch.zhaw.regularLanguages.evolution.ProblemSet;
import ch.zhaw.regularLanguages.evolution.SimpleEvolutionaryAlgorithm;
import ch.zhaw.regularLanguages.graphicalOutput.GraphvizRenderer;
import ch.zhaw.regularLanguages.languages.LanguageHelper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	/*
    	DeterministicFiniteAutomaton dfa = new DeterministicFiniteAutomaton();
    	dfa.initSimpleAutomaton();
    	
    	List<Character> input = LanguageHelper.generateSymbolList("babaaababbb");
    	
    	State result = dfa.process(input);
        System.out.println(dfa.isAcceptingState(result));
        
        GraphvizRenderer.renderGraph(dfa, "testOutput.svg");
        */
    	
    	List<String> words = new LinkedList<String>();
    	words.add("");
    	words.add("a");
    	words.add("b");
    	words.add("ab");
    	words.add("ba");
    	words.add("aa");
    	words.add("ab");
    	words.add("aaa");
    	words.add("aab");
    	words.add("aba");
    	words.add("abb");
    	words.add("baa");
    	words.add("bab");
    	words.add("bbb");
    	words.add("abab");
    	words.add("baba");
    	words.add("aaaa");
    	words.add("aaab");
    	words.add("aaba");
    	words.add("aabb");
    	words.add("abaa");
    	words.add("abba");
    	words.add("abbb");
    	words.add("baaa");
    	words.add("baab");
    	words.add("bbaa");
    	words.add("bbab");
    	words.add("bbbb");
    	words.add("aabab");
    	words.add("ababa");
    	words.add("bbbaabab");
    	words.add("ababaab");
    	words.add("aaaaababaaaaaababab");
    	words.add("ababaaaabbbbaaaabbbbaaa");
    	words.add("aababbabbabbababbabab");
    	words.add("bbbbbb");
    	words.add("aaaaaaaa");

    	
    	ProblemSet<List<Character>, Boolean> testProblems = LanguageHelper.generateLanguageProblemSet(".*abab$", words);
    	SimpleEvolutionaryAlgorithm<RandomDeterministicFiniteAutomaton> sea = new SimpleEvolutionaryAlgorithm<RandomDeterministicFiniteAutomaton>(testProblems, LanguageHelper.generateSymbolList("ab"), RandomDeterministicFiniteAutomaton.class);
    	sea.startEvolution();
    	if(sea.getWinner() != null){
    		System.out.println("Winner!");
    		GraphvizRenderer.renderGraph(sea.getWinner(), "winner.svg");
    		
    		int n = 0;
    		for(RandomDeterministicFiniteAutomaton o : sea.getObjects()){
    			GraphvizRenderer.renderGraph(o, n+".svg");
    			System.out.println("Generating: " + n+".svg");
    			n++;
    		}
    		
    		System.out.println(testProblems);
    		System.out.println("Max C: "+ sea.getMaxC() + " / ProblemSetSize : " + sea.getProblemSetSize());
    		for(int i = 0; i < sea.getCounter().length;i++){
    			System.out.println("["+i+"] " + sea.getCounter()[i]);
    		}
    	}else{
    		System.out.println("No winner!");
    		System.out.println(testProblems);
    		System.out.println("Max C: "+ sea.getMaxC() + " / ProblemSetSize : " + sea.getProblemSetSize());
    		for(int i = 0; i < sea.getCounter().length;i++){
    			System.out.println("["+i+"] " + sea.getCounter()[i]);
    		}
    		
    		int n = 0;
    		for(RandomDeterministicFiniteAutomaton o : sea.getObjects()){
    			GraphvizRenderer.renderGraph(o, n+".svg");
    			System.out.println("Generating: " + n+".svg");
    			n++;
    		}
    	}
    }
}
