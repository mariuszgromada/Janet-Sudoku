/*
 * @(#)Sudoku.java        0.0.1    2016-02-01
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
package org.mariuszgromada.math.entertain.sudokusolver;

import java.util.Stack;

import org.mariuszgromada.utils.ArrayX;
import org.mariuszgromada.utils.DateTimeX;

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
public class SudokuBoard {
	/**
	 * Sudoku solver version
	 */
	public static final String SUDOKU_SOLVER_VERSION = "0.0.1";
	/**
	 * Sudoku solver name;
	 */
	final String SUDOKU_SOLVER_NAME = "SudokuSolver";
	/**
	 * Sudoku board was successfully loaded.
	 */
	public static final int BOARD_EMPTY = 1;
	/**
	 * Sudoku board was successfully loaded.
	 */
	public static final int BOARD_LOADED = 2;
	/**
	 * Sudoku board is ready to start solving process.
	 */
	public static final int BOARD_READY = 3;
	/**
	 * Sudoku board is ready to start solving process.
	 */
	public static final int BOARD_ERROR = -1;
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
	public static final int ERROR_BOARD_LOADING_FAILED = 100;
	/**
	 * Sudoku board is ready to start solving process.
	 */
	public static final int ERROR_BOARD_CHECKING_FAILED = 101;
	/**
	 * Sudoku solving started, but failed.
	 */
	public static final int ERROR_SUDOKU_SOLVING_NOT_STARTED = 102;
	/**
	 * Sudoku solving started, but failed.
	 */
	public static final int ERROR_SUDOKU_SOLVING_FAILED = 103;
	/**
	 * Sudoku board size.
	 */
	public static final int BOARD_SIZE = 9;
	/**
	 * Sudoku board sub-square size.
	 */
	public static final int SUB_SQURE_SIZE = 3;
	/**
	 * We will use array indexes from 1.,.n, instead 0...n-1
	 */
	static final int BOARD_MAX_INDEX = BOARD_SIZE + 1;
	/**
	 * Number of fields on the Sudoku board
	 */
	static final int BOARD_FIELDS_NUMBER = BOARD_SIZE * BOARD_SIZE;
	/**
	 * Empty field.
	 */
	static final byte EMPTY_FIELD = BoardEntry.EMPTY_FIELD;
	/**
	 * Message type normal.
	 */
	private static final int NORMAL_MSG = 1;
	/**
	 * Message type error.
	 */
	private static final int ERROR_MSG = -1;
	/**
	 * Marker if analyzed digit 0...9 is still not used.
	 */
	static final int DIGIT_STILL_FREE = 1;
	/**
	 * Digit 0...9 can not be used in that place
	 */
	static final int DIGIT_IN_USE = 2;
	/**
	 * Field is not pointing to any fields on the board
	 */
	static final int NULL_INDEX = BoardEntry.NULL_INDEX;
	/**
	 * Sudoku board used while solving process.
	 */
	int[][] sudokuBoard = new int[BOARD_SIZE][BOARD_SIZE];
	/**
	 * Sudoku solution
	 */
	int[][] solvedBoard = null;
	/**
	 * Path to the sudoku solution
	 */
	private Stack<BoardEntry> solutionBoardEntries;
	/**
	 * Solving time in seconds
	 */
	private double solvingTime;
	/**
	 * Step back counter showing how many different
	 * routes were evaluated while solving
	 */
	private int stepsBackCounter;
	/**
	 * If yes then empty fields with the same number of
	 * still free digits will be randomized
	 */
	private boolean randomizeEmptyFields;
	/**
	 * If yes then still free digits for a given empty field
	 * will be randomized
	 */
	private boolean randomizeFreeDigits;
	/**
	 * Empty fields on the sudoku board
	 */
	EmptyField[] emptyFields = new EmptyField[BOARD_FIELDS_NUMBER];
	/**
	 * Pointers to the empty fields
	 */
	EmptyField[][] emptyFieldsPointer = new EmptyField[BOARD_SIZE][BOARD_SIZE];
	/**
	 * Number of empty fields on the sudoku board/
	 */
	int emptyFieldsNumber;
	/**
	 * Solving status indicator.
	 */
	int boardState;
	/**
	 * Solving status indicator.
	 */
	int solvingStatus;
	/**
	 * Error message, if necessary.
	 */
	String messages = "";
	/**
	 * last message;
	 */
	String lastMessage = "";
	/**
	 * last erro message;
	 */
	String lastErrorMessage = "";
	/**
	 * To clear the Sudoku board
	 */
	private void boardInit() {
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++) {
				sudokuBoard[i][j] = EMPTY_FIELD;
				emptyFieldsPointer[i][j] = null;
			}
		for (int i = 0; i < BOARD_FIELDS_NUMBER; i++)
			emptyFields[i] = new EmptyField();
		emptyFieldsNumber = 0;
		solvingStatus = SUDOKU_SOLVING_NOT_STARTED;
		boardState = BOARD_EMPTY;
		solvedBoard = null;
		solutionBoardEntries = null;
		solvingTime = 0;
		stepsBackCounter = 0;
		addMessage("Clearing sudoku board - board is empty.", NORMAL_MSG);
	}
	/**
	 * Search and initialize list of empty fields.
	 * @return    {@value SudokuBoard#BOARD_EMPTY}
	 *            {@value SudokuBoard#BOARD_READY}
	 *            {@value SudokuBoard#BOARD_ERROR}
	 */
	private int findEmptyFields() {
		for (int i = 0; i < BOARD_FIELDS_NUMBER; i++)
			emptyFields[i] = new EmptyField();
		int emptyFieldIndex = 0;
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++)
				if (sudokuBoard[i][j] == EMPTY_FIELD) {
					emptyFields[emptyFieldIndex].rowIdx = i;
					emptyFields[emptyFieldIndex].colIdx = j;
					emptyFieldsPointer[i][j] = emptyFields[emptyFieldIndex];
					findDigitsStillFree(emptyFields[emptyFieldIndex]);
					if (emptyFields[emptyFieldIndex].digitsStillFreeNumber == 0) {
						addMessage("Field empty, but no still free digit to fill in - field: " + i + ", " + j, ERROR_MSG);
						return BOARD_ERROR;
					}
					emptyFieldIndex++;
				}
		emptyFieldsNumber = emptyFieldIndex;
		addMessage("Empty fields evaluated - number of fields to solve: " + emptyFieldsNumber, NORMAL_MSG);
		if (boardState == BOARD_EMPTY) {
			addMessage("Empty board - please fill some values.", NORMAL_MSG);
		} else if (emptyFieldsNumber > 0) {
			sortEmptyFields(0, emptyFieldsNumber - 1);
			boardState = BOARD_READY;
		} else {
			addMessage("No fields to solve - check Sudoku board definition.", ERROR_MSG);
			boardState = BOARD_ERROR;
			return BOARD_ERROR;
		}
		return BOARD_READY;
	}
	/**
	 * Sub-square identification on the Sudoku board
	 * beased on the filed position
	 * @param emptyField   Field object, including field position
	 * @return             Sub-square left-top and right-bottom indexes.
	 */
	private SubSquare getSubSqare(EmptyField emptyField) {
		SubSquare sub = new SubSquare();
		if (emptyField.rowIdx < SUB_SQURE_SIZE) {
			sub.rowMin = 0;
			sub.rowMax = SUB_SQURE_SIZE;
		} else if (emptyField.rowIdx < 2*SUB_SQURE_SIZE) {
			sub.rowMin = SUB_SQURE_SIZE;
			sub.rowMax = 2*SUB_SQURE_SIZE;
		} else {
			sub.rowMin = 2*SUB_SQURE_SIZE;
			sub.rowMax = 3*SUB_SQURE_SIZE;
		}
		if (emptyField.colIdx < SUB_SQURE_SIZE) {
			sub.colMin = 0;
			sub.colMax = SUB_SQURE_SIZE;
		} else if (emptyField.colIdx < 2*SUB_SQURE_SIZE) {
			sub.colMin = SUB_SQURE_SIZE;
			sub.colMax = 2*SUB_SQURE_SIZE;
		} else {
			sub.colMin = 2*SUB_SQURE_SIZE;
			sub.colMax = 3*SUB_SQURE_SIZE;
		}
		return sub;

	}
	/**
	 * Find digits that still can be used in a given empty field
	 * @param emptyField Empty field to search still free digits for.
	 */
	private void findDigitsStillFree(EmptyField emptyField) {
		emptyField.setAllDigitsStillFree();
		for (int j = 0; j < BOARD_SIZE; j++) {
			int boardDigit = sudokuBoard[emptyField.rowIdx][j];
			if (boardDigit != EMPTY_FIELD)
				emptyField.digitsStillFree[boardDigit] = DIGIT_IN_USE;
		}
		for (int i = 0; i < BOARD_SIZE; i++) {
			int boardDigit = sudokuBoard[i][emptyField.colIdx];
			if (boardDigit != EMPTY_FIELD)
				emptyField.digitsStillFree[boardDigit] = DIGIT_IN_USE;
		}
		SubSquare sub = getSubSqare(emptyField);
		/*
		 * Mark digits used in a sub-sqre
		 */
		for (int i = sub.rowMin; i < sub.rowMax; i++)
			for (int j = sub.colMin; j < sub.colMax; j++) {
				int boardDigit = sudokuBoard[i][j];
				if (boardDigit != EMPTY_FIELD)
					emptyField.digitsStillFree[boardDigit] = DIGIT_IN_USE;
			}
		/*
		 * Find number of still free digits to use.
		 */
		emptyField.digitsStillFreeNumber = 0;
		for (int digit = 1; digit < 10; digit++)
			if (emptyField.digitsStillFree[digit] == DIGIT_STILL_FREE)
				emptyField.digitsStillFreeNumber++;
	}
	/**
	 * Find digits that still can be used in a given empty field
	 * @param emptyField Empty field to search still free digits for.
	 */
	private void updateDigitsStillFree(EmptyField emptyField) {
		for (int j = 0; j < BOARD_SIZE; j++)
			if (sudokuBoard[emptyField.rowIdx][j] == EMPTY_FIELD)
				findDigitsStillFree(emptyFieldsPointer[emptyField.rowIdx][j]);
		for (int i = 0; i < BOARD_SIZE; i++)
			if (sudokuBoard[i][emptyField.colIdx] == EMPTY_FIELD)
				findDigitsStillFree(emptyFieldsPointer[i][emptyField.colIdx]);
		SubSquare sub = getSubSqare(emptyField);
		for (int i = sub.rowMin; i < sub.rowMax; i++)
			for (int j = sub.colMin; j < sub.colMax; j++)
				if (sudokuBoard[i][j] == EMPTY_FIELD)
					findDigitsStillFree(emptyFieldsPointer[i][j]);
		/*
		 * Mark digits used in a sub-sqre
		 */
		for (int i = sub.rowMin; i < sub.rowMax; i++)
			for (int j = sub.colMin; j < sub.colMax; j++) {
				int boardDigit = sudokuBoard[i][j];
				if (boardDigit != EMPTY_FIELD)
					emptyField.digitsStillFree[boardDigit] = DIGIT_IN_USE;
			}
		/*
		 * Find number of still free digits to use.
		 */
		emptyField.digitsStillFreeNumber = 0;
		for (int digit = 1; digit < 10; digit++)
			if (emptyField.digitsStillFree[digit] == DIGIT_STILL_FREE)
				emptyField.digitsStillFreeNumber++;
	}
	/**
	 * Sorting empty fields list by ascending number of
	 * still free digits left that can be used in a context
	 * of a given empty field.
	 *
	 * @param l    Starting left index
	 * @param r    Starting rgth index
	 */
	private void sortEmptyFields(int l, int r) {
		int i = l;
		int j = r;
		EmptyField x;
		EmptyField w;
		x = emptyFields[(l+r)/2];
		do {
			if (randomizeEmptyFields == true) {
				/*
				 * Adding randomization
				 */
				while (emptyFields[i].digitsStillFreeNumber + emptyFields[i].randomSeed < x.digitsStillFreeNumber + x.randomSeed)
					i++;
				while (emptyFields[j].digitsStillFreeNumber + emptyFields[j].randomSeed > x.digitsStillFreeNumber + x.randomSeed)
					j--;
			} else {
				/*
				 * No randomization
				 */
				while (emptyFields[i].digitsStillFreeNumber < x.digitsStillFreeNumber)
					i++;
				while (emptyFields[j].digitsStillFreeNumber > x.digitsStillFreeNumber)
					j--;
			}
			if (i<=j)
			{
				w = emptyFields[i];
				emptyFields[i] = emptyFields[j];
				emptyFields[j] = w;
				i++;
				j--;
			}
		} while (i <= j);
		if (l < j)
			sortEmptyFields(l,j);
		if (i < r)
			sortEmptyFields(i,r);
	}
	/**
	 * Default constructor - only board initialization;
	 * @param sampleId
	 */
	public SudokuBoard() {
		boardInit();
		randomizeEmptyFields = true;
		randomizeFreeDigits = true;
		findEmptyFields();
	}
	/**
	 * Constructor - based on the Sudoku predefined example number;
	 * @param sampleId
	 */
	public SudokuBoard(int exampleNumber) {
		boardInit();
		loadSudokuExampled(exampleNumber);
	}
	/**
	 * Load Sudoku example given by the parameter exampleNumber.
	 *
	 * @param exampleNumber  Number of predefined Sudoku example
	 * @return {@value SudokuBoard#ERROR_BOARD_LOADING_FAILED} or
	 *         {@value SudokuBoard#BOARD_LOADED}
	 */
	public int loadSudokuExampled(int exampleNumber) {
		if ((exampleNumber < 1) || (exampleNumber > Store.NUMBER_OF_SUDOKU_EXAMPLES)) {
			addMessage("Loading failed - example number should be between 1 and " + Store.NUMBER_OF_SUDOKU_EXAMPLES, ERROR_MSG);
			return ERROR_BOARD_LOADING_FAILED;
		}
		if (boardState != BOARD_EMPTY)
			boardInit();
		int[][] newSudokuBoard = null;
		switch(exampleNumber) {
		case 1:
			newSudokuBoard = Store.SUDOKU_EXAMPLE_1;
			break;
		case 2:
			newSudokuBoard = Store.SUDOKU_EXAMPLE_2;
			break;
		case 3:
			newSudokuBoard = Store.SUDOKU_EXAMPLE_3;
			break;
		}
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++)
				sudokuBoard[i][j] = newSudokuBoard[i][j];
		boardState = BOARD_LOADED;
		addMessage("Sudoku example board " + exampleNumber + " loaded", NORMAL_MSG);
		return findEmptyFields();
	}
	/**
	 * Method starts solving procedure
	 */
	public int solve() {
		switch(boardState) {
		case BOARD_EMPTY:
			addMessage("Nothing to solve - the board is empty!", ERROR_MSG);
			solvingStatus = SUDOKU_SOLVING_NOT_STARTED;
			return ERROR_SUDOKU_SOLVING_NOT_STARTED;
		case BOARD_ERROR:
			addMessage("Can not start solving process - the board contains an error!", ERROR_MSG);
			solvingStatus = SUDOKU_SOLVING_NOT_STARTED;
			return ERROR_SUDOKU_SOLVING_NOT_STARTED;
		case BOARD_LOADED:
			addMessage("Can not start solving process - the board is not ready!", ERROR_MSG);
			solvingStatus = SUDOKU_SOLVING_NOT_STARTED;
			return ERROR_SUDOKU_SOLVING_NOT_STARTED;
		case BOARD_READY:
			addMessage("Starting solving process!", NORMAL_MSG);
			if (randomizeEmptyFields == true)
				addMessage(">>> Will randomize empty fields if number of still free digits is the same.", NORMAL_MSG);
			if (randomizeFreeDigits == true)
				addMessage(">>> Will randomize still free digits for a given empty field.", NORMAL_MSG);
			solvingStatus = SUDOKU_SOLVING_STARTED;
			solutionBoardEntries = new Stack<BoardEntry>();
			long solvingStartTime = DateTimeX.currentTimeMillis();
			stepsBackCounter = 0;
			solve(0);
			long solvingEndTime = DateTimeX.currentTimeMillis();
			solvingTime = (solvingEndTime - solvingStartTime) / 1000.0;
			if (solvingStatus != SUDOKU_SOLVED) {
				solvingStatus = ERROR_SUDOKU_SOLVING_FAILED;
				boardState = BOARD_ERROR;
				addMessage("Error while solving - no solutions found - setting board state as 'error' !!", ERROR_MSG);
			} else {
				addMessage("Sudoku solved !!! Entries solved: " + emptyFieldsNumber + " ... Closed routes: " + stepsBackCounter + " ... solving time: " + solvingTime + " s.", NORMAL_MSG);
				emptyFieldsNumber = 0;
			}
			return solvingStatus;
		}
		addMessage("Can not start solving process - do not know why :-(", ERROR_MSG);
		solvingStatus = SUDOKU_SOLVING_NOT_STARTED;
		return ERROR_SUDOKU_SOLVING_NOT_STARTED;
	}
	/**
	 * Recursive process of Sudoku solving
	 * @param level
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
		EmptyField emptyField;
		/**
		 * Check if solved
		 */
		if (level == emptyFieldsNumber) {
			solvingStatus = SUDOKU_SOLVED;
			solvedBoard = new int[BOARD_SIZE][BOARD_SIZE];
			for (int i = 0; i < BOARD_SIZE; i++)
				for (int j = 0; j < BOARD_SIZE; j++)
					solvedBoard[i][j] = sudokuBoard[i][j];
			return;
		}
		/**
		 * If still other fields are empty, perform recursive steps
		 */
		emptyField = emptyFields[level];
		int digitsStillFreeNumber = emptyField.digitsStillFreeNumber;
		if (digitsStillFreeNumber > 0) {
			int digitNum = 0;
			for (int digitIdx = 1; digitIdx <= 9; digitIdx++) {
				int digit = digitIdx;
				if (randomizeFreeDigits == true)
					digit = emptyField.digitsRandomSeed[digitIdx].digit;
				if (emptyField.digitsStillFree[digit] == DIGIT_STILL_FREE) {
					digitNum++;
					sudokuBoard[emptyField.rowIdx][emptyField.colIdx] = digit;
					if (level + 1 < emptyFieldsNumber - 1)
						sortEmptyFields(level+1, emptyFieldsNumber-1);
					solutionBoardEntries.push( new BoardEntry(emptyField.rowIdx, emptyField.colIdx, digit) );
					updateDigitsStillFree(emptyField);
					solve(level + 1);
					if (solvingStatus == SUDOKU_SOLVING_STARTED) {
						solutionBoardEntries.pop();
						if (digitNum == digitsStillFreeNumber) {
							sudokuBoard[emptyField.rowIdx][emptyField.colIdx] = EMPTY_FIELD;
							updateDigitsStillFree(emptyField);
							if (level < emptyFieldsNumber - 1)
								sortEmptyFields(level, emptyFieldsNumber-1);
							stepsBackCounter++;
						}
					} else
						return;
				}
			}
		} else {
			sudokuBoard[emptyField.rowIdx][emptyField.colIdx] = EMPTY_FIELD;
			updateDigitsStillFree(emptyField);
		}
	}
	/**
	 * Message builder;
	 * @param msg Message
	 */
	private void addMessage(String msg, int msgType) {
		String vdt = "[" + SUDOKU_SOLVER_NAME + "-v." + SUDOKU_SOLVER_VERSION + "][" + DateTimeX.getCurrDateTimeStr() + "]";
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
	 * @return  {@value SudokuBoard#BOARD_READY} or
	 *          {@value SudokuBoard#BOARD_EMPTY} or
	 *          {@value SudokuBoard#BBOARD_ERROR}
	 */
	public int getBoardState() {
		return boardState;
	}
	/**
	 * Return current solving status.
	 * @return  {@value SudokuBoard#SUDOKU_SOLVING_NOT_STARTED} or
	 *          {@value SudokuBoard#ERROR_SUDOKU_SOLVING_FAILED} or
	 *          {@value SudokuBoard#SUDOKU_SOLVED}
	 */
	public int getSolvingStatus() {
		return solvingStatus;
	}
	/**
	 * Get all current board entries
	 * @return  Array of current board entries.
	 */
	public BoardEntry[] getAllBoardEntries() {
		BoardEntry[] boardEntries = new BoardEntry[BOARD_FIELDS_NUMBER];
		int entryIdx = 0;
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; i < BOARD_SIZE; j++) {
				boardEntries[entryIdx] = new BoardEntry(i, j, sudokuBoard[i][j]);
				entryIdx++;
			}
		return boardEntries;
	}
	/**
	 * Return solution board entries keeping the solution
	 * path order. If error was encountered while solving
	 * path to the solution will be incomplete, but will show
	 * where solving process was aborted.
	 *
	 * @return Array of board entries that lead to the solution (keeping
	 *         the path order).
	 */
	public BoardEntry[] getSolutionBoardEntries() {
		if (solutionBoardEntries == null) return null;
		if (solutionBoardEntries.size() == 0) return null;
		return ArrayX.toArray(BoardEntry.class, solutionBoardEntries);
	}
	/**
	 * Return solving time in seconds.
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
		return stepsBackCounter;
	}
	/**
	 * By default random seed on empty fields is enabled. This parameter
	 * affects solving process only. Random seed on
	 * empty fields causes randomization on empty fields
	 * within empty fields with the same number of still free digits.
	 * Enabling extends ability to find different solutions, if exists.
	 */
	public void enableRndSeedOnEmptyFields() {
		randomizeEmptyFields = true;
	}
	/**
	 * By default random seed on empty fields is enabled. This parameter
	 * affects solving process only. Random seed on
	 * empty fields causes randomization on empty fields
	 * within empty fields with the same number of still free digits.
	 * Disabling limits ability to find different solutions, if exists.
	 */
	public void disableRndSeedOnEmptyFields() {
		randomizeEmptyFields = false;
	}
	/**
	 * By default random seed on free digits is enabled. This parameter
	 * affects solving process only. Random seed on
	 * free digits causes randomization on accessing free digits
	 * for a given empty fields. Each free digits is a starting point
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
	 * for a given empty fields. Each free digits is a starting point
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
	public String boardStateToString() {
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
		boardStateStr = boardStateStr + "\n" + "Initial empty fields: " + emptyFieldsNumber;
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
		case ERROR_SUDOKU_SOLVING_FAILED:
			boardStateStr = boardStateStr + "failed";
			break;
		}
		return boardStateStr;
	}
	/**
	 * Returns string board and empty fields representation.
	 * @return Board and empty fields representation.
	 */
	public String boardAndEmptyFieldsToString() {
		String boardStr = "    Sudoku board           Number of free digits\n";
		boardStr = boardStr + "=====================      =====================\n";
		for (int i = 0; i < BOARD_SIZE; i ++) {
			if ((i > 0) && (i < BOARD_SIZE) && (i % SUB_SQURE_SIZE == 0))
				boardStr = boardStr + "---------------------      ---------------------\n" ;
			for (int j = 0; j < BOARD_SIZE; j++) {
				if ((j > 0) && (j < BOARD_SIZE) && (j % SUB_SQURE_SIZE == 0))
					boardStr = boardStr + "| ";
				if (sudokuBoard[i][j] != EMPTY_FIELD)
					boardStr = boardStr + sudokuBoard[i][j] + " ";
				else
					boardStr = boardStr + ". ";
			}
			boardStr = boardStr + "     ";
			for (int j = 0; j < BOARD_SIZE; j++) {
				if ((j > 0) && (j < BOARD_SIZE) && (j % SUB_SQURE_SIZE == 0))
					boardStr = boardStr + "| ";
				if (sudokuBoard[i][j] == EMPTY_FIELD)
					boardStr = boardStr + emptyFieldsPointer[i][j].digitsStillFreeNumber + " ";
				else
					boardStr = boardStr + ". ";
			}
			boardStr = boardStr + "\n";
		}
		boardStr = boardStr + "=====================      =====================\n";
		boardStr = boardStr + boardStateToString() + "\n";
		return boardStr;
	}
	/**
	 * Returns string board (only) representation
	 * @return Board (only) representation.
	 */
	public String boardToString() {
		String boardStr = "    Sudoku board           Number of free digits\n";
		boardStr = boardStr + "=====================\n";
		for (int i = 0; i < BOARD_SIZE; i ++) {
			if ((i > 0) && (i < BOARD_SIZE) && (i % SUB_SQURE_SIZE == 0))
				boardStr = boardStr + "---------------------\n" ;
			for (int j = 0; j < BOARD_SIZE; j++) {
				if ((j > 0) && (j < BOARD_SIZE) && (j % SUB_SQURE_SIZE == 0))
					boardStr = boardStr + "| ";
				if (sudokuBoard[i][j] != EMPTY_FIELD)
					boardStr = boardStr + sudokuBoard[i][j] + " ";
				else
					boardStr = boardStr + ". ";
			}
			boardStr = boardStr + "\n";
		}
		boardStr = boardStr + "=====================\n";
		boardStr = boardStr + boardStateToString() + "\n";
		return boardStr;
	}
	/**
	 * Returns string empty fields (only) representation.
	 * @return Empty fields (only) representation.
	 */
	public String emptyFieldsToString() {
		String boardStr = "Number of free digits\n";
		boardStr = boardStr + "=====================\n";
		for (int i = 0; i < BOARD_SIZE; i ++) {
			if ((i > 0) && (i < BOARD_SIZE) && (i % SUB_SQURE_SIZE == 0))
				boardStr = boardStr + "---------------------\n" ;
			for (int j = 0; j < BOARD_SIZE; j++) {
				if ((j > 0) && (j < BOARD_SIZE) && (j % SUB_SQURE_SIZE == 0))
					boardStr = boardStr + "| ";
				if (sudokuBoard[i][j] == EMPTY_FIELD)
					boardStr = boardStr + emptyFieldsPointer[i][j].digitsStillFreeNumber + " ";
				else
					boardStr = boardStr + ". ";
			}
			boardStr = boardStr + "\n";
		}
		boardStr = boardStr + "=====================\n";
		boardStr = boardStr + boardStateToString() + "\n";
		return boardStr;
	}
}