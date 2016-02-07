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

import java.util.ArrayList;
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
	private static final int CELL_EMPTY = BoardCell.EMPTY;
	public int[][] generate() {
		SudokuSolver solved = new SudokuSolver("/home/pi/projects/github/Janet-Sudoku/CURRENT/examples/sudoku-04.txt");
		solved.solve();
		int[][] solvedBoard = solved.solvedBoard;
		BoardCell[] boardCells = new BoardCell[BOARD_CELLS_NUMBER];
		int cellIndex = 0;
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++) {
			boardCells[cellIndex] = new BoardCell(i, j, solvedBoard[i][j]);
			cellIndex++;
		}
		int filledCells = BOARD_CELLS_NUMBER;
		int n = 0;
		do {
			int r = (int)Math.floor( Math.random() * filledCells );
			int i = boardCells[r].rowIndex;
			int j = boardCells[r].colIndex;
			int d = solvedBoard[i][j];
			solvedBoard[i][j] = CELL_EMPTY;
			SudokuSolver s = new SudokuSolver();
			s.loadBoard(solvedBoard);
			s.findAllSolutions();
			ArrayList<SudokuBoard> solutionsList = s.getAllSolutionsList();
			n = solutionsList.size();
			System.out.println(filledCells + " - " + n);
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
		} while (filledCells > 0);
		SudokuSolver s = new SudokuSolver();
		s.loadBoard(solvedBoard);
		System.out.print("final: " + filledCells + " empty: " + s.emptyCellsNumber + ", ");
		s.findAllSolutions();
		ArrayList<SudokuBoard> solutionsList = s.getAllSolutionsList();
		n = solutionsList.size();
		System.out.println("solutions " + n);
		return solvedBoard;
	}
}
