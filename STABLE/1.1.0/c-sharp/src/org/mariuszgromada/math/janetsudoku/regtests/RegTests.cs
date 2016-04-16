/*
 * @(#)RegTests.cs        1.0.0    2016-03-19
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
 * THIS SOFTWARE IS PROVIDED BY MARIUSZ GROMADA ``AS IS'' AND ANY EXPRESS OR IMPLIED
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
 *     http://janetsudoku.mariuszgromada.org
 *     http://mathparser.org
 *     http://mathspace.pl
 *     http://github.com/mariuszgromada/Janet-Sudoku
 *     http://janetsudoku.codeplex.com
 *     http://sourceforge.net/projects/janetsudoku
 *     http://bitbucket.org/mariuszgromada/janet-sudoku
 *     http://github.com/mariuszgromada/MathParser.org-mXparser
 *
 *
 *                              Asked if he believes in one God, a mathematician answered:
 *                              "Yes, up to isomorphism."
 */
using org.mariuszgromada.math.janetsudoku.utils;
using System;

namespace org.mariuszgromada.math.janetsudoku.regtests {
	/**
	 * Starts all regression tests.
	 *
	 * @author         <b>Mariusz Gromada</b><br>
	 *                 <a href="mailto:mariusz.gromada@mathspace.pl">mariusz.gromada@mathspace.pl</a><br>
	 *                 <a href="http://janetsudoku.mariuszgromada.org" target="_blank">Janet Sudoku - project web page</a><br>
	 *                 <a href="http://mathspace.pl" target="_blank">MathSpace.pl</a><br>
	 *                 <a href="http://mathparser.org" target="_blank">MathParser.org - mXparser project page</a><br>
	 *                 <a href="http://github.com/mariuszgromada/Janet-Sudoku" target="_blank">Janet Sudoku on GitHub</a><br>
	 *                 <a href="http://janetsudoku.codeplex.com" target="_blank">Janet Sudoku on CodePlex</a><br>
	 *                 <a href="http://sourceforge.net/projects/janetsudoku" target="_blank">Janet Sudoku on SourceForge</a><br>
	 *                 <a href="http://bitbucket.org/mariuszgromada/janet-sudoku" target="_blank">Janet Sudoku on BitBucket</a><br>
	 *                 <a href="http://github.com/mariuszgromada/MathParser.org-mXparser" target="_blank">mXparser-MathParser.org on GitHub</a><br>
	 *
	 * @version        1.0.0
	 *
	 * @see SudokuSolver
	 */
	[CLSCompliant(true)]
	public class RegTests {
		/**
		 * Start all regression tests.
		 *
		 * @param threadsNumber  Threads number.
		 * @return               Number of tests with error result.
		 */
		public static int Start(int threadsNumber) {
			SudokuStore.consolePrintln("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			SudokuStore.consolePrintln("All regression tests - starting.");
			SudokuStore.consolePrintln("  - RegTestsSolver.start()");
			SudokuStore.consolePrintln("  - RegTestsGenerator.start()");
			SudokuStore.consolePrintln("  - RegTestsStore.start()");
			SudokuStore.consolePrintln("  - RegTestsApi.start()");
			SudokuStore.consolePrintln("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			long startTime = DateTimeX.currentTimeMillis();
			int solverErrors = RegTestsSolver.Start(threadsNumber);
			int generatorErrors = RegTestsGenerator.Start(threadsNumber);
			int storeErrors = RegTestsStore.Start(threadsNumber);
			int apiErrors = RegTestsApi.Start(threadsNumber);
			long endTime = DateTimeX.currentTimeMillis();
			double computingTime = (endTime - startTime) / 1000.0;
			int totalErrors = solverErrors + generatorErrors + storeErrors + apiErrors;
			SudokuStore.consolePrintln("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			SudokuStore.consolePrintln("All regression tests - finished.");
			SudokuStore.consolePrintln("Errors: " + totalErrors);
			SudokuStore.consolePrintln("  - RegTestsSolver errors: " + solverErrors);
			SudokuStore.consolePrintln("  - RegTestsGenerator errors: " + generatorErrors);
			SudokuStore.consolePrintln("  - RegTestsStore errors: " + storeErrors);
			SudokuStore.consolePrintln("  - RegTestsApi errors: " + apiErrors);
			SudokuStore.consolePrintln("");
			SudokuStore.consolePrintln("Computing time: " + computingTime + " s.");
			SudokuStore.consolePrintln("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			return totalErrors;
		}
		/**
		 * Start all regression tests with default number of threads.
		 *
		 * @return               Number of tests with error result.
		 */
		public static int Start() {
			return Start(SudokuStore.THREADS_NUMBER);
		}
		/**
		 * Start all regression tests.
		 * @param args     Optional first argument with threads number,
		 *                 otherwise default threads number is used.
		 */
		public static void Main(string[] args) {
			if (args != null) {
				if (args.Length > 0)
					if (args[0] != null) {
						int threadsNumber = int.Parse(args[0]);
						if (threadsNumber > 0) {
							Start(threadsNumber);
							return;
						}
					}
			}
			Start();
		}
	}
}