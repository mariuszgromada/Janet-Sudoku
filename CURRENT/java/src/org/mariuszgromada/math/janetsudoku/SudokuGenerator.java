/*
 * @(#)SudokuGenerator.java        0.0.1    2016-02-01
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

import org.mariuszgromada.janetutils.DateTimeX;

/**
 * Sudoku generator.
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
public class SudokuGenerator {
	/**
	 * Board size derived form SudokuBoard class.
	 */
	private static final int BOARD_SIZE = SudokuBoard.BOARD_SIZE;
	/**
	 * Board cells number derived form SudokuBoard class.
	 */
	private static final int BOARD_CELLS_NUMBER = SudokuBoard.BOARD_CELLS_NUMBER;
	/**
	 * Empty cell identifier.
	 */
	private static final int CELL_EMPTY = BoardCell.EMPTY;
	/**
	 * Marker if analyzed digit 0...9 is still not used.
	 */
	static final int DIGIT_STILL_FREE = SudokuSolver.DIGIT_STILL_FREE;
	/**
	 * Digit 0...9 can not be used in that place.
	 */
	static final int DIGIT_IN_USE = SudokuSolver.DIGIT_IN_USE;
	/**
	 * If yes then empty cells with the same number of
	 * still free digits will be randomized.
	 */
	private boolean randomizeEmptyCells;
	/**
	 * Solved board that will be a basis for
	 * the generation process
	 */
	int [][] solvedBoard;
	/**
	 * Board cells array
	 */
	BoardCell[] boardCells;
	/**
	 * Message type normal.
	 */
	private static final int MSG_INFO = 1;
	/**
	 * Message type error.
	 */
	private static final int MSG_ERROR = -1;
	/**
	 * Message from the solver.
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
	/**
	 * Solving time in seconds.
	 */
	private double computingTime;
	/**
	 * Generator state.
	 *
	 * @see #GENERATOR_INIT_STARTED
	 * @see #GENERATOR_INIT_FINISHED
	 * @see #GENERATOR_INIT_FAILED
	 * @see #GENERATOR_GEN_NOT_STARTED
	 * @see #GENERATOR_GEN_STARTED
	 * @see #GENERATOR_GEN_STARTED
	 * @see #GENERATOR_GEN_FAILED
	 */
	private int generatorState;
	/**
	 * Generator init started
	 * @see #getGeneratorState()
	 */
	public static final int GENERATOR_INIT_STARTED = 1;
	/**
	 * Generator init finished.
	 * @see #getGeneratorState()
	 */
	public static final int GENERATOR_INIT_FINISHED = 2;
	/**
	 * Generator init failed.
	 * @see #getGeneratorState()
	 */
	public static final int GENERATOR_INIT_FAILED = -1;
	/**
	 * Generation process not started.
	 * @see #getGeneratorState()
	 */
	public static final int GENERATOR_GEN_NOT_STARTED = -2;
	/**
	 * Generation process started.
	 * @see #getGeneratorState()
	 */
	public static final int GENERATOR_GEN_STARTED = 3;
	/**
	 * Generation process finished.
	 * @see #getGeneratorState()
	 */
	public static final int GENERATOR_GEN_FINISHED = 4;
	/**
	 * Generation process failed.
	 * @see #getGeneratorState()
	 */
	public static final int GENERATOR_GEN_FAILED = -3;
	/**
	 * Internal variables init for constructor.
	 */
	private void initInternalVars() {
		generatorState = GENERATOR_INIT_STARTED;
		randomizeEmptyCells = true;
		computingTime = 0;
	}
	/**
	 * Default constructor based on random sudoku puzzle example.
	 */
	public SudokuGenerator() {
		initInternalVars();
		int example = SudokuStore.randomNumber( SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES );
		SudokuSolver puzzle = new SudokuSolver(example);
		puzzle.solve();
		solvedBoard = SudokuStore.seqOfRandomBoardTransf(puzzle.solvedBoard);
		generatorState = GENERATOR_INIT_FINISHED;
		addMessage("Generator initiated using sequance of random transformation on the example number: " + example + ".", MSG_INFO);
	}
	/**
	 * Default constructor based on puzzle example.
	 */
	public SudokuGenerator(int example) {
		initInternalVars();
		if ( (example >= 1) && (example <= SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES) ) {
			SudokuSolver puzzle = new SudokuSolver(example);
			puzzle.solve();
			solvedBoard = SudokuStore.seqOfRandomBoardTransf(puzzle.solvedBoard);
			generatorState = GENERATOR_INIT_FINISHED;
			addMessage("Generator initiated using sequance of random transformation on the example number: " + example + ".", MSG_INFO);
		} else {
			generatorState = GENERATOR_INIT_FAILED;
			addMessage("Generator not initiated incorrect example number: " + example + " - should be between 1 and " + SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES + ".", MSG_ERROR);
		}
	}
	/**
	 * Default constructor based on provided solved board.
	 */
	public SudokuGenerator(int[][] solvedBoard) {
		initInternalVars();
		if (solvedBoard == null) {
			generatorState = GENERATOR_INIT_FAILED;
			addMessage("Generator not initiated - null solved board.", MSG_ERROR);
		} else if (solvedBoard.length != BOARD_SIZE) {
			generatorState = GENERATOR_INIT_FAILED;
			addMessage("Generator not initiated - incorrect number of rows in solved board, is: " + solvedBoard.length + ",  expected: " + BOARD_SIZE + ".", MSG_ERROR);
		} else if (solvedBoard[0].length != BOARD_SIZE) {
			generatorState = GENERATOR_INIT_FAILED;
			addMessage("Generator not initiated - incorrect number of columns in solved board, is: " + solvedBoard[0].length + ", expected: " + BOARD_SIZE + ".", MSG_ERROR);
		} else if (SudokuStore.checkSolvedBoard(solvedBoard) == false) {
			generatorState = GENERATOR_INIT_FAILED;
			addMessage("Generator not initiated - solved board contains an error.", MSG_ERROR);
		} else {
			this.solvedBoard = solvedBoard;
			generatorState = GENERATOR_INIT_FINISHED;
			addMessage("Generator initiated using provided solved board.", MSG_INFO);
		}
	}
	/**
	 * Sudoku puzzle generator.
	 *
	 * @return   Sudoku puzzle if process finished correctly, otherwise null.
	 */
	public int[][] generate() {
		if (generatorState != GENERATOR_INIT_FINISHED) {
			generatorState = GENERATOR_GEN_NOT_STARTED;
			addMessage("Generation process not started due to incorrect initialization.", MSG_ERROR);
			return null;
		}
		long solvingStartTime = DateTimeX.currentTimeMillis();
		generatorState = GENERATOR_GEN_STARTED;
		addMessage("Generation process started.", MSG_INFO);
		boardCells = new BoardCell[BOARD_CELLS_NUMBER];
		int cellIndex = 0;
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++) {
			boardCells[cellIndex] = new BoardCell(i, j, solvedBoard[i][j]);
			cellIndex++;
		}
		int filledCells = BOARD_CELLS_NUMBER;
		updateDigitsStillFreeCounts();
		sortBoardCells(0,filledCells-1);
		int n = 0;
		do {
			int r = 0;
			int i = boardCells[r].rowIndex;
			int j = boardCells[r].colIndex;
			int d = solvedBoard[i][j];
			solvedBoard[i][j] = CELL_EMPTY;
			SudokuSolver s = new SudokuSolver(solvedBoard);
			n = s.findAllSolutions();
			if (n != 1)
				solvedBoard[i][j] = d;
			int lastIndex = filledCells-1;
			if (r < lastIndex) {
				BoardCell b1 = boardCells[r];
				BoardCell b2 = boardCells[lastIndex];
				boardCells[lastIndex] = b1;
				boardCells[r] = b2;
			}
			filledCells--;
			updateDigitsStillFreeCounts();
			if (filledCells > 0) sortBoardCells(0,filledCells-1);
		} while (filledCells > 0);
		long solvingEndTime = DateTimeX.currentTimeMillis();
		computingTime = (solvingEndTime - solvingStartTime) / 1000.0;
		generatorState = GENERATOR_GEN_FINISHED;
		addMessage("Generation process finished, computing time: " + computingTime + " s.", MSG_INFO);
		return solvedBoard;
	}
	/**
	 * Updating digits still free for a specific cell
	 * while generating process.
	 */
	private void updateDigitsStillFreeCounts() {
		for (int i = 0; i < BOARD_CELLS_NUMBER; i++)
			countDigitsStillFree(boardCells[i]);
	}
	/**
	 * Counts number of digits still free
	 * for a specific cell.
	 *
	 * @param boardCell
	 */
	private void countDigitsStillFree(BoardCell boardCell) {
		int[] digitsStillFree = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int emptyCellsNumber = 0;
		for (int j = 0; j < BOARD_SIZE; j++) {
			int boardDigit = solvedBoard[boardCell.rowIndex][j];
			if (boardDigit != CELL_EMPTY)
				digitsStillFree[boardDigit] = DIGIT_IN_USE;
			else if (j != boardCell.colIndex)
				emptyCellsNumber++;
		}
		for (int i = 0; i < BOARD_SIZE; i++) {
			int boardDigit = solvedBoard[i][boardCell.colIndex];
			if (boardDigit != CELL_EMPTY)
				digitsStillFree[boardDigit] = DIGIT_IN_USE;
			else if (i != boardCell.rowIndex)
				emptyCellsNumber++;
		}
		SubSquare sub = SubSquare.getSubSqare(boardCell);
		/*
		 * Mark digits used in a sub-square.
		 */
		for (int i = sub.rowMin; i < sub.rowMax; i++)
			for (int j = sub.colMin; j < sub.colMax; j++) {
				int boardDigit = solvedBoard[i][j];
				if (boardDigit != CELL_EMPTY)
					digitsStillFree[boardDigit] = DIGIT_IN_USE;
				else if ((i != boardCell.rowIndex) && (j != boardCell.colIndex))
					emptyCellsNumber++;
			}
		/*
		 * Find number of still free digits to use.
		 */
		digitsStillFree[boardCell.digit] = 0;
		boardCell.digitsStillFreeNumber = emptyCellsNumber;
		for (int digit = 1; digit < 10; digit++)
			if (digitsStillFree[digit] == DIGIT_STILL_FREE)
				boardCell.digitsStillFreeNumber++;
	}
	/**
	 * Sorting board cells
	 * @param l  First index
	 * @param r  Last index
	 */
	private void sortBoardCells(int l, int r) {
		int i = l;
		int j = r;
		BoardCell x;
		BoardCell w;
		x = boardCells[(l+r)/2];
		do {
			if (randomizeEmptyCells == true) {
				/*
				 * Adding randomization
				 */
				while ( boardCells[i].orderPlusRndSeed() < x.orderPlusRndSeed() )
					i++;
				while ( boardCells[j].orderPlusRndSeed() > x.orderPlusRndSeed() )
					j--;
			} else {
				/*
				 * No randomization
				 */
				while ( boardCells[i].order() < x.order() )
					i++;
				while ( boardCells[j].order() > x.order() )
					j--;
			}
			if (i<=j)
			{
				w = boardCells[i];
				boardCells[i] = boardCells[j];
				boardCells[j] = w;
				i++;
				j--;
			}
		} while (i <= j);
		if (l < j)
			sortBoardCells(l,j);
		if (i < r)
			sortBoardCells(i,r);
	}
	/**
	 * By default random seed on empty cells is enabled. This parameter
	 * affects generating process only. Random seed on
	 * empty cells causes randomization on empty cells
	 * within empty cells with the same number of still free digits.
	 */
	public void enableRndSeedOnEmptyCells() {
		randomizeEmptyCells = true;
	}
	/**
	 * By default random seed on empty cells is enabled. This parameter
	 * affects generating process only. Random seed on
	 * empty cells causes randomization on empty cells
	 * within empty cells with the same number of still free digits.
	 */
	public void disableRndSeedOnEmptyCells() {
		randomizeEmptyCells = false;
	}
	/**
	 * Message builder.
	 * @param msg Message.
	 */
	private void addMessage(String msg, int msgType) {
		String vdt = "[" + SudokuStore.JANET_SUDOKU_NAME + "-v." + SudokuStore.JANET_SUDOKU_VERSION + "][" + DateTimeX.getCurrDateTimeStr() + "]";
		String mt = "(msg)";
		if (msgType == MSG_ERROR) {
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
	 * Return solving time in seconds..
	 * @return  Solving time in seconds.
	 */
	public double getComputingTime() {
		return computingTime;
	}
	/**
	 * Return current state of the generator
	 * @return  {@link #GENERATOR_INIT_STARTED} or
	 *          {@link #GENERATOR_INIT_FINISHED} or
	 *          {@link #GENERATOR_INIT_FAILED} or
	 *          {@link #GENERATOR_GEN_NOT_STARTED} or
	 *          {@link #GENERATOR_GEN_STARTED} or
	 *          {@link #GENERATOR_GEN_FINISHED} or
	 *          {@link #GENERATOR_GEN_FAILED}.
	 */
	public int getGeneratorState() {
		return generatorState;
	}
}
