/*
 * @(#)Errors.java        0.0.1    2016-01-30
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
}
