/*
 * @(#)Store.java        0.0.1    2016-02-01
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

import org.mariuszgromada.janetutils.io.FileX;
/**
 * Storehouse for various things used in library, i.e. sudoku board examples.
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
public final class SudokuStore {
	/**
	 * Sudoku solver version.
	 */
	public static final String JANET_SUDOKU_VERSION = "0.0.1";
	/**
	 * Sudoku solver name.
	 */
	public static final String JANET_SUDOKU_NAME = "Janet-Sudoku";
	/**
	 * Sudoku example - number 1.
	 */
	public static final int[][] SUDOKU_EXAMPLE_1 = {
            {5,3,0, 0,7,0, 0,0,0},
            {6,0,0, 1,9,5, 0,0,0},
            {0,9,8, 0,0,0, 0,6,0},

            {8,0,0, 0,6,0, 0,0,3},
            {4,0,0, 8,0,3, 0,0,1},
            {7,0,0, 0,2,0, 0,0,6},

            {0,6,0, 0,0,0, 2,8,0},
            {0,0,0, 4,1,9, 0,0,5},
            {0,0,0, 0,8,0, 0,7,9}
		};
	/**
	 * Sudoku example - number 2.
	 */
	public static final int[][] SUDOKU_EXAMPLE_2 = {
            {0,0,9, 1,0,8, 0,4,0},
            {0,4,0, 6,0,5, 7,0,8},
            {0,0,0, 0,0,0, 0,1,0},

            {8,0,0, 3,0,0, 0,7,0},
            {1,0,5, 7,0,2, 4,0,9},
            {0,9,0, 0,0,1, 0,0,2},

            {0,6,0, 0,0,0, 0,0,0},
            {9,0,3, 8,0,4, 0,2,0},
            {0,5,0, 2,0,6, 1,0,0},
		};
	/**
	 * Sudoku example - number 3.
	 */
	public static final int[][] SUDOKU_EXAMPLE_3 = {
            {3,0,0, 0,2,9, 0,0,5},
            {5,9,2, 0,3,8, 1,0,0},
            {0,7,8, 0,6,5, 3,9,2},

            {0,0,0, 9,0,0, 0,0,0},
            {0,0,1, 8,0,3, 9,0,0},
            {0,0,9, 0,0,4, 0,0,0},

            {8,0,0, 5,9,0, 4,2,3},
            {0,0,3, 0,8,0, 5,7,9},
            {9,0,0, 3,4,0, 0,0,1},
		};
	/**
	 * Number of Sudoku examples available in Store.
	 */
	public static final int NUMBER_OF_SUDOKU_EXAMPLES = 3;
	/**
	 * Board size derived form SudokuBoard class.
	 */
	private static final int BOARD_SIZE = SudokuBoard.BOARD_SIZE;
	/**
	 * Gets Sudoku example for the Sudoku Store.
	 * @param exampleNumber     Example number.
	 * @return                  Sudoku example is exists, otherwise null.
	 * @see SudokuStore#NUMBER_OF_SUDOKU_EXAMPLES
	 */
	public static final int[][] getSudokuExample(int exampleNumber) {
		switch(exampleNumber) {
		case 1: return SUDOKU_EXAMPLE_1;
		case 2: return SUDOKU_EXAMPLE_2;
		case 3: return SUDOKU_EXAMPLE_3;
		}
		return null;
	}
	/**
	 * Loads Sudoku board form text file.
	 *
	 * Format:
	 * Any character different than '1-9' and '.' is being removed.
	 * Any line starting with '#' is being removed.
	 * Any empty line is being removed.
	 * Any final line having less than 9 characters is being removed.
	 *
	 * If number of final lines is less then 9 then null is returned.
	 *
	 * Finally 9 starting characters for first 9 lines is the
	 * loaded board definition.
	 *
	 * @param filePath  Path to the file with Sudoku board definition.
	 * @return  Array representing loaded Sudoku board,
	 *          null - if problem occurred while loading.
	 */
	public static final int[][] loadBoard(String filePath) {
		ArrayList<String> fileLines = FileX.readFileLines2ArraList(filePath);
		ArrayList<String> sudokuRows = new ArrayList<String>();
		if (fileLines == null) return null;
		if (fileLines.size() < BOARD_SIZE) return null;
		for (String line : fileLines) {
			if (line.length() > 0) {
				if (line.charAt(0) != '#') {
					String sudokuRow = "";
					for (int j = 0; j < line.length(); j++) {
						char c = line.charAt(j);
						if (
							(c == '1') ||
							(c == '2') ||
							(c == '3') ||
							(c == '4') ||
							(c == '5') ||
							(c == '6') ||
							(c == '7') ||
							(c == '8') ||
							(c == '9') ||
							(c == '.')
						) sudokuRow = sudokuRow + c;
					}
					if (sudokuRow.length() >= BOARD_SIZE)
						sudokuRows.add(sudokuRow.substring(0, BOARD_SIZE));
				}
			}
		}
		if (sudokuRows.size() < BOARD_SIZE) return null;
		int[][] sudokuBoard = new int[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			String sudokuRow = sudokuRows.get(i);
			for (int j = 0; j < BOARD_SIZE; j++) {
				char c = sudokuRow.charAt(j);
				int d = BoardCell.EMPTY;
				if  (c == '1') d = 1;
				else if  (c == '2') d = 2;
				else if  (c == '3') d = 3;
				else if  (c == '4') d = 4;
				else if  (c == '5') d = 5;
				else if  (c == '6') d = 6;
				else if  (c == '7') d = 7;
				else if  (c == '8') d = 8;
				else if  (c == '9') d = 9;
				sudokuBoard[i][j] = d;
			}
		}
		return sudokuBoard;
	}
	/**
	 * Clockwise rotation of Sudoku board.
	 *
	 * @param sudokuBoard Array representing Sudoku board.
	 * @return Clockwise rotated sudoku board.
	 */
	public static final int[][] rotateClockWise(int[][] sudokuBoard) {
		int[][] rotatedBoard = new int[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i< BOARD_SIZE; i++) {
			int newColIndex = BOARD_SIZE - i - 1;
			for (int j = 0; j < BOARD_SIZE; j++)
				rotatedBoard[j][newColIndex] = sudokuBoard[i][j];
		}
		return rotatedBoard;
	}
	/**
	 * Counterclockwise rotation of Sudoku board.
	 *
	 * @param sudokuBoard Array representing Sudoku board.
	 * @return Clockwise rotated Sudoku board.
	 */
	public static final int[][] rotateCounterclockWise(int[][] sudokuBoard) {
		int[][] rotatedBoard = new int[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i< BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				int newRowsIndex = BOARD_SIZE - j -1;
				rotatedBoard[newRowsIndex][j] = sudokuBoard[i][j];
			}
		}
		return rotatedBoard;
	}
	/**
	 * Returns string board (only) representation.
	 * @param  sudokuBoard   Array representing Sudoku puzzles.
	 * @return Board (only) representation.
	 */
	public static final String boardToString(int[][] sudokuBoard) {
		String boardStr = "    Sudoku board\n";
		boardStr = boardStr + "=====================\n";
		for (int i = 0; i < SudokuBoard.BOARD_SIZE; i ++) {
			if ((i > 0) && (i < SudokuBoard.BOARD_SIZE) && (i % SudokuBoard.BOARD_SUB_SQURE_SIZE == 0))
				boardStr = boardStr + "---------------------\n" ;
			for (int j = 0; j < SudokuBoard.BOARD_SIZE; j++) {
				if ((j > 0) && (j < SudokuBoard.BOARD_SIZE) && (j % SudokuBoard.BOARD_SUB_SQURE_SIZE == 0))
					boardStr = boardStr + "| ";
				if (sudokuBoard[i][j] != BoardCell.EMPTY)
					boardStr = boardStr + sudokuBoard[i][j] + " ";
				else
					boardStr = boardStr + ". ";
			}
			boardStr = boardStr + "\n";
		}
		boardStr = boardStr + "=====================\n";
		return boardStr;
	}
	/**
	 * Returns string representation of empty cells (only).
	 * @param  emptyCells  Array representing empty cells of Sudoku puzzles.
	 * @return Empty cells (only) string representation.
	 */
	public static final String emptyCellsToString(int[][] emptyCells) {
		String boardStr = "Number of free digits\n";
		boardStr = boardStr + "=====================\n";
		for (int i = 0; i < SudokuBoard.BOARD_SIZE; i ++) {
			if ((i > 0) && (i < SudokuBoard.BOARD_SIZE) && (i % SudokuBoard.BOARD_SUB_SQURE_SIZE == 0))
				boardStr = boardStr + "---------------------\n" ;
			for (int j = 0; j < SudokuBoard.BOARD_SIZE; j++) {
				if ((j > 0) && (j < SudokuBoard.BOARD_SIZE) && (j % SudokuBoard.BOARD_SUB_SQURE_SIZE == 0))
					boardStr = boardStr + "| ";
				if (emptyCells[i][j] > 0)
					boardStr = boardStr + emptyCells[i][j] + " ";
				else
					boardStr = boardStr + ". ";
			}
			boardStr = boardStr + "\n";
		}
		boardStr = boardStr + "=====================\n";
		return boardStr;
	}
	/**
	 * Returns string board and empty cells representation.
	 * @param  sudokuBoard   Array representing Sudoku puzzles.
	 * @param  emptyCells    Array representing empty cells of Sudoku puzzles.
	 * @return Board and empty cells representation.
	 */
	public static final String boardAndEmptyCellsToString(int[][] sudokuBoard, int[][] emptyCells) {
		String boardStr = "    Sudoku board           Number of free digits\n";
		boardStr = boardStr + "=====================      =====================\n";
		for (int i = 0; i < SudokuBoard.BOARD_SIZE; i ++) {
			if ((i > 0) && (i < SudokuBoard.BOARD_SIZE) && (i % SudokuBoard.BOARD_SUB_SQURE_SIZE == 0))
				boardStr = boardStr + "---------------------      ---------------------\n" ;
			for (int j = 0; j < SudokuBoard.BOARD_SIZE; j++) {
				if ((j > 0) && (j < SudokuBoard.BOARD_SIZE) && (j % SudokuBoard.BOARD_SUB_SQURE_SIZE == 0))
					boardStr = boardStr + "| ";
				if (sudokuBoard[i][j] != BoardCell.EMPTY)
					boardStr = boardStr + sudokuBoard[i][j] + " ";
				else
					boardStr = boardStr + ". ";
			}
			boardStr = boardStr + "     ";
			for (int j = 0; j < SudokuBoard.BOARD_SIZE; j++) {
				if ((j > 0) && (j < SudokuBoard.BOARD_SIZE) && (j % SudokuBoard.BOARD_SUB_SQURE_SIZE == 0))
					boardStr = boardStr + "| ";
				if (emptyCells[i][j] > 0)
					boardStr = boardStr + emptyCells[i][j] + " ";
				else
					boardStr = boardStr + ". ";
			}
			boardStr = boardStr + "\n";
		}
		boardStr = boardStr + "=====================      =====================\n";
		return boardStr;
	}
	/**
	 * Returns string representation of path to the solution.
	 * @param solutionBoardCells  Array representing sequence of board cells.
	 * @return                      String representation of sequence of board cells.
	 */
	public static final String solutionPathToString(BoardCell[] solutionBoardCells) {
		String solutionPath = "";
		solutionPath = solutionPath + " --------------- \n";
		solutionPath = solutionPath + "| id | i, j | d |\n";
		solutionPath = solutionPath + "|----|----- |---|\n";
		if (solutionBoardCells != null)
			for (int i = 0; i < solutionBoardCells.length; i++) {
				BoardCell b = solutionBoardCells[i];
				if (i + 1 < 10) solutionPath = solutionPath + "|  ";
				else solutionPath = solutionPath + "| ";
				solutionPath = solutionPath + (i+1) + " | " + (b.rowIndex+1) + ", " + (b.colIndex + 1) + " | " + b.digit + " |\n";
			}
		solutionPath = solutionPath + " --------------- \n";
		return solutionPath;
	}
}
/**
 * Digit random seed data type.
 */
