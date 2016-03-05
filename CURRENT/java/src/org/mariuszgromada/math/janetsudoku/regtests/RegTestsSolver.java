package org.mariuszgromada.math.janetsudoku.regtests;

import org.mariuszgromada.math.janetsudoku.ErrorCodes;
import org.mariuszgromada.math.janetsudoku.SudokuBoard;
import org.mariuszgromada.math.janetsudoku.SudokuPuzzles;
import org.mariuszgromada.math.janetsudoku.SudokuSolver;
import org.mariuszgromada.math.janetsudoku.SudokuStore;

public class RegTestsSolver {
	public static boolean runTest(int testId) {
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
				SudokuStore.consolePrintln("Test: " + testId + ", findAllSolutions, example: " + example + ", solutions: " + solNum + ", time: " + s.getComputingTime() + " s.");
			}
			break;
		case 1:
			for (int example = 0; example < SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES; example++) {
				s = new SudokuSolver(example);
				solUnq = s.checkIfUniqueSolution();
				ErrorCodes.consolePrintlnIfError(solUnq);
				if (solUnq != SudokuSolver.SOLUTION_UNIQUE) {
					testResult = false;
					SudokuStore.consolePrintln("Test: " + testId + ", checkIfUniqueSolution, example: " + example + ", is solution unique: NO, time: " + s.getComputingTime() + " s.");
				} else {
					SudokuStore.consolePrintln("Test: " + testId + ", checkIfUniqueSolution, example: " + example + ", is solution unique: YES, time: " + s.getComputingTime() + " s.");
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
					SudokuStore.consolePrintln("Test: " + testId + ", solve, example: " + example + ", is solution correct: NO, time: " + s.getComputingTime() + " s.");
				} else {
					SudokuStore.consolePrintln("Test: " + testId + ", solve, example: " + example + ", is solution correct: YES, time: " + s.getComputingTime() + " s.");
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
				SudokuStore.consolePrintln("Test: " + testId + ", seqOfRandomBoardTransf + findAllSolutions, example: " + example + ", solutions: " + solNum + ", time: " + s.getComputingTime() + " s.");
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
					SudokuStore.consolePrintln("Test: " + testId + ", seqOfRandomBoardTransf + checkIfUniqueSolution, example: " + example + ", is solution unique: NO, time: " + s.getComputingTime() + " s.");
				} else {
					SudokuStore.consolePrintln("Test: " + testId + ", seqOfRandomBoardTransf + checkIfUniqueSolution, example: " + example + ", is solution unique: YES, time: " + s.getComputingTime() + " s.");
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
					SudokuStore.consolePrintln("Test: " + testId + ", seqOfRandomBoardTransf + solve, example: " + example + ", is solution correct: NO, time: " + s.getComputingTime() + " s.");
				} else {
					SudokuStore.consolePrintln("Test: " + testId + ", seqOfRandomBoardTransf + solve, example: " + example + ", is solution correct: YES, time: " + s.getComputingTime() + " s.");
				}
			}
			break;
		case 6:
			s = new SudokuSolver(SudokuPuzzles.PUZZLE_NON_UNIQUE_SOLUTION);
			solNum = s.findAllSolutions();
			ErrorCodes.consolePrintlnIfError(solNum);
			if (solNum <= 1) testResult = false;
			SudokuStore.consolePrintln("Test: " + testId + ", findAllSolutions, example: non unique, solutions: " + solNum + ", time: " + s.getComputingTime() + " s.");
			break;
		case 7:
			s = new SudokuSolver(SudokuPuzzles.PUZZLE_NON_UNIQUE_SOLUTION);
			solUnq = s.checkIfUniqueSolution();
			ErrorCodes.consolePrintlnIfError(solUnq);
			if (solUnq != SudokuSolver.SOLUTION_NON_UNIQUE) {
				testResult = false;
				SudokuStore.consolePrintln("Test: " + testId + ", checkIfUniqueSolution, example: non unique, is solution unique: YES, time: " + s.getComputingTime() + " s.");
			} else {
				SudokuStore.consolePrintln("Test: " + testId + ", checkIfUniqueSolution, example: non unique, is solution unique: NO, time: " + s.getComputingTime() + " s.");
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
				SudokuStore.consolePrintln("Test: " + testId + ", solve, example: non unique, is solution correct: NO, time: " + s.getComputingTime() + " s.");
			} else {
				SudokuStore.consolePrintln("Test: " + testId + ", solve, example: non unique, is solution correct: YES, time: " + s.getComputingTime() + " s.");
			}
			break;
		case 9:
			puzzle = SudokuStore.seqOfRandomBoardTransf( SudokuPuzzles.PUZZLE_NON_UNIQUE_SOLUTION );
			s = new SudokuSolver(puzzle);
			solNum = s.findAllSolutions();
			ErrorCodes.consolePrintlnIfError(solNum);
			if (solNum <= 1) testResult = false;
			SudokuStore.consolePrintln("Test: " + testId + ", seqOfRandomBoardTransf + findAllSolutions, example: non unique, solutions: " + solNum + ", time: " + s.getComputingTime() + " s.");
			break;
		case 10:
			puzzle = SudokuStore.seqOfRandomBoardTransf( SudokuPuzzles.PUZZLE_NON_UNIQUE_SOLUTION );
			s = new SudokuSolver(puzzle);
			solUnq = s.checkIfUniqueSolution();
			ErrorCodes.consolePrintlnIfError(solUnq);
			if (solUnq != SudokuSolver.SOLUTION_NON_UNIQUE) {
				testResult = false;
				SudokuStore.consolePrintln("Test: " + testId + ", seqOfRandomBoardTransf + checkIfUniqueSolution, example: non unique, is solution unique: YES, time: " + s.getComputingTime() + " s.");
			} else {
				SudokuStore.consolePrintln("Test: " + testId + ", seqOfRandomBoardTransf + checkIfUniqueSolution, example: non unique, is solution unique: NO, time: " + s.getComputingTime() + " s.");
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
				SudokuStore.consolePrintln("Test: " + testId + ", seqOfRandomBoardTransf + solve, example: non unique, is solution correct: NO, time: " + s.getComputingTime() + " s.");
			} else {
				SudokuStore.consolePrintln("Test: " + testId + ", seqOfRandomBoardTransf + solve, example: non unique, is solution correct: YES, time: " + s.getComputingTime() + " s.");
			}
			break;
		case 12:
			s = new SudokuSolver(SudokuPuzzles.PUZZLE_NO_SOLUTION);
			solNum = s.findAllSolutions();
			ErrorCodes.consolePrintlnIfError(solNum);
			if (solNum != 0) testResult = false;
			SudokuStore.consolePrintln("Test: " + testId + ", findAllSolutions, example: no solution, solutions: " + solNum + ", time: " + s.getComputingTime() + " s.");
			break;
		case 13:
			s = new SudokuSolver(SudokuPuzzles.PUZZLE_NO_SOLUTION);
			solUnq = s.checkIfUniqueSolution();
			ErrorCodes.consolePrintlnIfError(solUnq);
			if (solUnq == SudokuSolver.SOLUTION_NOT_EXISTS) {
				SudokuStore.consolePrintln("Test: " + testId + ", checkIfUniqueSolution, example: no solution, no solutions found: YES, time: " + s.getComputingTime() + " s.");
			} else {
				testResult = false;
				SudokuStore.consolePrintln("Test: " + testId + ", checkIfUniqueSolution, example: no solution, no solutions found: NO, time: " + s.getComputingTime() + " s.");
			}
			break;
		case 14:
			s = new SudokuSolver(SudokuPuzzles.PUZZLE_NO_SOLUTION);
			sol = s.solve();
			solution = s.getSolvedBoard();
			if (s.getSolvingState() == ErrorCodes.SUDOKUSOLVER_SOLVE_SOLVING_FAILED) {
				SudokuStore.consolePrintln("Test: " + testId + ", solve, example: no solution, solving failed: YES, time: " + s.getComputingTime() + " s.");
			} else {
				testResult = false;
				SudokuStore.consolePrintln("Test: " + testId + ", solve, example: no solution, solving failed: NO, time: " + s.getComputingTime() + " s.");
			}
			break;
		case 15:
			s = new SudokuSolver(SudokuPuzzles.PUZZLE_ERROR);
			solvingState = s.solve();
			boardState = s.getBoardState();
			if ( (boardState == SudokuBoard.BOARD_STATE_ERROR) && (solvingState == ErrorCodes.SUDOKUSOLVER_SOLVE_SOLVING_NOT_STARTED) ) {
				SudokuStore.consolePrintln("Test: " + testId + ", solve, example: board with error, board state error and solving not started: YES, time: " + s.getComputingTime() + " s.");
			} else {
				testResult = false;
				SudokuStore.consolePrintln("Test: " + testId + ", solve, example: board with error, board state error and solving not started: NO, time: " + s.getComputingTime() + " s.");
			}
			break;
		}
		if (testResult == true)
			SudokuStore.consolePrintln("Test: " + testId + " >>>>>>>>>>>>>>>>>>>>>> SudokuSolver, result: OK");
		else
			SudokuStore.consolePrintln("Test: " + testId + " >>>>>>>>>>>>>>>>>>>>>> SudokuSolver, result: ERROR");
		return true;
	}
	public static void main(String[] args) {
		int numberOfTests = 16;
		boolean[] testResults = new boolean[numberOfTests];
		int resultsError = 0;
		int resultsOk = 0;
		long startTime = System.currentTimeMillis();
		for (int t = 0; t < numberOfTests; t++) {
			testResults[t] = runTest(t);
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
