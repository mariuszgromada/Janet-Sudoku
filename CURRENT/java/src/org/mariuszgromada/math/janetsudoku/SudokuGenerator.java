package org.mariuszgromada.math.janetsudoku;

import java.util.ArrayList;

public class SudokuGenerator {
	private static final int BOARD_SIZE = SudokuBoard.BOARD_SIZE;
	private static final int BOARD_CELLS_NUMBER = SudokuBoard.BOARD_CELLS_NUMBER;
	private static final int CELL_EMPTY = BoardCell.EMPTY;
	public int[][] generate() {
		SudokuSolver solved = new SudokuSolver(1);
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
			System.out.println(filledCells);
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
			if (n != 1) {
				solvedBoard[i][j] = d;
			} else {
				int lastIndex = filledCells-1;
				if (r < lastIndex) {
					BoardCell b1 = boardCells[r];
					BoardCell b2 = boardCells[lastIndex];
					boardCells[lastIndex] = b1;
					boardCells[r] = b2;
				}
				filledCells--;
			}
		} while (n == 1);
		return solvedBoard;
	}

}
