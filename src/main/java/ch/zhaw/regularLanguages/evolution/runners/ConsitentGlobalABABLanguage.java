package ch.zhaw.regularLanguages.evolution.runners;

import ch.zhaw.regularLanguages.evolution.starters.ConsistentGlobalProblemSetStarter;
import ch.zhaw.regularLanguages.graphicalOutput.GraphvizRenderer;

public class ConsitentGlobalABABLanguage {

	public static void main(String[] args) {
		ConsistentGlobalProblemSetStarter starter = new ConsistentGlobalProblemSetStarter();
		starter.initLanguage(new char[]{'a','b'}, 15, "[ab]*abab");
		starter.initProblems(200);
		starter.initCandidates(200);
		System.out.println(starter.startEvolution(500));
		
		if(starter.getWinner() != null){
			GraphvizRenderer.renderGraph(starter.getWinner().getObj(), "winner.svg");
		}
	}

}
