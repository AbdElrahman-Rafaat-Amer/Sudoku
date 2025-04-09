package com.abdelrahman.raafat.sudoku

fun main() {
    val results = listOf(
        validateSudoku("Valid 3x3 Sudoku: Fully Filled and Correct", listOf(
            listOf("1", "2", "3"),
            listOf("2", "3", "1"),
            listOf("3", "1", "2"),
        ), expected = true),

        validateSudoku("Invalid Sudoku: The number(5) repeated in Row", listOf(
            listOf("5", "3", "4", "6", "7", "8", "9", "1", "-"),
            listOf("6", "7", "2", "1", "9", "5", "3", "4", "8"),
            listOf("1", "9", "8", "3", "4", "2", "5", "6", "7"),
            listOf("8", "5", "9", "7", "6", "1", "4", "2", "3"),
            listOf("4", "2", "6", "8", "5", "3", "7", "9", "1"),
            listOf("7", "1", "3", "9", "2", "4", "8", "5", "6"),
            listOf("9", "6", "1", "5", "3", "7", "2", "8", "4"),
            listOf("2", "8", "7", "4", "1", "9", "6", "3", "-"),
            listOf("3", "4", "5", "2", "8", "6", "1", "7", "9")
        ), expected = true)

        // Add all your other test cases here...
    )

    if (!results.all { it }) {
        println("❌ Some test cases failed.")
        kotlin.system.exitProcess(1)
    } else {
        println("✅ All test cases passed.")
    }
}

private fun validateSudoku(name: String, input: List<List<String>>, expected: Boolean): Boolean {
    val viewModel = SudokuViewModel()
    val result = viewModel.isValidSudoku(input)
    val passed = result == expected
    println("Test case: $name → Expected: $expected, Got: $result → ${if (passed) "✅ Passed" else "❌ Failed"}")
    return passed
}
