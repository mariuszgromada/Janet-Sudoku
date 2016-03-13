/*
 * @(#)RegTestsStore.java        0.0.1    2016-01-30
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
import org.mariuszgromada.math.janetsudoku.SudokuStore;

/**
 * Regression tests for the SudokuStore class.
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
public class RegTestsStore {
	/**
	 * Runs SudokuStore regression tests with default number of threads.
	 * @return    Number of tests with errors.
	 */
	public static int start() {
		return start( Runtime.getRuntime().availableProcessors() );
	}
	/**
	 * Runs SudokuStore regression tests.
	 * @param threadsNumber    Number of threads.
	 * @return    Number of tests with errors.
	 */
	public static int start(int threadsNumber) {
		int numberOfTests = StoreTests.NUMBER_OF_TESTS;
		int resultsError = 0;
		int resultsOk = 0;
		long startTime = System.currentTimeMillis();
		StoreTests st = new StoreTests(threadsNumber);
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
		SudokuStore.consolePrintln("Number of SudokuStore test: " + numberOfTests + ", OK: " + resultsOk + ", ERRORS: " + resultsError + ", computing time: " + computingTime);
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
class StoreTests {
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
	StoreTests(int threadsNumber) {
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
	 * Test scenario implementation.
	 * @param testId        Test id.
	 * @param threadId      Thread id.
	 * @return              True is test successful, otherwise false.
	 */
	static boolean runTest(int testId, int threadId) {
		boolean testResult = true;
		String testDesc = "", resultDesc = "";
		int[][] a = {
				{8,0,0,0,3,0,0,4,0},
				{0,0,0,0,0,9,0,0,5},
				{0,5,1,0,0,6,0,3,0},
				{0,0,5,0,4,0,0,0,9},
				{9,0,0,1,0,2,0,0,4},
				{2,0,0,0,0,0,3,0,0},
				{0,3,0,7,0,0,6,8,1},
				{1,0,0,9,0,0,0,0,0},
				{0,7,0,0,8,0,0,0,0}
				};
		switch(testId) {
		case 0:
			testDesc = "Boards equality test.";
			{
				int[][] b = {
						{8,0,0,0,3,0,0,4,0},
						{0,0,0,0,0,9,0,0,5},
						{0,5,1,0,0,6,0,3,0},
						{0,0,5,0,4,0,0,0,9},
						{9,0,0,1,0,2,0,0,4},
						{2,0,0,0,0,0,3,0,0},
						{0,3,0,7,0,0,6,8,1},
						{1,0,0,9,0,0,0,0,0},
						{0,7,0,0,8,0,0,0,0}
						};
				if (SudokuStore.boardsAreEqual(a, b) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 1:
			testDesc = "Boards inequality test #1.";
			{
				int[][] b = {
						{8,0,0,0,3,0,0,4,0},
						{0,0,0,0,0,9,0,0,5},
						{0,5,1,0,0,6,0,3,0},
						{0,0,5,0,4,0,0,0,9},
						{9,0,0,1,0,2,0,0,4},
						{2,0,0,0,0,0,3,0,0},
						{0,3,0,7,0,0,6,8,1},
						{1,0,0,9,0,1,0,0,0},
						{0,7,0,0,8,0,0,0,0}
						};
				if (SudokuStore.boardsAreEqual(a, b) == false) {
					resultDesc = "Expecting not equal - are not equal.";
				} else {
					resultDesc = "Expecting not equal - are equal.";
					testResult = false;
				}
			}
			break;
		case 2:
			testDesc = "Boards inequality test #2.";
			{
				int[][] b = {
						{8,0,0,0,3,0,0,4,0},
						{0,0,0,0,0,9,0,0,5},
						{0,5,1,0,0,6,0,3,0},
						{0,0,5,0,4,0,0,0,9},
						{9,0,0,1,0,2,0,0,4},
						{2,0,0,0,0,0,3,0,0},
						{0,3,0,7,0,0,6,8,1},
						{1,0,0,9,0,0,0,0,0},
						{0,7,0,0,8,0,0,0,0},
						{0,7,0,0,8,0,0,0,0}
						};
				if (SudokuStore.boardsAreEqual(a, b) == false) {
					resultDesc = "Expecting not equal - are not equal.";
				} else {
					resultDesc = "Expecting not equal - are equal.";
					testResult = false;
				}
			}
			break;
		case 3:
			testDesc = "Boards inequality test #3.";
			{
				int[][] b = {
						{8,0,0,0,3,0,0,4,0,1},
						{0,0,0,0,0,9,0,0,5,1},
						{0,5,1,0,0,6,0,3,0,1},
						{0,0,5,0,4,0,0,0,9,1},
						{9,0,0,1,0,2,0,0,4,1},
						{2,0,0,0,0,0,3,0,0,1},
						{0,3,0,7,0,0,6,8,1,1},
						{1,0,0,9,0,0,0,0,0,1},
						{0,7,0,0,8,0,0,0,0,1}
						};
				if (SudokuStore.boardsAreEqual(a, b) == false) {
					resultDesc = "Expecting not equal - are not equal.";
				} else {
					resultDesc = "Expecting not equal - are equal.";
					testResult = false;
				}
			}
			break;
		case 4:
			testDesc = "Boards inequality test #4.";
			{
				int[][] b = null;
				if (SudokuStore.boardsAreEqual(a, b) == false) {
					resultDesc = "Expecting not equal - are not equal.";
				} else {
					resultDesc = "Expecting not equal - are equal.";
					testResult = false;
				}
			}
			break;
		case 5:
			testDesc = "Boards inequality test #5.";
			{
				int[][] c = null;
				int[][] b = null;
				if (SudokuStore.boardsAreEqual(c, b) == false) {
					resultDesc = "Expecting not equal - are not equal.";
				} else {
					resultDesc = "Expecting not equal - are equal.";
					testResult = false;
				}
			}
			break;
		case 6:
			testDesc = "swapColsInSegment (0, 0, 1)";
			{
				int[][] b = {
						{0,8,0,0,3,0,0,4,0},
						{0,0,0,0,0,9,0,0,5},
						{5,0,1,0,0,6,0,3,0},
						{0,0,5,0,4,0,0,0,9},
						{0,9,0,1,0,2,0,0,4},
						{0,2,0,0,0,0,3,0,0},
						{3,0,0,7,0,0,6,8,1},
						{0,1,0,9,0,0,0,0,0},
						{7,0,0,0,8,0,0,0,0}
						};
				int[][] c = SudokuStore.swapColsInSegment(a, 0, 0, 1);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 7:
			testDesc = "swapColsInSegment (1, 2, 1)";
			{
				int[][] b = {
						{8,0,0,0,0,3,0,4,0},
						{0,0,0,0,9,0,0,0,5},
						{0,5,1,0,6,0,0,3,0},
						{0,0,5,0,0,4,0,0,9},
						{9,0,0,1,2,0,0,0,4},
						{2,0,0,0,0,0,3,0,0},
						{0,3,0,7,0,0,6,8,1},
						{1,0,0,9,0,0,0,0,0},
						{0,7,0,0,0,8,0,0,0}
						};
				int[][] c = SudokuStore.swapColsInSegment(a, 1, 2, 1);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 8:
			testDesc = "swapColsInSegment (2, 0, 2)";
			{
				int[][] b = {
						{8,0,0,0,3,0,0,4,0},
						{0,0,0,0,0,9,5,0,0},
						{0,5,1,0,0,6,0,3,0},
						{0,0,5,0,4,0,9,0,0},
						{9,0,0,1,0,2,4,0,0},
						{2,0,0,0,0,0,0,0,3},
						{0,3,0,7,0,0,1,8,6},
						{1,0,0,9,0,0,0,0,0},
						{0,7,0,0,8,0,0,0,0}
						};
				int[][] c = SudokuStore.swapColsInSegment(a, 2, 0, 2);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 9:
			testDesc = "swapRowsInSegment (0, 0, 1)";
			{
				int[][] b = {
						{0,0,0,0,0,9,0,0,5},
						{8,0,0,0,3,0,0,4,0},
						{0,5,1,0,0,6,0,3,0},
						{0,0,5,0,4,0,0,0,9},
						{9,0,0,1,0,2,0,0,4},
						{2,0,0,0,0,0,3,0,0},
						{0,3,0,7,0,0,6,8,1},
						{1,0,0,9,0,0,0,0,0},
						{0,7,0,0,8,0,0,0,0}
						};
				int[][] c = SudokuStore.swapRowsInSegment(a, 0, 0, 1);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 10:
			testDesc = "swapRowsInSegment (1, 2, 1)";
			{
				int[][] b = {
						{8,0,0,0,3,0,0,4,0},
						{0,0,0,0,0,9,0,0,5},
						{0,5,1,0,0,6,0,3,0},
						{0,0,5,0,4,0,0,0,9},
						{2,0,0,0,0,0,3,0,0},
						{9,0,0,1,0,2,0,0,4},
						{0,3,0,7,0,0,6,8,1},
						{1,0,0,9,0,0,0,0,0},
						{0,7,0,0,8,0,0,0,0}
						};
				int[][] c = SudokuStore.swapRowsInSegment(a, 1, 2, 1);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 11:
			testDesc = "swapRowsInSegment (2, 0, 2)";
			{
				int[][] b = {
						{8,0,0,0,3,0,0,4,0},
						{0,0,0,0,0,9,0,0,5},
						{0,5,1,0,0,6,0,3,0},
						{0,0,5,0,4,0,0,0,9},
						{9,0,0,1,0,2,0,0,4},
						{2,0,0,0,0,0,3,0,0},
						{0,7,0,0,8,0,0,0,0},
						{1,0,0,9,0,0,0,0,0},
						{0,3,0,7,0,0,6,8,1}
						};
				int[][] c = SudokuStore.swapRowsInSegment(a, 2, 0, 2);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 12:
			testDesc = "permuteColsInSegment (0, 2, 1, 0)";
			{
				int[] p = {2, 1, 0};
				int[][] b = {
						{0,0,8,0,3,0,0,4,0},
						{0,0,0,0,0,9,0,0,5},
						{1,5,0,0,0,6,0,3,0},
						{5,0,0,0,4,0,0,0,9},
						{0,0,9,1,0,2,0,0,4},
						{0,0,2,0,0,0,3,0,0},
						{0,3,0,7,0,0,6,8,1},
						{0,0,1,9,0,0,0,0,0},
						{0,7,0,0,8,0,0,0,0}
						};
				int[][] c = SudokuStore.permuteColsInSegment(a, 0, p);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 13:
			testDesc = "permuteColsInSegment (1, 1, 2, 0)";
			{
				int[] p = {1, 2, 0};
				int[][] b = {
						{8,0,0,3,0,0,0,4,0},
						{0,0,0,0,9,0,0,0,5},
						{0,5,1,0,6,0,0,3,0},
						{0,0,5,4,0,0,0,0,9},
						{9,0,0,0,2,1,0,0,4},
						{2,0,0,0,0,0,3,0,0},
						{0,3,0,0,0,7,6,8,1},
						{1,0,0,0,0,9,0,0,0},
						{0,7,0,8,0,0,0,0,0}
						};
				int[][] c = SudokuStore.permuteColsInSegment(a, 1, p);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
					SudokuStore.consolePrintBoard(a);
					SudokuStore.consolePrintBoard(c);
				}
			}
			break;
		case 14:
			testDesc = "permuteColsInSegment (2, 0, 2, 1)";
			{
				int[] p = {0, 2, 1};
				int[][] b = {
						{8,0,0,0,3,0,0,0,4},
						{0,0,0,0,0,9,0,5,0},
						{0,5,1,0,0,6,0,0,3},
						{0,0,5,0,4,0,0,9,0},
						{9,0,0,1,0,2,0,4,0},
						{2,0,0,0,0,0,3,0,0},
						{0,3,0,7,0,0,6,1,8},
						{1,0,0,9,0,0,0,0,0},
						{0,7,0,0,8,0,0,0,0}
						};
				int[][] c = SudokuStore.permuteColsInSegment(a, 2, p);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 15:
			testDesc = "permuteRowsInSegment (0, 2, 1, 0)";
			{
				int[] p = {2, 1, 0};
				int[][] b = {
						{0,5,1,0,0,6,0,3,0},
						{0,0,0,0,0,9,0,0,5},
						{8,0,0,0,3,0,0,4,0},
						{0,0,5,0,4,0,0,0,9},
						{9,0,0,1,0,2,0,0,4},
						{2,0,0,0,0,0,3,0,0},
						{0,3,0,7,0,0,6,8,1},
						{1,0,0,9,0,0,0,0,0},
						{0,7,0,0,8,0,0,0,0}
						};
				int[][] c = SudokuStore.permuteRowsInSegment(a, 0, p);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 16:
			testDesc = "permuteRowsInSegment (1, 1, 2, 0)";
			{
				int[] p = {1, 2, 0};
				int[][] b = {
						{8,0,0,0,3,0,0,4,0},
						{0,0,0,0,0,9,0,0,5},
						{0,5,1,0,0,6,0,3,0},
						{9,0,0,1,0,2,0,0,4},
						{2,0,0,0,0,0,3,0,0},
						{0,0,5,0,4,0,0,0,9},
						{0,3,0,7,0,0,6,8,1},
						{1,0,0,9,0,0,0,0,0},
						{0,7,0,0,8,0,0,0,0}
						};
				int[][] c = SudokuStore.permuteRowsInSegment(a, 1, p);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
					SudokuStore.consolePrintBoard(a);
					SudokuStore.consolePrintBoard(c);
				}
			}
			break;
		case 17:
			testDesc = "permuteRowsInSegment (2, 0, 2, 1)";
			{
				int[] p = {0, 2, 1};
				int[][] b = {
						{8,0,0,0,3,0,0,4,0},
						{0,0,0,0,0,9,0,0,5},
						{0,5,1,0,0,6,0,3,0},
						{0,0,5,0,4,0,0,0,9},
						{9,0,0,1,0,2,0,0,4},
						{2,0,0,0,0,0,3,0,0},
						{0,3,0,7,0,0,6,8,1},
						{0,7,0,0,8,0,0,0,0},
						{1,0,0,9,0,0,0,0,0}
						};
				int[][] c = SudokuStore.permuteRowsInSegment(a, 2, p);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 18:
			testDesc = "permuteColSegments (1, 2, 0)";
			{
				int[] p = {1, 2, 0};
				int[][] b = {
						{0,3,0,0,4,0,8,0,0},
						{0,0,9,0,0,5,0,0,0},
						{0,0,6,0,3,0,0,5,1},
						{0,4,0,0,0,9,0,0,5},
						{1,0,2,0,0,4,9,0,0},
						{0,0,0,3,0,0,2,0,0},
						{7,0,0,6,8,1,0,3,0},
						{9,0,0,0,0,0,1,0,0},
						{0,8,0,0,0,0,0,7,0}
						};
				int[][] c = SudokuStore.permuteColSegments(a, p);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 19:
			testDesc = "permuteRowSegments (1, 2, 0)";
			{
				int[] p = {1, 2, 0};
				int[][] b = {
						{0,0,5,0,4,0,0,0,9},
						{9,0,0,1,0,2,0,0,4},
						{2,0,0,0,0,0,3,0,0},
						{0,3,0,7,0,0,6,8,1},
						{1,0,0,9,0,0,0,0,0},
						{0,7,0,0,8,0,0,0,0},
						{8,0,0,0,3,0,0,4,0},
						{0,0,0,0,0,9,0,0,5},
						{0,5,1,0,0,6,0,3,0}
						};
				int[][] c = SudokuStore.permuteRowSegments(a, p);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 20:
			testDesc = "reflectHorizontally";
			{
				int[][] b = {
						{0,7,0,0,8,0,0,0,0},
						{1,0,0,9,0,0,0,0,0},
						{0,3,0,7,0,0,6,8,1},
						{2,0,0,0,0,0,3,0,0},
						{9,0,0,1,0,2,0,0,4},
						{0,0,5,0,4,0,0,0,9},
						{0,5,1,0,0,6,0,3,0},
						{0,0,0,0,0,9,0,0,5},
						{8,0,0,0,3,0,0,4,0}
						};
				int[][] c = SudokuStore.reflectHorizontally(a);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 21:
			testDesc = "reflectVertically";
			{
				int[][] b = {
						{0,4,0,0,3,0,0,0,8},
						{5,0,0,9,0,0,0,0,0},
						{0,3,0,6,0,0,1,5,0},
						{9,0,0,0,4,0,5,0,0},
						{4,0,0,2,0,1,0,0,9},
						{0,0,3,0,0,0,0,0,2},
						{1,8,6,0,0,7,0,3,0},
						{0,0,0,0,0,9,0,0,1},
						{0,0,0,0,8,0,0,7,0}
						};
				int[][] c = SudokuStore.reflectVertically(a);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 22:
			testDesc = "rotateClockWise";
			{
				int[][] b = {
						{0,1,0,2,9,0,0,0,8},
						{7,0,3,0,0,0,5,0,0},
						{0,0,0,0,0,5,1,0,0},
						{0,9,7,0,1,0,0,0,0},
						{8,0,0,0,0,4,0,0,3},
						{0,0,0,0,2,0,6,9,0},
						{0,0,6,3,0,0,0,0,0},
						{0,0,8,0,0,0,3,0,4},
						{0,0,1,0,4,9,0,5,0}
						};
				int[][] c = SudokuStore.rotateClockWise(a);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 23:
			testDesc = "rotateCounterclockWise";
			{
				int[][] b = {
						{0,5,0,9,4,0,1,0,0},
						{4,0,3,0,0,0,8,0,0},
						{0,0,0,0,0,3,6,0,0},
						{0,9,6,0,2,0,0,0,0},
						{3,0,0,4,0,0,0,0,8},
						{0,0,0,0,1,0,7,9,0},
						{0,0,1,5,0,0,0,0,0},
						{0,0,5,0,0,0,3,0,7},
						{8,0,0,0,9,2,0,1,0}
						};
				int[][] c = SudokuStore.rotateCounterclockWise(a);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 24:
			testDesc = "transposeTlBr";
			{
				int[][] b = {
						{8,0,0,0,9,2,0,1,0},
						{0,0,5,0,0,0,3,0,7},
						{0,0,1,5,0,0,0,0,0},
						{0,0,0,0,1,0,7,9,0},
						{3,0,0,4,0,0,0,0,8},
						{0,9,6,0,2,0,0,0,0},
						{0,0,0,0,0,3,6,0,0},
						{4,0,3,0,0,0,8,0,0},
						{0,5,0,9,4,0,1,0,0}
						};
				int[][] c = SudokuStore.transposeTlBr(a);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 25:
			testDesc = "transposeTrBl";
			{
				int[][] b = {
						{0,0,1,0,4,9,0,5,0},
						{0,0,8,0,0,0,3,0,4},
						{0,0,6,3,0,0,0,0,0},
						{0,0,0,0,2,0,6,9,0},
						{8,0,0,0,0,4,0,0,3},
						{0,9,7,0,1,0,0,0,0},
						{0,0,0,0,0,5,1,0,0},
						{7,0,3,0,0,0,5,0,0},
						{0,1,0,2,9,0,0,0,8}
						};
				int[][] c = SudokuStore.transposeTrBl(a);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 26:
			testDesc = "swapColSegments (0, 1)";
			{
				int[][] b = {
						{0,3,0,8,0,0,0,4,0},
						{0,0,9,0,0,0,0,0,5},
						{0,0,6,0,5,1,0,3,0},
						{0,4,0,0,0,5,0,0,9},
						{1,0,2,9,0,0,0,0,4},
						{0,0,0,2,0,0,3,0,0},
						{7,0,0,0,3,0,6,8,1},
						{9,0,0,1,0,0,0,0,0},
						{0,8,0,0,7,0,0,0,0}
						};
				int[][] c = SudokuStore.swapColSegments(a, 0, 1);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 27:
			testDesc = "swapColSegments (0, 2)";
			{
				int[][] b = {
						{0,4,0,0,3,0,8,0,0},
						{0,0,5,0,0,9,0,0,0},
						{0,3,0,0,0,6,0,5,1},
						{0,0,9,0,4,0,0,0,5},
						{0,0,4,1,0,2,9,0,0},
						{3,0,0,0,0,0,2,0,0},
						{6,8,1,7,0,0,0,3,0},
						{0,0,0,9,0,0,1,0,0},
						{0,0,0,0,8,0,0,7,0}
						};
				int[][] c = SudokuStore.swapColSegments(a, 0, 2);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 28:
			testDesc = "swapColSegments (1, 2)";
			{
				int[][] b = {
						{8,0,0,0,4,0,0,3,0},
						{0,0,0,0,0,5,0,0,9},
						{0,5,1,0,3,0,0,0,6},
						{0,0,5,0,0,9,0,4,0},
						{9,0,0,0,0,4,1,0,2},
						{2,0,0,3,0,0,0,0,0},
						{0,3,0,6,8,1,7,0,0},
						{1,0,0,0,0,0,9,0,0},
						{0,7,0,0,0,0,0,8,0}
						};
				int[][] c = SudokuStore.swapColSegments(a, 1, 2);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 29:
			testDesc = "swapRowSegments (0, 1)";
			{
				int[][] b = {
						{0,0,5,0,4,0,0,0,9},
						{9,0,0,1,0,2,0,0,4},
						{2,0,0,0,0,0,3,0,0},
						{8,0,0,0,3,0,0,4,0},
						{0,0,0,0,0,9,0,0,5},
						{0,5,1,0,0,6,0,3,0},
						{0,3,0,7,0,0,6,8,1},
						{1,0,0,9,0,0,0,0,0},
						{0,7,0,0,8,0,0,0,0}
						};
				int[][] c = SudokuStore.swapRowSegments(a, 0, 1);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 30:
			testDesc = "swapRowSegments (0, 2)";
			{
				int[][] b = {
						{0,3,0,7,0,0,6,8,1},
						{1,0,0,9,0,0,0,0,0},
						{0,7,0,0,8,0,0,0,0},
						{0,0,5,0,4,0,0,0,9},
						{9,0,0,1,0,2,0,0,4},
						{2,0,0,0,0,0,3,0,0},
						{8,0,0,0,3,0,0,4,0},
						{0,0,0,0,0,9,0,0,5},
						{0,5,1,0,0,6,0,3,0}
						};
				int[][] c = SudokuStore.swapRowSegments(a, 0, 2);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 31:
			testDesc = "swapRowSegments (1, 2)";
			{
				int[][] b = {
						{8,0,0,0,3,0,0,4,0},
						{0,0,0,0,0,9,0,0,5},
						{0,5,1,0,0,6,0,3,0},
						{0,3,0,7,0,0,6,8,1},
						{1,0,0,9,0,0,0,0,0},
						{0,7,0,0,8,0,0,0,0},
						{0,0,5,0,4,0,0,0,9},
						{9,0,0,1,0,2,0,0,4},
						{2,0,0,0,0,0,3,0,0}
						};
				int[][] c = SudokuStore.swapRowSegments(a, 1, 2);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 32:
			testDesc = "permuteBoard";
			{
				int[] p = {5, 2, 8, 9, 1, 3, 6, 4, 7};
				int[][] b = {
						{8,0,0,0,3,0,0,4,0},
						{0,0,0,0,0,9,0,0,5},
						{0,5,1,0,0,6,0,3,0},
						{0,0,5,0,4,0,0,0,9},
						{9,0,0,1,0,2,0,0,4},
						{2,0,0,0,0,0,3,0,0},
						{0,3,0,7,0,0,6,8,1},
						{1,0,0,9,0,0,0,0,0},
						{0,7,0,0,8,0,0,0,0}
						};
				int[][] c = SudokuStore.permuteBoard(a, p);
				if (SudokuStore.boardsAreEqual(b, c) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		}
		if (testResult == true)
			SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + " " + testDesc + " " + resultDesc + " >>>>>>>>>>>>>>>>>>>>>> SudokuStore, result: OK");
		else
			SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + " " + testDesc + " " + resultDesc + " >>>>>>>>>>>>>>>>>>>>>> SudokuStore, result: ERROR");
		return testResult;
	}
	/**
	 * Number of regression tests;
	 */
	static final int NUMBER_OF_TESTS = 33;
}