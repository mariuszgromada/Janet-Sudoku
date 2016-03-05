package org.mariuszgromada.math.janetsudoku.regtests;

import org.mariuszgromada.math.janetsudoku.ErrorCodes;
import org.mariuszgromada.math.janetsudoku.SudokuBoard;
import org.mariuszgromada.math.janetsudoku.SudokuPuzzles;
import org.mariuszgromada.math.janetsudoku.SudokuSolver;
import org.mariuszgromada.math.janetsudoku.SudokuStore;

public class RegTestsSolver {
	public static void main(String[] args) {
		int numberOfTests = SolverTests.NUMBER_OF_TESTS;
		int resultsError = 0;
		int resultsOk = 0;
		long startTime = System.currentTimeMillis();
		SolverTests st = new SolverTests();
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
		SudokuStore.consolePrintln("Number of SudokuSolver test: " + numberOfTests + ", OK: " + resultsOk + ", ERRORS: " + resultsError + ", computing time: " + computingTime);
		for (int t = 0; t < numberOfTests; t++)
			if (testResults[t] == false)
				SudokuStore.consolePrintln("ERROR: " + t);
		SudokuStore.consolePrintln("=============================================================");
	}
}
/*
 * assignments
 */
class SolverTests {

	private static int THREADS_NUMBER = Runtime.getRuntime().availableProcessors();
	private TestThread[] workers;
	private Thread[] threads;
	boolean[] testsResults;

	SolverTests() {
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
	class TestThread implements Runnable {
		int threadId;
		int[] assigments;
		TestThread(int threadId, int[] assigments) {
			this.assigments = assigments;
			this.threadId = threadId;
		}
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

	static final int NUMBER_OF_TESTS = 18;
	static boolean runTest(int testId, int threadId) {
		SudokuSolver s;
		int sol;
		int solNum;
		int solUnq;
		int solvingState;
		int boardState;
		int[][] solution;
		int[][] puzzle;
		boolean solCorr;
		boolean testResult = true;
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