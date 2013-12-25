package ch.zhaw.regularLanguages.evolution.runners;

import java.util.LinkedList;
import java.util.List;

import ch.zhaw.regularLanguages.evolution.starters.EvolvingGlobalProblemSetStarter;

public class EvolvingGlobalABABLanguage {

	public static void main(String[] args) {
		EvolvingGlobalProblemSetStarter starter = new EvolvingGlobalProblemSetStarter();
		starter.initLanguage(new char[]{'a','b'}, 10, "[ab]*abab");
		
		int solutionFoundCounter = 0;
		int noSolutionFound = 0;
		List<Long> cycleCount = new LinkedList<Long>();
		long tmpCycle;
		long timeStamp;
		
		for(int i = 0;i < 100;i++){
			timeStamp = System.currentTimeMillis();
			
			starter.initProblems(50);
			starter.initCandidates(200);
			tmpCycle = starter.startEvolution(1000);
			
			System.out.println(i+": finished ("+ (System.currentTimeMillis()-timeStamp) + "ms, " + tmpCycle + "cycles)");
			
			if(starter.getWinner() != null){
				solutionFoundCounter++;
				cycleCount.add(tmpCycle);
				System.out.println(i + ": Solution found.");
				//GraphvizRenderer.renderGraph(starter.getWinner().getObj(), "winner.svg");
			}else{
				noSolutionFound++;
				System.out.println(i + ": No solution found.");
			}
		}
		
		long max=0;
		long min=10000;
		long sum=0;
		for(long l : cycleCount){
			sum+=l;
			max = (l > max ? l : max);
			min = (l < min ? l : min);
		}
		
		System.out.println("Solution Found: " +solutionFoundCounter +" (Avg: " + sum/cycleCount.size() + ")");
		System.out.println("Max cycles: " + max);
		System.out.println("Min cycles: " + min);
		System.out.println("No solution found: "+noSolutionFound);
	}
}