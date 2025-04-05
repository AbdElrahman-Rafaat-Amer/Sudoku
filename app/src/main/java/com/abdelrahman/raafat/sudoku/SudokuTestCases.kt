package com.abdelrahman.raafat.sudoku

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
        input = listOf(
            listOf("5", "3", "4", "6", "7", "8", "9", "1", "2"),
            listOf("6", "7", "2", "1", "9", "5", "3", "4", "8"),
            listOf("1", "9", "8", "3", "4", "2", "5", "6", "7"),
            listOf("8", "5", "9", "7", "6", "1", "4", "2", "3"),
            listOf("4", "2", "6", "8", "5", "3", "7", "9", "1"),
            listOf("7", "1", "3", "9", "2", "4", "8", "5", "6"),
            listOf("9", "6", "1", "5", "3", "7", "2", "8", "4"),
            listOf("2", "8", "7", "4", "1", "9", "6", "3", "5"),
            listOf("3", "4", "5", "2", "8", "6", "1", "7", "9")
        ),
        expected = true
    )

    validateSudoku(
        name = "Valid 3x3 Sudoku: Fully Empty Board",
        input = listOf(
            listOf("-", "-", "-"),
            listOf("-", "-", "-"),
            listOf("-", "-", "-")
        ),
        expected = true
    )

    validateSudoku(
        name = "Valid 10x10 Sudoku: Includes Empty Values",
        input = listOf(
            listOf("5", "3", "-", "-", "7", "-", "-", "-", "-", "-"),
            listOf("6", "-", "-", "1", "9", "5", "-", "-", "-", "-"),
            listOf("-", "9", "8", "-", "-", "-", "-", "6", "-", "-"),
            listOf("-", "-", "-", "-", "6", "-", "-", "-", "3", "-"),
            listOf("4", "-", "-", "8", "-", "3", "-", "-", "-", "1"),
            listOf("7", "-", "-", "-", "2", "-", "-", "-", "-", "6"),
            listOf("-", "6", "-", "-", "-", "-", "2", "8", "-", "-"),
            listOf("-", "-", "-", "4", "1", "9", "-", "-", "5", "-"),
            listOf("-", "-", "-", "-", "8", "-", "-", "7", "9", "-"),
            listOf("-", "-", "-", "-", "-", "-", "-", "-", "-", "10")
        ),
        expected = true
    )
    // endregion

    // region InvalidSudokuFormat
    validateSudoku(
        name = "Invalid Sudoku: Empty Board",
        input = listOf(),
        expected = false
    )

    validateSudoku(
        name = "Invalid Sudoku: Not a Perfect Square Grid like (3x3, 9x9)",
        input = listOf(
            listOf(" ", " ", " ")
        ),
        expected = false
    )


    validateSudoku(
        name = "Invalid Sudoku: Non-numeric values used (Chars)",
        input = listOf(
            listOf("a", "a", "a"),
            listOf("a", "a", "a"),
            listOf("a", "a", "a"),
        ),
        expected = false
    )

    validateSudoku(
        name = "Invalid Sudoku: The number(5) repeated in Row",
        input = listOf(
            listOf("5", "3", "4", "6", "7", "8", "9", "1", "5"),
            listOf("6", "7", "2", "1", "9", "5", "3", "4", "8"),
            listOf("1", "9", "8", "3", "4", "2", "5", "6", "7"),
            listOf("8", "5", "9", "7", "6", "1", "4", "2", "3"),
            listOf("4", "2", "6", "8", "5", "3", "7", "9", "1"),
            listOf("7", "1", "3", "9", "2", "4", "8", "5", "6"),
            listOf("9", "6", "1", "5", "3", "7", "2", "8", "4"),
            listOf("2", "8", "7", "4", "1", "9", "6", "3", "-"),
            listOf("3", "4", "5", "2", "8", "6", "1", "7", "9")
        ),
        expected = false
    )

    validateSudoku(
        name = "Invalid Sudoku: The number(5) repeated in Column",
        input = listOf(
            listOf("5", "3", "-", "-", "7", "-", "-", "-", "-", "-"),
            listOf("6", "-", "-", "1", "9", "5", "-", "-", "-", "-"),
            listOf("-", "9", "8", "-", "-", "-", "-", "6", "-", "-"),
            listOf("8", "-", "-", "-", "6", "-", "-", "-", "3", "-"),
            listOf("4", "-", "-", "8", "-", "3", "-", "-", "-", "1"),
            listOf("7", "-", "-", "-", "2", "-", "-", "-", "-", "6"),
            listOf("-", "6", "-", "-", "-", "-", "2", "8", "-", "-"),
            listOf("-", "-", "-", "4", "1", "9", "-", "-", "5", "-"),
            listOf("-", "-", "-", "-", "8", "-", "-", "7", "9", "-"),
            listOf("5", "-", "-", "-", "-", "-", "-", "-", "-", "-")
        ),
        expected = false
    )

    validateSudoku(
        name = "Invalid Sudoku: The number(5) repeated in Grid",
        input = listOf(
            listOf("5", "3", "4", "6", "7", "8", "9", "1", "2"),
            listOf("6", "7", "2", "1", "9", "5", "3", "4", "8"),
            listOf("1", "9", "5", "3", "4", "2", "_", "6", "7"),
            listOf("8", "5", "9", "7", "6", "1", "4", "2", "3"),
            listOf("4", "2", "6", "8", "5", "3", "7", "9", "1"),
            listOf("7", "1", "3", "9", "2", "4", "8", "5", "6"),
            listOf("9", "6", "1", "5", "3", "7", "2", "8", "4"),
            listOf("2", "8", "7", "4", "1", "9", "6", "3", "5"),
            listOf("3", "4", "_", "2", "8", "6", "1", "7", "9")
        ),
        expected = false
    )

    validateSudoku(
        name = "Invalid Sudoku: Out Of Range (The board 3x3 numbers range from 1-3, The number 4 used)",
        input = listOf(
            listOf("1", "2", "3"),
            listOf("3", "4", "2"),
            listOf("2", "3", "1"),
        ),
        expected = false
    )

    // endregion
}

private fun validateSudoku(name: String, input: List<List<String>>, expected: Boolean) {
    val viewModel = SudokuViewModel()
    val result = viewModel.isValidSudoku(input)
    println("Test case: name: $name, inputSize: ${input.size}-> Expected: $expected, Got: $result, ${if (result == expected) "✅ Passed" else "❌ Failed"}")
}
