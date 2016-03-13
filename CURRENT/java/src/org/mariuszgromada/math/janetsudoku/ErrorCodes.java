/*
 * @(#)ErrorCodes.java        0.0.1    2016-01-30
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

/**
 * Error codes definition for Janet-Sudoku. The code convention is as follows:<br/>
 * Errors.<b>[CLASS]</b>_<b>[METHOD]</b>_<b>[ERROR_TYPE]</b>
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
public class ErrorCodes {
	/**
	 * Sudoku board loading failed.
	 *
	 * @see SudokuSolver
	 * @see SudokuSolver#loadBoard(int)
	 * #see SudokuSolver#loadBoard(int[][])
	 * @see SudokuSolver#loadBoard(String)
	 */
	public static final int SUDOKUSOLVER_LOADBOARD_LOADING_FAILED = -100;
	/**
	 * Sudoku solving requested, but not started.
	 *
	 * @see SudokuSolver
	 * @see SudokuSolver#solve()
	 */
	public static final int SUDOKUSOLVER_SOLVE_SOLVING_NOT_STARTED = -101;
	/**
	 * Sudoku solving requested, but falied.
	 *
	 * @see SudokuSolver
	 * @see SudokuSolver#solve()
	 */
	public static final int SUDOKUSOLVER_SOLVE_SOLVING_FAILED = -102;
	/**
	 * Finding all Sudoku solutions requested, but not started.
	 *
	 * @see SudokuSolver
	 * @see SudokuSolver#findAllSolutions()
	 */
	public static final int SUDOKUSOLVER_FINDALLSOLUTIONS_SEARCHING_NOT_STARTED = -103;
	/**
	 * Finding all Sudoku solutions requested, but not started.
	 *
	 * @see SudokuSolver
	 * @see SudokuSolver#findAllSolutions()
	 */
	public static final int SUDOKUSOLVER_CHECKIFUNIQUESOLUTION_CHECKING_NOT_STARTED = -104;
	/**
	 * Incorrect cell definition while calling setCell method (incorrect index or incorrect digit).
	 * @see SudokuSolver
	 * @see SudokuSolver#setCell(int, int, int)
	 */
	public static final int SUDOKUSOLVER_SETCELL_INCORRECT_DEFINITION = -105;
	/**
	 * Incorrect cell definition while calling getCell method (incorrect index).
	 * @see SudokuSolver
	 * @see SudokuSolver#getCellDigit(int, int)
	 */
	public static final int SUDOKUSOLVER_GETCELLDIGIT_INCORRECT_INDEX = -106;
	/**
	 * Incorrect segment index while calling {@link SudokuStore#boardSegmentStartIndex(int)}
	 *
	 * @see SudokuStore
	 * @see SudokuStore#boardSegmentStartIndex(int)
	 */
	public static final int SUDOKUSTORE_BOARDSEGMENTSTARTINDEX_INCORRECT_SEGMENT = -107;
	/**
	 * Negative or zero parameter while calling {@link SudokuStore#randomIndex(int)}
	 *
	 * @see SudokuStore
	 * @see SudokuStore#randomIndex(int)
	 */
	public static final int SUDOKUSTORE_RANDOMINDEX_INCORRECT_PARAMETER = -108;
	/**
	 * Negative or zero parameter while calling {@link SudokuStore#randomNumber(int)}
	 *
	 * @see SudokuStore
	 * @see SudokuStore#randomNumber(int)
	 */
	public static final int SUDOKUSTORE_RANDOMNUMBER_INCORRECT_PARAMETER = -109;
	/**
	 * Board contains an error.
	 * @see SudokuSolver
	 */
	public static final int SUDOKUSOLVER_BOARD_ERROR = -110;
	/**
	 * Sudoku board loading failed.
	 *
	 * @see SudokuSolver
	 * @see SudokuSolver#loadBoard(int)
	 * @see SudokuSolver#loadBoard(int[][])
	 * @see SudokuSolver#loadBoard(String)
	 */
	public static final String SUDOKUSOLVER_LOADBOARD_LOADING_FAILED_MSG =  "Failed loading sudoku board.";
	/**
	 * Sudoku solving requested, but not started.
	 *
	 * @see SudokuSolver
	 * @see SudokuSolver#solve()
	 */
	public static final String SUDOKUSOLVER_SOLVE_SOLVING_NOT_STARTED_MSG = "Sudoku solving process not started - board is not ready.";
	/**
	 * Sudoku solving requested, but falied.
	 *
	 * @see SudokuSolver
	 * @see SudokuSolver#solve()
	 */
	public static final String SUDOKUSOLVER_SOLVE_SOLVING_FAILED_MSG = "Sudoku solving process started, but failed - board contains an error?";
	/**
	 * Finding all Sudoku solutions requested, but not started.
	 *
	 * @see SudokuSolver
	 * @see SudokuSolver#findAllSolutions()
	 */
	public static final String SUDOKUSOLVER_FINDALLSOLUTIONS_SEARCHING_NOT_STARTED_MSG = "Searching for all solutions not started = board is not ready.";
	/**
	 * Finding all Sudoku solutions requested, but not started.
	 *
	 * @see SudokuSolver
	 * @see SudokuSolver#findAllSolutions()
	 */
	public static final String SUDOKUSOLVER_CHECKIFUNIQUESOLUTION_CHECKING_NOT_STARTED_MSG = "Checking if only unique solution exists not started - board is not ready.";
	/**
	 * Incorrect cell definition while calling setCell method (incorrect index or incorrect digit).
	 * @see SudokuSolver
	 * @see SudokuSolver#setCell(int, int, int)
	 */
	public static final String SUDOKUSOLVER_SETCELL_INCORRECT_DEFINITION_MSG = "Trying to access the cell, but definition contains an error.";
	/**
	 * Incorrect cell definition while calling getCell method (incorrect index).
	 * @see SudokuSolver
	 * @see SudokuSolver#getCellDigit(int, int)
	 */
	public static final String SUDOKUSOLVER_GETCELLDIGIT_INCORRECT_INDEX_MSG = "Trying to access the cell, but index out of board range.";
	/**
	 * Incorrect segment index while calling {@link SudokuStore#boardSegmentStartIndex(int)}
	 *
	 * @see SudokuStore
	 * @see SudokuStore#boardSegmentStartIndex(int)
	 */
	public static final String SUDOKUSTORE_BOARDSEGMENTSTARTINDEX_INCORRECT_SEGMENT_MSG = "Incorrect board segment index - should be betweem 0 and 2.";
	/**
	 * Negative or zero parameter while calling {@link SudokuStore#randomIndex(int)}
	 *
	 * @see SudokuStore
	 * @see SudokuStore#randomIndex(int)
	 */
	public static final String SUDOKUSTORE_RANDOMINDEX_INCORRECT_PARAMETER_MSG = "Parameter can not be negative.";
	/**
	 * Negative or zero parameter while calling {@link SudokuStore#randomNumber(int)}
	 *
	 * @see SudokuStore
	 * @see SudokuStore#randomNumber(int)
	 */
	public static final String SUDOKUSTORE_RANDOMNUMBER_INCORRECT_PARAMETER_MSG = "Parameter has to be positive.";
	/**
	 * Board contains an error.
	 * @see SudokuSolver
	 */
	public static final String SUDOKUSOLVER_BOARD_ERROR_MSG = "Sudoku board contains an error.";

	/**
	 * Error code unknown
	 * @see #getErrorDescription(int)
	 */
	public static final String ERROR_CODE_UNKNOWN_MSG = "Incorrect error code.";
	/**
	 * Return error code description.
	 *
	 * @param errorCode      Error code
	 * @return               Error code description if error code known,
	 *                       otherwise {@link #ERROR_CODE_UNKNOWN_MSG}.
	 */
	public static final String getErrorDescription(int errorCode) {
		switch(errorCode) {
		case SUDOKUSOLVER_LOADBOARD_LOADING_FAILED: return SUDOKUSOLVER_LOADBOARD_LOADING_FAILED_MSG;
		case SUDOKUSOLVER_SOLVE_SOLVING_NOT_STARTED:return SUDOKUSOLVER_SOLVE_SOLVING_NOT_STARTED_MSG;
		case SUDOKUSOLVER_SOLVE_SOLVING_FAILED: return SUDOKUSOLVER_SOLVE_SOLVING_FAILED_MSG;
		case SUDOKUSOLVER_FINDALLSOLUTIONS_SEARCHING_NOT_STARTED: return SUDOKUSOLVER_FINDALLSOLUTIONS_SEARCHING_NOT_STARTED_MSG;
		case SUDOKUSOLVER_CHECKIFUNIQUESOLUTION_CHECKING_NOT_STARTED: return SUDOKUSOLVER_CHECKIFUNIQUESOLUTION_CHECKING_NOT_STARTED_MSG;
		case SUDOKUSOLVER_SETCELL_INCORRECT_DEFINITION: return SUDOKUSOLVER_SETCELL_INCORRECT_DEFINITION_MSG;
		case SUDOKUSOLVER_GETCELLDIGIT_INCORRECT_INDEX: return SUDOKUSOLVER_GETCELLDIGIT_INCORRECT_INDEX_MSG;
		case SUDOKUSTORE_BOARDSEGMENTSTARTINDEX_INCORRECT_SEGMENT: return SUDOKUSTORE_BOARDSEGMENTSTARTINDEX_INCORRECT_SEGMENT_MSG;
		case SUDOKUSTORE_RANDOMINDEX_INCORRECT_PARAMETER: return SUDOKUSTORE_RANDOMINDEX_INCORRECT_PARAMETER_MSG;
		case SUDOKUSTORE_RANDOMNUMBER_INCORRECT_PARAMETER: return SUDOKUSTORE_RANDOMNUMBER_INCORRECT_PARAMETER_MSG;
		case SUDOKUSOLVER_BOARD_ERROR: return SUDOKUSOLVER_BOARD_ERROR_MSG;
		}
		return ERROR_CODE_UNKNOWN_MSG;
	}
	/**
	 * Checks whether error code exists.
	 *
	 * @param errorCode      Error code
	 * @return               True if error code exists, otherwise false.
	 */
	public static final boolean errorCodeExists(int errorCode) {
		switch(errorCode) {
		case SUDOKUSOLVER_LOADBOARD_LOADING_FAILED: return true;
		case SUDOKUSOLVER_SOLVE_SOLVING_NOT_STARTED:return true;
		case SUDOKUSOLVER_SOLVE_SOLVING_FAILED: return true;
		case SUDOKUSOLVER_FINDALLSOLUTIONS_SEARCHING_NOT_STARTED: return true;
		case SUDOKUSOLVER_CHECKIFUNIQUESOLUTION_CHECKING_NOT_STARTED: return true;
		case SUDOKUSOLVER_SETCELL_INCORRECT_DEFINITION: return true;
		case SUDOKUSOLVER_GETCELLDIGIT_INCORRECT_INDEX: return true;
		case SUDOKUSTORE_BOARDSEGMENTSTARTINDEX_INCORRECT_SEGMENT: return true;
		case SUDOKUSTORE_RANDOMINDEX_INCORRECT_PARAMETER: return true;
		case SUDOKUSTORE_RANDOMNUMBER_INCORRECT_PARAMETER: return true;
		case SUDOKUSOLVER_BOARD_ERROR: return true;
		}
		return false;
	}
	public static final void consolePrintlnIfError(int errorCode) {
		if (errorCodeExists(errorCode))
			SudokuStore.consolePrintln(getErrorDescription(errorCode));
	}
}