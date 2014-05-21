package ch.zhaw.regularLanguages.dfa.transformation;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;

public class Bastler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//String reg ="0[123456789]{3}[0123456789]{6}";
		String reg ="(0|101|11(01)*(1|00)1|(100|11(01)*(1|00)0)(1|0(01)*(1|00)0)*0(01)*(1|00)1)*";
		RegExp regexp = new RegExp(reg);
		
		Automaton a = regexp.toAutomaton();
		System.out.println(a.run("101"));
		System.out.println(a.run("1010"));
		System.out.println(a.run("111"));
		System.out.println(a.run("1011"));
		
		System.out.println("Matches:");
		System.out.println("101".matches(reg));
		System.out.println("1010".matches(reg));
		System.out.println("111".matches(reg));
		System.out.println("1011".matches(reg));
		
		char c = 0;
		String s = new String();
		
		System.out.println(s);
		System.out.println(s.length());
		s+=c;
		System.out.println(s);
		System.out.println(s.length());
		
	}

}
