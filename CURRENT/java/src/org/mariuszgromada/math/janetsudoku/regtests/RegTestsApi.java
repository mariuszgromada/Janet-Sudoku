/*
 * @(#)RegTestsApi.java        1.0.0    2016-03-19
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
import java.util.ArrayList;

import org.mariuszgromada.math.janetsudoku.BoardCell;
import org.mariuszgromada.math.janetsudoku.SudokuBoard;
import org.mariuszgromada.math.janetsudoku.SudokuPuzzles;
import org.mariuszgromada.math.janetsudoku.SudokuSolver;
import org.mariuszgromada.math.janetsudoku.SudokuStore;

/**
 * Regression tests for public API provided by Janet-Sudoku.
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
 * @version        1.0.0
 *
 * @see SudokuSolver
 */
public class RegTestsApi {
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
		int numberOfTests = ApiTests.NUMBER_OF_TESTS;
		int resultsError = 0;
		int resultsOk = 0;
		long startTime = System.currentTimeMillis();
		ApiTests at = new ApiTests(threadsNumber);
		at.start();
		boolean[] testResults = at.testsResults;
		for (int t = 0; t < numberOfTests; t++) {
			if (testResults[t] == true)
				resultsOk++;
			else
				resultsError++;
		}
		long endtTime = System.currentTimeMillis();
		double computingTime = (endtTime - startTime)/1000.0;
		SudokuStore.consolePrintln("=============================================================");
		SudokuStore.consolePrintln("Number of API test: " + numberOfTests + ", OK: " + resultsOk + ", ERRORS: " + resultsError + ", computing time: " + computingTime);
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
class ApiTests {
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
	ApiTests(int threadsNumber) {
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
				{0,0,0,8,0,0,0,0,0},
				{4,0,0,0,1,5,0,3,0},
				{0,2,9,0,4,0,5,1,8},
				{0,4,0,0,0,0,1,2,0},
				{0,0,0,6,0,2,0,0,0},
				{0,3,2,0,0,0,0,9,0},
				{6,9,3,0,5,0,8,7,0},
				{0,5,0,4,8,0,0,0,1},
				{0,0,0,0,0,3,0,0,0}
			};
		switch(testId) {
		case 0:
			testDesc = "SudokuSolver.setCell(int, int, int)";
			{
				SudokuSolver s = new SudokuSolver();
				s.setCell(0,0,0);
				s.setCell(1,0,4);
				s.setCell(2,0,0);
				s.setCell(3,0,0);
				s.setCell(4,0,0);
				s.setCell(5,0,0);
				s.setCell(6,0,6);
				s.setCell(7,0,0);
				s.setCell(8,0,0);
				s.setCell(0,1,0);
				s.setCell(1,1,0);
				s.setCell(2,1,2);
				s.setCell(3,1,4);
				s.setCell(4,1,0);
				s.setCell(5,1,3);
				s.setCell(6,1,9);
				s.setCell(7,1,5);
				s.setCell(8,1,0);
				s.setCell(0,2,0);
				s.setCell(1,2,0);
				s.setCell(2,2,9);
				s.setCell(3,2,0);
				s.setCell(4,2,0);
				s.setCell(5,2,2);
				s.setCell(6,2,3);
				s.setCell(7,2,0);
				s.setCell(8,2,0);
				s.setCell(0,3,8);
				s.setCell(1,3,0);
				s.setCell(2,3,0);
				s.setCell(3,3,0);
				s.setCell(4,3,6);
				s.setCell(5,3,0);
				s.setCell(6,3,0);
				s.setCell(7,3,4);
				s.setCell(8,3,0);
				s.setCell(0,4,0);
				s.setCell(1,4,1);
				s.setCell(2,4,4);
				s.setCell(3,4,0);
				s.setCell(4,4,0);
				s.setCell(5,4,0);
				s.setCell(6,4,5);
				s.setCell(7,4,8);
				s.setCell(8,4,0);
				s.setCell(0,5,0);
				s.setCell(1,5,5);
				s.setCell(2,5,0);
				s.setCell(3,5,0);
				s.setCell(4,5,2);
				s.setCell(5,5,0);
				s.setCell(6,5,0);
				s.setCell(7,5,0);
				s.setCell(8,5,3);
				s.setCell(0,6,0);
				s.setCell(1,6,0);
				s.setCell(2,6,5);
				s.setCell(3,6,1);
				s.setCell(4,6,0);
				s.setCell(5,6,0);
				s.setCell(6,6,8);
				s.setCell(7,6,0);
				s.setCell(8,6,0);
				s.setCell(0,7,0);
				s.setCell(1,7,3);
				s.setCell(2,7,1);
				s.setCell(3,7,2);
				s.setCell(4,7,0);
				s.setCell(5,7,9);
				s.setCell(6,7,7);
				s.setCell(7,7,0);
				s.setCell(8,7,0);
				s.setCell(0,8,0);
				s.setCell(1,8,0);
				s.setCell(2,8,8);
				s.setCell(3,8,0);
				s.setCell(4,8,0);
				s.setCell(5,8,0);
				s.setCell(6,8,0);
				s.setCell(7,8,1);
				s.setCell(8,8,0);
				int[][] b = s.getBoard();

				if ( (SudokuStore.boardsAreEqual(a, b) == true) ) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
					SudokuStore.consolePrintln(s.getMessages());
				}
			}
			break;
		case 1:
			testDesc = "SudokuSolver.getCellDigit(int, int)";
			{
				SudokuSolver s1 = new SudokuSolver(a);
				SudokuSolver s2 = new SudokuSolver();
				for (int i = 0; i < SudokuBoard.BOARD_SIZE; i++)
					for (int j = 0; j < SudokuBoard.BOARD_SIZE; j++) {
						int d = s1.getCellDigit(i, j);
						s2.setCell(i, j, d);
					}
				int[][] b = s2.getBoard();
				if ( (SudokuStore.boardsAreEqual(a, b) == true) ) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 2:
			testDesc = "SudokuSolver.getBoardCopy()";
			{
				SudokuSolver s = new SudokuSolver(a);
				int[][] b = s.getBoard();
				int[][] c = s.getBoardCopy();
				if ( (SudokuStore.boardsAreEqual(b, c) == true) ) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 3:
			testDesc = "SudokuSolver.getSolutionBoardCells()";
			{
				SudokuSolver s = new SudokuSolver(a);
				s.solve();
				int[][] b = s.getSolvedBoard();
				int[][] c = SudokuStore.boardCopy(a);
				BoardCell[] sol = s.getSolutionBoardCells();
				for (BoardCell bc : sol)
					c[bc.rowIndex][bc.colIndex] = bc.digit;

				if ( (SudokuStore.boardsAreEqual(b, c) == true) ) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
			break;
		case 4:
			testDesc = "SudokuSolver.getAllBoardCells()";
			{
				SudokuSolver s = new SudokuSolver(a);
				int[][] b = s.getBoardCopy();
				int[][] c = new int[SudokuBoard.BOARD_SIZE][SudokuBoard.BOARD_SIZE];
				BoardCell[] bc = s.getAllBoardCells();
				for (BoardCell cell : bc) {
					c[cell.rowIndex][cell.colIndex] = cell.digit;
				}
				if ( (SudokuStore.boardsAreEqual(b, c) == true) ) {
					resultDesc = "Expecting equal - are equal.";
				} else {
					resultDesc = "Expecting equal - are not equal.";
					testResult = false;
				}
			}
		case 5:
			testDesc = "SudokuSolver.getAllSolutionsList()";
			{
				SudokuSolver s = new SudokuSolver( SudokuPuzzles.PUZZLE_NON_UNIQUE_SOLUTION );
				s.findAllSolutions();
				ArrayList<SudokuBoard> solList;
				solList = s.getAllSolutionsList();
				for (SudokuBoard sb : solList) {
					if (SudokuStore.checkSolvedBoard( sb.board ) == false) {
						testResult = false;
						break;
					}
				}
				if ( testResult == true ) {
					resultDesc = "Expecting each solution valid - each is valid.";
				} else {
					resultDesc = "Expecting each solution valid - found not valid.";
				}
			}
			break;
		}
		if (testResult == true)
			SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + " " + testDesc + " " + resultDesc + " >>> ApiTests, result: OK");
		else
			SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + " " + testDesc + " " + resultDesc + " >>> ApiTests, result: ERROR");
		return testResult;
	}
	/**
	 * Number of regression tests;
	 */
	static final int NUMBER_OF_TESTS = 6;
}