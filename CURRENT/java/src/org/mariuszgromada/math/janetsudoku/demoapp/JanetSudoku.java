package org.mariuszgromada.math.janetsudoku.demoapp;

import java.util.Scanner;

import org.mariuszgromada.math.janetsudoku.SudokuBoard;
import org.mariuszgromada.math.janetsudoku.SudokuGenerator;
import org.mariuszgromada.math.janetsudoku.SudokuPuzzles;
import org.mariuszgromada.math.janetsudoku.SudokuSolver;
import org.mariuszgromada.math.janetsudoku.SudokuStore;

public class JanetSudoku {
	public static final String VERSION = "1.0.0";
	public static final String ABOUT = "Janet Sudoku demo application";
	public static final String AUTHOR = "Mariusz Gromada";
	private static final int BOARD_SIZE = SudokuBoard.BOARD_SIZE;
	static Scanner console;
	SudokuSolver solver;
	SudokuGenerator generator;
	int[][] puzzle;
	public JanetSudoku() {
		puzzle = SudokuStore.boardCopy(SudokuPuzzles.PUZZLE_EMPTY);
		JanetSudoku.console = new Scanner(System.in);
	}
	void consolePrintPuzzle() {
		SudokuStore.consolePrintBoard(puzzle);
	}
	static int consoleReadInt() {
		String line = console.nextLine();
		int consoleInt;
		try {
			consoleInt = Integer.parseInt(line);
		} catch (Exception e) {
			consoleInt = -1;
		}
		return consoleInt;
	}
	private void loopMenuMain() {
		Menu menu = new Menu(MenuData.MAIN_TITLE, MenuData.MAIN_CONTENT, this);
		int selItem;
		do {
			selItem = menu.getItem();
			switch(selItem) {
			case MenuData.LOAD: loopMenuLoad(); break;
			case MenuData.GENERATE: loopMenuGenerate(); break;
			case MenuData.INPUT: inputPuzzleFromKeyboard(); break;
			case MenuData.MODIFY: loopMenuModify(); break;
			case MenuData.EVALUATE: loopMenuEvaluate(); break;
			case MenuData.SOLVE: loopMenuSolve(); break;
			case MenuData.SAVE: savePuzzle(); break;
			case MenuData.OPTIONS: loopMenuOptions(); break;
			case MenuData.ABOUT: displayAboutInto(); break;
			case MenuData.RETURN: quitFromApp(); break;
			default: incorrectSelection();
			}
		} while ( selItem != MenuData.RETURN );
	}
	private void loopMenuLoad() {
		int selItem;
		Menu menu = new Menu(MenuData.LOAD_TITLE, MenuData.LOAD_CONTENT, this);
		do {
			selItem = menu.getItem();
			switch(selItem) {
			case MenuData.LOAD_FROM_FILE: ; break;
			case MenuData.LOAD_EXAMPLE: loadFromExample(); break;
			case MenuData.LOAD_EMPTY_PUZZLE: puzzle = SudokuStore.boardCopy(SudokuPuzzles.PUZZLE_EMPTY); break;
			default: incorrectSelection();
			}
		} while ( selItem != MenuData.RETURN );
	}
	private void loopMenuGenerate() {
		int selItem;
		Menu menu = new Menu(MenuData.GENERATE_TITLE, MenuData.GENERATE_CONTENT, this);
		do {
			selItem = menu.getItem();
			switch(selItem) {
			case MenuData.GENERATE_RANDOM: ; break;
			case MenuData.GENERATE_BASED_ON_EXAMPLE: ; break;
			case MenuData.GENERATE_BASED_ON_CURRENT_PUZZLE: ; break;
			default: incorrectSelection();
			}
		} while ( selItem != MenuData.RETURN );
	}
	private void loopMenuEvaluate() {
		int selItem;
		Menu menu = new Menu(MenuData.EVALUATE_TITLE, MenuData.EVALUATE_CONTENT, this);
		do {
			selItem = menu.getItem();
			switch(selItem) {
			case MenuData.EVALUATE_SOLUTION_EXISTENCE: ; break;
			case MenuData.EVALUATE_PUZZLE_DIFFICULTY: ; break;
			default: incorrectSelection();
			}
		} while ( selItem != MenuData.RETURN );
	}
	private void loopMenuSolve() {
		int selItem;
		Menu menu = new Menu(MenuData.SOLVE_TITLE, MenuData.SOLVE_CONTENT, this);
		do {
			selItem = menu.getItem();
			switch(selItem) {
			case MenuData.SOLVE_FIND_FIRST: solveFindFirst(); break;
			case MenuData.SOLVE_FIND_ALL: ; break;
			default: incorrectSelection();
			}
		} while ( selItem != MenuData.RETURN );
	}
	private void loopMenuModify() {
		int selItem;
		Menu menu = new Menu(MenuData.MODIFY_TITLE, MenuData.MODIFY_CONTENT, this);
		do {
			selItem = menu.getItem();
			switch(selItem) {
			case MenuData.MODIFY_SET_CELL: setCell() ; break;
			case MenuData.MODIFY_ROTATE_CLOCK_WISE: puzzle = SudokuStore.rotateClockWise(puzzle); break;
			case MenuData.MODIFY_ROTATE_COUNTER_CLOCK_WISE: puzzle = SudokuStore.rotateCounterclockWise(puzzle); break;
			case MenuData.MODIFY_TRANSPOSE_TL_BR: puzzle = SudokuStore.transposeTlBr(puzzle); break;
			case MenuData.MODIFY_TRANSPOSE_TR_BL: puzzle = SudokuStore.transposeTrBl(puzzle); break;
			case MenuData.MODIFY_REFLECT_HORIZ: puzzle = SudokuStore.reflectHorizontally(puzzle); break;
			case MenuData.MODIFY_REFLECT_VERT: puzzle = SudokuStore.reflectVertically(puzzle); break;
			case MenuData.MODIFY_SWAP_COL_SEGMENTS: puzzle = SudokuStore.swapColSegmentsRandomly(puzzle); break;
			case MenuData.MODIFY_SWAP_ROW_SEGMENTS: puzzle = SudokuStore.swapRowSegmentsRandomly(puzzle); break;
			case MenuData.MODIFY_SWAP_COLS_IN_SEGMENTS: puzzle = SudokuStore.swapColsInSegmentRandomly(puzzle); break;
			case MenuData.MODIFY_SWAP_ROWS_IN_SEGMENTS: puzzle = SudokuStore.swapRowsInSegmentRandomly(puzzle); break;
			case MenuData.MODIFY_PERMUTE: puzzle = SudokuStore.permuteBoard(puzzle); break;
			case MenuData.MODIFY_RANDOM_TRANSF_ONE: puzzle = SudokuStore.randomBoardTransf(puzzle); break;
			case MenuData.MODIFY_RANDOM_TRANSF_SEQ: puzzle = SudokuStore.seqOfRandomBoardTransf(puzzle); break;
			default: incorrectSelection();
			}
		} while ( selItem != MenuData.RETURN );
	}
	private void loopMenuOptions() {
		int selItem;
		Menu menu = new Menu(MenuData.OPTIONS_TITLE, MenuData.OPTIONS_CONTENT, this);
		do {
			selItem = menu.getItem();
			switch(selItem) {
			case MenuData.OPTIONS_RND_SEED_ON_EMPTY_CELL: ; break;
			case MenuData.OPTIONS_RND_SEED_ON_FREE_DIGIT: ; break;
			default: incorrectSelection();
			}
		} while ( selItem != MenuData.RETURN );
	}
	private void loadFromExample() {
		System.out.println();
		System.out.print("Please provide example number (between 0 and " + SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES + "): ");
		int example = consoleReadInt();
		if ((example >= 0) && (example <= SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES)) {
			System.out.println("Loading example: " + example);
			puzzle = SudokuStore.boardCopy(SudokuStore.getPuzzleExample(example));
		} else {
			System.out.println(">>> !!! Incorrect example number !!! <<<");
		}

	}
	private void inputPuzzleFromKeyboard() {
	}
	private void solveFindFirst() {
		solver = new SudokuSolver(puzzle);
		solver.solve();
		if (solver.getSolvingState() == SudokuSolver.SOLVING_STATE_SOLVED) {
			puzzle = solver.getSolvedBoard();
			System.out.println("Path leading to the solution:");
			System.out.println(solver.solutionPathToString());
		} else {
			System.out.println(solver.getMessages());
		}
	}
	private void savePuzzle() {
	}
	private void displayAboutInto() {
	}
	private void setCell() {
	}
	private void quitFromApp() {
		JanetSudoku.console.close();
	}
	private void incorrectSelection() {
		System.out.println("Error - unrecognized menu item.");
	}
	public void start() {
		loopMenuMain();
	}
	public static void main(String[] args) {
		JanetSudoku js = new JanetSudoku();
		js.start();
	}
}
