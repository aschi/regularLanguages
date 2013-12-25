package ch.zhaw.regularLanguages.evolution.runners;

import java.util.LinkedList;
import java.util.List;

import ch.zhaw.regularLanguages.evolution.starters.ConsistentGlobalProblemSetStarter;
import ch.zhaw.regularLanguages.graphicalOutput.GraphvizRenderer;

public class ConsitentGlobalABABLanguage {

	public static void main(String[] args) {
		
		int solutionFoundCount = 0;
		int noSolutionfoundCount = 0;
		long cycleTmp;
		List<Long> noCycles = new LinkedList<Long>();
		long timeStamp;
		
		ConsistentGlobalProblemSetStarter starter = new ConsistentGlobalProblemSetStarter();
		starter.initLanguage(new char[]{'a','b'}, 15, "[ab]*abab");
		
		for(int i = 0; i < 100;i++){
			timeStamp = System.currentTimeMillis();
			
			//init
			starter.initProblems(200);
			starter.initCandidates(200);
			
			//start evolution
			cycleTmp = starter.startEvolution(1000);
			
			System.out.println(i+": finished ("+ (System.currentTimeMillis()-timeStamp)/1000 + "s)");
			
			if(starter.getWinner() != null){
				noCycles.add(cycleTmp);
				solutionFoundCount++;
				System.out.println(i+": solution found");
				//GraphvizRenderer.renderGraph(starter.getWinner().getObj(), "winner.svg");
			}else{
				noSolutionfoundCount++;
				System.out.println(i+": no solution found");
			}
		}
		
		long sum = 0;
		for(long l : noCycles){
			sum+=l;
		}
		double avg = sum/noCycles.size();
		
		System.out.println("Solution Found:" + solutionFoundCount + "(Average cycles: " + avg +")");
		System.out.println("No solution Found:" + noSolutionfoundCount);

	}
}
