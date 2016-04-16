                Janet Sudoku - Sudoku Solver & Sudoku Generator
powerful and flexible library for JAVA, Android, .NET, MONO  (C# .NET CLS Compliant)

v.1.1.0 (2016-04-16): Initial release providing

    * Sudoku Solver API
        - simple solving
		- path / sequence leading to the solution
		- evaluating solution existence
		- finding all solutions
		- puzzle / solution loading / saving
		- solving randomization options
		- error handling (error codes, messages)
	
	* Sudoku Generator API
	    - random generator
		- generator based on puzzle
		- generating randomization options
		- error handling (error codes, messages)
	
	* Store API enabling variety of operations on
	    - 160 built-in puzzle examples
		- A bunch of built-in operations enabling puzzle modification
          (i.e. rotation, reflection, swapping, permutation, ...)
		- Puzzle checking
        - Puzzle loading and saving
		- Rating puzzle difficulty
		
    * Janet Sudoku Demo App - command line tool based on the library
        - Puzzle loading
		- Puzzle generating
		- Puzzle evaluating
		- Puzzle solving
		- Puzzle rating
		- Puzzle saving

    * Regression tests
        - Large number of regression / unit tests
		
 You may use this software under the condition of Simplified BSD License:

Copyright 2016 MARIUSZ GROMADA. All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are
permitted provided that the following conditions are met:

   1. Redistributions of source code must retain the above copyright notice, this list of
      conditions and the following disclaimer.

   2. Redistributions in binary form must reproduce the above copyright notice, this list
      of conditions and the following disclaimer in the documentation and/or other materials
      provided with the distribution.

THIS SOFTWARE IS PROVIDED BY MARIUSZ GROMADA ``AS IS'' AND ANY EXPRESS OR IMPLIED
WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL MARIUSZ GROMADA OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those of the
authors and should not be interpreted as representing official policies, either expressed
or implied, of MARIUSZ GROMADA.

If you have any questions/bugs feel free to contact:

     Mariusz Gromada
     mariusz.gromada@mathspace.pl
     http://janetsudoku.mariuszgromada.org
     http://mathparser.org
     http://mathspace.pl
     http://github.com/mariuszgromada/Janet-Sudoku
     http://janetsudoku.codeplex.com
     http://sourceforge.net/projects/janetsudoku
     http://bitbucket.org/mariuszgromada/janet-sudoku
     http://github.com/mariuszgromada/MathParser.org-mXparser

JanetSudoku documentation:
doc/index.html


* Executing Janet Sudoku Demo App:
    - just use provided exe / run files or hit commands listed below:

JAVA:
java -jar janetsudoku.jar
java -cp janetsudoku.jar org.mariuszgromada.math.janetsudoku.demoapp.JanetSudoku

.NET (PowerShell):
[Reflection.Assembly]::LoadFile("full\exact\path\to\janetsudoku.dll")
[org.mariuszgromada.math.janetsudoku.demoapp.JanetSudoku]::Start()

* Running regression / unit tests:
    - just use provided exe / run files or hit commands listed below:

JAVA:
java -cp janetsudoku.jar org.mariuszgromada.math.janetsudoku.regtests.RegTests
java -cp janetsudoku.jar org.mariuszgromada.math.janetsudoku.regtests.RegTestsApi
java -cp janetsudoku.jar org.mariuszgromada.math.janetsudoku.regtests.RegTestsGenerator
java -cp janetsudoku.jar org.mariuszgromada.math.janetsudoku.regtests.RegTestsSolver
java -cp janetsudoku.jar org.mariuszgromada.math.janetsudoku.regtests.RegTestsStore
java -cp janetsudoku.jar org.mariuszgromada.math.janetsudoku.tutorial.Tutorial

.NET (PowerShell):
[Reflection.Assembly]::LoadFile("full\exact\path\to\janetsudoku.dll")
[org.mariuszgromada.math.janetsudoku.regtests.RegTests]::Start()
[org.mariuszgromada.math.janetsudoku.regtests.RegTestsApi]::Start()
[org.mariuszgromada.math.janetsudoku.regtests.RegTestsGenerator]::Start()
[org.mariuszgromada.math.janetsudoku.regtests.RegTestsSolver]::Start()
[org.mariuszgromada.math.janetsudoku.regtests.RegTestsStore]::Start()
[org.mariuszgromada.math.janetsudoku.tutorial.Tutorial]::Start()

                                              Best regards,
                                            Mariusz Gromada