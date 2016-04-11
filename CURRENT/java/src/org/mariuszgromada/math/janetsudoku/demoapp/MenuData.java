package org.mariuszgromada.math.janetsudoku.demoapp;

final class MenuData {
	static final String MAIN_TITLE = "Main menu";
	static final String[] MAIN_CONTENT = {
		/* 0 */ "Quit from application"
		/* 1 */ ,"Load"
		/* 2 */ ,"Generate"
		/* 3 */ ,"Input"
		/* 4 */ ,"Modify"
		/* 5 */ ,"Evaluate"
		/* 6 */ ,"Solve"
		/* 7 */ ,"Save"
		/* 8 */ ,"Options"
		/* 9 */ ,"About"
	};

	static final int LOAD = 1;
	static final int LOAD_FROM_FILE = 1;
	static final int LOAD_EXAMPLE = 2;
	static final int LOAD_EMPTY_PUZZLE = 3;
	static final String LOAD_TITLE = "Load";
	static final String[] LOAD_CONTENT = {
		/* 0 */ "Return to main menu"
		/* 1 */ ,"Load from file"
		/* 2 */ ,"Load example"
		/* 3 */ ,"Load empty puzzle"
	};

	static final int GENERATE = 2;
	static final int GENERATE_RANDOM = 1;
	static final int GENERATE_BASED_ON_EXAMPLE = 2;
	static final int GENERATE_BASED_ON_CURRENT_PUZZLE = 3;
	static final String GENERATE_TITLE = "Generate";
	static final String[] GENERATE_CONTENT = {
		/* 0 */ "Return to main menu"
		/* 1 */ ,"Random"
		/* 2 */ ,"Based on example"
		/* 3 */ ,"Based on current puzzle"
	};

	static final int INPUT = 3;
	static final String INPUT_TITLE = "";
	static final String[] INPUT_CONTENT = {
	};

	static final int MODIFY = 4;
	static final String MODIFY_TITLE = "Modify";
	static final int MODIFY_SET_CELL = 1;
	static final int MODIFY_ROTATE_CLOCK_WISE = 2;
	static final int MODIFY_ROTATE_COUNTER_CLOCK_WISE = 3;
	static final int MODIFY_TRANSPOSE_TL_BR = 4;
	static final int MODIFY_TRANSPOSE_TR_BL = 5;
	static final int MODIFY_REFLECT_HORIZ = 6;
	static final int MODIFY_REFLECT_VERT = 7;
	static final int MODIFY_SWAP_COL_SEGMENTS = 8;
	static final int MODIFY_SWAP_ROW_SEGMENTS = 9;
	static final int MODIFY_SWAP_COLS_IN_SEGMENTS = 10;
	static final int MODIFY_SWAP_ROWS_IN_SEGMENTS = 11;
	static final int MODIFY_PERMUTE = 12;
	static final int MODIFY_RANDOM_TRANSF_ONE = 13;
	static final int MODIFY_RANDOM_TRANSF_SEQ = 14;

	static final String[] MODIFY_CONTENT = {
		/*  0 */ "Return to main menu"
		/*  1 */ ,"Set cell digit"
		/*  2 */ ,"Rotate clock wise"
		/*  3 */ ,"rotate cunter clock wise"
		/*  4 */ ,"Transpose Top-Left -> Bottom-Right"
		/*  5 */ ,"Transpose Top-Right -> Bottom-Left"
		/*  6 */ ,"Reflect Horizontally"
		/*  7 */ ,"Reflect Vertically"
		/*  8 */ ,"Swap column segmets (random)"
		/*  9 */ ,"Swap row segmets (random)"
		/* 10 */ ,"Swap columns within segments (randomly)"
		/* 11 */ ,"Swap rows within segments (randomly)"
		/* 12 */ ,"Puzzle permutation (random)"
		/* 13 */ ,"Random transformation - one"
		/* 14 */ ,"Random transformation - sequance"
	};

	static final int EVALUATE = 5;
	static final int EVALUATE_SOLUTION_EXISTENCE = 1;
	static final int EVALUATE_PUZZLE_DIFFICULTY = 2;
	static final String EVALUATE_TITLE = "Evaluate";
	static final String[] EVALUATE_CONTENT = {
		/* 0 */ "Return to main menu"
		/* 1 */ ,"Evaluate solution existence"
		/* 2 */ ,"Rate puzzle difficulty"
	};

	static final int SOLVE = 6;
	static final int SOLVE_FIND_FIRST = 1;
	static final int SOLVE_FIND_ALL = 2;
	static final String SOLVE_TITLE = "Solve";
	static final String[] SOLVE_CONTENT = {
		/* 0 */ "Return to main menu"
		/* 1 */ ,"Find first solution"
		/* 2 */ ,"Find all solutions"
	};

	static final int SAVE = 7;
	static final String SAVE_TITLE = "";
	static final String[] SAVE_CONTENT = {
	};

	static final int OPTIONS = 8;
	static final int OPTIONS_RND_SEED_ON_EMPTY_CELL = 1;
	static final int OPTIONS_RND_SEED_ON_FREE_DIGIT = 2;
	static final String OPTIONS_TITLE = "Options";
	static final String[] OPTIONS_CONTENT = {
		/* 0 */ "Return to main menu"
		/* 1 */ ,"Switch on/off random seed on empty cell"
		/* 2 */ ,"Switch on/off random seed on free digit"
	};

	static final int ABOUT = 9;
	static final String ABOUT_TITLE = "";
	static final String[] ABOUT_CONTENT = {
	};

	static final int RETURN = 0;
	static final String RETURN_TITLE = "";
	static final String[] RETURN_CONTENT = {
	};
}

