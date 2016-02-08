/*
 * @(#)SudokuPuzzles.java        0.0.1    2016-02-01
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
 * Set of static final arrays containing Sudoku puzzles.
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
public final class SudokuPuzzles {
	/**
	 * Number of Sudoku examples available in Store.
	 */
	public static final int NUMBER_OF_PUZZLE_EXAMPLES = 53;
	/**
	 * Sudoku example - number 1.
	 */
	public static final int[][] PUZZLE_EXAMPLE_01 = {
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
	public static final int[][] PUZZLE_EXAMPLE_02 = {
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
	public static final int[][] PUZZLE_EXAMPLE_03 = {
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
	 * Sudoku example - number 4.
	 */
	public static final int[][] PUZZLE_EXAMPLE_04 = {
			{0,0,3, 0,2,0, 6,0,0},
			{9,0,0, 3,0,5, 0,0,1},
			{0,0,1, 8,0,6, 4,0,0},

			{0,0,8, 1,0,2, 9,0,0},
			{7,0,0, 0,0,0, 0,0,8},
			{0,0,6, 7,0,8, 2,0,0},

			{0,0,2, 6,0,9, 5,0,0},
			{8,0,0, 2,0,3, 0,0,9},
			{0,0,5, 0,1,0, 3,0,0},
		};
	/**
	 * Sudoku example - number 5.
	 */
	public static final int[][] PUZZLE_EXAMPLE_05 = {
			{2,0,0, 0,8,0, 3,0,0},
			{0,6,0, 0,7,0, 0,8,4},
			{0,3,0, 5,0,0, 2,0,9},

			{0,0,0, 1,0,5, 4,0,8},
			{0,0,0, 0,0,0, 0,0,0},
			{4,0,2, 7,0,6, 0,0,0},

			{3,0,1, 0,0,7, 0,4,0},
			{7,2,0, 0,4,0, 0,6,0},
			{0,0,4, 0,1,0, 0,0,3},
		};
	/**
	 * Sudoku example - number 6.
	 */
	public static final int[][] PUZZLE_EXAMPLE_06 = {
			{0,0,0, 0,0,0, 9,0,7},
			{0,0,0, 4,2,0, 1,8,0},
			{0,0,0, 7,0,5, 0,2,6},

			{1,0,0, 9,0,4, 0,0,0},
			{0,5,0, 0,0,0, 0,4,0},
			{0,0,0, 5,0,7, 0,0,9},

			{9,2,0, 1,0,8, 0,0,0},
			{0,3,4, 0,5,9, 0,0,0},
			{5,0,7, 0,0,0, 0,0,0},
		};
	/**
	 * Sudoku example - number 7.
	 */
	public static final int[][] PUZZLE_EXAMPLE_07 = {
			{0,3,0, 0,5,0, 0,4,0},
			{0,0,8, 0,1,0, 5,0,0},
			{4,6,0, 0,0,0, 0,1,2},

			{0,7,0, 5,0,2, 0,8,0},
			{0,0,0, 6,0,3, 0,0,0},
			{0,4,0, 1,0,9, 0,3,0},

			{2,5,0, 0,0,0, 0,9,8},
			{0,0,1, 0,2,0, 6,0,0},
			{0,8,0, 0,6,0, 0,2,0},
		};
	/**
	 * Sudoku example - number 8.
	 */
	public static final int[][] PUZZLE_EXAMPLE_08 = {
			{0,2,0, 8,1,0, 7,4,0},
			{7,0,0, 0,0,3, 1,0,0},
			{0,9,0, 0,0,2, 8,0,5},

			{0,0,9, 0,4,0, 0,8,7},
			{4,0,0, 2,0,8, 0,0,3},
			{1,6,0, 0,3,0, 2,0,0},

			{3,0,2, 7,0,0, 0,6,0},
			{0,0,5, 6,0,0, 0,0,8},
			{0,7,6, 0,5,1, 0,9,0},
		};
	/**
	 * Sudoku example - number 9.
	 */
	public static final int[][] PUZZLE_EXAMPLE_09 = {
			{1,0,0, 9,2,0, 0,0,0},
			{5,2,4, 0,1,0, 0,0,0},
			{0,0,0, 0,0,0, 0,7,0},

			{0,5,0, 0,0,8, 1,0,2},
			{0,0,0, 0,0,0, 0,0,0},
			{4,0,2, 7,0,0, 0,9,0},

			{0,6,0, 0,0,0, 0,0,0},
			{0,0,0, 0,3,0, 9,4,5},
			{0,0,0, 0,7,1, 0,0,6},
		};
	/**
	 * Sudoku example - number 10.
	 */
	public static final int[][] PUZZLE_EXAMPLE_10 = {
			{0,4,3, 0,8,0, 2,5,0},
			{6,0,0, 0,0,0, 0,0,0},
			{0,0,0, 0,0,1, 0,9,4},

			{9,0,0, 0,0,4, 0,7,0},
			{0,0,0, 6,0,8, 0,0,0},
			{0,1,0, 2,0,0, 0,0,3},

			{8,2,0, 5,0,0, 0,0,0},
			{0,0,0, 0,0,0, 0,0,5},
			{0,3,4, 0,9,0, 7,1,0},
		};
	/**
	 * Sudoku example - number 11.
	 */
	public static final int[][] PUZZLE_EXAMPLE_11 = {
			{4,8,0, 0,0,6, 9,0,2},
			{0,0,2, 0,0,8, 0,0,1},
			{9,0,0, 3,7,0, 0,6,0},

			{8,4,0, 0,1,0, 2,0,0},
			{0,0,3, 7,0,4, 1,0,0},
			{0,0,1, 0,6,0, 0,4,9},

			{0,2,0, 0,8,5, 0,0,7},
			{7,0,0, 9,0,0, 6,0,0},
			{6,0,9, 2,0,0, 0,1,8},
		};
	/**
	 * Sudoku example - number 12.
	 */
	public static final int[][] PUZZLE_EXAMPLE_12 = {
			{0,0,0, 9,0,0, 0,0,2},
			{0,5,0, 1,2,3, 4,0,0},
			{0,3,0, 0,0,0, 1,6,0},

			{9,0,8, 0,0,0, 0,0,0},
			{0,7,0, 0,0,0, 0,9,0},
			{0,0,0, 0,0,0, 2,0,5},

			{0,9,1, 0,0,0, 0,5,0},
			{0,0,7, 4,3,9, 0,2,0},
			{4,0,0, 0,0,7, 0,0,0},
		};
	/**
	 * Sudoku example - number 13.
	 */
	public static final int[][] PUZZLE_EXAMPLE_13 = {
			{0,0,1, 9,0,0, 0,0,3},
			{9,0,0, 7,0,0, 1,6,0},
			{0,3,0, 0,0,5, 0,0,7},

			{0,5,0, 0,0,0, 0,0,9},
			{0,0,4, 3,0,2, 6,0,0},
			{2,0,0, 0,0,0, 0,7,0},

			{6,0,0, 1,0,0, 0,3,0},
			{0,4,2, 0,0,7, 0,0,6},
			{5,0,0, 0,0,6, 8,0,0},
		};
	/**
	 * Sudoku example - number 14.
	 */
	public static final int[][] PUZZLE_EXAMPLE_14 = {
			{0,0,0, 1,2,5, 4,0,0},
			{0,0,8, 4,0,0, 0,0,0},
			{4,2,0, 8,0,0, 0,0,0},

			{0,3,0, 0,0,0, 0,9,5},
			{0,6,0, 9,0,2, 0,1,0},
			{5,1,0, 0,0,0, 0,6,0},

			{0,0,0, 0,0,3, 0,4,9},
			{0,0,0, 0,0,7, 2,0,0},
			{0,0,1, 2,9,8, 0,0,0},
		};
	/**
	 * Sudoku example - number 15.
	 */
	public static final int[][] PUZZLE_EXAMPLE_15 = {
			{0,6,2, 3,4,0, 7,5,0},
			{1,0,0, 0,0,5, 6,0,0},
			{5,7,0, 0,0,0, 0,4,0},

			{0,0,0, 0,9,4, 8,0,0},
			{4,0,0, 0,0,0, 0,0,6},
			{0,0,5, 8,3,0, 0,0,0},

			{0,3,0, 0,0,0, 0,9,1},
			{0,0,6, 4,0,0, 0,0,7},
			{0,5,9, 0,8,3, 2,6,0},
		};
	/**
	 * Sudoku example - number 16.
	 */
	public static final int[][] PUZZLE_EXAMPLE_16 = {
			{3,0,0, 0,0,0, 0,0,0},
			{0,0,5, 0,0,9, 0,0,0},
			{2,0,0, 5,0,4, 0,0,0},

			{0,2,0, 0,0,0, 7,0,0},
			{1,6,0, 0,0,0, 0,5,8},
			{7,0,4, 3,1,0, 6,0,0},

			{0,0,0, 8,9,0, 1,0,0},
			{0,0,0, 0,6,7, 0,8,0},
			{0,0,0, 0,0,5, 4,3,7},
		};
	/**
	 * Sudoku example - number 17.
	 */
	public static final int[][] PUZZLE_EXAMPLE_17 = {
			{6,3,0, 0,0,0, 0,0,0},
			{0,0,0, 5,0,0, 0,0,8},
			{0,0,5, 6,7,4, 0,0,0},

			{0,0,0, 0,2,0, 0,0,0},
			{0,0,3, 4,0,1, 0,2,0},
			{0,0,0, 0,0,0, 3,4,5},

			{0,0,0, 0,0,7, 0,0,4},
			{0,8,0, 3,0,0, 9,0,2},
			{9,4,7, 1,0,0, 0,8,0},
		};
	/**
	 * Sudoku example - number 18.
	 */
	public static final int[][] PUZZLE_EXAMPLE_18 = {
			{0,0,0, 0,2,0, 0,4,0},
			{0,0,8, 0,3,5, 0,0,0},
			{0,0,0, 0,7,0, 6,0,2},

			{0,3,1, 0,4,6, 9,7,0},
			{2,0,0, 0,0,0, 0,0,0},
			{0,0,0, 5,0,1, 2,0,3},

			{0,4,9, 0,0,0, 7,3,0},
			{0,0,0, 0,0,0, 0,1,0},
			{8,0,0, 0,0,4, 0,0,0},
		};
	/**
	 * Sudoku example - number 19.
	 */
	public static final int[][] PUZZLE_EXAMPLE_19 = {
			{3,6,1, 0,2,5, 9,0,0},
			{0,8,0, 9,6,0, 0,1,0},
			{4,0,0, 0,0,0, 0,5,7},

			{0,0,8, 0,0,0, 4,7,1},
			{0,0,0, 6,0,3, 0,0,0},
			{2,5,9, 0,0,0, 8,0,0},

			{7,4,0, 0,0,0, 0,0,5},
			{0,2,0, 0,1,8, 0,6,0},
			{0,0,5, 4,7,0, 3,2,9},
		};
	/**
	 * Sudoku example - number 20.
	 */
	public static final int[][] PUZZLE_EXAMPLE_20 = {
			{0,5,0, 8,0,7, 0,2,0},
			{6,0,0, 0,1,0, 0,9,0},
			{7,0,2, 5,4,0, 0,0,6},

			{0,7,0, 0,2,0, 3,0,1},
			{5,0,4, 0,0,0, 9,0,8},
			{1,0,3, 0,8,0, 0,7,0},

			{9,0,0, 0,7,6, 2,0,5},
			{0,6,0, 0,9,0, 0,0,3},
			{0,8,0, 1,0,3, 0,4,0},
		};
	/**
	 * Sudoku example - number 21.
	 */
	public static final int[][] PUZZLE_EXAMPLE_21 = {
			{0,8,0, 0,0,5, 0,0,0},
			{0,0,0, 0,0,3, 4,5,7},
			{0,0,0, 0,7,0, 8,0,9},

			{0,6,0, 4,0,0, 9,0,3},
			{0,0,7, 0,1,0, 5,0,0},
			{4,0,8, 0,0,7, 0,2,0},

			{9,0,1, 0,2,0, 0,0,0},
			{8,4,2, 3,0,0, 0,0,0},
			{0,0,0, 1,0,0, 0,8,0},
		};
	/**
	 * Sudoku example - number 22.
	 */
	public static final int[][] PUZZLE_EXAMPLE_22 = {
			{0,0,3, 5,0,2, 9,0,0},
			{0,0,0, 0,4,0, 0,0,0},
			{1,0,6, 0,0,0, 3,0,5},

			{9,0,0, 2,5,1, 0,0,8},
			{0,7,0, 4,0,8, 0,3,0},
			{8,0,0, 7,6,3, 0,0,1},

			{3,0,8, 0,0,0, 1,0,4},
			{0,0,0, 0,2,0, 0,0,0},
			{0,0,5, 1,0,4, 8,0,0},
		};
	/**
	 * Sudoku example - number 23.
	 */
	public static final int[][] PUZZLE_EXAMPLE_23 = {
			{0,0,0, 0,0,0, 0,0,0},
			{0,0,9, 8,0,5, 1,0,0},
			{0,5,1, 9,0,7, 4,2,0},

			{2,9,0, 4,0,1, 0,6,5},
			{0,0,0, 0,0,0, 0,0,0},
			{1,4,0, 5,0,8, 0,9,3},

			{0,2,6, 7,0,9, 5,8,0},
			{0,0,5, 1,0,3, 6,0,0},
			{0,0,0, 0,0,0, 0,0,0},
		};
	/**
	 * Sudoku example - number 24.
	 */
	public static final int[][] PUZZLE_EXAMPLE_24 = {
			{0,2,0, 0,3,0, 0,9,0},
			{0,0,0, 9,0,7, 0,0,0},
			{9,0,0, 2,0,8, 0,0,5},

			{0,0,4, 8,0,6, 5,0,0},
			{6,0,7, 0,0,0, 2,0,8},
			{0,0,3, 1,0,2, 9,0,0},

			{8,0,0, 6,0,5, 0,0,7},
			{0,0,0, 3,0,9, 0,0,0},
			{0,3,0, 0,2,0, 0,5,0},
		};
	/**
	 * Sudoku example - number 25.
	 */
	public static final int[][] PUZZLE_EXAMPLE_25 = {
			{0,0,5, 0,0,0, 0,0,6},
			{0,7,0, 0,0,9, 0,2,0},
			{0,0,0, 5,0,0, 1,0,7},

			{8,0,4, 1,5,0, 0,0,0},
			{0,0,0, 8,0,3, 0,0,0},
			{0,0,0, 0,9,2, 8,0,5},

			{9,0,7, 0,0,6, 0,0,0},
			{0,3,0, 4,0,0, 0,1,0},
			{2,0,0, 0,0,0, 6,0,0},
		};
	/**
	 * Sudoku example - number 26.
	 */
	public static final int[][] PUZZLE_EXAMPLE_26 = {
			{0,4,0, 0,0,0, 0,5,0},
			{0,0,1, 9,4,3, 6,0,0},
			{0,0,9, 0,0,0, 3,0,0},

			{6,0,0, 0,5,0, 0,0,2},
			{1,0,3, 0,0,0, 5,0,6},
			{8,0,0, 0,2,0, 0,0,7},

			{0,0,5, 0,0,0, 2,0,0},
			{0,0,2, 4,3,6, 7,0,0},
			{0,3,0, 0,0,0, 0,4,0},
		};
	/**
	 * Sudoku example - number 27.
	 */
	public static final int[][] PUZZLE_EXAMPLE_27 = {
			{0,0,4, 0,0,0, 0,0,0},
			{0,0,0, 0,3,0, 0,0,2},
			{3,9,0, 7,0,0, 0,8,0},

			{4,0,0, 0,0,9, 0,0,1},
			{2,0,9, 8,0,1, 3,0,7},
			{6,0,0, 2,0,0, 0,0,8},

			{0,1,0, 0,0,8, 0,5,3},
			{9,0,0, 0,4,0, 0,0,0},
			{0,0,0, 0,0,0, 8,0,0},
		};
	/**
	 * Sudoku example - number 28.
	 */
	public static final int[][] PUZZLE_EXAMPLE_28 = {
			{3,6,0, 0,2,0, 0,8,9},
			{0,0,0, 3,6,1, 0,0,0},
			{0,0,0, 0,0,0, 0,0,0},

			{8,0,3, 0,0,0, 6,0,2},
			{4,0,0, 6,0,3, 0,0,7},
			{6,0,7, 0,0,0, 1,0,8},

			{0,0,0, 0,0,0, 0,0,0},
			{0,0,0, 4,1,8, 0,0,0},
			{9,7,0, 0,3,0, 0,1,4},
		};
	/**
	 * Sudoku example - number 29.
	 */
	public static final int[][] PUZZLE_EXAMPLE_29 = {
			{5,0,0, 4,0,0, 0,6,0},
			{0,0,9, 0,0,0, 8,0,0},
			{6,4,0, 0,2,0, 0,0,0},

			{0,0,0, 0,0,1, 0,0,8},
			{2,0,8, 0,0,0, 5,0,1},
			{7,0,0, 5,0,0, 0,0,0},

			{0,0,0, 0,9,0, 0,8,4},
			{0,0,3, 0,0,0, 6,0,0},
			{0,6,0, 0,0,3, 0,0,2},
		};
	/**
	 * Sudoku example - number 30.
	 */
	public static final int[][] PUZZLE_EXAMPLE_30 = {
			{0,0,7, 2,5,6, 4,0,0},
			{4,0,0, 0,0,0, 0,0,5},
			{0,1,0, 0,3,0, 0,6,0},

			{0,0,0, 5,0,8, 0,0,0},
			{0,0,8, 0,6,0, 2,0,0},
			{0,0,0, 1,0,7, 0,0,0},

			{0,3,0, 0,7,0, 0,9,0},
			{2,0,0, 0,0,0, 0,0,4},
			{0,0,6, 3,1,2, 7,0,0},
		};
	/**
	 * Sudoku example - number 31.
	 */
	public static final int[][] PUZZLE_EXAMPLE_31 = {
			{0,0,0, 0,0,0, 0,0,0},
			{0,7,9, 0,5,0, 1,8,0},
			{8,0,0, 0,0,0, 0,0,7},

			{0,0,7, 3,0,6, 8,0,0},
			{4,5,0, 7,0,8, 0,9,6},
			{0,0,3, 5,0,2, 7,0,0},

			{7,0,0, 0,0,0, 0,0,5},
			{0,1,6, 0,3,0, 4,2,0},
			{0,0,0, 0,0,0, 0,0,0},
		};
	/**
	 * Sudoku example - number 32.
	 */
	public static final int[][] PUZZLE_EXAMPLE_32 = {
			{0,3,0, 0,0,0, 0,8,0},
			{0,0,9, 0,0,0, 5,0,0},
			{0,0,7, 5,0,9, 2,0,0},

			{7,0,0, 1,0,5, 0,0,8},
			{0,2,0, 0,9,0, 0,3,0},
			{9,0,0, 4,0,2, 0,0,1},

			{0,0,4, 2,0,7, 1,0,0},
			{0,0,2, 0,0,0, 8,0,0},
			{0,7,0, 0,0,0, 0,9,0},
		};
	/**
	 * Sudoku example - number 33.
	 */
	public static final int[][] PUZZLE_EXAMPLE_33 = {
			{2,0,0, 1,7,0, 6,0,3},
			{0,5,0, 0,0,0, 1,0,0},
			{0,0,0, 0,0,6, 0,7,9},

			{0,0,0, 0,4,0, 7,0,0},
			{0,0,0, 8,0,1, 0,0,0},
			{0,0,9, 0,5,0, 0,0,0},

			{3,1,0, 4,0,0, 0,0,0},
			{0,0,5, 0,0,0, 0,6,0},
			{9,0,6, 0,3,7, 0,0,2},
		};
	/**
	 * Sudoku example - number 34.
	 */
	public static final int[][] PUZZLE_EXAMPLE_34 = {
			{0,0,0, 0,0,0, 0,8,0},
			{8,0,0, 7,0,1, 0,4,0},
			{0,4,0, 0,2,0, 0,3,0},

			{3,7,4, 0,0,0, 9,0,0},
			{0,0,0, 0,3,0, 0,0,0},
			{0,0,5, 0,0,0, 3,2,1},

			{0,1,0, 0,6,0, 0,5,0},
			{0,5,0, 8,0,2, 0,0,6},
			{0,8,0, 0,0,0, 0,0,0},
		};
	/**
	 * Sudoku example - number 35.
	 */
	public static final int[][] PUZZLE_EXAMPLE_35 = {
			{0,0,0, 0,0,0, 0,8,5},
			{0,0,0, 2,1,0, 0,0,9},
			{9,6,0, 0,8,0, 1,0,0},

			{5,0,0, 8,0,0, 0,1,6},
			{0,0,0, 0,0,0, 0,0,0},
			{8,9,0, 0,0,6, 0,0,7},

			{0,0,9, 0,7,0, 0,5,2},
			{3,0,0, 0,5,4, 0,0,0},
			{4,8,0, 0,0,0, 0,0,0},
		};
	/**
	 * Sudoku example - number 36.
	 */
	public static final int[][] PUZZLE_EXAMPLE_36 = {
			{6,0,8, 0,7,0, 5,0,2},
			{0,5,0, 6,0,8, 0,7,0},
			{0,0,2, 0,0,0, 3,0,0},

			{5,0,0, 0,9,0, 0,0,6},
			{0,4,0, 3,0,2, 0,5,0},
			{8,0,0, 0,5,0, 0,0,3},

			{0,0,5, 0,0,0, 2,0,0},
			{0,1,0, 7,0,4, 0,9,0},
			{4,0,9, 0,6,0, 7,0,1},
		};
	/**
	 * Sudoku example - number 37.
	 */
	public static final int[][] PUZZLE_EXAMPLE_37 = {
			{0,5,0, 0,1,0, 0,4,0},
			{1,0,7, 0,0,0, 6,0,2},
			{0,0,0, 9,0,5, 0,0,0},

			{2,0,8, 0,3,0, 5,0,1},
			{0,4,0, 0,7,0, 0,2,0},
			{9,0,1, 0,8,0, 4,0,6},

			{0,0,0, 4,0,1, 0,0,0},
			{3,0,4, 0,0,0, 7,0,9},
			{0,2,0, 0,6,0, 0,1,0},
		};
	/**
	 * Sudoku example - number 38.
	 */
	public static final int[][] PUZZLE_EXAMPLE_38 = {
			{0,5,3, 0,0,0, 7,9,0},
			{0,0,9, 7,5,3, 4,0,0},
			{1,0,0, 0,0,0, 0,0,2},

			{0,9,0, 0,8,0, 0,1,0},
			{0,0,0, 9,0,7, 0,0,0},
			{0,8,0, 0,3,0, 0,7,0},

			{5,0,0, 0,0,0, 0,0,3},
			{0,0,7, 6,4,1, 2,0,0},
			{0,6,1, 0,0,0, 9,4,0},
		};
	/**
	 * Sudoku example - number 39.
	 */
	public static final int[][] PUZZLE_EXAMPLE_39 = {
			{0,0,6, 0,8,0, 3,0,0},
			{0,4,9, 0,7,0, 2,5,0},
			{0,0,0, 4,0,5, 0,0,0},

			{6,0,0, 3,1,7, 0,0,4},
			{0,0,7, 0,0,0, 8,0,0},
			{1,0,0, 8,2,6, 0,0,9},

			{0,0,0, 7,0,2, 0,0,0},
			{0,7,5, 0,4,0, 1,9,0},
			{0,0,3, 0,9,0, 6,0,0},
		};
	/**
	 * Sudoku example - number 40.
	 */
	public static final int[][] PUZZLE_EXAMPLE_40 = {
			{0,0,5, 0,8,0, 7,0,0},
			{7,0,0, 2,0,4, 0,0,5},
			{3,2,0, 0,0,0, 0,8,4},

			{0,6,0, 1,0,5, 0,4,0},
			{0,0,8, 0,0,0, 5,0,0},
			{0,7,0, 8,0,3, 0,1,0},

			{4,5,0, 0,0,0, 0,9,1},
			{6,0,0, 5,0,8, 0,0,7},
			{0,0,3, 0,1,0, 6,0,0},
		};
	/**
	 * Sudoku example - number 41.
	 */
	public static final int[][] PUZZLE_EXAMPLE_41 = {
			{0,0,0, 9,0,0, 8,0,0},
			{1,2,8, 0,0,6, 4,0,0},
			{0,7,0, 8,0,0, 0,6,0},

			{8,0,0, 4,3,0, 0,0,7},
			{5,0,0, 0,0,0, 0,0,9},
			{6,0,0, 0,7,9, 0,0,8},

			{0,9,0, 0,0,4, 0,1,0},
			{0,0,3, 6,0,0, 2,8,4},
			{0,0,1, 0,0,7, 0,0,0},
		};
	/**
	 * Sudoku example - number 42.
	 */
	public static final int[][] PUZZLE_EXAMPLE_42 = {
			{0,0,0, 0,8,0, 0,0,0},
			{2,7,0, 0,0,0, 0,5,4},
			{0,9,5, 0,0,0, 8,1,0},

			{0,0,9, 8,0,6, 4,0,0},
			{0,2,0, 4,0,3, 0,6,0},
			{0,0,6, 9,0,5, 1,0,0},

			{0,1,7, 0,0,0, 6,2,0},
			{4,6,0, 0,0,0, 0,3,8},
			{0,0,0, 0,9,0, 0,0,0},
		};
	/**
	 * Sudoku example - number 43.
	 */
	public static final int[][] PUZZLE_EXAMPLE_43 = {
			{0,0,0, 6,0,2, 0,0,0},
			{4,0,0, 0,5,0, 0,0,1},
			{0,8,5, 0,1,0, 6,2,0},

			{0,3,8, 2,0,6, 7,1,0},
			{0,0,0, 0,0,0, 0,0,0},
			{0,1,9, 4,0,7, 3,5,0},

			{0,2,6, 0,4,0, 5,3,0},
			{9,0,0, 0,2,0, 0,0,7},
			{0,0,0, 8,0,9, 0,0,0},
		};
	/**
	 * Sudoku example - number 44.
	 */
	public static final int[][] PUZZLE_EXAMPLE_44 = {
			{0,0,0, 9,0,0, 0,0,2},
			{0,5,0, 1,2,3, 4,0,0},
			{0,3,0, 0,0,0, 1,6,0},

			{9,0,8, 0,0,0, 0,0,0},
			{0,7,0, 0,0,0, 0,9,0},
			{0,0,0, 0,0,0, 2,0,5},
			{0,9,1, 0,0,0, 0,5,0},

			{0,0,7, 4,3,9, 0,2,0},
			{4,0,0, 0,0,7, 0,0,0},
		};
	/**
	 * Sudoku example - number 45.
	 */
	public static final int[][] PUZZLE_EXAMPLE_45 = {
			{3,8,0, 0,0,0, 0,0,0},
			{0,0,0, 4,0,0, 7,8,5},
			{0,0,9, 0,2,0, 3,0,0},

			{0,6,0, 0,9,0, 0,0,0},
			{8,0,0, 3,0,2, 0,0,9},
			{0,0,0, 0,4,0, 0,7,0},

			{0,0,1, 0,7,0, 5,0,0},
			{4,9,5, 0,0,6, 0,0,0},
			{0,0,0, 0,0,0, 0,9,2},
		};
	/**
	 * Sudoku example - number 46.
	 */
	public static final int[][] PUZZLE_EXAMPLE_46 = {
			{0,0,0, 1,5,8, 0,0,0},
			{0,0,2, 0,6,0, 8,0,0},
			{0,3,0, 0,0,0, 0,4,0},

			{0,2,7, 0,3,0, 5,1,0},
			{0,0,0, 0,0,0, 0,0,0},
			{0,4,6, 0,8,0, 7,9,0},

			{0,5,0, 0,0,0, 0,8,0},
			{0,0,4, 0,7,0, 1,0,0},
			{0,0,0, 3,2,5, 0,0,0},
		};
	/**
	 * Sudoku example - number 47.
	 */
	public static final int[][] PUZZLE_EXAMPLE_47 = {
			{0,1,0, 5,0,0, 2,0,0},
			{9,0,0, 0,0,1, 0,0,0},
			{0,0,2, 0,0,8, 0,3,0},

			{5,0,0, 0,3,0, 0,0,7},
			{0,0,8, 0,0,0, 5,0,0},
			{6,0,0, 0,8,0, 0,0,4},

			{0,4,0, 1,0,0, 7,0,0},
			{0,0,0, 7,0,0, 0,0,6},
			{0,0,3, 0,0,4, 0,5,0},
		};
	/**
	 * Sudoku example - number 48.
	 */
	public static final int[][] PUZZLE_EXAMPLE_48 = {
			{0,8,0, 0,0,0, 0,4,0},
			{0,0,0, 4,6,9, 0,0,0},
			{4,0,0, 0,0,0, 0,0,7},

			{0,0,5, 9,0,4, 6,0,0},
			{0,7,0, 6,0,8, 0,3,0},
			{0,0,8, 5,0,2, 1,0,0},

			{9,0,0, 0,0,0, 0,0,5},
			{0,0,0, 7,8,1, 0,0,0},
			{0,6,0, 0,0,0, 0,1,0},
		};
	/**
	 * Sudoku example - number 49.
	 */
	public static final int[][] PUZZLE_EXAMPLE_49 = {
			{9,0,4, 2,0,0, 0,0,7},
			{0,1,0, 0,0,0, 0,0,0},
			{0,0,0, 7,0,6, 5,0,0},

			{0,0,0, 8,0,0, 0,9,0},
			{0,2,0, 9,0,4, 0,6,0},
			{0,4,0, 0,0,2, 0,0,0},

			{0,0,1, 6,0,7, 0,0,0},
			{0,0,0, 0,0,0, 0,3,0},
			{3,0,0, 0,0,5, 7,0,2},
		};
	/**
	 * Sudoku example - number 50.
	 */
	public static final int[][] PUZZLE_EXAMPLE_50 = {
			{0,0,0, 7,0,0, 8,0,0},
			{0,0,6, 0,0,0, 0,3,1},
			{0,4,0, 0,0,2, 0,0,0},

			{0,2,4, 0,7,0, 0,0,0},
			{0,1,0, 0,3,0, 0,8,0},
			{0,0,0, 0,6,0, 2,9,0},

			{0,0,0, 8,0,0, 0,7,0},
			{8,6,0, 0,0,0, 5,0,0},
			{0,0,2, 0,0,6, 0,0,0},
		};
	/**
	 * Sudoku example - number 51.
	 */
	public static final int[][] PUZZLE_EXAMPLE_51 = {
			{0,0,1, 0,0,7, 0,9,0},
			{5,9,0, 0,8,0, 0,0,1},
			{0,3,0, 0,0,0, 0,8,0},

			{0,0,0, 0,0,5, 8,0,0},
			{0,5,0, 0,6,0, 0,2,0},
			{0,0,4, 1,0,0, 0,0,0},

			{0,8,0, 0,0,0, 0,3,0},
			{1,0,0, 0,2,0, 0,7,9},
			{0,2,0, 7,0,0, 4,0,0},
		};
	/**
	 * Sudoku example - number 52.
	 */
	public static final int[][] PUZZLE_EXAMPLE_52 = {
			{0,0,0, 0,0,3, 0,1,7},
			{0,1,5, 0,0,9, 0,0,8},
			{0,6,0, 0,0,0, 0,0,0},

			{1,0,0, 0,0,7, 0,0,0},
			{0,0,9, 0,0,0, 2,0,0},
			{0,0,0, 5,0,0, 0,0,4},

			{0,0,0, 0,0,0, 0,2,0},
			{5,0,0, 6,0,0, 3,4,0},
			{3,4,0, 2,0,0, 0,0,0},
		};
	/**
	 * Sudoku example - number 53.
	 */
	public static final int[][] PUZZLE_EXAMPLE_53 = {
			{3,0,0, 2,0,0, 0,0,0},
			{0,0,0, 1,0,7, 0,0,0},
			{7,0,6, 0,3,0, 5,0,0},

			{0,7,0, 0,0,9, 0,8,0},
			{9,0,0, 0,2,0, 0,0,4},
			{0,1,0, 8,0,0, 0,5,0},

			{0,0,9, 0,4,0, 3,0,1},
			{0,0,0, 7,0,2, 0,0,0},
			{0,0,0, 0,0,8, 0,0,6},
		};
}
