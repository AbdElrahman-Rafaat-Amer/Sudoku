import com.abdelrahman.raafat.sudoku.isValidSudoku

/***
 * Class that has test cases that validate a List is valid Sudoku.
 * Sudoku Rules:
 *      1- ust not contain any repeated numbers in the same row, column, or 3x3 subgrid (box).
 *      2- Use the character '-' to represent empty cells within the puzzle.
 */

//                                    +-------+-------+-------+
//                                    | 5 3 . | . 7 . | . . . |
//                                    | 6 . . | 1 9 5 | . . . |
//                                    | . 9 8 | . . . | . 6 . |
//                                    +-------+-------+-------+
//                                    | 8 . . | . 6 . | . . 3 |
//                                    | 4 . . | 8 . 3 | . . 1 |
//                                    | 7 . . | . 2 . | . . 6 |
//                                    +-------+-------+-------+
//                                    | . 6 . | . . . | 2 8 . |
//                                    | . . . | 4 1 9 | . . 5 |
//                                    | . . . | . 8 . | . 7 9 |
//                                    +-------+-------+-------+

fun main() {

    // region ValidSudokuFormat
    validateSudoku(
        name = "Valid 9x9 Sudoku: Fully Filled and Correct",
        input = arrayOf(
            arrayOf("5", "3", "4", "6", "7", "8", "9", "1", "2"),
            arrayOf("6", "7", "2", "1", "9", "5", "3", "4", "8"),
            arrayOf("1", "9", "8", "3", "4", "2", "5", "6", "7"),
            arrayOf("8", "5", "9", "7", "6", "1", "4", "2", "3"),
            arrayOf("4", "2", "6", "8", "5", "3", "7", "9", "1"),
            arrayOf("7", "1", "3", "9", "2", "4", "8", "5", "6"),
            arrayOf("9", "6", "1", "5", "3", "7", "2", "8", "4"),
            arrayOf("2", "8", "7", "4", "1", "9", "6", "3", "5"),
            arrayOf("3", "4", "5", "2", "8", "6", "1", "7", "9")
        ),
        expected = true
    )

    validateSudoku(
        name = "Valid 3x3 Sudoku: Fully Empty Board",
        input = arrayOf(
            arrayOf("-", "-", "-"),
            arrayOf("-", "-", "-"),
            arrayOf("-", "-", "-")
        ),
        expected = true
    )

    validateSudoku(
        name = "Valid 10x10 Sudoku: Includes Empty Values",
        input = arrayOf(
            arrayOf("5", "3", "-", "-", "7", "-", "-", "-", "-", "-"),
            arrayOf("6", "-", "-", "1", "9", "5", "-", "-", "-", "-"),
            arrayOf("-", "9", "8", "-", "-", "-", "-", "6", "-", "-"),
            arrayOf("-", "-", "-", "-", "6", "-", "-", "-", "3", "-"),
            arrayOf("4", "-", "-", "8", "-", "3", "-", "-", "-", "1"),
            arrayOf("7", "-", "-", "-", "2", "-", "-", "-", "-", "6"),
            arrayOf("-", "6", "-", "-", "-", "-", "2", "8", "-", "-"),
            arrayOf("-", "-", "-", "4", "1", "9", "-", "-", "5", "-"),
            arrayOf("-", "-", "-", "-", "8", "-", "-", "7", "9", "-"),
            arrayOf("-", "-", "-", "-", "-", "-", "-", "-", "-", "10")
        ),
        expected = true
    )
    // endregion

    // region InvalidSudokuFormat
    validateSudoku(
        name = "Invalid Sudoku: Empty Board",
        input = arrayOf(),
        expected = false
    )

    validateSudoku(
        name = "Invalid Sudoku: Not a Perfect Square Grid like (3x3, 9x9)",
        input = arrayOf(
            arrayOf(" ", " ", " ")
        ),
        expected = false
    )


    validateSudoku(
        name = "Invalid Sudoku: Non-numeric values used (Chars)",
        input = arrayOf(
            arrayOf("a", "a", "a"),
            arrayOf("a", "a", "a"),
            arrayOf("a", "a", "a"),
        ),
        expected = false
    )

    validateSudoku(
        name = "Invalid Sudoku: The number(5) repeated in Row",
        input = arrayOf(
            arrayOf("5", "3", "4", "6", "7", "8", "9", "1", "5"),
            arrayOf("6", "7", "2", "1", "9", "5", "3", "4", "8"),
            arrayOf("1", "9", "8", "3", "4", "2", "5", "6", "7"),
            arrayOf("8", "5", "9", "7", "6", "1", "4", "2", "3"),
            arrayOf("4", "2", "6", "8", "5", "3", "7", "9", "1"),
            arrayOf("7", "1", "3", "9", "2", "4", "8", "5", "6"),
            arrayOf("9", "6", "1", "5", "3", "7", "2", "8", "4"),
            arrayOf("2", "8", "7", "4", "1", "9", "6", "3", "-"),
            arrayOf("3", "4", "5", "2", "8", "6", "1", "7", "9")
        ),
        expected = false
    )

    validateSudoku(
        name = "Invalid Sudoku: The number(5) repeated in Column",
        input = arrayOf(
            arrayOf("5", "3", "-", "-", "7", "-", "-", "-", "-", "-"),
            arrayOf("6", "-", "-", "1", "9", "5", "-", "-", "-", "-"),
            arrayOf("-", "9", "8", "-", "-", "-", "-", "6", "-", "-"),
            arrayOf("8", "-", "-", "-", "6", "-", "-", "-", "3", "-"),
            arrayOf("4", "-", "-", "8", "-", "3", "-", "-", "-", "1"),
            arrayOf("7", "-", "-", "-", "2", "-", "-", "-", "-", "6"),
            arrayOf("-", "6", "-", "-", "-", "-", "2", "8", "-", "-"),
            arrayOf("-", "-", "-", "4", "1", "9", "-", "-", "5", "-"),
            arrayOf("-", "-", "-", "-", "8", "-", "-", "7", "9", "-"),
            arrayOf("5", "-", "-", "-", "-", "-", "-", "-", "-", "-")
        ),
        expected = false
    )

    validateSudoku(
        name = "Invalid Sudoku: The number(5) repeated in Grid",
        input = arrayOf(
            arrayOf("5", "3", "4", "6", "7", "8", "9", "1", "2"),
            arrayOf("6", "7", "2", "1", "9", "5", "3", "4", "8"),
            arrayOf("1", "9", "5", "3", "4", "2", "_", "6", "7"),
            arrayOf("8", "5", "9", "7", "6", "1", "4", "2", "3"),
            arrayOf("4", "2", "6", "8", "5", "3", "7", "9", "1"),
            arrayOf("7", "1", "3", "9", "2", "4", "8", "5", "6"),
            arrayOf("9", "6", "1", "5", "3", "7", "2", "8", "4"),
            arrayOf("2", "8", "7", "4", "1", "9", "6", "3", "5"),
            arrayOf("3", "4", "_", "2", "8", "6", "1", "7", "9")
        ),
        expected = false
    )

    validateSudoku(
        name = "Invalid Sudoku: Out Of Range (The board 3x3 numbers range from 1-3, The number 4 used)",
        input = arrayOf(
            arrayOf("1", "2", "3"),
            arrayOf("3", "4", "2"),
            arrayOf("2", "3", "1"),
        ),
        expected = false
    )

    // endregion
}


private fun validateSudoku(name: String, input: Array<Array<String>>, expected: Boolean) {
//    val result = isValidSudoku(input)
//    println("Test case: name: $name, inputSize: ${input.size}-> Expected: $expected, Got: $result, ${if (result == expected) "✅ Passed" else "❌ Failed"}")
}
