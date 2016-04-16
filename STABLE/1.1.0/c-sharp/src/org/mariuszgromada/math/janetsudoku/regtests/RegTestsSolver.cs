/*
 * @(#)RegTestsSolver.cs        1.0.0    2016-03-19
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
 * THIS SOFTWARE IS PROVIDED BY MARIUSZ GROMADA ``AS IS'' AND ANY EXPRESS OR IMPLIED
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
 *     http://janetsudoku.mariuszgromada.org
 *     http://mathparser.org
 *     http://mathspace.pl
 *     http://github.com/mariuszgromada/Janet-Sudoku
 *     http://janetsudoku.codeplex.com
 *     http://sourceforge.net/projects/janetsudoku
 *     http://bitbucket.org/mariuszgromada/janet-sudoku
 *     http://github.com/mariuszgromada/MathParser.org-mXparser
 *
 *
 *                              Asked if he believes in one God, a mathematician answered:
 *                              "Yes, up to isomorphism."
 */
using org.mariuszgromada.math.janetsudoku.utils;
using System;
using System.Runtime.CompilerServices;
using System.Threading;

namespace org.mariuszgromada.math.janetsudoku.regtests {
	/**
	 * Regression tests for the SudokuSolver class.
	 *
	 * @author         <b>Mariusz Gromada</b><br>
	 *                 <a href="mailto:mariusz.gromada@mathspace.pl">mariusz.gromada@mathspace.pl</a><br>
	 *                 <a href="http://janetsudoku.mariuszgromada.org" target="_blank">Janet Sudoku - project web page</a><br>
	 *                 <a href="http://mathspace.pl" target="_blank">MathSpace.pl</a><br>
	 *                 <a href="http://mathparser.org" target="_blank">MathParser.org - mXparser project page</a><br>
	 *                 <a href="http://github.com/mariuszgromada/Janet-Sudoku" target="_blank">Janet Sudoku on GitHub</a><br>
	 *                 <a href="http://janetsudoku.codeplex.com" target="_blank">Janet Sudoku on CodePlex</a><br>
	 *                 <a href="http://sourceforge.net/projects/janetsudoku" target="_blank">Janet Sudoku on SourceForge</a><br>
	 *                 <a href="http://bitbucket.org/mariuszgromada/janet-sudoku" target="_blank">Janet Sudoku on BitBucket</a><br>
	 *                 <a href="http://github.com/mariuszgromada/MathParser.org-mXparser" target="_blank">mXparser-MathParser.org on GitHub</a><br>
	 *
	 * @version        1.0.0
	 *
	 * @see SudokuSolver
	 */
	[CLSCompliant(true)]
	public class RegTestsSolver {
		/**
		 * Runs SudokuSolver regression tests with default number of threads.
		 * @return    Number of tests with errors.
		 */
		public static int Start() {
			return Start(SudokuStore.THREADS_NUMBER);
		}
		/**
		 * Runs SudokuSolver regression tests.
		 * @param threadsNumber    Number of threads.
		 * @return    Number of tests with errors.
		 */
		public static int Start(int threadsNumber) {
			int numberOfTests = SolverTests.NUMBER_OF_TESTS;
			int resultsError = 0;
			int resultsOk = 0;
			long startTime = DateTimeX.currentTimeMillis();
			SolverTests st = new SolverTests(threadsNumber);
			st.start();
			bool[] testResults = st.testsResults;
			for (int t = 0; t < numberOfTests; t++) {
				if (testResults[t] == true)
					resultsOk++;
				else
					resultsError++;
			}
			long endtTime = DateTimeX.currentTimeMillis();
			double computingTime = (endtTime - startTime)/1000.0;
			SudokuStore.consolePrintln("=============================================================");
			SudokuStore.consolePrintln("Number of SudokuSolver test: " + numberOfTests + ", OK: " + resultsOk + ", ERRORS: " + resultsError + ", computing time: " + computingTime);
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
		public static void Main(string[] args) {
			if (args != null) {
				if (args.Length > 0)
					if (args[0] != null) {
						int threadsNumber = int.Parse(args[0]);
						if (threadsNumber > 0) {
							Start(threadsNumber);
							return;
						}
					}
			}
			Start();
		}
	}
	/**
	 * Regression tests definition.
	 */
	internal class SolverTests {
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
		internal bool[] testsResults;
		/**
		 * Default constructor.
		 * @param threadsNumber     Threads number.
		 */
		internal SolverTests(int threadsNumber) {
			THREADS_NUMBER = threadsNumber;
			threads = new Thread[THREADS_NUMBER];
			runners = new TestRunner[THREADS_NUMBER];
			testsResults = new bool[NUMBER_OF_TESTS];
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
				runners[i] = new TestRunner(i, assigments, testsResults);
				threads[i] = new Thread(runners[i].run);
			}
		}
		/**
		 * Threads start and join.
		 */
		public void start() {
			for (int i = 0; i < THREADS_NUMBER; i++) {
				threads[i].Start();
			}
			for (int i = 0; i < THREADS_NUMBER; i++) {
				try {
					threads[i].Join();
				} catch (ThreadInterruptedException e) {
					Console.WriteLine(e.StackTrace);
				}
			}
		}
		/**
		 * Runner implementation.
		 */
		internal class TestRunner {
			/**
			 * Thread id.
			 */
			internal int threadId;
			/**
			 * Tests that were assigned to that thread
			 */
			internal int[] assigments;
			/*
			 * Reference - table with joined results
			 */
			bool[] joinedTestsResults;
			/**
			 * Default constructor.
			 * @param threadId       Thread id.
			 * @param assigments     Test assigned to that thread.
			 */
			internal TestRunner(int threadId, int[] assigments, bool[] joinedTestsResults) {
				this.assigments = assigments;
				this.threadId = threadId;
				this.joinedTestsResults = joinedTestsResults;
			}
			/**
			 * Synchronized method to store test results.
			 * @param t          Test id.
			 * @param result     TEst result.
			 */
			[MethodImpl(MethodImplOptions.Synchronized)]
			private void setTestResult(int t, bool result) {
				joinedTestsResults[t] = result;
			}
			public void run() {
				foreach (int t in assigments)
					setTestResult(t, runTest(t, threadId));
			}
		}
		/**
		 * Number of regression tests;
		 */
		internal const int NUMBER_OF_TESTS = 18;
		/**
		 * Test scenario implementation.
		 * @param testId        Test id.
		 * @param threadId      Thread id.
		 * @return              True is test successful, otherwise false.
		 */
		static bool runTest(int testId, int threadId) {
			SudokuSolver s;
			int sol;
			int solNum;
			int solUnq;
			int solvingState;
			int boardState;
			int[,] solution;
			int[,] puzzle;
			bool solCorr;
			bool testResult = true;
			switch(testId) {
			case 0:
				for (int example = 0; example < SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES; example++) {
					s = new SudokuSolver(example);
					solNum = s.findAllSolutions();
					ErrorCodes.consolePrintlnIfError(solNum);
					if (solNum != 1) testResult = false;
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", findAllSolutions, example: " + example + ", solutions: " + solNum + ", time: " + s.getComputingTime() + " s.");
				}
				break;
			case 1:
				for (int example = 0; example < SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES; example++) {
					s = new SudokuSolver(example);
					solUnq = s.checkIfUniqueSolution();
					ErrorCodes.consolePrintlnIfError(solUnq);
					if (solUnq != SudokuSolver.SOLUTION_UNIQUE) {
						testResult = false;
						SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", checkIfUniqueSolution, example: " + example + ", is solution unique: NO, time: " + s.getComputingTime() + " s.");
					} else {
						SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", checkIfUniqueSolution, example: " + example + ", is solution unique: YES, time: " + s.getComputingTime() + " s.");
					}
				}
				break;
			case 2:
				for (int example = 0; example < SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES; example++) {
					s = new SudokuSolver(example);
					sol = s.solve();
					ErrorCodes.consolePrintlnIfError(sol);
					solution = s.getSolvedBoard();
					solCorr = SudokuStore.checkSolvedBoard(solution);
					if (solCorr != true) {
						testResult = false;
						SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", solve, example: " + example + ", is solution correct: NO, time: " + s.getComputingTime() + " s.");
					} else {
						SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", solve, example: " + example + ", is solution correct: YES, time: " + s.getComputingTime() + " s.");
					}
				}
				break;
			case 3:
				for (int example = 0; example < SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES; example++) {
					puzzle = SudokuStore.seqOfRandomBoardTransf( SudokuStore.getPuzzleExample(example) );
					s = new SudokuSolver(puzzle);
					solNum = s.findAllSolutions();
					ErrorCodes.consolePrintlnIfError(solNum);
					if (solNum != 1) testResult = false;
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", seqOfRandomBoardTransf + findAllSolutions, example: " + example + ", solutions: " + solNum + ", time: " + s.getComputingTime() + " s.");
				}
				break;
			case 4:
				for (int example = 0; example < SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES; example++) {
					puzzle = SudokuStore.seqOfRandomBoardTransf( SudokuStore.getPuzzleExample(example) );
					s = new SudokuSolver(puzzle);
					solUnq = s.checkIfUniqueSolution();
					ErrorCodes.consolePrintlnIfError(solUnq);
					if (solUnq != SudokuSolver.SOLUTION_UNIQUE) {
						testResult = false;
						SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", seqOfRandomBoardTransf + checkIfUniqueSolution, example: " + example + ", is solution unique: NO, time: " + s.getComputingTime() + " s.");
					} else {
						SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", seqOfRandomBoardTransf + checkIfUniqueSolution, example: " + example + ", is solution unique: YES, time: " + s.getComputingTime() + " s.");
					}
				}
				break;
			case 5:
				for (int example = 0; example < SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES; example++) {
					puzzle = SudokuStore.seqOfRandomBoardTransf( SudokuStore.getPuzzleExample(example) );
					s = new SudokuSolver(puzzle);
					sol = s.solve();
					ErrorCodes.consolePrintlnIfError(sol);
					solution = s.getSolvedBoard();
					solCorr = SudokuStore.checkSolvedBoard(solution);
					if (solCorr != true) {
						testResult = false;
						SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", seqOfRandomBoardTransf + solve, example: " + example + ", is solution correct: NO, time: " + s.getComputingTime() + " s.");
					} else {
						SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", seqOfRandomBoardTransf + solve, example: " + example + ", is solution correct: YES, time: " + s.getComputingTime() + " s.");
					}
				}
				break;
			case 6:
				s = new SudokuSolver(SudokuPuzzles.PUZZLE_NON_UNIQUE_SOLUTION);
				solNum = s.findAllSolutions();
				ErrorCodes.consolePrintlnIfError(solNum);
				if (solNum <= 1) testResult = false;
				SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", findAllSolutions, example: non unique, solutions: " + solNum + ", time: " + s.getComputingTime() + " s.");
				break;
			case 7:
				s = new SudokuSolver(SudokuPuzzles.PUZZLE_NON_UNIQUE_SOLUTION);
				solUnq = s.checkIfUniqueSolution();
				ErrorCodes.consolePrintlnIfError(solUnq);
				if (solUnq != SudokuSolver.SOLUTION_NON_UNIQUE) {
					testResult = false;
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", checkIfUniqueSolution, example: non unique, is solution unique: YES, time: " + s.getComputingTime() + " s.");
				} else {
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", checkIfUniqueSolution, example: non unique, is solution unique: NO, time: " + s.getComputingTime() + " s.");
				}
				break;
			case 8:
				s = new SudokuSolver(SudokuPuzzles.PUZZLE_NON_UNIQUE_SOLUTION);
				sol = s.solve();
				ErrorCodes.consolePrintlnIfError(sol);
				solution = s.getSolvedBoard();
				solCorr = SudokuStore.checkSolvedBoard(solution);
				if (solCorr != true) {
					testResult = false;
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", solve, example: non unique, is solution correct: NO, time: " + s.getComputingTime() + " s.");
				} else {
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", solve, example: non unique, is solution correct: YES, time: " + s.getComputingTime() + " s.");
				}
				break;
			case 9:
				puzzle = SudokuStore.seqOfRandomBoardTransf( SudokuPuzzles.PUZZLE_NON_UNIQUE_SOLUTION );
				s = new SudokuSolver(puzzle);
				solNum = s.findAllSolutions();
				ErrorCodes.consolePrintlnIfError(solNum);
				if (solNum <= 1) testResult = false;
				SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", seqOfRandomBoardTransf + findAllSolutions, example: non unique, solutions: " + solNum + ", time: " + s.getComputingTime() + " s.");
				break;
			case 10:
				puzzle = SudokuStore.seqOfRandomBoardTransf( SudokuPuzzles.PUZZLE_NON_UNIQUE_SOLUTION );
				s = new SudokuSolver(puzzle);
				solUnq = s.checkIfUniqueSolution();
				ErrorCodes.consolePrintlnIfError(solUnq);
				if (solUnq != SudokuSolver.SOLUTION_NON_UNIQUE) {
					testResult = false;
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", seqOfRandomBoardTransf + checkIfUniqueSolution, example: non unique, is solution unique: YES, time: " + s.getComputingTime() + " s.");
				} else {
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", seqOfRandomBoardTransf + checkIfUniqueSolution, example: non unique, is solution unique: NO, time: " + s.getComputingTime() + " s.");
				}
				break;
			case 11:
				puzzle = SudokuStore.seqOfRandomBoardTransf( SudokuPuzzles.PUZZLE_NON_UNIQUE_SOLUTION );
				s = new SudokuSolver(puzzle);
				sol = s.solve();
				ErrorCodes.consolePrintlnIfError(sol);
				solution = s.getSolvedBoard();
				solCorr = SudokuStore.checkSolvedBoard(solution);
				if (solCorr != true) {
					testResult = false;
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", seqOfRandomBoardTransf + solve, example: non unique, is solution correct: NO, time: " + s.getComputingTime() + " s.");
				} else {
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", seqOfRandomBoardTransf + solve, example: non unique, is solution correct: YES, time: " + s.getComputingTime() + " s.");
				}
				break;
			case 12:
				s = new SudokuSolver(SudokuPuzzles.PUZZLE_NO_SOLUTION);
				solNum = s.findAllSolutions();
				ErrorCodes.consolePrintlnIfError(solNum);
				if (solNum != 0) testResult = false;
				SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", findAllSolutions, example: no solution, solutions: " + solNum + ", time: " + s.getComputingTime() + " s.");
				break;
			case 13:
				s = new SudokuSolver(SudokuPuzzles.PUZZLE_NO_SOLUTION);
				solUnq = s.checkIfUniqueSolution();
				ErrorCodes.consolePrintlnIfError(solUnq);
				if (solUnq == SudokuSolver.SOLUTION_NOT_EXISTS) {
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", checkIfUniqueSolution, example: no solution, no solutions found: YES, time: " + s.getComputingTime() + " s.");
				} else {
					testResult = false;
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", checkIfUniqueSolution, example: no solution, no solutions found: NO, time: " + s.getComputingTime() + " s.");
				}
				break;
			case 14:
				s = new SudokuSolver(SudokuPuzzles.PUZZLE_NO_SOLUTION);
				sol = s.solve();
				solution = s.getSolvedBoard();
				if (s.getSolvingState() == ErrorCodes.SUDOKUSOLVER_SOLVE_SOLVING_FAILED) {
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", solve, example: no solution, solving failed: YES, time: " + s.getComputingTime() + " s.");
				} else {
					testResult = false;
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", solve, example: no solution, solving failed: NO, time: " + s.getComputingTime() + " s.");
				}
				break;
			case 15:
				s = new SudokuSolver(SudokuPuzzles.PUZZLE_ERROR);
				solvingState = s.solve();
				boardState = s.getBoardState();
				if ( (boardState == SudokuBoard.BOARD_STATE_ERROR) && (solvingState == ErrorCodes.SUDOKUSOLVER_SOLVE_SOLVING_NOT_STARTED) ) {
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", solve, example: board with error, board state error and solving not started: YES, time: " + s.getComputingTime() + " s.");
				} else {
					testResult = false;
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", solve, example: board with error, board state error and solving not started: NO, time: " + s.getComputingTime() + " s.");
				}
				break;
			case 16:
				s = new SudokuSolver(SudokuPuzzles.PUZZLE_REGTESTS);
				sol = s.solve();
				ErrorCodes.consolePrintlnIfError(sol);
				if ( SudokuStore.boardsAreEqual(s.getSolvedBoard(), SudokuPuzzles.PUZZLE_REGTESTS_SOLUTION) ) {
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", solve, example: with solution, solutions are equal: YES, time: " + s.getComputingTime() + " s.");
				} else {
					testResult = false;
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", solve, example: with solution, solutions are equal: NO, time: " + s.getComputingTime() + " s.");
				}
				break;
			case 17:
				s = new SudokuSolver(SudokuPuzzles.PUZZLE_EMPTY);
				sol = s.solve();
				solUnq = s.checkIfUniqueSolution();
				ErrorCodes.consolePrintlnIfError(sol);
				ErrorCodes.consolePrintlnIfError(solUnq);
				if ( ( SudokuStore.checkSolvedBoard(s.getSolvedBoard()) == true ) & (solUnq == SudokuSolver.SOLUTION_NON_UNIQUE) ) {
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", solve, example: empty puzzle, found solution and solution non unique: YES, time: " + s.getComputingTime() + " s.");
				} else {
					testResult = false;
					SudokuStore.consolePrintln("(Thread: " + threadId + ") " + "Test: " + testId + ", solve, example: empty puzzle, found solution and solution non unique: NO, time: " + s.getComputingTime() + " s.");
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
}