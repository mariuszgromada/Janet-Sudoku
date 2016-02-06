/*
 * @(#)SudokuSolver.java        0.0.1    2016-02-01
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
package org.mariuszgromada.math.janetsudoku;

import java.util.ArrayList;
import java.util.Stack;
import org.mariuszgromada.janetutils.ArrayX;
import org.mariuszgromada.janetutils.DateTimeX;

/**
 * Sudoku board, with predefined Sudoku examples and possibility to load
 * external examples from arrays or files. Class implements
 * loading methods as well as Sudoku solving methods.
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
 */
public class SudokuSolver {
	/**
	 * Sudoku solving not initiated.
	 */
	public static final int SUDOKU_SOLVING_NOT_STARTED = 1;
	/**
	 * Sudoku solving started.
	 */
	public static final int SUDOKU_SOLVING_STARTED = 2;
	/**
	 * Sudoku solving finished and successful.
	 */
	public static final int SUDOKU_SOLVED = 3;
	/**
	 * Sudoku solving started, but failed.
	 */
	public static final int ERROR_LOADBOARD_LOADING_FAILED = -100;
	/**
	 * Sudoku solving started, but failed.
	 */
	public static final int ERROR_SOLVE_SOLVING_NOT_STARTED = -101;
	/**
	 * Sudoku solving started, but failed.
	 */
	public static final int ERROR_SOLVE_SOLVING_FAILED = -102;
	/**
	 * Error related to setting cell by setCell() method.
	 * @see SudokuSolver#setCell(int, int, int)
	 */
	public static final int ERROR_SETCELL_INCORRECT_DEFINITION = -103;
	/**
	 * Error related to getCellDigit() method.
	 * @see SudokuSolver#getCellDigit(int, int)
	 */
	public static final int ERROR_GETCELLDIGIT_INCORRECT_INDEX = -104;
	/**
	 * Message type normal.
	 */
	private static final int NORMAL_MSG = 1;
	/**
	 * Message type error.
	 */
	private static final int ERROR_MSG = -1;
	/**
	 * Sudoku board size.
	 */
	private static final int BOARD_SIZE = SudokuBoard.BOARD_SIZE;
	/**
	 * Sudoku board sub-square size.
	 */
	private static final int SUB_SQURE_SIZE = SudokuBoard.SUB_SQURE_SIZE;
	/**
	 * Number of cells on the Sudoku board.
	 */
	private static final int BOARD_CELLS_NUMBER = SudokuBoard.BOARD_CELLS_NUMBER;
	/**
	 * Sudoku board was successfully loaded.
	 */
	private static final int BOARD_EMPTY = SudokuBoard.BOARD_EMPTY;
	/**
	 * Sudoku board was successfully loaded.
	 */
	private static final int BOARD_LOADED = SudokuBoard.BOARD_LOADED;
	/**
	 * Sudoku board is ready to start solving process.
	 */
	private static final int BOARD_READY = SudokuBoard.BOARD_READY;
	/**
	 * Sudoku board is ready to start solving process.
	 */
	private static final int BOARD_ERROR = SudokuBoard.BOARD_ERROR;
	/**
	 * Empty cell identifier.
	 */
	private static final int EMPTY_CELL = BoardCell.EMPTY_CELL;
	/**
	 * Marker if analyzed digit 0...9 is still not used.
	 */
	static final int DIGIT_STILL_FREE = 1;
	/**
	 * Digit 0...9 can not be used in that place.
	 */
	static final int DIGIT_IN_USE = 2;
	/**
	 * Cell is not pointing to any cells on the board.
	 */
	static final int NULL_INDEX = BoardCell.NULL_INDEX;
	/**
	 * Sudoku board used while solving process.
	 */
	int[][] sudokuBoard = new int[BOARD_SIZE][BOARD_SIZE];
	/**
	 * Sudoku solution.
	 */
	int[][] solvedBoard = null;
	/**
	 * Path to the sudoku solution.
	 */
	private Stack<BoardCell> solutionBoardCells;
	/**
	 * All solutions list
	 */
	private ArrayList<SudokuBoard> solutionsList;
	/**
	 * Solving status indicator.
	 */
	int boardState;
	/**
	 * Solving status indicator.
	 */
	int solvingStatus;
	/**
	 * Solving time in seconds.
	 */
	private double solvingTime;
	/**
	 * Step back counter showing how many different
	 * routes were evaluated while solving.
	 */
	private int closedPathsCounter;
	/**
	 * Total evaluated paths counter while finding all solutions.
	 */
	private int totalPathsCounter;

