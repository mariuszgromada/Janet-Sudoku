package org.mariuszgromada.math.janetsudoku.regtests;

import org.mariuszgromada.math.janetsudoku.ErrorCodes;
import org.mariuszgromada.math.janetsudoku.SudokuPuzzles;
import org.mariuszgromada.math.janetsudoku.SudokuSolver;
import org.mariuszgromada.math.janetsudoku.SudokuStore;

public class RegTestsSolver {
	public static boolean runTest(int testId) {
		SudokuSolver s;
		int solNum;
		int solUnq;
		int[][] solution;
		int[][] puzzle;
		boolean solCorr;
		boolean testResult = true;
		switch(testId) {
		case 0:
			for (int example = 0; example < SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES; example++) {
				s = new SudokuSolver(example);
				solNum = s.findAllSolutions();
				if (solNum != 1) testResult = false;
				SudokuStore.consolePrintln("Test: " + testId + ", findAllSolutions, example: " + example + ", solutions: " + solNum + ", time: " + s.getComputingTime() + " s.");
			}
			break;
		case 1:
			for (int example = 0; example < SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES; example++) {
				s = new SudokuSolver(example);
				solUnq = s.checkIfUniqueSolution();
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
				s.solve();
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
				if (solNum != 1) testResult = false;
				SudokuStore.consolePrintln("Test: " + testId + ", seqOfRandomBoardTransf + findAllSolutions, example: " + example + ", solutions: " + solNum + ", time: " + s.getComputingTime() + " s.");
			}
			break;
		case 4:
			for (int example = 0; example < SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES; example++) {
				puzzle = SudokuStore.seqOfRandomBoardTransf( SudokuStore.getPuzzleExample(example) );
				s = new SudokuSolver(puzzle);
				solUnq = s.checkIfUniqueSolution();
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
				s.solve();
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
			if (solNum <= 1) testResult = false;
			SudokuStore.consolePrintln("Test: " + testId + ", findAllSolutions, example: non unique, solutions: " + solNum + ", time: " + s.getComputingTime() + " s.");
			break;
		case 7:
			s = new SudokuSolver(SudokuPuzzles.PUZZLE_NON_UNIQUE_SOLUTION);
			solUnq = s.checkIfUniqueSolution();
			if (solUnq != SudokuSolver.SOLUTION_NON_UNIQUE) {
				testResult = false;
				SudokuStore.consolePrintln("Test: " + testId + ", checkIfUniqueSolution, example: non unique, is solution unique: YES, time: " + s.getComputingTime() + " s.");
			} else {
				SudokuStore.consolePrintln("Test: " + testId + ", checkIfUniqueSolution, example: non unique, is solution unique: NO, time: " + s.getComputingTime() + " s.");
			}
			break;
		case 8:
			s = new SudokuSolver(SudokuPuzzles.PUZZLE_NON_UNIQUE_SOLUTION);
			s.solve();
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
			if (solNum <= 1) testResult = false;
			SudokuStore.consolePrintln("Test: " + testId + ", seqOfRandomBoardTransf + findAllSolutions, example: non unique, solutions: " + solNum + ", time: " + s.getComputingTime() + " s.");
			break;
		case 10:
			puzzle = SudokuStore.seqOfRandomBoardTransf( SudokuPuzzles.PUZZLE_NON_UNIQUE_SOLUTION );
			s = new SudokuSolver(puzzle);
			solUnq = s.checkIfUniqueSolution();
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
			s.solve();
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
			if (solNum != 0) testResult = false;
			SudokuStore.consolePrintln("Test: " + testId + ", findAllSolutions, example: no solution, solutions: " + solNum + ", time: " + s.getComputingTime() + " s.");
			break;
		case 13:
			s = new SudokuSolver(SudokuPuzzles.PUZZLE_NO_SOLUTION);
			solUnq = s.checkIfUniqueSolution();
			if (solUnq == SudokuSolver.SOLUTION_NOT_EXISTS) {
				SudokuStore.consolePrintln("Test: " + testId + ", checkIfUniqueSolution, example: no solution, no solutions found: YES, time: " + s.getComputingTime() + " s.");
			} else {
				testResult = false;
				SudokuStore.consolePrintln("Test: " + testId + ", checkIfUniqueSolution, example: no solution, no solutions found: NO, time: " + s.getComputingTime() + " s.");
			}
			break;
		case 14:
			s = new SudokuSolver(SudokuPuzzles.PUZZLE_NO_SOLUTION);
			s.solve();
			solution = s.getSolvedBoard();
			if (s.getSolvingState() == ErrorCodes.SUDOKUSOLVER_SOLVE_SOLVING_FAILED) {
				SudokuStore.consolePrintln("Test: " + testId + ", solve, example: no solution, solving failed: YES, time: " + s.getComputingTime() + " s.");
			} else {
				testResult = false;
				SudokuStore.consolePrintln("Test: " + testId + ", solve, example: no solution, solving failed: NO, time: " + s.getComputingTime() + " s.");
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
		int numberOfTests = 15;
		for (int t = 0; t < numberOfTests; t++)
			runTest(t);
	}
}