class DigitRandomSeed {
	int digit;
	double randomSeed;
}
/**
 * Package level class describing empty cell.
 */
class EmptyCell {
	/**
	 * Empty cell id.
	 */
	public static final int CELL_ID = 0;
	/**
	 * Empty cell row number.
	 */
	int rowIndex;
	/**
	 * Empty cell column number.
	 */
	int colIndex;
	/**
	 * List of digits than still can be used.
	 */
	int[] digitsStillFree;
	/**
	 * Random seed for randomized accessing digits still free.
	 */
	DigitRandomSeed[] digitsRandomSeed;
	/**
	 * Number of digits than still can be used.
	 */
	int digitsStillFreeNumber;
	/**
	 * Default constructor.
	 */
	/**
	 * Random seed for randomized accessing empty cells.
	 */
	double randomSeed;
	/**
	 * Default constructor.
	 */
	public EmptyCell() {
		rowIndex = BoardCell.INDEX_NULL;
		colIndex = BoardCell.INDEX_NULL;
		digitsStillFree = new int[SudokuBoard.BOARD_MAX_INDEX];
		digitsRandomSeed = new DigitRandomSeed[SudokuBoard.BOARD_MAX_INDEX];
		for (int i = 0; i < SudokuBoard.BOARD_MAX_INDEX; i++) {
			digitsRandomSeed[i] = new DigitRandomSeed();
			digitsRandomSeed[i].digit = i;
			digitsRandomSeed[i].randomSeed = Math.random();
		}
		sortDigitsRandomSeed(1, SudokuBoard.BOARD_SIZE);
		randomSeed = Math.random();
		setAllDigitsStillFree();
	}
	/**
	 * Sorting digits  by random seed.
	 *
	 * @param l    Starting left index.
	 * @param r    Starting right index.
	 */
	private void sortDigitsRandomSeed(int l, int r) {
		int i = l;
		int j = r;
		DigitRandomSeed x;
		DigitRandomSeed w;
		x = digitsRandomSeed[(l+r)/2];
		do {
			while (digitsRandomSeed[i].randomSeed < x.randomSeed)
				i++;
			while (digitsRandomSeed[j].randomSeed > x.randomSeed)
					j--;
			if (i<=j)
			{
				w = digitsRandomSeed[i];
				digitsRandomSeed[i] = digitsRandomSeed[j];
				digitsRandomSeed[j] = w;
				i++;
				j--;
			}
		} while (i <= j);
		if (l < j)
			sortDigitsRandomSeed(l,j);
		if (i < r)
			sortDigitsRandomSeed(i,r);
	}
	/**
	 * All digits are set that can be used in the specified filed
	 * of the board.
	 */
	public void setAllDigitsStillFree() {
		for (int i = 0; i < SudokuBoard.BOARD_MAX_INDEX; i++) {
			digitsStillFree[i] = SudokuSolver.DIGIT_STILL_FREE;
		}
		digitsStillFreeNumber = 0;
	}
}
/**
 * Data type for sub-square definition
 * on the Sudoku board.
 */
class SubSquare {
	/**
	 * Left top - row index.
	 */
	int rowMin;
	/**
	 * Right bottom - row index.
	 */
	int rowMax;
	/**
	 * Left top - column index.
	 */
	int colMin;
	/**
	 * Right bottom - column index.
	 */
	int colMax;
}
