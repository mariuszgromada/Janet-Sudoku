package org.mariuszgromada.math.janetsudoku.demoapp;

import java.io.File;
import java.util.ArrayList;
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
	int[][] puzzleUndo;
	int[][] puzzleRedo;
	private boolean rndSeedOnEmptyCells;
	private boolean rndSeedOnFreeDigits;
	public JanetSudoku() {
		puzzle = SudokuStore.boardCopy(SudokuPuzzles.PUZZLE_EMPTY);
		JanetSudoku.console = new Scanner(System.in);
		rndSeedOnEmptyCells = true;
		rndSeedOnFreeDigits = true;
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
	static String consoleReadLine() {
		return console.nextLine();
	}
	private void loopMenuMain() {
		Menu menu = new Menu(MenuData.MAIN_TITLE, MenuData.MAIN_CONTENT, this);
		int selItem;
		do {
			selItem = menu.getItem();
			switch(selItem) {
			case MenuData.LOAD: loopMenuLoad(); break;
			case MenuData.GENERATE: loopMenuGenerate(); break;
			case MenuData.INPUT: loopMenuInput(); break;
			case MenuData.MODIFY: loopMenuModify(); break;
			case MenuData.EVALUATE: loopMenuEvaluate(); break;
			case MenuData.SOLVE: loopMenuSolve(); break;
			case MenuData.SAVE: savePuzzle(); break;
			case MenuData.OPTIONS: loopMenuOptions(); break;
			case MenuData.ABOUT: displayAboutInto(); break;
			case MenuData.RETURN: quitFromApp(); break;
			case MenuData.UNDO: puzzleUndo(); break;
			case MenuData.REDO: puzzleRedo(); break;
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
			case MenuData.LOAD_FROM_FILE: loadFromFile(); break;
			case MenuData.LOAD_EXAMPLE: loadFromExample(); break;
			case MenuData.LOAD_EMPTY_PUZZLE: trackPuzzleUndo(); puzzle = SudokuStore.boardCopy(SudokuPuzzles.PUZZLE_EMPTY); break;
			case MenuData.UNDO: puzzleUndo(); break;
			case MenuData.REDO: puzzleRedo(); break;
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
			case MenuData.GENERATE_RANDOM: generateRandomPuzzle(); break;
			case MenuData.GENERATE_RANDOM_PLUS_RATING: generateAndRateRandomPuzzle(); break;
			case MenuData.GENERATE_BASED_ON_EXAMPLE: generateFromExample(); break;
			case MenuData.GENERATE_BASED_ON_CURRENT_PUZZLE: generateFromCurrentPuzzle(); break;
			case MenuData.UNDO: puzzleUndo(); break;
			case MenuData.REDO: puzzleRedo(); break;
			default: incorrectSelection();
			}
		} while ( selItem != MenuData.RETURN );
	}
	private void loopMenuInput() {
		int selItem;
		Menu menu = new Menu(MenuData.INPUT_TITLE, MenuData.INPUT_CONTENT, this);
		do {
			selItem = menu.getItem();
			switch(selItem) {
			case MenuData.INPUT_ONE_LINE: inputPuzzleFromKeyboard1Line(); break;
			case MenuData.INPUT_9ROWS: inputPuzzleFromKeyboard9rows(); break;
			case MenuData.INPUT_11ROWS: inputPuzzleFromKeyboard11rows(); break;
			case MenuData.INPUT_13ROWS: inputPuzzleFromKeyboard13rows(); break;
			case MenuData.UNDO: puzzleUndo(); break;
			case MenuData.REDO: puzzleRedo(); break;
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
			case MenuData.EVALUATE_SOLUTION_EXISTENCE: evaluateSolutions(); break;
			case MenuData.EVALUATE_PUZZLE_DIFFICULTY: ratePuzzleDifficulty(); break;
			case MenuData.UNDO: puzzleUndo(); break;
			case MenuData.REDO: puzzleRedo(); break;
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
			case MenuData.SOLVE_FIND_ALL: solveFindAll(); break;
			case MenuData.UNDO: puzzleUndo(); break;
			case MenuData.REDO: puzzleRedo(); break;
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
			case MenuData.MODIFY_SET_CELL:
				trackPuzzleUndo();
				setCell();
				break;
			case MenuData.MODIFY_ROTATE_CLOCK_WISE:
				trackPuzzleUndo();
				puzzle = SudokuStore.rotateClockWise(puzzle);
				break;
			case MenuData.MODIFY_ROTATE_COUNTER_CLOCK_WISE:
				trackPuzzleUndo();
				puzzle = SudokuStore.rotateCounterclockWise(puzzle);
				break;
			case MenuData.MODIFY_TRANSPOSE_TL_BR:
				trackPuzzleUndo();
				puzzle = SudokuStore.transposeTlBr(puzzle);
				break;
			case MenuData.MODIFY_TRANSPOSE_TR_BL:
				trackPuzzleUndo();
				puzzle = SudokuStore.transposeTrBl(puzzle);
				break;
			case MenuData.MODIFY_REFLECT_HORIZ:
				trackPuzzleUndo();
				puzzle = SudokuStore.reflectHorizontally(puzzle);
				break;
			case MenuData.MODIFY_REFLECT_VERT:
				trackPuzzleUndo();
				puzzle = SudokuStore.reflectVertically(puzzle);
				break;
			case MenuData.MODIFY_SWAP_COL_SEGMENTS:
				trackPuzzleUndo();
				puzzle = SudokuStore.swapColSegmentsRandomly(puzzle);
				break;
			case MenuData.MODIFY_SWAP_ROW_SEGMENTS:
				trackPuzzleUndo();
				puzzle = SudokuStore.swapRowSegmentsRandomly(puzzle);
				break;
			case MenuData.MODIFY_SWAP_COLS_IN_SEGMENTS:
				trackPuzzleUndo();
				puzzle = SudokuStore.swapColsInSegmentRandomly(puzzle);
				break;
			case MenuData.MODIFY_SWAP_ROWS_IN_SEGMENTS:
				trackPuzzleUndo();
				puzzle = SudokuStore.swapRowsInSegmentRandomly(puzzle);
				break;
			case MenuData.MODIFY_PERMUTE:
				trackPuzzleUndo();
				puzzle = SudokuStore.permuteBoard(puzzle);
				break;
			case MenuData.MODIFY_RANDOM_TRANSF_ONE:
				trackPuzzleUndo();
				puzzle = SudokuStore.randomBoardTransf(puzzle);
				break;
			case MenuData.MODIFY_RANDOM_TRANSF_SEQ:
				trackPuzzleUndo();
				puzzle = SudokuStore.seqOfRandomBoardTransf(puzzle);
				break;
			case MenuData.UNDO:
				puzzleUndo();
				break;
			case MenuData.REDO:
				puzzleRedo();
				break;
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
			case MenuData.OPTIONS_RND_SEED_ON_EMPTY_CELL: optionRndSeedOnEmptyCells(); break;
			case MenuData.OPTIONS_RND_SEED_ON_FREE_DIGIT: optionRndSeedOnFreeDigits(); break;
			case MenuData.UNDO: puzzleUndo(); break;
			case MenuData.REDO: puzzleRedo(); break;
			default: incorrectSelection();
			}
		} while ( selItem != MenuData.RETURN );
	}
	private void loadFromFile() {
		System.out.print("File path: ");
		String filePath = consoleReadLine();
		File file = new File(filePath);
		if (file.exists() == false) {
			System.out.println(">>> !!! Error - file does not exist !!! <<<");
			return;
		}
		if (file.isFile() == false) {
			System.out.println(">>> !!! Error - not a file !!! <<<");
			return;
		}
		int[][] puzzleLoaded = SudokuStore.loadBoard(filePath);
		if (puzzleLoaded == null) {
			System.out.println(">>> !!! Error - incorrect file content !!! <<<");
			return;
		}
		trackPuzzleUndo();
		puzzle = puzzleLoaded;
	}
	private void loadFromExample() {
		System.out.println();
		System.out.print("Please provide example number (between 0 and " + (SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES-1) + "): ");
		int example = consoleReadInt();
		if ((example >= 0) && (example < SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES)) {
			System.out.println("Loading example: " + example);
			trackPuzzleUndo();
			puzzle = SudokuStore.boardCopy(SudokuStore.getPuzzleExample(example));
		} else {
			System.out.println(">>> !!! Incorrect example number !!! <<<");
		}

	}
	private void inputPuzzleFromKeyboard1Line() {
		System.out.print("One line definition: ");
		String line = consoleReadLine();
		int[][] parsedPuzzle = SudokuStore.loadBoardFromStringLine(line);
		if (parsedPuzzle != null) {
			trackPuzzleUndo();
			puzzle = parsedPuzzle;
		}
		else System.out.println(">>> !!! Error - incorrect puzzle definition !!! <<<");
	}
	private void inputPuzzleFromKeyboard9rows() {
		System.out.println("You will be asked for inputting 9 rows.");
		System.out.print("Row 1/9: "); String r1 = consoleReadLine();
		System.out.print("Row 2/9: "); String r2 = consoleReadLine();
		System.out.print("Row 3/9: "); String r3 = consoleReadLine();
		System.out.print("Row 4/9: "); String r4 = consoleReadLine();
		System.out.print("Row 5/9: "); String r5 = consoleReadLine();
		System.out.print("Row 6/9: "); String r6 = consoleReadLine();
		System.out.print("Row 7/9: "); String r7 = consoleReadLine();
		System.out.print("Row 8/9: "); String r8 = consoleReadLine();
		System.out.print("Row 9/9: "); String r9 = consoleReadLine();
		int[][] parsedPuzzle = SudokuStore.loadBoardFromStrings(r1, r2, r3, r4, r5, r6, r7, r8, r9);
		if (parsedPuzzle != null) {
			trackPuzzleUndo();
			puzzle = parsedPuzzle;
		}
		else System.out.println(">>> !!! Error - incorrect puzzle definition !!! <<<");
	}
	private void inputPuzzleFromKeyboard11rows() {
		System.out.println("You will be asked for inputting 11 rows (2 supporting).");
		System.out.print("Row  1/11: "); String r1 = consoleReadLine();
		System.out.print("Row  2/11: "); String r2 = consoleReadLine();
		System.out.print("Row  3/11: "); String r3 = consoleReadLine();
		System.out.print("Row  4/11: "); String r4 = consoleReadLine();
		System.out.print("Row  5/11: "); String r5 = consoleReadLine();
		System.out.print("Row  6/11: "); String r6 = consoleReadLine();
		System.out.print("Row  7/11: "); String r7 = consoleReadLine();
		System.out.print("Row  8/11: "); String r8 = consoleReadLine();
		System.out.print("Row  9/11: "); String r9 = consoleReadLine();
		System.out.print("Row 10/11: "); String r10 = consoleReadLine();
		System.out.print("Row 11/11: "); String r11 = consoleReadLine();
		int[][] parsedPuzzle = SudokuStore.loadBoardFromStrings(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
		if (parsedPuzzle != null) {
			trackPuzzleUndo();
			puzzle = parsedPuzzle;
		}
		else System.out.println(">>> !!! Error - incorrect puzzle definition !!! <<<");
	}
	private void inputPuzzleFromKeyboard13rows() {
		System.out.println("You will be asked for inputting 13 rows (4 supporting).");
		System.out.print("Row  1/13: "); String r1 = consoleReadLine();
		System.out.print("Row  2/13: "); String r2 = consoleReadLine();
		System.out.print("Row  3/13: "); String r3 = consoleReadLine();
		System.out.print("Row  4/13: "); String r4 = consoleReadLine();
		System.out.print("Row  5/13: "); String r5 = consoleReadLine();
		System.out.print("Row  6/13: "); String r6 = consoleReadLine();
		System.out.print("Row  7/13: "); String r7 = consoleReadLine();
		System.out.print("Row  8/13: "); String r8 = consoleReadLine();
		System.out.print("Row  9/13: "); String r9 = consoleReadLine();
		System.out.print("Row 10/13: "); String r10 = consoleReadLine();
		System.out.print("Row 11/13: "); String r11 = consoleReadLine();
		System.out.print("Row 12/13: "); String r12 = consoleReadLine();
		System.out.print("Row 13/13: "); String r13 = consoleReadLine();
		int[][] parsedPuzzle = SudokuStore.loadBoardFromStrings(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
		if (parsedPuzzle != null) {
			trackPuzzleUndo();
			puzzle = parsedPuzzle;
		}
		else System.out.println(">>> !!! Error - incorrect puzzle definition !!! <<<");
	}
	private void solveFindFirst() {
		solver = new SudokuSolver(puzzle);
		solver.solve();
		if (solver.getSolvingState() == SudokuSolver.SOLVING_STATE_SOLVED) {
			trackPuzzleUndo();
			puzzle = solver.getSolvedBoard();
			System.out.println("Path leading to the solution:");
			System.out.println(solver.solutionPathToString());
			System.out.println(">>>>> Computing time: " + solver.getComputingTime() +" s.");
			System.out.println(">>>>>  Closed routes: " + solver.getClosedRoutesNumber() +" s.");
		} else {
			System.out.println(solver.getMessages());
		}
	}
	public void solveFindAll() {
		solver = new SudokuSolver(puzzle);
		int solutionsNumber = solver.findAllSolutions();
		System.out.println(">>>>>>>> Solution found: " + solutionsNumber);
		if (solutionsNumber > 0) {
			ArrayList<SudokuBoard> solutions = solver.getAllSolutionsList();
			for (int i = 0; i < solutionsNumber; i++) {
				SudokuBoard solution = solutions.get(i);
				System.out.println(">>>>>    Solution nr: " + i);
				System.out.println(">>>>>        Path nr: " + solution.pathNumber);
				System.out.println(">>>>> Computing time: " + solver.getComputingTime() +" s.");
				SudokuStore.consolePrintBoard(solution.board);
			}
		} else {
			System.out.println(solver.getMessages());
		}
	}
	private void generateRandomPuzzle() {
		generator = new SudokuGenerator(SudokuGenerator.PARAM_GEN_RND_BOARD);
		int[][] generated = generator.generate();
		if (generator.getGeneratorState() == SudokuGenerator.GENERATOR_GEN_FINISHED) {
			trackPuzzleUndo();
			puzzle = generated;
		}
		else {
			System.out.println(">>> !!! Error while generating random puzzle !!! <<<");
			System.out.println(generator.getMessages());
		}
	}
	private void generateAndRateRandomPuzzle() {
		generator = new SudokuGenerator(SudokuGenerator.PARAM_GEN_RND_BOARD);
		int[][] generated = generator.generate();
		if (generator.getGeneratorState() == SudokuGenerator.GENERATOR_GEN_FINISHED) {
			trackPuzzleUndo();
			puzzle = generated;
			int rating = SudokuStore.calculatePuzzleRating(puzzle);
			System.out.println("Rating: " + rating);
		}
		else {
			System.out.println(">>> !!! Error while generating random puzzle !!! <<<");
			System.out.println(generator.getMessages());
		}
	}
	public void generateFromExample() {
	}
	public void generateFromCurrentPuzzle() {
	}
	public void evaluateSolutions() {
	}
	public void ratePuzzleDifficulty() {
	}
	private void savePuzzle() {
	}
	public void optionRndSeedOnEmptyCells() {
	}
	public void optionRndSeedOnFreeDigits() {
	}
	private void displayAboutInto() {
	}
	private void setCell() {
	}
	private void trackPuzzleUndo() {
		puzzleUndo = SudokuStore.boardCopy(puzzle);
	}
	private void trackPuzzleRedo() {
		puzzleRedo = SudokuStore.boardCopy(puzzle);
	}
	private void puzzleUndo() {
		if (puzzleUndo != null) {
			trackPuzzleRedo();
			puzzle = puzzleUndo;
			puzzleUndo = null;
		}
	}
	private void puzzleRedo() {
		if (puzzleRedo != null) {
			trackPuzzleUndo();
			puzzle = puzzleRedo;
			puzzleRedo = null;
		}
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
