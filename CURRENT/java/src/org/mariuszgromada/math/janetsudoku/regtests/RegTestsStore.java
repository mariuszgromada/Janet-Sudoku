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
import org.mariuszgromada.math.janetsudoku.SudokuPuzzles;
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
	private TestRunner[] runners;
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
		runners = new TestRunner[THREADS_NUMBER];
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
			runners[i] = new TestRunner(i, assigments);
			threads[i] = new Thread(runners[i]);
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
	class TestRunner implements Runnable {
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
		TestRunner(int threadId, int[] assigments) {
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
		case 33:
			testDesc = "isValidPermutation #1";
			{
				int[] p = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
				if (SudokuStore.isValidPermutation(p) == true) {
					resultDesc = "Expecting valid - is valid.";
				} else {
					resultDesc = "Expecting valid - is not valid.";
					testResult = false;
				}
			}
			break;
		case 34:
			testDesc = "isValidPermutation #2";
			{
				int[] p = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
				if (SudokuStore.isValidPermutation(p, 10) == true) {
					resultDesc = "Expecting valid - is valid.";
				} else {
					resultDesc = "Expecting valid - is not valid.";
					testResult = false;
				}
			}
			break;
		case 35:
			testDesc = "isValidPermutation #3";
			{
				int[] p = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
				if (SudokuStore.isValidPermutation(p, 8) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 36:
			testDesc = "isValidPermutation #4";
			{
				int[] p = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
				if (SudokuStore.isValidPermutation(p, 12) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 37:
			testDesc = "isValidPermutation #5";
			{
				int[] p = {0, 1, -2, 3, 4, 5, 6, 7, 8, 9};
				if (SudokuStore.isValidPermutation(p) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 38:
			testDesc = "isValidPermutation #6";
			{
				int[] p = {0, 1, 20, 3, 4, 5, 6, 7, 8, 9};
				if (SudokuStore.isValidPermutation(p) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 39:
			testDesc = "isValidPermutation #7";
			{
				int[] p = {0, 1, 3, 4, 5, 6, 7, 8, 9};
				if (SudokuStore.isValidPermutation(p) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 40:
			testDesc = "isValidPermutation #8";
			{
				int[] p = {};
				if (SudokuStore.isValidPermutation(p) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 41:
			testDesc = "isValidPermutation #9";
			{
				int[] p = null;
				if (SudokuStore.isValidPermutation(p) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 42:
			testDesc = "isValidPermutation #10";
			{
				int[] p = {2, 1, 3, 0, 4, 5, 7, 8, 6, 9};
				if (SudokuStore.isValidPermutation(p) == true) {
					resultDesc = "Expecting valid - is valid.";
				} else {
					resultDesc = "Expecting valid - is not valid.";
					testResult = false;
				}
			}
			break;
		case 43:
			testDesc = "checkPuzzle #1";
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
				if (SudokuStore.checkPuzzle(b) == true) {
					resultDesc = "Expecting valid - is valid.";
				} else {
					resultDesc = "Expecting valid - is not valid.";
					testResult = false;
				}
			}
			break;
		case 44:
			testDesc = "checkPuzzle #2 - empty";
			{
				int[][] b = {
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0}
					};
				if (SudokuStore.checkPuzzle(b) == true) {
					resultDesc = "Expecting valid - is valid.";
				} else {
					resultDesc = "Expecting valid - is not valid.";
					testResult = false;
				}
			}
			break;
		case 45:
			testDesc = "checkPuzzle #3 - solved";
			{
				int[][] b = {
						{3,1,5,8,2,7,9,4,6},
						{4,6,8,9,1,5,7,3,2},
						{7,2,9,3,4,6,5,1,8},
						{9,4,6,5,3,8,1,2,7},
						{5,7,1,6,9,2,4,8,3},
						{8,3,2,1,7,4,6,9,5},
						{6,9,3,2,5,1,8,7,4},
						{2,5,7,4,8,9,3,6,1},
						{1,8,4,7,6,3,2,5,9}
					};
				if (SudokuStore.checkPuzzle(b) == true) {
					resultDesc = "Expecting valid - is valid.";
				} else {
					resultDesc = "Expecting valid - is not valid.";
					testResult = false;
				}
			}
			break;
		case 46:
			testDesc = "checkPuzzle #4 - incorrect size";
			{
				int[][] b = {
						{3,1,5,8,2,7,9,4,6,1},
						{4,6,8,9,1,5,7,3,2,1},
						{7,2,9,3,4,6,5,1,8,1},
						{9,4,6,5,3,8,1,2,7,1},
						{5,7,1,6,9,2,4,8,3,1},
						{8,3,2,1,7,4,6,9,5,1},
						{6,9,3,2,5,1,8,7,4,1},
						{2,5,7,4,8,9,3,6,1,1},
						{1,8,4,7,6,3,2,5,9,1}
					};
				if (SudokuStore.checkPuzzle(b) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 47:
			testDesc = "checkPuzzle #5 - incorrect size";
			{
				int[][] b = {
						{3,1,5,8,2,7,9,4,6},
						{4,6,8,9,1,5,7,3,2},
						{7,2,9,3,4,6,5,1,8},
						{9,4,6,5,3,8,1,2,7},
						{5,7,1,6,9,2,4,8,3},
						{8,3,2,1,7,4,6,9,5},
						{6,9,3,2,5,1,8,7,4},
						{2,5,7,4,8,9,3,6,1},
						{1,8,4,7,6,3,2,5,9},
						{1,8,4,7,6,3,2,5,9}
					};
				if (SudokuStore.checkPuzzle(b) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 48:
			testDesc = "checkPuzzle #6 - incorrect size";
			{
				int[][] b = {
						{3,1,5,8,2,7,9,4,6},
						{4,6,8,9,1,5,7,3,2},
						{7,2,9,3,4,6,5,1,8},
						{9,4,6,5,3,8,1,2,7},
						{5,7,1,6,9,2,4,8,3},
						{8,3,2,1,7,4,6,9,5},
						{6,9,3,2,5,1,8,7,4},
						{2,5,7,4,8,9,3,6,1}
					};
				if (SudokuStore.checkPuzzle(b) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 49:
			testDesc = "checkPuzzle #7 - incorrect size";
			{
				int[][] b = {
						{3,1,5,8,2,7,9,4},
						{4,6,8,9,1,5,7,3},
						{7,2,9,3,4,6,5,1},
						{9,4,6,5,3,8,1,2},
						{5,7,1,6,9,2,4,8},
						{8,3,2,1,7,4,6,9},
						{6,9,3,2,5,1,8,7},
						{2,5,7,4,8,9,3,6},
						{1,8,4,7,6,3,2,5}
					};
				if (SudokuStore.checkPuzzle(b) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 50:
			testDesc = "checkPuzzle #8 - empty board";
			{
				int[][] b = {};
				if (SudokuStore.checkPuzzle(b) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 51:
			testDesc = "checkPuzzle #9 - null board";
			{
				int[][] b = null;
				if (SudokuStore.checkPuzzle(b) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 52:
			testDesc = "checkSolvedBoard #1 - solved";
			{
				int[][] b = {
						{3,1,5,8,2,7,9,4,6},
						{4,6,8,9,1,5,7,3,2},
						{7,2,9,3,4,6,5,1,8},
						{9,4,6,5,3,8,1,2,7},
						{5,7,1,6,9,2,4,8,3},
						{8,3,2,1,7,4,6,9,5},
						{6,9,3,2,5,1,8,7,4},
						{2,5,7,4,8,9,3,6,1},
						{1,8,4,7,6,3,2,5,9}
					};
				if (SudokuStore.checkSolvedBoard(b) == true) {
					resultDesc = "Expecting valid - is valid.";
				} else {
					resultDesc = "Expecting valid - is not valid.";
					testResult = false;
				}
			}
			break;
		case 53:
			testDesc = "checkSolvedBoard #2 - one missing";
			{
				int[][] b = {
						{3,1,5,8,2,7,9,4,6},
						{4,6,8,9,1,5,7,3,2},
						{7,2,9,3,4,6,5,1,8},
						{9,4,6,5,3,8,1,2,7},
						{5,7,1,6,9,2,4,8,3},
						{8,3,2,1,7,4,6,9,5},
						{6,9,3,2,5,1,8,7,4},
						{2,5,7,4,8,9,3,6,1},
						{1,8,4,7,6,3,2,5,0}
					};
				if (SudokuStore.checkSolvedBoard(b) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 54:
			testDesc = "checkSolvedBoard #3 - duplicate";
			{
				int[][] b = {
						{3,1,5,8,2,7,9,4,6},
						{4,6,8,9,1,5,7,3,2},
						{7,2,9,3,4,6,5,1,8},
						{9,4,6,5,3,8,1,2,7},
						{5,7,1,6,9,2,4,8,3},
						{8,3,2,1,7,4,6,9,5},
						{6,9,3,2,5,1,8,7,4},
						{2,5,7,4,8,9,3,6,1},
						{1,8,4,7,6,3,2,5,1}
					};
				if (SudokuStore.checkSolvedBoard(b) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 55:
			testDesc = "checkSolvedBoard #4 - incorect size";
			{
				int[][] b = {
						{3,1,5,8,2,7,9,4},
						{4,6,8,9,1,5,7,3},
						{7,2,9,3,4,6,5,1},
						{9,4,6,5,3,8,1,2},
						{5,7,1,6,9,2,4,8},
						{8,3,2,1,7,4,6,9},
						{6,9,3,2,5,1,8,7},
						{2,5,7,4,8,9,3,6},
						{1,8,4,7,6,3,2,5}
					};
				if (SudokuStore.checkSolvedBoard(b) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 56:
			testDesc = "checkSolvedBoard #5 - incorrect size";
			{
				int[][] b = {
						{3,1,5,8,2,7,9,4,6},
						{4,6,8,9,1,5,7,3,2},
						{7,2,9,3,4,6,5,1,8},
						{9,4,6,5,3,8,1,2,7},
						{5,7,1,6,9,2,4,8,3},
						{8,3,2,1,7,4,6,9,5},
						{6,9,3,2,5,1,8,7,4},
						{2,5,7,4,8,9,3,6,1}
					};
				if (SudokuStore.checkSolvedBoard(b) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 57:
			testDesc = "checkSolvedBoard #6 - empty board";
			{
				int[][] b = { };
				if (SudokuStore.checkSolvedBoard(b) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 58:
			testDesc = "checkSolvedBoard #7 - null board";
			{
				int[][] b = null;
				if (SudokuStore.checkSolvedBoard(b) == false) {
					resultDesc = "Expecting not valid - is not valid.";
				} else {
					resultDesc = "Expecting not valid - is valid.";
					testResult = false;
				}
			}
			break;
		case 59:
			testDesc = "loadBoard #1";
			{
				String[] s = {
					"{8,0,0,0,3,0,0,4,0}",
					"{0,0,0,0,0,9,0,0,5}",
					"{0,5,1,0,0,6,0,3,0}",
					"{0,0,5,0,4,0,0,0,9}",
					"{9,0,0,1,0,2,0,0,4}",
					"{2,0,0,0,0,0,3,0,0}",
					"{0,3,0,7,0,0,6,8,1}",
					"{1,0,0,9,0,0,0,0,0}",
					"{0,7,0,0,8,0,0,0,0}"
				};
				int[][] b = SudokuStore.loadBoard(s);
				if (SudokuStore.boardsAreEqual(a, b) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
					SudokuStore.consolePrintBoard(a);
					SudokuStore.consolePrintBoard(b);
				}
			}
			break;
		case 60:
			testDesc = "loadBoard #2";
			{
				String[] s = {
					"800030040",
					"000009005",
					"051006030",
					"005040009",
					"900102004",
					"200000300",
					"030700681",
					"100900000",
					"070080000"
				};
				int[][] b = SudokuStore.loadBoard(s);
				if (SudokuStore.boardsAreEqual(a, b) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
					SudokuStore.consolePrintBoard(a);
					SudokuStore.consolePrintBoard(b);
				}
			}
			break;
		case 61:
			testDesc = "loadBoard #3";
			{
				String[] s = {
						"8|0|0|0|3|0|0|4|0",
						"0|0|0|0|0|9|0|0|5",
						"0|5|1|0|0|6|0|3|0",
						"0|0|5|0|4|0|0|0|9",
						"9|0|0|1|0|2|0|0|4",
						"2|0|0|0|0|0|3|0|0",
						"0|3|0|7|0|0|6|8|1",
						"1|0|0|9|0|0|0|0|0",
						"0|7|0|0|8|0|0|0|0"
				};
				int[][] b = SudokuStore.loadBoard(s);
				if (SudokuStore.boardsAreEqual(a, b) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
					SudokuStore.consolePrintBoard(a);
					SudokuStore.consolePrintBoard(b);
				}
			}
			break;
		case 62:
			testDesc = "loadBoard #4";
			{
				String[] s = {
						"-----------",
						"800 030 040",
						"000 009 005",
						"051 006 030",
						"-----------",
						"005 040 009",
						"900 102 004",
						"200 000 300",
						"-----------",
						"030 700 681",
						"100 900 000",
						"070 080 000",
						"-----------"

				};
				int[][] b = SudokuStore.loadBoard(s);
				if (SudokuStore.boardsAreEqual(a, b) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
					SudokuStore.consolePrintBoard(a);
					SudokuStore.consolePrintBoard(b);
				}
			}
			break;
		case 63:
			testDesc = "loadBoard #5";
			{
				String[] s = {
						"+-----+-----+-----+",
						"| 800 | 030 | 040 |",
						"| 000 | 009 | 005 |",
						"| 051 | 006 | 030 |",
						"+-----+-----+-----+",
						"| 005 | 040 | 009 |",
						"| 900 | 102 | 004 |",
						"| 200 | 000 | 300 |",
						"+-----+-----+-----+",
						"| 030 | 700 | 681 |",
						"| 100 | 900 | 000 |",
						"| 070 | 080 | 000 |",
						"+-----+-----+-----+"
				};
				int[][] b = SudokuStore.loadBoard(s);
				if (SudokuStore.boardsAreEqual(a, b) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
					SudokuStore.consolePrintBoard(a);
					SudokuStore.consolePrintBoard(b);
				}
			}
			break;
		case 64:
			testDesc = "loadBoard #6";
			{
				String[] s = {
						"+-----+-----+-----+",
						"| 8.. | .3. | .4. |",
						"| ... | ..9 | ..5 |",
						"| .51 | ..6 | .3. |",
						"+-----+-----+-----+",
						"| ..5 | .4. | ..9 |",
						"| 9.. | 1.2 | ..4 |",
						"| 2.. | ... | 3.. |",
						"+-----+-----+-----+",
						"| .3. | 7.. | 681 |",
						"| 1.. | 9.. | ... |",
						"| .7. | .8. | ... |",
						"+-----+-----+-----+"
				};
				int[][] b = SudokuStore.loadBoard(s);
				if (SudokuStore.boardsAreEqual(a, b) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
					SudokuStore.consolePrintBoard(a);
					SudokuStore.consolePrintBoard(b);
				}
			}
			break;
		case 65:
			testDesc = "loadBoard #7";
			{
				String[] s = {
						"+-------+-------+-------+",
						"| 8 . . | . 3 . | . 4 . |",
						"| . . . | . . 9 | . . 5 |",
						"| . 5 1 | . . 6 | . 3 . |",
						"+-------+-------+-------+",
						"| . . 5 | . 4 . | . . 9 |",
						"| 9 . . | 1 . 2 | . . 4 |",
						"| 2 . . | . . . | 3 . . |",
						"+-------+-------+-------+",
						"| . 3 . | 7 . . | 6 8 1 |",
						"| 1 . . | 9 . . | . . . |",
						"| . 7 . | . 8 . | . . . |",
						"+-------+-------+-------+"
				};
				int[][] b = SudokuStore.loadBoard(s);
				if (SudokuStore.boardsAreEqual(a, b) == true) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 66:
			testDesc = "getPuzzleExample";
			{
				int i;
				for (i = 0; i < SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES; i++) {
					int[][] b = SudokuStore.getPuzzleExample(i);
					if (SudokuStore.checkPuzzle(b) == false) {
						testResult = false;
						break;
					}
				}
				if (testResult == true) {
					resultDesc = "All puzzle examples avaiable and correct.";
				} else {
					resultDesc = "Error in puzzle example nr: " + i;
				}
			}
			break;
		case 67:
			testDesc = "getPuzzleExampleRating";
			{
				int i;
				for (i = 0; i < SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES; i++) {
					double r = SudokuStore.getPuzzleExampleRating(i);
					if (r < 0) {
						testResult = false;
						break;
					}
				}
				if (testResult == true) {
					resultDesc = "All examples with pre-calculated rating.";
				} else {
					resultDesc = "No pre-calculated rating for example nr: " + i;
				}
			}
			break;
		case 68:
			testDesc = "randomNumber";
			{
				int j = 0;
				for (int i = 0; i < 100000; i++) {
					j = SudokuStore.randomNumber(20);
					if ( (j < 1) || (j > 20) ) {
						testResult = false;
						break;
					}
				}
				if (testResult == true) {
					resultDesc = "Expecting between 1 and 20 - all generated are between 1 and 20.";
				} else {
					resultDesc = "Expecting between 1 and 20 - generated number: " + j;
				}
			}
			break;
		case 69:
			testDesc = "randomIndex";
			{
				int j = 0;
				for (int i = 0; i < 100000; i++) {
					j = SudokuStore.randomIndex(20);
					if ( (j < 0) || (j > 19) ) {
						testResult = false;
						break;
					}
				}
				if (testResult == true) {
					resultDesc = "Expecting between 0 and 19 - all generated are between 0 and 19.";
				} else {
					resultDesc = "Expecting between 0 and 19 - generated number: " + j;
				}
			}
			break;
		case 70:
			testDesc = "generatePermutation";
			{
				for (int i = 0; i < 1000; i++) {
					int n = SudokuStore.randomNumber(1000);
					int[] p = SudokuStore.generatePermutation(n);
					if ( SudokuStore.isValidPermutation(p) == false ) {
						testResult = false;
						break;
					}
				}
				if (testResult == true) {
					resultDesc = "Expecting valid permutation - is valid.";
				} else {
					resultDesc = "Expecting valid permutation - is not valid.";
				}
			}
			break;
		case 71:
			testDesc = "calculatePuzzleRating";
			{
				int e = SudokuStore.randomIndex( SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES );
				int[][] b = SudokuStore.getPuzzleExample(e);
				double precalculatedRating = SudokuStore.getPuzzleExampleRating(e);
				double currentRating = SudokuStore.calculatePuzzleRating(b);
				double delta = Math.abs(precalculatedRating - currentRating);
				if (precalculatedRating != 0) {
					if ( (delta / precalculatedRating) > 0.15)
						testResult = false;
				} else {
					if (delta > 10)
						testResult = false;
				}
				resultDesc = "Example: " + e + ", Precalculated = " + precalculatedRating + ", Current = " + currentRating + ", Delta = " + delta;
				if (testResult == true) {
					resultDesc = resultDesc + " ... Difference is acceptable.";
				} else {
					resultDesc = resultDesc + " ... Difference is to high.";
				}
			}
			break;
		}
		if (testResult == true)
			SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + " " + testDesc + " " + resultDesc + " >>> SudokuStore, result: OK");
		else
			SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + " " + testDesc + " " + resultDesc + " >>> SudokuStore, result: ERROR");
		return testResult;
	}
	/**
	 * Number of regression tests;
	 */
	static final int NUMBER_OF_TESTS = 72;
}