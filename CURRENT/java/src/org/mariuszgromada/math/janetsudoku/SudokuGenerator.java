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
	private boolean randomizeEmptyCells = true;

	int [][] solvedBoard;
	BoardCell[] boardCells;
	/**
	 * Board size derived form SudokuBoard class.
	 */
	private static final int BOARD_SIZE = SudokuBoard.BOARD_SIZE;
	/**
	 * Board cells number derived form SudokuBoard class.
	 */
	private static final int BOARD_CELLS_NUMBER = SudokuBoard.BOARD_CELLS_NUMBER;
	public int[][] generate(int e) {
		SudokuSolver solved = new SudokuSolver(e);
		solved.solve();
		solvedBoard = SudokuStore.seqOfRandomBoardTransf(solved.solvedBoard);
		//solvedBoard = solved.solvedBoard;
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
			//System.out.println(filledCells + " - " + n);
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
		SudokuSolver s = new SudokuSolver();
		s.loadBoard(solvedBoard);
		//System.out.print("final: " + filledCells + " empty: " + s.emptyCellsNumber + ", ");
		s.findAllSolutions();
		ArrayList<SudokuBoard> solutionsList = s.getAllSolutionsList();
		n = solutionsList.size();
		//System.out.println("solutions " + n);
		return solvedBoard;
	}
	public void updateDigitsStillFreeCounts() {
		for (int i = 0; i < BOARD_CELLS_NUMBER; i++)
			countDigitsStillFree(boardCells[i]);
	}
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
				while (boardCells[i].digitsStillFreeNumber + boardCells[i].randomSeed < x.digitsStillFreeNumber + x.randomSeed)
					i++;
				while (boardCells[j].digitsStillFreeNumber + boardCells[j].randomSeed > x.digitsStillFreeNumber + x.randomSeed)
					j--;
			} else {
				/*
				 * No randomization
				 */
				while (boardCells[i].digitsStillFreeNumber < x.digitsStillFreeNumber)
					i++;
				while (boardCells[j].digitsStillFreeNumber > x.digitsStillFreeNumber)
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
}
