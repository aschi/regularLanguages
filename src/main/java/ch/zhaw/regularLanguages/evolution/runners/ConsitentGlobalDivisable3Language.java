package ch.zhaw.regularLanguages.evolution.runners;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ch.zhaw.regularLanguages.evolution.initialisation.ConsistentGlobalProblemSetInitialisation;
import ch.zhaw.regularLanguages.helpers.Logger;

public class ConsitentGlobalDivisable3Language {

	public static void main(String[] args) {
		ConsistentGlobalProblemSetInitialisation starter = new ConsistentGlobalProblemSetInitialisation();
		starter.initLanguage(new char[] { '0', '1' }, 10, "(1(01*0)*1|0)*");

		int solutionFoundCounter = 0;
		int noSolutionFound = 0;
		List<Long> cycleCount = new LinkedList<Long>();
		long tmpCycle;
		long timeStamp;

		int[] problemCount = new int[5];
		int[] candidatesCount = new int[5];
		int[] noCycles = new int[2];

		problemCount[0] = 10;
		problemCount[1] = 20;
		problemCount[2] = 30;
		problemCount[3] = 40;
		problemCount[4] = 50;

		candidatesCount[0] = 10;
		candidatesCount[1] = 20;
		candidatesCount[2] = 30;
		candidatesCount[3] = 40;
		candidatesCount[4] = 50;

		noCycles[0] = 250;
		noCycles[1] = 500;

		int pc = 0;
		int cc = 0;
		int nc = 0;
		for (int x = 0; x < 100; x++) {
			System.out.println("x:"+x);
			for (int n = 0; n < 25; n++) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");

				Logger l = new Logger("C_G_" + df.format(new Date()) + ".log", true);

				pc = problemCount[n % 5];
				cc = candidatesCount[(int) Math.floor(n / 5)];
				nc = noCycles[1];

				l.log("Problem Count: " + pc);
				l.log("CandidatesCount: " + cc);
				l.log("Max Cycles: " + nc);

				solutionFoundCounter = 0;
				noSolutionFound = 0;
				cycleCount = new LinkedList<Long>();

				for (int i = 0; i < 100; i++) {
					timeStamp = System.currentTimeMillis();

					starter.initProblems(pc);
					starter.initCandidates(cc);
					tmpCycle = starter.startEvolution(nc);

					l.log(i + ": finished ("
							+ (System.currentTimeMillis() - timeStamp) + "ms, "
							+ tmpCycle + "cycles)");

					if (starter.getWinner() != null) {
						solutionFoundCounter++;
						cycleCount.add(tmpCycle);
						l.log(i + ": Solution found.");
					} else {
						noSolutionFound++;
						l.log(i + ": No solution found.");
					}
				}

				long max = 0;
				long min = 10000;
				long sum = 0;
				for (long no : cycleCount) {
					sum += no;
					max = (no > max ? no : max);
					min = (no < min ? no : min);
				}

				l.log("Solution Found: " + solutionFoundCounter);
				l.log("Avg cycles: "
						+ (cycleCount.size() > 0 ? sum / cycleCount.size()
								: '0'));
				l.log("Max cycles: " + max);
				l.log("Min cycles: " + min);
				l.log("No solution found: " + noSolutionFound);
				l.finish();
			}
		}
	}
}
