package org.mariuszgromada.math.janetsudoku.demoapp;

final class MenuData {

	private static final String menuItem1(int itemId, String itemDescr) {
		return itemId + ". " + itemDescr;
	}
	private static final String menuItem2(int itemId, String itemDescr) {
		return " " + itemId + ". " + itemDescr;
	}

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
		/* 0 */ 	menuItem2(RETURN,								"Quit Janet-Sudoku")
		/* 1 */ ,	menuItem2(UNDO,									"Undo")
		/* 2 */ ,	menuItem2(REDO,									"Redo")
		/* 3 */ ,	menuItem2(LOAD,									"Load ...")
		/* 4 */ ,	menuItem2(GENERATE,								"Generate ...")
		/* 5 */ ,	menuItem2(INPUT,								"Input ...")
		/* 6 */ ,	menuItem2(MODIFY,								"Modify ...")
		/* 7 */ ,	menuItem2(EVALUATE,								"Evaluate ...")
		/* 8 */ ,	menuItem2(SOLVE,								"Solve ...")
		/* 9 */ ,	menuItem2(SAVE,									"Save")
		/* 10 */ ,	menuItem1(OPTIONS,								"Options ...")
		/* 11 */ ,	menuItem1(ABOUT,								"About")
	};

	static final String LOAD_TITLE									= "Load";
	static final int LOAD_FROM_FILE									= 3;
	static final int LOAD_EXAMPLE									= 4;
	static final int LOAD_EMPTY_PUZZLE								= 5;
	static final String[] LOAD_CONTENT = {
		/* 0 */ 	menuItem1(RETURN,								"Return to main menu")
		/* 1 */ ,	menuItem1(UNDO,									"Undo")
		/* 2 */ ,	menuItem1(REDO,									"Redo")
		/* 3 */ ,	menuItem1(LOAD_FROM_FILE,						"Load from file")
		/* 4 */ ,	menuItem1(LOAD_EXAMPLE,							"Load example")
		/* 5 */ ,	menuItem1(LOAD_EMPTY_PUZZLE,					"Load empty puzzle")
	};

	static final String GENERATE_TITLE								= "Generate";
	static final int GENERATE_RANDOM								= 3;
	static final int GENERATE_RANDOM_PLUS_RATING					= 4;
	static final int GENERATE_BASED_ON_EXAMPLE						= 5;
	static final int GENERATE_BASED_ON_CURRENT_PUZZLE				= 6;
	static final String[] GENERATE_CONTENT = {
		/* 0 */ 	menuItem1(RETURN,								"Return to main menu")
		/* 1 */ ,	menuItem1(UNDO,									"Undo")
		/* 2 */ ,	menuItem1(REDO,									"Redo")
		/* 3 */ ,	menuItem1(GENERATE_RANDOM,						"Random")
		/* 4 */ ,	menuItem1(GENERATE_RANDOM_PLUS_RATING,			"Random + difficulty rating")
		/* 5 */ ,	menuItem1(GENERATE_BASED_ON_EXAMPLE,			"Based on example")
		/* 6 */ ,	menuItem1(GENERATE_BASED_ON_CURRENT_PUZZLE,		"Based on current puzzle")
	};

	static final String INPUT_TITLE									= "Puzzle input";
	static final int INPUT_ONE_LINE									= 3;
	static final int INPUT_9ROWS									= 4;
	static final int INPUT_11ROWS									= 5;
	static final int INPUT_13ROWS									= 6;
	static final String[] INPUT_CONTENT = {
			/* 0 */ 	menuItem1(RETURN,							"Return to main menu")
			/* 1 */ ,	menuItem1(UNDO,								"Undo")
			/* 2 */ ,	menuItem1(REDO,								"Redo")
			/* 3 */ ,	menuItem1(INPUT_ONE_LINE,					"One line string")
			/* 4 */ ,	menuItem1(INPUT_9ROWS,						"Separate strings for each row - 9 rows")
			/* 5 */ ,	menuItem1(INPUT_11ROWS,						"Separate strings for each row - 11 rows")
			/* 6 */ ,	menuItem1(INPUT_13ROWS,						"Separate strings for each row - 13 rows")
	};

	static final String MODIFY_TITLE								= "Modify";
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
		/*  1 */ ,	menuItem2(UNDO,									"Undo")
		/*  2 */ ,	menuItem2(REDO,									"Redo")
		/*  3 */ ,	menuItem2(MODIFY_SET_CELL,						"Set cell digit")
		/*  4 */ ,	menuItem2(MODIFY_ROTATE_CLOCK_WISE,				"Rotate clock wise")
		/*  5 */ ,	menuItem2(MODIFY_ROTATE_COUNTER_CLOCK_WISE,		"Rotate cunter clock wise")
		/*  6 */ ,	menuItem2(MODIFY_TRANSPOSE_TL_BR,				"Transpose Top-Left -> Bottom-Right")
		/*  7 */ ,	menuItem2(MODIFY_TRANSPOSE_TR_BL,				"Transpose Top-Right -> Bottom-Left")
		/*  8 */ ,	menuItem2(MODIFY_REFLECT_HORIZ,					"Reflect Horizontally")
		/*  9 */ ,	menuItem2(MODIFY_REFLECT_VERT,					"Reflect Vertically")
		/* 10 */ ,	menuItem1(MODIFY_SWAP_COL_SEGMENTS,				"Swap column segmets (random)")
		/* 11 */ ,	menuItem1(MODIFY_SWAP_ROW_SEGMENTS,				"Swap row segmets (random)")
		/* 12 */ ,	menuItem1(MODIFY_SWAP_COLS_IN_SEGMENTS,			"Swap columns within segments (randomly)")
		/* 13 */ ,	menuItem1(MODIFY_SWAP_ROWS_IN_SEGMENTS,			"Swap rows within segments (randomly)")
		/* 14 */ ,	menuItem1(MODIFY_PERMUTE,						"Puzzle permutation (random)")
		/* 15 */ ,	menuItem1(MODIFY_RANDOM_TRANSF_ONE,				"Random transformation - one")
		/* 16 */ ,	menuItem1(MODIFY_RANDOM_TRANSF_SEQ,				"Random transformation - sequance")
	};

	static final String EVALUATE_TITLE								= "Evaluate";
	static final int EVALUATE_SOLUTION_EXISTENCE					= 3;
	static final int EVALUATE_PUZZLE_DIFFICULTY						= 4;
	static final String[] EVALUATE_CONTENT = {
		/* 0 */ 	menuItem1(RETURN,								"Return to main menu")
		/* 1 */ ,	menuItem1(UNDO,									"Undo")
		/* 2 */ ,	menuItem1(REDO,									"Redo")
		/* 3 */ ,	menuItem1(EVALUATE_SOLUTION_EXISTENCE,			"Evaluate solution existence")
		/* 4 */ ,	menuItem1(EVALUATE_PUZZLE_DIFFICULTY,			"Rate puzzle difficulty")
	};

	static final String SOLVE_TITLE									= "Solve";
	static final int SOLVE_FIND_FIRST								= 3;
	static final int SOLVE_FIND_ALL									= 4;
	static final String[] SOLVE_CONTENT = {
		/* 0 */ 	menuItem1(RETURN,								"Return to main menu")
		/* 1 */ ,	menuItem1(UNDO,									"Undo")
		/* 2 */ ,	menuItem1(REDO,									"Redo")
		/* 3 */ ,	menuItem1(SOLVE_FIND_FIRST,						"Find first solution")
		/* 4 */ ,	menuItem1(SOLVE_FIND_ALL,						"Find all solutions")
	};

	static final String SAVE_TITLE = "";
	static final String[] SAVE_CONTENT = {
	};

	static final String UNDO_TITLE = "Undo";
	static final String[] UNDO_CONTENT = {
	};

	static final String REDO_TITLE = "Redo";
	static final String[] REDO_CONTENT = {
	};

	static final String OPTIONS_TITLE								= "Options";
	static final int OPTIONS_RND_SEED_ON_EMPTY_CELL					= 4;
	static final int OPTIONS_RND_SEED_ON_FREE_DIGIT					= 5;
	static final String[] OPTIONS_CONTENT = {
		/* 0 */ 	menuItem1(RETURN,								"Return to main menu")
		/* 1 */ ,	menuItem1(UNDO,									"Undo")
		/* 2 */ ,	menuItem1(REDO,									"Redo")
		/* 3 */ ,	menuItem1(OPTIONS_RND_SEED_ON_EMPTY_CELL,		"Switch on/off random seed on empty cell")
		/* 4 */ ,	menuItem1(OPTIONS_RND_SEED_ON_FREE_DIGIT,		"Switch on/off random seed on free digit")
	};

	static final String ABOUT_TITLE = "";
	static final String[] ABOUT_CONTENT = {
	};

	static final String RETURN_TITLE = "";
	static final String[] RETURN_CONTENT = {
	};
}

