/*
 * @(#)MenuData.java        1.1.0    2016-04-16
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
package org.mariuszgromada.math.janetsudoku.demoapp;

/**
 * Static package level class providing command line menu data.
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
final class MenuData {
	/**
	 * Create menu item string definition
	 * @param itemId             Item id on menu
	 * @param itemDescr          Item description
	 * @return                   String containing item menu.
	 */
	private static final String menuItem1(int itemId, String itemDescr) {
		return itemId + ". " + itemDescr;
	}
	/**
	 * Create menu item string definition
	 * @param itemId             Item id on menu
	 * @param itemDescr          Item description
	 * @return                   String containing item menu.
	 */
	private static final String menuItem2(int itemId, String itemDescr) {
		return " " + itemId + ". " + itemDescr;
	}
	/**
	 * Main menu
	 */
	static final String MAIN_TITLE									= "Main menu";
	static final int UNDO 											= 1;
	static final int REDO											= 2;
	static final int LOAD											= 3;
	static final int GENERATE										= 4;
	static final int INPUT											= 5;
	static final int MODIFY											= 6;
	static final int EVALUATE										= 7;
	static final int SOLVE											= 8;
	static final int SAVE											= 9;
	static final int OPTIONS										= 10;
	static final int ABOUT											= 11;
	static final int RETURN											= 0;
	static final String[] MAIN_CONTENT = {
		/* 0 */ 	menuItem2(RETURN,								"Quit Janet-Sudoku Demo App")
		/* 1 */ ,	menuItem2(UNDO,									"Puzzle undo")
		/* 2 */ ,	menuItem2(REDO,									"Puzzle redo")
		/* 3 */ ,	menuItem2(LOAD,									"Load puzzle ...")
		/* 4 */ ,	menuItem2(GENERATE,								"Generate puzzle ...")
		/* 5 */ ,	menuItem2(INPUT,								"Input puzzle ...")
		/* 6 */ ,	menuItem2(MODIFY,								"Modify puzzle ...")
		/* 7 */ ,	menuItem2(EVALUATE,								"Evaluate puzzle ...")
		/* 8 */ ,	menuItem2(SOLVE,								"Solve puzzle ...")
		/* 9 */ ,	menuItem2(SAVE,									"Save puzzle")
		/* 10 */ ,	menuItem1(OPTIONS,								"Options ...")
		/* 11 */ ,	menuItem1(ABOUT,								"About Janet-Sudoku")
	};
	/**
	 * Load menu
	 */
	static final String LOAD_TITLE									= "Load puzzle";
	static final int LOAD_FROM_FILE									= 3;
	static final int LOAD_EXAMPLE									= 4;
	static final int LOAD_EMPTY_PUZZLE								= 5;
	static final int LOAD_LIST_EXAMPLES								= 6;
	static final String[] LOAD_CONTENT = {
		/* 0 */ 	menuItem1(RETURN,								"Return to main menu")
		/* 1 */ ,	menuItem1(UNDO,									"Puzzle undo")
		/* 2 */ ,	menuItem1(REDO,									"Puzzle redo")
		/* 3 */ ,	menuItem1(LOAD_FROM_FILE,						"Load from file")
		/* 4 */ ,	menuItem1(LOAD_EXAMPLE,							"Load example")
		/* 5 */ ,	menuItem1(LOAD_EMPTY_PUZZLE,					"Load empty puzzle")
		/* 6 */ ,	menuItem1(LOAD_LIST_EXAMPLES,					"List puzzle examples")
	};
	/**
	 * Main menu
	 */
	static final String GENERATE_TITLE								= "Generate puzzle";
	static final int GENERATE_RANDOM								= 3;
	static final int GENERATE_RANDOM_PLUS_RATING					= 4;
	static final int GENERATE_BASED_ON_EXAMPLE						= 5;
	static final int GENERATE_BASED_ON_CURRENT_PUZZLE				= 6;
	static final String[] GENERATE_CONTENT = {
		/* 0 */ 	menuItem1(RETURN,								"Return to main menu")
		/* 1 */ ,	menuItem1(UNDO,									"Puzzle undo")
		/* 2 */ ,	menuItem1(REDO,									"Puzzle redo")
		/* 3 */ ,	menuItem1(GENERATE_RANDOM,						"Random")
		/* 4 */ ,	menuItem1(GENERATE_RANDOM_PLUS_RATING,			"Random + difficulty rating")
		/* 5 */ ,	menuItem1(GENERATE_BASED_ON_EXAMPLE,			"Based on example")
		/* 6 */ ,	menuItem1(GENERATE_BASED_ON_CURRENT_PUZZLE,		"Based on current puzzle")
	};
	/**
	 * Input menu
	 */
	static final String INPUT_TITLE									= "Input puzzle";
	static final int INPUT_ONE_LINE									= 3;
	static final int INPUT_9ROWS									= 4;
	static final int INPUT_11ROWS									= 5;
	static final int INPUT_13ROWS									= 6;
	static final String[] INPUT_CONTENT = {
			/* 0 */ 	menuItem1(RETURN,							"Return to main menu")
			/* 1 */ ,	menuItem1(UNDO,								"Puzzle undo")
			/* 2 */ ,	menuItem1(REDO,								"Puzzle redo")
			/* 3 */ ,	menuItem1(INPUT_ONE_LINE,					"One line string")
			/* 4 */ ,	menuItem1(INPUT_9ROWS,						"Separate strings for each row - 9 rows")
			/* 5 */ ,	menuItem1(INPUT_11ROWS,						"Separate strings for each row - 11 rows")
			/* 6 */ ,	menuItem1(INPUT_13ROWS,						"Separate strings for each row - 13 rows")
	};
	/**
	 * Modify menu
	 */
	static final String MODIFY_TITLE								= "Modify puzzle";
	static final int MODIFY_SET_CELL								= 3;
	static final int MODIFY_ROTATE_CLOCK_WISE						= 4;
	static final int MODIFY_ROTATE_COUNTER_CLOCK_WISE				= 5;
	static final int MODIFY_TRANSPOSE_TL_BR							= 6;
	static final int MODIFY_TRANSPOSE_TR_BL							= 7;
	static final int MODIFY_REFLECT_HORIZ							= 8;
	static final int MODIFY_REFLECT_VERT							= 9;
	static final int MODIFY_SWAP_COL_SEGMENTS						= 10;
	static final int MODIFY_SWAP_ROW_SEGMENTS						= 11;
	static final int MODIFY_SWAP_COLS_IN_SEGMENTS					= 12;
	static final int MODIFY_SWAP_ROWS_IN_SEGMENTS					= 13;
	static final int MODIFY_PERMUTE									= 14;
	static final int MODIFY_RANDOM_TRANSF_ONE						= 15;
	static final int MODIFY_RANDOM_TRANSF_SEQ						= 16;
	static final String[] MODIFY_CONTENT = {
		/*  0 */ 	menuItem2(RETURN,								"Return to main menu")
		/*  1 */ ,	menuItem2(UNDO,									"Puzzle undo")
		/*  2 */ ,	menuItem2(REDO,									"Puzzle redo")
		/*  3 */ ,	menuItem2(MODIFY_SET_CELL,						"Set cell digit")
		/*  4 */ ,	menuItem2(MODIFY_ROTATE_CLOCK_WISE,				"Rotate clock wise")
		/*  5 */ ,	menuItem2(MODIFY_ROTATE_COUNTER_CLOCK_WISE,		"Rotate counter clock wise")
		/*  6 */ ,	menuItem2(MODIFY_TRANSPOSE_TL_BR,				"Transpose Top-Left -> Bottom-Right")
		/*  7 */ ,	menuItem2(MODIFY_TRANSPOSE_TR_BL,				"Transpose Top-Right -> Bottom-Left")
		/*  8 */ ,	menuItem2(MODIFY_REFLECT_HORIZ,					"Reflect Horizontally")
		/*  9 */ ,	menuItem2(MODIFY_REFLECT_VERT,					"Reflect Vertically")
		/* 10 */ ,	menuItem1(MODIFY_SWAP_COL_SEGMENTS,				"Swap column segments (random)")
		/* 11 */ ,	menuItem1(MODIFY_SWAP_ROW_SEGMENTS,				"Swap row segments (random)")
		/* 12 */ ,	menuItem1(MODIFY_SWAP_COLS_IN_SEGMENTS,			"Swap columns within segments (randomly)")
		/* 13 */ ,	menuItem1(MODIFY_SWAP_ROWS_IN_SEGMENTS,			"Swap rows within segments (randomly)")
		/* 14 */ ,	menuItem1(MODIFY_PERMUTE,						"Puzzle permutation (random)")
		/* 15 */ ,	menuItem1(MODIFY_RANDOM_TRANSF_ONE,				"Random transformation - one")
		/* 16 */ ,	menuItem1(MODIFY_RANDOM_TRANSF_SEQ,				"Random transformation - sequence")
	};
	/**
	 * Evaluate menu
	 */
	static final String EVALUATE_TITLE								= "Evaluate puzzle";
	static final int EVALUATE_SOLUTION_EXISTENCE					= 3;
	static final int EVALUATE_PUZZLE_DIFFICULTY						= 4;
	static final String[] EVALUATE_CONTENT = {
		/* 0 */ 	menuItem1(RETURN,								"Return to main menu")
		/* 1 */ ,	menuItem1(UNDO,									"Puzzle undo")
		/* 2 */ ,	menuItem1(REDO,									"Puzzle redo")
		/* 3 */ ,	menuItem1(EVALUATE_SOLUTION_EXISTENCE,			"Evaluate solution existence")
		/* 4 */ ,	menuItem1(EVALUATE_PUZZLE_DIFFICULTY,			"Rate puzzle difficulty")
	};
	/**
	 * Solve menu
	 */
	static final String SOLVE_TITLE									= "Solve puzzle";
	static final int SOLVE_FIND_FIRST								= 3;
	static final int SOLVE_FIND_ALL									= 4;
	static final String[] SOLVE_CONTENT = {
		/* 0 */ 	menuItem1(RETURN,								"Return to main menu")
		/* 1 */ ,	menuItem1(UNDO,									"Puzzle undo")
		/* 2 */ ,	menuItem1(REDO,									"Puzzle redo")
		/* 3 */ ,	menuItem1(SOLVE_FIND_FIRST,						"Find first solution")
		/* 4 */ ,	menuItem1(SOLVE_FIND_ALL,						"Find all solutions")
	};
	/**
	 * Save menu
	 */
	static final String SAVE_TITLE = "";
	static final String[] SAVE_CONTENT = {
	};
	/**
	 * Undo menu
	 */
	static final String UNDO_TITLE = "Puzzle undo";
	static final String[] UNDO_CONTENT = {
	};
	/**
	 * Redo menu
	 */
	static final String REDO_TITLE = "Puzzle redo";
	static final String[] REDO_CONTENT = {
	};
	/**
	 * Options menu
	 */
	static final String OPTIONS_TITLE								= "Options";
	static final int OPTIONS_RND_SEED_ON_EMPTY_CELL					= 3;
	static final int OPTIONS_RND_SEED_ON_FREE_DIGIT					= 4;
	static final String[] OPTIONS_CONTENT = {
		/* 0 */ 	menuItem1(RETURN,								"Return to main menu")
		/* 1 */ ,	menuItem1(UNDO,									"Puzzle undo")
		/* 2 */ ,	menuItem1(REDO,									"Puzzle redo")
		/* 3 */ ,	menuItem1(OPTIONS_RND_SEED_ON_EMPTY_CELL,		"Switch on/off random seed on cells")
		/* 4 */ ,	menuItem1(OPTIONS_RND_SEED_ON_FREE_DIGIT,		"Switch on/off random seed on digits")
	};
	/**
	 * About menu
	 */
	static final String ABOUT_TITLE = "";
	static final String[] ABOUT_CONTENT = {
	};
	/**
	 * Return / quit menu
	 */
	static final String RETURN_TITLE = "";
	static final String[] RETURN_CONTENT = {
	};
}