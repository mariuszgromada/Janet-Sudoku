/*
 * @(#)Tutorial.cs        1.0.0    2016-03-19
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
using org.mariuszgromada.math.janetsudoku.utils;
using System;

namespace org.mariuszgromada.math.janetsudoku.tutorial {
	/**
	 * Basic Janet-Sudoku Tutorial.
	 *
	 * @author         <b>Mariusz Gromada</b><br>
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
	 *
	 * @see SudokuSolver
	 * @see SudokuGenerator
	 * @see SudokuStore
	 * @see SudokuPuzzles
	 */
	[CLSCompliant(true)]
	public class Tutorial {
		/**
		 * Start the Janet-Sudoku Tutorial code.
		 * @param args    No arguments are considered.
		 */
		public static void Start() {
			String tmpDir = FileX.getTmpDir();
			{
				/*
				 * Simple sudoku generation.
				 */
				SudokuStore.consolePrintln("");
				SudokuStore.consolePrintln("Simple sudoku generation.");
				SudokuGenerator sg = new SudokuGenerator();
				int[,] puzzle = sg.generate();
				SudokuStore.consolePrintBoard(puzzle);
			}
			{
				/*
				 * Simple sudoku generation + parameters.
				 */
				SudokuStore.consolePrintln("");
				SudokuStore.consolePrintln("Simple sudoku generation + parameters.");
				SudokuGenerator sg = new SudokuGenerator(SudokuGenerator.PARAM_GEN_RND_BOARD);
				int[,] puzzle = sg.generate();
				SudokuStore.consolePrintBoard(puzzle);
			}
			{
				/*
				 * Simple sudoku generation + puzzle rating.
				 */
				SudokuStore.consolePrintln("");
				SudokuStore.consolePrintln("Simple sudoku generation + puzzle rating.");
				SudokuGenerator sg = new SudokuGenerator();
				int[,] puzzle = sg.generate();
				int rating = SudokuStore.calculatePuzzleRating(puzzle);
				SudokuStore.consolePrintBoard(puzzle);
				SudokuStore.consolePrintln("Puzzle rating: " + rating);
			}
			{
				/*
				 * Solving sudoku example.
				 */
				SudokuStore.consolePrintln("");
				SudokuStore.consolePrintln("Solving sudoku example.");
				SudokuSolver ss = new SudokuSolver(SudokuPuzzles.PUZZLE_EXAMPLE_001);
				SudokuStore.consolePrintBoard(ss.getBoard());
				ss.solve();
				SudokuStore.consolePrintBoard(ss.getSolvedBoard());
			}
			{
				/*
				 * Saving board examples
				 */
				SudokuStore.consolePrintln("");
				SudokuStore.consolePrintln("Saving board examples " + tmpDir);
				SudokuStore.saveBoard(SudokuPuzzles.PUZZLE_EXAMPLE_001, tmpDir + "sudoku-board-ex-1.txt");
				SudokuStore.saveBoard(SudokuPuzzles.PUZZLE_EXAMPLE_001, tmpDir + "sudoku-board-ex-2.txt", "This is a head comment");
				SudokuStore.saveBoard(SudokuPuzzles.PUZZLE_EXAMPLE_001, tmpDir + "sudoku-board-ex-3.txt", "This is a head comment", "And a tail comment");
				SudokuSolver ss = new SudokuSolver(1);
				ss.solve();
				ss.saveSolvedBoard(tmpDir + "sudoku-board-ex-sol.txt", "Solution for the PUZZLE_EXAMPLE_001");
			}
			/*
			 * And many other staff provided by the library :-)
			 */
		}
		public static void Main(string[] args) {
			Start();
		}
	}
}