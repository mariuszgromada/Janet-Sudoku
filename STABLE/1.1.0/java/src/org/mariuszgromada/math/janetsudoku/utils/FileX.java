/*
 * @(#)FileX.java        1.0.0    2016-04-16
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
package org.mariuszgromada.math.janetsudoku.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.mariuszgromada.math.janetsudoku.SudokuStore;


/**
 * Class implements general purpose methods
 * helping to work File object.
 *
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
public final class FileX {
	/**
	 * Reads file lines into separate strings stored in ArrayList.
	 *
	 * @param file            File object containing file definition.
	 * @return                If file reading was successful returns
	 *                        ArrayList<String> containing each lines
	 *                        from the file content, otherwise
	 *                        returns null. Method do not throws
	 *                        IOException.
	 */
	public static final ArrayList<String> readFileLines2ArraList(File file) {
		ArrayList<String> fileContent = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			try {
				fileContent = new ArrayList<String>();
				String line = null;
				while ((line = br.readLine()) != null) {
					fileContent.add(line);
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileContent;
	}
	/**
	 * Reads file lines into separate strings stored in ArrayList.
	 *
	 * @param filePath        Full path to the file.
	 * @return                If file reading was successful returns
	 *                        ArrayList<String> containing each lines
	 *                        from the file content, otherwise
	 *                        returns null. Method do not throws
	 *                        IOException.
	 */
	public static final ArrayList<String> readFileLines2ArraList(String filePath) {
		return readFileLines2ArraList(new File(filePath));
	}
	/**
	 * Writes the given string into the the given file, previous file
	 * content will be overwritten.
	 *
	 * @param file            File object containing file definition.
	 * @param content         String containing content to be written.
	 * @return                True if write was successful, otherwise
	 *                        returns false. Method do not throws
	 *                        IOException.
	 */
	public static boolean writeFile(File file, String content) {
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			fw.write(content);
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Writes the given string into the the given file, previous file
	 * content will be overwritten.
	 *
	 * @param filePath        Full path to the file.
	 * @param content         String containing content to be written.
	 * @return                True if write was successful, otherwise
	 *                        returns false. Method do not throws
	 *                        IOException.
	 */
	public static boolean writeFile(String filePath, String content) {
		return writeFile( new File(filePath), content);
	}
	/**
	 * Removes file denoted as file path.
	 *
	 * @param filePath         File pathname.
	 * @return                 True if file was removed, false otherwise.
	 */
	public static final boolean removeFile(String filePath) {
		if (filePath == null) return false;
		if (filePath.length() == 0) return false;
		File file = new File(filePath);
		if ( file.isFile() == false) return false;
		return file.delete();
	}
	/**
	 * Returns temporary directory location.
	 * @return Temporary directory location.
	 */
	public static final String getTmpDir() {
		String tmpDir = System.getProperty("java.io.tmpdir");
		File tmp = new File(tmpDir);
		return tmp.getAbsolutePath() + File.separator;
	}
	/**
	 * Generates random file name.
	 * @param length    File name length (without extension).
	 * @param fileExt   File extension.
	 * @return          Random file name containing a-zA-Z0-9.
	 */
	public static final String genRndFileName(int length, String fileExt) {
		if (length <= 0) return null;
		if (fileExt == null) return null;
		return randomString(length) + "." + fileExt;
	}
	/**
	 * Random string generator.
	 * @param length    Length of random string.
	 * @return          Random string containing a-zA-Z0-9.
	 */
	public static final String randomString(int length) {
		if (length < 1) return "";
		char[] chars = {
							'z','x','c','v','b','n','m','a','s','d','f','g','h','j','k','l','q','w','e','r','t','y','u','i','o','p',
							'Z','X','C','V','B','N','M','A','S','D','F','G','H','J','K','L','Q','W','E','R','T','Y','U','I','O','P',
							'0','1','2','3','4','5','6','7','8','9'
				};
		String rndStr = "";
		for (int i = 0; i < length; i++)
			rndStr = rndStr + chars[ SudokuStore.randomIndex(chars.length) ];
		return rndStr;
	}

}