/*
 * @(#)JanetSudoku.cs        1.0.0    2016-04-15
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
using System;
using System.Collections.Generic;
using System.IO;

namespace org.mariuszgromada.math.janetsudoku.demoapp {
	/**
	 * Janet Sudoku Demo Application utilizing Janet Sudoku Solver and Generator
	 * library.
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
	 */
	[CLSCompliant(true)]
	public class JanetSudoku {
		/**
		 * Demo app version.
		 */
		public const String VERSION = "1.1.0";
		/**
		 * Internal solver.
		 */
		internal SudokuSolver solver;
		/**
		 * Internal generator.
		 */
		internal SudokuGenerator generator;
		/**
		 * Current puzzle.
		 */
		internal int[,] puzzle;
		/**
		 * Current puzzle undo.
		 */
		internal int[,] puzzleUndo;
		/**
		 * Current puzzle redo.
		 */
		internal int[,] puzzleRedo;
		/**
		 * Random seed on cell option.
		 * {@link SudokuSolver#enableRndSeedOnEmptyCells()},
		 * {@link SudokuSolver#disableRndSeedOnEmptyCells(){,
		 * {@link SudokuGenerator#enableRndSeedOnFilledCells()},
		 * {@link SudokuGenerator#disableRndSeedOnFilledCells(){.
		 *
		 * @see SudokuSolver
		 * @see SudokuGenerator
		 */
		private bool rndSeedOnCells;
		/**
		 * Random seed on cell option.
		 * {@link SudokuSolver#enableRndSeedOnFreeDigits()},
		 * {@link SudokuSolver#disableRndSeedOnFreeDigits()}
		 *
		 * @see SudokuSolver
		 */
		private bool rndSeedOnDigits;
		/**
		 * Default constructor.
		 */
		public JanetSudoku() {
			puzzle = SudokuStore.boardCopy(SudokuPuzzles.PUZZLE_EMPTY);
			rndSeedOnCells = true;
			rndSeedOnDigits = true;
		}
		/*
		 * ========================================
		 *                Main Menu
		 * ========================================
		 */
		/**
		 * Main menu loop
		 */
		private void loopMenuMain() {
			Menu menu = new Menu(MenuData.MAIN_TITLE, MenuData.MAIN_CONTENT, this);
			int selItem;
			do {
				selItem = menu.getItem();
				switch (selItem) {
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
				case MenuData.UNDO: performPuzzleUndo(); break;
				case MenuData.REDO: performPuzzleRedo(); break;
				default: incorrectSelection(); break;
				}
			} while (selItem != MenuData.RETURN);
		}
		/*
		 * ========================================
		 *                Load Menu
		 * ========================================
		 */
		/**
		 * Load menu loop.
		 */
		private void loopMenuLoad() {
			int selItem;
			Menu menu = new Menu(MenuData.LOAD_TITLE, MenuData.LOAD_CONTENT, this);
			do {
				selItem = menu.getItem();
				switch (selItem) {
				case MenuData.LOAD_FROM_FILE: loadFromFile(); break;
				case MenuData.LOAD_EXAMPLE: loadFromExample(); break;
				case MenuData.LOAD_EMPTY_PUZZLE: trackPuzzleUndo(); puzzle = SudokuStore.boardCopy(SudokuPuzzles.PUZZLE_EMPTY); break;
				case MenuData.LOAD_LIST_EXAMPLES: listPuzzleExamples(); break;
				case MenuData.UNDO: performPuzzleUndo(); break;
				case MenuData.REDO: performPuzzleRedo(); break;
				default: incorrectSelection(); break;
				}
			} while (selItem != MenuData.RETURN);
		}
		/**
		 * Load puzzle from file.
		 * @see SudokuStore#loadBoard(String)
		 */
		private void loadFromFile() {
			JanetConsole.print("File path: ");
			String filePath = JanetConsole.readLine();
			FileInfo file = new FileInfo(filePath);
			if (file.Exists == false) {
				JanetConsole.println(">>> !!! Error - file does not exist !!! <<<");
				return;
			}
			DirectoryInfo dir = new DirectoryInfo(filePath);
			if (dir.Exists == true) {
				JanetConsole.println(">>> !!! Error - not a file !!! <<<");
				return;
			}
			int[,] puzzleLoaded = SudokuStore.loadBoard(filePath);
			if (puzzleLoaded == null) {
				JanetConsole.println(">>> !!! Error - incorrect file content !!! <<<");
				return;
			}
			trackPuzzleUndo();
			puzzle = puzzleLoaded;
		}
		/**
		 * Load puzzle from provided puzzle examples.
		 * @see SudokuPuzzles
		 * @see SudokuStore#getPuzzleExample(int)
		 */
		private void loadFromExample() {
			JanetConsole.println();
			JanetConsole.print("Please provide example number (between 0 and " + (SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES - 1) + "): ");
			int example = JanetConsole.readInt();
			if ((example >= 0) && (example < SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES)) {
				JanetConsole.println("Loading example: " + example);
				trackPuzzleUndo();
				puzzle = SudokuStore.boardCopy(SudokuStore.getPuzzleExample(example));
			} else {
				JanetConsole.println(">>> !!! Incorrect example number !!! <<<");
			}
		}
		/**
		 * Prints puzzle examples identifiers along with difficulty rating.
		 */
		private void listPuzzleExamples() {
			JanetConsole.println("");
			for (int i = 0; i < SudokuPuzzles.NUMBER_OF_PUZZLE_EXAMPLES; i++) {
				JanetConsole.println(">>> Example nr: " + i + ", rating = " + (int)SudokuPuzzles.getPuzzleExampleRating(i));
			}
			JanetConsole.println("");
		}
		/*
		 * ========================================
		 *             Generate Menu
		 * ========================================
		 */
		/**
		 * Generate menu loop.
		 */
		private void loopMenuGenerate() {
			int selItem;
			Menu menu = new Menu(MenuData.GENERATE_TITLE, MenuData.GENERATE_CONTENT, this);
			do {
				selItem = menu.getItem();
				switch (selItem) {
				case MenuData.GENERATE_RANDOM: generateRandomPuzzle(); break;
				case MenuData.GENERATE_RANDOM_PLUS_RATING: generateAndRateRandomPuzzle(); break;
				case MenuData.GENERATE_BASED_ON_EXAMPLE: generateFromExample(); break;
				case MenuData.GENERATE_BASED_ON_CURRENT_PUZZLE: generateFromCurrentPuzzle(); break;
				case MenuData.UNDO: performPuzzleUndo(); break;
				case MenuData.REDO: performPuzzleRedo(); break;
				default: incorrectSelection(); break;
				}
			} while (selItem != MenuData.RETURN);
		}
		/**
		 * Generates random puzzle with unique solution.
		 *
		 * @see SudokuGenerator
		 * @see SudokuGenerator#PARAM_GEN_RND_BOARD
		 * @see SudokuGenerator#generate()
		 */
		private void generateRandomPuzzle() {
			generator = new SudokuGenerator(SudokuGenerator.PARAM_GEN_RND_BOARD);
			setGeneratorOptions();
			int[,] generated = generator.generate();
			if (generator.getGeneratorState() == SudokuGenerator.GENERATOR_GEN_FINISHED) {
				trackPuzzleUndo();
				puzzle = generated;
			} else {
				JanetConsole.println(">>> !!! Error while generating random puzzle !!! <<<");
				JanetConsole.println(generator.getMessages());
			}
		}
		/**
		 * Generates random puzzle with unique solution.
		 * Evaluates puzzle difficulty rating.
		 *
		 * @see SudokuGenerator
		 * @see SudokuGenerator#PARAM_GEN_RND_BOARD
		 * @see SudokuGenerator#generate()
		 * @see SudokuStore#calculatePuzzleRating(int[,])
		 */
		private void generateAndRateRandomPuzzle() {
			generator = new SudokuGenerator(SudokuGenerator.PARAM_GEN_RND_BOARD);
			setGeneratorOptions();
			int[,] generated = generator.generate();
			if (generator.getGeneratorState() == SudokuGenerator.GENERATOR_GEN_FINISHED) {
				trackPuzzleUndo();
				puzzle = generated;
				ratePuzzleDifficulty();
			} else {
				JanetConsole.println(">>> !!! Error while generating random puzzle !!! <<<");
				JanetConsole.println(generator.getMessages());
			}
		}
		/**
		 * Generates puzzle based on provided puzzle example
		 * (different puzzle, same solution).
		 *
		 * @see SudokuGenerator
		 * @see SudokuGenerator#PARAM_DO_NOT_TRANSFORM
		 * @see SudokuGenerator#generate()
		 */
		private void generateFromExample() {
			loadFromExample();
			generateFromCurrentPuzzle();
		}
		/**
		 * Generates puzzle based on current puzzle
		 * (different puzzle, same solution).
		 *
		 * @see SudokuGenerator
		 * @see SudokuGenerator#PARAM_DO_NOT_TRANSFORM
		 * @see SudokuGenerator#generate()
		 */
		private void generateFromCurrentPuzzle() {
			generator = new SudokuGenerator(puzzle, SudokuGenerator.PARAM_DO_NOT_TRANSFORM);
			setGeneratorOptions();
			int[,] generated = generator.generate();
			if (generator.getGeneratorState() == SudokuGenerator.GENERATOR_GEN_FINISHED) {
				trackPuzzleUndo();
				puzzle = generated;
			} else {
				JanetConsole.println(">>> !!! Error while generating puzzle !!! <<<");
				JanetConsole.println(generator.getMessages());
			}
		}
		/*
		 * ========================================
		 *               Input Menu
		 * ========================================
		 */
		/**
		 * Input menu loop
		 */
		private void loopMenuInput() {
			int selItem;
			Menu menu = new Menu(MenuData.INPUT_TITLE, MenuData.INPUT_CONTENT, this);
			do {
				selItem = menu.getItem();
				switch (selItem) {
				case MenuData.INPUT_ONE_LINE: inputPuzzleFromKeyboard1Line(); break;
				case MenuData.INPUT_9ROWS: inputPuzzleFromKeyboard9rows(); break;
				case MenuData.INPUT_11ROWS: inputPuzzleFromKeyboard11rows(); break;
				case MenuData.INPUT_13ROWS: inputPuzzleFromKeyboard13rows(); break;
				case MenuData.UNDO: performPuzzleUndo(); break;
				case MenuData.REDO: performPuzzleRedo(); break;
				default: incorrectSelection(); break;
				}
			} while (selItem != MenuData.RETURN);
		}
		/**
		 * One line keyboard input (81 characters)
		 * 0 or '.' as empty cell.
		 *
		 * @see SudokuStore#loadBoardFromStringLine(String)
		 */
		private void inputPuzzleFromKeyboard1Line() {
			JanetConsole.print("One line definition: ");
			String line = JanetConsole.readLine();
			int[,] parsedPuzzle = SudokuStore.loadBoardFromStringLine(line);
			if (parsedPuzzle != null) {
				trackPuzzleUndo();
				puzzle = parsedPuzzle;
			} else JanetConsole.println(">>> !!! Error - incorrect puzzle definition !!! <<<");
		}
		/**
		 * 9 rows keyboard input - 0 or '.' as empty cell.
		 * Any other character is being filtered out.
		 *
		 * @see SudokuStore#loadBoardFromStrings(String...)
		 * @see SudokuStore#loadBoard(String[])
		 */
		private void inputPuzzleFromKeyboard9rows() {
			JanetConsole.println("You will be asked for inputting 9 rows.");
			JanetConsole.print("Row 1/9: "); String r1 = JanetConsole.readLine();
			JanetConsole.print("Row 2/9: "); String r2 = JanetConsole.readLine();
			JanetConsole.print("Row 3/9: "); String r3 = JanetConsole.readLine();
			JanetConsole.print("Row 4/9: "); String r4 = JanetConsole.readLine();
			JanetConsole.print("Row 5/9: "); String r5 = JanetConsole.readLine();
			JanetConsole.print("Row 6/9: "); String r6 = JanetConsole.readLine();
			JanetConsole.print("Row 7/9: "); String r7 = JanetConsole.readLine();
			JanetConsole.print("Row 8/9: "); String r8 = JanetConsole.readLine();
			JanetConsole.print("Row 9/9: "); String r9 = JanetConsole.readLine();
			int[,] parsedPuzzle = SudokuStore.loadBoardFromStrings(r1, r2, r3, r4, r5, r6, r7, r8, r9);
			if (parsedPuzzle != null) {
				trackPuzzleUndo();
				puzzle = parsedPuzzle;
			} else JanetConsole.println(">>> !!! Error - incorrect puzzle definition !!! <<<");
		}
		/**
		 * 11 rows keyboard input (2 supporting) - 0 or '.' as empty cell.
		 * Any other character is being filtered out.
		 *
		 * @see SudokuStore#loadBoardFromStrings(String...)
		 * @see SudokuStore#loadBoard(String[])
		 */
		private void inputPuzzleFromKeyboard11rows() {
			JanetConsole.println("You will be asked for inputting 11 rows (2 supporting).");
			JanetConsole.print("Row  1/11: "); String r1 = JanetConsole.readLine();
			JanetConsole.print("Row  2/11: "); String r2 = JanetConsole.readLine();
			JanetConsole.print("Row  3/11: "); String r3 = JanetConsole.readLine();
			JanetConsole.print("Row  4/11: "); String r4 = JanetConsole.readLine();
			JanetConsole.print("Row  5/11: "); String r5 = JanetConsole.readLine();
			JanetConsole.print("Row  6/11: "); String r6 = JanetConsole.readLine();
			JanetConsole.print("Row  7/11: "); String r7 = JanetConsole.readLine();
			JanetConsole.print("Row  8/11: "); String r8 = JanetConsole.readLine();
			JanetConsole.print("Row  9/11: "); String r9 = JanetConsole.readLine();
			JanetConsole.print("Row 10/11: "); String r10 = JanetConsole.readLine();
			JanetConsole.print("Row 11/11: "); String r11 = JanetConsole.readLine();
			int[,] parsedPuzzle = SudokuStore.loadBoardFromStrings(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
			if (parsedPuzzle != null) {
				trackPuzzleUndo();
				puzzle = parsedPuzzle;
			} else JanetConsole.println(">>> !!! Error - incorrect puzzle definition !!! <<<");
		}
		/**
		 * 13 rows keyboard input (4 supporting) - 0 or '.' as empty cell.
		 * Any other character is being filtered out.
		 *
		 * @see SudokuStore#loadBoardFromStrings(String...)
		 * @see SudokuStore#loadBoard(String[])
		 */
		private void inputPuzzleFromKeyboard13rows() {
			JanetConsole.println("You will be asked for inputting 13 rows (4 supporting).");
			JanetConsole.print("Row  1/13: "); String r1 = JanetConsole.readLine();
			JanetConsole.print("Row  2/13: "); String r2 = JanetConsole.readLine();
			JanetConsole.print("Row  3/13: "); String r3 = JanetConsole.readLine();
			JanetConsole.print("Row  4/13: "); String r4 = JanetConsole.readLine();
			JanetConsole.print("Row  5/13: "); String r5 = JanetConsole.readLine();
			JanetConsole.print("Row  6/13: "); String r6 = JanetConsole.readLine();
			JanetConsole.print("Row  7/13: "); String r7 = JanetConsole.readLine();
			JanetConsole.print("Row  8/13: "); String r8 = JanetConsole.readLine();
			JanetConsole.print("Row  9/13: "); String r9 = JanetConsole.readLine();
			JanetConsole.print("Row 10/13: "); String r10 = JanetConsole.readLine();
			JanetConsole.print("Row 11/13: "); String r11 = JanetConsole.readLine();
			JanetConsole.print("Row 12/13: "); String r12 = JanetConsole.readLine();
			JanetConsole.print("Row 13/13: "); String r13 = JanetConsole.readLine();
			int[,] parsedPuzzle = SudokuStore.loadBoardFromStrings(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
			if (parsedPuzzle != null) {
				trackPuzzleUndo();
				puzzle = parsedPuzzle;
			} else JanetConsole.println(">>> !!! Error - incorrect puzzle definition !!! <<<");
		}
		/*
		 * ========================================
		 *              Evaluate Menu
		 * ========================================
		 */
		/**
		 * Evaluate menu loop
		 */
		private void loopMenuEvaluate() {
			int selItem;
			Menu menu = new Menu(MenuData.EVALUATE_TITLE, MenuData.EVALUATE_CONTENT, this);
			do {
				selItem = menu.getItem();
				switch (selItem) {
				case MenuData.EVALUATE_SOLUTION_EXISTENCE: evaluateSolutions(); break;
				case MenuData.EVALUATE_PUZZLE_DIFFICULTY: ratePuzzleDifficulty(); break;
				case MenuData.UNDO: performPuzzleUndo(); break;
				case MenuData.REDO: performPuzzleRedo(); break;
				default: incorrectSelection(); break;
				}
			} while (selItem != MenuData.RETURN);
		}
		/**
		 * Verifies solution existence
		 *
		 * @see SudokuSolver#checkIfUniqueSolution()
		 */
		private void evaluateSolutions() {
			solver = new SudokuSolver(puzzle);
			int solutionsInfo = solver.checkIfUniqueSolution();
			JanetConsole.println(">>>");
			if (solutionsInfo == SudokuSolver.SOLUTION_UNIQUE) JanetConsole.println(">>> Solution exists and is unique!");
			else if (solutionsInfo == SudokuSolver.SOLUTION_NON_UNIQUE) JanetConsole.println(">>> Solution exists but is non-unique!");
			else if (solutionsInfo == SudokuSolver.SOLUTION_NOT_EXISTS) JanetConsole.println(">>> Solution does not exists.");
			else JanetConsole.println(solver.getMessages());
			JanetConsole.println(">>> Computing time: " + solver.getComputingTime() + " s.");
		}
		/**
		 * Rate puzzle difficulty meaning as number of closed routes (number of
		 * wrong guesses).
		 *
		 * @see SudokuStore#calculatePuzzleRating(int[,])
		 */
		private void ratePuzzleDifficulty() {
			int rating = SudokuStore.calculatePuzzleRating(puzzle);
			if (rating >= 0) {
				JanetConsole.println(">>>");
				JanetConsole.println(">>> Puzzle rating: " + rating);
				JanetConsole.println(">>>");
			} else {
				JanetConsole.println(">>> !!! Error code: " + rating + " !!! <<<");
				JanetConsole.println(">>> " + ErrorCodes.getErrorDescription(rating));
			}
		}
		/*
		 * ========================================
		 *             Solve Menu
		 * ========================================
		 */
		/**
		 * Solve menu loop
		 */
		private void loopMenuSolve() {
			int selItem;
			Menu menu = new Menu(MenuData.SOLVE_TITLE, MenuData.SOLVE_CONTENT, this);
			do {
				selItem = menu.getItem();
				switch (selItem) {
				case MenuData.SOLVE_FIND_FIRST: solveFindFirst(); break;
				case MenuData.SOLVE_FIND_ALL: solveFindAll(); break;
				case MenuData.UNDO: performPuzzleUndo(); break;
				case MenuData.REDO: performPuzzleRedo(); break;
				default: incorrectSelection(); break;
				}
			} while (selItem != MenuData.RETURN);
		}
		/**
		 * Solves current puzzle.
		 *
		 * @see SudokuSolver#solve()
		 * @see SudokuSolver#getSolvedBoard()
		 * @see SudokuSolver#getSolvingState()
		 */
		private void solveFindFirst() {
			solver = new SudokuSolver(puzzle);
			setSolverOptions();
			solver.solve();
			if (solver.getSolvingState() == SudokuSolver.SOLVING_STATE_SOLVED) {
				trackPuzzleUndo();
				puzzle = solver.getSolvedBoard();
				JanetConsole.println("Path leading to the solution:");
				JanetConsole.println(solver.solutionPathToString());
				JanetConsole.println(">>>>> Computing time: " + solver.getComputingTime() + " s.");
				JanetConsole.println(">>>>>  Closed routes: " + solver.getClosedRoutesNumber() + " s.");
			} else {
				JanetConsole.println(solver.getMessages());
			}
		}
		/**
		 * Solves current puzzle.
		 *
		 * @see SudokuSolver#findAllSolutions()
		 */
		private void solveFindAll() {
			solver = new SudokuSolver(puzzle);
			setSolverOptions();
			int solutionsNumber = solver.findAllSolutions();
			JanetConsole.println(">>>>>>>> Solutions found: " + solutionsNumber);
			if (solutionsNumber > 0) {
				List<SudokuBoard> solutions = solver.getAllSolutionsList();
				for (int i = 0; i < solutionsNumber; i++) {
					SudokuBoard solution = solutions[i];
					JanetConsole.println(">>>>>    Solution nr: " + i + "/" + solutionsNumber);
					JanetConsole.println(">>>>>        Path nr: " + solution.pathNumber);
					JanetConsole.println(">>>>> Computing time: " + solver.getComputingTime() + " s.");
					SudokuStore.consolePrintBoard(solution.board);
					JanetConsole.println(">>>>>");
					JanetConsole.println(">>>>> Hit enter o to continue (non empty line will cancel).");
					String line = JanetConsole.readLine();
					if (line.Length > 0) break;
				}
			} else {
				JanetConsole.println(solver.getMessages());
			}
		}
		/*
		 * ========================================
		 *              Save Menu
		 * ========================================
		 */
		/**
		 * Saves current puzzle in the txt file.
		 *
		 * @see SudokuStore#saveBoard(int[,], String)
		 */
		private void savePuzzle() {
			JanetConsole.print("File path: ");
			String filePath = JanetConsole.readLine();
			FileInfo file = new FileInfo(filePath);
			DirectoryInfo dir = new DirectoryInfo(filePath);
			if ((file.Exists == true) || (dir.Exists == true)) {
				JanetConsole.println(">>> !!! Error - file already exists !!! <<<");
				return;
			}
			bool puzzleSaved = SudokuStore.saveBoard(puzzle, filePath);
			if (puzzleSaved == false)
				JanetConsole.println(">>> !!! Error while saving !!! <<<");
		}
		/*
		 * ========================================
		 *              Modify Menu
		 * ========================================
		 */
		/**
		 * Modify menu loop
		 */
		private void loopMenuModify() {
			int selItem;
			Menu menu = new Menu(MenuData.MODIFY_TITLE, MenuData.MODIFY_CONTENT, this);
			do {
				selItem = menu.getItem();
				switch (selItem) {
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
					performPuzzleUndo();
					break;
				case MenuData.REDO:
					performPuzzleRedo();
					break;
				default: incorrectSelection(); break;
				}
			} while (selItem != MenuData.RETURN);
		}
		/**
		 * Manually sets cell value.
		 */
		private void setCell() {
			JanetConsole.print("   Row number (between 1 and 9): "); int row = JanetConsole.readInt();
			if ((row < 1) || (row > 9)) {
				JanetConsole.println(">>> !!! Error - incorrect row number !!! <<<");
				return;
			}
			JanetConsole.print("Column number (between 1 and 9): "); int col = JanetConsole.readInt();
			if ((col < 1) || (col > 9)) {
				JanetConsole.println(">>> !!! Error - incorrect column number !!! <<<");
				return;
			}
			JanetConsole.print("        Digit (between 0 and 9): "); int digit = JanetConsole.readInt();
			if ((digit < 0) || (digit > 9)) {
				JanetConsole.println(">>> !!! Error - incorrect digit !!! <<<");
				return;
			}
			trackPuzzleUndo();
			puzzle[row - 1,col - 1] = digit;
		}
		/*
		 * ========================================
		 *             Options Menu
		 * ========================================
		 */
		/**
		 * Options menu loop
		 */
		private void loopMenuOptions() {
			int selItem;
			Menu menu = new Menu(MenuData.OPTIONS_TITLE, MenuData.OPTIONS_CONTENT, this);
			do {
				selItem = menu.getItem();
				switch (selItem) {
				case MenuData.OPTIONS_RND_SEED_ON_EMPTY_CELL: rndSeedOnCells = !rndSeedOnCells; break;
				case MenuData.OPTIONS_RND_SEED_ON_FREE_DIGIT: rndSeedOnDigits = !rndSeedOnDigits; break;
				case MenuData.UNDO: performPuzzleUndo(); break;
				case MenuData.REDO: performPuzzleRedo(); break;
				default: incorrectSelection(); break;
				}
			} while (selItem != MenuData.RETURN);
		}
		/**
		 * Sets solver options.
		 *
		 * @see SudokuSolver#enableRndSeedOnEmptyCells()
		 * @see SudokuSolver#enableRndSeedOnFreeDigits()
		 * @see SudokuSolver#disableRndSeedOnEmptyCells()
		 * @see SudokuSolver#disableRndSeedOnFreeDigits()
		 */
		private void setSolverOptions() {
			if (solver != null) {
				if (rndSeedOnCells) solver.enableRndSeedOnEmptyCells();
				else solver.disableRndSeedOnEmptyCells();
				if (rndSeedOnDigits) solver.enableRndSeedOnFreeDigits();
				else solver.disableRndSeedOnFreeDigits();
			}
		}
		/**
		 * Sets generator options.
		 *
		 * @see SudokuGenerator#enableRndSeedOnFilledCells()
		 * @see SudokuGenerator#disableRndSeedOnFilledCells()
		 */
		private void setGeneratorOptions() {
			if (generator != null) {
				if (rndSeedOnCells) generator.enableRndSeedOnFilledCells();
				else generator.disableRndSeedOnFilledCells();
			}
		}
		/*
		 * ========================================
		 *            Undo / Redo Menu
		 * ========================================
		 */
		/**
		 * Performs puzzle undo.
		 */
		private void performPuzzleUndo() {
			if (puzzleUndo != null) {
				trackPuzzleRedo();
				puzzle = puzzleUndo;
				puzzleUndo = null;
			}
		}
		/**
		 * Performs puzzle redo.
		 */
		private void performPuzzleRedo() {
			if (puzzleRedo != null) {
				trackPuzzleUndo();
				puzzle = puzzleRedo;
				puzzleRedo = null;
			}
		}
		/**
		 * Tracks puzzle undo.
		 */
		private void trackPuzzleUndo() {
			puzzleUndo = SudokuStore.boardCopy(puzzle);
		}
		/**
		 * Tracks puzzle redo.
		 */
		private void trackPuzzleRedo() {
			puzzleRedo = SudokuStore.boardCopy(puzzle);
		}
		/*
		 * ========================================
		 *              About info
		 * ========================================
		 */
		/**
		 * Displays info about this app.
		 */
		private void displayAboutInto() {
			JanetConsole.println(">>>");
			JanetConsole.println(">>> Janet-Sudoku Demo App based on the Janet-Sudoku Library.");
			JanetConsole.println(">>>");
			JanetConsole.println(">>>                                  Author: Mariusz Gromada");
			JanetConsole.println(">>>                             mariusz.gromada@mathspace.pl");
			JanetConsole.println(">>>                    http://janetsudoku.mariuszgromada.org");
			JanetConsole.println(">>>");
			JanetConsole.println(">>>                                Demo App version: v." + VERSION);
			JanetConsole.println(">>>                            Janet-Sudoku version: v." + SudokuStore.JANET_SUDOKU_VERSION);
			JanetConsole.println(">>>");
		}
		/*
		 * ========================================
		 *               Quit from app
		 * ========================================
		 */
		/**
		 * Displays info on app quit.
		 */
		private void quitFromApp() {
			JanetConsole.println(">>>");
			JanetConsole.println(">>>                        Thank you for using Janet-Sudoku!");
			JanetConsole.println(">>>");
			JanetConsole.println(">>>                                            Please visit:");
			JanetConsole.println(">>>                    http://janetsudoku.mariuszgromada.org");
			JanetConsole.println(">>>                                    http://mathparser.org");
			JanetConsole.println(">>>                                      http://mathspace.pl");
			JanetConsole.println(">>>");
			JanetConsole.println(">>>                                          Mariusz Gromada");
			JanetConsole.println(">>>                             mariusz.gromada@mathspace.pl");
		}
		/**
		 * Error - when incorrect selection.
		 */
		private void incorrectSelection() {
			JanetConsole.println("Error - unrecognized menu item.");
		}
		/**
		 * Start the Janet-Sudoku Demp app.
		 */
		public void startApp() {
			loopMenuMain();
		}
		/**
		 * Start the Janet-Sudoku Demp app.
		 */
		public static void Start() {
			JanetSudoku js = new JanetSudoku();
			js.startApp();
		}
		/**
		 * Start the Janet-Sudoku Demp app.
		 *
		 * @param    args   Not used
		 */
		public static void Main(String[] args) {
			JanetSudoku js = new JanetSudoku();
			js.startApp();
		}
		/**
		 * Print current puzzle to the console
		 */
		internal void consolePrintPuzzle() {
			JanetConsole.println();
			JanetConsole.print(">>> Random seed option - empty cells = " + rndSeedOnCells + ", free digits = " + rndSeedOnDigits);
			SudokuStore.consolePrintBoard(puzzle);
		}
	}
}