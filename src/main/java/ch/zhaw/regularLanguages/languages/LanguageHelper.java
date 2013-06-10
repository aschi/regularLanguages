package ch.zhaw.regularLanguages.languages;

import java.util.LinkedList;
import java.util.List;

import ch.zhaw.regularLanguages.evolution.ProblemSet;

public class LanguageHelper {
	public static List<Character> generateSymbolList(String s){
		List<Character> output = new LinkedList<Character>();
		
		for(int i = 0;i < s.length();i++){
			output.add(s.charAt(i));
		}
		
		return output;
	}
	
	
	public static ProblemSet<List<Character>, Boolean> generateLanguageProblemSet(String regexp, List<String> words){
		List<Boolean> expectedResultList = new LinkedList<Boolean>();
		List<List<Character>> inputList = new LinkedList<List<Character>>();
		
		for(String w : words){
			inputList.add(LanguageHelper.generateSymbolList(w));
			expectedResultList.add(w.matches(regexp));
		}

		return new ProblemSet<List<Character>, Boolean>(inputList, expectedResultList);
	}
}