	/**
	 * If yes then empty cells with the same number of
	 * still free digits will be randomized.
	 */
	private boolean randomizeEmptyCells;
	/**
	 * If yes then still free digits for a given empty cell
	 * will be randomized.
	 */
	private boolean randomizeFreeDigits;
	/**
	 * Empty cells on the Sudoku board
	 */
	EmptyCell[] emptyCells = new EmptyCell[BOARD_CELLS_NUMBER];
	/**
	 * Pointers to the empty cells.
	 */
	EmptyCell[][] emptyCellsPointer = new EmptyCell[BOARD_SIZE][BOARD_SIZE];
	/**
	 * Number of empty cells on the Sudoku board.
	 */
	int emptyCellsNumber;
	/**
	 * Error message, if necessary.
	 */
	String messages = "";
	/**
	 * Last message.
	 */
	String lastMessage = "";
	/**
	 * Last error message.
	 */
	String lastErrorMessage = "";
	/*
	 * =====================================================
	 *                  Constructors
	 * =====================================================
	 */
	/**
	 * Default constructor - only board initialization.
	 */
	public SudokuSolver() {
		clearPuzzels();
		randomizeEmptyCells = true;
		randomizeFreeDigits = true;
		findEmptyCells();
	}
	/**
	 * Constructor - based on the Sudoku predefined example number.
	 * @param exampleNumber  number of Sudoku example to load between 1
	 * and {@link SudokuStore#NUMBER_OF_SUDOKU_EXAMPLES}
	 */
	public SudokuSolver(int exampleNumber) {
		clearPuzzels();
		loadBoard(exampleNumber);
	}
	/**
	 * Constructor - based on file path to the Sudoku definition.
	 * @param filePath     Path to the sudoku definition.
	 */
	public SudokuSolver(String filePath) {
		clearPuzzels();
		loadBoard(filePath);
	}
	/*
	 * =====================================================
	 *                  Loading methods
	 * =====================================================
	 */
	/**
	 * Loads Sudoku example given by the parameter exampleNumber.
	 *
	 * @param exampleNumber  Number of predefined Sudoku example.
	 * @return {@link SudokuSolver#ERROR_LOADBOARD_LOADING_FAILED} or
	 *         {@link SudokuBoard#BOARD_LOADED}.
	 */
	public int loadBoard(int exampleNumber) {
		if ((exampleNumber < 1) || (exampleNumber > SudokuStore.NUMBER_OF_SUDOKU_EXAMPLES)) {
			addMessage("Loading failed - example number should be between 1 and " + SudokuStore.NUMBER_OF_SUDOKU_EXAMPLES, ERROR_MSG);
			return ERROR_LOADBOARD_LOADING_FAILED;
		}
		if (boardState != BOARD_EMPTY)
			clearPuzzels();
		int[][] loadedBoard = SudokuStore.getSudokuExample(exampleNumber);
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++)
				sudokuBoard[i][j] = loadedBoard[i][j];
		boardState = BOARD_LOADED;
		addMessage("Sudoku example board " + exampleNumber + " loaded", NORMAL_MSG);
		return findEmptyCells();
	}
	/**
	 * Loads Sudoku from file.
	 *
	 * @param filePath File path that contains board definition.
	 * @return {@link SudokuSolver#ERROR_LOADBOARD_LOADING_FAILED} or
	 *         {@link SudokuBoard#BOARD_LOADED}.
	 */
	public int loadBoard(String filePath) {
		int[][] loadedBoard = SudokuStore.loadBoard(filePath);
		if (loadedBoard == null) {
			addMessage("Loading from file failed: " + filePath, ERROR_MSG);
			return ERROR_LOADBOARD_LOADING_FAILED;
		}
		if (boardState != BOARD_EMPTY)
			clearPuzzels();
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++)
				sudokuBoard[i][j] = loadedBoard[i][j];
		boardState = BOARD_LOADED;
		addMessage("Sudoku loaded, file: " + filePath, NORMAL_MSG);
		return findEmptyCells();
	}
	/**
	 * Loads Sudoku from array.
	 *
	 * @param sudokuBoard Array representing Sudoku puzzles.
	 * @return {@link SudokuSolver#ERROR_LOADBOARD_LOADING_FAILED} or
	 *         {@link SudokuBoard#BOARD_LOADED}.
	 */
	public int loadBoard(int[][] sudokuBoard) {
		if (sudokuBoard == null) {
			addMessage("Loading from array failed - null array!", ERROR_MSG);
			return ERROR_LOADBOARD_LOADING_FAILED;
		}
		if (sudokuBoard.length != BOARD_SIZE) {
			addMessage("Loading from array failed - incorrect number of rows! " + sudokuBoard.length, ERROR_MSG);
			return ERROR_LOADBOARD_LOADING_FAILED;
		}
		for (int i = 0; i < sudokuBoard.length; i++)
			if (sudokuBoard[i].length != BOARD_SIZE) {
				addMessage("Loading from array failed - incorrect number of columns! " + sudokuBoard[i].length, ERROR_MSG);
				return ERROR_LOADBOARD_LOADING_FAILED;
			}
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++) {
				int d = sudokuBoard[i][j];
				if ( !( ((d >= 1) && (d <= 9)) || (d == EMPTY_CELL) ) )  {
					addMessage("Loading from array failed - incorrect digit: " + d, ERROR_MSG);
					return ERROR_LOADBOARD_LOADING_FAILED;
				}
			}
		if (boardState != BOARD_EMPTY)
			clearPuzzels();
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++)
				this.sudokuBoard[i][j] = sudokuBoard[i][j];
		boardState = BOARD_LOADED;
		addMessage("Sudoku loaded from array!", NORMAL_MSG);
		return findEmptyCells();
	}
	/**
	 * Manually set cell value.
	 *
	 * @param rowIndex   Cell row index between 0 and 8.
	 * @param colIndex   Cell column index between 0 and 8.
	 * @param digit      Cell digit between 1 and 9, or EMPTY_CELL.
	 * @return           Number of empty cells that left if cell definition correct,
	 *                   {@link SudokuSolver#ERROR_SETCELL_INCORRECT_DEFINITION} otherwise.
	 */
	public int setCell(int rowIndex, int colIndex, int digit) {
		if ( (rowIndex < 0) || (rowIndex >= BOARD_SIZE) ) {
			addMessage("Incorrect row index - is: " + rowIndex + ", should be between 0 and " + BOARD_SIZE + ".", ERROR_MSG);
			return ERROR_SETCELL_INCORRECT_DEFINITION;
		}
		if ( (colIndex < 0) || (colIndex >= BOARD_SIZE) ) {
			addMessage("Incorrect colmn index - is: " + colIndex + ", should be between 0 and " + BOARD_SIZE + ".", ERROR_MSG);
			return ERROR_SETCELL_INCORRECT_DEFINITION;
		}
		if ( ( (digit < 1) || (digit > 9) ) && (digit != EMPTY_CELL) ){
			addMessage("Incorrect digit definition - is: " + digit + ", should be between 1 and 9, or " + EMPTY_CELL + " for empty cell", ERROR_MSG);
			return ERROR_SETCELL_INCORRECT_DEFINITION;
		}
		return findEmptyCells();
	}
	/**
	 * Returns cell digit from current Sudoku board.
	 * @param rowIndex    Cell row index between 0 and 8.
	 * @param colIndex    Cell column index between 0 and 8.
	 * @return            Cell digit between 1 and 9, if cell empty
	 *                    then {@link BoardCell#EMPTY_CELL}.
	 *                    If indexes are out of range then error
	 *                    {@link SudokuSolver#ERROR_GETCELLDIGIT_INCORRECT_INDEX}
	 *                    is returned.
	 */
	public int getCellDigit(int rowIndex, int colIndex) {
		if ( (rowIndex < 0) || (rowIndex >= BOARD_SIZE) ) {
			addMessage("Incorrect row index - is: " + rowIndex + ", should be between 0 and " + BOARD_SIZE + ".", ERROR_MSG);
			return ERROR_GETCELLDIGIT_INCORRECT_INDEX;
		}
		if ( (colIndex < 0) || (colIndex >= BOARD_SIZE) ) {
			addMessage("Incorrect colmn index - is: " + colIndex + ", should be between 0 and " + BOARD_SIZE + ".", ERROR_MSG);
			return ERROR_GETCELLDIGIT_INCORRECT_INDEX;
		}
		return sudokuBoard[rowIndex][colIndex];
	}
	/*
	 * =====================================================
	 *                  Solving methods
	 * =====================================================
	 */
	/**
	 * Method starts solving procedure.
	 * @return if board state is {@link SudokuBoard#BOARD_EMPTY} then {@link SudokuSolver#ERROR_SOLVE_SOLVING_NOT_STARTED},
	 *         if board state is {@link SudokuBoard#BOARD_ERROR} then {@link SudokuSolver#ERROR_SOLVE_SOLVING_NOT_STARTED},
	 *         if board state is {@link SudokuBoard#BOARD_LOADED} then {@link SudokuSolver#ERROR_SOLVE_SOLVING_NOT_STARTED},
	 *         if board state is {@link SudokuBoard#BOARD_READY} then returns solving status:
	 *              {@link SudokuSolver#SUDOKU_SOLVED},
	 *              {@link SudokuSolver#ERROR_SOLVE_SOLVING_FAILED}.
	 *
	 */
	public int solve() {
		switch(boardState) {
		case BOARD_EMPTY:
			addMessage("Nothing to solve - the board is empty!", ERROR_MSG);
			solvingStatus = SUDOKU_SOLVING_NOT_STARTED;
			return ERROR_SOLVE_SOLVING_NOT_STARTED;
		case BOARD_ERROR:
			addMessage("Can not start solving process - the board contains an error!", ERROR_MSG);
			solvingStatus = SUDOKU_SOLVING_NOT_STARTED;
			return ERROR_SOLVE_SOLVING_NOT_STARTED;
		case BOARD_LOADED:
			addMessage("Can not start solving process - the board is not ready!", ERROR_MSG);
			solvingStatus = SUDOKU_SOLVING_NOT_STARTED;
			return ERROR_SOLVE_SOLVING_NOT_STARTED;
		case BOARD_READY:
			addMessage("Starting solving process!", NORMAL_MSG);
			if (randomizeEmptyCells == true)
				addMessage(">>> Will randomize empty cells if number of still free digits is the same.", NORMAL_MSG);
			if (randomizeFreeDigits == true)
				addMessage(">>> Will randomize still free digits for a given empty cell.", NORMAL_MSG);
			solvingStatus = SUDOKU_SOLVING_STARTED;
			solutionBoardCells = new Stack<BoardCell>();
			long solvingStartTime = DateTimeX.currentTimeMillis();
			closedPathsCounter = 0;
			solve(0);
			long solvingEndTime = DateTimeX.currentTimeMillis();
			solvingTime = (solvingEndTime - solvingStartTime) / 1000.0;
			if (solvingStatus != SUDOKU_SOLVED) {
				solvingStatus = ERROR_SOLVE_SOLVING_FAILED;
				boardState = BOARD_ERROR;
				addMessage("Error while solving - no solutions found - setting board state as 'error' !!", ERROR_MSG);
			} else {
				addMessage("Sudoku solved !!! Cells solved: " + emptyCellsNumber + " ... Closed routes: " + closedPathsCounter + " ... solving time: " + solvingTime + " s.", NORMAL_MSG);
				emptyCellsNumber = 0;
			}
			return solvingStatus;
		}
		addMessage("Can not start solving process - do not know why :-(", ERROR_MSG);
		solvingStatus = SUDOKU_SOLVING_NOT_STARTED;
		return ERROR_SOLVE_SOLVING_NOT_STARTED;
	}
	/**
	 * Recursive process of Sudoku solving.
	 * @param level     Level of recursive step.
	 */
	private void solve(int level) {
		/**
		 * Close route if solving process stopped
		 */
		if (solvingStatus != SUDOKU_SOLVING_STARTED)
			return;
		/**
		 * Enter level
		 */
		EmptyCell emptyCell;
		/**
		 * Check if solved
		 */
		if (level == emptyCellsNumber) {
			solvingStatus = SUDOKU_SOLVED;
			solvedBoard = new int[BOARD_SIZE][BOARD_SIZE];
			for (int i = 0; i < BOARD_SIZE; i++)
				for (int j = 0; j < BOARD_SIZE; j++)
					solvedBoard[i][j] = sudokuBoard[i][j];
			return;
		}
		/**
		 * If still other cells are empty, perform recursive steps.
		 */
		emptyCell = emptyCells[level];
		int digitsStillFreeNumber = emptyCell.digitsStillFreeNumber;
		if (digitsStillFreeNumber > 0) {
			int digitNum = 0;
			for (int digitIndex = 1; digitIndex <= 9; digitIndex++) {
				int digit = digitIndex;
				if (randomizeFreeDigits == true)
					digit = emptyCell.digitsRandomSeed[digitIndex].digit;
				if (emptyCell.digitsStillFree[digit] == DIGIT_STILL_FREE) {
					digitNum++;
					sudokuBoard[emptyCell.rowIndex][emptyCell.colIndex] = digit;
					if (level + 1 < emptyCellsNumber - 1)
						sortEmptyCells(level+1, emptyCellsNumber-1);
					solutionBoardCells.push( new BoardCell(emptyCell.rowIndex, emptyCell.colIndex, digit) );
					updateDigitsStillFree(emptyCell);
					solve(level + 1);
					if (solvingStatus == SUDOKU_SOLVING_STARTED) {
						solutionBoardCells.pop();
						if (digitNum == digitsStillFreeNumber) {
							sudokuBoard[emptyCell.rowIndex][emptyCell.colIndex] = EMPTY_CELL;
							updateDigitsStillFree(emptyCell);
							if (level < emptyCellsNumber - 1)
								sortEmptyCells(level, emptyCellsNumber-1);
							closedPathsCounter++;
						}
					} else
						return;
				}
			}
		} else {
			sudokuBoard[emptyCell.rowIndex][emptyCell.colIndex] = EMPTY_CELL;
			updateDigitsStillFree(emptyCell);
		}
	}
	public int findAllSolutions() {
		switch(boardState) {
		case BOARD_EMPTY:
			addMessage("Nothing to solve - the board is empty!", ERROR_MSG);
			return ERROR_SOLVE_SOLVING_NOT_STARTED;
		case BOARD_ERROR:
			addMessage("Can not start solving process - the board contains an error!", ERROR_MSG);
			return ERROR_SOLVE_SOLVING_NOT_STARTED;
		case BOARD_LOADED:
			addMessage("Can not start solving process - the board is not ready!", ERROR_MSG);
			return ERROR_SOLVE_SOLVING_NOT_STARTED;
		case BOARD_READY:
			addMessage("Starting solving process!", NORMAL_MSG);
			if (randomizeEmptyCells == true)
				addMessage(">>> Will randomize empty cells if number of still free digits is the same.", NORMAL_MSG);
			if (randomizeFreeDigits == true)
				addMessage(">>> Will randomize still free digits for a given empty cell.", NORMAL_MSG);
			solutionsList = new ArrayList<SudokuBoard>();
			long solvingStartTime = DateTimeX.currentTimeMillis();
			totalPathsCounter = 0;
			findAllSolutions(0);
			long solvingEndTime = DateTimeX.currentTimeMillis();
			solvingTime = (solvingEndTime - solvingStartTime) / 1000.0;
			return solutionsList.size();
		}
		addMessage("Can not start solving process - do not know why :-(", ERROR_MSG);
		return ERROR_SOLVE_SOLVING_NOT_STARTED;
	}
	private void findAllSolutions(int level) {
		/*
		 * Enter level.
		 * Check if solved.
		 */
		if (level == emptyCellsNumber) {
			SudokuBoard solution = new SudokuBoard();
			for (int i = 0; i < BOARD_SIZE; i++)
				for (int j = 0; j < BOARD_SIZE; j++)
					solution.board[i][j] = sudokuBoard[i][j];
			solutionsList.add(solution);
			System.out.println("Solution + 1, total paths: " + totalPathsCounter);
			return;
		}
		/*
		 * If still other cells are empty, perform recursive steps.
		 */
		EmptyCell emptyCell = emptyCells[level];
		int digitsStillFreeNumber = emptyCell.digitsStillFreeNumber;
		if (digitsStillFreeNumber > 0) {
			int digitNum = 0;
			for (int digitIndex = 1; digitIndex <= 9; digitIndex++) {
				int digit = digitIndex;
				if (randomizeFreeDigits == true)
					digit = emptyCell.digitsRandomSeed[digitIndex].digit;
				if (emptyCell.digitsStillFree[digit] == DIGIT_STILL_FREE) {
					digitNum++;
					sudokuBoard[emptyCell.rowIndex][emptyCell.colIndex] = digit;
					if (level + 1 < emptyCellsNumber - 1)
						sortEmptyCells(level+1, emptyCellsNumber-1);
					updateDigitsStillFree(emptyCell);
					findAllSolutions(level + 1);
					if (digitNum == digitsStillFreeNumber) {
						sudokuBoard[emptyCell.rowIndex][emptyCell.colIndex] = EMPTY_CELL;
						updateDigitsStillFree(emptyCell);
						if (level < emptyCellsNumber - 1)
							sortEmptyCells(level, emptyCellsNumber-1);
						totalPathsCounter++;
					}
				}
			}
		} else {
			sudokuBoard[emptyCell.rowIndex][emptyCell.colIndex] = EMPTY_CELL;
			updateDigitsStillFree(emptyCell);
		}
	}
	/*
	 * =====================================================
	 *               Board related methods
	 * =====================================================
	 */
	/**
	 * To clear the Sudoku board.
	 */
	public void clearPuzzels() {
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++) {
				sudokuBoard[i][j] = EMPTY_CELL;
				emptyCellsPointer[i][j] = null;
			}
		for (int i = 0; i < BOARD_CELLS_NUMBER; i++)
			emptyCells[i] = new EmptyCell();
		emptyCellsNumber = 0;
		solvingStatus = SUDOKU_SOLVING_NOT_STARTED;
		boardState = BOARD_EMPTY;
		solvedBoard = null;
		solutionBoardCells = null;
		solvingTime = 0;
		closedPathsCounter = 0;
		addMessage("Clearing sudoku board - board is empty.", NORMAL_MSG);
	}
	/**
	 * Search and initialize list of empty cells.
	 * @return    {@link SudokuBoard#BOARD_EMPTY}
	 *            {@link SudokuBoard#BOARD_READY}
	 *            {@link SudokuBoard#BOARD_ERROR}.
	 */
	private int findEmptyCells() {
		for (int i = 0; i < BOARD_CELLS_NUMBER; i++)
			emptyCells[i] = new EmptyCell();
		int emptyCellIndex = 0;
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++)
				if (sudokuBoard[i][j] == EMPTY_CELL) {
					emptyCells[emptyCellIndex].rowIndex = i;
					emptyCells[emptyCellIndex].colIndex = j;
					emptyCellsPointer[i][j] = emptyCells[emptyCellIndex];
					findDigitsStillFree(emptyCells[emptyCellIndex]);
					if (emptyCells[emptyCellIndex].digitsStillFreeNumber == 0) {
						addMessage("Cell empty, but no still free digit to fill in - cell: " + i + ", " + j, ERROR_MSG);
						return BOARD_ERROR;
					}
					emptyCellIndex++;
				}
		emptyCellsNumber = emptyCellIndex;
		addMessage("Empty cells evaluated - number of cells to solve: " + emptyCellsNumber, NORMAL_MSG);
		if (boardState == BOARD_EMPTY) {
			addMessage("Empty board - please fill some values.", NORMAL_MSG);
		} else if (emptyCellsNumber > 0) {
			sortEmptyCells(0, emptyCellsNumber - 1);
			boardState = BOARD_READY;
		} else {
			addMessage("No cells to solve - check Sudoku board definition.", ERROR_MSG);
			boardState = BOARD_ERROR;
			return BOARD_ERROR;
		}
		return BOARD_READY;
	}
	/**
	 * Find digits that still can be used in a given empty cell.
	 * @param emptyCell Empty cell to search still free digits for.
	 */
	private void findDigitsStillFree(EmptyCell emptyCell) {
		emptyCell.setAllDigitsStillFree();
		for (int j = 0; j < BOARD_SIZE; j++) {
			int boardDigit = sudokuBoard[emptyCell.rowIndex][j];
			if (boardDigit != EMPTY_CELL)
				emptyCell.digitsStillFree[boardDigit] = DIGIT_IN_USE;
		}
		for (int i = 0; i < BOARD_SIZE; i++) {
			int boardDigit = sudokuBoard[i][emptyCell.colIndex];
			if (boardDigit != EMPTY_CELL)
				emptyCell.digitsStillFree[boardDigit] = DIGIT_IN_USE;
		}
		SubSquare sub = getSubSqare(emptyCell);
		/*
		 * Mark digits used in a sub-square.
		 */
		for (int i = sub.rowMin; i < sub.rowMax; i++)
			for (int j = sub.colMin; j < sub.colMax; j++) {
				int boardDigit = sudokuBoard[i][j];
				if (boardDigit != EMPTY_CELL)
					emptyCell.digitsStillFree[boardDigit] = DIGIT_IN_USE;
			}
		/*
		 * Find number of still free digits to use.
		 */
		emptyCell.digitsStillFreeNumber = 0;
		for (int digit = 1; digit < 10; digit++)
			if (emptyCell.digitsStillFree[digit] == DIGIT_STILL_FREE)
				emptyCell.digitsStillFreeNumber++;
	}
	/**
	 * Find digits that still can be used in a given empty cell.
	 * @param emptyCell Empty cell to search still free digits for.
	 */
	private void updateDigitsStillFree(EmptyCell emptyCell) {
		for (int j = 0; j < BOARD_SIZE; j++)
			if (sudokuBoard[emptyCell.rowIndex][j] == EMPTY_CELL)
				findDigitsStillFree(emptyCellsPointer[emptyCell.rowIndex][j]);
		for (int i = 0; i < BOARD_SIZE; i++)
			if (sudokuBoard[i][emptyCell.colIndex] == EMPTY_CELL)
				findDigitsStillFree(emptyCellsPointer[i][emptyCell.colIndex]);
		SubSquare sub = getSubSqare(emptyCell);
		for (int i = sub.rowMin; i < sub.rowMax; i++)
			for (int j = sub.colMin; j < sub.colMax; j++)
				if (sudokuBoard[i][j] == EMPTY_CELL)
					findDigitsStillFree(emptyCellsPointer[i][j]);
		/*
		 * Mark digits used in a sub-sqre
		 */
		for (int i = sub.rowMin; i < sub.rowMax; i++)
			for (int j = sub.colMin; j < sub.colMax; j++) {
				int boardDigit = sudokuBoard[i][j];
				if (boardDigit != EMPTY_CELL)
					emptyCell.digitsStillFree[boardDigit] = DIGIT_IN_USE;
			}
		/*
		 * Find number of still free digits to use.
		 */
		emptyCell.digitsStillFreeNumber = 0;
		for (int digit = 1; digit < 10; digit++)
			if (emptyCell.digitsStillFree[digit] == DIGIT_STILL_FREE)
				emptyCell.digitsStillFreeNumber++;
	}
	/**
	 * Sorting empty cells list by ascending number of
	 * still free digits left that can be used in a context
	 * of a given empty cell.
	 *
	 * @param l    Starting left index.
	 * @param r    Starting rgth index.
	 */
	private void sortEmptyCells(int l, int r) {
		int i = l;
		int j = r;
		EmptyCell x;
		EmptyCell w;
		x = emptyCells[(l+r)/2];
		do {
			if (randomizeEmptyCells == true) {
				/*
				 * Adding randomization
				 */
				while (emptyCells[i].digitsStillFreeNumber + emptyCells[i].randomSeed < x.digitsStillFreeNumber + x.randomSeed)
					i++;
				while (emptyCells[j].digitsStillFreeNumber + emptyCells[j].randomSeed > x.digitsStillFreeNumber + x.randomSeed)
					j--;
			} else {
				/*
				 * No randomization
				 */
				while (emptyCells[i].digitsStillFreeNumber < x.digitsStillFreeNumber)
					i++;
				while (emptyCells[j].digitsStillFreeNumber > x.digitsStillFreeNumber)
					j--;
			}
			if (i<=j)
			{
				w = emptyCells[i];
				emptyCells[i] = emptyCells[j];
				emptyCells[j] = w;
				i++;
				j--;
			}
		} while (i <= j);
		if (l < j)
			sortEmptyCells(l,j);
		if (i < r)
			sortEmptyCells(i,r);
	}
	/**
	 * Sub-square identification on the Sudoku board
	 * based on the cell position
	 * @param emptyCell   Cell object, including cell position
	 * @return             Sub-square left-top and right-bottom indexes.
	 */
	private SubSquare getSubSqare(EmptyCell emptyCell) {
		SubSquare sub = new SubSquare();
		if (emptyCell.rowIndex < SUB_SQURE_SIZE) {
			sub.rowMin = 0;
			sub.rowMax = SUB_SQURE_SIZE;
		} else if (emptyCell.rowIndex < 2*SUB_SQURE_SIZE) {
			sub.rowMin = SUB_SQURE_SIZE;
			sub.rowMax = 2*SUB_SQURE_SIZE;
		} else {
			sub.rowMin = 2*SUB_SQURE_SIZE;
			sub.rowMax = 3*SUB_SQURE_SIZE;
		}
		if (emptyCell.colIndex < SUB_SQURE_SIZE) {
			sub.colMin = 0;
			sub.colMax = SUB_SQURE_SIZE;
		} else if (emptyCell.colIndex < 2*SUB_SQURE_SIZE) {
			sub.colMin = SUB_SQURE_SIZE;
			sub.colMax = 2*SUB_SQURE_SIZE;
		} else {
			sub.colMin = 2*SUB_SQURE_SIZE;
			sub.colMax = 3*SUB_SQURE_SIZE;
		}
		return sub;

	}
	/**
	 * Message builder.
	 * @param msg Message.
	 */
	private void addMessage(String msg, int msgType) {
		String vdt = "[" + SudokuStore.JANET_SUDOKU_NAME + "-v." + SudokuStore.JANET_SUDOKU_VERSION + "][" + DateTimeX.getCurrDateTimeStr() + "]";
		String mt = "(msg)";
		if (msgType == ERROR_MSG) {
			mt = "(error)";
			lastErrorMessage = msg;
		}
		messages = messages + "\n" + vdt + mt + " " + msg;
		lastMessage = msg;
	}
	/**
	 * Returns list of recorded messages.
	 * @return List of recorded messages.
	 */
	public String getMessages() {
		return messages;
	}
	/**
	 * Clears list of recorded messages.
	 */
	public void clearMessages() {
		messages = "";
	}
	/**
	 * Gets last recorded message.
	 * @return Last recorded message.
	 */
	public String getLastMessage() {
		return lastMessage;
	}
	/**
	 * Gets last recorded error message.
	 * @return Last recorded message.
	 */
	public String getLastErrorMessage() {
		return lastErrorMessage;
	}
	/**
	 * Return current Sudou board state.
	 * @return  {@link SudokuBoard#BOARD_READY} or
	 *          {@link SudokuBoard#BOARD_EMPTY} or
	 *          {@link SudokuBoard#BOARD_ERROR}.
	 */
	public int getBoardState() {
		return boardState;
	}
	/**
	 * Return current solving status.
	 * @return  {@link SudokuSolver#SUDOKU_SOLVING_NOT_STARTED} or
	 *          {@link SudokuSolver#ERROR_SOLVE_SOLVING_FAILED} or
	 *          {@link SudokuSolver#SUDOKU_SOLVED}.
	 */
	public int getSolvingStatus() {
		return solvingStatus;
	}
	/**
	 * Gets array representing Sudoku board.
	 * @return Array representing Sudoku board.
	 */
	public int[][] getSudokuBoard() {
		return sudokuBoard;
	}
	/**
	 * Gets array representing solved Sudoku board.
	 * @return Array representing solved Sudoku board.
	 */
	public int[][] getSolvedBoard() {
		return solvedBoard;
	}
	/**
	 * Gets array representing evaluated empty cells.
	 * meaning number of still free digits possible.
	 * @return Array representing evaluated empty cells.
	 */
	public int[][] getEmptyCells() {
		int[][] emptyCells = new int[BOARD_SIZE][BOARD_SIZE];
		if (boardState == BOARD_EMPTY) {
			for (int i = 0; i < BOARD_SIZE; i++)
				for (int j = 0; i < BOARD_SIZE; i++)
					emptyCells[i][j] = 9;
			return emptyCells;
		}
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; i < BOARD_SIZE; i++) {
				if (sudokuBoard[i][j] == EMPTY_CELL)
					emptyCells[i][j] = emptyCellsPointer[i][j].digitsStillFreeNumber;
				else
					emptyCells[i][j] = 0;
			}
		return emptyCells;
	}
	/**
	 * Get all current board cells.
	 * @return  Array of current board cells.
	 */
	public BoardCell[] getAllBoardCells() {
		BoardCell[] boardCells = new BoardCell[BOARD_CELLS_NUMBER];
		int cellIndex = 0;
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; i < BOARD_SIZE; j++) {
				boardCells[cellIndex] = new BoardCell(i, j, sudokuBoard[i][j]);
				cellIndex++;
			}
		return boardCells;
	}
	/**
	 * Return solution board cells keeping the solution
	 * path order. If error was encountered while solving
	 * path to the solution will be incomplete, but will show
	 * where solving process was aborted.
	 *
	 * @return Array of board cells that lead to the solution (keeping
	 *         the path order).
	 */
	public BoardCell[] getSolutionBoardCells() {
		if (solutionBoardCells == null) return null;
		if (solutionBoardCells.size() == 0) return null;
		return ArrayX.toArray(BoardCell.class, solutionBoardCells);
	}
	/**
	 * Return solving time in seconds..
	 * @return  Solvnig time in seconds.
	 */
	public double getSolvingTime() {
		return solvingTime;
	}
	/**
	 * Number of routes that were assessed, but lead to nothing
	 * and required step back. The higher number to more computation was
	 * performed while solving.
	 * @return Number of closed routes while solving.
	 */
	public int getClosedRoutesNumber() {
		return closedPathsCounter;
	}
	/**
	 * By default random seed on empty cells is enabled. This parameter
	 * affects solving process only. Random seed on
	 * empty cells causes randomization on empty cells
	 * within empty cells with the same number of still free digits.
	 * Enabling extends ability to find different solutions, if exists.
	 */
	public void enableRndSeedOnEmptyCells() {
		randomizeEmptyCells = true;
	}
	/**
	 * By default random seed on empty cells is enabled. This parameter
	 * affects solving process only. Random seed on
	 * empty cells causes randomization on empty cells
	 * within empty cells with the same number of still free digits.
	 * Disabling limits ability to find different solutions, if exists.
	 */
	public void disableRndSeedOnEmptyCells() {
		randomizeEmptyCells = false;
	}
	/**
	 * By default random seed on free digits is enabled. This parameter
	 * affects solving process only. Random seed on
	 * free digits causes randomization on accessing free digits
	 * for a given empty cells. Each free digits is a starting point
	 * for a new recursive sub-path potentially leading to solution.
	 * Enabling extends ability to find different solutions, if exists.
	 */
	public void enableRndSeedOnFreeDigits() {
		randomizeFreeDigits = true;
	}
	/**
	 * By default random seed on free digits is enabled. This parameter
	 * affects solving process only. Random seed on
	 * free digits causes randomization on accessing free digits
	 * for a given empty cells. Each free digits is a starting point
	 * for a new recursive sub-path potentially leading to solution.
	 * Disabling limits ability to find different solutions, if exists.
	 */
	public void disableRndSeedOnFreeDigits() {
		randomizeFreeDigits = false;
	}
	/**
	 * Returns board state summary.
	 * @return Board state summary as string.
	 */
	private String boardStateToString() {
		String boardStateStr = "Board: ";
		switch(boardState) {
		case BOARD_EMPTY:
			boardStateStr = boardStateStr + "empty";
			break;
		case BOARD_ERROR:
			boardStateStr = boardStateStr + "error";
			break;
		case BOARD_LOADED:
			boardStateStr = boardStateStr + "loaded";
			break;
		case BOARD_READY:
			boardStateStr = boardStateStr + "ready";
			break;
		}
		boardStateStr = boardStateStr + "\n" + "Initial empty cells: " + emptyCellsNumber;
		boardStateStr = boardStateStr + "\n" + "Solving : ";
		switch(solvingStatus) {
		case SUDOKU_SOLVING_NOT_STARTED:
			boardStateStr = boardStateStr + "not started";
			break;
		case SUDOKU_SOLVING_STARTED:
			boardStateStr = boardStateStr + "started";
			break;
		case SUDOKU_SOLVED:
			boardStateStr = boardStateStr + "solved";
			break;
		case ERROR_SOLVE_SOLVING_FAILED:
			boardStateStr = boardStateStr + "failed";
			break;
		}
		return boardStateStr;
	}
	/**
	 * Returns string board and empty cells representation.
	 * @return Board and empty cells representation.
	 */
	public String boardAndEmptyCellsToString() {
		return SudokuStore.boardAndEmptyCellsToString(sudokuBoard, getEmptyCells()) + boardStateToString() + "\n";
	}
	/**
	 * Returns string board (only) representation.
	 * @return Board (only) representation.
	 */
	public String boardToString() {
		return SudokuStore.boardToString(sudokuBoard) + boardStateToString() + "\n";
	}
	/**
	 * Returns string empty cells (only) representation.
	 * @return Empty cells (only) representation.
	 */
	public String emptyCellsToString() {
		return SudokuStore.emptyCellsToString( getEmptyCells() ) + boardStateToString() + "\n";
	}
	/**
	 * Return string representation of cells that lead to
	 * the solution, keeping the sequence.
	 * @return  String representation of entries that lead to the solution.
	 */
	public String solutionPathToString() {
		return SudokuStore.solutionPathToString( getSolutionBoardCells() );
	}
}