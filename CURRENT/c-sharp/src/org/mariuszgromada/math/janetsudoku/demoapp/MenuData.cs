/*
 * @(#)MenuData.cs        1.0.0    2016-04-15
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
using System;

namespace org.mariuszgromada.math.janetsudoku.demoapp {
	/**
	 * Static package level class providing command line menu data.
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
	 */
	internal sealed class MenuData {
		/**
		 * Create menu item string definition
		 * @param itemId             Item id on menu
		 * @param itemDescr          Item description
		 * @return                   String containing item menu.
		 */
		private static String menuItem1(int itemId, String itemDescr) {
			return itemId + ". " + itemDescr;
		}
		/**
		 * Create menu item string definition
		 * @param itemId             Item id on menu
		 * @param itemDescr          Item description
		 * @return                   String containing item menu.
		 */
		private static String menuItem2(int itemId, String itemDescr) {
			return " " + itemId + ". " + itemDescr;
		}
		/**
		 * Main menu
		 */
		internal const String MAIN_TITLE								= "Main menu";
		internal const int UNDO 										= 1;
		internal const int REDO											= 2;
		internal const int LOAD											= 3;
		internal const int GENERATE										= 4;
		internal const int INPUT										= 5;
		internal const int MODIFY										= 6;
		internal const int EVALUATE										= 7;
		internal const int SOLVE										= 8;
		internal const int SAVE											= 9;
		internal const int OPTIONS										= 10;
		internal const int ABOUT										= 11;
		internal const int RETURN										= 0;
		internal static String[] MAIN_CONTENT = {
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
		internal const String LOAD_TITLE								= "Load puzzle";
		internal const int LOAD_FROM_FILE								= 3;
		internal const int LOAD_EXAMPLE									= 4;
		internal const int LOAD_EMPTY_PUZZLE							= 5;
		internal const int LOAD_LIST_EXAMPLES							= 6;
		internal static String[] LOAD_CONTENT = {
			/* 0 */ 	menuItem1(RETURN,								"Return to main menu")
			/* 1 */ ,	menuItem1(UNDO,									"Puzzle undo")
			/* 2 */ ,	menuItem1(REDO,									"Puzzle redo")
			/* 3 */ ,	menuItem1(LOAD_FROM_FILE,						"Load from file")
			/* 4 */ ,	menuItem1(LOAD_EXAMPLE,							"Load example")
			/* 5 */ ,	menuItem1(LOAD_EMPTY_PUZZLE,					"Load empty puzzle")
			/* 6 */ ,   menuItem1(LOAD_LIST_EXAMPLES,                   "List puzzle examples")
		};
		/**
		 * Main menu
		 */
		internal const String GENERATE_TITLE							= "Generate puzzle";
		internal const int GENERATE_RANDOM								= 3;
		internal const int GENERATE_RANDOM_PLUS_RATING					= 4;
		internal const int GENERATE_BASED_ON_EXAMPLE					= 5;
		internal const int GENERATE_BASED_ON_CURRENT_PUZZLE				= 6;
		internal static String[] GENERATE_CONTENT = {
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
		internal const String INPUT_TITLE								= "Input puzzle";
		internal const int INPUT_ONE_LINE								= 3;
		internal const int INPUT_9ROWS									= 4;
		internal const int INPUT_11ROWS									= 5;
		internal const int INPUT_13ROWS									= 6;
		internal static String[] INPUT_CONTENT = {
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
		internal const String MODIFY_TITLE								= "Modify puzzle";
		internal const int MODIFY_SET_CELL								= 3;
		internal const int MODIFY_ROTATE_CLOCK_WISE						= 4;
		internal const int MODIFY_ROTATE_COUNTER_CLOCK_WISE				= 5;
		internal const int MODIFY_TRANSPOSE_TL_BR						= 6;
		internal const int MODIFY_TRANSPOSE_TR_BL						= 7;
		internal const int MODIFY_REFLECT_HORIZ							= 8;
		internal const int MODIFY_REFLECT_VERT							= 9;
		internal const int MODIFY_SWAP_COL_SEGMENTS						= 10;
		internal const int MODIFY_SWAP_ROW_SEGMENTS						= 11;
		internal const int MODIFY_SWAP_COLS_IN_SEGMENTS					= 12;
		internal const int MODIFY_SWAP_ROWS_IN_SEGMENTS					= 13;
		internal const int MODIFY_PERMUTE								= 14;
		internal const int MODIFY_RANDOM_TRANSF_ONE						= 15;
		internal const int MODIFY_RANDOM_TRANSF_SEQ						= 16;
		internal static String[] MODIFY_CONTENT = {
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
		internal const String EVALUATE_TITLE							= "Evaluate puzzle";
		internal const int EVALUATE_SOLUTION_EXISTENCE					= 3;
		internal const int EVALUATE_PUZZLE_DIFFICULTY					= 4;
		internal static String[] EVALUATE_CONTENT = {
			/* 0 */ 	menuItem1(RETURN,								"Return to main menu")
			/* 1 */ ,	menuItem1(UNDO,									"Puzzle undo")
			/* 2 */ ,	menuItem1(REDO,									"Puzzle redo")
			/* 3 */ ,	menuItem1(EVALUATE_SOLUTION_EXISTENCE,			"Evaluate solution existence")
			/* 4 */ ,	menuItem1(EVALUATE_PUZZLE_DIFFICULTY,			"Rate puzzle difficulty")
		};
		/**
		 * Solve menu
		 */
		internal const String SOLVE_TITLE								= "Solve puzzle";
		internal const int SOLVE_FIND_FIRST								= 3;
		internal const int SOLVE_FIND_ALL								= 4;
		internal static String[] SOLVE_CONTENT = {
			/* 0 */ 	menuItem1(RETURN,								"Return to main menu")
			/* 1 */ ,	menuItem1(UNDO,									"Puzzle undo")
			/* 2 */ ,	menuItem1(REDO,									"Puzzle redo")
			/* 3 */ ,	menuItem1(SOLVE_FIND_FIRST,						"Find first solution")
			/* 4 */ ,	menuItem1(SOLVE_FIND_ALL,						"Find all solutions")
		};
		/**
		 * Save menu
		 */
		internal const String SAVE_TITLE = "";
		internal static String[] SAVE_CONTENT = {
		};
		/**
		 * Undo menu
		 */
		internal const String UNDO_TITLE = "Puzzle undo";
		internal static String[] UNDO_CONTENT = {
		};
		/**
		 * Redo menu
		 */
		internal const String REDO_TITLE = "Puzzle redo";
		internal static String[] REDO_CONTENT = {
		};
		/**
		 * Options menu
		 */
		internal const String OPTIONS_TITLE								= "Options";
		internal const int OPTIONS_RND_SEED_ON_EMPTY_CELL				= 3;
		internal const int OPTIONS_RND_SEED_ON_FREE_DIGIT				= 4;
		internal static String[] OPTIONS_CONTENT = {
			/* 0 */ 	menuItem1(RETURN,								"Return to main menu")
			/* 1 */ ,	menuItem1(UNDO,									"Puzzle undo")
			/* 2 */ ,	menuItem1(REDO,									"Puzzle redo")
			/* 3 */ ,	menuItem1(OPTIONS_RND_SEED_ON_EMPTY_CELL,		"Switch on/off random seed on cells")
			/* 4 */ ,	menuItem1(OPTIONS_RND_SEED_ON_FREE_DIGIT,		"Switch on/off random seed on digits")
		};
		/**
		 * About menu
		 */
		internal const String ABOUT_TITLE = "";
		internal static String[] ABOUT_CONTENT = {
		};
		/**
		 * Return / quit menu
		 */
		internal const String RETURN_TITLE = "";
		internal static String[] RETURN_CONTENT = {
		};
	}
}