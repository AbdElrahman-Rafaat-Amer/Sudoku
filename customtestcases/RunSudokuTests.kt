package customtestcases

import com.abdelrahman.raafat.sudoku.SudokuViewModel
import kotlin.system.exitProcess

fun main() {

    val testCases = listOf(
        TestCase(
            name = "Valid 3x3 Sudoku: Fully Filled and Correct",
            input = listOf(
                listOf("1", "2", "3"),
                listOf("2", "3", "1"),
                listOf("3", "1", "3"),
            ),
            expected = true
        ),
        TestCase(
            name = "Invalid Sudoku: The number(5) repeated in Row",
            input = listOf(
                listOf("5", "3", "4", "6", "7", "8", "9", "1", "-"),
                listOf("6", "7", "2", "1", "9", "5", "3", "4", "8"),
                listOf("1", "9", "8", "3", "4", "2", "5", "6", "7"),
                listOf("8", "5", "9", "7", "6", "1", "4", "2", "3"),
                listOf("4", "2", "6", "8", "5", "3", "7", "9", "1"),
                listOf("7", "1", "3", "9", "2", "4", "8", "5", "6"),
                listOf("9", "6", "1", "5", "3", "7", "2", "8", "4"),
                listOf("2", "8", "7", "4", "1", "9", "6", "3", "-"),
                listOf("3", "4", "5", "2", "8", "6", "1", "7", "9")
            ),
            expected = true
        )
        // ✅ Add the rest of your test cases here
    )

    val viewModel = SudokuViewModel()
    var failed = false

    testCases.forEach { test ->
        val result = viewModel.isValidSudoku(test.input)
        val passed = result == test.expected
        println("Test: ${test.name} — Expected: ${test.expected}, Got: $result -> ${if (passed) "✅ Passed" else "❌ Failed"}")
        if (!passed) failed = true
    }

    if (failed) {
        println("Some tests failed.")
        exitProcess(1)
    } else {
        println("All tests passed!")
    }
}

data class TestCase(
    val name: String,
    val input: List<List<String>>,
    val expected: Boolean
)
