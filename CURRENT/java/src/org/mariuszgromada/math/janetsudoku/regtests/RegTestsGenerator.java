/*
 * @(#)RegTestsSolver.java        0.0.1    2016-01-30
 *
 * You may use this software under the condition of "Simplified BSD License"
 *
 * Copyright 2016 MARIUSZ GROMADA. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY <MARIUSZ GROMADA> ``AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied, of MARIUSZ GROMADA.
 *
 * If you have any questions/bugs feel free to contact:
 *
 *     Mariusz Gromada
 *     mariusz.gromada@mathspace.pl
 *     http://mathspace.pl/
 *     http://mathparser.org/
 *     http://github.com/mariuszgromada/java-utils
 *     http://github.com/mariuszgromada/MathParser.org-mXparser
 *     http://mariuszgromada.github.io/MathParser.org-mXparser/
 *     http://mxparser.sourceforge.net/
 *     http://bitbucket.org/mariuszgromada/mxparser/
 *     http://mxparser.codeplex.com/
 *
 *                              Asked if he believes in one God, a mathematician answered:
 *                              "Yes, up to isomorphism."
 */
package org.mariuszgromada.math.janetsudoku.regtests;

import org.mariuszgromada.math.janetsudoku.ErrorCodes;
import org.mariuszgromada.math.janetsudoku.SudokuPuzzles;
import org.mariuszgromada.math.janetsudoku.SudokuSolver;
import org.mariuszgromada.math.janetsudoku.SudokuGenerator;
import org.mariuszgromada.math.janetsudoku.SudokuStore;

/**
 * Regression tests for the SudokuGenerator class.
 *
 * @author         <b>Mariusz Gromada</b><br/>
 *                 <a href="mailto:mariusz.gromada@mathspace.pl">mariusz.gromada@mathspace.pl</a><br>
 *                 <a href="http://mathspace.pl/" target="_blank">MathSpace.pl</a><br>
 *                 <a href="http://mathparser.org/" target="_blank">MathParser.org - mXparser project page</a><br>
 *                 <a href="http://github.com/mariuszgromada/java-utils" target="_blank">Java-Utils on GitHub</a><br>
 *                 <a href="http://github.com/mariuszgromada/MathParser.org-mXparser" target="_blank">mXparser on GitHub</a><br>
 *                 <a href="http://mariuszgromada.github.io/MathParser.org-mXparser/" target="_blank">mXparser on GitHub pages</a><br>
 *                 <a href="http://mxparser.sourceforge.net/" target="_blank">mXparser on SourceForge</a><br>
 *                 <a href="http://bitbucket.org/mariuszgromada/mxparser/" target="_blank">mXparser on Bitbucket</a><br>
 *                 <a href="http://mxparser.codeplex.com/" target="_blank">mXparser on CodePlex</a><br>
 *
 * @version        0.0.1
 *
 * @see SudokuSolver
 */
public class RegTestsGenerator {
	/**
	 * Runs SudokuSolver regression tests with default number of threads.
	 * @return    Number of tests with errors.
	 */
	public static int start() {
		return start( Runtime.getRuntime().availableProcessors() );
	}
	/**
	 * Runs SudokuSolver regression tests.
	 * @param threadsNumber    Number of threads.
	 * @return    Number of tests with errors.
	 */
	public static int start(int threadsNumber) {
		int numberOfTests = GeneratorTests.NUMBER_OF_TESTS;
		int resultsError = 0;
		int resultsOk = 0;
		long startTime = System.currentTimeMillis();
		GeneratorTests st = new GeneratorTests(threadsNumber);
		st.start();
		boolean[] testResults = st.testsResults;
		for (int t = 0; t < numberOfTests; t++) {
			if (testResults[t] == true)
				resultsOk++;
			else
				resultsError++;
		}
		long endtTime = System.currentTimeMillis();
		double computingTime = (endtTime - startTime)/1000.0;
		SudokuStore.consolePrintln("=============================================================");
		SudokuStore.consolePrintln("Number of SudokuGenerator test: " + numberOfTests + ", OK: " + resultsOk + ", ERRORS: " + resultsError + ", computing time: " + computingTime);
		for (int t = 0; t < numberOfTests; t++)
			if (testResults[t] == false)
				SudokuStore.consolePrintln("ERROR: " + t);
		SudokuStore.consolePrintln("=============================================================");
		return resultsError;
	}
	/**
	 * Runs regression tests
	 * @param args     Optional first argument with threads number,
	 *                 otherwise default threads number is used.
	 */
	public static void main(String[] args) {
		if (args != null) {
			if (args.length > 0)
				if (args[0] != null) {
					int threadsNumber = Integer.parseInt(args[0]);
					if (threadsNumber > 0) {
						start(threadsNumber);
						return;
					}
				}
		}
		start();
	}
}
/**
 * Regression tests definition.
 */
