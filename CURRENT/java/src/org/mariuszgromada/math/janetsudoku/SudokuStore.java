/*
 * @(#)SudokuStore.java        1.0.0    2016-03-19
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

import org.mariuszgromada.janetutils.ArrayX;
import org.mariuszgromada.janetutils.io.FileX;
import org.mariuszgromada.janetutils.DateTimeX;

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
 * @version        1.0.0
 */
public final class SudokuStore {
	/**
	 * Sudoku solver version.
	 */
	public static final String JANET_SUDOKU_VERSION = "1.0.0";
	/**
	 * Sudoku solver name.
	 */
	public static final String JANET_SUDOKU_NAME = "Janet-Sudoku";
	/**
	 * Number of Sudoku puzzles examples.
	 *
	 * @see SudokuPuzzles
	 * @see SudokuPuzzles#NUMBER_OF_PUZZLE_EXAMPLES
	 */
	public static final int NUMBER_OF_PUZZLE_EXAMPLES = SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES;
	/**
	 * Board size derived form SudokuBoard class.
	 */
	private static final int BOARD_SIZE = SudokuBoard.BOARD_SIZE;
	/**
	 * Sudoku board sub-square size derived from SudokuBoard class.
	 */
	private static final int BOARD_SUB_SQURE_SIZE = SudokuBoard.BOARD_SUB_SQURE_SIZE;
	/**
	 * Number of 9x3 column segments or 3x9 row segments.
	 * derived from SudokuBoard class.
	 */
	private static final int BOARD_SEGMENTS_NUMBER = SudokuBoard.BOARD_SEGMENTS_NUMBER;
	/**
	 * Number of available random board transformations.
	 *
	 * @see SudokuStore#randomBoardTransf(int[][]);
	 * @see SudokuStore#seqOfRandomBoardTransf(int[][]);
	 * @see SudokuStore#seqOfRandomBoardTransf(int[][], int);
	 */
	public static final int AVAILABLE_RND_BOARD_TRANSF = 17;
	/**
	 * Default sequence length of random board transformations.
	 *
	 * @see SudokuStore#randomBoardTransf(int[][]);
	 * @see SudokuStore#seqOfRandomBoardTransf(int[][]);
	 * @see SudokuStore#seqOfRandomBoardTransf(int[][], int);
	 */
	public static final int DEFAULT_RND_TRANSF_SEQ_LENGTH = 1000;
	/**
	 * Threads number.
	 */
	private static final int THREADS_NUMBER = Runtime.getRuntime().availableProcessors();
	/**
	 * Default number of iterations while calculating
	 * puzzle rating.
	 */
	private static final int RATING_DEF_NUM_OF_ITERATIONS = 1000;
	/*
	 * ======================================================
	 *     Loading / getting board methods
	 * ======================================================
	 */
	/**
	 * Gets Sudoku example for the Sudoku Store.
	 * @param exampleNumber     Example number.
	 * @return                  Sudoku example is exists, otherwise null.
	 * @see SudokuPuzzles#NUMBER_OF_PUZZLE_EXAMPLES
	 * @see SudokuPuzzles#getPuzzleExample(int)
	 */
	public static final int[][] getPuzzleExample(int exampleNumber) {
		return SudokuPuzzles.getPuzzleExample(exampleNumber);
	}
	/**
	 * Returns pre-calculated puzzle example difficulty rating based on
	 * the average number of steps-back performed while recursive
	 * solving sudoku board.
	 *
	 * @param exampleNumber
	 * @return Puzzle example difficulty rating if example exists, otherwise -1.
	 */
	public static final double getPuzzleExampleRating(int exampleNumber) {
		return SudokuPuzzles.getPuzzleExampleRating(exampleNumber);
	}
	/**
	 * Calculates difficulty of Sudoku puzzle. Returned difficulty level is an average
	 * of number of closed routes while performing recursive steps in order to find solution.
	 * This is multi-threading procedure.
	 *
	 * @param sudokuPuzzle   Sudoku puzzle to be rated.
	 * @return               If puzzle does not contain an error then difficulty rating is returned.
	 *                       If puzzle contains obvious error then {@link ErrorCodes#SUDOKUSTORE_CALCULATEPUZZLERATING_PUZZLE_ERROR}.
	 *                       If puzzle has no solutions then {@link ErrorCodes#SUDOKUSTORE_CALCULATEPUZZLERATING_NO_SOLUTION}.
	 *                       If solution is non-unique then {@link ErrorCodes#SUDOKUSTORE_CALCULATEPUZZLERATING_NON_UNIQUE_SOLUTION}.
	 */
	public static final int calculatePuzzleRating(int[][] sudokuPuzzle) {
		if (checkPuzzle(sudokuPuzzle) == false)
			return ErrorCodes.SUDOKUSTORE_CALCULATEPUZZLERATING_PUZZLE_ERROR;
		SudokuSolver s = new SudokuSolver(sudokuPuzzle);
		int solType = s.checkIfUniqueSolution();
		if (solType == SudokuSolver.SOLUTION_NOT_EXISTS)
			return ErrorCodes.SUDOKUSTORE_CALCULATEPUZZLERATING_NO_SOLUTION;
		if (solType == SudokuSolver.SOLUTION_NON_UNIQUE)
			return ErrorCodes.SUDOKUSTORE_CALCULATEPUZZLERATING_NON_UNIQUE_SOLUTION;
		/*
		 * Multi-threading implementation
		 */
		int threadIterNum = RATING_DEF_NUM_OF_ITERATIONS / THREADS_NUMBER;
		int[][] results = new int[THREADS_NUMBER][threadIterNum];
		/**
		 * Runner implementation.
		 */
		class Runner implements Runnable {
			/**
			 * Thread id.
			 */
			int threadId;
			/**
			 * Number of iterations.
			 */
			int iterNum;
			/**
			 * Default constructor.
			 * @param threadId       Thread id.
			 * @param assigments     Test assigned to that thread.
			 */
			Runner(int threadId, int iterNum) {
				this.threadId = threadId;
				this.iterNum = iterNum;
			}
			/**
			 * Synchronized method to store test results.
			 * @param t          Test id.
			 * @param result     TEst result.
			 */
			private void setTestResult(int i, int result) {
				synchronized(results) {
					results[threadId][i] = result;
				}
			}
			@Override
			public void run() {
				for (int i = 0; i < iterNum; i++) {
					SudokuSolver s = new SudokuSolver(sudokuPuzzle);
					s.solve();
					setTestResult(i, s.getClosedRoutesNumber());
				}
			}
		}
		Runner[] runners = new Runner[THREADS_NUMBER];
		Thread[] threads = new Thread[THREADS_NUMBER];
		for (int t = 0; t < THREADS_NUMBER; t++) {
			runners[t] = new Runner(t, threadIterNum);
			threads[t] = new Thread(runners[t]);
		}
		for (int t = 0; t < THREADS_NUMBER; t++)
			threads[t].start();
		for (int t = 0; t < THREADS_NUMBER; t++)
			try {
				threads[t].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
				return ErrorCodes.SUDOKUSTORE_CALCULATEPUZZLERATING_THREADS_JOIN_FAILED;
			}
		int sum = 0;
		for (int t = 0; t < THREADS_NUMBER; t++)
			for (int i = 0; i < threadIterNum; i++)
				sum+=results[t][i];
		return sum / (THREADS_NUMBER * threadIterNum);
	}
	/**
	 * Loads Sudoku board from text file.
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
		return loadBoard(fileLines);
	}
	/**
	 * Loads Sudoku board from list of strings (each string as a
	 * one row)
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
	 * @param boardDefinition  Board definition (list of strings).
	 * @return  Array representing loaded Sudoku board,
	 *          null - if problem occurred while loading.
	 */
	public static final int[][] loadBoard(ArrayList<String> boardDefinition) {
		return loadBoard( ArrayX.toArray(String.class, boardDefinition) );
	}
	/**
	 * Loads Sudoku board from array of strings (each string as a
	 * one row)
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
	 * @param boardDefinition  Board definition (array of strings).
	 * @return  Array representing loaded Sudoku board,
	 *          null - if problem occurred while loading.
	 */
	public static final int[][] loadBoard(String[] boardDefinition) {
		ArrayList<String> sudokuRows = new ArrayList<String>();
		if (boardDefinition == null) return null;
		if (boardDefinition.length < BOARD_SIZE) return null;
		for (String line : boardDefinition) {
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
							(c == '0') ||
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
	 * Saves board to the text file.
	 *
	 * @param sudokuBoard    Sudoku board to be saved.
	 * @param filePath       Path to the file.
	 * @return               True if saving was successful, otherwise false.
	 *
	 * @see SudokuStore#boardToString(int[][])
	 */
	public static final boolean saveBoard(int[][] sudokuBoard, String filePath) {
		String boardString = SudokuStore.boardToString(sudokuBoard);
		return FileX.writeFile(filePath, boardString);
	}
	/**
	 * Saves board to the text file.
	 *
	 * @param sudokuBoard    Sudoku board to be saved.
	 * @param filePath       Path to the file.
	 * @param headComment    Comment to be added at the head.
	 * @return               True if saving was successful, otherwise false.
	 *
	 * @see SudokuStore#boardToString(int[][], String)
	 */
	public static final boolean saveBoard(int[][] sudokuBoard, String filePath, String headComment) {
		String boardString = SudokuStore.boardToString(sudokuBoard, headComment);
		return FileX.writeFile(filePath, boardString);
	}
	/**
	 * Saves board to the text file.
	 *
	 * @param sudokuBoard    Sudoku board to be saved.
	 * @param filePath       Path to the file.
	 * @param headComment    Comment to be added at the head.
	 * @param tailComment    Comment to be added at the tail.
	 * @return               True if saving was successful, otherwise false.
	 *
	 * @see SudokuStore#boardToString(int[][], String, String)
	 */
	public static final boolean saveBoard(int[][] sudokuBoard, String filePath, String headComment, String tailComment) {
		String boardString = SudokuStore.boardToString(sudokuBoard, headComment, tailComment);
		return FileX.writeFile(filePath, boardString);
	}
	/**
	 * Set all digits to zero.
	 *
	 * @param digits
	 * @see SudokuStore#checkSolvedBoard(int[][])
	 */
	private static final void clearDigits(int[] digits) {
		for (int i = 1; i < 10; i++)
			digits[i] = 0;
	}
	/**
	 * Returns found unique digits number.
	 *
	 * @param digits
	 * @see SudokuStore#checkSolvedBoard(int[][])
	 *
	 * @return Unique digits number.
	 */
	private static final int sumDigits(int[] digits) {
		int s = 0;
		for (int i = 1; i < 10; i++)
			s += digits[i];
		return s;
	}
	/**
	 * Returns maximum count of found digits
	 *
	 * @param digits
	 * @see SudokuStore#checkPuzzle(int[][])
	 *
	 * @return Unique digits number.
	 */
	private static final int maxDigitCount(int[] digits) {
		int max = 0;
		for (int i = 1; i < 10; i++)
			if ( digits[i] > max) max = digits[i];
		return max;
	}
	/**
	 * Checks whether solved board is correct.
	 *
	 * @param solvedBoard   The solved board to be verified.
	 *
	 * @return              True if solved board is correct, otherwise false.
	 */
	public static boolean checkSolvedBoard(int[][] solvedBoard) {
		if (solvedBoard == null) return false;
		if (solvedBoard.length != BOARD_SIZE) return false;
		if (solvedBoard[0].length != BOARD_SIZE) return false;
		int[] digits = new int[10];
		for (int i = 0; i < BOARD_SIZE; i++) {
			clearDigits(digits);
			for (int j = 0; j < BOARD_SIZE; j++) {
				int d = solvedBoard[i][j];
				if ( (d < 1) || (d > 9) ) return false;
				digits[d] = 1;
			}
			if (sumDigits(digits) != 9) return false;
		}
		for (int j = 0; j < BOARD_SIZE; j++) {
			clearDigits(digits);
			for (int i = 0; i < BOARD_SIZE; i++) {
				int d = solvedBoard[i][j];
				digits[d] = 1;
			}
			if (sumDigits(digits) != 9) return false;
		}
		for (int rowSeg = 0; rowSeg < BOARD_SEGMENTS_NUMBER; rowSeg++) {
			int iSeg = boardSegmentStartIndex(rowSeg);
			for (int colSeg = 0; colSeg < BOARD_SEGMENTS_NUMBER; colSeg++) {
				int jSeg = boardSegmentStartIndex(colSeg);
				clearDigits(digits);
				for (int i = 0; i < BOARD_SUB_SQURE_SIZE; i++)
					for (int j = 0; j < BOARD_SUB_SQURE_SIZE; j++) {
						int d = solvedBoard[iSeg + i][jSeg + j];
						digits[d] = 1;
					}
				if (sumDigits(digits) != 9) return false;
			}
		}
		return true;
	}
	/**
	 * Checks whether Sudoku puzzle contains an obvious error.
	 *
	 * @param sudokuBoard   The board to be verified.
	 *
	 * @return              True if no obvious error, otherwise false.
	 */
	public static boolean checkPuzzle(int[][] sudokuBoard) {
		if (sudokuBoard == null) return false;
		if (sudokuBoard.length != BOARD_SIZE) return false;
		if (sudokuBoard[0].length != BOARD_SIZE) return false;
		int[] digits = new int[10];
		for (int i = 0; i < BOARD_SIZE; i++) {
			clearDigits(digits);
			for (int j = 0; j < BOARD_SIZE; j++) {
				int d = sudokuBoard[i][j];
				if ( (d < 0) || (d > 9) ) return false;
				digits[d]++;
			}
			if (maxDigitCount(digits) > 1) return false;
		}
		for (int j = 0; j < BOARD_SIZE; j++) {
			clearDigits(digits);
			for (int i = 0; i < BOARD_SIZE; i++) {
				int d = sudokuBoard[i][j];
				digits[d]++;
			}
			if (maxDigitCount(digits) > 1) return false;
		}
		for (int rowSeg = 0; rowSeg < BOARD_SEGMENTS_NUMBER; rowSeg++) {
			int iSeg = boardSegmentStartIndex(rowSeg);
			for (int colSeg = 0; colSeg < BOARD_SEGMENTS_NUMBER; colSeg++) {
				int jSeg = boardSegmentStartIndex(colSeg);
				clearDigits(digits);
				for (int i = 0; i < BOARD_SUB_SQURE_SIZE; i++)
					for (int j = 0; j < BOARD_SUB_SQURE_SIZE; j++) {
						int d = sudokuBoard[iSeg + i][jSeg + j];
						digits[d]++;
					}
				if (maxDigitCount(digits) > 1) return false;
			}
		}
		return true;
	}
	/*
	 * ======================================================
	 *         Allowed operation on Sudoku board
	 * ======================================================
	 */
	/*
	 * ======================================================
	 *                 Rotation methods
	 * ======================================================
	 */
	/**
	 * Clockwise rotation of Sudoku board.
	 *
	 * @param sudokuBoard Array representing Sudoku board.
	 * @return Clockwise rotated sudoku board.
	 */
	public static final int[][] rotateClockWise(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
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
		if (sudokuBoard == null) return null;
		int[][] rotatedBoard = new int[BOARD_SIZE][BOARD_SIZE];
			for (int j = 0; j < BOARD_SIZE; j++) {
				int newRowIndex = BOARD_SIZE - j - 1;
				for (int i = 0; i < BOARD_SIZE; i++)
					rotatedBoard[newRowIndex][i] = sudokuBoard[i][j];
			}
		return rotatedBoard;
	}
	/*
	 * ======================================================
	 *                 Reflection methods
	 * ======================================================
	 */
	/**
	 * Horizontal reflection of the Sudoku board.
	 *
	 * @param sudokuBoard Array representing Sudoku board.
	 * @return Horizontally reflected Sudoku board.
	 */
	public static final int[][] reflectHorizontally(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
		int[][] reflectedBoard = new int[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			int newRowIndex = BOARD_SIZE - i - 1;
			for (int j = 0; j < BOARD_SIZE; j++) {
				reflectedBoard[newRowIndex][j] = sudokuBoard[i][j];
			}
		}
		return reflectedBoard;
	}
	/**
	 * Vertical reflection of the Sudoku board.
	 *
	 * @param sudokuBoard Array representing Sudoku board.
	 * @return Vertically reflected Sudoku board.
	 */
	public static final int[][] reflectVertically(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
		int[][] reflectedBoard = new int[BOARD_SIZE][BOARD_SIZE];
		for (int j = 0; j < BOARD_SIZE; j++) {
			int newColIndex = BOARD_SIZE - j - 1;
			for (int i = 0; i < BOARD_SIZE; i++) {
				reflectedBoard[i][newColIndex] = sudokuBoard[i][j];
			}
		}
		return reflectedBoard;
	}
	/**
	 * Diagonal (Top-Left -> Bottom-Right) reflection of the Sudoku board.
	 *
	 * @param sudokuBoard Array representing Sudoku board.
	 * @return Diagonally (Top-Left -> Bottom-Right) reflection of the Sudoku board. reflected Sudoku board.
	 */
	public static final int[][] transposeTlBr(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
		int[][] reflectedBoard = new int[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++)
				reflectedBoard[j][i] = sudokuBoard[i][j];
		return reflectedBoard;
	}
	/**
	 * Diagonal (Top-Right -> Bottom-Left) reflection of the Sudoku board.
	 *
	 * @param sudokuBoard Array representing Sudoku board.
	 * @return Diagonally Top-Right -> Bottom-Left) reflection of the Sudoku board. reflected Sudoku board.
	 */
	public static final int[][] transposeTrBl(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
		int[][] reflectedBoard = new int[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			int newColIndex = BOARD_SIZE - i -1;
			for (int j = 0; j < BOARD_SIZE; j++) {
				int newRowIndex = BOARD_SIZE - j - 1;
				reflectedBoard[newRowIndex][newColIndex] = sudokuBoard[i][j];
			}
		}
		return reflectedBoard;
	}
	/*
	 * ======================================================
	 *                 Swapping methods
	 * ======================================================
	 */
	/**
	 * Swapping 2 row segments (3x9 each one) of Sudoku board.
	 *
	 * @param    sudokuBoard     Array representing Sudoku board.
	 * @param    rowSeg1         First row segment id: 0, 1, 2.
	 * @param    rowSeg2	     Second row segment id: 0, 1, 2.
	 * @return   New array containing values resulted form swapping
	 *           row  segments procedure. If both segments are equal
	 *           or one of the segments id is not 0, 1, or 2, then exact
	 *           copy of original board is returned.
	 */
	public static final int[][] swapRowSegments(int[][] sudokuBoard, int rowSeg1, int rowSeg2) {
		if (sudokuBoard == null) return null;
		int[][] newBoard = boardCopy(sudokuBoard);
		if (rowSeg1 == rowSeg2) return newBoard;
		if ( (rowSeg1 < 0) || (rowSeg1 > 2) ) return newBoard;
		if ( (rowSeg2 < 0) || (rowSeg2 > 2) ) return newBoard;
		int startRowSeg1 = boardSegmentStartIndex(rowSeg1);
		int startRowSeg2 = boardSegmentStartIndex(rowSeg2);
		for (int i = 0; i < BOARD_SUB_SQURE_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++) {
				newBoard[startRowSeg1 + i][j] = sudokuBoard[startRowSeg2 + i][j];
				newBoard[startRowSeg2 + i][j] = sudokuBoard[startRowSeg1 + i][j];
			}
		return newBoard;
	}
	/**
	 * Swapping randomly selected 2 row segments (3x9 each one) of Sudoku board.
	 *
	 * @param    sudokuBoard     Array representing Sudoku board.
	 * @return   New array containing values resulted form swapping
	 *           column  segments procedure.
	 */
	public static final int[][] swapRowSegmentsRandomly(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
		int seg1 = randomIndex(BOARD_SEGMENTS_NUMBER);
		int seg2 = randomIndex(BOARD_SEGMENTS_NUMBER);
		return swapRowSegments(sudokuBoard, seg1, seg2);
	}
	/**
	 * Swapping 2 column segments (9x3 each one) of Sudoku board.
	 *
	 * @param    sudokuBoard     Array representing Sudoku board.
	 * @param    colSeg1         First column segment id: 0, 1, 2.
	 * @param    colSeg2	     Second column segment id: 0, 1, 2.
	 * @return   New array containing values resulted form swapping
	 *           column  segments procedure. If both segments are equal
	 *           or one of the segments id is not 0, 1, or 2, then exact
	 *           copy of original board is returned.
	 */
	public static final int[][] swapColSegments(int[][] sudokuBoard, int colSeg1, int colSeg2) {
		if (sudokuBoard == null) return null;
		int[][] newBoard = boardCopy(sudokuBoard);
		if (colSeg1 == colSeg2) return newBoard;
		if ( (colSeg1 < 0) || (colSeg1 > 2) ) return newBoard;
		if ( (colSeg2 < 0) || (colSeg2 > 2) ) return newBoard;
		int startColSeg1 = boardSegmentStartIndex(colSeg1);
		int startColSeg2 = boardSegmentStartIndex(colSeg2);
		for (int j = 0; j < BOARD_SUB_SQURE_SIZE; j++)
			for (int i = 0; i < BOARD_SIZE; i++) {
				newBoard[i][startColSeg1 + j] = sudokuBoard[i][startColSeg2 + j];
				newBoard[i][startColSeg2 + j] = sudokuBoard[i][startColSeg1 + j];
			}
		return newBoard;
	}
	/**
	 * Swapping randomly selected 2 column segments (9x3 each one) of Sudoku board.
	 *
	 * @param    sudokuBoard     Array representing Sudoku board.
	 * @return   New array containing values resulted form swapping
	 *           column  segments procedure.
	 */
	public static final int[][] swapColSegmentsRandomly(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
		int seg1 = randomIndex(BOARD_SEGMENTS_NUMBER);
		int seg2 = randomIndex(BOARD_SEGMENTS_NUMBER);
		return swapColSegments(sudokuBoard, seg1, seg2);
	}
	/**
	 * Swapping 2 rows within a given row segment.
	 *
	 * @param    sudokuBoard     Array representing Sudoku board.
	 * @param    rowSeg          Row segment id: 0, 1, 2.
	 * @param    row1            First row id within a segment: 0, 1, 2.
	 * @param    row2            Second row id within a segment: 0, 1, 2.
	 * @return   New array containing values resulted form swapping
	 *           rows procedure. If both rows are equal
	 *           or one of the row id is not 0, 1, or 2, then exact
	 *           copy of original board is returned.
	 */
	public static final int[][] swapRowsInSegment(int[][] sudokuBoard, int rowSeg, int row1, int row2) {
		if (sudokuBoard == null) return null;
		int[][] newBoard = boardCopy(sudokuBoard);
		if ( (rowSeg < 0) || (rowSeg > 2) ) return newBoard;
		if ( (row1 < 0) || (row1 > 2) ) return newBoard;
		if ( (row2 < 0) || (row2 > 2) ) return newBoard;
		if (row1 == row2) return newBoard;
		int segStart = boardSegmentStartIndex(rowSeg);
		for (int j = 0; j < BOARD_SIZE; j++) {
			newBoard[segStart + row1][j] = sudokuBoard[segStart + row2][j];
			newBoard[segStart + row2][j] = sudokuBoard[segStart + row1][j];
		}
		return newBoard;
	}
	/**
	 * Swapping randomly selected 2 rows within randomly select row segment.
	 *
	 * @param    sudokuBoard     Array representing Sudoku board.
	 * @return   New array containing values resulted form swapping
	 *           column  segments procedure.
	 */
	public static final int[][] swapRowsInSegmentRandomly(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
		int rowSeg = randomIndex(BOARD_SEGMENTS_NUMBER);
		int row1 = randomIndex(BOARD_SUB_SQURE_SIZE);
		int row2 = randomIndex(BOARD_SUB_SQURE_SIZE);
		return swapRowsInSegment(sudokuBoard, rowSeg, row1, row2);
	}
	/**
	 * Swapping 2 columns within a given column segment.
	 *
	 * @param    sudokuBoard     Array representing Sudoku board.
	 * @param    colSeg          Column segment id: 0, 1, 2.
	 * @param    col1            First column id within a segment: 0, 1, 2.
	 * @param    col2            Second column id within a segment: 0, 1, 2.
	 * @return   New array containing values resulted form swapping
	 *           columns procedure. If both columns are equal
	 *           or one of the  id is not 0, 1, or 2, then exact
	 *           copy of original board is returned.
	 */
	public static final int[][] swapColsInSegment(int[][] sudokuBoard, int colSeg, int col1, int col2) {
		if (sudokuBoard == null) return null;
		int[][] newBoard = boardCopy(sudokuBoard);
		if ( (colSeg < 0) || (colSeg > 2) ) return newBoard;
		if ( (col1 < 0) || (col1 > 2) ) return newBoard;
		if ( (col2 < 0) || (col2 > 2) ) return newBoard;
		if (col1 == col2) return newBoard;
		int segStart = boardSegmentStartIndex(colSeg);
		for (int i = 0; i < BOARD_SIZE; i++) {
			newBoard[i][segStart + col1] = sudokuBoard[i][segStart + col2];
			newBoard[i][segStart + col2] = sudokuBoard[i][segStart + col1];
		}
		return newBoard;
	}
	/**
	 * Swapping randomly selected 2 columns within randomly select column segment.
	 *
	 * @param    sudokuBoard     Array representing Sudoku board.
	 * @return   New array containing values resulted form swapping
	 *           column  segments procedure.
	 */
	public static final int[][] swapColsInSegmentRandomly(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
		int colSeg = randomIndex(BOARD_SEGMENTS_NUMBER);
		int col1 = randomIndex(BOARD_SUB_SQURE_SIZE);
		int col2 = randomIndex(BOARD_SUB_SQURE_SIZE);
		return swapColsInSegment(sudokuBoard, colSeg, col1, col2);
	}
	/*
	 * ======================================================
	 *                 Permutation methods
	 * ======================================================
	 */
	/**
	 * Sudoku board permutation.
	 * @param sudokuBoard     Sudoku board to be permuted.
	 * @param permutation     Permutation (ie. 5, 2, 8, 9, 1, 3, 6, 4, 7)
	 * @return                If board is null then null,
	 *                        If permutation is not valid the original board.
	 *                        Otherwise permuted board.
	 */
	public static final int[][] permuteBoard(int[][] sudokuBoard, int[] permutation) {
		if (sudokuBoard == null) return null;
		if (isValidPermutation(permutation, BOARD_SIZE) == false) return boardCopy(sudokuBoard);
		int[][] permutatedBoard = new int[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++) {
				int digit = sudokuBoard[i][j];
				if (digit == BoardCell.EMPTY)
					permutatedBoard[i][j] = BoardCell.EMPTY;
				else
					permutatedBoard[i][j] = permutation[digit-1]+1;
			}
		return permutatedBoard;
	}
	/**
	 * Random permutation of sudoku board.
	 *
	 * @param sudokuBoard     Sudoku board to be permuted.
	 * @return                If sudoku board is  ot null then permuted board,
	 *                        otherwise null.
	 */
	public static final int[][] permuteBoard(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
		return permuteBoard(sudokuBoard, generatePermutation(BOARD_SIZE));
	}
	/**
	 * Method applies given permutation of length 3 (permutation of 0, 1, 2)
	 * to the 3 row segments (3x9 each one) of Sudoku array.
	 *
	 * @param    sudokuBoard    Array representing Sudoku board.
	 * @param    permutation    Array representing permutation of length 3 (permuting: 0, 1, 2).
	 * @return   New array resulting from permutation of given sudokuBoard. If sudokuBoard is null
	 *           the null is returned. If permutation is not valid copy of original Sudoku board
	 *           is returned.
	 */
	public static final int[][] permuteRowSegments(int[][] sudokuBoard, int[] permutation) {
		if (sudokuBoard == null) return null;
		if (isValidPermutation(permutation, BOARD_SEGMENTS_NUMBER) == false) return boardCopy(sudokuBoard);
		int[][] permutatedBoard = new int[BOARD_SIZE][BOARD_SIZE];
		int[] segmentStart = new int[BOARD_SEGMENTS_NUMBER];
		for (int seg = 0; seg < BOARD_SEGMENTS_NUMBER; seg ++)
			segmentStart[seg] = boardSegmentStartIndex(seg);
		for (int seg = 0; seg < BOARD_SEGMENTS_NUMBER; seg++)
			for (int i = 0; i < BOARD_SUB_SQURE_SIZE; i++) {
				int curRowIndex = segmentStart[ permutation[seg] ] + i;
				int newRowIndex = segmentStart[seg] + i;
				for (int j = 0; j < BOARD_SIZE; j++) {
					permutatedBoard[newRowIndex][j] = sudokuBoard[curRowIndex][j];
				}
			}
		return permutatedBoard;
	}
	/**
	 * Method applies randomly generated permutation of length 3 (permutation of 0, 1, 2)
	 * to the 3 row segments (3x9 each one) of Sudoku array.
	 *
	 * @param    sudokuBoard    Array representing Sudoku board.
	 * @return   New array resulting from permutation of given sudokuBoard. If sudokuBoard is null
	 *           the null is returned.
	 */
	public static final int[][] permuteRowSegments(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
		return permuteRowSegments(sudokuBoard, generatePermutation(BOARD_SEGMENTS_NUMBER) );
	}
	/**
	 * Method applies given permutation of length 3 (permutation of 0, 1, 2)
	 * to the 3 column segments (9x3 each one) of Sudoku array.
	 *
	 * @param    sudokuBoard    Array representing Sudoku board.
	 * @param    permutation    Array representing permutation of length 3 (permuting: 0, 1, 2).
	 * @return   New array resulting from permutation of given sudokuBoard. If sudokuBoard is null
	 *           the null is returned. If permutation is not valid copy of original Sudoku board
	 *           is returned.
	 */
	public static final int[][] permuteColSegments(int[][] sudokuBoard, int[] permutation) {
		if (sudokuBoard == null) return null;
		if (isValidPermutation(permutation, BOARD_SEGMENTS_NUMBER) == false) return boardCopy(sudokuBoard);
		int[][] permutatedBoard = new int[BOARD_SIZE][BOARD_SIZE];
		int[] segmentStart = new int[BOARD_SEGMENTS_NUMBER];
		for (int seg = 0; seg < BOARD_SEGMENTS_NUMBER; seg ++)
			segmentStart[seg] = boardSegmentStartIndex(seg);
		for (int seg = 0; seg < BOARD_SEGMENTS_NUMBER; seg++)
			for (int j = 0; j < BOARD_SUB_SQURE_SIZE; j++) {
				int curColIndex = segmentStart[ permutation[seg] ] + j;
				int newColIndex = segmentStart[seg] + j;
				for (int i = 0; i < BOARD_SIZE; i++) {
					permutatedBoard[i][newColIndex] = sudokuBoard[i][curColIndex];
				}
			}
		return permutatedBoard;
	}
	/**
	 * Method applies randomly generated permutation of length 3 (permutation of 0, 1, 2)
	 * to the 3 column segments (9x3 each one) of Sudoku array.
	 *
	 * @param    sudokuBoard    Array representing Sudoku board.
	 * @return   New array resulting from permutation of given sudokuBoard. If sudokuBoard is null
	 *           the null is returned.
	 */
	public static final int[][] permuteColSegments(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
		return permuteColSegments(sudokuBoard, generatePermutation(BOARD_SEGMENTS_NUMBER) );
	}
	/**
	 * Method applies given permutation of length 3 (permutation of 0, 1, 2)
	 * to the 3 rows in a given row segment (9x3 each one) of Sudoku array.
	 *
	 * @param    sudokuBoard    Array representing Sudoku board.
	 * @param    rowSeg         Row segment id: 0, 1, 2.
	 * @param    permutation    Permutation of length 3 (0, 1, 2)
	 * @return   New array resulting from permutation of given sudokuBoard. If sudokuBoard is null
	 *           the null is returned. If permutation is not valid exact copy of
	 *           Sudoku board is returned.
	 *
	 * @see SudokuStore#isValidPermutation(int[])
	 * @see SudokuStore#generatePermutation(int)
	 */
	public static final int[][] permuteRowsInSegment(int[][] sudokuBoard, int rowSeg, int[] permutation) {
		if (sudokuBoard == null) return null;
		if (isValidPermutation(permutation, BOARD_SUB_SQURE_SIZE) == false) return boardCopy(sudokuBoard);
		int[][] permutatedBoard = boardCopy(sudokuBoard);
		if ( (rowSeg < 0) || (rowSeg > 2) ) return permutatedBoard;
		int segStart =  boardSegmentStartIndex(rowSeg);
		for (int i = 0; i < BOARD_SUB_SQURE_SIZE; i++) {
			int curRowIndex = segStart + permutation[i];
			int newRowIndex = segStart + i;
			for (int j = 0; j < BOARD_SIZE; j++) {
				permutatedBoard[newRowIndex][j] = sudokuBoard[curRowIndex][j];
			}
		}
		return permutatedBoard;
	}
	/**
	 * Method applies randomly generated permutation of length 3 (permutation of 0, 1, 2)
	 * to the 3 rows in a given row segment (9x3 each one) of Sudoku array.
	 *
	 * @param    sudokuBoard    Array representing Sudoku board.
	 * @param    rowSeg         Row segment id: 0, 1, 2.
	 * @return   New array resulting from permutation of given sudokuBoard. If sudokuBoard is null
	 *           the null is returned.
	 *
	 * @see SudokuStore#generatePermutation(int)
	 */
	public static final int[][] permuteRowsInSegment(int[][] sudokuBoard, int rowSeg) {
		if (sudokuBoard == null) return null;
		return permuteRowsInSegment(sudokuBoard, rowSeg, generatePermutation(BOARD_SUB_SQURE_SIZE) );
	}
	/**
	 * Method applies randomly generated permutation of length 3 (permutation of 0, 1, 2)
	 * to the 3 rows in a randomly selected row segment (9x3 each one) of Sudoku array.
	 *
	 * @param    sudokuBoard    Array representing Sudoku board.
	 * @return   New array resulting from permutation of given sudokuBoard. If sudokuBoard is null
	 *           the null is returned.
	 *
	 * @see SudokuStore#generatePermutation(int)
	 */
	public static final int[][] permuteRowsInSegment(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
		return permuteRowsInSegment(sudokuBoard, randomIndex(BOARD_SEGMENTS_NUMBER), generatePermutation(BOARD_SUB_SQURE_SIZE) );
	}
	/**
	 * Method applies randomly generated permutations of length 3 (permutation of 0, 1, 2)
	 * to the 3 rows in a all row segments (9x3 each one) of Sudoku array. New permutation
	 * is generated separately for each row segment.
	 *
	 * @param    sudokuBoard    Array representing Sudoku board.
	 * @return   New array resulting from permutation of given sudokuBoard. If sudokuBoard is null
	 *           the null is returned.
	 *
	 * @see SudokuStore#generatePermutation(int)
	 */
	public static final int[][] permuteRowsInAllSegments(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
		int[][] permutatedBoard0 = permuteRowsInSegment(sudokuBoard, 0);
		int[][] permutatedBoard1 = permuteRowsInSegment(permutatedBoard0, 1);
		int[][] permutatedBoard2 = permuteRowsInSegment(permutatedBoard1, 2);
		return permutatedBoard2;
	}
	/**
	 * Method applies given permutation of length 3 (permutation of 0, 1, 2)
	 * to the 3 columns in a given column segment (3x9 each one) of Sudoku array.
	 *
	 * @param    sudokuBoard    Array representing Sudoku board.
	 * @param    colSeg         Column segment id: 0, 1, 2.
	 * @param    permutation    Permutation of length 3 (0, 1, 2)
	 * @return   New array resulting from permutation of given sudokuBoard. If sudokuBoard is null
	 *           the null is returned. If permutation is not valid exact copy of
	 *           Sudoku board is returned.
	 *
	 * @see SudokuStore#isValidPermutation(int[])
	 * @see SudokuStore#generatePermutation(int)
	 */
	public static final int[][] permuteColsInSegment(int[][] sudokuBoard, int colSeg, int[] permutation) {
		if (sudokuBoard == null) return null;
		if (isValidPermutation(permutation, BOARD_SUB_SQURE_SIZE) == false) return boardCopy(sudokuBoard);
		int[][] permutatedBoard = boardCopy(sudokuBoard);
		if ( (colSeg < 0) || (colSeg > 2) ) return permutatedBoard;
		int segStart =  boardSegmentStartIndex(colSeg);
		for (int j = 0; j < BOARD_SUB_SQURE_SIZE; j++) {
			int curColIndex = segStart + permutation[j];
			int newColIndex = segStart + j;
			for (int i = 0; i < BOARD_SIZE; i++) {
				permutatedBoard[i][newColIndex] = sudokuBoard[i][curColIndex];
			}
		}
		return permutatedBoard;
	}
	/**
	 * Method applies randomly generated permutation of length 3 (permutation of 0, 1, 2)
	 * to the 3 columns in a given column segment (3x9 each one) of Sudoku array.
	 *
	 * @param    sudokuBoard    Array representing Sudoku board.
	 * @param    colSeg         Column segment id: 0, 1, 2.
	 * @return   New array resulting from permutation of given sudokuBoard. If sudokuBoard is null
	 *           the null is returned.
	 *
	 * @see SudokuStore#generatePermutation(int)
	 */
	public static final int[][] permuteColsInSegment(int[][] sudokuBoard, int colSeg) {
		if (sudokuBoard == null) return null;
		return permuteColsInSegment(sudokuBoard, colSeg, generatePermutation(BOARD_SUB_SQURE_SIZE) );
	}
	/**
	 * Method applies randomly generated permutation of length 3 (permutation of 0, 1, 2)
	 * to the 3 columns in a randomly selected column segment (3x9 each one) of Sudoku array.
	 *
	 * @param    sudokuBoard    Array representing Sudoku board.
	 * @return   New array resulting from permutation of given sudokuBoard. If sudokuBoard is null
	 *           the null is returned.
	 *
	 * @see SudokuStore#generatePermutation(int)
	 */
	public static final int[][] permuteColsInSegment(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
		return permuteColsInSegment(sudokuBoard, randomIndex(BOARD_SEGMENTS_NUMBER), generatePermutation(BOARD_SUB_SQURE_SIZE) );
	}
	/**
	 * Method applies randomly generated permutations of length 3 (permutation of 0, 1, 2)
	 * to the 3 columns in a all column segments (9x3 each one) of Sudoku array. New permutation
	 * is generated separately for each columns segment.
	 *
	 * @param    sudokuBoard    Array representing Sudoku board.
	 * @return   New array resulting from permutation of given sudokuBoard. If sudokuBoard is null
	 *           the null is returned.
	 *
	 * @see SudokuStore#generatePermutation(int)
	 */
	public static final int[][] permuteColsInAllSegments(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
		int[][] permutatedBoard0 = permuteColsInSegment(sudokuBoard, 0);
		int[][] permutatedBoard1 = permuteColsInSegment(permutatedBoard0, 1);
		int[][] permutatedBoard2 = permuteColsInSegment(permutatedBoard1, 2);
		return permutatedBoard2;
	}
	/*
	 * ======================================================
	 *                 Random transformations
	 * ======================================================
	 */
	/**
	 * Based on the parameter value method applies
	 * one of the following board transformation:
	 * <ul>
	 * <li>0 - {@link #rotateClockWise(int[][])}
	 * <li>1 - {@link #rotateCounterclockWise(int[][])}
	 * <li>2 - {@link #reflectHorizontally(int[][])}
	 * <li>3 - {@link #reflectVertically(int[][])}
	 * <li>4 - {@link #transposeTlBr(int[][])}
	 * <li>5 - {@link #transposeTrBl(int[][])}
	 * <li>6 - {@link #swapRowSegmentsRandomly(int[][])}
	 * <li>7 - {@link #swapColSegmentsRandomly(int[][])}
	 * <li>8 - {@link #swapRowsInSegmentRandomly(int[][])}
	 * <li>9 - {@link #swapColsInSegmentRandomly(int[][])}
	 * <li>10 - {@link #permuteBoard(int[][])}
	 * <li>11 - {@link #permuteRowSegments(int[][])}
	 * <li>12 - {@link #permuteColSegments(int[][])}
	 * <li>13 - {@link #permuteRowsInSegment(int[][])}
	 * <li>14 - {@link #permuteRowsInAllSegments(int[][])}
	 * <li>15 - {@link #permuteColsInSegment(int[][])}
	 * <li>16 - {@link #permuteColsInAllSegments(int[][])}
	 * </ul>
	 *
	 * @param sudokuBoard   Sudoku board to be transformed
	 * @param transfId      Random operation id between 0 and {@link #AVAILABLE_RND_BOARD_TRANSF}.
	 * @return              Sudoku board resulting from transformation.
	 */
	public static final int[][] randomBoardTransf(int[][] sudokuBoard, int transfId) {
		if (sudokuBoard == null) return null;
		int rndTransf = randomIndex(AVAILABLE_RND_BOARD_TRANSF);
		switch (rndTransf) {
		case 0:  return rotateClockWise(sudokuBoard);
		case 1:  return rotateCounterclockWise(sudokuBoard);
		case 2:  return reflectHorizontally(sudokuBoard);
		case 3:  return reflectVertically(sudokuBoard);
		case 4:  return transposeTlBr(sudokuBoard);
		case 5:  return transposeTrBl(sudokuBoard);
		case 6:  return swapRowSegmentsRandomly(sudokuBoard);
		case 7:  return swapColSegmentsRandomly(sudokuBoard);
		case 8:  return swapRowsInSegmentRandomly(sudokuBoard);
		case 9:  return swapColsInSegmentRandomly(sudokuBoard);
		case 10: return permuteBoard(sudokuBoard);
		case 11: return permuteRowSegments(sudokuBoard);
		case 12: return permuteColSegments(sudokuBoard);
		case 13: return permuteRowsInSegment(sudokuBoard);
		case 14: return permuteRowsInAllSegments(sudokuBoard);
		case 15: return permuteColsInSegment(sudokuBoard);
		case 16: return permuteColsInAllSegments(sudokuBoard);
		}
		return sudokuBoard;
	}
	/**
	 * Random board transformation of type selected randomly (typed randomly selected between
	 * 0 and {@link #AVAILABLE_RND_BOARD_TRANSF}).
	 *
	 * @param sudokuBoard        Sudoku board to be transformed.
	 * @return                   Sudoku board resulting from transformation.
	 *
	 * @see #randomBoardTransf(int[][], int);
	 */
	public static final int[][] randomBoardTransf(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
		return randomBoardTransf(sudokuBoard, randomIndex(AVAILABLE_RND_BOARD_TRANSF));
	}
	/**
	 * Applies to the Sudoku board sequence (of a given length) of transformations selected randomly
	 * (each transformation selected randomly between 0 and {@link #AVAILABLE_RND_BOARD_TRANSF}).
	 *
	 * @param sudokuBoard       Sudoku board to be transformed.
	 * @param seqLength         Length of sequence (positive)
	 * @return                  Sudoku board resulting from transformations.
	 *                          If seqLengh is lower than 1 then exact copy of
	 *                          Sudoku board is returned.
	 *
	 * @see #randomBoardTransf(int[][], int);
	 */
	public static final int[][] seqOfRandomBoardTransf(int[][] sudokuBoard, int seqLength) {
		if (sudokuBoard == null) return null;
		if (seqLength < 1) return boardCopy(sudokuBoard);
		int[][] newBoard = boardCopy(sudokuBoard);
		for (int i = 0; i < seqLength; i++)
			newBoard = randomBoardTransf(newBoard);
		return newBoard;
	}
	/**
	 * Applies to the Sudoku board sequence (of default length) of transformations selected randomly
	 * (each transformation selected randomly between 0 and {@link #AVAILABLE_RND_BOARD_TRANSF}).
	 * Invocation of {@link #seqOfRandomBoardTransf(int[][], int)} with sequence length
	 * equal to {@link #DEFAULT_RND_TRANSF_SEQ_LENGTH}.
	 *
	 * @param sudokuBoard       Sudoku board to be transformed.
	 * @return                  Sudoku board resulting from transformations.
	 *                          If seqLengh is lower than 1 then exact copy of
	 *                          Sudoku board is returned.
	 *
	 * @see #randomBoardTransf(int[][], int);
	 */
	public static final int[][] seqOfRandomBoardTransf(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
		return seqOfRandomBoardTransf(sudokuBoard, DEFAULT_RND_TRANSF_SEQ_LENGTH);
	}
	/*
	 * ======================================================
	 *              Random numbers generators
	 *               Permutation generators
	 * ======================================================
	 */
	/**
	 * Random number generator for n returning random number between 0, 1, ... n-1.
	 *
	 * @param     n    The parameter influencing random number generation process.
	 * @return    Random number between 0, 1, ... n-1 if n is 1 or above, otherwise
	 *            error {@link ErrorCodes#SUDOKUSTORE_RANDOMINDEX_INCORRECT_PARAMETER}
	 *            is returned.
	 */
	public static final int randomIndex(int n) {
		if (n < 0)
			return ErrorCodes.SUDOKUSTORE_RANDOMINDEX_INCORRECT_PARAMETER;
		return (int)Math.floor(Math.random() * n);
	}
	/**
	 * Random number generator for n returning random number between 1, 2, ... n.
	 *
	 * @param     n    The parameter influencing random number generation process.
	 * @return    Random number between 1, 2, ... n if n is 1 or above, otherwise
	 *            error {@link ErrorCodes#SUDOKUSTORE_RANDOMINDEX_INCORRECT_PARAMETER}
	 *            is returned.
	 */
	public static final int randomNumber(int n) {
		if (n < 1)
			return ErrorCodes.SUDOKUSTORE_RANDOMNUMBER_INCORRECT_PARAMETER;
		return (int)Math.floor(Math.random() * n) + 1;
	}
	/**
	 * 9x9 Sudoku board consist either 3 row segments (3x9 each one)
	 * or 3 column segments (9x3 each one). Each segment has its own
	 * starting index in Sudoku array representing Sudoku board.
	 * For row segments starting index will be giving information
	 * on first column, and for column segments starting index
	 * shall be interpreted as first row in that segment.
	 * All parameters and values start form 0, because they
	 * are designed specifically for arrays.
	 *
	 * @param   segId    The segment id: 0, 1, 2
	 * @return  0 for segment 0, 3 for segment 1, 6 for segment 2,
	 *          otherwise {@link ErrorCodes#SUDOKUSTORE_BOARDSEGMENTSTARTINDEX_INCORRECT_SEGMENT}.
	 */
	public static final int boardSegmentStartIndex(int segId) {
		if (segId == 0) return 0;
		else if (segId == 1) return 3;
		else if (segId == 2) return 6;
		else return ErrorCodes.SUDOKUSTORE_BOARDSEGMENTSTARTINDEX_INCORRECT_SEGMENT;
	}
	/**
	 * Permutation generator assuming permuting
	 * 0, 1, ..., n-1 for n-length permutation.
	 *
	 * @param     n   The permutation length.
	 * @return    If n > 0 permutation array is returned
	 *            containing n randomly distributed elements
	 *            with values: 0, 1, ..., n-1. If n is zero or
	 *            negative null is returned.
	 */
	public static final int[] generatePermutation(int n) {
		if (n <= 0) return null;
		int[] permutation = new int[n];
		if (n == 0) {
			permutation[0] = 0;
			return permutation;
		}
		for (int i = 0; i < n; i++)
			permutation[i] = i;
		for (int notPermuted = n; notPermuted > 0; notPermuted--) {
			int lastNotPermutedIndex = notPermuted - 1;
			int newIndex = randomIndex(notPermuted);
			if (newIndex < lastNotPermutedIndex) {
				int b = permutation[lastNotPermutedIndex];
				permutation[lastNotPermutedIndex] = permutation[newIndex];
				permutation[newIndex] = b;
			}
		}
		return permutation;
	}
	/**
	 * Checks whether given array of length n is representing
	 * valid permutation of 0, 1, ..., n-1
	 *
	 * @param    permutation   Array representing permutation
	 * @return   True if permutation is valid, false otherwise.
	 */
	public static final boolean isValidPermutation(int[] permutation) {
		if (permutation == null) return false;
		int n = permutation.length;
		if (n == 0) return false;
		int[] seq = new int[n];
		for (int i = 0; i < n; i++) {
			seq[i] = 0;
			if ( (permutation[i] < 0) || (permutation[i] > n-1) ) return false;
		}
		for (int i = 0; i < n; i++)
			seq[permutation[i]] = 1;
		int nPerm = 0;
		for (int i = 0; i < n; i++)
			nPerm += seq[i];
		if(nPerm == n) return true;
		else return false;
	}
	/**
	 * Checks whether given array is representing
	 * valid permutation of length n and form of 0, 1, ..., n-1
	 *
	 * @param    permutation   Array representing permutation.
	 * @param    n             Assumed permutation length.
	 * @return   True if permutation is valid, false otherwise.
	 */
	public static final boolean isValidPermutation(int[] permutation, int n) {
		if (permutation == null) return false;
		if (n <= 0) return false;
		if (permutation.length != n) return false;
		return isValidPermutation(permutation);
	}
	/*
	 * ======================================================
	 *             Other board methods
	 * ======================================================
	 */
	/**
	 * Exact (1 to 1) copy of Sudoku board array.
	 *
	 * @param sudokuBoard     The board that will be copied.
	 * @return                 New array containing exact copy of given Sudoku board.
	 */
	public static final int[][] boardCopy(int[][] sudokuBoard) {
		if (sudokuBoard == null) return null;
		int[][] newBoard = new int[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j< BOARD_SIZE; j++)
				newBoard[i][j] = sudokuBoard[i][j];
		return newBoard;
	}
	/**
	 * Checks whether boards are equal.
	 * @param board1    First board.
	 * @param board2    Second board.
	 * @return          True if boards are equal, otherwise false
	 *                  (false also for null boards or board having different sizes).
	 */
	public static final boolean boardsAreEqual(int[][] board1, int[][] board2) {
		if (board1 == null) return false;
		if (board2 == null) return false;
		if (board1.length != board2.length) return false;
		if (board1[0].length != board2[0].length) return false;
		int rdim = board1.length;
		int cdim = board1[0].length;
		for (int i = 0; i < rdim; i++)
			for (int j = 0; j < cdim; j++)
				if (board1[i][j] != board2[i][j]) {
					return false;
				}
		return true;
	}
	/*
	 * ======================================================
	 *           Board to string methods
	 * ======================================================
	 */
	/**
	 * Returns string representation of the board.
	 * @param  sudokuBoard     Array representing Sudoku puzzles.
	 * @param  headComment     Comment to be added at the head.
	 * @param  tailComment     Comment to be added at the tail.
	 * @return Board (only) representation.
	 */
	private static final String convBoardToString(int[][] sudokuBoard, String headComment, String tailComment) {
		String boardStr = "";
		if (headComment != null)
			if (headComment.length() > 0)
					boardStr = boardStr + "# " + headComment + "\n\n";
		if (sudokuBoard == null) return "NULL sudoku board.";
		boardStr = boardStr + "+-------+-------+-------+\n";
		for (int i = 0; i < SudokuBoard.BOARD_SIZE; i ++) {
			if ((i > 0) && (i < SudokuBoard.BOARD_SIZE) && (i % SudokuBoard.BOARD_SUB_SQURE_SIZE == 0))
				boardStr = boardStr + "+-------+-------+-------+\n" ;
			boardStr = boardStr + "| ";
			for (int j = 0; j < SudokuBoard.BOARD_SIZE; j++) {
				if ((j > 0) && (j < SudokuBoard.BOARD_SIZE) && (j % SudokuBoard.BOARD_SUB_SQURE_SIZE == 0))
					boardStr = boardStr + "| ";
				if (sudokuBoard[i][j] != BoardCell.EMPTY)
					boardStr = boardStr + sudokuBoard[i][j] + " ";
				else
					boardStr = boardStr + ". ";
			}
			boardStr = boardStr + "|\n";
		}
		boardStr = boardStr + "+-------+-------+-------+\n\n";
		if (tailComment != null)
			if (tailComment.length() > 0)
					boardStr = boardStr + "# " + tailComment;
		return boardStr;
	}
	/**
	 * Returns string representation of the board.
	 * @param  sudokuBoard   Array representing Sudoku puzzles.
	 * @return Board (only) representation.
	 */
	public static final String boardToString(int[][] sudokuBoard) {
		return convBoardToString(sudokuBoard, "Sudoku puzzle", JANET_SUDOKU_NAME + "-v." + SudokuStore.JANET_SUDOKU_VERSION + ", " + DateTimeX.getCurrDateTimeStr());
	}
	/**
	 * Returns string representation of the board + provided comment.
	 * @param sudokuBoard     Sudoku board.
	 * @param headComment     Comment to be added at the head.
	 * @return                String representation of the sudoku board.
	 */
	public static final String boardToString(int[][] sudokuBoard, String headComment) {
		return convBoardToString(sudokuBoard, headComment, "");
	}
	/**
	 * Returns string representation of the board + provided comment.
	 * @param  sudokuBoard     Sudoku board.
	 * @param  headComment     Comment to be added at the head.
	 * @param  tailComment     Comment to be added at the tail.
	 * @return                 String representation of the sudoku board.
	 */
	public static final String boardToString(int[][] sudokuBoard, String headComment, String tailComment) {
		return convBoardToString(sudokuBoard, headComment, tailComment);
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
	 * Returns string representation of the board and empty cells.
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
	 * Returns string representation of the 'path' leading to the solution.
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
	/**
	 * Prints Sudoku board to the console.
	 * @param sudokuBoard     Sudoku board to be printed
	 */
	public static final void consolePrintBoard(int[][] sudokuBoard) {
		System.out.println(boardToString(sudokuBoard));
	}
	/**
	 * Prints object to the console.
	 * @param o    Object to be printed.
	 */
	public static final void consolePrintln(Object o) {
		System.out.println("[" + JANET_SUDOKU_NAME + "-v." + SudokuStore.JANET_SUDOKU_VERSION + "] " + o);
	}
}
/*
 * ======================================================
 *     Package level classes
 * ======================================================
 */
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
			digitsStillFree[i] = BoardCell.DIGIT_STILL_FREE;
		}
		digitsStillFreeNumber = 0;
	}
	int order() {
		return digitsStillFreeNumber;
	}
	double orderPlusRndSeed() {
		return digitsStillFreeNumber + randomSeed;
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
	/**
	 * Sub-square identification on the Sudoku board
	 * based on the cell position
	 * @param emptyCell   Cell object, including cell position
	 * @return             Sub-square left-top and right-bottom indexes.
	 */
	static final SubSquare getSubSqare(EmptyCell emptyCell) {
		return getSubSqare(emptyCell.rowIndex, emptyCell.colIndex);
	}
	/**
	 * Sub-square identification on the Sudoku board
	 * based on the cell position
	 * @param emptyCell   Cell object, including cell position
	 * @return             Sub-square left-top and right-bottom indexes.
	 */
	static final SubSquare getSubSqare(BoardCell boardCell) {
		return getSubSqare(boardCell.rowIndex, boardCell.colIndex);
	}
	/**
	 * Sub-square identification on the Sudoku board
	 * based on the cell position
	 * @param rowIndex     Row index
	 * @param colIndex     Column index
	 * @return             Sub-square left-top and right-bottom indexes.
	 */
	static final SubSquare getSubSqare(int rowIndex, int colIndex) {
		SubSquare sub = new SubSquare();
		if (rowIndex < SudokuBoard.BOARD_SUB_SQURE_SIZE) {
			sub.rowMin = 0;
			sub.rowMax = SudokuBoard.BOARD_SUB_SQURE_SIZE;
		} else if (rowIndex < 2 * SudokuBoard.BOARD_SUB_SQURE_SIZE) {
			sub.rowMin = SudokuBoard.BOARD_SUB_SQURE_SIZE;
			sub.rowMax = 2 * SudokuBoard.BOARD_SUB_SQURE_SIZE;
		} else {
			sub.rowMin = 2 * SudokuBoard.BOARD_SUB_SQURE_SIZE;
			sub.rowMax = 3 * SudokuBoard.BOARD_SUB_SQURE_SIZE;
		}
		if (colIndex < SudokuBoard.BOARD_SUB_SQURE_SIZE) {
			sub.colMin = 0;
			sub.colMax = SudokuBoard.BOARD_SUB_SQURE_SIZE;
		} else if (colIndex < 2 * SudokuBoard.BOARD_SUB_SQURE_SIZE) {
			sub.colMin = SudokuBoard.BOARD_SUB_SQURE_SIZE;
			sub.colMax = 2 * SudokuBoard.BOARD_SUB_SQURE_SIZE;
		} else {
			sub.colMin = 2 * SudokuBoard.BOARD_SUB_SQURE_SIZE;
			sub.colMax = 3 * SudokuBoard.BOARD_SUB_SQURE_SIZE;
		}
		return sub;
	}
}