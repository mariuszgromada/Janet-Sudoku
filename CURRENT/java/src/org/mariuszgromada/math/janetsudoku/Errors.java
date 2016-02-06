package org.mariuszgromada.math.janetsudoku;

public class Errors {
	/**
	 * Sudoku solving started, but failed.
	 */
	public static final int SUDOKUSOLVER_LOADBOARD_LOADING_FAILED = -100;
	/**
	 * Sudoku solving started, but failed.
	 */
	public static final int SUDOKUSOLVER_SOLVE_SOLVING_NOT_STARTED = -101;
	/**
	 * Sudoku solving started, but failed.
	 */
	public static final int SUDOKUSOLVER_SOLVE_SOLVING_FAILED = -102;
	/**
	 * Sudoku solving started, but failed.
	 */
	public static final int SUDOKUSOLVER_FINDALLSOLUTIONS_SEARCHING_NOT_STARTED = -103;
	/**
	 * Error related to setting cell by setCell() method.
	 * @see SudokuSolver#setCell(int, int, int)
	 */
	public static final int SUDOKUSOLVER_SETCELL_INCORRECT_DEFINITION = -104;
	/**
	 * Error related to getCellDigit() method.
	 * @see SudokuSolver#getCellDigit(int, int)
	 */
	public static final int SUDOKUSOLVER_GETCELLDIGIT_INCORRECT_INDEX = -105;
}
