package org.mariuszgromada.math.janetsudoku;

public class SudokuBoard {
	/**
	 * Sudoku board size.
	 */
	public static final int BOARD_SIZE = 9;
	/**
	 * Sudoku board sub-square size.
	 */
	public static final int BOARD_SUB_SQURE_SIZE = 3;
	/**
	 * We will use array indexes from 1.,.n, instead 0...n-1
	 */
	static final int BOARD_MAX_INDEX = BOARD_SIZE + 1;
	/**
	 * Number of cells on the Sudoku board.
	 */
	static final int BOARD_CELLS_NUMBER = BOARD_SIZE * BOARD_SIZE;
	/**
	 * Sudoku board was successfully loaded.
	 */
	public static final int BOARD_STATE_EMPTY = 1;
	/**
	 * Sudoku board was successfully loaded.
	 */
	public static final int BOARD_STATE_LOADED = 2;
	/**
	 * Sudoku board is ready to start solving process.
	 */
	public static final int BOARD_STATE_READY = 3;
	/**
	 * Sudoku board is ready to start solving process.
	 */
	public static final int BOARD_STATE_ERROR = -1;
	/**
	 * Path number gives the information on how many routes
	 * were verified until solutions was found.
	 */
	public int pathNumber;
	/**
	 * Sudoku board array.
	 */
	public int[][] board;
	/**
	 * Default constructor.
	 */
	public SudokuBoard() {
		board = new int[BOARD_SIZE][BOARD_SIZE];
		pathNumber = -1;
	}
}