class GeneratorTests {
	/**
	 * Threads number.
	 */
	private static int THREADS_NUMBER;
	/**
	 * Workers and threads.
	 */
	private TestThread[] workers;
	private Thread[] threads;
	/**
	 * Table containing test results.
	 */
	boolean[] testsResults;
	/**
	 * Default constructor.
	 * @param threadsNumber     Threads number.
	 */
	GeneratorTests(int threadsNumber) {
		THREADS_NUMBER = threadsNumber;
		threads = new Thread[THREADS_NUMBER];
		workers = new TestThread[THREADS_NUMBER];
		testsResults = new boolean[NUMBER_OF_TESTS];
		int[] testsIds = new int[NUMBER_OF_TESTS];
		for (int i = 0; i < NUMBER_OF_TESTS; i++)
			testsIds[i] = i;
		/*
		 * Randomization of tests before assignments to threads
		 */
		for (int i = 0; i < NUMBER_OF_TESTS; i++) {
			int lastIndex = NUMBER_OF_TESTS - i - 1;
			int j = SudokuStore.randomIndex(NUMBER_OF_TESTS - i);
			if (j != lastIndex) {
				int l = testsIds[lastIndex];
				testsIds[lastIndex] = testsIds[j];
				testsIds[j] = l;
			}
		}
		/*
		 * Tests assignments to threads
		 */
		int defThreadSize = NUMBER_OF_TESTS / THREADS_NUMBER;
		int remaining = NUMBER_OF_TESTS - defThreadSize * THREADS_NUMBER;
		int t = 0;
		for (int i = 0; i < THREADS_NUMBER; i++) {
			int threadSize = defThreadSize;
			if (i < remaining)
				threadSize++;
			int[] assigments = new int[threadSize];
			for (int j = 0; j < threadSize; j++) {
				assigments[j] = testsIds[t];
				t++;
			}
			workers[i] = new TestThread(i, assigments);
			threads[i] = new Thread(workers[i]);
		}
	}
	/**
	 * Threads start and join.
	 */
	public void start() {
		for (int i = 0; i < THREADS_NUMBER; i++)
			threads[i].start();
		for (int i = 0; i < THREADS_NUMBER; i++)
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	/**
	 * Runner implementation.
	 */
	class TestThread implements Runnable {
		/**
		 * Thread id.
		 */
		int threadId;
		/**
		 * Tests that were assigned to that thread
		 */
		int[] assigments;
		/**
		 * Default constructor.
		 * @param threadId       Thread id.
		 * @param assigments     Test assigned to that thread.
		 */
		TestThread(int threadId, int[] assigments) {
			this.assigments = assigments;
			this.threadId = threadId;
		}
		/**
		 * Synchronized method to store test results.
		 * @param t          Test id.
		 * @param result     TEst result.
		 */
		private void setTestResult(int t, boolean result) {
			synchronized(testsResults) {
				testsResults[t] = result;
			}
		}
		@Override
		public void run() {
			for (int t : assigments)
				setTestResult(t, runTest(t, threadId));
		}
	}
	/**
	 * Number of regression tests;
	 */
	static final int NUMBER_OF_TESTS = 2;
	/**
	 * Test scenario implementation.
	 * @param testId        Test id.
	 * @param threadId      Thread id.
	 * @return              True is test successful, otherwise false.
	 */
	static boolean runTest(int testId, int threadId) {
		SudokuSolver s1, s2;
		SudokuGenerator g;
		int sol;
		int solNum;
		int solUnq;
		int solvingState;
		int boardState;
		int[][] solution;
		int[][] puzzle;
		int[][] b1, b2, board;
		boolean solCorr;
		boolean testResult = true;
		switch(testId) {
		case 0:
			for (int example = 0; example < SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES; example++) {
				g = new SudokuGenerator(example, SudokuGenerator.PARAM_DO_NOT_TRANSFORM);
				puzzle = g.generate();
				s1 = new SudokuSolver(puzzle);
				ErrorCodes.consolePrintlnIfError(s1.solve());
				s2 = new SudokuSolver(example);
				ErrorCodes.consolePrintlnIfError(s2.solve());
				solUnq = s1.checkIfUniqueSolution();
				ErrorCodes.consolePrintlnIfError(solUnq);
				b1 = s1.getSolvedBoard();
				b2 = s2.getSolvedBoard();
				if ( (solUnq == SudokuSolver.SOLUTION_UNIQUE) && ( SudokuStore.boardsAreEqual(b1, b2) == true ) ) {
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", generate by example number, example: " + example + ", solution unique and correct: YES, time (g, s1, s2): " + g.getComputingTime() + " s., " + s1.getComputingTime() + " s., " + s2.getComputingTime() + " s.");
				} else {
					testResult = false;
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", generate by example number, example: " + example + ", solution unique and correct: NO, time (g, s1, s2): " + g.getComputingTime() + " s., " + s1.getComputingTime() + " s., " + s2.getComputingTime() + " s.");
				}
			}
			break;
		case 1:
			for (int example = 0; example < SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES; example++) {
				board = SudokuStore.getPuzzleExample(example);
				g = new SudokuGenerator(board, SudokuGenerator.PARAM_DO_NOT_TRANSFORM);
				puzzle = g.generate();
				s1 = new SudokuSolver(puzzle);
				ErrorCodes.consolePrintlnIfError(s1.solve());
				s2 = new SudokuSolver(board);
				ErrorCodes.consolePrintlnIfError(s2.solve());
				solUnq = s1.checkIfUniqueSolution();
				ErrorCodes.consolePrintlnIfError(solUnq);
				b1 = s1.getSolvedBoard();
				b2 = s2.getSolvedBoard();
				if ( (solUnq == SudokuSolver.SOLUTION_UNIQUE) && ( SudokuStore.boardsAreEqual(b1, b2) == true ) ) {
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", generate by example board, example: " + example + ", solution unique and correct: YES, time (g, s1, s2): " + g.getComputingTime() + " s., " + s1.getComputingTime() + " s., " + s2.getComputingTime() + " s.");
				} else {
					testResult = false;
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", generate by example board, example: " + example + ", solution unique and correct: NO, time (g, s1, s2): " + g.getComputingTime() + " s., " + s1.getComputingTime() + " s., " + s2.getComputingTime() + " s.");
				}
			}
			break;
		}
		if (testResult == true)
			SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + " >>>>>>>>>>>>>>>>>>>>>> SudokuSolver, result: OK");
		else
			SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + " >>>>>>>>>>>>>>>>>>>>>> SudokuSolver, result: ERROR");
		return testResult;
	}
}