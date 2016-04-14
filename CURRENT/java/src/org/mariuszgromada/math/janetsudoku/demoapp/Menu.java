package org.mariuszgromada.math.janetsudoku.demoapp;

class Menu {
	private static final int INCORRECT_ITEM = -1;
	String title;
	int itemsNum;
	String[] content;
	JanetSudoku janetSudoku;
	Menu(String title, String[] content, JanetSudoku janetSudoku) {
		this.title = title;
		this.itemsNum = content.length - 1;
		this.content = content;
		this.janetSudoku = janetSudoku;
	}
	void consolePrintMenue() {
		//System.out.println(JanetSudoku.ABOUT + " - v." + JanetSudoku.VERSION);
		//System.out.println("by " + JanetSudoku.AUTHOR);
		janetSudoku.consolePrintPuzzle();
		System.out.println("----- " + title + " -----");
		for (int i = 3; i <= itemsNum; i++) {
			System.out.println(content[i]);
		}
		System.out.println("----- " + "General" + " -----");
		System.out.println(content[1]);
		System.out.println(content[2]);
		System.out.println(content[0]);
	}
	int getItem() {
		int selItem = 0;
		boolean loop = true;
		do {
			consolePrintMenue();
			System.out.println();
			System.out.print("Your selection: ");
			selItem = JanetSudoku.consoleReadInt();
			if ((selItem >= 0) && (selItem <= itemsNum)) loop = false;
			else System.out.println(">>> !!! Please select correct menu item. !!! <<<");
		} while (loop == true);
		return selItem;
	}
}
