package ch.zhaw.regularLanguages.languages;

import java.util.Random;

import ch.zhaw.regularLanguages.evolution.problems.ProblemGenerator;
import ch.zhaw.regularLanguages.helpers.Tuple;


public class WordProblemGenerator implements ProblemGenerator<CharArrayWrapper, Boolean> {
	private char[] alphabet;
	private int maxWordLength;
	private String regexp;
	private Random rnd = new Random();
	
	public WordProblemGenerator(char[] alphabet, int maxWordLength, String regexp){
		this.alphabet = alphabet;
		this.maxWordLength = maxWordLength;
		this.regexp = regexp;
	}
	
	@Override
	public Tuple<CharArrayWrapper, Boolean> generateProblem() {
		int length = rnd.nextInt(maxWordLength);
		
		char[] rv = new char[length];
		
		for(int i = 0; i < length;i++){
			rv[i] = alphabet[rnd.nextInt(alphabet.length)];
		}
		
		return new Tuple<CharArrayWrapper, Boolean>(new CharArrayWrapper(rv), new String(rv).matches(regexp));
	}
}
